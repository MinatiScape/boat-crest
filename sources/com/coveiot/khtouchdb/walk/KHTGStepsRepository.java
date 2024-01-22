package com.coveiot.khtouchdb.walk;

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
public final class KHTGStepsRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7202a;
    @NotNull
    public KHTGStepsDataDao b;

    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KHTGStepsRepository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KHTGStepsRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KHTGStepsRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KHTGStepsRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KHTGStepsRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KHTGStepsRepository(Context context) {
        this.f7202a = context;
        this.b = KHTouchAppDatabase.Companion.getDatabase(context).getTGStepsDao();
    }

    public /* synthetic */ KHTGStepsRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<EntityTGStepData> getAllUnProcessedStepsData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllUnProcessedStepsData(macAddress);
    }

    @NotNull
    public final Context getContext() {
        return this.f7202a;
    }

    public final long insertStepsData(@NotNull EntityTGStepData stepData) {
        Intrinsics.checkNotNullParameter(stepData, "stepData");
        return this.b.insert(stepData);
    }

    public final void insertStepsDataList(@NotNull List<EntityTGStepData> stepList) {
        Intrinsics.checkNotNullParameter(stepList, "stepList");
        this.b.insertAll(stepList);
    }

    public final void updateStepsDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateStepsDataProcessedBefore(macAddress, j);
    }
}
