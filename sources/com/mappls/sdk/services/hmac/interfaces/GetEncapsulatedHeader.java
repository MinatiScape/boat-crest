package com.mappls.sdk.services.hmac.interfaces;

import com.mappls.sdk.services.hmac.CryptoServiceImpl;
import com.mappls.sdk.services.hmac.Enums;
import com.mappls.sdk.services.hmac.HMACSignatureBuilder;
import com.mappls.sdk.services.hmac.HMACSignatureBuilderImpl;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes8.dex */
public class GetEncapsulatedHeader {
    public HMACSignatureBuilder hmacSignatureBuilder;

    public static GetEncapsulatedHeader getInstance() {
        GetEncapsulatedHeader getEncapsulatedHeader = new GetEncapsulatedHeader();
        getEncapsulatedHeader.hmacSignatureBuilder = new HMACSignatureBuilderImpl(new CryptoServiceImpl());
        return getEncapsulatedHeader;
    }

    public Map<String, String> getEncapsulatedHeader(Map<String, String> map, String str) {
        if (map != null) {
            try {
                if (!map.isEmpty()) {
                    if (map.get("d") != null && !map.get("d").isEmpty()) {
                        if (map.get("hsa") != null && !map.get("hsa").isEmpty()) {
                            String generateEncapsulatedHMACMessageDigest = this.hmacSignatureBuilder.setApiSecret(str).setAlgorithm(Enums.SupportedHashingAlgorithm.valueOf(map.get("hsa"))).setPayload(map.get("d")).generateEncapsulatedHMACMessageDigest();
                            HashMap hashMap = new HashMap();
                            hashMap.put("x-ms-seh", generateEncapsulatedHMACMessageDigest);
                            return hashMap;
                        }
                        throw new IllegalArgumentException("hashAlgorithm cannot be null");
                    }
                    throw new IllegalArgumentException("No value is found to encapsulate");
                }
            } catch (IllegalArgumentException | InvalidKeyException | NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }
        }
        throw new IllegalArgumentException("The request body cannot be null or empty.");
    }
}
