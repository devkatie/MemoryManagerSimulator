package model;

import java.util.Arrays;

public class Demo {

	public static void main(String[] args) {
		
		MemoryList list = new MemoryList(2000, 100);
		System.out.println(list.getList());
		list.addBestFit(1, 200);
		list.addFirstFit(2, 200);
		list.addWorstFit(3, 200);
		list.addBestFit(4, 200);
		list.addFirstFit(5, 200);
		list.addWorstFit(6, 200);
		
		System.out.println(list.getList());
		
		list.addProcess(7, 1000, 0);
		list.addProcess(8, 800, 0);
		System.out.println(list.getWaitingQueue());
		
		list.removeProcess(5);
//		list.removeProcess(6);
		System.out.println(list.getList());
		System.out.println(list.getWaitingQueue());
		
		System.out.println(new OutputString(list).getOutputString());
		
		
		
		
	}

}
