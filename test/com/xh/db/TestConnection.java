package com.xh.db;

import java.sql.Connection;

import org.junit.Test;

public class TestConnection {
	@Test
	public void testConnecion()
	{
		Connection con = DbManager.getInstance().getRealConnection();
		System.out.println("connection:\t" + con);
	}
}
