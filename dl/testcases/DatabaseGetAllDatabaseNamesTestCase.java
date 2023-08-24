import com.jatin.sahijwani.sql.dl.database.*;
import com.jatin.sahijwani.sql.dl.exceptions.*;
public class DatabaseGetAllDatabaseNamesTestCase
{
	public static void main(String[] args) {
		try
		{
			String[] list = DLDatabase.getAllDatabaseNames();
			for(int i=0;i<list.length;i++)
			{
				System.out.println(list[i]);
			}
		}catch(DLException dle)
		{
			System.out.println(dle.getMessage());
		}
	}
}