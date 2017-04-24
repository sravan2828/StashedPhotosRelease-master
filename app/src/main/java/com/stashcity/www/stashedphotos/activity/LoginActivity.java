package com.stashcity.www.stashedphotos.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;
import com.stashcity.www.stashedphotos.ApplicationController;
import com.stashcity.www.stashedphotos.R;
import com.stashcity.www.stashedphotos.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;


public class LoginActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Button LoginButton;
    String URL;
    EditText Email;
    EditText Password;
    User userobj;
    TextView sigunup;

    Gson gson;

    CallbackManager callbackManager;
    LoginButton fbLoginButton;
    static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();

        user = new User();
        //get the login url from string constants;
        URL= getResources().getString(R.string.loginURL);
        gson = new Gson();
        final View view =findViewById(android.R.id.content);
        /*LoginButton= (Button)findViewById(R.id.btn_login);
        Email =(EditText)findViewById(R.id.input_email);
        Password = (EditText)findViewById(R.id.input_password);
        sigunup=(TextView)findViewById(R.id.link_signup);
        userobj=new User();*/
        Profile profile = Profile.getCurrentProfile();
        if(null != profile && null!=profile.getId())
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        /*LoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                userobj.setEmail(Email.getText().toString().trim());
                userobj.setPassword(Password.getText().toString().trim());
                showProgressDialog();
                login(userobj);


            }
        });
        sigunup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });*/
        fbLoginButton = (LoginButton) findViewById(R.id.login_button);
        fbLoginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));
        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Snackbar.make(view,"Login Success", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }

            @Override
            public void onCancel() {
                Snackbar.make(view, "Login Cancelled", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Snackbar.make(view, "Login Error", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    /*private void login(User user) {


        HashMap<String, String> params = new HashMap<>();
        params.put("Email", user.getEmail());
        params.put("Password", user.getPassword());


        JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            Log.e("CALL",response.toString());
                            progressDialog.dismiss();
                            userobj=gson.fromJson(response.toString(),User.class);
                            if (userobj.getResponse().getStatus().equals("000"))
                            {
                                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                            else
                            {

                                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();

                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        // add the request object to the queue to be executed
        ApplicationController.getInstance().addToRequestQueue(req);



    }*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void showProgressDialog(){
        progressDialog=new ProgressDialog(LoginActivity.this,
                R.style.MyMaterialTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        Window window = progressDialog.getWindow();
        window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        progressDialog.show();


    }
}
