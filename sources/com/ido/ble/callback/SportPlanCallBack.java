package com.ido.ble.callback;

import com.ido.ble.protocol.model.SportPlan;
/* loaded from: classes11.dex */
public class SportPlanCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onPlanEnd(boolean z);

        void onQueryResult(boolean z, SportPlan sportPlan);

        void onSportDataSend(boolean z);

        void onStartPlan(boolean z);
    }

    public static final void onPlanEnd(final boolean z) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SportPlanCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().D()) {
                    iCallBack.onPlanEnd(z);
                }
            }
        });
    }

    public static final void onQueryResult(final boolean z, final SportPlan sportPlan) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SportPlanCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().D()) {
                    iCallBack.onQueryResult(z, sportPlan);
                }
            }
        });
    }

    public static final void onSportDataSend(final boolean z) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SportPlanCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().D()) {
                    iCallBack.onSportDataSend(z);
                }
            }
        });
    }

    public static final void onStartPlan(final boolean z) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SportPlanCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().D()) {
                    iCallBack.onStartPlan(z);
                }
            }
        });
    }
}
