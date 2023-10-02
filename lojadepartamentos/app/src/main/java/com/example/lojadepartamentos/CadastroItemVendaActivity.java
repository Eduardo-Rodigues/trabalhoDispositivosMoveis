package com.example.lojadepartamentos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroItemVendaActivity extends AppCompatActivity {
    private EditText codigoEditText;
    private EditText descricaoEditText;
    private EditText valorUnitarioEditText;
    private Button cadastrarItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_item_venda);

        codigoEditText = findViewById(R.id.codigoEditText);
        descricaoEditText = findViewById(R.id.descricaoEditText);
        valorUnitarioEditText = findViewById(R.id.valorUnitarioEditText);
        cadastrarItemButton = findViewById(R.id.cadastrarItemButton);

        cadastrarItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int codigo = Integer.parseInt(codigoEditText.getText().toString());
                String descricao = descricaoEditText.getText().toString();
                double valorUnitario = Double.parseDouble(valorUnitarioEditText.getText().toString());

                if (codigo > 0 && !descricao.isEmpty() && valorUnitario > 0) {
                    ItemVenda item = new ItemVenda(codigo, descricao, valorUnitario);
                    // Faça o cadastro do item em algum lugar (por exemplo, em uma lista).
                    Toast.makeText(getApplicationContext(), "Item de venda cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    // Limpe os campos após o cadastro.
                    codigoEditText.setText("");
                    descricaoEditText.setText("");
                    valorUnitarioEditText.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos corretamente.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}