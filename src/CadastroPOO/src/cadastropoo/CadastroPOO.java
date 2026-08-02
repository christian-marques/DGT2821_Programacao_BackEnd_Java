/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastropoo;

import java.io.IOException;
import java.util.Scanner;
import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

/**
 *
 * @author chris
 */
public class CadastroPOO {

    private static final Scanner entrada = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Opção: ");

            switch (opcao) {
                case 1:
                    incluirPessoa(repoFisica, repoJuridica);
                    break;

                case 2:
                    alterarPessoa(repoFisica, repoJuridica);
                    break;

                case 3:
                    excluirPessoa(repoFisica, repoJuridica);
                    break;

                case 4:
                    exibirPorId(repoFisica, repoJuridica);
                    break;

                case 5:
                    exibirTodos(repoFisica, repoJuridica);
                    break;

                case 6:
                    salvarDados(repoFisica, repoJuridica);
                    break;

                case 7:
                    recuperarDados(repoFisica, repoJuridica);
                    break;

                case 0:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (opcao != 0);

        entrada.close();
    }

    private static void exibirMenu() {

        System.out.println();
        System.out.println("1-Incluir Pessoa");
        System.out.println("2-Alterar Pessoa");
        System.out.println("3-Excluir Pessoa");
        System.out.println("4-Exibir pelo Id");
        System.out.println("5-Exibir Todos");
        System.out.println("6-Salvar Dados");
        System.out.println("7-Recuperar Dados");
        System.out.println("0-Finalizar Programa");
    }

    private static void incluirPessoa(
            PessoaFisicaRepo repoFisica,
            PessoaJuridicaRepo repoJuridica) {

        char tipo = lerTipo();
        int id = lerInteiro("Id: ");
        String nome = lerTexto("Nome: ");

        if (tipo == 'F') {
            String cpf = lerTexto("CPF: ");
            int idade = lerInteiro("Idade: ");

            PessoaFisica pessoaFisica
                    = new PessoaFisica(id, nome, cpf, idade);

            repoFisica.inserir(pessoaFisica);

        } else {
            String cnpj = lerTexto("CNPJ: ");

            PessoaJuridica pessoaJuridica
                    = new PessoaJuridica(id, nome, cnpj);

            repoJuridica.inserir(pessoaJuridica);
        }

        System.out.println("Pessoa incluída com sucesso.");
    }

    private static void alterarPessoa(
            PessoaFisicaRepo repoFisica,
            PessoaJuridicaRepo repoJuridica) {

        char tipo = lerTipo();
        int id = lerInteiro("Informe o Id: ");

        if (tipo == 'F') {
            PessoaFisica pessoaAtual = repoFisica.obter(id);

            if (pessoaAtual == null) {
                System.out.println("Pessoa física não encontrada.");
                return;
            }

            System.out.println("\nDados atuais:");
            pessoaAtual.exibir();

            System.out.println("\nInforme os novos dados:");
            String nome = lerTexto("Nome: ");
            String cpf = lerTexto("CPF: ");
            int idade = lerInteiro("Idade: ");

            PessoaFisica pessoaNova
                    = new PessoaFisica(id, nome, cpf, idade);

            repoFisica.alterar(pessoaNova);

        } else {
            PessoaJuridica pessoaAtual = repoJuridica.obter(id);

            if (pessoaAtual == null) {
                System.out.println("Pessoa jurídica não encontrada.");
                return;
            }

            System.out.println("\nDados atuais:");
            pessoaAtual.exibir();

            System.out.println("\nInforme os novos dados:");
            String nome = lerTexto("Nome: ");
            String cnpj = lerTexto("CNPJ: ");

            PessoaJuridica pessoaNova
                    = new PessoaJuridica(id, nome, cnpj);

            repoJuridica.alterar(pessoaNova);
        }

        System.out.println("Pessoa alterada com sucesso.");
    }

    private static void excluirPessoa(
            PessoaFisicaRepo repoFisica,
            PessoaJuridicaRepo repoJuridica) {

        char tipo = lerTipo();
        int id = lerInteiro("Informe o Id: ");

        if (tipo == 'F') {
            PessoaFisica pessoaFisica = repoFisica.obter(id);

            if (pessoaFisica == null) {
                System.out.println("Pessoa física não encontrada.");
                return;
            }

            repoFisica.excluir(id);

        } else {
            PessoaJuridica pessoaJuridica = repoJuridica.obter(id);

            if (pessoaJuridica == null) {
                System.out.println("Pessoa jurídica não encontrada.");
                return;
            }

            repoJuridica.excluir(id);
        }

        System.out.println("Pessoa excluída com sucesso.");
    }

    private static void exibirPorId(
            PessoaFisicaRepo repoFisica,
            PessoaJuridicaRepo repoJuridica) {

        char tipo = lerTipo();
        int id = lerInteiro("Informe o Id: ");

        if (tipo == 'F') {
            PessoaFisica pessoaFisica = repoFisica.obter(id);

            if (pessoaFisica != null) {
                pessoaFisica.exibir();
            } else {
                System.out.println("Pessoa física não encontrada.");
            }

        } else {
            PessoaJuridica pessoaJuridica = repoJuridica.obter(id);

            if (pessoaJuridica != null) {
                pessoaJuridica.exibir();
            } else {
                System.out.println("Pessoa jurídica não encontrada.");
            }
        }
    }

    private static void exibirTodos(
            PessoaFisicaRepo repoFisica,
            PessoaJuridicaRepo repoJuridica) {

        char tipo = lerTipo();

        if (tipo == 'F') {

            if (repoFisica.obterTodos().isEmpty()) {
                System.out.println(
                        "Não existem pessoas físicas cadastradas."
                );
                return;
            }

            for (PessoaFisica pessoaFisica
                    : repoFisica.obterTodos()) {

                pessoaFisica.exibir();
                System.out.println();
            }

        } else {

            if (repoJuridica.obterTodos().isEmpty()) {
                System.out.println(
                        "Não existem pessoas jurídicas cadastradas."
                );
                return;
            }

            for (PessoaJuridica pessoaJuridica
                    : repoJuridica.obterTodos()) {

                pessoaJuridica.exibir();
                System.out.println();
            }
        }
    }

    private static void salvarDados(
            PessoaFisicaRepo repoFisica,
            PessoaJuridicaRepo repoJuridica) {

        String prefixo = lerTexto("Prefixo dos arquivos: ");

        try {
            repoFisica.persistir(prefixo + ".fisica.bin");
            repoJuridica.persistir(prefixo + ".juridica.bin");

            System.out.println("Dados salvos com sucesso.");

        } catch (IOException erro) {
            System.out.println(
                    "Erro ao salvar os dados: " + erro.getMessage()
            );
        }
    }

    private static void recuperarDados(
            PessoaFisicaRepo repoFisica,
            PessoaJuridicaRepo repoJuridica) {

        String prefixo = lerTexto("Prefixo dos arquivos: ");

        try {
            repoFisica.recuperar(prefixo + ".fisica.bin");
            repoJuridica.recuperar(prefixo + ".juridica.bin");

            System.out.println("Dados recuperados com sucesso.");

        } catch (IOException | ClassNotFoundException erro) {
            System.out.println(
                    "Erro ao recuperar os dados: " + erro.getMessage()
            );
        }
    }

    private static char lerTipo() {

        while (true) {
            String tipo = lerTexto(
                    "F-Pessoa Física | J-Pessoa Jurídica: "
            ).toUpperCase();

            if (tipo.equals("F") || tipo.equals("J")) {
                return tipo.charAt(0);
            }

            System.out.println(
                    "Tipo inválido. Digite somente F ou J."
            );
        }
    }

    private static int lerInteiro(String mensagem) {

        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(entrada.nextLine());

            } catch (NumberFormatException erro) {
                System.out.println(
                        "Digite um número inteiro válido."
                );
            }
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return entrada.nextLine();
    }
}