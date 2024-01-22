package com.coveiot.khsmadb.heartrate;

import android.content.Context;
import com.coveiot.khsmadb.KhSmaAppDatabase;
import com.coveiot.utils.SingletonHolder;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class KhHeartRateRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7147a;
    @NotNull
    public KhHeartRateDao b;

    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KhHeartRateRepository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KhHeartRateRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KhHeartRateRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KhHeartRateRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KhHeartRateRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KhHeartRateRepository(Context context) {
        this.f7147a = context;
        this.b = KhSmaAppDatabase.Companion.getDatabase(context).getHeartRateDao();
    }

    public /* synthetic */ KhHeartRateRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<KhBleHeartRate> getAllHeartRateData(@NotNull String macAddress, boolean z) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllHeartRateData(macAddress, z);
    }

    @NotNull
    public final Context getContext() {
        return this.f7147a;
    }

    @Nullable
    public final List<KhBleHeartRate> getHeartRateDataFor(@NotNull String macAddress, @NotNull String date) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(date, "date");
        return this.b.getHeartRateDataFor(macAddress, date);
    }

    @Nullable
    public final List<KhBleHeartRate> getHeartRateDataListBetweenTime(@NotNull String macAddress, int i, int i2) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getHeartRateDataListBetweenTime(macAddress, i, i2);
    }

    @Nullable
    public final List<String> getUniqueDateForHeartRate(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getUniqueDatesForHeartRate(macAddress);
    }

    public final void insertHeartRateData(@NotNull List<KhBleHeartRate> heartRateList) {
        Intrinsics.checkNotNullParameter(heartRateList, "heartRateList");
        this.b.insertAll(heartRateList);
    }

    public final void updateHeartRateDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateHeartRateDataProcessedBefore(macAddress, j);
    }

    public final void updateWorkoutHeartRateDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateWorkoutHeartRateDataProcessedBefore(macAddress, j);
    }
}
