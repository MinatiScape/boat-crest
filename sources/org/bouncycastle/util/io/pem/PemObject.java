package org.bouncycastle.util.io.pem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes13.dex */
public class PemObject implements PemObjectGenerator {
    public static final List d = Collections.unmodifiableList(new ArrayList());

    /* renamed from: a  reason: collision with root package name */
    public String f15406a;
    public List b;
    public byte[] c;

    public PemObject(String str, List list, byte[] bArr) {
        this.f15406a = str;
        this.b = Collections.unmodifiableList(list);
        this.c = bArr;
    }

    public PemObject(String str, byte[] bArr) {
        this(str, d, bArr);
    }

    @Override // org.bouncycastle.util.io.pem.PemObjectGenerator
    public PemObject generate() throws PemGenerationException {
        return this;
    }

    public byte[] getContent() {
        return this.c;
    }

    public List getHeaders() {
        return this.b;
    }

    public String getType() {
        return this.f15406a;
    }
}
