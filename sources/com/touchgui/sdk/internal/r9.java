package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGEventListener;
import com.touchgui.sdk.bean.TGDataUpdated;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class r9 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ t9 f13822a;

    public r9(t9 t9Var) {
        this.f13822a = t9Var;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        TGDataUpdated tGDataUpdated = (TGDataUpdated) obj;
        Iterator it = this.f13822a.b.iterator();
        while (it.hasNext()) {
            TGEventListener tGEventListener = (TGEventListener) it.next();
            if (tGDataUpdated.isAlarmUpdated1() || tGDataUpdated.isAlarmUpdated2()) {
                tGEventListener.onEvent(TGEventListener.ALARM_UPDATED);
            }
            if (tGDataUpdated.isRequestSyncWeather()) {
                tGEventListener.onEvent(TGEventListener.REQUEST_UPDATE_WEATHER);
            }
            if (tGDataUpdated.isRaiseWristUpdated()) {
                tGEventListener.onEvent(TGEventListener.AWAKE_SCREEN_UPDATED);
            }
            if (tGDataUpdated.getType() != 0) {
                tGEventListener.onEvent(tGDataUpdated.getType() + 256);
            }
            if ((tGDataUpdated.getNotifyType() & 32) == 32) {
                t9 t9Var = this.f13822a;
                a0 a0Var = t9Var.f13826a;
                if (a0Var.p == null) {
                    a0Var.p = new e5(a0Var);
                }
                e5 e5Var = a0Var.p;
                new g5(e5Var.f13759a, new s5()).execute(new n9(t9Var));
            } else if ((tGDataUpdated.getNotifyType() & 64) == 64) {
                t9 t9Var2 = this.f13822a;
                a0 a0Var2 = t9Var2.f13826a;
                if (a0Var2.p == null) {
                    a0Var2.p = new e5(a0Var2);
                }
                e5 e5Var2 = a0Var2.p;
                o9 o9Var = new o9(t9Var2);
                e5Var2.getClass();
                new g5(e5Var2.f13759a, new t5()).execute(new d5(e5Var2, o9Var));
            }
        }
    }
}
