package com.coveiot.android.fitnessbuddies.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.adapters.BuddiesTabAdapter;
import com.coveiot.android.fitnessbuddies.models.FitnessAcceptedCloversGoalData;
import com.coveiot.android.fitnessbuddies.models.FitnessCheerEvent;
import com.coveiot.android.fitnessbuddies.utils.PreferenceManager;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.material.tabs.TabLayout;
import com.squareup.otto.Subscribe;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class FitnessBuddiesFragment extends BaseFragment {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public BuddiesTabAdapter adapter;
    @Nullable
    public TextView m;
    @Nullable
    public TextView n;
    public int o;

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
        return this.o;
    }

    public final void k() {
        LayoutInflater from = LayoutInflater.from(getActivity());
        int i = R.layout.custom_tab;
        View inflate = from.inflate(i, (ViewGroup) null);
        int i2 = R.id.tab_title;
        View findViewById = inflate.findViewById(i2);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) findViewById;
        int i3 = R.id.tab_badge;
        View findViewById2 = inflate.findViewById(i3);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        textView.setText(getResources().getString(R.string.my_buddies));
        textView.setTextAppearance(R.style.selectedTabFont);
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        textView.setTextColor(ContextCompat.getColor(context, R.color.main_text_color));
        ((TextView) findViewById2).setVisibility(8);
        int i4 = R.id.tabLayout;
        TabLayout.Tab tabAt = ((TabLayout) _$_findCachedViewById(i4)).getTabAt(0);
        if (tabAt != null) {
            tabAt.setCustomView(inflate);
        }
        View inflate2 = LayoutInflater.from(getActivity()).inflate(i, (ViewGroup) null);
        View findViewById3 = inflate2.findViewById(i2);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView2 = (TextView) findViewById3;
        View findViewById4 = inflate2.findViewById(i3);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView3 = (TextView) findViewById4;
        this.n = textView3;
        Intrinsics.checkNotNull(textView3);
        textView3.setVisibility(8);
        int i5 = R.style.unSelectedTabFontOnBoarding;
        textView2.setTextAppearance(i5);
        textView2.setText(getResources().getString(R.string.messages));
        TabLayout.Tab tabAt2 = ((TabLayout) _$_findCachedViewById(i4)).getTabAt(1);
        if (tabAt2 != null) {
            tabAt2.setCustomView(inflate2);
        }
        View inflate3 = LayoutInflater.from(getActivity()).inflate(i, (ViewGroup) null);
        View findViewById5 = inflate3.findViewById(i2);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView4 = (TextView) findViewById5;
        textView4.setTextAppearance(i5);
        View findViewById6 = inflate3.findViewById(i3);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView5 = (TextView) findViewById6;
        this.m = textView5;
        Intrinsics.checkNotNull(textView5);
        textView5.setVisibility(8);
        textView4.setText(getResources().getString(R.string.manage_buddies));
        textView4.setLineSpacing(1.0f, 1.5f);
        textView4.setLines(2);
        TabLayout.Tab tabAt3 = ((TabLayout) _$_findCachedViewById(i4)).getTabAt(2);
        if (tabAt3 != null) {
            tabAt3.setCustomView(inflate3);
        }
        ((TabLayout) _$_findCachedViewById(i4)).addOnTabSelectedListener((TabLayout.BaseOnTabSelectedListener) new TabLayout.OnTabSelectedListener() { // from class: com.coveiot.android.fitnessbuddies.fragments.FitnessBuddiesFragment$setUpTabLayout$1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                View customView = tab.getCustomView();
                Intrinsics.checkNotNull(customView);
                View findViewById7 = customView.findViewById(R.id.tab_title);
                Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type android.widget.TextView");
                TextView textView6 = (TextView) findViewById7;
                Context context2 = FitnessBuddiesFragment.this.getContext();
                Intrinsics.checkNotNull(context2);
                textView6.setTextColor(ContextCompat.getColor(context2, R.color.main_text_color));
                textView6.setTextAppearance(R.style.selectedTabFont);
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                View customView = tab.getCustomView();
                Intrinsics.checkNotNull(customView);
                View findViewById7 = customView.findViewById(R.id.tab_title);
                Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type android.widget.TextView");
                ((TextView) findViewById7).setTextAppearance(R.style.unSelectedTabFontOnBoarding);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null && getArguments() != null) {
            Bundle arguments = getArguments();
            Intrinsics.checkNotNull(arguments);
            this.o = arguments.getInt("tab_pos");
        }
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        setAdapter(new BuddiesTabAdapter(childFragmentManager));
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_fitness_buddies, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Subscribe
    public final void onFitnessCheerNotification(@NotNull FitnessCheerEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (!isAdded() || ((ViewPager) _$_findCachedViewById(R.id.viewPager)).getCurrentItem() == 1) {
            return;
        }
        PreferenceManager.Companion companion = PreferenceManager.Companion;
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type android.content.Context");
        if (companion.getFitnessCheerCount(activity) > 0) {
            TextView textView = this.n;
            Intrinsics.checkNotNull(textView);
            textView.setVisibility(0);
            TextView textView2 = this.n;
            Intrinsics.checkNotNull(textView2);
            StringBuilder sb = new StringBuilder();
            sb.append("");
            FragmentActivity activity2 = getActivity();
            Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type android.content.Context");
            sb.append(companion.getFitnessCheerCount(activity2));
            textView2.setText(sb.toString());
            return;
        }
        TextView textView3 = this.n;
        Intrinsics.checkNotNull(textView3);
        textView3.setVisibility(8);
    }

    @Subscribe
    public final void onFitnessRequestNotification(@NotNull FitnessAcceptedCloversGoalData event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (!isAdded() || ((ViewPager) _$_findCachedViewById(R.id.viewPager)).getCurrentItem() == 2) {
            return;
        }
        PreferenceManager.Companion companion = PreferenceManager.Companion;
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type android.content.Context");
        if (companion.getFitnessNotificationCount(activity) > 0) {
            TextView textView = this.m;
            Intrinsics.checkNotNull(textView);
            textView.setVisibility(0);
            TextView textView2 = this.m;
            Intrinsics.checkNotNull(textView2);
            StringBuilder sb = new StringBuilder();
            sb.append("");
            FragmentActivity activity2 = getActivity();
            Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type android.content.Context");
            sb.append(companion.getFitnessNotificationCount(activity2));
            textView2.setText(sb.toString());
            TextView textView3 = this.m;
            Intrinsics.checkNotNull(textView3);
            ViewGroup.LayoutParams layoutParams = textView3.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            layoutParams2.setMargins(0, 0, (int) TypedValue.applyDimension(1, 5.0f, context.getResources().getDisplayMetrics()), 0);
            TextView textView4 = this.m;
            Intrinsics.checkNotNull(textView4);
            textView4.setLayoutParams(layoutParams2);
            return;
        }
        TextView textView5 = this.m;
        Intrinsics.checkNotNull(textView5);
        textView5.setVisibility(8);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        if (AppUtils.isBuddies) {
            ((ViewPager) _$_findCachedViewById(R.id.viewPager)).setCurrentItem(2);
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
        String string2 = getString(R.string.messages);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.messages)");
        adapter2.addFragment(messagesFragment, string2);
        BuddiesTabAdapter adapter3 = getAdapter();
        BuddiesFragment buddiesFragment = new BuddiesFragment();
        String string3 = getString(R.string.manage_buddies);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.manage_buddies)");
        adapter3.addFragment(buddiesFragment, string3);
        int i = R.id.viewPager;
        ((ViewPager) _$_findCachedViewById(i)).setAdapter(getAdapter());
        ((ViewPager) _$_findCachedViewById(i)).setOffscreenPageLimit(3);
        ((TabLayout) _$_findCachedViewById(R.id.tabLayout)).setupWithViewPager((ViewPager) _$_findCachedViewById(i));
        k();
        ((ViewPager) _$_findCachedViewById(i)).setCurrentItem(this.o);
        ((ViewPager) _$_findCachedViewById(i)).addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.coveiot.android.fitnessbuddies.fragments.FitnessBuddiesFragment$onViewCreated$1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i2, float f, int i3) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i2) {
                TextView textView;
                TextView textView2;
                TextView textView3;
                TextView textView4;
                FitnessBuddiesFragment fitnessBuddiesFragment = FitnessBuddiesFragment.this;
                int i3 = R.id.viewPager;
                boolean z = true;
                if (((ViewPager) fitnessBuddiesFragment._$_findCachedViewById(i3)).getCurrentItem() == 2) {
                    textView3 = FitnessBuddiesFragment.this.m;
                    if (textView3 != null && textView3.getVisibility() == 0) {
                        AnalyticsLog analyticsLog = new AnalyticsLog();
                        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.BOTTOM_MENU_BUDDY_SCREEN.getValue());
                        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_BUDDIES_SCREEN.getValue());
                        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.BUDDIES_BUTTON.getValue());
                        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                        textView4 = FitnessBuddiesFragment.this.m;
                        Intrinsics.checkNotNull(textView4);
                        textView4.setVisibility(0);
                        PreferenceManager.Companion companion = PreferenceManager.Companion;
                        FragmentActivity activity = FitnessBuddiesFragment.this.getActivity();
                        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type android.content.Context");
                        companion.saveFitnessNotificationCount(activity, 0);
                        return;
                    }
                }
                if (((ViewPager) FitnessBuddiesFragment.this._$_findCachedViewById(i3)).getCurrentItem() == 1) {
                    textView = FitnessBuddiesFragment.this.n;
                    if (textView == null || textView.getVisibility() != 0) {
                        z = false;
                    }
                    if (z) {
                        AnalyticsLog analyticsLog2 = new AnalyticsLog();
                        analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                        analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.BOTTOM_MENU_BUDDY_SCREEN.getValue());
                        analyticsLog2.setDescription(FirebaseEventParams.Description.OPEN_MESSAGES_SCREEN.getValue());
                        analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.MESSAGES_BUTTON.getValue());
                        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
                        textView2 = FitnessBuddiesFragment.this.n;
                        Intrinsics.checkNotNull(textView2);
                        textView2.setVisibility(8);
                        PreferenceManager.Companion companion2 = PreferenceManager.Companion;
                        FragmentActivity activity2 = FitnessBuddiesFragment.this.getActivity();
                        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type android.content.Context");
                        companion2.saveFitnessCheerCount(activity2, 0);
                        return;
                    }
                }
                AnalyticsLog analyticsLog3 = new AnalyticsLog();
                analyticsLog3.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                analyticsLog3.setScreenName(FirebaseEventParams.ScreenName.BOTTOM_MENU_BUDDY_SCREEN.getValue());
                analyticsLog3.setDescription(FirebaseEventParams.Description.OPEN_NOTIFICATION_SCREEN.getValue());
                analyticsLog3.setUiElementName(FirebaseEventParams.UiElementName.NOTIFICATIONS_BUTTON.getValue());
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog3);
            }
        });
        onFitnessRequestNotification(new FitnessAcceptedCloversGoalData());
        onFitnessCheerNotification(new FitnessCheerEvent());
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
        this.o = i;
    }
}
