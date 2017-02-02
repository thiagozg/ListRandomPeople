package br.com.listrandompeople.dao;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import br.com.listrandompeople.model.Pessoa;

/**
 * Created by thiagozg on 22/10/2016.
 */

public class PessoaDAO extends Activity {

	private SQLiteDatabase bd;
    private Context context;
	
	public PessoaDAO(Context c) {
		this.context = c;
	}
	
	public final void abreOuCriaBanco() {
    	try {
			bd = context.openOrCreateDatabase("Pessoas.db", MODE_WORLD_READABLE, null);

            String sql =
                    "CREATE TABLE IF NOT EXISTS tbl_pessoa " +
					"(id INTEGER UNSIGNED PRIMARY KEY, " +
					"nome VARCHAR(30), " +
					"email VARCHAR(100), " +
					"user VARCHAR(30), " +
					"telefone VARCHAR(20), " +
					"estado VARCHAR(30), " +
					"sobrenome VARCHAR(30), " +
					"titulo VARCHAR(30), " +
					"rua VARCHAR(30), " +
					"celular VARCHAR(30), " +
					"senha VARCHAR(30), " +
					"cidade VARCHAR(30));";

			bd.execSQL(sql);
		} catch (Exception e) {
			Log.w("Erro no BD", "Erro ao criar o banco ("+e+").");
		}
    }
    
    public final void inserirListaPessoas(List<Pessoa> pessoas) {
        for (Pessoa p : pessoas) {
            try {
                String sql =
                        "INSERT INTO tbl_pessoa " +
                        "(nome, " +
                        "email, " +
                        "user, " +
                        "telefone, " +
                        "estado, " +
                        "sobrenome, " +
                        "titulo, " +
                        "rua, " +
                        "celular, " +
                        "senha, " +
                        "cidade) " +
                        "VALUES " +
                        "('" + p.getNome() + "', " +
                        "'" + p.getEmail() + "', " +
                        "'" + p.getUser() + "', " +
                        "'" + p.getTelefone() + "', " +
                        "'" + p.getEstado() + "', " +
                        "'" + p.getSobrenome() + "', " +
                        "'" + p.getTitulo() + "', " +
                        "'" + p.getRua() + "', " +
                        "'" + p.getCelular() + "', " +
                        "'" + p.getSenha() + "', " +
                        "'" + p.getCidade() + "')";
                bd.execSQL(sql);
            } catch (Exception e) {
                Log.w("Erro no BD", "Erro ao inserir dados no banco (" + e + ").");
            }
        }
    }

    public final Pessoa consultarPessoa(String user) {
        Cursor cursor = null;

        try {
            bd = context.openOrCreateDatabase("Pessoas.db", MODE_WORLD_READABLE, null);
            String sql = "SELECT * FROM tbl_pessoa WHERE user = '" + user + "'";
            cursor = bd.rawQuery(sql, null);

            if (cursor != null) {
                cursor.moveToFirst();
            }

        } catch (Exception e) {
            Log.e("Erro no BD", "Erro ao consultar dados no banco (" + e + ").");
        }

        return new Pessoa(cursor);
    }

    public final void limparDados() {
        try {
            bd = context.openOrCreateDatabase("Pessoas.db", MODE_WORLD_READABLE, null);
            String sql = "DELETE FROM tbl_pessoa";
            bd.execSQL(sql);
        } catch (Exception e) {
            Log.e("Erro no BD", "Erro ao consultar dados no banco (" + e + ").");
        }
    }

    public final void fechaBanco(){
    	try {
    		bd.close();
		} catch (Exception e) {
			Log.w("Erro no BD", "Erro ao fechar o banco ("+e+").");
		}
    }
}
