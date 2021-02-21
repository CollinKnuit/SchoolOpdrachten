package offLoader;

import container.ContainerElement;

public class Crane extends ContainerOffloader{

	/**
	 * 
	 * @param name {@code String}
	 */
	public Crane(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		var ship = wharf.getShip();

		System.out.println("Crame " + this.getName() + " is going to start offloading the containers fromm the ship");
		boolean bool = true;
		while(!exit.get()) {
			
			var container = ship.getContainerStorage().removeContainer();
			
			if(container instanceof ContainerElement ) {
				((ContainerElement) container).detachFromElement();
			}
			
			System.out.println("Crane " + this.getName() + " is offloading container " + container.getContainerId() + " from the ship");
			
			this.sleepThread(nextSkewedBoundedDouble(1000, 6000, 0.5, -2));
			
			do {
				bool = wharf.getContainerStorage().addContainer(container);
			}
			while (!bool);
			
			System.out.println("Crane " + this.getName() + " has put container " + container.getContainerId() +  " on the wharf");
			container = null;
			this.sleepThread(500);
		}	
	}

}
