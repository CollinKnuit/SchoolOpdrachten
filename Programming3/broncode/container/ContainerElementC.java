package container;

public class ContainerElementC extends ContainerElement{
	
	@Override
	public  synchronized void attachToElement() {
		System.out.println("Container " + getContainerId() + " attaced to refrigerator");
		
	}
	
	@Override
	public  synchronized void detachFromElement() {
		System.out.println("Container " + getContainerId() + " detaced to refrigerator");
	
	}
	
}
