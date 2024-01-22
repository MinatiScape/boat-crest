package com.polidea.rxandroidble2.internal.scan;

import androidx.annotation.Nullable;
import java.util.Arrays;
/* loaded from: classes12.dex */
public class EmulatedScanFilterMatcher {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final ScanFilterInterface[] f13464a;
    public final boolean b;

    public EmulatedScanFilterMatcher(@Nullable ScanFilterInterface... scanFilterInterfaceArr) {
        this.f13464a = scanFilterInterfaceArr;
        boolean z = false;
        if (scanFilterInterfaceArr != null && scanFilterInterfaceArr.length != 0) {
            for (ScanFilterInterface scanFilterInterface : scanFilterInterfaceArr) {
                if (!scanFilterInterface.isAllFieldsEmpty()) {
                    break;
                }
            }
        }
        z = true;
        this.b = z;
    }

    public boolean isEmpty() {
        return this.b;
    }

    public boolean matches(RxBleInternalScanResult rxBleInternalScanResult) {
        ScanFilterInterface[] scanFilterInterfaceArr = this.f13464a;
        if (scanFilterInterfaceArr == null || scanFilterInterfaceArr.length == 0) {
            return true;
        }
        for (ScanFilterInterface scanFilterInterface : scanFilterInterfaceArr) {
            if (scanFilterInterface.matches(rxBleInternalScanResult)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "emulatedFilters=" + Arrays.toString(this.f13464a);
    }
}
