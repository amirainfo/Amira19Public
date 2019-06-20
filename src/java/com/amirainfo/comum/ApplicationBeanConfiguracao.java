/*
 * AMIRA - WEB SYSTEMS - Todos os Direitos Reservados - 2001-2019
 * ARQUIVO: AplicationBeanConfiguracao.java
 * PROJETO: Amira BOOTSTRAP 2019
 * DATA CRIAÇÃO: 20/06/2019 08:00AM
 * DATA ALTERAÇÃO: 
 * CRIADO POR: Danilo Parisotto
 * VERSAO: 1.0
 */
package com.amirainfo.comum;

import com.amirainfo.faces.FacesBean;
import java.util.TimeZone;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.FacesException;
import javax.inject.Named;
import javax.naming.*;

@Named("comum$ApplicationBeanConfiguracao")
@ApplicationScoped
@ManagedBean
public class ApplicationBeanConfiguracao extends FacesBean {

    //************************************************************** CONSTRUCTOR
    public ApplicationBeanConfiguracao() {

    }

    @PostConstruct
    public void inicializar() {

        try {
            this.setNomeContexto("");
            this.setContexto(new InitialContext());
            this.setRecursoJDBC("java:comp/env/jdbc/bdamira19");
            this.setFusoHorario("America/Sao_Paulo");
            
            /*Acerta o TimeZone - correção para o problema de horário de verão*/
            TimeZone.setDefault(TimeZone.getTimeZone(this.getFusoHorario()));

        } catch (Exception e) {
            super.log("ApplicationBeanConfiguracao Falhou na inicialização", e);
            throw e instanceof FacesException ? (FacesException) e : new FacesException(e);
        }

    }

    //********************************************************** GLOBAL PRIVATES
    private String empresa = "Amira";

    private String getEmpresa() {
        return this.empresa;
    }

    private InitialContext contexto;

    public InitialContext getContexto() {
        return this.contexto;
    }

    public void setContexto(InitialContext contexto) {
        this.contexto = contexto;
    }

    private String nomeContexto;

    public String getNomeContexto() {
        return this.nomeContexto;
    }

    public void setNomeContexto(String nomeContexto) {
        this.nomeContexto = nomeContexto;
    }

    private String recursoJDBC;

    public String getRecursoJDBC() {
        return this.recursoJDBC;
    }

    public void setRecursoJDBC(String recursoJDBC) {
        this.recursoJDBC = recursoJDBC;
    }

    private String fusoHorario;

    public String getFusoHorario() {
        return this.fusoHorario;
    }

    public void setFusoHorario(String fusoHorario) {
        this.fusoHorario = fusoHorario;
    }
    
    
}
