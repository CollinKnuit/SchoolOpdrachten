package model;

import java.util.concurrent.atomic.AtomicInteger;

import interfaces.FluidInterface;

public class FluidStorage implements FluidInterface{
	
	private AtomicInteger fluidLvl; 
	private final int FLUIDCAP; 
	
	/**
	 * 
	 * @param fluidCap
	 * @param initialFluidLvl
	 */
	public FluidStorage(int fluidCap, int initialFluidLvl) {
		this.fluidLvl = new AtomicInteger(initialFluidLvl);
		this.FLUIDCAP = fluidCap;
	}

	@Override
	public int drainFluid(int drainAmount) {
		
		synchronized (fluidLvl) {
			
			// If fluidlvl is 0 keep the thread waiting
			while(fluidLvl.get() == 0) {
				try {
					fluidLvl.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
			
			var a = fluidLvl.getAndAdd(-drainAmount) - Math.abs(drainAmount);
			
			/**
			 *  If </code a> is less then 0 set fluidlvl to 0 
			 *  notifyall thread
			 *  and return the leftover fluid 
			 *  
			 */
			if (a < 0) {
				fluidLvl.lazySet(0);
				fluidLvl.notifyAll();
				return Math.abs(a);
				
			}
			
			fluidLvl.notifyAll();
	
			return drainAmount;
		}
	}

	
	@Override
	public int addFluid(int fluidAmount) {
		synchronized (fluidLvl) {
			
			// If fluidlvl is the same as FLUIDCAP keep the thread waiting
			while(fluidLvl.get() == FLUIDCAP) {
				try {
					fluidLvl.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			var a = fluidLvl.getAndAdd(fluidAmount) + fluidAmount;
			
			/**
			 *  If </code a> is larger then FLUIDCAP set fluidlvl to
			 *  FLUIDCAP. Then notifyall threads and return the 
			 *  leftover fluid .
			 */
			if (a > FLUIDCAP) {
				fluidLvl.lazySet(FLUIDCAP);
				fluidLvl.notifyAll();
				return a - FLUIDCAP;
				
			}
			
			fluidLvl.notifyAll();
			
			return 0;
		}
	}

	public int getFluidLvl() {
		return fluidLvl.get();
	}

	public int getFLUIDCAP() {
		return FLUIDCAP;
	}

	
	
}
