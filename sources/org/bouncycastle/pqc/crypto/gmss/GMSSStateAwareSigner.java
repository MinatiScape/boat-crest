package org.bouncycastle.pqc.crypto.gmss;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.StateAwareMessageSigner;
import org.bouncycastle.util.Memoable;
/* loaded from: classes13.dex */
public class GMSSStateAwareSigner implements StateAwareMessageSigner {

    /* renamed from: a  reason: collision with root package name */
    public final GMSSSigner f15293a;
    public GMSSPrivateKeyParameters b;

    /* loaded from: classes13.dex */
    public class a implements GMSSDigestProvider {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Memoable f15294a;

        public a(GMSSStateAwareSigner gMSSStateAwareSigner, Memoable memoable) {
            this.f15294a = memoable;
        }

        @Override // org.bouncycastle.pqc.crypto.gmss.GMSSDigestProvider
        public Digest get() {
            return (Digest) this.f15294a.copy();
        }
    }

    public GMSSStateAwareSigner(Digest digest) {
        if (!(digest instanceof Memoable)) {
            throw new IllegalArgumentException("digest must implement Memoable");
        }
        this.f15293a = new GMSSSigner(new a(this, ((Memoable) digest).copy()));
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        if (this.b != null) {
            byte[] generateSignature = this.f15293a.generateSignature(bArr);
            this.b = this.b.nextKey();
            return generateSignature;
        }
        throw new IllegalStateException("signing key no longer usable");
    }

    @Override // org.bouncycastle.pqc.crypto.StateAwareMessageSigner
    public AsymmetricKeyParameter getUpdatedPrivateKey() {
        GMSSPrivateKeyParameters gMSSPrivateKeyParameters = this.b;
        this.b = null;
        return gMSSPrivateKeyParameters;
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        if (z) {
            this.b = cipherParameters instanceof ParametersWithRandom ? (GMSSPrivateKeyParameters) ((ParametersWithRandom) cipherParameters).getParameters() : (GMSSPrivateKeyParameters) cipherParameters;
        }
        this.f15293a.init(z, cipherParameters);
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        return this.f15293a.verifySignature(bArr, bArr2);
    }
}
