import tensorflow as tf
import os
import urllib
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from pandas.plotting import scatter_matrix
from sklearn import model_selection
from sklearn.neural_network import MLPClassifier
from sklearn.metrics import classification_report
from sklearn.metrics import confusion_matrix
from sklearn.metrics import accuracy_score

url = "https://archive.ics.uci.edu/ml/machine-learning-databases/iris/iris.data"
names = ['sepal-length', 'sepal-width', 'petal-length', 'petal-width', 'class']
dataset = pd.read_csv(url, names=names)

print(dataset.head(10))
print(dataset.describe())

pw1=dataset['petal-width'][dataset['class']=='Iris-setosa']
sw1=dataset['sepal-width'][dataset['class']=='Iris-setosa']
pw2=dataset['petal-width'][dataset['class']=='Iris-versicolor']
sw2=dataset['sepal-width'][dataset['class']=='Iris-versicolor']
pw3=dataset['petal-width'][dataset['class']=='Iris-virginica']
sw3=dataset['sepal-width'][dataset['class']=='Iris-virginica']
f,g=plt.subplots()
g.scatter(sw1,pw1,color='r')
g.scatter(sw2,pw2,color='b')
g.scatter(sw3,pw3,color='g')
plt.show()

arrayw=dataset.values
X=arrayw[:,0:4]
Y=arrayw[:,4]
split=0.2
seed =7
X_train,X_validate,Y_train,Y_validate=model_selection.train_test_split(X,Y,test_size=split,random_state=seed)
clf = MLPClassifier(solver='lbfgs', alpha=1e-5,hidden_layer_sizes=(3, 2,2), random_state=1)
clf.fit(X_train,Y_train)
kfold = model_selection.KFold(n_splits=10, random_state=seed)
cv_results = model_selection.cross_val_score(clf, X_train, Y_train, cv=kfold, scoring='accuracy')
name='ad'
msg = "%s: %f (%f)" % (name, cv_results.mean(), cv_results.std())
print(msg)

predictions = clf.predict(X_validate)
print(accuracy_score(Y_validate, predictions))
print(confusion_matrix(Y_validate, predictions))
print(classification_report(Y_validate, predictions))
