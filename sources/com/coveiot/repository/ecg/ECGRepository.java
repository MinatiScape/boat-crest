package com.coveiot.repository.ecg;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.covedb.ecg.EntityECGSummaryData;
import com.coveiot.repository.ResponseResult;
import com.coveiot.repository.SingletonHolder;
import com.coveiot.repository.ecg.db.read.ECGDBRead;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class ECGRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    public Context f7389a;

    /* loaded from: classes9.dex */
    public static final class Companion extends SingletonHolder<ECGRepository, Context> {

        /* loaded from: classes9.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, ECGRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, ECGRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final ECGRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new ECGRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ECGRepository(Context context) {
        new MutableLiveData();
        this.f7389a = context;
    }

    public /* synthetic */ ECGRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final List<EntityECGSummaryData> getECGSummaryDataList() {
        Context context = this.f7389a;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        List<EntityECGSummaryData> eCGSummaryDataList = ECGDBRead.getInstance(context).getECGSummaryDataList();
        Intrinsics.checkNotNullExpressionValue(eCGSummaryDataList, "getInstance(context)\n   … .getECGSummaryDataList()");
        return eCGSummaryDataList;
    }

    @NotNull
    public final EntityECGSummaryData getLastECGSummaryData() {
        Context context = this.f7389a;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context = null;
        }
        EntityECGSummaryData lastECGSummaryData = ECGDBRead.getInstance(context).getLastECGSummaryData();
        Intrinsics.checkNotNullExpressionValue(lastECGSummaryData, "getInstance(context)\n   …      .lastECGSummaryData");
        return lastECGSummaryData;
    }

    public final void setResponseResult(@NotNull MutableLiveData<ResponseResult> responseResult) {
        Intrinsics.checkNotNullParameter(responseResult, "responseResult");
    }
}
