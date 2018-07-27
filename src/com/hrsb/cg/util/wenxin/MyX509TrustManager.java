package com.hrsb.cg.util.wenxin;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.X509TrustManager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hrsb.cg.util.JsonUtil;

/**
 * 证书信任管理器（用于https请求）
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class MyX509TrustManager implements X509TrustManager {

	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
	public static void main(String[] args) {
		Gson gson=new Gson();
		Map<String,Object> aaa=new HashMap<String, Object>();
		aaa.put("a", "1");
		JsonObject jb = JsonUtil.maptojson(aaa);
		System.out.println(jb);
		Map<String,Object> m2 = JsonUtil.jsonToMap(jb);
		System.out.println(m2.get("a").toString());
		@SuppressWarnings("rawtypes")
		Map map=JsonUtil.toMap(gson.toJson(aaa));
		System.out.println(map.get("a").toString());
	}
}