package model;

import java.util.concurrent.atomic.AtomicReferenceArray;

import container.Container;
import container.ContainerElement;
import interfaces.ContainerInterface;

public class ContainerStorage implements ContainerInterface {
	
	private AtomicReferenceArray<Container> buffer;

	/**
	 * To set the max storageSize
	 * @param storageSize
	 */
	public ContainerStorage(int storageSize) {
		this.buffer = new AtomicReferenceArray<Container>(storageSize);
	}


	@Override
	public Container removeContainer() {
		
		synchronized(buffer) {
			
			// If the array buffer is empty keep the thread waiting
			while(checkIfEmpty()) {
				try {
					buffer.wait();
				} catch (InterruptedException e) {
			
				}
			}
			
			// if the container on that index is not null return it
			for(int i = 0; i < buffer.length(); i++) {
				var container = buffer.get(i);
				if (container == null) {
				continue;
				}
			
				synchronized (container) {
					return buffer.getAndSet(i, null);
			
				}
			}
		}
		return null;					
	}
		
	/**
	 * returns the Container on that specific index
	 * @param id {@code int}
	 * @return {@code Container}
	 */
	public Container removeContainer(int id) {
		synchronized(buffer) {
			var container = buffer.getAndSet(id, null);
			return container;
			
		}
	}
	
	/**
	 * Removes the first in the list that 
	 * is a instance of ContainerElement
	 * @return {@code Container}
	 */
	public Container removePriority() {
		for(int i = 0; i < buffer.length(); i++) {
			
			if(buffer.get(i) instanceof ContainerElement) {
				synchronized (buffer) {
					return buffer.getAndSet(i, null);
					
				}
			}
		}
		return null;
	}
	
	/**
	 * If the array buffer is empty return true
	 * else false
	 * @return {@code boolean}
	 */
	public synchronized boolean checkIfEmpty() {
		for(int i = 0; i < buffer.length(); i++) {
			 if(buffer.get(i) != null) return false;
		}
		return true;
	}

	
	@Override
	public boolean addContainer(Container container) {
		synchronized (buffer) {
			
			/**
			 *  If the Container on the specific index is empty,
			 *  place the container there. then notify all treads
			 */
			for(int i = 0; i < buffer.length(); i++) {
				var emptySpot = buffer.get(i);
				
				if (emptySpot == null) {
					buffer.lazySet(i, container);	
					try {
						buffer.notifyAll();
					} catch (Exception e) {
						// TODO: handle exception
					}
					return true;	
				}
			}
		}
		return false;	
	}
	
}
