package Generic;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet sheetname;
	
	public ExcelReader(String path, int sIndex) throws Exception{
		File src = new File(path);
		fis = new FileInputStream(src);
		wb = new XSSFWorkbook(fis);
		sheetname = wb.getSheetAt(sIndex);	
	}
	
	public XSSFCell eReader(int row, int col){
		
		XSSFRow rows = sheetname.getRow(row);
		return rows.getCell(col);
		}
	}
	
