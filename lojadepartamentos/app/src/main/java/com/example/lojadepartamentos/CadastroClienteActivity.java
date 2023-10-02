package com.example.lojadepartamentos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroClienteActivity extends AppCompatActivity {
    private EditText nomeEditText;
    private EditText cpfEditText;
    private Button cadastrarClienteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        nomeEditText = findViewById(R.id.nomeEditText);
        cpfEditText = findViewById(R.id.cpfEditText);
        cadastrarClienteButton = findViewById(R.id.cadastrarClienteButton);

        cadastrarClienteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = nomeEditText.getText().toString();
                String cpf = cpfEditText.getText().toString();

                if (!nome.isEmpty() && !cpf.isEmpty()) {
                    Cliente cliente = new Cliente(nome, cpf);
                    // Faça o cadastro do cliente em algum lugar (por exemplo, em uma lista).
                    Toast.makeText(getApplicationContext(), "Cliente cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    // Limpe os campos após o cadastro.
                    nomeEditText.setText("");
                    cpfEditText.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}