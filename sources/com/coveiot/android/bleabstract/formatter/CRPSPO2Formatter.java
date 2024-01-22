package com.coveiot.android.bleabstract.formatter;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.models.ManualSpo2Reading;
import com.coveiot.android.bleabstract.response.ReadManualSpo2Response;
import com.crrepa.ble.conn.bean.CRPHistoryBloodOxygenInfo;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class CRPSPO2Formatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3303a;
    public final String b;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<CRPSPO2Formatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.CRPSPO2Formatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, CRPSPO2Formatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3304a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, CRPSPO2Formatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public CRPSPO2Formatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new CRPSPO2Formatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3304a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CRPSPO2Formatter(Context context) {
        this.f3303a = context;
        this.b = CRPSPO2Formatter.class.getSimpleName();
    }

    public /* synthetic */ CRPSPO2Formatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final ArrayList<ReadManualSpo2Response> convertSPO2Data(@NotNull String macAddress, @NotNull List<? extends CRPHistoryBloodOxygenInfo> crpBloodOxygenInfos) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Intrinsics.checkNotNullParameter(crpBloodOxygenInfos, "crpBloodOxygenInfos");
        ArrayList<ReadManualSpo2Response> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        for (CRPHistoryBloodOxygenInfo cRPHistoryBloodOxygenInfo : crpBloodOxygenInfos) {
            ManualSpo2Reading manualSpo2Reading = new ManualSpo2Reading();
            manualSpo2Reading.setSpo2(cRPHistoryBloodOxygenInfo.getBo());
            manualSpo2Reading.setTimeStamp(cRPHistoryBloodOxygenInfo.getDate().getTime());
            arrayList2.add(manualSpo2Reading);
        }
        ReadManualSpo2Response readManualSpo2Response = new ReadManualSpo2Response(arrayList2);
        readManualSpo2Response.setComplete(true);
        arrayList.add(readManualSpo2Response);
        return arrayList;
    }

    @NotNull
    public final Context getContext() {
        return this.f3303a;
    }

    public final String getTAG() {
        return this.b;
    }
}
