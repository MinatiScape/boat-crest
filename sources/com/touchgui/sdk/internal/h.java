package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGConnectCallback;
import com.touchgui.sdk.TGConnectionListener;
import com.touchgui.sdk.bean.TGDevice;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class h implements TGConnectionListener {

    /* renamed from: a  reason: collision with root package name */
    public final String f13768a;
    public final /* synthetic */ com.touchgui.sdk.a b;

    public h(com.touchgui.sdk.a aVar, String str) {
        this.b = aVar;
        this.f13768a = str;
    }

    @Override // com.touchgui.sdk.TGConnectionListener
    public final void onConnectionStateChange(int i, String str) {
        Iterator it = this.b.c.iterator();
        while (it.hasNext()) {
            ((TGConnectCallback) it.next()).onConnectStateChange(str, i);
        }
    }

    @Override // com.touchgui.sdk.TGConnectionListener
    public final void onError(int i) {
        com.touchgui.sdk.a aVar = this.b;
        String str = this.f13768a;
        Iterator it = aVar.c.iterator();
        while (it.hasNext()) {
            ((TGConnectCallback) it.next()).onError(str, i);
        }
    }

    @Override // com.touchgui.sdk.TGConnectionListener
    public final void onReady(TGDevice tGDevice) {
        Iterator it = this.b.c.iterator();
        while (it.hasNext()) {
            ((TGConnectCallback) it.next()).onReady(tGDevice);
        }
    }
}
