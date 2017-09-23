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
	 
	 public static String getWatchedFromTable(String table, String sth, int id){	 
		 try{	
			 Statement sqlState = conn.createStatement();
			 String selectStuff = "Select "+ sth + " from "+table+" where watched=true";
			 ResultSet rows = sqlState.executeQuery(selectStuff);
			 int i = 1;
			 while(rows.next()){
				 if(i==id){
					 if(sth == "description") return "     " + rows.getString(sth);
					 else return rows.getString(sth);
				 }
				 i++;
			 }
		 }
		 catch (SQLException ex) {
			 System.out.println("SQLException: " + ex.getMessage());
			 System.out.println("VendorError: " + ex.getErrorCode()); 
		 }
		 return "";
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
	 
	 public static int getAmountOfWatched(String table){
		 try{	
			 Statement sqlState = conn.createStatement();
			 String selectStuff = "Select count(*) as amount from " + table +" where watched=true";
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
	 
	 public static int getAmountOfWatching(){
		 try{	
			 Statement sqlState = conn.createStatement();
			 String selectStuff = "Select count(*) as amount from series where watching=true";
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
	
	 public static int getAmountOfToWatch(String table){
		 try{	
			 Statement sqlState = conn.createStatement();
			 String selectStuff = "Select count(*) as amount from " + table +" where toWatch=true";
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
