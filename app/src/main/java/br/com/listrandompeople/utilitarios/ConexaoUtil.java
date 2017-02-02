package br.com.listrandompeople.utilitarios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.listrandompeople.model.Pessoa;
import br.com.listrandompeople.service.ConexaoAPI;

/**
 * Created by thiagozg on 22/10/2016.
 */

public final class ConexaoUtil {

    public static Pessoa getPessoa(String url){
        String json = ConexaoAPI.getJSONFromAPI(url);
        return converterJson(json);
    }

    private static Pessoa converterJson(String json){

        try {
            Pessoa pessoa = new Pessoa();

            JSONObject jsonObj = new JSONObject(json);
            JSONArray array = jsonObj.getJSONArray("results");

            JSONObject objArray = array.getJSONObject(0);

            pessoa.setEmail(objArray.getString("email"));
            pessoa.setTelefone(objArray.getString("phone"));
            pessoa.setCelular(objArray.getString("cell"));

            JSONObject nome = objArray.getJSONObject("name");
            pessoa.setNome(nome.getString("first"));
            pessoa.setSobrenome(nome.getString("last"));
            pessoa.setTitulo(nome.getString("title"));

            JSONObject endereco = objArray.getJSONObject("location");
            pessoa.setEstado(endereco.getString("state"));
            pessoa.setCidade(endereco.getString("city"));
            pessoa.setRua(endereco.getString("street"));

            JSONObject login = objArray.getJSONObject("login");
            pessoa.setUser(login.getString("username"));
            pessoa.setSenha(login.getString("password"));

            return pessoa;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
