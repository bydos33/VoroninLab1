import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DBworker {
	
	public DBworker(){ //иницилизирует каталог для хранения файлов базы данных
		try{
		File DBdir = new File("/DB");
		if(!DBdir.exists()){
			boolean created = DBdir.mkdir();
			if(created){
				System.out.println("Каталог базы данных успешно создан");
			}
		}else{
			System.out.println("Каталог Базы Данных найден");
		}
		}catch(Throwable t){
			System.out.println(t.getMessage());
		}
	}
	public int SaveUpdateUser(User svUser,int OpType){	//сохраняет или обновляет информацию о пользователе в файле username.json, за тип опреации отвечает поле OpType
		
		try{
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		System.out.println(gson.toJson(svUser));
		svUser.passwd = this.getHash(svUser.passwd);
		String userData = gson.toJson(svUser);
		String filepath = "/DB/"+ svUser.name+".json";
		
		File userFile = new File(filepath);
		if(userFile.exists()){
			if(OpType == 1){
				FileWriter writer = new FileWriter(filepath, false);
				writer.write(userData);
				writer.close();
			}
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
	public ArrayList<User> GetUserList(){
		ArrayList<User> userList = new ArrayList();
		File DbFolder = new File("/DB");
		File[] files = DbFolder.listFiles();
		for(File file : files){
			String[] extFile = file.getName().split("\\.");
			userList.add(this.GetUserData(extFile[0]));
		}
		return userList;
	}
public String getHash(String str) {
        
        MessageDigest md5 ;        
        StringBuffer  hexString = new StringBuffer();
        
        try {
                                    
            md5 = MessageDigest.getInstance("md5");
            
            md5.reset();
            md5.update(str.getBytes()); 
                        
                        
            byte messageDigest[] = md5.digest();
                        
            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
                                                                                        
        } 
        catch (NoSuchAlgorithmException e) {                        
            return e.toString();
        }
        
        return hexString.toString();
    }
}
