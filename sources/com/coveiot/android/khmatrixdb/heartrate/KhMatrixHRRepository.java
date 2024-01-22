package com.coveiot.android.khmatrixdb.heartrate;

import android.content.Context;
import com.coveiot.android.khmatrixdb.KhMatrixAppDatabase;
import com.coveiot.utils.SingletonHolder;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class KhMatrixHRRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4619a;
    @NotNull
    public KhMatrixHeartRateDataDao b;

    /* loaded from: classes3.dex */
    public static final class Companion extends SingletonHolder<KhMatrixHRRepository, Context> {

        /* loaded from: classes3.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KhMatrixHRRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KhMatrixHRRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KhMatrixHRRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KhMatrixHRRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KhMatrixHRRepository(Context context) {
        this.f4619a = context;
        this.b = KhMatrixAppDatabase.Companion.getDatabase(context).getHRDao();
    }

    public /* synthetic */ KhMatrixHRRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<KhMatrixHeartRate> getAllUnProcessedHeartRateData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllUnProcessedHRData(macAddress);
    }

    @NotNull
    public final Context getContext() {
        return this.f4619a;
    }

    public final void insertHRData(@NotNull List<KhMatrixHeartRate> stepList) {
        Intrinsics.checkNotNullParameter(stepList, "stepList");
        this.b.insertAll(stepList);
    }

    public final void updateHeartRateDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateHeartRateDataProcessedBefore(macAddress, j);
    }
}
