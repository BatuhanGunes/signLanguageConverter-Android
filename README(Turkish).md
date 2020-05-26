**Dil :** [ingilizce](https://github.com/BatuhanGunes/signLanguageConverter-Android) / Türkçe

# İşaret Dili Çeviricisi

### Ön Söz
Bu proje, bilgisayarlı görü ile derin öğrenme yöntemlerini kullanarak işaret dili bilmeyen bir insan ile işaret dili ile iletişim kuran bir insanın arasındaki iletişim problemlerini ortadan kaldırmaya çalışmaktadır. İşaret dili bilmeyen kullanıcı telefonundaki bu uygulama sayesinde işaret dili ile yapılan hareketlerin anlamlarını algılayabilmektedir. Bunun için uygulama açılır ve işaret dili kullanan kişinin görüntüsü alınır. Bu görüntü üzerinde canlı olarak derin öğrenme yöntemleri ile işaretlerin anlamları çıkartılır. Bu anlamlar diğer kullanıcıya sesli veya yazılı olarak aktarılır. Aynı zamanda işaret dili sözlüğü olabilicek şekilde işaretlerin anlamları farklı bir sayfada kullanıcıya sunulmaktadır. 

Ayrıca bu belgelerede bakabilirsiniz:
- Algoritma ile ilgili açıklamaları [Explore The_Code](https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/documentation/android/README(Turkish).md) sayfasını inceleyebilirsiniz.
- Model ile ilgili açıklamaları [Explore The_Model](https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/Model/model/README.md) sayfasını inceleyebilirsiniz.
- Proje hakkında teknik ve detaylı bilgi almak için [Sing Language Description](https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/master/android/SingLanguageDescription.pdf) sayfasını inceleyebilirsiniz. 

### Model

Modelimiz Türkçe alfabenin işaret dilinin datasetini içerdiği ve MobileNet modelini base alarak, yaklaşık 15 saat boyunca eğitildiği CNN(Konvolüsyonel Sinir Ağları) yapısında bir image classification(Resim Sınıflandırma) işlemi yapan deep learnning(Derin öğrenme) gerçekleştirmiş bir modeldir.

## Akış Diyagramı

<img align="center" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/master/android/images/flow_diagram.png">

## Ekran Görüntüleri

<img align="center" width="220" height="350" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/master/android/images/main.png">  <img align="center" width="220" height="350" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/master/android/images/c.png"> <img align="center" width="220" height="350" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/master/android/images/e.png">  <img align="center" width="220" height="350" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/master/android/images/d.png">

<img align="center" width="220" height="350" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/master/android/images/j.png">  <img align="center" width="220" height="350" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/master/android/images/k.png">  <img align="center" width="220" height="350" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/master/android/images/a.png">   <img align="center" width="220" height="350" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/master/android/images/apk.gif">

## Başlangıç

Projeyi çalıştırabilmek için proje dosyalarının bir kopyasını yerel makinenize indirin. Gerekli ortamları edindikten sonra projeyi bu ortamda açarak çalıştırabilirsiniz. Programı bir android cihaz üzerinde çalıştırabilirsiniz.

### Gereklilikler

* Android Studio 3.2 (Linux, Mac veya Windows makinesine yüklenmiş)

* [`Geliştirici modu`](https://developer.android.com/studio/debug/dev-options) ve USB hata ayıklama seçeneği etkin olan android cihaz

* USB kablosu (cihazı bilgisayarınıza bağlamak için)

## İnşa et ve Çalıştır

### Adım 1. sign Language Converter kaynak kodunu kopyalayın

Uygulamayı almak için GitHub deposunu bilgisayarınıza kopyalayın.

```
git clone https://github.com/https://github.com/BatuhanGunes/signLanguageConverter-Android.git
```

Android Studio'da signLanguageConverter kaynak kodunu açın. Bunu yapmak için Android Studio'yu açın ve `signLanguageConverter/android/` yolunu izleyin.

<img src="android/images/classifydemo_img1.png?raw=true" />

### Adım 2. Android Studio projesini oluşturma

`Build -> Make Project` ı seçin ve projenin başarıyla oluşturulduğunu kontrol edin. Android SDK sürümünü projenin ayarlarını yapılandırarak elde etmeniz gerekecek. En az 23. sürüm olan SDK'ya ihtiyacınız olacak. Gerekli yapılandırmaları yaptıktan sonra `build.gradle` dosyası eksik olan kütüphaneleri indirmenizi ister.

<img src="android/images/classifydemo_img4.png?raw=true" style="width: 40%" />

<img src="android/images/classifydemo_img2.png?raw=true" style="width: 60%" />

<aside class="note"><b>Not:</b><p>`build.gradle` TensorFlow Lite'ın nightly sürümünü kullanmak üzere yapılandırıldı. Tensorflow Lite'ın Java API'sı ile ilgili bir derleme veya uyumluluk hatası görürseniz (örneğin, `method X is undefined for type Interpreter`), büyük olasılıkla API'de geriye dönük olarak bir değişiklik olmuştur. Böyle bir durumda [TensorFlow Lite android examples](https://github.com/tensorflow/examples/tree/master/lite/examples/image_classification/android) deposunu klonlamanız ve gerçekleşen değişiklikleri bu projeye dahil etmeniz gerekmektedir.

### Step 3. Uygulamayı yükleme ve çalıştırma

Android cihazı bilgisayara bağlayın ve telefonunuzda görünen ADB izin istemlerini onayladığınızdan emin olun. `Run -> Run app.` seçin. Bağlı cihazlarda uygulamanın yükleneceği cihaza dağıtım hedefini seçin. Bu, uygulamayı cihaza yükleyecektir.

<img src="android/images/classifydemo_img5.png?raw=true" style="width: 60%" />

<img src="android/images/classifydemo_img6.png?raw=true" style="width: 70%" />

<img src="android/images/classifydemo_img7.png?raw=true" style="width: 40%" />

<img src="android/images/classifydemo_img8.png?raw=true" style="width: 80%" />

Uygulamayı test etmek için cihazınızda `İşaret Dili Çevirici` adlı uygulamayı açın. Uygulama ilk kez açıldığında, kameraya erişmek için izin isteyecektir. Uygulamanın yeniden yüklenmesi, önceki yüklemelerin kaldırılmasını gerektirebilir.

## Yazarlar

* **Batuhan Güneş**  - [BatuhanGunes](https://github.com/BatuhanGunes)
* **Hasan Hüseyin Öztunç**  - [hasanhoztunc](https://github.com/hasanhoztunc)
* **Muhammed Emin Berkay Kocaoğlu**  - [mebon](https://github.com/mebon)

Ayrıca, bu projeye katılan ve katkıda bulunanlara [contributors](https://github.com/BatuhanGunes/signLanguageConverter-Android/graphs/contributors) listesinden ulaşabilirsiniz.

## Lisans

Bu proje Apache lisansı altında lisanslanmıştır - ayrıntılar için [LICENSE.md](https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/master/LICENSE) dosyasına bakabilirsiniz.

