package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.play.integrity.internal.aa;
import com.google.android.play.integrity.internal.ac;
/* loaded from: classes10.dex */
public final class v implements aa {

    /* renamed from: a  reason: collision with root package name */
    private final ac f10466a;
    private final ac b;

    public v(ac acVar, ac acVar2) {
        this.f10466a = acVar;
        this.b = acVar2;
    }

    @Override // com.google.android.play.integrity.internal.ac
    public final /* bridge */ /* synthetic */ Object a() {
        return new t((Context) this.f10466a.a(), (com.google.android.play.integrity.internal.k) this.b.a());
    }
}
