package QueryApplication;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import javax.swing.*;

/**
 * @author Jon Janet 
 * Class QueryApplication creates a GUI that allows the user to enter an SQL query. It
 * then obtains a ResultSetTableModel for the query and uses it to display the
 * results of the query in a JTable.
 *
 */
public class QueryApplication extends JFrame
{

    ResultSetTableModelCreator creator;   
    JTextField query;                     
    JTable table;                                                
    JButton search;
    JLabel label;
    static String driverClassName = "com.mysql.jdbc.Driver";
    static String dbname = "jdbc:mysql://localhost:3306/resident accounts";
    static String username = "root";
    static String password = "JJanetIT315";

    /**
     * Constructor QueryApplication creates a GUI with a Jtextfield to enter an SQL query
     * a "search" button and a JTable that updates when the search button is pressed.
     *
     */
    public QueryApplication(ResultSetTableModelCreator creator)
    {
        super("Query Application");  
        
        addWindowListener(new WindowAdapter()
        {

            @Override
            public void windowClosing(WindowEvent evt)
            {
                System.exit(0);
            }
        });

        this.creator = creator;       
        query = new JTextField();     
        table = new JTable();                
        search = new JButton();
        search.setText("Search");
        label = new JLabel();
        label.setText("Enter query");


        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(label);
        contentPane.add(query);
        contentPane.add(search);
        contentPane.add(new JScrollPane(table));

        search.addActionListener(new ActionListener()
        {           
            @Override
            public void actionPerformed(ActionEvent e)
            {               
                displayQueryResults(query.getText());
            }
        });
    }// end constructor QueryApplication

    /**
     * Method displayQueryResults uses the SQL query string, and the
     * ResultSetTableModelCreator object to create a TableModel of the
     * results of the database query. Then it displays the TableModel in the JTable.
     * If there is an error the error is displayed in a dialog box 
     *
     */
    public void displayQueryResults(final String query)
    {
        EventQueue.invokeLater(new Runnable()
        {

            public void run()
            {
                try
                {
                    table.setModel(creator.getResultSetTableModel(query));
                } catch (SQLException exc)
                {
                    JOptionPane.showMessageDialog(QueryApplication.this,
                            new String[]
                            {  
                                exc.getClass().getName() + ": ",
                                exc.getMessage()
                            });
                }
            }// end method run
        });
    }// end method displayQueryResults
    
    /**
     * 
     * @param args
     * @throws Exception 
     */
    public static void main(String args[]) throws Exception
    {
        ResultSetTableModelCreator creator = new ResultSetTableModelCreator
                (driverClassName, dbname, username, password);

        QueryApplication queryApp = new QueryApplication(creator);
        queryApp.setSize(600, 400);
        queryApp.setVisible(true);
    }// end method main
}// end class QueryApplication
