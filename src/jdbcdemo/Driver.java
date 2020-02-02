package jdbcdemo;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.DblpDao;
import dto.DblpElement;
public class Driver extends Jdbc_Utils{
	public static void main(String[] args) {
		
		DblpDao dao= new DblpDao();
		List<DblpElement> allElements = new ArrayList<DblpElement>();
		allElements=dao.getAllElements();
		
		
		
		try {
			
			
			//1 get the connection from the Jdbc_Utils which is extended
			Connection myConn = getConnection();
            //2 write the sql statement 
			String sql = "insert into item(id,title,authors,type,publication_date,venue,price,paused,ban,quantity,seller_id)values(?,?,?,?,?,?,25,0,0,100,1)";
			//3.create the preparedStatement
			PreparedStatement preparedStatement = myConn.prepareStatement(sql);

			
			
			
		    //get every element from the allElements_list which is parsed from xml
			int size = allElements.size();
////			
			for (int i=0;i<1000;i++ ){
				String authors=null;
				DblpElement element = allElements.get(i);
			
				int id=element.getId();

				String itemType=element.getType();

				String title= element.getTitle();

				List<String> author=element.getAuthors();

				Date year= new SimpleDateFormat("yyyy").parse(element.getYear());
				java.sql.Date year1 = new java.sql.Date(year.getTime());
				
				String venue = new String();
				switch(i%3)
				{
				
				case 0:
					venue = "BPM";
					break;
				case 1:
					venue = "VLDB";
					break;
				case 2:
					venue = "WWW";
					break;
				}
				
				for(String a : author){
					if(authors==null){
						authors=a;
					}
					else{
					authors+=","+a;}
				}
//				System.out.println(id);
//				System.out.println(itemType);
//				System.out.println(title);
//				System.out.println(authors);

				//4.set the value
				preparedStatement.setInt(1, id);
				preparedStatement.setString(2, title);
				preparedStatement.setString(3, authors);
				preparedStatement.setString(4, itemType);
				preparedStatement.setDate(5, year1);
				preparedStatement.setString(6, venue);
				preparedStatement.addBatch();
				
				//insert into database
				////////////////////////////////////////////////////////////////////////////////////////////id,sellerId,itemType,title,author,editor,year,journal,publisher,booktitle,pages,url,ee,price
			    

				
			}
			preparedStatement.executeBatch();
			System.out.println("yes");
			
			
			//3.Execute Sql query
//			ResultSet myRs = myStmt.executeQuery("select * from employees");
//			4.Process the result set
//			while(myRs.next()){
//				System.out.println(myRs.getString("Last_name")+","+myRs.getString("First_name"));
//			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
//		
	}

}
