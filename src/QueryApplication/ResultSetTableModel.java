package QueryApplication;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * @author Jon Janet 
 * Class ResultSetTableModel implements the TableModel interface so the JTable  
 * can display the ResultSet. The results are set to not be editable.
 */
public class ResultSetTableModel implements TableModel
{
    ResultSet results;            
    ResultSetMetaData metadata;    
    int numcols, numrows;         

    /**
     * Constructor ResultSetTableModel creates a TableModel from the ResultSet, 
     * which is used by the ResultSetTableModelCreator. 
     */
    ResultSetTableModel(ResultSet results) throws SQLException
    {
        this.results = results;               
        metadata = results.getMetaData();       
        numcols = metadata.getColumnCount();    
        results.last();                         
        numrows = results.getRow();             
    }// end constructor ResultSetTableModel

    /**
     * method close closes the resultSet
     */
    public void close()
    {
        try
        {
            results.getStatement().close();
        } catch (SQLException e)
        {
        };
    }// end method close

    /**
     * Method getColumnCount returns the number of columns.
     * @return 
     */
    @Override
    public int getColumnCount()
    {
        return numcols;
    }// end method getColumnCount
     /**
      * Method getRowCount returns the number of rows.
      * @return 
      */
    @Override
    public int getRowCount()
    {
        return numrows;
    }// end method getRowCount
     /**
      * Method getColumnName returns the column names from the ResultSetMetaData.
      */
    @Override
    public String getColumnName(int column)
    {
        try
        {
            return metadata.getColumnLabel(column + 1);
        } catch (SQLException e)
        {
            return e.toString();
        }
    }// end method getColumnName
    /**
     * Method getColumnClass returns the data type for each column as a string.  
     */
    @Override
    public Class getColumnClass(int column)
    {
        return String.class;
    }// end method getColumnClass

    /**
     * Method getValueAt returns the value at each cell of the table as a string
     */
    @Override
    public Object getValueAt(int row, int column)
    {
        try
        {
            results.absolute(row + 1);                
            Object object = results.getObject(column + 1); 
            if (object == null)
            {
                return null;
            } else
            {
                return object.toString();               
            }
        } catch (SQLException ex)
        {
            return ex.toString();
        }
    }// end method getValueAt

    /**
     * The following methods are used to edit the table.  
     * None of the following methods will be implemented
     * because the table is currently set to not be editable.
     */
    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }

    @Override
    public void setValueAt(Object value, int row, int column)
    {
    }

    @Override
    public void addTableModelListener(TableModelListener l)
    {
    }

    @Override
    public void removeTableModelListener(TableModelListener l)
    {
    }
}
