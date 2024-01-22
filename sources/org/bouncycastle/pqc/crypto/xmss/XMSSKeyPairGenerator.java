package org.bouncycastle.pqc.crypto.xmss;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
import org.bouncycastle.pqc.crypto.xmss.XMSSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSPublicKeyParameters;
/* loaded from: classes13.dex */
public final class XMSSKeyPairGenerator {

    /* renamed from: a  reason: collision with root package name */
    public XMSSParameters f15326a;
    public SecureRandom b;

    public final XMSSPrivateKeyParameters a(XMSSParameters xMSSParameters, SecureRandom secureRandom) {
        int digestSize = xMSSParameters.getDigestSize();
        byte[] bArr = new byte[digestSize];
        secureRandom.nextBytes(bArr);
        byte[] bArr2 = new byte[digestSize];
        secureRandom.nextBytes(bArr2);
        byte[] bArr3 = new byte[digestSize];
        secureRandom.nextBytes(bArr3);
        return new XMSSPrivateKeyParameters.Builder(xMSSParameters).withSecretKeySeed(bArr).withSecretKeyPRF(bArr2).withPublicSeed(bArr3).withBDSState(new BDS(xMSSParameters, bArr3, bArr, (OTSHashAddress) new OTSHashAddress.Builder().build())).build();
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        XMSSPrivateKeyParameters a2 = a(this.f15326a, this.b);
        XMSSNode root = a2.a().getRoot();
        XMSSPrivateKeyParameters build = new XMSSPrivateKeyParameters.Builder(this.f15326a).withSecretKeySeed(a2.getSecretKeySeed()).withSecretKeyPRF(a2.getSecretKeyPRF()).withPublicSeed(a2.getPublicSeed()).withRoot(root.getValue()).withBDSState(a2.a()).build();
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new XMSSPublicKeyParameters.Builder(this.f15326a).withRoot(root.getValue()).withPublicSeed(build.getPublicSeed()).build(), (AsymmetricKeyParameter) build);
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        XMSSKeyGenerationParameters xMSSKeyGenerationParameters = (XMSSKeyGenerationParameters) keyGenerationParameters;
        this.b = xMSSKeyGenerationParameters.getRandom();
        this.f15326a = xMSSKeyGenerationParameters.getParameters();
    }
}
