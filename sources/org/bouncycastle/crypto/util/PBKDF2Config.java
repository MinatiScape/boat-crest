package org.bouncycastle.crypto.util;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.util.Integers;
/* loaded from: classes13.dex */
public class PBKDF2Config extends PBKDFConfig {
    public static final AlgorithmIdentifier PRF_SHA1;
    public static final AlgorithmIdentifier PRF_SHA256;
    public static final AlgorithmIdentifier PRF_SHA3_256;
    public static final AlgorithmIdentifier PRF_SHA3_512;
    public static final AlgorithmIdentifier PRF_SHA512;
    public static final Map e;
    public final int b;
    public final int c;
    public final AlgorithmIdentifier d;

    /* loaded from: classes13.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f14880a = 1024;
        public int b = -1;
        public AlgorithmIdentifier c = PBKDF2Config.PRF_SHA1;

        public PBKDF2Config build() {
            return new PBKDF2Config(this);
        }

        public Builder withIterationCount(int i) {
            this.f14880a = i;
            return this;
        }

        public Builder withPRF(AlgorithmIdentifier algorithmIdentifier) {
            this.c = algorithmIdentifier;
            return this;
        }

        public Builder withSaltLength(int i) {
            this.b = i;
            return this;
        }
    }

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = PKCSObjectIdentifiers.id_hmacWithSHA1;
        DERNull dERNull = DERNull.INSTANCE;
        PRF_SHA1 = new AlgorithmIdentifier(aSN1ObjectIdentifier, dERNull);
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = PKCSObjectIdentifiers.id_hmacWithSHA256;
        PRF_SHA256 = new AlgorithmIdentifier(aSN1ObjectIdentifier2, dERNull);
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = PKCSObjectIdentifiers.id_hmacWithSHA512;
        PRF_SHA512 = new AlgorithmIdentifier(aSN1ObjectIdentifier3, dERNull);
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = NISTObjectIdentifiers.id_hmacWithSHA3_256;
        PRF_SHA3_256 = new AlgorithmIdentifier(aSN1ObjectIdentifier4, dERNull);
        ASN1ObjectIdentifier aSN1ObjectIdentifier5 = NISTObjectIdentifiers.id_hmacWithSHA3_512;
        PRF_SHA3_512 = new AlgorithmIdentifier(aSN1ObjectIdentifier5, dERNull);
        HashMap hashMap = new HashMap();
        e = hashMap;
        hashMap.put(aSN1ObjectIdentifier, Integers.valueOf(20));
        hashMap.put(aSN1ObjectIdentifier2, Integers.valueOf(32));
        hashMap.put(aSN1ObjectIdentifier3, Integers.valueOf(64));
        hashMap.put(PKCSObjectIdentifiers.id_hmacWithSHA224, Integers.valueOf(28));
        hashMap.put(PKCSObjectIdentifiers.id_hmacWithSHA384, Integers.valueOf(48));
        hashMap.put(NISTObjectIdentifiers.id_hmacWithSHA3_224, Integers.valueOf(28));
        hashMap.put(aSN1ObjectIdentifier4, Integers.valueOf(32));
        hashMap.put(NISTObjectIdentifiers.id_hmacWithSHA3_384, Integers.valueOf(48));
        hashMap.put(aSN1ObjectIdentifier5, Integers.valueOf(64));
        hashMap.put(CryptoProObjectIdentifiers.gostR3411Hmac, Integers.valueOf(32));
        hashMap.put(RosstandartObjectIdentifiers.id_tc26_hmac_gost_3411_12_256, Integers.valueOf(32));
        hashMap.put(RosstandartObjectIdentifiers.id_tc26_hmac_gost_3411_12_512, Integers.valueOf(64));
        hashMap.put(GMObjectIdentifiers.hmac_sm3, Integers.valueOf(32));
    }

    public PBKDF2Config(Builder builder) {
        super(PKCSObjectIdentifiers.id_PBKDF2);
        this.b = builder.f14880a;
        AlgorithmIdentifier algorithmIdentifier = builder.c;
        this.d = algorithmIdentifier;
        this.c = builder.b < 0 ? a(algorithmIdentifier.getAlgorithm()) : builder.b;
    }

    public static int a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Map map = e;
        if (map.containsKey(aSN1ObjectIdentifier)) {
            return ((Integer) map.get(aSN1ObjectIdentifier)).intValue();
        }
        throw new IllegalStateException("no salt size for algorithm: " + aSN1ObjectIdentifier);
    }

    public int getIterationCount() {
        return this.b;
    }

    public AlgorithmIdentifier getPRF() {
        return this.d;
    }

    public int getSaltLength() {
        return this.c;
    }
}
