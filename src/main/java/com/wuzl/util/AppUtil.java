package com.wuzl.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class AppUtil {

	public AppUtil() {

	}
	
	public static String getStrFromMap(Map<?,?> map, String key){
		if (map == null || map.get(key) == null)
			return "";
		return String.valueOf(map.get(key)).trim();
	}
	
	public static Map<String, Object> requestToObjectMap(HttpServletRequest request) throws Exception {

		Map<?, ?> paramsMap = (Map<?, ?>) request.getParameterMap();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		for (Iterator<?> localIterator = paramsMap.keySet().iterator(); localIterator.hasNext();) {
			Object key = localIterator.next();
			Object value = ((String[]) paramsMap.get(key))[0];
			if(value == null || value.equals("")){
				value = null;
			}
			resultMap.put(String.valueOf(key), value);
		}
		return resultMap;
	}
	
	/**
	 * 将xx,xx,xx格式的字符串转换成 'xx','xx','xx'格式的字符串
	 * str为空 则返回null
	 * @param str
	 * @return
	 */
	public static String getSqlInStr(String str) {
		if(str != null && !str.equals("")){
			String result = "";
			String[] tmps = str.split(",");
			for (int i = 0; i < tmps.length; i++) {
				result += "'"+tmps[i].trim()+"',";
			}
			return result.substring(0,result.length()-1);
		}else{
			return null;
		}
	}
	
	public static Map<String, Object> copyMap(Map<?, ?> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		for (Iterator<?> localIterator = map.keySet().iterator(); localIterator.hasNext();) {
			Object key = localIterator.next();
			resultMap.put(String.valueOf(key), map.get(key));
		}
		return resultMap;
	}

	public static String getNormalStrDate(Date date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		String sdate  = sdf.format(date);
		return sdate;
	}
	
	public static String getUuid() throws Exception {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	
}
