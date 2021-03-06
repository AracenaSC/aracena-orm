package com.aracenasc.util;

import java.lang.reflect.Field;

import com.aracenasc.annotations.Id;
/**
 * Class responsible for inspecting the contents of the passed column at runtime
 * @author AracenaSC
 */
public class PrimaryKeyField {
	
	private Field field; // from java.lang.reflect
	
	public PrimaryKeyField(Field field) {
		
		if(field.getAnnotation(Id.class) == null) {
			throw new IllegalStateException("Cannot create ColumnField object! Provided field, "
					+ getName() + "is not annotated with @Id");
		}
		this.field = field;
	}
	
	public String getName() {
		return field.getName();
	}
	
	// return the TYPE of the field that's annotated
	public Class<?> getType() {
		return field.getType();
	}
	
	// get columnName() -=- extract the column name attribute from the column annotation
	public String getColumnName() {
		return field.getAnnotation(Id.class).columnName();
	}

}
