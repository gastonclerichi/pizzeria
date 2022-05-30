package com.mercantil.andina.pizzeria.util;

import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocalDateTimeJacksonSerializer extends StdSerializer<LocalDateTime>
{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LocalDateTimeJacksonSerializer() {
	        super(LocalDateTime.class);
	    }

	    @Override
	    public void serialize(LocalDateTime value, JsonGenerator generator, SerializerProvider provider) throws java.io.IOException {
	        generator.writeString(value.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME));
	    	
	    }
}
