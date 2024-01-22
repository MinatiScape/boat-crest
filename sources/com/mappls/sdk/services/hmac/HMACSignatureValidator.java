package com.mappls.sdk.services.hmac;
/* loaded from: classes8.dex */
public interface HMACSignatureValidator {
    HMACSignatureValidator setApiSecret(String str);

    HMACSignatureValidator setEncryptedRequest(String str);

    boolean validate() throws Exception;
}
