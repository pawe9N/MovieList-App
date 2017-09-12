import java.sql.*;

public class MySQL {
	
	static Connection conn;
	
	 public static void initialization() {
		 
		 try{
			 Class.forName("com.mysql.jdbc.Driver");
			 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			 conn = DriverManager.getConnection("jdbc:mysql://localhost/movieApp?characterEncoding=UTF-8&useSSL=false","Admin","1234");
			
		 }
		 catch (SQLException ex) {
			 System.out.println("SQLException: " + ex.getMessage());
			 System.out.println("VendorError: " + ex.getErrorCode());

		 }
		 catch (ClassNotFoundException e) {
			 e.printStackTrace();
		 }
	 }	 
	 
	 public static void showMovies(){
		 try{	
			 Statement sqlState = conn.createStatement();
			 String selectStuff = "Select title, imgName, description from movies";
			 ResultSet rows = sqlState.executeQuery(selectStuff);
			 while(rows.next()){
				 System.out.println(rows.getString("title"));
				 System.out.println(rows.getString("imgName"));
				 System.out.println(rows.getString("description"));
				 System.out.println();
			 }
		 }
		 catch (SQLException ex) {
			 System.out.println("SQLException: " + ex.getMessage());
			 System.out.println("VendorError: " + ex.getErrorCode());
		 }
	 }
	 
	 public static void setMovieIntoDatabase(String title, String imgName, String description){
		 try{
			 Statement sqlState = conn.createStatement();
			 String selectStuff = "insert into movies(title, imgName, description) values('"+title+"','"+imgName+"','"+description+"')";
			 sqlState.execute(selectStuff);
		 }
		 catch (SQLException ex) {
			 System.out.println("SQLException: " + ex.getMessage());
			 System.out.println("VendorError: " + ex.getErrorCode());
		 }
		 
	 }
	 
	 public static String getSthFromMoviesDatabase(String sth, int id){
		 try{	
			 Statement sqlState = conn.createStatement();
			 String selectStuff = "Select "+ sth + " from movies where id="+id;
			 ResultSet rows = sqlState.executeQuery(selectStuff);
			 while(rows.next()){
				 return rows.getString(sth);
			 }
		 }
		 catch (SQLException ex) {
			 System.out.println("SQLException: " + ex.getMessage());
			 System.out.println("VendorError: " + ex.getErrorCode()); 
		 }
		 return "Error";
	 }
		 
} 
