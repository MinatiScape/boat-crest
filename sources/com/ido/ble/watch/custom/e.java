package com.ido.ble.watch.custom;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Handler;
import android.os.Looper;
import com.ido.ble.bluetooth.setting.BluetoothGattSettingListener;
import com.ido.ble.gps.agps.AgpsFileTransConfig;
import com.ido.ble.gps.callback.GpsCallBack;
import com.ido.ble.gps.model.ConnParam;
import com.ido.ble.gps.model.ConnParamReply;
import com.ido.ble.gps.model.ControlGpsReply;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import com.veryfit.multi.nativeprotocol.Protocol;
import java.io.File;
@Deprecated
/* loaded from: classes11.dex */
class e {
    private static final String h = "WATCH_PLATE_FILE_TRANSLATE";
    private static final int i = 1000;
    private static e j = new e();
    private f d;
    private C0607e e;

    /* renamed from: a  reason: collision with root package name */
    private int f12321a = 0;
    private boolean b = false;
    private Handler c = new Handler(Looper.getMainLooper());
    private GpsCallBack.ITranAgpsFileCallBack f = new a();
    private GpsCallBack.IDeviceReplySetGpsCallBack g = new b();

    /* loaded from: classes11.dex */
    public class a implements GpsCallBack.ITranAgpsFileCallBack {
        public a() {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onFailed(int i) {
            e eVar = e.this;
            eVar.a("translate progress return code = " + i);
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onFailed(int i, Object obj) {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onFinish() {
            e.this.e();
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onProgress(int i) {
            e.this.a(i);
        }
    }

    /* loaded from: classes11.dex */
    public class b implements GpsCallBack.IDeviceReplySetGpsCallBack {
        public b() {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onControlGps(ControlGpsReply controlGpsReply) {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onSetConfigGps(boolean z) {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onSetConnParam(ConnParamReply connParamReply) {
            e.this.a(connParamReply);
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onSetHotStartGpsPara(boolean z) {
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            e.b(e.this);
            e.this.c();
        }
    }

    /* loaded from: classes11.dex */
    public class d implements BluetoothGattSettingListener.IListener {
        public d() {
        }

        @Override // com.ido.ble.bluetooth.setting.BluetoothGattSettingListener.IListener
        public BluetoothGattCharacteristic addParaToCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            bluetoothGattCharacteristic.setWriteType(1);
            return bluetoothGattCharacteristic;
        }
    }

    /* renamed from: com.ido.ble.watch.custom.e$e  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0607e extends AgpsFileTransConfig {

        /* renamed from: a  reason: collision with root package name */
        public String f12326a;

        @Override // com.ido.ble.gps.agps.AgpsFileTransConfig
        public String toString() {
            return "TransWatchPlateFileConfig{uniqueID='" + this.f12326a + "', filePath='" + this.filePath + "', listener=" + this.listener + ", PRN=" + this.PRN + '}';
        }
    }

    /* loaded from: classes11.dex */
    public enum f {
        STATE_NULL,
        CHECK_FILE,
        SET_FAST_SPEED,
        CHECK_FAST_SPEED_STATE,
        TRANSLATE_FILE
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2) {
        b(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ConnParamReply connParamReply) {
        if (connParamReply != null) {
            LogTool.d(h, connParamReply.toString());
        }
        f fVar = this.d;
        if (fVar != f.SET_FAST_SPEED) {
            if (fVar != f.CHECK_FAST_SPEED_STATE) {
                return;
            }
            if (connParamReply == null || connParamReply.currMode != 1) {
                if (this.f12321a < 5) {
                    this.c.postDelayed(new c(), 1000L);
                    return;
                }
                LogTool.b(h, "set fast translate mode failed.");
                a("set fast speed mode failed");
                return;
            }
            LogTool.d(h, "set fast translate mode ok.");
        }
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        LogTool.b(h, str);
        this.e.listener.onFailed(str);
        f();
    }

    public static /* synthetic */ int b(e eVar) {
        int i2 = eVar.f12321a;
        eVar.f12321a = i2 + 1;
        return i2;
    }

    private void b(int i2) {
        this.e.listener.onProgress(i2);
    }

    private boolean b() {
        LogTool.d(h, "check watch plate file.");
        this.d = f.CHECK_FILE;
        return new File(this.e.filePath).exists();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogTool.d(h, "check fast speed state.");
        this.d = f.CHECK_FAST_SPEED_STATE;
        ConnParam connParam = new ConnParam();
        connParam.mode = 0;
        com.ido.ble.h.a.a(connParam);
    }

    public static e d() {
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogTool.d(h, "translate watch plate file complete.");
        i();
    }

    private void f() {
        LogTool.d(h, "release.");
        BluetoothGattSettingListener.setBluetoothGattSettingListener(null);
        com.ido.ble.gps.callback.a.h().b(this.f);
        com.ido.ble.gps.callback.a.h().b(this.g);
        this.e = null;
        this.d = f.STATE_NULL;
        this.c.removeCallbacksAndMessages(null);
        this.b = false;
        this.f12321a = 0;
    }

    private void g() {
        LogTool.d(h, "set fast translate mode.");
        this.d = f.SET_FAST_SPEED;
        ConnParam connParam = new ConnParam();
        connParam.mode = 1;
        com.ido.ble.h.a.a(connParam);
    }

    private void h() {
        Protocol protocol;
        int i2;
        String str;
        LogTool.d(h, "begin translate watch plate file...");
        this.d = f.TRANSLATE_FILE;
        BluetoothGattSettingListener.setBluetoothGattSettingListener(new d());
        if (this.e.PRN <= 0) {
            protocol = Protocol.getInstance();
            i2 = 1;
        } else {
            protocol = Protocol.getInstance();
            i2 = this.e.PRN;
        }
        protocol.tranDataSetPRN(i2);
        byte[] a2 = com.ido.ble.common.c.a(this.e.filePath);
        if (a2 != null && a2.length > 0) {
            int a3 = u.a(a2, this.e.f12326a, 0);
            if (a3 != 0) {
                str = "setWatchPlateFileTranParas return code is " + a3;
            }
            Protocol.getInstance().tranDataStart();
        }
        str = "watchPlateData byte data is null";
        a(str);
        Protocol.getInstance().tranDataStart();
    }

    private void i() {
        LogTool.d(h, "translate success!");
        this.e.listener.onSuccess();
        f();
    }

    public void a() {
        if (this.b) {
            LogTool.d(h, "stop.");
            Protocol.getInstance().tranDataStop();
            f();
        }
    }

    public void a(C0607e c0607e) {
        if (this.b) {
            LogTool.b(h, "is in staring state, try to stop and start");
            a();
        }
        LogTool.d(h, "start ...");
        LogTool.d(h, "config is  " + c0607e.toString());
        this.e = c0607e;
        c0607e.listener.onStart();
        if (!b()) {
            a("watch plate is not exist.");
            return;
        }
        this.b = true;
        com.ido.ble.gps.callback.a.h().a(this.f);
        com.ido.ble.gps.callback.a.h().a(this.g);
        g();
    }
}
