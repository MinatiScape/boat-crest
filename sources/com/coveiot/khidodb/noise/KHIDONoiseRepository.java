package com.coveiot.khidodb.noise;

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
public final class KHIDONoiseRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7097a;
    @NotNull
    public KHIDOHealthNoiseDataDao b;

    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KHIDONoiseRepository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KHIDONoiseRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KHIDONoiseRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KHIDONoiseRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KHIDONoiseRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KHIDONoiseRepository(Context context) {
        this.f7097a = context;
        this.b = KHIDOAppDatabase.Companion.getDatabase(context).getHealthNoiseData();
    }

    public /* synthetic */ KHIDONoiseRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<EntityHealthNoise> getAllUnProcessedNoiseData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllUnProcessedStepsData(macAddress);
    }

    @NotNull
    public final Context getContext() {
        return this.f7097a;
    }

    public final long insertNoiseData(@NotNull EntityHealthNoise noise) {
        Intrinsics.checkNotNullParameter(noise, "noise");
        return this.b.insert(noise);
    }

    public final void insertNoiseDataList(@NotNull List<EntityHealthNoise> stepList) {
        Intrinsics.checkNotNullParameter(stepList, "stepList");
        this.b.insertAll(stepList);
    }

    public final void updateNoiseDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateStepsDataProcessedBefore(macAddress, j);
    }
}
