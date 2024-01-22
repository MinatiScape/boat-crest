package com.mappls.sdk.navigation.refresh;

import com.mappls.sdk.navigation.model.AdviseInfo;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
/* loaded from: classes11.dex */
public final class i implements OnResponseCallback<DirectionsRoute> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AdviseInfo f12938a;
    public final /* synthetic */ e b;

    public i(e eVar, AdviseInfo adviseInfo) {
        this.b = eVar;
        this.f12938a = adviseInfo;
    }

    @Override // com.mappls.sdk.services.api.OnResponseCallback
    public final void onError(int i, String str) {
        this.b.g();
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x02c3  */
    @Override // com.mappls.sdk.services.api.OnResponseCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onSuccess(com.mappls.sdk.services.api.directions.models.DirectionsRoute r15) {
        /*
            Method dump skipped, instructions count: 835
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.refresh.i.onSuccess(java.lang.Object):void");
    }
}
