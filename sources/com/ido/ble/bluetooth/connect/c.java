package com.ido.ble.bluetooth.connect;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.DeviceGattCallBack;
import com.ido.ble.logs.LogTool;
import java.lang.reflect.Field;
import java.util.LinkedList;
/* loaded from: classes11.dex */
abstract class c extends com.ido.ble.bluetooth.connect.b {
    private static final int v = 1;
    private static final long w = 3000;
    private static final int x = 10;
    private static long y = 2000;
    private BluetoothGattCharacteristic s;
    private BluetoothGattCharacteristic t;
    private LinkedList<byte[]> o = new LinkedList<>();
    private boolean p = false;
    private boolean q = false;
    private int r = 0;
    private Handler u = new a(Looper.getMainLooper());

    /* loaded from: classes11.dex */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1 && c.this.p) {
                c.this.p = false;
                if (c.this.o.size() > 10) {
                    LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BytesDataConnect] last send out time, mCmdDataQueue.size() > 10, clear and disconnect");
                    c.this.u();
                    return;
                }
                LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BytesDataConnect] no respond on last cmd, send next ...");
                c.this.A();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ byte[] f12066a;

        public b(byte[] bArr) {
            this.f12066a = bArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.q = true;
            String str = com.ido.ble.bluetooth.f.b.f12116a;
            LogTool.b(str, "[BytesDataConnect] retry send => " + com.ido.ble.common.c.b(this.f12066a));
            c.this.i(this.f12066a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void A() {
        if (this.p) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BytesDataConnect] sendNextCmdData is ing");
        } else if (this.q) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BytesDataConnect] retrySendData is ing");
        } else if (this.o.size() == 0) {
        } else {
            this.p = true;
            byte[] poll = this.o.poll();
            if (poll == null) {
                LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BytesDataConnect] sendNextCmdData data is null");
                this.p = false;
                A();
                return;
            }
            String str = com.ido.ble.bluetooth.f.b.f12116a;
            LogTool.d(str, "[BytesDataConnect] send => " + com.ido.ble.common.c.b(poll));
            if (r()) {
                i(poll);
                return;
            }
            this.p = false;
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BytesDataConnect] send(), isCanSendData = false. send failed");
        }
    }

    private BluetoothGattCharacteristic a(BluetoothGatt bluetoothGatt, byte[] bArr) {
        if (d(bArr)) {
            if (this.t == null) {
                this.t = com.ido.ble.bluetooth.f.a.a(bluetoothGatt);
            }
            return this.t;
        }
        if (this.s == null) {
            this.s = com.ido.ble.bluetooth.f.a.b(bluetoothGatt);
        }
        return this.s;
    }

    private void a(byte[] bArr, int i) {
        if (i == 0) {
            String str = com.ido.ble.bluetooth.f.b.f12116a;
            LogTool.d(str, "[BytesDataConnect] onDeviceResponseOnLastSend( " + com.ido.ble.common.c.b(bArr) + ")");
        } else {
            String str2 = com.ido.ble.bluetooth.f.b.f12116a;
            LogTool.b(str2, "[BytesDataConnect] onDeviceResponseOnLastSend[failed]( " + com.ido.ble.common.c.b(bArr) + ")");
        }
        this.p = false;
        this.u.removeMessages(1);
        A();
    }

    private void b(byte[] bArr) {
        if (!r()) {
            this.q = false;
            this.r = 0;
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BytesDataConnect] handleWriteFailedStatus(), isCanSendData = false. send failed");
            return;
        }
        int i = this.r + 1;
        this.r = i;
        if (i > 10) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BytesDataConnect] handleWriteFailedStatus, mSendFailedCount > 10, clear and disconnect");
            u();
            this.q = false;
            this.r = 0;
        } else if (!c(bArr) && !f(bArr)) {
            this.p = false;
            A();
        } else {
            this.q = true;
            String str = com.ido.ble.bluetooth.f.b.f12116a;
            LogTool.b(str, "[BytesDataConnect] isFileTranCmd  mIsReSendData:" + this.q);
            h(bArr);
        }
    }

    private boolean c(byte[] bArr) {
        return (bArr[0] & 255) == 209;
    }

    private boolean d(byte[] bArr) {
        return bArr[0] == 8 || bArr[0] == 9;
    }

    private boolean e(byte[] bArr) {
        return (bArr[0] & 255) == 209 || (bArr[0] & 255) == 19;
    }

    private boolean f(byte[] bArr) {
        return (bArr[0] & 255) == 19 || (bArr[0] & 255) == 17;
    }

    private void g(byte[] bArr) {
        String str = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.d(str, "[BytesDataConnect] receive <= " + com.ido.ble.common.c.b(bArr));
    }

    private void h(byte[] bArr) {
        this.u.postDelayed(new b(bArr), 300L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(byte[] bArr) {
        if (!j(bArr)) {
            b(bArr);
            return;
        }
        this.r = 0;
        this.q = false;
        String str = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.b(str, "[BytesDataConnect] mIsReSendData " + this.q);
        if (!e(bArr)) {
            this.u.sendEmptyMessageDelayed(1, w);
            return;
        }
        this.p = false;
        A();
    }

    private boolean j(byte[] bArr) {
        String str;
        String str2;
        BluetoothGattCharacteristic a2 = a(w(), bArr);
        if (a2 == null || (a2.getProperties() & 8) <= 0) {
            str = com.ido.ble.bluetooth.f.b.f12116a;
            str2 = "[BytesDataConnect] send(), characteristic error!";
        } else {
            a2.setValue(bArr);
            a2.setWriteType(e(bArr) ? 1 : 2);
            if ((a2.getProperties() & 12) != 0) {
                boolean writeCharacteristic = w().writeCharacteristic(a2);
                if (!writeCharacteristic) {
                    LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BytesDataConnect] send(), writeCharacteristic() error!");
                }
                return writeCharacteristic;
            }
            str = com.ido.ble.bluetooth.f.b.f12116a;
            str2 = "[BytesDataConnect] send(), characteristic.properties error!";
        }
        LogTool.b(str, str2);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BytesDataConnect] clearQueueAndDisconnect");
        this.o.clear();
        o();
    }

    private void v() {
        this.o.clear();
        this.p = false;
        this.q = false;
        this.r = 0;
        this.u.removeCallbacksAndMessages(null);
        this.t = null;
        this.s = null;
    }

    private BluetoothGatt w() {
        return q();
    }

    private void x() {
        if (this.o.size() > 10) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BytesDataConnect] cmd queue is out of max size, handle it...");
            for (int i = 0; i < 5; i++) {
                this.o.pollFirst();
            }
        }
    }

    private boolean y() {
        try {
            return ((Boolean) a(w(), "mDeviceBusy")).booleanValue();
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            String str = com.ido.ble.bluetooth.f.b.f12116a;
            LogTool.b(str, "[BytesDataConnect] isDeviceBusy()，e.printStackTrace() :" + e.toString());
            return false;
        }
    }

    private void z() {
        v();
    }

    public Object a(Object obj, String str) {
        Field declaredField = obj.getClass().getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField.get(obj);
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void a(int i, int i2) {
        v();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        g(bluetoothGattCharacteristic.getValue());
        DeviceGattCallBack.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        a(bluetoothGattCharacteristic.getValue(), i);
        DeviceGattCallBack.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
    }

    public void a(byte[] bArr, boolean z) {
        if (bArr == null || bArr.length == 0) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BytesDataConnect] onAddCmd() ignore, data is null");
            return;
        }
        String str = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.d(str, "[BytesDataConnect] addCmdData( " + com.ido.ble.common.c.b(bArr) + ")");
        if (z) {
            this.o.addFirst(bArr);
        } else {
            this.o.add(bArr);
        }
        String str2 = com.ido.ble.bluetooth.f.b.f12116a;
        LogTool.d(str2, "[BytesDataConnect] addCmdData que size = " + this.o.size());
        A();
    }

    public boolean a(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (w() == null) {
            LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BytesDataConnect] getRealGatt() == null!");
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - currentTimeMillis < y) {
                if (!y()) {
                    LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BytesDataConnect] mDeviceBusy == false，break");
                    break;
                }
                try {
                    Thread.sleep(100L);
                    LogTool.b(com.ido.ble.bluetooth.f.b.f12116a, "[BytesDataConnect] mDeviceBusy == true，sleep 100ms");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    String str = com.ido.ble.bluetooth.f.b.f12116a;
                    LogTool.b(str, "[BytesDataConnect] mDeviceBusy == true，e.printStackTrace() :" + e.toString());
                }
            } else {
                break;
            }
        }
        return w().writeCharacteristic(bluetoothGattCharacteristic);
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void b(BLEDevice bLEDevice, long j) {
        super.b(bLEDevice, j);
        z();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void d(BLEDevice bLEDevice) {
        super.d(bLEDevice);
        z();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void e() {
        v();
    }

    @Override // com.ido.ble.bluetooth.connect.b
    public void j() {
    }
}
