import com.jatin.sahijwani.sql.dl.exceptions.*;
import com.jatin.sahijwani.sql.dl.database.*;
import java.io.*;
public class DatabaseSelectDatabaseTestCase
{
	public static void main(String[] args) {
		String x = args[0];
		try
		{
			DLDatabase.selectDatabase(x);
			System.out.println("Database selected : " + x);
		}catch(DLException dle)
		{
			System.out.println(dle.getMessage());
		}
	}
}