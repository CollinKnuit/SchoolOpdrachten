package container;

import java.util.concurrent.atomic.AtomicInteger;

public class Container {
	
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);
	private int containerId;

	public Container() {
	    this.containerId = ID_GENERATOR.getAndIncrement();
	}

	public int getContainerId() {
		return containerId;
	}
	
}
