package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public final class t3 implements q {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f10130a;
    public final /* synthetic */ zzkn b;

    public t3(zzkn zzknVar, String str) {
        this.b = zzknVar;
        this.f10130a = str;
    }

    @Override // com.google.android.gms.measurement.internal.q
    public final void a(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        this.b.j(i, th, bArr, this.f10130a);
    }
}
