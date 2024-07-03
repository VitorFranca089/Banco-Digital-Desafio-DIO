import banco.Banco;
import cliente.Cliente;
import conta.Conta;
import conta.ContaCorrente;
import conta.ContaPoupanca;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final List<Cliente> clientes = Banco.listaClientes();
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        menuPrincipal();
        in.close();
    }

    private static void menuPrincipal(){
        boolean sair = false;
        do {
            System.out.println();
            System.out.println("=== Banco Digital ===");
            System.out.println("1 - Criar conta.");
            System.out.println("2 - Acessar conta.");
            System.out.println("3 - Ver informações do banco.");
            System.out.println("0 - Sair.");
            int opcao = in.nextInt();
            in.nextLine();
            switch(opcao) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    acessarConta();
                    break;
                case 3:
                    menuBanco();
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }while(!sair);
    }

    private static void criarConta(){
        System.out.println();
        System.out.println("=== Criação de Conta Bancária ===");
        System.out.println("Digite seu nome:");
        String nome = in.nextLine();
        if(!nome.isBlank()){
            System.out.println("Digite uma senha para sua conta: ");
            String senha = in.nextLine();
            if(!senha.isBlank()){
                Cliente cliente = new Cliente(nome, senha);
                Conta contaCorrente = new ContaCorrente(cliente);
                Conta contaPoupanca = new ContaPoupanca(cliente);
                cliente.adicionaContaCorrente(contaCorrente);
                cliente.adicionaContaPoupanca(contaPoupanca);
                System.out.println("Sua conta corrente foi criada com sucesso!");
                System.out.println("O seu identificador é: " + cliente.getIdCliente());
                System.out.println();
            }else{
                System.out.println("Senha inválida.");
                System.out.println();
            }
        }else{
            System.out.println("Nome inválido.");
            System.out.println();
        }
    }

    private static void acessarConta(){
        System.out.println();
        System.out.println("=== Acesso de Conta Bancária ===");
        System.out.println("Digite o seu identificador:");
        int idCliente = in.nextInt();
        in.nextLine();
        Optional<Cliente> clienteVerificado = clienteExistente(idCliente);
        if(clienteVerificado.isPresent()){
            Cliente cliente = clienteVerificado.get();
            System.out.println("Digite sua senha:");
            String senha = in.nextLine();
            if(cliente.verificaSenha(senha)){
                menuCliente(cliente);
            }else{
                System.out.println("Senha incorreta.");
            }
        }
    }

    private static Optional<Cliente> clienteExistente(int id){
        return clientes.stream().filter(p -> p.getIdCliente() == id).findFirst();
    }

    private static void menuCliente(Cliente cliente){
        boolean sair = false;
        do {
            System.out.println();
            System.out.println("=== Menu Cliente " + cliente.getNome() + " ===");
            System.out.println("1 - Conta Corrente.");
            System.out.println("2 - Conta Poupança.");
            System.out.println("0 - Sair.");
            int opcao = in.nextInt();
            in.nextLine();
            switch (opcao){
                case 1:
                    menuCorrente(cliente);
                    break;
                case 2:
                    menuPoupanca(cliente);
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }while (!sair);
    }

    private static void menuCorrente(Cliente cliente){
        boolean sair = false;
        Conta contaCorrente = cliente.contaCliente(1);
        do {
            System.out.println();
            System.out.println("=== Conta Corrente ===");
            System.out.println("1 - Saque.");
            System.out.println("2 - Depósito.");
            System.out.println("3 - Transferência bancária.");
            System.out.println("4 - Transferir para conta poupança.");
            System.out.println("5 - Extrato da conta.");
            System.out.println("0 - Sair.");
            double valor = 0;
            int opcao = in.nextInt();
            in.nextLine();
            switch (opcao){
                case 1:
                    System.out.println("Digite o valor do saque:");
                    valor = in.nextDouble();
                    in.nextLine();
                    contaCorrente.sacar(valor);
                    break;
                case 2:
                    System.out.println("Digite o valor do depósito:");
                    valor = in.nextDouble();
                    in.nextLine();
                    contaCorrente.depositar(valor);
                    break;
                case 3:
                    System.out.println("Digite o valor da transferência:");
                    valor = in.nextDouble();
                    in.nextLine();
                    System.out.println("Digite o identificador da conta alvo:");
                    int id = in.nextInt();
                    in.nextLine();
                    Optional<Cliente> clienteAlvo = clienteExistente(id);
                    if(clienteAlvo.isPresent()){
                        Conta contaAlvo = clienteAlvo.get().contaCliente(1);
                        contaCorrente.transferir(valor, contaAlvo);
                    }
                    break;
                case 4:
                    System.out.println("Digite o valor da transferência:");
                    valor = in.nextDouble();
                    in.nextLine();
                    contaCorrente.transferir(valor, cliente.contaCliente(2));
                    break;
                case 5:
                    contaCorrente.imprimirExtrato();
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }while(!sair);
    }

    private static void menuPoupanca(Cliente cliente){
        boolean sair = false;
        Conta contaPoupanca = cliente.contaCliente(2);
        do {
            System.out.println();
            System.out.println("=== Conta Corrente ===");
            System.out.println("1 - Depósito.");
            System.out.println("2 - Transferir para conta corrente.");
            System.out.println("3 - Extrato da conta.");
            System.out.println("0 - Sair.");
            double valor = 0;
            int opcao = in.nextInt();
            in.nextLine();
            switch (opcao){
                case 1:
                    System.out.println("Digite o valor do depósito:");
                    valor = in.nextDouble();
                    in.nextLine();
                    contaPoupanca.depositar(valor);
                    break;
                case 2:
                    System.out.println("Digite o valor da transferência:");
                    valor = in.nextDouble();
                    in.nextLine();
                    contaPoupanca.transferir(valor, cliente.contaCliente(1));
                    break;
                case 3:
                    contaPoupanca.imprimirExtrato();
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }while(!sair);
    }

    private static void menuBanco(){
        boolean sair = false;
        System.out.println();
        System.out.println("Digite a senha do administrador:");
        String senha = in.nextLine();
        if(Banco.verificaSenha(senha)) {
            do {
                System.out.println();
                System.out.println("=== Menu Bancário " + Banco.getNome() + "===");
                System.out.println("1 - Informações gerais.");
                System.out.println("2 - Lista de clientes.");
                System.out.println("3 - Lista de contas.");
                System.out.println("0 - Sair.");
                int opcao = in.nextInt();
                in.nextLine();
                switch (opcao) {
                    case 1:
                        Banco.informacoesGerais();
                        break;
                    case 2:
                        if(!Banco.listaClientes().isEmpty()){
                            Banco.listaClientes().stream().forEach(System.out::println);
                        }
                        break;
                    case 3:
                        if(!Banco.listaContas().isEmpty()){
                            Banco.listaContas().stream().forEach(System.out::println);
                        }
                        break;
                    case 0:
                        sair = true;
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            } while (!sair);
        } else {
            System.out.println("Senha incorreta.");
        }
    }

}