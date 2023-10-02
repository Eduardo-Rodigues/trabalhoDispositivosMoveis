package com.example.lojadepartamentos;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class LancamentoPedidoActivity extends AppCompatActivity {
    private Spinner clienteSpinner;
    private Spinner itemSpinner;
    private EditText quantidadeEditText;
    private EditText valorUnitarioEditText;
    private Button adicionarItemButton;
    private ListView listaItensListView;
    private TextView valorTotalTextView;
    private RadioGroup formaPagamentoRadioGroup;
    private RadioButton aVistaRadioButton;
    private RadioButton aPrazoRadioButton;
    private EditText quantidadeParcelasEditText;
    private ListView listaParcelasListView;
    private TextView valorTotalPedidoTextView;
    private Button concluirPedidoButton;

    private Cliente clienteSelecionado;
    private ItemVenda itemSelecionado;
    private ArrayList<ItemPedido> itensPedido;
    private double valorTotalPedido;
    private boolean pagamentoAVista;
    private int quantidadeParcelas;
    private ArrayAdapter<ItemPedido> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento_pedido);

        // Inicialize todas as Views e configure os listeners e adaptadores necessários.

        clienteSpinner = findViewById(R.id.clienteSpinner);
        itemSpinner = findViewById(R.id.itemSpinner);
        quantidadeEditText = findViewById(R.id.quantidadeEditText);
        valorUnitarioEditText = findViewById(R.id.valorUnitarioEditText);
        adicionarItemButton = findViewById(R.id.adicionarItemButton);
        listaItensListView = findViewById(R.id.listaItensListView);
        valorTotalTextView = findViewById(R.id.valorTotalTextView);
        formaPagamentoRadioGroup = findViewById(R.id.formaPagamentoRadioGroup);
        aVistaRadioButton = findViewById(R.id.aVistaRadioButton);
        aPrazoRadioButton = findViewById(R.id.aPrazoRadioButton);
        quantidadeParcelasEditText = findViewById(R.id.quantidadeParcelasEditText);
        listaParcelasListView = findViewById(R.id.listaParcelasListView);
        valorTotalPedidoTextView = findViewById(R.id.valorTotalPedidoTextView);
        concluirPedidoButton = findViewById(R.id.concluirPedidoButton);

        // Inicialize a lista de itens do pedido
        itensPedido = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itensPedido);
        listaItensListView.setAdapter(adapter);

        // Configurar o botão "Adicionar Item" para adicionar itens ao pedido
        adicionarItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtenha os valores dos campos (cliente, item, quantidade, valor unitário)
                // Valide os campos e adicione o item ao pedido usando o método adicionarItem do Pedido

                // Atualize a lista de itens e o valor total
                adapter.notifyDataSetChanged();
                calcularValorTotal();
            }
        });

        // Configurar o RadioGroup para escolher a forma de pagamento
        formaPagamentoRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.aVistaRadioButton) {
                    // Pagamento à vista selecionado
                    pagamentoAVista = true;
                } else if (checkedId == R.id.aPrazoRadioButton) {
                    // Pagamento a prazo selecionado
                    pagamentoAVista = false;
                }

                // Atualize o valor total com base na forma de pagamento
                calcularValorTotal();
            }
        });

        // Configurar o botão "Concluir Pedido" para finalizar o pedido
        concluirPedidoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Verifique se os campos necessários estão preenchidos (cliente, itens, forma de pagamento)

                // Crie um novo Pedido com as informações coletadas
                Pedido novoPedido = criarPedido();

                // Armazene o pedido em uma lista, banco de dados ou faça o que for necessário

                // Exiba uma mensagem de sucesso
                Toast.makeText(getApplicationContext(), "Pedido cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

                // Limpe os campos e prepare para um novo pedido
                limparCampos();
            }
        });
    }

    private void adicionarItem() {
        // Passo 1: Obter o cliente selecionado no Spinner de clientes
        Cliente clienteSelecionado = (Cliente) clienteSpinner.getSelectedItem();

        // Passo 2: Obter o item selecionado no Spinner de itens de venda
        ItemVenda itemSelecionado = (ItemVenda) itemSpinner.getSelectedItem();

        // Passo 3: Obter a quantidade do EditText de quantidade
        String quantidadeStr = quantidadeEditText.getText().toString();
        if (quantidadeStr.isEmpty()) {
            Toast.makeText(this, "Campo de quantidade vazio.", Toast.LENGTH_SHORT).show();
            return;
        }
        int quantidade = Integer.parseInt(quantidadeStr);

        // Passo 4: Obter o valor unitário do EditText de valor unitário
        String valorUnitarioStr = valorUnitarioEditText.getText().toString();
        if (valorUnitarioStr.isEmpty()) {
            Toast.makeText(this, "Campo de valor unitário vazio.", Toast.LENGTH_SHORT).show();
            return;
        }
        double valorUnitario = Double.parseDouble(valorUnitarioStr);

        // Passo 5: Crie um novo item do pedido com os valores obtidos
        ItemPedido itemPedido = new ItemPedido(itemSelecionado, quantidade, valorUnitario);

        // Passo 6: Adicione o item ao pedido usando o método adicionarItem do Pedido
        Pedido.adicionarItem(itemPedido); // Substitua 'seuPedido' pelo nome da instância do seu objeto Pedido

        // Passo 7: Atualize a lista de itens do pedido e o valor total
        itensPedido.add(itemPedido);
        adapter.notifyDataSetChanged();
        calcularValorTotal();
    }


    // Método para calcular o valor total do pedido
    private void calcularValorTotal() {
        // Implemente a lógica para calcular o valor total com base nos itens, quantidade, valor unitário e forma de pagamento
        // Atualize a exibição do valor total
        valorTotalTextView.setText("Valor Total: R$ " + String.format("%.2f", valorTotalPedido));
    }

    // Método para criar um novo Pedido com as informações coletadas
    private Pedido criarPedido() {
        // Passo 1: Obter o cliente selecionado
        Cliente clienteSelecionado = (Cliente) clienteSpinner.getSelectedItem();

        // Passo 2: Obter os itens do pedido a partir da lista de itens adicionados
        ArrayList<ItemPedido> itensPedido = new ArrayList<>();
        for (ItemPedido itemPedido : this.itensPedido) {
            itensPedido.add(itemPedido);
        }

        // Passo 3: Determinar a forma de pagamento com base na seleção do RadioGroup
        boolean pagamentoAVista = false; // Assume pagamento a prazo por padrão
        int selectedRadioButtonId = formaPagamentoRadioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId == R.id.aVistaRadioButton) {
            pagamentoAVista = true; // Pagamento à vista
        }

        // Passo 4: Calcular o valor total do pedido com base na forma de pagamento
        double valorTotalPedido = calcularValorTotalPedido(pagamentoAVista);

        // Agora, você pode criar um novo objeto Pedido com as informações coletadas
        // Certifique-se de ter uma classe Pedido construtor apropriado
        Pedido novoPedido = new Pedido(clienteSelecionado, itensPedido, pagamentoAVista, valorTotalPedido);

        return novoPedido;
    }

    // Método para calcular o valor total do pedido com base na forma de pagamento
    private double calcularValorTotalPedido(boolean pagamentoAVista) {
        double valorTotal = 0;

        // Calcule o valor total do pedido com base nos itens, quantidade, valor unitário, etc.

        // Se o pagamento for à vista, aplique um desconto de 5%
        if (pagamentoAVista) {
            valorTotal *= 0.95;
        }

        return valorTotal;
    }

    private void limparCampos() {
        // Limpar campos de seleção de cliente e item
        clienteSpinner.setSelection(0); // Defina a seleção de cliente de volta para o primeiro item (ou como preferir)
        itemSpinner.setSelection(0); // Defina a seleção de item de volta para o primeiro item (ou como preferir)

        // Limpar campos de quantidade e valor unitário
        quantidadeEditText.setText("");
        valorUnitarioEditText.setText("");

        // Limpar a lista de itens do pedido
        itensPedido.clear();
        adapter.notifyDataSetChanged();

        // Limpar campos de forma de pagamento
        formaPagamentoRadioGroup.clearCheck(); // Desmarcar todas as opções de forma de pagamento
        quantidadeParcelasEditText.setText("");

        // Limpar campo de valor total
        valorTotalTextView.setText("Valor Total: R$ 0.00");

        // Limpar campo de quantidade de parcelas
        quantidadeParcelasEditText.setText("");

        // Limpar lista de valores de parcelas (se houver)
        // listaParcelasListView.setAdapter(null); // Descomente esta linha se você tiver uma lista de parcelas para limpar

        // Limpar campo de valor total do pedido
        valorTotalPedidoTextView.setText("Valor Total do Pedido: R$ 0.00");
    }

}