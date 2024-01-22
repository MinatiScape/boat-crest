package com.polidea.rxandroidble2.internal.scan;

import android.bluetooth.le.ScanResult;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import com.polidea.rxandroidble2.scan.IsConnectable;
@RequiresApi(26)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes12.dex */
public class IsConnectableCheckerApi26 implements IsConnectableChecker {
    @Override // com.polidea.rxandroidble2.internal.scan.IsConnectableChecker
    public IsConnectable check(ScanResult scanResult) {
        return scanResult.isConnectable() ? IsConnectable.CONNECTABLE : IsConnectable.NOT_CONNECTABLE;
    }
}
