package org.bouncycastle.jcajce.provider.symmetric.util;

import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.RC5ParameterSpec;
import org.bouncycastle.asn1.cms.GCMParameters;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.engines.DSTU7624Engine;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.modes.CCMBlockCipher;
import org.bouncycastle.crypto.modes.CFBBlockCipher;
import org.bouncycastle.crypto.modes.CTSBlockCipher;
import org.bouncycastle.crypto.modes.EAXBlockCipher;
import org.bouncycastle.crypto.modes.GCFBBlockCipher;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.modes.GOFBBlockCipher;
import org.bouncycastle.crypto.modes.KCCMBlockCipher;
import org.bouncycastle.crypto.modes.KCTRBlockCipher;
import org.bouncycastle.crypto.modes.KGCMBlockCipher;
import org.bouncycastle.crypto.modes.OCBBlockCipher;
import org.bouncycastle.crypto.modes.OFBBlockCipher;
import org.bouncycastle.crypto.modes.OpenPGPCFBBlockCipher;
import org.bouncycastle.crypto.modes.PGPCFBBlockCipher;
import org.bouncycastle.crypto.modes.SICBlockCipher;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;
import org.bouncycastle.crypto.paddings.ISO10126d2Padding;
import org.bouncycastle.crypto.paddings.ISO7816d4Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.paddings.TBCPadding;
import org.bouncycastle.crypto.paddings.X923Padding;
import org.bouncycastle.crypto.paddings.ZeroBytePadding;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithSBox;
import org.bouncycastle.jcajce.spec.GOST28147ParameterSpec;
import org.bouncycastle.util.Strings;
/* loaded from: classes13.dex */
public class BaseBlockCipher extends BaseWrapCipher {
    public static final Class A = ClassUtil.loadClass(BaseBlockCipher.class, "javax.crypto.spec.GCMParameterSpec");
    public Class[] l;
    public BlockCipher m;
    public BlockCipherProvider n;
    public c o;
    public ParametersWithIV p;
    public AEADParameters q;
    public int r;
    public int s;
    public int t;
    public int u;
    public boolean v;
    public boolean w;
    public PBEParameterSpec x;
    public String y;
    public String z;

    /* loaded from: classes13.dex */
    public static class a implements c {
        public static final Constructor b;

        /* renamed from: a  reason: collision with root package name */
        public AEADBlockCipher f15063a;

        static {
            Class loadClass = ClassUtil.loadClass(BaseBlockCipher.class, "javax.crypto.AEADBadTagException");
            b = loadClass != null ? d(loadClass) : null;
        }

        public a(AEADBlockCipher aEADBlockCipher) {
            this.f15063a = aEADBlockCipher;
        }

        public static Constructor d(Class cls) {
            try {
                return cls.getConstructor(String.class);
            } catch (Exception unused) {
                return null;
            }
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.c
        public boolean a() {
            return false;
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.c
        public void b(byte[] bArr, int i, int i2) {
            this.f15063a.processAADBytes(bArr, i, i2);
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.c
        public int doFinal(byte[] bArr, int i) throws IllegalStateException, BadPaddingException {
            try {
                return this.f15063a.doFinal(bArr, i);
            } catch (InvalidCipherTextException e) {
                Constructor constructor = b;
                if (constructor != null) {
                    BadPaddingException badPaddingException = null;
                    try {
                        badPaddingException = (BadPaddingException) constructor.newInstance(e.getMessage());
                    } catch (Exception unused) {
                    }
                    if (badPaddingException != null) {
                        throw badPaddingException;
                    }
                }
                throw new BadPaddingException(e.getMessage());
            }
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.c
        public String getAlgorithmName() {
            return this.f15063a.getUnderlyingCipher().getAlgorithmName();
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.c
        public int getOutputSize(int i) {
            return this.f15063a.getOutputSize(i);
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.c
        public BlockCipher getUnderlyingCipher() {
            return this.f15063a.getUnderlyingCipher();
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.c
        public int getUpdateOutputSize(int i) {
            return this.f15063a.getUpdateOutputSize(i);
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.c
        public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
            this.f15063a.init(z, cipherParameters);
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.c
        public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
            return this.f15063a.processBytes(bArr, i, i2, bArr2, i3);
        }
    }

    /* loaded from: classes13.dex */
    public static class b implements c {

        /* renamed from: a  reason: collision with root package name */
        public BufferedBlockCipher f15064a;

        public b(BlockCipher blockCipher) {
            this.f15064a = new PaddedBufferedBlockCipher(blockCipher);
        }

        public b(BlockCipher blockCipher, BlockCipherPadding blockCipherPadding) {
            this.f15064a = new PaddedBufferedBlockCipher(blockCipher, blockCipherPadding);
        }

        public b(BufferedBlockCipher bufferedBlockCipher) {
            this.f15064a = bufferedBlockCipher;
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.c
        public boolean a() {
            return !(this.f15064a instanceof CTSBlockCipher);
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.c
        public void b(byte[] bArr, int i, int i2) {
            throw new UnsupportedOperationException("AAD is not supported in the current mode.");
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.c
        public int doFinal(byte[] bArr, int i) throws IllegalStateException, BadPaddingException {
            try {
                return this.f15064a.doFinal(bArr, i);
            } catch (InvalidCipherTextException e) {
                throw new BadPaddingException(e.getMessage());
            }
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.c
        public String getAlgorithmName() {
            return this.f15064a.getUnderlyingCipher().getAlgorithmName();
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.c
        public int getOutputSize(int i) {
            return this.f15064a.getOutputSize(i);
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.c
        public BlockCipher getUnderlyingCipher() {
            return this.f15064a.getUnderlyingCipher();
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.c
        public int getUpdateOutputSize(int i) {
            return this.f15064a.getUpdateOutputSize(i);
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.c
        public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
            this.f15064a.init(z, cipherParameters);
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.c
        public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
            return this.f15064a.processBytes(bArr, i, i2, bArr2, i3);
        }
    }

    /* loaded from: classes13.dex */
    public interface c {
        boolean a();

        void b(byte[] bArr, int i, int i2);

        int doFinal(byte[] bArr, int i) throws IllegalStateException, BadPaddingException;

        String getAlgorithmName();

        int getOutputSize(int i);

        BlockCipher getUnderlyingCipher();

        int getUpdateOutputSize(int i);

        void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException;

        int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException;
    }

    /* loaded from: classes13.dex */
    public static class d extends InvalidKeyException {
        private final Throwable cause;

        public d(String str, Throwable th) {
            super(str);
            this.cause = th;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }
    }

    public BaseBlockCipher(BlockCipher blockCipher) {
        this.l = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, A, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.s = -1;
        this.u = 0;
        this.w = true;
        this.x = null;
        this.y = null;
        this.z = null;
        this.m = blockCipher;
        this.o = new b(blockCipher);
    }

    public BaseBlockCipher(BlockCipher blockCipher, int i) {
        this(blockCipher, true, i);
    }

    public BaseBlockCipher(BlockCipher blockCipher, int i, int i2, int i3, int i4) {
        this.l = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, A, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.s = -1;
        this.u = 0;
        this.w = true;
        this.x = null;
        this.y = null;
        this.z = null;
        this.m = blockCipher;
        this.s = i;
        this.t = i2;
        this.r = i3;
        this.u = i4;
        this.o = new b(blockCipher);
    }

    public BaseBlockCipher(BlockCipher blockCipher, boolean z, int i) {
        this.l = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, A, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.s = -1;
        this.u = 0;
        this.w = true;
        this.x = null;
        this.y = null;
        this.z = null;
        this.m = blockCipher;
        this.w = z;
        this.o = new b(blockCipher);
        this.u = i / 8;
    }

    public BaseBlockCipher(BufferedBlockCipher bufferedBlockCipher, int i) {
        this(bufferedBlockCipher, true, i);
    }

    public BaseBlockCipher(BufferedBlockCipher bufferedBlockCipher, boolean z, int i) {
        this.l = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, A, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.s = -1;
        this.u = 0;
        this.w = true;
        this.x = null;
        this.y = null;
        this.z = null;
        this.m = bufferedBlockCipher.getUnderlyingCipher();
        this.o = new b(bufferedBlockCipher);
        this.w = z;
        this.u = i / 8;
    }

    public BaseBlockCipher(AEADBlockCipher aEADBlockCipher) {
        this.l = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, A, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.s = -1;
        this.u = 0;
        this.w = true;
        this.x = null;
        this.y = null;
        this.z = null;
        BlockCipher underlyingCipher = aEADBlockCipher.getUnderlyingCipher();
        this.m = underlyingCipher;
        this.u = underlyingCipher.getBlockSize();
        this.o = new a(aEADBlockCipher);
    }

    public BaseBlockCipher(AEADBlockCipher aEADBlockCipher, boolean z, int i) {
        this.l = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, A, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.s = -1;
        this.u = 0;
        this.w = true;
        this.x = null;
        this.y = null;
        this.z = null;
        this.m = aEADBlockCipher.getUnderlyingCipher();
        this.w = z;
        this.u = i;
        this.o = new a(aEADBlockCipher);
    }

    public BaseBlockCipher(BlockCipherProvider blockCipherProvider) {
        this.l = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, A, GOST28147ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.s = -1;
        this.u = 0;
        this.w = true;
        this.x = null;
        this.y = null;
        this.z = null;
        this.m = blockCipherProvider.get();
        this.n = blockCipherProvider;
        this.o = new b(blockCipherProvider.get());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final CipherParameters a(AlgorithmParameterSpec algorithmParameterSpec, CipherParameters cipherParameters) {
        ParametersWithSBox parametersWithSBox;
        ParametersWithIV parametersWithIV;
        if (cipherParameters instanceof ParametersWithIV) {
            CipherParameters parameters = ((ParametersWithIV) cipherParameters).getParameters();
            if (algorithmParameterSpec instanceof IvParameterSpec) {
                parametersWithIV = new ParametersWithIV(parameters, ((IvParameterSpec) algorithmParameterSpec).getIV());
            } else if (!(algorithmParameterSpec instanceof GOST28147ParameterSpec)) {
                return cipherParameters;
            } else {
                GOST28147ParameterSpec gOST28147ParameterSpec = (GOST28147ParameterSpec) algorithmParameterSpec;
                ParametersWithSBox parametersWithSBox2 = new ParametersWithSBox(cipherParameters, gOST28147ParameterSpec.getSbox());
                if (gOST28147ParameterSpec.getIV() == null || this.u == 0) {
                    return parametersWithSBox2;
                }
                parametersWithIV = new ParametersWithIV(parameters, gOST28147ParameterSpec.getIV());
            }
            this.p = parametersWithIV;
            return parametersWithIV;
        }
        if (algorithmParameterSpec instanceof IvParameterSpec) {
            ParametersWithIV parametersWithIV2 = new ParametersWithIV(cipherParameters, ((IvParameterSpec) algorithmParameterSpec).getIV());
            this.p = parametersWithIV2;
            parametersWithSBox = parametersWithIV2;
        } else if (!(algorithmParameterSpec instanceof GOST28147ParameterSpec)) {
            return cipherParameters;
        } else {
            GOST28147ParameterSpec gOST28147ParameterSpec2 = (GOST28147ParameterSpec) algorithmParameterSpec;
            ParametersWithSBox parametersWithSBox3 = new ParametersWithSBox(cipherParameters, gOST28147ParameterSpec2.getSbox());
            parametersWithSBox = parametersWithSBox3;
            if (gOST28147ParameterSpec2.getIV() != null) {
                parametersWithSBox = parametersWithSBox3;
                if (this.u != 0) {
                    return new ParametersWithIV(parametersWithSBox3, gOST28147ParameterSpec2.getIV());
                }
            }
        }
        return parametersWithSBox;
    }

    public final boolean b(String str) {
        return "CCM".equals(str) || "EAX".equals(str) || "GCM".equals(str) || "OCB".equals(str);
    }

    @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws IllegalBlockSizeException, BadPaddingException, ShortBufferException {
        int processBytes;
        if (engineGetOutputSize(i2) + i3 <= bArr2.length) {
            if (i2 != 0) {
                try {
                    processBytes = this.o.processBytes(bArr, i, i2, bArr2, i3);
                } catch (OutputLengthException e) {
                    throw new IllegalBlockSizeException(e.getMessage());
                } catch (DataLengthException e2) {
                    throw new IllegalBlockSizeException(e2.getMessage());
                }
            } else {
                processBytes = 0;
            }
            return processBytes + this.o.doFinal(bArr2, i3 + processBytes);
        }
        throw new ShortBufferException("output buffer too short for input.");
    }

    @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public byte[] engineDoFinal(byte[] bArr, int i, int i2) throws IllegalBlockSizeException, BadPaddingException {
        int engineGetOutputSize = engineGetOutputSize(i2);
        byte[] bArr2 = new byte[engineGetOutputSize];
        int processBytes = i2 != 0 ? this.o.processBytes(bArr, i, i2, bArr2, 0) : 0;
        try {
            int doFinal = processBytes + this.o.doFinal(bArr2, processBytes);
            if (doFinal == engineGetOutputSize) {
                return bArr2;
            }
            byte[] bArr3 = new byte[doFinal];
            System.arraycopy(bArr2, 0, bArr3, 0, doFinal);
            return bArr3;
        } catch (DataLengthException e) {
            throw new IllegalBlockSizeException(e.getMessage());
        }
    }

    @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public int engineGetBlockSize() {
        return this.m.getBlockSize();
    }

    @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public byte[] engineGetIV() {
        AEADParameters aEADParameters = this.q;
        if (aEADParameters != null) {
            return aEADParameters.getNonce();
        }
        ParametersWithIV parametersWithIV = this.p;
        if (parametersWithIV != null) {
            return parametersWithIV.getIV();
        }
        return null;
    }

    @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public int engineGetKeySize(Key key) {
        return key.getEncoded().length * 8;
    }

    @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public int engineGetOutputSize(int i) {
        return this.o.getOutputSize(i);
    }

    @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public AlgorithmParameters engineGetParameters() {
        if (this.engineParams == null) {
            if (this.x != null) {
                try {
                    AlgorithmParameters createParametersInstance = createParametersInstance(this.y);
                    this.engineParams = createParametersInstance;
                    createParametersInstance.init(this.x);
                } catch (Exception unused) {
                    return null;
                }
            } else if (this.q != null) {
                try {
                    AlgorithmParameters createParametersInstance2 = createParametersInstance("GCM");
                    this.engineParams = createParametersInstance2;
                    createParametersInstance2.init(new GCMParameters(this.q.getNonce(), this.q.getMacSize() / 8).getEncoded());
                } catch (Exception e) {
                    throw new RuntimeException(e.toString());
                }
            } else if (this.p != null) {
                String algorithmName = this.o.getUnderlyingCipher().getAlgorithmName();
                if (algorithmName.indexOf(47) >= 0) {
                    algorithmName = algorithmName.substring(0, algorithmName.indexOf(47));
                }
                try {
                    AlgorithmParameters createParametersInstance3 = createParametersInstance(algorithmName);
                    this.engineParams = createParametersInstance3;
                    createParametersInstance3.init(new IvParameterSpec(this.p.getIV()));
                } catch (Exception e2) {
                    throw new RuntimeException(e2.toString());
                }
            }
        }
        return this.engineParams;
    }

    @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        AlgorithmParameterSpec algorithmParameterSpec = null;
        if (algorithmParameters != null) {
            int i2 = 0;
            while (true) {
                Class[] clsArr = this.l;
                if (i2 == clsArr.length) {
                    break;
                }
                if (clsArr[i2] != null) {
                    try {
                        algorithmParameterSpec = algorithmParameters.getParameterSpec(clsArr[i2]);
                        break;
                    } catch (Exception unused) {
                        i2++;
                    }
                }
                i2++;
            }
            if (algorithmParameterSpec == null) {
                throw new InvalidAlgorithmParameterException("can't handle parameter " + algorithmParameters.toString());
            }
        }
        engineInit(i, key, algorithmParameterSpec, secureRandom);
        this.engineParams = algorithmParameters;
    }

    @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public void engineInit(int i, Key key, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            throw new InvalidKeyException(e.getMessage());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x01fb, code lost:
        if (r7 != false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00aa, code lost:
        if (r7 != false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00ac, code lost:
        r20.p = (org.bouncycastle.crypto.params.ParametersWithIV) r5;
        r5 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00f7, code lost:
        if (r7 != false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0143, code lost:
        if (r7 != false) goto L31;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v70, types: [org.bouncycastle.crypto.params.ParametersWithIV] */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v10, types: [org.bouncycastle.crypto.CipherParameters] */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v16, types: [org.bouncycastle.crypto.params.RC5Parameters, org.bouncycastle.crypto.CipherParameters] */
    /* JADX WARN: Type inference failed for: r5v19, types: [org.bouncycastle.crypto.params.RC2Parameters, org.bouncycastle.crypto.CipherParameters] */
    /* JADX WARN: Type inference failed for: r5v22 */
    /* JADX WARN: Type inference failed for: r5v23, types: [org.bouncycastle.crypto.params.ParametersWithSBox, org.bouncycastle.crypto.CipherParameters] */
    /* JADX WARN: Type inference failed for: r5v26, types: [org.bouncycastle.crypto.params.ParametersWithIV] */
    /* JADX WARN: Type inference failed for: r5v29, types: [org.bouncycastle.crypto.CipherParameters] */
    /* JADX WARN: Type inference failed for: r5v34, types: [org.bouncycastle.crypto.params.AEADParameters] */
    /* JADX WARN: Type inference failed for: r5v60 */
    /* JADX WARN: Type inference failed for: r5v61 */
    /* JADX WARN: Type inference failed for: r5v62 */
    /* JADX WARN: Type inference failed for: r5v63 */
    /* JADX WARN: Type inference failed for: r5v64 */
    /* JADX WARN: Type inference failed for: r5v65 */
    @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void engineInit(int r21, java.security.Key r22, java.security.spec.AlgorithmParameterSpec r23, java.security.SecureRandom r24) throws java.security.InvalidKeyException, java.security.InvalidAlgorithmParameterException {
        /*
            Method dump skipped, instructions count: 1318
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher.engineInit(int, java.security.Key, java.security.spec.AlgorithmParameterSpec, java.security.SecureRandom):void");
    }

    @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public void engineSetMode(String str) throws NoSuchAlgorithmException {
        c aVar;
        b bVar;
        String upperCase = Strings.toUpperCase(str);
        this.z = upperCase;
        if (upperCase.equals("ECB")) {
            this.u = 0;
            aVar = new b(this.m);
        } else if (this.z.equals("CBC")) {
            this.u = this.m.getBlockSize();
            aVar = new b(new CBCBlockCipher(this.m));
        } else if (this.z.startsWith("OFB")) {
            this.u = this.m.getBlockSize();
            if (this.z.length() != 3) {
                bVar = new b(new OFBBlockCipher(this.m, Integer.parseInt(this.z.substring(3))));
                this.o = bVar;
                return;
            }
            BlockCipher blockCipher = this.m;
            aVar = new b(new OFBBlockCipher(blockCipher, blockCipher.getBlockSize() * 8));
        } else if (!this.z.startsWith("CFB")) {
            if (this.z.startsWith("PGP")) {
                boolean equalsIgnoreCase = this.z.equalsIgnoreCase("PGPCFBwithIV");
                this.u = this.m.getBlockSize();
                bVar = new b(new PGPCFBBlockCipher(this.m, equalsIgnoreCase));
            } else if (this.z.equalsIgnoreCase("OpenPGPCFB")) {
                this.u = 0;
                aVar = new b(new OpenPGPCFBBlockCipher(this.m));
            } else if (this.z.startsWith("SIC")) {
                int blockSize = this.m.getBlockSize();
                this.u = blockSize;
                if (blockSize < 16) {
                    throw new IllegalArgumentException("Warning: SIC-Mode can become a twotime-pad if the blocksize of the cipher is too small. Use a cipher with a block size of at least 128 bits (e.g. AES)");
                }
                this.w = false;
                aVar = new b(new BufferedBlockCipher(new SICBlockCipher(this.m)));
            } else if (this.z.startsWith("CTR")) {
                this.u = this.m.getBlockSize();
                this.w = false;
                BlockCipher blockCipher2 = this.m;
                bVar = blockCipher2 instanceof DSTU7624Engine ? new b(new BufferedBlockCipher(new KCTRBlockCipher(blockCipher2))) : new b(new BufferedBlockCipher(new SICBlockCipher(blockCipher2)));
            } else if (this.z.startsWith("GOFB")) {
                this.u = this.m.getBlockSize();
                aVar = new b(new BufferedBlockCipher(new GOFBBlockCipher(this.m)));
            } else if (this.z.startsWith("GCFB")) {
                this.u = this.m.getBlockSize();
                aVar = new b(new BufferedBlockCipher(new GCFBBlockCipher(this.m)));
            } else if (this.z.startsWith("CTS")) {
                this.u = this.m.getBlockSize();
                aVar = new b(new CTSBlockCipher(new CBCBlockCipher(this.m)));
            } else if (this.z.startsWith("CCM")) {
                this.u = 12;
                aVar = this.m instanceof DSTU7624Engine ? new a(new KCCMBlockCipher(this.m)) : new a(new CCMBlockCipher(this.m));
            } else if (this.z.startsWith("OCB")) {
                if (this.n == null) {
                    throw new NoSuchAlgorithmException("can't support mode " + str);
                }
                this.u = 15;
                aVar = new a(new OCBBlockCipher(this.m, this.n.get()));
            } else if (this.z.startsWith("EAX")) {
                this.u = this.m.getBlockSize();
                aVar = new a(new EAXBlockCipher(this.m));
            } else if (!this.z.startsWith("GCM")) {
                throw new NoSuchAlgorithmException("can't support mode " + str);
            } else {
                this.u = this.m.getBlockSize();
                aVar = this.m instanceof DSTU7624Engine ? new a(new KGCMBlockCipher(this.m)) : new a(new GCMBlockCipher(this.m));
            }
            this.o = bVar;
            return;
        } else {
            this.u = this.m.getBlockSize();
            if (this.z.length() != 3) {
                bVar = new b(new CFBBlockCipher(this.m, Integer.parseInt(this.z.substring(3))));
                this.o = bVar;
                return;
            }
            BlockCipher blockCipher3 = this.m;
            aVar = new b(new CFBBlockCipher(blockCipher3, blockCipher3.getBlockSize() * 8));
        }
        this.o = aVar;
    }

    @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public void engineSetPadding(String str) throws NoSuchPaddingException {
        b bVar;
        String upperCase = Strings.toUpperCase(str);
        if (upperCase.equals("NOPADDING")) {
            if (!this.o.a()) {
                return;
            }
            bVar = new b(new BufferedBlockCipher(this.o.getUnderlyingCipher()));
        } else if (upperCase.equals("WITHCTS")) {
            bVar = new b(new CTSBlockCipher(this.o.getUnderlyingCipher()));
        } else {
            this.v = true;
            if (b(this.z)) {
                throw new NoSuchPaddingException("Only NoPadding can be used with AEAD modes.");
            }
            if (upperCase.equals("PKCS5PADDING") || upperCase.equals("PKCS7PADDING")) {
                bVar = new b(this.o.getUnderlyingCipher());
            } else if (upperCase.equals("ZEROBYTEPADDING")) {
                bVar = new b(this.o.getUnderlyingCipher(), new ZeroBytePadding());
            } else if (upperCase.equals("ISO10126PADDING") || upperCase.equals("ISO10126-2PADDING")) {
                bVar = new b(this.o.getUnderlyingCipher(), new ISO10126d2Padding());
            } else if (upperCase.equals("X9.23PADDING") || upperCase.equals("X923PADDING")) {
                bVar = new b(this.o.getUnderlyingCipher(), new X923Padding());
            } else if (upperCase.equals("ISO7816-4PADDING") || upperCase.equals("ISO9797-1PADDING")) {
                bVar = new b(this.o.getUnderlyingCipher(), new ISO7816d4Padding());
            } else if (!upperCase.equals("TBCPADDING")) {
                throw new NoSuchPaddingException("Padding " + str + " unknown.");
            } else {
                bVar = new b(this.o.getUnderlyingCipher(), new TBCPadding());
            }
        }
        this.o = bVar;
    }

    @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws ShortBufferException {
        if (this.o.getUpdateOutputSize(i2) + i3 <= bArr2.length) {
            try {
                return this.o.processBytes(bArr, i, i2, bArr2, i3);
            } catch (DataLengthException e) {
                throw new IllegalStateException(e.toString());
            }
        }
        throw new ShortBufferException("output buffer too short for input.");
    }

    @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher, javax.crypto.CipherSpi
    public byte[] engineUpdate(byte[] bArr, int i, int i2) {
        int updateOutputSize = this.o.getUpdateOutputSize(i2);
        if (updateOutputSize <= 0) {
            this.o.processBytes(bArr, i, i2, null, 0);
            return null;
        }
        byte[] bArr2 = new byte[updateOutputSize];
        int processBytes = this.o.processBytes(bArr, i, i2, bArr2, 0);
        if (processBytes == 0) {
            return null;
        }
        if (processBytes != updateOutputSize) {
            byte[] bArr3 = new byte[processBytes];
            System.arraycopy(bArr2, 0, bArr3, 0, processBytes);
            return bArr3;
        }
        return bArr2;
    }

    @Override // javax.crypto.CipherSpi
    public void engineUpdateAAD(ByteBuffer byteBuffer) {
        engineUpdateAAD(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.limit() - byteBuffer.position());
    }

    @Override // javax.crypto.CipherSpi
    public void engineUpdateAAD(byte[] bArr, int i, int i2) {
        this.o.b(bArr, i, i2);
    }
}
