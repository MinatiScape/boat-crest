package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGErrorCode;
import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.exception.TGException;
import java.nio.ByteBuffer;
import java.util.HashMap;
/* loaded from: classes12.dex */
public final class a4 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ com.touchgui.sdk.c f13741a;

    public a4(com.touchgui.sdk.c cVar) {
        this.f13741a = cVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        com.touchgui.sdk.c cVar = this.f13741a;
        new v8(cVar.f13733a.j.f13753a, new u6()).execute(new b4(cVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        com.touchgui.sdk.c cVar = this.f13741a;
        int i = cVar.m;
        if (i > 20) {
            cVar.a((Throwable) new TGException("The number of polling file write status exceeds the maximum", TGErrorCode.ERROR_AGPS_POLLING_WRITE_RESULT));
            return;
        }
        cVar.m = i + 1;
        a0 a0Var = cVar.f13733a;
        TGLogger.d(a0Var, "Number of polling file write status: " + cVar.m);
        a0 a0Var2 = cVar.f13733a;
        HashMap hashMap = u8.f13829a;
        m8 m8Var = new m8();
        ByteBuffer b = m8Var.b(18);
        b.put((byte) 1);
        b.put((byte) 3);
        new v8(a0Var2, m8Var).execute(new a4(cVar));
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.f13741a.a(th);
    }

    @Override // com.touchgui.sdk.TGCallback
    /* renamed from: a */
    public final void onSuccess(Integer num) {
        if (num.intValue() == 2) {
            this.f13741a.b.post(new Runnable() { // from class: com.touchgui.sdk.internal.wb
                @Override // java.lang.Runnable
                public final void run() {
                    a4.this.a();
                }
            });
        } else {
            this.f13741a.b.postDelayed(new Runnable() { // from class: com.touchgui.sdk.internal.vb
                @Override // java.lang.Runnable
                public final void run() {
                    a4.this.b();
                }
            }, 1000L);
        }
    }
}
