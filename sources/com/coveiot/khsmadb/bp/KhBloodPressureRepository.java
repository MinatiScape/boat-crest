package com.coveiot.khsmadb.bp;

import android.content.Context;
import com.coveiot.khsmadb.KhSmaAppDatabase;
import com.coveiot.utils.SingletonHolder;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class KhBloodPressureRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7141a;
    @NotNull
    public KhBloodPressureDao b;

    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KhBloodPressureRepository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KhBloodPressureRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KhBloodPressureRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KhBloodPressureRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KhBloodPressureRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KhBloodPressureRepository(Context context) {
        this.f7141a = context;
        this.b = KhSmaAppDatabase.Companion.getDatabase(context).getBloodPressureDao();
    }

    public /* synthetic */ KhBloodPressureRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<KhBleBloodPressure> getAllBloodPressureData(@NotNull String macAddress, boolean z) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllBloodPressureData(macAddress, z);
    }

    @Nullable
    public final List<KhBleBloodPressure> getBloodPressureDataFor(@NotNull String macAddress, @NotNull String date) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(date, "date");
        return this.b.getBloodPressureDataFor(macAddress, date);
    }

    @NotNull
    public final Context getContext() {
        return this.f7141a;
    }

    @Nullable
    public final List<String> getUniqueDateForBloodPressure(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getUniqueDatesForBloodPressure(macAddress);
    }

    public final void insertBloodPressureData(@NotNull List<KhBleBloodPressure> bloodPressureList) {
        Intrinsics.checkNotNullParameter(bloodPressureList, "bloodPressureList");
        this.b.insertAll(bloodPressureList);
    }

    public final void updateBloodPressureDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateBloodPressureDataProcessedBefore(macAddress, j);
    }
}
