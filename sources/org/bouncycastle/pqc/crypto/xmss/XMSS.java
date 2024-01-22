package org.bouncycastle.pqc.crypto.xmss;

import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Objects;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.pqc.crypto.xmss.XMSSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSPublicKeyParameters;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class XMSS {

    /* renamed from: a  reason: collision with root package name */
    public final XMSSParameters f15323a;
    public c b;
    public SecureRandom c;
    public XMSSPrivateKeyParameters d;
    public XMSSPublicKeyParameters e;

    public XMSS(XMSSParameters xMSSParameters, SecureRandom secureRandom) {
        Objects.requireNonNull(xMSSParameters, "params == null");
        this.f15323a = xMSSParameters;
        this.b = xMSSParameters.c();
        this.c = secureRandom;
    }

    public void a(XMSSPrivateKeyParameters xMSSPrivateKeyParameters, XMSSPublicKeyParameters xMSSPublicKeyParameters) {
        if (!Arrays.areEqual(xMSSPrivateKeyParameters.getRoot(), xMSSPublicKeyParameters.getRoot())) {
            throw new IllegalStateException("root of private key and public key do not match");
        }
        if (!Arrays.areEqual(xMSSPrivateKeyParameters.getPublicSeed(), xMSSPublicKeyParameters.getPublicSeed())) {
            throw new IllegalStateException("public seed of private key and public key do not match");
        }
        this.d = xMSSPrivateKeyParameters;
        this.e = xMSSPublicKeyParameters;
        this.b.j(new byte[this.f15323a.getDigestSize()], this.d.getPublicSeed());
    }

    public byte[] exportPrivateKey() {
        return this.d.toByteArray();
    }

    public byte[] exportPublicKey() {
        return this.e.toByteArray();
    }

    public void generateKeys() {
        XMSSKeyPairGenerator xMSSKeyPairGenerator = new XMSSKeyPairGenerator();
        xMSSKeyPairGenerator.init(new XMSSKeyGenerationParameters(getParams(), this.c));
        AsymmetricCipherKeyPair generateKeyPair = xMSSKeyPairGenerator.generateKeyPair();
        this.d = (XMSSPrivateKeyParameters) generateKeyPair.getPrivate();
        this.e = (XMSSPublicKeyParameters) generateKeyPair.getPublic();
        this.b.j(new byte[this.f15323a.getDigestSize()], this.d.getPublicSeed());
    }

    public int getIndex() {
        return this.d.getIndex();
    }

    public XMSSParameters getParams() {
        return this.f15323a;
    }

    public XMSSPrivateKeyParameters getPrivateKey() {
        return this.d;
    }

    public byte[] getPublicSeed() {
        return this.d.getPublicSeed();
    }

    public byte[] getRoot() {
        return this.d.getRoot();
    }

    public c getWOTSPlus() {
        return this.b;
    }

    public void importState(byte[] bArr, byte[] bArr2) {
        Objects.requireNonNull(bArr, "privateKey == null");
        Objects.requireNonNull(bArr2, "publicKey == null");
        XMSSPrivateKeyParameters build = new XMSSPrivateKeyParameters.Builder(this.f15323a).withPrivateKey(bArr, getParams()).build();
        XMSSPublicKeyParameters build2 = new XMSSPublicKeyParameters.Builder(this.f15323a).withPublicKey(bArr2).build();
        if (!Arrays.areEqual(build.getRoot(), build2.getRoot())) {
            throw new IllegalStateException("root of private key and public key do not match");
        }
        if (!Arrays.areEqual(build.getPublicSeed(), build2.getPublicSeed())) {
            throw new IllegalStateException("public seed of private key and public key do not match");
        }
        this.d = build;
        this.e = build2;
        this.b.j(new byte[this.f15323a.getDigestSize()], this.d.getPublicSeed());
    }

    public void setIndex(int i) {
        this.d = new XMSSPrivateKeyParameters.Builder(this.f15323a).withSecretKeySeed(this.d.getSecretKeySeed()).withSecretKeyPRF(this.d.getSecretKeyPRF()).withPublicSeed(this.d.getPublicSeed()).withRoot(this.d.getRoot()).withBDSState(this.d.a()).build();
    }

    public void setPublicSeed(byte[] bArr) {
        this.d = new XMSSPrivateKeyParameters.Builder(this.f15323a).withSecretKeySeed(this.d.getSecretKeySeed()).withSecretKeyPRF(this.d.getSecretKeyPRF()).withPublicSeed(bArr).withRoot(getRoot()).withBDSState(this.d.a()).build();
        this.e = new XMSSPublicKeyParameters.Builder(this.f15323a).withRoot(getRoot()).withPublicSeed(bArr).build();
        this.b.j(new byte[this.f15323a.getDigestSize()], bArr);
    }

    public void setRoot(byte[] bArr) {
        this.d = new XMSSPrivateKeyParameters.Builder(this.f15323a).withSecretKeySeed(this.d.getSecretKeySeed()).withSecretKeyPRF(this.d.getSecretKeyPRF()).withPublicSeed(getPublicSeed()).withRoot(bArr).withBDSState(this.d.a()).build();
        this.e = new XMSSPublicKeyParameters.Builder(this.f15323a).withRoot(bArr).withPublicSeed(getPublicSeed()).build();
    }

    public byte[] sign(byte[] bArr) {
        Objects.requireNonNull(bArr, "message == null");
        XMSSSigner xMSSSigner = new XMSSSigner();
        xMSSSigner.init(true, this.d);
        byte[] generateSignature = xMSSSigner.generateSignature(bArr);
        XMSSPrivateKeyParameters xMSSPrivateKeyParameters = (XMSSPrivateKeyParameters) xMSSSigner.getUpdatedPrivateKey();
        this.d = xMSSPrivateKeyParameters;
        a(xMSSPrivateKeyParameters, this.e);
        return generateSignature;
    }

    public boolean verifySignature(byte[] bArr, byte[] bArr2, byte[] bArr3) throws ParseException {
        Objects.requireNonNull(bArr, "message == null");
        Objects.requireNonNull(bArr2, "signature == null");
        Objects.requireNonNull(bArr3, "publicKey == null");
        XMSSSigner xMSSSigner = new XMSSSigner();
        xMSSSigner.init(false, new XMSSPublicKeyParameters.Builder(getParams()).withPublicKey(bArr3).build());
        return xMSSSigner.verifySignature(bArr, bArr2);
    }

    public g wotsSign(byte[] bArr, OTSHashAddress oTSHashAddress) {
        if (bArr.length == this.f15323a.getDigestSize()) {
            Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
            c cVar = this.b;
            cVar.j(cVar.i(this.d.getSecretKeySeed(), oTSHashAddress), getPublicSeed());
            return this.b.k(bArr, oTSHashAddress);
        }
        throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
    }
}
