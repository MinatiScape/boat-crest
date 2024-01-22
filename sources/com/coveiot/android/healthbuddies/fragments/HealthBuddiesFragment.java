package com.coveiot.android.healthbuddies.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.healthbuddies.R;
import com.coveiot.android.healthbuddies.activities.AddHealthBuddiesActivity;
import com.coveiot.android.healthbuddies.adapters.HealthBuddiesTabAdapter;
import com.coveiot.android.healthbuddies.constants.Constants;
import com.coveiot.android.healthbuddies.model.GuardianDeletionEvent;
import com.coveiot.android.healthbuddies.utils.HealthBuddiesPreferenceManager;
import com.coveiot.android.healthbuddies.utils.HealthBuddiesViewModelFactory;
import com.coveiot.android.healthbuddies.viewmodels.ManageDoctorViewModel;
import com.coveiot.android.healthbuddies.viewmodels.ManageHealthBuddiesViewModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.compundview.ScrollDisabledViewpager;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.CoveEventBusManager;
import com.google.android.material.tabs.TabLayout;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class HealthBuddiesFragment extends BaseFragment {
    @NotNull
    public static final String ARG_NAME = "tab_postion";
    @NotNull
    public static final Companion Companion = new Companion(null);
    public HealthBuddiesTabAdapter adapter;
    public int m;
    public ManageHealthBuddiesViewModel o;
    public ManageDoctorViewModel p;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final ArrayList<String> n = new ArrayList<>();
    public boolean q = true;

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final HealthBuddiesFragment newInstance(int i) {
            HealthBuddiesFragment healthBuddiesFragment = new HealthBuddiesFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(HealthBuddiesFragment.ARG_NAME, i);
            healthBuddiesFragment.setArguments(bundle);
            return healthBuddiesFragment;
        }
    }

    public static final void q(HealthBuddiesFragment this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            this$0.showProgress(false);
        }
    }

    public static final void r(HealthBuddiesFragment this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            return;
        }
        this$0.dismissProgress();
    }

    public static final void s(HealthBuddiesFragment this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            HealthBuddiesPreferenceManager.Companion companion = HealthBuddiesPreferenceManager.Companion;
            Context requireContext = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            companion.setHealthBuddiesFTUCompleted(requireContext, true);
        } else {
            HealthBuddiesPreferenceManager.Companion companion2 = HealthBuddiesPreferenceManager.Companion;
            Context requireContext2 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            companion2.setHealthBuddiesFTUCompleted(requireContext2, false);
        }
        this$0.x();
        ManageDoctorViewModel manageDoctorViewModel = this$0.p;
        if (manageDoctorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDoctorHealthBuddies");
            manageDoctorViewModel = null;
        }
        manageDoctorViewModel.manageAllBuddies();
    }

    public static final void t(HealthBuddiesFragment this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            HealthBuddiesPreferenceManager.Companion companion = HealthBuddiesPreferenceManager.Companion;
            Context requireContext = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            companion.setFamilyDoctorFTUCompleted(requireContext, true);
        } else {
            HealthBuddiesPreferenceManager.Companion companion2 = HealthBuddiesPreferenceManager.Companion;
            Context requireContext2 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            companion2.setFamilyDoctorFTUCompleted(requireContext2, false);
        }
        this$0.w();
    }

    public static final void u(HealthBuddiesFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this$0.getResources().getString(R.string.guardian_dependent);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.guardian_dependent)");
        String string2 = this$0.getString(R.string.add_friends_family);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.add_friends_family)");
        if (((ScrollDisabledViewpager) this$0._$_findCachedViewById(R.id.viewPager)).getCurrentItem() == 1) {
            string = this$0.getResources().getString(R.string.familydoc_dependent);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.familydoc_dependent)");
            string2 = this$0.getString(R.string.add_family_doctor);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.add_family_doctor)");
        }
        Intent intent = new Intent(this$0.getActivity(), AddHealthBuddiesActivity.class);
        intent.putExtra(Constants.RELATION_TYPE.getValue(), string);
        intent.putExtra(Constants.TOOLBAR_TITLE.getValue(), string2);
        this$0.requireActivity().startActivity(intent);
    }

    public static final void v(HealthBuddiesFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().finish();
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
    public final HealthBuddiesTabAdapter getAdapter() {
        HealthBuddiesTabAdapter healthBuddiesTabAdapter = this.adapter;
        if (healthBuddiesTabAdapter != null) {
            return healthBuddiesTabAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        return null;
    }

    public final int getTabPos() {
        return this.m;
    }

    @NotNull
    public final ArrayList<String> getTitleList() {
        return this.n;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        Integer valueOf = arguments != null ? Integer.valueOf(arguments.getInt(ARG_NAME)) : null;
        Intrinsics.checkNotNull(valueOf);
        this.m = valueOf.intValue();
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        setAdapter(new HealthBuddiesTabAdapter(childFragmentManager));
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_health_buddies, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Subscribe
    public final void onFitnessRequestUpdated(@NotNull GuardianDeletionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (isAdded()) {
            ManageHealthBuddiesViewModel manageHealthBuddiesViewModel = this.o;
            if (manageHealthBuddiesViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mManageHealthBuddies");
                manageHealthBuddiesViewModel = null;
            }
            manageHealthBuddiesViewModel.manageAllBuddies();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        ManageHealthBuddiesViewModel manageHealthBuddiesViewModel = this.o;
        if (manageHealthBuddiesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mManageHealthBuddies");
            manageHealthBuddiesViewModel = null;
        }
        manageHealthBuddiesViewModel.manageAllBuddies();
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
        FragmentActivity requireActivity = requireActivity();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ViewModel viewModel = ViewModelProviders.of(requireActivity, new HealthBuddiesViewModelFactory(requireContext)).get(ManageHealthBuddiesViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(requireActivity(), He…iesViewModel::class.java)");
        this.o = (ManageHealthBuddiesViewModel) viewModel;
        FragmentActivity requireActivity2 = requireActivity();
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        ViewModel viewModel2 = ViewModelProviders.of(requireActivity2, new HealthBuddiesViewModelFactory(requireContext2)).get(ManageDoctorViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "of(requireActivity(), He…torViewModel::class.java)");
        this.p = (ManageDoctorViewModel) viewModel2;
        ManageHealthBuddiesViewModel manageHealthBuddiesViewModel = this.o;
        ManageDoctorViewModel manageDoctorViewModel = null;
        if (manageHealthBuddiesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mManageHealthBuddies");
            manageHealthBuddiesViewModel = null;
        }
        manageHealthBuddiesViewModel.getLoading().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.healthbuddies.fragments.l
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                HealthBuddiesFragment.q(HealthBuddiesFragment.this, ((Boolean) obj).booleanValue());
            }
        });
        ManageDoctorViewModel manageDoctorViewModel2 = this.p;
        if (manageDoctorViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDoctorHealthBuddies");
            manageDoctorViewModel2 = null;
        }
        manageDoctorViewModel2.getLoading().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.healthbuddies.fragments.n
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                HealthBuddiesFragment.r(HealthBuddiesFragment.this, ((Boolean) obj).booleanValue());
            }
        });
        ManageHealthBuddiesViewModel manageHealthBuddiesViewModel2 = this.o;
        if (manageHealthBuddiesViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mManageHealthBuddies");
            manageHealthBuddiesViewModel2 = null;
        }
        manageHealthBuddiesViewModel2.getLoadViewWithData().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.healthbuddies.fragments.m
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                HealthBuddiesFragment.s(HealthBuddiesFragment.this, ((Boolean) obj).booleanValue());
            }
        });
        ManageDoctorViewModel manageDoctorViewModel3 = this.p;
        if (manageDoctorViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDoctorHealthBuddies");
        } else {
            manageDoctorViewModel = manageDoctorViewModel3;
        }
        manageDoctorViewModel.getLoadViewWithData().observe(getViewLifecycleOwner(), new Observer() { // from class: com.coveiot.android.healthbuddies.fragments.o
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                HealthBuddiesFragment.t(HealthBuddiesFragment.this, ((Boolean) obj).booleanValue());
            }
        });
        this.n.add(getString(R.string.friends_family));
        this.n.add(getString(R.string.family_doctor));
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.toolbar);
        ((TextView) constraintLayout.findViewById(R.id.toolbar_title)).setText(getString(SessionManager.getInstance(requireContext()).isShowIndusInd() ? R.string.indus_care_wellness_crew : R.string.wellness_crew));
        ((Button) _$_findCachedViewById(R.id.btnAdd)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.fragments.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HealthBuddiesFragment.u(HealthBuddiesFragment.this, view2);
            }
        });
        constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.fragments.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HealthBuddiesFragment.v(HealthBuddiesFragment.this, view2);
            }
        });
    }

    public final void setAdapter(@NotNull HealthBuddiesTabAdapter healthBuddiesTabAdapter) {
        Intrinsics.checkNotNullParameter(healthBuddiesTabAdapter, "<set-?>");
        this.adapter = healthBuddiesTabAdapter;
    }

    public final void setTabPos(int i) {
        this.m = i;
    }

    public final void w() {
        HealthBuddiesPreferenceManager.Companion companion = HealthBuddiesPreferenceManager.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (companion.isFamilyDoctorFTUCompleted(requireContext)) {
            HealthBuddiesTabAdapter adapter = getAdapter();
            ManageDoctorFragment manageDoctorFragment = new ManageDoctorFragment();
            String string = getString(R.string.family_doctor);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.family_doctor)");
            adapter.addFragment(1, manageDoctorFragment, string);
        } else {
            HealthBuddiesTabAdapter adapter2 = getAdapter();
            HealthBuddiesFTUFragment1 healthBuddiesFTUFragment1 = new HealthBuddiesFTUFragment1();
            String string2 = getString(R.string.family_doctor);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.family_doctor)");
            adapter2.addFragment(1, healthBuddiesFTUFragment1, string2);
        }
        if (this.q) {
            this.q = false;
            int i = R.id.viewPager;
            ((ScrollDisabledViewpager) _$_findCachedViewById(i)).setAdapter(getAdapter());
            ((ScrollDisabledViewpager) _$_findCachedViewById(i)).setOffscreenPageLimit(2);
            ScrollDisabledViewpager scrollDisabledViewpager = (ScrollDisabledViewpager) _$_findCachedViewById(i);
            int i2 = R.id.tab_layout;
            scrollDisabledViewpager.setCurrentItem(((TabLayout) _$_findCachedViewById(i2)).getSelectedTabPosition() == -1 ? this.m : ((TabLayout) _$_findCachedViewById(i2)).getSelectedTabPosition());
            ((TabLayout) _$_findCachedViewById(i2)).setupWithViewPager((ScrollDisabledViewpager) _$_findCachedViewById(i));
            y(this.n);
            return;
        }
        getAdapter().notifyDataSetChanged();
        y(this.n);
    }

    public final void x() {
        HealthBuddiesPreferenceManager.Companion companion = HealthBuddiesPreferenceManager.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (companion.isHealthBuddiesFTUCompleted(requireContext)) {
            HealthBuddiesTabAdapter adapter = getAdapter();
            ManageHealthBuddiesFragment manageHealthBuddiesFragment = new ManageHealthBuddiesFragment();
            String string = getString(R.string.friends_family);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.friends_family)");
            adapter.addFragment(0, manageHealthBuddiesFragment, string);
            return;
        }
        HealthBuddiesTabAdapter adapter2 = getAdapter();
        HealthBuddiesFTUFragment healthBuddiesFTUFragment = new HealthBuddiesFTUFragment();
        String string2 = getString(R.string.friends_family);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.friends_family)");
        adapter2.addFragment(0, healthBuddiesFTUFragment, string2);
    }

    public final void y(ArrayList<String> arrayList) {
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
        int i4 = R.id.tab_layout;
        TabLayout.Tab tabAt = ((TabLayout) _$_findCachedViewById(i4)).getTabAt(0);
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
            int i5 = R.id.btnAdd;
            ((Button) _$_findCachedViewById(i5)).setText(getString(R.string.add_friends_family));
            HealthBuddiesPreferenceManager.Companion companion = HealthBuddiesPreferenceManager.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            if (companion.isHealthBuddiesFTUCompleted(requireContext)) {
                ((Button) _$_findCachedViewById(i5)).setVisibility(0);
            } else {
                ((Button) _$_findCachedViewById(i5)).setVisibility(8);
            }
        } else {
            imageView.setVisibility(8);
            imageView2.setVisibility(0);
            textView.setTextAppearance(R.style.unSelectedTabFont);
            textView2.setTextAppearance(R.style.selectedTabFont);
            int i6 = R.id.btnAdd;
            ((Button) _$_findCachedViewById(i6)).setText(getString(R.string.add_family_doctor));
            HealthBuddiesPreferenceManager.Companion companion2 = HealthBuddiesPreferenceManager.Companion;
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (companion2.isFamilyDoctorFTUCompleted(requireContext2)) {
                ((Button) _$_findCachedViewById(i6)).setVisibility(0);
            } else {
                ((Button) _$_findCachedViewById(i6)).setVisibility(8);
            }
        }
        TabLayout.Tab tabAt2 = ((TabLayout) _$_findCachedViewById(i4)).getTabAt(1);
        if (tabAt2 != null) {
            tabAt2.setCustomView(inflate2);
        }
        ((TabLayout) _$_findCachedViewById(i4)).addOnTabSelectedListener((TabLayout.BaseOnTabSelectedListener) new TabLayout.OnTabSelectedListener() { // from class: com.coveiot.android.healthbuddies.fragments.HealthBuddiesFragment$setUpTabLayout$1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                View customView = tab.getCustomView();
                if (customView != null) {
                    View findViewById5 = customView.findViewById(R.id.tab_badge);
                    Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.ImageView");
                    View findViewById6 = customView.findViewById(R.id.tab_title);
                    Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type android.widget.TextView");
                    ((TextView) findViewById6).setTextAppearance(R.style.selectedTabFont);
                    ((ImageView) findViewById5).setVisibility(0);
                    if (tab.getPosition() == 0) {
                        HealthBuddiesFragment healthBuddiesFragment = HealthBuddiesFragment.this;
                        int i7 = R.id.btnAdd;
                        ((Button) healthBuddiesFragment._$_findCachedViewById(i7)).setText(HealthBuddiesFragment.this.getString(R.string.add_friends_family));
                        HealthBuddiesPreferenceManager.Companion companion3 = HealthBuddiesPreferenceManager.Companion;
                        Context requireContext3 = HealthBuddiesFragment.this.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                        if (companion3.isHealthBuddiesFTUCompleted(requireContext3)) {
                            ((Button) HealthBuddiesFragment.this._$_findCachedViewById(i7)).setVisibility(0);
                        } else {
                            ((Button) HealthBuddiesFragment.this._$_findCachedViewById(i7)).setVisibility(8);
                        }
                    } else {
                        HealthBuddiesFragment healthBuddiesFragment2 = HealthBuddiesFragment.this;
                        int i8 = R.id.btnAdd;
                        ((Button) healthBuddiesFragment2._$_findCachedViewById(i8)).setText(HealthBuddiesFragment.this.getString(R.string.add_family_doctor));
                        HealthBuddiesPreferenceManager.Companion companion4 = HealthBuddiesPreferenceManager.Companion;
                        Context requireContext4 = HealthBuddiesFragment.this.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                        if (companion4.isFamilyDoctorFTUCompleted(requireContext4)) {
                            ((Button) HealthBuddiesFragment.this._$_findCachedViewById(i8)).setVisibility(0);
                        } else {
                            ((Button) HealthBuddiesFragment.this._$_findCachedViewById(i8)).setVisibility(8);
                        }
                    }
                }
                HealthBuddiesFragment.this.setTabPos(tab.getPosition());
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                View customView = tab.getCustomView();
                if (customView != null) {
                    View findViewById5 = customView.findViewById(R.id.tab_badge);
                    Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.ImageView");
                    ((ImageView) findViewById5).setVisibility(8);
                    View findViewById6 = customView.findViewById(R.id.tab_title);
                    Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type android.widget.TextView");
                    ((TextView) findViewById6).setTextAppearance(R.style.unSelectedTabFont);
                }
            }
        });
    }
}
