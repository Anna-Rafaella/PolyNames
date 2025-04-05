package database;

public class PolyNamesDatabase extends MySQLDataBase{


    public PolyNamesDatabase(String host, int port, String databaseName, String user, String password) throws Exception{
        super( host,  port, databaseName, user, password);
    }
  
}
