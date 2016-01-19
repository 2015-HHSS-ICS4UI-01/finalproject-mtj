/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rts.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rts.game.MainGame;
import com.rts.game.MyRTSGame;
import com.rts.screens.AssetManager;

/**
 * The game's Main Menu; allows user to start a 2 player game,
 * or learn how to play the game.
 * @author besem4079
 */
public class MainMenu implements Screen{
    public final int V_WIDTH = 800;
    public final int V_HEIGHT = 600;
    
    private MyRTSGame manager;
    private MainGame newGame;
    private SpriteBatch batch;
    BitmapFont text;
    private OrthographicCamera camera;
    private Viewport viewport;
    
    private String currentPos;
    
    //menu button collisions
    private Rectangle singlePlayer;
    private Rectangle multiplayer;
    private Rectangle howToPlay;
    private Rectangle difficulty;
    
    //difficulty of a single player game
    private String[] difficultyLevels;
    private String currentDifficulty;
    
    //vector that helps tracks the coordinates of user's click
    private Vector2 clickPoint;
    
    //debugging
    private BitmapFont testing;
    private String coords;
    private String height;
    
    public MainMenu(MyRTSGame manager){
        this.manager = manager;
        singlePlayer = new Rectangle(0,0,64,64);
        howToPlay = new Rectangle(128,0,64,64);
        difficultyLevels = new String[] {"Easy", "Normal", "Hard"};
        currentDifficulty = difficultyLevels[0];
        currentPos = "Single Player";
        batch = new SpriteBatch();
        text = new BitmapFont();
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);
        clickPoint = new Vector2(); 
        camera.position.x = V_WIDTH/2f;
        // move the y position of the camera
        camera.position.y = V_HEIGHT/2f;
        // update the camera
        camera.update();
        
        //debugging
        testing = new BitmapFont();
        testing.setColor(Color.BLUE);
        coords = new String();
        height = String.valueOf(singlePlayer.height);
    }
    
    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        
        batch.begin();
        text.draw(batch, "MAIN MENU", 0, V_HEIGHT / 2, V_WIDTH, Align.center, false);
        batch.draw(AssetManager.grass,singlePlayer.x,singlePlayer.y,singlePlayer.width,singlePlayer.height);
        testing.draw(batch, coords, 128, 128);
        batch.draw(AssetManager.grass,howToPlay.x,howToPlay.y,howToPlay.width,howToPlay.height);
        testing.draw(batch, height, 256, 128);
        batch.end();
        
        //main menu inputs using the keyboard
        if(Gdx.input.isKeyPressed(Keys.ENTER)){
            System.out.println(currentPos);
            //starts single player mode
            if(currentPos.equals("Single Player")){
                manager.changeScreen(new MainGame(manager));
            }
            
            //starts multiplayer mode
            if(currentPos.equals("Multiplayer")){
                manager.changeScreen(new MainGame(manager));
            }
            
            //changes the difficulty of the single player mode
            if(currentPos.equals("Difficulty")){
                if(currentDifficulty.equals(difficultyLevels[difficultyLevels.length - 1])){
                    currentDifficulty = difficultyLevels[0];
                }else{
                    for(int i = 0; i < difficultyLevels.length; i++){
                        if(currentDifficulty.equals((difficultyLevels[i]))){
                            currentDifficulty = difficultyLevels[i+1];
                            break;
                        }
                    }
                }
            }
            
            //shows the how to play menu
            if(currentPos.equals("How to Play")){
                manager.changeScreen(new HowToPlay(manager));
            }
            
        }
        
        //main menu navigation using the keyboard
        //moves the selection down
        if(Gdx.input.isKeyJustPressed(Keys.DOWN)){
            String posChange = "";
            if(currentPos.equals("Single Player")){
                posChange = "Multiplayer";
            }
            
            if(currentPos.equals("Multiplayer")){
                posChange = "Difficulty";
            }
            
            if(currentPos.equals("Difficulty")){
                posChange = "How to Play";
            }
            
            if(currentPos.equals("How to Play")){
                posChange = "Single Player";
            }
            
            currentPos = posChange;
            
            System.out.println(currentPos);
        }
        //moves the selection up
        else if(Gdx.input.isKeyJustPressed(Keys.UP)){
            String posChange = "";
            if(currentPos.equals("Single Player")){
                posChange = "How to Play";
            }
            
            if(currentPos.equals("How to Play")){
                posChange = "Difficulty";
            }
            
            if(currentPos.equals("Difficulty")){
                posChange = "Multiplayer";
            }
            
            if(currentPos.equals("Multiplayer")){
                posChange = "Single Player";
            }
            
            currentPos = posChange;
            
            System.out.println(currentPos);
        }
        
        if(Gdx.input.isTouched()){
            //converts screen touch coordinates to ingame coordinates
            viewport.unproject(clickPoint.set(Gdx.input.getX(), Gdx.input.getY()));
            coords = String.valueOf(clickPoint.x) + " " + String.valueOf(clickPoint.y);
            
            //starts single player mode
            if(singlePlayer.contains(clickPoint.x, clickPoint.y)){
                newGame = new MainGame(manager);
                manager.changeScreen(newGame);
            }
            
//            if(multiplayer.contains(clickPoint.x, clickPoint.y)){
//                newGame = new MainGame(manager);
//                newGame.setMode("Multiplayer");
//                manager.changeScreen(newGame);
//            }
            
            //shows instructions on how to play
            if(howToPlay.contains(clickPoint.x, clickPoint.y)){
                manager.changeScreen(new HowToPlay(manager));
            }
            
//            if(difficulty.contains(clickPoint.x,clickPoint.y)){
//                if(difficultyLevel.equals("Easy")){
//                    difficultyLevel = "Medium";
//                }else if(difficultyLevel.equals("Medium")){
//                    difficultyLevel = "Hard";
//                }else{
//                    difficultyLevel = "Easy";
//                }
//            }
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void dispose() {
        
    }
    
}
