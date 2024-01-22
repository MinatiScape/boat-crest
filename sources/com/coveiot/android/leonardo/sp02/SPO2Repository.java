package com.coveiot.android.leonardo.sp02;

import android.content.Context;
import com.coveiot.android.leonardo.SingletonHolder;
import com.coveiot.android.leonardo.sp02.database.SPO2Database;
import com.coveiot.android.leonardo.sp02.database.dao.SPO2Dao;
import com.coveiot.android.leonardo.sp02.database.entities.SPO2Record;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SPO2Repository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5406a;
    @NotNull
    public SPO2Dao b;

    /* loaded from: classes5.dex */
    public static final class Companion extends SingletonHolder<SPO2Repository, Context> {

        /* loaded from: classes5.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, SPO2Repository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, SPO2Repository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final SPO2Repository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SPO2Repository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SPO2Repository(Context context) {
        this.f5406a = context;
        SPO2Dao spo2Dao = SPO2Database.getAppDatabase(context).spo2Dao();
        Intrinsics.checkNotNullExpressionValue(spo2Dao, "getAppDatabase(context).spo2Dao()");
        this.b = spo2Dao;
    }

    public /* synthetic */ SPO2Repository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final SPO2Record getLatestRecord(@NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        return this.b.getLatestSpo2Record(date);
    }

    @Nullable
    public final List<SPO2Record> getUnSyncedData() {
        return this.b.getUnSyncedSPO2Recods();
    }

    public final void insert(@NotNull SPO2Record spO2Record) {
        Intrinsics.checkNotNullParameter(spO2Record, "spO2Record");
        this.b.insert(spO2Record);
    }
}
