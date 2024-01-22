package com.google.android.gms.internal.firebase_messaging;
/* loaded from: classes7.dex */
public final class o extends l {

    /* renamed from: a  reason: collision with root package name */
    public final n f8650a = new n();

    @Override // com.google.android.gms.internal.firebase_messaging.l
    public final void a(Throwable th, Throwable th2) {
        if (th2 != th) {
            this.f8650a.a(th, true).add(th2);
            return;
        }
        throw new IllegalArgumentException("Self suppression is not allowed.", th2);
    }
}
