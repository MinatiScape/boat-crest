package com.coveiot.android.dashboard2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.dashboard2.databinding.FragmentFitnessGuestBinding;
import com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.utils.ThemesUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class FragmentFitnessGuest extends BaseFragment {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public FragmentFitnessGuestBinding m;

    public static final void o(FragmentFitnessGuest this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = this$0.n().tvMyBuddies;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvMyBuddies");
        TextView textView2 = this$0.n().tvMessages;
        Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvMessages");
        this$0.r(textView, textView2);
    }

    public static final void p(FragmentFitnessGuest this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = this$0.n().tvMessages;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvMessages");
        TextView textView2 = this$0.n().tvMyBuddies;
        Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvMyBuddies");
        this$0.r(textView, textView2);
    }

    public static final void q(FragmentFitnessGuest this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (themesUtils.isGuestUser(requireContext)) {
            if (this$0.getActivity() instanceof FragmentHomeCallBackInterface) {
                FragmentActivity activity = this$0.getActivity();
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
                ((FragmentHomeCallBackInterface) activity).navigateToLogin();
            }
        } else if (this$0.getActivity() instanceof FragmentHomeCallBackInterface) {
            FragmentActivity activity2 = this$0.getActivity();
            Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.dashboard2.listener.FragmentHomeCallBackInterface");
            ((FragmentHomeCallBackInterface) activity2).navigateToPairDevice();
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

    public final FragmentFitnessGuestBinding n() {
        FragmentFitnessGuestBinding fragmentFitnessGuestBinding = this.m;
        Intrinsics.checkNotNull(fragmentFitnessGuestBinding);
        return fragmentFitnessGuestBinding;
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentFitnessGuestBinding.inflate(inflater, viewGroup, false);
        View root = n().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
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
        FragmentFitnessGuestBinding n = n();
        n().tvMyBuddies.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFitnessGuest.o(FragmentFitnessGuest.this, view2);
            }
        });
        n().tvMessages.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFitnessGuest.p(FragmentFitnessGuest.this, view2);
            }
        });
        n.clGetStarted.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.fragment.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFitnessGuest.q(FragmentFitnessGuest.this, view2);
            }
        });
    }

    public final void r(TextView textView, TextView textView2) {
        textView.setBackgroundResource(R.drawable.rounded_dialog_button_background);
        textView.setTextColor(requireContext().getColor(R.color.white));
        textView.setTypeface(textView.getTypeface(), 1);
        textView2.setBackgroundResource(R.color.transparent);
        textView2.setTextColor(requireContext().getColor(R.color.color_cccccc));
        textView2.setTypeface(textView2.getTypeface(), 0);
    }
}
