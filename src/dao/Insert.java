package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.DblpElement;
import util.JdbcUtil;

public class Insert {
	public static void main(String[] args) {
		DblpDao dao= new DblpDao();
		List<DblpElement> allElements = new ArrayList<DblpElement>();
		allElements=dao.getAllElements();
		insertEntity(allElements);
	}
	public static void insertEntity(List<DblpElement> allElements) {
			try {
				int size = allElements.size();
				int authorNo = 0;
				for (int i=0;i<size;i++ ){
					DblpElement element = allElements.get(i);
					String title= element.getTitle();
					String venue =element. getType();
					String lid = Integer.toString(i+1);
					String sql1 = "INSERT INTO `Entity` (`Subject`, `Predicate`,`Object`) VALUES (?, ?, ?)";
					String sql2 = "INSERT INTO `Graph` (`Subject`, `Predicate`,`Object`) VALUES (?, ?, ?)";
					JdbcUtil.getQueryRunner().update(sql1,"P"+lid,"Type","Paper");
					JdbcUtil.getQueryRunner().update(sql1,"P"+lid,"Class","entityNode");
					JdbcUtil.getQueryRunner().update(sql1,"P"+lid,"Label",title);
					JdbcUtil.getQueryRunner().update(sql1,"V"+lid,"Type","Venue");
					JdbcUtil.getQueryRunner().update(sql1,"V"+lid,"Class","entityNode");
					JdbcUtil.getQueryRunner().update(sql1,"V"+lid,"Label",venue);
					List<String> author=element.getAuthors();
					for(String a : author){
						    authorNo++;
						    String aid = Integer.toString(authorNo);
							JdbcUtil.getQueryRunner().update(sql1,"A"+aid,"Type","Author");
							JdbcUtil.getQueryRunner().update(sql1,"A"+aid,"Class","entityNode");
							JdbcUtil.getQueryRunner().update(sql1,"A"+aid,"Label",a);
							JdbcUtil.getQueryRunner().update(sql2,"P"+lid,"E1","A"+aid);
					}
					JdbcUtil.getQueryRunner().update(sql2,"P"+lid,"E2","V"+lid);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		
	}
}

