package com.coveiot.mki.ota;

import com.coveiot.mki.f;
/* loaded from: classes9.dex */
public class c extends f {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7291a;
    public final /* synthetic */ OTAManager b;

    public c(OTAManager oTAManager, int i) {
        this.b = oTAManager;
        this.f7291a = i;
    }

    @Override // com.coveiot.mki.f
    public void onFail(String str) {
        super.onFail(str);
        synchronized (this.b.g) {
            if (this.b.e != null) {
                this.b.e.onError(this.b, "Upload failed");
            }
        }
    }

    @Override // com.coveiot.mki.f
    public void onSuccess(Object... objArr) {
        byte[] bArr;
        byte[] bArr2;
        synchronized (this.b.g) {
            if (this.b.e != null) {
                OTACallback oTACallback = this.b.e;
                OTAManager oTAManager = this.b;
                bArr2 = oTAManager.d;
                oTACallback.onProgress(oTAManager, (this.f7291a * 1024) / bArr2.length);
            }
        }
        int i = (this.f7291a + 1) * 1024;
        bArr = this.b.d;
        if (i < bArr.length) {
            OTAManager.c(this.b, this.f7291a + 1);
            return;
        }
        synchronized (this.b.g) {
            if (this.b.e != null) {
                this.b.e.onCompleted(this.b);
                this.b.e = null;
            }
            OTAManager.k(this.b);
        }
    }
}
