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
Pour plus de précisions, rendez-vous sur la page http://fablab.ensimag.fr/index.php/Frigo_Time_Machine qui décrit 
le projet et sa réalisation plus en détail.


## 3. L'architecture MVC
Pour réaliser ce site, l'utilisation du framework Play! invite à utiliser une architecture Modèle-Vue-Contrôleur. 
Le modèle est implémenté dans le dossier app/models, les vues dans le dossier app/views, et le contrôleur dans app/controllers.


  **A/ Le modèle**
Le modèle contient toutes les classes nécéssaires au bon fonctionnement du site.
* La classe User permet d'instancier des utlisateurs, qui auront donc chacun un compte propre avec les états de frigo correspondants. 
* La classe AlimentConnu permet simplement d'ajouter un aliment dans la base de données. Il s'agit d'un aliment fictif dans le sens qu'il n'existe pas nécéssairement dans le frigo de l'utilisateur.
* La classe Aliment elle permet d'instancier un aliment réellement présent dans le frigo de l'utilisateur. Cet aliment a donc une date d'entrée dans le frigo et une date de péremption
* La classe EtatFrigo permet d'instancier un nouvel objet à chaque reception de cliché depuis l'application Android.
* La classe Recette correspond aux recettes qui peuvent soient être enregistrées en tant que favorites par l'utilisateur, soit lui être suggérées par la plateforme.
* La classe ListeDeCourse correspond aux listes de courses de l'utilisateur (liste courante ou listes types)

  **B/ Les vues**  
Chaque fichier .html correspond à une vue différente. 
* Le fichier main.html est contenu dans toutes les autres pages : c'est lui qui permet d'afficher la barre de navigation et ainsi de poser le décor global du site.
* Toutes les autres pages sont contenues dans le dossier Application/
* A AJOUTER: explications pour les autres dossiers 
  
  **C/ Le contrôleur**
Le contrôleur permet de faire le lien entre le modèle et les vues, principalement via la classe Application.
Cette classe contient une méthode par page qui renvoie à chaque fois les informations nécéssaires pour la visualisation de la page en question.
Pour que le contrôleur soit bien appelé lors du chargement d'une page, il ne faut pas oublier d'ajouter le lien dans le fichier "routes" situé dans le dossier conf/.
A AJOUTER: explications sur les autres dossiers

  **D/ Les autres dossiers de app/**
A FAIRE


## 4. Annexe : le dossier public/
Ce dossier contient tous les fichiers nécéssaires à l'affichage des différentes pages.
* bootstrap/ contient les fihciers nécéssaires pour réaliser notre site à partir de cette base.
* images/ contient toutes les images utilisées au sein de notre plateforme (logos, boutons...)
* img/ contient les clichés reçus depuis l'application Android
* img_coverflow/ contient les images nécéssaires pour le bon fonctionnement du coverflow de la page Historique
* javascripts/ contient tous les scripts utilisés dans les différentes pages (menus déroulants, sous-onglets, ...)
* stylesheets/ contient tous les fichiers .css qui permettent de donner du style à notre site.
  


