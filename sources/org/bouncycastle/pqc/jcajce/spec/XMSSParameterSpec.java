package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
/* loaded from: classes13.dex */
public class XMSSParameterSpec implements AlgorithmParameterSpec {
    public static final String SHA256 = "SHA256";
    public static final String SHA512 = "SHA512";
    public static final String SHAKE128 = "SHAKE128";
    public static final String SHAKE256 = "SHAKE256";

    /* renamed from: a  reason: collision with root package name */
    public final int f15361a;
    public final String b;

    public XMSSParameterSpec(int i, String str) {
        this.f15361a = i;
        this.b = str;
    }

    public int getHeight() {
        return this.f15361a;
    }

    public String getTreeDigest() {
        return this.b;
    }
}
