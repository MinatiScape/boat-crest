package com.ido.ble.bluetooth.d;

import android.text.TextUtils;
import com.ido.ble.callback.GetDeviceInfoCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.MacAddressInfo;
/* loaded from: classes11.dex */
public class d {
    private static d d;

    /* renamed from: a  reason: collision with root package name */
    private b f12105a;
    private boolean b = false;
    private GetDeviceInfoCallBack.ICallBack c = new a();

    /* loaded from: classes11.dex */
    public class a extends com.ido.ble.callback.a {
        public a() {
        }

        @Override // com.ido.ble.callback.a, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetMacAddress(MacAddressInfo macAddressInfo) {
            if (d.this.b) {
                com.ido.ble.callback.b.N().b(d.this.c);
                if (macAddressInfo != null) {
                    LogTool.d(com.ido.ble.logs.a.q, "[GetBtMacAddressPresenter] getBtAddress from device is " + macAddressInfo.btAddress);
                    com.ido.ble.f.a.f.a.g0().c(macAddressInfo.btAddress);
                    d.this.f12105a.a(macAddressInfo.btAddress);
                } else {
                    d.this.f12105a.a("");
                }
                d.this.a();
            }
        }
    }

    /* loaded from: classes11.dex */
    public interface b {
        void a(String str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogTool.d(com.ido.ble.logs.a.q, "[GetBtMacAddressPresenter] finished");
        this.f12105a = null;
        this.b = false;
    }

    public static d b() {
        if (d == null) {
            d = new d();
        }
        return d;
    }

    public void a(b bVar) {
        LogTool.d(com.ido.ble.logs.a.q, "[GetBtMacAddressPresenter] getBtAddress start");
        String m = com.ido.ble.f.a.f.a.g0().m();
        if (!TextUtils.isEmpty(m)) {
            LogTool.d(com.ido.ble.logs.a.q, "[GetBtMacAddressPresenter] getBtAddress from cache is " + m);
            bVar.a(m);
            return;
        }
        this.b = true;
        this.f12105a = bVar;
        com.ido.ble.callback.b.N().b(this.c);
        com.ido.ble.callback.b.N().a(this.c);
        LogTool.d(com.ido.ble.logs.a.q, "[GetBtMacAddressPresenter] getBtAddress from device");
        com.ido.ble.i.a.a.I();
    }
}
