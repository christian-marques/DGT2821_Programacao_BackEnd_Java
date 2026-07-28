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
public class PessoaJuridicaRepo {

    private ArrayList<PessoaJuridica> pessoasJuridicas;

    public PessoaJuridicaRepo() {
        pessoasJuridicas = new ArrayList<>();
    }

    public void inserir(PessoaJuridica pessoaJuridica) {
        pessoasJuridicas.add(pessoaJuridica);
    }

    public void alterar(PessoaJuridica pessoaJuridica) {
        PessoaJuridica pessoaEncontrada = obter(pessoaJuridica.getId());

        if (pessoaEncontrada != null) {
            pessoaEncontrada.setNome(pessoaJuridica.getNome());
            pessoaEncontrada.setCnpj(pessoaJuridica.getCnpj());
        }
    }

    public void excluir(int id) {
        PessoaJuridica pessoaEncontrada = obter(id);

        if (pessoaEncontrada != null) {
            pessoasJuridicas.remove(pessoaEncontrada);
        }
    }

    public PessoaJuridica obter(int id) {
        return pessoasJuridicas.stream()
                .filter(pessoa -> pessoa.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public ArrayList<PessoaJuridica> obterTodos() {
        return pessoasJuridicas;
    }

    public void persistir(String nomeArquivo) throws IOException {
        try (FileOutputStream arquivo = new FileOutputStream(nomeArquivo);
             ObjectOutputStream objeto = new ObjectOutputStream(arquivo)) {

            objeto.writeObject(pessoasJuridicas);
        }
    }

    @SuppressWarnings("unchecked")
    public void recuperar(String nomeArquivo)
            throws IOException, ClassNotFoundException {

        try (FileInputStream arquivo = new FileInputStream(nomeArquivo);
             ObjectInputStream objeto = new ObjectInputStream(arquivo)) {

            pessoasJuridicas =
                    (ArrayList<PessoaJuridica>) objeto.readObject();
        }
    }
}