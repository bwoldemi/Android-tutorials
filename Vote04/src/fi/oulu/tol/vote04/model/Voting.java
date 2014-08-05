package fi.oulu.tol.vote04.model;

import java.util.Date;
import java.util.Vector;

public class Voting {

	private int id;
	private String creator;
	private String title;
	private Date startTime;
	private Date endTime;
	private String text;
	private Vector<Option> option;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Vector<Option> getOption() {
		return option;
	}

	public void setOption(Vector<Option> option) {
		this.option = option;
	}
}
