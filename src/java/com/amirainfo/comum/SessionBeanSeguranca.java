/*
 * AMIRA - WEB SYSTEMS - Todos os Direitos Reservados - 2001-2019
 * ARQUIVO: SessionBeanSeguranca.java
 * PROJETO: Amira BOOTSTRAP 2019
 * DATA CRIAÇÃO: 20/06/2019 08:00AM
 * DATA ALTERAÇÃO: 
 * CRIADO POR: Danilo Parisotto
 * VERSAO: 1.0 
 */

package com.amirainfo.comum;
import com.amirainfo.faces.FacesBean;
import com.wt.sql.*;
import java.io.Serializable;
import java.sql.*;
import javax.enterprise.context.SessionScoped;
import javax.faces.FacesException;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.sql.rowset.CachedRowSet;

@Named("comum$SessionBeanSeguranca")
@SessionScoped
public class SessionBeanSeguranca extends FacesBean implements Serializable {

    //************************************************************** CONSTRUCTOR
    public SessionBeanSeguranca() {

    }

    //***************************************************************** PROTECTS
    protected ApplicationBeanConfiguracao getApplicationBeanConfiguracao() {
        return (ApplicationBeanConfiguracao)super.getBean("comum$ApplicationBeanConfiguracao");
    }
    
    protected SessionBeanUsuario getSessionBeanUsuario() {
        return (SessionBeanUsuario)super.getBean("comum$SessionBeanUsuario");
    }

    public void verificaSessao() {
        try {
            if (!this.isLogado()) {
                ServletContext contexto = (ServletContext)this.getExternalContext().getContext();
                String pastaBase = contexto.getContextPath();
                contexto = null;
                this.getExternalContext().redirect(pastaBase + "/modulos/seguranca/autenticacao.ami");
            }
        } catch (Exception e) {
            log("SessionBeanSeguranca Falhou na Inicialização", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        }
    }
    
    public void verificaPermissao(String modulo) {

        boolean acessoNegado = true;
        Conexao conexao = new Conexao();
        try {
            if (conexao.abreConexao(this.getApplicationBeanConfiguracao().getContexto(), this.getApplicationBeanConfiguracao().getRecursoJDBC(), true)) {
                Comando comando = new Comando("stpWamiLisAcoes", conexao.getConexao());
                comando.setTipoComando(TipoComando.STORED_PROCEDURE);
                comando.adicionaParametro("pSiglaModulo", Types.VARCHAR, modulo);
                comando.adicionaParametro("pNivelAcesso", Types.NUMERIC, new Integer(this.getSessionBeanUsuario().getCodigoNivelAcesso()));
                
                CachedRowSet resultado = comando.executaConsultaCache();
                
                
                if (resultado != null) {
                    resultado.next();
                    this.setInserir(resultado.getBoolean("inserir"));
                    if (this.isInserir()) {
                        acessoNegado = false;
                    }
                    this.setExcluir(resultado.getBoolean("excluir"));
                    if (this.isExcluir()) {
                        acessoNegado = false;
                    }
                    this.setAlterar(resultado.getBoolean("alterar"));
                    if (this.isAlterar()) {
                        acessoNegado = false;
                    }
                    this.setVisualizar(resultado.getBoolean("visualizar"));
                    if (this.isVisualizar()) {
                        acessoNegado = false;
                    }
                    resultado.close();
                } else {
                    acessoNegado = true;
                }
                
                resultado = null;
                comando = null;
            } else {
                acessoNegado = true;
            }
            
            if (acessoNegado) {
                ServletContext contexto = (ServletContext)this.getExternalContext().getContext();
                String pastaBase = contexto.getContextPath();
                contexto = null;
                this.getExternalContext().redirect(pastaBase + "/modulos/seguranca/autenticacao.ami");
            }
            
        } catch (Exception e) {
            log("SessionBeanSeguranca Falhou na Inicialização verificaPermissao", e);
            throw e instanceof FacesException ? (FacesException) e: new FacesException(e);
        } finally {
            conexao.fechaConexao();
            conexao = null;
        }
    }


    //********************************************************** GLOBAL PRIVATES

    private boolean logado;
    public boolean isLogado() {
        return this.logado;
    }
    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    private boolean inserir = false;
    public boolean isInserir() {
        return this.inserir;
    }
    private void setInserir(boolean inserir) {
        this.inserir = inserir;
    }
    
    private boolean excluir = false;
    public boolean isExcluir() {
        return this.excluir;
    }
    private void setExcluir(boolean excluir) {
        this.excluir = excluir;
    }
    
    private boolean alterar = false;
    public boolean isAlterar() {
        return this.alterar;
    }
    private void setAlterar(boolean alterar) {
        this.alterar = alterar;
    }
    
    private boolean visualizar = true;
    public boolean isVisualizar() {
        return this.visualizar;
    }
    private void setVisualizar(boolean visualizar) {
        this.visualizar = visualizar;
    }
    
    private boolean gerarPDF = false;
    public boolean isGerarPDF() {
        return this.gerarPDF;
    }
    public void setGerarPDF(boolean gerarPDF) {
        this.gerarPDF = gerarPDF;
    }
    
    private boolean gerarPlanilha = false;
    public boolean isGerarPlanilha() {
        return this.gerarPlanilha;
    }
    public void setGerarPlanilha(boolean gerarPlanilha) {
        this.gerarPlanilha = gerarPlanilha;
    }
}