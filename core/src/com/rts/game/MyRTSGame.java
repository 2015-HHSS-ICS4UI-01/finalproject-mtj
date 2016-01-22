package com.rts.game;

import com.rts.screens.MainMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.rts.screens.AssetManager;

public class MyRTSGame extends Game {

    @Override
    public void create() {
        AssetManager.load();
        setScreen(new MainMenu(this));
    }

    public void changeScreen(Screen s) {
        setScreen(s);
    }
}
