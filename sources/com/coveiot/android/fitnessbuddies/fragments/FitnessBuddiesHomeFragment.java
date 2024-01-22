package com.coveiot.android.fitnessbuddies.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.adapters.BuddiesTabAdapter;
import com.coveiot.android.fitnessbuddies.databinding.FragmentFitnessBuddiesBinding;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class FitnessBuddiesHomeFragment extends BaseFragment {
    public BuddiesTabAdapter adapter;
    public int m;
    @Nullable
    public FragmentFitnessBuddiesBinding n;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ArrayList<String> o = new ArrayList<>();

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
    public final BuddiesTabAdapter getAdapter() {
        BuddiesTabAdapter buddiesTabAdapter = this.adapter;
        if (buddiesTabAdapter != null) {
            return buddiesTabAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        return null;
    }

    public final int getTabPos() {
        return this.m;
    }

    public final FragmentFitnessBuddiesBinding k() {
        FragmentFitnessBuddiesBinding fragmentFitnessBuddiesBinding = this.n;
        Intrinsics.checkNotNull(fragmentFitnessBuddiesBinding);
        return fragmentFitnessBuddiesBinding;
    }

    public final void l(ArrayList<String> arrayList) {
        LayoutInflater from = LayoutInflater.from(getActivity());
        int i = R.layout.customized_tab_view;
        View inflate = from.inflate(i, (ViewGroup) null);
        int i2 = R.id.tab_title;
        View findViewById = inflate.findViewById(i2);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) findViewById;
        int i3 = R.id.tab_badge;
        View findViewById2 = inflate.findViewById(i3);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.ImageView");
        ImageView imageView = (ImageView) findViewById2;
        if (!arrayList.isEmpty()) {
            textView.setText(arrayList.get(0));
        }
        TabLayout.Tab tabAt = k().tabLayout.getTabAt(0);
        if (tabAt != null) {
            tabAt.setCustomView(inflate);
        }
        View inflate2 = LayoutInflater.from(getActivity()).inflate(i, (ViewGroup) null);
        View findViewById3 = inflate2.findViewById(i2);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView2 = (TextView) findViewById3;
        View findViewById4 = inflate2.findViewById(i3);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.ImageView");
        ImageView imageView2 = (ImageView) findViewById4;
        if (!arrayList.isEmpty()) {
            textView2.setText(arrayList.get(1));
        }
        if (this.m == 0) {
            imageView.setVisibility(0);
            imageView2.setVisibility(8);
            textView.setTextAppearance(R.style.selectedTabFont);
            textView2.setTextAppearance(R.style.unSelectedTabFont);
        } else {
            imageView.setVisibility(8);
            imageView2.setVisibility(0);
            textView.setTextAppearance(R.style.unSelectedTabFont);
            textView2.setTextAppearance(R.style.selectedTabFont);
        }
        TabLayout.Tab tabAt2 = k().tabLayout.getTabAt(1);
        if (tabAt2 != null) {
            tabAt2.setCustomView(inflate2);
        }
        k().tabLayout.addOnTabSelectedListener((TabLayout.BaseOnTabSelectedListener) new TabLayout.OnTabSelectedListener() { // from class: com.coveiot.android.fitnessbuddies.fragments.FitnessBuddiesHomeFragment$setUpTabLayout$1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                View customView = tab.getCustomView();
                Intrinsics.checkNotNull(customView);
                View findViewById5 = customView.findViewById(R.id.tab_badge);
                Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.ImageView");
                View findViewById6 = customView.findViewById(R.id.tab_title);
                Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type android.widget.TextView");
                ((TextView) findViewById6).setTextAppearance(R.style.selectedTabFont);
                ((ImageView) findViewById5).setVisibility(0);
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                View customView = tab.getCustomView();
                Intrinsics.checkNotNull(customView);
                View findViewById5 = customView.findViewById(R.id.tab_badge);
                Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.ImageView");
                ((ImageView) findViewById5).setVisibility(8);
                View findViewById6 = customView.findViewById(R.id.tab_title);
                Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type android.widget.TextView");
                ((TextView) findViewById6).setTextAppearance(R.style.unSelectedTabFont);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null && getArguments() != null) {
            this.m = requireArguments().getInt("tab_pos");
        }
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        setAdapter(new BuddiesTabAdapter(childFragmentManager));
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.n = FragmentFitnessBuddiesBinding.inflate(inflater, viewGroup, false);
        return k().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        if (AppUtils.isBuddies) {
            k().viewPager.setCurrentItem(2);
            AppUtils.isBuddies = false;
        }
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        CoveEventBusManager.getInstance().getEventBus().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        CoveEventBusManager.getInstance().getEventBus().unregister(this);
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        BuddiesTabAdapter adapter = getAdapter();
        NotificationFragment notificationFragment = new NotificationFragment();
        String string = getString(R.string.buddies);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.buddies)");
        adapter.addFragment(notificationFragment, string);
        BuddiesTabAdapter adapter2 = getAdapter();
        MessagesFragment messagesFragment = new MessagesFragment();
        int i = R.string.messages;
        String string2 = getString(i);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.messages)");
        adapter2.addFragment(messagesFragment, string2);
        k().viewPager.setAdapter(getAdapter());
        k().viewPager.setOffscreenPageLimit(2);
        k().tabLayout.setupWithViewPager(k().viewPager);
        this.o.add(requireContext().getString(R.string.my_buddies));
        this.o.add(requireContext().getString(i));
        l(this.o);
        k().viewPager.setCurrentItem(this.m);
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.BOTTOM_MENU_BUDDY_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
    }

    public final void setAdapter(@NotNull BuddiesTabAdapter buddiesTabAdapter) {
        Intrinsics.checkNotNullParameter(buddiesTabAdapter, "<set-?>");
        this.adapter = buddiesTabAdapter;
    }

    public final void setTabPos(int i) {
        this.m = i;
    }
}
