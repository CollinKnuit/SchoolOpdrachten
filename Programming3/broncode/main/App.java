package main;

import container.Container;
import container.ContainerElementC;
import container.ContainerElementW;
import model.Wharf;
import offLoader.Crane;
import offLoader.Pump;
import offLoader.PumpWagon;
import offLoader.Truck;
import ship.Ship;


public class App{
	
	public static void main(String[] args) {
		
		var wharf = new Wharf(5, 10);
		var ship = new Ship("Boaty", 100, 500);
		
		wharf.setShip(ship);
		
		wharf.addCrane(new Crane("1"));
		wharf.addCrane(new Crane("2"));
		wharf.addCrane(new Crane("3"));
		
		wharf.addTruck(new Truck("1"));
		wharf.addTruck(new Truck("2"));
		wharf.addTruck(new Truck("3"));
		
		wharf.addPump(new Pump("1", 10));
		wharf.addPump(new Pump("2", 10));
		wharf.addPump(new Pump("3", 10));
		
		wharf.addPumpWagon(new PumpWagon("1", 10));
		wharf.addPumpWagon(new PumpWagon("2", 10));
		wharf.addPumpWagon(new PumpWagon("3", 10));
	
		for(int i = 0;i < 75; i++) {
			ship.getContainerStorage().addContainer(new Container());
		}
		
		for(int i = 0;i < 15; i++) {
			ship.getContainerStorage().addContainer(new ContainerElementC());
		}
		
		for(int i = 0;i < 10; i++) {
			ship.getContainerStorage().addContainer(new ContainerElementW());
		}

		
		var portListener1 = new Thread(new Runnable() {
			@Override
			public void run() {
				
				try {
					wharf.startUnloading();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		portListener1.start();
	}
	
}
