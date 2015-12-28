package com.mygdx.game.android;

import android.os.Bundle;
import android.widget.ListView;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

import actor.Player;

public class AndroidLauncher extends AndroidApplication {

    SQLiteHelper mydb;

	@Override
	protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mydb = new SQLiteHelper(this);
//        Player player = mydb.getPlayer();
//        if(player == null){
            Player player = new Player("Player", 0, 0);
            mydb.setPlayer(player);
//        }

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MyGdxGame(player), config);
	}
}
