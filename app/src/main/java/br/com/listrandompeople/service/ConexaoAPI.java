package br.com.listrandompeople.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by thiagozg on 22/10/2016.
 */

public final class ConexaoAPI {

    public static String getJSONFromAPI(String url){
        String retorno = "";
        HttpURLConnection conexao = null;

        try {
            URL apiEnd = new URL(url);
            InputStream is = null;
            int codigoResposta;

            conexao = (HttpURLConnection) apiEnd.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(15000);
            conexao.connect();

            codigoResposta = conexao.getResponseCode();
            if (codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST) {
                is = conexao.getInputStream();
            } else {
                is = conexao.getErrorStream();
            }

            retorno = converter(is);
            is.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conexao.disconnect();
        }

        return retorno;
    }

    private static String converter(InputStream is){
        StringBuffer buffer = new StringBuffer();
        BufferedReader br = null;

        try{

            String linha;
            br = new BufferedReader(new InputStreamReader(is));
            while ((linha = br.readLine())!=null) {
                buffer.append(linha);
            }

            br.close();

        } catch(IOException e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }

}
