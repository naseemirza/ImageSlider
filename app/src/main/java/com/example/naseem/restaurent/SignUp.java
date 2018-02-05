package com.example.naseem.restaurent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    TextView textView_sin;
    EditText Ename,Eemail,Emob,Epass;
    Button buttonsup;

    RequestQueue requestQueue;
    String NameHolder, EmailHolder,MobileHolder, PasswordHolder ;
    ProgressDialog progressDialog;
    String HttpUrl = "http://abrdemo.com/PriceOmaniaApp/register.php";

    Boolean CheckEditText ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Ename=(EditText)findViewById(R.id.editTextnm);
        Eemail=(EditText)findViewById(R.id.editTextemail);
        Emob=(EditText)findViewById(R.id.editTextmobile);
        Epass=(EditText)findViewById(R.id.editTextpass);

        buttonsup=(Button) findViewById(R.id.button_signup);
        requestQueue = Volley.newRequestQueue(SignUp.this);
        progressDialog = new ProgressDialog(SignUp.this);

        buttonsup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    UserRegistration();

                }
                else {

                    Toast.makeText(SignUp.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });


        textView_sin=(TextView)findViewById(R.id.text_sin);

        textView_sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,SignIn.class));
            }
        });
    }

    public void UserRegistration(){

        // Showing progress dialog at user registration time.
        progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
        progressDialog.show();

        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();

                        // Showing Echo Response Message Coming From Server.
                        Toast.makeText(SignUp.this, ServerResponse, Toast.LENGTH_LONG).show();

                        Ename.setText("");
                        Eemail.setText("");
                        Emob.setText("");
                        Epass.setText("");

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();

                        // Showing error message if something goes wrong.
                        Toast.makeText(SignUp.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
                params.put("name", NameHolder);
                params.put("email", EmailHolder);
                params.put("mobile", MobileHolder);
                params.put("password", PasswordHolder);

                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(SignUp.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

    }


    public void CheckEditTextIsEmptyOrNot(){

        // Getting values from EditText.
        NameHolder = Ename.getText().toString().trim();
        EmailHolder = Eemail.getText().toString().trim();
        MobileHolder = Emob.getText().toString().trim();
        PasswordHolder = Epass.getText().toString().trim();

        // Checking whether EditText value is empty or not.
        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {

            // If any of EditText is empty then set variable value as False.
            CheckEditText = false;

        }
        else {

            // If any of EditText is filled then set variable value as True.
            CheckEditText = true ;
        }
    }
}
