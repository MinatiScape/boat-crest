package com.ido.ble.bluetooth.connect;

import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.common.n;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private c f12069a;
    private boolean b = false;
    private int c = -1;
    private ConnectCallBack.ICallBack d = new a();

    /* loaded from: classes11.dex */
    public class a implements ConnectCallBack.ICallBack {

        /* renamed from: com.ido.ble.bluetooth.connect.g$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class C0571a implements n.b {
            public C0571a() {
            }

            @Override // com.ido.ble.common.n.b
            public void a() {
                g.this.f12069a.a();
            }
        }

        public a() {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectBreak(String str) {
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[DisconnectTask] disconnect success");
            com.ido.ble.common.n.a(g.this.c);
            g.this.b();
            com.ido.ble.common.n.a(new C0571a(), 1000L);
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectFailed(ConnectFailedReason connectFailedReason, String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectStart(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectSuccess(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnecting(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onDeviceInNotBindStatus(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInDfuMode(BLEDevice bLEDevice) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInitCompleted(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onRetry(int i, String str) {
        }
    }

    /* loaded from: classes11.dex */
    public class b implements n.b {
        public b() {
        }

        @Override // com.ido.ble.common.n.b
        public void a() {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[DisconnectTask] onTimeOut");
            g.this.b();
            g.this.f12069a.a();
        }
    }

    /* loaded from: classes11.dex */
    public interface c {
        void a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[DisconnectTask] finished");
        c();
    }

    private void c() {
        this.b = false;
        com.ido.ble.callback.b.N().b(this.d);
    }

    private void d() {
        this.c = com.ido.ble.common.n.a(new b(), 6000L);
    }

    public void a() {
        if (this.b) {
            LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[DisconnectTask] stop");
            com.ido.ble.common.n.a(this.c);
            c();
        }
    }

    public void a(c cVar) {
        if (this.b) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[DisconnectTask] is in doing state, ignore this action");
            return;
        }
        LogTool.d(com.ido.ble.bluetooth.f.b.f12116a, "[DisconnectTask] start");
        this.f12069a = cVar;
        this.b = true;
        com.ido.ble.callback.b.N().a(this.d);
        d();
        com.ido.ble.bluetooth.a.b();
    }
}
