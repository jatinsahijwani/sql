package com.jatin.sahijwani.sql.dl.table.dto;
import com.jatin.sahijwani.sql.dl.exceptions.*;
import com.jatin.sahijwani.sql.dl.database.*;
import java.util.*;
public class DLTableDTO
{
	private String name;
	private String[] columnNames;
	private Class[] datatypes;
	private Integer[] widths;
	public DLTableDTO()
	{
		this.name="";
		this.columnNames=null;
		this.datatypes=null;
		this.widths=null;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public String getName()
	{
		return this.name;
	}
	public void setColumnNames(String[] columnNames)
	{
		this.columnNames=columnNames;
	}
	public void setDatatypes(Class[] datatypes)
	{	
		this.datatypes=datatypes;
	}
	public String[] getColumnNames()
	{
		return this.columnNames;
	}
	public Class[] getDatatypes()
	{
		return this.datatypes;
	}
	public void setWidths(Integer[] widths) 
	{
		this.widths=widths;
	}
	public Integer[] getWidths()
	{
		return this.widths;
	}
}