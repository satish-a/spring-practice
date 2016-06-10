package org.sab.sampleqa.common.util.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTableResponseMap<T> {

	public Map<String, Object> mapOK(List<T> items, long total) {

		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("iTotalDisplayRecords", total);
		modelMap.put("iTotalRecords", total);
		modelMap.put("aaData", items);
		modelMap.put("success", true);

		return modelMap;
	}

	public Map<String, Object> mapOK(List<T> items, String message) {

		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		modelMap.put("message", message);
		modelMap.put("aaData", items);
		modelMap.put("success", true);

		return modelMap;
	}

	public Map<String, Object> mapError(String msg) {

		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put("message", msg);
		modelMap.put("success", false);

		return modelMap;
	}
}
