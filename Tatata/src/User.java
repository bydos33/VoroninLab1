
public class User {
	public String name;
	public String passwd;
	public int minPassLength;
	public boolean USBauth;
	
	public User(){
		
	}
	public User(String name,String passwd,int MinPassLength,boolean USBauth){
		this.name = name;
		this.passwd = passwd;
		this.minPassLength = MinPassLength;
		this.USBauth = USBauth;
	}
}
