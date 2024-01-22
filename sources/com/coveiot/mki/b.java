package com.coveiot.mki;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.clevertap.android.sdk.Constants;
import com.coveiot.mki.b;
import com.coveiot.sdk.ble.api.BleUUID;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
/* loaded from: classes9.dex */
public class b extends Service {
    public static final String ACTION_NOTIFICATION_CLOSE = "com.coveiot.mki.action.notification.close";
    public static final String ACTION_NOTIFICATION_INIT = "com.coveiot.mki.action.notification.init";
    public static final String CHANNEL_ID = "OTAService";
    public BluetoothAdapter j;
    public BluetoothGatt k;
    public BroadcastReceiver n;
    public String o;
    public String p;
    public BluetoothGattCharacteristic s;
    public BluetoothGattCharacteristic t;
    public Handler u;
    public Handler v;
    public HandlerThread w;
    public Handler x;
    public final com.coveiot.mki.c h = new com.coveiot.mki.c(this);
    public final List<com.coveiot.mki.a> i = new ArrayList();
    public final List<e> l = new ArrayList();
    public boolean m = false;
    public j q = j.f7282a;
    public i r = i.f7281a;
    public final h y = new h() { // from class: com.coveiot.mki.u
        @Override // com.coveiot.mki.h
        public final void a(e eVar) {
            b.this.x(eVar);
        }
    };
    public boolean z = false;
    public boolean A = false;
    public boolean B = false;
    public boolean C = false;
    public int D = 20;
    public final ScanCallback E = new a();
    public final BluetoothGattCallback F = new c();
    public final Runnable G = new Runnable() { // from class: com.coveiot.mki.c0
        @Override // java.lang.Runnable
        public final void run() {
            b.this.s();
        }
    };

    /* loaded from: classes9.dex */
    public class a extends ScanCallback {
        public a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b(ScanResult scanResult) {
            b.this.u.removeCallbacksAndMessages(null);
            b.this.X();
            b.this.connect(scanResult.getDevice());
        }

        @Override // android.bluetooth.le.ScanCallback
        public final void onScanFailed(int i) {
            super.onScanFailed(i);
            String str = b.ACTION_NOTIFICATION_INIT;
            p.a("Scan failed, error: %d", Integer.valueOf(i));
            b.this.C(true, "Scan failed");
        }

        @Override // android.bluetooth.le.ScanCallback
        public final void onScanResult(int i, final ScanResult scanResult) {
            if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(b.this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                String str = b.ACTION_NOTIFICATION_INIT;
                p.a("BLUETOOTH_CONNECT permission denied", new Object[0]);
                return;
            }
            BluetoothDevice device = scanResult.getDevice();
            if (!TextUtils.isEmpty(device.getName()) && device.getName().equals(b.this.o) && device.getAddress().equals(b.this.p)) {
                b.this.u.post(new Runnable() { // from class: com.coveiot.mki.i0
                    @Override // java.lang.Runnable
                    public final void run() {
                        b.a.this.b(scanResult);
                    }
                });
            }
        }
    }

    /* renamed from: com.coveiot.mki.b$b  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public class C0332b extends BroadcastReceiver {
        public C0332b() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction()) && intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1) == 10) {
                String str = b.ACTION_NOTIFICATION_INIT;
                p.a("Bluetooth turned off", new Object[0]);
                b.this.C(true, "Bluetooth turned off");
            }
        }
    }

    /* loaded from: classes9.dex */
    public class c extends BluetoothGattCallback {
        public c() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void k() {
            String str = b.ACTION_NOTIFICATION_INIT;
            p.b("Starting service discovery", new Object[0]);
            b.this.v.postDelayed(b.this.G, 10000L);
            if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(b.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                b.this.k.discoverServices();
            } else {
                p.a("BLUETOOTH_CONNECT permission denied", new Object[0]);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void l(int i, int i2) {
            if (i != 0) {
                String str = b.ACTION_NOTIFICATION_INIT;
                p.a("GATT error on connection.  Error id : %d", Integer.valueOf(i));
                b.this.C(true, "Connecting to device failed");
            } else if (i2 == 0) {
                String str2 = b.ACTION_NOTIFICATION_INIT;
                p.b("State changed to \"DISCONNECTED\"", new Object[0]);
            } else if (i2 == 1) {
                String str3 = b.ACTION_NOTIFICATION_INIT;
                p.b("State changed to \"CONNECTING\"", new Object[0]);
            } else if (i2 == 2) {
                String str4 = b.ACTION_NOTIFICATION_INIT;
                p.b("State changed to \"CONNECTED\"", new Object[0]);
                b.this.v.postDelayed(new Runnable() { // from class: com.coveiot.mki.j0
                    @Override // java.lang.Runnable
                    public final void run() {
                        b.c.this.k();
                    }
                }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
            } else if (i2 == 3) {
                String str5 = b.ACTION_NOTIFICATION_INIT;
                p.b("State changed to \"DISCONNECTING\"", new Object[0]);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void n(int i, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            e eVar;
            Iterator it = ((ArrayList) b.this.l).iterator();
            while (true) {
                if (!it.hasNext()) {
                    eVar = null;
                    break;
                }
                eVar = (e) it.next();
                if (!eVar.n()) {
                    break;
                }
            }
            if (eVar == null) {
                return;
            }
            boolean z = true;
            if (i == 0) {
                eVar.c(0);
                int l = eVar.l();
                eVar.d((eVar.g().size() <= 1 || eVar.h() != 1) ? (eVar.l() + bluetoothGattCharacteristic.getValue().length) - 4 : (eVar.l() + bluetoothGattCharacteristic.getValue().length) - 12);
                String str = b.ACTION_NOTIFICATION_INIT;
                p.b("Write characteristics for command %s successful [package = %d/%d, size: %d]", eVar.d().toString(), Integer.valueOf(eVar.h()), Integer.valueOf(eVar.g().size()), Integer.valueOf(bluetoothGattCharacteristic.getValue().length));
                if (eVar.h() >= eVar.g().size()) {
                    if (eVar.d().b() == 2) {
                        b.this.y(eVar, true, new Object[0]);
                        return;
                    }
                    return;
                }
                if (eVar.d().b() == 3 && (b.this.q == j.c || b.this.q == j.e ? l / 2048 != eVar.l() / 2048 : eVar.h() % 50 == 1)) {
                    z = false;
                }
                if (!z) {
                    return;
                }
            } else if (eVar.k() >= 16) {
                return;
            } else {
                eVar.c(eVar.k() + 1);
                eVar.b(eVar.h() - 1);
                try {
                    Thread.sleep(40L);
                } catch (Exception unused) {
                }
            }
            b.this.M(eVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0077, code lost:
            r8 = com.coveiot.mki.b.ACTION_NOTIFICATION_INIT;
            com.coveiot.mki.p.b("JieLi model number found, converting to JieLi device", new java.lang.Object[0]);
            r7.f7276a.q = com.coveiot.mki.j.g;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public /* synthetic */ void p(android.bluetooth.BluetoothGattCharacteristic r8, int r9) {
            /*
                r7 = this;
                android.bluetooth.BluetoothGattService r0 = r8.getService()
                java.util.UUID r0 = r0.getUuid()
                java.lang.String r0 = r0.toString()
                java.lang.String r1 = "0000180a-0000-1000-8000-00805f9b34fb"
                java.util.UUID r1 = java.util.UUID.fromString(r1)
                java.lang.String r1 = r1.toString()
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto La1
                java.util.UUID r0 = r8.getUuid()
                java.lang.String r0 = r0.toString()
                java.lang.String r1 = "00002a24-0000-1000-8000-00805f9b34fb"
                java.util.UUID r1 = java.util.UUID.fromString(r1)
                java.lang.String r1 = r1.toString()
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto La1
                com.coveiot.mki.b r0 = com.coveiot.mki.b.this
                android.os.Handler r0 = com.coveiot.mki.b.a0(r0)
                com.coveiot.mki.b r1 = com.coveiot.mki.b.this
                java.lang.Runnable r1 = com.coveiot.mki.b.Z(r1)
                r0.removeCallbacks(r1)
                r0 = 0
                if (r9 != 0) goto L91
                java.lang.String r9 = new java.lang.String     // Catch: java.lang.Exception -> L8b
                byte[] r8 = r8.getValue()     // Catch: java.lang.Exception -> L8b
                java.nio.charset.Charset r1 = java.nio.charset.StandardCharsets.US_ASCII     // Catch: java.lang.Exception -> L8b
                r9.<init>(r8, r1)     // Catch: java.lang.Exception -> L8b
                java.lang.String r8 = r9.trim()     // Catch: java.lang.Exception -> L8b
                java.util.Locale r9 = java.util.Locale.ENGLISH     // Catch: java.lang.Exception -> L8b
                java.lang.String r8 = r8.toUpperCase(r9)     // Catch: java.lang.Exception -> L8b
                r9 = 6
                java.lang.String r1 = "WA34V4"
                java.lang.String r2 = "WA34V5"
                java.lang.String r3 = "WA34V6"
                java.lang.String r4 = "WA35V2"
                java.lang.String r5 = "WA35V3"
                java.lang.String r6 = "WA35V4"
                java.lang.String[] r1 = new java.lang.String[]{r1, r2, r3, r4, r5, r6}     // Catch: java.lang.Exception -> L8b
                r2 = r0
            L6d:
                if (r2 >= r9) goto L8b
                r3 = r1[r2]     // Catch: java.lang.Exception -> L8b
                boolean r3 = r8.equals(r3)     // Catch: java.lang.Exception -> L8b
                if (r3 == 0) goto L88
                java.lang.String r8 = com.coveiot.mki.b.ACTION_NOTIFICATION_INIT     // Catch: java.lang.Exception -> L8b
                java.lang.String r8 = "JieLi model number found, converting to JieLi device"
                java.lang.Object[] r9 = new java.lang.Object[r0]     // Catch: java.lang.Exception -> L8b
                com.coveiot.mki.p.b(r8, r9)     // Catch: java.lang.Exception -> L8b
                com.coveiot.mki.b r8 = com.coveiot.mki.b.this     // Catch: java.lang.Exception -> L8b
                com.coveiot.mki.j r9 = com.coveiot.mki.j.g     // Catch: java.lang.Exception -> L8b
                com.coveiot.mki.b.q(r8, r9)     // Catch: java.lang.Exception -> L8b
                goto L8b
            L88:
                int r2 = r2 + 1
                goto L6d
            L8b:
                com.coveiot.mki.b r8 = com.coveiot.mki.b.this
                com.coveiot.mki.b.Q(r8)
                goto La1
            L91:
                java.lang.String r8 = com.coveiot.mki.b.ACTION_NOTIFICATION_INIT
                java.lang.Object[] r8 = new java.lang.Object[r0]
                java.lang.String r9 = "Read from characteristics returned failure"
                com.coveiot.mki.p.a(r9, r8)
                com.coveiot.mki.b r8 = com.coveiot.mki.b.this
                java.lang.String r9 = "Connecting to device failed"
                com.coveiot.mki.b.v(r8, r9)
            La1:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.mki.b.c.p(android.bluetooth.BluetoothGattCharacteristic, int):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void q(BluetoothGattCharacteristic bluetoothGattCharacteristic, final byte[] bArr, final BluetoothGatt bluetoothGatt) {
            if (bluetoothGattCharacteristic.getService().getUuid().toString().equals(UUID.fromString(BleUUID.UART_SERVICE_UUID).toString()) && bluetoothGattCharacteristic.getUuid().toString().equals(UUID.fromString(BleUUID.UART_READ_CHARATERISTICS_UUID).toString())) {
                b.this.x.post(new Runnable() { // from class: com.coveiot.mki.s0
                    @Override // java.lang.Runnable
                    public final void run() {
                        b.c.this.s(bArr, bluetoothGatt);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void r(BluetoothGattDescriptor bluetoothGattDescriptor, int i, BluetoothGatt bluetoothGatt) {
            if (bluetoothGattDescriptor.getCharacteristic().getUuid().toString().equals(UUID.fromString(BleUUID.UART_READ_CHARATERISTICS_UUID).toString()) && bluetoothGattDescriptor.getUuid().toString().equals(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb").toString())) {
                if (i == 0) {
                    o(bluetoothGatt);
                    return;
                }
                String str = b.ACTION_NOTIFICATION_INIT;
                p.a("Writing to descriptor returned failure", new Object[0]);
                b.this.C(true, "Connecting to device failed");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void s(byte[] bArr, BluetoothGatt bluetoothGatt) {
            e eVar;
            d dVar;
            d dVar2;
            if (bArr.length < 4) {
                eVar = null;
            } else if (bArr[0] == Byte.MAX_VALUE) {
                short s = ByteBuffer.wrap(new byte[]{0, bArr[1]}).getShort();
                int i = ByteBuffer.wrap(new byte[]{0, 0, bArr[3], bArr[2]}).getInt();
                if (i != 0) {
                    Iterator it = ((ArrayList) b.this.l).iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            eVar = null;
                            break;
                        }
                        e eVar2 = (e) it.next();
                        if (eVar2.j() == s) {
                            eVar = eVar2;
                            break;
                        }
                    }
                    if (eVar == null) {
                        String str = b.ACTION_NOTIFICATION_INIT;
                        p.a("Package ID match failed. Corrupt data", new Object[0]);
                        return;
                    } else if (i != eVar.e() + 1) {
                        String str2 = b.ACTION_NOTIFICATION_INIT;
                        p.a("Package index wrong. Corrupt data", new Object[0]);
                        b.this.y(eVar, false, "Incomplete or corrupt data");
                        return;
                    } else {
                        eVar.a(i);
                    }
                } else if (bArr.length < 12) {
                    String str3 = b.ACTION_NOTIFICATION_INIT;
                    p.a("Incomplete or corrupt data", new Object[0]);
                    return;
                } else {
                    byte b = bArr[8];
                    byte b2 = bArr[9];
                    d[] values = d.values();
                    int length = values.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            dVar2 = null;
                            break;
                        }
                        dVar2 = values[i2];
                        if (dVar2.a(true) == b && dVar2.a() == b2) {
                            break;
                        }
                        i2++;
                    }
                    if (dVar2 == null) {
                        String str4 = b.ACTION_NOTIFICATION_INIT;
                        p.a("Unknown command", new Object[0]);
                        return;
                    }
                    Iterator it2 = ((ArrayList) b.this.l).iterator();
                    e eVar3 = null;
                    while (it2.hasNext()) {
                        e eVar4 = (e) it2.next();
                        if (eVar4.d() == dVar2) {
                            if (dVar2.c()) {
                                if (bArr.length < 13) {
                                    String str5 = b.ACTION_NOTIFICATION_INIT;
                                    p.a("Hybrid command having invalid length", new Object[0]);
                                    return;
                                }
                                short s2 = ByteBuffer.wrap(new byte[]{0, bArr[12]}).getShort();
                                int i3 = s2 & 3;
                                if (eVar4.f() == i3) {
                                    if ((s2 & 128) == 0) {
                                        String str6 = b.ACTION_NOTIFICATION_INIT;
                                        p.a("Hybrid command having invalid direction", new Object[0]);
                                        return;
                                    } else if ((s2 & 64) != 0) {
                                        String str7 = b.ACTION_NOTIFICATION_INIT;
                                        p.a("Hybrid command with need response flag set true not implemented", new Object[0]);
                                        return;
                                    } else if (eVar4.n() && i3 != 2) {
                                    }
                                }
                            }
                            eVar3 = eVar4;
                        }
                    }
                    if (eVar3 == null) {
                        String str8 = b.ACTION_NOTIFICATION_INIT;
                        p.a("Command not in queue", new Object[0]);
                        return;
                    }
                    eVar3.a();
                    eVar3.b(s);
                    eVar3.a(i);
                    eVar3.e(ByteBuffer.wrap(new byte[]{0, 0, bArr[5], bArr[4]}).getInt());
                    eVar = eVar3;
                }
                List<Byte> i4 = eVar.i();
                for (int i5 = 4; i5 < bArr.length; i5++) {
                    i4.add(Byte.valueOf(bArr[i5]));
                }
                eVar.p();
                if (i != eVar.m() - 1) {
                    return;
                }
                if (i4.size() < 8) {
                    String str9 = b.ACTION_NOTIFICATION_INIT;
                    p.a("Invalid multi-packet size", new Object[0]);
                    b.this.y(eVar, false, "Incomplete or corrupt data");
                    return;
                }
                eVar.b(i4.subList(8, i4.size()));
                String str10 = b.ACTION_NOTIFICATION_INIT;
                p.b("Multi-packet data download complete, size: %d", Integer.valueOf(i4.size() - 8));
            } else {
                byte b3 = bArr[0];
                byte b4 = bArr[1];
                d dVar3 = d.JIELI_SEND_OTA;
                if (b3 == dVar3.a(true) && b4 == dVar3.a() && bArr.length != 5) {
                    return;
                }
                d[] values2 = d.values();
                int length2 = values2.length;
                int i6 = 0;
                while (true) {
                    if (i6 >= length2) {
                        dVar = null;
                        break;
                    }
                    dVar = values2[i6];
                    if (dVar.a(true) == b3 && dVar.a() == b4) {
                        break;
                    }
                    i6++;
                }
                Iterator it3 = ((ArrayList) b.this.l).iterator();
                eVar = null;
                while (it3.hasNext()) {
                    e eVar5 = (e) it3.next();
                    if (eVar5.d() == dVar) {
                        if (dVar.c()) {
                            if (bArr.length < 5) {
                                String str11 = b.ACTION_NOTIFICATION_INIT;
                                p.a("Hybrid command having invalid length", new Object[0]);
                                return;
                            }
                            short s3 = ByteBuffer.wrap(new byte[]{0, bArr[4]}).getShort();
                            int i7 = s3 & 3;
                            if (eVar5.f() == i7) {
                                if ((s3 & 128) == 0) {
                                    String str12 = b.ACTION_NOTIFICATION_INIT;
                                    p.a("Hybrid command having invalid direction", new Object[0]);
                                    return;
                                } else if ((s3 & 64) != 0) {
                                    String str13 = b.ACTION_NOTIFICATION_INIT;
                                    p.a("Hybrid command with need response flag set true not implemented", new Object[0]);
                                    return;
                                } else if (!eVar5.n() || i7 == 2) {
                                }
                            }
                        }
                        eVar = eVar5;
                    }
                }
                if (eVar == null) {
                    return;
                }
                int i8 = ByteBuffer.wrap(new byte[]{0, 0, bArr[3], bArr[2]}).getInt();
                if (i8 != bArr.length) {
                    String str14 = b.ACTION_NOTIFICATION_INIT;
                    p.a("Payload length %d in header not matching the received payload length %d. Corrupt data", Integer.valueOf(i8), Integer.valueOf(bArr.length));
                    b.this.y(eVar, false, "Incomplete or corrupt data");
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (int i9 = 4; i9 < bArr.length; i9++) {
                    arrayList.add(Byte.valueOf(bArr[i9]));
                }
                eVar.b(arrayList);
            }
            if (eVar == null) {
                return;
            }
            eVar.p();
            d d = eVar.d();
            if (eVar.d().b() == 3) {
                List<Byte> i10 = eVar.i();
                if (i10.size() < 3) {
                    String str15 = b.ACTION_NOTIFICATION_INIT;
                    p.a("Sending multi-packet data failed, error: Invalid package size", new Object[0]);
                    b.this.y(eVar, false, "Sending multi-packet data failed, error: Invalid package size");
                    return;
                } else if (i10.get(2).byteValue() != 1) {
                    String str16 = b.ACTION_NOTIFICATION_INIT;
                    p.a("Sending multi-packet data failed, error: Failure status received", new Object[0]);
                    b.this.y(eVar, false, "Sending multi-packet data failed, error: Failure status received");
                    return;
                } else if (ByteBuffer.wrap(new byte[]{0, 0, i10.get(1).byteValue(), i10.get(0).byteValue()}).getInt() != eVar.h() - 1) {
                    String str17 = b.ACTION_NOTIFICATION_INIT;
                    p.a("Sending multi-packet data failed, error: Invalid ack package", new Object[0]);
                    b.this.y(eVar, false, "Sending multi-packet data failed, error: Invalid ack package");
                    return;
                } else if (eVar.h() < eVar.g().size()) {
                    if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(b.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                        bluetoothGatt.requestConnectionPriority(1);
                    }
                    try {
                        Thread.sleep(4L);
                    } catch (Exception unused) {
                    }
                    b.this.M(eVar);
                    return;
                }
            }
            try {
                b.this.y(eVar, true, d.a(eVar.i()));
            } catch (Exception e) {
                e.printStackTrace();
                String str18 = b.ACTION_NOTIFICATION_INIT;
                p.a("Command decode failed for command %s, error: %s", d, e.getMessage());
                b.this.y(eVar, false, "Command decode failed");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void t(int i, BluetoothGatt bluetoothGatt) {
            b.this.v.removeCallbacks(b.this.G);
            if (i == 0) {
                if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(b.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                    b bVar = b.this;
                    j jVar = j.f7282a;
                    bVar.q = jVar;
                    if (bluetoothGatt.getService(UUID.fromString("0000FE59-0000-1000-8000-00805f9b34fb")) != null) {
                        String str = b.ACTION_NOTIFICATION_INIT;
                        p.b("Nordic secure DFU service found", new Object[0]);
                        b.this.q = j.b;
                    }
                    if (bluetoothGatt.getService(UUID.fromString("0000d0ff-3c17-d293-8e48-14fe2e4da212")) != null) {
                        String str2 = b.ACTION_NOTIFICATION_INIT;
                        p.b("Realtek OTA service found", new Object[0]);
                        b.this.q = j.c;
                    }
                    if (bluetoothGatt.getService(UUID.fromString("00000000-0000-0000-6473-5f696c666973")) != null) {
                        String str3 = b.ACTION_NOTIFICATION_INIT;
                        p.b("Sifli OTA service found", new Object[0]);
                        b.this.q = j.e;
                    }
                    if (bluetoothGatt.getService(UUID.fromString("66666666-6666-6666-6666-666666666666")) != null) {
                        String str4 = b.ACTION_NOTIFICATION_INIT;
                        p.b("BES OTA service found", new Object[0]);
                        b.this.q = j.f;
                    }
                    if (b.this.q == jVar) {
                        String str5 = b.ACTION_NOTIFICATION_INIT;
                        p.b("Considering device as Apollo", new Object[0]);
                        b.this.q = j.d;
                    }
                    BluetoothGattService service = bluetoothGatt.getService(UUID.fromString("0000180a-0000-1000-8000-00805f9b34fb"));
                    if (service == null) {
                        String str6 = b.ACTION_NOTIFICATION_INIT;
                        p.a("Device information Service not found", new Object[0]);
                    } else {
                        b.this.s = service.getCharacteristic(UUID.fromString("00002a24-0000-1000-8000-00805f9b34fb"));
                        if (b.this.s == null) {
                            String str7 = b.ACTION_NOTIFICATION_INIT;
                            p.a("Device Modal Number Characteristic not found", new Object[0]);
                        }
                    }
                    BluetoothGattService service2 = bluetoothGatt.getService(UUID.fromString(BleUUID.UART_SERVICE_UUID));
                    if (service2 == null) {
                        String str8 = b.ACTION_NOTIFICATION_INIT;
                        p.a("Nordic UART Service not found", new Object[0]);
                        o(bluetoothGatt);
                        return;
                    }
                    b.this.t = service2.getCharacteristic(UUID.fromString(BleUUID.UART_WRITE_CHARATERISTICS_UUID));
                    if (b.this.t == null) {
                        String str9 = b.ACTION_NOTIFICATION_INIT;
                        p.a("Nordic UART RX Characteristic not found", new Object[0]);
                    } else {
                        BluetoothGattCharacteristic characteristic = service2.getCharacteristic(UUID.fromString(BleUUID.UART_READ_CHARATERISTICS_UUID));
                        if (characteristic == null) {
                            String str10 = b.ACTION_NOTIFICATION_INIT;
                            p.a("Nordic UART TX Characteristic not found", new Object[0]);
                        } else {
                            boolean characteristicNotification = bluetoothGatt.setCharacteristicNotification(characteristic, true);
                            for (int i2 = 16; !characteristicNotification && i2 > 0; i2--) {
                                try {
                                    Thread.sleep(40L);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                characteristicNotification = bluetoothGatt.setCharacteristicNotification(characteristic, true);
                            }
                            if (characteristicNotification) {
                                BluetoothGattDescriptor descriptor = characteristic.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"));
                                if (descriptor == null) {
                                    String str11 = b.ACTION_NOTIFICATION_INIT;
                                    p.a("Nordic UART TX Descriptor not found", new Object[0]);
                                } else {
                                    String str12 = b.ACTION_NOTIFICATION_INIT;
                                    p.b("Writing to TX Descriptor...", new Object[0]);
                                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                                    boolean writeDescriptor = b.this.k.writeDescriptor(descriptor);
                                    for (int i3 = 16; !writeDescriptor && i3 > 0; i3--) {
                                        try {
                                            Thread.sleep(40L);
                                        } catch (InterruptedException e2) {
                                            e2.printStackTrace();
                                        }
                                        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                                        writeDescriptor = b.this.k.writeDescriptor(descriptor);
                                    }
                                    if (writeDescriptor) {
                                        b.this.v.postDelayed(b.this.G, 10000L);
                                        return;
                                    } else {
                                        String str13 = b.ACTION_NOTIFICATION_INIT;
                                        p.a("Write descriptor failed", new Object[0]);
                                    }
                                }
                            } else {
                                String str14 = b.ACTION_NOTIFICATION_INIT;
                                p.a("Enabling notification failed", new Object[0]);
                            }
                        }
                    }
                } else {
                    String str15 = b.ACTION_NOTIFICATION_INIT;
                    p.a("BLUETOOTH_CONNECT permission denied", new Object[0]);
                }
                b.this.C(true, "Connecting to device failed");
                return;
            }
            String str16 = b.ACTION_NOTIFICATION_INIT;
            p.a("Service discovery returned failure", new Object[0]);
            b.this.C(true, "Connecting to device failed");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void u(final BluetoothGattCharacteristic bluetoothGattCharacteristic, final int i) {
            if (bluetoothGattCharacteristic.getService().getUuid().toString().equals(UUID.fromString(BleUUID.UART_SERVICE_UUID).toString()) && bluetoothGattCharacteristic.getUuid().toString().equals(UUID.fromString(BleUUID.UART_WRITE_CHARATERISTICS_UUID).toString())) {
                b.this.x.post(new Runnable() { // from class: com.coveiot.mki.n0
                    @Override // java.lang.Runnable
                    public final void run() {
                        b.c.this.n(i, bluetoothGattCharacteristic);
                    }
                });
            }
        }

        public final void m(int i, BluetoothGatt bluetoothGatt) {
            if (b.this.C) {
                b.this.v.removeCallbacks(b.this.G);
                b.this.D = i - 3;
                String str = b.ACTION_NOTIFICATION_INIT;
                p.b("GATT init complete, mtu: %d", Integer.valueOf(i));
                if (b.this.q == j.d && b.this.s != null) {
                    p.b("Requesting model number", new Object[0]);
                    if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(b.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                        b.this.v.removeCallbacks(b.this.G);
                        boolean readCharacteristic = bluetoothGatt.readCharacteristic(b.this.s);
                        for (int i2 = 16; !readCharacteristic && i2 > 0; i2--) {
                            try {
                                Thread.sleep(40L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            readCharacteristic = bluetoothGatt.readCharacteristic(b.this.s);
                        }
                        if (readCharacteristic) {
                            b.this.v.postDelayed(b.this.G, 10000L);
                            return;
                        } else {
                            String str2 = b.ACTION_NOTIFICATION_INIT;
                            p.a("Reading model number failed", new Object[0]);
                        }
                    } else {
                        p.a("BLUETOOTH_CONNECT permission denied", new Object[0]);
                    }
                    b.this.C(true, "Connecting to device failed");
                    return;
                }
                b.Q(b.this);
            }
        }

        public final void o(BluetoothGatt bluetoothGatt) {
            if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(b.this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                String str = b.ACTION_NOTIFICATION_INIT;
                p.a("BLUETOOTH_CONNECT permission denied", new Object[0]);
                b.this.C(true, "Connecting to device failed");
                return;
            }
            b.this.v.removeCallbacks(b.this.G);
            b.this.C = true;
            boolean requestMtu = bluetoothGatt.requestMtu(185);
            for (int i = 16; !requestMtu && i > 0; i--) {
                try {
                    Thread.sleep(40L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                requestMtu = bluetoothGatt.requestMtu(185);
            }
            b bVar = b.this;
            if (requestMtu) {
                bVar.v.postDelayed(b.this.G, 10000L);
                return;
            }
            bVar.C = false;
            String str2 = b.ACTION_NOTIFICATION_INIT;
            p.a("MTU request failed", new Object[0]);
            b.this.C(true, "Connecting to device failed");
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onCharacteristicChanged(final BluetoothGatt bluetoothGatt, final BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            int length = bluetoothGattCharacteristic.getValue().length;
            final byte[] bArr = new byte[length];
            System.arraycopy(bluetoothGattCharacteristic.getValue(), 0, bArr, 0, length);
            b.this.v.post(new Runnable() { // from class: com.coveiot.mki.q0
                @Override // java.lang.Runnable
                public final void run() {
                    b.c.this.q(bluetoothGattCharacteristic, bArr, bluetoothGatt);
                }
            });
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onCharacteristicRead(BluetoothGatt bluetoothGatt, final BluetoothGattCharacteristic bluetoothGattCharacteristic, final int i) {
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            b.this.v.post(new Runnable() { // from class: com.coveiot.mki.p0
                @Override // java.lang.Runnable
                public final void run() {
                    b.c.this.p(bluetoothGattCharacteristic, i);
                }
            });
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onCharacteristicWrite(BluetoothGatt bluetoothGatt, final BluetoothGattCharacteristic bluetoothGattCharacteristic, final int i) {
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            b.this.v.post(new Runnable() { // from class: com.coveiot.mki.o0
                @Override // java.lang.Runnable
                public final void run() {
                    b.c.this.u(bluetoothGattCharacteristic, i);
                }
            });
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onConnectionStateChange(BluetoothGatt bluetoothGatt, final int i, final int i2) {
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            b.this.v.post(new Runnable() { // from class: com.coveiot.mki.k0
                @Override // java.lang.Runnable
                public final void run() {
                    b.c.this.l(i, i2);
                }
            });
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onDescriptorWrite(final BluetoothGatt bluetoothGatt, final BluetoothGattDescriptor bluetoothGattDescriptor, final int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            b.this.v.postDelayed(new Runnable() { // from class: com.coveiot.mki.r0
                @Override // java.lang.Runnable
                public final void run() {
                    b.c.this.r(bluetoothGattDescriptor, i, bluetoothGatt);
                }
            }, 40L);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onMtuChanged(final BluetoothGatt bluetoothGatt, final int i, int i2) {
            super.onMtuChanged(bluetoothGatt, i, i2);
            b.this.v.postDelayed(new Runnable() { // from class: com.coveiot.mki.l0
                @Override // java.lang.Runnable
                public final void run() {
                    b.c.this.m(i, bluetoothGatt);
                }
            }, 40L);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onPhyUpdate(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            super.onPhyUpdate(bluetoothGatt, i, i2, i3);
            String str = b.ACTION_NOTIFICATION_INIT;
            p.b("PHY updated, txPhy: %d, rxPhy: %d, status: %d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onServicesDiscovered(final BluetoothGatt bluetoothGatt, final int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            b.this.v.postDelayed(new Runnable() { // from class: com.coveiot.mki.m0
                @Override // java.lang.Runnable
                public final void run() {
                    b.c.this.t(i, bluetoothGatt);
                }
            }, 40L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.List<com.coveiot.mki.e>, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List<com.coveiot.mki.e>, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.util.List<com.coveiot.mki.e>, java.util.ArrayList] */
    public /* synthetic */ void A(f fVar, e eVar, Integer num, d dVar, l lVar) {
        if (this.t == null) {
            p.a("Missing write characteristics", new Object[0]);
            fVar.onFail("Missing write characteristics");
            return;
        }
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            if (!((e) it.next()).n()) {
                p.a("Previous command ongoing", new Object[0]);
                fVar.onFail("Previous command ongoing");
                return;
            }
        }
        this.l.add(eVar);
        eVar.a(this.x);
        List<List<Byte>> list = null;
        try {
            int i = this.D;
            if (num != null && num.intValue() > 0 && num.intValue() < this.D) {
                i = num.intValue();
            }
            list = t.a(i, dVar.a(false), dVar.a(), lVar.a());
        } catch (Exception e) {
            p.a("Failed encoding, error: %s", e.getMessage());
        }
        if (list == null || list.size() == 0) {
            fVar.onFail("Failed to generate frames");
            this.l.remove(eVar);
            return;
        }
        eVar.a(list);
        eVar.b(0);
        if (list.size() > 0 && (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0)) {
            this.k.requestConnectionPriority(1);
        }
        M(eVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void D(byte[] bArr, e eVar) {
        boolean writeCharacteristic;
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
            this.t.setValue(bArr);
            int i = 16;
            writeCharacteristic = this.k.writeCharacteristic(this.t);
            while (!writeCharacteristic && i > 0) {
                writeCharacteristic = this.k.writeCharacteristic(this.t);
                i--;
                try {
                    Thread.sleep(40L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            p.a("BLUETOOTH_CONNECT permission denied", new Object[0]);
            writeCharacteristic = false;
        }
        f c2 = eVar.c();
        if (writeCharacteristic) {
            if (c2 != null) {
                c2.onProgress(eVar.h() / eVar.g().size());
                return;
            }
            return;
        }
        p.a("Write characteristics for command %s failed", eVar.d().toString());
        C(true, "Connection to device terminated");
        if (c2 != null) {
            c2.onFail("Connection to device terminated");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void H() {
        p.b("Retrying scan after %dms", 10000);
        W();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void I(e eVar) {
        y(eVar, false, "Stopping all commands");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void J(boolean z, String str) {
        Object[] objArr = new Object[0];
        if (z) {
            p.a("Disconnecting due to failure", objArr);
        } else {
            p.b("Disconnecting", objArr);
        }
        try {
            this.k.disconnect();
            this.k.close();
            this.k = null;
            this.t = null;
        } catch (Exception unused) {
        }
        if (this.A) {
            this.x.post(new Runnable() { // from class: com.coveiot.mki.b0
                @Override // java.lang.Runnable
                public final void run() {
                    b.this.U();
                }
            });
            if (!this.z || !z) {
                this.A = false;
            }
            B(str);
        }
        if (this.z && z) {
            W();
            return;
        }
        X();
        stop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void L() {
        p.b("Stopping scan after timeout %dms", 10000);
        X();
        this.u.postDelayed(new Runnable() { // from class: com.coveiot.mki.a0
            @Override // java.lang.Runnable
            public final void run() {
                b.this.H();
            }
        }, 10000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void O() {
        if (!this.j.isEnabled()) {
            p.b("Bluetooth not enabled", new Object[0]);
            return;
        }
        this.u.postDelayed(new Runnable() { // from class: com.coveiot.mki.e0
            @Override // java.lang.Runnable
            public final void run() {
                b.this.L();
            }
        }, 20000L);
        BluetoothLeScanner bluetoothLeScanner = this.j.getBluetoothLeScanner();
        ScanFilter build = new ScanFilter.Builder().setDeviceName(this.o).setDeviceAddress(this.p).build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(build);
        ScanSettings.Builder builder = new ScanSettings.Builder();
        builder.setScanMode(1);
        p.b("Starting scan", 10000);
        bluetoothLeScanner.startScan(arrayList, builder.build(), this.E);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List<com.coveiot.mki.e>, java.util.ArrayList] */
    public /* synthetic */ void P() {
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            final e eVar = (e) it.next();
            if (!eVar.n()) {
                eVar.g().clear();
                this.x.post(new Runnable() { // from class: com.coveiot.mki.h0
                    @Override // java.lang.Runnable
                    public final void run() {
                        b.this.I(eVar);
                    }
                });
            }
        }
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [java.util.List<com.coveiot.mki.a>, java.util.ArrayList] */
    public static void Q(b bVar) {
        bVar.B = true;
        synchronized (bVar.i) {
            Iterator it = bVar.i.iterator();
            while (it.hasNext()) {
                ((com.coveiot.mki.a) it.next()).onConnected();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void S() {
        p.b("Stopping scan", new Object[0]);
        try {
            this.j.getBluetoothLeScanner().stopScan(this.E);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s() {
        C(true, "Timed out");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List<com.coveiot.mki.e>, java.util.ArrayList] */
    public /* synthetic */ void w(d dVar) {
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            if (eVar.n() && eVar.d() == dVar) {
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void x(e eVar) {
        if (Thread.currentThread().getId() != this.w.getId()) {
            throw new RuntimeException("Not in \"Command Request\" thread");
        }
        p.a("Command %s timed out", eVar.d().toString());
        y(eVar, false, String.format(Locale.ENGLISH, "Command %s timed out", eVar.d().toString()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.List<com.coveiot.mki.e>, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.util.List<com.coveiot.mki.e>, java.util.ArrayList] */
    public /* synthetic */ void z(f fVar, d dVar, e eVar) {
        if (this.t == null) {
            p.a("Missing write characteristics", new Object[0]);
            fVar.onFail("Missing write characteristics");
            return;
        }
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            e eVar2 = (e) it.next();
            if (eVar2.n() && eVar2.d() == dVar) {
                p.a("Previous command ongoing", new Object[0]);
                fVar.onFail("Previous command ongoing");
                return;
            }
        }
        this.l.add(eVar);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.List<com.coveiot.mki.a>, java.util.ArrayList] */
    public final void B(String str) {
        this.B = false;
        synchronized (this.i) {
            Iterator it = this.i.iterator();
            while (it.hasNext()) {
                ((com.coveiot.mki.a) it.next()).onDisconnected(str);
            }
        }
    }

    public final void C(final boolean z, final String str) {
        this.v.removeCallbacksAndMessages(null);
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") == 0) {
            this.v.post(new Runnable() { // from class: com.coveiot.mki.x
                @Override // java.lang.Runnable
                public final void run() {
                    b.this.J(z, str);
                }
            });
        } else {
            p.a("BLUETOOTH_CONNECT permission denied", new Object[0]);
        }
    }

    public final void M(final e eVar) {
        if (Thread.currentThread().getId() != this.w.getId()) {
            throw new RuntimeException("Not in \"Command Request\" thread");
        }
        if (eVar.h() >= eVar.g().size()) {
            p.a("Index for writing is out of bound", new Object[0]);
            return;
        }
        List<Byte> list = eVar.g().get(eVar.h());
        eVar.b(eVar.h() + 1);
        int size = list.size();
        final byte[] bArr = new byte[size];
        for (int i = 0; i < size; i++) {
            bArr[i] = list.get(i).byteValue();
        }
        eVar.p();
        this.x.post(new Runnable() { // from class: com.coveiot.mki.y
            @Override // java.lang.Runnable
            public final void run() {
                b.this.D(bArr, eVar);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.List<com.coveiot.mki.e>, java.util.ArrayList] */
    public final void U() {
        if (Thread.currentThread().getId() != this.w.getId()) {
            throw new RuntimeException("Not in \"Command Request\" thread");
        }
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            e eVar = (e) it.next();
            if (!eVar.n()) {
                eVar.b();
                it.remove();
            }
        }
    }

    public final void W() {
        this.u.removeCallbacksAndMessages(null);
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_SCAN") == 0) {
            this.u.post(new Runnable() { // from class: com.coveiot.mki.f0
                @Override // java.lang.Runnable
                public final void run() {
                    b.this.O();
                }
            });
        } else {
            p.a("BLUETOOTH_SCAN permission denied", new Object[0]);
        }
    }

    public final void X() {
        this.u.removeCallbacksAndMessages(null);
        if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_SCAN") == 0) {
            this.u.post(new Runnable() { // from class: com.coveiot.mki.d0
                @Override // java.lang.Runnable
                public final void run() {
                    b.this.S();
                }
            });
        } else {
            p.a("BLUETOOTH_SCAN permission denied", new Object[0]);
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.List<com.coveiot.mki.a>, java.util.ArrayList] */
    public void clearBleCallback(com.coveiot.mki.a aVar) {
        synchronized (this.i) {
            this.i.remove(aVar);
        }
    }

    public void command(d dVar, f fVar) {
        command(dVar, null, fVar);
    }

    public void command(d dVar, l lVar, f fVar) {
        command(dVar, lVar, null, fVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
        r10 = r10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void command(final com.coveiot.mki.d r9, com.coveiot.mki.l r10, final java.lang.Integer r11, final com.coveiot.mki.f r12) {
        /*
            r8 = this;
            if (r10 != 0) goto L16
            boolean r10 = r9.c()
            if (r10 == 0) goto L11
            com.coveiot.mki.o r10 = new com.coveiot.mki.o
            r10.<init>()
            r10.c()
            goto L16
        L11:
            com.coveiot.mki.l r10 = new com.coveiot.mki.l
            r10.<init>()
        L16:
            r6 = r10
            com.coveiot.mki.e r3 = new com.coveiot.mki.e
            com.coveiot.mki.h r10 = r8.y
            r3.<init>(r9, r12, r10)
            boolean r10 = r9.c()
            if (r10 == 0) goto L2e
            r10 = r6
            com.coveiot.mki.o r10 = (com.coveiot.mki.o) r10
            short r10 = r10.b()
            r3.a(r10)
        L2e:
            android.os.Handler r10 = r8.x
            com.coveiot.mki.w r7 = new com.coveiot.mki.w
            r0 = r7
            r1 = r8
            r2 = r12
            r4 = r11
            r5 = r9
            r0.<init>()
            r10.post(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.mki.b.command(com.coveiot.mki.d, com.coveiot.mki.l, java.lang.Integer, com.coveiot.mki.f):void");
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.List<com.coveiot.mki.a>, java.util.ArrayList] */
    public void connect(BluetoothDevice bluetoothDevice) {
        synchronized (this.i) {
            Iterator it = this.i.iterator();
            while (it.hasNext()) {
                ((com.coveiot.mki.a) it.next()).onConnecting();
            }
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 31 && ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
            p.a("BLUETOOTH_CONNECT permission denied", new Object[0]);
            B("Not able to connect to device");
            return;
        }
        this.o = bluetoothDevice.getName();
        this.p = bluetoothDevice.getAddress();
        this.r = t.a(this.o) ? i.c : i.b;
        this.A = true;
        this.C = false;
        if (this.k != null) {
            throw new RuntimeException("Previous gatt object not released");
        }
        p.b("Connecting to %s[%s]", this.o, this.p);
        this.k = i >= 26 ? bluetoothDevice.connectGatt(this, false, this.F, 2, 3, this.v) : i >= 23 ? bluetoothDevice.connectGatt(this, false, this.F, 2) : bluetoothDevice.connectGatt(this, false, this.F);
    }

    public void disconnect() {
        C(false, null);
    }

    public String getBluetoothAddress() {
        return this.p;
    }

    public String getBluetoothName() {
        return this.o;
    }

    public i getDeviceMode() {
        return this.r;
    }

    public j getDevicePlatform() {
        return this.q;
    }

    public boolean isConnected() {
        return this.B;
    }

    public boolean isConnecting() {
        return this.A;
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return this.h;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.j = BluetoothAdapter.getDefaultAdapter();
        HandlerThread handlerThread = new HandlerThread("thread-scanner");
        handlerThread.start();
        this.u = new Handler(handlerThread.getLooper());
        HandlerThread handlerThread2 = new HandlerThread("thread-gatt");
        handlerThread2.start();
        this.v = new Handler(handlerThread2.getLooper());
        HandlerThread handlerThread3 = new HandlerThread("thread-command-request");
        this.w = handlerThread3;
        handlerThread3.start();
        this.x = new Handler(this.w.getLooper());
        new HandlerThread("thread-toast").start();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.m = false;
        try {
            unregisterReceiver(this.n);
        } catch (Exception unused) {
        }
        C(false, null);
        this.w.quit();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent.getAction() != null) {
            String action = intent.getAction();
            action.getClass();
            if (action.equals(ACTION_NOTIFICATION_CLOSE)) {
                C(false, null);
                try {
                    unregisterReceiver(this.n);
                    return 2;
                } catch (Exception unused) {
                    return 2;
                }
            } else if (action.equals(ACTION_NOTIFICATION_INIT) && !this.m) {
                if (Build.VERSION.SDK_INT >= 26) {
                    ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(new NotificationChannel(CHANNEL_ID, "KaHa updater", 3));
                }
                startForeground(1, new NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("Updater").setContentText("Firmware update ongoing").setSmallIcon(R.mipmap.ic_launcher).build());
                this.m = true;
                C0332b c0332b = new C0332b();
                this.n = c0332b;
                registerReceiver(c0332b, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
                return 2;
            } else {
                return 2;
            }
        }
        return 2;
    }

    public void refreshService() {
        try {
            this.k.getClass().getMethod("refresh", new Class[0]).invoke(this.k, new Object[0]);
        } catch (Exception unused) {
        }
    }

    public void registerForLive(final d dVar, final f fVar) {
        final e eVar = new e(dVar, fVar);
        this.x.post(new Runnable() { // from class: com.coveiot.mki.v
            @Override // java.lang.Runnable
            public final void run() {
                b.this.z(fVar, dVar, eVar);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.List<com.coveiot.mki.a>, java.util.ArrayList] */
    public void setBleCallback(com.coveiot.mki.a aVar) {
        synchronized (this.i) {
            this.i.add(aVar);
        }
    }

    public void setLauncherActivity(Class<?> cls) {
    }

    public void setRetryOnFailure(boolean z) {
        this.z = z;
    }

    public void shareLogFile(AppCompatActivity appCompatActivity) {
        try {
            int i = p.f7292a;
            Uri uriForFile = FileProvider.getUriForFile(appCompatActivity, getPackageName() + ".provider", null);
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.STREAM", uriForFile);
            Intent createChooser = Intent.createChooser(intent, "Share with");
            if (createChooser.resolveActivityInfo(getPackageManager(), 0) == null) {
                p.a("No file sharing app found", new Object[0]);
                return;
            }
            for (ResolveInfo resolveInfo : appCompatActivity.getPackageManager().queryIntentActivities(createChooser, 65536)) {
                grantUriPermission(resolveInfo.activityInfo.packageName, uriForFile, 3);
            }
            appCompatActivity.startActivity(createChooser);
        } catch (Exception unused) {
            p.a("Logging session not started", new Object[0]);
        }
    }

    public void stop() {
        this.m = false;
        stopForeground(true);
        stopSelf();
    }

    public void stopAllCommands() {
        this.x.post(new Runnable() { // from class: com.coveiot.mki.z
            @Override // java.lang.Runnable
            public final void run() {
                b.this.P();
            }
        });
    }

    public void unregisterForLive(final d dVar) {
        this.x.post(new Runnable() { // from class: com.coveiot.mki.g0
            @Override // java.lang.Runnable
            public final void run() {
                b.this.w(dVar);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.List<com.coveiot.mki.e>, java.util.ArrayList] */
    public final void y(e eVar, boolean z, Object... objArr) {
        if (Thread.currentThread().getId() != this.w.getId()) {
            throw new RuntimeException("Not in \"Command Request\" thread");
        }
        f c2 = eVar.c();
        if (!eVar.n()) {
            eVar.b();
            this.l.remove(eVar);
        }
        if (c2 != null) {
            if (z) {
                c2.onSuccess(objArr);
            } else {
                c2.onFail(objArr.length > 0 ? (String) objArr[0] : "Unknown error");
            }
        }
    }
}
