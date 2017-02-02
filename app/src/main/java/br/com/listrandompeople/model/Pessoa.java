package br.com.listrandompeople.model;

import android.database.Cursor;

/**
 * Created by thiagozg on 22/10/2016.
 */

public class Pessoa {

    private String nome;
    private String email;
    private String user;
    private String telefone;
    private String cidade;
    private String estado;
    private String sobrenome;
    private String titulo;
    private String rua;
    private String celular;
    private String senha;

    public Pessoa() {}

    public Pessoa(Cursor cursor) {
        if (cursor == null) {
            throw new NullPointerException("Retorno do SQLite nulo!");
        }

        this.nome = cursor.getString(cursor.getColumnIndex("nome"));
        this.email = cursor.getString(cursor.getColumnIndex("email"));
        this.user = cursor.getString(cursor.getColumnIndex("user"));
        this.telefone = cursor.getString(cursor.getColumnIndex("telefone"));
        this.cidade = cursor.getString(cursor.getColumnIndex("cidade"));
        this.estado = cursor.getString(cursor.getColumnIndex("estado"));
        this.sobrenome = cursor.getString(cursor.getColumnIndex("sobrenome"));
        this.titulo = cursor.getString(cursor.getColumnIndex("titulo"));
        this.rua = cursor.getString(cursor.getColumnIndex("rua"));
        this.celular = cursor.getString(cursor.getColumnIndex("celular"));
        this.senha = cursor.getString(cursor.getColumnIndex("senha"));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
