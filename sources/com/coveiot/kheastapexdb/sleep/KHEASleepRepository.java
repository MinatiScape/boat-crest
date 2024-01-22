package com.coveiot.kheastapexdb.sleep;

import android.content.Context;
import com.coveiot.kheastapexdb.KHEastApexAppDatabase;
import com.coveiot.utils.SingletonHolder;
import com.coveiot.utils.utility.AppUtils;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.Calendar;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0011\b\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u001c\u0010\u001dJ\u0014\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002J\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0003J\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\u0006\u0010\u000b\u001a\u00020\nJ\u0016\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\bJ\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00022\u0006\u0010\u000b\u001a\u00020\nJ\u001e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\nJ\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u0010\u001a\u00020\nR\u0019\u0010\u0015\u001a\u00020\u00148\u0006@\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001a\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001b¨\u0006\u001f"}, d2 = {"Lcom/coveiot/kheastapexdb/sleep/KHEASleepRepository;", "", "", "Lcom/coveiot/kheastapexdb/sleep/EntityEASleepData;", "sleepList", "", "insertSleepDataList", "sleepData", "", "insertSleepData", "", DeviceKey.MacAddress, "getAllUnProcessedSleepData", "thresholdTimestamp", "updateSleepDataProcessedBefore", "getUniqueDateForSleep", "date", "getSleepDataFor", "Lkotlin/Pair;", "getFromAndToTime", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/coveiot/kheastapexdb/sleep/KHEASleepDataDao;", "sleepDao", "Lcom/coveiot/kheastapexdb/sleep/KHEASleepDataDao;", "<init>", "(Landroid/content/Context;)V", "Companion", "kheastapexdb_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes8.dex */
public final class KHEASleepRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7066a;
    @NotNull
    public KHEASleepDataDao b;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/coveiot/kheastapexdb/sleep/KHEASleepRepository$Companion;", "Lcom/coveiot/utils/SingletonHolder;", "Lcom/coveiot/kheastapexdb/sleep/KHEASleepRepository;", "Landroid/content/Context;", "<init>", "()V", "kheastapexdb_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KHEASleepRepository, Context> {

        @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KHEASleepRepository> {

            /* renamed from: a  reason: collision with root package name */
            public static final a f7067a = new a();

            public a() {
                super(1, KHEASleepRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public KHEASleepRepository invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KHEASleepRepository(p0, null);
            }
        }

        public Companion() {
            super(a.f7067a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KHEASleepRepository(Context context) {
        this.f7066a = context;
        this.b = KHEastApexAppDatabase.Companion.getDatabase(context).getEASleepsDao();
    }

    public /* synthetic */ KHEASleepRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<EntityEASleepData> getAllUnProcessedSleepData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllUnProcessedSleepData(macAddress);
    }

    @NotNull
    public final Context getContext() {
        return this.f7066a;
    }

    @NotNull
    public final Pair<Long, Long> getFromAndToTime(@NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(AppUtils.parseDate(date, "yyyy-MM-dd"));
        calendar.set(11, 12);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(6, -1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(AppUtils.parseDate(date, "yyyy-MM-dd"));
        calendar2.set(11, 11);
        calendar2.set(12, 59);
        calendar2.set(13, 59);
        calendar2.set(14, 999);
        return new Pair<>(Long.valueOf(calendar2.getTimeInMillis()), Long.valueOf(calendar.getTimeInMillis()));
    }

    @Nullable
    public final List<EntityEASleepData> getSleepDataFor(@NotNull String macAddress, @NotNull String date) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(date, "date");
        Pair<Long, Long> fromAndToTime = getFromAndToTime(date);
        long longValue = fromAndToTime.component1().longValue();
        return this.b.getSleepDataBetween(macAddress, fromAndToTime.component2().longValue(), longValue);
    }

    @Nullable
    public final List<String> getUniqueDateForSleep(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getUniqueDatesForSleep(macAddress);
    }

    public final long insertSleepData(@NotNull EntityEASleepData sleepData) {
        Intrinsics.checkNotNullParameter(sleepData, "sleepData");
        return this.b.insert(sleepData);
    }

    public final void insertSleepDataList(@NotNull List<EntityEASleepData> sleepList) {
        Intrinsics.checkNotNullParameter(sleepList, "sleepList");
        this.b.insertAll(sleepList);
    }

    public final void updateSleepDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateSleepDataProcessedBefore(macAddress, j);
    }
}
