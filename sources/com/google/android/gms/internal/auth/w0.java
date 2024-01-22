package com.google.android.gms.internal.auth;
/* loaded from: classes6.dex */
public final class w0 extends zzei {

    /* renamed from: a  reason: collision with root package name */
    public int f8544a;
    public int b;
    public int c;

    public /* synthetic */ w0(byte[] bArr, int i, int i2, boolean z, zzef zzefVar) {
        super(null);
        this.c = Integer.MAX_VALUE;
        this.f8544a = 0;
    }

    public final int a(int i) throws zzfa {
        int i2 = this.c;
        this.c = 0;
        int i3 = this.f8544a + this.b;
        this.f8544a = i3;
        if (i3 > 0) {
            this.b = i3;
            this.f8544a = i3 - i3;
        } else {
            this.b = 0;
        }
        return i2;
    }
}
