/*
 * AMIRA - WEB SYSTEMS - Todos os Direitos Reservados - 2001-2017
 * ARQUIVO: Logoff.java
 * PROJETO: Amira Matrix
 * DATA CRIAÇÃO: 13/11/2014 03:11PM
 * DATA ALTERAÇÃO: 06/01/2017 08:00AM
 * CRIADO POR: Danilo Parisotto
 * VERSAO: 1.3
 */


package com.amirainfo.modulos.seguranca;

import com.amirainfo.comum.SessionBeanEmpresas;
import com.amirainfo.comum.SessionBeanSeguranca;
import com.amirainfo.comum.SessionBeanUsuario;
import com.amirainfo.faces.FacesBean;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.*;
import javax.inject.Named;

@Named("modulos$seguranca$LogOff")
@RequestScoped
@ManagedBean
public class LogOff extends FacesBean {
    
    public LogOff() {
        
       
        
    }
    
    @PostConstruct
    public void inicializar() {
        
        
    }
   
    protected SessionBeanSeguranca getSessionBeanSeguranca() {
        return (SessionBeanSeguranca)super.getBean("comum$SessionBeanSeguranca");
    }
    
    protected SessionBeanUsuario getSessionBeanUsuario() {
        return (SessionBeanUsuario) getBean("comum$SessionBeanUsuario");
    }

    protected SessionBeanEmpresas getSessionBeanEmpresas() {
        return (SessionBeanEmpresas) getBean("comum$SessionBeanEmpresas");
    }


    private HtmlForm hfmFormulario = new HtmlForm();
    public HtmlForm getHfmFormulario() {
        return this.hfmFormulario;
    }
    public void setHfmFormulario(HtmlForm hfmFormulario) {
        this.hfmFormulario = hfmFormulario;
    }

    private HtmlInputHidden hihErro = new HtmlInputHidden();
    public HtmlInputHidden getHihErro() {
        return this.hihErro;
    }
    public void setHihErro(HtmlInputHidden hihErro) {
        this.hihErro = hihErro;
    }

    private HtmlOutputLabel holAguarde = new HtmlOutputLabel();
    public HtmlOutputLabel getHolAguarde() {
        return this.holAguarde;
    }
    public void setHolAguarde(HtmlOutputLabel holAguarde) {
        this.holAguarde = holAguarde;
    }

    public void efetuarLogoff(){
        
        this.getSessionBeanSeguranca().setLogado(false);
        this.getHolAguarde().setValue("Sessão Finalizada!");
        this.getSessionBeanUsuario().setAlterarCamposEspeciais(null);
        this.getSessionBeanUsuario().setAprovarPedidosVenda(null);
        this.getSessionBeanUsuario().setCadastrarEntregaComprasSemPedido(null);
        this.getSessionBeanUsuario().setAprovarPedidosVendaDebito(null);
        this.getSessionBeanUsuario().setCodigoNivelAcesso(0);
        this.getSessionBeanUsuario().setAlterarCamposEspeciais(null);
        this.getSessionBeanUsuario().setCodigoUsuario(0);
        this.getSessionBeanUsuario().setNomeUsuario("");
        this.getSessionBeanEmpresas().setEmpresaLogin("");
        
        
    }
    
    public String hcbFinalizar_action() {
        this.efetuarLogoff();
        return "/modulos/seguranca/autenticacao.ami";
    }
}