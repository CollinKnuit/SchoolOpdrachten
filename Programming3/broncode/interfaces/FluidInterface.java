package interfaces;

public interface FluidInterface {

	/**
	 * A method to drain fluid 
	 * @param drainAmount {@code int}
	 * @return {@code int}
	 */
	public int drainFluid(int drainAmount);
	
	/**
	 * A method to drain fluid 
	 * @param fluidAmount {@code int}
	 * @return {@code int}
	 */
	public int addFluid(int fluidAmount);
}
