package cliente;

import banco.Banco;
import conta.Conta;

import java.util.HashMap;
import java.util.Map;

public class Cliente {

    private static int ID_CLIENTE = 1;

    private int idCliente;
    private String nome;
    private String senha;
    private Map<Integer, Conta> contas;


    public Cliente(String nome, String senha){
        this.nome = nome;
        this.senha = senha;
        this.contas = new HashMap<>();
        this.idCliente = ID_CLIENTE++;
        Banco.adicionaCliente(this);
    }

    public String getNome() {
        return nome;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void adicionaContaCorrente(Conta conta) {
        contas.put(1, conta);
    }

    public void adicionaContaPoupanca(Conta conta) {
        contas.put(2, conta);
    }

    public boolean verificaSenha(String senha){
        return this.senha.equals(senha);
    }

    public Conta contaCliente(int tipoConta){
        return contas.get(tipoConta);
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + ". ";
    }

}
