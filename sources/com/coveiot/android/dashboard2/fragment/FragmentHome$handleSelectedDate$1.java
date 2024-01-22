package com.coveiot.android.dashboard2.fragment;

import android.text.format.DateUtils;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.dashboard2.listener.StepsDataModelListener;
import com.coveiot.android.dashboard2.util.Dashboard2Utils;
import com.coveiot.android.dashboard2.viewmodel.StepsDataViewModel;
import com.coveiot.covepreferences.data.StepsDataModel;
import java.util.Calendar;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$handleSelectedDate$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class FragmentHome$handleSelectedDate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Calendar $calendar;
    public int label;
    public final /* synthetic */ FragmentHome this$0;

    @DebugMetadata(c = "com.coveiot.android.dashboard2.fragment.FragmentHome$handleSelectedDate$1$1", f = "FragmentHome.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.coveiot.android.dashboard2.fragment.FragmentHome$handleSelectedDate$1$1  reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Calendar $calendar;
        public int label;
        public final /* synthetic */ FragmentHome this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(FragmentHome fragmentHome, Calendar calendar, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = fragmentHome;
            this.$calendar = calendar;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$calendar, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            StepsDataViewModel stepsDataViewModel;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                stepsDataViewModel = this.this$0.q;
                if (stepsDataViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mStepsDataViewModel");
                    stepsDataViewModel = null;
                }
                Calendar calendar = this.$calendar;
                FragmentActivity requireActivity = this.this$0.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                final FragmentHome fragmentHome = this.this$0;
                stepsDataViewModel.getPreviousDayStepsData(calendar, requireActivity, new StepsDataModelListener() { // from class: com.coveiot.android.dashboard2.fragment.FragmentHome.handleSelectedDate.1.1.1
                    @Override // com.coveiot.android.dashboard2.listener.StepsDataModelListener
                    public void onData(@Nullable StepsDataModel stepsDataModel) {
                        FragmentHome.this.T2(stepsDataModel);
                    }
                });
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentHome$handleSelectedDate$1(FragmentHome fragmentHome, Calendar calendar, Continuation<? super FragmentHome$handleSelectedDate$1> continuation) {
        super(2, continuation);
        this.this$0 = fragmentHome;
        this.$calendar = calendar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new FragmentHome$handleSelectedDate$1(this.this$0, this.$calendar, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((FragmentHome$handleSelectedDate$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            TextView textView = this.this$0.q0().calSelectionView.tvDate;
            Dashboard2Utils.Companion companion = Dashboard2Utils.Companion;
            long timeInMillis = this.$calendar.getTimeInMillis();
            FragmentActivity requireActivity = this.this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            textView.setText(companion.getTodayYesterdayStringFromTimeStamp(timeInMillis, requireActivity));
            if (DateUtils.isToday(this.$calendar.getTimeInMillis())) {
                this.this$0.Q = true;
                this.this$0.q0().calSelectionView.imgArrowRight.setImageResource(R.drawable.ic_right_arrow_cal_inactive);
                this.this$0.q0().calSelectionView.imgArrowRight.setEnabled(false);
                this.this$0.u0();
            } else {
                this.this$0.Q = false;
                this.this$0.q0().calSelectionView.imgArrowRight.setImageResource(R.drawable.ic_right_arrow_cal_active);
                this.this$0.q0().calSelectionView.imgArrowRight.setEnabled(true);
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getIO(), null, new AnonymousClass1(this.this$0, this.$calendar, null), 2, null);
            }
            this.this$0.t0(this.$calendar);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
