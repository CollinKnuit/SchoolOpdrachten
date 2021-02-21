package interfaces;

import container.Container;

public interface ContainerInterface {

	/**
	 * A method to remove the container 
	 * @return  {@code Container}
	 */
	public Container removeContainer();
	
	/**
	 * A method to add a container
	 * @param container {@code Container}
	 * @return {@code boolean}
	 */
	public boolean addContainer(Container container);
}
