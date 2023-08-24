package com.jatin.sahijwani.sql.dl.data.dto;
import com.jatin.sahijwani.sql.dl.table.dao.*;
import com.jatin.sahijwani.sql.dl.table.dto.*;
import com.jatin.sahijwani.sql.dl.database.*;
public class DLDataDTO
{
	private String database;
	private DLTableDTO table;
	private Object[] data;
	public DLDataDTO()
	{
		database=null;
		table=null;
		data=null;
	}
	public void setDatabase(String database)
	{
		this.database=database;
	}
	public void setData(Object[] data)
	{
		this.data = data;
	}
	public void setTable(DLTableDTO table) 
	{
		this.table = table;
	}
	public String getDatabase()
	{
		return this.database;
	}
	public Object[] getData()
	{
		return this.data;
	}
	public DLTableDTO getTable()
	{
		return this.table;
	}
}