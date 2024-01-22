package com.coveiot.android.leonardo.onboarding.goal.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.aigestudio.wheelpicker.WheelPicker;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.onboarding.goal.activities.ActivityStepsGoal;
import com.coveiot.android.leonardo.onboarding.goal.viewmodel.FragmentStepsGoalViewModel;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.remotecommandframework.RcfPreferenceManager;
import com.coveiot.android.remotecommandframework.alexa.activity.AccountLinkFromAlexaActivity;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentStepsGoal extends BaseFragment implements Observer<ArrayList<String>>, ViewModelListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public boolean m;
    public FragmentStepsGoalViewModel n;
    @Nullable
    public List<String> o;
    @Nullable
    public FitnessGoal p;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentStepsGoal newInstance() {
            return new FragmentStepsGoal();
        }
    }

    public static final void m(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        bottomSheetDialogOneButtonOneTitle.dismiss();
    }

    public static final void n(FragmentStepsGoal this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        List<String> list = this$0.o;
        Intrinsics.checkNotNull(list);
        String str = list.get(((WheelPicker) this$0._$_findCachedViewById(R.id.steps_goal_picker)).getCurrentItemPosition());
        if (!AppUtils.isNetConnected(this$0.requireContext())) {
            this$0.showNoInternetMessage();
            return;
        }
        this$0.m = true;
        FragmentStepsGoalViewModel fragmentStepsGoalViewModel = null;
        if (BleApiManager.getInstance(this$0.getContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            String string = this$0.getString(R.string.setting_goal);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_goal)");
            this$0.showProgresswithMsg(string);
            FragmentStepsGoalViewModel fragmentStepsGoalViewModel2 = this$0.n;
            if (fragmentStepsGoalViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                fragmentStepsGoalViewModel = fragmentStepsGoalViewModel2;
            }
            fragmentStepsGoalViewModel.onGoalSelected(str, 0);
        } else {
            SessionManager.getInstance(this$0.requireContext()).setWriteStepGoalAfterConnection(true);
            String string2 = this$0.getString(R.string.setting_goal);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.setting_goal)");
            this$0.showProgresswithMsg(string2);
            FragmentStepsGoalViewModel fragmentStepsGoalViewModel3 = this$0.n;
            if (fragmentStepsGoalViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                fragmentStepsGoalViewModel = fragmentStepsGoalViewModel3;
            }
            fragmentStepsGoalViewModel.onGoalSelected(str, 0);
        }
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.GOAL_SETTINGS_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_MAIN_HOME_DASHBOARD.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.DONE_BUTTON.getValue());
        FitnessGoal fitnessGoal = this$0.p;
        if (fitnessGoal != null) {
            Intrinsics.checkNotNull(fitnessGoal);
            analyticsLog.setOldStepstarget(String.valueOf(fitnessGoal.target));
        }
        analyticsLog.setNewStepsTarget(str);
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
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

    public final void bandDisconnectDialog() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String string = getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.band_not_connected)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(requireActivity, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.goal.fragments.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentStepsGoal.m(BottomSheetDialogOneButtonOneTitle.this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Nullable
    public final List<String> getDataset() {
        return this.o;
    }

    public final boolean isLoadDashBoard() {
        return this.m;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_steps_goal_fragment, viewGroup, false);
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onDataFailure(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (getActivity() == null || !isAdded()) {
            return;
        }
        Toast.makeText(requireActivity(), message, 1).show();
        dismissProgress();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onSuccess() {
        dismissProgress();
        if (getActivity() != null && (getActivity() instanceof ActivityStepsGoal)) {
            requireActivity().finish();
        }
        if (getActivity() != null && isAdded() && this.m) {
            this.m = false;
            RcfPreferenceManager.Companion companion = RcfPreferenceManager.Companion;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            if (companion.getInstance(requireActivity).getAlexaAccountLinkingUri() != null) {
                FragmentActivity requireActivity2 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                if (!Intrinsics.areEqual(String.valueOf(companion.getInstance(requireActivity2).getAlexaAccountLinkingUri()), "null")) {
                    SessionManager.getInstance(getContext()).setOnBoardingComplete(true);
                    Intent intent = new Intent(requireActivity(), AccountLinkFromAlexaActivity.class);
                    FragmentActivity requireActivity3 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
                    intent.setData(companion.getInstance(requireActivity3).getAlexaAccountLinkingUri());
                    intent.putExtra("wasNotLoggedIn", true);
                    startActivity(intent);
                    requireActivity().finish();
                    return;
                }
            }
            AppNavigator.Companion companion2 = AppNavigator.Companion;
            FragmentActivity requireActivity4 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity()");
            companion2.navigateToDashBoard(requireActivity4, false);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        FragmentStepsGoalViewModel fragmentStepsGoalViewModel = (FragmentStepsGoalViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(FragmentStepsGoalViewModel.class);
        this.n = fragmentStepsGoalViewModel;
        FragmentStepsGoalViewModel fragmentStepsGoalViewModel2 = null;
        if (fragmentStepsGoalViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStepsGoalViewModel = null;
        }
        fragmentStepsGoalViewModel.setMListener(this);
        int i = R.id.steps_goal_picker;
        ((WheelPicker) _$_findCachedViewById(i)).setAtmospheric(true);
        ((WheelPicker) _$_findCachedViewById(i)).setCyclic(true);
        FragmentStepsGoalViewModel fragmentStepsGoalViewModel3 = this.n;
        if (fragmentStepsGoalViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStepsGoalViewModel3 = null;
        }
        fragmentStepsGoalViewModel3.getStepsGoalList().observe(this, this);
        FragmentStepsGoalViewModel fragmentStepsGoalViewModel4 = this.n;
        if (fragmentStepsGoalViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStepsGoalViewModel4 = null;
        }
        fragmentStepsGoalViewModel4.prepareStepsGoalData();
        ((WheelPicker) _$_findCachedViewById(i)).setSelectedItemPosition(9);
        FragmentStepsGoalViewModel fragmentStepsGoalViewModel5 = this.n;
        if (fragmentStepsGoalViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentStepsGoalViewModel2 = fragmentStepsGoalViewModel5;
        }
        this.o = fragmentStepsGoalViewModel2.getStepsGoalList().getValue();
        FitnessGoal fitnessGoalStepsData = UserDataManager.getInstance(requireActivity()).getFitnessGoalStepsData();
        this.p = fitnessGoalStepsData;
        if (fitnessGoalStepsData != null) {
            List<String> list = this.o;
            Intrinsics.checkNotNull(list);
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                List<String> list2 = this.o;
                Intrinsics.checkNotNull(list2);
                FitnessGoal fitnessGoal = this.p;
                Intrinsics.checkNotNull(fitnessGoal);
                if (list2.get(i2).equals(String.valueOf(fitnessGoal.getTarget()))) {
                    ((WheelPicker) _$_findCachedViewById(R.id.steps_goal_picker)).setSelectedItemPosition(i2);
                }
            }
        }
        ((Button) _$_findCachedViewById(R.id.btn_done)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.goal.fragments.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentStepsGoal.n(FragmentStepsGoal.this, view2);
            }
        });
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.GOAL_SETTINGS_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
    }

    public final void setDataset(@Nullable List<String> list) {
        this.o = list;
    }

    public final void setLoadDashBoard(boolean z) {
        this.m = z;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable ArrayList<String> arrayList) {
        ((WheelPicker) _$_findCachedViewById(R.id.steps_goal_picker)).setData(arrayList);
        this.o = arrayList;
    }
}
