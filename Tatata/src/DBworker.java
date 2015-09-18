import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DBworker {
	
	public DBworker(){ //������������� ������� ��� �������� ������ ���� ������
		try{
		File DBdir = new File("/DB");
		if(!DBdir.exists()){
			boolean created = DBdir.mkdir();
			if(created){
				System.out.println("������� ���� ������ ������� ������");
			}
		}else{
			System.out.println("������� ���� ������ ������");
		}
		}catch(Throwable t){
			System.out.println(t.getMessage());
		}
	}
	public int SaveUser(User svUser){	//��������� ��� ��������� ���������� � ������������ � ����� username.json
		
		try{
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		System.out.println(gson.toJson(svUser));
		String userData = gson.toJson(svUser);
		String filepath = "/DB/"+ svUser.name+".json";
		
		File userFile = new File(filepath);
		if(userFile.exists()){
			FileWriter writer = new FileWriter(filepath, false);
			writer.write(userData);
			writer.close();
			return 0;
		}
		else{
			boolean created = userFile.createNewFile();
			if(created){
				FileWriter writer = new FileWriter(filepath, false);
				writer.write(userData);
				writer.close();
				return 1;
			}else{
				return 2;
			}
		}
		}catch(IOException ex){
			System.out.println(ex.getMessage());
			return 2;
		}
	}
	public User GetUserData(String username){
		String filepath = "/DB/"+username+".json";
		JsonObject obj = new JsonObject();
		try{
			JsonParser parser = new JsonParser();
			JsonElement jsonUser = parser.parse(new FileReader(filepath));
			obj = jsonUser.getAsJsonObject();
			User retUser = new User(obj.get("name").getAsString(),obj.get("passwd").getAsString(),obj.get("minPassLength").getAsInt(),obj.get("USBauth").getAsBoolean());
			return retUser;
		}catch(FileNotFoundException e){
			System.out.println(e.getMessage());
			return null;
		}
	}
}
