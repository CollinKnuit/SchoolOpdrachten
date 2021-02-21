package ship;


import model.ContainerStorage;
import model.FluidStorage;

public class Ship {
	
	private String name;
	private ContainerStorage containerStorage;
	private FluidStorage fluidStorage;
	private ShipType shipType;
	
	/**
	 * Depending on the the 2 integres set the shipType
	 * @param name {@code String}
	 * @param storageSize {@code int}
	 * @param fluidCap {@code int}
	 */
	public Ship(String name, int storageSize, int fluidCap) {
		
		this.name = name;
		
		if(storageSize != 0 && fluidCap != 0)
			this.shipType = ShipType.HybridShip;
		
		else if(storageSize != 0) {
			this.shipType = ShipType.ContainerShip;
			
		}
		else {
			this.shipType = ShipType.FluidShip;
		}
		
		this.containerStorage = new ContainerStorage(storageSize);
		this.fluidStorage = new FluidStorage(fluidCap, fluidCap);
	}
	

	public ShipType getShipType() {
		return shipType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ContainerStorage getContainerStorage() {
		return containerStorage;
	}

	public FluidStorage getFluidStorage() {
		return fluidStorage;
	}
	
}
