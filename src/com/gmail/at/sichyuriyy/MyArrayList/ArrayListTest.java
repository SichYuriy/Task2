package com.gmail.at.sichyuriyy.MyArrayList;

import static org.junit.Assert.*;

import org.junit.Test;

import com.gmail.at.sichyuriyy.MyLinkedList.LinkedList;

public class ArrayListTest {

	@Test
	public void test() throws InterruptedException {
		long timeForLife = 100;
		ArrayList<Integer> list = new ArrayList<Integer>(timeForLife);
		
		list.add(5);
		System.out.println(list.timeForLife);
		Thread.sleep(200);
		assertNull(list.get(0));
	}

}
