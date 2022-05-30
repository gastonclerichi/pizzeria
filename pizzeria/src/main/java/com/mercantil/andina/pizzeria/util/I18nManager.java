package com.mercantil.andina.pizzeria.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class I18nManager 
{
    private static I18nManager instance;
    private static final String BUNDLE_ERROR_SQL="codigosErrorSql";
    private static final String BUNDLE_MENSAJES="mensajes";
    private static final String BUNDLE_CHART="chart";
    private static final String BUNDLE_VAR_CONFIG="variablesConfiguracion";
    private static final String BUNDLE_CONF_APP="confApp";
    private static final String BUNDLE_MENSAJES_REPORTE="reportes";
    private static final String BUNDLE_MENSAJES_EMAIL="email";
    private static final String BUNDLE_MENSAJES_REST_CLIENT="restClient";

    private ResourceBundle codigosErrorSql = null;
    private ResourceBundle mensajesPantallas=null;
    private ResourceBundle chart=null;
    private ResourceBundle varConfig=null;
    private ResourceBundle confApp=null;
    private ResourceBundle mensajesReporte=null;
    private ResourceBundle mensajesEmail=null;
    private ResourceBundle restClient=null;

    public static I18nManager getInstance() 
    {
        if(instance == null) {
            instance = new I18nManager();
        }
        return instance;
    }

    private I18nManager() {

    }

    public String getCodigoErrorSql(String key)
    {
        if(codigosErrorSql == null) {
            createResourceBundleMessages();
        }

        String codigo = codigosErrorSql.getString(key);
        return codigo != null ? codigo : "";
    }

    public void createResourceBundleMessages() {
        codigosErrorSql = ResourceBundle.getBundle(BUNDLE_ERROR_SQL);
     }

    public void createMensajesResourceMessages() {
        mensajesPantallas = ResourceBundle.getBundle(BUNDLE_MENSAJES);
     }

    public void createChartResourceMessages() {
        chart = ResourceBundle.getBundle(BUNDLE_CHART);
     }

    public void createVarConfigResourceMessages() {
        varConfig = ResourceBundle.getBundle(BUNDLE_VAR_CONFIG);
     }

    public void createConfAppResourceMessages()
    {
        confApp = ResourceBundle.getBundle(BUNDLE_CONF_APP);
    }

    public void createMensajeReporteResourceMessages()
    {
        mensajesReporte = ResourceBundle.getBundle(BUNDLE_MENSAJES_REPORTE);
    }

    public void createMensajeEmailResourceMessages()
    {
        mensajesEmail = ResourceBundle.getBundle(BUNDLE_MENSAJES_EMAIL);
    }

    public void createRestClientResourceMessages() {
        restClient = ResourceBundle.getBundle(BUNDLE_MENSAJES_REST_CLIENT);
     }

    public String getMensaje(String key)
    {
        if(mensajesPantallas == null) {
            createMensajesResourceMessages();
        }

        return mensajesPantallas.getString(key);
    }

    public String getMensaje(String key,Object...args)
    {
        if(mensajesPantallas == null) {
            createMensajesResourceMessages();
        }

        return MessageFormat.format(mensajesPantallas.getString(key),args);
    }

    public String getMensajeReporte(String key,Object...args)
    {
        if(mensajesReporte == null) {
            createMensajeReporteResourceMessages();
        }

        return MessageFormat.format(mensajesReporte.getString(key),args);
    }

    public String getVariable(String key,Object...args)
    {
        if(varConfig == null) {
            createVarConfigResourceMessages();
        }

        return MessageFormat.format(varConfig.getString(key),args);
    }

    public String getConfApp(String key,Object...args)
    {
        if(confApp == null) {
            createConfAppResourceMessages();
        }

        return MessageFormat.format(confApp.getString(key),args);
    }

    public String getMensajeEmail(String key,Object...args)
    {
        if(mensajesEmail == null) {
            createMensajeEmailResourceMessages();
        }

        return MessageFormat.format(mensajesEmail.getString(key),args);
    }

    public String getPropertyLang(String key)
    {
        if(chart == null) {
            createChartResourceMessages();
        }

        return chart.getString(key);
    }

    public String getPropertyRest(String key)
    {
        if(restClient == null) {
            createRestClientResourceMessages();
        }

        return restClient.getString(key);
    }

}

