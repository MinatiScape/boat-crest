package org.bouncycastle.operator.bc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.digests.GOST3411Digest;
import org.bouncycastle.crypto.digests.GOST3411_2012_256Digest;
import org.bouncycastle.crypto.digests.GOST3411_2012_512Digest;
import org.bouncycastle.crypto.digests.MD2Digest;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.RIPEMD128Digest;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.RIPEMD256Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.operator.OperatorCreationException;
/* loaded from: classes13.dex */
public class BcDefaultDigestProvider implements BcDigestProvider {

    /* renamed from: a  reason: collision with root package name */
    public static final Map f15235a = a();
    public static final BcDigestProvider INSTANCE = new BcDefaultDigestProvider();

    /* loaded from: classes13.dex */
    public static class a implements BcDigestProvider {
        @Override // org.bouncycastle.operator.bc.BcDigestProvider
        public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
            return new MD5Digest();
        }
    }

    /* loaded from: classes13.dex */
    public static class b implements BcDigestProvider {
        @Override // org.bouncycastle.operator.bc.BcDigestProvider
        public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
            return new MD4Digest();
        }
    }

    /* loaded from: classes13.dex */
    public static class c implements BcDigestProvider {
        @Override // org.bouncycastle.operator.bc.BcDigestProvider
        public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
            return new MD2Digest();
        }
    }

    /* loaded from: classes13.dex */
    public static class d implements BcDigestProvider {
        @Override // org.bouncycastle.operator.bc.BcDigestProvider
        public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
            return new GOST3411Digest();
        }
    }

    /* loaded from: classes13.dex */
    public static class e implements BcDigestProvider {
        @Override // org.bouncycastle.operator.bc.BcDigestProvider
        public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
            return new GOST3411_2012_256Digest();
        }
    }

    /* loaded from: classes13.dex */
    public static class f implements BcDigestProvider {
        @Override // org.bouncycastle.operator.bc.BcDigestProvider
        public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
            return new GOST3411_2012_512Digest();
        }
    }

    /* loaded from: classes13.dex */
    public static class g implements BcDigestProvider {
        @Override // org.bouncycastle.operator.bc.BcDigestProvider
        public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
            return new RIPEMD128Digest();
        }
    }

    /* loaded from: classes13.dex */
    public static class h implements BcDigestProvider {
        @Override // org.bouncycastle.operator.bc.BcDigestProvider
        public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
            return new RIPEMD160Digest();
        }
    }

    /* loaded from: classes13.dex */
    public static class i implements BcDigestProvider {
        @Override // org.bouncycastle.operator.bc.BcDigestProvider
        public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
            return new RIPEMD256Digest();
        }
    }

    /* loaded from: classes13.dex */
    public static class j implements BcDigestProvider {
        @Override // org.bouncycastle.operator.bc.BcDigestProvider
        public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
            return new SHA1Digest();
        }
    }

    /* loaded from: classes13.dex */
    public static class k implements BcDigestProvider {
        @Override // org.bouncycastle.operator.bc.BcDigestProvider
        public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
            return new SHA224Digest();
        }
    }

    /* loaded from: classes13.dex */
    public static class l implements BcDigestProvider {
        @Override // org.bouncycastle.operator.bc.BcDigestProvider
        public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
            return new SHA256Digest();
        }
    }

    /* loaded from: classes13.dex */
    public static class m implements BcDigestProvider {
        @Override // org.bouncycastle.operator.bc.BcDigestProvider
        public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
            return new SHA384Digest();
        }
    }

    /* loaded from: classes13.dex */
    public static class n implements BcDigestProvider {
        @Override // org.bouncycastle.operator.bc.BcDigestProvider
        public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
            return new SHA512Digest();
        }
    }

    /* loaded from: classes13.dex */
    public static class o implements BcDigestProvider {
        @Override // org.bouncycastle.operator.bc.BcDigestProvider
        public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
            return new SHA3Digest(224);
        }
    }

    /* loaded from: classes13.dex */
    public static class p implements BcDigestProvider {
        @Override // org.bouncycastle.operator.bc.BcDigestProvider
        public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
            return new SHA3Digest(256);
        }
    }

    /* loaded from: classes13.dex */
    public static class q implements BcDigestProvider {
        @Override // org.bouncycastle.operator.bc.BcDigestProvider
        public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
            return new SHA3Digest(384);
        }
    }

    /* loaded from: classes13.dex */
    public static class r implements BcDigestProvider {
        @Override // org.bouncycastle.operator.bc.BcDigestProvider
        public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) {
            return new SHA3Digest(512);
        }
    }

    public static Map a() {
        HashMap hashMap = new HashMap();
        hashMap.put(OIWObjectIdentifiers.idSHA1, new j());
        hashMap.put(NISTObjectIdentifiers.id_sha224, new k());
        hashMap.put(NISTObjectIdentifiers.id_sha256, new l());
        hashMap.put(NISTObjectIdentifiers.id_sha384, new m());
        hashMap.put(NISTObjectIdentifiers.id_sha512, new n());
        hashMap.put(NISTObjectIdentifiers.id_sha3_224, new o());
        hashMap.put(NISTObjectIdentifiers.id_sha3_256, new p());
        hashMap.put(NISTObjectIdentifiers.id_sha3_384, new q());
        hashMap.put(NISTObjectIdentifiers.id_sha3_512, new r());
        hashMap.put(PKCSObjectIdentifiers.md5, new a());
        hashMap.put(PKCSObjectIdentifiers.md4, new b());
        hashMap.put(PKCSObjectIdentifiers.md2, new c());
        hashMap.put(CryptoProObjectIdentifiers.gostR3411, new d());
        hashMap.put(RosstandartObjectIdentifiers.id_tc26_gost_3411_12_256, new e());
        hashMap.put(RosstandartObjectIdentifiers.id_tc26_gost_3411_12_512, new f());
        hashMap.put(TeleTrusTObjectIdentifiers.ripemd128, new g());
        hashMap.put(TeleTrusTObjectIdentifiers.ripemd160, new h());
        hashMap.put(TeleTrusTObjectIdentifiers.ripemd256, new i());
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // org.bouncycastle.operator.bc.BcDigestProvider
    public ExtendedDigest get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
        BcDigestProvider bcDigestProvider = (BcDigestProvider) f15235a.get(algorithmIdentifier.getAlgorithm());
        if (bcDigestProvider != null) {
            return bcDigestProvider.get(algorithmIdentifier);
        }
        throw new OperatorCreationException("cannot recognise digest");
    }
}
