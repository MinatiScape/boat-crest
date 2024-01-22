package com.coveiot.android.leonardo.onboarding.profile.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.onboarding.profile.activities.ActivityProfile;
import com.coveiot.android.leonardo.onboarding.profile.listeners.ContractorBasicProfileInfo;
import com.coveiot.android.leonardo.onboarding.profile.model.HeightWeightDob;
import com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentBasicProfileInfoViewModel;
import com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentFinalProfileViewModel;
import com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentGenderViewModel;
import com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentSocialLoginViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.utils.model.FailureType;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.CircularImageView;
import com.coveiot.utils.utility.GlideUtils;
import com.google.android.material.textfield.TextInputLayout;
import com.theartofdev.edmodo.cropper.CropImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentBasicProfileInfo extends BaseFragment implements ContractorBasicProfileInfo {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public String m;
    public String mCurrentPhotoPath;
    public int n;
    @Nullable
    public String[] q;
    public FragmentBasicProfileInfoViewModel r;
    @Nullable
    public View s;
    @Nullable
    public Uri t;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int o = 1;
    public final int p = 2;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentBasicProfileInfo newInstance() {
            return new FragmentBasicProfileInfo();
        }
    }

    public static final void r(FragmentBasicProfileInfo this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.NAME_EMAIL_SIGN_UP_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.ADD_PHOTO_POPUP.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.ADD_PHOTO.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        this$0.v();
    }

    public static final void s(FragmentBasicProfileInfo this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.NAME_EMAIL_SIGN_UP_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_GENDER_SELECTION_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.SUBMIT_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        FragmentBasicProfileInfoViewModel fragmentBasicProfileInfoViewModel = this$0.r;
        if (fragmentBasicProfileInfoViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentBasicProfileInfoViewModel = null;
        }
        fragmentBasicProfileInfoViewModel.onSubmitClicked();
    }

    public static final void u(FragmentBasicProfileInfo this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity);
        AppUtils.openAppSettings(activity, -1);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void w(FragmentBasicProfileInfo this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i != 0) {
            if (i != 1) {
                return;
            }
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setParentScreenName(FirebaseEventParams.ScreenName.NAME_EMAIL_SIGN_UP_SCREEN.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.ADD_PHOTO_POPUP.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.CHOOSE_GALLERY.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.CHOOSE_FROM_GALLERY.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            this$0.n = 350;
            this$0.choosePhotoFromGallary();
            return;
        }
        AnalyticsLog analyticsLog2 = new AnalyticsLog();
        analyticsLog2.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog2.setParentScreenName(FirebaseEventParams.ScreenName.NAME_EMAIL_SIGN_UP_SCREEN.getValue());
        analyticsLog2.setScreenName(FirebaseEventParams.ScreenName.ADD_PHOTO_POPUP.getValue());
        analyticsLog2.setDescription(FirebaseEventParams.Description.OPEN_CAMERA.getValue());
        analyticsLog2.setUiElementName(FirebaseEventParams.UiElementName.TAKE_PHOTO_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
        this$0.n = 351;
        String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(this$0.getContext(), new String[]{"android.permission.CAMERA"});
        this$0.q = checkPermissionsHasGranted;
        Intrinsics.checkNotNull(checkPermissionsHasGranted);
        if (checkPermissionsHasGranted.length > 0) {
            FragmentActivity activity = this$0.getActivity();
            Intrinsics.checkNotNull(activity);
            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, "android.permission.CAMERA")) {
                FragmentActivity activity2 = this$0.getActivity();
                Intrinsics.checkNotNull(activity2);
                String[] strArr = this$0.q;
                Intrinsics.checkNotNull(strArr);
                ActivityCompat.requestPermissions(activity2, strArr, this$0.n);
                return;
            }
            String string = this$0.getString(R.string.storage_camera_permission_required);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.stora…mera_permission_required)");
            this$0.t(string);
            return;
        }
        this$0.x();
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

    public final void choosePhotoFromGallary() {
        startActivityForResult(PayUtils.INSTANCE.getGalleryIntent(), this.o);
    }

    @Override // com.coveiot.android.leonardo.onboarding.profile.listeners.ContractorBasicProfileInfo
    public void getEmail() {
        FragmentBasicProfileInfoViewModel fragmentBasicProfileInfoViewModel = this.r;
        if (fragmentBasicProfileInfoViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentBasicProfileInfoViewModel = null;
        }
        fragmentBasicProfileInfoViewModel.setEmail(StringsKt__StringsKt.trim(((EditText) _$_findCachedViewById(R.id.updateEmailEditText)).getText().toString()).toString());
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

    @Override // com.coveiot.android.leonardo.onboarding.profile.listeners.ContractorBasicProfileInfo
    public void getName() {
        EditText editText = (EditText) _$_findCachedViewById(R.id.updateNameEditText);
        FragmentBasicProfileInfoViewModel fragmentBasicProfileInfoViewModel = null;
        String valueOf = String.valueOf(editText != null ? editText.getText() : null);
        FragmentBasicProfileInfoViewModel fragmentBasicProfileInfoViewModel2 = this.r;
        if (fragmentBasicProfileInfoViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentBasicProfileInfoViewModel = fragmentBasicProfileInfoViewModel2;
        }
        fragmentBasicProfileInfoViewModel.setName(valueOf);
    }

    @Nullable
    public final Uri getPhotoURI$app_prodRelease() {
        return this.t;
    }

    @Nullable
    public final View getView$app_prodRelease() {
        return this.s;
    }

    @Override // com.coveiot.android.leonardo.onboarding.profile.listeners.ContractorBasicProfileInfo
    public void loadNextScreen() {
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.onboarding.profile.activities.ActivityProfile");
        ((ActivityProfile) activity).loadGenderFragment();
    }

    public final File o() throws IOException {
        AppUtils.formatDate(new Date(), "yyyyMMdd_HHmmss");
        String value = AppConstants.PROFILE_IMAGE_FILENAME.getValue();
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        File image = File.createTempFile(value, "", activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES));
        String absolutePath = image.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "image.absolutePath");
        setMCurrentPhotoPath(absolutePath);
        Intrinsics.checkNotNullExpressionValue(image, "image");
        return image;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        ImageView imageView;
        Uri data;
        super.onActivityResult(i, i2, intent);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (i == this.o) {
            if (intent == null || (data = intent.getData()) == null) {
                return;
            }
            try {
                BitmapFactory.decodeStream(requireActivity().getContentResolver().openInputStream(data)).compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
                CropImage.ActivityBuilder minCropResultSize = CropImage.activity(data).setMinCropResultSize(100, 100);
                FragmentActivity activity = getActivity();
                Intrinsics.checkNotNull(activity);
                startActivityForResult(minCropResultSize.getIntent(activity), 203);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (i == this.p) {
            Uri uri = this.t;
            if (uri == null || i2 != -1) {
                return;
            }
            CropImage.ActivityBuilder minCropResultSize2 = CropImage.activity(uri).setMinCropResultSize(100, 100);
            FragmentActivity activity2 = getActivity();
            Intrinsics.checkNotNull(activity2);
            startActivityForResult(minCropResultSize2.getIntent(activity2), 203);
        } else if (i == 203) {
            CropImage.ActivityResult activityResult = CropImage.getActivityResult(intent);
            if (i2 != -1) {
                if (i2 != 204) {
                    return;
                }
                activityResult.getError();
                return;
            }
            Uri uri2 = activityResult.getUri();
            if (uri2 != null) {
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(requireActivity().getContentResolver().openInputStream(uri2));
                    if (this.s != null && (imageView = (ImageView) _$_findCachedViewById(R.id.profile)) != null) {
                        imageView.setImageBitmap(CircularImageView.getCircleBitmap(decodeStream));
                    }
                    this.m = CircularImageView.saveImageToInternal(CircularImageView.getCircleBitmap(decodeStream), getActivity());
                    FragmentBasicProfileInfoViewModel fragmentBasicProfileInfoViewModel = this.r;
                    if (fragmentBasicProfileInfoViewModel != null) {
                        if (fragmentBasicProfileInfoViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            fragmentBasicProfileInfoViewModel = null;
                        }
                        String str = this.m;
                        Intrinsics.checkNotNull(str);
                        fragmentBasicProfileInfoViewModel.setProfilePic(str);
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View view = this.s;
        if (view != null) {
            Intrinsics.checkNotNull(view);
            if (view.getParent() != null) {
                View view2 = this.s;
                Intrinsics.checkNotNull(view2);
                ViewParent parent = view2.getParent();
                Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
                ((ViewGroup) parent).removeView(this.s);
            }
            return this.s;
        }
        View inflate = inflater.inflate(R.layout.fragment_basic_profile_info_fragment, viewGroup, false);
        this.s = inflate;
        return inflate;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        int i2 = this.n;
        if (i2 == 350) {
            if (!(grantResults.length == 0)) {
                choosePhotoFromGallary();
            }
        } else if (i2 != 351) {
            super.onRequestPermissionsResult(i, permissions, grantResults);
        } else {
            if (!(grantResults.length == 0)) {
                String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(getContext(), new String[]{"android.permission.CAMERA"});
                Intrinsics.checkNotNullExpressionValue(checkPermissionsHasGranted, "checkPermissionsHasGrant…                        )");
                if (!(checkPermissionsHasGranted.length == 0)) {
                    return;
                }
                x();
            }
        }
    }

    @Override // com.coveiot.android.leonardo.onboarding.profile.listeners.ContractorBasicProfileInfo
    public void onValidationFailed(@NotNull FailureType failureType, @NotNull String message) {
        Intrinsics.checkNotNullParameter(failureType, "failureType");
        Intrinsics.checkNotNullParameter(message, "message");
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        Toast.makeText(context, message, 1).show();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        FragmentBasicProfileInfoViewModel fragmentBasicProfileInfoViewModel = (FragmentBasicProfileInfoViewModel) ViewModelProviders.of(requireActivity(), new ViewModelFactory(requireActivity)).get(FragmentBasicProfileInfoViewModel.class);
        this.r = fragmentBasicProfileInfoViewModel;
        FragmentBasicProfileInfoViewModel fragmentBasicProfileInfoViewModel2 = null;
        if (fragmentBasicProfileInfoViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentBasicProfileInfoViewModel = null;
        }
        fragmentBasicProfileInfoViewModel.setContractInterface(this);
        if (getArguments() != null) {
            q();
        }
        if (ProfileData.getInstance().getMobileNumber() == null) {
            AppNavigator.Companion companion = AppNavigator.Companion;
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            companion.navigateToSplashActivityAndClear(requireActivity2);
            return;
        }
        FragmentBasicProfileInfoViewModel fragmentBasicProfileInfoViewModel3 = this.r;
        if (fragmentBasicProfileInfoViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentBasicProfileInfoViewModel2 = fragmentBasicProfileInfoViewModel3;
        }
        String mobileNumber = ProfileData.getInstance().getMobileNumber();
        Intrinsics.checkNotNull(mobileNumber);
        fragmentBasicProfileInfoViewModel2.setMobileNumber(mobileNumber);
        ((ImageView) _$_findCachedViewById(R.id.profile)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentBasicProfileInfo.r(FragmentBasicProfileInfo.this, view2);
            }
        });
        int i = R.id.btn_next;
        ((Button) _$_findCachedViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentBasicProfileInfo.s(FragmentBasicProfileInfo.this, view2);
            }
        });
        int i2 = R.id.updateNameEditText;
        Editable text = ((EditText) _$_findCachedViewById(i2)).getText();
        Intrinsics.checkNotNull(text);
        if (StringsKt__StringsKt.trim(text).toString().length() > 0) {
            ((EditText) _$_findCachedViewById(i2)).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.delete_image, 0);
        } else {
            ((EditText) _$_findCachedViewById(i2)).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        if (((EditText) _$_findCachedViewById(i2)).getText().length() > 0) {
            ((Button) _$_findCachedViewById(i)).setEnabled(true);
        }
        ((EditText) _$_findCachedViewById(i2)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.FragmentBasicProfileInfo$onViewCreated$3
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
                Intrinsics.checkNotNull(editable);
                if (StringsKt__StringsKt.trim(editable).toString().length() > 0) {
                    ((EditText) FragmentBasicProfileInfo.this._$_findCachedViewById(R.id.updateNameEditText)).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.delete_image, 0);
                    ((Button) FragmentBasicProfileInfo.this._$_findCachedViewById(R.id.btn_next)).setEnabled(true);
                    return;
                }
                ((EditText) FragmentBasicProfileInfo.this._$_findCachedViewById(R.id.updateNameEditText)).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i3, int i4, int i5) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i3, int i4, int i5) {
            }
        });
        ((EditText) _$_findCachedViewById(i2)).setOnTouchListener(new View.OnTouchListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.FragmentBasicProfileInfo$onViewCreated$4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(@NotNull View v, @NotNull MotionEvent event) {
                Intrinsics.checkNotNullParameter(v, "v");
                Intrinsics.checkNotNullParameter(event, "event");
                if (event.getAction() == 1) {
                    FragmentBasicProfileInfo fragmentBasicProfileInfo = FragmentBasicProfileInfo.this;
                    int i3 = R.id.updateNameEditText;
                    Editable text2 = ((EditText) fragmentBasicProfileInfo._$_findCachedViewById(i3)).getText();
                    Intrinsics.checkNotNull(text2);
                    if (StringsKt__StringsKt.trim(text2).toString().length() > 0 && event.getRawX() >= ((EditText) FragmentBasicProfileInfo.this._$_findCachedViewById(i3)).getRight() - ((EditText) FragmentBasicProfileInfo.this._$_findCachedViewById(i3)).getCompoundDrawables()[2].getBounds().width()) {
                        ((EditText) FragmentBasicProfileInfo.this._$_findCachedViewById(i3)).setText(AppConstants.EMPTY_STRING.getValue());
                        ((Button) FragmentBasicProfileInfo.this._$_findCachedViewById(R.id.btn_next)).setEnabled(false);
                        ((TextInputLayout) FragmentBasicProfileInfo.this._$_findCachedViewById(R.id.updateNameTextInputLayout)).requestFocus();
                        return true;
                    }
                }
                return false;
            }
        });
        int i3 = R.id.updateEmailEditText;
        Editable text2 = ((EditText) _$_findCachedViewById(i3)).getText();
        Intrinsics.checkNotNull(text2);
        if (StringsKt__StringsKt.trim(text2).toString().length() > 0) {
            ((TextInputLayout) _$_findCachedViewById(R.id.updateEmailTextInputLayout)).setHint(getResources().getString(R.string.email));
            ((EditText) _$_findCachedViewById(i3)).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.delete_image, 0);
        } else {
            ((TextInputLayout) _$_findCachedViewById(R.id.updateEmailTextInputLayout)).setHint(getResources().getString(R.string.email_optional));
            ((EditText) _$_findCachedViewById(i3)).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        ((EditText) _$_findCachedViewById(i3)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.FragmentBasicProfileInfo$onViewCreated$5
            @Override // android.text.TextWatcher
            public void afterTextChanged(@Nullable Editable editable) {
                ((TextInputLayout) FragmentBasicProfileInfo.this._$_findCachedViewById(R.id.updateEmailTextInputLayout)).setHint(FragmentBasicProfileInfo.this.getResources().getString(R.string.email));
                Intrinsics.checkNotNull(editable);
                if (StringsKt__StringsKt.trim(editable).toString().length() > 0) {
                    ((EditText) FragmentBasicProfileInfo.this._$_findCachedViewById(R.id.updateEmailEditText)).setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.delete_image, 0);
                } else {
                    ((EditText) FragmentBasicProfileInfo.this._$_findCachedViewById(R.id.updateEmailEditText)).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@Nullable CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@Nullable CharSequence charSequence, int i4, int i5, int i6) {
            }
        });
        ((EditText) _$_findCachedViewById(i3)).setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.FragmentBasicProfileInfo$onViewCreated$6
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(@Nullable View view2, boolean z) {
                if (z) {
                    ((TextInputLayout) FragmentBasicProfileInfo.this._$_findCachedViewById(R.id.updateEmailTextInputLayout)).setHint(FragmentBasicProfileInfo.this.getResources().getString(R.string.email));
                } else if (((EditText) FragmentBasicProfileInfo.this._$_findCachedViewById(R.id.updateEmailEditText)).getText().length() > 0) {
                    ((TextInputLayout) FragmentBasicProfileInfo.this._$_findCachedViewById(R.id.updateEmailTextInputLayout)).setHint(FragmentBasicProfileInfo.this.getResources().getString(R.string.email));
                } else {
                    ((TextInputLayout) FragmentBasicProfileInfo.this._$_findCachedViewById(R.id.updateEmailTextInputLayout)).setHint(FragmentBasicProfileInfo.this.getResources().getString(R.string.email_optional));
                }
            }
        });
        ((EditText) _$_findCachedViewById(i3)).setOnTouchListener(new View.OnTouchListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.FragmentBasicProfileInfo$onViewCreated$7
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(@NotNull View v, @NotNull MotionEvent event) {
                Intrinsics.checkNotNullParameter(v, "v");
                Intrinsics.checkNotNullParameter(event, "event");
                if (event.getAction() == 1) {
                    FragmentBasicProfileInfo fragmentBasicProfileInfo = FragmentBasicProfileInfo.this;
                    int i4 = R.id.updateEmailEditText;
                    Editable text3 = ((EditText) fragmentBasicProfileInfo._$_findCachedViewById(i4)).getText();
                    Intrinsics.checkNotNull(text3);
                    if (StringsKt__StringsKt.trim(text3).toString().length() <= 0 || event.getRawX() < ((EditText) FragmentBasicProfileInfo.this._$_findCachedViewById(i4)).getRight() - ((EditText) FragmentBasicProfileInfo.this._$_findCachedViewById(i4)).getCompoundDrawables()[2].getBounds().width()) {
                        return false;
                    }
                    ((EditText) FragmentBasicProfileInfo.this._$_findCachedViewById(i4)).setText(AppConstants.EMPTY_STRING.getValue());
                    ((TextInputLayout) FragmentBasicProfileInfo.this._$_findCachedViewById(R.id.updateEmailTextInputLayout)).requestFocus();
                    return true;
                }
                return false;
            }
        });
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.NAME_EMAIL_SIGN_UP_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
    }

    public final void p(String str) {
        Context context = getContext();
        Intrinsics.checkNotNull(str);
        GlideUtils.loadImageNoCache(context, str, new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.FragmentBasicProfileInfo$loadProfilePicFromSocialSignIn$1
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                FragmentBasicProfileInfoViewModel fragmentBasicProfileInfoViewModel;
                Intrinsics.checkNotNullParameter(resource, "resource");
                try {
                    Context context2 = FragmentBasicProfileInfo.this.getContext();
                    Intrinsics.checkNotNull(context2);
                    AppConstants appConstants = AppConstants.PROFILE_IMAGE_FILENAME;
                    FileOutputStream openFileOutput = context2.openFileOutput(appConstants.getValue(), 0);
                    CircularImageView.getCircleBitmap(resource).compress(Bitmap.CompressFormat.JPEG, 70, new ByteArrayOutputStream());
                    CircularImageView.getCircleBitmap(resource).compress(Bitmap.CompressFormat.JPEG, 70, openFileOutput);
                    openFileOutput.flush();
                    openFileOutput.close();
                    StringBuilder sb = new StringBuilder();
                    Context context3 = FragmentBasicProfileInfo.this.getContext();
                    Intrinsics.checkNotNull(context3);
                    sb.append(context3.getFilesDir().toString());
                    sb.append('/');
                    sb.append(appConstants.getValue());
                    File file = new File(sb.toString());
                    fragmentBasicProfileInfoViewModel = FragmentBasicProfileInfo.this.r;
                    if (fragmentBasicProfileInfoViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        fragmentBasicProfileInfoViewModel = null;
                    }
                    String absolutePath = file.getAbsolutePath();
                    Intrinsics.checkNotNullExpressionValue(absolutePath, "selFile.absolutePath");
                    fragmentBasicProfileInfoViewModel.setProfilePic(absolutePath);
                    ((ImageView) FragmentBasicProfileInfo.this._$_findCachedViewById(R.id.profile)).setImageBitmap(CircularImageView.getCircleBitmap(resource));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    public final void q() {
        Bundle arguments = getArguments();
        Intrinsics.checkNotNull(arguments);
        FragmentSocialLoginViewModel.Companion companion = FragmentSocialLoginViewModel.Companion;
        ((EditText) _$_findCachedViewById(R.id.updateNameEditText)).setText(arguments.getString(companion.getBUNDLE_DISPLAY_NAME()));
        Bundle arguments2 = getArguments();
        Intrinsics.checkNotNull(arguments2);
        String string = arguments2.getString(companion.getBUNDLE_SOCIAL_EMAIL());
        if (string != null) {
            ((EditText) _$_findCachedViewById(R.id.updateEmailEditText)).setText(string);
        }
        Bundle arguments3 = getArguments();
        Intrinsics.checkNotNull(arguments3);
        String string2 = arguments3.getString(companion.getBUNDLE_PROFILE_IMAGE());
        if (string2 != null) {
            p(string2);
        }
        Bundle arguments4 = getArguments();
        Intrinsics.checkNotNull(arguments4);
        String string3 = arguments4.getString(companion.getBUNDLE_SOCIAL_GENDER());
        if (string3 != null) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            MutableLiveData<String> gender = ((FragmentGenderViewModel) ViewModelProviders.of(activity, new ViewModelFactory(context)).get(FragmentGenderViewModel.class)).getGender();
            Intrinsics.checkNotNull(gender);
            gender.setValue(string3);
        }
        Bundle arguments5 = getArguments();
        Intrinsics.checkNotNull(arguments5);
        String string4 = arguments5.getString(companion.getBUNDLE_SOCIAL_BIRTHDAY());
        if (string4 != null) {
            FragmentActivity activity2 = getActivity();
            Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
            Context context2 = getContext();
            Intrinsics.checkNotNull(context2);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(AppUtils.parseDate(string4, "MM/DD/YYYY"));
            MutableLiveData<HeightWeightDob> heightWeightDob = ((FragmentFinalProfileViewModel) ViewModelProviders.of(activity2, new ViewModelFactory(context2)).get(FragmentFinalProfileViewModel.class)).getHeightWeightDob();
            Intrinsics.checkNotNull(heightWeightDob);
            HeightWeightDob value = heightWeightDob.getValue();
            Intrinsics.checkNotNull(value);
            value.setDateOfBirth(calendar);
        }
        Bundle arguments6 = getArguments();
        Intrinsics.checkNotNull(arguments6);
        if (arguments6.getString(companion.getBUNDLE_SOCIAL_LINK()) != null) {
            FragmentBasicProfileInfoViewModel fragmentBasicProfileInfoViewModel = this.r;
            if (fragmentBasicProfileInfoViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentBasicProfileInfoViewModel = null;
            }
            Bundle arguments7 = getArguments();
            Intrinsics.checkNotNull(arguments7);
            fragmentBasicProfileInfoViewModel.setSocialMediaId(arguments7.getString(companion.getBUNDLE_SOCIAL_LINK()));
        }
    }

    public final void setMCurrentPhotoPath(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mCurrentPhotoPath = str;
    }

    public final void setPhotoURI$app_prodRelease(@Nullable Uri uri) {
        this.t = uri;
    }

    public final void setView$app_prodRelease(@Nullable View view) {
        this.s = view;
    }

    public final void t(String str) {
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        String string = getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(context, string, str);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentBasicProfileInfo.u(FragmentBasicProfileInfo.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void v() {
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.CustomAlertDialog);
        builder.setTitle(getString(R.string.select_image));
        builder.setItems(new String[]{getString(R.string.select_image_from_camera), getString(R.string.select_image_from_gallery), getString(R.string.cancel)}, new DialogInterface.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.profile.fragments.a
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                FragmentBasicProfileInfo.w(FragmentBasicProfileInfo.this, dialogInterface, i);
            }
        });
        builder.show();
    }

    public final void x() {
        File file = null;
        this.t = null;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            try {
                file = o();
            } catch (IOException unused) {
            }
            if (file != null) {
                Context context = getContext();
                Intrinsics.checkNotNull(context);
                StringBuilder sb = new StringBuilder();
                Context context2 = getContext();
                Intrinsics.checkNotNull(context2);
                sb.append(context2.getPackageName());
                sb.append(".provider");
                Uri uriForFile = FileProvider.getUriForFile(context, sb.toString(), file);
                this.t = uriForFile;
                intent.putExtra("output", uriForFile);
                startActivityForResult(intent, this.p);
            }
        }
    }
}
