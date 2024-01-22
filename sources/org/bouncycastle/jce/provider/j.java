package org.bouncycastle.jce.provider;

import org.bouncycastle.asn1.x509.ReasonFlags;
/* loaded from: classes13.dex */
public class j {
    public static final j b = new j(33023);

    /* renamed from: a  reason: collision with root package name */
    public int f15114a;

    public j() {
        this(0);
    }

    public j(int i) {
        this.f15114a = i;
    }

    public j(ReasonFlags reasonFlags) {
        this.f15114a = reasonFlags.intValue();
    }

    public void a(j jVar) {
        this.f15114a = jVar.b() | this.f15114a;
    }

    public int b() {
        return this.f15114a;
    }

    public boolean c(j jVar) {
        return ((jVar.b() ^ this.f15114a) | this.f15114a) != 0;
    }

    public j d(j jVar) {
        j jVar2 = new j();
        jVar2.a(new j(jVar.b() & this.f15114a));
        return jVar2;
    }

    public boolean e() {
        return this.f15114a == b.f15114a;
    }
}
