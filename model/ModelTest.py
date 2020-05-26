from keras.models import load_model
from keras.preprocessing import image
import numpy as np
import matplotlib.pyplot as plt

# dimensions of our images
img_width, img_height = 224, 224

# load the model we saved
model = load_model('completed_results/isaretDili.h5')
model.compile(loss='binary_crossentropy',
              optimizer='rmsprop',
              metrics=['accuracy'])

# predicting images

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
    elif classes == 4:
        plt.title('Tahmin : E',fontsize=30)
    elif classes == 5:
        plt.title('Tahmin : F',fontsize=30)
    elif classes == 6:
        plt.title('Tahmin : G',fontsize=30)
    elif classes == 7:
        plt.title('Tahmin : H',fontsize=30)
    elif classes == 8:
        plt.title('Tahmin : I',fontsize=30)
    elif classes == 9:
        plt.title('Tahmin : J',fontsize=30)
    elif classes == 10:
        plt.title('Tahmin : K',fontsize=30)
    elif classes == 11:
        plt.title('Tahmin : L',fontsize=30)
    elif classes == 12:
        plt.title('Tahmin : M',fontsize=30)
    elif classes == 13:
        plt.title('Tahmin : N',fontsize=30)
    elif classes == 14:
        plt.title('Tahmin : O',fontsize=30)
    elif classes == 15:
        plt.title('Tahmin : P',fontsize=30)
    elif classes == 16:
        plt.title('Tahmin : R',fontsize=30)
    elif classes == 17:
        plt.title('Tahmin : S',fontsize=30)
    elif classes == 18:
        plt.title('Tahmin : T',fontsize=30)
    elif classes == 19:
        plt.title('Tahmin : U',fontsize=30)
    elif classes == 20:
        plt.title('Tahmin : V',fontsize=30)
    elif classes == 21:
        plt.title('Tahmin : Y',fontsize=30)
    elif classes == 22:
        plt.title('Tahmin : Z',fontsize=30)

    plt.imshow(img)
    plt.show()


test('tr_signLanguage_dataset/deneme.jpg')
