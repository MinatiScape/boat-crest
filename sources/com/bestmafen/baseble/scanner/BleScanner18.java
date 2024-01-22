package com.bestmafen.baseble.scanner;

import android.bluetooth.BluetoothAdapter;
import com.bestmafen.baseble.util.BleLog;
import java.util.UUID;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class BleScanner18 extends AbsBleScanner {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public BluetoothAdapter.LeScanCallback j;

    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleScanner18() {
        this(null, 1, null);
    }

    public /* synthetic */ BleScanner18(UUID[] uuidArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : uuidArr);
    }

    public static final void e(BleScanner18 this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BleScanCallback mBleScanCallback = this$0.getMBleScanCallback();
        if (mBleScanCallback != null) {
            mBleScanCallback.onBluetoothDisabled();
        }
    }

    public static final void f(BleScanner18 this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BleScanCallback mBleScanCallback = this$0.getMBleScanCallback();
        if (mBleScanCallback != null) {
            mBleScanCallback.onScan(this$0.isScanning());
        }
    }

    @Override // com.bestmafen.baseble.scanner.AbsBleScanner
    public void exit() {
        super.exit();
        this.j = null;
    }

    @Override // com.bestmafen.baseble.scanner.AbsBleScanner
    public synchronized void scan(boolean z) {
        if (getMBleScanCallback() != null) {
            BleLog bleLog = BleLog.INSTANCE;
            bleLog.d("BleScanner18 scan " + z + " -> isScanning=" + isScanning());
            if (isScanning() == z) {
                return;
            }
            if (z) {
                if (!getMBluetoothAdapter().isEnabled()) {
                    getMHandler().post(new Runnable() { // from class: com.bestmafen.baseble.scanner.b
                        @Override // java.lang.Runnable
                        public final void run() {
                            BleScanner18.e(BleScanner18.this);
                        }
                    });
                    return;
                } else {
                    getMBluetoothAdapter().startLeScan(getMServiceUuids(), this.j);
                    stopScanDelay();
                }
            } else {
                getMBluetoothAdapter().stopLeScan(this.j);
                removeStop();
            }
            setScanning(z);
            getMHandler().post(new Runnable() { // from class: com.bestmafen.baseble.scanner.c
                @Override // java.lang.Runnable
                public final void run() {
                    BleScanner18.f(BleScanner18.this);
                }
            });
            return;
        }
        throw new IllegalArgumentException("BleScanCallback cannot be null".toString());
    }

    public BleScanner18(@Nullable UUID[] uuidArr) {
        super(uuidArr, null, 2, null);
        this.j = new BleScanner18$mLeScanCallback$1(this);
    }
}
