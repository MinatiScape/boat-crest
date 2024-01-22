package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.bean.TGDeviceInfo;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public final class o6 implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f13806a;
    public final /* synthetic */ com.touchgui.sdk.h b;

    public o6(com.touchgui.sdk.h hVar, String str) {
        this.b = hVar;
        this.f13806a = str;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        this.b.a(th);
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        int resFlag = ((TGDeviceInfo) obj).getResFlag();
        if (resFlag != 0) {
            String[] strArr = u3.f13828a;
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (true) {
                String[] strArr2 = u3.d;
                if (i >= strArr2.length) {
                    this.b.a(this.f13806a, arrayList);
                    return;
                }
                int i2 = 1 << i;
                if ((resFlag & i2) == i2) {
                    arrayList.add(strArr2[i]);
                }
                i++;
            }
        } else {
            this.b.a(this.f13806a, (ArrayList) null);
        }
    }
}
