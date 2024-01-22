package com.coveiot.android.khmatrixdb.workout;

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
public final class KhMatrixSportsRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4637a;
    @NotNull
    public KhMatrixSportDataDao b;

    /* loaded from: classes3.dex */
    public static final class Companion extends SingletonHolder<KhMatrixSportsRepository, Context> {

        /* loaded from: classes3.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KhMatrixSportsRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KhMatrixSportsRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KhMatrixSportsRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KhMatrixSportsRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KhMatrixSportsRepository(Context context) {
        this.f4637a = context;
        this.b = KhMatrixAppDatabase.Companion.getDatabase(context).getSportsDao();
    }

    public /* synthetic */ KhMatrixSportsRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<KhMatrixSportData> getAllUnProcessedSportsData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllUnProcessedSportsData(macAddress);
    }

    @NotNull
    public final Context getContext() {
        return this.f4637a;
    }

    public final void insertSportsData(@NotNull List<KhMatrixSportData> stepList) {
        Intrinsics.checkNotNullParameter(stepList, "stepList");
        this.b.insertAll(stepList);
    }

    public final void updateSportsDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateSportsDataProcessedBefore(macAddress, j);
    }
}
