package com.realsil.sdk.core.c;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.bluetooth.scanner.LeScannerPresenter;
import com.realsil.sdk.core.bluetooth.scanner.ScannerParams;
import com.realsil.sdk.core.logger.ZLogger;
/* loaded from: classes12.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    public boolean f13583a;
    public boolean b;
    public BluetoothAdapter c;
    public boolean d;
    public ScannerParams e;
    public InterfaceC0718a f;

    /* renamed from: com.realsil.sdk.core.c.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public interface InterfaceC0718a {
    }

    public a(Context context) {
        this.f13583a = false;
        this.b = false;
        this.f13583a = RtkCore.DEBUG;
        this.b = RtkCore.VDBG;
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
        this.c = bluetoothManager != null ? bluetoothManager.getAdapter() : null;
    }

    public boolean a(ScannerParams scannerParams) {
        BluetoothAdapter bluetoothAdapter = this.c;
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            ZLogger.v(this.b, "LeScanner--startScan");
            InterfaceC0718a interfaceC0718a = this.f;
            if (interfaceC0718a != null) {
                LeScannerPresenter.a aVar = (LeScannerPresenter.a) interfaceC0718a;
            } else {
                ZLogger.v(this.b, "no listeners register");
            }
            this.d = true;
            this.e = scannerParams;
            return true;
        }
        ZLogger.w("BT Adapter is not turned ON");
        return false;
    }

    public boolean a() {
        InterfaceC0718a interfaceC0718a = this.f;
        if (interfaceC0718a != null) {
            LeScannerPresenter.a aVar = (LeScannerPresenter.a) interfaceC0718a;
            ZLogger.v(LeScannerPresenter.this.b, "onLeScanStop");
            LeScannerPresenter.this.a(3);
        } else {
            ZLogger.v(this.b, "no listeners register");
        }
        this.d = false;
        return true;
    }
}
