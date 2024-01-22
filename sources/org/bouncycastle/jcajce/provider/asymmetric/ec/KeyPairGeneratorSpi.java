package org.bouncycastle.jcajce.provider.asymmetric.ec;

import com.realsil.sdk.dfu.DfuConstants;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import java.util.Hashtable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveGenParameterSpec;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.util.Integers;
import org.jose4j.keys.EllipticCurves;
/* loaded from: classes13.dex */
public abstract class KeyPairGeneratorSpi extends KeyPairGenerator {

    /* loaded from: classes13.dex */
    public static class EC extends KeyPairGeneratorSpi {
        public static Hashtable h;

        /* renamed from: a  reason: collision with root package name */
        public ECKeyGenerationParameters f14945a;
        public ECKeyPairGenerator b;
        public Object c;
        public int d;
        public boolean e;
        public String f;
        public ProviderConfiguration g;

        static {
            Hashtable hashtable = new Hashtable();
            h = hashtable;
            hashtable.put(Integers.valueOf(192), new ECGenParameterSpec("prime192v1"));
            h.put(Integers.valueOf(239), new ECGenParameterSpec("prime239v1"));
            h.put(Integers.valueOf(256), new ECGenParameterSpec("prime256v1"));
            h.put(Integers.valueOf(224), new ECGenParameterSpec("P-224"));
            h.put(Integers.valueOf(384), new ECGenParameterSpec(EllipticCurves.P_384));
            h.put(Integers.valueOf(DfuConstants.PROGRESS_START_DFU_PROCESS), new ECGenParameterSpec(EllipticCurves.P_521));
        }

        public EC() {
            super("EC");
            this.b = new ECKeyPairGenerator();
            this.c = null;
            this.d = 239;
            new SecureRandom();
            this.e = false;
            this.f = "EC";
            this.g = BouncyCastleProvider.CONFIGURATION;
        }

        public EC(String str, ProviderConfiguration providerConfiguration) {
            super(str);
            this.b = new ECKeyPairGenerator();
            this.c = null;
            this.d = 239;
            new SecureRandom();
            this.e = false;
            this.f = str;
            this.g = providerConfiguration;
        }

        public ECKeyGenerationParameters createKeyGenParamsBC(ECParameterSpec eCParameterSpec, SecureRandom secureRandom) {
            return new ECKeyGenerationParameters(new ECDomainParameters(eCParameterSpec.getCurve(), eCParameterSpec.getG(), eCParameterSpec.getN(), eCParameterSpec.getH()), secureRandom);
        }

        public ECKeyGenerationParameters createKeyGenParamsJCE(java.security.spec.ECParameterSpec eCParameterSpec, SecureRandom secureRandom) {
            ECCurve convertCurve = EC5Util.convertCurve(eCParameterSpec.getCurve());
            return new ECKeyGenerationParameters(new ECDomainParameters(convertCurve, EC5Util.convertPoint(convertCurve, eCParameterSpec.getGenerator(), false), eCParameterSpec.getOrder(), BigInteger.valueOf(eCParameterSpec.getCofactor())), secureRandom);
        }

        public ECNamedCurveSpec createNamedCurveSpec(String str) throws InvalidAlgorithmParameterException {
            X9ECParameters d = a.d(str);
            if (d == null) {
                try {
                    d = ECNamedCurveTable.getByOID(new ASN1ObjectIdentifier(str));
                    if (d == null && (d = (X9ECParameters) this.g.getAdditionalECParameters().get(new ASN1ObjectIdentifier(str))) == null) {
                        throw new InvalidAlgorithmParameterException("unknown curve OID: " + str);
                    }
                } catch (IllegalArgumentException unused) {
                    throw new InvalidAlgorithmParameterException("unknown curve name: " + str);
                }
            }
            return new ECNamedCurveSpec(str, d.getCurve(), d.getG(), d.getN(), d.getH(), null);
        }

        @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
        public KeyPair generateKeyPair() {
            if (!this.e) {
                initialize(this.d, new SecureRandom());
            }
            AsymmetricCipherKeyPair generateKeyPair = this.b.generateKeyPair();
            ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters) generateKeyPair.getPublic();
            ECPrivateKeyParameters eCPrivateKeyParameters = (ECPrivateKeyParameters) generateKeyPair.getPrivate();
            Object obj = this.c;
            if (obj instanceof ECParameterSpec) {
                ECParameterSpec eCParameterSpec = (ECParameterSpec) obj;
                BCECPublicKey bCECPublicKey = new BCECPublicKey(this.f, eCPublicKeyParameters, eCParameterSpec, this.g);
                return new KeyPair(bCECPublicKey, new BCECPrivateKey(this.f, eCPrivateKeyParameters, bCECPublicKey, eCParameterSpec, this.g));
            } else if (obj == null) {
                return new KeyPair(new BCECPublicKey(this.f, eCPublicKeyParameters, this.g), new BCECPrivateKey(this.f, eCPrivateKeyParameters, this.g));
            } else {
                java.security.spec.ECParameterSpec eCParameterSpec2 = (java.security.spec.ECParameterSpec) obj;
                BCECPublicKey bCECPublicKey2 = new BCECPublicKey(this.f, eCPublicKeyParameters, eCParameterSpec2, this.g);
                return new KeyPair(bCECPublicKey2, new BCECPrivateKey(this.f, eCPrivateKeyParameters, bCECPublicKey2, eCParameterSpec2, this.g));
            }
        }

        @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
        public void initialize(int i, SecureRandom secureRandom) {
            this.d = i;
            ECGenParameterSpec eCGenParameterSpec = (ECGenParameterSpec) h.get(Integers.valueOf(i));
            if (eCGenParameterSpec == null) {
                throw new InvalidParameterException("unknown key size.");
            }
            try {
                initialize(eCGenParameterSpec, secureRandom);
            } catch (InvalidAlgorithmParameterException unused) {
                throw new InvalidParameterException("key size not configurable.");
            }
        }

        @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
        public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            String name;
            ECKeyGenerationParameters createKeyGenParamsJCE;
            ECParameterSpec eCParameterSpec;
            if (algorithmParameterSpec == null) {
                eCParameterSpec = this.g.getEcImplicitlyCa();
                if (eCParameterSpec == null) {
                    throw new InvalidAlgorithmParameterException("null parameter passed but no implicitCA set");
                }
                this.c = null;
            } else if (!(algorithmParameterSpec instanceof ECParameterSpec)) {
                if (algorithmParameterSpec instanceof java.security.spec.ECParameterSpec) {
                    this.c = algorithmParameterSpec;
                    createKeyGenParamsJCE = createKeyGenParamsJCE((java.security.spec.ECParameterSpec) algorithmParameterSpec, secureRandom);
                    this.f14945a = createKeyGenParamsJCE;
                    this.b.init(this.f14945a);
                    this.e = true;
                }
                if (algorithmParameterSpec instanceof ECGenParameterSpec) {
                    name = ((ECGenParameterSpec) algorithmParameterSpec).getName();
                } else if (!(algorithmParameterSpec instanceof ECNamedCurveGenParameterSpec)) {
                    throw new InvalidAlgorithmParameterException("parameter object not a ECParameterSpec");
                } else {
                    name = ((ECNamedCurveGenParameterSpec) algorithmParameterSpec).getName();
                }
                initializeNamedCurve(name, secureRandom);
                this.b.init(this.f14945a);
                this.e = true;
            } else {
                this.c = algorithmParameterSpec;
                eCParameterSpec = (ECParameterSpec) algorithmParameterSpec;
            }
            createKeyGenParamsJCE = createKeyGenParamsBC(eCParameterSpec, secureRandom);
            this.f14945a = createKeyGenParamsJCE;
            this.b.init(this.f14945a);
            this.e = true;
        }

        public void initializeNamedCurve(String str, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            ECNamedCurveSpec createNamedCurveSpec = createNamedCurveSpec(str);
            this.c = createNamedCurveSpec;
            this.f14945a = createKeyGenParamsJCE(createNamedCurveSpec, secureRandom);
        }
    }

    /* loaded from: classes13.dex */
    public static class ECDH extends EC {
        public ECDH() {
            super("ECDH", BouncyCastleProvider.CONFIGURATION);
        }
    }

    /* loaded from: classes13.dex */
    public static class ECDHC extends EC {
        public ECDHC() {
            super("ECDHC", BouncyCastleProvider.CONFIGURATION);
        }
    }

    /* loaded from: classes13.dex */
    public static class ECDSA extends EC {
        public ECDSA() {
            super("ECDSA", BouncyCastleProvider.CONFIGURATION);
        }
    }

    /* loaded from: classes13.dex */
    public static class ECMQV extends EC {
        public ECMQV() {
            super("ECMQV", BouncyCastleProvider.CONFIGURATION);
        }
    }

    public KeyPairGeneratorSpi(String str) {
        super(str);
    }
}
