package com.innovoak.util.webhelpers.data.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.JDBCType;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Column {

	String columnName();
	
	JDBCType type() default JDBCType.VARCHAR;
	
	boolean unique() default false;
	
	boolean nullable() default true;
	
	boolean primaryKey() default false;
}
