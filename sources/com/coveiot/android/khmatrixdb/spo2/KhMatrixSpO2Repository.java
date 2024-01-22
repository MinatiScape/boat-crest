package com.coveiot.android.khmatrixdb.spo2;

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
public final class KhMatrixSpO2Repository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4630a;
    @NotNull
    public KhMatrixSpO2DataDao b;

    /* loaded from: classes3.dex */
    public static final class Companion extends SingletonHolder<KhMatrixSpO2Repository, Context> {

        /* loaded from: classes3.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KhMatrixSpO2Repository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KhMatrixSpO2Repository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KhMatrixSpO2Repository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KhMatrixSpO2Repository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KhMatrixSpO2Repository(Context context) {
        this.f4630a = context;
        this.b = KhMatrixAppDatabase.Companion.getDatabase(context).getSpO2Dao();
    }

    public /* synthetic */ KhMatrixSpO2Repository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<KhMatrixSpO2> getAllUnProcessedSpO2Data(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllUnProcessedSp02Data(macAddress);
    }

    @NotNull
    public final Context getContext() {
        return this.f4630a;
    }

    public final void insertSpO2Data(@NotNull List<KhMatrixSpO2> stepList) {
        Intrinsics.checkNotNullParameter(stepList, "stepList");
        this.b.insertAll(stepList);
    }

    public final void updateSpO2DataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateSpO2DataProcessedBefore(macAddress, j);
    }
}
