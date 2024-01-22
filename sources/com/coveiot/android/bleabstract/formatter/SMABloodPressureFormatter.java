package com.coveiot.android.bleabstract.formatter;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.khsmadb.bp.KhBleBloodPressure;
import com.szabh.smable3.entity.BleBloodPressure;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SMABloodPressureFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3319a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<SMABloodPressureFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.SMABloodPressureFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, SMABloodPressureFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3320a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, SMABloodPressureFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public SMABloodPressureFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SMABloodPressureFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3320a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SMABloodPressureFormatter(Context context) {
        this.f3319a = context;
    }

    public /* synthetic */ SMABloodPressureFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final Context getContext() {
        return this.f3319a;
    }

    @NotNull
    public final List<KhBleBloodPressure> getKhBleBloodPressure(@NotNull String macAddress, @Nullable List<BleBloodPressure> list) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (BleBloodPressure bleBloodPressure : list) {
                arrayList.add(new KhBleBloodPressure(bleBloodPressure.getMTime(), bleBloodPressure.getMSystolic(), bleBloodPressure.getMDiastolic(), macAddress, null, 16, null));
            }
        }
        return arrayList;
    }
}
