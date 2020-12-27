import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jsonToJava {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
Class.forName("com.mysql.cj.jdbc.Driver");
Connection conn=null;

ArrayList<CustomerDetails> a=new ArrayList<CustomerDetails>();

conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "root", "root");

//object of statement class will help us to execute queries
Statement st=conn.createStatement();
ResultSet rs=st.executeQuery("select * from CustomerInfo where location = 'Asia' and purchasedDate=curdate();");
while(rs.next())
{
	CustomerDetails c=new CustomerDetails();
	//3 different json files, 3 diff java objects
	c.setCourseName(rs.getString(1));
	c.setPurchasedDate(rs.getString(2));
	c.setAmount(rs.getInt(3));
	c.setLocation(rs.getString(4));
	a.add(c);
	
	

}
for(int i=0;i<a.size();i++)
{
	ObjectMapper o=new ObjectMapper();
	o.writeValue(new File("C:\\Users\\rahul\\Selenium\\JsonJava\\customerInfo"+i+".json"),a.get(i));

}





conn.close();







	}

}
