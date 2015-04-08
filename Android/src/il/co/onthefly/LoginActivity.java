package il.co.onthefly;

import il.co.onthefly.db.User;

import java.util.Arrays;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.LoginButton.UserInfoChangedCallback;

public class LoginActivity extends FragmentActivity {

	private LoginButton loginBtn;
	private RelativeLayout loginButtonGooglePlus;
	private RelativeLayout loginButtonTwitter;
	private RelativeLayout loginButtonLinkedIn;
	public static User currentUser;

	private UiLifecycleHelper uiHelper;

	private static final List<String> PERMISSIONS = Arrays.asList("publish_actions");

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		uiHelper = new UiLifecycleHelper(this, statusCallback);
		uiHelper.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

		loginBtn = (LoginButton) findViewById(R.id.button_login_facebook);
		loginBtn.setUserInfoChangedCallback(new UserInfoChangedCallback() {
			@Override
			public void onUserInfoFetched(GraphUser user) {
				if (user != null) {
					// Logged In
					// Store User
					currentUser = new User(user.getId(), user.getLink(), user.getFirstName(), user.getLastName(), user.getBirthday(), "Israel", user.getUsername() + "@gmail.com", "Tel Aviv University", "MSc Computer Science", "Amadues", "AZ815", "TLV", "JFK");
					// Show toast
                	Toast.makeText(getApplicationContext(), "Welcome " + user.getFirstName() +"!",
              			   Toast.LENGTH_SHORT).show();
                	// Continue to next screen
                    continuteToNextScreen(2000);
				} else {
					// Unable to connect
				}
			}
		});
		loginButtonGooglePlus = (RelativeLayout) findViewById(R.id.layout_login_google_plus);
		loginButtonGooglePlus.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// // Show toast
            	Toast.makeText(getApplicationContext(), "Coming soon...",
           			   Toast.LENGTH_SHORT).show();
				
			}
		});
		loginButtonTwitter = (RelativeLayout) findViewById(R.id.layout_login_twitter);
		loginButtonTwitter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// // Show toast
            	Toast.makeText(getApplicationContext(), "Coming soon...",
           			   Toast.LENGTH_SHORT).show();
				
			}
		});
		loginButtonLinkedIn = (RelativeLayout) findViewById(R.id.layout_login_linkedin);
		loginButtonLinkedIn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// // Show toast
            	Toast.makeText(getApplicationContext(), "Coming soon...",
           			   Toast.LENGTH_SHORT).show();
				
			}
		});
	}
	
	private void continuteToNextScreen(long delay) {
    	new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Main Activity. */
                Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(mainIntent);
                LoginActivity.this.finish();
            }
        }, delay);
	}


	private Session.StatusCallback statusCallback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			if (state.isOpened()) {
				// TODO Logged In
				Log.d("FacebookSampleActivity", "Facebook session opened");
			} else if (state.isClosed()) {
				// TODO Logged Out				
				Log.d("FacebookSampleActivity", "Facebook session closed");
			}
		}
	};

	public boolean checkPermissions() {
		Session s = Session.getActiveSession();
		if (s != null) {
			return s.getPermissions().contains("publish_actions");
		} else
			return false;
	}

	public void requestPermissions() {
		Session s = Session.getActiveSession();
		if (s != null)
			s.requestNewPublishPermissions(new Session.NewPermissionsRequest(
					this, PERMISSIONS));
	}

	@Override
	public void onResume() {
		super.onResume();
		uiHelper.onResume();
		// TODO check if session is open 
		//if (Session.getActiveSession().isOpened()){}
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onSaveInstanceState(Bundle savedState) {
		super.onSaveInstanceState(savedState);
		uiHelper.onSaveInstanceState(savedState);
	}

}