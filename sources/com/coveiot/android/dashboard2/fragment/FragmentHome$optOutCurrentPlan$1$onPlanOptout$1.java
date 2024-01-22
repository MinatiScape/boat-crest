package com.coveiot.android.dashboard2.fragment;

import com.coveiot.android.activitymodes.eventmodels.PlanDeleted;
import com.coveiot.android.activitymodes.repository.PreparationPlanRepository;
import com.coveiot.utils.CoveEventBusManager;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class FragmentHome$optOutCurrentPlan$1$onPlanOptout$1 implements PreparationPlanRepository.PlanDetailsListner {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FragmentHome f4236a;

    public FragmentHome$optOutCurrentPlan$1$onPlanOptout$1(FragmentHome fragmentHome) {
        this.f4236a = fragmentHome;
    }

    public static final void b() {
        CoveEventBusManager.getInstance().getEventBus().post(new PlanDeleted());
    }

    @Override // com.coveiot.android.activitymodes.repository.PreparationPlanRepository.PlanDetailsListner
    public void onFailure(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.f4236a.dismissProgress();
    }

    @Override // com.coveiot.android.activitymodes.repository.PreparationPlanRepository.PlanDetailsListner
    public void onPlanFetchedSuccessfully() {
        this.f4236a.requireActivity().runOnUiThread(new Runnable() { // from class: com.coveiot.android.dashboard2.fragment.e1
            @Override // java.lang.Runnable
            public final void run() {
                FragmentHome$optOutCurrentPlan$1$onPlanOptout$1.b();
            }
        });
        this.f4236a.dismissProgress();
    }
}
