package org.bouncycastle.jcajce.provider.symmetric;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.misc.IDEACBCPar;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.engines.IDEAEngine;
import org.bouncycastle.crypto.macs.CBCBlockCipherMac;
import org.bouncycastle.crypto.macs.CFBBlockCipherMac;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.PBESecretKeyFactory;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;
/* loaded from: classes13.dex */
public final class IDEA {

    /* loaded from: classes13.dex */
    public static class AlgParamGen extends BaseAlgorithmParameterGenerator {
        @Override // java.security.AlgorithmParameterGeneratorSpi
        public AlgorithmParameters engineGenerateParameters() {
            byte[] bArr = new byte[8];
            if (this.random == null) {
                this.random = new SecureRandom();
            }
            this.random.nextBytes(bArr);
            try {
                AlgorithmParameters createParametersInstance = createParametersInstance("IDEA");
                createParametersInstance.init(new IvParameterSpec(bArr));
                return createParametersInstance;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        public void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for IDEA parameter generation.");
        }
    }

    /* loaded from: classes13.dex */
    public static class AlgParams extends BaseAlgorithmParameters {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f15028a;

        @Override // java.security.AlgorithmParametersSpi
        public byte[] engineGetEncoded() throws IOException {
            return engineGetEncoded("ASN.1");
        }

        @Override // java.security.AlgorithmParametersSpi
        public byte[] engineGetEncoded(String str) throws IOException {
            if (isASN1FormatString(str)) {
                return new IDEACBCPar(engineGetEncoded("RAW")).getEncoded();
            }
            if (str.equals("RAW")) {
                byte[] bArr = this.f15028a;
                byte[] bArr2 = new byte[bArr.length];
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                return bArr2;
            }
            return null;
        }

        @Override // java.security.AlgorithmParametersSpi
        public void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
            if (!(algorithmParameterSpec instanceof IvParameterSpec)) {
                throw new InvalidParameterSpecException("IvParameterSpec required to initialise a IV parameters algorithm parameters object");
            }
            this.f15028a = ((IvParameterSpec) algorithmParameterSpec).getIV();
        }

        @Override // java.security.AlgorithmParametersSpi
        public void engineInit(byte[] bArr) throws IOException {
            byte[] bArr2 = new byte[bArr.length];
            this.f15028a = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        }

        @Override // java.security.AlgorithmParametersSpi
        public void engineInit(byte[] bArr, String str) throws IOException {
            if (str.equals("RAW")) {
                engineInit(bArr);
            } else if (!str.equals("ASN.1")) {
                throw new IOException("Unknown parameters format in IV parameters object");
            } else {
                engineInit(new IDEACBCPar((ASN1Sequence) new ASN1InputStream(bArr).readObject()).getIV());
            }
        }

        @Override // java.security.AlgorithmParametersSpi
        public String engineToString() {
            return "IDEA Parameters";
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters
        public AlgorithmParameterSpec localEngineGetParameterSpec(Class cls) throws InvalidParameterSpecException {
            if (cls == IvParameterSpec.class) {
                return new IvParameterSpec(this.f15028a);
            }
            throw new InvalidParameterSpecException("unknown parameter spec passed to IV parameters object.");
        }
    }

    /* loaded from: classes13.dex */
    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new CBCBlockCipher(new IDEAEngine()), 64);
        }
    }

    /* loaded from: classes13.dex */
    public static class CFB8Mac extends BaseMac {
        public CFB8Mac() {
            super(new CFBBlockCipherMac(new IDEAEngine()));
        }
    }

    /* loaded from: classes13.dex */
    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new IDEAEngine());
        }
    }

    /* loaded from: classes13.dex */
    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            super("IDEA", 128, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes13.dex */
    public static class Mac extends BaseMac {
        public Mac() {
            super(new CBCBlockCipherMac(new IDEAEngine()));
        }
    }

    /* loaded from: classes13.dex */
    public static class Mappings extends AlgorithmProvider {

        /* renamed from: a  reason: collision with root package name */
        public static final String f15029a = IDEA.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            StringBuilder sb = new StringBuilder();
            String str = f15029a;
            sb.append(str);
            sb.append("$AlgParamGen");
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator.IDEA", sb.toString());
            configurableProvider.addAlgorithm("AlgorithmParameterGenerator.1.3.6.1.4.1.188.7.1.1.2", str + "$AlgParamGen");
            configurableProvider.addAlgorithm("AlgorithmParameters.IDEA", str + "$AlgParams");
            configurableProvider.addAlgorithm("AlgorithmParameters.1.3.6.1.4.1.188.7.1.1.2", str + "$AlgParams");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDIDEA", "PKCS12PBE");
            configurableProvider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDIDEA-CBC", "PKCS12PBE");
            configurableProvider.addAlgorithm("Cipher.IDEA", str + "$ECB");
            ASN1ObjectIdentifier aSN1ObjectIdentifier = MiscObjectIdentifiers.as_sys_sec_alg_ideaCBC;
            configurableProvider.addAlgorithm("Cipher", aSN1ObjectIdentifier, str + "$CBC");
            configurableProvider.addAlgorithm("Cipher.PBEWITHSHAANDIDEA-CBC", str + "$PBEWithSHAAndIDEA");
            configurableProvider.addAlgorithm("KeyGenerator.IDEA", str + "$KeyGen");
            configurableProvider.addAlgorithm("KeyGenerator", aSN1ObjectIdentifier, str + "$KeyGen");
            configurableProvider.addAlgorithm("SecretKeyFactory.PBEWITHSHAANDIDEA-CBC", str + "$PBEWithSHAAndIDEAKeyGen");
            configurableProvider.addAlgorithm("Mac.IDEAMAC", str + "$Mac");
            configurableProvider.addAlgorithm("Alg.Alias.Mac.IDEA", "IDEAMAC");
            configurableProvider.addAlgorithm("Mac.IDEAMAC/CFB8", str + "$CFB8Mac");
            configurableProvider.addAlgorithm("Alg.Alias.Mac.IDEA/CFB8", "IDEAMAC/CFB8");
        }
    }

    /* loaded from: classes13.dex */
    public static class PBEWithSHAAndIDEA extends BaseBlockCipher {
        public PBEWithSHAAndIDEA() {
            super(new CBCBlockCipher(new IDEAEngine()));
        }
    }

    /* loaded from: classes13.dex */
    public static class PBEWithSHAAndIDEAKeyGen extends PBESecretKeyFactory {
        public PBEWithSHAAndIDEAKeyGen() {
            super("PBEwithSHAandIDEA-CBC", null, true, 2, 1, 128, 64);
        }
    }
}
