package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGProgressCallback;
import com.touchgui.sdk.bean.TGIotDevice;
import com.touchgui.sdk.bean.TGSyncIotDevice;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public final class d5 implements TGCallback {
    public final TGProgressCallback b;
    public final /* synthetic */ e5 e;

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList f13755a = new ArrayList();
    public int c = 0;
    public int d = 0;

    public d5(e5 e5Var, o9 o9Var) {
        this.e = e5Var;
        this.b = o9Var;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.b.onFailure(th);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        TGSyncIotDevice tGSyncIotDevice = (TGSyncIotDevice) obj;
        if (tGSyncIotDevice != null) {
            this.c = tGSyncIotDevice.getTotalCount();
            this.d = tGSyncIotDevice.getCurrentIndex();
            ArrayList arrayList = this.f13755a;
            TGIotDevice tGIotDevice = new TGIotDevice();
            tGIotDevice.setMac(tGSyncIotDevice.getMac());
            tGIotDevice.setName(tGSyncIotDevice.getName());
            tGIotDevice.setIconName(tGSyncIotDevice.getIconName());
            tGIotDevice.setDeviceType(tGSyncIotDevice.getDeviceType());
            tGIotDevice.setFunctions(tGSyncIotDevice.getFunctions());
            arrayList.add(tGIotDevice);
            int i = this.c;
            if (i > 0) {
                TGProgressCallback tGProgressCallback = this.b;
                int i2 = this.d;
                tGProgressCallback.onProgress((i2 * 100) / i, i2, i);
            }
        }
        if (this.d <= this.c) {
            new g5(this.e.f13759a, new t5()).execute(this);
        } else {
            this.b.onSuccess(this.f13755a);
        }
    }
}
