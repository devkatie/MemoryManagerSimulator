package model;

import java.util.ArrayList;

//Written By: Danny Fayaud
//MemoryList is the container that holds everything the project needs.  A MemoryList is instantiated with
//values for maximum memory size and OS size.  public addProcess methods add Processes to an ArrayList by
//calling private add methods depending on which algorithm has been selected by the user.  

public class MemoryList {

	private ArrayList<Process> list;
	private ArrayList<Process> waitingQueue;
	private int memorySize;
	private int totalMem = 0;
	private int algorithmID;
	private boolean success = false;

	public MemoryList(int memorySize, int osSize) {
		super();
		this.memorySize = memorySize;
		this.totalMem += osSize;
		this.list = new ArrayList<Process>();
		this.waitingQueue = new ArrayList<Process>();
		this.algorithmID = 0;
		list.add(new Process(0, osSize));
		list.get(0).setPosition(0);
		list.add(new Process(-1, memorySize - osSize));
		list.get(1).setPosition(1);
	}

// Primary addProcess method  	
	
	public void addProcess(int processID, int processSize, int algorithm) {
		switch (algorithm) {
		case 0:
			this.algorithmID = 0;
			addFirstFit(processID, processSize);
			break;
		case 1:
			this.algorithmID = 1;
			addBestFit(processID, processSize);
			break;
		case 2:
			this.algorithmID = 2;
			addWorstFit(processID, processSize);
			break;
		}
	}
	
// Secondary addProcess method uses polymorphism, used for creating waitingQueue

	public void addProcess(Process process, int algorithm) {
		switch (algorithm) {
		case 0:
			this.algorithmID = 0;
			addFirstFit(process.getProcessID(), process.getSize());
			break;
		case 1:
			this.algorithmID = 1;
			addBestFit(process.getProcessID(), process.getSize());
			break;
		case 2:
			this.algorithmID = 2;
			addWorstFit(process.getProcessID(), process.getSize());
			break;
		}
		patchEmptySpace();
	}

	
// removes a process from the list and replaces it with EMPTY area. 
// consolidateFreeSpace is called twice because the method may "forget" to consolidate two empty spaces 
// if a process seperating them has been deleted.
	
	public void removeProcess(int processID) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getProcessID() == processID) {
				list.get(i).setProcessID(-1);

			}
		}
		consolidateFreeSpace();
		consolidateFreeSpace();
		patchEmptySpace();
	}

	
	private void consolidateFreeSpace() {
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i).getProcessID() == list.get(i + 1).getProcessID()) {
				list.get(i).setSize(list.get(i).getSize() + list.get(i + 1).getSize());
				list.remove(i + 1);
			}
		}
		setPositions();
		patchEmptySpace();
	}

// Once a process has been added, removed, or compacted, setPositions() re-assigns all processes relative position in the queue. 	
	
	private void setPositions() {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setPosition(i);
		}
	}
	
// adds Process to biggest gap.  Local gapList is created and sorted in descending order with a lambda expression

	private void addWorstFit(int processID, int processSize) {
		ArrayList<Process> gapList = buildGapList(processSize);
		if (gapList.isEmpty()) {

			return;
		}
		if (processSize <= gapList.get(0).getSize()) {
			gapList.sort((Process a, Process b) -> b.getSize() - a.getSize());
			int position = gapList.get(0).getPosition();
			list.add(position, new Process(processID, processSize));
			list.get(position).setPosition(position);
			updateGap(position + 1, processSize);
			setPositions();

		}
	}

// adds Process to the gap closest to Process size.  A local gapList is created with sizes the difference of process size
// and gap size, then sorted with a lambda expression.

	private void addBestFit(int processID, int processSize) {
		ArrayList<Process> gapList = buildGapList(processSize);
		if (gapList.isEmpty()) {
			success = false;
			Process p = new Process(processID, processSize);
			if (!isInWaitingQueue(p)) {
				waitingQueue.add(p);
			}
			return;
		}

		for (int i = 0; i < gapList.size(); i++) {
			gapList.get(i).setSize(gapList.get(i).getSize() - processSize);
		}
			gapList.sort((Process a, Process b) -> a.getSize() - b.getSize());
			int position = gapList.get(0).getPosition();
			list.add(position, new Process(processID, processSize));
			list.get(position).setPosition(position);
			updateGap(position + 1, processSize);
			setPositions();
		}
	}
	
// Adds process to the first available gap. Same as other methods, just no sorting by size.

	private void addFirstFit(int processID, int processSize) {
		ArrayList<Process> gapList = buildGapList(processSize);
		if (gapList.isEmpty()) {
			success = false;
			Process p = new Process(processID, processSize);
			if (!isInWaitingQueue(p)) {
				waitingQueue.add(p);
			}
			return;
		}
			int position = gapList.get(0).getPosition();
			list.add(position, new Process(processID, processSize));
			list.get(position).setPosition(position);
			updateGap(position + 1, processSize);
			setPositions();
		
	}
	
// method to add EMPTY process to list with an added Process

	private void updateGap(int position, int processSize) {
		list.get(position).setPosition(position);
		list.get(position).setSize(list.get(position).getSize() - processSize);

	}

// method returns a local gap list with all gaps large enough to accomdate the process being added.	
	
	private ArrayList<Process> buildGapList(int processSize) {
		ArrayList<Process> gapList = new ArrayList<Process>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getProcessID() == -1 && list.get(i).getSize() >= processSize) {
				gapList.add(new Process(list.get(i)));
			}

		}

		return gapList;
	}

// COMPACTS!  All EMPTY spaces removed from list, except the last one.	
	
	public void compact() {
		int totalMemoryUsed = 0;
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i).getProcessID() != -1) {
				totalMemoryUsed += list.get(i).getSize();
			} else {
				list.remove(i);
			}
		}
		list.get(list.size() - 1).setSize(memorySize - totalMemoryUsed);
		setPositions();
//		addFromWaitingQueue();
		patchEmptySpace();
	}

// method to automatically add process from waiting queue.  MIGHT not be used.	

	private void addFromWaitingQueue() {
		int size = waitingQueue.size();
		for (int i = 0; i < size; i++) {
			addProcess(waitingQueue.get(i), algorithmID);
		}
		for (int i = size; i >= 0; i--) {
			if (isInMemory(waitingQueue.get(i))) {
				waitingQueue.remove(i);
			}
		}
	}

//method to check if a Process is in memory.  PROBABLY won't be used.	
	
	private boolean isInMemory(Process process) {
		for (int i = 0; i < list.size(); i++) {
			if (process.getProcessID() == list.get(i).getProcessID()) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Process> getWaitingQueue() {
		return waitingQueue;
	}

	public void setWaitingQueue(ArrayList<Process> waitingQueue) {
		this.waitingQueue = waitingQueue;
	}

	public int getAlgorithmID() {
		return algorithmID;
	}

		
	public void patchEmptySpace() {
		int total = 0;
		for (int i = 0; i < list.size() - 1; i++) {
			total += list.get(i).getSize();
		}
		list.get(list.size() - 1).setSize(memorySize - total);
	}

	public void setAlgorithmID(int algorithmID) {
		this.algorithmID = algorithmID;
	}

	public ArrayList<Process> getList() {
		return list;
	}

	public void setList(ArrayList<Process> list) {
		this.list = list;
	}

	public int getMemorySize() {
		return memorySize;
	}

	public void setMemorySize(int memorySize) {
		this.memorySize = memorySize;
	}
	
	private boolean isInWaitingQueue(Process p) {
		for (int i = 0; i < waitingQueue.size(); i++) {
			if (p.getProcessID() == waitingQueue.get(i).getProcessID()) {
				return true;
			}
		}
		return false;
	}

}
