# Tamagotchi

Cammy Take-Home Coding Exercise

This project can be loaded using intellij 15 and scala sdk 2.11.7


And demonstrated using the console:
```
Hello, please Enter a name for your pet
Rex
Hello friend my name is Rex
Actions: Feed, Play, Sleep, Clean, Quit:
Feed
(Pet(Rex,1,2,2,53),Alive(true,0))
Actions: Feed, Play, Sleep, Clean, Quit:
Clean
(Pet(Rex,2,4,1,52),Alive(true,0))
Actions: Feed, Play, Sleep, Clean, Quit:
Clean
(Pet(Rex,3,6,0,51),Alive(true,0))
Actions: Feed, Play, Sleep, Clean, Quit:
sleep
(Pet(Rex,4,8,0,50),Alive(false,0))
Actions: Feed, Play, Sleep, Clean, Quit:
Quit
Actions: Feed, Play, PutToSleep, Clean, Quit:
(Pet(Rex,5,9,0,50),Alive(false,1))
```

The rules are as follows:

* A pet has five attributes: name, age, hunger, poop, and happiness.
* A pet dies is its age or its hunger reaches 100.
* There are four actions to be performed on a pet:  Feed, Play, Sleep and Clean.
    Fedding a pet reduces its hunger but generates poop.
    Playing with a pet increases the pet's happiness unless he is dirty. If a pet is unhappy below 20 it will refuse to eat.
    If a pet its put to sleep it takes lonnger for the pet to get hungry.
    Cleaning a pet allows the increase of happiness when the play action is triggered.
* Every tick the hunger and age of the pet increase
    

