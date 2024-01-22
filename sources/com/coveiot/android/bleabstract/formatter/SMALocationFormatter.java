package com.coveiot.android.bleabstract.formatter;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.khsmadb.location.KhLocation;
import com.szabh.smable3.entity.BleLocation;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SMALocationFormatter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3325a;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<SMALocationFormatter, Context> {

        /* renamed from: com.coveiot.android.bleabstract.formatter.SMALocationFormatter$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, SMALocationFormatter> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3326a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, SMALocationFormatter.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public SMALocationFormatter invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SMALocationFormatter(p0, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3326a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public SMALocationFormatter(Context context) {
        this.f3325a = context;
    }

    public /* synthetic */ SMALocationFormatter(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final Context getContext() {
        return this.f3325a;
    }

    @NotNull
    public final List<KhLocation> getKhBleLocation(@NotNull String macAddrees, @Nullable List<BleLocation> list) {
        Intrinsics.checkNotNullParameter(macAddrees, "macAddrees");
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (BleLocation bleLocation : list) {
                arrayList.add(new KhLocation(bleLocation.getMTime(), bleLocation.getMActivityMode(), bleLocation.getMAltitude(), bleLocation.getMLongitude(), bleLocation.getMLatitude(), macAddrees));
            }
        }
        return arrayList;
    }
}
