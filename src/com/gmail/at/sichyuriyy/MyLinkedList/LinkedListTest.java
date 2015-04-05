package com.gmail.at.sichyuriyy.MyLinkedList;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void test() throws InterruptedException {
		long timeForLife = 100;
		LinkedList<Integer> list = new LinkedList<Integer>(timeForLife);
		list.add(5);
		Thread.sleep(200);
		assertNull(list.getFirst());
	}

}
