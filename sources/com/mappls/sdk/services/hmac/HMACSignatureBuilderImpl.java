package com.mappls.sdk.services.hmac;

import com.mappls.sdk.services.hmac.Enums;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
/* loaded from: classes8.dex */
public class HMACSignatureBuilderImpl extends HMACMessageDigestGenerator implements HMACSignatureBuilder {

    /* renamed from: a  reason: collision with root package name */
    public Enums.SupportedHashingAlgorithm f13308a = Enums.SupportedHashingAlgorithm.SHA512;
    public final CryptoService b;
    public String c;
    public String d;

    public HMACSignatureBuilderImpl(CryptoService cryptoService) {
        this.b = cryptoService;
    }

    public final String a(Enums.SupportedHashingAlgorithm supportedHashingAlgorithm, String str, String str2) {
        StringBuilder sb = new StringBuilder("&");
        sb.append("hashingAlgorithm=" + supportedHashingAlgorithm.name());
        sb.append("&");
        sb.append("rawRequest=" + str);
        sb.append("&");
        sb.append("messageDigest=" + str2);
        return sb.toString();
    }

    public final String b() {
        String str = this.d;
        if (str != null && !str.isEmpty()) {
            return this.d + "~~" + (new Date().getTime() / 1000);
        }
        throw new IllegalArgumentException("The payload cannot be null or empty.");
    }

    @Override // com.mappls.sdk.services.hmac.HMACSignatureBuilder
    public String generateEncapsulatedHMACMessageDigest() throws NoSuchAlgorithmException, InvalidKeyException {
        String str = this.c;
        if (str != null && !str.isEmpty()) {
            String str2 = this.d;
            if (str2 != null && !str2.isEmpty()) {
                String b = b();
                return this.b.encryptAndEncodeUsingAES(this.c, a(this.f13308a, b, generateHMACMessageDigest(this.c, this.f13308a, b)));
            }
            throw new IllegalArgumentException("The payload cannot be null or empty.");
        }
        throw new IllegalArgumentException("The secret key cannot be null or empty.");
    }

    @Override // com.mappls.sdk.services.hmac.HMACSignatureBuilder
    public HMACSignatureBuilder setAlgorithm(Enums.SupportedHashingAlgorithm supportedHashingAlgorithm) {
        this.f13308a = supportedHashingAlgorithm;
        return this;
    }

    @Override // com.mappls.sdk.services.hmac.HMACSignatureBuilder
    public HMACSignatureBuilder setApiSecret(String str) {
        this.c = str;
        return this;
    }

    @Override // com.mappls.sdk.services.hmac.HMACSignatureBuilder
    public HMACSignatureBuilder setPayload(String str) {
        this.d = str;
        return this;
    }
}
