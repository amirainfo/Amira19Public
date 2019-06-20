 /*
* AMIRA - WEB SYSTEMS - Todos os Direitos Reservados - 2011
* ARQUIVO: FacesBean.java
* PROJETO: Amirainfo ver 7.0
* DATA CRIACAO: 19/05/2006 10:05AM
* DATA ALTERACAO: 11/05/2011 01:57AM
* CRIADO POR: Danilo Parisotto
* VERSAO: 1.2.4
 */


package com.amirainfo.faces;

import java.util.*;
import javax.el.*;
import javax.faces.*;
import javax.faces.application.*;
import javax.faces.component.*;
import javax.faces.context.*;
import javax.faces.lifecycle.*;

public abstract class FacesBean
{
    //************************************************************** CONSTRUCTOR

    public FacesBean() {
        
    }

    //***************************************************************** PROTECTS

    protected Application getApplication() {
        return FacesContext.getCurrentInstance().getApplication();
    }

    protected Map getApplicationMap() {
        return getExternalContext().getApplicationMap();
    }

    protected ExpressionFactory getExpressionFactory() {
        return FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
    }
    
    protected ELContext getELContext() {
        return FacesContext.getCurrentInstance().getELContext();
    }

    /**
     * @deprecated Method getContext is deprecated
     */
    protected FacesContext getContext() {
        return getFacesContext();
    }

    protected ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    protected Lifecycle getLifecycle() {
        String lifecycleId = getExternalContext().getInitParameter("javax.faces.LIFECYCLE_ID");
        if(lifecycleId == null || lifecycleId.length() == 0) {
            lifecycleId = "DEFAULT";
        }
        LifecycleFactory lifecycleFactory = (LifecycleFactory)FactoryFinder.getFactory("javax.faces.lifecycle.LifecycleFactory");
        return lifecycleFactory.getLifecycle(lifecycleId);
    }

    /*protected Map getRequestMap() {
        return getExternalContext().getRequestMap();
    }

    protected Map getSessionMap() {
        return getExternalContext().getSessionMap();
    }*/

   
    protected Object getBean(String name) {
    	return getValue("#{" + name + "}");
    }

    protected void setBean(String name, Object value) {
        setValue("#{" + name + "}", value);
    }

    protected Object getValue(String expr) {
        ValueExpression ve = createValueExpression(expr, Object.class);
        return ve.getValue(getELContext());
    }

    protected void setValue(String expr, Object value) {
        ValueExpression ve = createValueExpression(expr, Object.class);
        ve.setValue(getELContext(), value);
    }

    protected ValueExpression createValueExpression(String elExpression, Class<?> elClass) {
        return getExpressionFactory().createValueExpression(getELContext(), elExpression, elClass);
    }
    
    protected MethodExpression createMethodExpression(String elMethod) {
        Class<String> []parametros = new Class[0];
        return getExpressionFactory().createMethodExpression(getELContext(), elMethod, String.class, parametros);
    }

    protected void erase() {
        erase(((UIComponent) (getFacesContext().getViewRoot())));
    }

    private void erase(UIComponent component) {
        if(component instanceof EditableValueHolder) {
            ((EditableValueHolder)component).setSubmittedValue(null);
        }
        for(Iterator kids = component.getFacetsAndChildren(); kids.hasNext(); erase((UIComponent)kids.next())) { }
    }

    protected void log(String message) {
        getExternalContext().log(message);
    }

    protected void log(String message, Throwable throwable) {
        getExternalContext().log(message, throwable);
    }

    protected void info(String summary) {
        getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
    }

    protected void info(UIComponent component, String summary) {
        getFacesContext().addMessage(component.getClientId(getFacesContext()), new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
    }

    protected void warn(String summary) {
        getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, null));
    }

    protected void warn(UIComponent component, String summary) {
        getFacesContext().addMessage(component.getClientId(getFacesContext()), new FacesMessage(FacesMessage.SEVERITY_WARN, summary, null));
    }

    protected void error(String summary) {
        getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null));
    }

    protected void error(UIComponent component, String summary) {
        getFacesContext().addMessage(component.getClientId(getFacesContext()), new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null));
    }

    protected void fatal(String summary) {
        getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, null));
    }

    protected void fatal(UIComponent component, String summary) {
        getFacesContext().addMessage(component.getClientId(getFacesContext()), new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, null));
    }

}