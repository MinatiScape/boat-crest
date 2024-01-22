package com.coveiot.khidodb.stress;

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
public final class KHIDOStressRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7109a;
    @NotNull
    public KHHealthPressureDataDao b;

    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KHIDOStressRepository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KHIDOStressRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KHIDOStressRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KHIDOStressRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KHIDOStressRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KHIDOStressRepository(Context context) {
        this.f7109a = context;
        this.b = KHIDOAppDatabase.Companion.getDatabase(context).getHealthPressureData();
    }

    public /* synthetic */ KHIDOStressRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<EntityHealthPressure> getAllUnProcessedStressData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllUnProcessedPressureData(macAddress);
    }

    @NotNull
    public final Context getContext() {
        return this.f7109a;
    }

    public final long insertStressData(@NotNull EntityHealthPressure stepData) {
        Intrinsics.checkNotNullParameter(stepData, "stepData");
        return this.b.insert(stepData);
    }

    public final void insertStressDataList(@NotNull List<EntityHealthPressure> stepList) {
        Intrinsics.checkNotNullParameter(stepList, "stepList");
        this.b.insertAll(stepList);
    }

    public final void updateStressDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updatePressureDataProcessedBefore(macAddress, j);
    }
}
