import keras
import numpy
from keras.layers.core import Dense
from keras.optimizers import  Adam
from keras.preprocessing.image import ImageDataGenerator
import matplotlib.pyplot as plt
from sympy.printing.tests.test_tensorflow import tf
from keras.layers import Dropout
import os
from keras.models import Sequential
from keras.applications.mobilenet import MobileNet
from keras.callbacks import ModelCheckpoint

img_width, img_height = 224, 224
IMG_SHAPE = (img_width, img_height, 3)

train_dir = os.path.join('tr_signLanguage_dataset/train')
validation_dir = os.path.join('tr_signLanguage_dataset/validation')

trainBatchSize = 128
validBatchSize = 64

train_batches = ImageDataGenerator().flow_from_directory(train_dir,target_size=(img_width,img_height), classes=['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','R','S','T','U','V','Y','Z','del','nothing','space'],batch_size=trainBatchSize)
valid_batches = ImageDataGenerator().flow_from_directory(validation_dir,target_size=(img_width,img_height), classes=['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','R','S','T','U','V','Y','Z','del','nothing','space'],batch_size=validBatchSize)

base_model = MobileNet(
    weights='imagenet',
    include_top=False,
    input_shape=IMG_SHAPE,
    pooling='avg',
)
base_model.trainable = True

model = Sequential()
model.add(base_model)
model.add(Dropout(0.25))
model.add(Dense(26, activation='softmax'))
model.summary()

stepsPerEpoch = numpy.ceil(train_batches.n/4000)
validationSteps= numpy.ceil(valid_batches.n/2000)
model.compile(Adam(lr=.0001), loss='categorical_crossentropy',metrics = ['accuracy'])

checkDir = 'checkpoints'
checkpoint = ModelCheckpoint(filepath= os.path.join(checkDir,'model-{epoch:02d}.h5'))

history = model.fit_generator(train_batches, steps_per_epoch=10,
                    validation_data= valid_batches, validation_steps=10, epochs=100,verbose=1,callbacks=[checkpoint])
print("Training is done!")

def createLabelsTXT():
    train_datagen = tf.keras.preprocessing.image.ImageDataGenerator()

    train_generator = train_datagen.flow_from_directory(directory=train_dir, target_size=(img_width, img_height), batch_size=32)

    labels = '\n'.join(sorted(train_generator.class_indices.keys()))
    with open('completed_results/labels.txt', 'w') as f:
        f.write(labels)

createLabelsTXT()
print("Labels.txt creating is done!")

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
createGraphic()
print("Model history is done!")

def modelSaver():
    keras.models.save_model(model,'completed_results/isaretDili.h5')
    #model.save('isaretDili.h5')
    print("Model is successfully stored!")
modelSaver()


