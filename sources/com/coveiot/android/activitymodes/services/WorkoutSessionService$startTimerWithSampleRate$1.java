package com.coveiot.android.activitymodes.services;

import android.os.Handler;
import android.util.Log;
import com.coveiot.android.activitymodes.models.HeartRateSample;
import com.coveiot.android.activitymodes.models.LocationSample;
import com.coveiot.android.activitymodes.models.StepsSample;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes2.dex */
public final class WorkoutSessionService$startTimerWithSampleRate$1 extends TimerTask {
    public final /* synthetic */ WorkoutSessionService h;
    public final /* synthetic */ PreferenceManager i;

    public WorkoutSessionService$startTimerWithSampleRate$1(WorkoutSessionService workoutSessionService, PreferenceManager preferenceManager) {
        this.h = workoutSessionService;
        this.i = preferenceManager;
    }

    public static final void c(WorkoutSessionService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.y();
        this$0.a();
    }

    public static final void d(WorkoutSessionService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.x();
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        int i;
        int i2;
        int i3;
        Handler handler;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        String str;
        String str2;
        int i9;
        ActivityMode activityMode;
        ActivityMode activityMode2;
        int i10;
        LocationSample locationSample;
        LocationSample locationSample2;
        String g;
        Handler handler2;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        WorkoutSessionService workoutSessionService = this.h;
        i = workoutSessionService.D;
        workoutSessionService.D = i + 1;
        WorkoutSessionService workoutSessionService2 = this.h;
        i2 = workoutSessionService2.E;
        workoutSessionService2.E = i2 + 1;
        i3 = this.h.E;
        Handler handler3 = null;
        if (i3 % 5 == 0) {
            long currentTimeInSecs = WorkoutUtils.INSTANCE.getCurrentTimeInSecs();
            WorkoutSessionService workoutSessionService3 = this.h;
            i4 = workoutSessionService3.Q;
            i5 = this.h.y;
            workoutSessionService3.Q = i4 + i5;
            WorkoutSessionService workoutSessionService4 = this.h;
            i6 = workoutSessionService4.R;
            workoutSessionService4.R = i6 + 1;
            List<HeartRateSample> hrSamples = this.h.getHrSamples();
            i7 = this.h.y;
            hrSamples.add(new HeartRateSample(i7, currentTimeInSecs));
            int i16 = 0;
            i8 = this.h.z;
            if (i8 != 0) {
                i12 = this.h.k;
                i13 = this.h.z;
                if (i12 < i13) {
                    i14 = this.h.z;
                    i15 = this.h.k;
                    i16 = i14 - i15;
                }
            }
            str = this.h.j;
            Log.d(str, "Sample Steps: " + i16);
            str2 = this.h.j;
            StringBuilder sb = new StringBuilder();
            sb.append("Previous Steps: ");
            i9 = this.h.k;
            sb.append(i9);
            Log.d(str2, sb.toString());
            int height = this.i.getHeight();
            int weight = this.i.getWeight();
            activityMode = this.h.u;
            float calculateCalories = (float) AppUtils.calculateCalories(i16, height, weight, activityMode.name(), this.h.getWalkStrideLegth(), this.h.getRunStrideLegth());
            int height2 = this.i.getHeight();
            activityMode2 = this.h.u;
            this.h.getStepsSamples().add(new StepsSample(i16, calculateCalories, AppUtils.calculateDistanceInCentimeters(i16, height2, activityMode2.name(), this.h.getWalkStrideLegth(), this.h.getRunStrideLegth()), currentTimeInSecs));
            i10 = this.h.z;
            if (i10 != 0) {
                WorkoutSessionService workoutSessionService5 = this.h;
                i11 = workoutSessionService5.z;
                workoutSessionService5.k = i11;
            }
            if (i16 > 0) {
                this.h.h = System.currentTimeMillis();
            }
            locationSample = this.h.S;
            if (locationSample != null) {
                locationSample.setTimestamp(currentTimeInSecs);
            }
            ArrayList<LocationSample> locationSamples = this.h.getLocationSamples();
            locationSample2 = this.h.S;
            locationSamples.add(locationSample2);
            WorkoutSessionService workoutSessionService6 = this.h;
            g = workoutSessionService6.g();
            workoutSessionService6.C = g;
            handler2 = this.h.l;
            if (handler2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uiUpdateHandler");
                handler2 = null;
            }
            final WorkoutSessionService workoutSessionService7 = this.h;
            handler2.post(new Runnable() { // from class: com.coveiot.android.activitymodes.services.a
                @Override // java.lang.Runnable
                public final void run() {
                    WorkoutSessionService$startTimerWithSampleRate$1.c(WorkoutSessionService.this);
                }
            });
        }
        handler = this.h.l;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uiUpdateHandler");
        } else {
            handler3 = handler;
        }
        final WorkoutSessionService workoutSessionService8 = this.h;
        handler3.post(new Runnable() { // from class: com.coveiot.android.activitymodes.services.b
            @Override // java.lang.Runnable
            public final void run() {
                WorkoutSessionService$startTimerWithSampleRate$1.d(WorkoutSessionService.this);
            }
        });
    }
}
