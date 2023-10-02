package com.example.lojadepartamentos;

import java.util.ArrayList;

public class Pedido {
    private int codigoPedido;
    private Cliente cliente;
    private ArrayList<ItemVenda> itens;
    private double valorTotal;
    private boolean aVista;
    private int quantidadeParcelas;

    public Pedido(int codigoPedido, Cliente cliente) {
        this.codigoPedido = codigoPedido;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    public Pedido(Cliente clienteSelecionado, ArrayList<ItemPedido> itensPedido, boolean pagamentoAVista, double valorTotalPedido) {
    }

    public static void adicionarItem(ItemPedido itemPedido) {
    }
}
