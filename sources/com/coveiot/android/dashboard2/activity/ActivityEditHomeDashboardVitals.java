package com.coveiot.android.dashboard2.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.dashboard2.Dashboard2PreferenceManager;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.dashboard2.ViewModelFactory;
import com.coveiot.android.dashboard2.adapter.EditHomeDashboardVitalListAdapter;
import com.coveiot.android.dashboard2.customui.Listener;
import com.coveiot.android.dashboard2.model.FitnessVitalsDataModel;
import com.coveiot.android.dashboard2.model.SelectedFitnessVitalsDataModel;
import com.coveiot.android.dashboard2.viewmodel.EditDashboardVitalsViewModel;
import com.coveiot.android.theme.BaseActivity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class ActivityEditHomeDashboardVitals extends BaseActivity implements Listener {
    public EditDashboardVitalsViewModel p;
    public EditHomeDashboardVitalListAdapter q;
    @Nullable
    public RecyclerView s;
    @Nullable
    public TextView t;
    public EditHomeDashboardVitalListAdapter u;
    @Nullable
    public RecyclerView w;
    @Nullable
    public TextView x;
    @Nullable
    public Button y;
    @Nullable
    public TextView z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ArrayList<FitnessVitalsDataModel> r = new ArrayList<>();
    @NotNull
    public ArrayList<FitnessVitalsDataModel> v = new ArrayList<>();

    /* loaded from: classes4.dex */
    public static final class a extends Lambda implements Function1<List<FitnessVitalsDataModel>, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<FitnessVitalsDataModel> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable List<FitnessVitalsDataModel> list) {
            boolean z = true;
            if (list == null || list.isEmpty()) {
                return;
            }
            ActivityEditHomeDashboardVitals.this.r.clear();
            ActivityEditHomeDashboardVitals activityEditHomeDashboardVitals = ActivityEditHomeDashboardVitals.this;
            Intrinsics.checkNotNull(list, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.dashboard2.model.FitnessVitalsDataModel>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.dashboard2.model.FitnessVitalsDataModel> }");
            activityEditHomeDashboardVitals.r = (ArrayList) list;
            EditHomeDashboardVitalListAdapter editHomeDashboardVitalListAdapter = ActivityEditHomeDashboardVitals.this.q;
            if (editHomeDashboardVitalListAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("topListAdapter");
                editHomeDashboardVitalListAdapter = null;
            }
            Object clone = ActivityEditHomeDashboardVitals.this.r.clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.dashboard2.model.FitnessVitalsDataModel>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.dashboard2.model.FitnessVitalsDataModel> }");
            editHomeDashboardVitalListAdapter.setList((ArrayList) clone);
            ArrayList arrayList = ActivityEditHomeDashboardVitals.this.r;
            if (arrayList != null && !arrayList.isEmpty()) {
                z = false;
            }
            if (z) {
                TextView textView = ActivityEditHomeDashboardVitals.this.t;
                if (textView == null) {
                    return;
                }
                textView.setVisibility(0);
                return;
            }
            TextView textView2 = ActivityEditHomeDashboardVitals.this.t;
            if (textView2 == null) {
                return;
            }
            textView2.setVisibility(8);
        }
    }

    /* loaded from: classes4.dex */
    public static final class b extends Lambda implements Function1<List<FitnessVitalsDataModel>, Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<FitnessVitalsDataModel> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable List<FitnessVitalsDataModel> list) {
            boolean z = true;
            if (list == null || list.isEmpty()) {
                return;
            }
            ActivityEditHomeDashboardVitals.this.v.clear();
            ActivityEditHomeDashboardVitals activityEditHomeDashboardVitals = ActivityEditHomeDashboardVitals.this;
            Intrinsics.checkNotNull(list, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.dashboard2.model.FitnessVitalsDataModel>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.dashboard2.model.FitnessVitalsDataModel> }");
            activityEditHomeDashboardVitals.v = (ArrayList) list;
            EditHomeDashboardVitalListAdapter editHomeDashboardVitalListAdapter = ActivityEditHomeDashboardVitals.this.u;
            if (editHomeDashboardVitalListAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bottomListAdapter");
                editHomeDashboardVitalListAdapter = null;
            }
            Object clone = ActivityEditHomeDashboardVitals.this.v.clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.dashboard2.model.FitnessVitalsDataModel>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.dashboard2.model.FitnessVitalsDataModel> }");
            editHomeDashboardVitalListAdapter.setList((ArrayList) clone);
            ArrayList arrayList = ActivityEditHomeDashboardVitals.this.r;
            if (arrayList != null && !arrayList.isEmpty()) {
                z = false;
            }
            if (z) {
                TextView textView = ActivityEditHomeDashboardVitals.this.x;
                if (textView == null) {
                    return;
                }
                textView.setVisibility(0);
                return;
            }
            TextView textView2 = ActivityEditHomeDashboardVitals.this.x;
            if (textView2 == null) {
                return;
            }
            textView2.setVisibility(8);
        }
    }

    public static final void A(ActivityEditHomeDashboardVitals this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        EditHomeDashboardVitalListAdapter editHomeDashboardVitalListAdapter = this$0.q;
        EditHomeDashboardVitalListAdapter editHomeDashboardVitalListAdapter2 = null;
        if (editHomeDashboardVitalListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("topListAdapter");
            editHomeDashboardVitalListAdapter = null;
        }
        List<FitnessVitalsDataModel> list = editHomeDashboardVitalListAdapter.getList();
        if (!(list == null || list.isEmpty())) {
            Dashboard2PreferenceManager singletonHolder = Dashboard2PreferenceManager.Companion.getInstance(this$0);
            EditHomeDashboardVitalListAdapter editHomeDashboardVitalListAdapter3 = this$0.q;
            if (editHomeDashboardVitalListAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("topListAdapter");
                editHomeDashboardVitalListAdapter3 = null;
            }
            List<FitnessVitalsDataModel> list2 = editHomeDashboardVitalListAdapter3.getList();
            Intrinsics.checkNotNull(list2);
            singletonHolder.saveSelectedFitnessVitals(new SelectedFitnessVitalsDataModel(CollectionsKt___CollectionsKt.toMutableList((Collection) list2)));
            EditDashboardVitalsViewModel editDashboardVitalsViewModel = this$0.p;
            if (editDashboardVitalsViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                editDashboardVitalsViewModel = null;
            }
            EditHomeDashboardVitalListAdapter editHomeDashboardVitalListAdapter4 = this$0.q;
            if (editHomeDashboardVitalListAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("topListAdapter");
            } else {
                editHomeDashboardVitalListAdapter2 = editHomeDashboardVitalListAdapter4;
            }
            List<FitnessVitalsDataModel> list3 = editHomeDashboardVitalListAdapter2.getList();
            Intrinsics.checkNotNull(list3);
            editDashboardVitalsViewModel.saveEditedVitalsToServer(CollectionsKt___CollectionsKt.toMutableList((Collection) list3));
        }
        this$0.setResult(-1);
        this$0.finish();
    }

    public static final void B(ActivityEditHomeDashboardVitals this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void w(ActivityEditHomeDashboardVitals this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void y(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void z(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
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

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.edit_vital_cards_first_cap));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.activity.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditHomeDashboardVitals.w(ActivityEditHomeDashboardVitals.this, view);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_edit_dashboard_vitals);
        initToolbar();
        this.y = (Button) findViewById(R.id.saveBtn);
        this.z = (TextView) findViewById(R.id.cancelBtn);
        x();
        v();
        EditDashboardVitalsViewModel editDashboardVitalsViewModel = (EditDashboardVitalsViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(EditDashboardVitalsViewModel.class);
        this.p = editDashboardVitalsViewModel;
        EditDashboardVitalsViewModel editDashboardVitalsViewModel2 = null;
        if (editDashboardVitalsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            editDashboardVitalsViewModel = null;
        }
        MutableLiveData<List<FitnessVitalsDataModel>> selectedFitnessVitalsLiveData = editDashboardVitalsViewModel.getSelectedFitnessVitalsLiveData();
        final a aVar = new a();
        selectedFitnessVitalsLiveData.observe(this, new Observer() { // from class: com.coveiot.android.dashboard2.activity.e
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityEditHomeDashboardVitals.y(Function1.this, obj);
            }
        });
        EditDashboardVitalsViewModel editDashboardVitalsViewModel3 = this.p;
        if (editDashboardVitalsViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            editDashboardVitalsViewModel3 = null;
        }
        MutableLiveData<List<FitnessVitalsDataModel>> moreFitnessVitalsLiveData = editDashboardVitalsViewModel3.getMoreFitnessVitalsLiveData();
        final b bVar = new b();
        moreFitnessVitalsLiveData.observe(this, new Observer() { // from class: com.coveiot.android.dashboard2.activity.d
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityEditHomeDashboardVitals.z(Function1.this, obj);
            }
        });
        EditDashboardVitalsViewModel editDashboardVitalsViewModel4 = this.p;
        if (editDashboardVitalsViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            editDashboardVitalsViewModel4 = null;
        }
        editDashboardVitalsViewModel4.getSelectedFitnessVitals();
        EditDashboardVitalsViewModel editDashboardVitalsViewModel5 = this.p;
        if (editDashboardVitalsViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            editDashboardVitalsViewModel2 = editDashboardVitalsViewModel5;
        }
        editDashboardVitalsViewModel2.getMoreFitnessVitals();
        Button button = this.y;
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.activity.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityEditHomeDashboardVitals.A(ActivityEditHomeDashboardVitals.this, view);
                }
            });
        }
        TextView textView = this.z;
        if (textView != null) {
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.activity.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityEditHomeDashboardVitals.B(ActivityEditHomeDashboardVitals.this, view);
                }
            });
        }
    }

    @Override // com.coveiot.android.dashboard2.customui.Listener
    public void onMaximum() {
        if (isFinishing()) {
            return;
        }
        Toast.makeText(this, getString(R.string.max_5_card_warning), 0).show();
    }

    @Override // com.coveiot.android.dashboard2.customui.Listener
    public void onMinimum() {
        if (isFinishing()) {
            return;
        }
        Toast.makeText(this, getString(R.string.min_1_card_warning), 0).show();
    }

    @Override // com.coveiot.android.dashboard2.customui.Listener
    public void setEmptyListBottom(boolean z) {
        if (z) {
            RecyclerView recyclerView = this.w;
            if (recyclerView != null) {
                recyclerView.setVisibility(8);
            }
            TextView textView = this.x;
            if (textView == null) {
                return;
            }
            textView.setVisibility(0);
            return;
        }
        RecyclerView recyclerView2 = this.w;
        if (recyclerView2 != null) {
            recyclerView2.setVisibility(0);
        }
        TextView textView2 = this.x;
        if (textView2 == null) {
            return;
        }
        textView2.setVisibility(8);
    }

    @Override // com.coveiot.android.dashboard2.customui.Listener
    public void setEmptyListTop(boolean z) {
        if (z) {
            RecyclerView recyclerView = this.s;
            if (recyclerView != null) {
                recyclerView.setVisibility(8);
            }
            TextView textView = this.t;
            if (textView == null) {
                return;
            }
            textView.setVisibility(0);
            return;
        }
        RecyclerView recyclerView2 = this.s;
        if (recyclerView2 != null) {
            recyclerView2.setVisibility(0);
        }
        TextView textView2 = this.t;
        if (textView2 == null) {
            return;
        }
        textView2.setVisibility(8);
    }

    public final void v() {
        this.x = (TextView) findViewById(R.id.emptyView1);
        this.w = (RecyclerView) findViewById(R.id.rvSelectedVitals1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = this.w;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        RecyclerView recyclerView2 = this.w;
        if (recyclerView2 != null) {
            recyclerView2.addItemDecoration(new DividerItemDecoration(this, 0));
        }
        EditHomeDashboardVitalListAdapter editHomeDashboardVitalListAdapter = new EditHomeDashboardVitalListAdapter(this, this.v, this);
        this.u = editHomeDashboardVitalListAdapter;
        RecyclerView recyclerView3 = this.w;
        if (recyclerView3 != null) {
            recyclerView3.setAdapter(editHomeDashboardVitalListAdapter);
        }
        RecyclerView recyclerView4 = this.w;
        EditHomeDashboardVitalListAdapter editHomeDashboardVitalListAdapter2 = null;
        if (recyclerView4 != null) {
            EditHomeDashboardVitalListAdapter editHomeDashboardVitalListAdapter3 = this.u;
            if (editHomeDashboardVitalListAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bottomListAdapter");
                editHomeDashboardVitalListAdapter3 = null;
            }
            recyclerView4.setOnDragListener(editHomeDashboardVitalListAdapter3.getDragInstance());
        }
        TextView textView = this.x;
        if (textView != null) {
            EditHomeDashboardVitalListAdapter editHomeDashboardVitalListAdapter4 = this.u;
            if (editHomeDashboardVitalListAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bottomListAdapter");
            } else {
                editHomeDashboardVitalListAdapter2 = editHomeDashboardVitalListAdapter4;
            }
            textView.setOnDragListener(editHomeDashboardVitalListAdapter2.getDragInstance());
        }
    }

    public final void x() {
        this.t = (TextView) findViewById(R.id.emptyView);
        this.s = (RecyclerView) findViewById(R.id.rvSelectedVitals);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = this.s;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        RecyclerView recyclerView2 = this.s;
        if (recyclerView2 != null) {
            recyclerView2.addItemDecoration(new DividerItemDecoration(this, 0));
        }
        EditHomeDashboardVitalListAdapter editHomeDashboardVitalListAdapter = new EditHomeDashboardVitalListAdapter(this, this.r, this);
        this.q = editHomeDashboardVitalListAdapter;
        RecyclerView recyclerView3 = this.s;
        if (recyclerView3 != null) {
            recyclerView3.setAdapter(editHomeDashboardVitalListAdapter);
        }
        RecyclerView recyclerView4 = this.s;
        EditHomeDashboardVitalListAdapter editHomeDashboardVitalListAdapter2 = null;
        if (recyclerView4 != null) {
            EditHomeDashboardVitalListAdapter editHomeDashboardVitalListAdapter3 = this.q;
            if (editHomeDashboardVitalListAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("topListAdapter");
                editHomeDashboardVitalListAdapter3 = null;
            }
            recyclerView4.setOnDragListener(editHomeDashboardVitalListAdapter3.getDragInstance());
        }
        TextView textView = this.t;
        if (textView != null) {
            EditHomeDashboardVitalListAdapter editHomeDashboardVitalListAdapter4 = this.q;
            if (editHomeDashboardVitalListAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("topListAdapter");
            } else {
                editHomeDashboardVitalListAdapter2 = editHomeDashboardVitalListAdapter4;
            }
            textView.setOnDragListener(editHomeDashboardVitalListAdapter2.getDragInstance());
        }
    }
}
