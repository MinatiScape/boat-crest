package com.mappls.sdk.services.hmac;

import com.mappls.sdk.services.hmac.Enums;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
/* loaded from: classes8.dex */
public interface HMACSignatureBuilder {
    String generateEncapsulatedHMACMessageDigest() throws NoSuchAlgorithmException, InvalidKeyException;

    HMACSignatureBuilder setAlgorithm(Enums.SupportedHashingAlgorithm supportedHashingAlgorithm);

    HMACSignatureBuilder setApiSecret(String str);

    HMACSignatureBuilder setPayload(String str);
}
