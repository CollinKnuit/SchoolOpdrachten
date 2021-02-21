package container;

public class ContainerElementW extends ContainerElement {

	@Override
	public synchronized void detachFromElement() {
		System.out.println("Container " + getContainerId() + " attached to furnace");
			
	}
	
	@Override
	public synchronized void attachToElement() {
		System.out.println("Container " + getContainerId() + " detached from furnace");
	
	}
	
}
