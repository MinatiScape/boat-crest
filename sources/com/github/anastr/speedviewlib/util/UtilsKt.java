package com.github.anastr.speedviewlib.util;

import com.clevertap.android.sdk.Constants;
import com.github.anastr.speedviewlib.Gauge;
import com.github.anastr.speedviewlib.components.Section;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a-\u0010\b\u001a\u00020\u0006*\u00020\u00002!\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001\u001a\u0016\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t*n\u0010\u0012\"4\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00110\r24\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00110\r*r\u0010\u0015\"6\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0013\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00060\r26\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0013\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00060\r*\u0094\u0001\u0010\u001b\"G\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00060\u00162G\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00060\u0016¨\u0006\u001c"}, d2 = {"Lcom/github/anastr/speedviewlib/Gauge;", "Lkotlin/Function1;", "Lcom/github/anastr/speedviewlib/components/Section;", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "section", "", Constants.KEY_ACTION, "doOnSections", "", "a", "d", "getRoundAngle", "Lkotlin/Function2;", "", "tickPosition", "tick", "", "OnPrintTickLabelListener", "previousSection", "newSection", "OnSectionChangeListener", "Lkotlin/Function3;", "gauge", "", "isSpeedUp", "isByTremble", "OnSpeedChangeListener", "speedviewlib_release"}, k = 2, mv = {1, 5, 1})
/* loaded from: classes9.dex */
public final class UtilsKt {
    public static final void doOnSections(@NotNull Gauge gauge, @NotNull Function1<? super Section, Unit> action) {
        Intrinsics.checkNotNullParameter(gauge, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        ArrayList<Section> arrayList = new ArrayList(gauge.getSections());
        gauge.clearSections();
        for (Section it : arrayList) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            action.invoke(it);
        }
        gauge.addSections(arrayList);
    }

    public static final float getRoundAngle(float f, float f2) {
        return (float) (((f * 0.5f) * 360) / (f2 * 3.141592653589793d));
    }
}
