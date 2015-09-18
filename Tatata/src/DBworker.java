import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.naming.Context;

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
	public int SaveUser(User svUser){
		
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
}
