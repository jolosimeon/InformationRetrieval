
package intrnlpexerciseir;
import java.sql.*;

public class Database 
{
	private DBConnection connect;

    public Database() 
    {
    	connect = new DBConnection();
	connect.getConnection();
    }
    
    public String getFiles (String word)
    {
    	try {
			
			ResultSet rs;
			PreparedStatement statement;
			String query = "SELECT * FROM existence WHERE word = ?";

			statement = connect.getConnection().prepareStatement(query);
			statement.setString(1, word);
			rs = statement.executeQuery();
			if (rs.next()) 
			{
				return rs.getString("files");
			} 
			
			else
				return null;
				
		} catch (SQLException e) {
			System.out.println("Unable to SELECT word");
			e.printStackTrace();
		}
		
		return null;
    }

    public void addRow (String word, String files)
    {
    		try 
    		{
                    PreparedStatement statement;
                    String query = "INSERT INTO existence values(?,?)";
                    statement = connect.getConnection().prepareStatement(query);
                    statement.setString(1, word);
                    statement.setString(2, files);
                    statement.execute();

                } catch (SQLException e) {
                        System.out.println("Unable to INSERT new row");
                        e.printStackTrace();
                }
    }
        
}