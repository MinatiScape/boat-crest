package org.bouncycastle.pqc.crypto.xmss;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTPublicKeyParameters;
/* loaded from: classes13.dex */
public final class XMSSMTKeyPairGenerator {

    /* renamed from: a  reason: collision with root package name */
    public XMSSMTParameters f15328a;
    public XMSSParameters b;
    public SecureRandom c;

    public final XMSSMTPrivateKeyParameters a(BDSStateMap bDSStateMap) {
        int digestSize = this.f15328a.getDigestSize();
        byte[] bArr = new byte[digestSize];
        this.c.nextBytes(bArr);
        byte[] bArr2 = new byte[digestSize];
        this.c.nextBytes(bArr2);
        byte[] bArr3 = new byte[digestSize];
        this.c.nextBytes(bArr3);
        return new XMSSMTPrivateKeyParameters.Builder(this.f15328a).withSecretKeySeed(bArr).withSecretKeyPRF(bArr2).withPublicSeed(bArr3).withBDSState(bDSStateMap).build();
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        XMSSMTPrivateKeyParameters a2 = a(new XMSSMTPrivateKeyParameters.Builder(this.f15328a).build().a());
        this.b.c().j(new byte[this.f15328a.getDigestSize()], a2.getPublicSeed());
        int layers = this.f15328a.getLayers() - 1;
        BDS bds = new BDS(this.b, a2.getPublicSeed(), a2.getSecretKeySeed(), (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(layers).build());
        XMSSNode root = bds.getRoot();
        a2.a().put(layers, bds);
        XMSSMTPrivateKeyParameters build = new XMSSMTPrivateKeyParameters.Builder(this.f15328a).withSecretKeySeed(a2.getSecretKeySeed()).withSecretKeyPRF(a2.getSecretKeyPRF()).withPublicSeed(a2.getPublicSeed()).withRoot(root.getValue()).withBDSState(a2.a()).build();
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new XMSSMTPublicKeyParameters.Builder(this.f15328a).withRoot(root.getValue()).withPublicSeed(build.getPublicSeed()).build(), (AsymmetricKeyParameter) build);
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        XMSSMTKeyGenerationParameters xMSSMTKeyGenerationParameters = (XMSSMTKeyGenerationParameters) keyGenerationParameters;
        this.c = xMSSMTKeyGenerationParameters.getRandom();
        XMSSMTParameters parameters = xMSSMTKeyGenerationParameters.getParameters();
        this.f15328a = parameters;
        this.b = parameters.getXMSSParameters();
    }
}
