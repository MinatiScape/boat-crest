package com.coveiot.khsmadb.activity;

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
public final class KhActivityRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7137a;
    @NotNull
    public KhActivityDao b;

    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KhActivityRepository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KhActivityRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KhActivityRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KhActivityRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KhActivityRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KhActivityRepository(Context context) {
        this.f7137a = context;
        this.b = KhSmaAppDatabase.Companion.getDatabase(context).getActivityDao();
    }

    public /* synthetic */ KhActivityRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final void deleteActivityInfoForDate(@NotNull String macAddress, @NotNull String mDate) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(mDate, "mDate");
        this.b.deleteActivityInfoForDate(macAddress, mDate);
    }

    public final void deleteAllActivityInfo(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.deleteAllActivityInfo(macAddress);
    }

    public final void deleteAllActivityInfoFor(@NotNull String macAddress, int i) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.deleteAllActivityInfoFor(macAddress, i);
    }

    public final void deleteAllProcessedActivityInfo(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.deleteAllProcessedActivityInfo(macAddress);
    }

    public final void deleteAllProcessedWorkoutActivityInfo(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.deleteAllProcessedWorkoutActivityInfo(macAddress);
    }

    @Nullable
    public final List<KhBleActivity> getActivityInfo(@NotNull String macAddress, boolean z) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getActivityInfo(macAddress, z);
    }

    @Nullable
    public final List<KhBleActivity> getActivityListBetweenTime(@NotNull String macAddress, int i, int i2) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getActivityListBetweenTime(macAddress, i, i2);
    }

    @NotNull
    public final Context getContext() {
        return this.f7137a;
    }

    @Nullable
    public final Integer getMaxStepRecordByDate(@NotNull String macAddress, @NotNull String date) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(date, "date");
        return this.b.getMaxStepRecordByDate(macAddress, date);
    }

    @Nullable
    public final List<KhBleActivity> getOrderedActivityListBetweenTime(@NotNull String macAddress, int i, int i2) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getOrderedActivityListBetweenTime(macAddress, i, i2);
    }

    @Nullable
    public final List<KhBleActivity> getUniqueActivityData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getUniqueDayInfo(macAddress);
    }

    @Nullable
    public final List<KhBleActivity> getWorkoutActivityInfo(@NotNull String macAddress, boolean z) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getWorkoutActivityInfo(macAddress, z);
    }

    public final void insertActivityData(@NotNull List<KhBleActivity> activityList) {
        Intrinsics.checkNotNullParameter(activityList, "activityList");
        this.b.insertAll(activityList);
    }

    public final void setWorkoutReadyToProcessBetweenTime(@NotNull String macAddress, int i, int i2) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.setWorkoutReadyToProcessBetweenTime(macAddress, i, i2);
    }

    public final void updateActivityDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateActivityDataProcessedBefore(macAddress, j);
    }

    public final void updateActivityDataUnProcessedAfter(@NotNull String macAddress, int i) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateActivityDataUnProcessedAfter(macAddress, i);
    }

    public final void updateWorkoutActivityDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateWorkoutActivityDataProcessedBefore(macAddress, j);
    }
}
