package com.p3p20;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
 
import com.p3p20.dao.UserDao;
import com.p3p20.model.User;
import com.p3p20.util.HibernateUtil;
 
public class App {
	
	public static void addNewUser (UserDao dao, User user, String firstName, String lastName, String year, String month,String date,  String email) {
		
		 // Add new user
        user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        try {
            Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(""+year+"-"+month+"-"+date+"");
            user.setDob(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setEmail("dwp20p3@simplon.com");
        dao.addUser(user);
   
        System.out.println("User is added successfully into DB");
	}
 
    public static void main(String[] args) {
    	SessionFactory sf = HibernateUtil.getSessionFactory();
    	Session session = sf.openSession();
    	session.beginTransaction();
 
    	UserDao dao = new UserDao();
 
        // Add new user
    	User user = new User();
    	App.addNewUser (dao, user, "Athanasia","Pierre", "2018", "06", "04", "dwp20p3@simplon.com");
    	User user1 = new User();
    	App.addNewUser (dao, user1, "Pierre","Athanasia", "2018", "06", "08", "dwp20p3@simplon.co");
    	User user2 = new User();
    	App.addNewUser (dao, user2, "Pierre","PierreKlk", "2018", "08", "09", "dwp20pfccv3@simplon.com");
     
    	
    	// Update user : dwp20p3  avec le bon mail 
           user.setEmail("dwp20p3@simplon.co");
           user.setUserid(1);
           user.setFirstName("Athanasia");
           user.setLastName("Pierre");
           Date dob;
           try {
        	   dob = new SimpleDateFormat("yyyy-MM-dd").parse("2018-06-04");
        	   user.setDob(dob);
           } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
           }
          
           dao.updateUser(user);
           System.out.println("User is updated successfully");
    	
    	// Delete user by id
        dao.deleteUser(3);
    	
 
        // Get all users
        for (User iter : dao.getAllUsers()) {
        	System.out.println(iter);
        }
 
        // Get user by id
        System.out.println(dao.getUserById(2));
 
        //commit & close
        session.getTransaction().commit();
        session.save(user);
        session.close();
        
      
    }
 
}
