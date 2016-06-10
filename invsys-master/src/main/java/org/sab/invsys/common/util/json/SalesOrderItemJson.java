package org.sab.invsys.common.util.json;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.sab.invsys.common.util.extjs.JsonUtils;
import org.sab.invsys.web.model.order.sales.SalesOrderItemsUI;

public class SalesOrderItemJson extends JsonUtils {
	Logger logger = Logger.getLogger(SalesOrderItemJson.class);

	public String getJSON(List<SalesOrderItemsUI> data) {
		String result = "";
		try {
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(data);
		} catch (Exception ex) {
			logger.error(ex);
		}
		return result;
	}

	public List<SalesOrderItemsUI> getListFromJSON(String data) {
		List<SalesOrderItemsUI> values = new ArrayList<SalesOrderItemsUI>();

		try {
			ObjectMapper mapper = new ObjectMapper();

			values = mapper.readValue(data,
					new TypeReference<List<SalesOrderItemsUI>>() {
					});
		} catch (Exception ex) {
			logger.error(ex);
		}
		return values;
	}
}
