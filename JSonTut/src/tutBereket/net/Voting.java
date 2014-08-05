package tutBereket.net;

public class Voting {

	private String mStartTime;
	private String mEndTime;
	private String mText;
	private String option;
	private String mTitle;
	private String mId;
	private String creator;

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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public boolean equals(Voting v) {
		if (v.getmId() == this.mId && v.getCreator() == this.creator
				&& v.getmEndTime() == this.mEndTime
				&& v.getmText() == this.mText && v.getmTitle() == this.mTitle
				&& v.getmStartTime() == this.mStartTime
				&& v.getOption() == this.option) {
			return true;

		}
		return false;
	}

	public void merge(Voting v) {
		if (null != v.getmTitle()) {
			mTitle = v.getmTitle();
		}
		if (null != v.getmId()) {
			this.mId = v.getmId();
		}
		if (null != v.getmStartTime()) {
			this.mStartTime = v.getmStartTime();
		}
		if (null != v.getmEndTime()) {
			this.mEndTime = v.getmEndTime();
		}
		if (null != v.getmText()) {
			this.mText = v.getmText();
		}
		if (null != v.mTitle) {
			mTitle = v.mTitle;
		}
		if (null != v.mTitle) {
			mTitle = v.mTitle;
		}
		if (null != v.mTitle) {
			mTitle = v.mTitle;
		}
	
	}
}
