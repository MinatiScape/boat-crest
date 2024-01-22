package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
/* loaded from: classes13.dex */
public class XMSSMTParameterSpec implements AlgorithmParameterSpec {
    public static final String SHA256 = "SHA256";
    public static final String SHA512 = "SHA512";
    public static final String SHAKE128 = "SHAKE128";
    public static final String SHAKE256 = "SHAKE256";

    /* renamed from: a  reason: collision with root package name */
    public final int f15360a;
    public final int b;
    public final String c;

    public XMSSMTParameterSpec(int i, int i2, String str) {
        this.f15360a = i;
        this.b = i2;
        this.c = str;
    }

    public int getHeight() {
        return this.f15360a;
    }

    public int getLayers() {
        return this.b;
    }

    public String getTreeDigest() {
        return this.c;
    }
}
