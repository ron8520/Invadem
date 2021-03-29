# A Traditional Classic Game Stickman


## Screenshot of the game:


Using command gradle build and gradle run can run the program

The JSON file has a JSONArray outside, each JSONObject inside the JOSONArray
store the each level inforamtion. It include sticmanSize (JSONObject), levelSize(JSONObject),
mushroomPos (JSONObject), flagPos(JSONObject), slimePos(JSONAraay), footPos(JSONArray), 
levelFloorHeight (JsonObject), stickmanPos(JSONObject)

The json file name is call "default.json". it can located at the same directory with the src folder and
build.gradle

If you want to change to load different level, you can go to GameEngineImp that 
located at the stickman/model/GameEngine. Go to line 22, change levels.get(currentLevelIndex) to
levels.get(1), and restart the program. You are able to load differnt level. 

However, don't change the number more than 1. In current stage, it only has 2 levels. 
It will due to IndexOutOfRange error and crash the program

when the stickman(hero) touch the mushroom, press space can shoot bullets.