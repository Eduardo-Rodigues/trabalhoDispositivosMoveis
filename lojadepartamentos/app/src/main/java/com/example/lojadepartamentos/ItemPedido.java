package com.example.lojadepartamentos;

public class ItemPedido {
    private ItemVenda itemVenda;
    private int quantidade;
    private double valorUnitario;

    public ItemPedido(ItemVenda itemVenda, int quantidade, double valorUnitario) {
        this.itemVenda = itemVenda;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public ItemVenda getItemVenda() {
        return itemVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public double calcularSubtotal() {
        return quantidade * valorUnitario;
    }

    @Override
    public String toString() {
        return "Item: " + itemVenda.getDescricao() +
                ", Quantidade: " + quantidade +
                ", Subtotal: R$ " + String.format("%.2f", calcularSubtotal());
    }
}

