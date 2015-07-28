/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectobigdata.controller;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import model.DataTweet;

/**
 *
 * @author Ruxaxup
 */
public class SummaryViewer {
    
    private ArrayList<DataTweet> tweetsList;

    public SummaryViewer(ArrayList<DataTweet> tweetsList) {
        this.tweetsList = tweetsList;
    }
    
    public void showPositives(JTable tPositives, JTextArea taPositives){
        DefaultTableModel dtm = (DefaultTableModel) tPositives.getModel();
        int counter = 0;
        for (DataTweet dataTweet : tweetsList) {
            if(dataTweet.isPositive()){
                dtm.addRow(new Object[]{dataTweet.getContent(),dataTweet.getQuery()});
                counter++;
            }
        }
        taPositives.append("Total tweets: "+tweetsList.size());
        taPositives.append("\nPositive tweets: "+counter);
        double percentage = (double)counter / (double)tweetsList.size() * 100;
        taPositives.append("\nPercentage: "+ percentage + "%");
    }
    
    public void showNegatives(JTable tNegatives, JTextArea taNegatives){
        DefaultTableModel dtm = (DefaultTableModel) tNegatives.getModel();
        int counter = 0;
        for (DataTweet dataTweet : tweetsList) {
            if(dataTweet.isNegative()){
                dtm.addRow(new Object[]{dataTweet.getContent(),dataTweet.getQuery()});
                counter++;
            }
        }
        taNegatives.append("Total tweets: "+tweetsList.size());
        taNegatives.append("\nNegative tweets: "+counter);
        double percentage = (double)counter / (double)tweetsList.size() *100;
        taNegatives.append("\nPercentage: "+ percentage + "%");
    }
}