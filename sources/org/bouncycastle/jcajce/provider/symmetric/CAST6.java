package org.bouncycastle.jcajce.provider.symmetric;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.engines.CAST6Engine;
import org.bouncycastle.crypto.generators.Poly1305KeyGenerator;
import org.bouncycastle.crypto.macs.GMac;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider;
/* loaded from: classes13.dex */
public final class CAST6 {

    /* loaded from: classes13.dex */
    public static class ECB extends BaseBlockCipher {

        /* loaded from: classes13.dex */
        public class a implements BlockCipherProvider {
            @Override // org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider
            public BlockCipher get() {
                return new CAST6Engine();
            }
        }

        public ECB() {
            super(new a());
        }
    }

    /* loaded from: classes13.dex */
    public static class GMAC extends BaseMac {
        public GMAC() {
            super(new GMac(new GCMBlockCipher(new CAST6Engine())));
        }
    }

    /* loaded from: classes13.dex */
    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            super("CAST6", 256, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes13.dex */
    public static class Mappings extends b {

        /* renamed from: a  reason: collision with root package name */
        public static final String f15013a = CAST6.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            StringBuilder sb = new StringBuilder();
            String str = f15013a;
            sb.append(str);
            sb.append("$ECB");
            configurableProvider.addAlgorithm("Cipher.CAST6", sb.toString());
            configurableProvider.addAlgorithm("KeyGenerator.CAST6", str + "$KeyGen");
            addGMacAlgorithm(configurableProvider, "CAST6", str + "$GMAC", str + "$KeyGen");
            addPoly1305Algorithm(configurableProvider, "CAST6", str + "$Poly1305", str + "$Poly1305KeyGen");
        }
    }

    /* loaded from: classes13.dex */
    public static class Poly1305 extends BaseMac {
        public Poly1305() {
            super(new org.bouncycastle.crypto.macs.Poly1305(new CAST6Engine()));
        }
    }

    /* loaded from: classes13.dex */
    public static class Poly1305KeyGen extends BaseKeyGenerator {
        public Poly1305KeyGen() {
            super("Poly1305-CAST6", 256, new Poly1305KeyGenerator());
        }
    }
}
