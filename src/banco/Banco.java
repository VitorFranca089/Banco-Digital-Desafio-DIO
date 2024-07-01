package banco;

import cliente.Cliente;
import conta.Conta;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    private static final String ADMIN = "1111";
    private static final String nome = "Banco VI";
    private static final List<Conta> contas = new ArrayList<>();
    private static final List<Cliente> clientes = new ArrayList<>();

    public static String getNome() {
        return nome;
    }

    public static void adicionaConta(Conta conta){
        contas.add(conta);
    }

    public static void adicionaCliente(Cliente cliente){
        clientes.add(cliente);
    }

    public static void informacoesGerais() {
        System.out.println("=== Informações gerais ===");
        System.out.println("Clientes: " + clientes.size());
        System.out.println("Contas: " + contas.size());
    }

    public static List<Conta> listaContas(){
        return contas;
    }

    public static List<Cliente> listaClientes() {
        return clientes;
    }

    public static boolean verificaSenha(String senhaAdmin){
        return senhaAdmin.equals(ADMIN);
    }

}
