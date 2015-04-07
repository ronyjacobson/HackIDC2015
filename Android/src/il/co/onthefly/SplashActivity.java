package il.co.onthefly;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class SplashActivity extends Activity {

	private final int SPLASH_DISPLAY_LENGHT = 3000;

    /** Called when the activity is first created. */
    @SuppressLint({ "InlinedApi", "NewApi" }) @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        
        View decorView = getWindow().getDecorView();
		// Hide the status bar.
		int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
		decorView.setSystemUiVisibility(uiOptions);
		// Remember that you should never show the action bar if the
		// status bar is hidden, so hide that too if necessary.
		android.app.ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		// Set Layout 
        setContentView(R.layout.activity_splash);
        /**
        /* New Handler to start the Menu-Activity 
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the next Activity. */
            	/* Prompt login if the user isn't logged in or the type choosing screen otherwise. */
            	// if (!LoginActivity.user.isLoggedIn()){
            	//	Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                //	//Intent mainIntent = new Intent(SplashActivity.this, TypeChooserActivity.class);
                //    SplashActivity.this.startActivity(mainIntent);
                //    SplashActivity.this.finish();
            	//} else {
            		Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
            	}
            //}
        }, SPLASH_DISPLAY_LENGHT);
    }    
}
