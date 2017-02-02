package br.com.listrandompeople.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.listrandompeople.R;
import br.com.listrandompeople.dao.PessoaDAO;
import br.com.listrandompeople.interfaces.RecyclerViewOnClickListenerHack;
import br.com.listrandompeople.model.Pessoa;
import br.com.listrandompeople.service.GetJSON;
import br.com.listrandompeople.utilitarios.ListaAdapter;


/**
 * Created by thiagozg on 22/10/2016.
 */

public class MainActivity extends AppCompatActivity implements RecyclerViewOnClickListenerHack {

    private RecyclerView mRecyclerView;
    private List<Pessoa> mList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.receberQuantidade();
    }

    private void receberQuantidade() {
        View view = (LayoutInflater.from(this)).inflate(R.layout.dialog_quantidade, null);
        final EditText etQuantidade = (EditText) view.findViewById(R.id.etQuantidade);

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setView(view);
        alertBuilder.setCancelable(false)
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String aux = etQuantidade.getText().toString();
                        int qtd = Integer.valueOf(aux.isEmpty() ? "0" : aux);

                        if (qtd > 0) {
                            dialog.dismiss();
                            criarLista(qtd);
                        } else {
                            Toast.makeText(MainActivity.this, "Informe um número acima de 0", Toast.LENGTH_SHORT).show();
                            receberQuantidade();
                        }
                    }
                });

        Dialog dialog = alertBuilder.create();
        dialog.show();
    }

    private void criarLista(int qtd) {
        this.mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        this.mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.mRecyclerView.setLayoutManager(layoutManager);

        this.acessarWebService(qtd);
        ListaAdapter adapter = new ListaAdapter(this, this.mList);
        adapter.setRecyclerViewOnClickListenerHack(this);
        this.mRecyclerView.setAdapter(adapter);
    }

    private void acessarWebService(int qtd) {
        try {
            GetJSON getJSON = new GetJSON("https://randomuser.me/api/", qtd, getApplicationContext());
            mList = getJSON.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClickListener(View view, int position) {
        startActivity(new Intent(this, DetalhesActivity.class).putExtra("idUser", mList.get(position).getUser()));
    }

    @Override
    public void onLongPressClickListener(View view, int position) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PessoaDAO pDao = new PessoaDAO(getApplicationContext());
        pDao.limparDados();
    }
}
