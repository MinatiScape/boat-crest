package com.coveiot.android.leonardo.dashboard.health.viewmodel;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import com.coveiot.android.leonardo.dashboard.model.StressInsightModel;
import com.coveiot.covedb.stress.entity.DailyStress;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
/* loaded from: classes3.dex */
public final class FragmentStressPeriodicViewModel$loadDailyData$2 implements Observer<DailyStress> {
    public final /* synthetic */ Ref.IntRef h;
    public final /* synthetic */ FragmentStressPeriodicViewModel i;

    public FragmentStressPeriodicViewModel$loadDailyData$2(Ref.IntRef intRef, FragmentStressPeriodicViewModel fragmentStressPeriodicViewModel) {
        this.h = intRef;
        this.i = fragmentStressPeriodicViewModel;
    }

    public static final void b(StressInsightModel stressInsight, Ref.IntRef currentStress, Ref.IntRef previousStress, FragmentStressPeriodicViewModel this$0) {
        Intrinsics.checkNotNullParameter(stressInsight, "$stressInsight");
        Intrinsics.checkNotNullParameter(currentStress, "$currentStress");
        Intrinsics.checkNotNullParameter(previousStress, "$previousStress");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        stressInsight.setStressDifference(Integer.valueOf(Math.abs(currentStress.element - previousStress.element)));
        stressInsight.setStressIncreased(currentStress.element > previousStress.element);
        this$0.getStressInsightData().postValue(stressInsight);
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable @org.jetbrains.annotations.Nullable DailyStress dailyStress) {
        final StressInsightModel stressInsightModel = new StressInsightModel(null, false, 3, null);
        final Ref.IntRef intRef = new Ref.IntRef();
        if (dailyStress != null) {
            intRef.element = (int) dailyStress.stress_avg;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        final Ref.IntRef intRef2 = this.h;
        final FragmentStressPeriodicViewModel fragmentStressPeriodicViewModel = this.i;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.health.viewmodel.d
            @Override // java.lang.Runnable
            public final void run() {
                FragmentStressPeriodicViewModel$loadDailyData$2.b(StressInsightModel.this, intRef2, intRef, fragmentStressPeriodicViewModel);
            }
        }, 500L);
    }
}
