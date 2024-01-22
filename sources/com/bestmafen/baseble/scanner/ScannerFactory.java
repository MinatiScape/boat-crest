package com.bestmafen.baseble.scanner;

import android.os.Build;
import java.util.UUID;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class ScannerFactory {
    @NotNull
    public static final ScannerFactory INSTANCE = new ScannerFactory();

    public static /* synthetic */ AbsBleScanner newInstance$default(ScannerFactory scannerFactory, UUID[] uuidArr, ScanMode scanMode, int i, Object obj) {
        if ((i & 1) != 0) {
            uuidArr = null;
        }
        if ((i & 2) != 0) {
            scanMode = ScanMode.BALANCED;
        }
        return scannerFactory.newInstance(uuidArr, scanMode);
    }

    @NotNull
    public final AbsBleScanner newInstance(@Nullable UUID[] uuidArr, @NotNull ScanMode scanMode) {
        Intrinsics.checkNotNullParameter(scanMode, "scanMode");
        if (Build.VERSION.SDK_INT < 21) {
            return new BleScanner18(uuidArr);
        }
        return new BleScanner21(uuidArr, scanMode);
    }
}
