package com.coveiot.android.crpsdk.eventbus;

import android.os.Handler;
import android.os.Looper;
import com.squareup.otto.Bus;
/* loaded from: classes3.dex */
public class CRPBleEventBus extends Bus {

    /* renamed from: a  reason: collision with root package name */
    public final Handler f4116a = new Handler(Looper.getMainLooper());

    /* loaded from: classes3.dex */
    public class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Object f4117a;

        public a(Object obj) {
            this.f4117a = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            CRPBleEventBus.super.post(this.f4117a);
        }
    }

    @Override // com.squareup.otto.Bus
    public void post(Object obj) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(obj);
        } else {
            this.f4116a.post(new a(obj));
        }
    }
}
