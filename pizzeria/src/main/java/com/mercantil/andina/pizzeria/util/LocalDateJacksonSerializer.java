package com.mercantil.andina.pizzeria.util;

import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocalDateJacksonSerializer extends StdSerializer<LocalDate>
{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LocalDateJacksonSerializer() {
	        super(LocalDate.class);
	    }

	    @Override
	    public void serialize(LocalDate value, JsonGenerator generator, SerializerProvider provider) throws java.io.IOException {
	        generator.writeString(value.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE));
	    }
}
