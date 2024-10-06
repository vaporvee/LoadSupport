# Load Support
Shows when the player has **too less Java memory** allocated, and **plays a sound** when the game has finished loading.<br>
It is fully configurable in `config/loadsupport.toml`.

## Config 🚀
The config in `config/loadsupport.toml` is pretty self explainatory:
```toml
startSound = true
minMemory = 4.0
errorTitle = "Error: Not enough Java memory!"
errorDescription = "Please allocate at least {minMemory} GB of Java memory to your Minecraft Instance! You have currently {currentMemory} GB allocated."
```
The errorDescription does automatic line breaks. `{minMemory}` and `{currentMemory}` get replaced by their actual values.

## Planned Features 👀
- 🔊 Customizable startup sound
- 🪛 Ingame settings (Modlist support)
- 🧩 More features letting the player know why Minecraft is not loading

## Features
### Directly show a warning when too less Java Memory was selected 🚨
The player doesn't need to wait just to know that they selected the wrong amount of Java Memory.

![Screenshot](https://cdn.modrinth.com/data/cached_images/0b148e2023ca586cc64f821a2ce0ca8b87402ce5.png)

#### Blocks the title screen🚧
Make sure the player gets the correct performance experience.

<img src="https://cdn.modrinth.com/data/bnO15g6H/images/881f15c2413795ba1ba0bebd2baf4c0f4862336c.png" width="600px">

---

### Play a sound when Minecraft finished loading 🔊
Directly know when Minecraft started when beeing in the background.

Sound used: https://www.youtube.com/watch?v=h2eBoIoq5vw
