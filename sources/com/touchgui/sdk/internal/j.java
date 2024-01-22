package com.touchgui.sdk.internal;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.touchgui.sdk.TGLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public final class j {

    /* renamed from: a  reason: collision with root package name */
    public final Context f13780a;
    public BluetoothAdapter b;
    public i c = null;
    public final Handler d = new Handler(Looper.getMainLooper());
    public final AtomicBoolean e = new AtomicBoolean(false);
    public final CopyOnWriteArrayList f = new CopyOnWriteArrayList();
    public final Runnable g = new Runnable() { // from class: com.touchgui.sdk.internal.lc
        @Override // java.lang.Runnable
        public final void run() {
            j.this.d();
        }
    };

    public j(Context context) {
        this.f13780a = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d() {
        a(true);
    }

    public final boolean a() {
        BluetoothAdapter bluetoothAdapter;
        b();
        return c8.a(this.f13780a) && (bluetoothAdapter = this.b) != null && bluetoothAdapter.enable();
    }

    public final boolean b() {
        if (this.b == null) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            this.b = defaultAdapter;
            if (defaultAdapter != null) {
                this.c = new i(this);
            }
        }
        return this.b != null;
    }

    public final boolean c() {
        b();
        BluetoothAdapter bluetoothAdapter = this.b;
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    public final Boolean a(boolean z) {
        b();
        if (this.b == null) {
            TGLogger.e("Stop scan: bluetoothAdapter is null");
            return Boolean.FALSE;
        } else if (c8.b(this.f13780a)) {
            if (!this.e.getAndSet(false)) {
                TGLogger.w("Stop scan: not scanning");
                return Boolean.TRUE;
            }
            TGLogger.d("Stop scan: notifyCompleted=" + z);
            this.d.removeCallbacks(this.g);
            BluetoothLeScanner bluetoothLeScanner = this.b.getBluetoothLeScanner();
            if (bluetoothLeScanner != null) {
                bluetoothLeScanner.stopScan(this.c);
            }
            if (z) {
                Iterator it = this.f.iterator();
                while (it.hasNext()) {
                    ((g8) it.next()).onScanFinished();
                }
            }
            return Boolean.valueOf(bluetoothLeScanner != null);
        } else {
            return Boolean.FALSE;
        }
    }

    public final boolean a(String str, int i, boolean z) {
        ScanSettings.Builder builder;
        b();
        BluetoothAdapter bluetoothAdapter = this.b;
        if (bluetoothAdapter == null) {
            TGLogger.e("Start scan: bluetoothAdapter is null");
            return false;
        } else if (!bluetoothAdapter.isEnabled()) {
            TGLogger.w("Start scan: bluetooth is not turn on");
            return false;
        } else if (c8.b(this.f13780a)) {
            if (this.e.getAndSet(true)) {
                TGLogger.d("It's scanning");
                return true;
            }
            TGLogger.d("Start scan: timeout=" + i + ", screenOn=" + z);
            BluetoothLeScanner bluetoothLeScanner = this.b.getBluetoothLeScanner();
            if (bluetoothLeScanner != null) {
                if (i <= 0) {
                    i = 10;
                }
                this.d.postDelayed(this.g, i * 1000);
                ArrayList arrayList = null;
                if (TextUtils.isEmpty(str)) {
                    builder = null;
                } else {
                    arrayList = new ArrayList();
                    arrayList.add(new ScanFilter.Builder().setDeviceAddress(str).build());
                    builder = new ScanSettings.Builder();
                    if (!z) {
                        builder.setScanMode(2);
                    }
                }
                if (arrayList != null) {
                    bluetoothLeScanner.startScan(arrayList, builder.build(), this.c);
                } else {
                    bluetoothLeScanner.startScan(this.c);
                }
            } else {
                this.e.set(false);
            }
            return bluetoothLeScanner != null;
        } else {
            return false;
        }
    }
}
