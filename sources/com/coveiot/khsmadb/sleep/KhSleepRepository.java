package com.coveiot.khsmadb.sleep;

import android.content.Context;
import com.coveiot.khsmadb.KhSmaAppDatabase;
import com.coveiot.khsmadb.Utils;
import com.coveiot.utils.SingletonHolder;
import java.util.List;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class KhSleepRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7153a;
    @NotNull
    public KhSleepDao b;

    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KhSleepRepository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KhSleepRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KhSleepRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KhSleepRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KhSleepRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KhSleepRepository(Context context) {
        this.f7153a = context;
        this.b = KhSmaAppDatabase.Companion.getDatabase(context).getSleepDao();
    }

    public /* synthetic */ KhSleepRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<KhBleSleep> getAllSleepData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllSleepData(macAddress);
    }

    @NotNull
    public final Context getContext() {
        return this.f7153a;
    }

    @Nullable
    public final List<KhBleSleep> getSleepDataFor(@NotNull String macAddress, @NotNull String date) {
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

    public final void insertSleepData(@NotNull List<KhBleSleep> sleepList) {
        Intrinsics.checkNotNullParameter(sleepList, "sleepList");
        this.b.insertAll(sleepList);
    }

    public final void updateSleepDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateSleepDataProcessedBefore(macAddress, j);
    }
}
