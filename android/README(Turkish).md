# İşaret Dili Çevirici

Bu belge, cihaz kamerasını kullanarak görüntü sınıflandırması yapan bir Android mobil uygulamasının kodunu gösterir.

##  Algoritma Dökümantasyonu

Şimdi örnek kodun en önemli kısımlarını inceleyeceğiz.

### Kameradan Görüntü Alınması

Bu mobil uygulama kamera girişini [`CameraActivity.java`](https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/documentation/android/app/src/main/java/org/tensorflow/lite/examples/classification/CameraActivity.java) dosyasında tanımlanan fonksiyonları kullanarak alır. Bu dosya kamera izinlerini ayarlamak için [`AndroidManifest.xml`](https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/documentation/android/app/src/main/AndroidManifest.xml) dosyasına bağlıdır.

`CameraActivity` ayrıca kullanıcı arayüzünden kullanıcı tercihlerini yakalayarak diğer sınıfların kullanımını sağlar.

```java
device = Device.valueOf(deviceSpinner.getSelectedItem().toString());
numThreads = Integer.parseInt(threadsTextView.getText().toString().trim());
```

### Sınıflandırıcı

[`Classifier.java`](https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/documentation/android/app/src/main/java/org/tensorflow/lite/examples/classification/tflite/Classifier.java) dosyası, kamera girişini işlemek ve çıkarım yapmak için karmaşık mantığın çoğunu içerir.

`Classifier.java` dosyasının Hem kayan nokta hem de 'nicelenmiş' modellerin kullanımını göstermek için [`ClassifierFloatMobileNet.java`](https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/documentation/android/app/src/main/java/org/tensorflow/lite/examples/classification/tflite/ClassifierFloatMobileNet.java) ve [`ClassifierQuantizedMobileNet.java`](https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/documentation/android/app/src/main/java/org/tensorflow/lite/examples/classification/tflite/ClassifierQuantizedMobileNet.java) olmak üzere iki alt sınıfı mevcuttur.

`Classifier` sınıfı, sağlanan türe göre (kayan noktaya göre niceliklendirilmiş) uygun alt sınıfı başlatmak için kullanılan statik bir yöntem olan "create" komutunu uygular. 

#### Model ve Yorumlayıcı

Çıkarım yapmak için bir model dosyası yüklememiz ve bir yorumlayıcı başlatmamız gerekir. Bu olay, `Classifier` sınıfının 'Constructor' metodunda gerçekleştirirlir. Cihaz tipi ve iş parçacığı sayısı hakkındaki bilgiler, constructor metoduna aktarılan `Interpreter.Options`  nesnesi aracılığıyla yorumlayıcıyı yapılandırmak için kullanılır. Bir GPU, DSP (Dijital Sinyal İşlemcisi) veya NPU (Sinir İşleme Ünitesi) varsa, bu donanımdan tam olarak yararlanmak için bir `Delegate` kullanılabilir. Bu seçenekler daha fazla performans sağlayabilirken, aynı zamanda cihazın güç tüketimini attırmaktadır.

```java
protected Classifier(Activity activity, Device device, int numThreads) throws
    IOException {
  tfliteModel = FileUtil.loadMappedFile(activity, getModelPath());
  switch (device) {
    case NNAPI:
      nnApiDelegate = new NnApiDelegate();
      tfliteOptions.addDelegate(nnApiDelegate);
      break;
    case GPU:
      gpuDelegate = new GpuDelegate();
      tfliteOptions.addDelegate(gpuDelegate);
      break;
    case CPU:
      break;
  }
  tfliteOptions.setNumThreads(numThreads);
  tflite = new Interpreter(tfliteModel, tfliteOptions);
  labels = FileUtil.loadLabels(activity, getLabelPath());
...
```

Daha hızlı yükleme süreleri sunmak ve bellekteki kirli sayfaları azaltmak için model dosyasını önceden yüklemeyi ve bellek eşlemeyi gerçekleştirmek için `FileUtil.loadMappedFile` yöntemi kullanılır. Bu yöntem modeli içeren bir“ MappedByteBuffer ”döndürür.

"MappedByteBuffer", bir "Interpreter.Options" nesnesi ile birlikte "Interpreter" yapıcısına iletilir. Bu nesne, yorumlayıcıyı yapılandırmak için kullanılabilir, örneğin iş parçacığı sayısını ayarlayarak (`.setNumThreads (1)`) veya [NNAPI] (`.addDelegate (nnApiDelegate)`) etkinleştirerek.

#### İşlem Öncesi Bitmap Görüntüsü

`Classifier` constructor ile giriş kameradan bitmap görüntüsünü alıyoruz, verimli işleme için bir `TensorImage` formatına dönüştürüyor ve ön işlemler yapıyoruz. Adımlar 'private loadImage' metoodunda gösterilirmektedir:


```java
/** Loads input image, and applys preprocessing. */
private TensorImage loadImage(final Bitmap bitmap, int sensorOrientation) {
  // Loads bitmap into a TensorImage.
  image.load(bitmap);

  // Creates processor for the TensorImage.
  int cropSize = Math.min(bitmap.getWidth(), bitmap.getHeight());
  int numRoration = sensorOrientation / 90;
  ImageProcessor imageProcessor =
      new ImageProcessor.Builder()
          .add(new ResizeWithCropOrPadOp(cropSize, cropSize))
          .add(new ResizeOp(imageSizeX, imageSizeY, ResizeMethod.BILINEAR))
          .add(new Rot90Op(numRoration))
          .add(getPreprocessNormalizeOp())
          .build();
  return imageProcessor.process(inputImageBuffer);
}
```

Normalleştirme hariç ön yükleme işlemi `ClassifierFloatMobileNet` (nicelenmiş) ve `ClassifierQuantizedMobileNet` (Kayan nokta) modelleri için büyük ölçüde aynıdır.

`ClassifierFloatMobileNet`'te normalleştirme parametreleri şu şekilde tanımlanır:

```java
private static final float IMAGE_MEAN = 127.5f;
private static final float IMAGE_STD = 127.5f;
```

`ClassifierQuantizedMobileNet`'te normalleştirme gerekli değildir. Böylece, nomalizasyon parametreleri şu şekilde tanımlanır:

```java
private static final float IMAGE_MEAN = 0.0f;
private static final float IMAGE_STD = 1.0f;
```

#### Çıktı Nesnesi

Modelin çıkışı için `TensorBuffer`çıkışının başlatılması gerekmektedir. Bu işlem:

```java
/** Output probability TensorBuffer. */
private final TensorBuffer outputProbabilityBuffer;

//...
// Get the array size for the output buffer from the TensorFlow Lite model file
int probabilityTensorIndex = 0;
int[] probabilityShape =
    tflite.getOutputTensor(probabilityTensorIndex).shape(); // {1, 1001}
DataType probabilityDataType =
    tflite.getOutputTensor(probabilityTensorIndex).dataType();

// Creates the output tensor and its processor.
outputProbabilityBuffer =
    TensorBuffer.createFixedSize(probabilityShape, probabilityDataType);

// Creates the post processor for the output probability.
probabilityProcessor =
    new TensorProcessor.Builder().add(getPostprocessNormalizeOp()).build();
```
Nicemlenmiş modeller için, tahminleri NormalizeOp ile nicelleştirmemiz gerekir. Kayan Nokta modeli için nicemsizleştirme gerekli değildir. Daha spesifik olmak gerekirse,

`ClassifierQuantizedMobileNet`, içinde normalleştirilmiş parametreler şu şekilde tanımlanır:


```java
private static final float PROBABILITY_MEAN = 0.0f;
private static final float PROBABILITY_STD = 255.0f;
```

`ClassifierFloatMobileNet`, içinde normalleştirilmiş parametreler şu şekilde tanımlanır:

```java
private static final float PROBABILITY_MEAN = 0.0f;
private static final float PROBABILITY_MEAN = 1.0f;
```

#### Sonuç Çıkarımlama 

Çıkarım, `Classifier` sınıfında aşağıdakiler kullanılarak gerçekleştirilir:

```java
tflite.run(inputImageBuffer.getBuffer(),
    outputProbabilityBuffer.getBuffer().rewind());
```

#### Görüntü tanımlama

Doğrudan `run` çağırmak yerine, `recognizeImage` metotu kullanılır. Bir bitmap ve sensör yönelimini kabul eder, çıkarım yapar ve her biri bir etikete karşılık gelen sıralı `Recognition` örnekleri listesini döndürür. Yöntem, `MAX_RESULTS` ile sınırlı bir dizi sonuç döndürür, varsayılan olarak 3 değerindedir.

`Recognition`, belirli bir tanıma sonucu hakkında bilgi içeren basit bir sınıftır, buna `title` and `confidence` bilgileride dahildir. Tanımlama işlemi sonrasında değerler 0 ila 1 arasında bir değer alır. Daha sonra bu değerlere sabit olan nesneler sıralanır.

```java
/** Gets the label to probability map. */
Map<String, Float> labeledProbability =
    new TensorLabel(labels,
        probabilityProcessor.process(outputProbabilityBuffer))
        .getMapWithFloatValue();
```

Sıralama için bir `PriorityQueue` metotu kullanılır.

```java
/** Gets the top-k results. */
private static List<Recognition> getTopKProbability(
    Map<String, Float> labelProb) {
  // Find the best classifications.
  PriorityQueue<Recognition> pq =
      new PriorityQueue<>(
          MAX_RESULTS,
          new Comparator<Recognition>() {
            @Override
            public int compare(Recognition lhs, Recognition rhs) {
              // Intentionally reversed to put high confidence at the head of
              // the queue.
              return Float.compare(rhs.getConfidence(), lhs.getConfidence());
            }
          });

  for (Map.Entry<String, Float> entry : labelProb.entrySet()) {
    pq.add(new Recognition("" + entry.getKey(), entry.getKey(),
               entry.getValue(), null));
  }

  final ArrayList<Recognition> recognitions = new ArrayList<>();
  int recognitionsSize = Math.min(pq.size(), MAX_RESULTS);
  for (int i = 0; i < recognitionsSize; ++i) {
    recognitions.add(pq.poll());
  }
  return recognitions;
}
```

### Sonuçların Görüntülenmesi

Classifier sınıfı çağrılır ve sonuçlar çıkartılır. Çıkarılan sonuçlar [`ClassifierActivity.java`](https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/documentation/android/app/src/main/java/org/tensorflow/lite/examples/classification/tflite/Classifier.java) içindeki `processImage()` fonksiyonu tarafından görüntülenir.

`ClassifierActivity`, kamera görüntüsünü oluşturan, sınıflandırmayı çalıştıran ve sonuçları görüntüleyen yöntem uygulamalarını içeren `CameraActivity`'nin bir alt sınıfıdır. `ProcessImage()` fonksiyonu, iş parçacığında yapılan sınıflandırmayı olabildiğince hızlı çalıştırır, çıkarımın engellenmesini ve gecikmenin oluşmasını önlemek için UI iş parçacığında bilgi oluşturur.

```java
@Override
protected void processImage() {
  rgbFrameBitmap.setPixels(getRgbBytes(), 0, previewWidth, 0, 0, previewWidth,
      previewHeight);
  final int imageSizeX = classifier.getImageSizeX();
  final int imageSizeY = classifier.getImageSizeY();

  runInBackground(
      new Runnable() {
        @Override
        public void run() {
          if (classifier != null) {
            final long startTime = SystemClock.uptimeMillis();
            final List<Classifier.Recognition> results =
                classifier.recognizeImage(rgbFrameBitmap, sensorOrientation);
            lastProcessingTimeMs = SystemClock.uptimeMillis() - startTime;
            LOGGER.v("Detect: %s", results);

            runOnUiThread(
                new Runnable() {
                  @Override
                  public void run() {
                    showResultsInBottomSheet(results);
                    showFrameInfo(previewWidth + "x" + previewHeight);
                    showCropInfo(imageSizeX + "x" + imageSizeY);
                    showCameraResolution(imageSizeX + "x" + imageSizeY);
                    showRotationInfo(String.valueOf(sensorOrientation));
                    showInference(lastProcessingTimeMs + "ms");
                  }
                });
          }
          readyForNextImage();
        }
      });
}
```

`ClassifierActivity` nin bir diğer önemli rolü, kullanıcı tercihlerini belirlemek (`CameraActivity` yi sorgulayarak) ve uygun şekilde yapılandırılmış `Classifier` alt sınıfını başlatmaktır. Bu, video feed'i başladığında (`onPreviewSizeChosen()` aracılığıyla) ve kullanıcı arayüzünde seçenekler değiştirildiğinde (`onInferenceConfigurationChanged()` aracılığıyla) gerçekleşir.

```java
private void recreateClassifier(Model model, Device device, int numThreads) {
  if (classifier != null) {
    LOGGER.d("Closing classifier.");
    classifier.close();
    classifier = null;
  }
  if (device == Device.GPU && model == Model.QUANTIZED) {
    LOGGER.d("Not creating classifier: GPU doesn't support quantized models.");
    runOnUiThread(
        () -> {
          Toast.makeText(this, "GPU does not yet supported quantized models.",
              Toast.LENGTH_LONG)
              .show();
        });
    return;
  }
  try {
    LOGGER.d(
        "Creating classifier (model=%s, device=%s, numThreads=%d)", model,
        device, numThreads);
    classifier = Classifier.create(this, model, device, numThreads);
  } catch (IOException e) {
    LOGGER.e(e, "Failed to create classifier.");
  }
}
```
