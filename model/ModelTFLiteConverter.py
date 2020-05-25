import tensorflow as tf

def modelConvert():
    converter = tf.lite.TFLiteConverter.from_keras_model_file('completed_results/isaretDili.h5')
    #converter.optimizations = [tf.lite.Optimize.OPTIMIZE_FOR_SIZE]
    tflite_model = converter.convert()
    file = open("completed_results/isaretDili.tflite", "wb")
    file.write(tflite_model)
    print("TfLite model is succesfully stored!")

modelConvert()