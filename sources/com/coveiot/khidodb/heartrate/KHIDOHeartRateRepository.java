package com.coveiot.khidodb.heartrate;

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
public final class KHIDOHeartRateRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7091a;
    @NotNull
    public KHIDOHealthHeartRateDataDao b;

    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KHIDOHeartRateRepository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KHIDOHeartRateRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KHIDOHeartRateRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KHIDOHeartRateRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KHIDOHeartRateRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KHIDOHeartRateRepository(Context context) {
        this.f7091a = context;
        this.b = KHIDOAppDatabase.Companion.getDatabase(context).getHealthHeartRateDao();
    }

    public /* synthetic */ KHIDOHeartRateRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<EntityHealthHeartRateSecond> getAllUnProcessedHeartRateData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllUnProcessedStepsData(macAddress);
    }

    @NotNull
    public final Context getContext() {
        return this.f7091a;
    }

    public final void insertHeartRateDataList(@NotNull List<EntityHealthHeartRateSecond> stepList) {
        Intrinsics.checkNotNullParameter(stepList, "stepList");
        this.b.insertAll(stepList);
    }

    public final long insertHeartRateSecondData(@NotNull EntityHealthHeartRateSecond heartRateSecond) {
        Intrinsics.checkNotNullParameter(heartRateSecond, "heartRateSecond");
        return this.b.insert(heartRateSecond);
    }

    public final void updateHeartRateDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateStepsDataProcessedBefore(macAddress, j);
    }
}
