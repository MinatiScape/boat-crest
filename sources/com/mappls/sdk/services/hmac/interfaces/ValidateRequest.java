package com.mappls.sdk.services.hmac.interfaces;

import com.mappls.sdk.services.hmac.CryptoServiceImpl;
import com.mappls.sdk.services.hmac.HMACSignatureValidator;
import com.mappls.sdk.services.hmac.HMACSignatureValidatorImpl;
import java.util.Map;
/* loaded from: classes8.dex */
public class ValidateRequest {
    public HMACSignatureValidator hmacSignatureValidator;

    public static ValidateRequest getInstance() {
        ValidateRequest validateRequest = new ValidateRequest();
        validateRequest.hmacSignatureValidator = new HMACSignatureValidatorImpl(new CryptoServiceImpl());
        return validateRequest;
    }

    public boolean validateEncapsulatedCustomHeader(Map<String, String> map, String str) {
        try {
            if (map.get("x-ms-seh") != null && !map.get("x-ms-seh").isEmpty()) {
                return this.hmacSignatureValidator.setApiSecret(str).setEncryptedRequest(map.get("x-ms-seh")).validate();
            }
            throw new IllegalArgumentException("The x-ms-seh header is missing.");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
