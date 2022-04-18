# Rapport  AOC

## Notre modèle

![](https://i.imgur.com/r2QE7HM.png)


## Choix de conception

Globalement nous avons essayé respecter les schémas donnés les diagrammes de séquence présentés.

#### Diffusion Atomique

La diffusion atomique est reliée à tous les canaux par une liste. Lorsque que le capteur ```tick()```, la classe DiffusionAtomique exécute la méthode ```update()``` sur tous les canaux de sa liste.

#### Diffusion Sequentielle

La diffusion sequentielle est reliée à tous les canaux par une liste. Lorsque que le capteur ```tick()```, la classe DiffusionSequentielle fait un pile ou face pour exécute la méthode ```update()``` sur tous les canaux de sa liste.

#### Diffusion Epoque

La diffusion Epoque est reliée à tous les canaux par une liste. Lorsque que le capteur ```tick()```, la classe DiffusionEpoque pour chaque canal lance 2 randoms et les compare. Si la condition de la comparaison est vérifié alors on update ce canal et on passe au suivant. Si la condition n'est pas respecté alors on passe directement au suivant.
Si aucun canal n'est update (vérifier avec la variable ```ticked```) alors on reboucle sur les canaux en incrémentant les chances que la condition de comparaison entre les 2 randoms soit vérifié.
#### Afficheur

La classe ```Afficheur``` possède une attribut memory et la méthode ```printMemory()``` pour garder en mémoire les valeurs qu'il reçoit et pouvoir les afficher facilement. Utile pour débugguer.

#### Les classes GetValue - Update

Les classes GetValue et Update sont les méthodes d'invocations du pattern Active Object. On les a créées au début pour respecter le pattern le temps de comprendre exactement sont fonctionnements mais depuis nous les avons remplacés par des lambdas expressions.


### Les tests

Chaque appels de ```tick()``` est séparé par un ```Thread.sleep(n)``` pour permettre à "l'aller-retour"  Capteur->Canal->update()->Afficheur->getValue()->Capteur d'aller chercher la valeur du Capteur. Sans cela, un ```tick()```instantanément après un autre va changer la valeur du Capteur AVANT que la première exécution du ```tick()``` n'ait pu aller chercher la première valeur.

Chaque tests correspond à une stratégie différente et vérifie une condition précise et différente.

* Atomique, on vérifie que chaque afficheur est bien la valeur du capteur à la fin de chaque appel de ```tick()```.
* Sequentielle, on vérifie que les afficheurs est bien la même séquence de valeur à la fin de chaque appel de ```tick()```.
* Epoque, on vérifie qu'à chaque appel de ```tick()```, au moins 1 afficheur reçoit la valeur du capteur.

## Ce qu'il reste à faire

Implémenter une méthode d'attente automatique entre les ticks pour ne pas remplacer la valeur du capteur tant que le tick précédent n'a pas récupérer cette valeur.
