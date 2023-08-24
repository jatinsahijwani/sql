import com.jatin.sahijwani.sql.dl.table.dao.*;
import com.jatin.sahijwani.sql.dl.table.dto.*;
import com.jatin.sahijwani.sql.dl.database.*;
import com.jatin.sahijwani.sql.dl.exceptions.*;
import java.util.*;
public class TableGetAllNamesTestCase
{
	public static void main(String[] args) {
		try{
		String database = args[0];
		DLDatabase.selectDatabase(database);
		Set<String> x = (new DLTableDAO()).getAllTableNames();
		x.forEach((l)->{
			System.out.println(l);
		});
		}catch(DLException dle)
		{
			System.out.println(dle.getMessage());
		}
	}
}