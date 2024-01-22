package com.coveiot.mki.ota;

import com.coveiot.mki.f;
/* loaded from: classes9.dex */
public class a extends f {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ OTAManager f7289a;

    public a(OTAManager oTAManager) {
        this.f7289a = oTAManager;
    }

    @Override // com.coveiot.mki.f
    public void onFail(String str) {
        super.onFail(str);
        synchronized (this.f7289a.g) {
            if (this.f7289a.e != null) {
                this.f7289a.e.onError(this.f7289a, "Upload failed");
            }
        }
    }

    @Override // com.coveiot.mki.f
    public void onProgress(float f) {
        super.onProgress(f);
        synchronized (this.f7289a.g) {
            if (this.f7289a.e != null) {
                this.f7289a.e.onProgress(this.f7289a, f);
            }
            if (Math.round(f * 65535.0f) == 65535) {
                if (this.f7289a.e != null) {
                    this.f7289a.e.onCompleted(this.f7289a);
                    this.f7289a.e = null;
                }
                OTAManager.k(this.f7289a);
            }
        }
    }

    @Override // com.coveiot.mki.f
    public void onSuccess(Object... objArr) {
        synchronized (this.f7289a.g) {
            if (this.f7289a.e != null) {
                this.f7289a.e.onCompleted(this.f7289a);
                this.f7289a.e = null;
            }
            OTAManager.k(this.f7289a);
        }
    }
}
