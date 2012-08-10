package com.greenolvi.android.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.greenolvi.android.game.gfx.ScreenView;

public class AndroidGame extends Activity {
	
	private ScreenView mScreenView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        mScreenView = new ScreenView(this);
        
        setContentView(mScreenView);
    }

	@Override
	protected void onPause() {
		super.onPause();
		mScreenView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mScreenView.onResume();
	}
    
    
    
}