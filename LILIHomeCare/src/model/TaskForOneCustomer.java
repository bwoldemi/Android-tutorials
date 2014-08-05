package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
/**
 * Customer Task List 
 * @author bereket
 *
 */
public class TaskForOneCustomer implements Parcelable {
	
	private int customerId;
	private String customerName;
	private String customerAddress;
	private String careGiver;
	private Date startTimeCustomer;
	private Date endTimeCusotmer;
	private List<Tasks>tasksList= new ArrayList<Tasks>();

	public TaskForOneCustomer(Parcel source) {
		super();
		customerId = source.readInt();
		customerName= source.readString();
		customerAddress = source.readString();
		careGiver = source.readString();
		source.readTypedList(tasksList, Tasks.CREATOR);
		
	}
	
	public TaskForOneCustomer() {
	}
	public int getCustomerId() {
		return customerId;
	}
	
	public List<Tasks> getTasksList() {
		return this.tasksList;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAdress) {
		this.customerAddress = customerAdress;
	}

	public String getCareGiver() {
		return careGiver;
	}

	public void setCareGiver(String careGiver) {
		this.careGiver = careGiver;
	}


	public void addTasks(Tasks singleTask){
		tasksList.add(singleTask);
	}
	

	public Date getStartTimeCustomer() {
		return startTimeCustomer;
	}

	public void setStartTimeCustomer(Date startTimeCustomer) {
		this.startTimeCustomer = startTimeCustomer;
	}

	public Date getEndTimeCusotmer() {
		return endTimeCusotmer;
	}

	public void setEndTimeCusotmer(Date endTimeCusotmer) {
		this.endTimeCusotmer = endTimeCusotmer;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.customerId);
		dest.writeString(this.customerName);
		dest.writeString(this.customerAddress);
		dest.writeString(this.careGiver);
		dest.writeTypedList(tasksList);
	}
	public static final Parcelable.Creator<TaskForOneCustomer> CREATOR = new Creator<TaskForOneCustomer>() {
		@Override
		public TaskForOneCustomer[] newArray(int size) {
			return new TaskForOneCustomer [size];
		}
		
		@Override
		public TaskForOneCustomer createFromParcel(Parcel source) {
			TaskForOneCustomer customerTask= new TaskForOneCustomer(source);
			return customerTask;
		}
	};
	


}
