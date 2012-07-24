package com.greenolvi.android.game;

import android.app.Activity;
import android.os.Bundle;

import com.greenolvi.android.game.gfx.ScreenView;

public class AndroidGame extends Activity {
	
	private ScreenView mScreenView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mScreenView = new ScreenView(this);
        
        setContentView(mScreenView);
    }
}