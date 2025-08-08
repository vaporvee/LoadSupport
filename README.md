# Load Support
Shows when the player has **too less Java memory** allocated, and **plays a sound** when the game has finished loading.<br>
It is fully configurable in `config/loadsupport.toml`.

### CurseForge
I will never release this mod on Curseforge but you can do Modpacks wherever you like with this mod.
This mod is free and open source and i didn't create Minecraft so just use it how you like.

## Config 🚀
The config in `config/loadsupport.toml` is pretty self explainatory:
```toml
loadFinishSound = "minecraft:ui.toast.challenge_complete"
minMemory = 4.0
errorTitle = "Error: Not enough Java memory!"
errorMinMemory = "Please allocate at least {minMemory} GB of Java memory to your Minecraft instance!"
errorCurrentMemory = "You have currently {currentMemory} GB allocated."
memoryInfoLink = "https://github.com/vaporvee/LoadSupport/wiki/How-to-allocate-more-memory-to-your-Minecraft-instance"
```
`{minMemory}` and `{currentMemory}` get replaced by their actual values.

## Planned Features 👀
- 🪛 Ingame settings (Modlist support)
- 🧩 More features letting the player know why Minecraft is not loading

## Features
### Directly show a warning when too less Java Memory was selected 🚨
The player doesn't need to wait just to know that they selected the wrong amount of Java Memory.

![Screenshot](https://cdn.modrinth.com/data/cached_images/0b148e2023ca586cc64f821a2ce0ca8b87402ce5.png)

#### Blocks the game window🚧
Make sure the player gets the correct performance experience.

---

### Play a sound when Minecraft finished loading 🔊
Directly know when Minecraft started when beeing in the background.

Sound used: https://www.youtube.com/watch?v=h2eBoIoq5vw

