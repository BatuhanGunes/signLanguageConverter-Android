# Model
Modelimiz Türkçe alfabenin işaret dilinin datasetini içerdiği ve MobileNet modelini base alarak, yaklaşık 15 saat boyunca eğitildiği CNN(Konvolüsyonel Sinir Ağları) yapısında bir image classification(Resim Sınıflandırma) işlemi yapan deep learnning(Derin öğrenme) gerçekleştirmiş bir modeldir.
# Dataset ve Model Olusturma  
1-[Dataset Oluşturma](#dataset-oluşturma)  
2-[Data Augmentation](#data-augmentation)  
3-[Model Oluşturma](#model-oluşturma)  
4-[Model Test Etme](#model-test-etme)  
5-[Model Dönüştürme](#model-dönüştürme)  

## Dataset Oluşturma
  Kendi çektiğimiz ve internet üzerinden bulduğumuz türkçe işaret dili alfabesi resimlerini öncelikle bilgisayar ortamında kayıt ettik. Ardından herbir resmi içerdiği harflere göre ayrı klasörlere koyduk. Klasörleme işleminde bittikten sonra [PhotoScape](http://www.photoscape.org/ps/main/index.php) programı yardımı ile resimlerin isimlerini içerdiği harfler olarak değiştirdik. İsimlendirme işlemi bittikten sonra PhotoScape programı ile resimleri 224x224 piksel boyutuna dönüştürdük. Herbir harf klasöründe 80 adet farklı şekilde çekilmiş ve isimlendirme ile boyutlandırma işlemlerinin gerçekliştiği resimlerimiz veri çoğaltma yani [data augmentation](#data-augmentation) işlemini yapmaya hazır hale geldi. Data augmentation ile her resimde yakınlaştırma işlemi, döndürme işlemi, parlaklık değiştirme işlemi gibi işlemler gerçekleştirerek 80 ana resimden 4800 farklı resim verisi elde ettik. Bu sayede toplamda 119.400 adet eğitim verimizin ve 47.796 adet test verimizin bulunduğu veri setimiz hazır hale geldi.  
  Hazırladığımız [datasetimizi](https://www.kaggle.com/berkaykocaoglu/tr-sign-language) yapay zeka, makine öğrenimi gibi alanlarda kullanılan diğer datasetlerin bulunduğu [kaggle](https://www.kaggle.com/) adlı siteye yükleyerek ihtiyacı olan insanların erişebilmesi için paylaştık.  
  <img align="center" width="1000" height="600" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/Model/model/tutorial_images/trsignlanuage.png">
## Data Augmentation
Bir önceki başlıkta([Dataset Oluşturma](#dataset-oluşturma)) biraz bahsetmiştik. 80 ana resimden 4800 farklı resim verisi elde etmek için kullandığımızı belirtmiştik. Bu yöntem elde bulunan datasetteki verilerin az olduğundan ve bunun model eğitimi için bir sıkıntı olacağından verileri çoğaltmak için kullanılır. Bizim projemizde de resimleri tek bir şekilde tanıtmak yerine resimler üzerinde oynayarak onların farklı açılarda, mesafede, yönde çoğaltıp daha iyi bir veri seti ile daha iyi bir eğitim sağlamak için kullandık.    
Bu işlemde ImageDataGenerator ile resimlerin hangi oranlarla dönüştüreleceğini belirledik. Oranlar sırasıyla  
-En fazla 40 derecelik açıda herhangi bir yöne döndürme  
-Genişliği 1/5 oranla kaydırma  
-YÜkseliği 1/5 oranla kaydırma  
-Parlaklığı 0.2 ve 1.0 oranları arasında değiştirme  
-Resim yakınlığını 0.5 ve 1.0 oranları arasında değiştirme  
-Bir kenardan 1/5 oranla resmi kırpma  
-Bu işlemler oluşan boşlukları en yakın olacak şekilde kapatma olarak gerçekleştirdik. 
Bu işlemler her resim için uygulandı ama bir resim için tüm bu özelliklerinin hepsi uygulamadık.. 1000 adet resimde yakınlaştırma işlemi varken parlaklık değiştirme işlemi varken diğerlerinde kullanılmayarak, verilerin farklı şekilde çoğalmalarını sağladık.
```
imgProperties = ImageDataGenerator(
        rotation_range=40,
        width_shift_range=0.2,
        height_shift_range=0.2,
        brightness_range=[0.2,1.0],
        zoom_range=[0.5,1.0],
        shear_range=0.2,
        horizontal_flip=True,
        fill_mode='nearest')
```  
   Aşağıdaki işlemde ise öncelikle çoğaltılacak resimlerinin klasör isimlerini bir listeye ekledik. Ardından bu liste ile döngüyü başlatıp, herbir resmin ImageDataGenerator metoduna girmesini sağladık. Metoda giren resimler işlem uygulandıktan sonra belirtlen yollara kaydettik.  
```
def dataAugRun():
    letters = ['A','B','C','D','E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'R', 'S', 'T', 'U', 'V', 'Y', 'Z']
    for l in letters:
        for nmr in range(1,31):
            fileDir = 'tr_signLanguage_dataset/dataAugImg'
            lttr = l
            mainFileName = '{}.{}'.format(lttr,nmr)
            img = load_img('{}/{}/{}.jpg'.format(fileDir,lttr,mainFileName))
            x = img_to_array(img)
            x = x.reshape((1,) + x.shape)

            i = 0
            for batch in imgProperties.flow(x, batch_size=1,
                                            save_to_dir='tr_signLanguage_dataset/train/{}/'.format(lttr), save_prefix='{}'.format(lttr), save_format='jpg'):
                i += 1
                if i > 100:
                    break  # otherwise the generator would loop indefinitely

            i = 0
            for batch in imgProperties.flow(x, batch_size=1,
                                            save_to_dir='tr_signLanguage_dataset/validation/{}/'.format(lttr), save_prefix='{}'.format(lttr), save_format='jpg'):
                i += 1
                if i > 30:
                    break
```
## Model Oluşturma
### Dataseti Dahil Etmek
```
train_dir = os.path.join('tr_signLanguage_dataset/train')
validation_dir = os.path.join('tr_signLanguage_dataset/validation')
train_batches = ImageDataGenerator().flow_from_directory(train_dir,target_size=(img_width,img_height), classes=['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','R','S','T','U','V','Y','Z','del','nothing','space'],batch_size=trainBatchSize)
valid_batches = ImageDataGenerator().flow_from_directory(validation_dir,target_size=(img_width,img_height), classes=['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','R','S','T','U','V','Y','Z','del','nothing','space'],batch_size=validBatchSize)
```
Öncelikle daha önceden hazırladığımız datasetimizi sisteme dahil ettik. Train yani eğitim verilerimizi train_dir'e atadık ve test yani validation verilerimizi de validation_dir'e atadık. Ardından ImageDataGenerator resim seçme bunların ayarlarını yapma metodunda bulunan flow_from_directory ile de verilerimizi hangi sınıflardan(classes=) alacağını ve her seferde kaç adet alacağını(batch_size) ve boyutlarının(target_size) belirledik. Bunlardaki amaçlar ise;  
-Bir model eğitilirken dosya boyutu 100,100 - 128,128 - 224,224 gibi farklı değerler alınabilir ama mobilenetin de kullandığı ve resimlerin daha iyi eğitilmesini sağlayan tavsiye edilen boyut 224,224 olduğu için biz de bu şekidle ayarladık.  
-Bir modelde batch size her eğitim aşamasında bir arada alıp eğitilen veri sayısıdır. Batch size düşük olursa, eğitim hızlanır fakat yüksek hata oranları alınır. Eğer batch size yüksek olursa daha fazla veri bir arada alınacağı için daha iyi bir eğitim gerçekleşir, hata oranı azalır ama eğitim daha uzun sürede tamamlanır. Biz de bu yüzden batch size'ı 128 yapıp iyiye yakın bir eğitim gerçekleştirmeyi düşündük.  
### MobileNet'i Dahil Etmek
```
base_model = MobileNet(
    weights='imagenet',
    include_top=False,
    input_shape=IMG_SHAPE,
    pooling='avg',
)
base_model.trainable = True
```
Mobilenet google tarafından geliştirilmiş, minimum bellek ve kaynak kullanmasına rağmen yüksek isabet oranına sahip bir sinir ağı mimarisi yani bir modeldir. Daha çok mobil ve gömülü sistemler için uygundur. Biz bu MobileNet'i projemize dahil ederek hem oluşturduğumuz modelin dosya boyutunun düşmesini, hem android platformda çalışmasını, hem de eğitim işleminin hızlanmasını sağladık.
### Model Eğitimini Ayarlamak
```
model = Sequential()
model.add(base_model)
model.add(Dropout(0.25))
model.add(Dense(26, activation='softmax'))
model.summary()

stepsPerEpoch = numpy.ceil(train_batches.n/4000)
validationSteps= numpy.ceil(valid_batches.n/2000)
model.compile(Adam(lr=.0001), loss='categorical_crossentropy',metrics = ['accuracy'])
```
Veri setimizi dahil edip ayarladıktan sonra ve MobileNet'i dahil ettikten sonra model eğitimine başlama aşamasına geldik.  
-Önce Sequential metodu ile modelimizin eğitilecek bir yapı olduğunu tanımladık.  
-.add(base_model) ile MobileNet modelimizi kullanabilmek için kendi yapacağımız modelin içerisine ekledik.  
-.add(Dropout(0.25) ile de her eğitim adımında 4 nörondan 1 nöronu kapatıp işlemi bu şekilde gerçekleştirmesini istedik. Dropout işlemi overfittingi yani modelin sürekli aynı yollarla eğitilmesinden kaynaklı hataların engellemesini sağlar. Optimum sonuçlar almak için kullanılan bir iyileştirme metotudur.  
-.add(Dense(26, activation='softmax')) ile de önce 26 adet sınıfımız olduğu için 26 adet nöron oluşturduk. Daha sonra da activation fonksiyonu olarak da softmax'i tanımladık. Yapay sinir ağı modellerinin çıktı olarak verdiği skor değerler normalize edilmemiş değerlerdir. Aktivasyon fonksiyonu olan softmax bu değerleri normalize ederek olasılık değerlerine dönüştürmektedir. 
-.summary() ile yapımızı özetledik.  
-stepsPerEpoch her eğitim adımında  kaç adım olacağını belirledik ve validationSteps ise test atımlarını belirledik.  
-son olarak da compile hata oranlarını azalmatısını saplaması için optimizasyon fonksiyonu olan Adam fonksiyonunu, hatalı verilerin zamanla 0'a yaklaşması için cross entropy fonksiyonunu ekledik. metrics ile de oranları ekrana yazdırmayı ayarladık.  
### Checkpoint Ayarlamak
```
checkDir = 'checkpoints'
checkpoint = ModelCheckpoint(filepath= os.path.join(checkDir,'model-{epoch:02d}.h5'))
```
Model eğitim süresi veri sayısı yükseldikçe, batch size arttıkça, nöron sayısı arttıkça artmaktadır. Eğitim süresi günlerce sürebilmektedir. Biz de eğitim sırasında elektrik kesintisi, bilgisayar hatası gibi hatalardan dolayı eğitimimizin boşa gitmemesi için her adımda bir kayıt dosyası almak için Modelcheckpoint metodunu kullandık.
### Eğitimi Başlatmak
```
history = model.fit_generator(train_batches, steps_per_epoch=10,
                    validation_data= valid_batches, validation_steps=10, epochs=100,verbose=1,callbacks=[checkpoint])
```
Modelimizin içerisinde fit_generator ile eğitim verilerini test verileri atadık. Kaçar adım olacağını her adımda kaçar eder işlem yapacağını ve kayıt dosyası olarak hangi metodu kullacağımızı belirledik ve eğitimi başlattık.
### Eğitim Süreci
Model eğitimi yaklaşık 12 saatlik bir süreç sonucunda tamamlandı.  
Loss, hata oranıdır. Accuracy ise başarı oranıdır. val_loss, test verilerinin hata oranıdır. val_accuracy ise test verilerinin başarı oranıdır.  
1.adımda loss : 3.6690 - accuracy : 0.0953 - val_loss : 4.5646 - val_accuracy : 0.0391  
100.adımda loss : 0.0082 - accuracy : 0.9992 - val_loss : 0.0076 - val_accuracy : 1.0000  
olduğunu gözlemledik. Loss, hata oranıdır.  
### Model ve Labels Dosyalarını Kaydetmek
```
def createLabelsTXT():
    train_datagen = tf.keras.preprocessing.image.ImageDataGenerator()

    train_generator = train_datagen.flow_from_directory(directory=train_dir, target_size=(img_width, img_height), batch_size=32)

    labels = '\n'.join(sorted(train_generator.class_indices.keys()))
    with open('completed_results/labels.txt', 'w') as f:
        f.write(labels)

```
Labels.txt dosyası model ile birlikte kullanılan içerisinde modeldeki sınıfların isimlerinin bulunduğu bir metin belgesidir. Eğitim sonunda oluşturduk.  
```
def modelSaver():
    keras.models.save_model(model,'completed_results/isaretDili.h5')
    #model.save('isaretDili.h5')
    print("Model is successfully stored!")
```
Model eğitimi sonunda modelimizi keras modeli olarak .h5 uzantılı şekilde kayıt ettik.  
### Eğitimdeki Hata ve Başarım Oranlarını Gözlemlemek
```
def createGraphic():
    print(history.history.keys())
    plt.plot(history.history['accuracy'])
    plt.plot(history.history['val_accuracy'])
    plt.title('model accuracy')
    plt.ylabel('accuracy')
    plt.xlabel('epoch')
    plt.legend(['train', 'test'], loc='upper left')
    plt.savefig("completed_results/accuracy.png",dpi=720)
    plt.close()
    #plt.show()
    plt.plot(history.history['loss'])
    plt.plot(history.history['val_loss'])
    plt.title('model loss')
    plt.ylabel('loss')
    plt.xlabel('epoch')
    plt.legend(['train', 'test'], loc='upper left')
    plt.savefig("completed_results/loss.png",dpi=720)
    #plt.show()
```
Loss Grafiğinde modelin adım adım hata oranının azalışını görmekteyiz. Mavi olan kısımlar eğitimdeki hata oranı, turuncu kısımlar testteki hata oranlarıdır. Belirli bir süreden sonra hata oranının çok yavaş düştüğünü ve gittikçe 0'a yaklaştığını görmekteyiz.  
<img align="center" width="500" height="500" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/Model/model/completed_results/loss.png">  
Accuracy Grafiğinde modelin adım adım başarı oranının artışını görmekteyiz. Mavi olan kısımlar eğitimdeki başarı oranı, turuncu kısımlar testteki başarı oranlarıdır. Belirli bir süreden sonra başarı oranının çok yavaş arttığını ve gittikçe 1'a yaklaştığını görmekteyiz.  
<img align="center" width="500" height="500" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/Model/model/completed_results/accuracy.png">
## Model Test Etme
Eğittimiz modeli android ortamında çalıştırmadan yani [model dönüştürme](#model-test-etme) işlemi yapmadan önce PyCharm üzerinden test ettik. Seçtiğimiz bir resimleri sisteme dahil edip test edip sonuçları ise aşağıdaki gibi gözlemledik.  
<img align="center" width="500" height="500" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/Model/model/tutorial_images/Figure_1.png">  
Öncellikle load_model ile hazırladığımız modeli sisteme dahil ettik. Ardından compile ile bunu çalıştırdık. test metodumuz ile seçtiğimiz resmi model.predict_classes ile tahmin etme işlemine soktuk. Çıkan tahmin sonucunun bulunduğu sınıfın yani harfin ismini alıp bunu if döngüsüne sokup matploblib kütüphanesi ile ettiği tahmini ve kulladığımız resmi ekranda gösterdik. Bu işlemin gerçekleştirildiği kodlar ise aşağıda bulunmaktadır.
```
model = load_model('completed_results/isaretDili.h5')
model.compile(loss='binary_crossentropy',
              optimizer='rmsprop',
              metrics=['accuracy'])
def test(filePath):
    img = image.load_img(filePath, target_size=(img_width, img_height))
    x = image.img_to_array(img)
    x = np.expand_dims(x, axis=0)

    images = np.vstack([x])
    classes = model.predict_classes(images, batch_size=128)
    if classes == 0:
        plt.title('Tahmin : A',fontsize=30)
    elif classes == 1:
        plt.title('Tahmin : B',fontsize=30)
    elif classes == 2:
        plt.title('Tahmin : C',fontsize=30)
    elif classes == 3:
        plt.title('Tahmin : D',fontsize=30)
```
## Model Dönüştürme
Tensorflow ve keras yardımıyla eğittimiz modelimizi, TFLiteConverter.from_keras_model_file metodu ile programa dahil edip bunu bir değişkene atadık. Ardından bu değişken ile convert metodunu çağırarak modelimizi tflite modele çevirdik. Ardından bunu da dosya yazma işlemlerinde kullanulan metod ile kaydettik.  
```   
    converter = tf.lite.TFLiteConverter.from_keras_model_file('completed_results/isaretDili.h5')
    tflite_model = converter.convert()
    file = open("completed_results/isaretDili.tflite", "wb")
    file.write(tflite_model)
    print("TfLite model is succesfully stored!")
```

[Başa Dön](#dataset-ve-model-olusturma)
