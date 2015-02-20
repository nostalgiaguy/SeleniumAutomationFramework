package com.erevmax.assignment.tests;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.htmlunit.corejs.javascript.tools.debugger.Main;

import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class MockTest {
	
	
	@Test
	public void login(){
		
		List<String> mockadd = mock(List.class);
		mockadd.add("Hello");
		mockadd.add("test1");
		mockadd.add("test1");
		mockadd.add("Shubham");
		System.out.println(mockadd.contains("Shubham"));
		//mockadd.clear();
		when(mockadd.get(0)).thenReturn("first");
		verify(mockadd).add("Hello");  //verify(mock).someMethod("some arg");
		verify(mockadd).add("Shubham");
		//verify(mockadd).add("Shubhamp");
		System.out.println(mockadd.contains("Hello"));
		System.out.println(mockadd.get(0));

	}

}
