package com.coveiot.android.bleabstract.formatter.eastapex;

import android.content.Context;
import com.apex.bluetooth.model.EABleBloodOxygen;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ManualSpo2Reading;
import com.coveiot.android.bleabstract.response.ReadManualSpo2Response;
import com.coveiot.kheastapexdb.spo2.EntityEASpO2Data;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class EastApexSpo2Formatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3346a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<EastApexSpo2Formatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.eastapex.EastApexSpo2Formatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, EastApexSpo2Formatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3347a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, EastApexSpo2Formatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public EastApexSpo2Formatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new EastApexSpo2Formatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3347a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public EastApexSpo2Formatter(Context context) {
        this.f3346a = context;
    }

    public /* synthetic */ EastApexSpo2Formatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Nullable
    public final ArrayList<EntityEASpO2Data> convertEASpo2DataToEntity(@Nullable List<? extends EABleBloodOxygen> list) {
        if (list != null) {
            ArrayList<EntityEASpO2Data> arrayList = new ArrayList<>();
            Iterator<? extends EABleBloodOxygen> it = list.iterator();
            while (it.hasNext()) {
                EABleBloodOxygen next = it.next();
                Long valueOf = next != null ? Long.valueOf(next.time_stamp) : null;
                Intrinsics.checkNotNull(valueOf);
                EntityEASpO2Data entityEASpO2Data = new EntityEASpO2Data(valueOf.longValue(), BleApiManager.getInstance(this.f3346a).getBleApi().getMacAddress());
                entityEASpO2Data.setSpo2Value(next.blood_oxygen_value);
                entityEASpO2Data.setTimeStamp(next.time_stamp * 1000);
                arrayList.add(entityEASpO2Data);
            }
            return arrayList;
        }
        return null;
    }

    @NotNull
    public final ReadManualSpo2Response convertSpO2Data(@NotNull List<EntityEASpO2Data> spO2List) {
        Intrinsics.checkNotNullParameter(spO2List, "spO2List");
        ArrayList arrayList = new ArrayList();
        for (EntityEASpO2Data entityEASpO2Data : spO2List) {
            ManualSpo2Reading manualSpo2Reading = new ManualSpo2Reading();
            manualSpo2Reading.setSpo2(entityEASpO2Data.getSpo2Value());
            manualSpo2Reading.setTimeStamp(entityEASpO2Data.getTimeStamp());
            arrayList.add(manualSpo2Reading);
        }
        ReadManualSpo2Response readManualSpo2Response = new ReadManualSpo2Response(arrayList);
        readManualSpo2Response.setComplete(true);
        return readManualSpo2Response;
    }

    @NotNull
    public final Context getContext() {
        return this.f3346a;
    }
}
