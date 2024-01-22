package com.coveiot.khtouchdb.sleep;

import android.content.Context;
import com.coveiot.khtouchdb.KHTouchAppDatabase;
import com.coveiot.utils.SingletonHolder;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class KHTGSleepRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7190a;
    @NotNull
    public KHTGSleepDataDao b;

    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KHTGSleepRepository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KHTGSleepRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KHTGSleepRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KHTGSleepRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KHTGSleepRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KHTGSleepRepository(Context context) {
        this.f7190a = context;
        this.b = KHTouchAppDatabase.Companion.getDatabase(context).getTGSleepDao();
    }

    public /* synthetic */ KHTGSleepRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<EntityTGSleepData> getAllUnProcessedSleepData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllUnProcessedSleepData(macAddress);
    }

    @NotNull
    public final Context getContext() {
        return this.f7190a;
    }

    public final long insertSleepData(@NotNull EntityTGSleepData sleepData) {
        Intrinsics.checkNotNullParameter(sleepData, "sleepData");
        return this.b.insert(sleepData);
    }

    public final void insertSleepDataList(@NotNull List<EntityTGSleepData> stepList) {
        Intrinsics.checkNotNullParameter(stepList, "stepList");
        this.b.insertAll(stepList);
    }

    public final void updateSleepDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateSleepDataProcessedBefore(macAddress, j);
    }
}
