package com.coveiot.android.leonardo.dashboard.home.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentFitnessHomeBinding;
import com.coveiot.android.leonardo.dashboard.home.adapters.FitnessHomePagerAdapter;
import com.coveiot.android.theme.BaseFragment;
import com.google.android.material.tabs.TabLayout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FragmentFitnessHome extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public FitnessHomePagerAdapter fitnessHomePagerAdapter;
    @Nullable
    public FragmentFitnessHomeBinding p;
    public int q;
    public int r;
    @Nullable
    public Calendar s;
    public int t;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String m = "FragmentFitnessHome";
    @NotNull
    public ArrayList<Fragment> n = new ArrayList<>();
    @NotNull
    public String[] o = new String[0];

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentFitnessHome newInstance() {
            return new FragmentFitnessHome();
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
    public final FitnessHomePagerAdapter getFitnessHomePagerAdapter() {
        FitnessHomePagerAdapter fitnessHomePagerAdapter = this.fitnessHomePagerAdapter;
        if (fitnessHomePagerAdapter != null) {
            return fitnessHomePagerAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("fitnessHomePagerAdapter");
        return null;
    }

    @NotNull
    public final String getTAG() {
        return this.m;
    }

    public final int getTabPos() {
        return this.t;
    }

    public final FragmentFitnessHomeBinding k() {
        FragmentFitnessHomeBinding fragmentFitnessHomeBinding = this.p;
        Intrinsics.checkNotNull(fragmentFitnessHomeBinding);
        return fragmentFitnessHomeBinding;
    }

    public final void l(String[] strArr) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.customized_tab_view, (ViewGroup) null);
        View findViewById = inflate.findViewById(R.id.tab_title);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) findViewById;
        View findViewById2 = inflate.findViewById(R.id.tab_badge);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.ImageView");
        ImageView imageView = (ImageView) findViewById2;
        if (!(strArr.length == 0)) {
            textView.setText(strArr[0]);
        }
        TabLayout.Tab tabAt = k().tabLayout.getTabAt(0);
        if (tabAt != null) {
            tabAt.setCustomView(inflate);
        }
        View inflate2 = LayoutInflater.from(getActivity()).inflate(R.layout.customized_tab_view, (ViewGroup) null);
        View findViewById3 = inflate2.findViewById(R.id.tab_title);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView2 = (TextView) findViewById3;
        View findViewById4 = inflate2.findViewById(R.id.tab_badge);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.ImageView");
        ImageView imageView2 = (ImageView) findViewById4;
        if (!(strArr.length == 0)) {
            textView2.setText(strArr[1]);
        }
        if (this.t == 0) {
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
        k().tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.coveiot.android.leonardo.dashboard.home.fragments.FragmentFitnessHome$setUpTabLayout$1
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
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            Intrinsics.checkNotNull(arguments);
            this.q = arguments.getInt("tab_pos");
            Bundle arguments2 = getArguments();
            Intrinsics.checkNotNull(arguments2);
            this.r = arguments2.getInt("is_from_dashboard");
            Bundle arguments3 = getArguments();
            Intrinsics.checkNotNull(arguments3);
            if (arguments3.getSerializable("calender") != null) {
                Bundle arguments4 = getArguments();
                Intrinsics.checkNotNull(arguments4);
                Serializable serializable = arguments4.getSerializable("calender");
                Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type java.util.Calendar");
                this.s = (Calendar) serializable;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.p = FragmentFitnessHomeBinding.inflate(inflater, viewGroup, false);
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
        String[] stringArray = getResources().getStringArray(R.array.fitness_home);
        Intrinsics.checkNotNullExpressionValue(stringArray, "resources.getStringArray(R.array.fitness_home)");
        this.o = stringArray;
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        setFitnessHomePagerAdapter(new FitnessHomePagerAdapter(childFragmentManager));
        FitnessHomePagerAdapter fitnessHomePagerAdapter = getFitnessHomePagerAdapter();
        String[] strArr = this.o;
        Intrinsics.checkNotNull(strArr);
        fitnessHomePagerAdapter.setTitles(strArr);
        this.n.clear();
        String[] strArr2 = this.o;
        Intrinsics.checkNotNull(strArr2);
        int length = strArr2.length;
        for (int i = 0; i < length; i++) {
            String[] strArr3 = this.o;
            Intrinsics.checkNotNull(strArr3);
            String str = strArr3[i];
            String string = getResources().getString(R.string.fitness);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.fitness)");
            if (StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) string, false, 2, (Object) null)) {
                this.n.add(FragmentFitness.Companion.newInstance());
            } else {
                String[] strArr4 = this.o;
                Intrinsics.checkNotNull(strArr4);
                String str2 = strArr4[i];
                String string2 = getResources().getString(R.string.vitals);
                Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.vitals)");
                if (StringsKt__StringsKt.contains$default((CharSequence) str2, (CharSequence) string2, false, 2, (Object) null)) {
                    this.n.add(FragmentVitals.Companion.newInstance(this.q, this.s));
                }
            }
        }
        getFitnessHomePagerAdapter().setFragments(this.n);
        k().tabLayout.setTabMode(1);
        k().viewPager.setAdapter(getFitnessHomePagerAdapter());
        k().viewPager.setOffscreenPageLimit(this.o.length);
        k().tabLayout.setupWithViewPager(k().viewPager);
        getFitnessHomePagerAdapter().getFragments().get(0);
        getFitnessHomePagerAdapter().getFragments().get(1);
        k().viewPager.setPagingEnabled(false);
        if (this.r == 1) {
            this.t = 1;
        }
        k().viewPager.setCurrentItem(this.t);
        l(this.o);
    }

    public final void setFitnessHomePagerAdapter(@NotNull FitnessHomePagerAdapter fitnessHomePagerAdapter) {
        Intrinsics.checkNotNullParameter(fitnessHomePagerAdapter, "<set-?>");
        this.fitnessHomePagerAdapter = fitnessHomePagerAdapter;
    }

    public final void setTabPos(int i) {
        this.t = i;
    }
}
