# Impoertar las librerias necesarias
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

"""
    Perform gradient descent to optimize parameters w and b for linear regression

    Parameters:
    x (numpy.ndarray): Input data points.
    y (numpy.ndarray): Target values.
    w (float): Current weight parameter.
    b (float): Current bias parameter.
    learning_rate (float): The learning rate for gradient descent.

    Returns:
    float: Updated weight parameter.
    float: Updated bias parameter.
"""
def gradient_descend(x, y, w, b, learning_rate): 
    dldw = 0.0   
    dldb = 0.0   
    N = x.shape[0]   # Number of datapoints in x
    # loss function = (y-(wx+b)))**2
    for xi, yi in zip(x,y):         # zip nos permite recorrer xi y yi al mismo tiempo
       # Partial derivative of loss function respect to w
       dldw += -2*xi*(yi-(w*xi+b))   
       # Partial derivative of loss function respect to b
       dldb += -2*(yi-(w*xi+b))      
    
    # Actualiza los parametros de w y b
    w = w - learning_rate*(1/N)*dldw
    b = b - learning_rate*(1/N)*dldb
    return w, b 



"""
    Predicts the output values for new input data points using trained parameters.

    Parameters:
    trained_w (float): Trained weight parameter.
    trained_b (float): Trained bias parameter.
"""
def predict(trained_w, trained_b):
    # Ask the user for new input data points
    num_points = int(input("Enter the number of new data points: "))
    new_x = []
    for i in range(num_points):
        data_point = float(input(f"Enter value for data point {i + 1}: "))
        new_x.append([data_point])
    new_x = np.array(new_x)

    # Realiza predicciones para los nuevos datos de entradaa
    predictions = trained_w * new_x + trained_b
    print("Predictions:", predictions)


columns = ["class","Alcohol","Malic Acid", "Ash", "Alcalinity of Ash","Magnesium","Total phenols","Flavanoids","Nonflavanoid Phenols","Proanthocyanins","Color Intensity","Hue","OD280/OD315 of diluted wines","Proline"]
data = pd.read_csv('DataSets/wine.data',names = columns)
# Extract features and target
x = data['Color Intensity'].values
y = data['Alcohol'].values

# Datos dummies
#x1 = np.random.randn(20,1)
#y1 = 5*x + np.random.rand()

# Paramettros necesarios
w = 0.0 
b = 0.0 
learning_rate = 0.01
losses = [] # Arreglo para graficar como decrementa la perdida


# Hacer las actualizaciones por cada epoca
for epoch in range(4000): 
    w,b = gradient_descend(x,y,w,b,learning_rate) # Actualiza
    yhat = w*x + b # yhat es y_prediction
    loss = np.divide(np.sum((y-yhat)**2, axis=0), x.shape[0]) # MSE
    losses.append(loss)  # Guarda la perdida para esta epoca 
    print(f'{epoch} loss is {loss}, paramters w:{w}, b:{b}')

print("Final Parameters: w:", w, "b: ", b)
# Grafica los valores de la perdida a lo largo de la epoca
plt.plot(losses)
plt.xlabel('Epoch')
plt.ylabel('Loss')
plt.title('Loss over Epochs')
plt.show()

# Parametros entrenados
trained_w = w  # Despues de entrenar guarda datos de w
trained_b = b  # Despues de entrenar guarda datos de b

while True:
    # Pregunta al usuario si quiere hacer una prediccion
    user_choice = input("Do you want to make a prediction? (yes/no): ").lower()
    if user_choice == "no":
        print("Exiting the program.")
        break

    elif user_choice == "yes":
        predict(trained_w, trained_b)