package com.coveiot.mki.ota;

import com.coveiot.mki.f;
/* loaded from: classes9.dex */
public class b extends f {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ OTAManager f7290a;

    public b(OTAManager oTAManager) {
        this.f7290a = oTAManager;
    }

    @Override // com.coveiot.mki.f
    public void onFail(String str) {
        super.onFail(str);
        synchronized (this.f7290a.g) {
            if (this.f7290a.e != null) {
                this.f7290a.e.onError(this.f7290a, "Setting OTA mode failed");
            }
        }
    }

    @Override // com.coveiot.mki.f
    public void onSuccess(Object... objArr) {
        OTAManager.c(this.f7290a, 0);
    }
}
