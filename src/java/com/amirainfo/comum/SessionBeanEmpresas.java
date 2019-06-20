/*
 * AMIRA - WEB SYSTEMS - Todos os Direitos Reservados - 2001-2019
 * ARQUIVO: SessionBeanEmpresas.java
 * PROJETO: Amira BOOTSTRAP 2019
 * DATA CRIAÇÃO: 20/06/2019 08:00AM
 * DATA ALTERAÇÃO: 
 * CRIADO POR: Danilo Parisotto
 * VERSAO: 1.0
 */
 

package com.amirainfo.comum;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("comum$SessionBeanEmpresas")
@SessionScoped
@ManagedBean
public class SessionBeanEmpresas implements Serializable {

    public SessionBeanEmpresas(){

    }
    
    @PostConstruct
    public void inicializar() {
        
    }
    
    private int CodEmpresa;

    public int getCodEmpresa() {
        return CodEmpresa;
    }

    public void setCodEmpresa(int CodEmpresa) {
        this.CodEmpresa = CodEmpresa;
    }

    private String Cnpj;

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String Cnpj) {
        this.Cnpj = Cnpj;
    }

    private String Empresa;

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String Empresa) {
        this.Empresa = Empresa;
    }

    private String Email;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    private String Fone;

    public String getFone() {
        return Fone;
    }

    public void setFone(String Fone) {
        this.Fone = Fone;
    }

    private String EmpresaLogin;

    public String getEmpresaLogin() {
        return EmpresaLogin;
    }

    public void setEmpresaLogin(String EmpresaLogin) {
        this.EmpresaLogin = EmpresaLogin;
    }

    private int UfEmpresa;

    public int getUfEmpresa() {
        return UfEmpresa;
    }

    public void setUfEmpresa(int UfEmpresa) {
        this.UfEmpresa = UfEmpresa;
    }

    private int CidadeEmpresa;

    public int getCidadeEmpresa() {
        return CidadeEmpresa;
    }

    public void setCidadeEmpresa(int CidadeEmpresa) {
        this.CidadeEmpresa = CidadeEmpresa;
    }
    
   private String Endereco;

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }
    
   private String Municipio_Estado;

    public String getMunicipio_Estado() {
        return Municipio_Estado;
    }

    public void setMunicipio_Estado(String Municipio_Estado) {
        this.Municipio_Estado = Municipio_Estado;
    }
    
   private String Cep;

    public String getCep() {
        return Cep;
    }

    public void setCep(String Cep) {
        this.Cep = Cep;
    }

}
