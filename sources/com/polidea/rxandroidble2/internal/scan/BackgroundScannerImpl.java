package com.polidea.rxandroidble2.internal.scan;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.bluetooth.le.ScanResult;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.exceptions.BleScanException;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
import com.polidea.rxandroidble2.scan.BackgroundScanner;
import com.polidea.rxandroidble2.scan.ScanFilter;
import com.polidea.rxandroidble2.scan.ScanSettings;
import java.util.ArrayList;
import java.util.List;
@TargetApi(26)
/* loaded from: classes12.dex */
public class BackgroundScannerImpl implements BackgroundScanner {

    /* renamed from: a  reason: collision with root package name */
    public final RxBleAdapterWrapper f13462a;
    public final AndroidScanObjectsConverter b;
    public final InternalScanResultCreator c;
    public final InternalToExternalScanResultConverter d;

    @Inject
    public BackgroundScannerImpl(RxBleAdapterWrapper rxBleAdapterWrapper, AndroidScanObjectsConverter androidScanObjectsConverter, InternalScanResultCreator internalScanResultCreator, InternalToExternalScanResultConverter internalToExternalScanResultConverter) {
        this.f13462a = rxBleAdapterWrapper;
        this.b = androidScanObjectsConverter;
        this.c = internalScanResultCreator;
        this.d = internalToExternalScanResultConverter;
    }

    public static List<ScanResult> b(@NonNull Intent intent) {
        return (List) intent.getSerializableExtra("android.bluetooth.le.extra.LIST_SCAN_RESULT");
    }

    public final com.polidea.rxandroidble2.scan.ScanResult a(int i, ScanResult scanResult) {
        return this.d.apply(this.c.create(i, scanResult));
    }

    @Override // com.polidea.rxandroidble2.scan.BackgroundScanner
    public List<com.polidea.rxandroidble2.scan.ScanResult> onScanResultReceived(@NonNull Intent intent) {
        int intExtra = intent.getIntExtra("android.bluetooth.le.extra.CALLBACK_TYPE", -1);
        int intExtra2 = intent.getIntExtra("android.bluetooth.le.extra.ERROR_CODE", 0);
        List<ScanResult> b = b(intent);
        ArrayList arrayList = new ArrayList();
        if (intExtra2 == 0) {
            for (ScanResult scanResult : b) {
                arrayList.add(a(intExtra, scanResult));
            }
            return arrayList;
        }
        throw new BleScanException(intExtra2);
    }

    @Override // com.polidea.rxandroidble2.scan.BackgroundScanner
    @RequiresApi(26)
    public void scanBleDeviceInBackground(@NonNull PendingIntent pendingIntent, ScanSettings scanSettings, ScanFilter... scanFilterArr) {
        if (Build.VERSION.SDK_INT < 26) {
            RxBleLog.w("PendingIntent based scanning is available for Android O and higher only.", new Object[0]);
        } else if (this.f13462a.isBluetoothEnabled()) {
            RxBleLog.i("Requesting pending intent based scan.", new Object[0]);
            int startLeScan = this.f13462a.startLeScan(this.b.toNativeFilters(scanFilterArr), this.b.toNativeSettings(scanSettings), pendingIntent);
            if (startLeScan == 0) {
                return;
            }
            BleScanException bleScanException = new BleScanException(startLeScan);
            RxBleLog.w(bleScanException, "Failed to start scan", new Object[0]);
            throw bleScanException;
        } else {
            RxBleLog.w("PendingIntent based scanning is available only when Bluetooth is ON.", new Object[0]);
            throw new BleScanException(1);
        }
    }

    @Override // com.polidea.rxandroidble2.scan.BackgroundScanner
    @RequiresApi(26)
    public void stopBackgroundBleScan(@NonNull PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT < 26) {
            RxBleLog.w("PendingIntent based scanning is available for Android O and higher only.", new Object[0]);
        } else if (!this.f13462a.isBluetoothEnabled()) {
            RxBleLog.w("PendingIntent based scanning is available only when Bluetooth is ON.", new Object[0]);
        } else {
            RxBleLog.i("Stopping pending intent based scan.", new Object[0]);
            this.f13462a.stopLeScan(pendingIntent);
        }
    }
}
