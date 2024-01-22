package com.coveiot.android.leonardo.onboarding.phonevalidation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.covepreferences.SessionManager;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentVerificationSuccess extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public String m;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentVerificationSuccess newInstance() {
            return new FragmentVerificationSuccess();
        }
    }

    public static final void n(FragmentVerificationSuccess this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().onBackPressed();
    }

    public static final void o(FragmentVerificationSuccess this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        hashMap.putAll(companion.getDeviceId(requireContext));
        companion.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_PAIR_WATCH_NOW.getValue(), hashMap);
        AppNavigator.Companion companion2 = AppNavigator.Companion;
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        companion2.navigateToBluetoothScanActivity(requireActivity);
    }

    public static final void p(FragmentVerificationSuccess this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SessionManager.getInstance(this$0.requireContext()).setIsPairDeviceLater(true);
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        hashMap.putAll(companion.getDeviceId(requireContext));
        companion.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_SKIP_WATCH_PAIR.getValue(), hashMap);
        AppNavigator.Companion companion2 = AppNavigator.Companion;
        Context requireContext2 = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        companion2.navigateToDashBoard(requireContext2, true);
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

    @Nullable
    public final String getCountryCodePhoneNo() {
        return this.m;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_verification_success, viewGroup, false);
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
        SessionManager sessionManager = SessionManager.getInstance(requireContext());
        if (sessionManager.isNewUser()) {
            sessionManager.setIsNewUser(false);
            HashMap<String, Object> hashMap = new HashMap<>();
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            hashMap.putAll(companion.getDeviceId(requireContext));
            companion.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_USER_REGISTRATION.getValue(), hashMap);
        } else {
            HashMap<String, Object> hashMap2 = new HashMap<>();
            DeviceUtils.Companion companion2 = DeviceUtils.Companion;
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            hashMap2.putAll(companion2.getDeviceId(requireContext2));
            companion2.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_USER_LOGIN.getValue(), hashMap2);
        }
        HashMap hashMap3 = new HashMap();
        hashMap3.put("Identity", SessionManager.getInstance(requireContext()).getAxTrackerId());
        CoveAnalyticsManager.Companion.getInstance().setOnUserLogin(hashMap3);
        ((TextView) _$_findCachedViewById(R.id.phone_valid_isdcode)).setText(AppConstants.COUNTRY_CODE.getValue());
        ((TextView) _$_findCachedViewById(R.id.phone_number_with_isd_code)).setText(AppConstants.PHONE_NO.getValue());
        ((TextView) ((ConstraintLayout) view.findViewById(R.id.toolbar)).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.u
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentVerificationSuccess.n(FragmentVerificationSuccess.this, view2);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_submit)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.v
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentVerificationSuccess.o(FragmentVerificationSuccess.this, view2);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvPairDeviceLater)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.fragments.t
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentVerificationSuccess.p(FragmentVerificationSuccess.this, view2);
            }
        });
    }

    public final void setCountryCodePhoneNo(@Nullable String str) {
        this.m = str;
    }
}
