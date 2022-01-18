package com.aracenasc.util;

import java.lang.reflect.Field;

import com.aracenasc.annotations.Column;
/**
 * Class responsible for inspecting the contents of the passed column at runtime
 * @author AracenaSC
 */
public class ColumnField {
	
	private Field field;
	
	public ColumnField(Field field) {
		
		if(field.getAnnotation(Column.class) == null) {
			throw new IllegalStateException("Cannot create ColumnField object! Provided field, "
					+ getName() + "is not annotated with @Column");
		}
		this.field = field;
	}
	
	public String getName() {
		return field.getName();
	}
	
	public Class<?> getType() {
		return field.getType();
	}
	
	public String getColumnName() {
		return field.getAnnotation(Column.class).columnName();
	}
	
	public boolean isUnique() {
		return field.getAnnotation(Column.class).unique();
	}
	
	public boolean isNullable() {
		return field.getAnnotation(Column.class).nullable();
	}

	public String getDataType() {
		return field.getAnnotation(Column.class).dataType();
	}
}
