# Lab 8 : Threads et AsyncTask en Android
analyste:souaid med amine
**Contexte :** 4ème année Ingénierie Cybersécurité & Réseaux (EMSI)  
**Sujet :** construit une application Android avec des boutons : un bouton lance un traitement long, un autre affiche un Toast immédiatement.  

---

Ce projet est une application Android éducative conçue pour démontrer la gestion des tâches asynchrones et l'importance de ne pas bloquer le thread principal (UI Thread).

## Description

L'application illustre deux méthodes courantes pour exécuter des opérations lourdes en arrière-plan tout en maintenant l'interface utilisateur fluide et réactive.

##  Fonctionnalités

1.  **Chargement d'image (Thread + Handler)** : Simule le téléchargement ou le chargement d'une image complexe en utilisant un `Thread` Java standard et met à jour l'interface via un `Handler`.
2.  **Calcul Intensif (AsyncTask)** : Exécute un calcul mathématique lourd en utilisant la classe `AsyncTask`, avec une mise à jour en temps réel d'une barre de progression.
3.  **Test de Réactivité** : Un bouton permet d'afficher un message (`Toast`) à tout moment pour prouver que l'interface n'est pas figée pendant les calculs.

## Concepts Techniques

-   **UI Thread** : Gestion de l'affichage et des interactions utilisateur.
-   **Background Thread** : Exécution de tâches longues (sommeil simulé, calculs).
-   **Handler / Looper** : Mécanisme pour renvoyer des résultats du thread secondaire vers le thread principal.
-   **AsyncTask** : Modèle structuré pour gérer les étapes d'une tâche (avant, pendant, progression, après).

## Aperçu de l'Interface

L'interface se compose de :
-   Un label de statut (`TextView`) pour informer l'utilisateur.
-   Une barre de progression (`ProgressBar`) pour le feedback visuel.
-   Une zone d'affichage d'image (`ImageView`).
-   Trois boutons d'action ergonomiques.

## Installation

1. Cloner le dépôt.
2. Ouvrir le projet dans **Android Studio**.
3. Synchroniser avec Gradle.
4. Lancer sur un émulateur ou un appareil physique (API 24+ recommandé).

## Test et Validation

<img width="311" height="696" alt="image" src="https://github.com/user-attachments/assets/a5e4bfd0-924e-434b-bed2-bc9cf57cdfa1" />


<img width="313" height="731" alt="image" src="https://github.com/user-attachments/assets/eb7e785e-94fd-475a-9599-49c756e57e62" />

---
*Réalisé dans le cadre du cours sur la programmation concurrente sous Android.*
