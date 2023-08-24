package com.jatin.sahijwani.sql.dl.data.dao;
import com.jatin.sahijwani.sql.dl.data.dto.*;
import com.jatin.sahijwani.sql.dl.exceptions.*;
import com.jatin.sahijwani.sql.dl.table.dao.*;
import com.jatin.sahijwani.sql.dl.table.dto.*;
import com.jatin.sahijwani.sql.dl.database.*;
import java.io.*;
public class DLDataDAO
{
	public void add(DLDataDTO dlDataDTO) throws DLException
	{
		if(dlDataDTO==null) throw new DLException("Data object required");
		String database = dlDataDTO.getDatabase().trim();
		Object[] data = dlDataDTO.getData();
		DLTableDTO table = dlDataDTO.getTable();
		String tableName = table.getName().trim();
		Integer[] tableWidths = table.getWidths();
		String[] tableColumnNames = table.getColumnNames();
		Class[] datatypes = table.getDatatypes();
		if(database==null || database.length()==0) throw new DLException("Database is required");
		if(data==null || data.length==0) throw new DLException("Data is required");
		if(table==null) throw new DLException("Table is required");
		DLTableDAO dlTableDAO = new DLTableDAO();
		if(dlTableDAO.isTableValid(table)==false) throw new DLException("Table is inappropriate");
		if(DLDatabase.databaseExists(database)==false) throw new DLException("Database does not exists");
		if(data.length != datatypes.length) throw new DLException("Irregular data provided");
		for(int i=0;i<data.length;i++)
		{
			if((data[i].getClass().getName()).equalsIgnoreCase(datatypes[i].getName())==false) throw new DLException("Data : " + data[i] + "is not of appropriate type");
		}
		if(dlTableDAO.tableExists(table.getName(),database)==false) throw new DLException("Table does not exists");
		try
		{
			File file = new File("/Users/jatinsahijwani/DB/" + database + "/" + tableName + ".data");
			if(file.exists()==false) throw new DLException("Data file for table does not exists");
			RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
			randomAccessFile.seek(randomAccessFile.length());
			for(int i=0;i<data.length;i++)
			{
				if(tableWidths[i]!=0)
				{
					String d = (String) data[i];
					int x=0;
					while(x<d.length() && x<tableWidths[i])
					{
						randomAccessFile.writeChar((int)d.charAt(x));
						x++;
					}
					randomAccessFile.writeBytes("\n");
				}
				else
				{
					randomAccessFile.writeBytes(String.valueOf(data[i]) + "\n");
				}
			}
			randomAccessFile.close();
		}catch(IOException ioe)
		{
			throw new DLException(ioe.getMessage());
		}
	}
}