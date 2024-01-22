package com.polidea.rxandroidble2.internal.scan;

import com.polidea.rxandroidble2.internal.scan.ExternalScanSettingsExtension;
/* loaded from: classes12.dex */
public interface ExternalScanSettingsExtension<R extends ExternalScanSettingsExtension<R>> {

    /* loaded from: classes12.dex */
    public interface Builder<T extends Builder<T>> {
        T setShouldCheckLocationServicesState(boolean z);
    }

    R copyWithCallbackType(int i);

    boolean shouldCheckLocationProviderState();
}
