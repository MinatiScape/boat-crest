package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.TGWorkoutDataCallback;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes12.dex */
public final class oa implements ka {

    /* renamed from: a  reason: collision with root package name */
    public final ob f13808a;
    public final CopyOnWriteArrayList b = new CopyOnWriteArrayList();

    public oa(a0 a0Var) {
        ob obVar = new ob(a0Var);
        this.f13808a = obVar;
        obVar.a(new na(this, 0));
    }

    @Override // com.touchgui.sdk.internal.ka
    public final boolean a() {
        if (this.f13808a.b.get()) {
            TGLogger.w(this.f13808a.f13809a, "Synchronization is in progress. ");
            return false;
        }
        ob obVar = this.f13808a;
        if (obVar.b.getAndSet(true)) {
            TGLogger.w(obVar.f13809a, "Synchronization is in progress. Please do not call repeatedly");
        } else {
            TGLogger.d(obVar.f13809a, "Start to sync sport data");
            na naVar = obVar.c;
            if (naVar != null) {
                Iterator it = naVar.f13802a.b.iterator();
                while (it.hasNext()) {
                    ((TGWorkoutDataCallback) it.next()).onStart();
                }
            }
            new v8(obVar.f13809a, new d9()).execute(new hb(obVar));
        }
        return true;
    }

    @Override // com.touchgui.sdk.internal.ka
    public final void b(TGWorkoutDataCallback tGWorkoutDataCallback) {
        if (this.b.contains(tGWorkoutDataCallback)) {
            return;
        }
        this.b.add(tGWorkoutDataCallback);
    }

    @Override // com.touchgui.sdk.internal.ka
    public final void a(TGWorkoutDataCallback tGWorkoutDataCallback) {
        this.b.remove(tGWorkoutDataCallback);
    }
}
