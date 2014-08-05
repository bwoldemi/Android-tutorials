package com.example.ex2esp;

import java.util.Date;
import java.util.UUID;

public class Voting {

	private UUID mId;
	private String mTitle;
	private String mStartTime;
	private String mEndTime;
	private String mText;
	private int mSelectedOption;
	
	
	private String creator;
	public Voting(){};
	public Voting(String mStartTime, String mEndTime, String mText,
			String option, String mTitle, UUID mId, String creator) {
		this.mStartTime = mStartTime;
		this.mEndTime = mEndTime;
		this.mText = mText;
		
		this.mTitle = mTitle;
		this.mId = mId;
		this.creator = creator;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getmStartTime() {
		return mStartTime;
	}

	public void setmStartTime(String mStartTime) {
		this.mStartTime = mStartTime;
	}

	public String getmEndTime() {
		return mEndTime;
	}

	public void setmEndTime(String mEndTime) {
		this.mEndTime = mEndTime;
	}

	public String getmText() {
		return mText;
	}

	public void setmText(String mText) {
		this.mText = mText;
	}

	

	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public UUID getmId() {
		return mId;
	}

	public void setmId(UUID mId) {
		this.mId = mId;
	}

	public boolean isOpen(){
		return false;
		
	}
	public boolean isClosed(){
		return true;
	}
	public boolean isUpComing(){
		
		return true;
	}

}
