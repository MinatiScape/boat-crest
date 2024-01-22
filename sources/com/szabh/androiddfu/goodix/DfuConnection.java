package com.szabh.androiddfu.goodix;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.clevertap.android.sdk.Constants;
import com.goodix.ble.gr.libdfu.EasyDfu;
import com.goodix.ble.libcomx.transceiver.IFrameSender;
import com.szabh.androiddfu.goodix.ble_connect.BLEConnectCallback;
import com.szabh.androiddfu.goodix.ble_connect.BLEConnectManager;
import java.util.UUID;
/* loaded from: classes12.dex */
public class DfuConnection {
    public static final UUID k = UUID.fromString("a6ed0401-d344-460a-8075-b9e8ec90d71b");
    public static final UUID l = UUID.fromString("a6ed0402-d344-460a-8075-b9e8ec90d71b");
    public static final UUID m = UUID.fromString("a6ed0403-d344-460a-8075-b9e8ec90d71b");

    /* renamed from: a  reason: collision with root package name */
    public BluetoothGattCharacteristic f13714a;
    public BluetoothGattCharacteristic b;
    public BluetoothDevice c;
    public BLEConnectManager d;
    public EasyDfu e = new EasyDfu();
    public MutableLiveData<ConnState> f = new MutableLiveData<>();
    public MutableLiveData<String> g = new MutableLiveData<>();
    public Handler h = new Handler();
    public IFrameSender i = new a();
    public BLEConnectCallback j = new b();

    /* loaded from: classes12.dex */
    public enum ConnState {
        CONNECTING,
        CONNECTED,
        INIT,
        READY,
        DISCONNECTING,
        DISCONNECTED
    }

    /* loaded from: classes12.dex */
    public class a implements IFrameSender {
        public a() {
        }

        @Override // com.goodix.ble.libcomx.transceiver.IFrameSender
        public boolean sendFrame(byte[] bArr) {
            if (DfuConnection.this.f.getValue() == ConnState.READY) {
                Log.v("GoodixDfu", "sendFrame()" + HexUtil.encodeHexStr(bArr));
                DfuConnection.this.b.setValue(bArr);
                DfuConnection.this.d.writeCharacteristic(DfuConnection.this.b, bArr, DfuConnection.this.b.getWriteType());
                return true;
            }
            return false;
        }
    }

    /* loaded from: classes12.dex */
    public class b implements BLEConnectCallback {

        /* loaded from: classes12.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                DfuConnection.this.d.changeMtu(247);
            }
        }

        public b() {
        }

        @Override // com.szabh.androiddfu.goodix.ble_connect.BLEConnectCallback
        public void onBleCharacteristicNotify(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            Log.d("GoodixDfu", "onBleCharacteristicNotify() called with: characteristic = [" + bluetoothGattCharacteristic.getUuid() + "]" + HexUtil.encodeHexStr(bluetoothGattCharacteristic.getValue()));
            if (bluetoothGattCharacteristic.equals(DfuConnection.this.f13714a)) {
                DfuConnection.this.e.onRcvPduSegment(bluetoothGattCharacteristic.getValue());
            }
        }

        @Override // com.szabh.androiddfu.goodix.ble_connect.BLEConnectCallback
        public void onBleCharacteristicWriteComplete(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            Log.d("GoodixDfu", "onBleCharacteristicWriteComplete() called with: characteristic = [" + bluetoothGattCharacteristic.getUuid() + "]" + HexUtil.encodeHexStr(bluetoothGattCharacteristic.getValue()));
            DfuConnection.this.e.onSentPduSegment();
        }

        @Override // com.szabh.androiddfu.goodix.ble_connect.BLEConnectCallback
        public void onBleConnected() {
            Log.d("GoodixDfu", "onBleConnected() called");
            DfuConnection.this.f.postValue(ConnState.CONNECTED);
        }

        @Override // com.szabh.androiddfu.goodix.ble_connect.BLEConnectCallback
        public void onBleDisconnected() {
            Log.d("GoodixDfu", "onBleDisconnected() called");
            DfuConnection.this.f.postValue(ConnState.DISCONNECTED);
        }

        @Override // com.szabh.androiddfu.goodix.ble_connect.BLEConnectCallback
        public void onBleError(String str, int i) {
            Log.d("GoodixDfu", "onBleError() called with: message = [" + str + "], errorCode = [" + i + "]");
            DfuConnection.this.g.postValue(str);
        }

        @Override // com.szabh.androiddfu.goodix.ble_connect.BLEConnectCallback
        public void onBleMtuChanged(int i) {
            Log.d("GoodixDfu", "onBleMtuChanged() called with: mtu = [" + i + "]");
            DfuConnection.this.e.setMaxSegmentSize(i + (-3));
            DfuConnection.this.f.postValue(ConnState.READY);
        }

        @Override // com.szabh.androiddfu.goodix.ble_connect.BLEConnectCallback
        public void onBleNotifyEnable() {
            Log.d("GoodixDfu", "onBleNotifyEnable() called");
            DfuConnection.this.h.postDelayed(new a(), Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
        }

        @Override // com.szabh.androiddfu.goodix.ble_connect.BLEConnectCallback
        public void onBleServicesDiscovered(BluetoothGatt bluetoothGatt) {
            Log.d("GoodixDfu", "onBleServicesDiscovered() called with: gatt = [" + bluetoothGatt + "]");
            BluetoothGattService service = bluetoothGatt.getService(DfuConnection.k);
            if (service != null) {
                DfuConnection.this.f13714a = service.getCharacteristic(DfuConnection.l);
                DfuConnection.this.b = service.getCharacteristic(DfuConnection.m);
            }
            if (DfuConnection.this.f13714a == null || DfuConnection.this.b == null) {
                DfuConnection.this.g.postValue("Device Not Support");
                DfuConnection.this.requestDisconnect();
                return;
            }
            DfuConnection.this.d.enableNotify(DfuConnection.this.f13714a);
            DfuConnection.this.f.postValue(ConnState.INIT);
        }

        @Override // com.szabh.androiddfu.goodix.ble_connect.BLEConnectCallback
        public void onBleTimeOut(String str) {
            Log.d("GoodixDfu", "onBleTimeOut() called with: type = [" + str + "]");
            MutableLiveData mutableLiveData = DfuConnection.this.g;
            mutableLiveData.postValue("onBleTimeOut() called with: type = [" + str + "]");
        }
    }

    static {
        UUID.fromString("a6ed0404-d344-460a-8075-b9e8ec90d71b");
    }

    public DfuConnection(Context context) {
        this.d = new BLEConnectManager(context, this.j);
        this.e.setPduSender(this.i);
    }

    public LiveData<ConnState> getConnectionState() {
        return this.f;
    }

    public BluetoothDevice getDevice() {
        return this.c;
    }

    public EasyDfu getDfu() {
        return this.e;
    }

    public LiveData<String> getErrorInfo() {
        return this.g;
    }

    public void requestConnect() {
        if (this.c == null) {
            return;
        }
        this.f.postValue(ConnState.CONNECTING);
        this.d.connect(this.c, false, 30000);
    }

    public void requestDisconnect() {
        if (this.c == null) {
            return;
        }
        this.f.postValue(ConnState.DISCONNECTING);
        this.d.disconnect();
    }

    public void setDevice(BluetoothDevice bluetoothDevice) {
        this.c = bluetoothDevice;
    }

    public DfuConnection(Context context, BluetoothDevice bluetoothDevice) {
        this.d = new BLEConnectManager(context, this.j);
        this.c = bluetoothDevice;
        this.e.setPduSender(this.i);
    }
}
