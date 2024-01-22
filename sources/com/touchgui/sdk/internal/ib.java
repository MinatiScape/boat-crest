package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.TGWorkoutDataCallback;
import com.touchgui.sdk.bean.TGWorkoutRecord;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes12.dex */
public final class ib implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List f13779a;
    public final /* synthetic */ ob b;

    public ib(ob obVar, ArrayList arrayList) {
        this.b = obVar;
        this.f13779a = arrayList;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        ob obVar = this.b;
        List<TGWorkoutRecord> list = this.f13779a;
        TGLogger.d(obVar.f13809a, "Sync sport data is completed");
        na naVar = obVar.c;
        if (naVar != null) {
            Iterator it = naVar.f13802a.b.iterator();
            while (it.hasNext()) {
                ((TGWorkoutDataCallback) it.next()).onCompleted(list);
            }
        }
        obVar.b.set(false);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        f8 f8Var = (f8) obj;
        ob obVar = this.b;
        List<TGWorkoutRecord> list = this.f13779a;
        TGLogger.d(obVar.f13809a, "Sync sport data is completed");
        na naVar = obVar.c;
        if (naVar != null) {
            Iterator it = naVar.f13802a.b.iterator();
            while (it.hasNext()) {
                ((TGWorkoutDataCallback) it.next()).onCompleted(list);
            }
        }
        obVar.b.set(false);
    }
}
