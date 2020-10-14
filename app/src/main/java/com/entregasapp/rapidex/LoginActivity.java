package com.entregasapp.rapidex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText mTextUsuario;
    EditText mTextSenha;
    Button mButtonLogin;
    TextView mTextViewCadastrase;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        mTextUsuario = (EditText) findViewById(R.id.edittext_usuario);
        mTextSenha = (EditText) findViewById(R.id.edittext_senha);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mTextViewCadastrase = (TextView) findViewById(R.id.textview_cadastrase);
        mTextViewCadastrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cadastraseIntent = new Intent(LoginActivity.this,CadastraseActivity.class);
                startActivity(cadastraseIntent);
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsuario.getText().toString().trim();
                String pwd = mTextSenha.getText().toString().trim();
                Boolean res = db.checkUser(user, pwd);
                if (res == true)
                {
                    Intent HomePage = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(HomePage);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Senha Errada", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}