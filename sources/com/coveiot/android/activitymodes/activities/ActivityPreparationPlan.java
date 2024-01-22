package com.coveiot.android.activitymodes.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.ActivityModesNavigator;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.repository.PreparationPlanRepository;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.LanguageHelper;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityPreparationPlan extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    @DebugMetadata(c = "com.coveiot.android.activitymodes.activities.ActivityPreparationPlan$onCreate$1", f = "ActivityPreparationPlan.kt", i = {}, l = {30}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        /* renamed from: com.coveiot.android.activitymodes.activities.ActivityPreparationPlan$a$a  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static final class C0235a extends Lambda implements Function1<Intent, Unit> {
            public static final C0235a INSTANCE = new C0235a();

            public C0235a() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
                invoke2(intent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Intent launchActivity) {
                Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
            }
        }

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(continuation);
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
                this.label = 1;
                obj = PreparationPlanRepository.Companion.getInstance(ActivityPreparationPlan.this).doesPlanExists(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            if (((Boolean) obj).booleanValue()) {
                ActivityPreparationPlan activityPreparationPlan = ActivityPreparationPlan.this;
                C0235a c0235a = C0235a.INSTANCE;
                Intent intent = new Intent(activityPreparationPlan, ActivityFitnessPlan.class);
                c0235a.invoke((C0235a) intent);
                activityPreparationPlan.startActivityForResult(intent, -1, null);
                ActivityPreparationPlan.this.finish();
            } else if (!AppUtils.isNetConnected(ActivityPreparationPlan.this)) {
                ActivityPreparationPlan.this.showInternetOffDialog();
            } else {
                AnalyticsLog analyticsLog = new AnalyticsLog();
                analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog.setScreenName(FirebaseEventParams.ScreenName.MAIN_MODES_DASHBOARD.getValue());
                analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_BUILD_NEW_PLAN.getValue());
                analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.MODES_BUILD_NEW_PLAN_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                if (UserDataManager.getInstance(ActivityPreparationPlan.this).getPlanConfigUrlsFromPref() != null && UserDataManager.getInstance(ActivityPreparationPlan.this).getPlanConfigUrlsFromPref().getOnboarding() != null) {
                    String url = UserDataManager.getInstance(ActivityPreparationPlan.this).getPlanConfigUrlsFromPref().getOnboarding();
                    if (AppUtils.isValidUrl(url)) {
                        ActivityModesNavigator.Companion companion = ActivityModesNavigator.Companion;
                        ActivityPreparationPlan activityPreparationPlan2 = ActivityPreparationPlan.this;
                        String string = activityPreparationPlan2.getString(R.string.build_plan);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.build_plan)");
                        Intrinsics.checkNotNullExpressionValue(url, "url");
                        companion.navigateToWorkoutWebViewer(activityPreparationPlan2, string, url);
                        ActivityPreparationPlan.this.finish();
                    }
                }
            }
            return Unit.INSTANCE;
        }
    }

    public static final void r(ActivityPreparationPlan this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(@Nullable Context context) {
        super.attachBaseContext(LanguageHelper.onAttach(context));
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setVisibility(8);
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.p1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityPreparationPlan.r(ActivityPreparationPlan.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_preparation_plan);
        initToolbar();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new a(null), 2, null);
    }
}
