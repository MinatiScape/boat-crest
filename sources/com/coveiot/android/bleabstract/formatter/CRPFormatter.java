package com.coveiot.android.bleabstract.formatter;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
import com.coveiot.android.bleabstract.response.ReadManualSpo2Response;
import com.coveiot.android.bleabstract.response.SleepResponse;
import com.coveiot.android.bleabstract.response.StepsResponse;
import com.crrepa.ble.conn.bean.CRPHeartRateInfo;
import com.crrepa.ble.conn.bean.CRPHistoryBloodOxygenInfo;
import com.crrepa.ble.conn.bean.CRPMovementHeartRateInfo;
import com.crrepa.ble.conn.bean.CRPSleepInfo;
import com.crrepa.ble.conn.bean.CRPStepsCategoryInfo;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class CRPFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3299a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<CRPFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.CRPFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, CRPFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3300a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, CRPFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public CRPFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new CRPFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3300a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CRPFormatter(Context context) {
        this.f3299a = context;
    }

    public /* synthetic */ CRPFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final ArrayList<HeartRateResponse> convertHrData(@NotNull List<? extends CRPHeartRateInfo> crpHeartRateInfos, int i) {
        Intrinsics.checkNotNullParameter(crpHeartRateInfos, "crpHeartRateInfos");
        return CRPHRFormatter.Companion.getInstance(this.f3299a).convertData(crpHeartRateInfos, i);
    }

    @NotNull
    public final ArrayList<SleepResponse> convertSleepData(int i, @NotNull List<? extends CRPSleepInfo> crpSleepInfos, int i2) {
        Intrinsics.checkNotNullParameter(crpSleepInfos, "crpSleepInfos");
        return CRPSleepFormatter.Companion.getInstance(this.f3299a).convertSleepData(i, crpSleepInfos, i2);
    }

    @NotNull
    public final ArrayList<ReadManualSpo2Response> convertSpo2Data(@NotNull String macAddress, @NotNull List<? extends CRPHistoryBloodOxygenInfo> crpBloodOxygenInfos) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(crpBloodOxygenInfos, "crpBloodOxygenInfos");
        return CRPSPO2Formatter.Companion.getInstance(this.f3299a).convertSPO2Data(macAddress, crpBloodOxygenInfos);
    }

    @NotNull
    public final ArrayList<StepsResponse> convertStepData(@NotNull CRPStepsCategoryInfo crpStepsCategoryInfo, int i) {
        Intrinsics.checkNotNullParameter(crpStepsCategoryInfo, "crpStepsCategoryInfo");
        return CRPStepsFormatter.Companion.getInstance(this.f3299a).convertStepData(crpStepsCategoryInfo, i);
    }

    @NotNull
    public final List<ActivityModeSummaryResponse> convertWorkoutData(@NotNull String macAddress, @NotNull List<CRPMovementHeartRateInfo> crpMovementHeartListInfoList) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(crpMovementHeartListInfoList, "crpMovementHeartListInfoList");
        return CRPWorkoutFormatter.Companion.getInstance(this.f3299a).convertWorkoutData(macAddress, crpMovementHeartListInfoList);
    }

    @NotNull
    public final Context getContext() {
        return this.f3299a;
    }
}
