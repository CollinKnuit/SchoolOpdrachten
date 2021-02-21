package offLoader;

import container.Container;

public abstract class ContainerOffloader extends Offloader {
	
	protected Container container;
	
	/**
	 * 
	 * @param name {@code String}
	 */
	public ContainerOffloader(String name) {
		super(name);
	}

}
