package com.coveiot.sdk.ble.eventbus;

import android.os.Handler;
import android.os.Looper;
import com.squareup.otto.Bus;
/* loaded from: classes9.dex */
public class BleEventBus extends Bus {
    public final Handler j = new Handler(Looper.getMainLooper());

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public final /* synthetic */ Object h;

        public a(Object obj) {
            this.h = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            BleEventBus.super.post(this.h);
        }
    }

    @Override // com.squareup.otto.Bus
    public void post(Object obj) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(obj);
        } else {
            this.j.post(new a(obj));
        }
    }
}
