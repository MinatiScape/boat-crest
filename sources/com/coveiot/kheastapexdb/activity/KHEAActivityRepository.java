package com.coveiot.kheastapexdb.activity;

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
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0011\b\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0016\u0010\u0017J\u0014\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0003J\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\bJ\u0016\u0010\r\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bR\u0019\u0010\u000f\u001a\u00020\u000e8\u0006@\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0014\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0019"}, d2 = {"Lcom/coveiot/kheastapexdb/activity/KHEAActivityRepository;", "", "", "Lcom/coveiot/kheastapexdb/activity/EntityEAActivityData;", "activityList", "", "insertActivityData", "activity", "", DeviceKey.MacAddress, "getAllUnProcessedActivityData", "", "thresholdTimestamp", "updateActivityDataProcessedBefore", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/coveiot/kheastapexdb/activity/KHEAActivityDataDao;", "activityV3Doa", "Lcom/coveiot/kheastapexdb/activity/KHEAActivityDataDao;", "<init>", "(Landroid/content/Context;)V", "Companion", "kheastapexdb_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes8.dex */
public final class KHEAActivityRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7057a;
    @NotNull
    public KHEAActivityDataDao b;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/coveiot/kheastapexdb/activity/KHEAActivityRepository$Companion;", "Lcom/coveiot/utils/SingletonHolder;", "Lcom/coveiot/kheastapexdb/activity/KHEAActivityRepository;", "Landroid/content/Context;", "<init>", "()V", "kheastapexdb_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<KHEAActivityRepository, Context> {

        @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
        /* loaded from: classes8.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, KHEAActivityRepository> {

            /* renamed from: a  reason: collision with root package name */
            public static final a f7058a = new a();

            public a() {
                super(1, KHEAActivityRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public KHEAActivityRepository invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new KHEAActivityRepository(p0, null);
            }
        }

        public Companion() {
            super(a.f7058a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KHEAActivityRepository(Context context) {
        this.f7057a = context;
        this.b = KHEastApexAppDatabase.Companion.getDatabase(context).getEAActivityDataDao();
    }

    public /* synthetic */ KHEAActivityRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final List<EntityEAActivityData> getAllUnProcessedActivityData(@NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return this.b.getAllUnProcessedActivityData(macAddress);
    }

    @NotNull
    public final Context getContext() {
        return this.f7057a;
    }

    public final void insertActivityData(@NotNull List<EntityEAActivityData> activityList) {
        Intrinsics.checkNotNullParameter(activityList, "activityList");
        this.b.insertAll(activityList);
    }

    public final void updateActivityDataProcessedBefore(@NotNull String macAddress, long j) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.b.updateActivityDataProcessedBefore(macAddress, j);
    }

    public final void insertActivityData(@NotNull EntityEAActivityData activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.b.insert(activity);
    }
}
