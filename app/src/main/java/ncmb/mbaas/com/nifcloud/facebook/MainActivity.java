package ncmb.mbaas.com.nifcloud.facebook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.nifcloud.mbaas.core.NCMB;
import com.nifcloud.mbaas.core.NCMBException;
import com.nifcloud.mbaas.core.NCMBFacebookParameters;
import com.nifcloud.mbaas.core.NCMBUser;

public class MainActivity extends AppCompatActivity {

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //**************** APIキーの設定とSDKの初期化 **********************
        NCMB.initialize(this.getApplicationContext(), "YOUR_APPLICATION_KEY", "YOUR_APPLICATION_KEY");

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppEventsLogger.activateApp(this.getApplication());

        callbackManager = CallbackManager.Factory.create();
        LoginButton mLoginButton = findViewById(R.id.login_button);
        mLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Login to NIFCLOUD mobile backend
                NCMBFacebookParameters parameters = new NCMBFacebookParameters(
                        loginResult.getAccessToken().getUserId(),
                        loginResult.getAccessToken().getToken(),
                        loginResult.getAccessToken().getExpires()
                );
                try {
                    NCMBUser.loginWith(parameters);
                    Toast.makeText(getApplicationContext(), "Login to NIFCLOUD mbaas with Facebook account", Toast.LENGTH_LONG).show();
                } catch (NCMBException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancel() {
                // App code
                Log.d("tag", "onCancel");
            }

            @Override
            public void onError(@NonNull FacebookException e) {
                // App code
                Log.d("tag", "onError:" + e);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
