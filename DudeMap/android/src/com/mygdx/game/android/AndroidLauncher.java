package com.mygdx.game.android;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.gwt.resources.client.impl.ImageResourcePrototype;
import com.mygdx.game.DudeMap;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new DudeMap(), config);
	}

    @Override
    public void runOnUiThread(Runnable runnable) {

    }

    @Override
    public void startActivity(android.content.Intent intent) {

    }

    @Override
    public android.view.WindowManager getWindowManager() {
        return null;
    }
}
