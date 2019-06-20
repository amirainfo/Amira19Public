/*
 * AMIRA - WEB SYSTEMS - Todos os Direitos Reservados - 2001-2019
 * ARQUIVO: Inicio.java
 * PROJETO: Amira BOOTSTRAP 2019
 * DATA CRIAÇÃO: 20/06/2019 08:00AM
 * DATA ALTERAÇÃO: 
 * CRIADO POR: Danilo Parisotto
 * VERSAO: 1.0
 */

package com.amirainfo;


import com.amirainfo.comum.ApplicationBeanConfiguracao;
import com.amirainfo.comum.SessionBeanSeguranca;
import com.amirainfo.faces.FacesBean;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlForm;
import javax.inject.Named;


@Named("Inicio")
@RequestScoped
@ManagedBean

public class Inicio extends FacesBean{
    
     //************************************************************** CONSTRUCTOR
    public Inicio() {
             
    }
    
    @PostConstruct
    public void inicializar() {
        
        this.getSessionBeanSeguranca().verificaSessao();
        
    }
    
    //***************************************************************** PROTECTS

    protected ApplicationBeanConfiguracao getApplicationBeanConfiguracao() {
        return (ApplicationBeanConfiguracao) super.getBean("comum$ApplicarionBeanConfiguracao");
    }
    
     protected SessionBeanSeguranca getSessionBeanSeguranca() {
        return (SessionBeanSeguranca) super.getBean("comum$SessionBeanSeguranca");
    }
    
    //********************************************************** GLOBAL PRIVATES
    private HtmlForm hfmFormulario = new HtmlForm();

    public HtmlForm getHfmFormulario() {
        return this.hfmFormulario;
    }

    public void setHfmFormulario(HtmlForm hfmFormulario) {
        this.hfmFormulario = hfmFormulario;
    }
    
    
}

