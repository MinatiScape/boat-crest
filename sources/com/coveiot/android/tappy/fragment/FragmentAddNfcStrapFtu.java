package com.coveiot.android.tappy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.activity.AddNfcStrapFtuActivity;
import com.coveiot.android.tappy.adapter.PagerAdapterAddNfcStrapFtu;
import com.coveiot.android.tappy.databinding.FragmentAddNfcStrapFtuBinding;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.covepreferences.SessionManager;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class FragmentAddNfcStrapFtu extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public FragmentAddNfcStrapFtuBinding m;
    public TextView n;
    @NotNull
    public final int[] o;
    public int p;
    public final int q;

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentAddNfcStrapFtu newInstance() {
            return new FragmentAddNfcStrapFtu();
        }
    }

    public FragmentAddNfcStrapFtu() {
        int[] iArr = {R.drawable.tap_pay_ftu1, R.drawable.tap_pay_ftu2, R.drawable.tap_pay_ftu3, R.drawable.tap_pay_ftu4};
        this.o = iArr;
        this.q = iArr.length;
    }

    public static final void n(FragmentAddNfcStrapFtu this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().onBackPressed();
    }

    @JvmStatic
    @NotNull
    public static final FragmentAddNfcStrapFtu newInstance() {
        return Companion.newInstance();
    }

    public static final void o(FragmentAddNfcStrapFtu this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.tappy.activity.AddNfcStrapFtuActivity");
        ((AddNfcStrapFtuActivity) activity).loadSupportedBankFragment();
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_PAY_GETSTARTED_TAPPED.getValue(), null);
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
        return this.q;
    }

    public final int getSelectedItemPosition() {
        return this.p;
    }

    public final void initToolbar() {
        View findViewById = m().toolbar.findViewById(R.id.toolbar_back_arrow);
        Intrinsics.checkNotNullExpressionValue(findViewById, "binding.toolbar.findView…(R.id.toolbar_back_arrow)");
        this.n = (TextView) findViewById;
        ((TextView) m().toolbar.findViewById(R.id.toolbar_title)).setText(getString(R.string.boat_pay));
        TextView textView = this.n;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolBarBack");
            textView = null;
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentAddNfcStrapFtu.n(FragmentAddNfcStrapFtu.this, view);
            }
        });
    }

    public final FragmentAddNfcStrapFtuBinding m() {
        FragmentAddNfcStrapFtuBinding fragmentAddNfcStrapFtuBinding = this.m;
        if (fragmentAddNfcStrapFtuBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return fragmentAddNfcStrapFtuBinding;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentAddNfcStrapFtuBinding inflate = FragmentAddNfcStrapFtuBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.m = inflate;
        return m().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        m().setFTUItemCount(Integer.valueOf(this.q));
        initToolbar();
        q();
        SessionManager.getInstance(requireContext()).setIsTapAndPayFtuShown(true);
        m().btnGetStarted.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentAddNfcStrapFtu.o(FragmentAddNfcStrapFtu.this, view2);
            }
        });
    }

    public final void p() {
        m().tvDesc.setText(getString(R.string.tappy_ftu_desc));
        int i = this.p;
        if (i == 0) {
            m().tvTitle.setText(getString(R.string.select_bank));
            m().tvDesc.setText(getString(R.string.to_initiate_boat_pay_select_the_bank_you_nee_to_activate));
        } else if (i == 1) {
            m().tvTitle.setText(getString(R.string.add_card));
            m().tvDesc.setText(getString(R.string.input_your_card_details));
        } else if (i != 2) {
            m().tvTitle.setText(getString(R.string.activate_card));
            m().tvDesc.setText(getString(R.string.track_every_details_also_view_compare_your_session_after));
        } else {
            m().tvTitle.setText(getString(R.string.add_nfc_strap_small));
            m().tvDesc.setText(getString(R.string.provide_the_model_details_of_your_boat_smartwatch));
        }
    }

    public final void q() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        m().viewPager.setAdapter(new PagerAdapterAddNfcStrapFtu(requireContext, this.o));
        m().viewPager.setOffscreenPageLimit(this.o.length - 1);
        m().viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.coveiot.android.tappy.fragment.FragmentAddNfcStrapFtu$setupViewPager$1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
                FragmentAddNfcStrapFtuBinding m;
                m = FragmentAddNfcStrapFtu.this.m();
                m.setSelectedItemPosition(Integer.valueOf(i));
                FragmentAddNfcStrapFtu.this.setSelectedItemPosition(i);
                FragmentAddNfcStrapFtu.this.p();
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
            }
        });
    }

    public final void setSelectedItemPosition(int i) {
        this.p = i;
    }
}
