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
			 String selectStuff = "Select title, imgName, description, premiere, country, rating, runtime from movies";
			 ResultSet rows = sqlState.executeQuery(selectStuff);
			 while(rows.next()){
				 System.out.println(rows.getString("title"));
				 System.out.println(rows.getString("imgName"));
				 System.out.println(rows.getString("description"));
				 System.out.println(rows.getString("premiere"));
				 System.out.println(rows.getString("country"));
				 System.out.println(rows.getString("rating"));
				 System.out.println(rows.getString("runtime"));
				 System.out.println();
			 }
		 }
		 catch (SQLException ex) {
			 System.out.println("SQLException: " + ex.getMessage());
			 System.out.println("VendorError: " + ex.getErrorCode());
		 }
	 }
	 
	 public static void setMovieIntoDatabase(String title, String imgName, String description, String premiere, String country, String rating, String runtime){
		 try{
			 Statement sqlState = conn.createStatement();
			 String selectStuff = "insert into movies(title, imgName, description, premiere, country, rating, runtime) values('"+title+"','"+imgName+"','"+description+"',"+premiere+"','"+country+"','"+rating+"','"+runtime+")";
			 sqlState.execute(selectStuff);
		 }
		 catch (SQLException ex) {
			 System.out.println("SQLException: " + ex.getMessage());
			 System.out.println("VendorError: " + ex.getErrorCode());
		 }
		 
	 }
	 
	 public static String getStringFromTable(String table, String sth, int id){	 
		 try{	
			 Statement sqlState = conn.createStatement();
			 String selectStuff = "Select "+ sth + " from "+table+" where id="+id;
			 ResultSet rows = sqlState.executeQuery(selectStuff);
			 while(rows.next()){
				 if(sth == "description") return "     " + rows.getString(sth);
				 else return rows.getString(sth);
			 }
		 }
		 catch (SQLException ex) {
			 System.out.println("SQLException: " + ex.getMessage());
			 System.out.println("VendorError: " + ex.getErrorCode()); 
		 }
		 return "Error";
	 }
	 
	 public static int getIntFromTable(String table, String sth, int id){	 
		 try{	
			 Statement sqlState = conn.createStatement();
			 String selectStuff = "Select "+ sth + " from "+ table +" where id="+id;
			 ResultSet rows = sqlState.executeQuery(selectStuff);
			 while(rows.next()){
				 return rows.getInt(sth);
			 }
		 }
		 catch (SQLException ex) {
			 System.out.println("SQLException: " + ex.getMessage());
			 System.out.println("VendorError: " + ex.getErrorCode()); 
		 }
		 return -1;
	 }
	 
	 public static int getAmountOfRows(String table){
		 try{	
			 Statement sqlState = conn.createStatement();
			 String selectStuff = "Select count(*) as amount from " + table;
			 ResultSet rows = sqlState.executeQuery(selectStuff);
			 while(rows.next()){
				 return rows.getInt("amount");
			 }
		 }
		 catch (SQLException ex) {
			 System.out.println("SQLException: " + ex.getMessage());
			 System.out.println("VendorError: " + ex.getErrorCode()); 
		 }
		 return -1;
	 }
		 
} 
