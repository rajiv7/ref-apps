/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : Jul 14, 2015
 */
package com.sanlark.vendormgmnt.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sanlark.vendormgmnt.pojo.common.BatchTableInfo;

public class FormBatchTable implements Serializable {
	private static final long serialVersionUID = 4974925412483023996L;
	
	private List<BatchTableInfo> batchCol;

	public FormBatchTable() {
		this.batchCol = new ArrayList<BatchTableInfo>();
	}

	public List<BatchTableInfo> getBatchCol() {
		return batchCol;
	}

	public void setBatchCol(List<BatchTableInfo> batchCol) {
		this.batchCol = batchCol;
	}
}
