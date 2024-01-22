package com.coveiot.android.leonardo.onboarding.paring.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import com.airbnb.lottie.LottieAnimationView;
import com.clevertap.android.sdk.PushPermissionManager;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentDevicePairedBinding;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.GenericMessageDialog;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentDevicePaired extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int m = 101;
    @Nullable
    public FragmentDevicePairedBinding n;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentDevicePaired newInstance(@NotNull String param1, @NotNull String param2) {
            Intrinsics.checkNotNullParameter(param1, "param1");
            Intrinsics.checkNotNullParameter(param2, "param2");
            FragmentDevicePaired fragmentDevicePaired = new FragmentDevicePaired();
            Bundle bundle = new Bundle();
            bundle.putString("param1", param1);
            bundle.putString("param2", param2);
            fragmentDevicePaired.setArguments(bundle);
            return fragmentDevicePaired;
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentDevicePaired newInstance(@NotNull String str, @NotNull String str2) {
        return Companion.newInstance(str, str2);
    }

    public static final void q(FragmentDevicePaired this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Build.VERSION.SDK_INT >= 33) {
            this$0.o();
        } else {
            this$0.p();
        }
    }

    public static final void s(GenericMessageDialog dialog, FragmentDevicePaired this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.p();
    }

    public static final void t(GenericMessageDialog dialog, FragmentDevicePaired this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this$0.requireActivity().getPackageName(), null));
        this$0.startActivity(intent);
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

    public final FragmentDevicePairedBinding n() {
        FragmentDevicePairedBinding fragmentDevicePairedBinding = this.n;
        Intrinsics.checkNotNull(fragmentDevicePairedBinding);
        return fragmentDevicePairedBinding;
    }

    public final void o() {
        if (AppUtils.isBluetoothEnabled(requireContext())) {
            if (AppUtils.isGpsEnabled(requireContext())) {
                if (Build.VERSION.SDK_INT >= 33) {
                    PermissionUtils.checkPermission(requireContext(), PushPermissionManager.ANDROID_PERMISSION_STRING, new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentDevicePaired$getNotificationPermission$1
                        @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
                        public void onPermissionAsk() {
                            ActivityCompat.requestPermissions(FragmentDevicePaired.this.requireActivity(), new String[]{PushPermissionManager.ANDROID_PERMISSION_STRING}, 101);
                        }

                        @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
                        public void onPermissionDisabled() {
                            FragmentDevicePaired.this.showNotificationPermissionDialog();
                        }

                        @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
                        public void onPermissionGranted() {
                            FragmentDevicePaired.this.p();
                        }

                        @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
                        public void onPermissionPreviouslyDenied() {
                            FragmentDevicePaired.this.showNotificationPermissionDialog();
                        }
                    });
                    return;
                }
                return;
            }
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String string = getString(R.string.please_check_gps);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_check_gps)");
            toast(requireContext, string);
            return;
        }
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        String string2 = getString(R.string.bluetooth_off_message);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.bluetooth_off_message)");
        toast(requireContext2, string2);
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
        this.n = FragmentDevicePairedBinding.inflate(inflater, viewGroup, false);
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
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == this.m && permissions.length > 0 && Intrinsics.areEqual(permissions[0], PushPermissionManager.ANDROID_PERMISSION_STRING) && grantResults.length > 0 && grantResults[0] == 0) {
            p();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        hashMap.putAll(companion.getDeviceId(requireContext));
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        hashMap.putAll(companion.getWatchDetails(requireContext2));
        companion.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_WATCH_PAIRED_SUCCESS.getValue(), hashMap);
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.PAIRING_SUCCESS_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
        FragmentDevicePairedBinding n = n();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String deviceName = SessionManager.getInstance(requireContext()).getBleDeviceName();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("Your %s is successfully paired", Arrays.copyOf(new Object[]{deviceName}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        SpannableString spannableString = new SpannableString(format);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#f14950"));
        Intrinsics.checkNotNullExpressionValue(deviceName, "deviceName");
        spannableString.setSpan(foregroundColorSpan, StringsKt__StringsKt.indexOf$default((CharSequence) format, deviceName, 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) format, deviceName, 0, false, 6, (Object) null) + deviceName.length(), 33);
        spannableString.setSpan(new TextAppearanceSpan(requireContext(), R.style.bold), StringsKt__StringsKt.indexOf$default((CharSequence) format, deviceName, 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) format, deviceName, 0, false, 6, (Object) null) + deviceName.length(), 18);
        spannableStringBuilder.append((CharSequence) spannableString);
        n.tvDeviceConnected.setText(spannableStringBuilder);
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        if (companion.isRoundWatch(requireContext3)) {
            u(R.raw.round_blue_connect_success);
        } else {
            u(R.raw.rect_blue_connect_success);
        }
        n.btnGetStarted.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentDevicePaired.q(FragmentDevicePaired.this, view2);
            }
        });
    }

    public final void p() {
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.PAIRING_SUCCESS_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_GOAL_SETTINGS_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.NEXT_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing");
        ((ActivityPairing) activity).onBluetoothPairedNextClicked();
    }

    public final void r() {
        n().clGrid.setBackgroundResource(R.color.black);
        LottieAnimationView lottieAnimationView = n().ivConnectionLoader;
        Intrinsics.checkNotNullExpressionValue(lottieAnimationView, "binding.ivConnectionLoader");
        gone(lottieAnimationView);
        ImageView imageView = n().ivDeviceImage;
        Intrinsics.checkNotNullExpressionValue(imageView, "binding.ivDeviceImage");
        visible(imageView);
        if (isAdded()) {
            ImageView imageView2 = n().ivDeviceImage;
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            imageView2.setImageResource(companion.getDevicePairingResultImage(requireContext));
        }
        Button button = n().btnGetStarted;
        Intrinsics.checkNotNullExpressionValue(button, "binding.btnGetStarted");
        visible(button);
    }

    public final void showNotificationPermissionDialog() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = requireContext().getString(R.string.notification_permission_dialog_title);
        Intrinsics.checkNotNullExpressionValue(string, "requireContext().getStri…_permission_dialog_title)");
        String string2 = requireContext().getString(R.string.notification_permission_dialog_message);
        Intrinsics.checkNotNullExpressionValue(string2, "requireContext().getStri…ermission_dialog_message)");
        final GenericMessageDialog genericMessageDialog = new GenericMessageDialog(requireContext, string, string2);
        View findViewById = genericMessageDialog.findViewById(R.id.cancel);
        Intrinsics.checkNotNullExpressionValue(findViewById, "dialog.findViewById(com.…tnessbuddies.R.id.cancel)");
        View findViewById2 = genericMessageDialog.findViewById(R.id.proceed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "dialog.findViewById(com.…nessbuddies.R.id.proceed)");
        ((TextView) findViewById).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentDevicePaired.s(GenericMessageDialog.this, this, view);
            }
        });
        ((TextView) findViewById2).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.x
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentDevicePaired.t(GenericMessageDialog.this, this, view);
            }
        });
        genericMessageDialog.show();
    }

    public final void u(int i) {
        n().ivConnectionLoader.setAnimation(i);
        n().ivConnectionLoader.playAnimation();
        n().ivConnectionLoader.addAnimatorListener(new AnimatorListenerAdapter() { // from class: com.coveiot.android.leonardo.onboarding.paring.fragments.FragmentDevicePaired$startLottieAnimation$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(@NotNull Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                super.onAnimationEnd(animation);
                FragmentDevicePaired.this.r();
            }
        });
    }
}
