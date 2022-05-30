package com.mercantil.andina.pizzeria.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Funciones 
{
    public static final String FORMATO_CONVERSION_FECHA_TIMEZONE="dd-MM-yyyy hh:mm:ss a";

    public static String capitalize(String texto)
    {
        if(texto!=null && !texto.trim().equals(""))
        {
            String letra1=texto.substring(0, 1).toUpperCase();
            return letra1+((texto.length()>1)?texto.substring(1).toLowerCase():"");
        }
        return texto;
    }

    public static String capitalizeFirstLetterInmutable(String texto)
    {
        if(texto!=null && !texto.trim().equals(""))
        {
            String letra1=texto.substring(0, 1).toUpperCase();
            return letra1+((texto.length()>1)?texto.substring(1):"");
        }
        return texto;
    }


    public static Date changeTimeZone(Date fecha)
    {
        TimeZone timeZone = TimeZone.getTimeZone(VariablesConfiguracion.APP_TIMEZONE_VALOR);
        SimpleDateFormat formatterWithTimeZone = new SimpleDateFormat(FORMATO_CONVERSION_FECHA_TIMEZONE);
        formatterWithTimeZone.setTimeZone(timeZone);

    String sDate = formatterWithTimeZone.format(fecha);

    SimpleDateFormat formatter = new SimpleDateFormat(FORMATO_CONVERSION_FECHA_TIMEZONE);
    try 
    {
        fecha = formatter.parse(sDate);
    } 
     catch (ParseException e) 
    {
        e.printStackTrace();
    } // string t
    return fecha;
}

public static Date changeTimeZone(Date fecha,TimeZone timeZone)
{
    SimpleDateFormat formatterWithTimeZone = new SimpleDateFormat(FORMATO_CONVERSION_FECHA_TIMEZONE);
    formatterWithTimeZone.setTimeZone(timeZone);

    String sDate = formatterWithTimeZone.format(fecha);

    SimpleDateFormat formatter = new SimpleDateFormat(FORMATO_CONVERSION_FECHA_TIMEZONE);
    try 
    {
        fecha = formatter.parse(sDate);
    } 
     catch (ParseException e) 
    {
        e.printStackTrace();
    } // string t
    return fecha;
}

public static java.sql.Date convertToSqlDateAndChangeTimeZone(Date fecha)
{
    fecha=changeTimeZone(fecha);
    java.sql.Date sqlDate=new java.sql.Date(fecha.getTime());
    return sqlDate;
}

public static java.sql.Date getSqlDateNew()
{
    Date fecha=new Date();
    fecha=changeTimeZone(fecha);
    java.sql.Date sqlDate=new java.sql.Date(fecha.getTime());
    return sqlDate;
}

public static java.util.Date getUtilDateNew()
{
    Date fecha=new Date();
    fecha=changeTimeZone(fecha);
    return fecha;
}

public static LocalDate getLocalDateNow()
{
    LocalDate localDate= LocalDate.now(ZoneId.of(VariablesConfiguracion.APP_TIMEZONE_VALOR));
    return localDate;
}

public static LocalDateTime getLocalDateTimeNow()
{
    LocalDateTime localDateTime= LocalDateTime.now(ZoneId.of(VariablesConfiguracion.APP_TIMEZONE_VALOR));
    return localDateTime;
}

public static String formatTimeAMPMAndTimezone(String horaString)
{
    String ampm = horaString.substring(9,11);
    horaString = horaString.substring(0, 8);
    LocalTime hora = null;
    
    if(ampm.equals("PM"))
        hora = LocalTime.parse(horaString).plusHours(12);
    else
        hora = LocalTime.parse(horaString);
    
    return hora.minusHours(3).toString();
}

public static Boolean isPatternYYYYMMDDValido(String cadena)
{
    Pattern pat = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
}
}

