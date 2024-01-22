package com.coveiot.android.leonardo.sensai.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentOverallStatsBinding;
import com.coveiot.android.theme.BaseFragment;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentOverallStats extends BaseFragment {
    @Nullable
    public FragmentOverallStatsBinding m;
    public int o;
    public final int p;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public boolean n = true;
    public final int q = 1;
    public final int r = 2;

    public static final void q(FragmentOverallStats this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.n = true;
        this$0.p().tvBatting.setTextColor(this$0.requireContext().getColor(R.color.main_text_color));
        this$0.p().tvBowling.setTextColor(this$0.requireContext().getColor(R.color.color_b3b3b3));
        this$0.p().tvBatting.setCompoundDrawablesWithIntrinsicBounds(R.drawable.sens_ai_selected_batting, 0, 0, 0);
        this$0.p().tvBowling.setCompoundDrawablesWithIntrinsicBounds(R.drawable.sens_ai_unselected_bowling, 0, 0, 0);
        this$0.w();
    }

    public static final void r(FragmentOverallStats this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.n = false;
        this$0.p().tvBatting.setTextColor(this$0.requireContext().getColor(R.color.color_b3b3b3));
        this$0.p().tvBowling.setTextColor(this$0.requireContext().getColor(R.color.main_text_color));
        this$0.p().tvBatting.setCompoundDrawablesWithIntrinsicBounds(R.drawable.sens_ai_unselected_batting, 0, 0, 0);
        this$0.p().tvBowling.setCompoundDrawablesWithIntrinsicBounds(R.drawable.sens_ai_selected_bowling, 0, 0, 0);
        this$0.w();
    }

    public static final void s(FragmentOverallStats this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.o = this$0.p;
        this$0.v();
        this$0.w();
    }

    public static final void t(FragmentOverallStats this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.o = this$0.q;
        this$0.v();
        this$0.w();
    }

    public static final void u(FragmentOverallStats this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.o = this$0.r;
        this$0.v();
        this$0.w();
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

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentOverallStatsBinding.inflate(inflater, viewGroup, false);
        return p().getRoot();
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
        w();
        p().tvBatting.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentOverallStats.q(FragmentOverallStats.this, view2);
            }
        });
        p().tvBowling.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentOverallStats.r(FragmentOverallStats.this, view2);
            }
        });
        p().tvWeek.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentOverallStats.s(FragmentOverallStats.this, view2);
            }
        });
        p().tvMonth.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentOverallStats.t(FragmentOverallStats.this, view2);
            }
        });
        p().tvYear.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentOverallStats.u(FragmentOverallStats.this, view2);
            }
        });
    }

    public final FragmentOverallStatsBinding p() {
        FragmentOverallStatsBinding fragmentOverallStatsBinding = this.m;
        Intrinsics.checkNotNull(fragmentOverallStatsBinding);
        return fragmentOverallStatsBinding;
    }

    public final void v() {
        if (isAdded()) {
            int i = this.o;
            if (i == this.p) {
                p().tvWeek.setBackground(requireContext().getDrawable(R.drawable.red_gradient_bottom_border_background));
                p().tvWeek.setTextColor(requireContext().getColor(R.color.main_text_color));
                p().tvMonth.setBackground(null);
                p().tvMonth.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                p().tvYear.setBackground(null);
                p().tvYear.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                p().tvWeek.setTextAppearance(R.style.bold);
                p().tvMonth.setTextAppearance(R.style.regular);
                p().tvYear.setTextAppearance(R.style.regular);
            } else if (i == this.q) {
                p().tvWeek.setBackground(null);
                p().tvWeek.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                p().tvMonth.setBackground(requireContext().getDrawable(R.drawable.red_gradient_bottom_border_background));
                p().tvMonth.setTextColor(requireContext().getColor(R.color.main_text_color));
                p().tvYear.setBackground(null);
                p().tvYear.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                p().tvWeek.setTextAppearance(R.style.regular);
                p().tvMonth.setTextAppearance(R.style.bold);
                p().tvYear.setTextAppearance(R.style.regular);
            } else {
                p().tvWeek.setBackground(null);
                p().tvWeek.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                p().tvMonth.setBackground(null);
                p().tvMonth.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                p().tvYear.setBackground(requireContext().getDrawable(R.drawable.red_gradient_bottom_border_background));
                p().tvYear.setTextColor(requireContext().getColor(R.color.main_text_color));
                p().tvWeek.setTextAppearance(R.style.regular);
                p().tvMonth.setTextAppearance(R.style.regular);
                p().tvYear.setTextAppearance(R.style.bold);
            }
        }
    }

    public final void w() {
        if (this.n) {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, FragmentOverallStatsBatting.Companion.newInstance(this.o)).commitAllowingStateLoss();
        } else {
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, FragmentOverallStatsBowling.Companion.newInstance(this.o)).commitAllowingStateLoss();
        }
    }
}
