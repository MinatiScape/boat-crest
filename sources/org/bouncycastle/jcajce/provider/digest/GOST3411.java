package org.bouncycastle.jcajce.provider.digest;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.digests.GOST3411Digest;
import org.bouncycastle.crypto.digests.GOST3411_2012_256Digest;
import org.bouncycastle.crypto.digests.GOST3411_2012_512Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.PBESecretKeyFactory;
/* loaded from: classes13.dex */
public class GOST3411 {

    /* loaded from: classes13.dex */
    public static class Digest extends BCMessageDigest implements Cloneable {
        public Digest() {
            super(new GOST3411Digest());
        }

        @Override // java.security.MessageDigest, java.security.MessageDigestSpi
        public Object clone() throws CloneNotSupportedException {
            Digest digest = (Digest) super.clone();
            digest.digest = new GOST3411Digest((GOST3411Digest) this.digest);
            return digest;
        }
    }

    /* loaded from: classes13.dex */
    public static class Digest2012_256 extends BCMessageDigest implements Cloneable {
        public Digest2012_256() {
            super(new GOST3411_2012_256Digest());
        }

        @Override // java.security.MessageDigest, java.security.MessageDigestSpi
        public Object clone() throws CloneNotSupportedException {
            Digest2012_256 digest2012_256 = (Digest2012_256) super.clone();
            digest2012_256.digest = new GOST3411_2012_256Digest((GOST3411_2012_256Digest) this.digest);
            return digest2012_256;
        }
    }

    /* loaded from: classes13.dex */
    public static class Digest2012_512 extends BCMessageDigest implements Cloneable {
        public Digest2012_512() {
            super(new GOST3411_2012_512Digest());
        }

        @Override // java.security.MessageDigest, java.security.MessageDigestSpi
        public Object clone() throws CloneNotSupportedException {
            Digest2012_512 digest2012_512 = (Digest2012_512) super.clone();
            digest2012_512.digest = new GOST3411_2012_512Digest((GOST3411_2012_512Digest) this.digest);
            return digest2012_512;
        }
    }

    /* loaded from: classes13.dex */
    public static class HashMac extends BaseMac {
        public HashMac() {
            super(new HMac(new GOST3411Digest()));
        }
    }

    /* loaded from: classes13.dex */
    public static class HashMac2012_256 extends BaseMac {
        public HashMac2012_256() {
            super(new HMac(new GOST3411_2012_256Digest()));
        }
    }

    /* loaded from: classes13.dex */
    public static class HashMac2012_512 extends BaseMac {
        public HashMac2012_512() {
            super(new HMac(new GOST3411_2012_512Digest()));
        }
    }

    /* loaded from: classes13.dex */
    public static class KeyGenerator extends BaseKeyGenerator {
        public KeyGenerator() {
            super("HMACGOST3411", 256, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes13.dex */
    public static class KeyGenerator2012_256 extends BaseKeyGenerator {
        public KeyGenerator2012_256() {
            super("HMACGOST3411", 256, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes13.dex */
    public static class KeyGenerator2012_512 extends BaseKeyGenerator {
        public KeyGenerator2012_512() {
            super("HMACGOST3411", 512, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes13.dex */
    public static class Mappings extends a {

        /* renamed from: a  reason: collision with root package name */
        public static final String f14972a = GOST3411.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            StringBuilder sb = new StringBuilder();
            String str = f14972a;
            sb.append(str);
            sb.append("$Digest");
            configurableProvider.addAlgorithm("MessageDigest.GOST3411", sb.toString());
            configurableProvider.addAlgorithm("Alg.Alias.MessageDigest.GOST", "GOST3411");
            configurableProvider.addAlgorithm("Alg.Alias.MessageDigest.GOST-3411", "GOST3411");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Alg.Alias.MessageDigest.");
            ASN1ObjectIdentifier aSN1ObjectIdentifier = CryptoProObjectIdentifiers.gostR3411;
            sb2.append(aSN1ObjectIdentifier);
            configurableProvider.addAlgorithm(sb2.toString(), "GOST3411");
            addHMACAlgorithm(configurableProvider, "GOST3411", str + "$HashMac", str + "$KeyGenerator");
            addHMACAlias(configurableProvider, "GOST3411", aSN1ObjectIdentifier);
            configurableProvider.addAlgorithm("MessageDigest.GOST3411-2012-256", str + "$Digest2012_256");
            configurableProvider.addAlgorithm("Alg.Alias.MessageDigest.GOST-2012-256", "GOST3411-2012-256");
            configurableProvider.addAlgorithm("Alg.Alias.MessageDigest.GOST-3411-2012-256", "GOST3411-2012-256");
            configurableProvider.addAlgorithm("Alg.Alias.MessageDigest." + RosstandartObjectIdentifiers.id_tc26_gost_3411_12_256, "GOST3411-2012-256");
            addHMACAlgorithm(configurableProvider, "GOST3411-2012-256", str + "$HashMac2012_256", str + "$KeyGenerator2012_256");
            addHMACAlias(configurableProvider, "GOST3411-2012-256", RosstandartObjectIdentifiers.id_tc26_hmac_gost_3411_12_256);
            configurableProvider.addAlgorithm("MessageDigest.GOST3411-2012-512", str + "$Digest2012_512");
            configurableProvider.addAlgorithm("Alg.Alias.MessageDigest.GOST-2012-512", "GOST3411-2012-512");
            configurableProvider.addAlgorithm("Alg.Alias.MessageDigest.GOST-3411-2012-512", "GOST3411-2012-512");
            configurableProvider.addAlgorithm("Alg.Alias.MessageDigest." + RosstandartObjectIdentifiers.id_tc26_gost_3411_12_512, "GOST3411-2012-512");
            addHMACAlgorithm(configurableProvider, "GOST3411-2012-512", str + "$HashMac2012_512", str + "$KeyGenerator2012_512");
            addHMACAlias(configurableProvider, "GOST3411-2012-512", RosstandartObjectIdentifiers.id_tc26_hmac_gost_3411_12_512);
            configurableProvider.addAlgorithm("SecretKeyFactory.PBEWITHHMACGOST3411", str + "$PBEWithMacKeyFactory");
            configurableProvider.addAlgorithm("Alg.Alias.SecretKeyFactory." + aSN1ObjectIdentifier, "PBEWITHHMACGOST3411");
        }
    }

    /* loaded from: classes13.dex */
    public static class PBEWithMacKeyFactory extends PBESecretKeyFactory {
        public PBEWithMacKeyFactory() {
            super("PBEwithHmacGOST3411", null, false, 2, 6, 256, 0);
        }
    }
}
