package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.listener.OnInternetBleCheckListener;
import com.coveiot.android.leonardo.listener.OnSuccessListener;
import com.coveiot.android.leonardo.more.ContractorMyAccount;
import com.coveiot.android.leonardo.onboarding.profile.listeners.UpdateDobListener;
import com.coveiot.android.leonardo.onboarding.profile.model.HeightWeightDob;
import com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentFinalProfileViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.weeklyreport.utils.WeeklyReportConstant;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveOnboarding;
import com.coveiot.coveaccess.fitness.config.FitnessConfigApi;
import com.coveiot.coveaccess.fitness.config.models.requestmodel.FitnessConfigRequest;
import com.coveiot.coveaccess.fitness.config.models.responsemodel.FitnessConfigResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.onboarding.model.UpdateProfilePictureReq;
import com.coveiot.coveaccess.onboarding.model.UpdateProfilePictureRes;
import com.coveiot.coveaccess.onboarding.model.UpdateUserReq;
import com.coveiot.coveaccess.onboarding.model.UpdateUserRes;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.coveiot.coveaccess.weeklyreport.WeeklyReportApiManager;
import com.coveiot.coveaccess.weeklyreport.request.WeeklyReportUnsubscribeReq;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.NameDetails;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.covepreferences.data.WeeklyReportPrefData;
import com.coveiot.utils.model.FailureType;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.gson.JsonObject;
import com.jstyle.blesdk1860.constant.BleConst;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwx.HeaderParameterNames;
/* loaded from: classes5.dex */
public final class MyAccountViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5189a;
    @NotNull
    public final String b;
    @Nullable
    public File c;
    @Nullable
    public String d;
    public String date_var;
    public DialogListener dialogListener;
    @Nullable
    public String e;
    @Nullable
    public String f;
    @Nullable
    public String g;
    @Nullable
    public String h;
    @NotNull
    public MutableLiveData<String> i;
    @Nullable
    public MutableLiveData<HeightWeightDob> j;
    @Nullable
    public String k;
    public Date l;
    @Nullable
    public String m;
    public String month_var;
    @NotNull
    public ArrayList<String> n;
    @NotNull
    public ArrayList<String> o;
    public OnInternetBleCheckListener onInternetBleCheckListener;
    public OnSuccessListener onSuccessListener;
    @NotNull
    public ArrayList<String> p;
    @NotNull
    public String q;
    @NotNull
    public String r;
    @NotNull
    public final String[] s;
    @Nullable
    public Calendar t;
    public int u;
    public UpdateDobListener updateDobListener;
    public int v;
    public ContractorMyAccount view;
    public int w;

    public MyAccountViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5189a = context;
        this.b = "MyAccountViewModel";
        this.e = BleConst.GetDeviceTime;
        this.i = new MutableLiveData<>();
        this.j = new MutableLiveData<>();
        this.n = new ArrayList<>();
        this.o = new ArrayList<>();
        this.p = new ArrayList<>();
        this.q = "1900";
        this.r = "1";
        this.s = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        MutableLiveData<HeightWeightDob> mutableLiveData = new MutableLiveData<>();
        this.j = mutableLiveData;
        mutableLiveData.setValue(new HeightWeightDob());
    }

    public final void a(final UpdateUserReq updateUserReq, final File file, final FitnessConfigRequest fitnessConfigRequest, final FragmentFinalProfileViewModel.SubmitClickListner submitClickListner) {
        getDialogListener().onShowProgressDialog();
        CoveOnboarding.updateUser(updateUserReq, new CoveApiListener<UpdateUserRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.MyAccountViewModel$callUpdateUserAPI$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                if (coveApiErrorModel != null) {
                    if (coveApiErrorModel.getCode() != Integer.parseInt(AppConstants.SESSION_EXPIRE.getValue())) {
                        FragmentFinalProfileViewModel.SubmitClickListner submitClickListner2 = submitClickListner;
                        FailureType failureType = FailureType.API_ERROR;
                        String string = MyAccountViewModel.this.getContext().getString(R.string.somethings_went_wrong);
                        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.somethings_went_wrong)");
                        submitClickListner2.onFailure(failureType, string);
                    }
                    MyAccountViewModel.this.getDialogListener().onDismiss();
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable UpdateUserRes updateUserRes) {
                if (updateUserRes != null) {
                    MyAccountViewModel.this.c(updateUserReq, file, fitnessConfigRequest, submitClickListner);
                }
            }
        });
    }

    public final void b(UpdateUserReq updateUserReq, FitnessConfigRequest fitnessConfigRequest, UpdateProfilePictureRes updateProfilePictureRes) {
        ProfileData profileData = ProfileData.getInstance();
        profileData.setName(updateUserReq.getName());
        profileData.setEmail(updateUserReq.getEmailId());
        profileData.setGender(updateUserReq.getGender());
        profileData.setDob(updateUserReq.getBirthDate());
        String userId = updateUserReq.getUserId();
        Intrinsics.checkNotNullExpressionValue(userId, "reqModel.userId");
        profileData.setUserId(Integer.parseInt(userId));
        profileData.setHeight(fitnessConfigRequest.getHeight());
        profileData.setWeight(fitnessConfigRequest.getWeight());
        profileData.setMobileNumber(getMobileNumber());
        profileData.setDpUrl(updateProfilePictureRes.getDpUrl());
        NameDetails nameDetails = SessionManager.getInstance(this.f5189a).getNameDetails();
        if (nameDetails == null) {
            nameDetails = new NameDetails();
        }
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
        SessionManager sessionManager = SessionManager.getInstance(this.f5189a);
        sessionManager.createLoginSession(profileData);
        sessionManager.saveNameDetails(nameDetails);
    }

    public final void c(final UpdateUserReq updateUserReq, File file, final FitnessConfigRequest fitnessConfigRequest, final FragmentFinalProfileViewModel.SubmitClickListner submitClickListner) {
        if (AppUtils.isNetConnected(this.f5189a)) {
            CoveOnboarding.updateProfilePicture(new UpdateProfilePictureReq(file), new CoveApiListener<UpdateProfilePictureRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.MyAccountViewModel$updatedpApi$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    if (coveApiErrorModel != null) {
                        MyAccountViewModel.this.getDialogListener().onDismiss();
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable UpdateProfilePictureRes updateProfilePictureRes) {
                    if (updateProfilePictureRes != null) {
                        MyAccountViewModel.this.b(updateUserReq, fitnessConfigRequest, updateProfilePictureRes);
                        MyAccountViewModel.this.saveFitnessConfig(fitnessConfigRequest, submitClickListner);
                    }
                }
            });
            return;
        }
        getDialogListener().onDismiss();
        FailureType failureType = FailureType.NO_NETWORK;
        String string = this.f5189a.getString(R.string.noconnection);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.noconnection)");
        submitClickListner.onFailure(failureType, string);
    }

    @NotNull
    public final Context getContext() {
        return this.f5189a;
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
        return this.m;
    }

    @NotNull
    public final ArrayList<String> getDays() {
        return this.n;
    }

    @NotNull
    public final DialogListener getDialogListener() {
        DialogListener dialogListener = this.dialogListener;
        if (dialogListener != null) {
            return dialogListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dialogListener");
        return null;
    }

    @Nullable
    public final String getEmail() {
        return this.k;
    }

    @NotNull
    public final MutableLiveData<String> getGender() {
        return this.i;
    }

    @Nullable
    public final MutableLiveData<HeightWeightDob> getHeightWeightDob() {
        return this.j;
    }

    public final int getMDayItemPosition() {
        return this.u;
    }

    public final int getMMonthSelectedPosition() {
        return this.v;
    }

    public final int getMYearSelectedPosition() {
        return this.w;
    }

    @Nullable
    public final String getMobileNumber() {
        return this.h;
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
        return this.o;
    }

    @Nullable
    public final String getName() {
        return this.d;
    }

    @NotNull
    public final OnInternetBleCheckListener getOnInternetBleCheckListener() {
        OnInternetBleCheckListener onInternetBleCheckListener = this.onInternetBleCheckListener;
        if (onInternetBleCheckListener != null) {
            return onInternetBleCheckListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onInternetBleCheckListener");
        return null;
    }

    @NotNull
    public final OnSuccessListener getOnSuccessListener() {
        OnSuccessListener onSuccessListener = this.onSuccessListener;
        if (onSuccessListener != null) {
            return onSuccessListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onSuccessListener");
        return null;
    }

    @NotNull
    public final String getOne() {
        return this.r;
    }

    @Nullable
    public final File getProfilePic() {
        return this.c;
    }

    @Nullable
    public final String getRunStrideLength() {
        return this.g;
    }

    @NotNull
    public final String getStarting_year() {
        return this.q;
    }

    @NotNull
    public final String getTAG() {
        return this.b;
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
    public final ContractorMyAccount getView() {
        ContractorMyAccount contractorMyAccount = this.view;
        if (contractorMyAccount != null) {
            return contractorMyAccount;
        }
        Intrinsics.throwUninitializedPropertyAccessException(Promotion.ACTION_VIEW);
        return null;
    }

    @Nullable
    public final String getWalkStrideLength() {
        return this.f;
    }

    @NotNull
    public final ArrayList<String> getYears() {
        return this.p;
    }

    public final void initializeDob(@NotNull String dobEditText) {
        Intrinsics.checkNotNullParameter(dobEditText, "dobEditText");
        this.t = Calendar.getInstance();
        if (!AppUtils.isEmpty(dobEditText)) {
            try {
                Date parse = AppUtils.getSimpleDateFormat("d MMM yyyy").parse(kotlin.text.m.replace$default(kotlin.text.m.replace$default(kotlin.text.m.replace$default(kotlin.text.m.replace$default(dobEditText, "rd", "", false, 4, (Object) null), "st", "", false, 4, (Object) null), "nd", "", false, 4, (Object) null), "th", "", false, 4, (Object) null));
                Calendar calendar = this.t;
                Intrinsics.checkNotNull(calendar);
                calendar.setTime(parse);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            Calendar calendar2 = this.t;
            Intrinsics.checkNotNull(calendar2);
            calendar2.add(1, -Integer.parseInt(AppConstants.MIN_AGE_RESTRICTION.getValue()));
        }
        this.l = new Date();
        Calendar calendar3 = this.t;
        Intrinsics.checkNotNull(calendar3);
        setDate_var(String.valueOf(calendar3.get(5)));
        PayUtils payUtils = PayUtils.INSTANCE;
        Calendar calendar4 = this.t;
        Intrinsics.checkNotNull(calendar4);
        setMonth_var(payUtils.getMonth(String.valueOf(calendar4.get(2) + 1)));
        Calendar calendar5 = this.t;
        Intrinsics.checkNotNull(calendar5);
        String.valueOf(calendar5.get(1));
        Calendar calendar6 = this.t;
        Intrinsics.checkNotNull(calendar6);
        this.q = String.valueOf(calendar6.get(1));
        Calendar calendar7 = this.t;
        Intrinsics.checkNotNull(calendar7);
        setDateOfBirth(AppUtils.formatDate(calendar7.getTime(), "yyyy-MM-dd"));
    }

    public final boolean isLeapYear(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i);
        return calendar.getActualMaximum(6) > 365;
    }

    public final void oDobOkAction() {
        SimpleDateFormat simpleDateFormat;
        int monthFromStringMMM = !CoveUtil.isEmpty(getMonth_var()) ? PayUtils.INSTANCE.getMonthFromStringMMM(getMonth_var()) : 0;
        this.m = kotlin.text.m.replace$default(kotlin.text.m.replace$default(kotlin.text.m.replace$default(kotlin.text.m.replace$default(getDate_var(), "rd", "", false, 4, (Object) null), "st", "", false, 4, (Object) null), "nd", "", false, 4, (Object) null), "th", "", false, 4, (Object) null);
        PayUtils payUtils = PayUtils.INSTANCE;
        int parseInt = Integer.parseInt(this.q);
        String str = this.m;
        Intrinsics.checkNotNull(str);
        int parseInt2 = Integer.parseInt(payUtils.getAge(parseInt, monthFromStringMMM, Integer.parseInt(str)));
        if (3 <= parseInt2 && parseInt2 < 129) {
            getUpdateDobListener().onUpdate(getDate_var() + ' ' + getMonth_var() + ' ' + this.q);
            Date parse = AppUtils.getSimpleDateFormat("d MMM yyyy").parse(this.m + ' ' + getMonth_var() + ' ' + this.q);
            Intrinsics.checkNotNullExpressionValue(parse, "simpleDateFormat.parse(\"…onth_var $starting_year\")");
            this.l = parse;
            MutableLiveData<HeightWeightDob> mutableLiveData = this.j;
            Date date = null;
            HeightWeightDob value = mutableLiveData != null ? mutableLiveData.getValue() : null;
            if (value != null) {
                Date date2 = this.l;
                if (date2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mDate");
                    date2 = null;
                }
                value.setStrDob(AppUtils.formatDate(date2, "yyyy-MM-dd"));
            }
            try {
                if (kotlin.text.m.endsWith$default(getDate_var(), "1", false, 2, null) && !kotlin.text.m.endsWith$default(getDate_var(), BleConst.GetDeviceVersion, false, 2, null)) {
                    simpleDateFormat = AppUtils.getSimpleDateFormat("d'st' MMM yyyy");
                    Intrinsics.checkNotNullExpressionValue(simpleDateFormat, "getSimpleDateFormat(\"d'st' MMM yyyy\")");
                } else if (kotlin.text.m.endsWith$default(getDate_var(), "2", false, 2, null) && !kotlin.text.m.endsWith$default(getDate_var(), BleConst.CMD_Reset, false, 2, null)) {
                    simpleDateFormat = AppUtils.getSimpleDateFormat("d'nd' MMM yyyy");
                    Intrinsics.checkNotNullExpressionValue(simpleDateFormat, "getSimpleDateFormat(\"d'nd' MMM yyyy\")");
                } else if (kotlin.text.m.endsWith$default(getDate_var(), "3", false, 2, null) && !kotlin.text.m.endsWith$default(getDate_var(), BleConst.CMD_MCUReset, false, 2, null)) {
                    simpleDateFormat = AppUtils.getSimpleDateFormat("d'rd' MMM yyyy");
                    Intrinsics.checkNotNullExpressionValue(simpleDateFormat, "getSimpleDateFormat(\"d'rd' MMM yyyy\")");
                } else {
                    simpleDateFormat = AppUtils.getSimpleDateFormat("d'th' MMM yyyy");
                    Intrinsics.checkNotNullExpressionValue(simpleDateFormat, "getSimpleDateFormat(\"d'th' MMM yyyy\")");
                }
                Date date3 = this.l;
                if (date3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mDate");
                } else {
                    date = date3;
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
        Toast.makeText(this.f5189a, (int) R.string.age_confirmation, 1).show();
    }

    /* JADX WARN: Code restructure failed: missing block: B:125:0x0376, code lost:
        if (com.coveiot.utils.utility.AppUtils.isEmpty(r12.getStrDob()) != false) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0394, code lost:
        if (com.coveiot.utils.utility.AppUtils.isEmpty(r9.getStrDob()) == false) goto L130;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x03ad, code lost:
        if (com.coveiot.utils.utility.AppUtils.isEmpty(r8) != false) goto L135;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x03c4, code lost:
        if (com.coveiot.utils.utility.AppUtils.isEmpty(r8) == false) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0084, code lost:
        if (r0.subSequence(r4, r3 + 1).toString().length() == 0) goto L214;
     */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0416  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x05f4  */
    /* JADX WARN: Type inference failed for: r7v0, types: [T, com.coveiot.coveaccess.onboarding.model.UpdateUserReq] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onSubmitClicked(@org.jetbrains.annotations.NotNull final com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentFinalProfileViewModel.SubmitClickListner r23, boolean r24) {
        /*
            Method dump skipped, instructions count: 1592
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.viewmodel.MyAccountViewModel.onSubmitClicked(com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentFinalProfileViewModel$SubmitClickListner, boolean):void");
    }

    public final void populateDaysDataInView() {
        this.n.clear();
        int i = 1;
        if (kotlin.text.m.equals(getMonth_var(), "Feb", true) && !isLeapYear(Integer.parseInt(this.q))) {
            while (true) {
                int i2 = i + 1;
                this.n.add(String.valueOf(i));
                if (i2 > 28) {
                    break;
                }
                i = i2;
            }
            if (Integer.parseInt(getDate_var()) > 28) {
                setDate_var(this.r);
            }
        } else if (kotlin.text.m.equals(getMonth_var(), "Feb", true) && isLeapYear(Integer.parseInt(this.q))) {
            while (true) {
                int i3 = i + 1;
                this.n.add(String.valueOf(i));
                if (i3 > 29) {
                    break;
                }
                i = i3;
            }
            if (Integer.parseInt(getDate_var()) > 29) {
                setDate_var(this.r);
            }
        } else if (kotlin.text.m.equals(getMonth_var(), "Apr", true) || kotlin.text.m.equals(getMonth_var(), "Jun", true) || kotlin.text.m.equals(getMonth_var(), "Sep", true) || kotlin.text.m.equals(getMonth_var(), "Nov", true)) {
            while (true) {
                int i4 = i + 1;
                this.n.add(String.valueOf(i));
                if (i4 > 30) {
                    break;
                }
                i = i4;
            }
            if (Integer.parseInt(getDate_var()) > 30) {
                setDate_var(this.r);
            }
        } else {
            while (true) {
                int i5 = i + 1;
                this.n.add(String.valueOf(i));
                if (i5 > 31) {
                    break;
                }
                i = i5;
            }
        }
        this.u = this.n.indexOf(getDate_var());
    }

    public final void populateMonthsDataInView() {
        this.o.clear();
        int length = this.s.length - 1;
        if (length >= 0) {
            int i = 0;
            while (true) {
                this.o.add(this.s[i]);
                if (i == length) {
                    break;
                }
                i++;
            }
        }
        this.v = this.o.indexOf(getMonth_var());
    }

    public final void populateYearsDataInView() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(1, -Integer.parseInt(AppConstants.MIN_AGE_RESTRICTION.getValue()));
        this.p.clear();
        int parseInt = Integer.parseInt(String.valueOf(calendar.get(1)));
        int i = 1900;
        if (1900 <= parseInt) {
            while (true) {
                this.p.add(String.valueOf(i));
                if (i == parseInt) {
                    break;
                }
                i++;
            }
        }
        this.w = this.p.indexOf(this.q);
    }

    public final void saveFitnessConfig(@NotNull final FitnessConfigRequest reqModel, @NotNull final FragmentFinalProfileViewModel.SubmitClickListner listner) {
        Intrinsics.checkNotNullParameter(reqModel, "reqModel");
        Intrinsics.checkNotNullParameter(listner, "listner");
        if (AppUtils.isNetConnected(this.f5189a)) {
            FitnessConfigApi.saveFitnessConfigData(reqModel, new CoveApiListener<FitnessConfigResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.MyAccountViewModel$saveFitnessConfig$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                }

                /* JADX WARN: Code restructure failed: missing block: B:33:0x0185, code lost:
                    if (r0.equals(r1.getWeight()) == false) goto L33;
                 */
                @Override // com.coveiot.coveaccess.CoveApiListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void onSuccess(@org.jetbrains.annotations.Nullable com.coveiot.coveaccess.fitness.config.models.responsemodel.FitnessConfigResponse r9) {
                    /*
                        Method dump skipped, instructions count: 707
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.more.viewmodel.MyAccountViewModel$saveFitnessConfig$1.onSuccess(com.coveiot.coveaccess.fitness.config.models.responsemodel.FitnessConfigResponse):void");
                }
            });
            return;
        }
        FailureType failureType = FailureType.NO_NETWORK;
        String string = this.f5189a.getString(R.string.noconnection);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.noconnection)");
        listner.onFailure(failureType, string);
    }

    public final void setContractInterface(@NotNull ContractorMyAccount contractorMyAccount) {
        Intrinsics.checkNotNullParameter(contractorMyAccount, "contractorMyAccount");
        setView(contractorMyAccount);
    }

    public final void setDateOfBirth(@Nullable String str) {
        MutableLiveData<HeightWeightDob> mutableLiveData = this.j;
        Intrinsics.checkNotNull(mutableLiveData);
        HeightWeightDob value = mutableLiveData.getValue();
        Intrinsics.checkNotNull(value);
        value.setStrDob(str);
        MutableLiveData<HeightWeightDob> mutableLiveData2 = this.j;
        Intrinsics.checkNotNull(mutableLiveData2);
        MutableLiveData<HeightWeightDob> mutableLiveData3 = this.j;
        Intrinsics.checkNotNull(mutableLiveData3);
        mutableLiveData2.postValue(mutableLiveData3.getValue());
    }

    public final void setDate_var(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.date_var = str;
    }

    public final void setDate_var_replaced(@Nullable String str) {
        this.m = str;
    }

    public final void setDays(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.n = arrayList;
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }

    public final void setEmail(@Nullable String str) {
        this.k = str;
    }

    public final void setGender(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.i = mutableLiveData;
    }

    public final void setHeight(@Nullable String str) {
        String str2;
        if (str != null) {
            String string = this.f5189a.getString(R.string.cms);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.cms)");
            String replace$default = kotlin.text.m.replace$default(str, string, AppConstants.EMPTY_STRING.getValue(), false, 4, (Object) null);
            if (replace$default != null) {
                str2 = StringsKt__StringsKt.trim(replace$default).toString();
                MutableLiveData<HeightWeightDob> mutableLiveData = this.j;
                Intrinsics.checkNotNull(mutableLiveData);
                HeightWeightDob value = mutableLiveData.getValue();
                Intrinsics.checkNotNull(value);
                value.setHeight(str2);
                MutableLiveData<HeightWeightDob> mutableLiveData2 = this.j;
                Intrinsics.checkNotNull(mutableLiveData2);
                MutableLiveData<HeightWeightDob> mutableLiveData3 = this.j;
                Intrinsics.checkNotNull(mutableLiveData3);
                mutableLiveData2.postValue(mutableLiveData3.getValue());
            }
        }
        str2 = null;
        MutableLiveData<HeightWeightDob> mutableLiveData4 = this.j;
        Intrinsics.checkNotNull(mutableLiveData4);
        HeightWeightDob value2 = mutableLiveData4.getValue();
        Intrinsics.checkNotNull(value2);
        value2.setHeight(str2);
        MutableLiveData<HeightWeightDob> mutableLiveData22 = this.j;
        Intrinsics.checkNotNull(mutableLiveData22);
        MutableLiveData<HeightWeightDob> mutableLiveData32 = this.j;
        Intrinsics.checkNotNull(mutableLiveData32);
        mutableLiveData22.postValue(mutableLiveData32.getValue());
    }

    public final void setHeightWeightDob(@Nullable MutableLiveData<HeightWeightDob> mutableLiveData) {
        this.j = mutableLiveData;
    }

    public final void setMDayItemPosition(int i) {
        this.u = i;
    }

    public final void setMMonthSelectedPosition(int i) {
        this.v = i;
    }

    public final void setMYearSelectedPosition(int i) {
        this.w = i;
    }

    public final void setMobileNumber(@NotNull String mobile) {
        Intrinsics.checkNotNullParameter(mobile, "mobile");
        this.h = mobile;
    }

    public final void setMonth_var(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.month_var = str;
    }

    public final void setMonths(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.o = arrayList;
    }

    public final void setName(@Nullable String str) {
        this.d = str;
    }

    public final void setOnInternetBleCheckListener(@NotNull OnInternetBleCheckListener onInternetBleCheckListener) {
        Intrinsics.checkNotNullParameter(onInternetBleCheckListener, "<set-?>");
        this.onInternetBleCheckListener = onInternetBleCheckListener;
    }

    public final void setOnSuccessListener(@NotNull OnSuccessListener onSuccessListener) {
        Intrinsics.checkNotNullParameter(onSuccessListener, "<set-?>");
        this.onSuccessListener = onSuccessListener;
    }

    public final void setOne(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.r = str;
    }

    public final void setProfilePic(@Nullable File file) {
        this.c = file;
    }

    public final void setRunStrideLength(@Nullable String str) {
        if (!AppUtils.isEmpty(str)) {
            this.g = str;
        } else {
            this.g = null;
        }
    }

    public final void setStarting_year(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.q = str;
    }

    public final void setUpdateDobListener(@NotNull UpdateDobListener updateDobListener) {
        Intrinsics.checkNotNullParameter(updateDobListener, "<set-?>");
        this.updateDobListener = updateDobListener;
    }

    public final void setUserId(@NotNull String userIdStr) {
        Intrinsics.checkNotNullParameter(userIdStr, "userIdStr");
        this.e = userIdStr;
    }

    public final void setView(@NotNull ContractorMyAccount contractorMyAccount) {
        Intrinsics.checkNotNullParameter(contractorMyAccount, "<set-?>");
        this.view = contractorMyAccount;
    }

    public final void setWalkStrideLength(@Nullable String str) {
        if (!AppUtils.isEmpty(str)) {
            this.f = str;
        } else {
            this.f = null;
        }
    }

    public final void setWeight(@Nullable String str) {
        String str2;
        if (str != null) {
            String string = this.f5189a.getString(R.string.kgs);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.kgs)");
            String replace$default = kotlin.text.m.replace$default(str, string, AppConstants.EMPTY_STRING.getValue(), false, 4, (Object) null);
            if (replace$default != null) {
                str2 = StringsKt__StringsKt.trim(replace$default).toString();
                MutableLiveData<HeightWeightDob> mutableLiveData = this.j;
                Intrinsics.checkNotNull(mutableLiveData);
                HeightWeightDob value = mutableLiveData.getValue();
                Intrinsics.checkNotNull(value);
                value.setWeight(str2);
                MutableLiveData<HeightWeightDob> mutableLiveData2 = this.j;
                Intrinsics.checkNotNull(mutableLiveData2);
                MutableLiveData<HeightWeightDob> mutableLiveData3 = this.j;
                Intrinsics.checkNotNull(mutableLiveData3);
                mutableLiveData2.postValue(mutableLiveData3.getValue());
            }
        }
        str2 = null;
        MutableLiveData<HeightWeightDob> mutableLiveData4 = this.j;
        Intrinsics.checkNotNull(mutableLiveData4);
        HeightWeightDob value2 = mutableLiveData4.getValue();
        Intrinsics.checkNotNull(value2);
        value2.setWeight(str2);
        MutableLiveData<HeightWeightDob> mutableLiveData22 = this.j;
        Intrinsics.checkNotNull(mutableLiveData22);
        MutableLiveData<HeightWeightDob> mutableLiveData32 = this.j;
        Intrinsics.checkNotNull(mutableLiveData32);
        mutableLiveData22.postValue(mutableLiveData32.getValue());
    }

    public final void setYears(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.p = arrayList;
    }

    public final void unSubscribeEmail() {
        WeeklyReportUnsubscribeReq weeklyReportUnsubscribeReq = new WeeklyReportUnsubscribeReq();
        WeeklyReportUnsubscribeReq.Subscription subscription = new WeeklyReportUnsubscribeReq.Subscription();
        ArrayList arrayList = new ArrayList();
        subscription.setMedium(WeeklyReportConstant.EMAIL.getValue());
        subscription.setTopic(WeeklyReportConstant.WEEKLY_FITNESS_REPORT.getValue());
        subscription.setUnsubscribeReason(this.f5189a.getResources().getString(R.string.no_longer_want_this_email));
        arrayList.add(subscription);
        weeklyReportUnsubscribeReq.setSubscriptions(arrayList);
        WeeklyReportApiManager.unsubscribeFromWeeklyReport(weeklyReportUnsubscribeReq, new CoveApiListener<JsonObject, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.MyAccountViewModel$unSubscribeEmail$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                MyAccountViewModel.this.getOnSuccessListener().onSuccess(false);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull JsonObject jsonObject) {
                Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
                WeeklyReportPrefData weeklyReportData = UserDataManager.getInstance(MyAccountViewModel.this.getContext()).getWeeklyReportData();
                if (weeklyReportData == null) {
                    weeklyReportData = WeeklyReportPrefData.getInstance();
                }
                weeklyReportData.setSubscribed(false);
                weeklyReportData.setEmailVerified(false);
                UserDataManager.getInstance(MyAccountViewModel.this.getContext()).saveWeeklyReport(weeklyReportData);
                MyAccountViewModel.this.getOnSuccessListener().onSuccess(true);
                LogHelper.d(HeaderParameterNames.AUTHENTICATION_TAG, "unsubscribe from myprofile  *** " + jsonObject.get(Constants.KEY_MESSAGE));
            }
        });
    }

    public final void updateProfileApi(boolean z, @NotNull UpdateUserReq reqModel, @Nullable File file, @NotNull FitnessConfigRequest fitnessConfigReq, @NotNull FragmentFinalProfileViewModel.SubmitClickListner listner) {
        Intrinsics.checkNotNullParameter(reqModel, "reqModel");
        Intrinsics.checkNotNullParameter(fitnessConfigReq, "fitnessConfigReq");
        Intrinsics.checkNotNullParameter(listner, "listner");
        if (AppUtils.isNetConnected(this.f5189a)) {
            if (z || !AppUtils.isBluetoothEnabled(this.f5189a)) {
                if (!z) {
                    getDialogListener().onDismiss();
                    OnInternetBleCheckListener onInternetBleCheckListener = getOnInternetBleCheckListener();
                    String string = this.f5189a.getResources().getString(R.string.bluetooth_off);
                    Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…g(R.string.bluetooth_off)");
                    String string2 = this.f5189a.getResources().getString(R.string.bluetooth_off_message);
                    Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…ng.bluetooth_off_message)");
                    onInternetBleCheckListener.onCheck(string, string2, true);
                    return;
                }
                a(reqModel, file, fitnessConfigReq, listner);
                return;
            } else if (BleApiManager.getInstance(this.f5189a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                a(reqModel, file, fitnessConfigReq, listner);
                return;
            } else {
                getDialogListener().onDismiss();
                OnInternetBleCheckListener onInternetBleCheckListener2 = getOnInternetBleCheckListener();
                String string3 = this.f5189a.getString(R.string.band_not_connected);
                Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.band_not_connected)");
                String string4 = this.f5189a.getString(R.string.there_is_a_change_in_height);
                Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.stri…re_is_a_change_in_height)");
                onInternetBleCheckListener2.onCheck(string3, string4, true);
                return;
            }
        }
        getDialogListener().onDismiss();
        FailureType failureType = FailureType.NO_NETWORK;
        String string5 = this.f5189a.getString(R.string.noconnection);
        Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.string.noconnection)");
        listner.onFailure(failureType, string5);
    }

    @Nullable
    /* renamed from: getGender  reason: collision with other method in class */
    public final String m106getGender() {
        MutableLiveData<String> mutableLiveData = this.i;
        Intrinsics.checkNotNull(mutableLiveData);
        return mutableLiveData.getValue();
    }

    public final void setGender(@Nullable String str) {
        this.i.setValue(str);
    }
}
