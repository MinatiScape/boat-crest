package com.goodix.ble.libble.v2.impl;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.util.Log;
import androidx.annotation.AnyThread;
import androidx.annotation.IntRange;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.goodix.ble.libble.BleUuid;
import com.goodix.ble.libble.chain.GattCallbackDispatcher;
import com.goodix.ble.libble.v2.gb.pojo.GBError;
import com.goodix.ble.libble.v2.impl.data.BleCI;
import com.goodix.ble.libble.v2.impl.data.BleIntState;
import com.goodix.ble.libble.v2.impl.data.BlePairingVariant;
import com.goodix.ble.libble.v2.impl.data.BlePhy;
import com.goodix.ble.libble.v2.impl.data.BleValue;
import com.goodix.ble.libcomx.ILogger;
import com.goodix.ble.libcomx.event.Event;
import com.goodix.ble.libcomx.logger.Logger;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.lang.reflect.Method;
/* loaded from: classes5.dex */
public class BleGattX extends GattCallbackDispatcher {
    public static final int EVT_ADAPTER_STATE_CHANGED = 4003;
    public static final int EVT_BOND_STATE_CHANGED = 4004;
    public static final int EVT_CHAR_CHANGED = 1005;
    public static final int EVT_CHAR_INDICATE = 1004;
    public static final int EVT_CHAR_NOTIFY = 1003;
    public static final int EVT_CHAR_READ = 1001;
    public static final int EVT_CHAR_WRITTEN = 1002;
    public static final int EVT_CI = 3004;
    public static final int EVT_CONNECTION_CHANGED = 2001;
    public static final int EVT_DESC_READ = 1006;
    public static final int EVT_DESC_WRITTEN = 1007;
    public static final int EVT_ERROR = 4001;
    public static final int EVT_LINK_LOSS = 4002;
    public static final int EVT_MTU = 3002;
    public static final int EVT_PHY = 3003;
    public static final int EVT_RSSI = 3001;
    public static final int EVT_SERVICE_DISCOVERED = 2005;
    @Nullable
    public ILogger r;
    public final Context s;
    public BluetoothGatt t;
    public BluetoothDevice u;
    public boolean v;
    public boolean w;
    public int x;
    public int y;
    public Event<GBError> b = new Event<>(this, EVT_ERROR);
    public Event<Integer> c = new Event<>(this, 2001);
    public Event<Integer> d = new Event<>(this, EVT_SERVICE_DISCOVERED);
    public Event<BleValue> e = new Event<>(this, 1001);
    public Event<BleValue> f = new Event<>(this, 1002);
    public Event<BleValue> g = new Event<>(this, 1003);
    public Event<BleValue> h = new Event<>(this, 1004);
    public Event<BleValue> i = new Event<>(this, 1005);
    public Event<BluetoothGattDescriptor> j = new Event<>(this, 1006);
    public Event<BluetoothGattDescriptor> k = new Event<>(this, 1007);
    public Event<Integer> l = new Event<>(this, 3002);
    public Event<Integer> m = new Event<>(this, 3001);
    public Event<BlePhy> n = new Event<>(this, 3003);
    public Event<BleCI> o = new Event<>(this, 3004);
    public Event<BleIntState> p = new Event<>(this, EVT_ADAPTER_STATE_CHANGED);
    public Event<BleIntState> q = new Event<>(this, EVT_BOND_STATE_CHANGED);
    public boolean z = false;
    public c A = new c();
    public b B = new b();
    public d C = new d();

    /* loaded from: classes5.dex */
    public class b extends BroadcastReceiver {
        public b() {
        }

        public final String a(int i) {
            switch (i) {
                case 10:
                    return "OFF";
                case 11:
                    return "TURNING ON";
                case 12:
                    return "ON";
                case 13:
                    return "TURNING OFF";
                default:
                    return "UNKNOWN (" + i + ")";
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
            int intExtra2 = intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", 10);
            ILogger iLogger = BleGattX.this.r;
            if (iLogger != null) {
                iLogger.d("BleGatt", "[Broadcast] Action received: android.bluetooth.adapter.action.STATE_CHANGED, state changed from " + a(intExtra2) + " to " + a(intExtra));
            }
            BleGattX.this.p.postEvent(new BleIntState(intExtra2, intExtra));
            if (intExtra != 10) {
                return;
            }
            BleGattX.this.tryDisconnect();
            BleGattX.this.tryCloseGatt();
        }
    }

    /* loaded from: classes5.dex */
    public class c extends BroadcastReceiver {
        public c() {
        }

        public final String a(int i) {
            switch (i) {
                case 10:
                    return "BOND_NONE";
                case 11:
                    return "BOND_BONDING";
                case 12:
                    return "BOND_BONDED";
                default:
                    return "UNKNOWN";
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str;
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1);
            int intExtra2 = intent.getIntExtra("android.bluetooth.device.extra.PREVIOUS_BOND_STATE", -1);
            BluetoothDevice bluetoothDevice2 = BleGattX.this.u;
            if (bluetoothDevice == null || bluetoothDevice2 == null || !bluetoothDevice.getAddress().equals(bluetoothDevice2.getAddress())) {
                return;
            }
            ILogger iLogger = BleGattX.this.r;
            if (iLogger != null) {
                iLogger.d("BleGatt", "[Broadcast] Action received: android.bluetooth.device.action.BOND_STATE_CHANGED, bond state changed from " + a(intExtra2) + " to " + a(intExtra));
            }
            BleGattX.this.q.postEvent(new BleIntState(intExtra2, intExtra));
            if (intExtra != 10) {
                if (intExtra != 12 || iLogger == null) {
                    return;
                }
                str = "Device bonded";
            } else if (intExtra2 == 11) {
                if (iLogger != null) {
                    iLogger.w("BleGatt", "Bonding failed");
                    return;
                }
                return;
            } else if (intExtra2 != 12 || iLogger == null) {
                return;
            } else {
                str = "Bond information removed";
            }
            iLogger.i("BleGatt", str);
        }
    }

    /* loaded from: classes5.dex */
    public class d extends BroadcastReceiver {
        public d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            BluetoothDevice bluetoothDevice2 = BleGattX.this.u;
            if (bluetoothDevice == null || bluetoothDevice2 == null || !bluetoothDevice.getAddress().equals(bluetoothDevice2.getAddress())) {
                return;
            }
            int intExtra = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", 0);
            ILogger iLogger = BleGattX.this.r;
            if (iLogger != null) {
                iLogger.d("BleGatt", "[Broadcast] Action received: android.bluetooth.device.action.PAIRING_REQUEST, pairing variant: " + BlePairingVariant.toString(intExtra) + " (" + intExtra + ")");
            }
        }
    }

    public BleGattX(@NonNull Context context) {
        this.s = context.getApplicationContext();
    }

    public static String c(HexStringBuilder hexStringBuilder, byte[] bArr) {
        if (bArr == null) {
            return "null";
        }
        if (bArr.length == 0) {
            return "[0]";
        }
        if (hexStringBuilder == null) {
            hexStringBuilder = new HexStringBuilder((bArr.length * 2) + 8);
        }
        hexStringBuilder.a("[").append(bArr.length).a("] ").put(bArr);
        hexStringBuilder.a(" (");
        for (byte b2 : bArr) {
            int i = b2 & 255;
            int i2 = 46;
            if (i < 32) {
                i = 46;
            }
            if (i <= 126) {
                i2 = i;
            }
            hexStringBuilder.append((char) i2);
        }
        hexStringBuilder.a(")");
        return hexStringBuilder.toString();
    }

    public static String d(byte[] bArr) {
        return c(null, bArr);
    }

    public static String gattStatusToString(int i) {
        HexStringBuilder hexStringBuilder = new HexStringBuilder(64);
        hexStringBuilder.append((CharSequence) "[0x").append((CharSequence) Integer.toHexString(i)).append((CharSequence) "] ");
        hexStringBuilder.append((CharSequence) GattStatus.gattStatusToString(i));
        return hexStringBuilder.toString();
    }

    public final String b(int i) {
        switch (i) {
            case 1:
                return "LE 1M";
            case 2:
                return "LE 2M";
            case 3:
                return "LE 1M or LE 2M";
            case 4:
                return "LE Coded";
            case 5:
                return "LE 1M or LE Coded";
            case 6:
                return "LE 2M or LE Coded";
            case 7:
                return "LE 1M, LE 2M or LE Coded";
            default:
                return "UNKNOWN (" + i + ")";
        }
    }

    public void cleanReceiver() {
        if (this.z) {
            Logger.d(this.r, "BleGatt", "cleanReceiver()");
            try {
                this.s.unregisterReceiver(this.B);
                this.s.unregisterReceiver(this.C);
                this.s.unregisterReceiver(this.A);
            } catch (Exception e) {
                ILogger iLogger = this.r;
                if (iLogger != null) {
                    iLogger.w("BleGatt", "Error on cleaning up receiver: " + e);
                }
            }
            this.z = false;
        }
    }

    public void clearListener(Object obj) {
        this.g.clear(obj);
        this.h.clear(obj);
        this.e.clear(obj);
        this.f.clear(obj);
        this.j.clear(obj);
        this.k.clear(obj);
        this.l.clear(obj);
        this.m.clear(obj);
        this.n.clear(obj);
        this.o.clear(obj);
        this.b.clear(obj);
        this.d.clear(obj);
        this.c.clear(obj);
    }

    public final boolean e(BluetoothGattDescriptor bluetoothGattDescriptor) {
        BluetoothGatt bluetoothGatt = this.t;
        if (bluetoothGatt == null || bluetoothGattDescriptor == null || !this.w) {
            return false;
        }
        BluetoothGattCharacteristic characteristic = bluetoothGattDescriptor.getCharacteristic();
        int writeType = characteristic.getWriteType();
        characteristic.setWriteType(2);
        boolean writeDescriptor = bluetoothGatt.writeDescriptor(bluetoothGattDescriptor);
        characteristic.setWriteType(writeType);
        return writeDescriptor;
    }

    public Event<BleIntState> evtAdapterStateChanged() {
        return this.p;
    }

    public Event<BleIntState> evtBondStateChanged() {
        return this.q;
    }

    public Event<BleCI> evtCI() {
        return this.o;
    }

    public Event<BleValue> evtCharChanged() {
        return this.i;
    }

    public Event<BleValue> evtCharIndicate() {
        return this.h;
    }

    public Event<BleValue> evtCharNotify() {
        return this.g;
    }

    public Event<BleValue> evtCharRead() {
        return this.e;
    }

    public Event<BleValue> evtCharWritten() {
        return this.f;
    }

    public Event<BluetoothGattDescriptor> evtDescRead() {
        return this.j;
    }

    public Event<BluetoothGattDescriptor> evtDescWritten() {
        return this.k;
    }

    public Event<GBError> evtError() {
        return this.b;
    }

    public Event<Integer> evtMtu() {
        return this.l;
    }

    public Event<BlePhy> evtPhy() {
        return this.n;
    }

    public Event<Integer> evtRssi() {
        return this.m;
    }

    public Event<Integer> evtServiceDiscovered() {
        return this.d;
    }

    public Event<Integer> evtStateChanged() {
        return this.c;
    }

    @RequiresApi(26)
    public final String g(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return "UNKNOWN (" + i + ")";
                }
                return "LE Coded";
            }
            return "LE 2M";
        }
        return "LE 1M";
    }

    public int getConnectionState() {
        return this.x;
    }

    public BluetoothGatt getGatt() {
        return this.t;
    }

    public int getMtu() {
        return this.y;
    }

    public final String i(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return "UNKNOWN(" + i + ")";
                    }
                    return "DISCONNECTING";
                }
                return "CONNECTED";
            }
            return "CONNECTING";
        }
        return "DISCONNECTED";
    }

    public boolean isConnected() {
        return this.w;
    }

    public final String j(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 4) {
                    return "UNKNOWN: " + i;
                }
                return "WRITE SIGNED";
            }
            return "WRITE REQUEST";
        }
        return "WRITE COMMAND";
    }

    @Override // com.goodix.ble.libble.chain.GattCallbackDispatcher, android.bluetooth.BluetoothGattCallback
    public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        ILogger iLogger = this.r;
        if (iLogger != null) {
            byte[] value = bluetoothGattCharacteristic.getValue();
            HexStringBuilder hexStringBuilder = new HexStringBuilder(256);
            hexStringBuilder.a("CharacteristicChanged <").a(bluetoothGattCharacteristic.getUuid().toString()).a("> : ");
            c(hexStringBuilder, value);
            iLogger.i("BleGatt", hexStringBuilder.toString());
        }
        super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
        if (BleUuid.SERVICE_CHANGED_CHARACTERISTIC.equals(bluetoothGattCharacteristic.getUuid())) {
            bluetoothGatt.discoverServices();
        }
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(BleUuid.CCCD);
        boolean z = false;
        if (descriptor == null || descriptor.getValue() == null || descriptor.getValue().length != 2 || descriptor.getValue()[0] == 1) {
            z = true;
        }
        BleValue bleValue = new BleValue(bluetoothGatt, bluetoothGattCharacteristic);
        (z ? this.g : this.h).postEvent(bleValue);
        this.i.postEvent(bleValue);
    }

    @Override // com.goodix.ble.libble.chain.GattCallbackDispatcher, android.bluetooth.BluetoothGattCallback
    public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        ILogger iLogger = this.r;
        if (iLogger != null) {
            iLogger.i("BleGatt", "Read characteristic <" + bluetoothGattCharacteristic.getUuid() + ">: " + d(bluetoothGattCharacteristic.getValue()));
        }
        super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
        if (i == 0) {
            this.e.postEvent(new BleValue(bluetoothGatt, bluetoothGattCharacteristic));
        } else if (i != 5 && i != 8 && i != 137) {
            String str = "Error on reading characteristic <" + bluetoothGattCharacteristic.getUuid() + ">: " + gattStatusToString(i);
            if (iLogger != null) {
                iLogger.e("BleGatt", str);
            }
            this.b.postEvent(new GBError(i, str));
        } else {
            String str2 = "Error on reading characteristic <" + bluetoothGattCharacteristic.getUuid() + ">: " + gattStatusToString(i);
            if (iLogger != null) {
                iLogger.w("BleGatt", str2);
            }
            if (bluetoothGatt.getDevice().getBondState() != 10 && iLogger != null) {
                iLogger.w("BleGatt", "Phone has lost bonding information");
            }
            this.b.postEvent(new GBError(i, "Phone has lost bonding information"));
        }
    }

    @Override // com.goodix.ble.libble.chain.GattCallbackDispatcher, android.bluetooth.BluetoothGattCallback
    public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        ILogger iLogger = this.r;
        if (iLogger != null) {
            iLogger.i("BleGatt", "Data written to <" + bluetoothGattCharacteristic.getUuid() + ">: " + d(bluetoothGattCharacteristic.getValue()) + " status: " + i);
        }
        super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
        if (i == 0) {
            this.f.postEvent(new BleValue(bluetoothGatt, bluetoothGattCharacteristic));
        } else if (i != 5 && i != 8 && i != 137) {
            this.b.postEvent(new GBError(i, "Error on writing characteristic"));
        } else {
            if (iLogger != null) {
                iLogger.w("BleGatt", "Authentication required (" + i + ")");
            }
            if (bluetoothGatt.getDevice().getBondState() != 10) {
                if (iLogger != null) {
                    iLogger.w("BleGatt", "Phone has lost bonding information");
                }
                this.b.postEvent(new GBError(i, "Phone has lost bonding information"));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0087  */
    @Override // com.goodix.ble.libble.chain.GattCallbackDispatcher, android.bluetooth.BluetoothGattCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onConnectionStateChange(android.bluetooth.BluetoothGatt r7, int r8, int r9) {
        /*
            r6 = this;
            com.goodix.ble.libcomx.ILogger r0 = r6.r
            java.lang.String r1 = "BleGatt"
            if (r0 == 0) goto L33
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "[Callback] Connection state changed with status: "
            r2.append(r3)
            r2.append(r8)
            java.lang.String r3 = " and new state: "
            r2.append(r3)
            r2.append(r9)
            java.lang.String r3 = " ("
            r2.append(r3)
            java.lang.String r3 = r6.i(r9)
            r2.append(r3)
            java.lang.String r3 = ")"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.d(r1, r2)
        L33:
            r2 = 2
            r3 = 1
            if (r9 != r2) goto L72
            if (r8 != 0) goto L77
            android.bluetooth.BluetoothGatt r4 = r6.t
            if (r4 != 0) goto L4d
            if (r0 == 0) goto L49
            java.lang.String r8 = "Device received notification after disconnection."
            r0.e(r1, r8)
            java.lang.String r8 = "gatt.close()"
            r0.d(r1, r8)
        L49:
            r7.close()     // Catch: java.lang.Throwable -> L4c
        L4c:
            return
        L4d:
            if (r0 == 0) goto L6b
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Connected to "
            r4.append(r5)
            android.bluetooth.BluetoothDevice r5 = r7.getDevice()
            java.lang.String r5 = r5.getAddress()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r0.i(r1, r4)
        L6b:
            r4 = 23
            r6.y = r4
            r6.w = r3
            goto L75
        L72:
            r2 = 0
            r6.w = r2
        L75:
            r6.x = r2
        L77:
            super.onConnectionStateChange(r7, r8, r9)
            com.goodix.ble.libcomx.event.Event<java.lang.Integer> r9 = r6.c
            int r2 = r6.x
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r9.postEvent(r2)
            if (r8 == 0) goto Lb7
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r2 = "Connection Error: (0x"
            r9.append(r2)
            java.lang.String r2 = java.lang.Integer.toHexString(r8)
            r9.append(r2)
            java.lang.String r2 = "): "
            r9.append(r2)
            java.lang.String r2 = com.goodix.ble.libble.v2.impl.GattStatus.gattDisconnectReasonToString(r8)
            r9.append(r2)
            java.lang.String r9 = r9.toString()
            if (r0 == 0) goto Lad
            r0.e(r1, r9)
        Lad:
            com.goodix.ble.libcomx.event.Event<com.goodix.ble.libble.v2.gb.pojo.GBError> r0 = r6.b
            com.goodix.ble.libble.v2.gb.pojo.GBError r1 = new com.goodix.ble.libble.v2.gb.pojo.GBError
            r1.<init>(r8, r9)
            r0.postEvent(r1)
        Lb7:
            int r8 = r6.x
            if (r8 != 0) goto Ld0
            boolean r8 = r6.v
            if (r8 == 0) goto Ld0
            boolean r7 = r7.connect()
            if (r7 == 0) goto Ld0
            com.goodix.ble.libcomx.event.Event<java.lang.Integer> r7 = r6.c
            r6.x = r3
            java.lang.Integer r8 = java.lang.Integer.valueOf(r3)
            r7.postEvent(r8)
        Ld0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.goodix.ble.libble.v2.impl.BleGattX.onConnectionStateChange(android.bluetooth.BluetoothGatt, int, int):void");
    }

    @Override // com.goodix.ble.libble.chain.GattCallbackDispatcher
    @Keep
    @RequiresApi(api = 26)
    public final void onConnectionUpdated(@NonNull BluetoothGatt bluetoothGatt, @IntRange(from = 6, to = 3200) int i, @IntRange(from = 0, to = 499) int i2, @IntRange(from = 10, to = 3200) int i3, int i4) {
        Event<GBError> event;
        GBError gBError;
        ILogger iLogger = this.r;
        if (iLogger != null) {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Connection parameters updated (interval: ");
            sb.append(i * 1.25d);
            sb.append("ms, latency: ");
            sb.append(i2);
            sb.append(", timeout: ");
            sb.append(i3 * 10);
            sb.append("ms)");
            if (i4 == 0) {
                iLogger.i("BleGatt", sb.toString());
            } else {
                sb.append(", status: ");
                sb.append(gattStatusToString(i4));
                iLogger.w("BleGatt", sb.toString());
            }
        }
        super.onConnectionUpdated(bluetoothGatt, i, i2, i3, i4);
        if (i4 == 0) {
            this.o.postEvent(new BleCI(i, i2, i3));
            return;
        }
        if (i4 == 59) {
            if (iLogger != null) {
                iLogger.e("BleGatt", "Connection parameters update failed with status: UNACCEPT CONN INTERVAL (0x3b)");
            }
            event = this.b;
            gBError = new GBError(i4, "UNACCEPT CONN INTERVAL");
        } else {
            if (iLogger != null) {
                iLogger.e("BleGatt", "Connection parameters update failed");
            }
            event = this.b;
            gBError = new GBError(i4, "Error on connection priority request");
        }
        event.postEvent(gBError);
    }

    @Override // com.goodix.ble.libble.chain.GattCallbackDispatcher, android.bluetooth.BluetoothGattCallback
    public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        ILogger iLogger = this.r;
        if (iLogger != null) {
            iLogger.v("BleGatt", "Data read from descriptor: " + bluetoothGattDescriptor.getUuid() + HexStringBuilder.DEFAULT_SEPARATOR + d(bluetoothGattDescriptor.getValue()));
        }
        super.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
        if (i == 0) {
            this.j.postEvent(bluetoothGattDescriptor);
        } else if (i == 5 || i == 8 || i == 137) {
            String str = "Authentication required while reading descriptor: " + gattStatusToString(i);
            this.b.postEvent(new GBError(i, str));
            if (iLogger != null) {
                iLogger.w("BleGatt", str);
            }
        } else {
            String str2 = "Error on reading descriptor: " + gattStatusToString(i);
            this.b.postEvent(new GBError(i, str2));
            if (iLogger != null) {
                iLogger.e("BleGatt", str2);
            }
        }
    }

    @Override // com.goodix.ble.libble.chain.GattCallbackDispatcher, android.bluetooth.BluetoothGattCallback
    public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        byte[] value;
        StringBuilder sb;
        String str;
        ILogger iLogger = this.r;
        if (iLogger != null) {
            iLogger.v("BleGatt", "Data written to descriptor: " + bluetoothGattDescriptor.getUuid() + HexStringBuilder.DEFAULT_SEPARATOR + d(bluetoothGattDescriptor.getValue()));
        }
        super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
        if (i != 0) {
            if (i == 5 || i == 8 || i == 137) {
                String str2 = "Authentication required while writing descriptor: " + gattStatusToString(i);
                this.b.postEvent(new GBError(i, str2));
                if (iLogger != null) {
                    iLogger.w("BleGatt", str2);
                    return;
                }
                return;
            }
            String str3 = "Error on writing descriptor: " + gattStatusToString(i);
            this.b.postEvent(new GBError(i, str3));
            if (iLogger != null) {
                iLogger.e("BleGatt", str3);
                return;
            }
            return;
        }
        this.k.postEvent(bluetoothGattDescriptor);
        if (iLogger != null && BleUuid.CCCD.equals(bluetoothGattDescriptor.getUuid()) && (value = bluetoothGattDescriptor.getValue()) != null && value.length == 2 && value[1] == 0) {
            byte b2 = value[0];
            if (b2 == 0) {
                sb = new StringBuilder();
                str = "Notification disabled: ";
            } else if (b2 == 1) {
                sb = new StringBuilder();
                str = "Notification enabled: ";
            } else if (b2 != 2) {
                return;
            } else {
                sb = new StringBuilder();
                str = "Indication enabled: ";
            }
            sb.append(str);
            sb.append(bluetoothGattDescriptor.getCharacteristic().getUuid());
            iLogger.i("BleGatt", sb.toString());
        }
    }

    @Override // com.goodix.ble.libble.chain.GattCallbackDispatcher, android.bluetooth.BluetoothGattCallback
    @RequiresApi(api = 21)
    public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
        ILogger iLogger = this.r;
        if (iLogger != null) {
            if (i2 == 0) {
                iLogger.v("BleGatt", "MTU changed to: " + i);
            } else {
                iLogger.w("BleGatt", "MTU changed to: " + i + " status: " + gattStatusToString(i2));
            }
        }
        super.onMtuChanged(bluetoothGatt, i, i2);
        if (i2 != 0) {
            this.b.postEvent(new GBError(i2, "Error on mtu request"));
        }
        if (this.y != i) {
            this.l.postEvent(this, 3002, Integer.valueOf(i));
            this.y = i;
        }
    }

    @Override // com.goodix.ble.libble.chain.GattCallbackDispatcher, android.bluetooth.BluetoothGattCallback
    @RequiresApi(api = 26)
    public final void onPhyRead(@NonNull BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
        ILogger iLogger = this.r;
        if (iLogger != null) {
            StringBuilder sb = new StringBuilder(128);
            sb.append("PHY read (TX: ");
            sb.append(g(i));
            sb.append(", RX: ");
            sb.append(g(i2));
            sb.append(")");
            if (i3 == 0) {
                iLogger.v("BleGatt", sb.toString());
            } else {
                sb.append("), status: ");
                sb.append(gattStatusToString(i3));
                iLogger.w("BleGatt", sb.toString());
            }
        }
        super.onPhyRead(bluetoothGatt, i, i2, i3);
        if (i3 == 0) {
            this.n.postEvent(new BlePhy(i, i2));
            return;
        }
        if (iLogger != null) {
            iLogger.w("BleGatt", "PHY read failed with status " + i3);
        }
        this.b.postEvent(new GBError(i3, "Error on PHY read"));
    }

    @Override // com.goodix.ble.libble.chain.GattCallbackDispatcher, android.bluetooth.BluetoothGattCallback
    @RequiresApi(api = 26)
    public final void onPhyUpdate(@NonNull BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
        ILogger iLogger = this.r;
        if (iLogger != null) {
            StringBuilder sb = new StringBuilder(128);
            sb.append("PHY updated (TX: ");
            sb.append(g(i));
            sb.append(", RX: ");
            sb.append(g(i2));
            sb.append(")");
            if (i3 == 0) {
                iLogger.v("BleGatt", sb.toString());
            } else {
                sb.append("), status: ");
                sb.append(gattStatusToString(i3));
                iLogger.w("BleGatt", sb.toString());
            }
        }
        super.onPhyUpdate(bluetoothGatt, i, i2, i3);
        if (i3 == 0) {
            this.n.postEvent(new BlePhy(i, i2));
            return;
        }
        if (iLogger != null) {
            iLogger.e("BleGatt", "PHY update failed with status " + i3);
        }
        this.b.postEvent(new GBError(i3, "Error on PHY update"));
    }

    @Override // com.goodix.ble.libble.chain.GattCallbackDispatcher, android.bluetooth.BluetoothGattCallback
    public void onReadRemoteRssi(@NonNull BluetoothGatt bluetoothGatt, int i, int i2) {
        super.onReadRemoteRssi(bluetoothGatt, i, i2);
        ILogger iLogger = this.r;
        if (i2 != 0) {
            if (iLogger != null) {
                iLogger.w("BleGatt", "Reading remote RSSI failed with status " + i2);
            }
            this.b.postEvent(new GBError(i2, "Error on RSSI read"));
            return;
        }
        if (iLogger != null) {
            iLogger.i("BleGatt", "Remote RSSI received: " + i + " dBm");
        }
        this.m.postEvent(this, 3001, Integer.valueOf(i));
    }

    @Override // com.goodix.ble.libble.chain.GattCallbackDispatcher, android.bluetooth.BluetoothGattCallback
    public void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int i) {
        super.onReliableWriteCompleted(bluetoothGatt, i);
    }

    @Override // com.goodix.ble.libble.chain.GattCallbackDispatcher, android.bluetooth.BluetoothGattCallback
    public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
        String str = "Services Discovered: " + gattStatusToString(i);
        ILogger iLogger = this.r;
        if (iLogger != null) {
            if (i == 0) {
                iLogger.i("BleGatt", str);
            } else {
                iLogger.e("BleGatt", str);
                this.b.postEvent(new GBError(i, str));
            }
        }
        super.onServicesDiscovered(bluetoothGatt, i);
        this.d.postEvent(Integer.valueOf(i));
    }

    public void readRemoteRssi() {
        BluetoothGatt bluetoothGatt = this.t;
        if (bluetoothGatt != null) {
            bluetoothGatt.readRemoteRssi();
        }
    }

    public void setDevice(BluetoothDevice bluetoothDevice) {
        this.u = bluetoothDevice;
    }

    public void setLogger(@Nullable ILogger iLogger) {
        this.r = iLogger;
    }

    public void setupReceiver() {
        if (this.z) {
            return;
        }
        Logger.d(this.r, "BleGatt", "setupReceiver()");
        this.s.registerReceiver(this.B, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        this.s.registerReceiver(this.C, new IntentFilter("android.bluetooth.device.action.PAIRING_REQUEST"));
        this.s.registerReceiver(this.A, new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED"));
        this.z = true;
    }

    public void tryCloseGatt() {
        ILogger iLogger = this.r;
        if (iLogger != null) {
            iLogger.v("BleGatt", "Close gatt and dispose resource.");
        }
        cleanReceiver();
        synchronized (this) {
            BluetoothGatt bluetoothGatt = this.t;
            if (bluetoothGatt != null) {
                bluetoothGatt.close();
                this.t = null;
                this.v = false;
                if (this.x != 0) {
                    this.w = false;
                    this.x = 0;
                    this.c.postEvent(0);
                }
            }
        }
    }

    @AnyThread
    public boolean tryConnect(int i, boolean z) {
        BluetoothGatt connectGatt;
        ILogger iLogger = this.r;
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            if (iLogger != null) {
                iLogger.e("BleGatt", "tryConnect() failed, for bluetooth adapter is not available.");
            }
            return false;
        }
        BluetoothDevice bluetoothDevice = this.u;
        if (this.w) {
            if (iLogger != null) {
                iLogger.d("BleGatt", "tryConnect() skipped, and there is an exist connection.");
            }
            return true;
        }
        synchronized (this) {
            BluetoothGatt bluetoothGatt = this.t;
            if (bluetoothGatt != null) {
                try {
                    Logger.d(this.r, "BleGatt", "gatt.close()");
                    bluetoothGatt.close();
                } catch (Throwable unused) {
                }
                this.t = null;
                this.v = false;
                try {
                    Logger.d(this.r, "BleGatt", "wait(200)");
                    Thread.sleep(200L);
                } catch (InterruptedException unused2) {
                }
            }
            setupReceiver();
        }
        this.x = 1;
        this.c.postEvent(1);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            if (iLogger != null) {
                iLogger.d("BleGatt", "connectGatt(autoConnect = " + z + ", TRANSPORT_LE, " + b(i) + ")");
            }
            connectGatt = bluetoothDevice.connectGatt(this.s, z, this, 2, i);
        } else if (i2 >= 23) {
            if (iLogger != null) {
                iLogger.d("BleGatt", "connectGatt(autoConnect = " + z + ", TRANSPORT_LE)");
            }
            connectGatt = bluetoothDevice.connectGatt(this.s, z, this, 2);
        } else {
            if (iLogger != null) {
                iLogger.d("BleGatt", "connectGatt(autoConnect = " + z + ")");
            }
            connectGatt = bluetoothDevice.connectGatt(this.s, z, this);
        }
        synchronized (this) {
            this.t = connectGatt;
            this.v = z;
            if (connectGatt == null) {
                this.v = false;
                cleanReceiver();
            }
        }
        return connectGatt != null;
    }

    public boolean tryCreateBond() {
        BluetoothDevice bluetoothDevice = this.u;
        if (bluetoothDevice == null) {
            return false;
        }
        ILogger iLogger = this.r;
        if (iLogger != null) {
            iLogger.v("BleGatt", "Starting pairing...");
        }
        if (bluetoothDevice.getBondState() == 12) {
            if (iLogger != null) {
                iLogger.w("BleGatt", "Device is already bonded");
            }
            return false;
        } else if (Build.VERSION.SDK_INT >= 19) {
            if (iLogger != null) {
                iLogger.d("BleGatt", "device.createBond()");
            }
            return bluetoothDevice.createBond();
        } else {
            try {
                Method method = bluetoothDevice.getClass().getMethod("createBond", new Class[0]);
                if (iLogger != null) {
                    iLogger.d("BleGatt", "device.createBond() (hidden)");
                }
                Boolean bool = (Boolean) method.invoke(bluetoothDevice, new Object[0]);
                if (bool != null) {
                    return bool.booleanValue();
                }
                return false;
            } catch (Exception e) {
                if (iLogger != null) {
                    iLogger.w("BleGatt", "An exception occurred while creating bond: " + e);
                }
                return false;
            }
        }
    }

    public boolean tryDisconnect() {
        ILogger iLogger = this.r;
        boolean z = this.w;
        if (this.t != null) {
            if (iLogger != null) {
                iLogger.v("BleGatt", z ? "Disconnecting..." : "Cancelling connection...");
            }
            this.x = 3;
            this.c.postEvent(3);
            this.w = false;
            this.t.disconnect();
            if (z) {
                return true;
            }
            if (iLogger != null) {
                iLogger.v("BleGatt", "Cancel");
            }
            this.x = 0;
            this.c.postEvent(0);
            return true;
        }
        return false;
    }

    public boolean tryEnableNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, boolean z2) {
        StringBuilder sb;
        String str;
        BluetoothGatt bluetoothGatt = this.t;
        if (bluetoothGatt != null && bluetoothGattCharacteristic != null && this.w) {
            ILogger iLogger = this.r;
            if (((z ? 32 : 16) & bluetoothGattCharacteristic.getProperties()) == 0) {
                if (iLogger != null) {
                    String str2 = z ? "INDICATE" : "NOTIFY";
                    iLogger.w("BleGatt", "Not found required property " + str2 + " in " + bluetoothGattCharacteristic.getUuid());
                }
                return false;
            }
            BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(BleUuid.CCCD);
            if (descriptor != null) {
                if (iLogger != null) {
                    iLogger.d("BleGatt", "gatt.setCharacteristicNotification(" + bluetoothGattCharacteristic.getUuid() + ", " + z2 + ")");
                }
                bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z2);
                if (!z2) {
                    descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
                    if (iLogger != null) {
                        sb = new StringBuilder();
                        str = "Disabling notification and indication of ";
                        sb.append(str);
                        sb.append(bluetoothGattCharacteristic.getUuid());
                        iLogger.v("BleGatt", sb.toString());
                    }
                    return e(descriptor);
                } else if (z) {
                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                    if (iLogger != null) {
                        sb = new StringBuilder();
                        str = "Enabling indication of ";
                        sb.append(str);
                        sb.append(bluetoothGattCharacteristic.getUuid());
                        iLogger.v("BleGatt", sb.toString());
                    }
                    return e(descriptor);
                } else {
                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                    if (iLogger != null) {
                        sb = new StringBuilder();
                        str = "Enabling notification of ";
                        sb.append(str);
                        sb.append(bluetoothGattCharacteristic.getUuid());
                        iLogger.v("BleGatt", sb.toString());
                    }
                    return e(descriptor);
                }
            } else if (iLogger != null) {
                iLogger.w("BleGatt", "Can not get CCCD of " + bluetoothGattCharacteristic.getUuid());
            }
        }
        return false;
    }

    public boolean tryReadCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BluetoothGatt bluetoothGatt = this.t;
        if (bluetoothGatt == null || bluetoothGattCharacteristic == null || !this.w) {
            return false;
        }
        ILogger iLogger = this.r;
        if (iLogger != null) {
            iLogger.v("BleGatt", "Reading characteristic " + bluetoothGattCharacteristic.getUuid());
        }
        if ((bluetoothGattCharacteristic.getProperties() & 2) == 0) {
            if (iLogger != null) {
                iLogger.e("BleGatt", "No PROPERTY_READ in characteristic: " + bluetoothGattCharacteristic.getUuid());
            }
            return false;
        }
        return bluetoothGatt.readCharacteristic(bluetoothGattCharacteristic);
    }

    public boolean tryReadDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor) {
        BluetoothGatt bluetoothGatt = this.t;
        if (bluetoothGatt == null || bluetoothGattDescriptor == null || !this.w) {
            return false;
        }
        ILogger iLogger = this.r;
        if (iLogger != null) {
            iLogger.v("BleGatt", "Reading descriptor " + bluetoothGattDescriptor.getUuid());
        }
        return bluetoothGatt.readDescriptor(bluetoothGattDescriptor);
    }

    public boolean tryRefreshDeviceCache() {
        BluetoothGatt bluetoothGatt = this.t;
        if (bluetoothGatt == null) {
            return false;
        }
        ILogger iLogger = this.r;
        try {
            Boolean bool = (Boolean) bluetoothGatt.getClass().getMethod("refresh", new Class[0]).invoke(bluetoothGatt, new Object[0]);
            boolean z = bool != null && bool.booleanValue();
            if (iLogger != null) {
                iLogger.v("BleGatt", "Refresh device cache: " + z);
            }
            return z;
        } catch (Exception e) {
            Log.w("BleGatt", "Exception on refreshing device", e);
            if (iLogger != null) {
                iLogger.w("BleGatt", "Exception on refreshing device: " + e);
            }
            return false;
        }
    }

    public boolean tryRemoveBond() {
        BluetoothDevice bluetoothDevice = this.u;
        if (bluetoothDevice == null) {
            return false;
        }
        ILogger iLogger = this.r;
        if (iLogger != null) {
            iLogger.v("BleGatt", "Removing bond information...");
        }
        if (bluetoothDevice.getBondState() == 10) {
            if (iLogger != null) {
                iLogger.w("BleGatt", "Device is not bonded");
            }
            return false;
        }
        try {
            Method method = bluetoothDevice.getClass().getMethod("removeBond", new Class[0]);
            if (iLogger != null) {
                iLogger.d("BleGatt", "device.removeBond() (hidden)");
            }
            return ((Boolean) method.invoke(bluetoothDevice, new Object[0])).booleanValue();
        } catch (Exception e) {
            if (iLogger != null) {
                iLogger.w("BleGatt", "An exception occurred while removing bond: " + e);
            }
            return false;
        }
    }

    public boolean tryWriteCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        ILogger iLogger = this.r;
        BluetoothGatt bluetoothGatt = this.t;
        if (bluetoothGatt == null || bluetoothGattCharacteristic == null || !this.w) {
            return false;
        }
        if ((bluetoothGattCharacteristic.getProperties() & 12) == 0) {
            if (iLogger != null) {
                iLogger.w("BleGatt", "Writing characteristic " + bluetoothGattCharacteristic.getUuid() + " (no required properties)");
            }
            return false;
        }
        boolean writeCharacteristic = bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
        if (iLogger != null) {
            iLogger.v("BleGatt", "Writing characteristic " + bluetoothGattCharacteristic.getUuid() + " (" + j(bluetoothGattCharacteristic.getWriteType()) + ", ret = " + writeCharacteristic + "): " + d(bluetoothGattCharacteristic.getValue()));
        }
        return writeCharacteristic;
    }

    public boolean tryWriteDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor) {
        if (this.t == null || bluetoothGattDescriptor == null || !this.w) {
            return false;
        }
        ILogger iLogger = this.r;
        if (iLogger != null) {
            iLogger.v("BleGatt", "Writing descriptor " + bluetoothGattDescriptor.getUuid());
        }
        return e(bluetoothGattDescriptor);
    }
}
