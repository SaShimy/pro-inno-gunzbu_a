package com.etna.gunzbu_a.freshdj;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    JSONArray array = new JSONArray();
    private static final String REGISTER_URL = "http://apifreshdj.cloudapp.net/signup/";

    public static final String KEY_USERNAME = "login";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "mail";

    EditText edUsername;
    EditText edPassword;
    EditText edEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button signUp = (Button) findViewById(R.id.signUp);
        edUsername = (EditText) findViewById(R.id.username);
        edPassword = (EditText) findViewById(R.id.password);
        edEmail = (EditText) findViewById(R.id.email);

        assert signUp != null;
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = edUsername.getText().toString().trim();
                final String password = edPassword.getText().toString().trim();
                final String email = edEmail.getText().toString().trim();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(SignUpActivity.this,response,Toast.LENGTH_LONG).show();
                                Log.v("RES", response.toString());
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(SignUpActivity.this,error.toString(), Toast.LENGTH_LONG).show();
                                Log.v("ERR", error.toString());
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put(KEY_USERNAME,username);
                        params.put(KEY_PASSWORD,password);
                        params.put(KEY_EMAIL, email);
                        Log.v("PAR", params.toString());
                        return params;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(SignUpActivity.this);
                requestQueue.add(stringRequest);
            }
        });

    }
}