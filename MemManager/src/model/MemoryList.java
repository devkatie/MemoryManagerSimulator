package model;

import java.util.ArrayList;

public class MemoryList {

	private ArrayList<Process> list;
	private int memorySize;

	public MemoryList(int memorySize, int osSize) {
		super();
		this.memorySize = memorySize;
		this.list = new ArrayList<Process>();
		list.add(new Process(0, osSize));
		list.add(new Process(-1, memorySize - osSize));
	}

	public void addProcess(int processID, int processSize, int algorithm) {
		switch (algorithm) {
		case 0:
			addFirstFit(processID, processSize);
			break;
		case 1:
			addBestFit(processID, processSize);
			break;
		case 2:
			addWorstFit(processID, processSize);
			break;
		}
	}
	
	public void removeProcess(int processID) {
		for(int i = 0; i< list.size(); i++) {
			if(list.get(i).getProcessID() == processID) {
				list.get(i).setProcessID(-1);
				consolidateFreeSpace(i);
			}
		}
	}

	private void consolidateFreeSpace(int index) {
		
		
	}

	public void addWorstFit(int processID, int processSize) {
		ArrayList<Process> gapList = buildGapList(processSize);
		if (gapList.isEmpty()) {
			return;
		}
		gapList.sort((Process a, Process b) -> b.getSize() - a.getSize());
		int position = gapList.get(0).getPosition();
		list.add(position, new Process(processID, processSize));
		list.get(position).setPosition(position);
		updateGap(position + 1, processSize);
	}

	public void addBestFit(int processID, int processSize) {
		ArrayList<Process> gapList = buildGapList(processSize);
		if (gapList.isEmpty()) {
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
	}

	public void addFirstFit(int processID, int processSize) {
		ArrayList<Process> gapList = buildGapList(processSize);
		if (gapList.isEmpty()) {
			return;
		}

		int position = gapList.get(0).getPosition();
		list.add(position, new Process(processID, processSize));
		list.get(position).setPosition(position);
		updateGap(position + 1, processSize);

	}

	private void updateGap(int position, int processSize) {
		list.get(position).setPosition(position);
		list.get(position).setSize(list.get(position).getSize() - processSize);

	}

	private ArrayList<Process> buildGapList(int processSize) {
		ArrayList<Process> gapList = new ArrayList<Process>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getProcessID() == -1 && list.get(i).getSize() >= processSize) {
				gapList.add(new Process(list.get(i)));
			}

		}

		return gapList;
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

}
