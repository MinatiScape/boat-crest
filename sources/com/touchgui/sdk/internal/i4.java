package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
/* loaded from: classes12.dex */
public final class i4 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ k4 f13777a;

    public i4(k4 k4Var) {
        this.f13777a = k4Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a() {
        this.f13777a.b();
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.f13777a.f13765a.a(-1);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        if (obj != null) {
            this.f13777a.a(obj);
        }
        k4 k4Var = this.f13777a;
        if (k4Var.d) {
            j6.a().post(new Runnable() { // from class: com.touchgui.sdk.internal.kc
                @Override // java.lang.Runnable
                public final void run() {
                    i4.this.a();
                }
            });
        } else {
            k4Var.c();
        }
    }
}
