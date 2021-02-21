package offLoader;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import interfaces.UnloaderInterface;
import model.Wharf;

public abstract class Offloader extends Thread implements UnloaderInterface{
	
	protected AtomicBoolean exit = new AtomicBoolean(false);
	protected Wharf wharf;
	private final static Random RANDOM = new Random(System.currentTimeMillis());
	
	/**
	 * 
	 * @param name {@code String}
	 */
	public Offloader(String name) {
		this.setName(name);
		this.setDaemon(true);
	}
	
	@Override
	public boolean stopThread() {
		return exit.compareAndSet(false, true);
	}
	
	/**
	 * Supends thread temporarily
	 * @param sleepTime
	 */
	protected void sleepThread(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean stopIndefintly() {
		try {
			this.wait();
			return true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * based on https://stackoverflow.com/questions/5853187/skewing-java-random-number-generation-toward-a-certain-number
	 * @param min
	 * @param max
	 * @param skew
	 * @param bias
	 * @return {@code int}
	 */
    protected int nextSkewedBoundedDouble(double min, double max, double skew, double bias) {
        double range = max - min;
        double mid = min + range / 2.0;
        double unitGaussian = RANDOM.nextGaussian();
        double biasFactor = Math.exp(bias);
        double retval = mid+(range*(biasFactor/(biasFactor+Math.exp(-unitGaussian/skew))-0.5));
        return (int) retval;
    }
	
	public Wharf getWharf() {
		return wharf;
	}

	public void setWharf(Wharf wharf) {
		this.wharf = this.wharf == null ? wharf : this.wharf;
	}
	
	
}
