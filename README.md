# Load Support
Shows when the user has too less Java memory allocated, and plays a sound when the game has loaded.<br>
It is fully configurable in `config/loadsupport.toml`.
## Directly Shows a warning message
![image](https://github.com/user-attachments/assets/b571a607-ec88-4032-9996-876637c156c1)
## Blocks the title screen
![image](https://github.com/user-attachments/assets/e05b52bd-3cf2-4526-826b-db62c8723831)
## Config
The config in `config/loadsupport.toml` is pretty self explainatory:
```toml
startSound = true
minMemory = 4.0
errorTitle = "Error: Not enough Java memory!"
errorDescription = "Please allocate at least {minMemory} GB of Java memory to your Minecraft Instance! You have currently {currentMemory} GB allocated."
```
The errorDescription does automatic line breaks. `{minMemory}` and `{currentMemory}` get replaced by their actual values.
