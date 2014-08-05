package model;

import java.util.Vector;

public interface TaskInterface {
	
	public TaskForOneCustomer getCurrentTask();
	/**
	 * Add tasks for all customers 
	 * @param task
	 */
	public void addTasks(Vector<TaskForOneCustomer> task);
	public TaskForOneCustomer getCustomerTask(int customerId);
}
