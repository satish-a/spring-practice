package org.sab.invsys.common.util.extjs;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JsonUtils {
	Logger logger = Logger.getLogger(JsonUtils.class);

	public static List<FilterRequest> getListFromJsonArray(String data) {

		List<FilterRequest> values = new ArrayList<FilterRequest>();

		try {
			ObjectMapper mapper = new ObjectMapper();

			values = mapper.readValue(data,
					new TypeReference<List<FilterRequest>>() {
					});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return values;
	}
}
