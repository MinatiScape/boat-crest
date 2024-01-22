package com.mappls.sdk.services.hmac;

import java.util.HashMap;
/* loaded from: classes8.dex */
public class HMACUtils {
    public static HashMap<String, String> convertToQueryStringToHashMap(String str) throws Exception {
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str2 : str.split("&")) {
            String[] split = str2.split("=", 2);
            if (split.length >= 2) {
                hashMap.put(split[0], split[1]);
            } else {
                hashMap.put(split[0], "");
            }
        }
        return hashMap;
    }
}
