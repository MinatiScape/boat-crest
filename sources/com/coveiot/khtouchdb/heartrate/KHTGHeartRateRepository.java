package com.coveiot.khtouchdb.heartrate;

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
public final class KHTGHeartRateRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7186a;
    @NotNull
    public KHTGHeartRateDataDao b;

    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KHTGHeartRateRepository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KHTGHeartRateRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KHTGHeartRateRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KHTGHeartRateRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KHTGHeartRateRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KHTGHeartRateRepository(Context context) {
        this.f7186a = context;
        this.b = KHTouchAppDatabase.Companion.getDatabase(context).getTGHeartRateDao();
    }

    public /* synthetic */ KHTGHeartRateRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<EntityTGHeartRateData> getAllUnProcessedHeartRateData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllUnProcessedStepsData(macAddress);
    }

    @NotNull
    public final Context getContext() {
        return this.f7186a;
    }

    public final long insertHeartRateData(@NotNull EntityTGHeartRateData heartRateData) {
        Intrinsics.checkNotNullParameter(heartRateData, "heartRateData");
        return this.b.insert(heartRateData);
    }

    public final void insertHeartRateDataList(@NotNull List<EntityTGHeartRateData> stepList) {
        Intrinsics.checkNotNullParameter(stepList, "stepList");
        this.b.insertAll(stepList);
    }

    public final void updateHeartRateDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateStepsDataProcessedBefore(macAddress, j);
    }
}
