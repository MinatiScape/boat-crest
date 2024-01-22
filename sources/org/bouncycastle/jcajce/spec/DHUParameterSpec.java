package org.bouncycastle.jcajce.spec;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class DHUParameterSpec implements AlgorithmParameterSpec {

    /* renamed from: a  reason: collision with root package name */
    public final PublicKey f15070a;
    public final PrivateKey b;
    public final PublicKey c;
    public final byte[] d;

    public DHUParameterSpec(KeyPair keyPair, PublicKey publicKey) {
        this(keyPair.getPublic(), keyPair.getPrivate(), publicKey, null);
    }

    public DHUParameterSpec(KeyPair keyPair, PublicKey publicKey, byte[] bArr) {
        this(keyPair.getPublic(), keyPair.getPrivate(), publicKey, bArr);
    }

    public DHUParameterSpec(PrivateKey privateKey, PublicKey publicKey) {
        this(null, privateKey, publicKey, null);
    }

    public DHUParameterSpec(PrivateKey privateKey, PublicKey publicKey, byte[] bArr) {
        this(null, privateKey, publicKey, bArr);
    }

    public DHUParameterSpec(PublicKey publicKey, PrivateKey privateKey, PublicKey publicKey2) {
        this(publicKey, privateKey, publicKey2, null);
    }

    public DHUParameterSpec(PublicKey publicKey, PrivateKey privateKey, PublicKey publicKey2, byte[] bArr) {
        this.f15070a = publicKey;
        this.b = privateKey;
        this.c = publicKey2;
        this.d = Arrays.clone(bArr);
    }

    public PrivateKey getEphemeralPrivateKey() {
        return this.b;
    }

    public PublicKey getEphemeralPublicKey() {
        return this.f15070a;
    }

    public PublicKey getOtherPartyEphemeralKey() {
        return this.c;
    }

    public byte[] getUserKeyingMaterial() {
        return Arrays.clone(this.d);
    }
}
