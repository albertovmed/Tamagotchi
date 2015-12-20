# Tamagotchi

Cammy Take-Home Coding Exercise

This project can be loaded using intellij 15 and scala sdk 2.11.7


And demonstrated using the console:

Hello, please Enter a name for your pet
Rex
Hello friend my name is Rex
Actions: Feed, Play, Sleep, Quit:
Feed
Actions: Feed, Play, Sleep, Quit:
Play
Actions: Feed, Play, Sleep, Quit:
Sleep
Actions: Feed, Play, Sleep, Quit:
Quit
Actions: Feed, Play, PutToSleep, Quit:

The rules are as follows:

1) A pet has five attributes: name, age, hunger, poop, and happiness.
2) A pet dies is its age or its hunger reaches 100.
3) There are four actions to be performed on a pet:  Feed, Play, Sleep and Clean.
    Fedding a pet reduces its hunger but generates poop.
    Playing with a pet increases the pet's happiness unless he is dirty. If a pet is unhappy below 20 it will refuse to eat.
    If a pet its put to sleep it takes lonnger for the pet to get hungry.
    Cleaning a pet allows the increase of happiness when the play action is triggered.
4)Every tick the hunger and age of the pet increase
    

