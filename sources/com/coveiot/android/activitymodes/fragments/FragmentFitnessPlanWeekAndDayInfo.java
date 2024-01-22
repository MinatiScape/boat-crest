package com.coveiot.android.activitymodes.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activities.ActivityFitnessPlan;
import com.coveiot.android.activitymodes.activities.PlanDetailsViewModel;
import com.coveiot.android.activitymodes.adapters.FitnessPlanDayAdapter;
import com.coveiot.android.activitymodes.adapters.FitnessPlanSelectedDayGoalAdapter;
import com.coveiot.android.activitymodes.adapters.FitnessPlanWeekAdapter;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding;
import com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo;
import com.coveiot.android.activitymodes.models.ActivityInfo;
import com.coveiot.android.activitymodes.models.CurrentWeekAndDay;
import com.coveiot.android.activitymodes.models.DayInfo;
import com.coveiot.android.activitymodes.models.FitnessPlanData;
import com.coveiot.android.activitymodes.models.WeekInfo;
import com.coveiot.android.activitymodes.repository.WorkoutSessionRepository;
import com.coveiot.android.activitymodes.utils.PlanStatus;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard;
import com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseClass;
import com.coveiot.coveaccess.model.server.FitnessPlanTemplateRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import com.jstyle.blesdk1860.constant.BleConst;
import com.touchgui.sdk.TGEventListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentFitnessPlanWeekAndDayInfo extends BaseFragment implements FitnessPlanWeekAdapter.FitnessWeekClickListener, FitnessPlanDayAdapter.FitnessDayClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public double B;
    public boolean C;
    public FragmentFitnessPlanWeekDayInfoBinding m;
    public ViewModelCurrentPlanDashboard n;
    public ViewModelWorkoutFeedback o;
    public FitnessPlanWeekAdapter p;
    public FitnessPlanDayAdapter q;
    public FitnessPlanSelectedDayGoalAdapter r;
    public int s;
    public int t;
    public FitnessPlanData u;
    public CurrentWeekAndDay x;
    public boolean y;
    public boolean z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ArrayList<WeekInfo> v = new ArrayList<>();
    @NotNull
    public ArrayList<String> w = new ArrayList<>();
    @NotNull
    public String A = "";

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentFitnessPlanWeekAndDayInfo newInstance(@NotNull FitnessPlanData fitnessPlanData, @NotNull CurrentWeekAndDay currentWeekAndDay, @NotNull String backgroundImage, boolean z) {
            Intrinsics.checkNotNullParameter(fitnessPlanData, "fitnessPlanData");
            Intrinsics.checkNotNullParameter(currentWeekAndDay, "currentWeekAndDay");
            Intrinsics.checkNotNullParameter(backgroundImage, "backgroundImage");
            FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo = new FragmentFitnessPlanWeekAndDayInfo();
            Bundle bundle = new Bundle();
            bundle.putSerializable("fitnessKey", fitnessPlanData);
            bundle.putSerializable("fitnessCurrentWeekDay", currentWeekAndDay);
            bundle.putString("fitnessBackground", backgroundImage);
            bundle.putBoolean("isPlanHistory", z);
            fragmentFitnessPlanWeekAndDayInfo.setArguments(bundle);
            return fragmentFitnessPlanWeekAndDayInfo;
        }
    }

    /* loaded from: classes2.dex */
    public static final class a extends Lambda implements Function1<FitnessPlanTemplateRes.FitnessPlanTemplateData, Unit> {
        public final /* synthetic */ FragmentFitnessPlanWeekDayInfoBinding $this_setCompletedCardData;

        @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$setCompletedCardData$1$1$1$1$1$1", f = "FragmentFitnessPlanWeekAndDayInfo.kt", i = {}, l = {395, 406}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$a$a  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static final class C0239a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ DayInfo $dayInfo;
            public final /* synthetic */ FitnessPlanTemplateRes.FitnessPlanTemplateData.ActivityMapping $tempInfo;
            public final /* synthetic */ FragmentFitnessPlanWeekDayInfoBinding $this_setCompletedCardData;
            public final /* synthetic */ Ref.IntRef $weekCoveredDistance;
            public Object L$0;
            public int label;
            public final /* synthetic */ FragmentFitnessPlanWeekAndDayInfo this$0;

            /* renamed from: com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes2.dex */
            public static final class C0240a extends Lambda implements Function1<Integer, Unit> {
                public final /* synthetic */ FragmentFitnessPlanWeekDayInfoBinding $this_setCompletedCardData;
                public final /* synthetic */ Ref.IntRef $weekCoveredDistance;
                public final /* synthetic */ FragmentFitnessPlanWeekAndDayInfo this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0240a(Ref.IntRef intRef, FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo, FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding) {
                    super(1);
                    this.$weekCoveredDistance = intRef;
                    this.this$0 = fragmentFitnessPlanWeekAndDayInfo;
                    this.$this_setCompletedCardData = fragmentFitnessPlanWeekDayInfoBinding;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    Ref.IntRef intRef = this.$weekCoveredDistance;
                    int i2 = intRef.element + i;
                    intRef.element = i2;
                    this.this$0.w(this.$this_setCompletedCardData, i2);
                }
            }

            /* renamed from: com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$a$a$b */
            /* loaded from: classes2.dex */
            public static final class b extends Lambda implements Function1<Integer, Unit> {
                public final /* synthetic */ FragmentFitnessPlanWeekDayInfoBinding $this_setCompletedCardData;
                public final /* synthetic */ Ref.IntRef $weekCoveredDistance;
                public final /* synthetic */ FragmentFitnessPlanWeekAndDayInfo this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(Ref.IntRef intRef, FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo, FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding) {
                    super(1);
                    this.$weekCoveredDistance = intRef;
                    this.this$0 = fragmentFitnessPlanWeekAndDayInfo;
                    this.$this_setCompletedCardData = fragmentFitnessPlanWeekDayInfoBinding;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    Ref.IntRef intRef = this.$weekCoveredDistance;
                    int i2 = intRef.element + i;
                    intRef.element = i2;
                    this.this$0.w(this.$this_setCompletedCardData, i2);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0239a(FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo, FitnessPlanTemplateRes.FitnessPlanTemplateData.ActivityMapping activityMapping, DayInfo dayInfo, Ref.IntRef intRef, FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding, Continuation<? super C0239a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentFitnessPlanWeekAndDayInfo;
                this.$tempInfo = activityMapping;
                this.$dayInfo = dayInfo;
                this.$weekCoveredDistance = intRef;
                this.$this_setCompletedCardData = fragmentFitnessPlanWeekDayInfoBinding;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0239a(this.this$0, this.$tempInfo, this.$dayInfo, this.$weekCoveredDistance, this.$this_setCompletedCardData, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0239a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x004f  */
            /* JADX WARN: Removed duplicated region for block: B:26:0x0097  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r13) {
                /*
                    r12 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                    int r1 = r12.label
                    r2 = 0
                    java.lang.String r3 = "viewModelWorkoutFeedback"
                    r4 = 2
                    r5 = 1
                    if (r1 == 0) goto L2a
                    if (r1 == r5) goto L22
                    if (r1 != r4) goto L1a
                    java.lang.Object r1 = r12.L$0
                    java.util.Iterator r1 = (java.util.Iterator) r1
                    kotlin.ResultKt.throwOnFailure(r13)
                    goto L90
                L1a:
                    java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r13.<init>(r0)
                    throw r13
                L22:
                    java.lang.Object r1 = r12.L$0
                    java.util.Iterator r1 = (java.util.Iterator) r1
                    kotlin.ResultKt.throwOnFailure(r13)
                    goto L48
                L2a:
                    kotlin.ResultKt.throwOnFailure(r13)
                    com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo r13 = r12.this$0
                    android.content.Context r13 = r13.requireContext()
                    com.coveiot.covepreferences.UserDataManager r13 = com.coveiot.covepreferences.UserDataManager.getInstance(r13)
                    boolean r13 = r13.isOneKSupported()
                    if (r13 == 0) goto L85
                    com.coveiot.coveaccess.model.server.FitnessPlanTemplateRes$FitnessPlanTemplateData$ActivityMapping r13 = r12.$tempInfo
                    java.util.List r13 = r13.getPhysicalActivityCodes()
                    java.util.Iterator r13 = r13.iterator()
                    r1 = r13
                L48:
                    r13 = r12
                L49:
                    boolean r4 = r1.hasNext()
                    if (r4 == 0) goto Lcd
                    java.lang.Object r4 = r1.next()
                    java.lang.String r4 = (java.lang.String) r4
                    com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo r6 = r13.this$0
                    com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback r6 = com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo.access$getViewModelWorkoutFeedback$p(r6)
                    if (r6 != 0) goto L61
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
                    r6 = r2
                L61:
                    java.lang.String r7 = "code"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r7)
                    com.coveiot.android.activitymodes.models.DayInfo r7 = r13.$dayInfo
                    java.lang.String r7 = r7.getDate()
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
                    com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$a$a$a r8 = new com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$a$a$a
                    kotlin.jvm.internal.Ref$IntRef r9 = r13.$weekCoveredDistance
                    com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo r10 = r13.this$0
                    com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding r11 = r13.$this_setCompletedCardData
                    r8.<init>(r9, r10, r11)
                    r13.L$0 = r1
                    r13.label = r5
                    java.lang.Object r4 = r6.getDistanceValueByActivityNCategoryIDs(r4, r7, r8, r13)
                    if (r4 != r0) goto L49
                    return r0
                L85:
                    com.coveiot.coveaccess.model.server.FitnessPlanTemplateRes$FitnessPlanTemplateData$ActivityMapping r13 = r12.$tempInfo
                    java.util.List r13 = r13.getSessionTypes()
                    java.util.Iterator r13 = r13.iterator()
                    r1 = r13
                L90:
                    r13 = r12
                L91:
                    boolean r5 = r1.hasNext()
                    if (r5 == 0) goto Lcd
                    java.lang.Object r5 = r1.next()
                    java.lang.String r5 = (java.lang.String) r5
                    com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo r6 = r13.this$0
                    com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback r6 = com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo.access$getViewModelWorkoutFeedback$p(r6)
                    if (r6 != 0) goto La9
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
                    r6 = r2
                La9:
                    com.coveiot.android.activitymodes.models.DayInfo r7 = r13.$dayInfo
                    java.lang.String r7 = r7.getDate()
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
                    java.lang.String r8 = "type"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r8)
                    com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$a$a$b r8 = new com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$a$a$b
                    kotlin.jvm.internal.Ref$IntRef r9 = r13.$weekCoveredDistance
                    com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo r10 = r13.this$0
                    com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanWeekDayInfoBinding r11 = r13.$this_setCompletedCardData
                    r8.<init>(r9, r10, r11)
                    r13.L$0 = r1
                    r13.label = r4
                    java.lang.Object r5 = r6.getDistanceValueByActivityType(r7, r5, r8, r13)
                    if (r5 != r0) goto L91
                    return r0
                Lcd:
                    kotlin.Unit r13 = kotlin.Unit.INSTANCE
                    return r13
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo.a.C0239a.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$setCompletedCardData$1$2$1$2", f = "FragmentFitnessPlanWeekAndDayInfo.kt", i = {}, l = {448}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ DayInfo $dayInfo;
            public final /* synthetic */ FragmentFitnessPlanWeekDayInfoBinding $this_setCompletedCardData;
            public final /* synthetic */ Ref.IntRef $weekCoveredDistance;
            public int label;
            public final /* synthetic */ FragmentFitnessPlanWeekAndDayInfo this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo, DayInfo dayInfo, Ref.IntRef intRef, FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = fragmentFitnessPlanWeekAndDayInfo;
                this.$dayInfo = dayInfo;
                this.$weekCoveredDistance = intRef;
                this.$this_setCompletedCardData = fragmentFitnessPlanWeekDayInfoBinding;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.this$0, this.$dayInfo, this.$weekCoveredDistance, this.$this_setCompletedCardData, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    WorkoutSessionRepository.Companion companion = WorkoutSessionRepository.Companion;
                    Context requireContext = this.this$0.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    String date = this.$dayInfo.getDate();
                    Intrinsics.checkNotNull(date);
                    this.label = 1;
                    obj = companion.getInstance(requireContext).getSingleDayDistanceWithoutActivityMapping(date, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                int intValue = ((Number) obj).intValue();
                Ref.IntRef intRef = this.$weekCoveredDistance;
                int i2 = intRef.element + intValue;
                intRef.element = i2;
                this.this$0.w(this.$this_setCompletedCardData, i2);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding) {
            super(1);
            this.$this_setCompletedCardData = fragmentFitnessPlanWeekDayInfoBinding;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData) {
            invoke2(fitnessPlanTemplateData);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull FitnessPlanTemplateRes.FitnessPlanTemplateData template) {
            Intrinsics.checkNotNullParameter(template, "template");
            if (FragmentFitnessPlanWeekAndDayInfo.this.isAdded()) {
                Ref.IntRef intRef = new Ref.IntRef();
                if (template.getActivityMappings() != null) {
                    if (FragmentFitnessPlanWeekAndDayInfo.this.v != null) {
                        ArrayList<WeekInfo> arrayList = FragmentFitnessPlanWeekAndDayInfo.this.v;
                        FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo = FragmentFitnessPlanWeekAndDayInfo.this;
                        FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding = this.$this_setCompletedCardData;
                        for (WeekInfo weekInfo : arrayList) {
                            if (weekInfo.getDayList() != null) {
                                for (DayInfo dayInfo : weekInfo.getDayList()) {
                                    if (dayInfo.getActivitylist() != null) {
                                        ArrayList<ActivityInfo> activitylist = dayInfo.getActivitylist();
                                        Intrinsics.checkNotNull(activitylist);
                                        for (ActivityInfo activityInfo : activitylist) {
                                            if (activityInfo.getActivityType() != null) {
                                                List<FitnessPlanTemplateRes.FitnessPlanTemplateData.ActivityMapping> activityMappings = template.getActivityMappings();
                                                Intrinsics.checkNotNullExpressionValue(activityMappings, "template.activityMappings");
                                                for (FitnessPlanTemplateRes.FitnessPlanTemplateData.ActivityMapping activityMapping : activityMappings) {
                                                    String activityType = activityInfo.getActivityType();
                                                    Intrinsics.checkNotNull(activityType);
                                                    if (Intrinsics.areEqual(activityType, activityMapping.getActivityType())) {
                                                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(fragmentFitnessPlanWeekAndDayInfo), null, null, new C0239a(fragmentFitnessPlanWeekAndDayInfo, activityMapping, dayInfo, intRef, fragmentFitnessPlanWeekDayInfoBinding, null), 3, null);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        return;
                    }
                    return;
                }
                ArrayList<WeekInfo> arrayList2 = FragmentFitnessPlanWeekAndDayInfo.this.v;
                FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo2 = FragmentFitnessPlanWeekAndDayInfo.this;
                FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding2 = this.$this_setCompletedCardData;
                for (WeekInfo weekInfo2 : arrayList2) {
                    if (weekInfo2.getDayList() != null) {
                        for (DayInfo dayInfo2 : weekInfo2.getDayList()) {
                            if (dayInfo2.getActivitylist() != null) {
                                ArrayList<ActivityInfo> activitylist2 = dayInfo2.getActivitylist();
                                Intrinsics.checkNotNull(activitylist2);
                                int i = 0;
                                for (ActivityInfo activityInfo2 : activitylist2) {
                                    String target = activityInfo2.getTarget();
                                    if (!(target == null || target.length() == 0) && !kotlin.text.m.equals$default(activityInfo2.getTarget(), BleConst.GetDeviceTime, false, 2, null)) {
                                        String target2 = activityInfo2.getTarget();
                                        Intrinsics.checkNotNull(target2);
                                        i += Integer.parseInt(target2);
                                    }
                                }
                                if (i > 0) {
                                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(fragmentFitnessPlanWeekAndDayInfo2), Dispatchers.getIO(), null, new b(fragmentFitnessPlanWeekAndDayInfo2, dayInfo2, intRef, fragmentFitnessPlanWeekDayInfoBinding2, null), 2, null);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$setDailyGoal$1", f = "FragmentFitnessPlanWeekAndDayInfo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ DayInfo $dayInfo;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(DayInfo dayInfo, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$dayInfo = dayInfo;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$3$lambda$2(FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo, FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding, DayInfo dayInfo) {
            CurrentWeekAndDay currentWeekAndDay = null;
            if (!fragmentFitnessPlanWeekAndDayInfo.z) {
                String date = dayInfo.getDate();
                fragmentFitnessPlanWeekAndDayInfo.q(fragmentFitnessPlanWeekDayInfoBinding, date != null ? Boolean.valueOf(WorkoutUtils.INSTANCE.isSelectedDayIsFutureDay(date)) : null);
            }
            if (dayInfo.getActivitylist() != null) {
                ArrayList<ActivityInfo> activitylist = dayInfo.getActivitylist();
                Intrinsics.checkNotNull(activitylist);
                Iterator<ActivityInfo> it = activitylist.iterator();
                int i = 0;
                while (it.hasNext()) {
                    ActivityInfo next = it.next();
                    if (next.getTarget() != null) {
                        String target = next.getTarget();
                        Intrinsics.checkNotNull(target);
                        i += Integer.parseInt(target);
                    }
                }
                fragmentFitnessPlanWeekDayInfoBinding.setIsRestDay(Boolean.valueOf(i == 0));
            } else {
                fragmentFitnessPlanWeekDayInfoBinding.setIsRestDay(Boolean.TRUE);
            }
            if (dayInfo.getActivitylist() != null) {
                ArrayList<ActivityInfo> arrayList = new ArrayList<>();
                ArrayList<ActivityInfo> activitylist2 = dayInfo.getActivitylist();
                Intrinsics.checkNotNull(activitylist2);
                for (ActivityInfo activityInfo : activitylist2) {
                    String target2 = activityInfo.getTarget();
                    if (!(target2 == null || target2.length() == 0) && !kotlin.text.m.equals$default(activityInfo.getTarget(), BleConst.GetDeviceTime, false, 2, null)) {
                        arrayList.add(activityInfo);
                    }
                }
                FitnessPlanSelectedDayGoalAdapter fitnessPlanSelectedDayGoalAdapter = fragmentFitnessPlanWeekAndDayInfo.r;
                if (fitnessPlanSelectedDayGoalAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanSelectedDayGoalAdapter");
                    fitnessPlanSelectedDayGoalAdapter = null;
                }
                fitnessPlanSelectedDayGoalAdapter.setSelectedDayData(arrayList);
            }
            CurrentWeekAndDay currentWeekAndDay2 = fragmentFitnessPlanWeekAndDayInfo.x;
            if (currentWeekAndDay2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("currentWeekAndDay");
                currentWeekAndDay2 = null;
            }
            if (currentWeekAndDay2.getCurrentWeek() == fragmentFitnessPlanWeekAndDayInfo.t) {
                CurrentWeekAndDay currentWeekAndDay3 = fragmentFitnessPlanWeekAndDayInfo.x;
                if (currentWeekAndDay3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("currentWeekAndDay");
                } else {
                    currentWeekAndDay = currentWeekAndDay3;
                }
                if (Intrinsics.areEqual(currentWeekAndDay.getCurrentDate(), dayInfo.getDate())) {
                    fragmentFitnessPlanWeekDayInfoBinding.tvSelectedDay.setText(fragmentFitnessPlanWeekAndDayInfo.requireContext().getString(R.string.today_s_goal));
                    return;
                }
            }
            fragmentFitnessPlanWeekDayInfoBinding.tvSelectedDay.setText(fragmentFitnessPlanWeekAndDayInfo.requireContext().getString(R.string.day) + ' ' + (fragmentFitnessPlanWeekAndDayInfo.s + 1) + ' ' + fragmentFitnessPlanWeekAndDayInfo.requireContext().getString(R.string.goals));
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$dayInfo, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                final FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding = FragmentFitnessPlanWeekAndDayInfo.this.m;
                if (fragmentFitnessPlanWeekDayInfoBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentFitnessPlanWeekDayInfoBinding = null;
                }
                final DayInfo dayInfo = this.$dayInfo;
                final FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo = FragmentFitnessPlanWeekAndDayInfo.this;
                if (dayInfo != null) {
                    fragmentFitnessPlanWeekAndDayInfo.requireActivity().runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.fragments.r0
                        @Override // java.lang.Runnable
                        public final void run() {
                            FragmentFitnessPlanWeekAndDayInfo.b.invokeSuspend$lambda$3$lambda$2(FragmentFitnessPlanWeekAndDayInfo.this, fragmentFitnessPlanWeekDayInfoBinding, dayInfo);
                        }
                    });
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes2.dex */
    public static final class c extends Lambda implements Function1<FitnessPlanTemplateRes.FitnessPlanTemplateData, Unit> {
        public final /* synthetic */ DayInfo $day;
        public final /* synthetic */ FragmentFitnessPlanWeekAndDayInfo this$0;

        @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$setDaysDistanceValue$1$1", f = "FragmentFitnessPlanWeekAndDayInfo.kt", i = {}, l = {251}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ DayInfo $day;
            public final /* synthetic */ Ref.IntRef $finalCoveredDistance;
            public final /* synthetic */ Ref.IntRef $totalDistance;
            public int label;
            public final /* synthetic */ FragmentFitnessPlanWeekAndDayInfo this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo, DayInfo dayInfo, Ref.IntRef intRef, Ref.IntRef intRef2, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentFitnessPlanWeekAndDayInfo;
                this.$day = dayInfo;
                this.$totalDistance = intRef;
                this.$finalCoveredDistance = intRef2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.this$0, this.$day, this.$totalDistance, this.$finalCoveredDistance, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (this.this$0.isAdded()) {
                        WorkoutSessionRepository.Companion companion = WorkoutSessionRepository.Companion;
                        Context requireContext = this.this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        String date = this.$day.getDate();
                        Intrinsics.checkNotNull(date);
                        this.label = 1;
                        obj = companion.getInstance(requireContext).getSessionsOfParticularDay(date, this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                List<EntityWorkoutSession> list = (List) obj;
                ArrayList<ActivityInfo> activitylist = this.$day.getActivitylist();
                if (activitylist != null) {
                    Ref.IntRef intRef = this.$totalDistance;
                    for (ActivityInfo activityInfo : activitylist) {
                        String target = activityInfo.getTarget();
                        if (!(target == null || target.length() == 0)) {
                            int i2 = intRef.element;
                            String target2 = activityInfo.getTarget();
                            Intrinsics.checkNotNull(target2);
                            intRef.element = i2 + Integer.parseInt(target2);
                        }
                    }
                }
                if (list.isEmpty()) {
                    this.this$0.z(0, this.$totalDistance.element);
                } else {
                    Ref.IntRef intRef2 = this.$finalCoveredDistance;
                    FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo = this.this$0;
                    Ref.IntRef intRef3 = this.$totalDistance;
                    for (EntityWorkoutSession entityWorkoutSession : list) {
                        int total_distance = intRef2.element + entityWorkoutSession.getTotal_distance();
                        intRef2.element = total_distance;
                        fragmentFitnessPlanWeekAndDayInfo.z(total_distance, intRef3.element);
                    }
                }
                return Unit.INSTANCE;
            }
        }

        @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$setDaysDistanceValue$1$2$1", f = "FragmentFitnessPlanWeekAndDayInfo.kt", i = {}, l = {TGEventListener.WORKOUT_START, 305}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ ActivityInfo $activity;
            public final /* synthetic */ DayInfo $day;
            public final /* synthetic */ Ref.IntRef $finalCoveredDistance;
            public final /* synthetic */ FitnessPlanTemplateRes.FitnessPlanTemplateData.ActivityMapping $tempItem;
            public final /* synthetic */ Ref.IntRef $totalDistance;
            public Object L$0;
            public int label;
            public final /* synthetic */ FragmentFitnessPlanWeekAndDayInfo this$0;

            /* loaded from: classes2.dex */
            public static final class a extends Lambda implements Function1<Integer, Unit> {
                public final /* synthetic */ Ref.IntRef $finalCoveredDistance;
                public final /* synthetic */ Ref.IntRef $totalDistance;
                public final /* synthetic */ FragmentFitnessPlanWeekAndDayInfo this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(Ref.IntRef intRef, FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo, Ref.IntRef intRef2) {
                    super(1);
                    this.$finalCoveredDistance = intRef;
                    this.this$0 = fragmentFitnessPlanWeekAndDayInfo;
                    this.$totalDistance = intRef2;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    Ref.IntRef intRef = this.$finalCoveredDistance;
                    int i2 = intRef.element + i;
                    intRef.element = i2;
                    this.this$0.z(i2, this.$totalDistance.element);
                }
            }

            /* renamed from: com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$c$b$b  reason: collision with other inner class name */
            /* loaded from: classes2.dex */
            public static final class C0241b extends Lambda implements Function1<Integer, Unit> {
                public final /* synthetic */ Ref.IntRef $finalCoveredDistance;
                public final /* synthetic */ Ref.IntRef $totalDistance;
                public final /* synthetic */ FragmentFitnessPlanWeekAndDayInfo this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0241b(Ref.IntRef intRef, FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo, Ref.IntRef intRef2) {
                    super(1);
                    this.$finalCoveredDistance = intRef;
                    this.this$0 = fragmentFitnessPlanWeekAndDayInfo;
                    this.$totalDistance = intRef2;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(int i) {
                    Ref.IntRef intRef = this.$finalCoveredDistance;
                    int i2 = intRef.element + i;
                    intRef.element = i2;
                    this.this$0.z(i2, this.$totalDistance.element);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo, FitnessPlanTemplateRes.FitnessPlanTemplateData.ActivityMapping activityMapping, DayInfo dayInfo, ActivityInfo activityInfo, Ref.IntRef intRef, Ref.IntRef intRef2, Continuation<? super b> continuation) {
                super(2, continuation);
                this.this$0 = fragmentFitnessPlanWeekAndDayInfo;
                this.$tempItem = activityMapping;
                this.$day = dayInfo;
                this.$activity = activityInfo;
                this.$finalCoveredDistance = intRef;
                this.$totalDistance = intRef2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new b(this.this$0, this.$tempItem, this.$day, this.$activity, this.$finalCoveredDistance, this.$totalDistance, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0057  */
            /* JADX WARN: Removed duplicated region for block: B:32:0x00ba  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r13) {
                /*
                    r12 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                    int r1 = r12.label
                    java.lang.String r2 = "viewModelWorkoutFeedback"
                    r3 = 1
                    r4 = 0
                    r5 = 2
                    if (r1 == 0) goto L2a
                    if (r1 == r3) goto L22
                    if (r1 != r5) goto L1a
                    java.lang.Object r1 = r12.L$0
                    java.util.Iterator r1 = (java.util.Iterator) r1
                    kotlin.ResultKt.throwOnFailure(r13)
                    goto Lb3
                L1a:
                    java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r13.<init>(r0)
                    throw r13
                L22:
                    java.lang.Object r1 = r12.L$0
                    java.util.Iterator r1 = (java.util.Iterator) r1
                    kotlin.ResultKt.throwOnFailure(r13)
                    goto L50
                L2a:
                    kotlin.ResultKt.throwOnFailure(r13)
                    com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo r13 = r12.this$0
                    boolean r13 = r13.isAdded()
                    if (r13 == 0) goto Lf0
                    com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo r13 = r12.this$0
                    androidx.fragment.app.FragmentActivity r13 = r13.requireActivity()
                    com.coveiot.covepreferences.UserDataManager r13 = com.coveiot.covepreferences.UserDataManager.getInstance(r13)
                    boolean r13 = r13.isOneKSupported()
                    if (r13 == 0) goto L95
                    com.coveiot.coveaccess.model.server.FitnessPlanTemplateRes$FitnessPlanTemplateData$ActivityMapping r13 = r12.$tempItem
                    java.util.List r13 = r13.getPhysicalActivityCodes()
                    java.util.Iterator r13 = r13.iterator()
                    r1 = r13
                L50:
                    r13 = r12
                L51:
                    boolean r5 = r1.hasNext()
                    if (r5 == 0) goto Lf0
                    java.lang.Object r5 = r1.next()
                    java.lang.String r5 = (java.lang.String) r5
                    com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo r6 = r13.this$0
                    boolean r6 = r6.isAdded()
                    if (r6 == 0) goto L51
                    com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo r6 = r13.this$0
                    com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback r6 = com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo.access$getViewModelWorkoutFeedback$p(r6)
                    if (r6 != 0) goto L71
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
                    r6 = r4
                L71:
                    java.lang.String r7 = "code"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r7)
                    com.coveiot.android.activitymodes.models.DayInfo r7 = r13.$day
                    java.lang.String r7 = r7.getDate()
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
                    com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$c$b$a r8 = new com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$c$b$a
                    kotlin.jvm.internal.Ref$IntRef r9 = r13.$finalCoveredDistance
                    com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo r10 = r13.this$0
                    kotlin.jvm.internal.Ref$IntRef r11 = r13.$totalDistance
                    r8.<init>(r9, r10, r11)
                    r13.L$0 = r1
                    r13.label = r3
                    java.lang.Object r5 = r6.getDistanceValueByActivityNCategoryIDs(r5, r7, r8, r13)
                    if (r5 != r0) goto L51
                    return r0
                L95:
                    com.coveiot.android.activitymodes.models.ActivityInfo r13 = r12.$activity
                    java.lang.String r13 = r13.getActivityType()
                    com.coveiot.coveaccess.model.server.FitnessPlanTemplateRes$FitnessPlanTemplateData$ActivityMapping r1 = r12.$tempItem
                    java.lang.String r1 = r1.getActivityType()
                    r3 = 0
                    boolean r13 = kotlin.text.m.equals$default(r13, r1, r3, r5, r4)
                    if (r13 == 0) goto Lf0
                    com.coveiot.coveaccess.model.server.FitnessPlanTemplateRes$FitnessPlanTemplateData$ActivityMapping r13 = r12.$tempItem
                    java.util.List r13 = r13.getSessionTypes()
                    java.util.Iterator r13 = r13.iterator()
                    r1 = r13
                Lb3:
                    r13 = r12
                Lb4:
                    boolean r3 = r1.hasNext()
                    if (r3 == 0) goto Lf0
                    java.lang.Object r3 = r1.next()
                    java.lang.String r3 = (java.lang.String) r3
                    com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo r6 = r13.this$0
                    com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback r6 = com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo.access$getViewModelWorkoutFeedback$p(r6)
                    if (r6 != 0) goto Lcc
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
                    r6 = r4
                Lcc:
                    com.coveiot.android.activitymodes.models.DayInfo r7 = r13.$day
                    java.lang.String r7 = r7.getDate()
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
                    java.lang.String r8 = "type"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r8)
                    com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$c$b$b r8 = new com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$c$b$b
                    kotlin.jvm.internal.Ref$IntRef r9 = r13.$finalCoveredDistance
                    com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo r10 = r13.this$0
                    kotlin.jvm.internal.Ref$IntRef r11 = r13.$totalDistance
                    r8.<init>(r9, r10, r11)
                    r13.L$0 = r1
                    r13.label = r5
                    java.lang.Object r3 = r6.getDistanceValueByActivityType(r7, r3, r8, r13)
                    if (r3 != r0) goto Lb4
                    return r0
                Lf0:
                    kotlin.Unit r13 = kotlin.Unit.INSTANCE
                    return r13
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo.c.b.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(DayInfo dayInfo, FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo) {
            super(1);
            this.$day = dayInfo;
            this.this$0 = fragmentFitnessPlanWeekAndDayInfo;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(FitnessPlanTemplateRes.FitnessPlanTemplateData fitnessPlanTemplateData) {
            invoke2(fitnessPlanTemplateData);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull FitnessPlanTemplateRes.FitnessPlanTemplateData template) {
            Intrinsics.checkNotNullParameter(template, "template");
            Ref.IntRef intRef = new Ref.IntRef();
            Ref.IntRef intRef2 = new Ref.IntRef();
            if (this.$day.getActivitylist() != null) {
                if (template.getActivityMappings() == null) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getIO(), null, new a(this.this$0, this.$day, intRef2, intRef, null), 2, null);
                    return;
                }
                ArrayList<ActivityInfo> activitylist = this.$day.getActivitylist();
                if (activitylist != null) {
                    FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo = this.this$0;
                    DayInfo dayInfo = this.$day;
                    for (ActivityInfo activityInfo : activitylist) {
                        String target = activityInfo.getTarget();
                        if (!(target == null || target.length() == 0)) {
                            int i = intRef2.element;
                            String target2 = activityInfo.getTarget();
                            Intrinsics.checkNotNull(target2);
                            intRef2.element = i + Integer.parseInt(target2);
                            if (template.getActivityMappings() != null) {
                                for (FitnessPlanTemplateRes.FitnessPlanTemplateData.ActivityMapping activityMapping : template.getActivityMappings()) {
                                    String activityType = activityInfo.getActivityType();
                                    Intrinsics.checkNotNull(activityType);
                                    if (Intrinsics.areEqual(activityType, activityMapping.getActivityType())) {
                                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(fragmentFitnessPlanWeekAndDayInfo), Dispatchers.getIO(), null, new b(fragmentFitnessPlanWeekAndDayInfo, activityMapping, dayInfo, activityInfo, intRef, intRef2, null), 2, null);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$weekClick$1", f = "FragmentFitnessPlanWeekAndDayInfo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ int $position;
        public final /* synthetic */ WeekInfo $weekInfo;
        public int label;
        public final /* synthetic */ FragmentFitnessPlanWeekAndDayInfo this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(WeekInfo weekInfo, FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo, int i, Continuation<? super d> continuation) {
            super(2, continuation);
            this.$weekInfo = weekInfo;
            this.this$0 = fragmentFitnessPlanWeekAndDayInfo;
            this.$position = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new d(this.$weekInfo, this.this$0, this.$position, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$weekInfo != null) {
                    this.this$0.t = this.$position;
                    this.this$0.s = 0;
                    FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo = this.this$0;
                    fragmentFitnessPlanWeekAndDayInfo.B(fragmentFitnessPlanWeekAndDayInfo.t);
                    FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo2 = this.this$0;
                    DayInfo dayInfo = this.$weekInfo.getDayList().get(this.this$0.s);
                    Intrinsics.checkNotNullExpressionValue(dayInfo, "weekInfo.dayList[selectedDay]");
                    fragmentFitnessPlanWeekAndDayInfo2.y(dayInfo);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void A(FragmentFitnessPlanWeekAndDayInfo this$0, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding = this$0.m;
        FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding2 = null;
        if (fragmentFitnessPlanWeekDayInfoBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFitnessPlanWeekDayInfoBinding = null;
        }
        TextView textView = fragmentFitnessPlanWeekDayInfoBinding.tvTodayGoalTotalValue;
        StringBuilder sb = new StringBuilder();
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        double convertCmToMeters = this$0.z ? i : themesUtils.convertCmToMeters(i);
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        sb.append(themesUtils.getStringFormattedValueTillNDecimal(Double.valueOf(convertCmToMeters / themesUtils.getDivisionValueAsPerUnit(requireContext)), 2));
        sb.append('/');
        Context requireContext2 = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(this$0.requireContext()).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(requireContext()).isDistanceUnitInMile");
        sb.append(themesUtils.getDistanceWithUnit(requireContext2, i2, isDistanceUnitInMile.booleanValue() ? 1 : 0));
        textView.setText(sb.toString());
        FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding3 = this$0.m;
        if (fragmentFitnessPlanWeekDayInfoBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFitnessPlanWeekDayInfoBinding3 = null;
        }
        fragmentFitnessPlanWeekDayInfoBinding3.todayGoalProgress.setMax(i2);
        FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding4 = this$0.m;
        if (fragmentFitnessPlanWeekDayInfoBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentFitnessPlanWeekDayInfoBinding2 = fragmentFitnessPlanWeekDayInfoBinding4;
        }
        ProgressBar progressBar = fragmentFitnessPlanWeekDayInfoBinding2.todayGoalProgress;
        if (!this$0.z) {
            i = (int) themesUtils.convertCmToMeters(i);
        }
        progressBar.setProgress(i);
    }

    public static final void E(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @JvmStatic
    @NotNull
    public static final FragmentFitnessPlanWeekAndDayInfo newInstance(@NotNull FitnessPlanData fitnessPlanData, @NotNull CurrentWeekAndDay currentWeekAndDay, @NotNull String str, boolean z) {
        return Companion.newInstance(fitnessPlanData, currentWeekAndDay, str, z);
    }

    public static final void s(final FragmentFitnessPlanWeekAndDayInfo this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isNetConnected(this$0.requireContext())) {
            this$0.showNoInternetDialog();
            return;
        }
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = this$0.getString(R.string.confirmation);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
        String string2 = this$0.getString(this$0.C ? R.string.do_you_want_continue : R.string.end_plan_msg);
        Intrinsics.checkNotNullExpressionValue(string2, "if (planHasEnded) getStr…ng(R.string.end_plan_msg)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(requireContext, string, string2, false, 8, null);
        String string3 = this$0.requireContext().getString(R.string.yes);
        Intrinsics.checkNotNullExpressionValue(string3, "requireContext().getString(R.string.yes)");
        bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.m0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFitnessPlanWeekAndDayInfo.t(FragmentFitnessPlanWeekAndDayInfo.this, bottomSheetDialogTwoButtons, view2);
            }
        });
        String string4 = this$0.requireContext().getString(R.string.f2703no);
        Intrinsics.checkNotNullExpressionValue(string4, "requireContext().getString(R.string.no)");
        bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.n0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFitnessPlanWeekAndDayInfo.u(BottomSheetDialogTwoButtons.this, view2);
            }
        });
        if (bottomSheetDialogTwoButtons.isShowing()) {
            return;
        }
        bottomSheetDialogTwoButtons.show();
    }

    public static final void t(FragmentFitnessPlanWeekAndDayInfo this$0, BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogTwoButtons, "$bottomSheetDialogTwoButtons");
        if (!AppUtils.isNetConnected(this$0.requireContext())) {
            this$0.showNoInternetDialog();
            return;
        }
        bottomSheetDialogTwoButtons.dismiss();
        this$0.H();
    }

    public static final void u(BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogTwoButtons, "$bottomSheetDialogTwoButtons");
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void x(FragmentFitnessPlanWeekAndDayInfo this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.s = i;
        FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding = this$0.m;
        FitnessPlanDayAdapter fitnessPlanDayAdapter = null;
        if (fragmentFitnessPlanWeekDayInfoBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFitnessPlanWeekDayInfoBinding = null;
        }
        fragmentFitnessPlanWeekDayInfoBinding.rvDays.smoothScrollToPosition(this$0.s);
        FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding2 = this$0.m;
        if (fragmentFitnessPlanWeekDayInfoBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFitnessPlanWeekDayInfoBinding2 = null;
        }
        fragmentFitnessPlanWeekDayInfoBinding2.rvDays.scrollToPosition(this$0.s);
        FitnessPlanDayAdapter fitnessPlanDayAdapter2 = this$0.q;
        if (fitnessPlanDayAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanDayAdapter");
        } else {
            fitnessPlanDayAdapter = fitnessPlanDayAdapter2;
        }
        fitnessPlanDayAdapter.setDaySelectedPosition(this$0.s);
    }

    public final void B(int i) {
        FitnessPlanDayAdapter fitnessPlanDayAdapter = this.q;
        CurrentWeekAndDay currentWeekAndDay = null;
        if (fitnessPlanDayAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanDayAdapter");
            fitnessPlanDayAdapter = null;
        }
        fitnessPlanDayAdapter.setDayList(this.v.get(i).getDayList());
        ArrayList<WeekInfo> arrayList = this.v;
        CurrentWeekAndDay currentWeekAndDay2 = this.x;
        if (currentWeekAndDay2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentWeekAndDay");
            currentWeekAndDay2 = null;
        }
        ArrayList<DayInfo> dayList = arrayList.get(currentWeekAndDay2.getCurrentWeek()).getDayList();
        CurrentWeekAndDay currentWeekAndDay3 = this.x;
        if (currentWeekAndDay3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentWeekAndDay");
            currentWeekAndDay3 = null;
        }
        DayInfo dayInfo = dayList.get(currentWeekAndDay3.getCurrentDay());
        Intrinsics.checkNotNullExpressionValue(dayInfo, "weekListMain[currentWeek…entWeekAndDay.currentDay]");
        C(dayInfo);
        ArrayList<WeekInfo> arrayList2 = this.v;
        CurrentWeekAndDay currentWeekAndDay4 = this.x;
        if (currentWeekAndDay4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentWeekAndDay");
            currentWeekAndDay4 = null;
        }
        ArrayList<DayInfo> dayList2 = arrayList2.get(currentWeekAndDay4.getCurrentWeek()).getDayList();
        CurrentWeekAndDay currentWeekAndDay5 = this.x;
        if (currentWeekAndDay5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentWeekAndDay");
            currentWeekAndDay5 = null;
        }
        DayInfo dayInfo2 = dayList2.get(currentWeekAndDay5.getCurrentDay());
        Intrinsics.checkNotNullExpressionValue(dayInfo2, "weekListMain[currentWeek…entWeekAndDay.currentDay]");
        y(dayInfo2);
        CurrentWeekAndDay currentWeekAndDay6 = this.x;
        if (currentWeekAndDay6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentWeekAndDay");
            currentWeekAndDay6 = null;
        }
        if (currentWeekAndDay6.getCurrentWeek() != this.t) {
            setCurrentDay(0);
            return;
        }
        CurrentWeekAndDay currentWeekAndDay7 = this.x;
        if (currentWeekAndDay7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentWeekAndDay");
        } else {
            currentWeekAndDay = currentWeekAndDay7;
        }
        setCurrentDay(currentWeekAndDay.getCurrentDay());
    }

    public final void C(DayInfo dayInfo) {
        if (isAdded()) {
            if (!this.z) {
                ViewModelWorkoutFeedback viewModelWorkoutFeedback = this.o;
                FitnessPlanData fitnessPlanData = null;
                if (viewModelWorkoutFeedback == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutFeedback");
                    viewModelWorkoutFeedback = null;
                }
                FitnessPlanData fitnessPlanData2 = this.u;
                if (fitnessPlanData2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
                } else {
                    fitnessPlanData = fitnessPlanData2;
                }
                viewModelWorkoutFeedback.getPlanTemplate(fitnessPlanData.getPlanTemplateId(), new c(dayInfo, this));
            } else if (dayInfo.getActivitylist() != null) {
                ArrayList<ActivityInfo> activitylist = dayInfo.getActivitylist();
                Intrinsics.checkNotNull(activitylist);
                int i = 0;
                int i2 = 0;
                for (ActivityInfo activityInfo : activitylist) {
                    String target = activityInfo.getTarget();
                    boolean z = true;
                    if (!(target == null || target.length() == 0)) {
                        String target2 = activityInfo.getTarget();
                        Intrinsics.checkNotNull(target2);
                        i2 += Integer.parseInt(target2);
                        String targetAchieved = activityInfo.getTargetAchieved();
                        if (targetAchieved != null && targetAchieved.length() != 0) {
                            z = false;
                        }
                        if (!z) {
                            i += (int) Double.parseDouble(String.valueOf(activityInfo.getTargetAchieved()));
                        }
                    }
                }
                z(i, i2);
            }
            dismissProgress();
        }
    }

    public final void D() {
        ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard = this.n;
        if (viewModelCurrentPlanDashboard == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelCurrentPlanDashboard");
            viewModelCurrentPlanDashboard = null;
        }
        MutableLiveData<PlanStatus> planStatusLiveData = viewModelCurrentPlanDashboard.getPlanStatusLiveData();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final FragmentFitnessPlanWeekAndDayInfo$setObservers$1 fragmentFitnessPlanWeekAndDayInfo$setObservers$1 = new FragmentFitnessPlanWeekAndDayInfo$setObservers$1(this);
        planStatusLiveData.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.activitymodes.fragments.o0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentFitnessPlanWeekAndDayInfo.E(Function1.this, obj);
            }
        });
    }

    public final void F(FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding) {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        FitnessPlanWeekAdapter fitnessPlanWeekAdapter = new FitnessPlanWeekAdapter(requireContext, this);
        this.p = fitnessPlanWeekAdapter;
        fragmentFitnessPlanWeekDayInfoBinding.rvWeeks.setAdapter(fitnessPlanWeekAdapter);
        fragmentFitnessPlanWeekDayInfoBinding.rvWeeks.setLayoutManager(new LinearLayoutManager(requireActivity(), 0, false));
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        FitnessPlanDayAdapter fitnessPlanDayAdapter = new FitnessPlanDayAdapter(requireContext2, this);
        this.q = fitnessPlanDayAdapter;
        fragmentFitnessPlanWeekDayInfoBinding.rvDays.setAdapter(fitnessPlanDayAdapter);
        fragmentFitnessPlanWeekDayInfoBinding.rvDays.setLayoutManager(new LinearLayoutManager(requireActivity(), 0, false));
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        FitnessPlanSelectedDayGoalAdapter fitnessPlanSelectedDayGoalAdapter = new FitnessPlanSelectedDayGoalAdapter(requireContext3);
        this.r = fitnessPlanSelectedDayGoalAdapter;
        fragmentFitnessPlanWeekDayInfoBinding.rvTodayGoal.setAdapter(fitnessPlanSelectedDayGoalAdapter);
        fragmentFitnessPlanWeekDayInfoBinding.rvTodayGoal.setLayoutManager(new LinearLayoutManager(requireActivity()));
    }

    public final void G(ArrayList<WeekInfo> arrayList) {
        CurrentWeekAndDay currentWeekAndDay = null;
        if (arrayList.size() >= 2) {
            FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding = this.m;
            if (fragmentFitnessPlanWeekDayInfoBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentFitnessPlanWeekDayInfoBinding = null;
            }
            RecyclerView recyclerView = fragmentFitnessPlanWeekDayInfoBinding.rvWeeks;
            Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.rvWeeks");
            visible(recyclerView);
        } else {
            FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding2 = this.m;
            if (fragmentFitnessPlanWeekDayInfoBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentFitnessPlanWeekDayInfoBinding2 = null;
            }
            RecyclerView recyclerView2 = fragmentFitnessPlanWeekDayInfoBinding2.rvWeeks;
            Intrinsics.checkNotNullExpressionValue(recyclerView2, "binding.rvWeeks");
            gone(recyclerView2);
        }
        FitnessPlanWeekAdapter fitnessPlanWeekAdapter = this.p;
        if (fitnessPlanWeekAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanWeekAdapter");
            fitnessPlanWeekAdapter = null;
        }
        CurrentWeekAndDay currentWeekAndDay2 = this.x;
        if (currentWeekAndDay2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentWeekAndDay");
            currentWeekAndDay2 = null;
        }
        fitnessPlanWeekAdapter.setWeekSelectedPosition(currentWeekAndDay2.getCurrentWeek());
        FitnessPlanWeekAdapter fitnessPlanWeekAdapter2 = this.p;
        if (fitnessPlanWeekAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanWeekAdapter");
            fitnessPlanWeekAdapter2 = null;
        }
        fitnessPlanWeekAdapter2.setWeekList(this.v);
        FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding3 = this.m;
        if (fragmentFitnessPlanWeekDayInfoBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFitnessPlanWeekDayInfoBinding3 = null;
        }
        RecyclerView recyclerView3 = fragmentFitnessPlanWeekDayInfoBinding3.rvWeeks;
        CurrentWeekAndDay currentWeekAndDay3 = this.x;
        if (currentWeekAndDay3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentWeekAndDay");
        } else {
            currentWeekAndDay = currentWeekAndDay3;
        }
        recyclerView3.scrollToPosition(currentWeekAndDay.getCurrentWeek());
    }

    public final void H() {
        ViewModelWorkoutFeedback viewModelWorkoutFeedback;
        FitnessPlanData fitnessPlanData = null;
        BaseFragment.showProgress$default(this, false, 1, null);
        ViewModelWorkoutFeedback viewModelWorkoutFeedback2 = this.o;
        if (viewModelWorkoutFeedback2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutFeedback");
            viewModelWorkoutFeedback = null;
        } else {
            viewModelWorkoutFeedback = viewModelWorkoutFeedback2;
        }
        double d2 = this.B;
        ArrayList<String> arrayList = this.w;
        FitnessPlanData fitnessPlanData2 = this.u;
        if (fitnessPlanData2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
        } else {
            fitnessPlanData = fitnessPlanData2;
        }
        viewModelWorkoutFeedback.updateFitnessPlanProgress(false, d2, arrayList, fitnessPlanData.getPlanTemplateId(), new CoveApiListener<CommonResponseClass, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$unsubscribePlan$1

            @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$unsubscribePlan$1$onError$1", f = "FragmentFitnessPlanWeekAndDayInfo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes2.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ FragmentFitnessPlanWeekAndDayInfo this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = fragmentFitnessPlanWeekAndDayInfo;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.this$0.dismissProgress();
                        Toast.makeText(this.this$0.requireActivity(), this.this$0.getString(R.string.some_thing_went_wrong), 0).show();
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                if (coveApiErrorModel != null && coveApiErrorModel.getCode() == 200) {
                    if (FragmentFitnessPlanWeekAndDayInfo.this.isAdded()) {
                        if (FragmentFitnessPlanWeekAndDayInfo.this.requireActivity() instanceof ActivityFitnessPlan) {
                            FragmentActivity requireActivity = FragmentFitnessPlanWeekAndDayInfo.this.requireActivity();
                            Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.activitymodes.activities.ActivityFitnessPlan");
                            ((ActivityFitnessPlan) requireActivity).setCurrentPlan(null);
                        }
                        FragmentFitnessPlanWeekAndDayInfo.this.v();
                    }
                } else if (FragmentFitnessPlanWeekAndDayInfo.this.isAdded()) {
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentFitnessPlanWeekAndDayInfo.this), Dispatchers.getMain(), null, new a(FragmentFitnessPlanWeekAndDayInfo.this, null), 2, null);
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable CommonResponseClass commonResponseClass) {
                if (FragmentFitnessPlanWeekAndDayInfo.this.isAdded()) {
                    if (FragmentFitnessPlanWeekAndDayInfo.this.requireActivity() instanceof ActivityFitnessPlan) {
                        FragmentActivity requireActivity = FragmentFitnessPlanWeekAndDayInfo.this.requireActivity();
                        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.activitymodes.activities.ActivityFitnessPlan");
                        ((ActivityFitnessPlan) requireActivity).setCurrentPlan(null);
                    }
                    FragmentFitnessPlanWeekAndDayInfo.this.v();
                }
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.coveiot.android.activitymodes.adapters.FitnessPlanDayAdapter.FitnessDayClickListener
    public void dayClick(@NotNull DayInfo day) {
        Intrinsics.checkNotNullParameter(day, "day");
        this.s = day.getDayNumber() - 1;
        y(day);
        FitnessPlanDayAdapter fitnessPlanDayAdapter = this.q;
        if (fitnessPlanDayAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanDayAdapter");
            fitnessPlanDayAdapter = null;
        }
        fitnessPlanDayAdapter.setDaySelectedPosition(day.getDayNumber() - 1);
        C(day);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            Serializable serializable = arguments.getSerializable("fitnessKey");
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.android.activitymodes.models.FitnessPlanData");
            FitnessPlanData fitnessPlanData = (FitnessPlanData) serializable;
            this.u = fitnessPlanData;
            FitnessPlanData fitnessPlanData2 = null;
            if (fitnessPlanData == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
                fitnessPlanData = null;
            }
            this.y = fitnessPlanData.getPlanCompleted();
            FitnessPlanData fitnessPlanData3 = this.u;
            if (fitnessPlanData3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
            } else {
                fitnessPlanData2 = fitnessPlanData3;
            }
            ArrayList<WeekInfo> weekList = fitnessPlanData2.getWeekList();
            Intrinsics.checkNotNull(weekList);
            this.v = weekList;
            Serializable serializable2 = arguments.getSerializable("fitnessCurrentWeekDay");
            Intrinsics.checkNotNull(serializable2, "null cannot be cast to non-null type com.coveiot.android.activitymodes.models.CurrentWeekAndDay");
            this.x = (CurrentWeekAndDay) serializable2;
            String string = arguments.getString("fitnessBackground", "");
            Intrinsics.checkNotNullExpressionValue(string, "it.getString(\"fitnessBackground\", \"\")");
            this.A = string;
            this.z = arguments.getBoolean("isPlanHistory");
        }
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentFitnessPlanWeekDayInfoBinding inflate = FragmentFitnessPlanWeekDayInfoBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.m = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        View root = inflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        SessionManager.getInstance(requireContext()).setUserPlanId("");
        SessionManager.getInstance(requireContext()).setUserPlanBackground("");
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        String sb;
        double convertCmToMeters;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        CurrentWeekAndDay currentWeekAndDay = null;
        BaseFragment.showProgress$default(this, false, 1, null);
        ViewModel viewModel = new ViewModelProvider(this).get(ViewModelWorkoutFeedback.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProvider(this)[…koutFeedback::class.java]");
        this.o = (ViewModelWorkoutFeedback) viewModel;
        ViewModel viewModel2 = new ViewModelProvider(this).get(ViewModelCurrentPlanDashboard.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "ViewModelProvider(this)[…lanDashboard::class.java]");
        this.n = (ViewModelCurrentPlanDashboard) viewModel2;
        ViewModel viewModel3 = new ViewModelProvider(this).get(PlanDetailsViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel3, "ViewModelProvider(this)[…ilsViewModel::class.java]");
        PlanDetailsViewModel planDetailsViewModel = (PlanDetailsViewModel) viewModel3;
        D();
        ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard = this.n;
        if (viewModelCurrentPlanDashboard == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelCurrentPlanDashboard");
            viewModelCurrentPlanDashboard = null;
        }
        viewModelCurrentPlanDashboard.checkIfPlanOngoingOrFinished();
        FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding = this.m;
        if (fragmentFitnessPlanWeekDayInfoBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFitnessPlanWeekDayInfoBinding = null;
        }
        fragmentFitnessPlanWeekDayInfoBinding.setIsPlanCompleted(Boolean.valueOf(this.y));
        fragmentFitnessPlanWeekDayInfoBinding.setIsHistoryPlan(Boolean.valueOf(this.z));
        FitnessPlanData fitnessPlanData = this.u;
        if (fitnessPlanData == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
            fitnessPlanData = null;
        }
        fragmentFitnessPlanWeekDayInfoBinding.setPlanTitle(fitnessPlanData.getTitle());
        fragmentFitnessPlanWeekDayInfoBinding.setPlanBg(this.A);
        TextView tvPlanActivityCompleted = fragmentFitnessPlanWeekDayInfoBinding.tvPlanActivityCompleted;
        Intrinsics.checkNotNullExpressionValue(tvPlanActivityCompleted, "tvPlanActivityCompleted");
        FitnessPlanData fitnessPlanData2 = this.u;
        if (fitnessPlanData2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
            fitnessPlanData2 = null;
        }
        visibleIf(tvPlanActivityCompleted, fitnessPlanData2.getActivitiesMapped());
        TextView textView = (TextView) requireActivity().findViewById(R.id.toolbar_title);
        if (this.z) {
            FitnessPlanData fitnessPlanData3 = this.u;
            if (fitnessPlanData3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
                fitnessPlanData3 = null;
            }
            sb = fitnessPlanData3.getTitle();
        } else {
            StringBuilder sb2 = new StringBuilder();
            FitnessPlanData fitnessPlanData4 = this.u;
            if (fitnessPlanData4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
                fitnessPlanData4 = null;
            }
            sb2.append(fitnessPlanData4.getTitle());
            sb2.append(' ');
            sb2.append(getString(R.string.plan));
            sb = sb2.toString();
        }
        textView.setText(sb);
        if (this.y) {
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            if (this.z) {
                FitnessPlanData fitnessPlanData5 = this.u;
                if (fitnessPlanData5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
                    fitnessPlanData5 = null;
                }
                convertCmToMeters = fitnessPlanData5.getCompletedDistance();
            } else {
                FitnessPlanData fitnessPlanData6 = this.u;
                if (fitnessPlanData6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
                    fitnessPlanData6 = null;
                }
                convertCmToMeters = themesUtils.convertCmToMeters((int) fitnessPlanData6.getCompletedDistance());
            }
            fragmentFitnessPlanWeekDayInfoBinding.setCompletedDistance(themesUtils.getDistanceWithUnit(requireContext, (int) convertCmToMeters, 2));
            FitnessPlanData fitnessPlanData7 = this.u;
            if (fitnessPlanData7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
                fitnessPlanData7 = null;
            }
            fragmentFitnessPlanWeekDayInfoBinding.setCompletedCalories(String.valueOf((int) fitnessPlanData7.getTotalCalories()));
        }
        r(this.v);
        CurrentWeekAndDay currentWeekAndDay2 = this.x;
        if (currentWeekAndDay2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentWeekAndDay");
            currentWeekAndDay2 = null;
        }
        this.t = currentWeekAndDay2.getCurrentWeek();
        CurrentWeekAndDay currentWeekAndDay3 = this.x;
        if (currentWeekAndDay3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentWeekAndDay");
            currentWeekAndDay3 = null;
        }
        this.s = currentWeekAndDay3.getCurrentDay();
        F(fragmentFitnessPlanWeekDayInfoBinding);
        G(this.v);
        setCompletedCardData(fragmentFitnessPlanWeekDayInfoBinding);
        CurrentWeekAndDay currentWeekAndDay4 = this.x;
        if (currentWeekAndDay4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentWeekAndDay");
        } else {
            currentWeekAndDay = currentWeekAndDay4;
        }
        B(currentWeekAndDay.getCurrentWeek());
        fragmentFitnessPlanWeekDayInfoBinding.clUnsubscribePlan.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.l0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFitnessPlanWeekAndDayInfo.s(FragmentFitnessPlanWeekAndDayInfo.this, view2);
            }
        });
        D();
    }

    public final void q(FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding, Boolean bool) {
        fragmentFitnessPlanWeekDayInfoBinding.setIsFutureDay(bool);
        fragmentFitnessPlanWeekDayInfoBinding.setFutureDayInfo(getString(R.string.your_goals_for_the_day_will_start_on_day) + ' ' + (this.s + 1));
    }

    public final void r(ArrayList<WeekInfo> arrayList) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            int size2 = arrayList.get(i).getDayList().size();
            for (int i2 = 0; i2 < size2; i2++) {
                String date = arrayList.get(i).getDayList().get(i2).getDate();
                if (date != null) {
                    this.w.add(date);
                }
            }
        }
    }

    public final void setCompletedCardData(@NotNull FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding) {
        Intrinsics.checkNotNullParameter(fragmentFitnessPlanWeekDayInfoBinding, "<this>");
        FitnessPlanData fitnessPlanData = this.u;
        FitnessPlanData fitnessPlanData2 = null;
        if (fitnessPlanData == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
            fitnessPlanData = null;
        }
        String categoryChosen = fitnessPlanData.getCategoryChosen();
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
        String upperCase = categoryChosen.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
        fragmentFitnessPlanWeekDayInfoBinding.setGoalCategory(upperCase);
        if (!this.z) {
            ViewModelWorkoutFeedback viewModelWorkoutFeedback = this.o;
            if (viewModelWorkoutFeedback == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutFeedback");
                viewModelWorkoutFeedback = null;
            }
            FitnessPlanData fitnessPlanData3 = this.u;
            if (fitnessPlanData3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
                fitnessPlanData3 = null;
            }
            viewModelWorkoutFeedback.getPlanTemplate(fitnessPlanData3.getPlanTemplateId(), new a(fragmentFitnessPlanWeekDayInfoBinding));
        } else {
            FitnessPlanData fitnessPlanData4 = this.u;
            if (fitnessPlanData4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
                fitnessPlanData4 = null;
            }
            w(fragmentFitnessPlanWeekDayInfoBinding, (int) fitnessPlanData4.getCompletedDistance());
        }
        StringBuilder sb = new StringBuilder();
        FitnessPlanData fitnessPlanData5 = this.u;
        if (fitnessPlanData5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
            fitnessPlanData5 = null;
        }
        sb.append(fitnessPlanData5.getTotalActivities());
        sb.append(' ');
        int i = R.string.activities;
        sb.append(getString(i));
        sb.append(" | ");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        FitnessPlanData fitnessPlanData6 = this.u;
        if (fitnessPlanData6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
            fitnessPlanData6 = null;
        }
        int totalDistance = fitnessPlanData6.getTotalDistance();
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(requireContext()).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(requireContext()).isDistanceUnitInMile");
        sb.append(themesUtils.getDistanceWithUnit(requireContext, totalDistance, isDistanceUnitInMile.booleanValue() ? 1 : 0));
        fragmentFitnessPlanWeekDayInfoBinding.setTotalActivitiesAndDistance(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        FitnessPlanData fitnessPlanData7 = this.u;
        if (fitnessPlanData7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
            fitnessPlanData7 = null;
        }
        sb2.append(fitnessPlanData7.getCompletedActivities());
        sb2.append('/');
        FitnessPlanData fitnessPlanData8 = this.u;
        if (fitnessPlanData8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
        } else {
            fitnessPlanData2 = fitnessPlanData8;
        }
        sb2.append(fitnessPlanData2.getTotalActivities());
        sb2.append(' ');
        sb2.append(getString(i));
        fragmentFitnessPlanWeekDayInfoBinding.setActivitiesCompleted(sb2.toString());
    }

    public final void setCurrentDay(final int i) {
        requireActivity().runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.fragments.p0
            @Override // java.lang.Runnable
            public final void run() {
                FragmentFitnessPlanWeekAndDayInfo.x(FragmentFitnessPlanWeekAndDayInfo.this, i);
            }
        });
    }

    public final void v() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new FragmentFitnessPlanWeekAndDayInfo$optOutCurrentPlan$1(this, null), 2, null);
    }

    public final void w(FragmentFitnessPlanWeekDayInfoBinding fragmentFitnessPlanWeekDayInfoBinding, int i) {
        float totalDistance;
        String sb;
        StringBuilder sb2 = new StringBuilder();
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        sb2.append(themesUtils.getDistanceByUnitSelected(requireContext, this.z ? i : (int) themesUtils.convertCmToMeters(i)));
        sb2.append('/');
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        FitnessPlanData fitnessPlanData = this.u;
        FitnessPlanData fitnessPlanData2 = null;
        if (fitnessPlanData == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
            fitnessPlanData = null;
        }
        int totalDistance2 = fitnessPlanData.getTotalDistance();
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(requireContext()).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(requireContext()).isDistanceUnitInMile");
        sb2.append(themesUtils.getDistanceWithUnit(requireContext2, totalDistance2, isDistanceUnitInMile.booleanValue() ? 1 : 0));
        fragmentFitnessPlanWeekDayInfoBinding.setDistanceCovered(sb2.toString());
        fragmentFitnessPlanWeekDayInfoBinding.setDayProgress(this.z ? Integer.valueOf(i) : Integer.valueOf((int) themesUtils.convertCmToMeters(i)));
        FitnessPlanData fitnessPlanData3 = this.u;
        if (fitnessPlanData3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
            fitnessPlanData3 = null;
        }
        fragmentFitnessPlanWeekDayInfoBinding.setDayProgressMax(Integer.valueOf(fitnessPlanData3.getTotalDistance()));
        FitnessPlanData fitnessPlanData4 = this.u;
        if (fitnessPlanData4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
            fitnessPlanData4 = null;
        }
        if (fitnessPlanData4.getTotalDistance() == 0) {
            this.B = 0.0d;
            fragmentFitnessPlanWeekDayInfoBinding.setProgressText(getString(R.string.zero_percent));
            return;
        }
        if (this.z) {
            float f = i;
            FitnessPlanData fitnessPlanData5 = this.u;
            if (fitnessPlanData5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
            } else {
                fitnessPlanData2 = fitnessPlanData5;
            }
            totalDistance = f / fitnessPlanData2.getTotalDistance();
        } else {
            double convertCmToMeters = themesUtils.convertCmToMeters(i);
            FitnessPlanData fitnessPlanData6 = this.u;
            if (fitnessPlanData6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fitnessPlanData");
            } else {
                fitnessPlanData2 = fitnessPlanData6;
            }
            totalDistance = (float) (convertCmToMeters / fitnessPlanData2.getTotalDistance());
        }
        double parseDouble = Double.parseDouble(themesUtils.getStringFormattedValueTillNDecimal(Float.valueOf(totalDistance * 100), 2));
        this.B = parseDouble;
        if (parseDouble >= 100.0d) {
            sb = "100%";
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(parseDouble);
            sb3.append('%');
            sb = sb3.toString();
        }
        fragmentFitnessPlanWeekDayInfoBinding.setProgressText(sb);
    }

    @Override // com.coveiot.android.activitymodes.adapters.FitnessPlanWeekAdapter.FitnessWeekClickListener
    public void weekClick(@NotNull WeekInfo weekInfo, int i) {
        Intrinsics.checkNotNullParameter(weekInfo, "weekInfo");
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new d(weekInfo, this, i, null), 2, null);
    }

    public final void y(DayInfo dayInfo) {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new b(dayInfo, null), 2, null);
    }

    @SuppressLint({"SetTextI18n"})
    public final void z(final int i, final int i2) {
        if (isAdded()) {
            requireActivity().runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.fragments.q0
                @Override // java.lang.Runnable
                public final void run() {
                    FragmentFitnessPlanWeekAndDayInfo.A(FragmentFitnessPlanWeekAndDayInfo.this, i, i2);
                }
            });
        }
    }
}
