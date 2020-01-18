package com.jiujiu.sggame.utils;

import java.util.HashMap;
import java.util.Map;

public class SnsJsonUtil {

	public static Map<String, Object> result(Object data, String msg, String userid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 0);
		map.put("message", msg != null ? msg : "");
		map.put("data", data);
		map.put("userid", userid);
		return map;
	}

	public static Map<String, Object> resultError(Object data, String msg, String userid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 1);
		map.put("message", msg != null ? msg : "");
		map.put("data", data);
		map.put("userid", "");
		return map;
	}

}
