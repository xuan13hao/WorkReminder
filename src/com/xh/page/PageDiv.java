package com.xh.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageDiv <T extends Serializable>
{
	private int pageNo;
	
	private int pageSize;
	
	private int totalNo;
	
	private int totalPage;
	
	private List<T> list = new ArrayList<T>();
	
	public PageDiv()
	{
		
	}
	
	public PageDiv(int pageNo,int pageSize)
	{
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	
	public PageDiv(int pageNo,int pageSize,int totalNo,List<T> list)
	{
		
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalNo = totalNo;
		int total = totalNo/pageSize;
		int total1 = totalNo%pageSize;
		if(total1==0)
			totalPage = total;
		else
			totalPage = total + 1;
		this.list = list;
	}

	public int getPageNo() 
	{
		return pageNo;
	}

	public void setPageNo(int pageNo)
	{
		if(pageNo < 1)
			this.pageNo = 1;
		else if(pageNo > totalPage)
			this.pageNo = totalPage;
		else
			this.pageNo = pageNo;
	}

	public int getPageSize() 
	{
		return pageSize;	
	}

	public void setPageSize(int pageSize) 
	{
		this.pageSize = pageSize;
	}

	public int getTotalNo() 
	{
		return totalNo;
	}

	public void setTotalNo(int totalNo)
	{
		this.totalNo = totalNo;
	}

	public int getTotalPage()
	{
		return totalPage;
	}

	public void setTotalPage(int totalPage)
	{
		this.totalPage = totalPage;
	}

	public List<T> getList() 
	{
		return list;
	}

	public void setList(List<T> list)
	{
		this.list = list;
	}
}
