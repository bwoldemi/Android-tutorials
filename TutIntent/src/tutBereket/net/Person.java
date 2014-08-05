package tutBereket.net;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable{
	
	private String name;
	private String adress;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.name);
		dest.writeString(adress);
		dest.writeInt(age);
		
	}
	public static final Parcelable.Creator<Person> CREATOR= new Parcelable.Creator<Person>() {
		
		@Override
		public Person[] newArray(int size) {
			 return new Person[size];
		}
		
		@Override
		public Person createFromParcel(Parcel source) {
			Person person= new Person();
			person.name= source.readString();
			person.adress= source.readString();
			person.age= source.readInt();
			
			return person;
		}
	};
	
	
}
