package com.jatin.sahijwani.sql.dl.table.dao;
import com.jatin.sahijwani.sql.dl.exceptions.*;
import com.jatin.sahijwani.sql.dl.database.*;
import com.jatin.sahijwani.sql.dl.table.dto.*;
import java.io.*;
import java.util.*;
import java.math.*;
public class DLTableDAO
{
	public void createTable(DLTableDTO dlTableDTO) throws DLException
	{
		if(dlTableDTO==null) throw new DLException("Table is required");
		String database = DLDatabase.selectedDatabase;
		if(database==null) throw new DLException("No Database selected");
		if(tableExists(dlTableDTO.getName(),database)==true) throw new DLException("Table already exists : " + dlTableDTO.getName());
		String name = dlTableDTO.getName().trim();
		if(name.length()==0) throw new DLException("Name is required to create table");
		String[] columnTitles = dlTableDTO.getColumnNames();
		Class[] datatypes = dlTableDTO.getDatatypes();
		Integer[] widths = dlTableDTO.getWidths();
		if(columnTitles==null || columnTitles.length==0)
		{
			throw new DLException("Column Titles are required");
		}
		for(int i=0;i<columnTitles.length;i++)
		{
			if(columnTitles[i]==null) throw new DLException("Column Name is null at index : " + i);
		}
		if(datatypes==null || datatypes.length==0)
		{
			throw new DLException("Datatypes are required");
		}
		for(int i=0;i<datatypes.length;i++)
		{
			if(datatypes[i]==null) throw new DLException("Datatype is null at index : " + i);
		}
		if(widths==null || widths.length==0)
		{
			throw new DLException("Widths are required");
		}
		for(int i=0;i<widths.length;i++)
		{
			if(widths[i]==null) throw new DLException("Width is null at index : " + i);
			if(widths[i]==0 && datatypes[i]==String.class) throw new DLException("Width for string field is required");
			if(widths[i]>0 && datatypes[i]!=String.class) throw new DLException("Non string field cannot have a width");
		}
		if(columnTitles.length != datatypes.length || datatypes.length!=widths.length || columnTitles.length!=widths.length)
		{
			throw new DLException("Irregular data provided to create table");
		}
		try{
		File specFile = new File("/Users/jatinsahijwani/DB/" + database + "/" + name + ".spec");
		File dataFile = new File("/Users/jatinsahijwani/DB/" + database + "/" + name + ".data");
		specFile.createNewFile();
		dataFile.createNewFile();
		RandomAccessFile specRandomAccessFile = new RandomAccessFile(specFile,"rw");
		specRandomAccessFile.writeBytes(String.valueOf(datatypes.length) + "\n");
		for(int i=0;i<columnTitles.length;i++)
		{
			specRandomAccessFile.writeBytes(columnTitles[i] + "\n");
			specRandomAccessFile.writeBytes(datatypes[i].getName() + "\n");
			specRandomAccessFile.writeBytes(String.valueOf(widths[i]) + "\n");
		}
		specRandomAccessFile.close();
		}catch(IOException ioe)
		{
			throw new DLException(ioe.getMessage());
		}
	}
	public void deleteTable(String name) throws DLException
	{
		String database = DLDatabase.selectedDatabase;
		if(database==null) throw new DLException("No Database selected");
		if(tableExists(name,database)==false) throw new DLException("No table found : " + name);
		File specFile = new File("/Users/jatinsahijwani/DB/" + database + "/" + name + ".spec");
		File dataFile = new File("/Users/jatinsahijwani/DB/" + database + "/" + name + ".data");
		specFile.delete();
		dataFile.delete();
	}
	public boolean tableExists(String table,String db) throws DLException
	{
		if(table==null) throw new DLException("Table is required");
		if(db==null) throw new DLException("Database is required");
		table = table.trim();
		db = db.trim();
		File file = new File("/Users/jatinsahijwani/DB/" + db);
		if(file.exists()==false) throw new DLException("Database does not exists : " + db);
		String[] tables = file.list();
		if(tables==null) return false;
		for(int i=0;i<tables.length;i++)
		{
			if(tables[i].equalsIgnoreCase(table + ".spec")) return true;
		}
		return false;
	}
	public Set<String> getAllTableNames() throws DLException
	{
		String database = DLDatabase.selectedDatabase;
		if(database==null) throw new DLException("Database not selected");
		File file = new File("/Users/jatinsahijwani/DB/" + database);
		if(file.exists()==false) throw new DLException("Database does not exists");
		String[] tables = file.list();
		Set<String> tableNames = new TreeSet<>();
		for(int i=0;i<tables.length;i++)
		{
			String tableName = tables[i].substring(0,tables[i].length()-5);
			if(tableNames.contains(tableName)) continue;
			else tableNames.add(tableName);
		}
		return tableNames;
	}
	public DLTableDTO getTable(String tableName) throws DLException
	{
		String database = DLDatabase.selectedDatabase;
		if(database==null) throw new DLException("Database not selected");
		try{
		File file = new File("/Users/jatinsahijwani/DB/" + database + "/" + tableName + ".spec");
		if(file.exists()==false) throw new DLException("Table does not exists" + tableName);
		RandomAccessFile specRandomAccessFile = new RandomAccessFile(file,"rw");
		int l = Integer.parseInt(specRandomAccessFile.readLine());
		String[] columnNames = new String[l];
		Integer[] widths = new Integer[l];
		Class[] datatypes = new Class[l];
		int i=0;
		while(specRandomAccessFile.getFilePointer() < specRandomAccessFile.length() && i<l)
		{
			columnNames[i]=specRandomAccessFile.readLine().trim();
			datatypes[i]=Class.forName(specRandomAccessFile.readLine().trim());
			widths[i]=Integer.parseInt(specRandomAccessFile.readLine().trim());
			i++;
		}
		DLTableDTO dlTableDTO = new DLTableDTO();
		dlTableDTO.setName(tableName);
		dlTableDTO.setDatatypes(datatypes);
		dlTableDTO.setWidths(widths);
		dlTableDTO.setColumnNames(columnNames);
		return dlTableDTO;
		}catch(IOException ioe)
		{
			throw new DLException(ioe.getMessage());
		}
		catch(ClassNotFoundException cnfe)
		{
			throw new DLException(cnfe.getMessage());	
		}
	}
	public boolean isTableValid(DLTableDTO dlTableDTO)
	{
		if(dlTableDTO==null) return false;
		String name = dlTableDTO.getName().trim();
		if(name.length()==0) return false;
		String[] columnTitles = dlTableDTO.getColumnNames();
		Class[] datatypes = dlTableDTO.getDatatypes();
		Integer[] widths = dlTableDTO.getWidths();
		if(columnTitles==null || columnTitles.length==0)
		{
			return false;
		}
		if(datatypes==null || datatypes.length==0)
		{
			return false;
		}
		if(widths==null || widths.length==0)
		{
			return false;
		}
		if(columnTitles.length != datatypes.length || datatypes.length!=widths.length || columnTitles.length!=widths.length)
		{
			return false;
		}
		for(int i=0;i<columnTitles.length;i++)
		{
			if(columnTitles[i]==null) return false;
		}
		for(int i=0;i<datatypes.length;i++)
		{
			if(datatypes[i]==null) return false;
		}
		for(int i=0;i<widths.length;i++)
		{
			if(widths[i]==null) return false;
			if(widths[i]==0 && datatypes[i]==String.class) return false;
			if(widths[i]>0 && datatypes[i]!=String.class) return false;
		}		
		return true;
	}
}