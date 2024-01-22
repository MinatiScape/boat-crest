package com.bestmafen.baseble.scanner;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public interface BleScanFilter {
    boolean match(@NotNull BleDevice bleDevice);
}
