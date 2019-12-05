package model;

import java.util.Arrays;

public class Demo {

	public static void main(String[] args) {
		
		MemoryList list = new MemoryList(2000, 100);
		System.out.println(list.getList());
		list.addProcess(1, 200, 1);
		list.addProcess(2, 200, 1);
		list.addProcess(3, 200, 1);
		list.addProcess(4, 200, 1);
		list.addProcess(5, 200, 1);
		list.addProcess(6, 200, 1);
		list.addProcess(7, 200, 1);
		list.addProcess(8, 200, 1);
		list.addProcess(9, 200, 1);
		list.addProcess(10, 200, 1);
		list.addProcess(11, 200, 1);
		
		
		
		System.out.println(list.getList());
		
		list.addProcess(7, 1000, 0);
		list.addProcess(8, 800, 0);
		System.out.println(list.getWaitingQueue());
		
		list.removeProcess(5);
		list.removeProcess(6);
		list.removeProcess(1);
		list.removeProcess(2);
//		list.compact();
//		list.addProcess(12, 100, 0);
		
		System.out.println(list.getList());
		System.out.println(list.getWaitingQueue());
		
		System.out.println(new OutputString(list).getOutputString());
		
		
		
		
	}

}
