#!/usr/bin/env python3

from perceptron import initialize_weights, train, test_accuracy

def generate_all_matrices():
    matrices = []
    
    for i in range(512):
        binary = format(i, '09b')
        matrix = [int(bit) for bit in binary]
        matrices.append(matrix)
    
    return matrices

def perceptronVert(matriu):
    for i in range(0,3):
        if matriu[i] == 1 and matriu[i] == matriu[i+3] and matriu[i] == matriu[i+6]:
            return 1
    return 0

def perceptronHoriz(matriu):
    for i in range(0,9,3):
        if matriu[i] == 1 and matriu[i] == matriu[i+1] and matriu[i] == matriu[i+2]:
            return 1
    return 0

def perceptronDiag(matriu):
    if matriu [0] == 1 and matriu[4] == 1 and matriu[8] == 1 or matriu[2] == 1 and matriu[4] == 1 and matriu [6] == 1:
        return 1
    else:
        return 0

EPOCHS = 80 
LEARNING_RATE = 0.1
INPUT_SIZE = 9

inputs = generate_all_matrices()
labels = []

for matriu in inputs:
    if perceptronHoriz(matriu) == 1:
        labels.append(perceptronHoriz(matriu))
    elif perceptronVert(matriu) == 1:
        labels.append(perceptronVert(matriu))
    elif perceptronDiag(matriu) == 1:
        labels.append(perceptronDiag(matriu))
    else:
        labels.append(0)

weights = initialize_weights(INPUT_SIZE)
bias = 0.0

weights, bias = train(weights, bias, inputs, labels, LEARNING_RATE, EPOCHS)
accuracy = test_accuracy(weights, bias, inputs, labels)
print(f"\nPercentatge d'encert del Perceptró entrenat amb {EPOCHS} EPOCHS: {accuracy}%\n")
