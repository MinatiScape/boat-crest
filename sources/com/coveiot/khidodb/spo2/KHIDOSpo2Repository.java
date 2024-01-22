package com.coveiot.khidodb.spo2;

import android.content.Context;
import com.coveiot.khidodb.KHIDOAppDatabase;
import com.coveiot.utils.SingletonHolder;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class KHIDOSpo2Repository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7105a;
    @NotNull
    public KHHealthSpo2DataDao b;

    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KHIDOSpo2Repository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KHIDOSpo2Repository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KHIDOSpo2Repository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KHIDOSpo2Repository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KHIDOSpo2Repository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KHIDOSpo2Repository(Context context) {
        this.f7105a = context;
        this.b = KHIDOAppDatabase.Companion.getDatabase(context).getHealthSpo2Data();
    }

    public /* synthetic */ KHIDOSpo2Repository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<EntityHealthSpo2> getAllUnProcessedSpo2Data(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllUnProcessedSpo2Data(macAddress);
    }

    @NotNull
    public final Context getContext() {
        return this.f7105a;
    }

    public final long insertSpo2Data(@NotNull EntityHealthSpo2 stepData) {
        Intrinsics.checkNotNullParameter(stepData, "stepData");
        return this.b.insert(stepData);
    }

    public final void insertSpo2DataList(@NotNull List<EntityHealthSpo2> stepList) {
        Intrinsics.checkNotNullParameter(stepList, "stepList");
        this.b.insertAll(stepList);
    }

    public final void updateSpo2DataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateSpo2DataProcessedBefore(macAddress, j);
    }
}
