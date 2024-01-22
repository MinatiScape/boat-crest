package com.coveiot.khidodb.activities;

import android.content.Context;
import com.coveiot.khidodb.KHIDOAppDatabase;
import com.coveiot.utils.SingletonHolder;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class KHIDOActivityRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7087a;
    @NotNull
    public KHIDOHealthActivityV3Doa b;

    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KHIDOActivityRepository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KHIDOActivityRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KHIDOActivityRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KHIDOActivityRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KHIDOActivityRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KHIDOActivityRepository(Context context) {
        this.f7087a = context;
        this.b = KHIDOAppDatabase.Companion.getDatabase(context).getActivityV3Data();
    }

    public /* synthetic */ KHIDOActivityRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<EntityHealthActivityV3> getAllUnProcessedActivityData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllUnProcessedActivityData(macAddress);
    }

    @Nullable
    public final List<EntityHealthSwimV3> getAllUnProcessedSwimActivityData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllUnProcessedSwimActivityData(macAddress);
    }

    @NotNull
    public final Context getContext() {
        return this.f7087a;
    }

    public final void insertActivityData(@NotNull List<EntityHealthActivityV3> activityList) {
        Intrinsics.checkNotNullParameter(activityList, "activityList");
        this.b.insertAll(activityList);
    }

    public final void insertSwimActivityData(@NotNull EntityHealthSwimV3 activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.b.insertSwimData(activity);
    }

    public final void insertSwimActivityDataList(@NotNull List<EntityHealthSwimV3> activityList) {
        Intrinsics.checkNotNullParameter(activityList, "activityList");
        this.b.insertAllSwimData(activityList);
    }

    public final void updateActivityDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateActivityDataProcessedBefore(macAddress, j);
    }

    public final void updateSwimActivityDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateASwimActivityDataProcessedBefore(macAddress, j);
    }

    public final void insertActivityData(@NotNull EntityHealthActivityV3 activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.b.insert(activity);
    }
}
