package proyectobigdata.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import jxl.Sheet;
import jxl.Workbook;
import model.DataTweet;

public class ExcelReader {
    public static final int CONTENT = 0;
    public static final int POS = 1;
    public static final int NEG = 2;
    public static final int QUERY = 3;
    private ArrayList<DataTweet> tweetsList;
    
    public ArrayList<DataTweet> getTweetsList(){
        return tweetsList;
    }
    
    public ExcelReader(){
        tweetsList = new ArrayList<>();
    }
    
    public void leerArchivoExcel(String archivoDestino, int sheet) {        
        if(tweetsList == null){
            System.err.println("Initialize array first");
            return;
        }
        try {
            Workbook archivoExcel = Workbook.getWorkbook(new File(
                    archivoDestino));          
            Sheet hoja = archivoExcel.getSheet(sheet);
            int numFilas = hoja.getRows();
            String content;
            String query;
            int positive;
            int negative;
            for (int fila = 1; fila < numFilas; fila++) { // Recorre cada                 
                content = hoja.getCell(CONTENT, fila).getContents();
                query = hoja.getCell(QUERY, fila).getContents();
                positive = Integer.parseInt(hoja.getCell(POS, fila).getContents());
                negative = Integer.parseInt(hoja.getCell(NEG, fila).getContents());
                
                tweetsList.add(new DataTweet(
                        content, 
                        (positive==1),
                        (negative==1),
                        query));
            }
            System.out.println("Length: "+tweetsList.size());
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }    
}
