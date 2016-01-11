package com.mygdx.game.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class AndroidLauncher extends AndroidApplication {

    SQLiteHelper mydb;

	@Override
	protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mydb = new SQLiteHelper(this);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MyGdxGame(), config);
	}
}
