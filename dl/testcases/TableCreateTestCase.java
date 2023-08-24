import com.jatin.sahijwani.sql.dl.table.dao.*;
import com.jatin.sahijwani.sql.dl.table.dto.*;
import com.jatin.sahijwani.sql.dl.database.*;
import com.jatin.sahijwani.sql.dl.exceptions.*;
public class TableCreateTestCase
{
	public static void main(String[] args) {
		try{
		DLTableDTO dlTableDTO = new DLTableDTO();
		dlTableDTO.setName("10Class");
		dlTableDTO.setColumnNames(new String[]{
			"Name",
			"RollNo"
		});
		dlTableDTO.setDatatypes(new Class[]{
			String.class,
			Integer.class
		});
		dlTableDTO.setWidths(new Integer[]{
			20,
			0
		});
		DLDatabase.selectDatabase("School");
		(new DLTableDAO()).createTable(dlTableDTO);
		}catch(DLException dle)
		{
			System.out.println(dle.getMessage());
		}
	}
}	