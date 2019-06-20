/*
 * AMIRA - WEB SYSTEMS - Todos os Direitos Reservados - 2001-2019
 * ARQUIVO: SessionBeanUsuario.java
 * PROJETO: Amira BOOTSTRAP 2019
 * DATA CRIAÇÃO: 20/06/2019 08:00AM
 * DATA ALTERAÇÃO: 
 * CRIADO POR: Danilo Parisotto
 * VERSAO: 1.0
 */

package com.amirainfo.comum;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("comum$SessionBeanUsuario")
@SessionScoped
public class SessionBeanUsuario implements Serializable {

    //************************************************************** CONSTRUCTOR
    public SessionBeanUsuario() {

    }

    //********************************************************** GLOBAL PRIVATES
    private int codigoNivelAcesso;
    public int getCodigoNivelAcesso() {
        return this.codigoNivelAcesso;
    }
    public void setCodigoNivelAcesso(int codigoNivelAcesso) {
        this.codigoNivelAcesso = codigoNivelAcesso;
    }

    private int codigoUsuario;
    public int getCodigoUsuario() {
        return this.codigoUsuario;
    }
    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    private String nomeUsuario;
    public String getNomeUsuario() {
        return this.nomeUsuario;
    }
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
    private String departamentoUsuario;
    public String getDepartamentoUsuario() {
        return departamentoUsuario;
    }
    public void setDepartamentoUsuario(String departamentoUsuario) {
        this.departamentoUsuario = departamentoUsuario;
    }
    
    private String numeroIp;
    public String getNumeroIp() {
        return this.numeroIp;
    }
    public void setNumeroIp(String numeroIp) {
        this.numeroIp = numeroIp;
    }

    private String situacaoSistema;
    public String getSituacaoSistema() {
        return situacaoSistema;
    }
    public void setSituacaoSistema(String situacaoSistema) {
        this.situacaoSistema = situacaoSistema;
    }

    private String confirmarPedidosVenda;
    public String getConfirmarPedidosVenda() {
        return confirmarPedidosVenda;
    }
    public void setConfirmarPedidosVenda(String confirmarPedidosVenda) {
        this.confirmarPedidosVenda = confirmarPedidosVenda;
    }

    private String aprovarPedidosVenda;
    public String getAprovarPedidosVenda() {
        return aprovarPedidosVenda;
    }
    public void setAprovarPedidosVenda(String aprovarPedidosVenda) {
        this.aprovarPedidosVenda = aprovarPedidosVenda;
    }

    private String aprovarPedidosVendaDebito;
    public String getAprovarPedidosVendaDebito() {
        return aprovarPedidosVendaDebito;
    }
    public void setAprovarPedidosVendaDebito(String aprovarPedidosVendaDebito) {
        this.aprovarPedidosVendaDebito = aprovarPedidosVendaDebito;
    }


    //************************************************************ OTHER METHODS
    private String cadastrarEntregaComprasSemPedido;
    public String getCadastrarEntregaComprasSemPedido() {
        return cadastrarEntregaComprasSemPedido;
    }
    public void setCadastrarEntregaComprasSemPedido(String cadastrarEntregaComprasSemPedido) {
        this.cadastrarEntregaComprasSemPedido = cadastrarEntregaComprasSemPedido;
    }

    private String alterarCamposEspeciais;
    public String getAlterarCamposEspeciais() {
        return alterarCamposEspeciais;
    }
    public void setAlterarCamposEspeciais(String alterarCamposEspeciais) {
        this.alterarCamposEspeciais = alterarCamposEspeciais;
    }
    
    private String PermitirAcessoExterno;

    public String getPermitirAcessoExterno() {
        return PermitirAcessoExterno;
    }

    public void setPermitirAcessoExterno(String PermitirAcessoExterno) {
        this.PermitirAcessoExterno = PermitirAcessoExterno;
    }

}