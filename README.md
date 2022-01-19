# Favorite
![version](https://img.shields.io/badge/Maintained%3F-no-red.svg) [![GitHub contributors](https://img.shields.io/github/contributors/KevinLennoz/Favorite.svg)](https://Github.com/KevinLennoz/Favorite/graphs/contributors/) [![Open Source? Yes!](https://badgen.net/badge/Open%20Source%20%3F/Yes%21/blue?icon=github)](https://github.com/KevinLennoz/Favorite/)
<br/>
![version](http://ForTheBadge.com/images/badges/built-by-developers.svg) ![version](http://ForTheBadge.com/images/badges/built-with-love.svg)
[![ForTheBadge makes-people-smile](http://ForTheBadge.com/images/badges/makes-people-smile.svg)](http://ForTheBadge.com) [![ForTheBadge winter-is-coming](http://ForTheBadge.com/images/badges/winter-is-coming.svg)](http://ForTheBadge.com)

---

## Langages
![css version](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![css version](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![css version](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![css version](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)

---

## Logiciels
![css version](https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white)
![Eclipse](https://img.shields.io/badge/Eclipse-FE7A16.svg?style=for-the-badge&logo=Eclipse&logoColor=white)
![Firefox](https://img.shields.io/badge/Firefox-FF7139?style=for-the-badge&logo=Firefox-Browser&logoColor=white)
![Google Chrome](https://img.shields.io/badge/Google%20Chrome-4285F4?style=for-the-badge&logo=GoogleChrome&logoColor=white)
![css version](https://img.shields.io/badge/Discord-7289DA?style=for-the-badge&logo=discord&logoColor=white)
![Microsoft Office](https://img.shields.io/badge/Microsoft_Office-D83B01?style=for-the-badge&logo=microsoft-office&logoColor=white)
![css version](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)

---

## Installation
### Vagrant
- Installez vagrant sur votre PC.
- Téléchargez le projet sur votre poste.
- Ouvrez le terminal dans le répértoire du projet et tapez la commande `vagrant up jenkins` puis entrer.
- À la fin de l'installation copier le code généré par Jenkins.
- Ouvrez un nouveau terminal dans le répértoire du projet et tapez la commande `vagrant up docker` puis entrer.
- À la fin de l'installation tapez dans le terminal `vagrant ssh docker` puis entrer vous allez vous connecter à la machine virtuelle.
- Tapez dans le terminal de jenkins `vagrant ssh jenkins` puis entrer vous allez vous connecter à la machine virtuelle.
- Tapez `ssh-keygen` ensuite appuyez sur entrer 4 fois ce qui va générer une clé publique et une privée pour la machine.
- Tapez `ssh-copy-id vagrant@192.168.33.20` puis entrer puis entrer ATTENTION ! Pour que cette commande fonctionne, la machine de docker doit être démarrée.
- La machine vous répondra par :
```
/usr/bin/ssh-copy-id: INFO: Source of key(s) to be installed: "/home/vagrant/.ssh/id_rsa.pub"
The authenticity of host '192.168.33.20 (192.168.33.20)' can't be established.
ECDSA key fingerprint is SHA256:cDqQ1xEeETP7fkQ7CGp2xgeabPwYXMItvB8kM/6+mM4.
Are you sure you want to continue connecting (yes/no)?
```
- Il faut noter que le SHA256 est spécifique à chaque machine
- Tapez `yes` puis Entrer pour confirmer la connection.
- La machine vous demandera le mot de passe de la machine docker. Tapez `vagrant` puis entrer et appuyez sur entrer. La machine répondra par `Number of key(s) added: 1`.
- Pour tester la connection ssh tapez `ssh 192.168.33.20`. La machine doit vous répondre :
```
Welcome to Ubuntu 18.04.6 LTS (GNU/Linux 4.15.0-163-generic x86_64)

 * Documentation:  https://help.ubuntu.com/
 * Management:     https://landscape.canonical.com/
 * Support:        https://ubuntu.com/advantage

  System information as of Tue Jan 18 16:15:03 UTC 2022

  System load:                    0.0
  Usage of /:                     6.0% of 61.80GB
  Memory usage:                   84%
  Swap usage:                     59%
  Processes:                      119
  Users logged in:                1
  IP address for eth0:            10.0.2.15
  IP address for eth1:            192.168.33.20
  IP address for docker0:         172.17.0.1
  IP address for br-5091df7a2377: 172.18.0.1
  IP address for br-712275afbbf3: 172.19.0.1


This system is built by the Bento project by Chef Software
More information can be found at https://github.com/chef/bento
Last login: Tue Jan 18 15:59:35 2022 from 10.0.2.2
```
- Maintenant la machine Jenkins peut controller la machine docker.

### Docker
- Sur le terminal vangrant@docker tapez `cd ../../vagrant/docker-compose-file` puis entrer.
- Tapez `docker-compose up -d` puis entrer. Cette commande démarre l'initialisation des bases de données.
- 
### Jenkins
- Ouvrez votre navigateur préférer et tapez dans la barre d'adresse `192.168.33.10:8080`.
- Jenkins vous demande d'insérer le mot de pass.
- Insérer le mot de passe copier depuis le terminal de vagrant@jenkins et tapez sur suivant.
- Choisissez d'installer les plug-ins par défauts.
- Après l'installation, vous pouvez passer l'étape de créer un compte en appuyant sur `Continuer en tant qu'administrateur`.
- Valider le url `192.168.33.10:8080`
- Vous pouvez maintenant changez le mot de passe de l'admin en appuyant sur admin ensuite configurer.
- Appuyer sur `Administrer Jenkins` puis `Configuration globale des outils`.
- Dans `Maven` appuyez sur `Ajouter Maven`.
- Dans `Nom` tapez `LOCAL_MAVEN`.
- Décochez `Install automatically`.
- Dans la machine vagran@jenkins tapez `mvn --version` et copier le chemin de `Maven home` dans notre cas c'est `/usr/share/maven`.
- Dans le navigateur coller `/usr/share/maven` dans `MAVEN_HOME` puis appuyer sur `Sauver`.
- Appuyer sur `Tableau de bord`.
- Appuyer sur `Nouveau Item` pour créer un job.
- Saisissez le nom `favorite` et choisissez `Pipeline` puis appuyez sur `Ok`.
- Dans `Pipeline` choisissez `Pipeline scipt from SCM` comme définition.
- Dans `SCM` choisissez `Git`. et dans `Repository URL` vous tapez `https://github.com/KevinLennoz/Favorite.git`.
- Changer le `Branch specifier` selon le nom de la branche principale du projet Git. Dans ce cas c'est `*/main`
- Dans `Scipt Path` tapez `Jenkinsfile`.
- Appuyez sur `Sauver`.
- Appuyer sur `Pipeline Syntax`.
- Dans `Sample step` choisissez `withCredentials: Bind credentials to variables`.
- Dans `Bindings` choisissez `SSH User Private Key`.
- Dans `Key File Variables` tapez `SSH_KEY_FOR_FAVORITE`.
- Dans `Credentials` appuyez sur `Ajouter` puis `Jenkins`.
- Dans `Type` choisissez `SSH Username with private key`.
- Dans `ID` tapez `2631e1d0-85aa-40f1-a410-57f03f11fe86`.
- Dans `Private Key` choisissez `Enter directly` et appuyer sur `Add`.
- Maintenant sur le terminal dans la machine vagrant@jenkin tapez `cat .ssh/id_rsa` puis entrer.
- Copier tout le résultat de `-----BEGIN RSA PRIVATE KEY-----` jusqu'à `-----END RSA PRIVATE KEY-----`.
- Dans le navigateur coller le private key et appuyer sur `Ajouter`.
- Appuyer sur `Generate Pipeline Script` vous devez avoir le résultat suivant :
```
withCredentials([sshUserPrivateKey(credentialsId: '2631e1d0-85aa-40f1-a410-57f03f11fe86', keyFileVariable: 'SSH_KEY_FOR_FAVORITE')]) {
    // some block
}
```
- Appuyer sur `Back`.
- ATTENTION ! n'appuyez pas sur `Lancer un build`. vous devez attendre jusqu'à ce que les webservices `favortie-orderWS`, `favorite-productWS` et `favorite-userWS` que vous allez créer soit déployés.
- Créez 3 nouveaux items `Pipeline` en les nommant respectivement `favortie-orderWS`, `favorite-productWS` et `favorite-userWS`.
- Pour chaque project répétez les mêmes étapes sans les étapes du `Pipeline Syntax` en leur donnant les url Git suivant :
```
Pour favorite-orderWS : https://github.com/DuvCharles/favorite-orderWS
Pour favorite-productWS : https://github.com/hajjoujti/favorite-productWS
Pour favorite-userWS : https://github.com/DuvCharles/favorite-userWS
```
- Vous pouvez choisir que la fin du build d'un service déclenche le build d'un autre service et le dernier service buildé déclenche le build du projet `favorite`.
- Vous pouvez aussi déclencher le build de chaque service manuellement et à la fin de succès de tous vous déclencherez le projet `favorite`.
---

## Suivi du projet
Suivez les differentes phases du projet sur notre Trello

[<img alt="Trello" src="https://img.shields.io/badge/Trello-%23026AA7.svg?style=for-the-badge&logo=Trello&logoColor=white" />](https://trello.com/b/tOmQ9nzc/projet-2)

---

### Participants au projet

<p align="center"><a href="https://github.com/DuvCharles"><img src="https://img.shields.io/github/followers/DuvCharles?label=follow%20cDuvCharles&style=social" alt="DuvCharles"/></a></p>
<p align="center"><img src="https://github-readme-stats.vercel.app/api?username=DuvCharles&theme=blue-white" alt="DuvCharles"/></p><br/>

<p align="center"><a href="https://github.com/KevinLennoz"><img src="https://img.shields.io/github/followers/KevinLennoz?label=follow%20KevinLennoz&style=social" alt="KevinLennoz"/></a></p>
<p align="center"><img src="https://github-readme-stats.vercel.app/api?username=KevinLennoz&theme=blue-white" alt="KevinLennoz"/></p><br/>

<p align="center"><a href="https://github.com/hajjoujti"><img src="https://img.shields.io/github/followers/hajjoujti?label=follow%20hajjoujti&style=social" alt="hajjoujti"/></a></p>
<p align="center"><img src="https://github-readme-stats.vercel.app/api?username=hajjoujti&theme=blue-white" alt="hajjoujti"/></p><br/>

<p align="center"><a href="https://github.com/tru3v0r"><img src="https://img.shields.io/github/followers/tru3v0r?label=follow%20tru3v0r&style=social" alt="tru3v0r"/></a></p>
<p align="center"><img src="https://github-readme-stats.vercel.app/api?username=tru3v0r&theme=blue-white" alt="tru3v0r"/></p><br/>

<p align="center"><img src="https://contributor-overtime-api.apiseven.com/contributors-svg?chart=contributorOverTime&repo=KevinLennoz/Favorite" alt="Favorite"/></p>
