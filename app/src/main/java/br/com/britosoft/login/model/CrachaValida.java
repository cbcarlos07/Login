package br.com.britosoft.login.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by carlos on 13/08/17.
 */

public class CrachaValida {

    @SerializedName("success")

    private Integer success;
    @SerializedName("ativo")

    private Integer ativo;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getAtivo() {
        return ativo;
    }

    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
    }

}
