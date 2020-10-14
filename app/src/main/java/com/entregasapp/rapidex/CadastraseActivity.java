package com.entregasapp.rapidex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CadastraseActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText mTextUsuario;
    EditText mTextSenha;
    EditText mTextCnfSenha;
    Button mButtonCadastrase;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrase);

        db = new DatabaseHelper(this);
        mTextUsuario = (EditText) findViewById(R.id.edittext_usuario);
        mTextSenha = (EditText) findViewById(R.id.edittext_senha);
        mTextCnfSenha = (EditText) findViewById(R.id.edittext_cnf_senha);
        mButtonCadastrase = (Button) findViewById(R.id.button_cadastrase);
        mTextViewLogin = (TextView) findViewById(R.id.textview_login);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent (CadastraseActivity.this,LoginActivity.class);
                startActivity(LoginIntent);
            }
        });
        mButtonCadastrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = mTextUsuario.getText().toString().trim();
                String pwd = mTextSenha.getText().toString().trim();
                String cnf_pwd = mTextCnfSenha.getText().toString().trim();

                if(pwd.equals(cnf_pwd)){
                    long val = db.addUser(user,pwd);
                    if(val > 0){
                        Toast.makeText(CadastraseActivity.this, "Você foi cadastrado", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(CadastraseActivity.this, LoginActivity.class);
                        startActivity(moveToLogin);
                    }
                    else{
                        Toast.makeText(CadastraseActivity.this, "Erro de cadastro", Toast.LENGTH_SHORT).show();
                    }


                }
                else {
                    Toast.makeText(CadastraseActivity.this, "Senha não é compatível", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}