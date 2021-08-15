/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.io.InputStream;

/**
 *
 * @author evert
 */
public class Pessoa {
    
    private String nomeCompleto;
    private String login;
    private String senha;
    private int ic_restricao;
    private InputStream fotoBiometria1;
    private InputStream fotoBiometria2;
    private InputStream fotoBiometria3;
    private InputStream fotoBiometria4;
    private int identificador;

    public Pessoa(String nomeCompleto, String login, String senha, int ic_restricao, InputStream fotoBiometria1, InputStream fotoBiometria2, InputStream fotoBiometria3, InputStream fotoBiometria4) {
        this.nomeCompleto = nomeCompleto;
        this.login = login;
        this.senha = senha;
        this.ic_restricao = ic_restricao;
        this.fotoBiometria1 = fotoBiometria1;
        this.fotoBiometria2 = fotoBiometria2;
        this.fotoBiometria3 = fotoBiometria3;
        this.fotoBiometria4 = fotoBiometria4;
    }

   

    public InputStream getFotoBiometria1() {
        return fotoBiometria1;
    }

    public void setFotoBiometria1(InputStream fotoBiometria1) {
        this.fotoBiometria1 = fotoBiometria1;
    }

    public InputStream getFotoBiometria2() {
        return fotoBiometria2;
    }

    public void setFotoBiometria2(InputStream fotoBiometria2) {
        this.fotoBiometria2 = fotoBiometria2;
    }

    public InputStream getFotoBiometria3() {
        return fotoBiometria3;
    }

    public void setFotoBiometria3(InputStream fotoBiometria3) {
        this.fotoBiometria3 = fotoBiometria3;
    }

    public InputStream getFotoBiometria4() {
        return fotoBiometria4;
    }

    public void setFotoBiometria4(InputStream fotoBiometria4) {
        this.fotoBiometria4 = fotoBiometria4;
    }

    

 


    
 

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nome) {
        this.nomeCompleto = nome;
    }

  

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

   
    
     public int getIc_restricao() {
        return ic_restricao;
    }

    public void setIc_restricao(int ic_restricao) {
        this.ic_restricao = ic_restricao;
    }
    
    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
}
