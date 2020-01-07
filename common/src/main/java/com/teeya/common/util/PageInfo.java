package com.teeya.common.util;


import java.util.List;

/**
 * 分页封装类
 * @param <T>
 */
public class PageInfo<T> {

	protected int pageNum = 1;//页码
	protected int pageSize = 10;//返回条数
	protected long totalRecord = -1;//总记录数
	protected int totalPage = -1;//总页数
	protected int startIndex = 0; //第几条记录开始
	protected List<T> resultList;//结果集

	public PageInfo() {
	}

	public PageInfo(int pageSize, int pageNum) {
		this.pageSize = pageSize;
		this.pageNum = pageNum;
	}

	public PageInfo(Integer pageNum) {
		if (pageNum != null) {
			this.pageNum = pageNum.intValue();
		}
	}

	public PageInfo(int pageNum, int pageSize, long totalRecord, List<T> resultList) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		this.resultList = resultList;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
		setPageSize(this.pageSize);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		computeTotalPage();
	}

	public long getTotalRecord() {
		return totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
		computeTotalPage();
	}

	public int getStartIndex() {
		return (pageNum - 1) * pageSize;
	}

	protected void computeTotalPage() {
		if (getPageSize() > 0 && getTotalRecord() > -1) {
			this.totalPage = (int) (getTotalRecord() % getPageSize() == 0 ? getTotalRecord()
					/ getPageSize()
					: getTotalRecord() / getPageSize() + 1);
		}
		if (this.totalPage == 0) {
			this.totalPage = 1;
		}
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder().append("Page [pageNum=")
				.append(pageNum).append(", pageSize=").append(pageSize)
				.append(", totalRecord=")
				.append(totalRecord < 0 ? "null" : totalRecord)
				.append(", totalPage=")
				.append(totalPage < 0 ? "null" : totalPage);
		return builder.toString();
	}

}
