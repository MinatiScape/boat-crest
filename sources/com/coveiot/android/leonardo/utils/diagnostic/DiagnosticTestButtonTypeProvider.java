package com.coveiot.android.leonardo.utils.diagnostic;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.HButtonType;
import com.coveiot.android.bleabstract.models.WatchDiagnosticFeatureType;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.model.DiagnosticTestModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class DiagnosticTestButtonTypeProvider {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes5.dex */
    public static final class Companion {

        /* loaded from: classes5.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[HButtonType.values().length];
                try {
                    iArr[HButtonType.H2.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[HButtonType.H3.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[HButtonType.H4.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[HButtonType.H5.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final List<DiagnosticTestModel> getHButtonTestModels(@NotNull Context context) {
            BleApi bleApi;
            DeviceSupportedFeatures deviceSupportedFeatures;
            Intrinsics.checkNotNullParameter(context, "context");
            ArrayList arrayList = new ArrayList();
            BleApiManager bleApiManager = BleApiManager.getInstance(context);
            List<HButtonType> hButtonsSupported = (bleApiManager == null || (bleApi = bleApiManager.getBleApi()) == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null) ? null : deviceSupportedFeatures.getHButtonsSupported();
            int i = 0;
            if (!(hButtonsSupported == null || hButtonsSupported.isEmpty()) && (hButtonsSupported.size() != 1 || hButtonsSupported.get(0).getPosition() != HButtonType.H3.getPosition())) {
                for (Object obj : hButtonsSupported) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    int i3 = WhenMappings.$EnumSwitchMapping$0[((HButtonType) obj).ordinal()];
                    if (i3 == 1) {
                        int id = WatchDiagnosticFeatureType.BUTTON_TEST.getId();
                        String string = context.getString(R.string.h2_button_test);
                        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.h2_button_test)");
                        arrayList.add(new DiagnosticTestModel(id, string, null, false, HButtonType.H2.getPosition(), null, 44, null));
                    } else if (i3 == 2) {
                        int id2 = WatchDiagnosticFeatureType.BUTTON_TEST.getId();
                        String string2 = context.getString(R.string.h3_button_test);
                        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.h3_button_test)");
                        arrayList.add(new DiagnosticTestModel(id2, string2, null, false, HButtonType.H3.getPosition(), null, 44, null));
                    } else if (i3 == 3) {
                        int id3 = WatchDiagnosticFeatureType.BUTTON_TEST.getId();
                        String string3 = context.getString(R.string.h4_button_test);
                        Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.h4_button_test)");
                        arrayList.add(new DiagnosticTestModel(id3, string3, null, false, HButtonType.H4.getPosition(), null, 44, null));
                    } else if (i3 == 4) {
                        int id4 = WatchDiagnosticFeatureType.BUTTON_TEST.getId();
                        String string4 = context.getString(R.string.h5_button_test);
                        Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.h5_button_test)");
                        arrayList.add(new DiagnosticTestModel(id4, string4, null, false, HButtonType.H5.getPosition(), null, 44, null));
                    }
                    i = i2;
                }
            } else {
                int id5 = WatchDiagnosticFeatureType.BUTTON_TEST.getId();
                String string5 = context.getString(R.string.button_test);
                Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.string.button_test)");
                arrayList.add(new DiagnosticTestModel(id5, string5, null, false, HButtonType.H3.getPosition(), null, 44, null));
            }
            return arrayList;
        }
    }
}
