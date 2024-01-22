package com.ido.ble.dfu.d.b;

import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private b f12170a;
    private boolean b = false;
    private ConnectCallBack.ICallBack c = new C0584a();

    /* renamed from: com.ido.ble.dfu.d.b.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0584a implements ConnectCallBack.ICallBack {
        public C0584a() {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectBreak(String str) {
            LogTool.b(com.ido.ble.dfu.a.f12157a, "[DFUConnectTask] onConnectBreak");
            a.this.b();
            a.this.f12170a.b();
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectFailed(ConnectFailedReason connectFailedReason, String str) {
            LogTool.b(com.ido.ble.dfu.a.f12157a, "[DFUConnectTask] onConnectFailed");
            a.this.b();
            a.this.f12170a.b();
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectStart(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectSuccess(String str) {
            LogTool.d(com.ido.ble.dfu.a.f12157a, "[DFUConnectTask] onConnectSuccess");
            a.this.b();
            a.this.f12170a.a();
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnecting(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onDeviceInNotBindStatus(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInDfuMode(BLEDevice bLEDevice) {
            LogTool.b(com.ido.ble.dfu.a.f12157a, "[DFUConnectTask] onInDfuMode");
            a.this.b();
            a.this.f12170a.c();
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInitCompleted(String str) {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onRetry(int i, String str) {
        }
    }

    /* loaded from: classes11.dex */
    public interface b {
        void a();

        void b();

        void c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogTool.d(com.ido.ble.dfu.a.f12157a, "[DFUConnectTask] finished");
        c();
    }

    private void c() {
        this.b = false;
        com.ido.ble.callback.b.N().b(this.c);
    }

    public void a() {
        if (this.b) {
            LogTool.d(com.ido.ble.dfu.a.f12157a, "[DFUConnectTask] stop");
            c();
        }
    }

    public void a(b bVar, BLEDevice bLEDevice) {
        if (this.b) {
            LogTool.b(com.ido.ble.dfu.a.f12157a, "[DFUConnectTask] is in doing state, ignore this action");
            return;
        }
        LogTool.d(com.ido.ble.dfu.a.f12157a, "[DFUConnectTask] start");
        this.f12170a = bVar;
        this.b = true;
        com.ido.ble.callback.b.N().a(this.c);
        com.ido.ble.bluetooth.a.a(bLEDevice);
    }
}
