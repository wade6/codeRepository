package com.code.repository.tools;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public abstract class FeatureDO {

	private Map<String, String> featureMap;

	@SuppressWarnings("unchecked")
	public Map<String, String> getFeatureMap() {
		if (this.featureMap != null && this.featureMap.size() > 0) {
			return this.featureMap;
		}

		Map<String, String> featureMap = new HashMap<String, String>();
		if (StringUtils.isBlank(getFeatureString())) {
			return featureMap;
		}

		try {
			featureMap = (Map<String, String>) JSONObject.parseObject(getFeatureString(), Map.class);
		} catch (Exception e) {
		}

		return featureMap;
	}

	public String getFeature(String name) {
		Map<String, String> featureMap = this.getFeatureMap();
		String value = featureMap.get(name);
		return value == null ? "" : value;
	}

	public void addFeature(String name, String value) {
		if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(value)) {
			featureMap = getFeatureMap();
			featureMap.put(name, value);
			resetFeature();
		}
	}

	public boolean removeFeature(String name) {
		if (StringUtils.isBlank(name)) {
			return true;
		}
		featureMap = getFeatureMap();
		if (featureMap.containsKey(name)) {
			featureMap.remove(name);
			resetFeature();
			return true;
		}
		return false;
	}

	public void addFeatureMap(Map<String, String> map) {
		if (map != null && !map.isEmpty()) {
			featureMap = getFeatureMap();
			featureMap.putAll(map);
			resetFeature();
		}
	}

	/**
	 * 
	 * 重置feature字段
	 * 
	 * this.feature = JSONObject.toJSONString(getFeatureMap());
	 * 
	 * @author zhaoyuan.lizy
	 * @since 2018年4月17日
	 */
	protected abstract void resetFeature();

	/**
	 * 
	 * 获取属性字符串
	 * 
	 * return this.feature;
	 * 
	 * @author zhaoyuan.lizy
	 * @since 2018年4月17日 
	 * @return
	 */
	protected abstract String getFeatureString();
}

