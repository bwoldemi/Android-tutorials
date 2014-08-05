package lilihomecare.fi;

public interface TaskObsorver {
	/**
	 * Task update notifier 
	 */
	public void updateData();
	/**
	 * Task completed notifier 
	 */
	public void taskCompleted();
	
	/**
	 * Task start notifier 
	 */
	public void taskStarted();
	
	/**
	 * download completed 
	 */
	public void downloadCompleted();
	
	/**
	 * error notifier 
	 */
	public void error(String text);
}
