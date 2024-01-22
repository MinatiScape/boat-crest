package com.coveiot.khsmadb.temperature;

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
public final class KhTemperatureRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7163a;
    @NotNull
    public KhTemperatureDao b;

    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KhTemperatureRepository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KhTemperatureRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KhTemperatureRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KhTemperatureRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KhTemperatureRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KhTemperatureRepository(Context context) {
        this.f7163a = context;
        this.b = KhSmaAppDatabase.Companion.getDatabase(context).getTemperatureDao();
    }

    public /* synthetic */ KhTemperatureRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<KhBleTemperature> getAllTemperatureData(@NotNull String macAddress, boolean z) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllTemperatureData(macAddress, z);
    }

    @NotNull
    public final Context getContext() {
        return this.f7163a;
    }

    @Nullable
    public final List<KhBleTemperature> getTemperatureDataFor(@NotNull String macAddress, @NotNull String date) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(date, "date");
        return this.b.getTemperatureDataFor(macAddress, date);
    }

    @Nullable
    public final List<KhBleTemperature> getTemperatureDataListBetweenTime(@NotNull String macAddress, int i, int i2) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getTemperatureDataListBetweenTime(macAddress, i, i2);
    }

    @Nullable
    public final List<String> getUniqueDateForTemperature(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getUniqueDatesForTemperature(macAddress);
    }

    public final void insertTemperatureData(@NotNull List<KhBleTemperature> temperatureList) {
        Intrinsics.checkNotNullParameter(temperatureList, "temperatureList");
        this.b.insertAll(temperatureList);
    }

    public final void updateTemperatureDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateTemperatureDataProcessedBefore(macAddress, j);
    }
}
