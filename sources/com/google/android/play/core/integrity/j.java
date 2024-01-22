package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.play.integrity.internal.aa;
import com.google.android.play.integrity.internal.ab;
import com.google.android.play.integrity.internal.ac;
import com.google.android.play.integrity.internal.z;
/* loaded from: classes10.dex */
final class j {

    /* renamed from: a  reason: collision with root package name */
    private final j f10455a = this;
    private final ac b;
    private final ac c;
    private final ac d;
    private final ac e;

    public /* synthetic */ j(Context context, i iVar) {
        p pVar;
        aa b = ab.b(context);
        this.b = b;
        pVar = o.f10460a;
        ac b2 = z.b(pVar);
        this.c = b2;
        ac b3 = z.b(new v(b, b2));
        this.d = b3;
        this.e = z.b(new n(b3));
    }

    public final IntegrityManager a() {
        return (IntegrityManager) this.e.a();
    }
}
