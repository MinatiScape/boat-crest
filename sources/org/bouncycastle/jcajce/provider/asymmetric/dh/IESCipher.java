package org.bouncycastle.jcajce.provider.asymmetric.dh;

import java.io.ByteArrayOutputStream;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.interfaces.DHKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.KeyEncoder;
import org.bouncycastle.crypto.agreement.DHBasicAgreement;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.engines.DESedeEngine;
import org.bouncycastle.crypto.engines.IESEngine;
import org.bouncycastle.crypto.generators.DHKeyPairGenerator;
import org.bouncycastle.crypto.generators.EphemeralKeyPairGenerator;
import org.bouncycastle.crypto.generators.KDF2BytesGenerator;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DHKeyGenerationParameters;
import org.bouncycastle.crypto.params.DHKeyParameters;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.DHPublicKeyParameters;
import org.bouncycastle.crypto.params.IESWithCipherParameters;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.parsers.DHIESPublicKeyParser;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.jcajce.provider.asymmetric.util.DHUtil;
import org.bouncycastle.jcajce.provider.asymmetric.util.IESUtil;
import org.bouncycastle.jcajce.provider.util.BadBlockException;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.interfaces.IESKey;
import org.bouncycastle.jce.spec.IESParameterSpec;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Strings;
/* loaded from: classes13.dex */
public class IESCipher extends CipherSpi {

    /* renamed from: a  reason: collision with root package name */
    public final JcaJceHelper f14935a;
    public final int b;
    public IESEngine c;
    public int d;
    public ByteArrayOutputStream e;
    public AlgorithmParameters f;
    public IESParameterSpec g;
    public AsymmetricKeyParameter h;
    public SecureRandom i;
    public AsymmetricKeyParameter j;

    /* loaded from: classes13.dex */
    public static class IES extends IESCipher {
        public IES() {
            super(new IESEngine(new DHBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA1()), new HMac(DigestFactory.createSHA1())));
        }
    }

    /* loaded from: classes13.dex */
    public static class IESwithAESCBC extends IESCipher {
        public IESwithAESCBC() {
            super(new IESEngine(new DHBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA1()), new HMac(DigestFactory.createSHA1()), new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()))), 16);
        }
    }

    /* loaded from: classes13.dex */
    public static class IESwithDESedeCBC extends IESCipher {
        public IESwithDESedeCBC() {
            super(new IESEngine(new DHBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA1()), new HMac(DigestFactory.createSHA1()), new PaddedBufferedBlockCipher(new CBCBlockCipher(new DESedeEngine()))), 8);
        }
    }

    /* loaded from: classes13.dex */
    public class a implements KeyEncoder {
        public a(IESCipher iESCipher) {
        }

        @Override // org.bouncycastle.crypto.KeyEncoder
        public byte[] getEncoded(AsymmetricKeyParameter asymmetricKeyParameter) {
            int bitLength = (((DHKeyParameters) asymmetricKeyParameter).getParameters().getP().bitLength() + 7) / 8;
            byte[] bArr = new byte[bitLength];
            byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(((DHPublicKeyParameters) asymmetricKeyParameter).getY());
            if (asUnsignedByteArray.length <= bitLength) {
                System.arraycopy(asUnsignedByteArray, 0, bArr, bitLength - asUnsignedByteArray.length, asUnsignedByteArray.length);
                return bArr;
            }
            throw new IllegalArgumentException("Senders's public key longer than expected.");
        }
    }

    public IESCipher(IESEngine iESEngine) {
        this.f14935a = new BCJcaJceHelper();
        this.d = -1;
        this.e = new ByteArrayOutputStream();
        this.f = null;
        this.g = null;
        this.j = null;
        this.c = iESEngine;
        this.b = 0;
    }

    public IESCipher(IESEngine iESEngine, int i) {
        this.f14935a = new BCJcaJceHelper();
        this.d = -1;
        this.e = new ByteArrayOutputStream();
        this.f = null;
        this.g = null;
        this.j = null;
        this.c = iESEngine;
        this.b = i;
    }

    @Override // javax.crypto.CipherSpi
    public int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        byte[] engineDoFinal = engineDoFinal(bArr, i, i2);
        System.arraycopy(engineDoFinal, 0, bArr2, i3, engineDoFinal.length);
        return engineDoFinal.length;
    }

    @Override // javax.crypto.CipherSpi
    public byte[] engineDoFinal(byte[] bArr, int i, int i2) throws IllegalBlockSizeException, BadPaddingException {
        if (i2 != 0) {
            this.e.write(bArr, i, i2);
        }
        byte[] byteArray = this.e.toByteArray();
        this.e.reset();
        CipherParameters iESWithCipherParameters = new IESWithCipherParameters(this.g.getDerivationV(), this.g.getEncodingV(), this.g.getMacKeySize(), this.g.getCipherKeySize());
        if (this.g.getNonce() != null) {
            iESWithCipherParameters = new ParametersWithIV(iESWithCipherParameters, this.g.getNonce());
        }
        DHParameters parameters = ((DHKeyParameters) this.h).getParameters();
        AsymmetricKeyParameter asymmetricKeyParameter = this.j;
        if (asymmetricKeyParameter != null) {
            try {
                int i3 = this.d;
                if (i3 != 1 && i3 != 3) {
                    this.c.init(false, this.h, asymmetricKeyParameter, iESWithCipherParameters);
                    return this.c.processBlock(byteArray, 0, byteArray.length);
                }
                this.c.init(true, asymmetricKeyParameter, this.h, iESWithCipherParameters);
                return this.c.processBlock(byteArray, 0, byteArray.length);
            } catch (Exception e) {
                throw new BadBlockException("unable to process block", e);
            }
        }
        int i4 = this.d;
        if (i4 == 1 || i4 == 3) {
            DHKeyPairGenerator dHKeyPairGenerator = new DHKeyPairGenerator();
            dHKeyPairGenerator.init(new DHKeyGenerationParameters(this.i, parameters));
            try {
                this.c.init(this.h, iESWithCipherParameters, new EphemeralKeyPairGenerator(dHKeyPairGenerator, new a(this)));
                return this.c.processBlock(byteArray, 0, byteArray.length);
            } catch (Exception e2) {
                throw new BadBlockException("unable to process block", e2);
            }
        } else if (i4 == 2 || i4 == 4) {
            try {
                IESEngine iESEngine = this.c;
                AsymmetricKeyParameter asymmetricKeyParameter2 = this.h;
                iESEngine.init(asymmetricKeyParameter2, iESWithCipherParameters, new DHIESPublicKeyParser(((DHKeyParameters) asymmetricKeyParameter2).getParameters()));
                return this.c.processBlock(byteArray, 0, byteArray.length);
            } catch (InvalidCipherTextException e3) {
                throw new BadBlockException("unable to process block", e3);
            }
        } else {
            throw new IllegalStateException("IESCipher not initialised");
        }
    }

    @Override // javax.crypto.CipherSpi
    public int engineGetBlockSize() {
        if (this.c.getCipher() != null) {
            return this.c.getCipher().getBlockSize();
        }
        return 0;
    }

    @Override // javax.crypto.CipherSpi
    public byte[] engineGetIV() {
        IESParameterSpec iESParameterSpec = this.g;
        if (iESParameterSpec != null) {
            return iESParameterSpec.getNonce();
        }
        return null;
    }

    @Override // javax.crypto.CipherSpi
    public int engineGetKeySize(Key key) {
        if (key instanceof DHKey) {
            return ((DHKey) key).getParams().getP().bitLength();
        }
        throw new IllegalArgumentException("not a DH key");
    }

    @Override // javax.crypto.CipherSpi
    public int engineGetOutputSize(int i) {
        int size;
        BufferedBlockCipher cipher;
        if (this.h != null) {
            int macSize = this.c.getMac().getMacSize();
            int bitLength = this.j == null ? (((((DHKeyParameters) this.h).getParameters().getP().bitLength() + 7) * 2) / 8) + 1 : 0;
            if (this.c.getCipher() != null) {
                int i2 = this.d;
                if (i2 == 1 || i2 == 3) {
                    cipher = this.c.getCipher();
                } else if (i2 != 2 && i2 != 4) {
                    throw new IllegalStateException("cipher not initialised");
                } else {
                    cipher = this.c.getCipher();
                    i = (i - macSize) - bitLength;
                }
                i = cipher.getOutputSize(i);
            }
            int i3 = this.d;
            if (i3 == 1 || i3 == 3) {
                size = this.e.size() + macSize + bitLength;
            } else if (i3 != 2 && i3 != 4) {
                throw new IllegalStateException("IESCipher not initialised");
            } else {
                size = (this.e.size() - macSize) - bitLength;
            }
            return size + i;
        }
        throw new IllegalStateException("cipher not initialised");
    }

    @Override // javax.crypto.CipherSpi
    public AlgorithmParameters engineGetParameters() {
        if (this.f == null && this.g != null) {
            try {
                AlgorithmParameters createAlgorithmParameters = this.f14935a.createAlgorithmParameters("IES");
                this.f = createAlgorithmParameters;
                createAlgorithmParameters.init(this.g);
            } catch (Exception e) {
                throw new RuntimeException(e.toString());
            }
        }
        return this.f;
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        AlgorithmParameterSpec parameterSpec;
        if (algorithmParameters != null) {
            try {
                parameterSpec = algorithmParameters.getParameterSpec(IESParameterSpec.class);
            } catch (Exception e) {
                throw new InvalidAlgorithmParameterException("cannot recognise parameters: " + e.toString());
            }
        } else {
            parameterSpec = null;
        }
        this.f = algorithmParameters;
        engineInit(i, key, parameterSpec, secureRandom);
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int i, Key key, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException("cannot handle supplied parameter spec: " + e.getMessage());
        }
    }

    @Override // javax.crypto.CipherSpi
    public void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException, InvalidKeyException {
        IESParameterSpec iESParameterSpec;
        AsymmetricKeyParameter generatePublicKeyParameter;
        PrivateKey privateKey;
        if (algorithmParameterSpec == null) {
            byte[] bArr = null;
            int i2 = this.b;
            if (i2 != 0 && i == 1) {
                bArr = new byte[i2];
                secureRandom.nextBytes(bArr);
            }
            iESParameterSpec = IESUtil.guessParameterSpec(this.c.getCipher(), bArr);
        } else if (!(algorithmParameterSpec instanceof IESParameterSpec)) {
            throw new InvalidAlgorithmParameterException("must be passed IES parameters");
        } else {
            iESParameterSpec = (IESParameterSpec) algorithmParameterSpec;
        }
        this.g = iESParameterSpec;
        byte[] nonce = this.g.getNonce();
        int i3 = this.b;
        if (i3 != 0 && (nonce == null || nonce.length != i3)) {
            throw new InvalidAlgorithmParameterException("NONCE in IES Parameters needs to be " + this.b + " bytes long");
        }
        if (i == 1 || i == 3) {
            if (!(key instanceof DHPublicKey)) {
                if (!(key instanceof IESKey)) {
                    throw new InvalidKeyException("must be passed recipient's public DH key for encryption");
                }
                IESKey iESKey = (IESKey) key;
                this.h = DHUtil.generatePublicKeyParameter(iESKey.getPublic());
                this.j = DHUtil.generatePrivateKeyParameter(iESKey.getPrivate());
                this.i = secureRandom;
                this.d = i;
                this.e.reset();
            }
            generatePublicKeyParameter = DHUtil.generatePublicKeyParameter((PublicKey) key);
        } else if (i != 2 && i != 4) {
            throw new InvalidKeyException("must be passed EC key");
        } else {
            if (key instanceof DHPrivateKey) {
                privateKey = (PrivateKey) key;
            } else if (!(key instanceof IESKey)) {
                throw new InvalidKeyException("must be passed recipient's private DH key for decryption");
            } else {
                IESKey iESKey2 = (IESKey) key;
                this.j = DHUtil.generatePublicKeyParameter(iESKey2.getPublic());
                privateKey = iESKey2.getPrivate();
            }
            generatePublicKeyParameter = DHUtil.generatePrivateKeyParameter(privateKey);
        }
        this.h = generatePublicKeyParameter;
        this.i = secureRandom;
        this.d = i;
        this.e.reset();
    }

    @Override // javax.crypto.CipherSpi
    public void engineSetMode(String str) throws NoSuchAlgorithmException {
        String upperCase = Strings.toUpperCase(str);
        if (upperCase.equals("NONE") || upperCase.equals("DHAES")) {
            return;
        }
        throw new IllegalArgumentException("can't support mode " + str);
    }

    @Override // javax.crypto.CipherSpi
    public void engineSetPadding(String str) throws NoSuchPaddingException {
        String upperCase = Strings.toUpperCase(str);
        if (!upperCase.equals("NOPADDING") && !upperCase.equals("PKCS5PADDING") && !upperCase.equals("PKCS7PADDING")) {
            throw new NoSuchPaddingException("padding not available with IESCipher");
        }
    }

    @Override // javax.crypto.CipherSpi
    public int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        this.e.write(bArr, i, i2);
        return 0;
    }

    @Override // javax.crypto.CipherSpi
    public byte[] engineUpdate(byte[] bArr, int i, int i2) {
        this.e.write(bArr, i, i2);
        return null;
    }
}
