package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.TGWorkoutDataCallback;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public final class ob {

    /* renamed from: a  reason: collision with root package name */
    public final a0 f13809a;
    public final AtomicBoolean b = new AtomicBoolean(false);
    public na c;

    public ob(a0 a0Var) {
        this.f13809a = a0Var;
    }

    public static void a(ob obVar, int i) {
        a0 a0Var = obVar.f13809a;
        TGLogger.e(a0Var, "SportData, code=" + i);
        na naVar = obVar.c;
        if (naVar != null) {
            Iterator it = naVar.f13802a.b.iterator();
            while (it.hasNext()) {
                ((TGWorkoutDataCallback) it.next()).onError(i, "Failed to synchronize sport data");
            }
        }
        obVar.b.set(false);
    }

    public final void a(na naVar) {
        this.c = naVar;
    }
}
