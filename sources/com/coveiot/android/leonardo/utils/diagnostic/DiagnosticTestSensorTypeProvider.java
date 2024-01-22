package com.coveiot.android.leonardo.utils.diagnostic;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.SensorType;
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
public final class DiagnosticTestSensorTypeProvider {
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
        public final List<DiagnosticTestModel> getSensorTestModels(@NotNull Context context) {
            BleApi bleApi;
            DeviceSupportedFeatures deviceSupportedFeatures;
            Intrinsics.checkNotNullParameter(context, "context");
            ArrayList arrayList = new ArrayList();
            BleApiManager bleApiManager = BleApiManager.getInstance(context);
            List<SensorType> sensorsSupported = (bleApiManager == null || (bleApi = bleApiManager.getBleApi()) == null || (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) == null) ? null : deviceSupportedFeatures.getSensorsSupported();
            if (sensorsSupported != null) {
                int i = 0;
                for (Object obj : sensorsSupported) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    SensorType sensorType = (SensorType) obj;
                    int type = sensorType.getType();
                    if (type == SensorType.ACCELEROMETER.getType()) {
                        int id = WatchDiagnosticFeatureType.SENSOR_TEST.getId();
                        String string = context.getString(R.string.accelerometer_sensor_test);
                        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…ccelerometer_sensor_test)");
                        arrayList.add(new DiagnosticTestModel(id, string, null, false, sensorType.getType(), null, 44, null));
                    } else if (type == SensorType.PPG.getType()) {
                        int id2 = WatchDiagnosticFeatureType.SENSOR_TEST.getId();
                        String string2 = context.getString(R.string.ppg_sensor_test);
                        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.ppg_sensor_test)");
                        arrayList.add(new DiagnosticTestModel(id2, string2, null, false, sensorType.getType(), null, 44, null));
                    } else if (type == SensorType.TEMPERATURE.getType()) {
                        int id3 = WatchDiagnosticFeatureType.SENSOR_TEST.getId();
                        String string3 = context.getString(R.string.temperature_sensor_test);
                        Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.stri….temperature_sensor_test)");
                        arrayList.add(new DiagnosticTestModel(id3, string3, null, false, sensorType.getType(), null, 44, null));
                    } else if (type == SensorType.ALL.getType()) {
                        int id4 = WatchDiagnosticFeatureType.SENSOR_TEST.getId();
                        String string4 = context.getString(R.string.all_sensor_test);
                        Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.all_sensor_test)");
                        arrayList.add(new DiagnosticTestModel(id4, string4, null, false, sensorType.getType(), null, 44, null));
                    }
                    i = i2;
                }
            }
            return arrayList;
        }
    }
}
