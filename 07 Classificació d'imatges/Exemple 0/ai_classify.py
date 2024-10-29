#!/usr/bin/env python3

import os
import json
import shutil
import zipfile
import torch
import torch.nn as nn
from torchvision import transforms
from torchvision.models import resnet18
from PIL import Image
from pathlib import Path
from tqdm import tqdm

DATA_FOLDER = './data/testing'

# Imatges de test amb les seves etiquetes
test_images = [
    [f"{DATA_FOLDER}/img14469279.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img15019810.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img16615685.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img16745259.jpg", "cat"],
    [f"{DATA_FOLDER}/img17242442.jpg", "cat"],
    [f"{DATA_FOLDER}/img21960791.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img22921893.jpg", "cat"],
    [f"{DATA_FOLDER}/img23001964.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img27753996.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img30802655.jpg", "cat"],
    [f"{DATA_FOLDER}/img32929134.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img34040492.jpg", "cat"],
    [f"{DATA_FOLDER}/img37438645.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img38446080.jpg", "cat"],
    [f"{DATA_FOLDER}/img43753560.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img44113566.jpg", "cat"],
    [f"{DATA_FOLDER}/img46733274.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img47486374.jpg", "cat"],
    [f"{DATA_FOLDER}/img48140375.jpg", "cat"],
    [f"{DATA_FOLDER}/img49165968.jpg", "cat"],
    [f"{DATA_FOLDER}/img50470376.jpg", "cat"],
    [f"{DATA_FOLDER}/img53355576.jpg", "cat"],
    [f"{DATA_FOLDER}/img55000620.jpg", "cat"],
    [f"{DATA_FOLDER}/img57107487.jpg", "cat"],
    [f"{DATA_FOLDER}/img58115239.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img62846124.jpg", "cat"],
    [f"{DATA_FOLDER}/img63161136.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img69539582.jpg", "cat"],
    [f"{DATA_FOLDER}/img69679487.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img69957115.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img69968821.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img70610683.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img70610683.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img72202194.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img75381857.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img75918332.jpg", "cat"],
    [f"{DATA_FOLDER}/img76888003.jpg", "cat"],
    [f"{DATA_FOLDER}/img77688616.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img79053052.jpg", "cat"],
    [f"{DATA_FOLDER}/img83842359.jpg", "cat"],
    [f"{DATA_FOLDER}/img83918667.jpg", "cat"],
    [f"{DATA_FOLDER}/img84146180.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img90037107.jpg", "cat"],
    [f"{DATA_FOLDER}/img93578086.jpg", "cat"],
    [f"{DATA_FOLDER}/img95378073.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img95996327.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img96295260.jpg", "non_cat"],
    [f"{DATA_FOLDER}/img96872108.jpg", "cat"],
    [f"{DATA_FOLDER}/img99363609.jpg", "non_cat"]
]

# Carregar configuració
with open('iscat_config.json', 'r') as f:
    config = json.load(f)

# Treu les dades de test del zip
def decompress_data_zip(type):
    # Esborra la carpeta de test
    if os.path.exists(DATA_FOLDER):
        shutil.rmtree(DATA_FOLDER)

    # Descomprimeix l'arxiu que conté les carpetes de test
    zip_filename = f"./data/{type}.zip"
    extract_to = './data/'
    with zipfile.ZipFile(zip_filename, 'r') as zipf:
        for member in zipf.namelist():
            # Filtra per ignorar carpetes ocultes i per extreure només la carpeta
            if member.startswith(f"{type}/") and not member.startswith('__MACOSX/'):
                zipf.extract(member, extract_to)

# Crea una nova instància del model ResNet18 sense pesos. 
# Aquesta configuració s'utilitza per construir el mateix 
# tipus de model que s'ha fet servir durant l'entrenament.
def create_model(num_classes):
    """Crear el mateix model que fem servir per entrenar"""
    # Crear una instància del model ResNet18 sense pesos preentrenats
    model = resnet18(weights=None)
    
    # Obtenir el nombre de característiques d'entrada de la capa final de classificació
    num_ftrs = model.fc.in_features
    
    # Substituir la capa final amb una nova seqüència que inclou:
    # - Una capa de Dropout per reduir el sobreajustament
    # - Una capa Lineal (fully connected) que transforma les característiques en el nombre de sortides desitjat
    # - Una activació Sigmoid per produir sortides entre 0 i 1, útil per a classificació binària o multietiquetes
    model.fc = nn.Sequential(
        nn.Dropout(config['model_params']['dropout_rate']),
        nn.Linear(num_ftrs, config['model_params']['num_output']),
        nn.Sigmoid()
    )
    
    # Retornar el model modificat
    return model

# Retorna un conjunt de transformacions d'imatge utilitzades durant el procés de validació. 
# Aquestes transformacions asseguren que les imatges que es passen al model tenen
# la mateixa estructura i escales que les imatges utilitzades durant l'entrenament
def get_transform():
    # Obtenir les mateixes transformacions que fem servir per validació
    return transforms.Compose([
        # Redimensiona la imatge a la mida especificada al paràmetre 'image_size' de la configuració
        transforms.Resize(tuple(config['image_size'])),
        
        # Converteix la imatge a un tensor, canviant la representació de píxels a valors numèrics
        transforms.ToTensor(),
        
        # Normalitza els valors del tensor utilitzant la mitjana i la desviació estàndard especificades
        transforms.Normalize(mean=config['normalize_mean'], 
                             std=config['normalize_std'])
    ])

# Carrega un model de xarxa neuronal prèviament entrenat des d'un fitxer especificat
def load_model(model_path, num_classes, device):
    """Carregar el model amb els pesos entrenats"""
    # Crear una instància del model amb el nombre de classes especificat
    model = create_model(num_classes)
    
    # Carregar el checkpoint des de el fitxer especificat, utilitzant el dispositiu adequat (CPU o GPU)
    checkpoint = torch.load(model_path, map_location=device, weights_only=True)
    
    # Comprovar si el checkpoint conté el diccionari d'estat complet del model
    if isinstance(checkpoint, dict) and 'model_state_dict' in checkpoint:
        # Carregar l'estat del model des del diccionari guardat
        model.load_state_dict(checkpoint['model_state_dict'])
    else:  # Si el checkpoint només conté els pesos del model
        model.load_state_dict(checkpoint)
    
    # Moure el model al dispositiu especificat
    model = model.to(device)
    
    # Configurar el model en mode d'avaluació (desactivant Dropout, BatchNorm, etc.)
    model.eval()
    
    # Retornar el model carregat i preparat per a inferència
    return model

# Avaluar el rendiment del model utilitzant un conjunt de dades de test. 
# L'objectiu és obtenir les prediccions per a cada imatge 
# del conjunt de test i comparar-les amb les etiquetes reals
def evaluate_model(model, test_images, transform, class_names, device):
    # Avaluar el model en el conjunt de test
    # Variables per seguir el nombre de prediccions correctes i el total d'imatges
    correct = 0
    total = 0
    predictions = []
    
    # Desactiva el càlcul dels gradients perquè només es vol fer inferència
    with torch.no_grad():
        for img_path, true_label in tqdm(test_images, disable=True):
            # Carregar la imatge des de la ruta i convertir-la a RGB per assegurar el format correcte
            img = Image.open(img_path).convert('RGB')
            
            # Aplicar les transformacions (resize, normalize) i afegir una dimensió per a batch
            img_tensor = transform(img).unsqueeze(0).to(device)
            
            # Fer predicció amb el model
            outputs = model(img_tensor)
            
            # Convertir la sortida del model a una probabilitat
            predicted_prob = outputs.item()
            
            # Determinar l'etiqueta predita basada en un llindar de 0.5
            predicted_label = class_names[1] if predicted_prob > 0.5 else class_names[0]
            
            # Calcular la confiança en la predicció
            confidence = predicted_prob if predicted_prob > 0.5 else (1 - predicted_prob)
            
            # Comprovar si la predicció és correcta
            is_correct = predicted_label == true_label
            if is_correct:
                correct += 1
            total += 1
            
            # Guardar els resultats de la predicció
            predictions.append({
                'image': Path(img_path).name,
                'predicted': predicted_label,
                'true_label': true_label,
                'confidence': confidence,
                'correct': is_correct
            })
            
            # Mostrar resultat en consola amb format clar
            print(f"Image: {Path(img_path).name}, Prediction: {confidence:.2%} = {"'"+predicted_label+"'":9} ({"'"+true_label+"'":9} > {'correct' if is_correct else 'wrong'})")    
    
    # Retornar el nombre de prediccions correctes i el total d'imatges
    return correct, total

def main():
    # Descomprimir les dades de test
    decompress_data_zip("testing")

    # Configurar dispositiu
    device = torch.device("mps" if torch.backends.mps.is_available() else "cuda" if torch.cuda.is_available() else "cpu")
    if device.type == "cuda" or device.type == "mps":
        print(f"Using device: {device} (GPU accelerated)")
    else:
        print(f"Using device: {device} (CPU based)")
    
    # Carregar les classes des de la configuració
    class_names = config['classes']
    print(f"Classes: {class_names}")
    
    # Preparar el model i aplicar les transformacions necessàries
    model = load_model(config['model_path'], len(class_names), device)
    transform = get_transform()
    
    # Avaluar el model utilitzant les imatges de test
    correct, total = evaluate_model(
        model, test_images, transform, class_names, device
    )
    
    # Calcular i mostrar les mètriques de rendiment
    accuracy = correct / total
    print("\nGlobal results:")
    print(f"Total images: {total}")
    print(f"Hits: {correct}")
    print(f"Accuracy: {accuracy:.2%}")

    # Esborrar la carpeta amb les dades de test
    if os.path.exists(DATA_FOLDER):
        shutil.rmtree(DATA_FOLDER)

if __name__ == "__main__":
    main()