package com.example.testqu;

public class Voting {
	private String id;
            private String creator;
            private String title;
            private String startTime;
            private  String endimetime;
            private String text;
            private String option;
           // getOptions(jsonObj.getJSONArray("options"))
			public Voting(String id, String creator, String title,
					String startTime, String endimetime, String text,String option) {
				super();
				this.id = id;
				this.creator = creator;
				this.title = title;
				this.startTime = startTime;
				this.endimetime = endimetime;
				this.text = text;
				this.option=option;
			}
			public String getId() {
				return id;
			}
			public void setId(String id) {
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
			public String getStartTime() {
				return startTime;
			}
			public void setStartTime(String startTime) {
				this.startTime = startTime;
			}
			public String getEndimetime() {
				return endimetime;
			}
			public void setEndimetime(String endimetime) {
				this.endimetime = endimetime;
			}
			public String getText() {
				return text;
			}
			public void setText(String text) {
				this.text = text;
			}
			public String getOption() {
				return option;
			}
			public void setOption(String option) {
				this.option = option;
			}
            
            
}
