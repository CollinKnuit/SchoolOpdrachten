package offLoader;

public class Pump extends PumpOffloader{

	/**
	 * 
	 * @param name {@code String}
	 * @param pumpAmount {@code int}
	 */
	public Pump(String name, int pumpAmount) {
		super(name, pumpAmount);
	}

	@Override
	public void run() {
		var ship = wharf.getShip();
		var drainAmount = 0;
		System.out.println("Pump " + this.getName() + " is going to start draining the ship");
		while(!exit.get()) {
			
			if(drainAmount != 0) {
				System.out.println("Pump " + this.getName() + " is pumping excess fluid back into the ship");
				drainAmount = ship.getFluidStorage().addFluid(drainAmount);
				continue;
			}
			
			drainAmount = ship.getFluidStorage().drainFluid(PUMPAMOUNT);
				
			System.out.println("Pump " + this.getName() + " is pumping " + drainAmount + " from ship");
			this.sleepThread(nextSkewedBoundedDouble(1000, 6000, 0.5, -2));
			drainAmount = wharf.getFluidStorage().addFluid(drainAmount);
			System.out.println("Pump " + this.getName() + " has pumped " + drainAmount + " liters of fluid into the wharf");
			this.sleepThread(500);
		}	
	}
}
