package com.coveiot.android.leonardo.utils.diagnostic;

import android.content.Context;
import com.coveiot.android.bleabstract.models.WatchDiagnosticFeatureType;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.model.DiagnosticTestModel;
import com.coveiot.sdk.ble.utils.BleUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class DiagnosticTestDisplayColorProvider {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final List<DiagnosticTestModel> getDisplayTestModels(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            ArrayList arrayList = new ArrayList();
            int id = WatchDiagnosticFeatureType.DISPLAY_TEST.getId();
            String string = context.getString(R.string.display_test);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.display_test)");
            arrayList.add(new DiagnosticTestModel(id, string, null, false, 0, CollectionsKt__CollectionsKt.mutableListOf(Integer.valueOf(BleUtils.convertHexTo565("#0000ff")), Integer.valueOf(BleUtils.convertHexTo565("#00ff00")), Integer.valueOf(BleUtils.convertHexTo565("#ff0000")), Integer.valueOf(BleUtils.convertHexTo565("#ffffff"))), 28, null));
            return arrayList;
        }
    }
}
