import java.sql.*;

/**
 * Created by acer on 09-Jan-18.
 */
public class dbthief {

    static Connection connection ;
    static PreparedStatement ps,psColl,psData;
    static ResultSet rs,rsColl,rsData;
    static int numberOfDatabases = 0;
    //static String[] dbNames =new String[1000];

    public static void main(String[] args) {
        String name;
        String[] dbNames =new String[1000];
        String[] dbTables =new String[10000];

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Connected");

            connection  = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "");
            ps = connection.prepareStatement("");
            psColl = connection.prepareStatement("");
            psData = connection.prepareStatement("");

            //GET DB NAMES;
            rs=ps.executeQuery("SHOW DATABASES");

            System.out.println("*****************");
            while (rs.next()){
                //System.out.println(rs.getString(1));
                //System.out.println(i);
                try {
                    name =rs.getString(1);
                    dbNames[numberOfDatabases] = name;
                    System.out.println("Name : "+dbNames[numberOfDatabases]+", "+name);
                    numberOfDatabases++;
                }catch (Exception e2){
                    System.out.println("Error 1: "+e2.getMessage());
                }
            }
            System.out.println("*****************");
        }catch (Exception e1){
            System.out.println("Error 1: "+e1.getMessage());
        }

        int numberOfTables=0;
        int numberOfcolumns=0;
        //GET DB DATA;
        try {
            for (int i1=0;i1<=numberOfDatabases;i1++) {
                ps.executeQuery("USE " + dbNames[i1]);
                System.out.println("************\nDatabase '"+dbNames[i1]+ "' is using");
                rs = ps.executeQuery("SHOW TABLES");

                while (rs.next()) {

                    dbTables[numberOfTables] = rs.getString(1);
                    System.out.println(dbTables[numberOfTables]);

                    try {
                        //rsColl = ps.executeQuery("SELECT * FROM " + dbTables[numberOfTables]);
                        //GET HOW MANY NUMBER OF COLUMNS IN THAT SELECTED TABLE;
                        rsColl = psColl.executeQuery("show columns from "+ dbTables[numberOfTables]);
                        //get number of columns in selected table.
                        while (rsColl.next()) {
                            System.out.println("** "+rsColl.getString(1));
                            numberOfcolumns++;
                        }
                        System.out.println("number of columns :"+numberOfcolumns);

                        //GET DATA FROM TABLE;

                            rsData = psData.executeQuery("SELECT * FROM "+dbTables[numberOfTables]);
                                while (rsData.next()){
                                    for (int j=1;j<=numberOfcolumns;j++){

                                        System.out.print(rsData.getString(j)+"\t");
                                }
                                    System.out.println();
                            }

                        numberOfcolumns=0;

                    }catch (Exception e4){
                        System.out.println("Error 4 : "+e4.getMessage());
                    }

                    numberOfTables++;
                }
                System.out.println(numberOfTables +" table(s) were found in The database '"+dbNames[i1]+"'");
                numberOfTables=0;
            }
        } catch (SQLException e3) {
            System.out.println("Error 3: "+e3.getMessage());
        }

    }
}
