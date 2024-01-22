package com.polidea.rxandroidble2.internal.scan;

import android.annotation.SuppressLint;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.scan.ScanSettings;
import java.util.ArrayList;
import java.util.List;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes12.dex */
public class AndroidScanObjectsConverter {

    /* renamed from: a  reason: collision with root package name */
    public final int f13460a;

    @Inject
    public AndroidScanObjectsConverter(@Named("device-sdk") int i) {
        this.f13460a = i;
    }

    @RequiresApi(23)
    public static void a(ScanSettings scanSettings, ScanSettings.Builder builder) {
        builder.setCallbackType(scanSettings.getCallbackType()).setMatchMode(scanSettings.getMatchMode()).setNumOfMatches(scanSettings.getNumOfMatches());
    }

    @RequiresApi(21)
    public static ScanFilter b(com.polidea.rxandroidble2.scan.ScanFilter scanFilter) {
        ScanFilter.Builder builder = new ScanFilter.Builder();
        if (scanFilter.getServiceDataUuid() != null) {
            builder.setServiceData(scanFilter.getServiceDataUuid(), scanFilter.getServiceData(), scanFilter.getServiceDataMask());
        }
        if (scanFilter.getDeviceAddress() != null) {
            builder.setDeviceAddress(scanFilter.getDeviceAddress());
        }
        return builder.setDeviceName(scanFilter.getDeviceName()).setManufacturerData(scanFilter.getManufacturerId(), scanFilter.getManufacturerData(), scanFilter.getManufacturerDataMask()).setServiceUuid(scanFilter.getServiceUuid(), scanFilter.getServiceUuidMask()).build();
    }

    @Nullable
    @RequiresApi(21)
    public List<ScanFilter> toNativeFilters(com.polidea.rxandroidble2.scan.ScanFilter... scanFilterArr) {
        if (scanFilterArr != null && scanFilterArr.length > 0) {
            ArrayList arrayList = new ArrayList(scanFilterArr.length);
            for (com.polidea.rxandroidble2.scan.ScanFilter scanFilter : scanFilterArr) {
                arrayList.add(b(scanFilter));
            }
            return arrayList;
        }
        return null;
    }

    @RequiresApi(21)
    @SuppressLint({"NewApi"})
    public android.bluetooth.le.ScanSettings toNativeSettings(com.polidea.rxandroidble2.scan.ScanSettings scanSettings) {
        ScanSettings.Builder builder = new ScanSettings.Builder();
        if (this.f13460a >= 23) {
            a(scanSettings, builder);
            if (this.f13460a >= 26) {
                builder.setLegacy(scanSettings.getLegacy());
            }
        }
        return builder.setReportDelay(scanSettings.getReportDelayMillis()).setScanMode(scanSettings.getScanMode()).build();
    }
}
