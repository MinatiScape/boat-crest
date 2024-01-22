package com.coveiot.android.leonardo.onboarding.profile.viewmodel;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.provider.Settings;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.util.Dashboard2Constants;
import com.coveiot.android.leonardo.onboarding.profile.listeners.UpdateDobListener;
import com.coveiot.android.leonardo.onboarding.profile.model.HeightWeightDob;
import com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentFinalProfileViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveOnboarding;
import com.coveiot.coveaccess.fitness.config.FitnessConfigApi;
import com.coveiot.coveaccess.fitness.config.models.requestmodel.FitnessConfigRequest;
import com.coveiot.coveaccess.fitness.config.models.responsemodel.FitnessConfigResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.onboarding.model.RegisterNewUserReq;
import com.coveiot.coveaccess.onboarding.model.RegisterNewUserRes;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.NameDetails;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.repository.profile.ProfileRepository;
import com.coveiot.utils.model.FailureType;
import com.coveiot.utils.utility.AppUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.common.Scopes;
import com.jstyle.blesdk1860.constant.BleConst;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentFinalProfileViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5330a;
    @Nullable
    public MutableLiveData<HeightWeightDob> b;
    @Nullable
    public String c;
    public Date d;
    public String date_var;
    @Nullable
    public String e;
    @NotNull
    public ArrayList<String> f;
    @NotNull
    public ArrayList<String> g;
    @NotNull
    public ArrayList<String> h;
    @NotNull
    public String i;
    @NotNull
    public String j;
    @NotNull
    public final String[] k;
    @Nullable
    public Calendar l;
    public int m;
    public String month_var;
    public int n;
    public int o;
    public UpdateDobListener updateDobListener;
    public String year_var;

    /* loaded from: classes5.dex */
    public interface SubmitClickListner {
        void onFailure(@NotNull FailureType failureType, @NotNull String str);

        void proccedToNextScreen();
    }

    public FragmentFinalProfileViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5330a = context;
        this.b = new MutableLiveData<>();
        MutableLiveData<HeightWeightDob> mutableLiveData = new MutableLiveData<>();
        this.b = mutableLiveData;
        mutableLiveData.setValue(new HeightWeightDob());
        this.f = new ArrayList<>();
        this.g = new ArrayList<>();
        this.h = new ArrayList<>();
        this.i = "1900";
        this.j = "1";
        this.k = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    }

    @NotNull
    public final Context getContext() {
        return this.f5330a;
    }

    @Nullable
    public final String getDateOfBirth() {
        return this.c;
    }

    @NotNull
    public final String getDate_var() {
        String str = this.date_var;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("date_var");
        return null;
    }

    @Nullable
    public final String getDate_var_replaced() {
        return this.e;
    }

    @NotNull
    public final ArrayList<String> getDays() {
        return this.f;
    }

    @Nullable
    public final MutableLiveData<HeightWeightDob> getHeightWeightDob() {
        return this.b;
    }

    public final int getMDayItemPosition() {
        return this.m;
    }

    public final int getMMonthSelectedPosition() {
        return this.n;
    }

    public final int getMYearSelectedPosition() {
        return this.o;
    }

    @NotNull
    public final String getMonth_var() {
        String str = this.month_var;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("month_var");
        return null;
    }

    @NotNull
    public final ArrayList<String> getMonths() {
        return this.g;
    }

    @NotNull
    public final String getOne() {
        return this.j;
    }

    @NotNull
    public final String getStarting_year() {
        return this.i;
    }

    @NotNull
    public final UpdateDobListener getUpdateDobListener() {
        UpdateDobListener updateDobListener = this.updateDobListener;
        if (updateDobListener != null) {
            return updateDobListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("updateDobListener");
        return null;
    }

    @NotNull
    public final String getYear_var() {
        String str = this.year_var;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("year_var");
        return null;
    }

    @NotNull
    public final ArrayList<String> getYears() {
        return this.h;
    }

    public final void initializeDob(@NotNull String dobEditText) {
        Intrinsics.checkNotNullParameter(dobEditText, "dobEditText");
        this.l = Calendar.getInstance();
        if (!AppUtils.isEmpty(dobEditText)) {
            try {
                Date parse = AppUtils.getSimpleDateFormat("d MMM yyyy").parse(m.replace$default(m.replace$default(m.replace$default(m.replace$default(dobEditText, "rd", "", false, 4, (Object) null), "st", "", false, 4, (Object) null), "nd", "", false, 4, (Object) null), "th", "", false, 4, (Object) null));
                Calendar calendar = this.l;
                Intrinsics.checkNotNull(calendar);
                calendar.setTime(parse);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            Calendar calendar2 = this.l;
            Intrinsics.checkNotNull(calendar2);
            calendar2.add(1, -Integer.parseInt(AppConstants.MIN_AGE_RESTRICTION.getValue()));
        }
        this.d = new Date();
        Calendar calendar3 = this.l;
        Intrinsics.checkNotNull(calendar3);
        setDate_var(String.valueOf(calendar3.get(5)));
        PayUtils payUtils = PayUtils.INSTANCE;
        Calendar calendar4 = this.l;
        Intrinsics.checkNotNull(calendar4);
        setMonth_var(payUtils.getMonth(String.valueOf(calendar4.get(2) + 1)));
        Calendar calendar5 = this.l;
        Intrinsics.checkNotNull(calendar5);
        setYear_var(String.valueOf(calendar5.get(1)));
        Calendar calendar6 = this.l;
        Intrinsics.checkNotNull(calendar6);
        this.i = String.valueOf(calendar6.get(1));
        setDateOfBirth(this.l);
    }

    public final boolean isLeapYear(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i);
        return calendar.getActualMaximum(6) > 365;
    }

    public final void oDobOkAction() {
        SimpleDateFormat simpleDateFormat;
        int monthFromStringMMM = !CoveUtil.isEmpty(getMonth_var()) ? PayUtils.INSTANCE.getMonthFromStringMMM(getMonth_var()) : 0;
        String replace$default = m.replace$default(m.replace$default(m.replace$default(m.replace$default(getDate_var(), "rd", "", false, 4, (Object) null), "st", "", false, 4, (Object) null), "nd", "", false, 4, (Object) null), "th", "", false, 4, (Object) null);
        this.e = replace$default;
        if (replace$default != null) {
            PayUtils payUtils = PayUtils.INSTANCE;
            int parseInt = Integer.parseInt(this.i);
            String str = this.e;
            Intrinsics.checkNotNull(str);
            String age = payUtils.getAge(parseInt, monthFromStringMMM, Integer.parseInt(str));
            int parseInt2 = Integer.parseInt(AppConstants.MIN_AGE_RESTRICTION.getValue());
            int parseInt3 = Integer.parseInt(age);
            if (parseInt2 <= parseInt3 && parseInt3 < 129) {
                getUpdateDobListener().onUpdate(getDate_var() + ' ' + getMonth_var() + ' ' + this.i);
                Date parse = AppUtils.getSimpleDateFormat("d MMM yyyy").parse(this.e + ' ' + getMonth_var() + ' ' + this.i);
                Intrinsics.checkNotNullExpressionValue(parse, "simpleDateFormat.parse(\"…onth_var $starting_year\")");
                this.d = parse;
                Calendar calendar = this.l;
                Date date = null;
                if (calendar != null) {
                    if (parse == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mDate");
                        parse = null;
                    }
                    calendar.setTime(parse);
                }
                try {
                    if (m.endsWith$default(getDate_var(), "1", false, 2, null) && !m.endsWith$default(getDate_var(), BleConst.GetDeviceVersion, false, 2, null)) {
                        simpleDateFormat = AppUtils.getSimpleDateFormat("d'st' MMM yyyy");
                        Intrinsics.checkNotNullExpressionValue(simpleDateFormat, "getSimpleDateFormat(\"d'st' MMM yyyy\")");
                    } else if (m.endsWith$default(getDate_var(), "2", false, 2, null) && !m.endsWith$default(getDate_var(), BleConst.CMD_Reset, false, 2, null)) {
                        simpleDateFormat = AppUtils.getSimpleDateFormat("d'nd' MMM yyyy");
                        Intrinsics.checkNotNullExpressionValue(simpleDateFormat, "getSimpleDateFormat(\"d'nd' MMM yyyy\")");
                    } else if (m.endsWith$default(getDate_var(), "3", false, 2, null) && !m.endsWith$default(getDate_var(), BleConst.CMD_MCUReset, false, 2, null)) {
                        simpleDateFormat = AppUtils.getSimpleDateFormat("d'rd' MMM yyyy");
                        Intrinsics.checkNotNullExpressionValue(simpleDateFormat, "getSimpleDateFormat(\"d'rd' MMM yyyy\")");
                    } else {
                        simpleDateFormat = AppUtils.getSimpleDateFormat("d'th' MMM yyyy");
                        Intrinsics.checkNotNullExpressionValue(simpleDateFormat, "getSimpleDateFormat(\"d'th' MMM yyyy\")");
                    }
                    Date date2 = this.d;
                    if (date2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mDate");
                    } else {
                        date = date2;
                    }
                    String dob = simpleDateFormat.format(date);
                    UpdateDobListener updateDobListener = getUpdateDobListener();
                    Intrinsics.checkNotNullExpressionValue(dob, "dob");
                    updateDobListener.onUpdate(dob);
                    return;
                } catch (ParseException e) {
                    e.printStackTrace();
                    return;
                }
            }
            Toast.makeText(this.f5330a, (int) R.string.age_confirmation, 1).show();
        }
    }

    @SuppressLint({"HardwareIds"})
    public final void onSubmitClicked(@NotNull final SubmitClickListner listner) {
        String str;
        Intrinsics.checkNotNullParameter(listner, "listner");
        Context context = this.f5330a;
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        FragmentBasicProfileInfoViewModel fragmentBasicProfileInfoViewModel = (FragmentBasicProfileInfoViewModel) ViewModelProviders.of((FragmentActivity) context, new ViewModelFactory(this.f5330a)).get(FragmentBasicProfileInfoViewModel.class);
        Context context2 = this.f5330a;
        Intrinsics.checkNotNull(context2, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        FragmentGenderViewModel fragmentGenderViewModel = (FragmentGenderViewModel) ViewModelProviders.of((FragmentActivity) context2, new ViewModelFactory(this.f5330a)).get(FragmentGenderViewModel.class);
        fragmentBasicProfileInfoViewModel.getName();
        RegisterNewUserReq registerNewUserReq = new RegisterNewUserReq();
        registerNewUserReq.setName(fragmentBasicProfileInfoViewModel.getName());
        registerNewUserReq.setEmailId(fragmentBasicProfileInfoViewModel.getEmail());
        String profilePic = fragmentBasicProfileInfoViewModel.getProfilePic();
        MutableLiveData<String> gender = fragmentGenderViewModel.getGender();
        Intrinsics.checkNotNull(gender);
        String value = gender.getValue();
        if (AppUtils.isEmpty(profilePic)) {
            Bitmap bitmapFromVectorDrawable = AppUtils.getBitmapFromVectorDrawable(this.f5330a, 2131231665);
            if (!AppUtils.isEmpty(value)) {
                if (m.equals(value, Dashboard2Constants.FEMALE, true)) {
                    bitmapFromVectorDrawable = AppUtils.getBitmapFromVectorDrawable(this.f5330a, 2131231757);
                } else if (m.equals(value, "Male", true)) {
                    bitmapFromVectorDrawable = AppUtils.getBitmapFromVectorDrawable(this.f5330a, 2131232762);
                }
            }
            File dir = new ContextWrapper(this.f5330a.getApplicationContext()).getDir(Scopes.PROFILE, 0);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(dir, "profile_pic.png");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmapFromVectorDrawable.compress(Bitmap.CompressFormat.PNG, 70, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            profilePic = file.getAbsolutePath();
        }
        registerNewUserReq.setDpFile(new File(profilePic));
        registerNewUserReq.setMobileNumber(fragmentBasicProfileInfoViewModel.getMobileNumber());
        final ProfileData profileData = ProfileData.getInstance();
        profileData.setName(fragmentBasicProfileInfoViewModel.getName());
        Boolean bool = null;
        if (AppUtils.isEmpty(fragmentBasicProfileInfoViewModel.getEmail())) {
            profileData.setEmail(null);
        } else {
            profileData.setEmail(fragmentBasicProfileInfoViewModel.getEmail());
        }
        profileData.setProfile_pic(profilePic);
        profileData.setMobileNumber(fragmentBasicProfileInfoViewModel.getMobileNumber());
        final NameDetails nameDetails = new NameDetails();
        String name = profileData.getName();
        Intrinsics.checkNotNullExpressionValue(name, "profileData.name");
        if (StringsKt__StringsKt.split$default((CharSequence) StringsKt__StringsKt.trim(name).toString(), new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 2, 2, (Object) null).size() > 1) {
            String name2 = profileData.getName();
            Intrinsics.checkNotNullExpressionValue(name2, "profileData.name");
            nameDetails.setFname((String) StringsKt__StringsKt.split$default((CharSequence) StringsKt__StringsKt.trim(name2).toString(), new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 2, 2, (Object) null).get(0));
            String name3 = profileData.getName();
            Intrinsics.checkNotNullExpressionValue(name3, "profileData.name");
            nameDetails.setLname((String) StringsKt__StringsKt.split$default((CharSequence) StringsKt__StringsKt.trim(name3).toString(), new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 2, 2, (Object) null).get(1));
        } else {
            String name4 = profileData.getName();
            Intrinsics.checkNotNullExpressionValue(name4, "profileData.name");
            nameDetails.setFname(StringsKt__StringsKt.trim(name4).toString());
            nameDetails.setLname(null);
        }
        if (!AppUtils.isEmpty(value)) {
            String m107getGender = fragmentGenderViewModel.m107getGender();
            if (m107getGender != null) {
                str = m107getGender.toUpperCase();
                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toUpperCase()");
            } else {
                str = null;
            }
            registerNewUserReq.setGender(str);
            profileData.setGender(fragmentGenderViewModel.m107getGender());
        }
        if (this.c != null) {
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = this.l;
            registerNewUserReq.setBirthDate(simpleDateFormat.format(calendar != null ? calendar.getTime() : null));
            profileData.setDob(registerNewUserReq.getBirthDate());
        }
        registerNewUserReq.setDeviceID(Settings.Secure.getString(this.f5330a.getContentResolver(), "android_id"));
        if (fragmentBasicProfileInfoViewModel.getSocialMediaId() != null) {
            String socialMediaId = fragmentBasicProfileInfoViewModel.getSocialMediaId();
            if (socialMediaId != null) {
                bool = Boolean.valueOf(socialMediaId.length() > 0);
            }
            Intrinsics.checkNotNull(bool);
            if (bool.booleanValue()) {
                registerNewUserReq.setSocialMediaId(fragmentBasicProfileInfoViewModel.getSocialMediaId());
            }
        }
        Object systemService = this.f5330a.getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        registerNewUserReq.setBleId(((BluetoothManager) systemService).getAdapter().getAddress());
        CoveOnboarding.registerNewUser(registerNewUserReq, new CoveApiListener<RegisterNewUserRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentFinalProfileViewModel$onSubmitClicked$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                FragmentFinalProfileViewModel.SubmitClickListner submitClickListner = listner;
                FailureType failureType = FailureType.API_ERROR;
                String string = this.getContext().getString(R.string.show_something_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…how_something_went_wrong)");
                submitClickListner.onFailure(failureType, string);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull RegisterNewUserRes registerNewUserRes) {
                Intrinsics.checkNotNullParameter(registerNewUserRes, "registerNewUserRes");
                if (registerNewUserRes.getCode() == 200 && registerNewUserRes.getUserId() != 0) {
                    ProfileData.this.setUserId(registerNewUserRes.getUserId());
                    ProfileData.this.setExistingUser(false);
                    ProfileData.this.setDpUrl(registerNewUserRes.getDpUrl());
                    ProfileData.this.setAppTrackerId(registerNewUserRes.getAppTrackerId());
                    if (registerNewUserRes.getAxTrackerId() != null) {
                        SessionManager.getInstance(this.getContext()).setAxTrackerId(registerNewUserRes.getAxTrackerId());
                    }
                    SessionManager.getInstance(this.getContext()).saveNameDetails(nameDetails);
                    ProfileRepository.getInstance().updateGender(this.getContext(), !m.equals(ProfileData.this.getGender(), AppConstants.MALE.getValue(), true) ? 1 : 0);
                    if (!AppUtils.isEmpty(ProfileData.this.getDob())) {
                        ProfileRepository.getInstance().updateAge(this.getContext(), AppUtils.getAge(ProfileData.this.getDob()));
                    }
                    MutableLiveData<HeightWeightDob> heightWeightDob = this.getHeightWeightDob();
                    Intrinsics.checkNotNull(heightWeightDob);
                    HeightWeightDob value2 = heightWeightDob.getValue();
                    Intrinsics.checkNotNull(value2);
                    String height = value2.getHeight();
                    MutableLiveData<HeightWeightDob> heightWeightDob2 = this.getHeightWeightDob();
                    Intrinsics.checkNotNull(heightWeightDob2);
                    HeightWeightDob value3 = heightWeightDob2.getValue();
                    Intrinsics.checkNotNull(value3);
                    String weight = value3.getWeight();
                    MutableLiveData<HeightWeightDob> heightWeightDob3 = this.getHeightWeightDob();
                    Intrinsics.checkNotNull(heightWeightDob3);
                    HeightWeightDob value4 = heightWeightDob3.getValue();
                    Intrinsics.checkNotNull(value4);
                    FitnessConfigRequest fitnessConfigRequest = new FitnessConfigRequest(height, weight, value4.getStrideeLength());
                    final ProfileData profileData2 = ProfileData.this;
                    final FragmentFinalProfileViewModel fragmentFinalProfileViewModel = this;
                    final FragmentFinalProfileViewModel.SubmitClickListner submitClickListner = listner;
                    FitnessConfigApi.saveFitnessConfigData(fitnessConfigRequest, new CoveApiListener<FitnessConfigResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentFinalProfileViewModel$onSubmitClicked$1$onSuccess$1
                        @Override // com.coveiot.coveaccess.CoveApiListener
                        public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                            Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                        }

                        @Override // com.coveiot.coveaccess.CoveApiListener
                        public void onSuccess(@NotNull FitnessConfigResponse fitnessConfigResponse) {
                            Intrinsics.checkNotNullParameter(fitnessConfigResponse, "fitnessConfigResponse");
                            ProfileData profileData3 = ProfileData.this;
                            MutableLiveData<HeightWeightDob> heightWeightDob4 = fragmentFinalProfileViewModel.getHeightWeightDob();
                            Intrinsics.checkNotNull(heightWeightDob4);
                            HeightWeightDob value5 = heightWeightDob4.getValue();
                            Intrinsics.checkNotNull(value5);
                            profileData3.setHeight(value5.getHeight());
                            ProfileData profileData4 = ProfileData.this;
                            MutableLiveData<HeightWeightDob> heightWeightDob5 = fragmentFinalProfileViewModel.getHeightWeightDob();
                            Intrinsics.checkNotNull(heightWeightDob5);
                            HeightWeightDob value6 = heightWeightDob5.getValue();
                            Intrinsics.checkNotNull(value6);
                            profileData4.setWeight(value6.getWeight());
                            String height2 = ProfileData.this.getHeight();
                            String weight2 = ProfileData.this.getWeight();
                            if (height2 != null && weight2 != null) {
                                ProfileRepository.getInstance().updateHeightWeight(fragmentFinalProfileViewModel.getContext(), Integer.parseInt(height2), Double.parseDouble(weight2));
                                ProfileData profileData5 = ProfileData.this;
                                MutableLiveData<HeightWeightDob> heightWeightDob6 = fragmentFinalProfileViewModel.getHeightWeightDob();
                                Intrinsics.checkNotNull(heightWeightDob6);
                                HeightWeightDob value7 = heightWeightDob6.getValue();
                                Intrinsics.checkNotNull(value7);
                                profileData5.setStride_length(value7.getStrideLength());
                                SessionManager.getInstance(fragmentFinalProfileViewModel.getContext()).createLoginSession(ProfileData.this);
                                submitClickListner.proccedToNextScreen();
                                return;
                            }
                            FragmentFinalProfileViewModel.SubmitClickListner submitClickListner2 = submitClickListner;
                            FailureType failureType = FailureType.API_ERROR;
                            String string = fragmentFinalProfileViewModel.getContext().getString(R.string.something_went_wrong);
                            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.something_went_wrong)");
                            submitClickListner2.onFailure(failureType, string);
                        }
                    });
                    return;
                }
                FragmentFinalProfileViewModel.SubmitClickListner submitClickListner2 = listner;
                FailureType failureType = FailureType.API_ERROR;
                String string = this.getContext().getString(R.string.show_something_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…how_something_went_wrong)");
                submitClickListner2.onFailure(failureType, string);
            }
        });
    }

    public final void populateDaysDataInView() {
        this.f.clear();
        int i = 1;
        if (m.equals(getMonth_var(), "Feb", true) && !isLeapYear(Integer.parseInt(this.i))) {
            while (true) {
                int i2 = i + 1;
                this.f.add(String.valueOf(i));
                if (i2 > 28) {
                    break;
                }
                i = i2;
            }
            if (Integer.parseInt(getDate_var()) > 28) {
                setDate_var(this.j);
            }
        } else if (m.equals(getMonth_var(), "Feb", true) && isLeapYear(Integer.parseInt(this.i))) {
            while (true) {
                int i3 = i + 1;
                this.f.add(String.valueOf(i));
                if (i3 > 29) {
                    break;
                }
                i = i3;
            }
            if (Integer.parseInt(getDate_var()) > 29) {
                setDate_var(this.j);
            }
        } else if (m.equals(getMonth_var(), "Apr", true) || m.equals(getMonth_var(), "Jun", true) || m.equals(getMonth_var(), "Sep", true) || m.equals(getMonth_var(), "Nov", true)) {
            while (true) {
                int i4 = i + 1;
                this.f.add(String.valueOf(i));
                if (i4 > 30) {
                    break;
                }
                i = i4;
            }
            if (Integer.parseInt(getDate_var()) > 30) {
                setDate_var(this.j);
            }
        } else {
            while (true) {
                int i5 = i + 1;
                this.f.add(String.valueOf(i));
                if (i5 > 31) {
                    break;
                }
                i = i5;
            }
        }
        this.m = this.f.indexOf(getDate_var());
    }

    public final void populateMonthsDataInView() {
        this.g.clear();
        int length = this.k.length - 1;
        if (length >= 0) {
            int i = 0;
            while (true) {
                this.g.add(this.k[i]);
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        this.n = this.g.indexOf(getMonth_var());
    }

    public final void populateYearsDataInView() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(1, -Integer.parseInt(AppConstants.MIN_AGE_RESTRICTION.getValue()));
        this.h.clear();
        int parseInt = Integer.parseInt(String.valueOf(calendar.get(1)));
        int i = 1900;
        if (1900 <= parseInt) {
            while (true) {
                this.h.add(String.valueOf(i));
                if (i == parseInt) {
                    break;
                }
                i++;
            }
        }
        this.o = this.h.size() - 1;
    }

    public final void setDateOfBirth(@Nullable String str) {
        this.c = str;
    }

    public final void setDate_var(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.date_var = str;
    }

    public final void setDate_var_replaced(@Nullable String str) {
        this.e = str;
    }

    public final void setDays(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.f = arrayList;
    }

    public final void setHeight(@Nullable String str) {
        MutableLiveData<HeightWeightDob> mutableLiveData = this.b;
        Intrinsics.checkNotNull(mutableLiveData);
        HeightWeightDob value = mutableLiveData.getValue();
        Intrinsics.checkNotNull(value);
        value.setHeight(str);
        MutableLiveData<HeightWeightDob> mutableLiveData2 = this.b;
        Intrinsics.checkNotNull(mutableLiveData2);
        MutableLiveData<HeightWeightDob> mutableLiveData3 = this.b;
        Intrinsics.checkNotNull(mutableLiveData3);
        mutableLiveData2.postValue(mutableLiveData3.getValue());
    }

    public final void setHeightWeightDob(@Nullable MutableLiveData<HeightWeightDob> mutableLiveData) {
        this.b = mutableLiveData;
    }

    public final void setMDayItemPosition(int i) {
        this.m = i;
    }

    public final void setMMonthSelectedPosition(int i) {
        this.n = i;
    }

    public final void setMYearSelectedPosition(int i) {
        this.o = i;
    }

    public final void setMonth_var(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.month_var = str;
    }

    public final void setMonths(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.g = arrayList;
    }

    public final void setOne(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.j = str;
    }

    public final void setStarting_year(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.i = str;
    }

    public final void setUpdateDobListener(@NotNull UpdateDobListener updateDobListener) {
        Intrinsics.checkNotNullParameter(updateDobListener, "<set-?>");
        this.updateDobListener = updateDobListener;
    }

    public final void setWeight(@Nullable String str) {
        MutableLiveData<HeightWeightDob> mutableLiveData = this.b;
        Intrinsics.checkNotNull(mutableLiveData);
        HeightWeightDob value = mutableLiveData.getValue();
        Intrinsics.checkNotNull(value);
        value.setWeight(str);
        MutableLiveData<HeightWeightDob> mutableLiveData2 = this.b;
        Intrinsics.checkNotNull(mutableLiveData2);
        MutableLiveData<HeightWeightDob> mutableLiveData3 = this.b;
        Intrinsics.checkNotNull(mutableLiveData3);
        mutableLiveData2.postValue(mutableLiveData3.getValue());
    }

    public final void setYear_var(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.year_var = str;
    }

    public final void setYears(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.h = arrayList;
    }

    public final void setDateOfBirth(@Nullable Calendar calendar) {
        MutableLiveData<HeightWeightDob> mutableLiveData = this.b;
        Intrinsics.checkNotNull(mutableLiveData);
        HeightWeightDob value = mutableLiveData.getValue();
        Intrinsics.checkNotNull(value);
        value.setDateOfBirth(calendar);
        MutableLiveData<HeightWeightDob> mutableLiveData2 = this.b;
        Intrinsics.checkNotNull(mutableLiveData2);
        MutableLiveData<HeightWeightDob> mutableLiveData3 = this.b;
        Intrinsics.checkNotNull(mutableLiveData3);
        mutableLiveData2.postValue(mutableLiveData3.getValue());
    }
}
