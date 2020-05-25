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
 <img align="center" width="1000" height="300" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/Model/model/tutorial_images/dataAug1.jpg">  
   Aşağıdaki işlemde ise öncelikle çoğaltılacak resimlerinin klasör isimlerini bir listeye ekledik. Ardından bu liste ile döngüyü başlatıp, herbir resmin ImageDataGenerator metoduna girmesini sağladık. Metoda giren resimler işlem uygulandıktan sonra belirtlen yollara kaydettik. 
 <img align="center" width="1000" height="550" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/Model/model/tutorial_images/dataAug2.jpg">  
## Model Oluşturma
## Model Test Etme
Eğittimiz modeli android ortamında çalıştırmadan yani [model dönüştürme](#model-test-etme) işlemi yapmadan önce PyCharm üzerinden test ettik. Seçtiğimiz bir resimleri sisteme dahil edip test edip sonuçları ise aşağıdaki gibi gözlemledik.  
<img align="center" width="500" height="500" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/Model/model/tutorial_images/Figure_1.png">  
Öncellikle load_model ile hazırladığımız modeli sisteme dahil ettik. Ardından compile ile bunu çalıştırdık. test metodumuz ile seçtiğimiz resmi model.predict_classes ile tahmin etme işlemine soktuk. Çıkan tahmin sonucunun bulunduğu sınıfın yani harfin ismini alıp bunu if döngüsüne sokup matploblib kütüphanesi ile ettiği tahmini ve kulladığımız resmi ekranda gösterdik. Bu işlemin gerçekleştirildiği kodlar ise aşağıda bulunmaktadır.
<img align="center" width="1000" height="550" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/Model/model/tutorial_images/test1.jpg">
## Model Dönüştürme
Tensorflow ve keras yardımıyla eğittimiz modelimizi, TFLiteConverter.from_keras_model_file metodu ile programa dahil edip bunu bir değişkene atadık. Ardından bu değişken ile convert metodunu çağırarak modelimizi tflite modele çevirdik. Ardından bunu da dosya yazma işlemlerinde kullanulan metod ile kaydettik.
<img align="center" width="1000" height="550" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/Model/model/tutorial_images/convert.jpg">

[Başa Dön](#dataset-ve-model-olusturma)
