package com.coveiot.android.tappy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatRadioButton;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.activity.CardActivationActivity;
import com.coveiot.android.tappy.databinding.FragmentStepupOptionsBinding;
import com.coveiot.android.tappy.model.GetStepUpOptionsResponse;
import com.coveiot.android.tappy.utils.StepUpOptions;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.CleverTapConstants;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class FragmentStepUpOptions extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public FragmentStepupOptionsBinding m;
    @Nullable
    public GetStepUpOptionsResponse n;
    @Nullable
    public String o;
    @Nullable
    public String p;

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentStepUpOptions newInstance(@NotNull GetStepUpOptionsResponse stepUpOptionsResponse) {
            Intrinsics.checkNotNullParameter(stepUpOptionsResponse, "stepUpOptionsResponse");
            FragmentStepUpOptions fragmentStepUpOptions = new FragmentStepUpOptions();
            fragmentStepUpOptions.n = stepUpOptionsResponse;
            return fragmentStepUpOptions;
        }
    }

    public static final void n(AppCompatRadioButton[] radioButtons, AppCompatRadioButton radioButton, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(radioButtons, "$radioButtons");
        Intrinsics.checkNotNullParameter(radioButton, "$radioButton");
        if (z) {
            for (AppCompatRadioButton appCompatRadioButton : radioButtons) {
                if (!Intrinsics.areEqual(appCompatRadioButton, radioButton)) {
                    appCompatRadioButton.setChecked(false);
                }
            }
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentStepUpOptions newInstance(@NotNull GetStepUpOptionsResponse getStepUpOptionsResponse) {
        return Companion.newInstance(getStepUpOptionsResponse);
    }

    public static final void o(AppCompatRadioButton[] radioButtons, FragmentStepUpOptions this$0, CardActivationActivity cardActivationActivity, View view) {
        StepUpOptions stepUpOptions;
        AppCompatRadioButton appCompatRadioButton;
        Intrinsics.checkNotNullParameter(radioButtons, "$radioButtons");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int length = radioButtons.length;
        int i = 0;
        while (true) {
            stepUpOptions = null;
            if (i >= length) {
                appCompatRadioButton = null;
                break;
            }
            appCompatRadioButton = radioButtons[i];
            if (appCompatRadioButton.isChecked()) {
                break;
            }
            i++;
        }
        if (appCompatRadioButton != null) {
            DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_PAY_MOBILE_OTP_SENT.getValue(), null);
            if (Intrinsics.areEqual(appCompatRadioButton, this$0.m().rbCallMe)) {
                stepUpOptions = StepUpOptions.ISSUER_CALL_CARDHOLDER;
            } else if (Intrinsics.areEqual(appCompatRadioButton, this$0.m().rbSendCodeThroughSms)) {
                stepUpOptions = StepUpOptions.OTP_SMS;
            } else if (Intrinsics.areEqual(appCompatRadioButton, this$0.m().rbSendCodeThroughEmail)) {
                stepUpOptions = StepUpOptions.OTP_EMAIL;
            } else if (Intrinsics.areEqual(appCompatRadioButton, this$0.m().rbCallIssuer)) {
                stepUpOptions = StepUpOptions.CARDHOLDER_CALL_ISSUER;
            } else if (Intrinsics.areEqual(appCompatRadioButton, this$0.m().rbAppToApp)) {
                stepUpOptions = StepUpOptions.ISSUER_APP_TO_APP;
            }
            if (stepUpOptions != null) {
                if (cardActivationActivity != null) {
                    cardActivationActivity.setSelectedOption(stepUpOptions);
                }
                if (cardActivationActivity != null) {
                    cardActivationActivity.putStepUpOption(false);
                    return;
                }
                return;
            }
            return;
        }
        Toast.makeText(this$0.requireActivity(), this$0.getString(R.string.please_select_one_of_the_options), 0).show();
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

    public final FragmentStepupOptionsBinding m() {
        FragmentStepupOptionsBinding fragmentStepupOptionsBinding = this.m;
        Intrinsics.checkNotNull(fragmentStepupOptionsBinding);
        return fragmentStepupOptionsBinding;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentStepupOptionsBinding.inflate(inflater, viewGroup, false);
        return m().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* JADX WARN: Removed duplicated region for block: B:85:0x02fd A[LOOP:1: B:84:0x02fb->B:85:0x02fd, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0315  */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onViewCreated(@org.jetbrains.annotations.NotNull android.view.View r9, @org.jetbrains.annotations.Nullable android.os.Bundle r10) {
        /*
            Method dump skipped, instructions count: 828
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.tappy.fragment.FragmentStepUpOptions.onViewCreated(android.view.View, android.os.Bundle):void");
    }
}
