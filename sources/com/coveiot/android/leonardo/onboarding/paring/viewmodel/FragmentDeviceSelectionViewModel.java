package com.coveiot.android.leonardo.onboarding.paring.viewmodel;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import androidx.lifecycle.ViewModel;
import com.coveiot.covepreferences.SessionManager;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentDeviceSelectionViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5259a;
    public final long b;
    @NotNull
    public final SensorManager c;
    public final Sensor d;

    public FragmentDeviceSelectionViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5259a = context;
        this.b = 5L;
        Object systemService = context.getSystemService("sensor");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.SensorManager");
        SensorManager sensorManager = (SensorManager) systemService;
        this.c = sensorManager;
        this.d = sensorManager.getDefaultSensor(19);
    }

    @NotNull
    public final Context getContext() {
        return this.f5259a;
    }

    public final long getSCHEDULER_INTERVAL() {
        return this.b;
    }

    @NotNull
    public final SensorManager getSensorManager() {
        return this.c;
    }

    public final Sensor getStepCounterSensor() {
        return this.d;
    }

    public final void startPedoMeterSenser() {
        this.c.registerListener(new SensorEventListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.FragmentDeviceSelectionViewModel$startPedoMeterSenser$1
            @Override // android.hardware.SensorEventListener
            public void onAccuracyChanged(@Nullable Sensor sensor, int i) {
            }

            @Override // android.hardware.SensorEventListener
            public void onSensorChanged(@NotNull SensorEvent event) {
                Intrinsics.checkNotNullParameter(event, "event");
                FragmentDeviceSelectionViewModel.this.getSensorManager().unregisterListener(this);
                float[] fArr = event.values;
                Intrinsics.checkNotNull(fArr);
                float f = fArr[0];
                Context context = FragmentDeviceSelectionViewModel.this.getContext();
                Intrinsics.checkNotNull(context);
                if (SessionManager.getInstance(context).getStepsOfPedometerSensor() == -1.0f) {
                    Context context2 = FragmentDeviceSelectionViewModel.this.getContext();
                    Intrinsics.checkNotNull(context2);
                    SessionManager.getInstance(context2).saveStepsOfPedometerSensor(f);
                }
            }
        }, this.d, 2);
    }
}
