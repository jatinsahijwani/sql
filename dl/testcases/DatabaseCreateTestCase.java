import com.jatin.sahijwani.sql.dl.exceptions.*;
import com.jatin.sahijwani.sql.dl.database.*;
import java.io.*;
public class DatabaseCreateTestCase
{
	public static void main(String[] args) {
		String x = args[0];
		try
		{
			DLDatabase.createDatabase(x);
		}catch(DLException dle)
		{
			System.out.println(dle.getMessage());
		}
	}
}