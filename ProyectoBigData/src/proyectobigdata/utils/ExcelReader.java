/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobigdata.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import jxl.Sheet;
import jxl.Workbook;
import model.DataTweet;

/**
 *
 * @author Ruxaxup
 */
public class ExcelReader {
    public static final int CONTENT = 0;
    public static final int POS = 1;
    public static final int NEG = 2;
    public static final int QUERY = 3;

    private List<DataTweet> leerArchivoExcel(String archivoDestino) {
        List<DataTweet> tweetsList = new ArrayList<>();
        try {
            Workbook archivoExcel = Workbook.getWorkbook(new File(
                    archivoDestino));          
            Sheet hoja = archivoExcel.getSheet(1);
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
                
                //System.out.println(data);
            }
            System.out.println("Length: "+tweetsList.size());
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
        return tweetsList;
    }

    public static void main(String arg[]) {
        ExcelReader excel = new ExcelReader();
        excel.leerArchivoExcel("twitter_spanish.xls");
    }
}
