package org.bouncycastle.pqc.crypto.xmss;

import java.util.Objects;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.StateAwareMessageSigner;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
import org.bouncycastle.pqc.crypto.xmss.XMSSSignature;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class XMSSSigner implements StateAwareMessageSigner {

    /* renamed from: a  reason: collision with root package name */
    public XMSSPrivateKeyParameters f15338a;
    public XMSSPrivateKeyParameters b;
    public XMSSPublicKeyParameters c;
    public XMSSParameters d;
    public b e;
    public boolean f;
    public boolean g;

    public final g a(byte[] bArr, OTSHashAddress oTSHashAddress) {
        if (bArr.length == this.d.getDigestSize()) {
            Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
            this.d.c().j(this.d.c().i(this.f15338a.getSecretKeySeed(), oTSHashAddress), this.f15338a.getPublicSeed());
            return this.d.c().k(bArr, oTSHashAddress);
        }
        throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        Objects.requireNonNull(bArr, "message == null");
        if (this.f) {
            XMSSPrivateKeyParameters xMSSPrivateKeyParameters = this.f15338a;
            if (xMSSPrivateKeyParameters != null) {
                if (xMSSPrivateKeyParameters.a().getAuthenticationPath().isEmpty()) {
                    throw new IllegalStateException("not initialized");
                }
                int index = this.f15338a.getIndex();
                long j = index;
                if (XMSSUtil.isIndexValid(this.d.getHeight(), j)) {
                    byte[] d = this.e.d(this.f15338a.getSecretKeyPRF(), XMSSUtil.toBytesBigEndian(j, 32));
                    XMSSSignature xMSSSignature = (XMSSSignature) new XMSSSignature.Builder(this.d).withIndex(index).withRandom(d).withWOTSPlusSignature(a(this.e.c(Arrays.concatenate(d, this.f15338a.getRoot(), XMSSUtil.toBytesBigEndian(j, this.d.getDigestSize())), bArr), (OTSHashAddress) new OTSHashAddress.Builder().withOTSAddress(index).build())).withAuthPath(this.f15338a.a().getAuthenticationPath()).build();
                    this.g = true;
                    XMSSPrivateKeyParameters xMSSPrivateKeyParameters2 = this.b;
                    if (xMSSPrivateKeyParameters2 != null) {
                        XMSSPrivateKeyParameters nextKey = xMSSPrivateKeyParameters2.getNextKey();
                        this.f15338a = nextKey;
                        this.b = nextKey;
                    } else {
                        this.f15338a = null;
                    }
                    return xMSSSignature.toByteArray();
                }
                throw new IllegalStateException("index out of bounds");
            }
            throw new IllegalStateException("signing key no longer usable");
        }
        throw new IllegalStateException("signer not initialized for signature generation");
    }

    @Override // org.bouncycastle.pqc.crypto.StateAwareMessageSigner
    public AsymmetricKeyParameter getUpdatedPrivateKey() {
        XMSSPrivateKeyParameters nextKey;
        if (this.g) {
            nextKey = this.f15338a;
            this.f15338a = null;
        } else {
            nextKey = this.b.getNextKey();
        }
        this.b = null;
        return nextKey;
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        XMSSParameters parameters;
        if (z) {
            this.f = true;
            this.g = false;
            XMSSPrivateKeyParameters xMSSPrivateKeyParameters = (XMSSPrivateKeyParameters) cipherParameters;
            this.f15338a = xMSSPrivateKeyParameters;
            this.b = xMSSPrivateKeyParameters;
            parameters = xMSSPrivateKeyParameters.getParameters();
        } else {
            this.f = false;
            XMSSPublicKeyParameters xMSSPublicKeyParameters = (XMSSPublicKeyParameters) cipherParameters;
            this.c = xMSSPublicKeyParameters;
            parameters = xMSSPublicKeyParameters.getParameters();
        }
        this.d = parameters;
        this.e = parameters.c().d();
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        XMSSSignature build = new XMSSSignature.Builder(this.d).withSignature(bArr2).build();
        int index = build.getIndex();
        this.d.c().j(new byte[this.d.getDigestSize()], this.c.getPublicSeed());
        long j = index;
        byte[] c = this.e.c(Arrays.concatenate(build.getRandom(), this.c.getRoot(), XMSSUtil.toBytesBigEndian(j, this.d.getDigestSize())), bArr);
        int height = this.d.getHeight();
        return Arrays.constantTimeAreEqual(i.a(this.d.c(), height, c, build, (OTSHashAddress) new OTSHashAddress.Builder().withOTSAddress(index).build(), XMSSUtil.getLeafIndex(j, height)).getValue(), this.c.getRoot());
    }
}
