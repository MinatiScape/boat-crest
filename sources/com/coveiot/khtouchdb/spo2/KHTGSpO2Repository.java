package com.coveiot.khtouchdb.spo2;

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
public final class KHTGSpO2Repository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7194a;
    @NotNull
    public KHTGSpO2DataDao b;

    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KHTGSpO2Repository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KHTGSpO2Repository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KHTGSpO2Repository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KHTGSpO2Repository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KHTGSpO2Repository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KHTGSpO2Repository(Context context) {
        this.f7194a = context;
        this.b = KHTouchAppDatabase.Companion.getDatabase(context).getTGSpo2Dao();
    }

    public /* synthetic */ KHTGSpO2Repository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<EntityTGSpO2Data> getAllUnProcessedSpO2Data(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllUnProcessedSpO2Data(macAddress);
    }

    @NotNull
    public final Context getContext() {
        return this.f7194a;
    }

    public final long insertSpO2Data(@NotNull EntityTGSpO2Data spO2Data) {
        Intrinsics.checkNotNullParameter(spO2Data, "spO2Data");
        return this.b.insert(spO2Data);
    }

    public final void insertSpO2DataList(@NotNull List<EntityTGSpO2Data> stepList) {
        Intrinsics.checkNotNullParameter(stepList, "stepList");
        this.b.insertAll(stepList);
    }

    public final void updateSpO2DataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateSpO2DataProcessedBefore(macAddress, j);
    }
}
