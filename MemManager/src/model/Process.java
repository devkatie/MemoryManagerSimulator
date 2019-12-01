package model;

public class Process {
	
	private int processID;
	private int size;
	private int position;
	
	public Process(int processID, int size) {
		super();
		this.processID = processID;
		this.size = size;
	}
	public Process(Process process) {
		super();
		this.processID = process.getProcessID();
		this.size = process.getSize();
		this.position = process.getPosition();
		
		
	}
	public int getProcessID() {
		return processID;
	}
	public void setProcessID(int processID) {
		this.processID = processID;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
}
