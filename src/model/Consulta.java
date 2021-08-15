/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author evert
 */
public class Consulta {
    
    private int cd_registro;
    private String nm_fazendeiro;
    private String endereco;
    private String ds_produtoCultivado;
    private String agrotoxico;
    private int ic_restricao;

    public Consulta(String nm_fazendeiro, String endereco, String ds_produtoCultivado, String agrotoxico, int ic_restricao) {
        this.nm_fazendeiro = nm_fazendeiro;
        this.endereco = endereco;
        this.ds_produtoCultivado = ds_produtoCultivado;
        this.agrotoxico = agrotoxico;
        this.ic_restricao = ic_restricao;
    }

    public int getCd_registro() {
        return cd_registro;
    }

    public void setCd_registro(int cd_registro) {
        this.cd_registro = cd_registro;
    }

    public String getNm_fazendeiro() {
        return nm_fazendeiro;
    }

    public void setNm_fazendeiro(String nm_fazendeiro) {
        this.nm_fazendeiro = nm_fazendeiro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDs_produtoCultivado() {
        return ds_produtoCultivado;
    }

    public void setDs_produtoCultivado(String ds_produtoCultivado) {
        this.ds_produtoCultivado = ds_produtoCultivado;
    }

    public String getAgrotoxico() {
        return agrotoxico;
    }

    public void setAgrotoxico(String agrotoxico) {
        this.agrotoxico = agrotoxico;
    }

    public int getIc_restricao() {
        return ic_restricao;
    }

    public void setIc_restricao(int ic_restricao) {
        this.ic_restricao = ic_restricao;
    }
    
    
    
}
