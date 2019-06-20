/*
 * AMIRA - WEB SYSTEMS - Todos os Direitos Reservados - 2001-2017
 * ARQUIVO: SessionBeanMenu.java
 * PROJETO: Amira Matrix
 * DATA CRIAÇÃO: 27/04/2011 12:14AM
 * DATA ALTERAÇÃO: 06/01/2017 08:00AM
 * CRIADO POR: Danilo Parisotto
 * VERSAO: 1.3
 */

package com.amirainfo.comum;

import com.amirainfo.faces.FacesBean;
import com.wt.sql.*;
import java.io.Serializable;
import java.sql.Types;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.sql.rowset.CachedRowSet;

@Named("comum$SessionBeanMenu")
@SessionScoped
public class SessionBeanMenu extends FacesBean implements Serializable {

    //************************************************************** CONSTRUCTOR
    public SessionBeanMenu() {

    }
    
     @PostConstruct
    public void inicializar() {
        
    }

    //***************************************************************** PROTECTS
    protected ApplicationBeanConfiguracao getApplicationBeanConfiguracao() {
        return (ApplicationBeanConfiguracao)super.getBean("comum$ApplicationBeanConfiguracao");
    }

    protected SessionBeanUsuario getSessionBeanUsuario() {
        return (SessionBeanUsuario)super.getBean("comum$SessionBeanUsuario");
    }

    //********************************************************** GLOBAL PRIVATES

    private ArrayList menu = new ArrayList();
    public ArrayList getMenu() {
        return this.menu;
    }

    private String erro = new String();
    public String getErro() {
        return this.erro;
    }
    public void setErro(String erro) {
        this.erro = erro;
    }

    //*********************************************************** GLOBAL METHODS
    public void carregaMenu() {
        try {
            this.setErro(null);
            Conexao conexao = new Conexao();
            
            if (conexao.abreConexao(this.getApplicationBeanConfiguracao().getContexto(), this.getApplicationBeanConfiguracao().getRecursoJDBC(), true)) {
                Comando comando = new Comando("stpWamiMonMenMod", conexao.getConexao());
                comando.setTipoComando(TipoComando.STORED_PROCEDURE);
                comando.adicionaParametro("pModuloPai", Types.NUMERIC, new Integer(0));
                comando.adicionaParametro("pNivelAcesso", Types.NUMERIC, new Integer(this.getSessionBeanUsuario().getCodigoNivelAcesso()));
                CachedRowSet resultadoPai = comando.executaConsultaCache();

                this.getMenu().clear();
                int x = 0;

                if (resultadoPai != null) {
                    while(resultadoPai.next()) {
                        TreeMap itens = new TreeMap();

                        comando.setComandoSQL("stpWamiMonMenMod");
                        comando.limpaParametros();
                        comando.adicionaParametro("pModuloPai", Types.NUMERIC, new Integer(resultadoPai.getInt("modulo")));
                        comando.adicionaParametro("pNivelAcesso", Types.NUMERIC, new Integer(this.getSessionBeanUsuario().getCodigoNivelAcesso()));

                        CachedRowSet resultadoFilho = comando.executaConsultaCache();

                        if (resultadoFilho.isBeforeFirst()) {
                            ArrayList arrayFilho = new ArrayList();

                            int y = 0;
                            while(resultadoFilho.next()) {
                                TreeMap itemFilho = new TreeMap();

                                comando.setComandoSQL("stpWamiMonMenMod");
                                comando.limpaParametros();
                                comando.adicionaParametro("pModuloPai", Types.NUMERIC, new Integer(resultadoFilho.getInt("modulo")));
                                comando.adicionaParametro("pNivelAcesso", Types.NUMERIC, new Integer(this.getSessionBeanUsuario().getCodigoNivelAcesso()));

                                CachedRowSet resultadoNeto = comando.executaConsultaCache();

                                if (resultadoNeto.isBeforeFirst()) {
                                    ArrayList arrayNeto = new ArrayList();

                                    int z = 0;
                                    while(resultadoNeto.next()) {
                                        TreeMap itemNeto = new TreeMap();
                                        itemNeto.put("modulo", resultadoNeto.getString("modulo"));
                                        itemNeto.put("descricao", resultadoNeto.getString("descricao"));
                                        itemNeto.put("ordem", resultadoNeto.getString("ordem"));
                                        itemNeto.put("caminho", resultadoNeto.getString("caminho"));
                                        itemNeto.put("icone", resultadoNeto.getString("icone"));

                                        arrayNeto.add(z, itemNeto);
                                        z++;
                                        itemNeto = null;
                                    }

                                    itemFilho.put("neto", arrayNeto);
                                    arrayNeto = null;
                                }
                                resultadoNeto.close();
                                resultadoNeto = null;

                                itemFilho.put("modulo", resultadoFilho.getString("modulo"));
                                itemFilho.put("descricao", resultadoFilho.getString("descricao"));
                                itemFilho.put("ordem", resultadoFilho.getString("ordem"));
                                itemFilho.put("caminho", resultadoFilho.getString("caminho"));
                                itemFilho.put("icone", resultadoFilho.getString("icone"));

                                arrayFilho.add(y, itemFilho);
                                y++;
                                itemFilho = null;
                            }

                            itens.put("filho", arrayFilho);
                            arrayFilho = null;
                        }
                        resultadoFilho.close();
                        resultadoFilho = null;

                        itens.put("modulo", resultadoPai.getString("modulo"));
                        itens.put("descricao", resultadoPai.getString("descricao"));
                        itens.put("ordem", resultadoPai.getString("ordem"));
                        itens.put("caminho", resultadoPai.getString("caminho"));
                        itens.put("icone", resultadoPai.getString("icone"));
                        this.getMenu().add(x, itens);
                        x++;
                        itens = null;

                    }
                    resultadoPai.close();
                    resultadoPai = null;
                } else {
                    this.setErro(comando.getErro());
                }
                conexao.fechaConexao();
            } else {
                this.setErro(conexao.getErro());
            }
            conexao = null;
        } catch(Exception e) {
            this.setErro(e.getMessage());
        }
    }

   
}