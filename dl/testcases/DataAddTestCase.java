import com.jatin.sahijwani.sql.dl.exceptions.*;
import com.jatin.sahijwani.sql.dl.table.dao.*;
import com.jatin.sahijwani.sql.dl.table.dto.*;
import com.jatin.sahijwani.sql.dl.data.dto.*;
import com.jatin.sahijwani.sql.dl.data.dao.*;
import com.jatin.sahijwani.sql.dl.database.*;
public class DataAddTestCase
{
	public static void main(String[] args) {
		try{
		DLDatabase.selectDatabase("HRDB");
		DLTableDTO dlTableDTO = new DLTableDAO().getTable("employee");
		DLDataDTO dlDataDTO = new DLDataDTO();
		dlDataDTO.setTable(dlTableDTO);
		dlDataDTO.setDatabase(DLDatabase.getSelectedDatabase());
		dlDataDTO.setData(new Object[]{
			27,
			"Jatin Sahijwani",
			250000,
			true,
			"The Boss"
		});
		new DLDataDAO().add(dlDataDTO);
		}catch(DLException dle)
		{
			System.out.println(dle.getMessage());
		}
	}
}