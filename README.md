# GameAssetLoader
Loads audio and image files into memory to quickly be used when required.

Uses HashMap so that assets can be loaded with efficiency O(1).

Ignores file extension after loading into memory.

Usage:
```Java
double volume = 0.5;
Image playerOne;
ResourceLoader resourceLoader = new ResourceLoader("assets"); //Replacing assets with the relative folder name

if (resourceLoader.loadingSuccessful()) {
  //All assets loaded successfully
  ResourceLoader.getAudio("MenuMusic").setCycleCount(AudioClip.INDEFINITE); //Set backing music to loop
  ResourceLoader.playAudio("MenuMusic", volume);
  playerOne = ResourceLoader.getImage("playerOneIdle");
}
```
