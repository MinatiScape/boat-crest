package com.sifli.siflidfu;

import android.app.IntentService;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.BleConst;
import com.sifli.siflidfu.constants.General;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;
/* loaded from: classes12.dex */
public class SifliDFUService extends IntentService {
    public static final String BROADCAST_DFU_LOG = "com.sifli.siflidfu.BROADCAST_DFU_LOG";
    public static final String BROADCAST_DFU_PROGRESS = "com.sifli.siflidfu.EXTRA_BROADCAST_PROGRESS";
    public static final String BROADCAST_DFU_STATE = "com.sifli.siflidfu.BROADCAST_DFU_STATE";
    public static final String EXTRA_DFU_PROGRESS = "com.sifli.siflidfu.EXTRA_DFU_PROGRESS";
    public static final String EXTRA_DFU_PROGRESS_TYPE = "com.sifli.siflidfu.EXTRA_DFU_PROGRESS_TYPE";
    public static final String EXTRA_DFU_STATE = "com.sifli.siflidfu.EXTRA_DFU_STATE";
    public static final String EXTRA_DFU_STATE_RESULT = "com.sifli.siflidfu.EXTRA_DFU_STATE_RESULT";
    public static final String EXTRA_LOG_LEVEL = "com.sifli.siflidfu.LOG_LEVEL";
    public static final String EXTRA_LOG_MESSAGE = "com.sifli.siflidfu.EXTRA_LOG_MESSAGE";
    public static final Boolean X = Boolean.FALSE;
    public boolean A;
    public int B;
    public int C;
    public int D;
    public int E;
    public int F;
    public int G;
    public int H;
    public int I;
    public int J;
    public long K;
    public BroadcastReceiver L;
    public final Object M;
    public Handler N;
    public boolean O;
    public int P;
    public byte[] Q;
    public int R;
    public int S;
    public final Runnable T;
    public final Runnable U;
    public final Runnable V;
    public final Runnable W;
    public String h;
    public int i;
    public Handler j;
    public BluetoothGattCharacteristic k;
    public BluetoothGatt l;
    public BluetoothAdapter m;
    public BluetoothGattCallback n;
    public boolean o;
    public DFUState p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    public boolean y;
    public boolean z;

    /* loaded from: classes12.dex */
    public class a extends BluetoothGattCallback {
        public a() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            if (bluetoothGattCharacteristic.getUuid().toString().equals(SifliDFUService.this.k.getUuid().toString())) {
                SifliDFUService.this.K(bluetoothGattCharacteristic.getValue());
                synchronized (SifliDFUService.this.M) {
                    SifliDFUService.this.M.notifyAll();
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            SifliDFUService.this.y = true;
            synchronized (SifliDFUService.this.M) {
                SifliDFUService.this.M.notifyAll();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            Log.d("sifli-DFU", "onConnectionStateChange status " + i + ", new state " + i2);
            if (i2 == 2) {
                if (!SifliDFUService.this.o) {
                    SifliDFUService.this.o = true;
                } else {
                    Log.e("sifli-DFU", "return for repeat");
                    return;
                }
            } else if (i2 == 0) {
                SifliDFUService.this.o = false;
            }
            if (i2 == 2) {
                if (SifliDFUService.this.i == 3) {
                    Log.e("sifli-DFU", "state is ready!");
                    return;
                }
                if (SifliDFUService.this.p.getState() == 2 || SifliDFUService.this.p.getState() == 10) {
                    SifliDFUService.this.p.setState(3);
                }
                SifliDFUService.this.i = 2;
                SifliDFUService.this.waitFor(1000L);
                SifliDFUService.this.n0(bluetoothGatt, 247);
                SifliDFUService.this.waitFor(1000L);
                SifliDFUService.this.refreshDeviceCache(bluetoothGatt);
                SifliDFUService.this.waitFor(Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
                if (SifliDFUService.this.H != 0) {
                    Log.d("sifli-DFU", "abort connect due to error");
                    if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(SifliDFUService.this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                        Log.e("sifli-DFU", "no Permission");
                        SifliDFUService.this.H = 61;
                        synchronized (SifliDFUService.this.M) {
                            SifliDFUService.this.M.notifyAll();
                        }
                        return;
                    }
                    bluetoothGatt.close();
                    synchronized (SifliDFUService.this.M) {
                        SifliDFUService.this.M.notifyAll();
                    }
                    return;
                } else if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(SifliDFUService.this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                    Log.e("sifli-DFU", "no Permission");
                    SifliDFUService.this.H = 61;
                    synchronized (SifliDFUService.this.M) {
                        SifliDFUService.this.M.notifyAll();
                    }
                    return;
                } else {
                    boolean discoverServices = bluetoothGatt.discoverServices();
                    SifliDFUService.this.N.postDelayed(SifliDFUService.this.W, 40000L);
                    Log.i("sifli-DFU", "Attempting to start service discovery:" + discoverServices);
                    if (discoverServices) {
                        return;
                    }
                    SifliDFUService.this.H = 66;
                }
            } else if (i2 == 0) {
                Log.i("sifli-DFU", "disconnect with " + i);
                SifliDFUService sifliDFUService = SifliDFUService.this;
                sifliDFUService.h0(5, "disconnect with " + i);
                SifliDFUService.this.B = i;
                Log.i("sifli-DFU", "clear state");
                SifliDFUService.this.i = 0;
                bluetoothGatt.close();
                if (SifliDFUService.this.p.getState() == 2) {
                    Log.d("sifli-DFU", "disconnect remote reboot");
                } else if (SifliDFUService.this.p.getState() != 9 && SifliDFUService.this.p.getState() != 10) {
                    SifliDFUService.this.H = 70;
                } else if (i != 133 && i != 62) {
                    SifliDFUService.this.H = 70;
                } else {
                    Log.w("sifli-DFU", "retry due to reboot connect 133");
                    SifliDFUService.this.p.setState(10);
                }
            }
            synchronized (SifliDFUService.this.M) {
                SifliDFUService.this.M.notifyAll();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            Log.i("sifli-DFU", "onDescriptorWrite");
            SifliDFUService.this.A = true;
            synchronized (SifliDFUService.this.M) {
                SifliDFUService.this.M.notifyAll();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            Log.d("sifli-DFU", "onMtuChanged " + i + ", status " + i2);
            if (i > 247) {
                SifliDFUService.this.q = 244;
            } else if (i < 23) {
                SifliDFUService.this.q = 20;
            } else {
                SifliDFUService.this.q = i - 3;
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            SifliDFUService.this.N.removeCallbacks(SifliDFUService.this.W);
            int i2 = 0;
            if (i == 0) {
                Log.d("sifli-DFU", "onServicesDiscovered");
                int i3 = 0;
                while (i2 < bluetoothGatt.getServices().size()) {
                    Iterator<BluetoothGattCharacteristic> it = bluetoothGatt.getServices().get(i2).getCharacteristics().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            BluetoothGattCharacteristic next = it.next();
                            Log.e("sifli-DFU", "find uuid: " + next.getUuid().toString() + ", expect: 00000000-0000-0200-6473-5f696c666973");
                            if (next.getUuid().toString().equals("00000000-0000-0200-6473-5f696c666973")) {
                                Log.i("sifli-DFU", "find serial trans UUID");
                                SifliDFUService.this.k = next;
                                SifliDFUService.this.i = 3;
                                i3 = 1;
                                break;
                            }
                        }
                    }
                    i2++;
                }
                i2 = i3;
            } else {
                Log.w("sifli-DFU", "onServicesDiscovered received: " + i);
                SifliDFUService.this.H = 101;
                SifliDFUService.this.B = i;
                SifliDFUService.this.i = 4;
                SifliDFUService sifliDFUService = SifliDFUService.this;
                sifliDFUService.g0(sifliDFUService.H, i);
            }
            if (i2 == 0) {
                Log.e("sifli-DFU", "fail to find target uuid");
                SifliDFUService.this.h0(20, "fail to find target uuid");
                SifliDFUService.this.H = 102;
                SifliDFUService.this.i = 4;
                SifliDFUService sifliDFUService2 = SifliDFUService.this;
                sifliDFUService2.g0(sifliDFUService2.H, i);
            }
            synchronized (SifliDFUService.this.M) {
                SifliDFUService.this.M.notifyAll();
            }
        }
    }

    /* loaded from: classes12.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.e("sifli-DFU", "connect call timeout, system bt may error");
            SifliDFUService.this.H = 62;
            synchronized (SifliDFUService.this.M) {
                SifliDFUService.this.M.notify();
            }
        }
    }

    /* loaded from: classes12.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.e("sifli-DFU", "write descriptor timeout");
            SifliDFUService.this.H = 68;
            synchronized (SifliDFUService.this.M) {
                SifliDFUService.this.M.notifyAll();
            }
        }
    }

    /* loaded from: classes12.dex */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.e("sifli-DFU", "ota command timeout");
            SifliDFUService.this.H = 79;
            synchronized (SifliDFUService.this.M) {
                SifliDFUService.this.M.notifyAll();
            }
        }
    }

    /* loaded from: classes12.dex */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.e("sifli-DFU", "discovery timeout");
            SifliDFUService.this.h0(5, "discovery timeout");
            SifliDFUService.this.H = 67;
            synchronized (SifliDFUService.this.M) {
                SifliDFUService.this.M.notifyAll();
            }
        }
    }

    /* loaded from: classes12.dex */
    public class f extends BroadcastReceiver {
        public f() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE) != 10) {
                return;
            }
            Log.e("sifli-DFU", "Bluetooth off");
            SifliDFUService.this.H = 74;
            SifliDFUService.this.j.removeMessages(1);
            synchronized (SifliDFUService.this.M) {
                SifliDFUService.this.M.notifyAll();
            }
        }
    }

    /* loaded from: classes12.dex */
    public class g implements Handler.Callback {
        public g() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            if (SifliDFUService.this.i != 0 && SifliDFUService.this.H == 0) {
                int i = message.what;
                if (i != 0) {
                    if (i == 1) {
                        byte b = message.getData().getByteArray("com.sifli.siflidfu.extra.BLE_DATA")[1];
                        if (b == 0 || b == 1) {
                            SifliDFUService sifliDFUService = SifliDFUService.this;
                            int i2 = sifliDFUService.E + 1;
                            sifliDFUService.E = i2;
                            int i3 = ((i2 + sifliDFUService.D) * 100) / sifliDFUService.C;
                            sifliDFUService.N(i3, sifliDFUService.F);
                            if (SifliDFUService.X.booleanValue()) {
                                Log.d("sifli-DFU", "progress " + i3 + ", countCurrent " + SifliDFUService.this.E + ", countPrevious " + SifliDFUService.this.D + ", countAll " + SifliDFUService.this.C);
                            }
                        }
                    }
                    return false;
                }
                byte[] byteArray = message.getData().getByteArray("com.sifli.siflidfu.extra.BLE_DATA");
                if (SifliDFUService.this.k == null || SifliDFUService.this.l == null) {
                    return false;
                }
                SifliDFUService.this.k.setValue(byteArray);
                SifliDFUService.this.k.setWriteType(1);
                SifliDFUService.this.y = false;
                if (Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(SifliDFUService.this, "android.permission.BLUETOOTH_CONNECT") == 0) {
                    SifliDFUService.this.l.writeCharacteristic(SifliDFUService.this.k);
                    synchronized (SifliDFUService.this.M) {
                        while (!SifliDFUService.this.y && SifliDFUService.this.H == 0) {
                            try {
                                SifliDFUService.this.M.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    return false;
                }
                Log.e("sifli-DFU", "no Permission");
                SifliDFUService.this.H = 61;
                synchronized (SifliDFUService.this.M) {
                    SifliDFUService.this.M.notifyAll();
                }
                return false;
            }
            Log.e("sifli-DFU", "handle exit " + SifliDFUService.this.H);
            return false;
        }

        public /* synthetic */ g(SifliDFUService sifliDFUService, a aVar) {
            this();
        }
    }

    public SifliDFUService() {
        super("SifliDFUIntentService");
        this.h = " 1.1.12";
        this.q = 20;
        this.G = 0;
        this.M = new Object();
        this.N = new Handler();
        this.O = false;
        this.T = new b();
        this.U = new c();
        this.V = new d();
        this.W = new e();
    }

    public static String byte2hex(byte[] bArr) {
        if (bArr == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                hexString = BleConst.GetDeviceTime + hexString;
            }
            sb.append(hexString);
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
        }
        return sb.toString();
    }

    public static void startActionDFUNand(Context context, String str, ArrayList<DFUImagePath> arrayList, int i, int i2) {
        Intent intent = new Intent(context, SifliDFUService.class);
        intent.setAction("com.sifli.siflidfu.action.NAND");
        intent.putExtra("com.sifli.siflidfu.extra.ADDRESS", str);
        intent.putExtra("com.sifli.siflidfu.extra.IMAGES", arrayList);
        intent.putExtra("com.sifli.siflidfu.extra.PARAM1", i);
        intent.putExtra("com.sifli.siflidfu.extra.PARAM2", i2);
        context.startService(intent);
    }

    public static void startActionDFUNor(Context context, String str, ArrayList<DFUImagePath> arrayList, int i, int i2) {
        Intent intent = new Intent(context, SifliDFUService.class);
        intent.setAction("com.sifli.siflidfu.action.NOR_V1");
        intent.putExtra("com.sifli.siflidfu.extra.ADDRESS", str);
        intent.putExtra("com.sifli.siflidfu.extra.IMAGES", arrayList);
        intent.putExtra("com.sifli.siflidfu.extra.PARAM1", i);
        intent.putExtra("com.sifli.siflidfu.extra.PARAM2", i2);
        context.startService(intent);
    }

    public static void startActionDFUNorExt(Context context, String str, ArrayList<DFUImagePath> arrayList, int i, int i2) {
        Intent intent = new Intent(context, SifliDFUService.class);
        intent.setAction("com.sifli.siflidfu.action.NOR_V2");
        intent.putExtra("com.sifli.siflidfu.extra.ADDRESS", str);
        intent.putExtra("com.sifli.siflidfu.extra.IMAGES", arrayList);
        intent.putExtra("com.sifli.siflidfu.extra.PARAM1", i);
        intent.putExtra("com.sifli.siflidfu.extra.PARAM2", i2);
        context.startService(intent);
    }

    public final OTAFile A(ArrayList<OTAFile> arrayList, int i) {
        Iterator<OTAFile> it = arrayList.iterator();
        while (it.hasNext()) {
            OTAFile next = it.next();
            if (next.getImageID() == i) {
                return next;
            }
        }
        return null;
    }

    public final int B(int i) {
        int i2;
        Log.d("sifli-DFU", "image total len " + i);
        if (i % General.SIFLI_DFU_PACKET_BODY_LEN_NAND == 0) {
            i2 = i / General.SIFLI_DFU_PACKET_BODY_LEN_NAND;
        } else {
            i2 = (i / General.SIFLI_DFU_PACKET_BODY_LEN_NAND) + 1;
        }
        int i3 = i - (i2 * 36);
        Log.d("sifli-DFU", "imageLen " + i3);
        return i3;
    }

    public final int C(ArrayList<OTAFile> arrayList, int i, int i2) {
        int fileLength;
        Iterator<OTAFile> it = arrayList.iterator();
        int i3 = 0;
        while (it.hasNext()) {
            OTAFile next = it.next();
            if (next.getFileIndex() < i) {
                if (next.getFileLength() % i2 == 0) {
                    fileLength = next.getFileLength() / i2;
                } else {
                    fileLength = (next.getFileLength() / i2) + 1;
                }
                i3 += fileLength;
            }
        }
        return i3;
    }

    public final int D(ArrayList<OTAFile> arrayList, int i, int i2) {
        int fileLength;
        Iterator<OTAFile> it = arrayList.iterator();
        int i3 = 0;
        while (it.hasNext()) {
            OTAFile next = it.next();
            if (next.getImageID() != -1) {
                if (next.getImageID() >= i) {
                    break;
                }
                if (next.getFileLength() % i2 == 0) {
                    fileLength = next.getFileLength() / i2;
                } else {
                    fileLength = (next.getFileLength() / i2) + 1;
                }
                i3 += fileLength;
            }
        }
        return i3;
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02c6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int E(java.lang.String r16, java.util.ArrayList<com.sifli.siflidfu.DFUImagePath> r17, int r18, int r19) {
        /*
            Method dump skipped, instructions count: 865
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sifli.siflidfu.SifliDFUService.E(java.lang.String, java.util.ArrayList, int, int):int");
    }

    public final int F(String str, ArrayList<DFUImagePath> arrayList, int i, int i2) {
        Log.d("sifli-DFU", "handleActionDFUNorV1");
        int J = J();
        this.w = i2;
        if (J != 0) {
            return J;
        }
        HandlerThread handlerThread = new HandlerThread("BleWrite");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper(), new g(this, null));
        this.j = handler;
        handler.removeCallbacksAndMessages(null);
        ArrayList<OTAFile> imageFile = FileProcess.getImageFile(arrayList, this, 0);
        if (imageFile == null) {
            return 60;
        }
        Collections.sort(imageFile, new OTAImageComparator());
        w(str, this.n);
        if (this.i == 3 && this.H == 0) {
            o0(this.l, this.k);
            synchronized (this.M) {
                while (!this.A && this.H == 0) {
                    try {
                        this.M.wait();
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            this.N.removeCallbacks(this.U);
            int i3 = this.H;
            if (i3 != 0) {
                return i3;
            }
            OTAFile A = A(imageFile, -1);
            Objects.requireNonNull(A);
            W(A.getFileData(), i);
            int i4 = this.H;
            if (i4 != 0) {
                return i4;
            }
            if (this.G == 1) {
                this.p.setState(2);
            }
            if (i == 2) {
                b0();
            } else {
                X();
            }
            if (this.G == 1) {
                Log.d("sifli-DFU", "wait reboot, mConnectionState " + this.i);
                if (this.i != 0) {
                    synchronized (this.M) {
                        while (this.i != 0) {
                            try {
                                this.M.wait();
                            } catch (InterruptedException e3) {
                                throw new RuntimeException(e3);
                            }
                        }
                    }
                }
                this.p.setState(9);
                w(str, this.n);
                if (this.i == 3 && this.H == 0) {
                    o0(this.l, this.k);
                    synchronized (this.M) {
                        while (!this.A && this.H == 0) {
                            try {
                                this.M.wait();
                            } catch (InterruptedException e4) {
                                e4.printStackTrace();
                            }
                        }
                    }
                    this.N.removeCallbacks(this.U);
                    int i5 = this.H;
                    if (i5 != 0) {
                        return i5;
                    }
                } else {
                    return this.H;
                }
            }
            int i6 = i == 2 ? 1 : 0;
            this.F = 0;
            this.C = x(imageFile, 548);
            m0();
            int I = I(i6, imageFile, 548);
            Log.i("sifli-DFU", "send image end with " + I);
            if (I != 0) {
                return I;
            }
            Log.i("sifli-DFU", "wait end mode " + this.I);
            int i7 = this.I;
            if (i7 == 1) {
                e0();
            } else if (i7 == 0) {
                d0();
            } else if (i7 == 2) {
                e0();
                O(this.H);
            }
            int i8 = this.H;
            return i8 != 0 ? i8 : I;
        }
        return this.H;
    }

    public final int G(String str, ArrayList<DFUImagePath> arrayList, int i, int i2) {
        int i3;
        Log.d("sifli-DFU", "handleActionDFUNorV2");
        h0(5, "OTA START");
        int J = J();
        this.w = i2;
        if (J != 0) {
            return J;
        }
        HandlerThread handlerThread = new HandlerThread("BleWrite");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper(), new g(this, null));
        this.j = handler;
        handler.removeCallbacksAndMessages(null);
        ArrayList<OTAFile> imageFile = FileProcess.getImageFile(arrayList, this, 0);
        if (imageFile == null) {
            return 60;
        }
        Collections.sort(imageFile, new OTAImageComparator());
        w(str, this.n);
        if (this.i == 3 && this.H == 0) {
            o0(this.l, this.k);
            synchronized (this.M) {
                while (!this.A && this.H == 0) {
                    try {
                        this.M.wait();
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            this.N.removeCallbacks(this.U);
            int i4 = this.H;
            if (i4 != 0) {
                return i4;
            }
            OTAFile A = A(imageFile, -1);
            Objects.requireNonNull(A);
            Z(A.getFileData());
            int i5 = this.H;
            if (i5 != 0) {
                return i5;
            }
            if (this.G == 1) {
                this.p.setState(2);
            }
            Log.d("sifli-DFU", "sendDfuImageInitComplete resumeMode " + i + ", remote resume " + this.r);
            if (i != 1 || this.r == 0) {
                i3 = 0;
            } else {
                Log.i("sifli-DFU", "sendDfuImageInitComplete use resume");
                i3 = 1;
            }
            Y(i3);
            if (i3 == 1) {
                i3 = this.r;
            }
            if (this.G == 1) {
                Log.d("sifli-DFU", "wait reboot, mConnectionState " + this.i);
                if (this.i != 0) {
                    synchronized (this.M) {
                        while (this.i != 0) {
                            try {
                                this.M.wait();
                            } catch (InterruptedException e3) {
                                throw new RuntimeException(e3);
                            }
                        }
                    }
                }
                this.p.setState(9);
                w(str, this.n);
                if (this.i == 3 && this.H == 0) {
                    o0(this.l, this.k);
                    synchronized (this.M) {
                        while (!this.A && this.H == 0) {
                            try {
                                this.M.wait();
                            } catch (InterruptedException e4) {
                                e4.printStackTrace();
                            }
                        }
                    }
                    this.N.removeCallbacks(this.U);
                    int i6 = this.H;
                    if (i6 != 0) {
                        return i6;
                    }
                } else {
                    return this.H;
                }
            }
            this.F = 0;
            this.C = x(imageFile, 548);
            m0();
            I(i3, imageFile, 548);
            int i7 = this.H;
            if (i7 != 0) {
                return i7;
            }
            Log.i("sifli-DFU", "wait end mode " + this.I);
            int i8 = this.I;
            if (i8 == 1) {
                e0();
            } else if (i8 == 0) {
                d0();
            } else if (i8 == 2) {
                e0();
                int i9 = this.H;
                if (i9 == 0) {
                    O(i9);
                    waitFor(5000L);
                }
            }
            int i10 = this.H;
            return i10 != 0 ? i10 : J;
        }
        return this.H;
    }

    public final int H(int i, ArrayList<OTAFile> arrayList) {
        int i2;
        if (i == 1) {
            Log.d("sifli-DFU", "enable resume");
            i2 = this.s;
        } else {
            i2 = 0;
        }
        while (i2 < arrayList.size()) {
            OTAFile oTAFile = arrayList.get(i2);
            CurrentSendFile currentSendFile = new CurrentSendFile(oTAFile.getFileData(), 10240, oTAFile.getFileName());
            T(oTAFile.getFilePath(), currentSendFile.getTotalSize(), currentSendFile.getTotalCount(), oTAFile.getFileIndex());
            this.D = C(arrayList, oTAFile.getFileIndex(), 10240);
            this.E = 0;
            int i3 = this.H;
            if (i3 != 0) {
                return i3;
            }
            int i4 = 0;
            boolean z = false;
            while (i4 < currentSendFile.getTotalCount()) {
                int i5 = i4 + 1;
                S(i5, currentSendFile.getData(i4));
                if (currentSendFile.getTotalCount() == i5) {
                    z = true;
                }
                int i6 = this.w;
                if (i6 != 0 && i5 % i6 == 0) {
                    z = true;
                }
                if (z) {
                    this.z = false;
                    try {
                        synchronized (this.M) {
                            while (!this.z && this.H == 0) {
                                this.M.wait();
                            }
                        }
                    } catch (InterruptedException unused) {
                        Log.e("sifli-DFU", "Sleeping interrupted");
                    }
                    int i7 = this.H;
                    if (i7 != 0) {
                        if (i7 == 13) {
                            int i8 = this.x;
                            this.E = i8;
                            i4 = i8 - 1;
                        } else {
                            Log.d("sifli-DFU", "download exit with " + this.H);
                            return this.H;
                        }
                    }
                    z = false;
                }
                i4++;
            }
            int i9 = this.H;
            if (i9 != 0) {
                return i9;
            }
            P(oTAFile.getFileIndex());
            int i10 = this.H;
            if (i10 != 0) {
                return i10;
            }
            i2++;
        }
        return 0;
    }

    public final int I(int i, ArrayList<OTAFile> arrayList, int i2) {
        int i3;
        int i4;
        int i5;
        int i6 = 1;
        int i7 = 0;
        if (i == 1) {
            Log.d("sifli-DFU", "enable resume");
            i3 = this.t;
            if (this.u == 1) {
                this.s = 0;
            }
            this.w = this.v;
            i4 = 1;
        } else {
            i3 = 0;
            i4 = 0;
        }
        int i8 = 2;
        if (i == 2) {
            Log.d("sifli-DFU", "resume query");
            i3 = this.t;
            this.w = this.v;
        }
        int i9 = i3;
        int i10 = 0;
        while (i10 < arrayList.size()) {
            OTAFile oTAFile = arrayList.get(i10);
            this.D = D(arrayList, oTAFile.getImageID(), i2);
            this.E = i7;
            if (oTAFile.getImageID() != -1) {
                if (i == i8 && oTAFile.getImageID() == i9) {
                    Log.d("sifli-DFU", "resume psram image id " + i9 + ", " + this.s);
                    i4 = i6;
                }
                if (i4 == 0) {
                    i5 = i7;
                } else if (oTAFile.getImageID() != i9) {
                    continue;
                } else {
                    i5 = this.s;
                    this.E = i5;
                    i4 = i7;
                }
                CurrentSendFile currentSendFile = new CurrentSendFile(oTAFile.getFileData(), i2, oTAFile.getFileName());
                c0(currentSendFile.getTotalSize(), currentSendFile.getTotalCount(), oTAFile.getImageID());
                int i11 = this.H;
                if (i11 != 0) {
                    return i11;
                }
                if (this.J == i6) {
                    Log.i("sifli-DFU", "skip " + oTAFile.getImageID());
                } else {
                    int i12 = i5;
                    int i13 = i7;
                    while (i12 < currentSendFile.getTotalCount()) {
                        int i14 = i12 + 1;
                        if (i14 == currentSendFile.getTotalCount()) {
                            Log.d("sifli-DFU", "last packet " + (FileProcess.getFileSize(oTAFile.getFilePath()) - (i12 * i2)));
                        }
                        a0(oTAFile.getImageID(), i14, currentSendFile.getData(i12));
                        if (currentSendFile.getTotalCount() == i14) {
                            i13 = 1;
                        }
                        if (this.H != 0) {
                            i13 = 1;
                        }
                        int i15 = this.w;
                        if (i15 != 0 && i14 % i15 == 0) {
                            i13 = 1;
                        }
                        if (i13 != 0) {
                            this.z = false;
                            try {
                                synchronized (this.M) {
                                    while (!this.z && this.H == 0) {
                                        this.M.wait();
                                    }
                                }
                            } catch (InterruptedException unused) {
                                Log.e("sifli-DFU", "Sleeping interrupted");
                            }
                            int i16 = this.H;
                            if (i16 != 0) {
                                if (i16 == 13) {
                                    int i17 = this.x;
                                    i12 = i17 - 1;
                                    this.E = i17;
                                    Log.d("sifli-DFU", "continue with index " + i12);
                                    Log.d("sifli-DFU", "send lose rsp again");
                                    f0(0);
                                    this.H = 0;
                                } else {
                                    Log.d("sifli-DFU", "download exit with " + this.H);
                                    return this.H;
                                }
                            }
                            i13 = 0;
                        }
                        i6 = 1;
                        i12++;
                    }
                    int i18 = this.H;
                    if (i18 != 0) {
                        return i18;
                    }
                    V(oTAFile.getImageID(), i10 + 1 != arrayList.size() ? i6 : 0);
                }
            }
            i10++;
            i7 = 0;
            i8 = 2;
        }
        return i7;
    }

    public final int J() {
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService("bluetooth");
        if (bluetoothManager == null) {
            Log.e("sifli-DFU", "Unable to initialize BluetoothManager.");
            return 62;
        }
        this.O = false;
        BluetoothAdapter adapter = bluetoothManager.getAdapter();
        this.m = adapter;
        if (adapter == null) {
            Log.e("sifli-DFU", "Unable to obtain a BluetoothAdapter.");
            return 62;
        }
        this.p = new DFUState();
        this.n = z();
        this.o = false;
        this.I = 0;
        L();
        return 0;
    }

    public final void K(byte[] bArr) {
        byte[] l0 = l0(bArr);
        if (l0 != null && this.P == 1) {
            int unsignedShortFromByteArray = Utils.getUnsignedShortFromByteArray(l0, 0);
            int unsignedShortFromByteArray2 = Utils.getUnsignedShortFromByteArray(l0, 2);
            switch (unsignedShortFromByteArray) {
                case 1:
                    Log.d("sifli-DFU", "receive init response len " + l0.length);
                    this.z = true;
                    int unsignedShortFromByteArray3 = Utils.getUnsignedShortFromByteArray(l0, 4);
                    h0(5, "image init response " + unsignedShortFromByteArray3);
                    g0(unsignedShortFromByteArray, unsignedShortFromByteArray3);
                    if (unsignedShortFromByteArray3 != 0) {
                        Log.d("sifli-DFU", "receive init response " + unsignedShortFromByteArray3);
                        this.H = unsignedShortFromByteArray3;
                        return;
                    }
                    byte b2 = l0[6];
                    Log.d("sifli-DFU", "receive init response, result: " + unsignedShortFromByteArray3 + ", boot: " + ((int) b2));
                    this.G = b2;
                    return;
                case 4:
                    Log.d("sifli-DFU", "receive resume response");
                    this.z = true;
                    int unsignedShortFromByteArray4 = Utils.getUnsignedShortFromByteArray(l0, 4);
                    g0(unsignedShortFromByteArray, unsignedShortFromByteArray4);
                    if (unsignedShortFromByteArray4 != 0) {
                        this.H = unsignedShortFromByteArray4;
                        return;
                    }
                    byte b3 = l0[6];
                    this.G = b3;
                    this.u = l0[7];
                    Log.i("sifli-DFU", "resume message len " + unsignedShortFromByteArray2);
                    if (unsignedShortFromByteArray2 == 8) {
                        this.s = Utils.getUnsignedShortFromByteArray(l0, 8);
                        this.t = l0[10];
                        Log.i("sifli-DFU", "resume rsp, img id " + this.t + ", img num " + this.s);
                    } else if (unsignedShortFromByteArray2 == 12) {
                        this.s = Utils.getIntFromByteArray(l0, 8);
                        this.t = l0[12];
                        this.v = l0[13];
                        Log.i("sifli-DFU", "resume rsp, img id " + this.t + ", img num " + this.v + ",reply freq " + this.v);
                    } else {
                        Log.i("sifli-DFU", "fail to resume due to get data error");
                        this.H = 71;
                        return;
                    }
                    Log.i("sifli-DFU", "resume rsp: boot " + ((int) b3) + ", resume restart " + this.u);
                    return;
                case 7:
                    int unsignedShortFromByteArray5 = Utils.getUnsignedShortFromByteArray(l0, 4);
                    g0(unsignedShortFromByteArray, unsignedShortFromByteArray5);
                    Log.d("sifli-DFU", "SIFLI_DFU_IMAGE_SEND_START_RESPONSE " + unsignedShortFromByteArray5);
                    h0(5, "image start rsp " + unsignedShortFromByteArray5);
                    this.z = true;
                    if (unsignedShortFromByteArray5 != 0) {
                        this.H = unsignedShortFromByteArray5;
                        return;
                    } else if (l0.length == 8) {
                        this.I = l0[6];
                        Log.d("sifli-DFU", "wait end mode " + this.I);
                        this.J = l0[7];
                        Log.w("sifli-DFU", "image skip " + this.J);
                        return;
                    } else {
                        return;
                    }
                case 9:
                    byte b4 = l0[4];
                    Log.d("sifli-DFU", "DFU_IMAGE_END_RESPONSE " + ((int) b4));
                    h0(5, "image end rsp " + ((int) b4));
                    g0(unsignedShortFromByteArray, b4);
                    this.z = true;
                    if (b4 != 0) {
                        this.H = b4;
                        return;
                    }
                    return;
                case 11:
                    int unsignedShortFromByteArray6 = Utils.getUnsignedShortFromByteArray(l0, 4);
                    Log.d("sifli-DFU", "DFU_IMAGE_PACKET_DATA_RESPONSE " + unsignedShortFromByteArray6);
                    g0(unsignedShortFromByteArray, unsignedShortFromByteArray6);
                    this.z = true;
                    if (unsignedShortFromByteArray6 != 0) {
                        this.H = unsignedShortFromByteArray6;
                        return;
                    }
                    return;
                case 13:
                    byte b5 = l0[4];
                    Log.d("sifli-DFU", "DFU_END " + ((int) b5));
                    g0(unsignedShortFromByteArray, b5);
                    this.z = true;
                    if (b5 != 0) {
                        this.H = b5;
                        return;
                    }
                    return;
                case 22:
                    this.z = true;
                    int unsignedShortFromByteArray7 = Utils.getUnsignedShortFromByteArray(l0, 4);
                    g0(unsignedShortFromByteArray, unsignedShortFromByteArray7);
                    Log.d("sifli-DFU", "processNotify: result " + unsignedShortFromByteArray7);
                    h0(5, "dfu file init rsp result " + unsignedShortFromByteArray7);
                    if (unsignedShortFromByteArray7 != 0) {
                        this.H = unsignedShortFromByteArray7;
                        return;
                    }
                    this.r = Utils.getUnsignedShortFromByteArray(l0, 6);
                    this.s = Utils.getIntFromByteArray(l0, 8);
                    Log.e("sifli-DFU", "processNotify: init response " + unsignedShortFromByteArray7 + ", mode " + this.r + ", " + this.s);
                    return;
                case 25:
                    int unsignedShortFromByteArray8 = Utils.getUnsignedShortFromByteArray(l0, 4);
                    g0(unsignedShortFromByteArray, unsignedShortFromByteArray8);
                    this.z = true;
                    if (unsignedShortFromByteArray8 != 0) {
                        this.H = unsignedShortFromByteArray8;
                        return;
                    }
                    return;
                case 27:
                    int unsignedShortFromByteArray9 = Utils.getUnsignedShortFromByteArray(l0, 4);
                    g0(unsignedShortFromByteArray, unsignedShortFromByteArray9);
                    this.z = true;
                    if (unsignedShortFromByteArray9 != 0) {
                        Log.e("sifli-DFU", "file packet result " + unsignedShortFromByteArray9);
                        this.H = unsignedShortFromByteArray9;
                    }
                    this.w = Utils.getUnsignedShortFromByteArray(l0, 6);
                    this.x = Utils.getIntFromByteArray(l0, 8);
                    return;
                case 29:
                    int unsignedShortFromByteArray10 = Utils.getUnsignedShortFromByteArray(l0, 4);
                    g0(unsignedShortFromByteArray, unsignedShortFromByteArray10);
                    Log.d("sifli-DFU", "file end " + unsignedShortFromByteArray10);
                    this.H = unsignedShortFromByteArray10;
                    this.z = true;
                    return;
                case 31:
                    int unsignedShortFromByteArray11 = Utils.getUnsignedShortFromByteArray(l0, 4);
                    g0(unsignedShortFromByteArray, unsignedShortFromByteArray11);
                    Log.d("sifli-DFU", "file total end " + unsignedShortFromByteArray11);
                    h0(5, "file total end " + unsignedShortFromByteArray11);
                    this.H = unsignedShortFromByteArray11;
                    this.z = true;
                    return;
                case 33:
                    this.z = true;
                    Log.d("sifli-DFU", "data length " + l0.length);
                    int unsignedShortFromByteArray12 = Utils.getUnsignedShortFromByteArray(l0, 4);
                    Log.i("sifli-DFU", "dfu init rsp ext result " + unsignedShortFromByteArray12);
                    g0(unsignedShortFromByteArray, unsignedShortFromByteArray12);
                    if (unsignedShortFromByteArray12 != 0) {
                        this.H = unsignedShortFromByteArray12;
                        return;
                    }
                    this.r = l0[6];
                    this.u = l0[7];
                    this.s = Utils.getIntFromByteArray(l0, 8);
                    this.t = l0[12];
                    this.v = l0[13];
                    this.G = l0[14];
                    Log.w("sifli-DFU", "remote dfu version " + ((int) l0[15]));
                    Log.i("sifli-DFU", "dfu init rsp ext resume " + this.r + ", restart " + this.u);
                    Log.i("sifli-DFU", "dfu init rsp ext count " + this.s + ", id " + this.t + ", rsp " + this.v + ", boot " + this.G);
                    StringBuilder sb = new StringBuilder();
                    sb.append("init rsp ext ");
                    sb.append(unsignedShortFromByteArray12);
                    h0(5, sb.toString());
                    h0(0, "dfu init rsp ext resume " + this.r + ", restart " + this.u);
                    h0(0, "dfu init rsp ext count " + this.s + ", id " + this.t + ", rsp " + this.v + ", boot " + this.G);
                    return;
                case 35:
                    g0(unsignedShortFromByteArray, l0[4]);
                    this.w = Utils.getUnsignedShortFromByteArray(l0, 6);
                    if (this.x == Utils.getIntFromByteArray(l0, 8) && System.currentTimeMillis() - this.K < Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS) {
                        Log.w("sifli-DFU", "repeat message, ignore");
                        return;
                    }
                    this.x = Utils.getIntFromByteArray(l0, 8);
                    this.K = System.currentTimeMillis();
                    Log.e("sifli-DFU", "lost check at index " + this.x);
                    this.j.removeMessages(1);
                    f0(0);
                    this.H = 13;
                    return;
                case 37:
                    int unsignedShortFromByteArray13 = Utils.getUnsignedShortFromByteArray(l0, 4);
                    g0(unsignedShortFromByteArray, unsignedShortFromByteArray13);
                    Log.d("sifli-DFU", "dfu abort with " + unsignedShortFromByteArray13);
                    h0(5, "dfu abort " + unsignedShortFromByteArray13);
                    this.H = unsignedShortFromByteArray13;
                    this.z = true;
                    return;
                default:
                    return;
            }
        }
    }

    public final void L() {
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
        BroadcastReceiver y = y();
        this.L = y;
        registerReceiver(y, intentFilter);
    }

    public final void M() {
        if (this.O) {
            Log.d("sifli-DFU", "already released");
            return;
        }
        Log.i("sifli-DFU", "release");
        this.O = true;
        unregisterReceiver(this.L);
        if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
            Log.e("sifli-DFU", "no Permission");
            this.H = 61;
            synchronized (this.M) {
                this.M.notifyAll();
            }
            return;
        }
        BluetoothGatt bluetoothGatt = this.l;
        if (bluetoothGatt == null || this.i == 0) {
            return;
        }
        bluetoothGatt.disconnect();
        this.l.close();
    }

    public void N(int i, int i2) {
        Intent intent = new Intent(BROADCAST_DFU_PROGRESS);
        intent.putExtra(EXTRA_DFU_PROGRESS, i);
        intent.putExtra(EXTRA_DFU_PROGRESS_TYPE, i2);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public final void O(int i) {
        Log.i("sifli-DFU", "sendDfuEndCommand " + i);
        byte[] bArr = new byte[5];
        Utils.addShortToByteArray(13, bArr, 0);
        Utils.addShortToByteArray(1, bArr, 2);
        bArr[4] = (byte) i;
        j0(bArr, 0);
    }

    public final void P(int i) {
        Log.d("sifli-DFU", "sendDfuFileEnd");
        byte[] bArr = new byte[6];
        Utils.addShortToByteArray(28, bArr, 0);
        Utils.addShortToByteArray(2, bArr, 2);
        Utils.addShortToByteArray(i, bArr, 4);
        j0(bArr, 0);
        this.z = false;
        this.N.postDelayed(this.V, Constants.ONE_MIN_IN_MILLIS);
        try {
            synchronized (this.M) {
                while (!this.z && this.H == 0) {
                    this.M.wait();
                }
            }
        } catch (InterruptedException unused) {
            Log.e("sifli-DFU", "Sleeping interrupted");
        }
        this.N.removeCallbacks(this.V);
    }

    public final void Q(int i, int i2) {
        Log.d("sifli-DFU", "sendDfuFileInit " + i + ", count " + i2);
        byte[] bArr = new byte[20];
        Utils.addShortToByteArray(21, bArr, 0);
        Utils.addShortToByteArray(16, bArr, 2);
        Utils.addIntToByteArray(i2, bArr, 4);
        Utils.addIntToByteArray(i, bArr, 8);
        Utils.addShortToByteArray(2, bArr, 12);
        Utils.addShortToByteArray(4, bArr, 14);
        Utils.addIntToByteArray(100, bArr, 16);
        j0(bArr, 0);
        this.z = false;
        this.N.postDelayed(this.V, Constants.ONE_MIN_IN_MILLIS);
        try {
            synchronized (this.M) {
                while (!this.z && this.H == 0) {
                    this.M.wait();
                }
            }
        } catch (InterruptedException unused) {
            Log.e("sifli-DFU", "Sleeping interrupted");
        }
        this.N.removeCallbacks(this.V);
    }

    public final void R(int i) {
        Log.d("sifli-DFU", "sendDfuFileInitComplete");
        byte[] bArr = new byte[5];
        Utils.addShortToByteArray(23, bArr, 0);
        int i2 = 1;
        Utils.addShortToByteArray(1, bArr, 2);
        if (i == 1 && this.r == 1) {
            Log.e("sifli-DFU", "sendDfuFileInitComplete resume");
        } else {
            i2 = 0;
        }
        bArr[4] = (byte) i2;
        j0(bArr, 0);
    }

    public final void S(int i, byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 8];
        Utils.addShortToByteArray(26, bArr2, 0);
        Utils.addShortToByteArray(bArr.length + 4, bArr2, 2);
        Utils.addShortToByteArray(i, bArr2, 4);
        Utils.addShortToByteArray(bArr.length, bArr2, 6);
        System.arraycopy(bArr, 0, bArr2, 8, bArr.length);
        j0(bArr2, 1);
    }

    public final void T(String str, int i, int i2, int i3) {
        Log.d("sifli-DFU", "sendDfuFileStart " + str + ", len " + i + ", count " + i2 + ", index " + i3);
        StringBuilder sb = new StringBuilder();
        sb.append("sendDfuFileStart freq ");
        sb.append(this.w);
        Log.d("sifli-DFU", sb.toString());
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        byte[] bArr = new byte[bytes.length + 12 + 4];
        Utils.addShortToByteArray(24, bArr, 0);
        Utils.addShortToByteArray(bytes.length + 12, bArr, 2);
        Utils.addShortToByteArray(i3, bArr, 4);
        Utils.addShortToByteArray(this.w, bArr, 6);
        Utils.addIntToByteArray(i, bArr, 8);
        Utils.addShortToByteArray(i2, bArr, 12);
        Utils.addShortToByteArray(bytes.length, bArr, 14);
        System.arraycopy(bytes, 0, bArr, 16, bytes.length);
        j0(bArr, 0);
        this.z = false;
        this.N.postDelayed(this.V, Constants.ONE_MIN_IN_MILLIS);
        try {
            synchronized (this.M) {
                while (!this.z && this.H == 0) {
                    this.M.wait();
                }
            }
        } catch (InterruptedException unused) {
            Log.e("sifli-DFU", "Sleeping interrupted");
        }
        this.N.removeCallbacks(this.V);
    }

    public final void U(int i) {
        Log.d("sifli-DFU", "sendDfuFileTotalEnd " + i);
        byte[] bArr = new byte[6];
        Utils.addShortToByteArray(30, bArr, 0);
        Utils.addShortToByteArray(2, bArr, 2);
        Utils.addShortToByteArray(i, bArr, 4);
        j0(bArr, 0);
        this.z = false;
        this.N.postDelayed(this.V, Constants.ONE_MIN_IN_MILLIS);
        try {
            synchronized (this.M) {
                while (!this.z && this.H == 0) {
                    this.M.wait();
                }
            }
        } catch (InterruptedException unused) {
            Log.e("sifli-DFU", "Sleeping interrupted");
        }
        this.N.removeCallbacks(this.V);
    }

    public final void V(int i, int i2) {
        Log.i("sifli-DFU", "sendDfuImageEnd");
        h0(5, "send dfu image end");
        byte[] bArr = new byte[6];
        Utils.addShortToByteArray(8, bArr, 0);
        Utils.addShortToByteArray(2, bArr, 2);
        bArr[4] = (byte) i;
        bArr[5] = (byte) i2;
        Log.d("sifli-DFU", "sendDfuImageEnd, id " + i + ",more image: " + i2);
        j0(bArr, 0);
        this.z = false;
        this.N.postDelayed(this.V, 180000L);
        try {
            synchronized (this.M) {
                while (!this.z && this.H == 0) {
                    this.M.wait();
                }
            }
        } catch (InterruptedException unused) {
            Log.e("sifli-DFU", "Sleeping interrupted");
        }
        this.N.removeCallbacks(this.V);
    }

    public final void W(byte[] bArr, int i) {
        Log.d("sifli-DFU", "sendDfuInit " + bArr.length + ", mode " + i);
        byte[] bArr2 = new byte[bArr.length + 4];
        int i2 = 14;
        if (i == 1) {
            i2 = 0;
        } else if (i == 2) {
            i2 = 3;
        } else if (i != 3) {
            Log.e("sifli-DFU", "error mode " + i);
        }
        Utils.addShortToByteArray(i2, bArr2, 0);
        Utils.addShortToByteArray(bArr.length, bArr2, 2);
        System.arraycopy(bArr, 0, bArr2, 4, bArr.length);
        j0(bArr2, 0);
        this.z = false;
        this.N.postDelayed(this.V, Constants.ONE_MIN_IN_MILLIS);
        try {
            synchronized (this.M) {
                while (!this.z && this.H == 0) {
                    this.M.wait();
                }
            }
        } catch (InterruptedException unused) {
            Log.e("sifli-DFU", "Sleeping interrupted");
        }
        this.N.removeCallbacks(this.V);
    }

    public final void X() {
        Log.i("sifli-DFU", "sendDfuImageInitComplete");
        byte[] bArr = new byte[5];
        Utils.addShortToByteArray(2, bArr, 0);
        Utils.addShortToByteArray(1, bArr, 2);
        bArr[4] = (byte) 1;
        j0(bArr, 0);
    }

    public final void Y(int i) {
        Log.i("sifli-DFU", "sendDfuImageInitCompleteExt");
        byte[] bArr = new byte[5];
        Utils.addShortToByteArray(34, bArr, 0);
        Utils.addShortToByteArray(1, bArr, 2);
        bArr[4] = (byte) i;
        j0(bArr, 0);
    }

    public final void Z(byte[] bArr) {
        Log.d("sifli-DFU", "sendDfuInitExt " + bArr.length);
        byte[] bArr2 = new byte[bArr.length + 4];
        Utils.addShortToByteArray(32, bArr2, 0);
        Utils.addShortToByteArray(bArr.length, bArr2, 2);
        System.arraycopy(bArr, 0, bArr2, 4, bArr.length);
        j0(bArr2, 0);
        this.z = false;
        this.N.postDelayed(this.V, Constants.ONE_MIN_IN_MILLIS);
        try {
            synchronized (this.M) {
                while (!this.z && this.H == 0) {
                    this.M.wait();
                }
            }
        } catch (InterruptedException unused) {
            Log.e("sifli-DFU", "Sleeping interrupted");
        }
        this.N.removeCallbacks(this.V);
    }

    public final void a0(int i, int i2, byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 10];
        Utils.addShortToByteArray(10, bArr2, 0);
        Utils.addShortToByteArray(bArr.length + 6, bArr2, 2);
        Utils.addShortToByteArray(i, bArr2, 4);
        Utils.addShortToByteArray(i2, bArr2, 6);
        Utils.addShortToByteArray(bArr.length, bArr2, 8);
        System.arraycopy(bArr, 0, bArr2, 10, bArr.length);
        j0(bArr2, 1);
    }

    public final void b0() {
        Log.i("sifli-DFU", "sendDfuImageResumeComplete");
        byte[] bArr = new byte[5];
        Utils.addShortToByteArray(5, bArr, 0);
        Utils.addShortToByteArray(1, bArr, 2);
        bArr[4] = (byte) 1;
        j0(bArr, 0);
    }

    public final void c0(int i, int i2, int i3) {
        Log.i("sifli-DFU", "send dfu start id " + i3 + ", count " + i2 + ", len " + i);
        StringBuilder sb = new StringBuilder();
        sb.append("IMG ID: ");
        sb.append(i3);
        h0(5, sb.toString());
        byte[] bArr = new byte[14];
        Utils.addShortToByteArray(6, bArr, 0);
        Utils.addShortToByteArray(10, bArr, 2);
        Utils.addIntToByteArray(i, bArr, 4);
        Utils.addIntToByteArray(i2, bArr, 8);
        bArr[12] = (byte) this.w;
        bArr[13] = (byte) i3;
        j0(bArr, 0);
        this.z = false;
        this.N.postDelayed(this.V, 180000L);
        try {
            synchronized (this.M) {
                while (!this.z && this.H == 0) {
                    this.M.wait();
                }
            }
        } catch (InterruptedException unused) {
            Log.e("sifli-DFU", "Sleeping interrupted");
        }
        this.N.removeCallbacks(this.V);
    }

    public void close(BluetoothGatt bluetoothGatt) {
        Log.d("sifli-DFU", "Cleaning up...");
        if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
            this.H = 61;
            synchronized (this.M) {
                this.M.notifyAll();
            }
            return;
        }
        if (bluetoothGatt != null) {
            Log.d("sifli-DFU", "gatt.disconnect()");
            bluetoothGatt.disconnect();
            Log.d("sifli-DFU", "gatt.close()");
            bluetoothGatt.close();
        }
        this.i = 5;
    }

    public final void d0() {
        Log.i("sifli-DFU", "sendDfuImageTransmissionEnd");
        byte[] bArr = new byte[5];
        Utils.addShortToByteArray(12, bArr, 0);
        Utils.addShortToByteArray(1, bArr, 2);
        bArr[4] = 0;
        j0(bArr, 0);
    }

    public final void e0() {
        Log.i("sifli-DFU", "sendDfuImageTransmissionEndWait");
        byte[] bArr = new byte[5];
        Utils.addShortToByteArray(12, bArr, 0);
        Utils.addShortToByteArray(1, bArr, 2);
        bArr[4] = 0;
        j0(bArr, 0);
        this.z = false;
        this.N.postDelayed(this.V, Constants.ONE_MIN_IN_MILLIS);
        try {
            synchronized (this.M) {
                while (!this.z && this.H == 0) {
                    this.M.wait();
                }
            }
        } catch (InterruptedException unused) {
            Log.e("sifli-DFU", "Sleeping interrupted");
        }
        this.N.removeCallbacks(this.V);
    }

    public final void f0(int i) {
        Log.e("sifli-DFU", "sendDfuLinkLoseResponse");
        byte[] bArr = new byte[6];
        Utils.addShortToByteArray(36, bArr, 0);
        Utils.addShortToByteArray(2, bArr, 2);
        Utils.addShortToByteArray(i, bArr, 4);
        k0(bArr, 0, true);
    }

    public void g0(int i, int i2) {
        Intent intent = new Intent(BROADCAST_DFU_STATE);
        intent.putExtra(EXTRA_DFU_STATE, i);
        intent.putExtra(EXTRA_DFU_STATE_RESULT, i2);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public void h0(int i, String str) {
        Intent intent = new Intent(BROADCAST_DFU_LOG);
        intent.putExtra(EXTRA_LOG_MESSAGE, "[DFU] " + str);
        intent.putExtra(EXTRA_LOG_LEVEL, i);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public final void i0(byte[] bArr, int i, boolean z) {
        Message message = new Message();
        message.what = i;
        Bundle bundle = new Bundle();
        bundle.putByteArray("com.sifli.siflidfu.extra.BLE_DATA", bArr);
        message.setData(bundle);
        if (z) {
            this.j.sendMessageAtFrontOfQueue(message);
        } else {
            this.j.sendMessage(message);
        }
    }

    public final void j0(byte[] bArr, int i) {
        k0(bArr, i, false);
    }

    public final void k0(byte[] bArr, int i, boolean z) {
        int length = bArr.length;
        int i2 = length + 4;
        if (i2 > 65535) {
            Log.e("sifli-DFU", "serial length over");
            return;
        }
        int i3 = this.q;
        if (i2 <= i3) {
            byte[] bArr2 = new byte[i2];
            bArr2[0] = 1;
            bArr2[1] = 0;
            Utils.addShortToByteArray(length, bArr2, 2);
            System.arraycopy(bArr, 0, bArr2, 4, length);
            i0(bArr2, i, z);
            return;
        }
        byte[] bArr3 = new byte[i3];
        bArr3[0] = 1;
        bArr3[1] = 1;
        Utils.addShortToByteArray(length, bArr3, 2);
        System.arraycopy(bArr, 0, bArr3, 4, this.q - 4);
        int i4 = (this.q - 4) + 0;
        i0(bArr3, i, z);
        while (i4 < length) {
            int i5 = length - i4;
            int i6 = this.q;
            if (i5 > (i6 - 4) + 2) {
                byte[] bArr4 = new byte[i6];
                bArr4[0] = 1;
                bArr4[1] = 2;
                System.arraycopy(bArr, i4, bArr4, 2, (i6 - 4) + 2);
                i4 += (this.q - 4) + 2;
                i0(bArr4, i, z);
            } else {
                byte[] bArr5 = new byte[(i5 + 4) - 2];
                bArr5[0] = 1;
                bArr5[1] = 3;
                System.arraycopy(bArr, i4, bArr5, 2, i5);
                i0(bArr5, i, z);
                i4 = length;
            }
        }
    }

    public final byte[] l0(byte[] bArr) {
        this.P = bArr[0];
        byte b2 = bArr[1];
        if (b2 == 0) {
            int unsignedShortFromByteArray = Utils.getUnsignedShortFromByteArray(bArr, 2);
            this.R = unsignedShortFromByteArray;
            byte[] bArr2 = new byte[unsignedShortFromByteArray];
            this.Q = bArr2;
            System.arraycopy(bArr, 4, bArr2, 0, unsignedShortFromByteArray);
            return this.Q;
        } else if (b2 == 1) {
            this.S = 0;
            int unsignedShortFromByteArray2 = Utils.getUnsignedShortFromByteArray(bArr, 2);
            this.R = unsignedShortFromByteArray2;
            this.Q = new byte[unsignedShortFromByteArray2];
            Log.d("sifli-DFU", "receiveDataLen " + this.R);
            System.arraycopy(bArr, 4, this.Q, 0, bArr.length - 4);
            this.S = this.S + (bArr.length - 4);
            return null;
        } else if (b2 == 2) {
            System.arraycopy(bArr, 2, this.Q, this.S, bArr.length - 2);
            this.S += bArr.length - 2;
            return null;
        } else if (b2 == 3) {
            System.arraycopy(bArr, 2, this.Q, this.S, bArr.length - 2);
            int length = this.S + (bArr.length - 2);
            this.S = length;
            if (length != this.R) {
                Log.e("sifli-DFU", "length error, final: " + this.S + ", expect: " + this.R);
                return null;
            }
            Log.d("sifli-DFU", "receive finish");
            return this.Q;
        } else {
            return null;
        }
    }

    public final void m0() {
        Log.i("sifli-DFU", "update link");
        int i = Build.VERSION.SDK_INT;
        if (i >= 31 && ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
            Log.e("sifli-DFU", "no Permission");
            this.H = 61;
            synchronized (this.M) {
                this.M.notifyAll();
            }
            return;
        }
        this.l.requestConnectionPriority(1);
        waitFor(1000L);
        if (i >= 26) {
            this.l.setPreferredPhy(2, 2, 0);
        }
        waitFor(1000L);
    }

    public final void n0(BluetoothGatt bluetoothGatt, int i) {
        if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
            Log.e("sifli-DFU", "no Permission update mtu");
            this.H = 61;
            synchronized (this.M) {
                this.M.notifyAll();
            }
            return;
        }
        bluetoothGatt.requestMtu(i);
    }

    public final void o0(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (Build.VERSION.SDK_INT >= 31 && ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
            Log.e("sifli-DFU", "no Permission");
            this.H = 61;
            synchronized (this.M) {
                this.M.notifyAll();
            }
            return;
        }
        bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"));
        if (descriptor == null) {
            this.H = 68;
            Log.e("sifli-DFU", "desc null!!!");
            return;
        }
        this.A = false;
        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        Log.d("sifli-DFU", "Write descriptor");
        this.N.postDelayed(this.U, 30000L);
        bluetoothGatt.writeDescriptor(descriptor);
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.d("sifli-DFU", "onCreate()");
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        M();
        Log.i("sifli-DFU", "onDestroy");
    }

    @Override // android.app.IntentService
    public void onHandleIntent(Intent intent) {
        Log.i("sifli-DFU", "start with " + this.h);
        if (intent != null) {
            String action = intent.getAction();
            if ("com.sifli.siflidfu.action.NOR_V1".equals(action)) {
                int F = F(intent.getStringExtra("com.sifli.siflidfu.extra.ADDRESS"), intent.getParcelableArrayListExtra("com.sifli.siflidfu.extra.IMAGES"), intent.getIntExtra("com.sifli.siflidfu.extra.PARAM1", 0), intent.getIntExtra("com.sifli.siflidfu.extra.PARAM2", 0));
                try {
                    Thread.sleep(5000L);
                    Log.w("sifli-DFU", "nor ota end with " + F);
                    h0(5, "DFU end with " + F);
                    g0(100, F);
                    M();
                } catch (InterruptedException e2) {
                    throw new RuntimeException(e2);
                }
            } else if ("com.sifli.siflidfu.action.NOR_V2".equals(action)) {
                int G = G(intent.getStringExtra("com.sifli.siflidfu.extra.ADDRESS"), intent.getParcelableArrayListExtra("com.sifli.siflidfu.extra.IMAGES"), intent.getIntExtra("com.sifli.siflidfu.extra.PARAM1", 0), intent.getIntExtra("com.sifli.siflidfu.extra.PARAM2", 0));
                try {
                    Thread.sleep(5000L);
                    Log.w("sifli-DFU", "end with " + G);
                    h0(5, "DFU end with " + G);
                    g0(100, G);
                    M();
                } catch (InterruptedException e3) {
                    throw new RuntimeException(e3);
                }
            } else if ("com.sifli.siflidfu.action.NAND".equals(action)) {
                int E = E(intent.getStringExtra("com.sifli.siflidfu.extra.ADDRESS"), intent.getParcelableArrayListExtra("com.sifli.siflidfu.extra.IMAGES"), intent.getIntExtra("com.sifli.siflidfu.extra.PARAM1", 0), intent.getIntExtra("com.sifli.siflidfu.extra.PARAM2", 0));
                Log.w("sifli-DFU", "end with " + E);
                h0(5, "DFU end with " + E);
                g0(100, E);
                M();
            }
        }
    }

    @Override // android.app.IntentService, android.app.Service
    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        Log.d("sifli-DFU", "onStart()");
    }

    public void refreshDeviceCache(BluetoothGatt bluetoothGatt) {
        try {
            boolean booleanValue = ((Boolean) bluetoothGatt.getClass().getMethod("refresh", new Class[0]).invoke(bluetoothGatt, new Object[0])).booleanValue();
            Log.i("sifli-DFU", "Refreshing result: " + booleanValue);
        } catch (Exception e2) {
            Log.e("sifli-DFU", "An exception occurred while refreshing device", e2);
            h0(15, "Refreshing failed");
        }
    }

    public final BluetoothGatt v(BluetoothDevice bluetoothDevice, BluetoothGattCallback bluetoothGattCallback) {
        BluetoothGatt connectGatt;
        if (this.m.isEnabled()) {
            this.i = 1;
            Log.i("sifli-DFU", "connecting to " + bluetoothDevice);
            int i = Build.VERSION.SDK_INT;
            if (i >= 31 && ContextCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                Log.e("sifli-DFU", "no Permission");
                this.H = 61;
                synchronized (this.M) {
                    this.M.notifyAll();
                }
                return null;
            }
            if (i >= 26) {
                h0(0, "gatt = device.connectGatt(autoConnect = false, TRANSPORT_LE, preferredPhy = LE_1M|LE_2M)");
                connectGatt = bluetoothDevice.connectGatt(this, false, bluetoothGattCallback, 2, 3);
            } else if (i >= 23) {
                h0(0, "gatt = device.connectGatt(autoConnect = false, TRANSPORT_LE)");
                connectGatt = bluetoothDevice.connectGatt(this, false, bluetoothGattCallback, 2);
            } else {
                h0(0, "gatt = device.connectGatt(autoConnect = false)");
                connectGatt = bluetoothDevice.connectGatt(this, false, bluetoothGattCallback);
            }
            this.N.postDelayed(this.T, Constants.ONE_MIN_IN_MILLIS);
            try {
                synchronized (this.M) {
                    while (true) {
                        int i2 = this.i;
                        if ((i2 == 1 || i2 == 2) && this.H == 0) {
                            this.M.wait();
                        }
                    }
                }
            } catch (InterruptedException unused) {
                Log.e("sifli-DFU", "Sleeping interrupted");
            }
            this.N.removeCallbacks(this.T);
            if (this.H == 62) {
                return null;
            }
            return connectGatt;
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0094, code lost:
        android.util.Log.d("sifli-DFU", "retry success");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean w(java.lang.String r14, android.bluetooth.BluetoothGattCallback r15) {
        /*
            Method dump skipped, instructions count: 418
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sifli.siflidfu.SifliDFUService.w(java.lang.String, android.bluetooth.BluetoothGattCallback):boolean");
    }

    public void waitFor(long j) {
        synchronized (this.M) {
            try {
                h0(0, "wait(" + j + ")");
                this.M.wait(j);
            } catch (InterruptedException unused) {
                Log.e("sifli-DFU", "Sleeping interrupted");
            }
        }
    }

    public final int x(ArrayList<OTAFile> arrayList, int i) {
        int fileLength;
        Iterator<OTAFile> it = arrayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            OTAFile next = it.next();
            if (next.getImageID() != -1) {
                if (next.getFileLength() % i == 0) {
                    fileLength = next.getFileLength() / i;
                } else {
                    fileLength = (next.getFileLength() / i) + 1;
                }
                i2 += fileLength;
            }
        }
        Log.d("sifli-DFU", "all file len " + i2);
        return i2;
    }

    public final BroadcastReceiver y() {
        return new f();
    }

    public final BluetoothGattCallback z() {
        return new a();
    }
}
