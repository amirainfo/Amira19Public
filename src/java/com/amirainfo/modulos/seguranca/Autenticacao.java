/*
 * AMIRA - WEB SYSTEMS - Todos os Direitos Reservados - 2001-2019
 * ARQUIVO: Autenticacao.java
 * PROJETO: Amira BOOTSTRAP 2019
 * DATA CRIAÇÃO: 20/06/2019 08:00AM
 * DATA ALTERAÇÃO: 
 * CRIADO POR: Danilo Parisotto
 * VERSAO: 1.0
 */


package com.amirainfo.modulos.seguranca;


import com.amirainfo.comum.ApplicationBeanConfiguracao;
import com.amirainfo.comum.SessionBeanEmpresas;
import com.amirainfo.comum.SessionBeanMenu;
import com.amirainfo.comum.SessionBeanSeguranca;
import com.amirainfo.comum.SessionBeanUsuario;
import com.amirainfo.faces.FacesBean;
import com.wt.sql.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UISelectItem;
import javax.faces.component.html.*;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.CachedRowSet;

@Named("modulos$seguranca$Autenticacao")
@RequestScoped
@ManagedBean
public class Autenticacao extends FacesBean {

    //************************************************************** CONSTRUCTOR
    public Autenticacao() {
        
             
    }
    
    @PostConstruct
    public void inicializar() {
        
        
    }

    //***************************************************************** PROTECTS
    protected ApplicationBeanConfiguracao getApplicationBeanConfiguracao() {
        return (ApplicationBeanConfiguracao) super.getBean("comum$ApplicationBeanConfiguracao");
    }
  
    protected SessionBeanMenu getSessionBeanMenu() {
        return (SessionBeanMenu) super.getBean("comum$SessionBeanMenu");
    }

    protected SessionBeanSeguranca getSessionBeanSeguranca() {
        return (SessionBeanSeguranca) super.getBean("comum$SessionBeanSeguranca");
    }

    protected SessionBeanUsuario getSessionBeanUsuario() {
        return (SessionBeanUsuario) getBean("comum$SessionBeanUsuario");
    }

    protected SessionBeanEmpresas getSessionBeanEmpresas() {
        return (SessionBeanEmpresas) getBean("comum$SessionBeanEmpresas");
    }

   
    //********************************************************** GLOBAL PRIVATES
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
    private HtmlInputText hitUsuario = new HtmlInputText();

    public HtmlInputText getHitUsuario() {
        return this.hitUsuario;
    }

    public void setHitUsuario(HtmlInputText hitUsuario) {
        this.hitUsuario = hitUsuario;
    }
    
    private HtmlInputSecret hisPassword = new HtmlInputSecret();

    public HtmlInputSecret getHisPassword() {
        return hisPassword;
    }

    public void setHisPassword(HtmlInputSecret hisPassword) {
        this.hisPassword = hisPassword;
    }

    private HtmlSelectOneMenu hsomEmpresa = new HtmlSelectOneMenu();

    public HtmlSelectOneMenu getHsomEmpresa() {
        return hsomEmpresa;
    }

    public void setHsomEmpresa(HtmlSelectOneMenu hsomEmpresa) {
        this.hsomEmpresa = hsomEmpresa;
    }
    private HtmlInputHidden hihProcedure = new HtmlInputHidden();

    public HtmlInputHidden getHihProcedure() {
        return hihProcedure;
    }

    public void setHihProcedure(HtmlInputHidden hihProcedure) {
        this.hihProcedure = hihProcedure;
    }
    private HtmlInputHidden hihMetodo = new HtmlInputHidden();

    public HtmlInputHidden getHihMetodo() {
        return hihMetodo;
    }

    public void setHihMetodo(HtmlInputHidden hihMetodo) {
        this.hihMetodo = hihMetodo;
    }
    private HtmlInputHidden hihErroAutenticacao = new HtmlInputHidden();

    public HtmlInputHidden getHihErroAutenticacao() {
        return hihErroAutenticacao;
    }

    public void setHihErroAutenticacao(HtmlInputHidden hihErroAutenticacao) {
        this.hihErroAutenticacao = hihErroAutenticacao;
    }

    //*********************************************************** GLOBAL METHODS
    public String hcbGravarLogErro_action() {


        Conexao conexao = new Conexao();
        try {
            if (conexao.abreConexao(this.getApplicationBeanConfiguracao().getContexto(), this.getApplicationBeanConfiguracao().getRecursoJDBC(), true)) {
                Comando comando = new Comando("stpWamiAddLogErro", conexao.getConexao());
                comando.setTipoComando(TipoComando.STORED_PROCEDURE);
                comando.adicionaParametro("pEmpresa", Types.INTEGER, this.getSessionBeanEmpresas().getCodEmpresa());
                comando.adicionaParametro("pUsuario", Types.INTEGER, this.getSessionBeanUsuario().getCodigoUsuario());
                comando.adicionaParametro("pErro", Types.VARCHAR, this.getHihErro().getValue().toString());
                comando.adicionaParametro("pModulo", Types.VARCHAR, "autenticacao");
                comando.adicionaParametro("pProcedure", Types.VARCHAR, this.getHihProcedure().getValue().toString());
                comando.adicionaParametro("pMetodo", Types.VARCHAR, this.getHihMetodo().getValue().toString());
                comando.adicionaParametro("pRegistro", Types.INTEGER, "1");


                if (comando.executa() < 1) {
                    this.getHihErro().setValue(comando.getErro());
                }
                comando = null;


            } else {
                this.getHihErro().setValue(conexao.getErro());
            }
        } catch (Exception e) {
            this.getHihErro().setValue(e.getMessage());
        } finally {
            conexao.fechaConexao();
            conexao = null;
        }
        return null;
    }

    public String hcbGravarLogAutenticacao_action() {

        Conexao conexao = new Conexao();
        try {
            if (conexao.abreConexao(this.getApplicationBeanConfiguracao().getContexto(), this.getApplicationBeanConfiguracao().getRecursoJDBC(), true)) {
                Comando comando = new Comando("stpWamiAddLogAut", conexao.getConexao());
                comando.setTipoComando(TipoComando.STORED_PROCEDURE);
                comando.adicionaParametro("pUsuario", Types.VARCHAR, this.getHitUsuario().getValue().toString());
                comando.adicionaParametro("pIp", Types.VARCHAR, this.getSessionBeanUsuario().getNumeroIp());
                comando.adicionaParametro("pEmpresa", Types.VARCHAR, this.getSessionBeanEmpresas().getEmpresaLogin());

                CachedRowSet resultado = comando.executaConsultaCache();

                if (resultado != null) {
                    resultado.next();
                    this.getHihErroAutenticacao().setValue(resultado.getString("numero_erros"));
                    resultado.close();
                }
                resultado = null;
                comando = null;

            } else {
                this.getHihErro().setValue(conexao.getErro());
                this.getHihMetodo().setValue("hcbGravarLogAutenticacao_action");
                this.getHihProcedure().setValue("stpWamiAddLogAut");
                this.hcbGravarLogErro_action();
            }
        } catch (Exception e) {
            this.getHihErro().setValue(e.getMessage());
            this.getHihMetodo().setValue("hcbGravarLogAutenticacao_action2");
            this.getHihProcedure().setValue("stpWamiAddLogAut");
            this.hcbGravarLogErro_action();
        } finally {
            conexao.fechaConexao();
            conexao = null;
        }
        return null;
    }

    public void recuperaNumeroIp() {

        HttpServletRequest request = (HttpServletRequest) super.getExternalContext().getRequest();
        this.getSessionBeanUsuario().setNumeroIp(request.getRemoteAddr());
        request = null;
    }

    public void recuperaEmpresa(int empresa) {
        Conexao conexao = new Conexao();
        try {
            if (conexao.abreConexao(this.getApplicationBeanConfiguracao().getContexto(), this.getApplicationBeanConfiguracao().getRecursoJDBC(), true)) {
                Comando comando = new Comando("stpWamiRDEmpLogin", conexao.getConexao());
                comando.setTipoComando(TipoComando.STORED_PROCEDURE);
                comando.adicionaParametro("pEmpresa", Types.INTEGER, empresa);
                CachedRowSet resultado = comando.executaConsultaCache();

                if (resultado != null) {
                    if (resultado.next()) {

                        this.getSessionBeanEmpresas().setEmpresa(resultado.getString("fantasia"));
                        this.getSessionBeanEmpresas().setEmail(resultado.getString("email"));
                        this.getSessionBeanEmpresas().setFone(resultado.getString("fone"));
                        this.getSessionBeanEmpresas().setEmpresaLogin(resultado.getString("emp_login"));
                        this.getSessionBeanEmpresas().setCidadeEmpresa(resultado.getInt("cidade"));
                        this.getSessionBeanEmpresas().setUfEmpresa(resultado.getInt("uf"));
                        this.getSessionBeanEmpresas().setEndereco(resultado.getString("endereco_format"));
                        this.getSessionBeanEmpresas().setMunicipio_Estado(resultado.getString("estado_format"));
                        this.getSessionBeanEmpresas().setCep(resultado.getString("cep"));
                        

                    } else {
                        this.getHihErro().setValue("Login Inválido!");
                        this.getHihMetodo().setValue("recuperaEmpresa");
                        this.getHihProcedure().setValue("stpWamiRDEmpLogin");
                        this.hcbGravarLogErro_action();
                    }
                    resultado.close();
                } else {
                    this.getHihErro().setValue(comando.getErro());
                    this.getHihMetodo().setValue("recuperaEmpresa1");
                    this.getHihProcedure().setValue("stpWamiRDEmpLogin");
                    this.hcbGravarLogErro_action();
                }
                resultado = null;
                comando = null;
            } else {
                this.getHihErro().setValue(conexao.getErro());
                this.getHihMetodo().setValue("recuperaEmpresa2");
                this.getHihProcedure().setValue("stpWamiRDEmpLogin");
                this.hcbGravarLogErro_action();
            }
        } catch (Exception e) {
            this.getHihErro().setValue(e.getMessage());
            this.getHihMetodo().setValue("recuperaEmpresa3");
            this.getHihProcedure().setValue("stpWamiRDEmpLogin");
            this.hcbGravarLogErro_action();
        } finally {
            conexao.fechaConexao();
            conexao = null;
        }
    }

    public String hcbEntrar_action() {

        if (this.validaSistema() == false) {
            this.getHihErro().setValue("Sistema expirado, contate a <b>Amira Web-Systems</b>!<br><br>(19) 3312-2067<br><br><b>www.amirainfo.com.br</b>");
            return null;
        }

        this.recuperaNumeroIp();
       // this.recuperaEmpresa(Integer.valueOf(this.getHsomEmpresa().getValue().toString()));
        String retorno = "/inicio.ami";

        if (!this.getHitUsuario().getValue().equals("admin")) { //Se não for usuário de administração geral
            Conexao conexao = new Conexao();
            try {
                if (conexao.abreConexao(this.getApplicationBeanConfiguracao().getContexto(), this.getApplicationBeanConfiguracao().getRecursoJDBC(), true)) {
                    Comando comando = new Comando("stpWamiValUsr", conexao.getConexao());
                    comando.setTipoComando(TipoComando.STORED_PROCEDURE);
                    comando.adicionaParametro("pNomeAcesso", Types.CHAR, this.getHitUsuario().getValue());
                    comando.adicionaParametro("pSenha", Types.CHAR, this.getHisPassword().getValue());
                    comando.adicionaParametro("pEmpresa", Types.INTEGER, this.getHsomEmpresa().getValue());
                    comando.adicionaParametro("pIp", Types.VARCHAR, this.getSessionBeanUsuario().getNumeroIp());

                    CachedRowSet resultado;
                    resultado = comando.executaConsultaCache();


                    if (resultado != null) {
                        if (resultado.next()) {
                            this.getSessionBeanUsuario().setCodigoUsuario(resultado.getInt("usuario"));
                            this.getSessionBeanUsuario().setCodigoNivelAcesso(resultado.getInt("nivel_acesso"));
                            this.getSessionBeanEmpresas().setCodEmpresa(resultado.getInt("empresa"));
                            this.getSessionBeanUsuario().setNomeUsuario(resultado.getString("nome").trim());
                            this.getSessionBeanUsuario().setConfirmarPedidosVenda(resultado.getString("conf_ped_venda"));
                            this.getSessionBeanUsuario().setAprovarPedidosVenda(resultado.getString("app_ped_venda"));
                            this.getSessionBeanUsuario().setAprovarPedidosVendaDebito(resultado.getString("app_ped_venda_deb"));
                            this.getSessionBeanUsuario().setCadastrarEntregaComprasSemPedido(resultado.getString("cad_ent_com_se_ped"));
                            this.getSessionBeanUsuario().setDepartamentoUsuario(resultado.getString("departamento"));
                            this.getSessionBeanUsuario().setAlterarCamposEspeciais(resultado.getString("alt_camp_espec"));
                            this.getSessionBeanUsuario().setPermitirAcessoExterno(resultado.getString("acesso_externo"));


                            //REVER FUNCAO DE BLOQUEIO DE ACESSO EXTERNO
                          /*  if (this.getSessionBeanUsuario().getPermitirAcessoExterno().equals("0")) {
                             this.validaAcessoExterno();
                             return null;
                             } else {
                             this.getSessionBeanSeguranca().setLogado(true);
                             this.getSessionBeanMenu().carregaMenu();
                             this.gravaLogAcesso();
                             } */

                            this.getSessionBeanSeguranca().setLogado(true);
                            this.getSessionBeanMenu().carregaMenu();
                            this.gravaLogAcesso();
                            
                            retorno = "/inicio.ami";


                           /* if (this.getSessionBeanUsuario().getCodigoNivelAcesso() == this.getApplicationBeanConfiguracao().getCodigoAdministrador() || this.getSessionBeanUsuario().getCodigoNivelAcesso() == this.getApplicationBeanConfiguracao().getCodigoDiretoria()) {
                                retorno = "inicio_administrador";
                            } else if (this.getSessionBeanUsuario().getCodigoNivelAcesso() == this.getApplicationBeanConfiguracao().getCodigoCompras()) {
                                retorno = "inicio_compras";
                            } else if (this.getSessionBeanUsuario().getCodigoNivelAcesso() == this.getApplicationBeanConfiguracao().getCodigoVendas()) {
                                retorno = "inicio_vendas";
                            } else if (this.getSessionBeanUsuario().getCodigoNivelAcesso() == this.getApplicationBeanConfiguracao().getCodigoFinanceiro()) {
                                retorno = "inicio_financeiro";
                            } */


                        } else {
                            this.hcbGravarLogAutenticacao_action();
                            int erro_autenticacao = 5 - Integer.valueOf(this.getHihErroAutenticacao().getValue().toString());

                            if (erro_autenticacao <= 0) {

                                this.getHihErro().setValue("Usuário / Senha Inválidos! Acesso Bloqueado. Peça ao administrador do sistema que libere o acesso novamente");

                            } else {

                                this.getHihErro().setValue("Usuário / Senha Inválidos! Você tem mais " + erro_autenticacao + " chances para acesso");

                            }
                            this.getSessionBeanEmpresas().setEmpresaLogin("");
                            retorno = null;
                        }
                    } else {
                        this.getHihErro().setValue(comando.getErro());
                        this.getHihMetodo().setValue("hcbEntrar_action");
                        this.getHihProcedure().setValue("stpWamiValUsr");
                        this.hcbGravarLogErro_action();
                        retorno = null;
                    }
                    resultado.close();
                    resultado = null;
                    comando = null;
                } else {
                    this.getHihErro().setValue(conexao.getErro());
                    this.getHihMetodo().setValue("hcbEntrar_action1");
                    this.getHihProcedure().setValue("stpWamiValUsr");
                    this.hcbGravarLogErro_action();
                    retorno = null;
                }
            } catch (Exception e) {
                this.getHihErro().setValue(e.getMessage());
                this.getHihMetodo().setValue("hcbEntrar_action2");
                this.getHihProcedure().setValue("stpWamiValUsr");
                this.hcbGravarLogErro_action();
                retorno = null;
            } finally {
                conexao.fechaConexao();
                conexao = null;
            }
        } else {
            if (this.getHisPassword().getValue().equals(this.getSenhaMaster())) {
                this.getSessionBeanSeguranca().setLogado(true);
                this.getSessionBeanUsuario().setCodigoUsuario(0);
                this.getSessionBeanUsuario().setCodigoNivelAcesso(0);
                this.getSessionBeanUsuario().setPermitirAcessoExterno("1");
                this.getSessionBeanEmpresas().setCodEmpresa(1);
                this.getSessionBeanEmpresas().setEmpresaLogin("MODO SUPORTE");
                this.getSessionBeanUsuario().setNomeUsuario("Administrador do Sistema");
              //  this.gravaLogAcesso();
              //  this.getSessionBeanMenu().carregaMenu();
                retorno = "/inicio.ami";
            } else {
                this.getHihErro().setValue("Usuário / Senha Inválidos!");
                retorno = null;
            }
        }
        return retorno;
    }

    public String gravaLogAcesso() {

        Conexao conexao = new Conexao();
        try {
            if (conexao.abreConexao(this.getApplicationBeanConfiguracao().getContexto(), this.getApplicationBeanConfiguracao().getRecursoJDBC(), true)) {
                Comando comando = new Comando("stpWamiAddLogAce", conexao.getConexao());
                comando.setTipoComando(TipoComando.STORED_PROCEDURE);
                comando.adicionaParametro("pEmpresa", Types.INTEGER, this.getSessionBeanEmpresas().getCodEmpresa());
                comando.adicionaParametro("pUsuario", Types.INTEGER, this.getSessionBeanUsuario().getCodigoUsuario());
                comando.adicionaParametro("pNomeAcesso", Types.VARCHAR, this.getHitUsuario().getValue().toString());
                comando.adicionaParametro("pIp", Types.VARCHAR, this.getSessionBeanUsuario().getNumeroIp());
                comando.adicionaParametro("pAcessoExterno", Types.VARCHAR, this.getSessionBeanUsuario().getPermitirAcessoExterno());


                if (comando.executa() < 1) {
                    this.getHihErro().setValue(comando.getErro());
                    this.getHihMetodo().setValue("gravaLogAcesso");
                    this.getHihProcedure().setValue("stpWamiAddLogAce");
                    this.hcbGravarLogErro_action();
                }
                comando = null;


            } else {
                this.getHihErro().setValue(conexao.getErro());
                this.getHihMetodo().setValue("gravaLogAcesso1");
                this.getHihProcedure().setValue("stpWamiAddLogAce");
                this.hcbGravarLogErro_action();
            }
        } catch (Exception e) {
            this.getHihErro().setValue(e.getMessage());
            this.getHihMetodo().setValue("gravaLogAcesso2");
            this.getHihProcedure().setValue("stpWamiAddLogAce");
            this.hcbGravarLogErro_action();
        } finally {
            conexao.fechaConexao();
            conexao = null;
        }
        return null;
    }

    public void validaAcessoExterno() {

        Conexao conexao = new Conexao();
        try {
            if (conexao.abreConexao(this.getApplicationBeanConfiguracao().getContexto(), this.getApplicationBeanConfiguracao().getRecursoJDBC(), true)) {
                Comando comando = new Comando("stpWamiValAceExt", conexao.getConexao());
                comando.setTipoComando(TipoComando.STORED_PROCEDURE);
                comando.adicionaParametro("pIp", Types.VARCHAR, this.getSessionBeanUsuario().getNumeroIp());

                CachedRowSet resultado = comando.executaConsultaCache();

                if (resultado != null) {
                    resultado.next();
                    if (resultado.getInt("status") == 0) {
                        this.getHihErro().setValue(resultado.getString("Erro"));
                        this.getHihMetodo().setValue("hcbGravar_action");
                        this.getHihProcedure().setValue("");
                        this.hcbGravarLogErro_action();
                    }

                } else {
                    this.getHihErro().setValue(comando.getErro());
                    this.getHihMetodo().setValue("hcbGravar_action1");
                    this.getHihProcedure().setValue("");
                    this.hcbGravarLogErro_action();

                }

                this.getSessionBeanSeguranca().setLogado(true);
                this.getSessionBeanMenu().carregaMenu();
                this.gravaLogAcesso();

                resultado.close();
                comando = null;

            } else {
                this.getHihErro().setValue(conexao.getErro());
                this.getHihMetodo().setValue("hcbGravar_action3");
                this.getHihProcedure().setValue("");
                this.hcbGravarLogErro_action();

            }
        } catch (Exception e) {
            this.getHihErro().setValue(e.getMessage());
            this.getHihMetodo().setValue("hcbGravar_action4");
            this.getHihProcedure().setValue("");
            this.hcbGravarLogErro_action();

        } finally {
            conexao.fechaConexao();

        }

    }

    public String getSenhaMaster() {
        return "d04m27";
    }

    private Boolean validaSistema() {

        Timestamp vencimento = Timestamp.valueOf("2030-01-30 23:59:59");

        Date hoje = new Date();

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp data_hoje_st = Timestamp.valueOf(formato.format(hoje));

        Calendar data_hoje = Calendar.getInstance();
        Calendar data_vencimento = Calendar.getInstance();

        data_hoje.setTime(data_hoje_st);
        data_vencimento.setTime(vencimento);

        vencimento = null;
        hoje = null;
        formato = null;
        data_hoje_st = null;

        if (data_hoje.getTimeInMillis() >= data_vencimento.getTimeInMillis()) {
            this.getHihErro().setValue("Sistema Expirado!");
            data_hoje = null;
            data_vencimento = null;
            System.gc();

            return false;
        }

        long diferenca = data_vencimento.getTimeInMillis() - data_hoje.getTimeInMillis();
        long diferencaDias = (diferenca / (24 * 60 * 60 * 1000)) + 1;

        if (diferencaDias < 11) {
            this.getSessionBeanUsuario().setSituacaoSistema("Falta(m) " + diferencaDias + " dia(s) para expirar o Sistema!");
        }

        data_hoje = null;
        data_vencimento = null;
        System.gc();

        return true;
    }

    //******************************************************************** COMBO
    public void carregaComboEmpresa() {

        UISelectItem item;
        this.getHsomEmpresa().getChildren().clear();

        item = new UISelectItem();
        item.setItemValue("0");
        item.setItemLabel("Selecione...");
        this.getHsomEmpresa().getChildren().add(item);
        item = null;

        CachedRowSet resultado = null;

        Conexao conexao = new Conexao();
        try {
            if (conexao.abreConexao(this.getApplicationBeanConfiguracao().getContexto(), this.getApplicationBeanConfiguracao().getRecursoJDBC(), true)) {
                Comando comando = new Comando("stpWamiEmpLogin", conexao.getConexao());
                comando.setTipoComando(TipoComando.STORED_PROCEDURE);
                resultado = comando.executaConsultaCache();

                int i = 0;
                while (resultado.next()) {
                    item = new UISelectItem();
                    item.setId("hsomEmpresa_" + i);
                    item.setItemValue(resultado.getString("empresa"));
                    item.setItemLabel(resultado.getString("emp_login"));
                    this.getHsomEmpresa().getChildren().add(item);
                    item = null;
                    i++;
                }

                if (resultado == null) {
                    this.getHihErro().setValue(comando.getErro());
                    this.getHihMetodo().setValue("carregaComboEmpresa");
                    this.getHihProcedure().setValue("stpCarComEmp");
                    this.hcbGravarLogErro_action();
                }
                comando = null;
            } else {
                this.getHihErro().setValue(conexao.getErro());
                this.getHihMetodo().setValue("carregaComboEmpresa1");
                this.getHihProcedure().setValue("stpCarComEmp");
                this.hcbGravarLogErro_action();
            }
        } catch (Exception e) {
            this.getHihErro().setValue(e.getMessage());
            this.getHihMetodo().setValue("carregaComboEmpresa2");
            this.getHihProcedure().setValue("stpCarComEmp");
            this.hcbGravarLogErro_action();
        } finally {
            conexao.fechaConexao();
            conexao = null;
        }
    }
}





