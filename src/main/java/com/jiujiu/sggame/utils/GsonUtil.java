package com.jiujiu.sggame.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class GsonUtil {

	public static String utf8 = "UTF-8";
	public static Gson gson = new Gson();
//	public static Gson gson = new GsonBuilder().setDateFormat("").

	public static void saveToFile(String src, Object content) {
		if (content == null || content.toString().length() == 0) {
			FileUtil.saveToFile(src, "", utf8);
		} else {
			String res = gson.toJson(content);
			FileUtil.saveToFile(src, res, utf8);
		}
	}

	public static List<Map> getMapList(String src) {

		String res = FileUtil.getFileContent(src, utf8);
		if (res != null && res.length() != 0) {
			return gson.fromJson(res, new TypeToken<List<Map>>() {
			}.getType());
		}
		return null;
	}

	public static Map getContentMap(String content) {

		if (content != null && content.length() != 0) {
			return gson.fromJson(content, new TypeToken<Map>() {
			}.getType());
		}
		return null;
	}

	public static List<Map> getContentMapList(String content) {

		if (content != null && content.length() != 0) {
			return gson.fromJson(content, new TypeToken<List<Map>>() {
			}.getType());
		}
		return null;
	}

	public static List<String> getContentStringList(String content) {

		if (content != null && content.length() != 0) {
			return gson.fromJson(content, new TypeToken<List<String>>() {
			}.getType());
		}
		return null;
	}

	public static <T> T getModel(String data, Class<T> requiredClass) {
		return gson.fromJson(data, requiredClass);
	}

	public static Map<String, Object> JsonToMap(String jsonStr, Map<String, Object> result) {
		if (jsonStr == null) {
			return null;
		}
		if (result == null) {
			result = new HashMap<String, Object>();
		}
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(jsonStr);
		return JsonToMap(result, "▲▼◆", jsonElement);
	}

	private static Map<String, Object> JsonToMap(Map<String, Object> result, String key, JsonElement value) {
		// 如果key为null 直接报错
		if (key == null) {
			throw new RuntimeException("key值不能为null");
		}
		// 如果value为null,则直接put到map中
		if (value == null) {
			result.put(key, value);
		} else {
			// 如果value为基本数据类型，则放入到map中
			if (value.isJsonPrimitive()) {
				result.put(key, value.getAsBigDecimal());
			} else if (value.isJsonObject()) {
				// 如果value为JsonObject数据类型，则遍历此JSONObject，进行递归调用本方法
				JsonObject jsonObject = value.getAsJsonObject();
				Iterator<Entry<String, JsonElement>> iterator = jsonObject.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, JsonElement> next = iterator.next();
					result = JsonToMap(result, next.getKey(), next.getValue());
				}
			} else if (value.isJsonArray()) {
				// 如果value为JsonArray数据类型，则遍历此JsonArray，进行递归调用本方法
				JsonArray jsonArray = value.getAsJsonArray();
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				for (int i = 0, len = jsonArray.size(); i < len; i++) {
					Map<String, Object> tempMap = new HashMap<String, Object>();
					JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
					Iterator<Entry<String, JsonElement>> iterator = jsonObject.entrySet().iterator();
					while (iterator.hasNext()) {
						Entry<String, JsonElement> next = iterator.next();
						tempMap = JsonToMap(tempMap, next.getKey(), next.getValue());
					}
					list.add(tempMap);
				}
				result.put(key, list);
			}
		}
		// 返回最终结果
		return result;
	}

	public static boolean isNested(Object jsonObj) {

		return jsonObj.toString().contains("{");
	}

	public static String checkFormat(String str) {
		String _str = str.trim();
		if (_str.startsWith("[") && _str.endsWith("]")) {
			return _str.substring(1, _str.length() - 1);
		}
		return _str;
	}

}
