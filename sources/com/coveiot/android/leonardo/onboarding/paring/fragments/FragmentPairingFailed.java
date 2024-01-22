package com.coveiot.android.leonardo.onboarding.paring.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import com.airbnb.lottie.LottieAnimationView;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentPairingFailedBinding;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.covepreferences.SessionManager;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentPairingFailed extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public FragmentPairingFailedBinding m;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentPairingFailed newInstance(@NotNull String param1, @NotNull String param2) {
            Intrinsics.checkNotNullParameter(param1, "param1");
            Intrinsics.checkNotNullParameter(param2, "param2");
            FragmentPairingFailed fragmentPairingFailed = new FragmentPairingFailed();
            Bundle bundle = new Bundle();
            bundle.putString("param1", param1);
            bundle.putString("param2", param2);
            fragmentPairingFailed.setArguments(bundle);
            return fragmentPairingFailed;
        }
    }

    public static final void n(FragmentPairingFailed this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.CV_PAIR_CONN_TRY_AGAIN.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.PAIRING_FAILED_SCREEN.getValue());
        analyticsLog.setPreviousScreenName(FirebaseEventParams.ScreenName.DEVICE_SELECTION_SCREEN.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        hashMap.putAll(companion.getDeviceId(requireContext));
        Context requireContext2 = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        hashMap.putAll(companion.getWatchDetails(requireContext2));
        companion.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_DEVICE_NOT_PAIRED_TRY_AGAIN.getValue(), hashMap);
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
        ((ActivityPairing) activity).onTryAgainClicked();
    }

    @JvmStatic
    @NotNull
    public static final FragmentPairingFailed newInstance(@NotNull String str, @NotNull String str2) {
        return Companion.newInstance(str, str2);
    }

    public static final void o(FragmentPairingFailed this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (SessionManager.getInstance(this$0.requireContext()).getTroubleshootUrl() != null) {
            AppNavigator.Companion companion = AppNavigator.Companion;
            FragmentActivity requireActivity = this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            String string = this$0.getString(R.string.troubleshoot_guide);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.troubleshoot_guide)");
            String troubleshootUrl = SessionManager.getInstance(this$0.requireContext()).getTroubleshootUrl();
            Intrinsics.checkNotNullExpressionValue(troubleshootUrl, "getInstance(requireContext()).troubleshootUrl");
            companion.navigateToWebViewer(requireActivity, string, troubleshootUrl);
        } else {
            AppNavigator.Companion companion2 = AppNavigator.Companion;
            FragmentActivity requireActivity2 = this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            String string2 = this$0.getString(R.string.troubleshoot_guide);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.troubleshoot_guide)");
            companion2.navigateToWebViewer(requireActivity2, string2, AppConstants.TROUBLESHOOT_URL.getValue());
        }
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.CV_PAIR_CONN_TROUBLESHOOT.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.PAIRING_FAILED_SCREEN.getValue());
        analyticsLog.setPreviousScreenName(FirebaseEventParams.ScreenName.DEVICE_SELECTION_SCREEN.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceUtils.Companion companion3 = DeviceUtils.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        hashMap.putAll(companion3.getDeviceId(requireContext));
        Context requireContext2 = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        hashMap.putAll(companion3.getWatchDetails(requireContext2));
        companion3.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_DEVICE_PAIRING_SUPPORT.getValue(), hashMap);
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

    public final FragmentPairingFailedBinding m() {
        FragmentPairingFailedBinding fragmentPairingFailedBinding = this.m;
        Intrinsics.checkNotNull(fragmentPairingFailedBinding);
        return fragmentPairingFailedBinding;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.getString("param1");
            arguments.getString("param2");
        }
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentPairingFailedBinding.inflate(inflater, viewGroup, false);
        View root = m().getRoot();
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
        BleApiManager.getInstance(getActivity()).getBleApi().stopService();
        final FragmentPairingFailedBinding m = m();
        m.tvTryAgain.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.a0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentPairingFailed.n(FragmentPairingFailed.this, view2);
            }
        });
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (companion.isRoundWatch(requireContext)) {
            m.animPairingFailed.setAnimation(R.raw.round_blue_scan_fail);
        } else {
            m.animPairingFailed.setAnimation(R.raw.rect_blue_scan_fail);
        }
        m.animPairingFailed.playAnimation();
        m.animPairingFailed.addAnimatorListener(new AnimatorListenerAdapter() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentPairingFailed$onViewCreated$1$2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(@NotNull Animator animation, boolean z) {
                FragmentPairingFailedBinding m2;
                Intrinsics.checkNotNullParameter(animation, "animation");
                super.onAnimationEnd(animation, z);
                FragmentPairingFailed fragmentPairingFailed = FragmentPairingFailed.this;
                LottieAnimationView animPairingFailed = m.animPairingFailed;
                Intrinsics.checkNotNullExpressionValue(animPairingFailed, "animPairingFailed");
                fragmentPairingFailed.gone(animPairingFailed);
                FragmentPairingFailed fragmentPairingFailed2 = FragmentPairingFailed.this;
                m2 = fragmentPairingFailed2.m();
                ConstraintLayout constraintLayout = m2.clPairingFailed;
                Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clPairingFailed");
                fragmentPairingFailed2.visible(constraintLayout);
            }
        });
        m.tvUnableInfo.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.z
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentPairingFailed.o(FragmentPairingFailed.this, view2);
            }
        });
        ImageView imageView = m.ivWatch;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        imageView.setImageResource(companion.getDevicePairingResultImage(requireContext2));
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.PAIRING_FAILED_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
    }
}
