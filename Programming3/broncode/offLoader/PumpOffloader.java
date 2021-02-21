package offLoader;

public abstract class PumpOffloader extends Offloader{
	
	protected final int PUMPAMOUNT;
	
	/**
	 * 
	 * @param name {@code String}
	 * @param pumpAmount {@code int}
	 */
	public PumpOffloader(String name, int pumpAmount) {
		super(name);
		this.PUMPAMOUNT = pumpAmount;
	}

}
