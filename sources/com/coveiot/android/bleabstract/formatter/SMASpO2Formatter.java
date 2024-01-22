package com.coveiot.android.bleabstract.formatter;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.khsmadb.Utils;
import com.coveiot.khsmadb.spo2.KhBleSpO2;
import com.szabh.smable3.entity.BleBloodOxygen;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SMASpO2Formatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3329a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<SMASpO2Formatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.SMASpO2Formatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, SMASpO2Formatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3330a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, SMASpO2Formatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public SMASpO2Formatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SMASpO2Formatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3330a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SMASpO2Formatter(Context context) {
        this.f3329a = context;
    }

    public /* synthetic */ SMASpO2Formatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final Context getContext() {
        return this.f3329a;
    }

    @NotNull
    public final List<KhBleSpO2> getKhBleSpO2(@NotNull String macAddress, @Nullable List<BleBloodOxygen> list) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (BleBloodOxygen bleBloodOxygen : list) {
                Utils utils = Utils.INSTANCE;
                arrayList.add(new KhBleSpO2((int) ((utils.convertSDKTimeToCalendarTillMinute(bleBloodOxygen.getMTime()).getTimeInMillis() - utils.getCalenderFor2000().getTimeInMillis()) / 1000), bleBloodOxygen.getMValue(), macAddress, null, 8, null));
            }
        }
        return arrayList;
    }
}
