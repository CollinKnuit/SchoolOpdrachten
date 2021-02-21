package offLoader;

public class PumpWagon extends PumpOffloader{

	/**
	 * 
	 * @param name {@code String}
	 * @param pumpAmount {@code int}
	 */
	public PumpWagon(String name, int pumpAmount) {
		super(name, pumpAmount);
	}
	
	@Override
	public void run() {
		var notEmpty = 0;
		System.out.println("PumpWagon " + this.getName() + " is going to start draining the ship");
		while(!exit.get()) {

			notEmpty = wharf.getFluidStorage().drainFluid(PUMPAMOUNT);
							
			System.out.println("PumpWagon " + this.getName() + " is pumping " + notEmpty + " from the wharf");
			this.sleepThread(nextSkewedBoundedDouble(1000, 6000, 0.5, -2));
			System.out.println("PumpWagon " + this.getName() + " has thrown " + notEmpty + " liters of fluid away");
			this.sleepThread(500);			
		}		
	}

}
