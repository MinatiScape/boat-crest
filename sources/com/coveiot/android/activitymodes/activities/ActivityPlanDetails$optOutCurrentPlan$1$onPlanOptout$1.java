package com.coveiot.android.activitymodes.activities;

import android.content.Intent;
import com.coveiot.android.activitymodes.eventmodels.PlanDeleted;
import com.coveiot.android.activitymodes.repository.PreparationPlanRepository;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.utils.CoveEventBusManager;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class ActivityPlanDetails$optOutCurrentPlan$1$onPlanOptout$1 implements PreparationPlanRepository.PlanDetailsListner {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityPlanDetails f2708a;

    public ActivityPlanDetails$optOutCurrentPlan$1$onPlanOptout$1(ActivityPlanDetails activityPlanDetails) {
        this.f2708a = activityPlanDetails;
    }

    public static final void b() {
        CoveEventBusManager.getInstance().getEventBus().post(new PlanDeleted());
    }

    @Override // com.coveiot.android.activitymodes.repository.PreparationPlanRepository.PlanDetailsListner
    public void onFailure(@NotNull String mesaage) {
        Intrinsics.checkNotNullParameter(mesaage, "mesaage");
    }

    @Override // com.coveiot.android.activitymodes.repository.PreparationPlanRepository.PlanDetailsListner
    public void onPlanFetchedSuccessfully() {
        this.f2708a.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activities.o1
            @Override // java.lang.Runnable
            public final void run() {
                ActivityPlanDetails$optOutCurrentPlan$1$onPlanOptout$1.b();
            }
        });
        this.f2708a.dismissProgress();
        Intent intent = new Intent();
        intent.putExtra(WorkoutConstants.END_FITNESS_PLAN, true);
        this.f2708a.setResult(-1, intent);
        this.f2708a.finish();
    }
}
