package com.ido.ble.dfu.d.b;

import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.common.n;
import com.ido.ble.logs.LogTool;
/* loaded from: classes11.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private c f12172a;
    private boolean b = false;
    private int c = -1;
    private ConnectCallBack.ICallBack d = new a();

    /* loaded from: classes11.dex */
    public class a implements ConnectCallBack.ICallBack {

        /* renamed from: com.ido.ble.dfu.d.b.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class C0585a implements n.b {
            public C0585a() {
            }

            @Override // com.ido.ble.common.n.b
            public void a() {
                b.this.f12172a.a();
            }
        }

        public a() {
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectBreak(String str) {
            LogTool.d(com.ido.ble.dfu.a.f12157a, "[DFUDisconnectTask] onConnectBreak");
            n.a(b.this.c);
            b.this.b();
            n.a(new C0585a(), 1000L);
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

    /* renamed from: com.ido.ble.dfu.d.b.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0586b implements n.b {
        public C0586b() {
        }

        @Override // com.ido.ble.common.n.b
        public void a() {
            LogTool.b(com.ido.ble.dfu.a.f12157a, "[DFUDisconnectTask] onTimeOut");
            b.this.b();
            b.this.f12172a.a();
        }
    }

    /* loaded from: classes11.dex */
    public interface c {
        void a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogTool.d(com.ido.ble.dfu.a.f12157a, "[DFUDisconnectTask] finished");
        c();
    }

    private void c() {
        this.b = false;
        com.ido.ble.callback.b.N().b(this.d);
    }

    private void d() {
        this.c = n.a(new C0586b(), 5000L);
    }

    public void a() {
        if (this.b) {
            LogTool.d(com.ido.ble.dfu.a.f12157a, "[DFUDisconnectTask] stop");
            n.a(this.c);
            c();
        }
    }

    public void a(c cVar) {
        if (this.b) {
            LogTool.b(com.ido.ble.dfu.a.f12157a, "[DFUDisconnectTask] is in doing state, ignore this action");
            return;
        }
        LogTool.d(com.ido.ble.dfu.a.f12157a, "[DFUDisconnectTask] start");
        this.f12172a = cVar;
        this.b = true;
        com.ido.ble.callback.b.N().a(this.d);
        d();
        com.ido.ble.bluetooth.a.b();
    }
}
