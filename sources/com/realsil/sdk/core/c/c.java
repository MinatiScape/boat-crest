package com.realsil.sdk.core.c;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.ParcelUuid;
import com.realsil.sdk.core.bluetooth.scanner.LeScannerPresenter;
import com.realsil.sdk.core.bluetooth.scanner.ScannerParams;
import com.realsil.sdk.core.bluetooth.scanner.compat.CompatScanFilter;
import com.realsil.sdk.core.c.a;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@TargetApi(19)
/* loaded from: classes12.dex */
public class c extends com.realsil.sdk.core.c.a {
    public BluetoothAdapter.LeScanCallback g;

    /* loaded from: classes12.dex */
    public class a implements BluetoothAdapter.LeScanCallback {
        public a() {
        }

        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            c cVar = c.this;
            a.InterfaceC0718a interfaceC0718a = cVar.f;
            if (interfaceC0718a != null) {
                LeScannerPresenter.this.a(bluetoothDevice, i, bArr);
            } else {
                ZLogger.v(cVar.b, "no listeners register");
            }
        }
    }

    public c(Context context) {
        super(context);
        this.g = new a();
        ZLogger.v(this.b, "LeScannerV19 init");
    }

    @Override // com.realsil.sdk.core.c.a
    public boolean a(ScannerParams scannerParams) {
        if (super.a(scannerParams)) {
            UUID[] uuidArr = null;
            List<CompatScanFilter> scanFilters = scannerParams.getScanFilters();
            if (scanFilters != null && scanFilters.size() > 0) {
                ZLogger.v(this.b, "contains " + scanFilters.size() + " filters");
                ArrayList arrayList = new ArrayList();
                for (CompatScanFilter compatScanFilter : scanFilters) {
                    ZLogger.v(compatScanFilter.toString());
                    if (compatScanFilter.getServiceUuid() != null) {
                        arrayList.add(compatScanFilter.getServiceUuid());
                    }
                }
                int size = arrayList.size();
                if (size > 0) {
                    uuidArr = new UUID[size];
                    for (int i = 0; i < size; i++) {
                        if (arrayList.get(i) != null) {
                            uuidArr[i] = ((ParcelUuid) arrayList.get(i)).getUuid();
                        }
                    }
                }
            }
            try {
                return this.c.startLeScan(uuidArr, this.g);
            } catch (Exception e) {
                ZLogger.w(e.toString());
                return false;
            }
        }
        return false;
    }

    @Override // com.realsil.sdk.core.c.a
    public boolean a() {
        super.a();
        BluetoothAdapter bluetoothAdapter = this.c;
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            try {
                this.c.stopLeScan(this.g);
                return true;
            } catch (Exception e) {
                ZLogger.w(e.toString());
                return false;
            }
        }
        ZLogger.w("BT Adapter is not turned ON");
        return false;
    }
}
