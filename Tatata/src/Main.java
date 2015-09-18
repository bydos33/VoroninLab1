import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user1 = new User();
		user1.name="Pestik";
		user1.passwd="123456";
		user1.minPassLength=2;
		user1.USBauth= true;
		DBworker myDB = new DBworker();
		myDB.SaveUpdateUser(user1, 1);
		User user2 = myDB.GetUserData("Pestik");
		System.out.println(user2.name + user2.passwd + user2.minPassLength + user2.USBauth);
	}
}
