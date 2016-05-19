/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.business;

import java.io.IOException;

/**
 *
 * @author Emil
 */
public interface ApplicationInterface {
    void saveToCSV()throws IOException;
    void deleteEvent();
    void showDataFromDataBaseTable();
    void setAlarm();
    void addToDatabase();
    void Update_table();
    void PlayAlarm();
    void showCurrentTime();
    
}
