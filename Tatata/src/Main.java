import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user1 = new User();
		user1.name="Pestik";
		user1.passwd="123456";
		user1.minPassLength=2;
		user1.USBauth= true;
		DBworker myDB = new DBworker();
		for(User u : myDB.GetUserList()){
			System.out.println(u.name);
		}
	}
}
