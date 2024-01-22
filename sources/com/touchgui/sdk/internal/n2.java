package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.exception.TGException;
/* loaded from: classes12.dex */
public final class n2 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ com.touchgui.sdk.b f13799a;

    public n2(com.touchgui.sdk.b bVar) {
        this.f13799a = bVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a() {
        this.f13799a.a();
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.f13799a.a(th);
    }

    @Override // com.touchgui.sdk.TGCallback
    /* renamed from: a */
    public final void onSuccess(Integer num) {
        if (num.intValue() == 1) {
            this.f13799a.b.post(new Runnable() { // from class: com.touchgui.sdk.internal.oc
                @Override // java.lang.Runnable
                public final void run() {
                    n2.this.a();
                }
            });
        } else {
            this.f13799a.a(new TGException("set dail operate failure", 60000, num.intValue()));
        }
    }
}
