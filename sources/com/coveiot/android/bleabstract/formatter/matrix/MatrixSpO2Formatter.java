package com.coveiot.android.bleabstract.formatter.matrix;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ManualSpo2Reading;
import com.coveiot.android.bleabstract.response.ReadManualSpo2Response;
import com.coveiot.android.khmatrixdb.spo2.KhMatrixSpO2;
import com.htsmart.wristband2.bean.data.OxygenData;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class MatrixSpO2Formatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3370a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<MatrixSpO2Formatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.matrix.MatrixSpO2Formatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, MatrixSpO2Formatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3371a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, MatrixSpO2Formatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public MatrixSpO2Formatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new MatrixSpO2Formatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3371a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public MatrixSpO2Formatter(Context context) {
        this.f3370a = context;
    }

    public /* synthetic */ MatrixSpO2Formatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final ReadManualSpo2Response convertSpO2Data(@NotNull List<KhMatrixSpO2> spO2List) {
        Intrinsics.checkNotNullParameter(spO2List, "spO2List");
        ArrayList arrayList = new ArrayList();
        for (KhMatrixSpO2 khMatrixSpO2 : spO2List) {
            ManualSpo2Reading manualSpo2Reading = new ManualSpo2Reading();
            manualSpo2Reading.setSpo2(khMatrixSpO2.getMSpO2());
            manualSpo2Reading.setTimeStamp(khMatrixSpO2.getMTime());
            arrayList.add(manualSpo2Reading);
        }
        ReadManualSpo2Response readManualSpo2Response = new ReadManualSpo2Response(arrayList);
        readManualSpo2Response.setComplete(true);
        return readManualSpo2Response;
    }

    @NotNull
    public final Context getContext() {
        return this.f3370a;
    }

    @NotNull
    public final List<KhMatrixSpO2> getMatrixBleSpO2(@Nullable List<? extends OxygenData> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            String macAddress = BleApiManager.getInstance(this.f3370a).getBleApi().getMacAddress();
            for (OxygenData oxygenData : list) {
                arrayList.add(new KhMatrixSpO2(macAddress, oxygenData.getTimeStamp(), oxygenData.getOxygen(), null, 8, null));
            }
        }
        return arrayList;
    }
}
