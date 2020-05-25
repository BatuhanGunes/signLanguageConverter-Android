from keras.preprocessing.image import ImageDataGenerator, array_to_img, img_to_array, load_img

imgProperties = ImageDataGenerator(
        rotation_range=40,
        width_shift_range=0.2,
        height_shift_range=0.2,
        brightness_range=[0.2,1.0],
        zoom_range=[0.5,1.0],
        shear_range=0.2,
        horizontal_flip=True,
        fill_mode='nearest')

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
dataAugRun()