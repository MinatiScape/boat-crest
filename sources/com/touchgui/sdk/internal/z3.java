package com.touchgui.sdk.internal;

import com.clevertap.android.sdk.Constants;
import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGErrorCode;
import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.exception.TGException;
import java.nio.ByteBuffer;
import java.util.HashMap;
/* loaded from: classes12.dex */
public final class z3 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ com.touchgui.sdk.c f13851a;

    public z3(com.touchgui.sdk.c cVar) {
        this.f13851a = cVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a() {
        this.f13851a.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        com.touchgui.sdk.c cVar = this.f13851a;
        int i = cVar.m;
        if (i > 5) {
            cVar.a((Throwable) new TGException("Polling the device if the number of times the file can be written exceeds the maximum", TGErrorCode.ERROR_AGPS_POLLING_CAN_WRITE));
            return;
        }
        cVar.m = i + 1;
        a0 a0Var = cVar.f13733a;
        TGLogger.d(a0Var, "Number of polling device status for write AGPS file: " + cVar.m);
        a0 a0Var2 = cVar.f13733a;
        HashMap hashMap = u8.f13829a;
        m8 m8Var = new m8();
        ByteBuffer b = m8Var.b(18);
        b.put((byte) 2);
        b.put((byte) 3);
        new v8(a0Var2, m8Var).execute(new z3(cVar));
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.f13851a.a(th);
    }

    @Override // com.touchgui.sdk.TGCallback
    /* renamed from: a */
    public final void onSuccess(Integer num) {
        if (num.intValue() == 2) {
            this.f13851a.b.post(new Runnable() { // from class: com.touchgui.sdk.internal.yc
                @Override // java.lang.Runnable
                public final void run() {
                    z3.this.a();
                }
            });
        } else {
            this.f13851a.b.postDelayed(new Runnable() { // from class: com.touchgui.sdk.internal.xc
                @Override // java.lang.Runnable
                public final void run() {
                    z3.this.b();
                }
            }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
        }
    }
}
