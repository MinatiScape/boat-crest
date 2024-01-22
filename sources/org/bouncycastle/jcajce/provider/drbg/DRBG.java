package org.bouncycastle.jcajce.provider.drbg;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.SecureRandomSpi;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.crypto.prng.EntropySourceProvider;
import org.bouncycastle.crypto.prng.SP800SecureRandom;
import org.bouncycastle.crypto.prng.SP800SecureRandomBuilder;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.ClassUtil;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.Strings;
/* loaded from: classes13.dex */
public class DRBG {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14991a = "org.bouncycastle.jcajce.provider.drbg.DRBG";
    public static final String[][] b = {new String[]{"sun.security.provider.Sun", "sun.security.provider.SecureRandom"}, new String[]{"org.apache.harmony.security.provider.crypto.CryptoProvider", "org.apache.harmony.security.provider.crypto.SHA1PRNG_SecureRandomImpl"}, new String[]{"com.android.org.conscrypt.OpenSSLProvider", "com.android.org.conscrypt.OpenSSLRandom"}, new String[]{"org.conscrypt.OpenSSLProvider", "org.conscrypt.OpenSSLRandom"}};
    public static final Object[] c = j();

    /* loaded from: classes13.dex */
    public static class Default extends SecureRandomSpi {
        private static final SecureRandom random = DRBG.f(true);

        @Override // java.security.SecureRandomSpi
        public byte[] engineGenerateSeed(int i) {
            return random.generateSeed(i);
        }

        @Override // java.security.SecureRandomSpi
        public void engineNextBytes(byte[] bArr) {
            random.nextBytes(bArr);
        }

        @Override // java.security.SecureRandomSpi
        public void engineSetSeed(byte[] bArr) {
            random.setSeed(bArr);
        }
    }

    /* loaded from: classes13.dex */
    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("SecureRandom.DEFAULT", DRBG.f14991a + "$Default");
            configurableProvider.addAlgorithm("SecureRandom.NONCEANDIV", DRBG.f14991a + "$NonceAndIV");
        }
    }

    /* loaded from: classes13.dex */
    public static class NonceAndIV extends SecureRandomSpi {
        private static final SecureRandom random = DRBG.f(false);

        @Override // java.security.SecureRandomSpi
        public byte[] engineGenerateSeed(int i) {
            return random.generateSeed(i);
        }

        @Override // java.security.SecureRandomSpi
        public void engineNextBytes(byte[] bArr) {
            random.nextBytes(bArr);
        }

        @Override // java.security.SecureRandomSpi
        public void engineSetSeed(byte[] bArr) {
            random.setSeed(bArr);
        }
    }

    /* loaded from: classes13.dex */
    public static class a implements PrivilegedAction<Boolean> {
        @Override // java.security.PrivilegedAction
        /* renamed from: a */
        public Boolean run() {
            try {
                return Boolean.valueOf(SecureRandom.class.getMethod("getInstanceStrong", new Class[0]) != null);
            } catch (Exception unused) {
                return Boolean.FALSE;
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class b implements PrivilegedAction<SecureRandom> {
        @Override // java.security.PrivilegedAction
        /* renamed from: a */
        public SecureRandom run() {
            try {
                return (SecureRandom) SecureRandom.class.getMethod("getInstanceStrong", new Class[0]).invoke(null, new Object[0]);
            } catch (Exception unused) {
                return DRBG.b();
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class c implements PrivilegedAction<EntropySourceProvider> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f14992a;

        public c(String str) {
            this.f14992a = str;
        }

        @Override // java.security.PrivilegedAction
        /* renamed from: a */
        public EntropySourceProvider run() {
            try {
                return (EntropySourceProvider) ClassUtil.loadClass(DRBG.class, this.f14992a).newInstance();
            } catch (Exception e) {
                throw new IllegalStateException("entropy source " + this.f14992a + " not created: " + e.getMessage(), e);
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class d extends SecureRandom {
        public d() {
            super((SecureRandomSpi) DRBG.c[1], (Provider) DRBG.c[0]);
        }
    }

    /* loaded from: classes13.dex */
    public static class e extends SecureRandom {
        private final SecureRandom baseRandom;
        private final SP800SecureRandom drbg;
        private final AtomicInteger samples;
        private final AtomicBoolean seedAvailable;

        /* loaded from: classes13.dex */
        public class a implements EntropySourceProvider {
            public a() {
            }

            @Override // org.bouncycastle.crypto.prng.EntropySourceProvider
            public EntropySource get(int i) {
                return new b(i);
            }
        }

        /* loaded from: classes13.dex */
        public class b implements EntropySource {

            /* renamed from: a  reason: collision with root package name */
            public final int f14994a;
            public final AtomicReference b = new AtomicReference();
            public final AtomicBoolean c = new AtomicBoolean(false);

            /* loaded from: classes13.dex */
            public class a implements Runnable {
                public final int h;

                public a(int i) {
                    this.h = i;
                }

                @Override // java.lang.Runnable
                public void run() {
                    b.this.b.set(e.this.baseRandom.generateSeed(this.h));
                    e.this.seedAvailable.set(true);
                }
            }

            public b(int i) {
                this.f14994a = (i + 7) / 8;
            }

            @Override // org.bouncycastle.crypto.prng.EntropySource
            public int entropySize() {
                return this.f14994a * 8;
            }

            @Override // org.bouncycastle.crypto.prng.EntropySource
            public byte[] getEntropy() {
                byte[] bArr = (byte[]) this.b.getAndSet(null);
                if (bArr == null || bArr.length != this.f14994a) {
                    bArr = e.this.baseRandom.generateSeed(this.f14994a);
                } else {
                    this.c.set(false);
                }
                if (!this.c.getAndSet(true)) {
                    new Thread(new a(this.f14994a)).start();
                }
                return bArr;
            }

            @Override // org.bouncycastle.crypto.prng.EntropySource
            public boolean isPredictionResistant() {
                return true;
            }
        }

        public e() {
            super(null, null);
            this.seedAvailable = new AtomicBoolean(false);
            this.samples = new AtomicInteger(0);
            SecureRandom e = DRBG.e();
            this.baseRandom = e;
            this.drbg = new SP800SecureRandomBuilder(new a()).setPersonalizationString(Strings.toByteArray("Bouncy Castle Hybrid Entropy Source")).buildHMAC(new HMac(new SHA512Digest()), e.generateSeed(32), false);
        }

        @Override // java.security.SecureRandom
        public byte[] generateSeed(int i) {
            byte[] bArr = new byte[i];
            if (this.samples.getAndIncrement() > 20 && this.seedAvailable.getAndSet(false)) {
                this.samples.set(0);
                this.drbg.reseed(null);
            }
            this.drbg.nextBytes(bArr);
            return bArr;
        }

        @Override // java.security.SecureRandom, java.util.Random
        public void setSeed(long j) {
            SP800SecureRandom sP800SecureRandom = this.drbg;
            if (sP800SecureRandom != null) {
                sP800SecureRandom.setSeed(j);
            }
        }

        @Override // java.security.SecureRandom
        public void setSeed(byte[] bArr) {
            SP800SecureRandom sP800SecureRandom = this.drbg;
            if (sP800SecureRandom != null) {
                sP800SecureRandom.setSeed(bArr);
            }
        }
    }

    public static /* synthetic */ SecureRandom b() {
        return g();
    }

    public static /* synthetic */ SecureRandom e() {
        return i();
    }

    public static SecureRandom f(boolean z) {
        if (System.getProperty("org.bouncycastle.drbg.entropysource") == null) {
            e eVar = new e();
            byte[] generateSeed = eVar.generateSeed(16);
            return new SP800SecureRandomBuilder(eVar, true).setPersonalizationString(z ? k(generateSeed) : l(generateSeed)).buildHash(new SHA512Digest(), eVar.generateSeed(32), z);
        }
        EntropySourceProvider h = h();
        EntropySource entropySource = h.get(128);
        byte[] entropy = entropySource.getEntropy();
        return new SP800SecureRandomBuilder(h).setPersonalizationString(z ? k(entropy) : l(entropy)).buildHash(new SHA512Digest(), Arrays.concatenate(entropySource.getEntropy(), entropySource.getEntropy()), z);
    }

    public static SecureRandom g() {
        return c != null ? new d() : new SecureRandom();
    }

    public static EntropySourceProvider h() {
        return (EntropySourceProvider) AccessController.doPrivileged(new c(System.getProperty("org.bouncycastle.drbg.entropysource")));
    }

    public static SecureRandom i() {
        return ((Boolean) AccessController.doPrivileged(new a())).booleanValue() ? (SecureRandom) AccessController.doPrivileged(new b()) : g();
    }

    public static final Object[] j() {
        int i = 0;
        while (true) {
            String[][] strArr = b;
            if (i >= strArr.length) {
                return null;
            }
            String[] strArr2 = strArr[i];
            try {
                return new Object[]{Class.forName(strArr2[0]).newInstance(), Class.forName(strArr2[1]).newInstance()};
            } catch (Throwable unused) {
                i++;
            }
        }
    }

    public static byte[] k(byte[] bArr) {
        return Arrays.concatenate(Strings.toByteArray("Default"), bArr, Pack.longToBigEndian(Thread.currentThread().getId()), Pack.longToBigEndian(System.currentTimeMillis()));
    }

    public static byte[] l(byte[] bArr) {
        return Arrays.concatenate(Strings.toByteArray("Nonce"), bArr, Pack.longToLittleEndian(Thread.currentThread().getId()), Pack.longToLittleEndian(System.currentTimeMillis()));
    }
}
