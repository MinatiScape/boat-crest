package com.polidea.rxandroidble2.scan;

import android.app.PendingIntent;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.util.List;
@RequiresApi(26)
/* loaded from: classes12.dex */
public interface BackgroundScanner {
    List<ScanResult> onScanResultReceived(@NonNull Intent intent);

    @RequiresApi(26)
    void scanBleDeviceInBackground(@NonNull PendingIntent pendingIntent, ScanSettings scanSettings, ScanFilter... scanFilterArr);

    @RequiresApi(26)
    void stopBackgroundBleScan(@NonNull PendingIntent pendingIntent);
}
