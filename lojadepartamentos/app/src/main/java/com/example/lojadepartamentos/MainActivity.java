package com.example.lojadepartamentos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirCadastroClienteActivity(View view) {
        Intent intent = new Intent(this, CadastroClienteActivity.class);
        startActivity(intent);
    }

    public void abrirCadastroItemVendaActivity(View view) {
        Intent intent = new Intent(this, CadastroItemVendaActivity.class);
        startActivity(intent);
    }

    public void abrirLancamentoPedidoActivity(View view) {
        Intent intent = new Intent(this, LancamentoPedidoActivity.class);
        startActivity(intent);
    }
}
