package com.bestmafen.baseble.scanner;

import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.os.ParcelUuid;
import androidx.annotation.RequiresApi;
import com.bestmafen.baseble.util.BleLog;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@RequiresApi(21)
/* loaded from: classes.dex */
public final class BleScanner21 extends AbsBleScanner {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public List<ScanFilter> j;
    @NotNull
    public final ScanSettings.Builder k;
    @Nullable
    public ScanCallback l;

    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ScanMode.values().length];
            try {
                iArr[ScanMode.LOW_POWER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ScanMode.BALANCED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ScanMode.LOW_LATENCY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public BleScanner21() {
        this(null, null, 3, null);
    }

    public /* synthetic */ BleScanner21(UUID[] uuidArr, ScanMode scanMode, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : uuidArr, (i & 2) != 0 ? ScanMode.BALANCED : scanMode);
    }

    public static final void f(BleScanner21 this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BleScanCallback mBleScanCallback = this$0.getMBleScanCallback();
        if (mBleScanCallback != null) {
            mBleScanCallback.onBluetoothDisabled();
        }
    }

    public static final void g(BleScanner21 this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BleScanCallback mBleScanCallback = this$0.getMBleScanCallback();
        if (mBleScanCallback != null) {
            mBleScanCallback.onScan(this$0.isScanning());
        }
    }

    public final ScanSettings e() {
        ScanSettings.Builder builder = this.k;
        int i = WhenMappings.$EnumSwitchMapping$0[getMScanMode().ordinal()];
        if (i == 1) {
            builder.setScanMode(0);
        } else if (i == 2) {
            builder.setScanMode(1);
        } else if (i == 3) {
            builder.setScanMode(2);
        }
        return builder.build();
    }

    @Override // com.bestmafen.baseble.scanner.AbsBleScanner
    public void exit() {
        super.exit();
        this.l = null;
    }

    @Override // com.bestmafen.baseble.scanner.AbsBleScanner
    public synchronized void scan(boolean z) {
        if (getMBleScanCallback() != null) {
            BleLog bleLog = BleLog.INSTANCE;
            bleLog.d("BleScanner21 scan " + z + " -> isScanning=" + isScanning() + " , scanMode=" + e().getScanMode());
            if (isScanning() == z) {
                return;
            }
            BluetoothLeScanner bluetoothLeScanner = getMBluetoothAdapter().getBluetoothLeScanner();
            if (z) {
                if (getMBluetoothAdapter().isEnabled() && bluetoothLeScanner != null) {
                    bluetoothLeScanner.startScan(this.j, e(), this.l);
                    stopScanDelay();
                }
                getMHandler().post(new Runnable() { // from class: com.bestmafen.baseble.scanner.f
                    @Override // java.lang.Runnable
                    public final void run() {
                        BleScanner21.f(BleScanner21.this);
                    }
                });
                return;
            }
            if (bluetoothLeScanner != null) {
                bluetoothLeScanner.stopScan(this.l);
            }
            removeStop();
            setScanning(z);
            getMHandler().post(new Runnable() { // from class: com.bestmafen.baseble.scanner.e
                @Override // java.lang.Runnable
                public final void run() {
                    BleScanner21.g(BleScanner21.this);
                }
            });
            return;
        }
        throw new IllegalArgumentException("BleScanCallback cannot be null".toString());
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BleScanner21(@Nullable UUID[] uuidArr, @NotNull ScanMode scanMode) {
        super(uuidArr, scanMode);
        UUID[] mServiceUuids;
        Intrinsics.checkNotNullParameter(scanMode, "scanMode");
        this.k = new ScanSettings.Builder();
        this.l = new BleScanner21$mScanCallback$1(this);
        if (getMServiceUuids() != null) {
            this.j = new ArrayList();
            for (UUID uuid : getMServiceUuids()) {
                List<ScanFilter> list = this.j;
                if (list != null) {
                    ScanFilter build = new ScanFilter.Builder().setServiceUuid(new ParcelUuid(uuid)).build();
                    Intrinsics.checkNotNullExpressionValue(build, "Builder().setServiceUuid(ParcelUuid(uuid)).build()");
                    list.add(build);
                }
            }
        }
    }
}
