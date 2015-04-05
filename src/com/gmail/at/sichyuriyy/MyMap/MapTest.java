package com.gmail.at.sichyuriyy.MyMap;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapTest {

	@Test
	public void test() throws InterruptedException {
		MyMap<String, Integer> map = new MyMap<String,Integer>(100L);
		map.put("abcd", 12);
		Thread.sleep(50);
		assertEquals((Integer)map.get("abcd"), (Integer)12);
		Thread.sleep(100);
		assertNull(map.get("abcd"));
	}

}
