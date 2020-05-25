**Language :** English / [Turkish](https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/master/README(Turkish).md)

# Sign Language Converter

### Overview
This project was developed using computer vision and deep learning methods. It tries to eliminate communication problems between a person who does not know sign language and those who communicate with sign language. The user, who does not know the sign language, can perceive the meaning of the movements made with the sign language thanks to this application on his phone. For this, the application is opened and the image of the person using the sign language is taken. In this live image, the meanings of the signs are derived using deep learning methods. These meanings are conveyed to the user by voice or in writing. At the same time, the meaning of all signs is presented to the user on a different page. 

You can also see 
- [`Sing Language Description`](https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/master/android/SingLanguageDescription.pdf) to get technical and detailed information about the project. 
- Find explanations of the code on the [`Explore The_Code`](https://github.com/BatuhanGunes/signLanguageConverter-Android/tree/master/android).
- Find explanations of the model on the [`Explore The Model`]() //Yönlendirme yok berkay ekleyecek.

Note: "Turkish Sign Language" was used as sign language in this project.

### Model

// berkay Oluşturacak. Kısa Tek Paragraflık Modelin anlatımı

## Screenshots

<img align="center" width="200" height="375" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/documentation/android/images/main.png"> <img align="center" width="200" height="375" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/documentation/android/images/dictionary.png"> <img align="center" width="200" height="375" src="https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/documentation/android/images/about_us.png">

## Getting Started

Download a copy of the project files to your local machine to run the project. Once you have the required environments, you can open the project and run it in this environment. You can run the program on an android device.

## Requirements

*   Android Studio 3.2 (installed on a Linux, Mac or Windows machine)
*   Android device in [`developer mode`](https://developer.android.com/studio/debug/dev-options) with USB debugging enabled
*   USB cable (to connect Android device to your computer)

## Build and run

### Step 1. Clone the Sign Language Converter source code

Clone the Sign Language Converter GitHub repository to your computer to get the application.

```
git clone https://github.com/https://github.com/BatuhanGunes/signLanguageConverter-Android.git
```

Open the Sign Language Converter source code in Android Studio. To do this, open Android Studio and select `Open an existing project`, setting the folder to `signLanguageConverter-Android/android`

<img src="android/images/classifydemo_img1.png?raw=true" />

### Step 2. Build the Android Studio project

Select `Build -> Make Project` and check that the project builds successfully. You will need Android SDK configured in the settings. You'll need at least SDK version 23. The `build.gradle` file will prompt you to download any missing libraries.

<img src="android/images/classifydemo_img4.png?raw=true" style="width: 40%" />

<img src="android/images/classifydemo_img2.png?raw=true" style="width: 60%" />

<aside class="note"><b>Note:</b><p>`build.gradle` is configured to use TensorFlow Lite's nightly build.</p><p>If you see a build error related to compatibility with Tensorflow Lite's Java API (for example, `method X is undefined for type Interpreter`), there has likely been a backwards compatible change to the API. You will need the cloned [TensorFlow Lite android examples](https://github.com/tensorflow/examples/tree/master/lite/examples/image_classification/android) repository, and changes from this repository will need to be imported into this project. </p></aside>

### Step 3. Install and run the app

Connect the Android device to the computer and be sure to approve any ADB permission prompts that appear on your phone. Select `Run -> Run app.` Select the deployment target in the connected devices to the device on which the app will be installed. This will install the app on the device.

<img src="android/images/classifydemo_img5.png?raw=true" style="width: 60%" />

<img src="android/images/classifydemo_img6.png?raw=true" style="width: 70%" />

<img src="android/images/classifydemo_img7.png?raw=true" style="width: 40%" />

<img src="android/images/classifydemo_img8.png?raw=true" style="width: 80%" />

To test the app, open the app called `TFL Classify` on your device. When you run the app the first time, the app will request permission to access the camera. Re-installing the app may require you to uninstall the previous installations.

## Authors

* **Batuhan Güneş**  - [BatuhanGunes](https://github.com/BatuhanGunes)
* **Hasan Hüseyin Öztunç**  - [hasanhoztunc](https://github.com/hasanhoztunc)
* **Muhammed Emin Berkay Kocaoğlu**  - [mebon](https://github.com/mebon)

See also the list of [contributors](https://github.com/BatuhanGunes/signLanguageConverter-Android/graphs/contributors) who participated in this project.

## License

This project is licensed under the Apache License - see the [LICENSE.md](https://github.com/BatuhanGunes/signLanguageConverter-Android/blob/master/LICENSE) file for details.

