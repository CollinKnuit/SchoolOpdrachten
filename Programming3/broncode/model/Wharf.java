package model;

import java.util.ArrayList;

import interfaces.UnloaderInterface;
import offLoader.Crane;
import offLoader.Pump;
import offLoader.PumpWagon;
import offLoader.Truck;
import ship.Ship;

public class Wharf  {

	private Ship ship;
	private FluidStorage fluidStorage;
	private ContainerStorage containerStorage;
	private ArrayList<UnloaderInterface> crane = new ArrayList<UnloaderInterface>();
	private ArrayList<UnloaderInterface> pump = new ArrayList<UnloaderInterface>();
	private ArrayList<UnloaderInterface> pumpWagon = new ArrayList<UnloaderInterface>();
	private ArrayList<UnloaderInterface> truck = new ArrayList<UnloaderInterface>();

	/**
	 * 
	 * @param fluidCap
	 * @param storageSize
	 */
	public Wharf(int fluidTankSize, int storageSize) {
		this.fluidStorage = new FluidStorage(fluidTankSize, 0);
		this.containerStorage = new ContainerStorage(storageSize);
	}

	public synchronized void startUnloading() throws InterruptedException {
		if(ship == null) return;
		
		// start processes depending on the shipType
		switch(ship.getShipType()) {
		  case ContainerShip:
			  start(crane);
			  this.wait(6000);
			  start(truck);
			  break;
		  case FluidShip:
			  start(pump);
			  this.wait(6000);
			  start(pumpWagon);
			  break;
		  case HybridShip:
			  start(crane);
			  start(pump);
			  this.wait(6000);
			  start(pumpWagon);
			  start(truck);
			break;
		}
		
		// Dirty way of closing all threads
		while(true) {
			if(ship.getContainerStorage().checkIfEmpty() && 
					containerStorage.checkIfEmpty() && 
					ship.getFluidStorage().getFluidLvl() == 0 && 
					fluidStorage.getFluidLvl() == 0) {
				this.wait(6000);
				System.out.println("Wharf has completed unloading " + ship.getName());
				break;
			}
		
			this.wait(6000);
		
		}
	}
	
	/**
	 * Stop all runing threads
	 */
	public void stopUnloading() {
		if(ship == null) return;
		switch(ship.getShipType()) {
		  case ContainerShip:
			  stop(crane);
			  stop(truck);
			  break;
		  case FluidShip:
			  stop(pump);
			  stop(pumpWagon);
			  break;
		  case HybridShip:
			  stop(crane);
			  stop(pump);
			  stop(pumpWagon);
			  stop(truck);
			break;
		}
	}
	
	/**
	 * Start all threads in the array
	 * @param unloader {@code classes that implements UnloaderInterface}
	 */
	private void start(ArrayList<UnloaderInterface> unloader) {
		for(var a : unloader) {
			a.start();
		}
	}
	
	/**
	 * Stop all threads in the array
	 * @param unloader {@code classes that implements UnloaderInterface}
	 */
	private void stop(ArrayList<UnloaderInterface> unloader) {
		for(var a : unloader) {
			a.stopThread();
		}
	}
	
	public void addCrane(Crane crane) {
		crane.setWharf(this);
		this.crane.add(crane);
	}
	
	public void addPump(Pump pump) {
		pump.setWharf(this);
		this.pump.add(pump);
	}
	
	public void addPumpWagon(PumpWagon pumpWagon) {
		pumpWagon.setWharf(this);
		this.pumpWagon.add(pumpWagon);
	}
	
	public void addTruck(Truck truck) {
		truck.setWharf(this);
		this.truck.add(truck);
	}
	
	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public FluidStorage getFluidStorage() {
		return fluidStorage;
	}

	public ContainerStorage getContainerStorage() {
		return containerStorage;
	}

}
