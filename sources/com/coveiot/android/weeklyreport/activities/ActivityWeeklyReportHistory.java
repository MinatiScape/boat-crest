package com.coveiot.android.weeklyreport.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.weeklyreport.R;
import com.coveiot.android.weeklyreport.adapter.WeeklyFitnessReportAdapter;
import com.coveiot.android.weeklyreport.databinding.ActivityWeeklyReportHistoryBinding;
import com.coveiot.android.weeklyreport.fragments.FragmentFitnessReportHistory;
import com.coveiot.android.weeklyreport.utils.ViewModelFactory;
import com.coveiot.android.weeklyreport.viewmodel.WeeklyReportViewModel;
import com.coveiot.coveaccess.weeklyreport.response.FitnessReportRes;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.List;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class ActivityWeeklyReportHistory extends BaseActivity implements WeeklyFitnessReportAdapter.FitnessReportClickListener {
    public ActivityWeeklyReportHistoryBinding p;
    public WeeklyReportViewModel q;
    public WeeklyFitnessReportAdapter r;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int s = 2001;

    /* loaded from: classes8.dex */
    public static final class a extends Lambda implements Function1<List<? extends FitnessReportRes.FitnessReportItem>, Unit> {

        @DebugMetadata(c = "com.coveiot.android.weeklyreport.activities.ActivityWeeklyReportHistory$fitnessReportLiveObserver$1$1", f = "ActivityWeeklyReportHistory.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.coveiot.android.weeklyreport.activities.ActivityWeeklyReportHistory$a$a  reason: collision with other inner class name */
        /* loaded from: classes8.dex */
        public static final class C0328a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ List<FitnessReportRes.FitnessReportItem> $reports;
            public int label;
            public final /* synthetic */ ActivityWeeklyReportHistory this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public C0328a(ActivityWeeklyReportHistory activityWeeklyReportHistory, List<? extends FitnessReportRes.FitnessReportItem> list, Continuation<? super C0328a> continuation) {
                super(2, continuation);
                this.this$0 = activityWeeklyReportHistory;
                this.$reports = list;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new C0328a(this.this$0, this.$reports, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((C0328a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (!this.this$0.isFinishing()) {
                        List<FitnessReportRes.FitnessReportItem> list = this.$reports;
                        if (!(list == null || list.isEmpty())) {
                            WeeklyFitnessReportAdapter weeklyFitnessReportAdapter = this.this$0.r;
                            if (weeklyFitnessReportAdapter == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("weeklyFitnessReportAdapter");
                                weeklyFitnessReportAdapter = null;
                            }
                            List<FitnessReportRes.FitnessReportItem> reports = this.$reports;
                            Intrinsics.checkNotNullExpressionValue(reports, "reports");
                            weeklyFitnessReportAdapter.setReportList(reports);
                            this.this$0.x(false);
                            return Unit.INSTANCE;
                        }
                    }
                    this.this$0.x(true);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends FitnessReportRes.FitnessReportItem> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(List<? extends FitnessReportRes.FitnessReportItem> list) {
            ActivityWeeklyReportHistory.this.dismissProgress();
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityWeeklyReportHistory.this), null, null, new C0328a(ActivityWeeklyReportHistory.this, list, null), 3, null);
        }
    }

    public static final void t(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void v(ActivityWeeklyReportHistory this$0, View view) {
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

    public final void initToolbar(@NotNull String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.activities.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWeeklyReportHistory.v(ActivityWeeklyReportHistory.this, view);
            }
        });
        ((TextView) findViewById(R.id.toolbar_title)).setText(title);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.historyViewContainer);
        if (findFragmentById != null) {
            getSupportFragmentManager().beginTransaction().remove(findFragmentById).commitAllowingStateLoss();
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityWeeklyReportHistoryBinding inflate = ActivityWeeklyReportHistoryBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        WeeklyReportViewModel weeklyReportViewModel = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        String string = getString(R.string.fitness_report_history);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fitness_report_history)");
        initToolbar(string);
        if (Build.VERSION.SDK_INT < 29) {
            u();
        }
        showProgress();
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(WeeklyReportViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this, ViewModelFactor…:class.java\n            )");
        this.q = (WeeklyReportViewModel) viewModel;
        this.r = new WeeklyFitnessReportAdapter(this, this);
        ActivityWeeklyReportHistoryBinding activityWeeklyReportHistoryBinding = this.p;
        if (activityWeeklyReportHistoryBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWeeklyReportHistoryBinding = null;
        }
        RecyclerView recyclerView = activityWeeklyReportHistoryBinding.rvFitnessReport;
        WeeklyFitnessReportAdapter weeklyFitnessReportAdapter = this.r;
        if (weeklyFitnessReportAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("weeklyFitnessReportAdapter");
            weeklyFitnessReportAdapter = null;
        }
        recyclerView.setAdapter(weeklyFitnessReportAdapter);
        ActivityWeeklyReportHistoryBinding activityWeeklyReportHistoryBinding2 = this.p;
        if (activityWeeklyReportHistoryBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWeeklyReportHistoryBinding2 = null;
        }
        activityWeeklyReportHistoryBinding2.rvFitnessReport.setLayoutManager(new LinearLayoutManager(this, 1, false));
        s();
        if (AppUtils.isNetConnected(this)) {
            WeeklyReportViewModel weeklyReportViewModel2 = this.q;
            if (weeklyReportViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            } else {
                weeklyReportViewModel = weeklyReportViewModel2;
            }
            weeklyReportViewModel.getWeeklyReportHistory();
            return;
        }
        x(true);
        showNoInternetMessage();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == this.s) {
            if ((!(grantResults.length == 0)) && grantResults[0] == 0) {
                Toast.makeText(this, getString(R.string.grant_permissions), 1).show();
            }
        }
    }

    @Override // com.coveiot.android.weeklyreport.adapter.WeeklyFitnessReportAdapter.FitnessReportClickListener
    public void reportClick(@NotNull FitnessReportRes.FitnessReportItem reportData) {
        Intrinsics.checkNotNullParameter(reportData, "reportData");
        if (AppUtils.isNetConnected(this)) {
            w(reportData);
        } else {
            showNoInternetMessage();
        }
    }

    public final void s() {
        WeeklyReportViewModel weeklyReportViewModel = this.q;
        if (weeklyReportViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModel");
            weeklyReportViewModel = null;
        }
        MutableLiveData<List<FitnessReportRes.FitnessReportItem>> getWeeklyReportLiveData = weeklyReportViewModel.getGetWeeklyReportLiveData();
        if (getWeeklyReportLiveData != null) {
            final a aVar = new a();
            getWeeklyReportLiveData.observe(this, new Observer() { // from class: com.coveiot.android.weeklyreport.activities.g
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ActivityWeeklyReportHistory.t(Function1.this, obj);
                }
            });
        }
    }

    public final void u() {
        PermissionUtils.checkPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE", new ActivityWeeklyReportHistory$getWriteExternalPermission$1(this));
    }

    public final void w(FitnessReportRes.FitnessReportItem fitnessReportItem) {
        String string = getString(R.string.fitness_report);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.fitness_report)");
        initToolbar(string);
        getSupportFragmentManager().beginTransaction().replace(R.id.historyViewContainer, FragmentFitnessReportHistory.Companion.newInstance(fitnessReportItem)).commit();
    }

    public final void x(boolean z) {
        int i;
        ActivityWeeklyReportHistoryBinding activityWeeklyReportHistoryBinding = this.p;
        ActivityWeeklyReportHistoryBinding activityWeeklyReportHistoryBinding2 = null;
        if (activityWeeklyReportHistoryBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWeeklyReportHistoryBinding = null;
        }
        activityWeeklyReportHistoryBinding.tvMsg.setVisibility(0);
        ActivityWeeklyReportHistoryBinding activityWeeklyReportHistoryBinding3 = this.p;
        if (activityWeeklyReportHistoryBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWeeklyReportHistoryBinding3 = null;
        }
        activityWeeklyReportHistoryBinding3.rvFitnessReport.setVisibility(z ? 8 : 0);
        ActivityWeeklyReportHistoryBinding activityWeeklyReportHistoryBinding4 = this.p;
        if (activityWeeklyReportHistoryBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWeeklyReportHistoryBinding4 = null;
        }
        TextView textView = activityWeeklyReportHistoryBinding4.tvMsg;
        if (z) {
            i = R.string.you_do_not_have_any_historical;
        } else {
            i = R.string.you_can_view_the_historical;
        }
        textView.setText(getString(i));
        ActivityWeeklyReportHistoryBinding activityWeeklyReportHistoryBinding5 = this.p;
        if (activityWeeklyReportHistoryBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWeeklyReportHistoryBinding5 = null;
        }
        activityWeeklyReportHistoryBinding5.ivEmtyHistory.setVisibility(z ? 0 : 8);
        ActivityWeeklyReportHistoryBinding activityWeeklyReportHistoryBinding6 = this.p;
        if (activityWeeklyReportHistoryBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityWeeklyReportHistoryBinding2 = activityWeeklyReportHistoryBinding6;
        }
        activityWeeklyReportHistoryBinding2.tvEmpty.setVisibility(z ? 0 : 8);
    }
}
