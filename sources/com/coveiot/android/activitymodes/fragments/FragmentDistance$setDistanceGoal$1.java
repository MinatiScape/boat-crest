package com.coveiot.android.activitymodes.fragments;

import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.PickerDialog;
import com.coveiot.covepreferences.UserDataManager;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentDistance$setDistanceGoal$1", f = "FragmentDistance.kt", i = {}, l = {59}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class FragmentDistance$setDistanceGoal$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public Object L$0;
    public int label;
    public final /* synthetic */ FragmentDistance this$0;

    @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentDistance$setDistanceGoal$1$1", f = "FragmentDistance.kt", i = {}, l = {60}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Float>, Object> {
        public int label;
        public final /* synthetic */ FragmentDistance this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(FragmentDistance fragmentDistance, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = fragmentDistance;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Float> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FragmentDistance fragmentDistance = this.this$0;
                this.label = 1;
                obj = fragmentDistance.a(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentDistance$setDistanceGoal$1(FragmentDistance fragmentDistance, Continuation<? super FragmentDistance$setDistanceGoal$1> continuation) {
        super(2, continuation);
        this.this$0 = fragmentDistance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$0(final FragmentDistance fragmentDistance, View view) {
        String string;
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.MAIN_ACTIVITY_DASHBOARD.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_EDIT_DISTANCE_GOAL_POPUP.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.EDIT_TARGET_DISTANCE_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        PickerDialog.Companion companion = PickerDialog.Companion;
        FragmentActivity activity = fragmentDistance.getActivity();
        Intrinsics.checkNotNull(activity);
        String string2 = fragmentDistance.getString(R.string.distance);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.distance)");
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(fragmentDistance.getContext()).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
        if (isDistanceUnitInMile.booleanValue()) {
            string = fragmentDistance.getString(R.string.miles);
        } else {
            string = fragmentDistance.getString(R.string.kms);
        }
        String str = string;
        Intrinsics.checkNotNullExpressionValue(str, "if (UserDataManager.getI…  getString(R.string.kms)");
        companion.dataPickerWeight(activity, string2, str, 1, 20, 1, (int) fragmentDistance.getValue(), new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.activitymodes.fragments.FragmentDistance$setDistanceGoal$1$2$1
            @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
            public void onValueSelected(@NotNull String distanceValue) {
                Intrinsics.checkNotNullParameter(distanceValue, "distanceValue");
                FragmentDistance.this.setValue(Float.parseFloat(distanceValue));
                DecimalFormat decimalFormat = new DecimalFormat("##0.00");
                decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
                if (!UserDataManager.getInstance(FragmentDistance.this.getContext()).isDistanceUnitInMile().booleanValue()) {
                    TextView textView = (TextView) FragmentDistance.this._$_findCachedViewById(R.id.tv_distance);
                    if (textView == null) {
                        return;
                    }
                    textView.setText(decimalFormat.format(Float.valueOf(FragmentDistance.this.getValue())));
                    return;
                }
                TextView textView2 = (TextView) FragmentDistance.this._$_findCachedViewById(R.id.tv_distance);
                if (textView2 == null) {
                    return;
                }
                textView2.setText(decimalFormat.format(WorkoutUtils.INSTANCE.convertKMToMiles(FragmentDistance.this.getValue())));
            }
        });
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new FragmentDistance$setDistanceGoal$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((FragmentDistance$setDistanceGoal$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        FragmentDistance fragmentDistance;
        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FragmentDistance fragmentDistance2 = this.this$0;
            CoroutineDispatcher io2 = Dispatchers.getIO();
            a aVar = new a(this.this$0, null);
            this.L$0 = fragmentDistance2;
            this.label = 1;
            Object withContext = BuildersKt.withContext(io2, aVar, this);
            if (withContext == coroutine_suspended) {
                return coroutine_suspended;
            }
            fragmentDistance = fragmentDistance2;
            obj = withContext;
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            fragmentDistance = (FragmentDistance) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        fragmentDistance.setValue(((Number) obj).floatValue());
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        if (!UserDataManager.getInstance(this.this$0.getContext()).isDistanceUnitInMile().booleanValue()) {
            TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.tv_distance);
            if (textView != null) {
                textView.setText(decimalFormat.format(Boxing.boxFloat(this.this$0.getValue())));
            }
        } else {
            TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.tv_distance);
            if (textView2 != null) {
                textView2.setText(decimalFormat.format(WorkoutUtils.INSTANCE.convertKMToMiles(this.this$0.getValue())));
            }
        }
        TextView textView3 = (TextView) this.this$0._$_findCachedViewById(R.id.edit_distance_image);
        if (textView3 != null) {
            final FragmentDistance fragmentDistance3 = this.this$0;
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.i0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentDistance$setDistanceGoal$1.invokeSuspend$lambda$0(FragmentDistance.this, view);
                }
            });
        }
        return Unit.INSTANCE;
    }
}
