**Dil :** [English](https://github.com/BatuhanGunes/signLanguageConverter-Android) / Turkish

# İşaret Dili Çeviricisi

Bu proje, bilgisayarlı görü ile derin öğrenme yöntemlerini kullanarak işaret dili bilmeyen bir insan ile işaret dili ile iletişim kuran bir insanın arasındaki iletişim problemlerini ortadan kaldırmaya çalışmaktadır. İşaret dili bilmeyen kullanıcı telefonundaki bu uygulama sayesinde işaret dili ile yapılan hareketlerin anlamlarını algılayabilmektedir. Bunun için uygulama açılır ve işaret dili kullanan kişinin görüntüsü alınır. Bu görüntü üzerinde canlı olarak derin öğrenme yöntemleri ile işaretlerin anlamları çıkartılır. Bu anlamlar diğer kullanıcıya sesli veya yazılı olarak aktarılır. Aynı zamanda işaret dili sözlüğü olabilicek şekilde işaretlerin anlamları farklı bir sayfada kullanıcıya sunulmaktadır. Proje hakkında teknik ve ayrıntılı bilgi almak için [SingLanguageDescription](https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/documentation/android/SingLanguageDescription.pdf) sayfasını inceleyebilirsiniz. Ayrıca kod ile ilgili açıklamaları [Explore The_Code](https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/master/android/EXPLORE_THE_CODE.md) sayfasında bulabilirsiniz.

Not: Bu projede işaret dili olarak "Türk İşaret Dili"  kullanılmıştır.

## Ekran Görüntüleri

<img align="center" width="200" height="375" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/documentation/android/images/main.png"> <img align="center" width="200" height="375" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/documentation/android/images/dictionary.png"> <img align="center" width="200" height="375" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/documentation/android/images/about_us.png">

## Başlangıç

Projeyi çalıştırabilmek için proje dosyalarının bir kopyasını yerel makinenize indirin. Gerekli ortamları edindikten sonra projeyi bu ortamda açarak çalıştırabilirsiniz. Programı bir ios cihaz üzerinde çalıştırabilirsiniz.

### Gereklilikler

* Android Studio 3.2 (Linux, Mac veya Windows makinesine yüklenmiş)

* Geliştirici modundayken USB hata ayıklama etkin olan cihaz

* USB kablosu (cihazı bilgisayarınıza bağlamak için)

## İnşa et ve Çalıştır

### Adım 1. signLanguageConverter kaynak kodunu kopyalayın

Uygulamayı almak için GitHub deposunu bilgisayarınıza kopyalayın.

Android Studio'da signLanguageConverter kaynak kodunu açın. Bunu yapmak için Android
Studio'yu açın ve `signLanguageConverter/android/examples/`

<img src="images/classifydemo_img1.png?raw=true" />

### Adım 2. Android Studio projesini oluşturma

`Build -> Make Project` ı seçin ve projenin başarıyla oluşturulduğunu kontrol edin.
Ayarlarda yapılandırılmış Android SDK'ye ihtiyacınız olacak. En az 23 sürüm olan SDK'ya ihtiyacınız olacak
sürüm 23. `build.gradle` dosyası eksik olanları indirmenizi ister kütüphaneler.

<img src="images/classifydemo_img4.png?raw=true" style="width: 40%" />

<img src="images/classifydemo_img2.png?raw=true" style="width: 60%" />

<aside class="note"><b>Note:</b><p>`build.gradle` kullanmak üzere yapılandırıldı
    TensorFlow Lite's nightly build.</p><p>İle ilgili bir derleme hatası görürseniz
    Tensorflow Lite'ın Java API'sı ile uyumluluk (örneğin, X yöntemi
     Interpreter` türü için tanımlanmamışsa), geriye dönük olarak uyumlu olması muhtemeldir
     API olarak değiştirin. Örnek depoda "git pull" i çalıştırmanız gerekecek.
     gecelik yapıya uygun bir sürüm elde edin.</p></aside>

### Step 3. Uygulamayı yükle ve çalıştır

Android cihazı bilgisayara bağlayın ve herhangi bir ADB'yi onayladığınızdan emin olun.
telefonunuzda görünen izin istemleri. `Çalıştır -> Uygulamayı çalıştır'ı seçin.
bağlı cihazlarda uygulamanın hedeflendiği cihaza dağıtım hedefi
yüklenecek. Bu, uygulamayı cihaza yükleyecektir.

<img src="images/classifydemo_img5.png?raw=true" style="width: 60%" />

<img src="images/classifydemo_img6.png?raw=true" style="width: 70%" />

<img src="images/classifydemo_img7.png?raw=true" style="width: 40%" />

<img src="images/classifydemo_img8.png?raw=true" style="width: 80%" />

Uygulamayı test etmek için cihazınızda `` TFL Classify '' adlı uygulamayı açın. 
uygulama ilk kez Koştuğunda, uygulama kameraya erişmek için izin isteyecektir.
Uygulamanın yeniden yüklenmesi, önceki yüklemelerin kaldırılmasını gerektirebilir.

## Varlıklar klasörü 
içeriğini silmeyin_. Açıkça sildiyseniz, silinen model dosyalarını yeniden indirmek için `` Oluştur -> Yeniden Oluştur '' u seçin.

## Yazarlar

* **Batuhan Güneş**  - [BatuhanGunes](https://github.com/BatuhanGunes)

Ayrıca, bu projeye katılan ve katkıda bulunanlara [contributors](https://github.com/BatuhanGunes/signLanguageConverter-Android/graphs/contributors) listesinden ulaşabilirsiniz.
