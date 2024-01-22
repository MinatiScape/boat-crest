package com.coveiot.leaderboard.views.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.utils.GridSpacingItemDecoration;
import com.coveiot.coveaccess.leaderboard.model.AllBadgesModel;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.databinding.AllBadgesFragmentBinding;
import com.coveiot.leaderboard.utils.LeaderBoardDataUtiils;
import com.coveiot.leaderboard.views.AllBadgesDetailsDialog;
import com.coveiot.leaderboard.views.adapters.DailyBadgesListAdapter;
import com.coveiot.leaderboard.views.adapters.SpecialBadgesListAdapter;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class FragmentAllBadges extends BaseFragment implements SpecialBadgesListAdapter.OnItemClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public AllBadgesFragmentBinding m;
    @Nullable
    public DailyBadgesListAdapter n;
    @Nullable
    public SpecialBadgesListAdapter o;
    @Nullable
    public AllBadgesDetailsDialog r;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public List<AllBadgesModel.DataBean.BadgesBean> p = new ArrayList();
    @NotNull
    public List<AllBadgesModel.DataBean.BadgesBean> q = new ArrayList();

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentAllBadges newInstance() {
            return new FragmentAllBadges();
        }
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

    @NotNull
    public final List<AllBadgesModel.DataBean.BadgesBean> getDailyBadgesList() {
        return this.p;
    }

    @Nullable
    public final DailyBadgesListAdapter getDailyBadgesListAdapter() {
        return this.n;
    }

    @NotNull
    public final List<AllBadgesModel.DataBean.BadgesBean> getSpecialBadgesList() {
        return this.q;
    }

    @Nullable
    public final SpecialBadgesListAdapter getSpecialBadgesListAdapter() {
        return this.o;
    }

    public final void k() {
        AllBadgesModel.DataBean data;
        AllBadgesModel.DataBean data2;
        AllBadgesModel.DataBean data3;
        AllBadgesModel.DataBean data4;
        AllBadgesModel allBadgesModel = (AllBadgesModel) new Gson().fromJson(LeaderBoardDataUtiils.getDailyBadges(getActivity()), (Class<Object>) AllBadgesModel.class);
        AllBadgesModel allBadgesModel2 = (AllBadgesModel) new Gson().fromJson(LeaderBoardDataUtiils.getSpecialBadges(getActivity()), (Class<Object>) AllBadgesModel.class);
        this.p = new ArrayList();
        if (((allBadgesModel == null || (data4 = allBadgesModel.getData()) == null) ? null : data4.getBadges()) != null) {
            List<AllBadgesModel.DataBean.BadgesBean> badges = (allBadgesModel == null || (data3 = allBadgesModel.getData()) == null) ? null : data3.getBadges();
            Intrinsics.checkNotNull(badges);
            if (badges.size() > 0) {
                List<AllBadgesModel.DataBean.BadgesBean> badges2 = allBadgesModel.getData().getBadges();
                Intrinsics.checkNotNullExpressionValue(badges2, "dailyBadgesModel.data.badges");
                this.p = badges2;
            }
        }
        this.q = new ArrayList();
        if (((allBadgesModel2 == null || (data2 = allBadgesModel2.getData()) == null) ? null : data2.getBadges()) != null) {
            List<AllBadgesModel.DataBean.BadgesBean> badges3 = (allBadgesModel2 == null || (data = allBadgesModel2.getData()) == null) ? null : data.getBadges();
            Intrinsics.checkNotNull(badges3);
            if (badges3.size() > 0) {
                List<AllBadgesModel.DataBean.BadgesBean> badges4 = allBadgesModel2.getData().getBadges();
                Intrinsics.checkNotNullExpressionValue(badges4, "specialBadgesModel.data.badges");
                this.q = badges4;
            }
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        boolean z = false;
        l().rvDailyBadgeList.addItemDecoration(new GridSpacingItemDecoration(2, 30, false));
        l().rvDailyBadgeList.setLayoutManager(gridLayoutManager);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.n = new DailyBadgesListAdapter(requireContext, this.p, this);
        l().rvDailyBadgeList.setAdapter(this.n);
        List<AllBadgesModel.DataBean.BadgesBean> list = this.p;
        if (list == null || list.isEmpty()) {
            RecyclerView recyclerView = l().rvDailyBadgeList;
            Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.rvDailyBadgeList");
            gone(recyclerView);
            TextView textView = l().tvDailyBadges;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvDailyBadges");
            gone(textView);
        }
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(requireContext(), 2);
        l().rvSpecialBadgeList.addItemDecoration(new GridSpacingItemDecoration(2, 30, false));
        l().rvSpecialBadgeList.setLayoutManager(gridLayoutManager2);
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        this.o = new SpecialBadgesListAdapter(requireContext2, this.q, this);
        l().rvSpecialBadgeList.setAdapter(this.o);
        List<AllBadgesModel.DataBean.BadgesBean> list2 = this.q;
        if (list2 == null || list2.isEmpty()) {
            z = true;
        }
        if (z) {
            RecyclerView recyclerView2 = l().rvSpecialBadgeList;
            Intrinsics.checkNotNullExpressionValue(recyclerView2, "binding.rvSpecialBadgeList");
            gone(recyclerView2);
            TextView textView2 = l().tvSpecialBadges;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvSpecialBadges");
            gone(textView2);
        } else {
            TextView textView3 = l().tvSpecialBadges;
            StringBuilder sb = new StringBuilder();
            Context context = getContext();
            sb.append(context != null ? context.getString(R.string.special_badges) : null);
            sb.append(" (");
            sb.append(this.q.size());
            sb.append(HexStringBuilder.COMMENT_END_CHAR);
            textView3.setText(sb.toString());
        }
        dismissProgress();
    }

    public final AllBadgesFragmentBinding l() {
        AllBadgesFragmentBinding allBadgesFragmentBinding = this.m;
        if (allBadgesFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return allBadgesFragmentBinding;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        AllBadgesFragmentBinding inflate = AllBadgesFragmentBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater,container,false)");
        this.m = inflate;
        return l().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.leaderboard.views.adapters.SpecialBadgesListAdapter.OnItemClickListener
    public void onItemClicked(@NotNull AllBadgesModel.DataBean.BadgesBean badgesBean) {
        AllBadgesDetailsDialog allBadgesDetailsDialog;
        Intrinsics.checkNotNullParameter(badgesBean, "badgesBean");
        AllBadgesDetailsDialog allBadgesDetailsDialog2 = this.r;
        if (allBadgesDetailsDialog2 != null) {
            boolean z = true;
            if (allBadgesDetailsDialog2 == null || !allBadgesDetailsDialog2.isShowing()) {
                z = false;
            }
            if (z && (allBadgesDetailsDialog = this.r) != null) {
                allBadgesDetailsDialog.dismiss();
            }
            this.r = null;
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        AllBadgesDetailsDialog allBadgesDetailsDialog3 = new AllBadgesDetailsDialog(requireActivity, badgesBean);
        this.r = allBadgesDetailsDialog3;
        allBadgesDetailsDialog3.setCancelableOutside(false);
        AllBadgesDetailsDialog allBadgesDetailsDialog4 = this.r;
        if (allBadgesDetailsDialog4 != null) {
            allBadgesDetailsDialog4.show();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        showProgress(true);
        k();
    }

    public final void setDailyBadgesList(@NotNull List<AllBadgesModel.DataBean.BadgesBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.p = list;
    }

    public final void setDailyBadgesListAdapter(@Nullable DailyBadgesListAdapter dailyBadgesListAdapter) {
        this.n = dailyBadgesListAdapter;
    }

    public final void setSpecialBadgesList(@NotNull List<AllBadgesModel.DataBean.BadgesBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.q = list;
    }

    public final void setSpecialBadgesListAdapter(@Nullable SpecialBadgesListAdapter specialBadgesListAdapter) {
        this.o = specialBadgesListAdapter;
    }
}
