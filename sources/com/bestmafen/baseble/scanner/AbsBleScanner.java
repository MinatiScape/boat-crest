package com.bestmafen.baseble.scanner;

import android.bluetooth.BluetoothAdapter;
import android.os.Handler;
import android.os.Looper;
import com.bestmafen.baseble.util.BleLog;
import java.util.UUID;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public abstract class AbsBleScanner {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int DEFAULT_DURATION = 10;
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final UUID[] f2223a;
    @NotNull
    public ScanMode b;
    @NotNull
    public final BluetoothAdapter c;
    @NotNull
    public final Handler d;
    public volatile boolean e;
    @Nullable
    public BleScanCallback f;
    @Nullable
    public BleScanFilter g;
    public int h;
    @NotNull
    public final Runnable i;

    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public AbsBleScanner() {
        this(null, null, 3, null);
    }

    public AbsBleScanner(@Nullable UUID[] uuidArr, @NotNull ScanMode mScanMode) {
        Intrinsics.checkNotNullParameter(mScanMode, "mScanMode");
        this.f2223a = uuidArr;
        this.b = mScanMode;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        Intrinsics.checkNotNullExpressionValue(defaultAdapter, "getDefaultAdapter()");
        this.c = defaultAdapter;
        this.d = new Handler(Looper.getMainLooper());
        this.h = 10;
        this.i = new Runnable() { // from class: com.bestmafen.baseble.scanner.a
            @Override // java.lang.Runnable
            public final void run() {
                AbsBleScanner.b(AbsBleScanner.this);
            }
        };
    }

    public static final void b(AbsBleScanner this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.scan(false);
    }

    public void exit() {
        scan(false);
        this.f = null;
    }

    @Nullable
    public final BleScanCallback getMBleScanCallback() {
        return this.f;
    }

    @NotNull
    public final BluetoothAdapter getMBluetoothAdapter() {
        return this.c;
    }

    @NotNull
    public final Handler getMHandler() {
        return this.d;
    }

    @Nullable
    public final BleScanFilter getMScanFilter() {
        return this.g;
    }

    @NotNull
    public final ScanMode getMScanMode() {
        return this.b;
    }

    @Nullable
    public final UUID[] getMServiceUuids() {
        return this.f2223a;
    }

    public final boolean isScanning() {
        return this.e;
    }

    public final void removeStop() {
        BleLog.INSTANCE.d("AbsBleScanner -> removeStop");
        this.d.removeCallbacks(this.i);
    }

    public abstract void scan(boolean z);

    @NotNull
    public final AbsBleScanner setBleScanCallback(@NotNull BleScanCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.f = callback;
        return this;
    }

    public final void setMBleScanCallback(@Nullable BleScanCallback bleScanCallback) {
        this.f = bleScanCallback;
    }

    public final void setMScanFilter(@Nullable BleScanFilter bleScanFilter) {
        this.g = bleScanFilter;
    }

    public final void setMScanMode(@NotNull ScanMode scanMode) {
        Intrinsics.checkNotNullParameter(scanMode, "<set-?>");
        this.b = scanMode;
    }

    @NotNull
    public final AbsBleScanner setScanDuration(int i) {
        this.h = i;
        return this;
    }

    @NotNull
    public final AbsBleScanner setScanFilter(@NotNull BleScanFilter filter) {
        Intrinsics.checkNotNullParameter(filter, "filter");
        this.g = filter;
        return this;
    }

    public final void setScanning(boolean z) {
        this.e = z;
    }

    public final void stopScanDelay() {
        BleLog.INSTANCE.d("AbsBleScanner -> stopScanDelay");
        this.d.postDelayed(this.i, this.h * 1000);
    }

    public /* synthetic */ AbsBleScanner(UUID[] uuidArr, ScanMode scanMode, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : uuidArr, (i & 2) != 0 ? ScanMode.BALANCED : scanMode);
    }
}
