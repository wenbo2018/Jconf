package com.github.wenbo2018.jconf.web.dto;

import java.util.List;

public class PageModel<T> {
	// pageIndex必须跟JS自己定义pageIndexName的名字一样,下面道理一样
	private Integer pageIndex;
	private Integer pageSize;
	private Integer total;
	// list这里名字可以由自己随意定义
	private List<T> list;

	private List<T> parameter;

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public PageModel() {
		super();
	}

	public List<T> getParameter() {
		return parameter;
	}

	public void setParameter(List<T> parameter) {
		this.parameter = parameter;
	}
}
