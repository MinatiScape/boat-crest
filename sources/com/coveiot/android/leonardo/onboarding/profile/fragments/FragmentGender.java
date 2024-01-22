package com.coveiot.android.leonardo.onboarding.profile.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.util.Dashboard2Constants;
import com.coveiot.android.leonardo.onboarding.profile.activities.ActivityProfile;
import com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentGenderViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentGender extends BaseFragment implements Observer<String> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public FragmentGenderViewModel m;
    @Nullable
    public View n;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentGender newInstance() {
            return new FragmentGender();
        }
    }

    public static final void o(FragmentGender this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.profile.activities.ActivityProfile");
        ((ActivityProfile) activity).loadFinalProfileFragment();
        FragmentGenderViewModel fragmentGenderViewModel = this$0.m;
        if (fragmentGenderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentGenderViewModel = null;
        }
        MutableLiveData<String> gender = fragmentGenderViewModel.getGender();
        Intrinsics.checkNotNull(gender);
        gender.setValue(null);
    }

    public static final void p(FragmentGender this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentGenderViewModel fragmentGenderViewModel = this$0.m;
        if (fragmentGenderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentGenderViewModel = null;
        }
        fragmentGenderViewModel.onMaleSlected();
    }

    public static final void q(FragmentGender this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentGenderViewModel fragmentGenderViewModel = this$0.m;
        if (fragmentGenderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentGenderViewModel = null;
        }
        fragmentGenderViewModel.onFemaleSelected();
    }

    public static final void r(FragmentGender this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.GENDER_SELECTION_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_HEIGHT_WEIGHT_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.NEXT_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.profile.activities.ActivityProfile");
        ((ActivityProfile) activity).loadFinalProfileFragment();
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
    public final View getView$app_prodRelease() {
        return this.n;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        if (this.n != null) {
            if (requireView().getParent() != null) {
                ViewParent parent = requireView().getParent();
                Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
                ((ViewGroup) parent).removeView(this.n);
            }
            return this.n;
        }
        View inflate = inflater.inflate(R.layout.fragment_gender_fragment, viewGroup, false);
        this.n = inflate;
        return inflate;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        FragmentGenderViewModel fragmentGenderViewModel = this.m;
        if (fragmentGenderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentGenderViewModel = null;
        }
        MutableLiveData<String> gender = fragmentGenderViewModel.getGender();
        Intrinsics.checkNotNull(gender);
        gender.removeObservers(this);
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity requireActivity = requireActivity();
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        FragmentGenderViewModel fragmentGenderViewModel = (FragmentGenderViewModel) ViewModelProviders.of(requireActivity, new ViewModelFactory(requireActivity2)).get(FragmentGenderViewModel.class);
        this.m = fragmentGenderViewModel;
        FragmentGenderViewModel fragmentGenderViewModel2 = null;
        if (fragmentGenderViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentGenderViewModel = null;
        }
        MutableLiveData<String> gender = fragmentGenderViewModel.getGender();
        Intrinsics.checkNotNull(gender);
        gender.setValue(null);
        FragmentGenderViewModel fragmentGenderViewModel3 = this.m;
        if (fragmentGenderViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentGenderViewModel2 = fragmentGenderViewModel3;
        }
        MutableLiveData<String> gender2 = fragmentGenderViewModel2.getGender();
        Intrinsics.checkNotNull(gender2);
        gender2.observe(getViewLifecycleOwner(), this);
        if (getActivity() != null && !requireActivity().isFinishing() && isAdded()) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.profile.activities.ActivityProfile");
            if (((ActivityProfile) activity).isSkipTextViewInitialised()) {
                FragmentActivity activity2 = getActivity();
                Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.profile.activities.ActivityProfile");
                ((ActivityProfile) activity2).getSkipTextView().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.u
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        FragmentGender.o(FragmentGender.this, view2);
                    }
                });
            }
        }
        ((ImageView) _$_findCachedViewById(R.id.iv_male)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.t
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentGender.p(FragmentGender.this, view2);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.iv_female)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentGender.q(FragmentGender.this, view2);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_next)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentGender.r(FragmentGender.this, view2);
            }
        });
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.GENDER_SELECTION_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
    }

    public final void s() {
        int color = ContextCompat.getColor(requireContext(), R.color.main_text_color);
        ((ImageView) _$_findCachedViewById(R.id.iv_female)).setImageResource(R.drawable.female_avatar_colored);
        ((TextView) _$_findCachedViewById(R.id.tv_female)).setTextColor(color);
    }

    public final void setView$app_prodRelease(@Nullable View view) {
        this.n = view;
    }

    public final void t() {
        int color = ContextCompat.getColor(requireContext(), R.color.main_text_color);
        ((ImageView) _$_findCachedViewById(R.id.iv_male)).setImageResource(R.drawable.male_avatar_colored);
        ((TextView) _$_findCachedViewById(R.id.tv_male)).setTextColor(color);
    }

    public final void u() {
        int color = ContextCompat.getColor(requireContext(), R.color.main_text_color);
        ((ImageView) _$_findCachedViewById(R.id.iv_female)).setImageResource(R.drawable.female_avatar);
        ((TextView) _$_findCachedViewById(R.id.tv_female)).setTextColor(color);
    }

    public final void v() {
        int color = ContextCompat.getColor(requireContext(), R.color.main_text_color);
        ((ImageView) _$_findCachedViewById(R.id.iv_male)).setImageResource(R.drawable.male_avatar);
        ((TextView) _$_findCachedViewById(R.id.tv_male)).setTextColor(color);
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable String str) {
        if (str != null) {
            if (Intrinsics.areEqual(str, "Male")) {
                t();
                u();
                ((Button) _$_findCachedViewById(R.id.btn_next)).setEnabled(true);
                return;
            } else if (Intrinsics.areEqual(str, Dashboard2Constants.FEMALE)) {
                s();
                v();
                ((Button) _$_findCachedViewById(R.id.btn_next)).setEnabled(true);
                return;
            } else {
                return;
            }
        }
        u();
        v();
        ((Button) _$_findCachedViewById(R.id.btn_next)).setEnabled(false);
    }
}
