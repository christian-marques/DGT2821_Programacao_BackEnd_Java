/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastropoo;

import java.io.IOException;
import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

/**
 *
 * @author chris
 */
public class CadastroPOO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            // Repositório de pessoas físicas
            PessoaFisicaRepo repo1 = new PessoaFisicaRepo();

            repo1.inserir(
                    new PessoaFisica(
                            1,
                            "Ada Marques",
                            "111.111.111-11",
                            25
                    )
            );

            repo1.inserir(
                    new PessoaFisica(
                            2,
                            "Luna Marques",
                            "222.222.222-22",
                            32
                    )
            );

            repo1.persistir("pessoas_fisicas.bin");
            
            System.out.println("Dados de Pessoa Física Armazenados.");

            // Recuperação das pessoas físicas
            PessoaFisicaRepo repo2 = new PessoaFisicaRepo();

            repo2.recuperar("pessoas_fisicas.bin");
            
            System.out.println("Dados de Pessoa Física Recuperados.\n");

            for (PessoaFisica pessoa : repo2.obterTodos()) {
                pessoa.exibir();
                System.out.println();
            }

            // Repositório de pessoas jurídicas
            PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();

            repo3.inserir(
                    new PessoaJuridica(
                            1,
                            "Adidas",
                            "11.111.111/0001-11"
                    )
            );

            repo3.inserir(
                    new PessoaJuridica(
                            2,
                            "Nike",
                            "22.222.222/0001-22"
                    )
            );

            repo3.persistir("pessoas_juridicas.bin");
            
            System.out.println("Dados de Pessoa Jurídica Armazenados.");

            // Recuperação das pessoas jurídicas
            PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();

            repo4.recuperar("pessoas_juridicas.bin");
            
            System.out.println("Dados de Pessoa Jurídica Recuperados.\n");

            for (PessoaJuridica pessoa : repo4.obterTodos()) {
                pessoa.exibir();
                System.out.println();
            }

        } catch (IOException | ClassNotFoundException erro) {
            System.out.println(
                    "Erro ao salvar ou recuperar os dados: "
                    + erro.getMessage()
            );
        }
    } 
}