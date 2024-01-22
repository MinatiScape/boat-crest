package com.mappls.sdk.services.hmac;

import com.mappls.sdk.services.hmac.Enums;
import java.util.Date;
import java.util.HashMap;
/* loaded from: classes8.dex */
public class HMACSignatureValidatorImpl extends HMACMessageDigestGenerator implements HMACSignatureValidator {

    /* renamed from: a  reason: collision with root package name */
    public final CryptoService f13309a;
    public String b;
    public String c;

    public HMACSignatureValidatorImpl(CryptoService cryptoService) {
        this.f13309a = cryptoService;
    }

    @Override // com.mappls.sdk.services.hmac.HMACSignatureValidator
    public HMACSignatureValidator setApiSecret(String str) {
        this.b = str;
        return this;
    }

    @Override // com.mappls.sdk.services.hmac.HMACSignatureValidator
    public HMACSignatureValidator setEncryptedRequest(String str) {
        this.c = str;
        return this;
    }

    @Override // com.mappls.sdk.services.hmac.HMACSignatureValidator
    public boolean validate() throws Exception {
        String str = this.b;
        if (str != null && !str.isEmpty()) {
            String str2 = this.c;
            if (str2 != null && !str2.isEmpty()) {
                String decryptMessageUsingAES = this.f13309a.decryptMessageUsingAES(this.b, this.c);
                if (decryptMessageUsingAES != null && !decryptMessageUsingAES.isEmpty()) {
                    HashMap<String, String> convertToQueryStringToHashMap = HMACUtils.convertToQueryStringToHashMap(decryptMessageUsingAES);
                    String str3 = convertToQueryStringToHashMap.get("rawRequest");
                    String str4 = convertToQueryStringToHashMap.get("messageDigest");
                    String str5 = convertToQueryStringToHashMap.get("hashingAlgorithm");
                    if (str4 != null && !str4.isEmpty()) {
                        if (str3 != null && !str3.isEmpty()) {
                            if (str5 != null && !str5.isEmpty()) {
                                String[] split = str3.split("~~");
                                if (split.length > 1) {
                                    if (Long.parseLong(split[1]) + 5 >= new Date().getTime() / 1000) {
                                        return str4.contentEquals(generateHMACMessageDigest(this.b, Enums.SupportedHashingAlgorithm.valueOf(str5), str3));
                                    }
                                    throw new IllegalArgumentException("The request is not valid as it crosses the epoch threshold.");
                                }
                                throw new IllegalArgumentException("The request doesn't have epoch.");
                            }
                            throw new IllegalArgumentException("hashingAlgorithm is missing in the request " + decryptMessageUsingAES);
                        }
                        throw new IllegalArgumentException("rawRequest is missing " + decryptMessageUsingAES);
                    }
                    throw new IllegalArgumentException("messageDigest is missing " + decryptMessageUsingAES);
                }
                throw new IllegalArgumentException("No content found after decrypting the request.");
            }
            throw new IllegalArgumentException("The encrypted request cannot be null or empty.");
        }
        throw new IllegalArgumentException("The secret key cannot be null or empty.");
    }
}
