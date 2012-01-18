/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.jswitch.auditoria.modelo;

import com.jswitch.base.modelo.util.bean.BeanVO;

/**
 *
 * @author orlandobcrra
 */
public class AtributoValor extends BeanVO {

    private String atributo;
    private Object valor;

    public AtributoValor() {
    }

    public AtributoValor(String atributo, Object valor) {
        this.atributo = atributo;
        this.valor = valor;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
}
