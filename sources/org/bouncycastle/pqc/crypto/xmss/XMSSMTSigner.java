package org.bouncycastle.pqc.crypto.xmss;

import java.util.Objects;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.StateAwareMessageSigner;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTSignature;
import org.bouncycastle.pqc.crypto.xmss.XMSSReducedSignature;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class XMSSMTSigner implements StateAwareMessageSigner {

    /* renamed from: a  reason: collision with root package name */
    public XMSSMTPrivateKeyParameters f15333a;
    public XMSSMTPrivateKeyParameters b;
    public XMSSMTPublicKeyParameters c;
    public XMSSMTParameters d;
    public XMSSParameters e;
    public c f;
    public boolean g;
    public boolean h;

    public final g a(byte[] bArr, OTSHashAddress oTSHashAddress) {
        if (bArr.length == this.d.getDigestSize()) {
            Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
            c cVar = this.f;
            cVar.j(cVar.i(this.f15333a.getSecretKeySeed(), oTSHashAddress), this.f15333a.getPublicSeed());
            return this.f.k(bArr, oTSHashAddress);
        }
        throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        Objects.requireNonNull(bArr, "message == null");
        if (this.h) {
            XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParameters = this.f15333a;
            if (xMSSMTPrivateKeyParameters != null) {
                if (xMSSMTPrivateKeyParameters.a().isEmpty()) {
                    throw new IllegalStateException("not initialized");
                }
                BDSStateMap a2 = this.f15333a.a();
                long index = this.f15333a.getIndex();
                int height = this.d.getHeight();
                int height2 = this.e.getHeight();
                if (XMSSUtil.isIndexValid(height, index)) {
                    byte[] d = this.f.d().d(this.f15333a.getSecretKeyPRF(), XMSSUtil.toBytesBigEndian(index, 32));
                    byte[] c = this.f.d().c(Arrays.concatenate(d, this.f15333a.getRoot(), XMSSUtil.toBytesBigEndian(index, this.d.getDigestSize())), bArr);
                    XMSSMTSignature build = new XMSSMTSignature.Builder(this.d).withIndex(index).withRandom(d).build();
                    long treeIndex = XMSSUtil.getTreeIndex(index, height2);
                    int leafIndex = XMSSUtil.getLeafIndex(index, height2);
                    this.f.j(new byte[this.d.getDigestSize()], this.f15333a.getPublicSeed());
                    OTSHashAddress oTSHashAddress = (OTSHashAddress) new OTSHashAddress.Builder().withTreeAddress(treeIndex).withOTSAddress(leafIndex).build();
                    if (a2.get(0) == null || leafIndex == 0) {
                        a2.put(0, new BDS(this.e, this.f15333a.getPublicSeed(), this.f15333a.getSecretKeySeed(), oTSHashAddress));
                    }
                    build.getReducedSignatures().add(new XMSSReducedSignature.Builder(this.e).withWOTSPlusSignature(a(c, oTSHashAddress)).withAuthPath(a2.get(0).getAuthenticationPath()).build());
                    for (int i = 1; i < this.d.getLayers(); i++) {
                        XMSSNode root = a2.get(i - 1).getRoot();
                        int leafIndex2 = XMSSUtil.getLeafIndex(treeIndex, height2);
                        treeIndex = XMSSUtil.getTreeIndex(treeIndex, height2);
                        OTSHashAddress oTSHashAddress2 = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(i).withTreeAddress(treeIndex).withOTSAddress(leafIndex2).build();
                        g a3 = a(root.getValue(), oTSHashAddress2);
                        if (a2.get(i) == null || XMSSUtil.isNewBDSInitNeeded(index, height2, i)) {
                            a2.put(i, new BDS(this.e, this.f15333a.getPublicSeed(), this.f15333a.getSecretKeySeed(), oTSHashAddress2));
                        }
                        build.getReducedSignatures().add(new XMSSReducedSignature.Builder(this.e).withWOTSPlusSignature(a3).withAuthPath(a2.get(i).getAuthenticationPath()).build());
                    }
                    this.g = true;
                    XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParameters2 = this.b;
                    if (xMSSMTPrivateKeyParameters2 != null) {
                        XMSSMTPrivateKeyParameters nextKey = xMSSMTPrivateKeyParameters2.getNextKey();
                        this.f15333a = nextKey;
                        this.b = nextKey;
                    } else {
                        this.f15333a = null;
                    }
                    return build.toByteArray();
                }
                throw new IllegalStateException("index out of bounds");
            }
            throw new IllegalStateException("signing key no longer usable");
        }
        throw new IllegalStateException("signer not initialized for signature generation");
    }

    @Override // org.bouncycastle.pqc.crypto.StateAwareMessageSigner
    public AsymmetricKeyParameter getUpdatedPrivateKey() {
        XMSSMTPrivateKeyParameters nextKey;
        if (this.g) {
            nextKey = this.f15333a;
            this.f15333a = null;
        } else {
            nextKey = this.b.getNextKey();
        }
        this.b = null;
        return nextKey;
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        XMSSMTParameters parameters;
        if (z) {
            this.h = true;
            this.g = false;
            XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParameters = (XMSSMTPrivateKeyParameters) cipherParameters;
            this.f15333a = xMSSMTPrivateKeyParameters;
            this.b = xMSSMTPrivateKeyParameters;
            parameters = xMSSMTPrivateKeyParameters.getParameters();
        } else {
            this.h = false;
            XMSSMTPublicKeyParameters xMSSMTPublicKeyParameters = (XMSSMTPublicKeyParameters) cipherParameters;
            this.c = xMSSMTPublicKeyParameters;
            parameters = xMSSMTPublicKeyParameters.getParameters();
        }
        this.d = parameters;
        this.e = parameters.getXMSSParameters();
        this.f = new c(new e(this.d.getDigest()));
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        Objects.requireNonNull(bArr, "message == null");
        Objects.requireNonNull(bArr2, "signature == null");
        Objects.requireNonNull(this.c, "publicKey == null");
        XMSSMTSignature build = new XMSSMTSignature.Builder(this.d).withSignature(bArr2).build();
        byte[] c = this.f.d().c(Arrays.concatenate(build.getRandom(), this.c.getRoot(), XMSSUtil.toBytesBigEndian(build.getIndex(), this.d.getDigestSize())), bArr);
        long index = build.getIndex();
        int height = this.e.getHeight();
        long treeIndex = XMSSUtil.getTreeIndex(index, height);
        int leafIndex = XMSSUtil.getLeafIndex(index, height);
        this.f.j(new byte[this.d.getDigestSize()], this.c.getPublicSeed());
        OTSHashAddress oTSHashAddress = (OTSHashAddress) new OTSHashAddress.Builder().withTreeAddress(treeIndex).withOTSAddress(leafIndex).build();
        XMSSNode a2 = i.a(this.f, height, c, build.getReducedSignatures().get(0), oTSHashAddress, leafIndex);
        int i = 1;
        while (i < this.d.getLayers()) {
            int leafIndex2 = XMSSUtil.getLeafIndex(treeIndex, height);
            long treeIndex2 = XMSSUtil.getTreeIndex(treeIndex, height);
            OTSHashAddress oTSHashAddress2 = (OTSHashAddress) new OTSHashAddress.Builder().withLayerAddress(i).withTreeAddress(treeIndex2).withOTSAddress(leafIndex2).build();
            a2 = i.a(this.f, height, a2.getValue(), build.getReducedSignatures().get(i), oTSHashAddress2, leafIndex2);
            i++;
            treeIndex = treeIndex2;
        }
        return Arrays.constantTimeAreEqual(a2.getValue(), this.c.getRoot());
    }
}
