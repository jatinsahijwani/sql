package com.jatin.sahijwani.sql.dl.database;
import com.jatin.sahijwani.sql.dl.exceptions.DLException;
import java.io.*;
public class DLDatabase
{
	public static String selectedDatabase = null;
	public static void createDatabase(String title) throws DLException
	{
		if(title==null) throw new DLException("Title is required");
		title = title.trim();
		if(title.length()==0) throw new DLException("Title is required");
		if(databaseExists(title)) throw new DLException("Database already exists : " + title);
		File file = new File("/Users/jatinsahijwani/DB/" + title);
		file.mkdir();
	}
	 public static void selectDatabase(String title) throws DLException
	{
		if(title==null) throw new DLException("Title is required");
		title = title.trim();
		if(title.length()==0) throw new DLException("Title is required");
		if(databaseExists(title)==false) throw new DLException("Database does not exists : " + title);
		selectedDatabase = title;
	}
	public static void deleteDatabase(String title) throws DLException
	{
		if(title==null) throw new DLException("Title is required");
		title = title.trim();
		if(title.length()==0) throw new DLException("Title is required");
		if(databaseExists(title)==false) throw new DLException("Database does not exists : " + title);
		File file = new File("/Users/jatinsahijwani/DB/" + title);
		selectedDatabase=null;
		if(file.list()==null) file.delete();
		else
		{
			File[] tables = file.listFiles();
			for(int i=0;i<tables.length;i++) tables[i].delete();
			file.delete();
		}
	}
	public static String getSelectedDatabase()
	{
		return selectedDatabase;
	}
	public static boolean databaseExists(String title) throws DLException
	{
		File file = new File("/Users/jatinsahijwani/DB");
		String[] databaseNames = file.list();
		if(databaseNames==null) return false;
		for(int i=0;i<databaseNames.length;i++)
		{
			if(databaseNames[i].equalsIgnoreCase(title)) return true;
		}
		return false;
	}
	public static String[] getAllDatabaseNames() throws DLException
	{
		File file = new File("/Users/jatinsahijwani/DB");
		if(file.list()==null) throw new DLException("No databases found");
		return file.list();
	}
}