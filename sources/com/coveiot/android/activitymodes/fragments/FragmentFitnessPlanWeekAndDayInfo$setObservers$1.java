package com.coveiot.android.activitymodes.fragments;

import androidx.fragment.app.FragmentActivity;
import com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding;
import com.coveiot.android.activitymodes.utils.PlanStatus;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentFitnessPlanWeekAndDayInfo$setObservers$1 extends Lambda implements Function1<PlanStatus, Unit> {
    public final /* synthetic */ FragmentFitnessPlanWeekAndDayInfo this$0;

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PlanStatus.values().length];
            try {
                iArr[PlanStatus.UPCOMING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PlanStatus.ONGOING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PlanStatus.ENDED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentFitnessPlanWeekAndDayInfo$setObservers$1(FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo) {
        super(1);
        this.this$0 = fragmentFitnessPlanWeekAndDayInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(FragmentFitnessPlanWeekAndDayInfo this$0, PlanStatus planStatus) {
        boolean z;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        z = this$0.y;
        if (z || planStatus == null) {
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[planStatus.ordinal()];
        FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding = null;
        if (i == 1) {
            FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding2 = this$0.m;
            if (fragmentFitnessPlanWeekDayInfoBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentFitnessPlanWeekDayInfoBinding = fragmentFitnessPlanWeekDayInfoBinding2;
            }
            fragmentFitnessPlanWeekDayInfoBinding.setIsPlanStartsTomorrow(Boolean.TRUE);
        } else if (i != 2) {
            if (i != 3) {
                return;
            }
            this$0.H();
        } else {
            FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding3 = this$0.m;
            if (fragmentFitnessPlanWeekDayInfoBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentFitnessPlanWeekDayInfoBinding3 = null;
            }
            Boolean bool = Boolean.FALSE;
            fragmentFitnessPlanWeekDayInfoBinding3.setIsPlanCompleted(bool);
            FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding4 = this$0.m;
            if (fragmentFitnessPlanWeekDayInfoBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentFitnessPlanWeekDayInfoBinding = fragmentFitnessPlanWeekDayInfoBinding4;
            }
            fragmentFitnessPlanWeekDayInfoBinding.setIsPlanStartsTomorrow(bool);
        }
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(PlanStatus planStatus) {
        invoke2(planStatus);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@Nullable final PlanStatus planStatus) {
        FragmentActivity requireActivity = this.this$0.requireActivity();
        final FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo = this.this$0;
        requireActivity.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.fragments.s0
            @Override // java.lang.Runnable
            public final void run() {
                FragmentFitnessPlanWeekAndDayInfo$setObservers$1.invoke$lambda$0(FragmentFitnessPlanWeekAndDayInfo.this, planStatus);
            }
        });
    }
}
