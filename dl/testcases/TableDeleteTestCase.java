import com.jatin.sahijwani.sql.dl.table.dao.*;
import com.jatin.sahijwani.sql.dl.table.dto.*;
import com.jatin.sahijwani.sql.dl.database.*;
import com.jatin.sahijwani.sql.dl.exceptions.*;
public class TableDeleteTestCase
{
	public static void main(String[] args) {
		try{
		String table = args[0];
		DLDatabase.selectDatabase(args[1]);
		(new DLTableDAO()).deleteTable(table);
		System.out.println("Done!");
		}catch(DLException dle)
		{
			System.out.println(dle.getMessage());
		}
	}
}