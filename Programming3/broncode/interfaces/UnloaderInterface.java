package interfaces;

public interface UnloaderInterface {
	
	/**
	 * Stops a thread 
	 * @return {@code boolean}
	 */
	public boolean stopThread();
	
	/**
	 * Starts a thread
	 */
	public void start();
	
	/**
	 * supend a thread indefintly
	 * @return {@code boolean}
	 */
	public boolean stopIndefintly();
}
