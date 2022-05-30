package com.mercantil.andina.pizzeria.security;

public final class SecurityConstants 
{
		public static final String URL_PATH = "/pizzeria";
		public static final String AUTH_LOGIN_URL = URL_PATH+"/api/authenticate";
		
		public static final String PARAM_USUARIO="username";
		public static final String PARAM_PASSWORD="password";

	    // Signing key for HS512 algorithm
	    // You can use the page http://www.allkeysgenerator.com/ to generate all kinds of keys
	  //  public static final String JWT_SECRET = "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";
		public static final String JWT_SECRET="G-KaPdSgVkYp3s6v9y$B&E)H@MbQeThWmZq4t7w!z%C*F-JaNdRfUjXn2r5u8x/A";
	    // JWT token defaults
	    public static final String TOKEN_HEADER = "Authorization";
	    public static final String TOKEN_PREFIX = "Bearer ";
	    public static final String TOKEN_TYPE = "JWT";
	    public static final String TOKEN_ISSUER = "secure-api";
	    public static final String TOKEN_AUDIENCE = "secure-app";
	    
	    public static final String TOKEN_TIME_EXPIRATION_MS="86400000"; //1 dia en milisegundos

	    private SecurityConstants() 
	    {
	        throw new IllegalStateException("No se puede crear una instancia de una clase final");
	    }
}
