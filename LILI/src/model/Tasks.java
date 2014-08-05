package model;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Html;
/**
 * each task detail 
 * @author bereket
 *
 */
public class Tasks implements Parcelable {
	private String taskName;
	private String taskId;
	private String taskDetail;
	private Date taskStartTime=null;
	private Date taskEndTime=null;
	
	public Tasks(Parcel source) {
		super();
		this.taskName= source.readString();
		this.taskId =source.readString();
		this.taskDetail=source.readString();
	}
	public Tasks() {
		
	}
	@Override
	public String toString() {
		return "<br/><b>Task</b> : "+taskName + "<br/> "+" Start time: " + taskStartTime.toGMTString() +"<br>"+" End time: "+taskEndTime.toGMTString() +"<br/>";
	}

	public String getTaskName() {
		return taskName;
	}
	

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskDetail() {
		return taskDetail;
	}

	public void setTaskDetail(String taskDetail) {
		this.taskDetail = taskDetail;
	}

	public Date getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(Date taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public Date getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(Date taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.taskName);
		dest.writeString(this.taskId);
		dest.writeString(this.taskDetail);	
	}
	
	public static final Parcelable.Creator<Tasks> CREATOR = new Parcelable.Creator<Tasks>() {

		@Override
		public Tasks createFromParcel(Parcel source) {
			Tasks tasks= new Tasks(source);
			
			return tasks;
		}

		@Override
		public Tasks[] newArray(int size) {
			
			return new Tasks[size];
		}
	};
	

}