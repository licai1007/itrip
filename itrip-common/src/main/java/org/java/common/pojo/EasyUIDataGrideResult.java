package org.java.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 在EasyUI的dataGride（网格）展示数据需要封装的属性有total,rows
 * @author licai
 *
 */
public class EasyUIDataGrideResult implements Serializable{
	private static final long serialVersionUID = 1L;
	private long total;//数据的总条数
	private List<?> rows;//一页信息的列表
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
