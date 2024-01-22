package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGCallback;
import com.touchgui.sdk.TGConnectionListener;
import com.touchgui.sdk.TGErrorCode;
import com.touchgui.sdk.bean.TGDevice;
import com.touchgui.sdk.bean.TGDeviceInfo;
import com.touchgui.sdk.exception.TGException;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class u implements TGCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f13827a;
    public final /* synthetic */ String b;
    public final /* synthetic */ a0 c;

    public u(a0 a0Var, String str, String str2) {
        this.c = a0Var;
        this.f13827a = str;
        this.b = str2;
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onFailure(Throwable th) {
        if ((th instanceof TGException) && ((TGException) th).getCode() == 10004) {
            return;
        }
        Iterator it = this.c.g.iterator();
        while (it.hasNext()) {
            ((TGConnectionListener) it.next()).onError(TGErrorCode.ERROR_CONNECTED_INIT_FAILURE);
        }
    }

    @Override // com.touchgui.sdk.TGCallback
    public final void onSuccess(Object obj) {
        a0 a0Var;
        TGDeviceInfo tGDeviceInfo = (TGDeviceInfo) obj;
        int i = 0;
        boolean z = (tGDeviceInfo.getRunMode() == 0 && tGDeviceInfo.getResFlag() == 0) ? false : true;
        TGDevice tGDevice = new TGDevice();
        tGDevice.setName(this.f13827a);
        tGDevice.setAddress(this.b);
        tGDevice.setVersionCode(tGDeviceInfo.getVersionCode());
        tGDevice.setForceOTA(z);
        tGDevice.setPaired(tGDeviceInfo.getPairFlag() == 1);
        tGDevice.setBattery(tGDeviceInfo.getEnerge());
        tGDevice.setResFlag(tGDeviceInfo.getResFlag());
        if (tGDeviceInfo.getId() >= 500) {
            a0Var = this.c;
            i = tGDeviceInfo.getId();
        } else {
            a0Var = this.c;
        }
        a0Var.f13739a = i;
        this.c.b = tGDeviceInfo.getPlatform();
        this.c.c = tGDeviceInfo.getProtocolVersion();
        a0 a0Var2 = this.c;
        if (a0Var2.c >= 777) {
            new v8(a0Var2, new z7()).execute(new x(a0Var2, tGDevice));
        } else {
            new v8(a0Var2, new l7()).execute(new v(a0Var2, tGDevice));
        }
    }
}
