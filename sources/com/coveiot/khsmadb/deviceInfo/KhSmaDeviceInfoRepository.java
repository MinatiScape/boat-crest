package com.coveiot.khsmadb.deviceInfo;

import android.content.Context;
import com.coveiot.khsmadb.KhSmaAppDatabase;
import com.coveiot.utils.SingletonHolder;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class KhSmaDeviceInfoRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7144a;
    @NotNull
    public KhSmaDeviceInfoDao b;

    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KhSmaDeviceInfoRepository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KhSmaDeviceInfoRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KhSmaDeviceInfoRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KhSmaDeviceInfoRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KhSmaDeviceInfoRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KhSmaDeviceInfoRepository(Context context) {
        this.f7144a = context;
        this.b = KhSmaAppDatabase.Companion.getDatabase(context).getDeviceInfoDao();
    }

    public /* synthetic */ KhSmaDeviceInfoRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final Context getContext() {
        return this.f7144a;
    }

    @Nullable
    public final KhSmaDeviceInfo getDeviceInfo(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getDeviceInfo(macAddress);
    }

    public final void insertOrUpdateDeviceInfo(@NotNull KhSmaDeviceInfo deviceInfo) {
        Intrinsics.checkNotNullParameter(deviceInfo, "deviceInfo");
        this.b.insert(deviceInfo);
    }

    public final void updateBloodPressureLastSyncTime(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateBloodPressureLastSyncTime(macAddress, j);
    }

    public final void updateHeartRateLastSyncTime(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateHeartRateLastSyncTime(macAddress, j);
    }

    public final void updateSleepLastSyncTime(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateSleepLastSyncTime(macAddress, j);
    }

    public final void updateSpO2LastSyncTime(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateSpO2LastSyncTime(macAddress, j);
    }

    public final void updateStepLastSyncTime(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateStepLastSyncTime(macAddress, j);
    }

    public final void updateTemperatureLastSyncTime(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateTemperatureLastSyncTime(macAddress, j);
    }
}
