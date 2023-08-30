from flask import Flask, render_template, request
import os
from keras.models import load_model
from keras.preprocessing import image

app = Flask(__name__)
# Get the path of the current script
current_dir = os.path.dirname(os.path.abspath(__file__))
# Construct the path to the model.h5 file
model_file_path = os.path.join(current_dir, 'model.h5')

dic = {0 : 'Cat', 1 : 'Dog'}

model = load_model(model_file_path)

model.make_predict_function()

def predict_label(img_path):
    i = image.load_img(img_path, target_size=(100,100))
    i = image.img_to_array(i)/255.0
    i = i.reshape(1, 100,100,3)

    p = model.predict(i)
    prediction_value = p[0][0]

    if prediction_value > 0.85 :
        return dic[1]
    elif prediction_value < 0.15:
        return dic[0]
    else:
        return prediction_value


# Construct the path to the static folder
static_folder_path = os.path.join(current_dir, 'static/')

# routes
@app.route("/", methods=['GET', 'POST'])
def main():
    return render_template("index.html")

@app.route("/submit", methods = ['POST'])
def get_output():
    if request.method == 'POST':
        img = request.files['my_image']
        img_path = static_folder_path + img.filename
        img.save(img_path)
        p = predict_label(img_path)
    return render_template("index.html", prediction = p, img_path = "../static/"+img.filename)

if __name__ =='__main__':
    #app.debug = True
    app.run(debug = True)