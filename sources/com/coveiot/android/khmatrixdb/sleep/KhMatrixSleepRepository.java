package com.coveiot.android.khmatrixdb.sleep;

import android.content.Context;
import com.coveiot.android.khmatrixdb.KhMatrixAppDatabase;
import com.coveiot.android.khmatrixdb.Utils;
import com.coveiot.utils.SingletonHolder;
import java.util.List;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class KhMatrixSleepRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4627a;
    @NotNull
    public KhMatrixSleepDataDao b;

    /* loaded from: classes3.dex */
    public static final class Companion extends SingletonHolder<KhMatrixSleepRepository, Context> {

        /* loaded from: classes3.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KhMatrixSleepRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KhMatrixSleepRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KhMatrixSleepRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KhMatrixSleepRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KhMatrixSleepRepository(Context context) {
        this.f4627a = context;
        this.b = KhMatrixAppDatabase.Companion.getDatabase(context).getSleepDao();
    }

    public /* synthetic */ KhMatrixSleepRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<KhMatrixSleepData> getAllSleepData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllSleepData(macAddress);
    }

    @Nullable
    public final List<KhMatrixSleepData> getAllUnProcessedSleepData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllUnProcessedSleepData(macAddress);
    }

    @Nullable
    public final List<KhMatrixSleepDetailData> getAllUnProcessedSleepDetailData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllUnProcessedSleepDetailData(macAddress);
    }

    @NotNull
    public final Context getContext() {
        return this.f4627a;
    }

    @NotNull
    public final KhMatrixSleepData getSleepDataByTime(long j, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getSleepDataByTime(j, macAddress);
    }

    @Nullable
    public final List<KhMatrixSleepDetailData> getSleepDataFor(@NotNull String macAddress, @NotNull String date) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(date, "date");
        Pair<Long, Long> fromAndToTime = Utils.INSTANCE.getFromAndToTime(date);
        long longValue = fromAndToTime.component1().longValue();
        return this.b.getSleepDataBetween(macAddress, fromAndToTime.component2().longValue(), longValue);
    }

    @Nullable
    public final List<String> getUniqueDateForSleep(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getUniqueDatesForSleep(macAddress);
    }

    public final void insertAllSleepDetailData(@NotNull List<KhMatrixSleepDetailData> sleepList) {
        Intrinsics.checkNotNullParameter(sleepList, "sleepList");
        this.b.insertAllSleepDetailData(sleepList);
    }

    public final void insertSleepData(@NotNull List<KhMatrixSleepData> sleepList) {
        Intrinsics.checkNotNullParameter(sleepList, "sleepList");
        this.b.insertAll(sleepList);
    }

    public final void updateSleepDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateSleepDataProcessedBefore(macAddress, j);
    }

    public final void updateSleepDetailProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateSleepDetailProcessedBefore(macAddress, j);
    }
}
