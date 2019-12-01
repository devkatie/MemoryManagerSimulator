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
		
		list.removeProcess(4);
		list.removeProcess(3);
		list.removeProcess(5);
		
		System.out.println(list.getList());

		list.addFirstFit(7, 100);
		list.addFirstFit(8, 501);
		
		System.out.println(list.getList());
		
		list.addWorstFit(10, 100);
		
		System.out.println(list.getList());
		
		list.addBestFit(11, 50);
		
		System.out.println(list.getList());
		
		list.removeProcess(1);
		list.removeProcess(2);
		
		System.out.println(list.getList());
		
		list.compact();
		
		System.out.println(list.getList());
		
		
	}

}
