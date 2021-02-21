package offLoader;

import container.ContainerElement;

public class Truck extends ContainerOffloader{

	/**
	 * 
	 * @param name {@code String}
	 */
	public Truck(String name) {
		super(name);
	}
	
	@Override
	public void run() {

		System.out.println("Truck "+ this.getName() + " is going to start throwing the containers away");
		
		while(!exit.get()) {
			
			container = wharf.getContainerStorage().removePriority();
			
			if(container == null ) {
				container = wharf.getContainerStorage().removeContainer();
			}
		
			System.out.println("Truck " + this.getName() + " is offloading container " + container.getContainerId() + " from the wharf");
			
			if(container instanceof ContainerElement) {
				((ContainerElement) container).attachToElement();
			}
			
			this.sleepThread(nextSkewedBoundedDouble(1000, 6000, 0.5, -2));
				
			if(container instanceof ContainerElement) {
				((ContainerElement) container).detachFromElement();
			}
			
			System.out.println("Truck " + this.getName() + " has thrown container " + container.getContainerId() +  " away");
				
			container = null;
			
			this.sleepThread(500);
		}	
			
	}	
	
}