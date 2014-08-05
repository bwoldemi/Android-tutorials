package model;

import java.util.Vector;
/**
 * Entire Tasks that going to be download from the server  
 * @author bereket TaskContainer 
 *
 */
public class TaskContainer implements TaskInterface{
	Vector<TaskForOneCustomer> entireTasks;
	

	@Override
	public TaskForOneCustomer getCurrentTask() {
		return null;
	}

	@Override
	public void addTasks(Vector<TaskForOneCustomer> tasksForAllCustomers) {
		this.entireTasks=tasksForAllCustomers;
	}

	@Override
	public TaskForOneCustomer getCustomerTask(int customerId) {
		for(TaskForOneCustomer customerTask: entireTasks){
			if(customerId==customerTask.getCustomerId()){
				return customerTask;
			}
		}
		return null;
	}
	
}

