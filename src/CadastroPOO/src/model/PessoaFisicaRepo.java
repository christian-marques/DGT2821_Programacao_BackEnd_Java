/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author chris
 */
public class PessoaFisicaRepo {

    private ArrayList<PessoaFisica> pessoasFisicas;

    public PessoaFisicaRepo() {
        pessoasFisicas = new ArrayList<>();
    }

    public void inserir(PessoaFisica pessoaFisica) {
        pessoasFisicas.add(pessoaFisica);
    }

    public void alterar(PessoaFisica pessoaFisica) {
        PessoaFisica pessoaEncontrada = obter(pessoaFisica.getId());

        if (pessoaEncontrada != null) {
            pessoaEncontrada.setNome(pessoaFisica.getNome());
            pessoaEncontrada.setCpf(pessoaFisica.getCpf());
            pessoaEncontrada.setIdade(pessoaFisica.getIdade());
        }
    }

    public void excluir(int id) {
        PessoaFisica pessoaEncontrada = obter(id);

        if (pessoaEncontrada != null) {
            pessoasFisicas.remove(pessoaEncontrada);
        }
    }

    public PessoaFisica obter(int id) {
        return pessoasFisicas.stream()
                .filter(pessoa -> pessoa.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public ArrayList<PessoaFisica> obterTodos() {
        return pessoasFisicas;
    }

    public void persistir(String nomeArquivo) throws IOException {
        try (FileOutputStream arquivo = new FileOutputStream(nomeArquivo);
             ObjectOutputStream objeto = new ObjectOutputStream(arquivo)) {

            objeto.writeObject(pessoasFisicas);
        }
    }

    @SuppressWarnings("unchecked")
    public void recuperar(String nomeArquivo)
            throws IOException, ClassNotFoundException {

        try (FileInputStream arquivo = new FileInputStream(nomeArquivo);
             ObjectInputStream objeto = new ObjectInputStream(arquivo)) {

            pessoasFisicas = (ArrayList<PessoaFisica>) objeto.readObject();
        }
    }
}