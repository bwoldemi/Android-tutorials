package com.bereket.tutfiledownloadwith;

/**
 * Voting data type 
 * @author bereket and Samsi 
 *
 */
public class Voting {

	private String mStartTime;
	private String mEndTime;
	private String mText;
	// private Date currentTime; I think not important to here 
	private String option;
	// private int mSelectedOption; I think not important to here 
	private String mTitle;
	private String mId;
	private String creator;

	/*
	 * public boolean isUpcoming(){
	 * 
	 * currentTime = new Date();
	 * 
	 * if(currentTime.before(mStartTime));
	 * 
	 * return true;
	 * 
	 * } public boolean isOpen(){ if(currentTime.after(mStartTime));
	 * 
	 * return true;
	 * 
	 * } public boolean isClosed(){
	 * 
	 * if(currentTime.after(mEndTime));
	 * 
	 * return true;
	 * 
	 * }
	 */
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

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	
	
	/***
	 * 
	 * Nested class
	 * 
	 */
	public class Option {

		private String mText;
		private int mCount;

		public Option() {

		}

		public String getmText() {
			return mText;
		}

		public void setmText(String mText) {
			this.mText = mText;
		}

		public int getmCount() {
			return mCount;
		}

		public void setmCount(int mCount) {
			this.mCount = mCount;
		}

		public String toString() {
			return mText + mTitle;

		}

	}

}
