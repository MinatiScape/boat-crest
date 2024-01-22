package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public final class q1 extends o1 {

    /* renamed from: a  reason: collision with root package name */
    public final char f8719a;

    public q1(char c) {
        this.f8719a = c;
    }

    public final String toString() {
        String a2;
        a2 = zzlw.a(this.f8719a);
        StringBuilder sb = new StringBuilder(String.valueOf(a2).length() + 18);
        sb.append("CharMatcher.is('");
        sb.append(a2);
        sb.append("')");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzlw
    public final boolean zzb(char c) {
        return c == this.f8719a;
    }
}
