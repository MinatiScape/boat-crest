package com.coveiot.android.leonardo.more.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.aigestudio.wheelpicker.WheelPicker;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.dashboard.ViewModelActivityDashboardNew;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.listener.OnInternetBleCheckListener;
import com.coveiot.android.leonardo.listener.OnSuccessListener;
import com.coveiot.android.leonardo.more.ContractorMyAccount;
import com.coveiot.android.leonardo.more.viewmodel.MyAccountViewModel;
import com.coveiot.android.leonardo.more.viewmodel.WomenWellnessViewModel;
import com.coveiot.android.leonardo.onboarding.profile.listeners.UpdateDobListener;
import com.coveiot.android.leonardo.onboarding.profile.model.HeightWeightDob;
import com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentFinalProfileViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.AppSessionManager;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.PreferenceManager;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessageTwoBtns;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogProfileGender;
import com.coveiot.android.theme.BottomSheetDialogProfileInfo;
import com.coveiot.android.theme.BottomSheetDialogProfilePicOptions;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.BottomSheetDialogTwoButtonsOneTitle;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.GenericMessageDialog;
import com.coveiot.android.theme.PickerDialog;
import com.coveiot.android.theme.StrideLengthAnimationActivity;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.android.weeklyreport.activities.ActivityWeeklyReport;
import com.coveiot.android.weeklyreport.utils.WeeklyReportConstant;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.covepreferences.data.WeeklyReportPrefData;
import com.coveiot.covepreferences.data.WomenWellnessData;
import com.coveiot.repository.profile.ProfileRepository;
import com.coveiot.utils.model.FailureType;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.CircularImageView;
import com.coveiot.utils.utility.GlideUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.BleConst;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityEditProfile extends BaseActivity implements ContractorMyAccount, UpdateDobListener, DialogListener, OnInternetBleCheckListener, OnSuccessListener {
    @Nullable
    public String[] E;
    public File F;
    @Nullable
    public Uri G;
    public int H;
    public int I;
    public int J;
    public int K;
    public boolean L;
    public boolean M;
    @NotNull
    public String N;
    @NotNull
    public String O;
    @NotNull
    public String P;
    @NotNull
    public String Q;
    public boolean R;
    public boolean S;
    @Nullable
    public BottomSheetDialogTwoButtonsOneTitle T;
    @Nullable
    public BottomSheetDialogImageTitleMessageTwoBtns U;
    public String mCurrentPhotoPath;
    public ProfileData p;
    public MyAccountViewModel q;
    public WomenWellnessViewModel r;
    @Nullable
    public String s;
    public int t;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int u = 1;
    public final int v = 2;
    @NotNull
    public final String w = "forWalkStride";
    @NotNull
    public final String x = "strideLength";
    @NotNull
    public final String y = "DEVICE_DISCONNECTED";
    @NotNull
    public final String z = "INTERNET_DISCONNECTED";
    @NotNull
    public final String A = "profileSuccess";
    @NotNull
    public final String B = "profileInfo";
    public final int C = 11;
    public final int D = 173;

    public ActivityEditProfile() {
        AppConstants appConstants = AppConstants.HEIGHT_DEFAULT;
        this.H = Integer.parseInt(appConstants.getValue());
        this.I = Integer.parseInt(appConstants.getValue());
        this.J = Integer.parseInt(appConstants.getValue());
        this.K = Integer.parseInt(AppConstants.WEIGHT_DEFAULT.getValue());
        this.N = "";
        this.O = "";
        this.P = "";
        this.Q = "";
        this.S = true;
    }

    public static final void A0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((ConstraintLayout) this$0._$_findCachedViewById(R.id.updateHeightTextCardView)).performClick();
    }

    public static final void A1(BottomSheetDialogProfilePicOptions bottomSheetDialogProfilePicOptions, ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogProfilePicOptions, "$bottomSheetDialogProfilePicOptions");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int selectedOption = bottomSheetDialogProfilePicOptions.getSelectedOption();
        if (selectedOption == 0) {
            this$0.t = 350;
            this$0.choosePhotoFromGallary();
        } else if (selectedOption == 1) {
            this$0.t = 351;
            String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(this$0, new String[]{"android.permission.CAMERA"});
            this$0.E = checkPermissionsHasGranted;
            Intrinsics.checkNotNull(checkPermissionsHasGranted);
            if (checkPermissionsHasGranted.length > 0) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this$0, "android.permission.CAMERA")) {
                    String[] strArr = this$0.E;
                    Intrinsics.checkNotNull(strArr);
                    ActivityCompat.requestPermissions(this$0, strArr, this$0.t);
                } else {
                    String string = this$0.getString(R.string.camera_permission_text);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.camera_permission_text)");
                    this$0.w1(string);
                }
            } else {
                this$0.F1();
            }
        }
        bottomSheetDialogProfilePicOptions.dismiss();
    }

    public static final void B0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((ConstraintLayout) this$0._$_findCachedViewById(R.id.updateWalkStrideLengthTextCardView)).performClick();
    }

    public static final void C0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.D1();
    }

    public static final void C1(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        if (this$0.S) {
            this$0.sendValueToWorkoutJs(true, "");
        } else if (!kotlin.text.m.equals(SessionManager.getInstance(this$0).getUserDetails().getName(), this$0.getString(R.string.boathead), true)) {
            if (!AppSessionManager.getInstance(this$0).isProfileCompletedFirstTime()) {
                AppSessionManager.getInstance(this$0).setIsProfileCompletedFirstTime(true);
                this$0.s1();
                return;
            }
            this$0.x0();
        } else {
            this$0.x0();
        }
    }

    public static final void D0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((ConstraintLayout) this$0._$_findCachedViewById(R.id.updateRunStrideLengthTextCardView)).performClick();
    }

    public static final void E0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.B1();
    }

    public static final void F0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.EDIT_PROFILE_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_HEIGHT_POP_UP.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.CHANGE_HEIGHT.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        this$0.v1();
    }

    public static final void G0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((ConstraintLayout) this$0._$_findCachedViewById(R.id.updateWeightTextCardView)).performClick();
    }

    public static final void H0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.EDIT_PROFILE_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_WEIGHT_POP_UP.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.CHANGE_WEIGHT.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        this$0.E1();
    }

    public static final void I0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((ConstraintLayout) this$0._$_findCachedViewById(R.id.updateDateOfBirthTextCardView)).performClick();
    }

    public static final void J0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.EDIT_PROFILE_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_DOB_POP_UP.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.CHANGE_DOB.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        this$0.c1();
    }

    public static final void K0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.f1();
    }

    public static final void L0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.f1();
    }

    public static final void M0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.l0();
    }

    public static final void N0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this$0.getString(R.string.tell_us_who_you_really_are);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.tell_us_who_you_really_are)");
        String string2 = this$0.getString(R.string.tell_us_info);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.tell_us_info)");
        this$0.i1(string, string2);
    }

    public static final void O0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this$0.getString(R.string.we_want_to_know_you_better);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.we_want_to_know_you_better)");
        String string2 = this$0.getString(R.string.we_want_to_know_info);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.we_want_to_know_info)");
        this$0.i1(string, string2);
    }

    public static final void P0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.V0(true);
    }

    public static final void Q0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.V0(false);
    }

    public static final void R0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        WeeklyReportPrefData weeklyReportData = UserDataManager.getInstance(this$0).getWeeklyReportData();
        if (weeklyReportData != null) {
            weeklyReportData.isEmailVerified();
            if (weeklyReportData.isEmailVerified()) {
                weeklyReportData.isSubscribed();
                if (weeklyReportData.isSubscribed()) {
                    this$0.confirmationDialogForEmailEdit();
                    return;
                }
            }
        }
        int i = R.id.updateEmailEditText;
        Editable text = ((EditText) this$0._$_findCachedViewById(i)).getText();
        Intrinsics.checkNotNull(text);
        if (StringsKt__StringsKt.trim(text).toString().length() > 0) {
            ((EditText) this$0._$_findCachedViewById(i)).setText(AppConstants.EMPTY_STRING.getValue());
        }
    }

    public static final void T0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void W0(ActivityEditProfile this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.o0();
    }

    public static final void X0(String str, ActivityEditProfile this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MyAccountViewModel myAccountViewModel = this$0.q;
        MyAccountViewModel myAccountViewModel2 = null;
        if (myAccountViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel = null;
        }
        if (!Intrinsics.areEqual(str, myAccountViewModel.getWalkStrideLength())) {
            PreferenceManager.saveWalkStrideLengthManuallyEdited(this$0, true);
            ((Button) this$0._$_findCachedViewById(R.id.btn_update_profile)).setEnabled(true);
            MyAccountViewModel myAccountViewModel3 = this$0.q;
            if (myAccountViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                myAccountViewModel2 = myAccountViewModel3;
            }
            myAccountViewModel2.setWalkStrideLength(str);
        } else if (!Intrinsics.areEqual(this$0.N, str)) {
            this$0.o0();
        } else {
            ((Button) this$0._$_findCachedViewById(R.id.btn_update_profile)).setEnabled(true);
        }
    }

    public static final void Y0(String str, ActivityEditProfile this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MyAccountViewModel myAccountViewModel = this$0.q;
        MyAccountViewModel myAccountViewModel2 = null;
        if (myAccountViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel = null;
        }
        if (!Intrinsics.areEqual(str, myAccountViewModel.getRunStrideLength())) {
            PreferenceManager.saveRunStrideLengthManuallyEdited(this$0, true);
            ((Button) this$0._$_findCachedViewById(R.id.btn_update_profile)).setEnabled(true);
            MyAccountViewModel myAccountViewModel3 = this$0.q;
            if (myAccountViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                myAccountViewModel2 = myAccountViewModel3;
            }
            myAccountViewModel2.setRunStrideLength(str);
        } else if (!Intrinsics.areEqual(this$0.P, str)) {
            this$0.o0();
        } else {
            ((Button) this$0._$_findCachedViewById(R.id.btn_update_profile)).setEnabled(true);
        }
    }

    public static final void Z0(ActivityEditProfile this$0, BottomSheetDialogTwoButtons dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        ((Button) this$0._$_findCachedViewById(R.id.btn_update_profile)).performClick();
        dialog.dismiss();
    }

    public static final void a1(BottomSheetDialogTwoButtons dialog, ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.finish();
    }

    public static final void b1(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void d1(ActivityEditProfile this$0, Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        MyAccountViewModel myAccountViewModel = this$0.q;
        if (myAccountViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel = null;
        }
        myAccountViewModel.oDobOkAction();
        ((Dialog) dialog.element).dismiss();
    }

    public static final void e1(Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        ((Dialog) dialog.element).dismiss();
    }

    public static final void g1(BottomSheetDialogProfileGender bottomSheetDialogProfileGender, ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogProfileGender, "$bottomSheetDialogProfileGender");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String gender = bottomSheetDialogProfileGender.getGender();
        int hashCode = gender.hashCode();
        MyAccountViewModel myAccountViewModel = null;
        if (hashCode != 2358797) {
            if (hashCode != 75532016) {
                if (hashCode == 2070122316 && gender.equals("FEMALE")) {
                    MyAccountViewModel myAccountViewModel2 = this$0.q;
                    if (myAccountViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    } else {
                        myAccountViewModel = myAccountViewModel2;
                    }
                    MutableLiveData<String> gender2 = myAccountViewModel.getGender();
                    String upperCase = AppConstants.FEMALE.getValue().toUpperCase();
                    Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
                    gender2.setValue(upperCase);
                    ((EditText) this$0._$_findCachedViewById(R.id.updateGenderEditText)).setText(this$0.getResources().getString(R.string.gender_female));
                }
            } else if (gender.equals("OTHER")) {
                MyAccountViewModel myAccountViewModel3 = this$0.q;
                if (myAccountViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    myAccountViewModel = myAccountViewModel3;
                }
                MutableLiveData<String> gender3 = myAccountViewModel.getGender();
                String upperCase2 = AppConstants.MALE.getValue().toUpperCase();
                Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase()");
                gender3.setValue(upperCase2);
                ((EditText) this$0._$_findCachedViewById(R.id.updateGenderEditText)).setText(this$0.getResources().getString(R.string.gender_other));
            }
        } else if (gender.equals("MALE")) {
            MyAccountViewModel myAccountViewModel4 = this$0.q;
            if (myAccountViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                myAccountViewModel = myAccountViewModel4;
            }
            MutableLiveData<String> gender4 = myAccountViewModel.getGender();
            String upperCase3 = AppConstants.MALE.getValue().toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase3, "this as java.lang.String).toUpperCase()");
            gender4.setValue(upperCase3);
            ((EditText) this$0._$_findCachedViewById(R.id.updateGenderEditText)).setText(this$0.getResources().getString(R.string.gender_male));
        }
        this$0.o0();
        bottomSheetDialogProfileGender.dismiss();
        this$0.p0();
    }

    public static final void h1(BottomSheetDialogProfileGender bottomSheetDialogProfileGender, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogProfileGender, "$bottomSheetDialogProfileGender");
        bottomSheetDialogProfileGender.dismiss();
    }

    public static final void j1(BottomSheetDialogProfileInfo bottomSheetDialogProfileInfo, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogProfileInfo, "$bottomSheetDialogProfileInfo");
        bottomSheetDialogProfileInfo.dismiss();
    }

    public static final void l1(ActivityEditProfile this$0, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MyAccountViewModel myAccountViewModel = this$0.q;
        if (myAccountViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel = null;
        }
        myAccountViewModel.setDate_var(obj.toString());
    }

    public static final void m0(ActivityEditProfile this$0, Ref.ObjectRef mPhoneModifyDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mPhoneModifyDialog, "$mPhoneModifyDialog");
        if (this$0.isFinishing() || !((GenericMessageDialog) mPhoneModifyDialog.element).isShowing()) {
            return;
        }
        ((GenericMessageDialog) mPhoneModifyDialog.element).dismiss();
        AppNavigator.Companion companion = AppNavigator.Companion;
        ProfileData profileData = this$0.p;
        if (profileData == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
            profileData = null;
        }
        String mobileNumber = profileData.getMobileNumber();
        Intrinsics.checkNotNullExpressionValue(mobileNumber, "mUserDetails.mobileNumber");
        companion.navigateToPhoneValidationScreen(this$0, true, mobileNumber);
    }

    public static final void n0(ActivityEditProfile this$0, Ref.ObjectRef mPhoneModifyDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mPhoneModifyDialog, "$mPhoneModifyDialog");
        if (this$0.isFinishing() || !((GenericMessageDialog) mPhoneModifyDialog.element).isShowing()) {
            return;
        }
        ((GenericMessageDialog) mPhoneModifyDialog.element).dismiss();
    }

    public static final void n1(ActivityEditProfile this$0, Dialog dialog, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        MyAccountViewModel myAccountViewModel = this$0.q;
        if (myAccountViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel = null;
        }
        myAccountViewModel.setMonth_var(obj.toString());
        this$0.k1(dialog);
    }

    public static final void p1(ActivityEditProfile this$0, Dialog dialog, WheelPicker wheelPicker, Object obj, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        MyAccountViewModel myAccountViewModel = this$0.q;
        if (myAccountViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel = null;
        }
        myAccountViewModel.setStarting_year(obj.toString());
        this$0.k1(dialog);
    }

    public static final void q0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0)) {
            this$0.showProgress();
            MyAccountViewModel myAccountViewModel = this$0.q;
            if (myAccountViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                myAccountViewModel = null;
            }
            myAccountViewModel.unSubscribeEmail();
            return;
        }
        Toast.makeText(this$0, this$0.getResources().getString(R.string.please_check_your_internet), 1).show();
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns = this$0.U;
        if (bottomSheetDialogImageTitleMessageTwoBtns != null) {
            bottomSheetDialogImageTitleMessageTwoBtns.dismiss();
        }
    }

    public static final void r0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns = this$0.U;
        if (bottomSheetDialogImageTitleMessageTwoBtns != null) {
            bottomSheetDialogImageTitleMessageTwoBtns.dismiss();
        }
    }

    public static final void r1(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        if (this$0.S) {
            this$0.sendValueToWorkoutJs(false, this$0.y);
        } else {
            this$0.finish();
        }
    }

    public static final void s0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.confirmationDialogForDeleteEmail();
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle = this$0.T;
        if (bottomSheetDialogTwoButtonsOneTitle != null) {
            bottomSheetDialogTwoButtonsOneTitle.dismiss();
        }
    }

    public static final void t0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0, ActivityWeeklyReport.class);
        WeeklyReportConstant weeklyReportConstant = WeeklyReportConstant.UPDATE_EMAIL;
        intent.putExtra(weeklyReportConstant.getValue(), weeklyReportConstant.getValue());
        this$0.startActivity(intent);
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle = this$0.T;
        if (bottomSheetDialogTwoButtonsOneTitle != null) {
            bottomSheetDialogTwoButtonsOneTitle.dismiss();
        }
    }

    public static final void t1(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ScrollView edit_profile_scrollView = (ScrollView) this$0._$_findCachedViewById(R.id.edit_profile_scrollView);
        Intrinsics.checkNotNullExpressionValue(edit_profile_scrollView, "edit_profile_scrollView");
        this$0.visible(edit_profile_scrollView);
        Button btn_update_profile = (Button) this$0._$_findCachedViewById(R.id.btn_update_profile);
        Intrinsics.checkNotNullExpressionValue(btn_update_profile, "btn_update_profile");
        this$0.visible(btn_update_profile);
        ConstraintLayout clCongratulations = (ConstraintLayout) this$0._$_findCachedViewById(R.id.clCongratulations);
        Intrinsics.checkNotNullExpressionValue(clCongratulations, "clCongratulations");
        this$0.gone(clCongratulations);
        View findViewById = this$0.findViewById(R.id.toolbar_title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.toolbar_title)");
        this$0.visible(findViewById);
        View findViewById2 = this$0.findViewById(R.id.toolbar_back_arrow);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById<TextView>(R.id.toolbar_back_arrow)");
        this$0.visible(findViewById2);
    }

    public static final void u1(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.x0();
    }

    public static final void x1(ActivityEditProfile this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        AppUtils.openAppSettings(this$0, -1);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void z0(ActivityEditProfile this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.y1();
    }

    public static final void z1(BottomSheetDialogProfilePicOptions bottomSheetDialogProfilePicOptions, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogProfilePicOptions, "$bottomSheetDialogProfilePicOptions");
        bottomSheetDialogProfilePicOptions.dismiss();
    }

    public final void B1() {
        PickerDialog.Companion companion = PickerDialog.Companion;
        Intrinsics.checkNotNull(this);
        String string = getString(R.string.run_stride_length);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.run_stride_length)");
        companion.dataPickerStrideLength(this, string, Integer.parseInt(AppConstants.STRIDE_LENGTH_START_RANGE.getValue()), Integer.parseInt(AppConstants.STRIDE_LENGTH_END_RANGE.getValue()), this.J, new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityEditProfile$showRunStrideLengthPickerDialog$1
            @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
            public void onValueSelected(@NotNull String value) {
                MyAccountViewModel myAccountViewModel;
                String str;
                String str2;
                MyAccountViewModel myAccountViewModel2;
                MyAccountViewModel myAccountViewModel3;
                MyAccountViewModel myAccountViewModel4;
                MyAccountViewModel myAccountViewModel5;
                Intrinsics.checkNotNullParameter(value, "value");
                myAccountViewModel = ActivityEditProfile.this.q;
                MyAccountViewModel myAccountViewModel6 = null;
                if (myAccountViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    myAccountViewModel = null;
                }
                if (Intrinsics.areEqual(value, myAccountViewModel.getRunStrideLength())) {
                    str = ActivityEditProfile.this.Q;
                    str2 = ActivityEditProfile.this.P;
                    if (!Intrinsics.areEqual(str, str2)) {
                        ActivityEditProfile.this.o0();
                    } else {
                        ((Button) ActivityEditProfile.this._$_findCachedViewById(R.id.btn_update_profile)).setEnabled(true);
                    }
                } else {
                    PreferenceManager.saveRunStrideLengthManuallyEdited(ActivityEditProfile.this, true);
                    ActivityEditProfile.this.P = value;
                    ((Button) ActivityEditProfile.this._$_findCachedViewById(R.id.btn_update_profile)).setEnabled(true);
                }
                ActivityEditProfile activityEditProfile = ActivityEditProfile.this;
                myAccountViewModel2 = activityEditProfile.q;
                if (myAccountViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    myAccountViewModel2 = null;
                }
                activityEditProfile.M = true ^ Intrinsics.areEqual(value, myAccountViewModel2.getRunStrideLength());
                myAccountViewModel3 = ActivityEditProfile.this.q;
                if (myAccountViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    myAccountViewModel3 = null;
                }
                myAccountViewModel3.setRunStrideLength(value);
                ((EditText) ActivityEditProfile.this._$_findCachedViewById(R.id.updateRunStrideLength)).setText(value + ' ' + ActivityEditProfile.this.getString(R.string.cms));
                myAccountViewModel4 = ActivityEditProfile.this.q;
                if (myAccountViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    myAccountViewModel4 = null;
                }
                if (AppUtils.isEmpty(myAccountViewModel4.getWalkStrideLength())) {
                    return;
                }
                EditText editText = (EditText) ActivityEditProfile.this._$_findCachedViewById(R.id.updateWalkStrideLength);
                StringBuilder sb = new StringBuilder();
                myAccountViewModel5 = ActivityEditProfile.this.q;
                if (myAccountViewModel5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    myAccountViewModel6 = myAccountViewModel5;
                }
                sb.append(myAccountViewModel6.getWalkStrideLength());
                sb.append(' ');
                sb.append(ActivityEditProfile.this.getString(R.string.cms));
                editText.setText(sb.toString());
            }
        });
    }

    public final void D1() {
        PickerDialog.Companion companion = PickerDialog.Companion;
        Intrinsics.checkNotNull(this);
        String string = getString(R.string.walk_stride_length);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.walk_stride_length)");
        companion.dataPickerStrideLength(this, string, Integer.parseInt(AppConstants.STRIDE_LENGTH_START_RANGE.getValue()), Integer.parseInt(AppConstants.STRIDE_LENGTH_END_RANGE.getValue()), this.I, new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityEditProfile$showWalkStrideLengthPickerDialog$1
            @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
            public void onValueSelected(@NotNull String value) {
                MyAccountViewModel myAccountViewModel;
                String str;
                String str2;
                MyAccountViewModel myAccountViewModel2;
                MyAccountViewModel myAccountViewModel3;
                MyAccountViewModel myAccountViewModel4;
                MyAccountViewModel myAccountViewModel5;
                Intrinsics.checkNotNullParameter(value, "value");
                myAccountViewModel = ActivityEditProfile.this.q;
                MyAccountViewModel myAccountViewModel6 = null;
                if (myAccountViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    myAccountViewModel = null;
                }
                if (Intrinsics.areEqual(value, myAccountViewModel.getWalkStrideLength())) {
                    str = ActivityEditProfile.this.O;
                    str2 = ActivityEditProfile.this.N;
                    if (!Intrinsics.areEqual(str, str2)) {
                        ActivityEditProfile.this.o0();
                    } else {
                        ((Button) ActivityEditProfile.this._$_findCachedViewById(R.id.btn_update_profile)).setEnabled(true);
                    }
                } else {
                    PreferenceManager.saveWalkStrideLengthManuallyEdited(ActivityEditProfile.this, true);
                    ActivityEditProfile.this.N = value;
                    ((Button) ActivityEditProfile.this._$_findCachedViewById(R.id.btn_update_profile)).setEnabled(true);
                }
                ActivityEditProfile activityEditProfile = ActivityEditProfile.this;
                myAccountViewModel2 = activityEditProfile.q;
                if (myAccountViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    myAccountViewModel2 = null;
                }
                activityEditProfile.L = true ^ Intrinsics.areEqual(value, myAccountViewModel2.getWalkStrideLength());
                myAccountViewModel3 = ActivityEditProfile.this.q;
                if (myAccountViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    myAccountViewModel3 = null;
                }
                myAccountViewModel3.setWalkStrideLength(value);
                ((EditText) ActivityEditProfile.this._$_findCachedViewById(R.id.updateWalkStrideLength)).setText(value + ' ' + ActivityEditProfile.this.getString(R.string.cms));
                myAccountViewModel4 = ActivityEditProfile.this.q;
                if (myAccountViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    myAccountViewModel4 = null;
                }
                if (AppUtils.isEmpty(myAccountViewModel4.getRunStrideLength())) {
                    return;
                }
                EditText editText = (EditText) ActivityEditProfile.this._$_findCachedViewById(R.id.updateRunStrideLength);
                StringBuilder sb = new StringBuilder();
                myAccountViewModel5 = ActivityEditProfile.this.q;
                if (myAccountViewModel5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    myAccountViewModel6 = myAccountViewModel5;
                }
                sb.append(myAccountViewModel6.getRunStrideLength());
                sb.append(' ');
                sb.append(ActivityEditProfile.this.getString(R.string.cms));
                editText.setText(sb.toString());
            }
        });
    }

    public final void E1() {
        PickerDialog.Companion companion = PickerDialog.Companion;
        String string = getString(R.string.weight);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.weight)");
        String string2 = getString(R.string.kgs);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.kgs)");
        companion.dataPickerWeight(this, string, string2, Integer.parseInt(AppConstants.WEIGHT_START_RANGE.getValue()), Integer.parseInt(AppConstants.WEIGHT_END_RANGE.getValue()), 1, this.K, new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityEditProfile$showWeightPickerDialog$1
            @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
            public void onValueSelected(@NotNull String value) {
                MyAccountViewModel myAccountViewModel;
                Intrinsics.checkNotNullParameter(value, "value");
                ActivityEditProfile.this.setDefault_value_weight(Integer.parseInt(value));
                myAccountViewModel = ActivityEditProfile.this.q;
                if (myAccountViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    myAccountViewModel = null;
                }
                myAccountViewModel.setWeight(value);
                ActivityEditProfile.this.q1();
            }
        });
    }

    public final void F1() {
        File file = null;
        this.G = null;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(getPackageManager()) != null) {
            try {
                file = u0();
            } catch (IOException unused) {
            }
            if (file != null) {
                Uri uriForFile = FileProvider.getUriForFile(this, getPackageName() + ".provider", file);
                this.G = uriForFile;
                intent.putExtra("output", uriForFile);
                startActivityForResult(intent, this.v);
            }
        }
    }

    public final void S0() {
        AppConstants appConstants;
        try {
            Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), 2131231665);
            Intrinsics.checkNotNullExpressionValue(decodeResource, "decodeResource(this.reso….drawable.default_avatar)");
            FileOutputStream openFileOutput = openFileOutput(AppConstants.PROFILE_IMAGE_FILENAME.getValue(), 0);
            Intrinsics.checkNotNullExpressionValue(openFileOutput, "this.openFileOutput(\n   …ODE_PRIVATE\n            )");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            decodeResource.compress(Bitmap.CompressFormat.PNG, 70, openFileOutput);
            decodeResource.compress(Bitmap.CompressFormat.PNG, 70, byteArrayOutputStream);
            openFileOutput.flush();
            openFileOutput.close();
            this.F = new File(getFilesDir().toString() + '/' + appConstants.getValue());
            MyAccountViewModel myAccountViewModel = this.q;
            File file = null;
            if (myAccountViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                myAccountViewModel = null;
            }
            File file2 = this.F;
            if (file2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mProfilePicFile");
            } else {
                file = file2;
            }
            myAccountViewModel.setProfilePic(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void U0() {
        MyAccountViewModel myAccountViewModel = this.q;
        MyAccountViewModel myAccountViewModel2 = null;
        if (myAccountViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel = null;
        }
        MutableLiveData<HeightWeightDob> heightWeightDob = myAccountViewModel.getHeightWeightDob();
        Intrinsics.checkNotNull(heightWeightDob);
        heightWeightDob.observe(this, new Observer<HeightWeightDob>() { // from class: com.coveiot.android.leonardo.more.activities.ActivityEditProfile$initViewModelValues$1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable HeightWeightDob heightWeightDob2) {
                MyAccountViewModel myAccountViewModel3;
                MyAccountViewModel myAccountViewModel4;
                if (heightWeightDob2 != null) {
                    if (!AppUtils.isEmpty(heightWeightDob2.getHeight())) {
                        ((EditText) ActivityEditProfile.this._$_findCachedViewById(R.id.updateHeightEditText)).setText(heightWeightDob2.getHeight());
                        ActivityEditProfile.this.o0();
                        ActivityEditProfile.this.p0();
                        MyAccountViewModel myAccountViewModel5 = null;
                        if (!PreferenceManager.isWalkStrideLengthManuallyEdited(ActivityEditProfile.this)) {
                            ActivityEditProfile activityEditProfile = ActivityEditProfile.this;
                            String height = heightWeightDob2.getHeight();
                            Integer valueOf = height != null ? Integer.valueOf(Integer.parseInt(height)) : null;
                            Intrinsics.checkNotNull(valueOf);
                            activityEditProfile.setDefault_value_walkStrideLength(AppUtils.caluclateWalkingStrideLenght(valueOf.intValue()));
                            ((EditText) ActivityEditProfile.this._$_findCachedViewById(R.id.updateWalkStrideLength)).setText(ActivityEditProfile.this.getDefault_value_walkStrideLength() + ' ' + ActivityEditProfile.this.getString(R.string.cms));
                            myAccountViewModel4 = ActivityEditProfile.this.q;
                            if (myAccountViewModel4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                myAccountViewModel4 = null;
                            }
                            myAccountViewModel4.setWalkStrideLength(String.valueOf(ActivityEditProfile.this.getDefault_value_walkStrideLength()));
                        }
                        if (!PreferenceManager.isRunStrideLengthManuallyEdited(ActivityEditProfile.this)) {
                            ActivityEditProfile activityEditProfile2 = ActivityEditProfile.this;
                            String height2 = heightWeightDob2.getHeight();
                            Integer valueOf2 = height2 != null ? Integer.valueOf(Integer.parseInt(height2)) : null;
                            Intrinsics.checkNotNull(valueOf2);
                            activityEditProfile2.setDefault_value_runStrideLength(AppUtils.caluclateRunningStrideLenght(valueOf2.intValue()));
                            ((EditText) ActivityEditProfile.this._$_findCachedViewById(R.id.updateRunStrideLength)).setText(ActivityEditProfile.this.getDefault_value_runStrideLength() + ' ' + ActivityEditProfile.this.getString(R.string.cms));
                            DeviceUtils.Companion companion = DeviceUtils.Companion;
                            if (companion.isCZDevice(ActivityEditProfile.this) || companion.isCADevice(ActivityEditProfile.this) || companion.isCYDevice(ActivityEditProfile.this) || companion.isPS1Device(ActivityEditProfile.this) || companion.isBESDevice(ActivityEditProfile.this)) {
                                myAccountViewModel3 = ActivityEditProfile.this.q;
                                if (myAccountViewModel3 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                } else {
                                    myAccountViewModel5 = myAccountViewModel3;
                                }
                                myAccountViewModel5.setRunStrideLength(String.valueOf(ActivityEditProfile.this.getDefault_value_runStrideLength()));
                            }
                        }
                    }
                    if (AppUtils.isEmpty(heightWeightDob2.getWeight())) {
                        return;
                    }
                    ((EditText) ActivityEditProfile.this._$_findCachedViewById(R.id.updateWeightEditText)).setText(heightWeightDob2.getWeight());
                    ActivityEditProfile.this.o0();
                    ActivityEditProfile.this.p0();
                }
            }
        });
        MyAccountViewModel myAccountViewModel3 = this.q;
        if (myAccountViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel3 = null;
        }
        myAccountViewModel3.setUpdateDobListener(this);
        MyAccountViewModel myAccountViewModel4 = this.q;
        if (myAccountViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel4 = null;
        }
        myAccountViewModel4.setDialogListener(this);
        MyAccountViewModel myAccountViewModel5 = this.q;
        if (myAccountViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            myAccountViewModel2 = myAccountViewModel5;
        }
        myAccountViewModel2.initializeDob(((EditText) _$_findCachedViewById(R.id.updateDateOfBirthEditText)).getText().toString());
    }

    public final void V0(boolean z) {
        Intent intent = new Intent(this, StrideLengthAnimationActivity.class);
        intent.putExtra(this.w, z);
        startActivityForResult(intent, this.D);
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [T, android.app.Dialog] */
    public final void c1() {
        MyAccountViewModel myAccountViewModel = this.q;
        if (myAccountViewModel == null) {
            return;
        }
        if (myAccountViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel = null;
        }
        int i = R.id.updateDateOfBirthEditText;
        myAccountViewModel.initializeDob(((EditText) _$_findCachedViewById(i)).getText().toString());
        ((EditText) _$_findCachedViewById(i)).setFocusable(false);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? dialog = new Dialog(this, R.style.DialogTheme);
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
        ((TextView) ((Dialog) objectRef.element).findViewById(R.id.ok_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.w7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.d1(ActivityEditProfile.this, objectRef, view);
            }
        });
        ((TextView) ((Dialog) objectRef.element).findViewById(R.id.cancel_popup)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.k8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.e1(Ref.ObjectRef.this, view);
            }
        });
        k1((Dialog) objectRef.element);
        m1((Dialog) objectRef.element);
        o1((Dialog) objectRef.element);
    }

    public final void choosePhotoFromGallary() {
        startActivityForResult(PayUtils.INSTANCE.getGalleryIntent(), this.u);
    }

    public final void confirmationDialogForDeleteEmail() {
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns;
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns2;
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns3 = this.U;
        if (bottomSheetDialogImageTitleMessageTwoBtns3 != null) {
            Boolean valueOf = bottomSheetDialogImageTitleMessageTwoBtns3 != null ? Boolean.valueOf(bottomSheetDialogImageTitleMessageTwoBtns3.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() && (bottomSheetDialogImageTitleMessageTwoBtns2 = this.U) != null) {
                bottomSheetDialogImageTitleMessageTwoBtns2.dismiss();
            }
            this.U = null;
        }
        Drawable drawable = getResources().getDrawable(R.drawable.info_icon_new);
        Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.info_icon_new)");
        String string = getString(R.string.delete_email_id);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.delete_email_id)");
        String string2 = getString(R.string.deleting_email_id_info);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.deleting_email_id_info)");
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns4 = new BottomSheetDialogImageTitleMessageTwoBtns(this, drawable, string, string2, true);
        this.U = bottomSheetDialogImageTitleMessageTwoBtns4;
        String string3 = getString(R.string.yes);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(com.coveiot.an…watchfaceui.R.string.yes)");
        bottomSheetDialogImageTitleMessageTwoBtns4.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.r8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.q0(ActivityEditProfile.this, view);
            }
        });
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns5 = this.U;
        if (bottomSheetDialogImageTitleMessageTwoBtns5 != null) {
            String string4 = getString(R.string.f4085no);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(com.coveiot.an….watchfaceui.R.string.no)");
            bottomSheetDialogImageTitleMessageTwoBtns5.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.h7
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityEditProfile.r0(ActivityEditProfile.this, view);
                }
            });
        }
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns6 = this.U;
        Boolean valueOf2 = bottomSheetDialogImageTitleMessageTwoBtns6 != null ? Boolean.valueOf(bottomSheetDialogImageTitleMessageTwoBtns6.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if (valueOf2.booleanValue() || (bottomSheetDialogImageTitleMessageTwoBtns = this.U) == null) {
            return;
        }
        bottomSheetDialogImageTitleMessageTwoBtns.show();
    }

    public final void confirmationDialogForEmailEdit() {
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle;
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle2;
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle3 = this.T;
        if (bottomSheetDialogTwoButtonsOneTitle3 != null) {
            Boolean valueOf = bottomSheetDialogTwoButtonsOneTitle3 != null ? Boolean.valueOf(bottomSheetDialogTwoButtonsOneTitle3.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() && (bottomSheetDialogTwoButtonsOneTitle2 = this.T) != null) {
                bottomSheetDialogTwoButtonsOneTitle2.dismiss();
            }
            this.T = null;
        }
        String string = getString(R.string.choose_action);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.choose_action)");
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle4 = new BottomSheetDialogTwoButtonsOneTitle(this, string);
        this.T = bottomSheetDialogTwoButtonsOneTitle4;
        String string2 = getString(R.string.delete);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.delete)");
        bottomSheetDialogTwoButtonsOneTitle4.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.u8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.s0(ActivityEditProfile.this, view);
            }
        });
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle5 = this.T;
        if (bottomSheetDialogTwoButtonsOneTitle5 != null) {
            String string3 = getString(R.string.edit);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.edit)");
            bottomSheetDialogTwoButtonsOneTitle5.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.e7
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityEditProfile.t0(ActivityEditProfile.this, view);
                }
            });
        }
        BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle6 = this.T;
        Boolean valueOf2 = bottomSheetDialogTwoButtonsOneTitle6 != null ? Boolean.valueOf(bottomSheetDialogTwoButtonsOneTitle6.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if (valueOf2.booleanValue() || (bottomSheetDialogTwoButtonsOneTitle = this.T) == null) {
            return;
        }
        bottomSheetDialogTwoButtonsOneTitle.show();
    }

    public final void f1() {
        MyAccountViewModel myAccountViewModel = this.q;
        if (myAccountViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel = null;
        }
        final BottomSheetDialogProfileGender bottomSheetDialogProfileGender = new BottomSheetDialogProfileGender(this, String.valueOf(myAccountViewModel.getGender().getValue()));
        bottomSheetDialogProfileGender.setPositiveButton(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.e8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.g1(BottomSheetDialogProfileGender.this, this, view);
            }
        });
        bottomSheetDialogProfileGender.setNegativeButton(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.d8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.h1(BottomSheetDialogProfileGender.this, view);
            }
        });
        bottomSheetDialogProfileGender.setCancelable(false);
        bottomSheetDialogProfileGender.show();
    }

    @Nullable
    public final BottomSheetDialogImageTitleMessageTwoBtns getBottomSheetDialogImageTitleMessage() {
        return this.U;
    }

    @Nullable
    public final BottomSheetDialogTwoButtonsOneTitle getBottomSheetDialogTwoButtonsOneTitle() {
        return this.T;
    }

    public final int getDefault_value_height() {
        return this.H;
    }

    public final int getDefault_value_runStrideLength() {
        return this.J;
    }

    public final int getDefault_value_walkStrideLength() {
        return this.I;
    }

    public final int getDefault_value_weight() {
        return this.K;
    }

    @Override // com.coveiot.android.leonardo.more.ContractorMyAccount
    public void getDob() {
        MyAccountViewModel myAccountViewModel = this.q;
        if (myAccountViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel = null;
        }
        myAccountViewModel.setDateOfBirth(((EditText) _$_findCachedViewById(R.id.updateDateOfBirthEditText)).getText().toString());
    }

    @Override // com.coveiot.android.leonardo.more.ContractorMyAccount
    public void getEmail() {
        MyAccountViewModel myAccountViewModel = this.q;
        if (myAccountViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel = null;
        }
        myAccountViewModel.setEmail(((EditText) _$_findCachedViewById(R.id.updateEmailEditText)).getText().toString());
    }

    @Override // com.coveiot.android.leonardo.more.ContractorMyAccount
    public void getGender() {
        int i = R.id.updateGenderEditText;
        MyAccountViewModel myAccountViewModel = null;
        if (kotlin.text.m.equals(((EditText) _$_findCachedViewById(i)).getText().toString(), getResources().getString(R.string.gender_female), true)) {
            MyAccountViewModel myAccountViewModel2 = this.q;
            if (myAccountViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                myAccountViewModel = myAccountViewModel2;
            }
            String upperCase = AppConstants.FEMALE.getValue().toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
            myAccountViewModel.setGender(upperCase);
        } else if (kotlin.text.m.equals(((EditText) _$_findCachedViewById(i)).getText().toString(), getResources().getString(R.string.gender_male), true)) {
            MyAccountViewModel myAccountViewModel3 = this.q;
            if (myAccountViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                myAccountViewModel = myAccountViewModel3;
            }
            String upperCase2 = AppConstants.MALE.getValue().toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase()");
            myAccountViewModel.setGender(upperCase2);
        } else {
            MyAccountViewModel myAccountViewModel4 = this.q;
            if (myAccountViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                myAccountViewModel = myAccountViewModel4;
            }
            myAccountViewModel.setGender("");
        }
    }

    @Override // com.coveiot.android.leonardo.more.ContractorMyAccount
    public void getHeight() {
        MyAccountViewModel myAccountViewModel = this.q;
        if (myAccountViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel = null;
        }
        myAccountViewModel.setHeight(((EditText) _$_findCachedViewById(R.id.updateHeightEditText)).getText().toString());
    }

    @NotNull
    public final String getMCurrentPhotoPath() {
        String str = this.mCurrentPhotoPath;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mCurrentPhotoPath");
        return null;
    }

    @Override // com.coveiot.android.leonardo.more.ContractorMyAccount
    public void getName() {
        MyAccountViewModel myAccountViewModel = this.q;
        if (myAccountViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel = null;
        }
        myAccountViewModel.setName(((EditText) _$_findCachedViewById(R.id.updateNameEditText)).getText().toString());
    }

    @Override // com.coveiot.android.leonardo.more.ContractorMyAccount
    public void getPhoneNumber() {
        MyAccountViewModel myAccountViewModel = this.q;
        ProfileData profileData = null;
        if (myAccountViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel = null;
        }
        ProfileData profileData2 = this.p;
        if (profileData2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
        } else {
            profileData = profileData2;
        }
        String mobileNumber = profileData.getMobileNumber();
        Intrinsics.checkNotNullExpressionValue(mobileNumber, "mUserDetails.mobileNumber");
        myAccountViewModel.setMobileNumber(mobileNumber);
    }

    @Nullable
    public final Uri getPhotoURI$app_prodRelease() {
        return this.G;
    }

    @Override // com.coveiot.android.leonardo.more.ContractorMyAccount
    public void getRunStrideLength() {
        MyAccountViewModel myAccountViewModel = this.q;
        if (myAccountViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel = null;
        }
        myAccountViewModel.setRunStrideLength(((EditText) _$_findCachedViewById(R.id.updateRunStrideLength)).getText().toString());
    }

    @Override // com.coveiot.android.leonardo.more.ContractorMyAccount
    public void getWalkStrideLength() {
        MyAccountViewModel myAccountViewModel = this.q;
        if (myAccountViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel = null;
        }
        myAccountViewModel.setWalkStrideLength(((EditText) _$_findCachedViewById(R.id.updateWalkStrideLength)).getText().toString());
    }

    @Override // com.coveiot.android.leonardo.more.ContractorMyAccount
    public void getWeight() {
        MyAccountViewModel myAccountViewModel = this.q;
        if (myAccountViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel = null;
        }
        myAccountViewModel.setWeight(((EditText) _$_findCachedViewById(R.id.updateWeightEditText)).getText().toString());
    }

    public final void i1(String str, String str2) {
        final BottomSheetDialogProfileInfo bottomSheetDialogProfileInfo = new BottomSheetDialogProfileInfo(this, str, str2);
        bottomSheetDialogProfileInfo.setPositiveButton(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.f8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.j1(BottomSheetDialogProfileInfo.this, view);
            }
        });
        bottomSheetDialogProfileInfo.setCancelable(false);
        bottomSheetDialogProfileInfo.show();
    }

    public final void initClickListeners() {
        _$_findCachedViewById(R.id.tellUsInfo).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.t8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.N0(ActivityEditProfile.this, view);
            }
        });
        _$_findCachedViewById(R.id.WeWantToKnowInfo).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.k7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.O0(ActivityEditProfile.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivWalkStrideInfo)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.q7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.P0(ActivityEditProfile.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivRunStrideInfo)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.r7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.Q0(ActivityEditProfile.this, view);
            }
        });
        ((EditText) _$_findCachedViewById(R.id.updateEmailEditText)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.more.activities.ActivityEditProfile$initClickListeners$5
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
                Intrinsics.checkNotNull(editable);
                if (StringsKt__StringsKt.trim(editable).toString().length() > 0) {
                    if (Patterns.EMAIL_ADDRESS.matcher(editable).matches()) {
                        ((ConstraintLayout) ActivityEditProfile.this._$_findCachedViewById(R.id.updateEmailTextCardView)).setBackgroundResource(R.drawable.rounded_corners_grey_new);
                        ActivityEditProfile activityEditProfile = ActivityEditProfile.this;
                        TextView tvEmailError = (TextView) activityEditProfile._$_findCachedViewById(R.id.tvEmailError);
                        Intrinsics.checkNotNullExpressionValue(tvEmailError, "tvEmailError");
                        activityEditProfile.gone(tvEmailError);
                        WeeklyReportPrefData weeklyReportData = UserDataManager.getInstance(ActivityEditProfile.this).getWeeklyReportData();
                        if (weeklyReportData != null) {
                            weeklyReportData.isEmailVerified();
                            if (weeklyReportData.isEmailVerified()) {
                                weeklyReportData.isSubscribed();
                                if (weeklyReportData.isSubscribed()) {
                                    ((EditText) ActivityEditProfile.this._$_findCachedViewById(R.id.updateEmailEditText)).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.email_verify_new, 0);
                                }
                            }
                        }
                        ActivityEditProfile.this.o0();
                        ActivityEditProfile.this.p0();
                        return;
                    }
                    ((ConstraintLayout) ActivityEditProfile.this._$_findCachedViewById(R.id.updateEmailTextCardView)).setBackgroundResource(R.drawable.rounded_red_border_grey_background_10dp);
                    ActivityEditProfile activityEditProfile2 = ActivityEditProfile.this;
                    TextView tvEmailError2 = (TextView) activityEditProfile2._$_findCachedViewById(R.id.tvEmailError);
                    Intrinsics.checkNotNullExpressionValue(tvEmailError2, "tvEmailError");
                    activityEditProfile2.visible(tvEmailError2);
                    ((Button) ActivityEditProfile.this._$_findCachedViewById(R.id.btn_update_profile)).setEnabled(false);
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        ((EditText) _$_findCachedViewById(R.id.updateNameEditText)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.more.activities.ActivityEditProfile$initClickListeners$6
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
                ActivityEditProfile.this.o0();
                ActivityEditProfile.this.p0();
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.edit_image_email)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.w8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.R0(ActivityEditProfile.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.img_profile)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.f7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.z0(ActivityEditProfile.this, view);
            }
        });
        Button btn_update_profile = (Button) _$_findCachedViewById(R.id.btn_update_profile);
        Intrinsics.checkNotNullExpressionValue(btn_update_profile, "btn_update_profile");
        ViewUtilsKt.setSafeOnClickListener(btn_update_profile, new Function1<View, Unit>() { // from class: com.coveiot.android.leonardo.more.activities.ActivityEditProfile$initClickListeners$9
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull View it) {
                MyAccountViewModel myAccountViewModel;
                Intrinsics.checkNotNullParameter(it, "it");
                myAccountViewModel = ActivityEditProfile.this.q;
                if (myAccountViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    myAccountViewModel = null;
                }
                final ActivityEditProfile activityEditProfile = ActivityEditProfile.this;
                myAccountViewModel.onSubmitClicked(new FragmentFinalProfileViewModel.SubmitClickListner() { // from class: com.coveiot.android.leonardo.more.activities.ActivityEditProfile$initClickListeners$9.1
                    @Override // com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentFinalProfileViewModel.SubmitClickListner
                    public void onFailure(@NotNull FailureType failureType, @NotNull String message) {
                        Intrinsics.checkNotNullParameter(failureType, "failureType");
                        Intrinsics.checkNotNullParameter(message, "message");
                        ActivityEditProfile.this.showErrorDialog();
                    }

                    @Override // com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentFinalProfileViewModel.SubmitClickListner
                    public void proccedToNextScreen() {
                        WomenWellnessViewModel womenWellnessViewModel;
                        WomenWellnessViewModel womenWellnessViewModel2;
                        WomenWellnessViewModel womenWellnessViewModel3;
                        if (!ThemesUtils.INSTANCE.isPairDeviceLater(ActivityEditProfile.this)) {
                            womenWellnessViewModel = ActivityEditProfile.this.r;
                            WomenWellnessViewModel womenWellnessViewModel4 = null;
                            if (womenWellnessViewModel == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("womenWellnessViewModel");
                                womenWellnessViewModel = null;
                            }
                            WomenWellnessData womenWellnessDataFromPref = womenWellnessViewModel.getWomenWellnessDataFromPref();
                            ProfileData userDetails = SessionManager.getInstance(ActivityEditProfile.this).getUserDetails();
                            if (userDetails != null) {
                                if (userDetails.getGender() != null && kotlin.text.m.equals(userDetails.getGender(), "MALE", true)) {
                                    womenWellnessDataFromPref.setEnabled(false);
                                } else if (womenWellnessDataFromPref == null) {
                                    womenWellnessDataFromPref.setEnabled(true);
                                }
                            } else {
                                womenWellnessDataFromPref.setEnabled(false);
                            }
                            DeviceUtils.Companion companion = DeviceUtils.Companion;
                            if (!companion.isCADevice(ActivityEditProfile.this) && !companion.isCYDevice(ActivityEditProfile.this) && !companion.isPS1Device(ActivityEditProfile.this) && !companion.isBESDevice(ActivityEditProfile.this)) {
                                womenWellnessViewModel3 = ActivityEditProfile.this.r;
                                if (womenWellnessViewModel3 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessViewModel");
                                } else {
                                    womenWellnessViewModel4 = womenWellnessViewModel3;
                                }
                                womenWellnessViewModel4.sendWomenWellnessDataToBand(womenWellnessDataFromPref);
                            } else if (womenWellnessDataFromPref.getLastPeriodYear() != 0) {
                                womenWellnessViewModel2 = ActivityEditProfile.this.r;
                                if (womenWellnessViewModel2 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("womenWellnessViewModel");
                                } else {
                                    womenWellnessViewModel4 = womenWellnessViewModel2;
                                }
                                womenWellnessViewModel4.sendWomenWellnessDataToBand(womenWellnessDataFromPref);
                            }
                            AnalyticsLog analyticsLog = new AnalyticsLog();
                            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
                            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.EDIT_PROFILE_SCREEN.getValue());
                            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_BOTTOM_MENU_MORE_SCREEN.getValue());
                            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.SAVE_BUTTON.getValue());
                            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                            ActivityEditProfile.this.showSuccessDialog();
                            return;
                        }
                        ActivityEditProfile.this.showSuccessDialog();
                    }
                }, ThemesUtils.INSTANCE.isPairDeviceLater(ActivityEditProfile.this));
            }
        });
        ((EditText) _$_findCachedViewById(R.id.updateHeightEditText)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.t7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.A0(ActivityEditProfile.this, view);
            }
        });
        ((EditText) _$_findCachedViewById(R.id.updateWalkStrideLength)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.j7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.B0(ActivityEditProfile.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.updateWalkStrideLengthTextCardView)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.s8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.C0(ActivityEditProfile.this, view);
            }
        });
        ((EditText) _$_findCachedViewById(R.id.updateRunStrideLength)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.v8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.D0(ActivityEditProfile.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.updateRunStrideLengthTextCardView)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.p7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.E0(ActivityEditProfile.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.updateHeightTextCardView)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.d7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.F0(ActivityEditProfile.this, view);
            }
        });
        ((EditText) _$_findCachedViewById(R.id.updateWeightEditText)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.s7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.G0(ActivityEditProfile.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.updateWeightTextCardView)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.c7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.H0(ActivityEditProfile.this, view);
            }
        });
        ((EditText) _$_findCachedViewById(R.id.updateDateOfBirthEditText)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.l7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.I0(ActivityEditProfile.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.updateDateOfBirthTextCardView)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.n7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.J0(ActivityEditProfile.this, view);
            }
        });
        ((EditText) _$_findCachedViewById(R.id.updateGenderEditText)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.m7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.K0(ActivityEditProfile.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.updateGenderTextCardView)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.i7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.L0(ActivityEditProfile.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvContactChange)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.o7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.M0(ActivityEditProfile.this, view);
            }
        });
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.edit_my_profile));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.y7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.T0(ActivityEditProfile.this, view);
            }
        });
    }

    public final boolean isDataChanged() {
        String email;
        String gender;
        ProfileData userDetails = SessionManager.getInstance(this).getUserDetails();
        Intrinsics.checkNotNullExpressionValue(userDetails, "getInstance(this).userDetails");
        this.p = userDetails;
        ProfileData profileData = null;
        if (userDetails == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
            userDetails = null;
        }
        String dob = userDetails.getDob();
        if (dob != null) {
            List split$default = StringsKt__StringsKt.split$default((CharSequence) dob, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null);
            try {
                if (kotlin.text.m.endsWith$default((String) split$default.get(0), "1", false, 2, null) && !kotlin.text.m.endsWith$default((String) split$default.get(0), BleConst.GetDeviceVersion, false, 2, null)) {
                    dob = AppUtils.formatDate(AppUtils.parseDate(dob, "yyyy-MM-dd"), "d'st' MMM yyyy");
                } else if (kotlin.text.m.endsWith$default((String) split$default.get(0), "2", false, 2, null) && !kotlin.text.m.endsWith$default((String) split$default.get(0), BleConst.CMD_Reset, false, 2, null)) {
                    dob = AppUtils.formatDate(AppUtils.parseDate(dob, "yyyy-MM-dd"), "d'nd' MMM yyyy");
                } else if (kotlin.text.m.endsWith$default((String) split$default.get(0), "3", false, 2, null) && !kotlin.text.m.endsWith$default((String) split$default.get(0), BleConst.CMD_MCUReset, false, 2, null)) {
                    dob = AppUtils.formatDate(AppUtils.parseDate(dob, "yyyy-MM-dd"), "d'rd' MMM yyyy");
                } else {
                    dob = AppUtils.formatDate(AppUtils.parseDate(dob, "yyyy-MM-dd"), "d'th' MMM yyyy");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("isDataChanged: ");
            sb.append((Object) ((EditText) _$_findCachedViewById(R.id.updateNameEditText)).getText());
            sb.append(" || ");
            ProfileData profileData2 = this.p;
            if (profileData2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
                profileData2 = null;
            }
            sb.append(profileData2.getName());
            Log.d("nameCheck", sb.toString());
        }
        if (!y0()) {
            String obj = ((EditText) _$_findCachedViewById(R.id.updateEmailEditText)).getText().toString();
            ProfileData profileData3 = this.p;
            if (profileData3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
                profileData3 = null;
            }
            if (profileData3.getEmail() == null) {
                email = "";
            } else {
                ProfileData profileData4 = this.p;
                if (profileData4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
                    profileData4 = null;
                }
                email = profileData4.getEmail();
            }
            if (Intrinsics.areEqual(obj, email)) {
                String obj2 = ((EditText) _$_findCachedViewById(R.id.updateGenderEditText)).getText().toString();
                ProfileData profileData5 = this.p;
                if (profileData5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
                    profileData5 = null;
                }
                if (profileData5.getGender() == null) {
                    gender = "";
                } else {
                    ProfileData profileData6 = this.p;
                    if (profileData6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
                        profileData6 = null;
                    }
                    gender = profileData6.getGender();
                }
                if (kotlin.text.m.equals(obj2, gender, true)) {
                    String obj3 = ((EditText) _$_findCachedViewById(R.id.updateDateOfBirthEditText)).getText().toString();
                    if (dob == null) {
                        dob = "";
                    }
                    if (Intrinsics.areEqual(obj3, dob)) {
                        String obj4 = ((EditText) _$_findCachedViewById(R.id.updateHeightEditText)).getText().toString();
                        ProfileData profileData7 = this.p;
                        if (profileData7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
                            profileData7 = null;
                        }
                        if (Intrinsics.areEqual(obj4, profileData7.getHeight())) {
                            String obj5 = ((EditText) _$_findCachedViewById(R.id.updateWeightEditText)).getText().toString();
                            ProfileData profileData8 = this.p;
                            if (profileData8 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
                            } else {
                                profileData = profileData8;
                            }
                            if (Intrinsics.areEqual(obj5, profileData.getWeight())) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public final void k1(Dialog dialog) {
        MyAccountViewModel myAccountViewModel = this.q;
        MyAccountViewModel myAccountViewModel2 = null;
        if (myAccountViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel = null;
        }
        myAccountViewModel.populateDaysDataInView();
        int i = R.id.number_picker_date;
        WheelPicker wheelPicker = (WheelPicker) dialog.findViewById(i);
        MyAccountViewModel myAccountViewModel3 = this.q;
        if (myAccountViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel3 = null;
        }
        wheelPicker.setData(myAccountViewModel3.getDays());
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(3);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        WheelPicker wheelPicker2 = (WheelPicker) dialog.findViewById(i);
        MyAccountViewModel myAccountViewModel4 = this.q;
        if (myAccountViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel4 = null;
        }
        wheelPicker2.setSelectedItemPosition(myAccountViewModel4.getMDayItemPosition());
        ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        MyAccountViewModel myAccountViewModel5 = this.q;
        if (myAccountViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            myAccountViewModel2 = myAccountViewModel5;
        }
        myAccountViewModel2.setDate_var("1");
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.activities.l8
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker3, Object obj, int i2) {
                ActivityEditProfile.l1(ActivityEditProfile.this, wheelPicker3, obj, i2);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.coveiot.android.theme.GenericMessageDialog, T] */
    public final void l0() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        String string = getString(R.string.confirmation);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
        String string2 = getString(R.string.modify_phone_desc);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.modify_phone_desc)");
        ?? genericMessageDialog = new GenericMessageDialog(this, string, string2);
        objectRef.element = genericMessageDialog;
        ((GenericMessageDialog) genericMessageDialog).setCanceledOnTouchOutside(false);
        ((GenericMessageDialog) objectRef.element).setCancelable(false);
        ((GenericMessageDialog) objectRef.element).show();
        ((TextView) ((GenericMessageDialog) objectRef.element).findViewById(R.id.proceed)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.z7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.m0(ActivityEditProfile.this, objectRef, view);
            }
        });
        ((TextView) ((GenericMessageDialog) objectRef.element).findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.x7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.n0(ActivityEditProfile.this, objectRef, view);
            }
        });
    }

    public final void m1(final Dialog dialog) {
        MyAccountViewModel myAccountViewModel = this.q;
        MyAccountViewModel myAccountViewModel2 = null;
        if (myAccountViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel = null;
        }
        myAccountViewModel.populateMonthsDataInView();
        int i = R.id.number_picker_month;
        WheelPicker wheelPicker = (WheelPicker) dialog.findViewById(i);
        MyAccountViewModel myAccountViewModel3 = this.q;
        if (myAccountViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel3 = null;
        }
        wheelPicker.setData(myAccountViewModel3.getMonths());
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(3);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        WheelPicker wheelPicker2 = (WheelPicker) dialog.findViewById(i);
        MyAccountViewModel myAccountViewModel4 = this.q;
        if (myAccountViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            myAccountViewModel2 = myAccountViewModel4;
        }
        wheelPicker2.setSelectedItemPosition(myAccountViewModel2.getMMonthSelectedPosition());
        ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.activities.n8
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker3, Object obj, int i2) {
                ActivityEditProfile.n1(ActivityEditProfile.this, dialog, wheelPicker3, obj, i2);
            }
        });
    }

    public final void o0() {
        ((Button) _$_findCachedViewById(R.id.btn_update_profile)).setEnabled(isDataChanged() || this.R);
    }

    public final void o1(final Dialog dialog) {
        MyAccountViewModel myAccountViewModel = this.q;
        MyAccountViewModel myAccountViewModel2 = null;
        if (myAccountViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel = null;
        }
        myAccountViewModel.populateYearsDataInView();
        int i = R.id.number_picker_year;
        WheelPicker wheelPicker = (WheelPicker) dialog.findViewById(i);
        MyAccountViewModel myAccountViewModel3 = this.q;
        if (myAccountViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel3 = null;
        }
        wheelPicker.setData(myAccountViewModel3.getYears());
        ((WheelPicker) dialog.findViewById(i)).setAtmospheric(true);
        ((WheelPicker) dialog.findViewById(i)).setVisibleItemCount(3);
        ((WheelPicker) dialog.findViewById(i)).setItemSpace(30);
        WheelPicker wheelPicker2 = (WheelPicker) dialog.findViewById(i);
        MyAccountViewModel myAccountViewModel4 = this.q;
        if (myAccountViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            myAccountViewModel2 = myAccountViewModel4;
        }
        wheelPicker2.setSelectedItemPosition(myAccountViewModel2.getMYearSelectedPosition());
        ((WheelPicker) dialog.findViewById(i)).setCyclic(true);
        ((WheelPicker) dialog.findViewById(i)).setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() { // from class: com.coveiot.android.leonardo.more.activities.m8
            @Override // com.aigestudio.wheelpicker.WheelPicker.OnItemSelectedListener
            public final void onItemSelected(WheelPicker wheelPicker3, Object obj, int i2) {
                ActivityEditProfile.p1(ActivityEditProfile.this, dialog, wheelPicker3, obj, i2);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        MyAccountViewModel myAccountViewModel = null;
        InputStream openInputStream = null;
        MyAccountViewModel myAccountViewModel2 = null;
        MyAccountViewModel myAccountViewModel3 = null;
        if (i == this.u) {
            if (intent == null || intent.getData() == null) {
                return;
            }
            Uri data = intent.getData();
            if (data != null) {
                try {
                    openInputStream = getContentResolver().openInputStream(data);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream);
            if (decodeStream != null) {
                decodeStream.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
                startActivityForResult(CropImage.activity(data).setCropShape(CropImageView.CropShape.OVAL).setAspectRatio(1, 1).getIntent(this), 203);
                return;
            }
            Toast.makeText(this, getString(R.string.file_format_not_supported), 0).show();
        } else if (i == this.v) {
            Uri uri = this.G;
            if (uri == null || i2 != -1) {
                return;
            }
            startActivityForResult(CropImage.activity(uri).setCropShape(CropImageView.CropShape.OVAL).setAspectRatio(1, 1).getIntent(this), 203);
        } else if (i == 203) {
            CropImage.ActivityResult activityResult = CropImage.getActivityResult(intent);
            if (i2 != -1) {
                if (i2 != 204) {
                    return;
                }
                activityResult.getError();
                return;
            }
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), activityResult.getUri());
                this.R = true;
                ((ImageView) _$_findCachedViewById(R.id.img_profile)).setImageBitmap(CircularImageView.getCircleBitmap(bitmap));
                this.s = CircularImageView.saveImageToInternal(CircularImageView.getCircleBitmap(bitmap), this);
                MyAccountViewModel myAccountViewModel4 = this.q;
                if (myAccountViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    myAccountViewModel2 = myAccountViewModel4;
                }
                myAccountViewModel2.setProfilePic(new File(this.s));
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.o8
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityEditProfile.W0(ActivityEditProfile.this);
                    }
                }, 1000L);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } else if (i == this.D) {
            Boolean valueOf = intent != null ? Boolean.valueOf(intent.getBooleanExtra(this.w, false)) : null;
            Intrinsics.checkNotNull(valueOf);
            boolean booleanValue = valueOf.booleanValue();
            final String stringExtra = intent.getStringExtra(this.x);
            if (stringExtra == null) {
                o0();
            } else if (booleanValue) {
                this.O = stringExtra;
                ((EditText) _$_findCachedViewById(R.id.updateWalkStrideLength)).setText(stringExtra + ' ' + getString(R.string.cms));
                MyAccountViewModel myAccountViewModel5 = this.q;
                if (myAccountViewModel5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    myAccountViewModel3 = myAccountViewModel5;
                }
                this.L = !Intrinsics.areEqual(stringExtra, myAccountViewModel3.getWalkStrideLength());
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.q8
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityEditProfile.X0(stringExtra, this);
                    }
                }, 1000L);
            } else {
                this.Q = stringExtra;
                ((EditText) _$_findCachedViewById(R.id.updateRunStrideLength)).setText(stringExtra + ' ' + getString(R.string.cms));
                MyAccountViewModel myAccountViewModel6 = this.q;
                if (myAccountViewModel6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    myAccountViewModel = myAccountViewModel6;
                }
                this.M = !Intrinsics.areEqual(stringExtra, myAccountViewModel.getRunStrideLength());
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.p8
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityEditProfile.Y0(stringExtra, this);
                    }
                }, 1000L);
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (!isDataChanged() && !this.M && !this.L && !this.R) {
            super.onBackPressed();
            return;
        }
        String string = getString(R.string.confirmation);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
        String string2 = getString(R.string.save_changes);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
        String string3 = getString(R.string.save_changes_btn);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
        bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.v7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.Z0(ActivityEditProfile.this, bottomSheetDialogTwoButtons, view);
            }
        });
        String string4 = getString(R.string.discard);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
        bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.i8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.a1(BottomSheetDialogTwoButtons.this, this, view);
            }
        });
        bottomSheetDialogTwoButtons.show();
    }

    @Override // com.coveiot.android.leonardo.listener.OnInternetBleCheckListener
    public void onCheck(@NotNull String title, @NotNull String message, boolean z) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        sendValueToWorkoutJs(false, z ? this.y : this.z);
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, title, message);
        String string = getResources().getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.c8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.b1(BottomSheetDialogOneButtonTitleMessage.this, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_edit_profile_new);
        if (getIntent().getExtras() != null) {
            this.S = getIntent().getBooleanExtra("fromFitnessPlan", false);
        }
        Log.d("planCheck", "fromFitnessPlan: " + this.S);
        initToolbar();
        this.q = (MyAccountViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(MyAccountViewModel.class);
        this.r = (WomenWellnessViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(WomenWellnessViewModel.class);
        ViewModelActivityDashboardNew viewModelActivityDashboardNew = (ViewModelActivityDashboardNew) ViewModelProviders.of(this).get(ViewModelActivityDashboardNew.class);
        MyAccountViewModel myAccountViewModel = this.q;
        MyAccountViewModel myAccountViewModel2 = null;
        if (myAccountViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel = null;
        }
        myAccountViewModel.setContractInterface(this);
        MyAccountViewModel myAccountViewModel3 = this.q;
        if (myAccountViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel3 = null;
        }
        myAccountViewModel3.setOnInternetBleCheckListener(this);
        MyAccountViewModel myAccountViewModel4 = this.q;
        if (myAccountViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            myAccountViewModel2 = myAccountViewModel4;
        }
        myAccountViewModel2.setOnSuccessListener(this);
        S0();
        U0();
        w0();
        initClickListeners();
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.EDIT_PROFILE_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onDismiss() {
        dismissProgress();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.GRANT_PERMISSION_DIALOG.getValue());
        analyticsLog.setAppPermissionId("Manifest.permission.CAMERA");
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        int i2 = this.t;
        if (i2 == 350) {
            if (!(grantResults.length == 0)) {
                choosePhotoFromGallary();
            }
        } else if (i2 != 351) {
            super.onRequestPermissionsResult(i, permissions, grantResults);
        } else {
            if (!(grantResults.length == 0)) {
                String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(this, new String[]{"android.permission.CAMERA"});
                Intrinsics.checkNotNullExpressionValue(checkPermissionsHasGranted, "checkPermissionsHasGrant…                        )");
                if (!(checkPermissionsHasGranted.length == 0)) {
                    return;
                }
                F1();
            }
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        ProfileData userDetails = SessionManager.getInstance(this).getUserDetails();
        Intrinsics.checkNotNullExpressionValue(userDetails, "getInstance(this@ActivityEditProfile).userDetails");
        this.p = userDetails;
        ProfileData profileData = null;
        if (userDetails == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
            userDetails = null;
        }
        if (userDetails.getEmail() != null) {
            int i = R.id.updateEmailEditText;
            Editable text = ((EditText) _$_findCachedViewById(i)).getText();
            Intrinsics.checkNotNullExpressionValue(text, "updateEmailEditText.text");
            if (text.length() > 0) {
                String obj = ((EditText) _$_findCachedViewById(i)).getText().toString();
                ProfileData profileData2 = this.p;
                if (profileData2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
                    profileData2 = null;
                }
                if (!obj.equals(profileData2.getEmail())) {
                    EditText editText = (EditText) _$_findCachedViewById(i);
                    ProfileData profileData3 = this.p;
                    if (profileData3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
                        profileData3 = null;
                    }
                    editText.setText(profileData3.getEmail());
                }
            }
        }
        if (!ThemesUtils.INSTANCE.isPairDeviceLater(this)) {
            if (BleApiManager.getInstance(this) != null && BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                int i2 = R.id.edit_profile_scrollView;
                ((ScrollView) _$_findCachedViewById(i2)).setAlpha(1.0f);
                ((ScrollView) _$_findCachedViewById(i2)).setEnabled(true);
            } else {
                int i3 = R.id.edit_profile_scrollView;
                ((ScrollView) _$_findCachedViewById(i3)).setAlpha(0.3f);
                ((ScrollView) _$_findCachedViewById(i3)).setEnabled(false);
            }
        }
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvPhoneNumber);
        ProfileData profileData4 = this.p;
        if (profileData4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
        } else {
            profileData = profileData4;
        }
        textView.setText(profileData.getMobileNumber());
        o0();
        p0();
        super.onResume();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onShowProgressDialog() {
        BaseActivity.showProgressWithTitle$default(this, null, 1, null);
    }

    @Override // com.coveiot.android.leonardo.listener.OnSuccessListener
    public void onSuccess(boolean z) {
        dismissProgress();
        BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns = this.U;
        if (bottomSheetDialogImageTitleMessageTwoBtns != null) {
            bottomSheetDialogImageTitleMessageTwoBtns.dismiss();
        }
        if (z) {
            ((EditText) _$_findCachedViewById(R.id.updateEmailEditText)).setText("");
            Toast.makeText(this, getResources().getString(R.string.unsubscribe_success), 1).show();
            return;
        }
        Toast.makeText(this, getResources().getString(R.string.some_thing_went_wrong), 1).show();
    }

    @Override // com.coveiot.android.leonardo.onboarding.profile.listeners.UpdateDobListener
    public void onUpdate(@NotNull String dob) {
        Intrinsics.checkNotNullParameter(dob, "dob");
        ((Button) _$_findCachedViewById(R.id.btn_update_profile)).setEnabled(isDataChanged());
        ((EditText) _$_findCachedViewById(R.id.updateDateOfBirthEditText)).setText(dob);
        p0();
    }

    @Override // android.app.Activity
    public void onUserInteraction() {
        super.onUserInteraction();
        if (ThemesUtils.INSTANCE.isPairDeviceLater(this)) {
            return;
        }
        if (BleApiManager.getInstance(this) != null && BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.DISCONNECTED) {
            v0(false);
        } else {
            v0(true);
        }
    }

    @Override // com.coveiot.android.leonardo.more.ContractorMyAccount
    public void onValidationFailed(@NotNull FailureType failureType, @NotNull String message) {
        Intrinsics.checkNotNullParameter(failureType, "failureType");
        Intrinsics.checkNotNullParameter(message, "message");
        Toast.makeText(this, message, 0).show();
    }

    public final void p0() {
        if (isDataChanged()) {
            return;
        }
        ((Button) _$_findCachedViewById(R.id.btn_update_profile)).setEnabled(this.M || this.L);
    }

    public final void q1() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%.2f", Arrays.copyOf(new Object[]{Double.valueOf(this.K / Math.pow(this.H / 100, 2.0d))}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        ((TextView) _$_findCachedViewById(R.id.tvBmiValue)).setText(format);
    }

    public final void s1() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String string = getString(R.string.hundred_percent_updated);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.hundred_percent_updated)");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string2 = getString(R.string.your_profile_is);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.your_profile_is)");
        String format = String.format(string2, Arrays.copyOf(new Object[]{string}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        SpannableString spannableString = new SpannableString(format);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#03c28a")), StringsKt__StringsKt.indexOf$default((CharSequence) format, string, 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) format, string, 0, false, 6, (Object) null) + string.length(), 33);
        spannableString.setSpan(new TextAppearanceSpan(this, R.style.bold), StringsKt__StringsKt.indexOf$default((CharSequence) format, string, 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) format, string, 0, false, 6, (Object) null) + string.length(), 18);
        spannableStringBuilder.append((CharSequence) spannableString);
        ((TextView) _$_findCachedViewById(R.id.tvYourProfile)).setText(spannableStringBuilder);
        ScrollView edit_profile_scrollView = (ScrollView) _$_findCachedViewById(R.id.edit_profile_scrollView);
        Intrinsics.checkNotNullExpressionValue(edit_profile_scrollView, "edit_profile_scrollView");
        gone(edit_profile_scrollView);
        Button btn_update_profile = (Button) _$_findCachedViewById(R.id.btn_update_profile);
        Intrinsics.checkNotNullExpressionValue(btn_update_profile, "btn_update_profile");
        gone(btn_update_profile);
        ConstraintLayout clCongratulations = (ConstraintLayout) _$_findCachedViewById(R.id.clCongratulations);
        Intrinsics.checkNotNullExpressionValue(clCongratulations, "clCongratulations");
        visible(clCongratulations);
        View findViewById = findViewById(R.id.toolbar_title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.toolbar_title)");
        gone(findViewById);
        View findViewById2 = findViewById(R.id.toolbar_back_arrow);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById<TextView>(R.id.toolbar_back_arrow)");
        gone(findViewById2);
        ((Button) _$_findCachedViewById(R.id.btnBackToProfile)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.g7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.t1(ActivityEditProfile.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btnClose)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.j8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.u1(ActivityEditProfile.this, view);
            }
        });
    }

    public final void sendValueToWorkoutJs(boolean z, @NotNull String info) {
        Intrinsics.checkNotNullParameter(info, "info");
        Intent intent = new Intent();
        intent.putExtra(this.A, z);
        intent.putExtra(this.B, info);
        setResult(this.C, intent);
        finish();
    }

    public final void setBottomSheetDialogImageTitleMessage(@Nullable BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns) {
        this.U = bottomSheetDialogImageTitleMessageTwoBtns;
    }

    public final void setBottomSheetDialogTwoButtonsOneTitle(@Nullable BottomSheetDialogTwoButtonsOneTitle bottomSheetDialogTwoButtonsOneTitle) {
        this.T = bottomSheetDialogTwoButtonsOneTitle;
    }

    public final void setDefault_value_height(int i) {
        this.H = i;
    }

    public final void setDefault_value_runStrideLength(int i) {
        this.J = i;
    }

    public final void setDefault_value_walkStrideLength(int i) {
        this.I = i;
    }

    public final void setDefault_value_weight(int i) {
        this.K = i;
    }

    public final void setMCurrentPhotoPath(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mCurrentPhotoPath = str;
    }

    public final void setPhotoURI$app_prodRelease(@Nullable Uri uri) {
        this.G = uri;
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showErrorDialog() {
        onDismiss();
        String string = getString(!AppUtils.isNetConnected(this) ? R.string.noconnection : R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string, "if (!AppUtils.isNetConne…uldnot_save\n            )");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.b8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.r1(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showSuccessDialog() {
        onDismiss();
        String string = getString(R.string.user_profile_save);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.user_profile_save)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n            R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.a8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.C1(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final File u0() throws IOException {
        String formatDate = AppUtils.formatDate(new Date(), "yyyyMMdd_HHmmss");
        File image = File.createTempFile("JPEG_" + formatDate + '_', ".jpg", getExternalFilesDir(Environment.DIRECTORY_PICTURES));
        String absolutePath = image.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "image.absolutePath");
        setMCurrentPhotoPath(absolutePath);
        Intrinsics.checkNotNullExpressionValue(image, "image");
        return image;
    }

    public final void v0(boolean z) {
        ((ScrollView) _$_findCachedViewById(R.id.edit_profile_scrollView)).setEnabled(z);
        ((EditText) _$_findCachedViewById(R.id.updateNameEditText)).setEnabled(z);
        ((EditText) _$_findCachedViewById(R.id.updateEmailEditText)).setEnabled(z);
        ((TextView) _$_findCachedViewById(R.id.tvContactChange)).setEnabled(z);
        ((EditText) _$_findCachedViewById(R.id.updateGenderEditText)).setEnabled(z);
        ((EditText) _$_findCachedViewById(R.id.updateDateOfBirthEditText)).setEnabled(z);
        ((EditText) _$_findCachedViewById(R.id.updateHeightEditText)).setEnabled(z);
        ((EditText) _$_findCachedViewById(R.id.updateWeightEditText)).setEnabled(z);
        ((EditText) _$_findCachedViewById(R.id.updateWalkStrideLength)).setEnabled(z);
        ((EditText) _$_findCachedViewById(R.id.updateRunStrideLength)).setEnabled(z);
        ((ImageView) _$_findCachedViewById(R.id.img_profile)).setEnabled(z);
    }

    public final void v1() {
        PickerDialog.Companion companion = PickerDialog.Companion;
        String string = getString(R.string.height);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.height)");
        String string2 = getString(R.string.cms);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.cms)");
        companion.dataPickerWeight(this, string, string2, Integer.parseInt(AppConstants.HEIGHT_START_RANGE.getValue()), Integer.parseInt(AppConstants.HEIGHT_END_RANGE.getValue()), 1, this.H, new PickerDialog.Companion.SelectionListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityEditProfile$showHeightPickerDialog$1
            @Override // com.coveiot.android.theme.PickerDialog.Companion.SelectionListener
            public void onValueSelected(@NotNull String value) {
                MyAccountViewModel myAccountViewModel;
                Intrinsics.checkNotNullParameter(value, "value");
                ActivityEditProfile.this.setDefault_value_height(Integer.parseInt(value));
                myAccountViewModel = ActivityEditProfile.this.q;
                if (myAccountViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    myAccountViewModel = null;
                }
                myAccountViewModel.setHeight(value);
                ActivityEditProfile.this.q1();
            }
        });
    }

    public final void w0() {
        WeeklyReportPrefData weeklyReportData;
        String formatDate;
        ProfileData userDetails = SessionManager.getInstance(this).getUserDetails();
        Intrinsics.checkNotNullExpressionValue(userDetails, "getInstance(this).userDetails");
        this.p = userDetails;
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (!companion.isCZDevice(this) && !companion.isCADevice(this) && !companion.isCYDevice(this) && !companion.isPS1Device(this) && !companion.isBESDevice(this)) {
            ConstraintLayout updateWalkStrideLengthTextCardView = (ConstraintLayout) _$_findCachedViewById(R.id.updateWalkStrideLengthTextCardView);
            Intrinsics.checkNotNullExpressionValue(updateWalkStrideLengthTextCardView, "updateWalkStrideLengthTextCardView");
            gone(updateWalkStrideLengthTextCardView);
            TextView tvMyStride = (TextView) _$_findCachedViewById(R.id.tvMyStride);
            Intrinsics.checkNotNullExpressionValue(tvMyStride, "tvMyStride");
            gone(tvMyStride);
            ConstraintLayout updateRunStrideLengthTextCardView = (ConstraintLayout) _$_findCachedViewById(R.id.updateRunStrideLengthTextCardView);
            Intrinsics.checkNotNullExpressionValue(updateRunStrideLengthTextCardView, "updateRunStrideLengthTextCardView");
            gone(updateRunStrideLengthTextCardView);
            TextView tvMyRunStride = (TextView) _$_findCachedViewById(R.id.tvMyRunStride);
            Intrinsics.checkNotNullExpressionValue(tvMyRunStride, "tvMyRunStride");
            gone(tvMyRunStride);
        } else {
            ConstraintLayout updateWalkStrideLengthTextCardView2 = (ConstraintLayout) _$_findCachedViewById(R.id.updateWalkStrideLengthTextCardView);
            Intrinsics.checkNotNullExpressionValue(updateWalkStrideLengthTextCardView2, "updateWalkStrideLengthTextCardView");
            visible(updateWalkStrideLengthTextCardView2);
            TextView tvMyStride2 = (TextView) _$_findCachedViewById(R.id.tvMyStride);
            Intrinsics.checkNotNullExpressionValue(tvMyStride2, "tvMyStride");
            visible(tvMyStride2);
            ConstraintLayout updateRunStrideLengthTextCardView2 = (ConstraintLayout) _$_findCachedViewById(R.id.updateRunStrideLengthTextCardView);
            Intrinsics.checkNotNullExpressionValue(updateRunStrideLengthTextCardView2, "updateRunStrideLengthTextCardView");
            visible(updateRunStrideLengthTextCardView2);
            TextView tvMyRunStride2 = (TextView) _$_findCachedViewById(R.id.tvMyRunStride);
            Intrinsics.checkNotNullExpressionValue(tvMyRunStride2, "tvMyRunStride");
            visible(tvMyRunStride2);
        }
        ProfileData profileData = this.p;
        ProfileData profileData2 = null;
        if (profileData == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
            profileData = null;
        }
        GlideUtils.loadImage(this, profileData.getDpUrl(), new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.leonardo.more.activities.ActivityEditProfile$fillExistingUserData$1
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                AppConstants appConstants;
                MyAccountViewModel myAccountViewModel;
                File file;
                Intrinsics.checkNotNullParameter(resource, "resource");
                ((ImageView) ActivityEditProfile.this._$_findCachedViewById(R.id.img_profile)).setImageBitmap(AppUtils.getCircleBitmap(resource));
                FileOutputStream openFileOutput = ActivityEditProfile.this.openFileOutput(AppConstants.PROFILE_IMAGE_FILENAME.getValue(), 0);
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                resource.compress(Bitmap.CompressFormat.JPEG, 70, openFileOutput);
                resource.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
                openFileOutput.flush();
                openFileOutput.close();
                ActivityEditProfile.this.F = new File(ActivityEditProfile.this.getFilesDir().toString() + '/' + appConstants.getValue());
                myAccountViewModel = ActivityEditProfile.this.q;
                File file2 = null;
                if (myAccountViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    myAccountViewModel = null;
                }
                file = ActivityEditProfile.this.F;
                if (file == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mProfilePicFile");
                } else {
                    file2 = file;
                }
                myAccountViewModel.setProfilePic(file2);
            }
        });
        ProfileData profileData3 = this.p;
        if (profileData3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
            profileData3 = null;
        }
        String name = profileData3.getName();
        Intrinsics.checkNotNullExpressionValue(name, "mUserDetails.name");
        String lowerCase = name.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        if (Intrinsics.areEqual(lowerCase, getString(R.string.boathead))) {
            ((EditText) _$_findCachedViewById(R.id.updateNameEditText)).setText("");
            ((Button) _$_findCachedViewById(R.id.btn_update_profile)).setEnabled(false);
        } else {
            EditText editText = (EditText) _$_findCachedViewById(R.id.updateNameEditText);
            ProfileData profileData4 = this.p;
            if (profileData4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
                profileData4 = null;
            }
            editText.setText(profileData4.getName());
        }
        EditText editText2 = (EditText) _$_findCachedViewById(R.id.updateEmailEditText);
        ProfileData profileData5 = this.p;
        if (profileData5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
            profileData5 = null;
        }
        editText2.setText(profileData5.getEmail());
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvPhoneNumber);
        ProfileData profileData6 = this.p;
        if (profileData6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
            profileData6 = null;
        }
        textView.setText(profileData6.getMobileNumber());
        if (ProfileRepository.getInstance().getLatestProfileData(this) != null) {
            int i = ProfileRepository.getInstance().getLatestProfileData(this).walkStrideLength;
            int i2 = ProfileRepository.getInstance().getLatestProfileData(this).runStrideLength;
            if (i > 0) {
                ((EditText) _$_findCachedViewById(R.id.updateWalkStrideLength)).setText(i + ' ' + getString(R.string.cms));
                this.I = i;
                MyAccountViewModel myAccountViewModel = this.q;
                if (myAccountViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    myAccountViewModel = null;
                }
                myAccountViewModel.setWalkStrideLength(String.valueOf(i));
            } else {
                ProfileData profileData7 = this.p;
                if (profileData7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
                    profileData7 = null;
                }
                String height = profileData7.getHeight();
                Intrinsics.checkNotNullExpressionValue(height, "mUserDetails.height");
                this.I = AppUtils.caluclateWalkingStrideLenght(Integer.parseInt(height));
                ((EditText) _$_findCachedViewById(R.id.updateWalkStrideLength)).setText(this.I + ' ' + getString(R.string.cms));
            }
            if (i2 > 0) {
                ((EditText) _$_findCachedViewById(R.id.updateRunStrideLength)).setText(i2 + ' ' + getString(R.string.cms));
                this.J = i2;
                MyAccountViewModel myAccountViewModel2 = this.q;
                if (myAccountViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    myAccountViewModel2 = null;
                }
                myAccountViewModel2.setRunStrideLength(String.valueOf(i2));
            } else {
                ProfileData profileData8 = this.p;
                if (profileData8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
                    profileData8 = null;
                }
                String height2 = profileData8.getHeight();
                Intrinsics.checkNotNullExpressionValue(height2, "mUserDetails.height");
                this.J = AppUtils.caluclateRunningStrideLenght(Integer.parseInt(height2));
                ((EditText) _$_findCachedViewById(R.id.updateRunStrideLength)).setText(this.J + ' ' + getString(R.string.cms));
                if (companion.isCZDevice(this) || companion.isCADevice(this) || companion.isCYDevice(this) || companion.isPS1Device(this) || companion.isBESDevice(this)) {
                    MyAccountViewModel myAccountViewModel3 = this.q;
                    if (myAccountViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        myAccountViewModel3 = null;
                    }
                    myAccountViewModel3.setRunStrideLength(String.valueOf(this.J));
                }
            }
        } else {
            ProfileData profileData9 = this.p;
            if (profileData9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
                profileData9 = null;
            }
            String height3 = profileData9.getHeight();
            Intrinsics.checkNotNullExpressionValue(height3, "mUserDetails.height");
            this.I = AppUtils.caluclateWalkingStrideLenght(Integer.parseInt(height3));
            ((EditText) _$_findCachedViewById(R.id.updateWalkStrideLength)).setText(this.I + ' ' + getString(R.string.cms));
            ProfileData profileData10 = this.p;
            if (profileData10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
                profileData10 = null;
            }
            String height4 = profileData10.getHeight();
            Intrinsics.checkNotNullExpressionValue(height4, "mUserDetails.height");
            this.J = AppUtils.caluclateRunningStrideLenght(Integer.parseInt(height4));
            ((EditText) _$_findCachedViewById(R.id.updateRunStrideLength)).setText(this.J + ' ' + getString(R.string.cms));
            if (companion.isCZDevice(this) || companion.isCADevice(this) || companion.isCYDevice(this) || companion.isPS1Device(this) || companion.isBESDevice(this)) {
                MyAccountViewModel myAccountViewModel4 = this.q;
                if (myAccountViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    myAccountViewModel4 = null;
                }
                myAccountViewModel4.setRunStrideLength(String.valueOf(this.J));
            }
        }
        ProfileData profileData11 = this.p;
        if (profileData11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
            profileData11 = null;
        }
        if (kotlin.text.m.equals(profileData11.getGender(), AppConstants.MALE.getValue(), true)) {
            ((EditText) _$_findCachedViewById(R.id.updateGenderEditText)).setText(getResources().getString(R.string.gender_male));
        } else {
            ProfileData profileData12 = this.p;
            if (profileData12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
                profileData12 = null;
            }
            if (kotlin.text.m.equals(profileData12.getGender(), AppConstants.FEMALE.getValue(), true)) {
                ((EditText) _$_findCachedViewById(R.id.updateGenderEditText)).setText(getResources().getString(R.string.gender_female));
            }
        }
        ProfileData profileData13 = this.p;
        if (profileData13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
            profileData13 = null;
        }
        String dob = profileData13.getDob();
        if (dob != null) {
            List split$default = StringsKt__StringsKt.split$default((CharSequence) dob, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null);
            try {
                if (kotlin.text.m.endsWith$default((String) split$default.get(0), "1", false, 2, null) && !kotlin.text.m.endsWith$default((String) split$default.get(0), BleConst.GetDeviceVersion, false, 2, null)) {
                    formatDate = AppUtils.formatDate(AppUtils.parseDate(dob, "yyyy-MM-dd"), "d'st' MMM yyyy");
                } else if (kotlin.text.m.endsWith$default((String) split$default.get(0), "2", false, 2, null) && !kotlin.text.m.endsWith$default((String) split$default.get(0), BleConst.CMD_Reset, false, 2, null)) {
                    formatDate = AppUtils.formatDate(AppUtils.parseDate(dob, "yyyy-MM-dd"), "d'nd' MMM yyyy");
                } else if (kotlin.text.m.endsWith$default((String) split$default.get(0), "3", false, 2, null) && !kotlin.text.m.endsWith$default((String) split$default.get(0), BleConst.CMD_MCUReset, false, 2, null)) {
                    formatDate = AppUtils.formatDate(AppUtils.parseDate(dob, "yyyy-MM-dd"), "d'rd' MMM yyyy");
                } else {
                    formatDate = AppUtils.formatDate(AppUtils.parseDate(dob, "yyyy-MM-dd"), "d'th' MMM yyyy");
                }
                ((EditText) _$_findCachedViewById(R.id.updateDateOfBirthEditText)).setText(formatDate);
                Calendar calendar = Calendar.getInstance();
                ProfileData profileData14 = this.p;
                if (profileData14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
                    profileData14 = null;
                }
                calendar.setTime(AppUtils.parseDate(profileData14.getDob(), "yyyy-MM-dd"));
                MyAccountViewModel myAccountViewModel5 = this.q;
                if (myAccountViewModel5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    myAccountViewModel5 = null;
                }
                MutableLiveData<HeightWeightDob> heightWeightDob = myAccountViewModel5.getHeightWeightDob();
                Intrinsics.checkNotNull(heightWeightDob);
                HeightWeightDob value = heightWeightDob.getValue();
                Intrinsics.checkNotNull(value);
                value.setDateOfBirth(calendar);
                MyAccountViewModel myAccountViewModel6 = this.q;
                if (myAccountViewModel6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    myAccountViewModel6 = null;
                }
                MutableLiveData<HeightWeightDob> heightWeightDob2 = myAccountViewModel6.getHeightWeightDob();
                Intrinsics.checkNotNull(heightWeightDob2);
                HeightWeightDob value2 = heightWeightDob2.getValue();
                Intrinsics.checkNotNull(value2);
                HeightWeightDob heightWeightDob3 = value2;
                ProfileData profileData15 = this.p;
                if (profileData15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
                    profileData15 = null;
                }
                heightWeightDob3.setStrDob(profileData15.getDob());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            MyAccountViewModel myAccountViewModel7 = this.q;
            if (myAccountViewModel7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                myAccountViewModel7 = null;
            }
            MutableLiveData<HeightWeightDob> heightWeightDob4 = myAccountViewModel7.getHeightWeightDob();
            Intrinsics.checkNotNull(heightWeightDob4);
            HeightWeightDob value3 = heightWeightDob4.getValue();
            Intrinsics.checkNotNull(value3);
            value3.setDateOfBirth(null);
            MyAccountViewModel myAccountViewModel8 = this.q;
            if (myAccountViewModel8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                myAccountViewModel8 = null;
            }
            MutableLiveData<HeightWeightDob> heightWeightDob5 = myAccountViewModel8.getHeightWeightDob();
            Intrinsics.checkNotNull(heightWeightDob5);
            HeightWeightDob value4 = heightWeightDob5.getValue();
            Intrinsics.checkNotNull(value4);
            value4.setStrDob(null);
        }
        EditText editText3 = (EditText) _$_findCachedViewById(R.id.updateHeightEditText);
        ProfileData profileData16 = this.p;
        if (profileData16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
            profileData16 = null;
        }
        editText3.setText(String.valueOf(profileData16.getHeight()));
        EditText editText4 = (EditText) _$_findCachedViewById(R.id.updateWeightEditText);
        ProfileData profileData17 = this.p;
        if (profileData17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
            profileData17 = null;
        }
        editText4.setText(String.valueOf(profileData17.getWeight()));
        ProfileData profileData18 = this.p;
        if (profileData18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
            profileData18 = null;
        }
        String height5 = profileData18.getHeight();
        Intrinsics.checkNotNullExpressionValue(height5, "mUserDetails.height");
        this.H = Integer.parseInt(height5);
        ProfileData profileData19 = this.p;
        if (profileData19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
            profileData19 = null;
        }
        String weight = profileData19.getWeight();
        Intrinsics.checkNotNullExpressionValue(weight, "mUserDetails.weight");
        this.K = Integer.parseInt(weight);
        q1();
        MyAccountViewModel myAccountViewModel9 = this.q;
        if (myAccountViewModel9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel9 = null;
        }
        ProfileData profileData20 = this.p;
        if (profileData20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
            profileData20 = null;
        }
        myAccountViewModel9.setUserId(String.valueOf(profileData20.getUserId()));
        MyAccountViewModel myAccountViewModel10 = this.q;
        if (myAccountViewModel10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            myAccountViewModel10 = null;
        }
        MutableLiveData<String> gender = myAccountViewModel10.getGender();
        ProfileData profileData21 = this.p;
        if (profileData21 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
        } else {
            profileData2 = profileData21;
        }
        gender.setValue(profileData2.getGender());
        int i3 = R.id.updateEmailEditText;
        Editable text = ((EditText) _$_findCachedViewById(i3)).getText();
        Intrinsics.checkNotNull(text);
        if (!(StringsKt__StringsKt.trim(text).toString().length() > 0) || (weeklyReportData = UserDataManager.getInstance(this).getWeeklyReportData()) == null) {
            return;
        }
        weeklyReportData.isEmailVerified();
        if (weeklyReportData.isEmailVerified()) {
            weeklyReportData.isSubscribed();
            if (weeklyReportData.isSubscribed()) {
                ((EditText) _$_findCachedViewById(i3)).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.email_verify_new, 0);
            }
        }
    }

    public final void w1(String str) {
        String string = getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, str);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.u7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.x1(ActivityEditProfile.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void x0() {
        setResult(-1, getIntent());
        finish();
    }

    public final boolean y0() {
        int i = R.id.updateNameEditText;
        Editable text = ((EditText) _$_findCachedViewById(i)).getText();
        Intrinsics.checkNotNullExpressionValue(text, "updateNameEditText.text");
        if (text.length() > 0) {
            String obj = ((EditText) _$_findCachedViewById(i)).getText().toString();
            ProfileData profileData = this.p;
            if (profileData == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mUserDetails");
                profileData = null;
            }
            if (!Intrinsics.areEqual(obj, profileData.getName())) {
                return true;
            }
        }
        return false;
    }

    public final void y1() {
        final BottomSheetDialogProfilePicOptions bottomSheetDialogProfilePicOptions = new BottomSheetDialogProfilePicOptions(this);
        bottomSheetDialogProfilePicOptions.setPositiveButton(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.g8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.z1(BottomSheetDialogProfilePicOptions.this, view);
            }
        });
        bottomSheetDialogProfilePicOptions.setRadioOptionClick(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.h8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityEditProfile.A1(BottomSheetDialogProfilePicOptions.this, this, view);
            }
        });
        bottomSheetDialogProfilePicOptions.setCancelable(false);
        bottomSheetDialogProfilePicOptions.show();
    }
}
