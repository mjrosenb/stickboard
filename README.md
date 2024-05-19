# Stickboard: an alternate android keyboard

About the name: I think of it as a grid of joysticks that can be pushed in
8 directions

## Issues
* Keyboard size is hard-coded.  It works on a pixel 6, good luck if you have a different device
* Can't swipe off of the keyboard. I'm confused about this one because it also doesn't work even though the area to the left of the keyboard is an area that I've drawn on
* Auto-capitalization could be smarter
* No visual feedback for capslock mode
* The basic app that Android Studio created still exists.

## Future work
* Any amount of runtime configuration
* dead keys / accents
* skews + other linear transformations on the keyboard layout. I think a rhombus leaning right would make the keyboard easier to use due to where the thumb sits
* chording?
* I think some apps have a 'done' action that is different from return, which could be useful.