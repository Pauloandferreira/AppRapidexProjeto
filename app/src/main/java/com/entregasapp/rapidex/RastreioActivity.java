package com.entregasapp.rapidex;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class RastreioActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rastreio);

        drawerLayout = findViewById(R.id.drawer_layout);

    }

    public void ClickMenu (View view){
        openDrawer(drawerLayout);

    }

    private static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);

    }
    public void ClickLogo (View view){
        closeDrawer(drawerLayout);
    }

    private static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){

            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void ClickRastreamento(View view){
        recreate();
    }
    public void ClickHome(View view){ redirectActivity(this,HomeActivity.class); }
    public void ClickServicos(View view){ redirectActivity(this,ServicosActivity.class); }
    public void ClickFazerPedido(View view){ redirectActivity(this,FazerPedidoActivity.class); }
    public void ClickMeusPedidos(View view){ redirectActivity(this,MeusPedidosActivity.class); }
    public void ClickContato(View view){
        redirectActivity(this,ContatoActivity.class);
    }
    public void ClickSobrenos(View view){
        redirectActivity(this,SobreActivity.class);
    }

    public void ClickLogout(View view){
        logout(this);
    }

    private static void logout(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Sair");
        builder.setMessage("Você tem certeza que quer sair ?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                System.exit(0);

            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private static void redirectActivity(Activity activity,Class aClass){
        Intent intent = new Intent(activity,aClass);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}
