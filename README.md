frigoPlay
=========

Depôt du projet **Frigo Time Machine** utilisant la version du framework Play! 1.2.5

Ce dépôt suit donc l'architecture fournie par Play!, et est ainsi constitué de 5 dossiers :
   * META-INF  : contient des fichiers générés automatiquement par Play!
   * app  : contient le modèle MVC au coeur du projet
   * conf  : contient les fichiers de configuration 
   * public  : contient les fichiers nécéssaires à l'exécution du code principal du dossier app (notamment les scripts, les fichiers .css et les images)
   * test



## 1. Remarques générales
Pour utiliser ce dépôt, vous devez au préalable avoir installé 
   * Eclipse (http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/junosr2) 
   * Play! 1.2.5 (http://www.playframework.com/download) sur votre machine sous Windows 7
   * l'application GitHub (http://windows.github.com/)
Nous avons choisi cette version car elle permet l'utilisation du CRUD contrairement aux versions plus récentes. 
Cet outil s'est révélé assez délicat à aborder (voir les tutoriels http://www.playframework.com/documentation/2.1.1/Home), mais précieux une fois sa prise en main terminée.

Une fois ces logiciels installés, voici la démarche à suivre :
   * Ouvrez l'application GitHub sur votre ordinateur. Ajouter frigoPlay à vos Repositories.
   * Ouvrez Eclipse et choisissez le dossier de votre workspace (par exemple /workspace/frigoPlay)
   * Une fois Eclipse démarrer, dans le menu "Fichier", choisissez "Import..." puis dans Git sélectionnez "Projects from Git". 
    Appuyez sur "Next" puis choisissez "Local", "Next", puis donnez le path vers le dépôt sur votre ordinateur (dans le dossier "GitHub").
    Appuyez à nouveau sur "Next", sélectionnez "Import as a general project" et enfin le dernier "Next".
Vous avez maintenant accès au code de la plateforme. Maintenant pour visualiser le site :
   * Ouvrez un terminal, et rendez-vous dans le dossier contenant le dépôt.
   * Tapez la commande "play start"
   * Tapez la commande "play run" 
   * Si le terminal vous donne le message d'erreur "port 9000 unavailable", allez dans le fichier "conf/application.conf" et modifiez "http.port=9000" en "http.port=9002" par exemple.
   * Ouvrez votre navigateur préféré à l'adresse http://localhost:9000/login (ou 9001 si vous avez changé le port)
Ca y est !


## 2. Présentation globale du site
Le but de cette plateforme web est de permettre à un utilisateur d'utiliser son frigo de manière optimale. 
Pour cela, une application Android (dont voici le dépôt : https://github.com/kenshuri/FTM_Android) permet à un 
smartphone situé dans le frigo de prendre des clichés à chaque ouverture de porte et d'envoyer ces clichés à la 
plateforme. 
Celle-ci va alors permettre à l'utilisateur à l'aide de ces clichés de connaître l'état de son frigo, actuellement et 
dans le passé. 
Elle va aussi lui permettre de générer des listes de courses, de consulter des recettes (favorites ou suggérées à partir 
du contenu du frigo) ou encore de recevoir des alertes lorsqu'un aliment du frigo arrive à sa date de péremption. 


## 3. L'architecture MVC
Pour réaliser ce site, l'utilisation du framework Play! invite à utiliser une architecture Modèle-Vue-Contrôleur. 
Le modèle est implémenté dans le dossier app/models, les vues dans le dossier app/views, et le contrôleur dans app/controllers.

  ### A/ Le modèle


  ### B/ Les vues
  
  
  ### C/ Le contrôleur
  
  
  ### D/ Les uatres dossiers de app/
  
  


