package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.exception.TGException;
/* loaded from: classes12.dex */
public final class v9 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ com.touchgui.sdk.k f13834a;

    public v9(com.touchgui.sdk.k kVar) {
        this.f13834a = kVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        com.touchgui.sdk.k kVar = this.f13834a;
        a0 a0Var = kVar.f13855a;
        p3 p3Var = new p3();
        p3Var.b(1).put((byte) 10);
        new m3(a0Var, p3Var).execute(new w9(kVar));
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.f13834a.a(th);
    }

    @Override // com.touchgui.sdk.TGCallback
    /* renamed from: a */
    public final void onSuccess(Integer num) {
        if (num.intValue() == 0) {
            this.f13834a.b.post(new Runnable() { // from class: com.touchgui.sdk.internal.vc
                @Override // java.lang.Runnable
                public final void run() {
                    v9.this.a();
                }
            });
        } else if (num.intValue() == 126) {
            this.f13834a.a();
        } else {
            this.f13834a.a(TGException.otaStartError(num.intValue()));
        }
    }
}
