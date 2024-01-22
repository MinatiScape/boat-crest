package com.coveiot.android.dashboard2.fragment;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.android.activitymodes.utils.PlanStatus;
import com.coveiot.android.dashboard2.databinding.FragmentHomeBinding;
import com.coveiot.android.dashboard2.model.DoMoreWithYourWatchDataModel;
import com.coveiot.android.dashboard2.model.DoMoreWithYourWatchFeatureType;
import com.coveiot.android.dashboard2.uihelper.FragmentHomeHelper;
import java.util.Collection;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$registerFitnessPlanLiveData$1$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class FragmentHome$registerFitnessPlanLiveData$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ PlanStatus $it;
    public int label;
    public final /* synthetic */ FragmentHome this$0;

    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PlanStatus.values().length];
            try {
                iArr[PlanStatus.ONGOING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PlanStatus.UPCOMING.ordinal()] = 2;
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
    public FragmentHome$registerFitnessPlanLiveData$1$1(PlanStatus planStatus, FragmentHome fragmentHome, Continuation<? super FragmentHome$registerFitnessPlanLiveData$1$1> continuation) {
        super(2, continuation);
        this.$it = planStatus;
        this.this$0 = fragmentHome;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new FragmentHome$registerFitnessPlanLiveData$1$1(this.$it, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((FragmentHome$registerFitnessPlanLiveData$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        List mutableList;
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            PlanStatus planStatus = this.$it;
            int i = 0;
            if (planStatus == null) {
                this.this$0.S = false;
                this.this$0.q0().setIsFitnessPlanOngoing(Boxing.boxBoolean(false));
            } else {
                int i2 = WhenMappings.$EnumSwitchMapping$0[planStatus.ordinal()];
                boolean z = true;
                if (i2 == 1) {
                    this.this$0.S = false;
                    FragmentHome fragmentHome = this.this$0;
                    ConstraintLayout constraintLayout = fragmentHome.q0().fitnessJourneyOngoing.clUpcoming;
                    Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.fitnessJourneyOngoing.clUpcoming");
                    fragmentHome.gone(constraintLayout);
                    FragmentHome fragmentHome2 = this.this$0;
                    ConstraintLayout constraintLayout2 = fragmentHome2.q0().fitnessJourneyOngoing.clOngoing;
                    Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.fitnessJourneyOngoing.clOngoing");
                    fragmentHome2.visible(constraintLayout2);
                    this.this$0.q0().setIsFitnessPlanOngoing(Boxing.boxBoolean(true));
                    this.this$0.q0().doMoreWithYourWatchCardContainer.setShowFitnessPlan(Boxing.boxBoolean(false));
                    List<DoMoreWithYourWatchDataModel> doMoreWithYourWatchGridDataModels = this.this$0.getDoMoreWithYourWatchGridDataModels();
                    mutableList = doMoreWithYourWatchGridDataModels != null ? CollectionsKt___CollectionsKt.toMutableList((Collection) doMoreWithYourWatchGridDataModels) : null;
                    if (mutableList != null && !mutableList.isEmpty()) {
                        z = false;
                    }
                    if (!z) {
                        FragmentHome fragmentHome3 = this.this$0;
                        for (Object obj2 : mutableList) {
                            int i3 = i + 1;
                            if (i < 0) {
                                CollectionsKt__CollectionsKt.throwIndexOverflow();
                            }
                            DoMoreWithYourWatchDataModel doMoreWithYourWatchDataModel = (DoMoreWithYourWatchDataModel) obj2;
                            if (doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType() != null) {
                                DoMoreWithYourWatchFeatureType doMoreWithYourWatchType = doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType();
                                Intrinsics.checkNotNull(doMoreWithYourWatchType);
                                if (Intrinsics.areEqual(doMoreWithYourWatchType.name(), "BUILD_FITNESS_PLAN")) {
                                    fragmentHome3.getDoMoreWithYourWatchGridDataModels().remove(doMoreWithYourWatchDataModel);
                                    FragmentHomeHelper fragmentHomeHelper = fragmentHome3.getFragmentHomeHelper();
                                    if (fragmentHomeHelper != null) {
                                        List<DoMoreWithYourWatchDataModel> doMoreWithYourWatchGridDataModels2 = fragmentHome3.getDoMoreWithYourWatchGridDataModels();
                                        FragmentHomeBinding q0 = fragmentHome3.q0();
                                        FragmentActivity requireActivity = fragmentHome3.requireActivity();
                                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                                        fragmentHomeHelper.populateDoMoreWithYourWatchData(doMoreWithYourWatchGridDataModels2, q0, requireActivity, fragmentHome3);
                                    }
                                }
                            }
                            i = i3;
                        }
                    }
                    this.this$0.A2();
                    this.this$0.o2();
                } else if (i2 == 2) {
                    this.this$0.S = true;
                    FragmentHome fragmentHome4 = this.this$0;
                    ConstraintLayout constraintLayout3 = fragmentHome4.q0().fitnessJourneyOngoing.clUpcoming;
                    Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.fitnessJourneyOngoing.clUpcoming");
                    fragmentHome4.visible(constraintLayout3);
                    FragmentHome fragmentHome5 = this.this$0;
                    ConstraintLayout constraintLayout4 = fragmentHome5.q0().fitnessJourneyOngoing.clOngoing;
                    Intrinsics.checkNotNullExpressionValue(constraintLayout4, "binding.fitnessJourneyOngoing.clOngoing");
                    fragmentHome5.gone(constraintLayout4);
                    this.this$0.q0().fitnessJourneyOngoing.tvWeekPlanStatus.setText(PlanStatus.UPCOMING.name());
                    this.this$0.q0().setIsFitnessPlanOngoing(Boxing.boxBoolean(true));
                    this.this$0.q0().doMoreWithYourWatchCardContainer.setShowFitnessPlan(Boxing.boxBoolean(false));
                    List<DoMoreWithYourWatchDataModel> doMoreWithYourWatchGridDataModels3 = this.this$0.getDoMoreWithYourWatchGridDataModels();
                    mutableList = doMoreWithYourWatchGridDataModels3 != null ? CollectionsKt___CollectionsKt.toMutableList((Collection) doMoreWithYourWatchGridDataModels3) : null;
                    if (mutableList != null && !mutableList.isEmpty()) {
                        z = false;
                    }
                    if (!z) {
                        FragmentHome fragmentHome6 = this.this$0;
                        for (Object obj3 : mutableList) {
                            int i4 = i + 1;
                            if (i < 0) {
                                CollectionsKt__CollectionsKt.throwIndexOverflow();
                            }
                            DoMoreWithYourWatchDataModel doMoreWithYourWatchDataModel2 = (DoMoreWithYourWatchDataModel) obj3;
                            if (doMoreWithYourWatchDataModel2.getDoMoreWithYourWatchType() != null) {
                                DoMoreWithYourWatchFeatureType doMoreWithYourWatchType2 = doMoreWithYourWatchDataModel2.getDoMoreWithYourWatchType();
                                Intrinsics.checkNotNull(doMoreWithYourWatchType2);
                                if (Intrinsics.areEqual(doMoreWithYourWatchType2.name(), "BUILD_FITNESS_PLAN")) {
                                    fragmentHome6.getDoMoreWithYourWatchGridDataModels().remove(doMoreWithYourWatchDataModel2);
                                    FragmentHomeHelper fragmentHomeHelper2 = fragmentHome6.getFragmentHomeHelper();
                                    if (fragmentHomeHelper2 != null) {
                                        List<DoMoreWithYourWatchDataModel> doMoreWithYourWatchGridDataModels4 = fragmentHome6.getDoMoreWithYourWatchGridDataModels();
                                        FragmentHomeBinding q02 = fragmentHome6.q0();
                                        FragmentActivity requireActivity2 = fragmentHome6.requireActivity();
                                        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                                        fragmentHomeHelper2.populateDoMoreWithYourWatchData(doMoreWithYourWatchGridDataModels4, q02, requireActivity2, fragmentHome6);
                                    }
                                }
                            }
                            i = i4;
                        }
                    }
                    this.this$0.A2();
                    this.this$0.o2();
                } else if (i2 != 3) {
                    this.this$0.S = false;
                    this.this$0.q0().setIsFitnessPlanOngoing(Boxing.boxBoolean(false));
                } else {
                    this.this$0.S = false;
                    this.this$0.q0().setIsFitnessPlanOngoing(Boxing.boxBoolean(false));
                    this.this$0.n0();
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
