package com.coveiot.android.fitnesschallenges.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.android.fitnesschallenges.adpter.PageAdapterChallengeHome;
import com.coveiot.android.fitnesschallenges.databinding.FragmentMyChallengeBinding;
import com.coveiot.android.theme.BaseFragment;
import com.google.android.material.tabs.TabLayout;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentMyChallenge extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public PageAdapterChallengeHome adapter;
    public FragmentMyChallengeBinding m;
    public int n;
    public int o;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentMyChallenge newInstance(int i) {
            FragmentMyChallenge fragmentMyChallenge = new FragmentMyChallenge();
            fragmentMyChallenge.setSubType(i);
            return fragmentMyChallenge;
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
    public final PageAdapterChallengeHome getAdapter() {
        PageAdapterChallengeHome pageAdapterChallengeHome = this.adapter;
        if (pageAdapterChallengeHome != null) {
            return pageAdapterChallengeHome;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        return null;
    }

    public final int getSubType() {
        return this.o;
    }

    public final FragmentMyChallengeBinding k() {
        FragmentMyChallengeBinding fragmentMyChallengeBinding = this.m;
        if (fragmentMyChallengeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return fragmentMyChallengeBinding;
    }

    public final void l() {
        LayoutInflater from = LayoutInflater.from(requireContext());
        int i = R.layout.custom_tab;
        View inflate = from.inflate(i, (ViewGroup) null);
        int i2 = R.id.tab_title;
        View findViewById = inflate.findViewById(i2);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) findViewById;
        int i3 = R.id.tab_badge;
        View findViewById2 = inflate.findViewById(i3);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById2).setVisibility(8);
        textView.setText(getResources().getString(R.string.my_creted));
        textView.setTextAppearance(R.style.selectedTabFont);
        textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.main_text_color));
        TabLayout.Tab tabAt = k().tabLayout.getTabAt(0);
        if (tabAt != null) {
            tabAt.setCustomView(inflate);
        }
        View inflate2 = LayoutInflater.from(requireContext()).inflate(i, (ViewGroup) null);
        View findViewById3 = inflate2.findViewById(i2);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView2 = (TextView) findViewById3;
        View findViewById4 = inflate2.findViewById(i3);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById4).setVisibility(8);
        int i4 = R.style.unSelectedTabFontGreyText;
        textView2.setTextAppearance(i4);
        textView2.setText(getResources().getString(R.string.joined));
        TabLayout.Tab tabAt2 = k().tabLayout.getTabAt(1);
        if (tabAt2 != null) {
            tabAt2.setCustomView(inflate2);
        }
        View inflate3 = LayoutInflater.from(requireContext()).inflate(i, (ViewGroup) null);
        View findViewById5 = inflate3.findViewById(i2);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView3 = (TextView) findViewById5;
        View findViewById6 = inflate3.findViewById(i3);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById6).setVisibility(8);
        textView3.setTextAppearance(i4);
        textView3.setText(getResources().getString(R.string.completed_ended));
        TabLayout.Tab tabAt3 = k().tabLayout.getTabAt(2);
        if (tabAt3 != null) {
            tabAt3.setCustomView(inflate3);
        }
        k().tabLayout.addOnTabSelectedListener((TabLayout.BaseOnTabSelectedListener) new TabLayout.OnTabSelectedListener() { // from class: com.coveiot.android.fitnesschallenges.fragments.FragmentMyChallenge$setUpTabLayout$1
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
                TextView textView4 = (TextView) findViewById7;
                textView4.setTextColor(ContextCompat.getColor(FragmentMyChallenge.this.requireContext(), R.color.main_text_color));
                textView4.setTextAppearance(R.style.selectedTabFont);
                FragmentMyChallenge.this.n = tab.getPosition();
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                View customView = tab.getCustomView();
                Intrinsics.checkNotNull(customView);
                View findViewById7 = customView.findViewById(R.id.tab_title);
                Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type android.widget.TextView");
                ((TextView) findViewById7).setTextAppearance(R.style.unSelectedTabFontGreyText);
            }
        });
    }

    public final void m() {
        PageAdapterChallengeHome adapter = getAdapter();
        FragmentMyCreated fragmentMyCreated = new FragmentMyCreated();
        String string = getString(R.string.my_creted);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.my_creted)");
        adapter.addFragment(fragmentMyCreated, string);
        PageAdapterChallengeHome adapter2 = getAdapter();
        FragmentJoined fragmentJoined = new FragmentJoined();
        String string2 = getString(R.string.joined);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.joined)");
        adapter2.addFragment(fragmentJoined, string2);
        PageAdapterChallengeHome adapter3 = getAdapter();
        FragmentCompleted fragmentCompleted = new FragmentCompleted();
        String string3 = getString(R.string.completed_ended);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.completed_ended)");
        adapter3.addFragment(fragmentCompleted, string3);
        k().viewPager.setAdapter(getAdapter());
        k().tabLayout.setupWithViewPager(k().viewPager);
        l();
        k().viewPager.setCurrentItem(this.o);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentMyChallengeBinding inflate = FragmentMyChallengeBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater,container,false)");
        this.m = inflate;
        return k().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        setAdapter(new PageAdapterChallengeHome(childFragmentManager));
        m();
    }

    public final void setAdapter(@NotNull PageAdapterChallengeHome pageAdapterChallengeHome) {
        Intrinsics.checkNotNullParameter(pageAdapterChallengeHome, "<set-?>");
        this.adapter = pageAdapterChallengeHome;
    }

    public final void setSubType(int i) {
        this.o = i;
    }
}
