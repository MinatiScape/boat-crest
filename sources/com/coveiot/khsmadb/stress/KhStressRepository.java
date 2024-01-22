package com.coveiot.khsmadb.stress;

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
public final class KhStressRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7160a;
    @NotNull
    public KhStressDao b;

    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KhStressRepository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KhStressRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KhStressRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KhStressRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KhStressRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KhStressRepository(Context context) {
        this.f7160a = context;
        this.b = KhSmaAppDatabase.Companion.getDatabase(context).getStressDao();
    }

    public /* synthetic */ KhStressRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<KhBlePressure> getAllStressData(@NotNull String macAddress, boolean z) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllStressData(macAddress, z);
    }

    @NotNull
    public final Context getContext() {
        return this.f7160a;
    }

    @Nullable
    public final List<KhBlePressure> getStressDataFor(@NotNull String macAddress, @NotNull String date) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(date, "date");
        return this.b.getStressDataFor(macAddress, date);
    }

    @Nullable
    public final List<KhBlePressure> getTemperatureDataLisgetStressDataListBetweenTimetBetweenTime(@NotNull String macAddress, int i, int i2) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getStressDataListBetweenTime(macAddress, i, i2);
    }

    @Nullable
    public final List<String> getUniqueDatesForStress(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getUniqueDatesForStress(macAddress);
    }

    public final void insertAll(@NotNull List<KhBlePressure> temperatureList) {
        Intrinsics.checkNotNullParameter(temperatureList, "temperatureList");
        this.b.insertAll(temperatureList);
    }

    public final void updateStressDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateStressDataProcessedBefore(macAddress, j);
    }
}
