package com.coveiot.android.leonardo.onboarding.profile.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.aigestudio.wheelpicker.WheelPicker;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.onboarding.profile.activities.ActivityProfile;
import com.coveiot.android.leonardo.onboarding.profile.listeners.UpdateDobListener;
import com.coveiot.android.leonardo.onboarding.profile.model.HeightWeightDob;
import com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentFinalProfileViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.PickerDialog;
import com.coveiot.utils.model.FailureType;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentFinalProfile extends BaseFragment implements Observer<HeightWeightDob>, UpdateDobListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public FragmentFinalProfileViewModel m;
    @Nullable
    public View n;
    public ActivityResultLauncher<String[]> o;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @RequiresApi(31)
    @NotNull
    public final String[] p = {"android.permission.BLUETOOTH_SCAN", "android.permission.BLUETOOTH_CONNECT"};

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentFinalProfile newInstance() {
            return new FragmentFinalProfile();
        }
    }

    public static final void A(Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setParentScreenName(FirebaseEventParams.ScreenName.HEIGHT_WEIGHT_SCREEN.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.DOB_POPUP.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_HEIGHT_WEIGHT_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.CANCEL_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        ((Dialog) dialog.element).dismiss();
    }

    public static final void C(FragmentFinalProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((TextInputLayout) this$0._$_findCachedViewById(R.id.updateHeightTextInputLayout)).performClick();
    }

    public static final void D(FragmentFinalProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.HEIGHT_WEIGHT_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_HEIGHT_POP_UP.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.SELECT_HEIGHT.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        this$0.R();
    }

    public static final void E(FragmentFinalProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((TextInputLayout) this$0._$_findCachedViewById(R.id.updateWeightTextInputLayout)).performClick();
    }

    public static final void F(FragmentFinalProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.HEIGHT_WEIGHT_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_WEIGHT_POP_UP.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.SELECT_WEIGHT.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        this$0.T();
    }

    public static final void G(FragmentFinalProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((TextInputLayout) this$0._$_findCachedViewById(R.id.updateDateOfBirthTextInputLayout)).performClick();
    }

    public static final void H(FragmentFinalProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.HEIGHT_WEIGHT_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_DOB_POP_UP.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.SELECT_DOB.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        this$0.y();
    }

    public static final void I(FragmentFinalProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.HEIGHT_WEIGHT_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_PAIRING_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.SUBMIT_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        if (AppUtils.isNetConnected(this$0.getActivity())) {
            if (Build.VERSION.SDK_INT >= 31) {
                if (PayUtils.INSTANCE.checkIfBluetoothPermissionExists(this$0.requireContext())) {
                    this$0.B();
                    return;
                } else {
                    this$0.x();
                    return;
                }
            }
            this$0.B();
            return;
        }
        Toast.makeText(this$0.getActivity(), this$0.getString(R.string.noconnection), 0).show();
    }

    public static final void K(FragmentFinalProfile this$0, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel = this$0.m;
        if (fragmentFinalProfileViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel = null;
        }
        fragmentFinalProfileViewModel.setDate_var(obj.toString());
    }

    public static final void M(FragmentFinalProfile this$0, Dialog dialog, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel = this$0.m;
        if (fragmentFinalProfileViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel = null;
        }
        fragmentFinalProfileViewModel.setMonth_var(obj.toString());
        this$0.J(dialog);
    }

    public static final void O(FragmentFinalProfile this$0, Dialog dialog, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel = this$0.m;
        if (fragmentFinalProfileViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel = null;
        }
        fragmentFinalProfileViewModel.setStarting_year(obj.toString());
        this$0.J(dialog);
    }

    public static final void Q(Ref.IntRef permissionCount, FragmentFinalProfile this$0, Map map) {
        Intrinsics.checkNotNullParameter(permissionCount, "$permissionCount");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        for (Map.Entry entry : map.entrySet()) {
            if (((Boolean) entry.getValue()).booleanValue()) {
                permissionCount.element++;
            }
        }
        if (permissionCount.element == 2) {
            this$0.B();
            return;
        }
        String string = this$0.getString(R.string.bluetooth_permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bluetooth_permission_required)");
        this$0.S(string);
    }

    public static final void z(FragmentFinalProfile this$0, Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setParentScreenName(FirebaseEventParams.ScreenName.HEIGHT_WEIGHT_SCREEN.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.DOB_POPUP.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_HEIGHT_WEIGHT_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.OK_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel = this$0.m;
        if (fragmentFinalProfileViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel = null;
        }
        fragmentFinalProfileViewModel.oDobOkAction();
        ((Dialog) dialog.element).dismiss();
    }

    public final void B() {
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel = null;
        BaseFragment.showProgress$default(this, false, 1, null);
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel2 = this.m;
        if (fragmentFinalProfileViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentFinalProfileViewModel = fragmentFinalProfileViewModel2;
        }
        fragmentFinalProfileViewModel.onSubmitClicked(new FragmentFinalProfileViewModel.SubmitClickListner() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.FragmentFinalProfile$onSubmitClick$1
            @Override // com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentFinalProfileViewModel.SubmitClickListner
            public void onFailure(@NotNull FailureType failureType, @NotNull String message) {
                Intrinsics.checkNotNullParameter(failureType, "failureType");
                Intrinsics.checkNotNullParameter(message, "message");
                FragmentFinalProfile.this.dismissProgress();
                if (FragmentFinalProfile.this.getContext() != null) {
                    Context context = FragmentFinalProfile.this.getContext();
                    Intrinsics.checkNotNull(context);
                    Toast.makeText(context, message, 1).show();
                }
            }

            @Override // com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentFinalProfileViewModel.SubmitClickListner
            public void proccedToNextScreen() {
                FragmentFinalProfile.this.dismissProgress();
                if (FragmentFinalProfile.this.getActivity() != null) {
                    FragmentActivity activity = FragmentFinalProfile.this.getActivity();
                    Intrinsics.checkNotNull(activity);
                    ((ActivityProfile) activity).loadBlueToothScanActivity();
                }
            }
        });
    }

    public final void J(Dialog dialog) {
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel = this.m;
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel2 = null;
        if (fragmentFinalProfileViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel = null;
        }
        fragmentFinalProfileViewModel.populateDaysDataInView();
        int i = R.id.number_picker_date;
        WheelPicker wheelPicker = (WheelPicker) dialog.findViewById(i);
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel3 = this.m;
        if (fragmentFinalProfileViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel3 = null;
        }
        wheelPicker.setData(fragmentFinalProfileViewModel3.getDays());
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(3);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel4 = this.m;
        if (fragmentFinalProfileViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel4 = null;
        }
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel5 = this.m;
        if (fragmentFinalProfileViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel5 = null;
        }
        ArrayList<String> days = fragmentFinalProfileViewModel5.getDays();
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel6 = this.m;
        if (fragmentFinalProfileViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel6 = null;
        }
        fragmentFinalProfileViewModel4.setMDayItemPosition(days.indexOf(fragmentFinalProfileViewModel6.getDate_var()));
        WheelPicker wheelPicker2 = (WheelPicker) dialog.findViewById(i);
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel7 = this.m;
        if (fragmentFinalProfileViewModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentFinalProfileViewModel2 = fragmentFinalProfileViewModel7;
        }
        wheelPicker2.setSelectedItemPosition(fragmentFinalProfileViewModel2.getMDayItemPosition());
        ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.f
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker3, Object obj, int i2) {
                FragmentFinalProfile.K(FragmentFinalProfile.this, wheelPicker3, obj, i2);
            }
        });
    }

    public final void L(final Dialog dialog) {
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel = this.m;
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel2 = null;
        if (fragmentFinalProfileViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel = null;
        }
        fragmentFinalProfileViewModel.populateMonthsDataInView();
        int i = R.id.number_picker_month;
        WheelPicker wheelPicker = (WheelPicker) dialog.findViewById(i);
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel3 = this.m;
        if (fragmentFinalProfileViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel3 = null;
        }
        wheelPicker.setData(fragmentFinalProfileViewModel3.getMonths());
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(3);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel4 = this.m;
        if (fragmentFinalProfileViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel4 = null;
        }
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel5 = this.m;
        if (fragmentFinalProfileViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel5 = null;
        }
        ArrayList<String> months = fragmentFinalProfileViewModel5.getMonths();
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel6 = this.m;
        if (fragmentFinalProfileViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel6 = null;
        }
        fragmentFinalProfileViewModel4.setMMonthSelectedPosition(months.indexOf(fragmentFinalProfileViewModel6.getMonth_var()));
        WheelPicker wheelPicker2 = (WheelPicker) dialog.findViewById(i);
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel7 = this.m;
        if (fragmentFinalProfileViewModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentFinalProfileViewModel2 = fragmentFinalProfileViewModel7;
        }
        wheelPicker2.setSelectedItemPosition(fragmentFinalProfileViewModel2.getMMonthSelectedPosition());
        ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.h
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker3, Object obj, int i2) {
                FragmentFinalProfile.M(FragmentFinalProfile.this, dialog, wheelPicker3, obj, i2);
            }
        });
    }

    public final void N(final Dialog dialog) {
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel = this.m;
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel2 = null;
        if (fragmentFinalProfileViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel = null;
        }
        fragmentFinalProfileViewModel.populateYearsDataInView();
        int i = R.id.number_picker_year;
        WheelPicker wheelPicker = (WheelPicker) dialog.findViewById(i);
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel3 = this.m;
        if (fragmentFinalProfileViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel3 = null;
        }
        wheelPicker.setData(fragmentFinalProfileViewModel3.getYears());
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(3);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel4 = this.m;
        if (fragmentFinalProfileViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel4 = null;
        }
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel5 = this.m;
        if (fragmentFinalProfileViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel5 = null;
        }
        ArrayList<String> years = fragmentFinalProfileViewModel5.getYears();
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel6 = this.m;
        if (fragmentFinalProfileViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel6 = null;
        }
        fragmentFinalProfileViewModel4.setMYearSelectedPosition(years.indexOf(fragmentFinalProfileViewModel6.getYear_var()));
        WheelPicker wheelPicker2 = (WheelPicker) dialog.findViewById(i);
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel7 = this.m;
        if (fragmentFinalProfileViewModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentFinalProfileViewModel2 = fragmentFinalProfileViewModel7;
        }
        wheelPicker2.setSelectedItemPosition(fragmentFinalProfileViewModel2.getMYearSelectedPosition());
        ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.g
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker3, Object obj, int i2) {
                FragmentFinalProfile.O(FragmentFinalProfile.this, dialog, wheelPicker3, obj, i2);
            }
        });
    }

    public final void P() {
        final Ref.IntRef intRef = new Ref.IntRef();
        ActivityResultLauncher<String[]> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.q
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                FragmentFinalProfile.Q(Ref.IntRef.this, this, (Map) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…          }\n            }");
        this.o = registerForActivityResult;
    }

    public final void R() {
        PickerDialog.Companion companion = PickerDialog.Companion;
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        String string = getString(R.string.height);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.height)");
        String string2 = getString(R.string.cms);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.cms)");
        companion.dataPickerWeight(context, string, string2, 70, 272, 1, 165, new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.FragmentFinalProfile$showHeightPickerDialog$1
            @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
            public void onValueSelected(@NotNull String value) {
                FragmentFinalProfileViewModel fragmentFinalProfileViewModel;
                Intrinsics.checkNotNullParameter(value, "value");
                fragmentFinalProfileViewModel = FragmentFinalProfile.this.m;
                if (fragmentFinalProfileViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    fragmentFinalProfileViewModel = null;
                }
                fragmentFinalProfileViewModel.setHeight(value);
            }
        });
    }

    public final void S(String str) {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(requireContext, str);
        String string = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…ctivitymodes.R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.FragmentFinalProfile$showPermissionDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogOneButtonOneTitle.this.dismiss();
                AppUtils.openAppSettings(this.requireActivity(), 121);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void T() {
        PickerDialog.Companion companion = PickerDialog.Companion;
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        String string = getString(R.string.weight);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.weight)");
        String string2 = getString(R.string.kgs);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.kgs)");
        companion.dataPickerWeight(context, string, string2, 10, 450, 1, 60, new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.FragmentFinalProfile$showWeightPickerDialog$1
            @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
            public void onValueSelected(@NotNull String value) {
                FragmentFinalProfileViewModel fragmentFinalProfileViewModel;
                Intrinsics.checkNotNullParameter(value, "value");
                fragmentFinalProfileViewModel = FragmentFinalProfile.this.m;
                if (fragmentFinalProfileViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    fragmentFinalProfileViewModel = null;
                }
                fragmentFinalProfileViewModel.setWeight(value);
            }
        });
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

    @NotNull
    public final String[] getBluetoothPermissions() {
        return this.p;
    }

    @Nullable
    public final View getView$app_prodRelease() {
        return this.n;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View view = this.n;
        if (view != null) {
            Intrinsics.checkNotNull(view);
            if (view.getParent() != null) {
                View view2 = this.n;
                Intrinsics.checkNotNull(view2);
                ViewParent parent = view2.getParent();
                Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
                ((ViewGroup) parent).removeView(this.n);
            }
            return this.n;
        }
        View inflate = inflater.inflate(R.layout.fragment_final_profile_fragment, viewGroup, false);
        this.n = inflate;
        return inflate;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.leonardo.onboarding.profile.listeners.UpdateDobListener
    public void onUpdate(@NotNull String dob) {
        Intrinsics.checkNotNullParameter(dob, "dob");
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel = this.m;
        if (fragmentFinalProfileViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel = null;
        }
        fragmentFinalProfileViewModel.setDateOfBirth(dob);
        ((EditText) _$_findCachedViewById(R.id.updateDateOfBirthEditText)).setText(dob);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        int i = R.id.updateHeightEditText;
        ((EditText) _$_findCachedViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFinalProfile.C(FragmentFinalProfile.this, view2);
            }
        });
        ((TextInputLayout) _$_findCachedViewById(R.id.updateHeightTextInputLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFinalProfile.D(FragmentFinalProfile.this, view2);
            }
        });
        int i2 = R.id.updateWeightEditText;
        ((EditText) _$_findCachedViewById(i2)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFinalProfile.E(FragmentFinalProfile.this, view2);
            }
        });
        ((TextInputLayout) _$_findCachedViewById(R.id.updateWeightTextInputLayout)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFinalProfile.F(FragmentFinalProfile.this, view2);
            }
        });
        int i3 = R.id.updateDateOfBirthEditText;
        ((EditText) _$_findCachedViewById(i3)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFinalProfile.G(FragmentFinalProfile.this, view2);
            }
        });
        int i4 = R.id.updateDateOfBirthTextInputLayout;
        ((TextInputLayout) _$_findCachedViewById(i4)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFinalProfile.H(FragmentFinalProfile.this, view2);
            }
        });
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        FragmentActivity activity2 = getActivity();
        Intrinsics.checkNotNull(activity2);
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel = (FragmentFinalProfileViewModel) ViewModelProviders.of(activity, new ViewModelFactory(activity2)).get(FragmentFinalProfileViewModel.class);
        this.m = fragmentFinalProfileViewModel;
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel2 = null;
        if (fragmentFinalProfileViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel = null;
        }
        MutableLiveData<HeightWeightDob> heightWeightDob = fragmentFinalProfileViewModel.getHeightWeightDob();
        Intrinsics.checkNotNull(heightWeightDob);
        heightWeightDob.observe(this, this);
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel3 = this.m;
        if (fragmentFinalProfileViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel3 = null;
        }
        fragmentFinalProfileViewModel3.setWeight(null);
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel4 = this.m;
        if (fragmentFinalProfileViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel4 = null;
        }
        fragmentFinalProfileViewModel4.setHeight(null);
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel5 = this.m;
        if (fragmentFinalProfileViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel5 = null;
        }
        fragmentFinalProfileViewModel5.setUpdateDobListener(this);
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel6 = this.m;
        if (fragmentFinalProfileViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentFinalProfileViewModel2 = fragmentFinalProfileViewModel6;
        }
        fragmentFinalProfileViewModel2.initializeDob(((EditText) _$_findCachedViewById(i3)).getText().toString());
        P();
        ((Button) _$_findCachedViewById(R.id.btn_submit)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFinalProfile.I(FragmentFinalProfile.this, view2);
            }
        });
        Editable text = ((EditText) _$_findCachedViewById(i3)).getText();
        Intrinsics.checkNotNull(text);
        if (StringsKt__StringsKt.trim(text).toString().length() > 0) {
            ((TextInputLayout) _$_findCachedViewById(i4)).setHint(getResources().getString(R.string.date_of_birth));
            ((EditText) _$_findCachedViewById(i3)).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.delete_image, 0);
        } else {
            ((TextInputLayout) _$_findCachedViewById(i4)).setHint(getResources().getString(R.string.date_of_birth_optional));
            ((EditText) _$_findCachedViewById(i3)).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        ((EditText) _$_findCachedViewById(i3)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.FragmentFinalProfile$onViewCreated$8
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
                Intrinsics.checkNotNull(editable);
                if (StringsKt__StringsKt.trim(editable).toString().length() > 0) {
                    ((TextInputLayout) FragmentFinalProfile.this._$_findCachedViewById(R.id.updateDateOfBirthTextInputLayout)).setHint(FragmentFinalProfile.this.getResources().getString(R.string.date_of_birth));
                    ((EditText) FragmentFinalProfile.this._$_findCachedViewById(R.id.updateDateOfBirthEditText)).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.delete_image, 0);
                    return;
                }
                ((EditText) FragmentFinalProfile.this._$_findCachedViewById(R.id.updateDateOfBirthEditText)).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                ((TextInputLayout) FragmentFinalProfile.this._$_findCachedViewById(R.id.updateDateOfBirthTextInputLayout)).setHint(FragmentFinalProfile.this.getResources().getString(R.string.date_of_birth_optional));
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i5, int i6, int i7) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i5, int i6, int i7) {
            }
        });
        ((EditText) _$_findCachedViewById(i3)).setOnTouchListener(new View.OnTouchListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.FragmentFinalProfile$onViewCreated$9
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(@NotNull View v, @NotNull MotionEvent event) {
                FragmentFinalProfileViewModel fragmentFinalProfileViewModel7;
                FragmentFinalProfileViewModel fragmentFinalProfileViewModel8;
                Intrinsics.checkNotNullParameter(v, "v");
                Intrinsics.checkNotNullParameter(event, "event");
                if (event.getAction() == 1) {
                    FragmentFinalProfile fragmentFinalProfile = FragmentFinalProfile.this;
                    int i5 = R.id.updateDateOfBirthEditText;
                    Editable text2 = ((EditText) fragmentFinalProfile._$_findCachedViewById(i5)).getText();
                    Intrinsics.checkNotNull(text2);
                    if (StringsKt__StringsKt.trim(text2).toString().length() <= 0 || event.getRawX() < ((EditText) FragmentFinalProfile.this._$_findCachedViewById(i5)).getRight() - ((EditText) FragmentFinalProfile.this._$_findCachedViewById(i5)).getCompoundDrawables()[2].getBounds().width()) {
                        return false;
                    }
                    ((EditText) FragmentFinalProfile.this._$_findCachedViewById(i5)).setText(AppConstants.EMPTY_STRING.getValue());
                    fragmentFinalProfileViewModel7 = FragmentFinalProfile.this.m;
                    if (fragmentFinalProfileViewModel7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        fragmentFinalProfileViewModel7 = null;
                    }
                    fragmentFinalProfileViewModel7.setDateOfBirth((String) null);
                    fragmentFinalProfileViewModel8 = FragmentFinalProfile.this.m;
                    if (fragmentFinalProfileViewModel8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        fragmentFinalProfileViewModel8 = null;
                    }
                    fragmentFinalProfileViewModel8.setDateOfBirth((Calendar) null);
                    return true;
                }
                return false;
            }
        });
        Editable text2 = ((EditText) _$_findCachedViewById(i)).getText();
        Intrinsics.checkNotNull(text2);
        if (StringsKt__StringsKt.trim(text2).toString().length() > 0) {
            ((EditText) _$_findCachedViewById(i)).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.delete_image, 0);
        } else {
            ((EditText) _$_findCachedViewById(i)).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        ((EditText) _$_findCachedViewById(i)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.FragmentFinalProfile$onViewCreated$10
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
                Intrinsics.checkNotNull(editable);
                if (StringsKt__StringsKt.trim(editable).toString().length() > 0) {
                    ((EditText) FragmentFinalProfile.this._$_findCachedViewById(R.id.updateHeightEditText)).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.delete_image, 0);
                } else {
                    ((EditText) FragmentFinalProfile.this._$_findCachedViewById(R.id.updateHeightEditText)).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i5, int i6, int i7) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i5, int i6, int i7) {
            }
        });
        ((EditText) _$_findCachedViewById(i)).setOnTouchListener(new View.OnTouchListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.FragmentFinalProfile$onViewCreated$11
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(@NotNull View v, @NotNull MotionEvent event) {
                FragmentFinalProfileViewModel fragmentFinalProfileViewModel7;
                Intrinsics.checkNotNullParameter(v, "v");
                Intrinsics.checkNotNullParameter(event, "event");
                if (event.getAction() == 1) {
                    FragmentFinalProfile fragmentFinalProfile = FragmentFinalProfile.this;
                    int i5 = R.id.updateHeightEditText;
                    Editable text3 = ((EditText) fragmentFinalProfile._$_findCachedViewById(i5)).getText();
                    Intrinsics.checkNotNull(text3);
                    if (StringsKt__StringsKt.trim(text3).toString().length() <= 0 || event.getRawX() < ((EditText) FragmentFinalProfile.this._$_findCachedViewById(i5)).getRight() - ((EditText) FragmentFinalProfile.this._$_findCachedViewById(i5)).getCompoundDrawables()[2].getBounds().width()) {
                        return false;
                    }
                    ((EditText) FragmentFinalProfile.this._$_findCachedViewById(i5)).setText(AppConstants.EMPTY_STRING.getValue());
                    fragmentFinalProfileViewModel7 = FragmentFinalProfile.this.m;
                    if (fragmentFinalProfileViewModel7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        fragmentFinalProfileViewModel7 = null;
                    }
                    fragmentFinalProfileViewModel7.setHeight(null);
                    return true;
                }
                return false;
            }
        });
        Editable text3 = ((EditText) _$_findCachedViewById(i2)).getText();
        Intrinsics.checkNotNull(text3);
        if (StringsKt__StringsKt.trim(text3).toString().length() > 0) {
            ((EditText) _$_findCachedViewById(i2)).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.delete_image, 0);
        } else {
            ((EditText) _$_findCachedViewById(i2)).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        ((EditText) _$_findCachedViewById(i2)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.FragmentFinalProfile$onViewCreated$12
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
                Intrinsics.checkNotNull(editable);
                if (StringsKt__StringsKt.trim(editable).toString().length() > 0) {
                    ((EditText) FragmentFinalProfile.this._$_findCachedViewById(R.id.updateWeightEditText)).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.delete_image, 0);
                } else {
                    ((EditText) FragmentFinalProfile.this._$_findCachedViewById(R.id.updateWeightEditText)).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i5, int i6, int i7) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i5, int i6, int i7) {
            }
        });
        ((EditText) _$_findCachedViewById(i2)).setOnTouchListener(new View.OnTouchListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.FragmentFinalProfile$onViewCreated$13
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(@NotNull View v, @NotNull MotionEvent event) {
                FragmentFinalProfileViewModel fragmentFinalProfileViewModel7;
                Intrinsics.checkNotNullParameter(v, "v");
                Intrinsics.checkNotNullParameter(event, "event");
                if (event.getAction() == 1) {
                    FragmentFinalProfile fragmentFinalProfile = FragmentFinalProfile.this;
                    int i5 = R.id.updateWeightEditText;
                    Editable text4 = ((EditText) fragmentFinalProfile._$_findCachedViewById(i5)).getText();
                    Intrinsics.checkNotNull(text4);
                    if (StringsKt__StringsKt.trim(text4).toString().length() <= 0 || event.getRawX() < ((EditText) FragmentFinalProfile.this._$_findCachedViewById(i5)).getRight() - ((EditText) FragmentFinalProfile.this._$_findCachedViewById(i5)).getCompoundDrawables()[2].getBounds().width()) {
                        return false;
                    }
                    ((EditText) FragmentFinalProfile.this._$_findCachedViewById(i5)).setText(AppConstants.EMPTY_STRING.getValue());
                    fragmentFinalProfileViewModel7 = FragmentFinalProfile.this.m;
                    if (fragmentFinalProfileViewModel7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        fragmentFinalProfileViewModel7 = null;
                    }
                    fragmentFinalProfileViewModel7.setWeight(null);
                    return true;
                }
                return false;
            }
        });
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.HEIGHT_WEIGHT_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
    }

    public final void setView$app_prodRelease(@Nullable View view) {
        this.n = view;
    }

    @RequiresApi(31)
    public final void x() {
        if (getActivity() != null) {
            ActivityResultLauncher<String[]> activityResultLauncher = this.o;
            if (activityResultLauncher == null) {
                Intrinsics.throwUninitializedPropertyAccessException("permissionRequest");
                activityResultLauncher = null;
            }
            activityResultLauncher.launch(this.p);
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [T, android.app.Dialog] */
    public final void y() {
        FragmentFinalProfileViewModel fragmentFinalProfileViewModel = this.m;
        if (fragmentFinalProfileViewModel == null) {
            return;
        }
        if (fragmentFinalProfileViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentFinalProfileViewModel = null;
        }
        int i = R.id.updateDateOfBirthEditText;
        fragmentFinalProfileViewModel.initializeDob(((EditText) _$_findCachedViewById(i)).getText().toString());
        ((EditText) _$_findCachedViewById(i)).setFocusable(false);
        if (getContext() != null) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            ?? dialog = new Dialog(context, R.style.GenericDialog);
            objectRef.element = dialog;
            ((Dialog) dialog).setContentView(R.layout.dialog_date_of_birth);
            ((Dialog) objectRef.element).setCancelable(true);
            ((Dialog) objectRef.element).setCanceledOnTouchOutside(true);
            Window window = ((Dialog) objectRef.element).getWindow();
            Intrinsics.checkNotNull(window);
            if (window != null) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                Intrinsics.checkNotNullExpressionValue(attributes, "window.attributes");
                attributes.width = -1;
                attributes.height = -2;
                attributes.gravity = 17;
                window.setAttributes(attributes);
            }
            ((Dialog) objectRef.element).show();
            ((TextView) ((Dialog) objectRef.element).findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.o
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentFinalProfile.z(FragmentFinalProfile.this, objectRef, view);
                }
            });
            ((TextView) ((Dialog) objectRef.element).findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.p
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentFinalProfile.A(Ref.ObjectRef.this, view);
                }
            });
            J((Dialog) objectRef.element);
            L((Dialog) objectRef.element);
            N((Dialog) objectRef.element);
        }
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@NotNull HeightWeightDob heightWeightDob) {
        Intrinsics.checkNotNullParameter(heightWeightDob, "heightWeightDob");
        if (!AppUtils.isEmpty(heightWeightDob.getHeight())) {
            ((EditText) _$_findCachedViewById(R.id.updateHeightEditText)).setText(heightWeightDob.getHeight() + ' ' + getString(R.string.cms));
        }
        if (!AppUtils.isEmpty(heightWeightDob.getWeight())) {
            ((EditText) _$_findCachedViewById(R.id.updateWeightEditText)).setText(heightWeightDob.getWeight() + ' ' + getString(R.string.kgs));
        }
        heightWeightDob.getDateOfBirth();
        ((Button) _$_findCachedViewById(R.id.btn_submit)).setEnabled((AppUtils.isEmpty(heightWeightDob.getWeight()) || AppUtils.isEmpty(heightWeightDob.getHeight())) ? false : true);
    }
}
