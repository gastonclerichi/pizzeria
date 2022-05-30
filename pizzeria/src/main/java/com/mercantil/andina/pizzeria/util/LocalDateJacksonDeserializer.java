package com.mercantil.andina.pizzeria.util;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateJacksonDeserializer extends StdDeserializer<LocalDate>
{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected LocalDateJacksonDeserializer() {
	        super(LocalDate.class);
	    }

	 @Override
	 public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
	        return LocalDate.parse(parser.readValueAs(String.class));
	 }
}
