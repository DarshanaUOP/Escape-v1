package GUI;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ExecutionException;

/**
 * Created by acer on 10-Jan-18.
 */
public class dbData {

    static Connection connection;
    static PreparedStatement psDb,psDbOpen,psColl,psTable,psData;
    static ResultSet rsDb,rsColl,rstable,rsData;

    public void createConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection  = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "");
            psDb = connection.prepareStatement("");
            psColl = connection.prepareStatement("");
            psData = connection.prepareStatement("");
            psDbOpen = connection.prepareStatement("");
            //System.out.println("Connection Created");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage()+" \nError in connect to dataBase","Error",JOptionPane.PLAIN_MESSAGE);
        }
    }
    public double[] getCount(){
        int dbCount=0,tableCount=0;
        double[] count = new double[2];
        try{
            rsDb = psDb.executeQuery("SHOW DATABASES");
            while (rsDb.next()){
                rsColl = psColl.executeQuery("USE "+rsDb.getString(1));
                rsColl = psColl.executeQuery("ShOW TABLES");
                while (rsColl.next()){
                    tableCount++;
                }
                dbCount++;
            }
            count[0] = dbCount;
            count[1] = tableCount;
            //System.out.println(dbCount+" , "+tableCount);
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e.getMessage() + "\n Error in count databases","Error",JOptionPane.PLAIN_MESSAGE);
        }
    return count;
    }

}
