package QueryApplication;

import java.sql.*;

/**
 * 
 * @author Jon Janet 6701505 IT315 6/18/2012
 * Class ResultSetTableModelCreator makes a database connection then takes the 
 * SQL query and returns a ResultSetTableModel object that can be displayed in the JTable
 */
public class ResultSetTableModelCreator
{
    Connection connection;  

    /**
     * Constructor ResultSetTableModelCreator creates a Connection to the database
     */
    public ResultSetTableModelCreator(String driverClassName, String dbname,
            String username, String password)
            throws ClassNotFoundException, SQLException
    {
        Class.forName(driverClassName);

        connection = DriverManager.getConnection(dbname, username, password);
    }// end constructor ResultSetTableModelCreator

    /**
     * 
     * method getResultSetTableModel takes a SQL query and sends it to the database. 
     * It then gets the results as a ResultSet, and returns a ResultSetTableModel object that
     * puts the results in a form that the JTable can use.
     *
     */
    public ResultSetTableModel getResultSetTableModel(String query)
            throws SQLException
    {
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(query);
        return new ResultSetTableModel(resultSet);
    }//end method ResultSetTableModel

    /**
     * method close closes the connection with the database. 
     *
     */
    public void close()
    {
        try
        {
            connection.close();
        } 
        catch (Exception e)
        {
        }     
    }// end method close
}// end class ResultSetTableModelCreator

