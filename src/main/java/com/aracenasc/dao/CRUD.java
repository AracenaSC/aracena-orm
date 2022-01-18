package com.aracenasc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.aracenasc.util.ColumnField;
import com.aracenasc.util.Configuration;
import com.aracenasc.util.MetaModel;
import com.aracenasc.util.PrimaryKeyField;

/**
 * Class in charge of running CRUD SQL operations on the database
 * 
 * @author AracenaSC
 *
 */
public class CRUD {
	private static Logger logger = Logger.getLogger(CRUD.class);
	Connection conn = Configuration.getConnection(Configuration.dbUrl, Configuration.dbUsername, Configuration.dbPassword);

	/*
	 * DDL OPERATIONS
	 */
	public boolean createTable(Class<?> clazz) {
		MetaModel<Class<?>> model = MetaModel.of(clazz);
		PrimaryKeyField id = model.getPrimaryKey();
		List<ColumnField> columns = model.getColumns();
		Iterator<ColumnField> itr = columns.listIterator();

		try {
			Statement s = conn.createStatement();
			// Make sure the Table doesn't exist before we create it and pass our primary key field
			String sql = String.format("CREATE TABLE IF NOT EXISTS %s (%s SERIAL PRIMARY KEY,",
					model.getTableName(), model.getPrimaryKey().getName());

			// Scan the columns list to see how many columns we'll need and append the
			// appropriate string for the field object
			for (ColumnField col : columns) {
				String unique = (col.isUnique()) ? " unique " : "";
				String nullable = (col.isNullable() ? "" : " NOT NULL ");
				String completeCol = String.format(" %s %s%s%s", col.getColumnName(), col.getDataType(), unique,
						nullable);
				itr.next();
				
				if (itr.hasNext()) {
					sql += completeCol + ",";
//					
				} else if(!itr.hasNext()){
					sql += completeCol+");";
				}

			}
			// Log the full sql command for debug purposes
			logger.info(sql);
			s.execute(sql);
			logger.info("Successfully created a table");
			return true;
		}

		catch (SQLException e) {
			logger.error("Looks like something went wrong on our end. Please try again.");
			e.printStackTrace();
		}
		return false;

	}

	/*
	 * DML OPERATIONS
	 */
//	public boolean insert(Class<?> clazz) {
//		MetaModel<Class<?>> model = MetaModel.of(clazz);
//		List<ColumnField> columns = model.getColumns();
//		Iterator<ColumnField> itr = columns.listIterator();
//		
//		try {
//			Statement s = conn.createStatement();
//			String sql = String.format("insert into %s (", null);
//			
//			
//		
//		} catch (SQLException e) {
//			logger.error("Looks like something went wrong on our end. Please try again.");
//			e.printStackTrace();
//		}
//		return false;
//	}
}