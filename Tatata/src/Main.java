import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user1 = new User();
		user1.name="Pestik";
		user1.passwd="12345";
		user1.minPassLength=3;
		user1.USBauth= true;
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		System.out.println(gson.toJson(user1));
	}
}
