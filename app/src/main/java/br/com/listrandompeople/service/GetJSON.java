package br.com.listrandompeople.service;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import br.com.listrandompeople.dao.PessoaDAO;
import br.com.listrandompeople.model.Pessoa;
import br.com.listrandompeople.utilitarios.ConexaoUtil;


/**
 * Created by thiagozg on 22/10/2016.
 */

public class GetJSON extends AsyncTask<Void, Void, List<Pessoa>> {

    private String url;
    private int qtdRequisicoes = 0;
    private Context context;

    public GetJSON(String url, int qtdRequisicoes, Context context) {
        this.url = url;
        this.qtdRequisicoes = qtdRequisicoes;
        this.context = context;
    }

    @Override
    protected List<Pessoa> doInBackground(Void... params) {
        List<Pessoa> listaPessoas = new ArrayList<>();

        for (int i = 0; i < qtdRequisicoes; i++) {
            listaPessoas.add(ConexaoUtil.getPessoa(url));
        }

        return listaPessoas;
    }

    @Override
    protected void onPostExecute(List<Pessoa> pessoas) {
        PessoaDAO pDao = new PessoaDAO(this.context);

        try {
            pDao.abreOuCriaBanco();
            pDao.inserirListaPessoas(pessoas);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pDao.fechaBanco();
        }
    }

}
