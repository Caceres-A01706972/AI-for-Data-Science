# **ENTREGA: Implementación de una técnica de aprendizaje máquina sin el uso de un framework.**
Este código muestra cómo realizar regresión lineal utilizando el método de "Gradient Descend". Esta es una técnica común para optimizar
parámetros de un modelo de regresión lineal y ajustarlo a los datos.

## Contenido
El código se compone de la siguiente manera:
1. **Importación de Librerías**: Se importan las librerías necesarias para hacer el manejo de los datos y la visualización.
2. **Función `gradient_descend`**: Esta función realiza el descenso de gradiente para optimizar los parámetros "w" y "b"
de la regresión lineal. Toma los datos de entrada "x" y los valores objetivo "y", así como los parámetros actuales
"w" y "b", y la tasa de aprendizaje. Calcula las derivadas parciales de la función de pérdida con respecto
a "w" y "b" y actualiza los parámetros en cada iteración.
3. **Función `predict`**:  utiliza los parámetros entrenados "w" y "b" para hacer predicciones sobre nuevos datos de entrada.
Solicita al usuario los nuevos puntos de datos, calcula las predicciones y las muestra en pantalla.
4. **Carga de Datos:** Se carga un conjunto de datos _"wine.data"_. Se extraen las características y los objetivos de los datos para su uso en
la regresión lineal.
5. **Parámetros y Entrenamiento:** Se inicializan los parámetros "w" y "b" junto con el learning_rate. Se realiza el entrenamiento en un bucle
donde se actualizan los parámetros cada época. También se calcula y almacena la pérdoda en cada época.
6. **Visualización de Pérdida:** Se grafica como disminuye la pérdida a lo largo de las epocas utilizando matplotlib
7. **Predcciones:** Utilizando los parametros entrenados, el programa pregunta al usuario si desea hacer una predicción y muestra los resultados.

## Uso del Código

1. Tener instaladas las bibliotecas `numpy`, `pandas` y `matplotlib`.
2. Ejecuta el código y el programa llevará a cabo el entrenamiento utilizando el descenso de gradiente y mostrará el progreso de la pérdida a lo largo de las épocas.
3. Cierra la ventana de `matplotlib`
4. Después el programa dará la opción de hacer predicciones utilizando los parámetros entrenados
