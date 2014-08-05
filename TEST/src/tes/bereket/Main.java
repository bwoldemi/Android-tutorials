package tes.bereket;

import java.util.UUID;
import java.util.Vector;
import java.net.HttpURLConnection;
public class Main {
	String name;
	int age;
	Vector<Option>opt;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Main(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public static void main(String[] args) {
		Vector<Main> v= new Vector<Main>();
		Main m= new Main("bereket",3);
		Main m2= new Main("bereket",3);
		
		
		v.add(m);
		v.add(m2);
		
		
		Vector<Main> v2= new Vector<Main>();
		Main m11= new Main("ter",3);
		Main m12= new Main("ter",3);
		v2.add(m11);
		v2.add(m12);
		v=v2;
		
		System.out.println(v.get(0).getName());
	}
	
	
	class Option{
		String name;
		int num;
		
		Option(String name, int num){
			this.name=name;
			this.num=num;
		}
	}
}