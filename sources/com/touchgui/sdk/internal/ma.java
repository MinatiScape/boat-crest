package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.TGWorkoutDataCallback;
import com.touchgui.sdk.bean.TGWorkoutRecord;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes12.dex */
public final class ma implements ka {

    /* renamed from: a  reason: collision with root package name */
    public final gb f13796a;
    public final CopyOnWriteArrayList b = new CopyOnWriteArrayList();
    public ArrayList c;

    public ma(a0 a0Var) {
        gb gbVar = new gb(a0Var);
        this.f13796a = gbVar;
        gbVar.a(new la(this, 0));
    }

    public final TGWorkoutRecord a(Date date) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            TGWorkoutRecord tGWorkoutRecord = (TGWorkoutRecord) it.next();
            if (tGWorkoutRecord.getSummary().getDate().getTime() / 1000 == date.getTime() / 1000) {
                return tGWorkoutRecord;
            }
        }
        return null;
    }

    @Override // com.touchgui.sdk.internal.ka
    public final void b(TGWorkoutDataCallback tGWorkoutDataCallback) {
        if (this.b.contains(tGWorkoutDataCallback)) {
            return;
        }
        this.b.add(tGWorkoutDataCallback);
    }

    @Override // com.touchgui.sdk.internal.ka
    public final boolean a() {
        if (this.f13796a.e.get()) {
            TGLogger.w(this.f13796a.f13767a, "Synchronization is in progress. ");
            return false;
        }
        this.c = new ArrayList();
        this.f13796a.c(0);
        return true;
    }

    @Override // com.touchgui.sdk.internal.ka
    public final void a(TGWorkoutDataCallback tGWorkoutDataCallback) {
        this.b.remove(tGWorkoutDataCallback);
    }
}
