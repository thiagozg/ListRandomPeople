package br.com.listrandompeople.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.com.listrandompeople.R;
import br.com.listrandompeople.dao.PessoaDAO;
import br.com.listrandompeople.model.Pessoa;

/**
 * Created by thiagozg on 22/10/2016.
 */

public class DetalhesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Pessoa pessoa = null;
        PessoaDAO pDao = new PessoaDAO(getApplicationContext());

        try {
            pDao.abreOuCriaBanco();
            pessoa = pDao.consultarPessoa(getIntent().getExtras().getString("idUser"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pDao.fechaBanco();
        }

        this.popularCampos(pessoa);
    }

    private void popularCampos(Pessoa pessoa) {
        TextView tvTitulo = (TextView) findViewById(R.id.tvAdTitulo);
        TextView tvNome = (TextView) findViewById(R.id.tvAdNome);
        TextView tvSobrenome = (TextView) findViewById(R.id.tvAdSobrenome);
        TextView tvUser = (TextView) findViewById(R.id.tvAdUser);
        TextView tvSenha = (TextView) findViewById(R.id.tvAdSenha);
        TextView tvEmail = (TextView) findViewById(R.id.tvAdEmail);
        TextView tvTelefone = (TextView) findViewById(R.id.tvAdTelefone);
        TextView tvCelular = (TextView) findViewById(R.id.tvAdCelular);
        TextView tvRua = (TextView) findViewById(R.id.tvAdRua);
        TextView tvCidade = (TextView) findViewById(R.id.tvAdCidade);
        TextView tvEstado = (TextView) findViewById(R.id.tvAdEstado);


        tvTitulo.setText(toUperCase(pessoa.getTitulo() + "."));
        tvNome.setText(" " + toUperCase(pessoa.getNome()));
        tvSobrenome.setText(" " + toUperCase(pessoa.getSobrenome()));
        tvUser.setText("Login: " + pessoa.getUser());
        tvSenha.setText("\tSenha: " + pessoa.getSenha());
        tvEmail.setText(pessoa.getEmail());
        tvTelefone.setText(pessoa.getTelefone());
        tvCelular.setText(pessoa.getCelular());
        tvRua.setText(toUperCase(pessoa.getRua()));
        tvCidade.setText(toUperCase(pessoa.getCidade()));
        tvEstado.setText(toUperCase(pessoa.getEstado()));
    }

    private String toUperCase(String palavra) {
        char primeiraLetra = palavra.charAt(0);
        String aux = String.valueOf(primeiraLetra);
        return palavra.replace(aux, aux.toUpperCase());
    }

}
