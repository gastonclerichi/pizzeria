package com.mercantil.andina.pizzeria.backend;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * JPA does not know how to handle Java 8 java.time.LocalDateTime so this
 * converts LocalDateTime into format it can handle.
 */
@Converter(autoApply = true)
public class LocalDateTimeJpaConverter implements AttributeConverter<LocalDateTime, Timestamp>
{

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime dateTime)
	{
		if (dateTime == null)
		{
			return null;
		}
		return Timestamp.valueOf(dateTime);
//		Timestamp time=Timestamp.from(dateTime.toInstant(ZoneOffset.UTC));
//		return time;
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp time)
	{
		if (time == null)
		{
			return null;
		}
		
		
		//LocalDateTime dt=LocalDateTime.ofInstant(time.toInstant(), ZoneOffset.UTC);
		LocalDateTime dt=time.toLocalDateTime();
		return dt;
	}
}
