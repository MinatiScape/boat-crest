package com.coveiot.khsmadb.workout;

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
public final class KhWorkoutRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7166a;
    @NotNull
    public KhBleWorkoutDao b;

    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KhWorkoutRepository, Context> {

        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KhWorkoutRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, KhWorkoutRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final KhWorkoutRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KhWorkoutRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KhWorkoutRepository(Context context) {
        this.f7166a = context;
        this.b = KhSmaAppDatabase.Companion.getDatabase(context).getWorkoutDao();
    }

    public /* synthetic */ KhWorkoutRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final Context getContext() {
        return this.f7166a;
    }

    @Nullable
    public final List<KhBleWorkout> getUnMarkedWorkoutList(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getUnMarkedWorkoutList(macAddress);
    }

    public final void insertAll(@NotNull List<KhBleWorkout> heartRateList) {
        Intrinsics.checkNotNullParameter(heartRateList, "heartRateList");
        this.b.insertAll(heartRateList);
    }
}
