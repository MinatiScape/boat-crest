package com.coveiot.leaderboard.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.utils.GridSpacingItemDecoration;
import com.coveiot.coveaccess.leaderboard.model.FilterType;
import com.coveiot.coveaccess.leaderboard.model.RankType;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.ViewModelFactory;
import com.coveiot.leaderboard.databinding.ActivityRankFilterBinding;
import com.coveiot.leaderboard.views.adapters.RanksFilterAdapter;
import com.coveiot.leaderboard.views.model.RankFilterModel;
import com.coveiot.leaderboard.views.viewmodel.LeaderboardViewModel;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class ActivityRanksFilter extends BaseActivity implements RanksFilterAdapter.OnItemClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String FILTER_TYPE_SELECTED_FILTER = "filter_type_selected_filter";
    @NotNull
    public static final String RANK_TYPE_SELECTED_FILTER = "rank_type_selected_filter";
    public ActivityRankFilterBinding p;
    @Nullable
    public RanksFilterAdapter q;
    @Nullable
    public RanksFilterAdapter s;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public List<RankFilterModel> r = new ArrayList();
    @NotNull
    public List<RankFilterModel> t = new ArrayList();
    @NotNull
    public String u = "";
    @NotNull
    public String v = "";

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public static final void s(ActivityRanksFilter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void t(ActivityRanksFilter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = this$0.getIntent();
        intent.putExtra(RANK_TYPE_SELECTED_FILTER, this$0.u);
        intent.putExtra(FILTER_TYPE_SELECTED_FILTER, this$0.v);
        this$0.setResult(-1, intent);
        this$0.finish();
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

    @Nullable
    public final RanksFilterAdapter getDayFilterAdapter() {
        return this.q;
    }

    @NotNull
    public final List<RankFilterModel> getDayFilterList() {
        return this.r;
    }

    @Nullable
    public final RanksFilterAdapter getLocalityFilterAdapter() {
        return this.s;
    }

    @NotNull
    public final List<RankFilterModel> getLocalityFilterList() {
        return this.t;
    }

    public final void initToolbar() {
        ActivityRankFilterBinding activityRankFilterBinding = this.p;
        ActivityRankFilterBinding activityRankFilterBinding2 = null;
        if (activityRankFilterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRankFilterBinding = null;
        }
        TextView textView = (TextView) activityRankFilterBinding.toolbar.findViewById(R.id.toolbar_title);
        ActivityRankFilterBinding activityRankFilterBinding3 = this.p;
        if (activityRankFilterBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityRankFilterBinding2 = activityRankFilterBinding3;
        }
        textView.setText(getString(R.string.select_filters));
        ((TextView) activityRankFilterBinding2.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.activities.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityRanksFilter.s(ActivityRanksFilter.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityRankFilterBinding inflate = ActivityRankFilterBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivityRankFilterBinding activityRankFilterBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        ViewModel viewModel = new ViewModelProvider(this, new ViewModelFactory(this)).get(LeaderboardViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProvider(this, …ardViewModel::class.java)");
        LeaderboardViewModel leaderboardViewModel = (LeaderboardViewModel) viewModel;
        initToolbar();
        Bundle extras = getIntent().getExtras();
        if ((extras != null ? extras.get(RANK_TYPE_SELECTED_FILTER) : null) != null) {
            Bundle extras2 = getIntent().getExtras();
            if ((extras2 != null ? extras2.get(FILTER_TYPE_SELECTED_FILTER) : null) != null) {
                Bundle extras3 = getIntent().getExtras();
                Object obj = extras3 != null ? extras3.get(RANK_TYPE_SELECTED_FILTER) : null;
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.coveiot.coveaccess.leaderboard.model.RankType");
                RankType rankType = (RankType) obj;
                Bundle extras4 = getIntent().getExtras();
                Object obj2 = extras4 != null ? extras4.get(FILTER_TYPE_SELECTED_FILTER) : null;
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type com.coveiot.coveaccess.leaderboard.model.FilterType");
                FilterType filterType = (FilterType) obj2;
            }
        }
        ArrayList arrayList = new ArrayList();
        this.r = arrayList;
        String string = getString(R.string.rank_daily);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.rank_daily)");
        arrayList.add(new RankFilterModel(false, string));
        List<RankFilterModel> list = this.r;
        String string2 = getString(R.string.rank_weekly);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.rank_weekly)");
        list.add(new RankFilterModel(false, string2));
        List<RankFilterModel> list2 = this.r;
        String string3 = getString(R.string.rank_monthly);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.rank_monthly)");
        list2.add(new RankFilterModel(false, string3));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        ActivityRankFilterBinding activityRankFilterBinding2 = this.p;
        if (activityRankFilterBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRankFilterBinding2 = null;
        }
        activityRankFilterBinding2.rvDayFilter.addItemDecoration(new GridSpacingItemDecoration(3, 30, false));
        ActivityRankFilterBinding activityRankFilterBinding3 = this.p;
        if (activityRankFilterBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRankFilterBinding3 = null;
        }
        activityRankFilterBinding3.rvDayFilter.setLayoutManager(gridLayoutManager);
        this.q = new RanksFilterAdapter(this, this.r, true, this);
        ActivityRankFilterBinding activityRankFilterBinding4 = this.p;
        if (activityRankFilterBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRankFilterBinding4 = null;
        }
        activityRankFilterBinding4.rvDayFilter.setAdapter(this.q);
        ArrayList arrayList2 = new ArrayList();
        this.t = arrayList2;
        String string4 = getString(R.string.rank_locality);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.rank_locality)");
        arrayList2.add(new RankFilterModel(false, string4));
        List<RankFilterModel> list3 = this.t;
        String string5 = getString(R.string.rank_state);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.rank_state)");
        list3.add(new RankFilterModel(false, string5));
        List<RankFilterModel> list4 = this.t;
        String string6 = getString(R.string.rank_city);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.rank_city)");
        list4.add(new RankFilterModel(false, string6));
        List<RankFilterModel> list5 = this.t;
        String string7 = getString(R.string.rank_country);
        Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.rank_country)");
        list5.add(new RankFilterModel(false, string7));
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this, 3);
        ActivityRankFilterBinding activityRankFilterBinding5 = this.p;
        if (activityRankFilterBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRankFilterBinding5 = null;
        }
        activityRankFilterBinding5.rvLocalityFilter.addItemDecoration(new GridSpacingItemDecoration(3, 30, false));
        ActivityRankFilterBinding activityRankFilterBinding6 = this.p;
        if (activityRankFilterBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRankFilterBinding6 = null;
        }
        activityRankFilterBinding6.rvLocalityFilter.setLayoutManager(gridLayoutManager2);
        this.s = new RanksFilterAdapter(this, this.t, false, this);
        ActivityRankFilterBinding activityRankFilterBinding7 = this.p;
        if (activityRankFilterBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRankFilterBinding7 = null;
        }
        activityRankFilterBinding7.rvLocalityFilter.setAdapter(this.s);
        ActivityRankFilterBinding activityRankFilterBinding8 = this.p;
        if (activityRankFilterBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRankFilterBinding8 = null;
        }
        activityRankFilterBinding8.infoDetails.tvInfoText.setText(getString(R.string.note_disclaimer));
        ActivityRankFilterBinding activityRankFilterBinding9 = this.p;
        if (activityRankFilterBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityRankFilterBinding = activityRankFilterBinding9;
        }
        activityRankFilterBinding.btnNext.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.activities.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityRanksFilter.t(ActivityRanksFilter.this, view);
            }
        });
    }

    @Override // com.coveiot.leaderboard.views.adapters.RanksFilterAdapter.OnItemClickListener
    public void onItemClicked(@NotNull RankFilterModel rankFilter, boolean z) {
        Intrinsics.checkNotNullParameter(rankFilter, "rankFilter");
        ActivityRankFilterBinding activityRankFilterBinding = this.p;
        if (activityRankFilterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRankFilterBinding = null;
        }
        activityRankFilterBinding.btnNext.setEnabled(true);
        if (z && rankFilter.isSelected()) {
            this.u = rankFilter.getFilterName();
        }
        if (z || !rankFilter.isSelected()) {
            return;
        }
        this.v = rankFilter.getFilterName();
    }

    public final void setDayFilterAdapter(@Nullable RanksFilterAdapter ranksFilterAdapter) {
        this.q = ranksFilterAdapter;
    }

    public final void setDayFilterList(@NotNull List<RankFilterModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.r = list;
    }

    public final void setLocalityFilterAdapter(@Nullable RanksFilterAdapter ranksFilterAdapter) {
        this.s = ranksFilterAdapter;
    }

    public final void setLocalityFilterList(@NotNull List<RankFilterModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.t = list;
    }
}
