package com.coveiot.android.navigation.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.navigation.R;
import com.coveiot.android.navigation.ViewModelFactory;
import com.coveiot.android.navigation.activities.ActivityNavigationRecentHistory;
import com.coveiot.android.navigation.adapter.RecentHistoryAdapter;
import com.coveiot.android.navigation.databinding.ActivityNavigationRecentHistoryBinding;
import com.coveiot.android.navigation.db.model.RecentSearchHistoryData;
import com.coveiot.android.navigation.interfaces.RecentHistorySearchListener;
import com.coveiot.android.navigation.repository.RecentSearchHistoryRepository;
import com.coveiot.android.navigation.viewModels.NavigationMainViewModel;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
/* loaded from: classes5.dex */
public final class ActivityNavigationRecentHistory extends BaseActivity implements RecentHistorySearchListener {
    public ActivityNavigationRecentHistoryBinding binding;
    public NavigationMainViewModel q;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public String p = ActivityNavigationRecentHistoryBinding.class.getSimpleName();

    @DebugMetadata(c = "com.coveiot.android.navigation.activities.ActivityNavigationRecentHistory$initListeners$1$1", f = "ActivityNavigationRecentHistory.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

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
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                RecentSearchHistoryRepository.Companion.getInstance(ActivityNavigationRecentHistory.this).clearRecentSearchHistory();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.navigation.activities.ActivityNavigationRecentHistory$loadRecentSearchHistory$1", f = "ActivityNavigationRecentHistory.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(ActivityNavigationRecentHistory activityNavigationRecentHistory, List it) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            if (!(!it.isEmpty())) {
                activityNavigationRecentHistory.v();
                return;
            }
            activityNavigationRecentHistory.getBinding().rvRecentHistory.setAdapter(new RecentHistoryAdapter(it, true, activityNavigationRecentHistory));
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(continuation);
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
                if (!ActivityNavigationRecentHistory.this.isFinishing()) {
                    NavigationMainViewModel navigationMainViewModel = ActivityNavigationRecentHistory.this.q;
                    NavigationMainViewModel navigationMainViewModel2 = null;
                    if (navigationMainViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        navigationMainViewModel = null;
                    }
                    navigationMainViewModel.getRecentSearchHistory();
                    NavigationMainViewModel navigationMainViewModel3 = ActivityNavigationRecentHistory.this.q;
                    if (navigationMainViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    } else {
                        navigationMainViewModel2 = navigationMainViewModel3;
                    }
                    LiveData<List<RecentSearchHistoryData>> recentSearchHistoryData = navigationMainViewModel2.getRecentSearchHistoryData();
                    final ActivityNavigationRecentHistory activityNavigationRecentHistory = ActivityNavigationRecentHistory.this;
                    recentSearchHistoryData.observe(activityNavigationRecentHistory, new Observer() { // from class: com.coveiot.android.navigation.activities.k0
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(Object obj2) {
                            ActivityNavigationRecentHistory.b.invokeSuspend$lambda$0(ActivityNavigationRecentHistory.this, (List) obj2);
                        }
                    });
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void t(ActivityNavigationRecentHistory this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getIO(), null, new a(null), 2, null);
    }

    public static final void y(ActivityNavigationRecentHistory this$0, View view) {
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

    @NotNull
    public final ActivityNavigationRecentHistoryBinding getBinding() {
        ActivityNavigationRecentHistoryBinding activityNavigationRecentHistoryBinding = this.binding;
        if (activityNavigationRecentHistoryBinding != null) {
            return activityNavigationRecentHistoryBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final String getTAG() {
        return this.p;
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityNavigationRecentHistoryBinding inflate = ActivityNavigationRecentHistoryBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        setBinding(inflate);
        setContentView(getBinding().getRoot());
        this.q = (NavigationMainViewModel) new ViewModelProvider(this, new ViewModelFactory(this)).get(NavigationMainViewModel.class);
        x();
    }

    @Override // com.coveiot.android.navigation.interfaces.RecentHistorySearchListener
    public void onRecentHistorySelected(@NotNull RecentSearchHistoryData recentSearchHistoryData) {
        Intrinsics.checkNotNullParameter(recentSearchHistoryData, "recentSearchHistoryData");
    }

    public final void s() {
        getBinding().tvClearAllRecentHistory.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.j0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationRecentHistory.t(ActivityNavigationRecentHistory.this, view);
            }
        });
    }

    public final void setBinding(@NotNull ActivityNavigationRecentHistoryBinding activityNavigationRecentHistoryBinding) {
        Intrinsics.checkNotNullParameter(activityNavigationRecentHistoryBinding, "<set-?>");
        this.binding = activityNavigationRecentHistoryBinding;
    }

    public final void setTAG(String str) {
        this.p = str;
    }

    public final void u() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new b(null), 2, null);
    }

    public final void v() {
        getBinding().tvNoRecentHistory.setVisibility(0);
        getBinding().clRecentHistory.setVisibility(8);
    }

    public final void w() {
        getBinding().tvNoRecentHistory.setVisibility(8);
        getBinding().clRecentHistory.setVisibility(0);
    }

    public final void x() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getResources().getString(R.string.recent_history));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.i0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationRecentHistory.y(ActivityNavigationRecentHistory.this, view);
            }
        });
        w();
        u();
        s();
    }
}
