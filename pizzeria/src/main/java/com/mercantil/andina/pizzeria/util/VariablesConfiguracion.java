package com.mercantil.andina.pizzeria.util;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercantil.andina.pizzeria.service.ConfigVariableService;

@Component
public  class VariablesConfiguracion 
{
	static Logger logger = LoggerFactory.getLogger(VariablesConfiguracion.class);
	 	
	private final ConfigVariableService configVarService;
	
	public static final String LOCALE_LOCAL_PAIS="locale_local_pais";
	public static final String LOCALE_LOCAL_IDIOMA="locale.local.idioma";
	public static final String APP_TIMEZONE="app.timezone";
	public static String APP_TIMEZONE_VALOR;
	
	public static final String APP_VERSION="app.version";
	public static final String APP_SESSION_TIMEOUT="app.session.timeout";	
	
	public static Locale LOCALE_APP_VALOR;
	
	@Autowired
	public VariablesConfiguracion(ConfigVariableService configVarServ) 
	{
		this.configVarService = configVarServ;
		
		try
		{
			actualizarValores(this.configVarService);
		}
		catch(Exception e)
		{
			logger.error("Faltan definidir algunas variables de configuración. Verifique la tabla config_variable");
		}
	}
	
	public static void actualizarValores(ConfigVariableService configVarService)
	{
		LOCALE_APP_VALOR=new Locale(configVarService.getVariable(I18nManager.getInstance().getVariable(VariablesConfiguracion.LOCALE_LOCAL_IDIOMA)).getValor(),
		configVarService.getVariable(I18nManager.getInstance().getVariable(VariablesConfiguracion.LOCALE_LOCAL_PAIS)).getValor());
				
		APP_TIMEZONE_VALOR=configVarService.getVariable(I18nManager.getInstance().getVariable(VariablesConfiguracion.APP_TIMEZONE)).getValor();		
	}
}
