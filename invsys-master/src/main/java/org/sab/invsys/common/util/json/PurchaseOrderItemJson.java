package org.sab.invsys.common.util.json;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.sab.invsys.web.model.order.purchase.PurchaseOrderItemsUI;

public class PurchaseOrderItemJson {
	Logger logger = Logger.getLogger(PurchaseOrderItemJson.class);

	public String getJSON(List<PurchaseOrderItemsUI> data) {
		String result = "";
		try {
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(data);
		} catch (Exception ex) {
			logger.error(ex);
		}
		return result;
	}

	public List<PurchaseOrderItemsUI> getListFromJSON(String data) {
		List<PurchaseOrderItemsUI> values = new ArrayList<PurchaseOrderItemsUI>();

		try {
			ObjectMapper mapper = new ObjectMapper();

			values = mapper.readValue(data,
					new TypeReference<List<PurchaseOrderItemsUI>>() {
					});
		} catch (Exception ex) {
			logger.error(ex);
		}
		return values;
	}
}
