package com.coveiot.android.sportsnotification.filters;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.coveiot.android.sportsnotification.R;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.databinding.ActivitySportsFilterBinding;
import com.coveiot.android.sportsnotification.filters.adapter.AdapterFilterOptions;
import com.coveiot.android.sportsnotification.filters.adapter.AdapterSoccerFilter;
import com.coveiot.android.sportsnotification.filters.fragments.FragmentFilterOptions;
import com.coveiot.android.sportsnotification.model.Filters;
import com.coveiot.android.sportsnotification.model.SoccerFilterConfig;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.ktx.RemoteConfigKt;
import com.google.gson.Gson;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SportsFilterActivity extends BaseActivity implements AdapterFilterOptions.FilterSelectionListener {
    public ActivitySportsFilterBinding p;
    @Nullable
    public ArrayList<Integer> q;
    @Nullable
    public Filters s;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final String r = SportsFilterActivity.class.getSimpleName();

    public static final void A(FirebaseRemoteConfig remoteConfig, SportsFilterActivity this$0, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        String string = remoteConfig.getString("soccer_filte_config");
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(C…CER_FILTER_CONFIGURATION)");
        SoccerFilterConfig soccerFilterConfigData = (SoccerFilterConfig) new Gson().fromJson(string, (Class<Object>) SoccerFilterConfig.class);
        SportsPreference.Companion companion = SportsPreference.Companion;
        Intrinsics.checkNotNullExpressionValue(soccerFilterConfigData, "soccerFilterConfigData");
        companion.saveSoccerConfig(this$0, soccerFilterConfigData);
        this$0.x();
    }

    public static final void B(SportsFilterActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = this$0.getIntent();
        intent.putExtra("extra_selected_filter_options", this$0.q);
        this$0.setResult(-1, intent);
        this$0.finish();
    }

    public static final void C(SportsFilterActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ArrayList<Integer> arrayList = this$0.q;
        if (arrayList != null) {
            arrayList.clear();
        }
        this$0.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentFilterOptions.Companion.newInstance(this$0.s, this$0.q)).commit();
    }

    public static final void w(SportsFilterActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void z(final FirebaseRemoteConfig remoteConfig, final SportsFilterActivity this$0, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            Void r3 = (Void) task.getResult();
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.sportsnotification.filters.e
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    SportsFilterActivity.A(FirebaseRemoteConfig.this, this$0, task2);
                }
            });
            return;
        }
        String string = remoteConfig.getString("soccer_filte_config");
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(C…CER_FILTER_CONFIGURATION)");
        SoccerFilterConfig soccerFilterConfigData = (SoccerFilterConfig) new Gson().fromJson(string, (Class<Object>) SoccerFilterConfig.class);
        SportsPreference.Companion companion = SportsPreference.Companion;
        Intrinsics.checkNotNullExpressionValue(soccerFilterConfigData, "soccerFilterConfigData");
        companion.saveSoccerConfig(this$0, soccerFilterConfigData);
        this$0.x();
        LogHelper.e(this$0.r, "Remote Config Failed");
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

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivitySportsFilterBinding inflate = ActivitySportsFilterBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        if (getIntent().hasExtra("extra_selected_filter_options")) {
            Bundle extras = getIntent().getExtras();
            Serializable serializable = extras != null ? extras.getSerializable("extra_selected_filter_options") : null;
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type java.util.ArrayList<kotlin.Int>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.Int> }");
            this.q = (ArrayList) serializable;
        }
        v();
        final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
        remoteConfig.setDefaultsAsync(R.xml.remote_config_details);
        remoteConfig.fetch(10L).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.sportsnotification.filters.d
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                SportsFilterActivity.z(FirebaseRemoteConfig.this, this, task);
            }
        });
        ActivitySportsFilterBinding activitySportsFilterBinding = this.p;
        if (activitySportsFilterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportsFilterBinding = null;
        }
        activitySportsFilterBinding.btnApply.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.filters.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsFilterActivity.B(SportsFilterActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.clear_filters)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.filters.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsFilterActivity.C(SportsFilterActivity.this, view);
            }
        });
        ArrayList<Integer> arrayList = this.q;
        if (arrayList != null) {
            boolean z = true;
            if (arrayList == null || arrayList.size() != 1) {
                z = false;
            }
            if (z) {
                TextView textView = (TextView) _$_findCachedViewById(R.id.tv_filters_applied);
                StringBuilder sb = new StringBuilder();
                ArrayList<Integer> arrayList2 = this.q;
                sb.append(arrayList2 != null ? Integer.valueOf(arrayList2.size()) : null);
                sb.append(" Filter Applied");
                textView.setText(sb.toString());
                return;
            }
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.tv_filters_applied);
            StringBuilder sb2 = new StringBuilder();
            ArrayList<Integer> arrayList3 = this.q;
            sb2.append(arrayList3 != null ? Integer.valueOf(arrayList3.size()) : null);
            sb2.append(" Filters Applied");
            textView2.setText(sb2.toString());
            return;
        }
        ((TextView) _$_findCachedViewById(R.id.tv_filters_applied)).setText("0 Filters Applied");
    }

    @Override // com.coveiot.android.sportsnotification.filters.adapter.AdapterFilterOptions.FilterSelectionListener
    public void onFilterOption(int i, boolean z) {
        if (this.q == null) {
            this.q = new ArrayList<>();
        }
        if (z) {
            ArrayList<Integer> arrayList = this.q;
            if (arrayList != null) {
                arrayList.add(Integer.valueOf(i));
                return;
            }
            return;
        }
        ArrayList<Integer> arrayList2 = this.q;
        if (arrayList2 != null) {
            arrayList2.remove(Integer.valueOf(i));
        }
    }

    public final void v() {
        ActivitySportsFilterBinding activitySportsFilterBinding = this.p;
        ActivitySportsFilterBinding activitySportsFilterBinding2 = null;
        if (activitySportsFilterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportsFilterBinding = null;
        }
        ((TextView) activitySportsFilterBinding.toolbar.findViewById(R.id.toolbar_title)).setText(getString(R.string.filters));
        ActivitySportsFilterBinding activitySportsFilterBinding3 = this.p;
        if (activitySportsFilterBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySportsFilterBinding2 = activitySportsFilterBinding3;
        }
        ((TextView) activitySportsFilterBinding2.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.filters.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsFilterActivity.w(SportsFilterActivity.this, view);
            }
        });
    }

    public final AdapterSoccerFilter x() {
        SoccerFilterConfig soccerConfig = SportsPreference.Companion.getSoccerConfig(this);
        ActivitySportsFilterBinding activitySportsFilterBinding = this.p;
        if (activitySportsFilterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportsFilterBinding = null;
        }
        activitySportsFilterBinding.rcvFilterTypes.setLayoutManager(new LinearLayoutManager(this));
        AdapterSoccerFilter adapterSoccerFilter = new AdapterSoccerFilter(this);
        ActivitySportsFilterBinding activitySportsFilterBinding2 = this.p;
        if (activitySportsFilterBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportsFilterBinding2 = null;
        }
        activitySportsFilterBinding2.rcvFilterTypes.setAdapter(adapterSoccerFilter);
        adapterSoccerFilter.setData(soccerConfig != null ? soccerConfig.getFilters() : null);
        adapterSoccerFilter.setItemClickListener(new AdapterSoccerFilter.ItemClickListener() { // from class: com.coveiot.android.sportsnotification.filters.SportsFilterActivity$loadFilterData$1
            @Override // com.coveiot.android.sportsnotification.filters.adapter.AdapterSoccerFilter.ItemClickListener
            public void onItemClick(@Nullable Filters filters) {
                SportsFilterActivity.this.y(filters);
            }
        });
        return adapterSoccerFilter;
    }

    public final void y(Filters filters) {
        this.s = filters;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentFilterOptions.Companion.newInstance(filters, this.q)).commit();
    }
}
