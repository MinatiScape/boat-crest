package com.google.android.play.core.integrity;

import android.content.Context;
import java.util.Objects;
/* loaded from: classes10.dex */
final class h implements k {

    /* renamed from: a  reason: collision with root package name */
    private Context f10454a;

    private h() {
    }

    public /* synthetic */ h(g gVar) {
    }

    public final h a(Context context) {
        Objects.requireNonNull(context);
        this.f10454a = context;
        return this;
    }

    @Override // com.google.android.play.core.integrity.k
    public final j b() {
        Context context = this.f10454a;
        if (context != null) {
            return new j(context, null);
        }
        throw new IllegalStateException(String.valueOf(Context.class.getCanonicalName()).concat(" must be set"));
    }
}
