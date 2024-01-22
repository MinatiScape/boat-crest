package com.coveiot.khtouchdb.activities;

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
public final class KHTGActivityRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7170a;
    @NotNull
    public KHTGWorkoutRecordsDao b;

    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KHTGActivityRepository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KHTGActivityRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KHTGActivityRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KHTGActivityRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KHTGActivityRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KHTGActivityRepository(Context context) {
        this.f7170a = context;
        this.b = KHTouchAppDatabase.Companion.getDatabase(context).getTGWorkoutDao();
    }

    public /* synthetic */ KHTGActivityRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<EntityTGWorkoutRecord> getAllUnProcessedActivityData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllUnProcessedActivityData(macAddress);
    }

    @NotNull
    public final Context getContext() {
        return this.f7170a;
    }

    public final void insertActivityData(@NotNull List<EntityTGWorkoutRecord> activityList) {
        Intrinsics.checkNotNullParameter(activityList, "activityList");
        this.b.insertAll(activityList);
    }

    public final void updateActivityDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateActivityDataProcessedBefore(macAddress, j);
    }

    public final void insertActivityData(@NotNull EntityTGWorkoutRecord activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.b.insert(activity);
    }
}
