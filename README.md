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
- T??l??chargez le projet sur votre poste.
- Ouvrez le terminal dans le r??p??rtoire du projet et tapez la commande `vagrant up jenkins` puis entrer.
- ?? la fin de l'installation copier le code g??n??r?? par Jenkins.
- Ouvrez un nouveau terminal dans le r??p??rtoire du projet et tapez la commande `vagrant up docker` puis entrer.
- ?? la fin de l'installation tapez dans le terminal `vagrant ssh docker` puis entrer vous allez vous connecter ?? la machine virtuelle.
- Tapez dans le terminal de jenkins `vagrant ssh jenkins` puis entrer vous allez vous connecter ?? la machine virtuelle.
- Tapez `ssh-keygen` ensuite appuyez sur entrer 4 fois ce qui va g??n??rer une cl?? publique et une priv??e pour la machine.
- Tapez `ssh-copy-id vagrant@192.168.33.20` puis entrer puis entrer ATTENTION ! Pour que cette commande fonctionne, la machine de docker doit ??tre d??marr??e.
- La machine vous r??pondra par :

```
/usr/bin/ssh-copy-id: INFO: Source of key(s) to be installed: "/home/vagrant/.ssh/id_rsa.pub"
The authenticity of host '192.168.33.20 (192.168.33.20)' can't be established.
ECDSA key fingerprint is SHA256:cDqQ1xEeETP7fkQ7CGp2xgeabPwYXMItvB8kM/6+mM4.
Are you sure you want to continue connecting (yes/no)?
```

- Il faut noter que le SHA256 est sp??cifique ?? chaque machine
- Tapez `yes` puis Entrer pour confirmer la connection.
- La machine vous demandera le mot de passe de la machine docker. Tapez `vagrant` puis entrer et appuyez sur entrer. La machine r??pondra par `Number of key(s) added: 1`.
- Pour tester la connection ssh tapez `ssh 192.168.33.20`. La machine doit vous r??pondre :

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
- Tapez `docker-compose up -d` puis entrer. Cette commande d??marre l'initialisation des bases de donn??es.

### SonarQube

- Ouvrez votre navigateur pr??f??rer et tapez dans la barre d'adresse `192.168.33.10:9000`.
- Le site de SonarQube s'ouvre et il vous demande de saisir le nom d'utilisateur et le mot de passe.
- Utilisateur `admin` et mot de passe `admin` puis entrer.
- Le site vous demande de changer le mot de passe.
- Apr??s avoir chang?? le mot de passe appuyer sur la lettre `A` en haut ?? droite puis appuyer sur `My account`.
- Dans l'onglet `Security` sous `Generate Tokens` Entrer un nom de token et appuyer sur `Generate`.
- SonarQube vous g??n??re un code. Copiez-le pour l'utiliser apr??s dans Jenkins.

### Jenkins

- Ouvrez votre navigateur pr??f??rer et tapez dans la barre d'adresse `192.168.33.10:8080`.
- Jenkins vous demande d'ins??rer le mot de pass.
- Ins??rer le mot de passe copier depuis le terminal de vagrant@jenkins et tapez sur suivant.
- Choisissez d'installer les plug-ins par d??fauts.
- Apr??s l'installation, vous pouvez passer l'??tape de cr??er un compte en appuyant sur `Continuer en tant qu'administrateur`.
- Valider le url `192.168.33.10:8080`
- Vous pouvez maintenant changez le mot de passe de l'admin en appuyant sur admin ensuite configurer.
- Appuyer sur `Administrer Jenkins` puis `Configuration globale des outils`.
- Dans `Maven` appuyez sur `Ajouter Maven`.
- Dans `Nom` tapez `LOCAL_MAVEN`.
- D??cochez `Install automatically`.
- Dans la machine vagran@jenkins tapez `mvn --version` et copier le chemin de `Maven home` dans notre cas c'est `/usr/share/maven`.
- Dans le navigateur coller `/usr/share/maven` dans `MAVEN_HOME` puis appuyer sur `Sauver`.
- Appuyer sur `Tableau de bord`.
- Appuyer de nouveau sur `Administrer Jenkins` puis `Manage Pulgins`.
- dans l'onglet `Available` rechercher et cockez les plugins suivant :

```
- Warnings Next Generation Plugin
- JaCoCo plugin
- SonarQube Scanner for Jenkins
```

- Apr??s avoir cochez ces trois plugins appuyer sur `Install without restart`.
- A la fin de l'installation revenez sur `Administrer Jenkins` puis `Configure system`.
- Sous `SonarQube servers` cochez `Environment variables Enable injection of SonarQube server configuration as build environment variables ` puis appuyez sur `Add SonaQube`.
- Dans `Name` tapez `LOCAL_SONAR` et pour `Server URL` tapez `http://192.168.33.10:9000`.
- Sous `Server authentication token` appurez sur `Add` puis `Jenkins`.
- Sous `Kind` choisissez `Secret text` puis sous `Secret` coller le code du Token que SonarQube a g??n??r?? puis appuyer sur `OK`.
- Appuyez sur `Sauver`.
- Appuyer sur `Nouveau Item` pour cr??er un job.
- Saisissez le nom `favorite` et choisissez `Pipeline` puis appuyez sur `Ok`.
- Dans `Pipeline` choisissez `Pipeline scipt from SCM` comme d??finition.
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
- Copier tout le r??sultat de `-----BEGIN RSA PRIVATE KEY-----` jusqu'?? `-----END RSA PRIVATE KEY-----`.
- Dans le navigateur coller le private key et appuyer sur `Ajouter`.
- Appuyer sur `Generate Pipeline Script` vous devez avoir le r??sultat suivant :

```
withCredentials([sshUserPrivateKey(credentialsId: '2631e1d0-85aa-40f1-a410-57f03f11fe86', keyFileVariable: 'SSH_KEY_FOR_FAVORITE')]) {
    // some block
}
```

- Appuyer sur `Back`.
- ATTENTION ! n'appuyez pas sur `Lancer un build`. vous devez attendre jusqu'?? ce que les webservices `favortie-orderWS`, `favorite-productWS` et `favorite-userWS` que vous allez cr??er soit d??ploy??s.
- Cr??ez 3 nouveaux items `Pipeline` en les nommant respectivement `favortie-orderWS`, `favorite-productWS` et `favorite-userWS`.
- Pour chaque project r??p??tez les m??mes ??tapes sans les ??tapes du `Pipeline Syntax` en leur donnant les url Git suivant :

```
Pour favorite-orderWS : https://github.com/DuvCharles/favorite-orderWS
Pour favorite-productWS : https://github.com/hajjoujti/favorite-productWS
Pour favorite-userWS : https://github.com/DuvCharles/favorite-userWS
```

- Vous pouvez choisir que la fin du build d'un service d??clenche le build d'un autre service et le dernier service build?? d??clenche le build du projet `favorite`.
- Vous pouvez aussi d??clencher le build de chaque service manuellement et ?? la fin de succ??s de tous vous d??clencherez le projet `favorite`.

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
