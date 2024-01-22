package com.coveiot.khsmadb.location;

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
public final class KhLocationRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7150a;
    @NotNull
    public KhLocationDao b;

    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KhLocationRepository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KhLocationRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KhLocationRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KhLocationRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KhLocationRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KhLocationRepository(Context context) {
        this.f7150a = context;
        this.b = KhSmaAppDatabase.Companion.getDatabase(context).getLocationDao();
    }

    public /* synthetic */ KhLocationRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final Context getContext() {
        return this.f7150a;
    }

    @Nullable
    public final List<KhLocation> getLocationDataListBetweenTime(@NotNull String macAddress, int i, int i2) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getLocationDataListBetweenTime(macAddress, i, i2);
    }

    public final void insertAll(@NotNull List<KhLocation> heartRateList) {
        Intrinsics.checkNotNullParameter(heartRateList, "heartRateList");
        this.b.insertAll(heartRateList);
    }
}
