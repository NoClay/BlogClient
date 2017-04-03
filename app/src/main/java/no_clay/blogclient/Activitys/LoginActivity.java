package no_clay.blogclient.Activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import no_clay.blogclient.R;
import no_clay.blogclient.Utils.CsdnBloger;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity
    implements TextView.OnEditorActionListener{
    @BindView(R.id.email)
    AutoCompleteTextView mUserName;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.login)
    Button mLogin;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPassword.setOnEditorActionListener(this);
    }

    @OnClick(R.id.login)
    public void onClick() {
        attemptLogin();
    }

    public void attemptLogin(){
        Toast.makeText(this, "尝试登陆", Toast.LENGTH_SHORT).show();
        final CsdnBloger csdnBloger = new CsdnBloger(this);
//        try {
//            csdnBloger.login("15667027286", "glh19950602.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    csdnBloger.login("15667027286", "glh19950602.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        Log.d(TAG, "onEditorAction: actionId = " + actionId);
        Log.d(TAG, "onEditorAction: id = " + EditorInfo.IME_ACTION_GO);
        if (actionId== EditorInfo.IME_ACTION_GO
                || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
            attemptLogin();
            return true;
        }
        return false;
    }
}

