package com.touchgui.sdk.internal;
/* loaded from: classes12.dex */
public final class d2 {

    /* renamed from: a  reason: collision with root package name */
    public final a0 f13753a;

    public d2(a0 a0Var) {
        this.f13753a = a0Var;
    }

    public final g5 a(String str, String str2, String str3, int i) {
        a0 a0Var = this.f13753a;
        if (a0Var.c < 768) {
            boolean z = false;
            boolean z2 = i >= 48;
            if (i >= 26 && i <= 31) {
                z = true;
            }
            if (z2 | z) {
                i = 128;
            }
        }
        int i2 = i;
        if (i2 == 1 && a0Var.a(33687816)) {
            this.f13753a.E = str2;
        } else {
            this.f13753a.E = null;
        }
        g5 g5Var = new g5(this.f13753a, new a6(str, this.f13753a.a(34146306), str2, str3, i2));
        g5Var.b = 1;
        return g5Var;
    }
}
