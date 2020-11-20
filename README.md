# First Person Project
 This is a program that simulates the way that humans see. The technique is fairly similar to the one used to create the game *Wolfenstein* as well as many others.
 
 # Table of contents
 - [What can it do?](#what-can-it-do?)
 - [How does it work?](#how-does-it-work?)
 - [Raytracing](#Raytracing)
 
 # What can it do?
 This program shows a very simplified and easy to understand version of how more complicated first person games are built. The code allows people to make their own maps as well as see the underlying code needed to create other similar games of their own.
 
 # How does it work?
 This program is a simple raycaster with a different way of displaying the graphics added on top. The way that I wrote the code interprets every ray as a ray of light, which then fills one column of the user's vision. When rays are displayed in this way on the users screen, it will look like they are seeing the program from the in-game person's perspective.

<a href="https://raw.githubusercontent.com/jwMaxwell/First-Person-Project/main/demo/RayMapping.png?token=AL4DQSETBYIPRY4QECFA6CC7W4L5I" target="_blank"><\a>

 Raytraing is not only used to simulate the vision, however. In order for the player to move relative to the direction they are facing rather than the position on the map (as you would experience in top down games such as the first zelda games), you have to choose the center ray and move relative to it. In order to move forward, you must move in the direction of the ray, to move backward you must move in the direction opposite of the ray, and so on. While this is not particularly difficult to accomplish, it is pretty jarring to find out about its necessity first-hand.
 The final thing I would like to mention is how the map is generated. The map is generated by a given string that is hard-coded into the program. This string allows for simple changes to be made to the map and for users to exercise their creativity. Generating the map this way also just so happens to be the easiest map generation technique as well.
 
 # Raytracing
 Raytracing is where many rays are shot from a single point and continue until they reach a wall or some other stopping point. You can think of these rays as beams of light coming from a flashlight. Those beams will continue until they hit another object and the person holding the flashlight can only see where the flashlight is pointing. This is similar to how the raytracing works in my program as well.

