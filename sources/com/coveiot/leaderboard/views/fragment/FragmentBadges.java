package com.coveiot.leaderboard.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.utils.GridSpacingItemDecoration;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.leaderboard.LeaderBoardNavigator;
import com.coveiot.leaderboard.callbacks.IMyBadgeItemClick;
import com.coveiot.leaderboard.databinding.FragmentBadgesBinding;
import com.coveiot.leaderboard.utils.LeaderBoardDataUtiils;
import com.coveiot.leaderboard.views.BadgesDetailsDialog;
import com.coveiot.leaderboard.views.adapters.HomeMyBadgesAdapter;
import com.coveiot.leaderboard.views.adapters.HomeMyDailyBadgesAdapter;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class FragmentBadges extends BaseFragment implements IMyBadgeItemClick {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public FragmentBadgesBinding m;
    @Nullable
    public HomeMyBadgesAdapter n;
    @Nullable
    public HomeMyDailyBadgesAdapter o;
    @Nullable
    public BadgesDetailsDialog r;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public List<MyBadgesModel.DataBean.BadgesBean> p = new ArrayList();
    @NotNull
    public List<MyBadgesModel.DataBean.BadgesBean> q = new ArrayList();

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentBadges newInstance() {
            return new FragmentBadges();
        }
    }

    public static final int o(MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean, MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean2) {
        return Intrinsics.compare(badgeLevelsBean.getLevelWeight(), badgeLevelsBean2.getLevelWeight());
    }

    public static final int p(MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean, MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean2) {
        return Intrinsics.compare(badgeLevelsBean.getLevelWeight(), badgeLevelsBean2.getLevelWeight());
    }

    public static final void r(FragmentBadges this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LeaderBoardNavigator.navigateToBadgesScreen(this$0.getActivity());
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
    public final List<MyBadgesModel.DataBean.BadgesBean> getDailyBadgesList() {
        return this.p;
    }

    @Nullable
    public final HomeMyBadgesAdapter getMyBadgesAdapter() {
        return this.n;
    }

    @Nullable
    public final HomeMyDailyBadgesAdapter getMyDailyBadgesAdapter() {
        return this.o;
    }

    @NotNull
    public final List<MyBadgesModel.DataBean.BadgesBean> getSpecialBadgesList() {
        return this.q;
    }

    public final void n() {
        MyBadgesModel myBadgesModel = (MyBadgesModel) new Gson().fromJson(LeaderBoardDataUtiils.getMyBadges(getActivity()), (Class<Object>) MyBadgesModel.class);
        if (myBadgesModel != null && myBadgesModel.getData() != null && myBadgesModel.getData().getBadges().size() > 0) {
            ConstraintLayout constraintLayout = q().clNoBadge;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clNoBadge");
            gone(constraintLayout);
            ConstraintLayout constraintLayout2 = q().clBadge;
            Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clBadge");
            visible(constraintLayout2);
            for (MyBadgesModel.DataBean.BadgesBean i : myBadgesModel.getData().getBadges()) {
                if (i.getCategoryId() != null && Intrinsics.areEqual(i.getCategoryId(), "DAILY")) {
                    List<MyBadgesModel.DataBean.BadgesBean> list = this.p;
                    Intrinsics.checkNotNullExpressionValue(i, "i");
                    list.add(i);
                } else {
                    List<MyBadgesModel.DataBean.BadgesBean> list2 = this.q;
                    Intrinsics.checkNotNullExpressionValue(i, "i");
                    list2.add(i);
                }
            }
            for (MyBadgesModel.DataBean.BadgesBean badgesBean : this.p) {
                List<MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean> badgeLevels = badgesBean.getBadgeLevels();
                Intrinsics.checkNotNull(badgeLevels);
                Collections.sort(badgeLevels, new Comparator() { // from class: com.coveiot.leaderboard.views.fragment.e
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        int o;
                        o = FragmentBadges.o((MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean) obj, (MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean) obj2);
                        return o;
                    }
                });
            }
            for (MyBadgesModel.DataBean.BadgesBean badgesBean2 : this.q) {
                List<MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean> badgeLevels2 = badgesBean2.getBadgeLevels();
                Intrinsics.checkNotNull(badgeLevels2);
                Collections.sort(badgeLevels2, new Comparator() { // from class: com.coveiot.leaderboard.views.fragment.d
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        int p;
                        p = FragmentBadges.p((MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean) obj, (MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean) obj2);
                        return p;
                    }
                });
            }
            GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
            boolean z = false;
            q().rvDailyBadgeList.addItemDecoration(new GridSpacingItemDecoration(2, 30, false));
            q().rvDailyBadgeList.setLayoutManager(gridLayoutManager);
            HomeMyDailyBadgesAdapter homeMyDailyBadgesAdapter = new HomeMyDailyBadgesAdapter(requireContext(), this, Boolean.FALSE);
            this.o = homeMyDailyBadgesAdapter;
            Intrinsics.checkNotNull(homeMyDailyBadgesAdapter);
            homeMyDailyBadgesAdapter.setData(this.p);
            q().rvDailyBadgeList.setAdapter(this.o);
            List<MyBadgesModel.DataBean.BadgesBean> list3 = this.p;
            if (list3 == null || list3.isEmpty()) {
                RecyclerView recyclerView = q().rvDailyBadgeList;
                Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.rvDailyBadgeList");
                gone(recyclerView);
                TextView textView = q().tvDailyBadges;
                Intrinsics.checkNotNullExpressionValue(textView, "binding.tvDailyBadges");
                gone(textView);
            }
            GridLayoutManager gridLayoutManager2 = new GridLayoutManager(requireContext(), 2);
            q().rvSpecialBadgeList.addItemDecoration(new GridSpacingItemDecoration(2, 30, false));
            q().rvSpecialBadgeList.setLayoutManager(gridLayoutManager2);
            HomeMyBadgesAdapter homeMyBadgesAdapter = new HomeMyBadgesAdapter(requireContext(), this);
            this.n = homeMyBadgesAdapter;
            Intrinsics.checkNotNull(homeMyBadgesAdapter);
            homeMyBadgesAdapter.setData(this.q);
            q().rvSpecialBadgeList.setAdapter(this.n);
            List<MyBadgesModel.DataBean.BadgesBean> list4 = this.q;
            if ((list4 == null || list4.isEmpty()) ? true : true) {
                RecyclerView recyclerView2 = q().rvSpecialBadgeList;
                Intrinsics.checkNotNullExpressionValue(recyclerView2, "binding.rvSpecialBadgeList");
                gone(recyclerView2);
                TextView textView2 = q().tvSpecialBadges;
                Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvSpecialBadges");
                gone(textView2);
            }
        } else {
            ConstraintLayout constraintLayout3 = q().clNoBadge;
            Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.clNoBadge");
            visible(constraintLayout3);
            ConstraintLayout constraintLayout4 = q().clBadge;
            Intrinsics.checkNotNullExpressionValue(constraintLayout4, "binding.clBadge");
            gone(constraintLayout4);
        }
        dismissProgress();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentBadgesBinding inflate = FragmentBadgesBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater,container,false)");
        this.m = inflate;
        return q().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.leaderboard.callbacks.IMyBadgeItemClick
    public void onMyBadgeItemClick(@Nullable MyBadgesModel.DataBean.BadgesBean badgesBean) {
        BadgesDetailsDialog badgesDetailsDialog;
        BadgesDetailsDialog badgesDetailsDialog2 = this.r;
        if (badgesDetailsDialog2 != null) {
            boolean z = true;
            if (badgesDetailsDialog2 == null || !badgesDetailsDialog2.isShowing()) {
                z = false;
            }
            if (z && (badgesDetailsDialog = this.r) != null) {
                badgesDetailsDialog.dismiss();
            }
            this.r = null;
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        BadgesDetailsDialog badgesDetailsDialog3 = new BadgesDetailsDialog(requireActivity, badgesBean);
        this.r = badgesDetailsDialog3;
        badgesDetailsDialog3.setCancelableOutside(false);
        BadgesDetailsDialog badgesDetailsDialog4 = this.r;
        if (badgesDetailsDialog4 != null) {
            badgesDetailsDialog4.show();
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
        n();
        q().ivBadges.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.fragment.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentBadges.r(FragmentBadges.this, view2);
            }
        });
    }

    public final FragmentBadgesBinding q() {
        FragmentBadgesBinding fragmentBadgesBinding = this.m;
        if (fragmentBadgesBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return fragmentBadgesBinding;
    }

    public final void setDailyBadgesList(@NotNull List<MyBadgesModel.DataBean.BadgesBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.p = list;
    }

    public final void setMyBadgesAdapter(@Nullable HomeMyBadgesAdapter homeMyBadgesAdapter) {
        this.n = homeMyBadgesAdapter;
    }

    public final void setMyDailyBadgesAdapter(@Nullable HomeMyDailyBadgesAdapter homeMyDailyBadgesAdapter) {
        this.o = homeMyDailyBadgesAdapter;
    }

    public final void setSpecialBadgesList(@NotNull List<MyBadgesModel.DataBean.BadgesBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.q = list;
    }
}
