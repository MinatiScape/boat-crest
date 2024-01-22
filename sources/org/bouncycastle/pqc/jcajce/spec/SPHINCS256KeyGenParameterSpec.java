package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
/* loaded from: classes13.dex */
public class SPHINCS256KeyGenParameterSpec implements AlgorithmParameterSpec {
    public static final String SHA3_256 = "SHA3-256";
    public static final String SHA512_256 = "SHA512-256";

    /* renamed from: a  reason: collision with root package name */
    public final String f15359a;

    public SPHINCS256KeyGenParameterSpec() {
        this(SHA512_256);
    }

    public SPHINCS256KeyGenParameterSpec(String str) {
        this.f15359a = str;
    }

    public String getTreeDigest() {
        return this.f15359a;
    }
}
