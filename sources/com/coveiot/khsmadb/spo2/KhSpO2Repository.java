package com.coveiot.khsmadb.spo2;

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
public final class KhSpO2Repository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7157a;
    @NotNull
    public KhSpO2Dao b;

    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KhSpO2Repository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KhSpO2Repository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KhSpO2Repository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KhSpO2Repository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KhSpO2Repository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KhSpO2Repository(Context context) {
        this.f7157a = context;
        this.b = KhSmaAppDatabase.Companion.getDatabase(context).getSpO2Dao();
    }

    public /* synthetic */ KhSpO2Repository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<KhBleSpO2> getAllSpO2Data(@NotNull String macAddress, boolean z) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllSpO2Data(macAddress, z);
    }

    @NotNull
    public final Context getContext() {
        return this.f7157a;
    }

    @Nullable
    public final List<KhBleSpO2> getSpO2DataFor(@NotNull String macAddress, @NotNull String date) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(date, "date");
        return this.b.getSpO2DataFor(macAddress, date);
    }

    public final void insertSpO2Data(@NotNull List<KhBleSpO2> spO2List) {
        Intrinsics.checkNotNullParameter(spO2List, "spO2List");
        this.b.insertAll(spO2List);
    }

    public final void updateSpO2DataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateSpO2DataProcessedBefore(macAddress, j);
    }
}
