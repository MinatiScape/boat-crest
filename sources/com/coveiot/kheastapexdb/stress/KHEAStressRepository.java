package com.coveiot.kheastapexdb.stress;

import android.content.Context;
import com.coveiot.kheastapexdb.KHEastApexAppDatabase;
import com.coveiot.utils.SingletonHolder;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0011\b\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0017\u0010\u0018J\u0014\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002J\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0003J\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\u0006\u0010\u000b\u001a\u00020\nJ\u0016\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\bR\u0019\u0010\u0010\u001a\u00020\u000f8\u0006@\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0015\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016¨\u0006\u001a"}, d2 = {"Lcom/coveiot/kheastapexdb/stress/KHEAStressRepository;", "", "", "Lcom/coveiot/kheastapexdb/stress/EntityEAStressData;", "stressList", "", "insertStressDataList", "stepData", "", "insertStressData", "", DeviceKey.MacAddress, "getAllUnProcessedStressData", "thresholdTimestamp", "updateStressDataProcessedBefore", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/coveiot/kheastapexdb/stress/KHEAStressDataDao;", "pressureDataDao", "Lcom/coveiot/kheastapexdb/stress/KHEAStressDataDao;", "<init>", "(Landroid/content/Context;)V", "Companion", "kheastapexdb_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes8.dex */
public final class KHEAStressRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7074a;
    @NotNull
    public KHEAStressDataDao b;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/coveiot/kheastapexdb/stress/KHEAStressRepository$Companion;", "Lcom/coveiot/utils/SingletonHolder;", "Lcom/coveiot/kheastapexdb/stress/KHEAStressRepository;", "Landroid/content/Context;", "<init>", "()V", "kheastapexdb_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KHEAStressRepository, Context> {

        @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KHEAStressRepository> {

            /* renamed from: a  reason: collision with root package name */
            public static final a f7075a = new a();

            public a() {
                super(1, KHEAStressRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public KHEAStressRepository invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KHEAStressRepository(p0, null);
            }
        }

        public Companion() {
            super(a.f7075a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KHEAStressRepository(Context context) {
        this.f7074a = context;
        this.b = KHEastApexAppDatabase.Companion.getDatabase(context).getEAStressDao();
    }

    public /* synthetic */ KHEAStressRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<EntityEAStressData> getAllUnProcessedStressData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllUnProcessedPressureData(macAddress);
    }

    @NotNull
    public final Context getContext() {
        return this.f7074a;
    }

    public final long insertStressData(@NotNull EntityEAStressData stepData) {
        Intrinsics.checkNotNullParameter(stepData, "stepData");
        return this.b.insert(stepData);
    }

    public final void insertStressDataList(@NotNull List<EntityEAStressData> stressList) {
        Intrinsics.checkNotNullParameter(stressList, "stressList");
        this.b.insertAll(stressList);
    }

    public final void updateStressDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updatePressureDataProcessedBefore(macAddress, j);
    }
}
