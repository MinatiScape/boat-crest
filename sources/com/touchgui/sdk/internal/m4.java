package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
/* loaded from: classes12.dex */
public final class m4 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o4 f13794a;

    public m4(o4 o4Var) {
        this.f13794a = o4Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a() {
        this.f13794a.b();
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.f13794a.f13765a.a(-1);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        this.f13794a.b = obj != null ? this.f13794a.a(obj) : false;
        j6.a().post(new Runnable() { // from class: com.touchgui.sdk.internal.nc
            @Override // java.lang.Runnable
            public final void run() {
                m4.this.a();
            }
        });
    }
}
