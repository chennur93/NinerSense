package meghashyam.ninersense;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GetPass.getPassInterface{

    EditText userBox, passBox;
    Button  login;
    String uname, password;
    public static int Tpower=0;
    public static int Intense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userBox = (EditText) findViewById(R.id.username);
        passBox = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname = userBox.getText().toString();
                password = passBox.getText().toString();
                if(uname.equals("") ||password.equals("")){
                    Toast.makeText(getBaseContext(),"Enter Username and Password!", Toast.LENGTH_SHORT).show();
                }
                else if(isNetworkAvailable()){
                    Log.d("test0","Network available");
                    new GetPass(uname,password,MainActivity.this).execute();
                }
                else{
                    Toast.makeText(getBaseContext(), "No Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    @Override
    public void getResult(String resultpass) {
        Toast.makeText(this,resultpass+"",Toast.LENGTH_SHORT).show();
        Log.d("ResultPass", resultpass);
        String passwd = resultpass;
        if(!((passwd.equals("null\n")) || (passwd.equals("")))){
            if(passwd.trim().equals("true")){
                Toast.makeText(getBaseContext(),"Login Successfull!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this,NavHome.class);
                startActivity(i);
            }
            else
                Toast.makeText(getBaseContext(),"Invalid Uname/Pass!", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getBaseContext(),"Login Failed!", Toast.LENGTH_SHORT).show();
    }


}
