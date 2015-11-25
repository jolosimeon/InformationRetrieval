
package intrnlpexerciseir;
import java.sql.*;
import java.util.HashMap;

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
    
    public HashMap<String, HashMap<String,String>> getAllFiles ()
    {
    	try {
			HashMap<String, HashMap<String,String>> index = new HashMap();
			ResultSet rs;
			PreparedStatement statement;
			String query = "SELECT * FROM existence";

			statement = connect.getConnection().prepareStatement(query);
			rs = statement.executeQuery();
			while (rs.next()) 
			{
                            HashMap <String, String> documents = new HashMap();
                            String [] allFiles = rs.getString("files").split(",");
                            //System.out.println(rs.getString("word"));
                            for (int i = 0; i < allFiles.length - 1; i++)
                            {
                                
                                String [] files = allFiles[i].split("-", 2);
                                documents.put(files[0].replaceAll("\\s",""), files[1].replaceAll("\\s",""));
                            }    
                            
                            index.put(rs.getString("word"), documents);
                            //System.out.println(rs.getString("word") + "-" + documents.size());
			} 
                        return index;
			
		} catch (SQLException e) {
			System.out.println("Unable to SELECT word");
			e.printStackTrace();
		}
		
		return null;
    }
        
}