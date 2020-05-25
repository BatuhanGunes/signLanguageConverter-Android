# Dataset ve Model Olusturma  
1-[Dataset Oluşturma](#dataset-oluşturma)  
2-[Data Augmentation](#data-augmentation)  
3-[Model Oluşturma](#model-oluşturma)  
4-[Model Test Etme](#model-test-etme)  
5-[Model Dönüştürme](#model-dönüştürme)  

## Dataset Oluşturma
  Kendi çektiğimiz ve internet üzerinden bulduğumuz türkçe işaret dili alfabesi resimlerini öncelikle bilgisayar ortamında kayıt ettik. Ardından herbir resmi içerdiği harflere göre ayrı klasörlere koyduk. Klasörleme işleminde bittikten sonra [PhotoScape](http://www.photoscape.org/ps/main/index.php) programı yardımı ile resimlerin isimlerini içerdiği harfler olarak değiştirdik. İsimlendirme işlemi bittikten sonra PhotoScape programı ile resimleri 224x224 piksel boyutuna dönüştürdük. Herbir harf klasöründe 80 adet farklı şekilde çekilmiş ve isimlendirme ile boyutlandırma işlemlerinin gerçekliştiği resimlerimiz veri çoğaltma yani [data augmentation](#data-augmentation) işlemini yapmaya hazır hale geldi. Data augmentation ile her resimde yakınlaştırma işlemi, döndürme işlemi, parlaklık değiştirme işlemi gibi işlemler gerçekleştirerek 80 ana resimden 4800 farklı resim verisi elde ettik. Bu sayede toplamda 119.400 adet eğitim verimizin ve 47.796 adet test verimizin bulunduğu veri setimiz hazır hale geldi.  
  Hazırladığımız [datasetimizi](https://www.kaggle.com/berkaykocaoglu/tr-sign-language) yapay zeka, makine öğrenimi gibi alanlarda kullanılan diğer datasetlerin bulunduğu [kaggle](https://www.kaggle.com/) adlı siteye yükleyerek ihtiyacı olan insanların erişebilmesi için paylaştık.  
  <img align="center" width="1000" height="600" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/Model/model/tutarial_images/trsignlanuage.png">
## Data Augmentation
## Model Oluşturma
## Model Test Etme
## Model Dönüştürme


[Başa Dön](#dataset-ve-model-olusturma)
