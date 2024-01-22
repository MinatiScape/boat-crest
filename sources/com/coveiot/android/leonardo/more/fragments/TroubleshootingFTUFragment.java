package com.coveiot.android.leonardo.more.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentTroubleshoootingFTUBinding;
import com.coveiot.android.leonardo.more.activities.TroubleshootingActivityNew;
import com.coveiot.android.leonardo.more.adapters.TroubleshootPagerAdapterFirstTimeUser;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.covepreferences.SessionManager;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class TroubleshootingFTUFragment extends BaseFragment {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public FragmentTroubleshoootingFTUBinding m;
    public TextView n;
    @NotNull
    public final int[] o;
    @NotNull
    public final int[] p;
    @NotNull
    public final int[] q;
    public final int r;
    public final int s;
    @NotNull
    public final Handler t;

    public TroubleshootingFTUFragment() {
        int[] iArr = {R.string.troubleshoot_sms_and_app, R.string.troubleshoot_call_notifiaction, R.string.troubleshoot_bt_calling};
        this.o = iArr;
        this.p = new int[]{R.string.troubleshoot_desc_1, R.string.troubleshoot_desc_2, R.string.troubleshoot_desc_3};
        this.q = new int[]{R.drawable.trouble_shooting_ftu1, R.drawable.trouble_shooting_ftu2, R.drawable.trouble_shooting_ftu3};
        this.s = iArr.length;
        this.t = new Handler();
    }

    public static final void n(TroubleshootingFTUFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.requireActivity() instanceof TroubleshootingActivityNew) {
            FragmentActivity requireActivity = this$0.requireActivity();
            Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.leonardo.more.activities.TroubleshootingActivityNew");
            ((TroubleshootingActivityNew) requireActivity).loadTroubleshootingFragment();
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

    public final int getFtuItemCount() {
        return this.s;
    }

    @NotNull
    public final Handler getHandler() {
        return this.t;
    }

    public final int getSelectedItemPosition() {
        return this.r;
    }

    public final FragmentTroubleshoootingFTUBinding l() {
        FragmentTroubleshoootingFTUBinding fragmentTroubleshoootingFTUBinding = this.m;
        if (fragmentTroubleshoootingFTUBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return fragmentTroubleshoootingFTUBinding;
    }

    public final void m() {
        FragmentTroubleshoootingFTUBinding l = l();
        if (l != null) {
            ((TextView) l.toolbar.findViewById(R.id.toolbar_title)).setVisibility(4);
            ((TextView) l.toolbar.findViewById(R.id.toolbar_back_arrow)).setVisibility(4);
            View findViewById = l.toolbar.findViewById(R.id.save);
            Intrinsics.checkNotNullExpressionValue(findViewById, "it.toolbar.findViewById(R.id.save)");
            TextView textView = (TextView) findViewById;
            this.n = textView;
            TextView textView2 = null;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toolBarSkip");
                textView = null;
            }
            textView.setVisibility(0);
            TextView textView3 = this.n;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toolBarSkip");
                textView3 = null;
            }
            textView3.setText(getString(R.string.skip));
            TextView textView4 = this.n;
            if (textView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toolBarSkip");
            } else {
                textView2 = textView4;
            }
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.a3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TroubleshootingFTUFragment.n(TroubleshootingFTUFragment.this, view);
                }
            });
        }
    }

    public final void o() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        l().viewPager.setAdapter(new TroubleshootPagerAdapterFirstTimeUser(requireContext, this.o, this.p, this.q));
        l().viewPager.setOffscreenPageLimit(this.o.length - 1);
        l().viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.coveiot.android.leonardo.more.fragments.TroubleshootingFTUFragment$setupViewPager$1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                FragmentTroubleshoootingFTUBinding l;
                int[] iArr;
                TextView textView;
                TextView textView2;
                l = TroubleshootingFTUFragment.this.l();
                l.setSelectedItemPosition(Integer.valueOf(i));
                iArr = TroubleshootingFTUFragment.this.o;
                TextView textView3 = null;
                if (i == iArr.length - 1) {
                    textView2 = TroubleshootingFTUFragment.this.n;
                    if (textView2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("toolBarSkip");
                    } else {
                        textView3 = textView2;
                    }
                    textView3.setText(TroubleshootingFTUFragment.this.getString(R.string.done));
                    return;
                }
                textView = TroubleshootingFTUFragment.this.n;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("toolBarSkip");
                } else {
                    textView3 = textView;
                }
                textView3.setText(TroubleshootingFTUFragment.this.getString(R.string.skip));
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentTroubleshoootingFTUBinding inflate = FragmentTroubleshoootingFTUBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.m = inflate;
        return l().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        l().viewPager.setAdapter(null);
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        l().setFTUItemCount(Integer.valueOf(this.o.length));
        SessionManager.getInstance(requireContext()).setIsTroubleshootFtuShown(true);
        o();
        m();
    }
}
