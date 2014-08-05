package server.fi;

import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.TaskForOneCustomer;
import model.Tasks;

public class Parser {
	
	public static Vector<TaskForOneCustomer> parse(String content) throws JSONException{
		Vector<TaskForOneCustomer> entireTaskVector = new Vector<TaskForOneCustomer>();
		JSONArray jsonArray= new JSONArray(content);
		TaskForOneCustomer taskForOneCustomer;
		for (int i = 0; i < jsonArray.length(); i++) {
			taskForOneCustomer = new TaskForOneCustomer();
			JSONObject object= jsonArray.getJSONObject(i);
			taskForOneCustomer.setCustomerId(Integer.parseInt(object.getString("customer_id")));
			taskForOneCustomer.setCustomerName(object.getString("customer_name"));
			taskForOneCustomer.setCustomerAddress(object.getString("customer_address"));
			taskForOneCustomer.setCareGiver(object.getString("care_giver_name"));
			// JSON Array 
			System.out.println(object.getString("tasks"));
			JSONArray array = new JSONArray(object.getString("tasks"));
			System.out.println(object.getString("tasks"));
			for (int j = 0; j < array.length(); j++) {
				JSONObject taskObjects= array.getJSONObject(j);
				Tasks tasks =  new Tasks();
				tasks.setTaskId(taskObjects.getString("task_id"));
				tasks.setTaskName(taskObjects.getString("task_name"));
				tasks.setTaskDetail(taskObjects.optString("task_detail"));
				// add each tasks 
				taskForOneCustomer.addTasks(tasks);
			}
			// add data 
			entireTaskVector.add(taskForOneCustomer);
		}
		return entireTaskVector;
	}
	
	

}
