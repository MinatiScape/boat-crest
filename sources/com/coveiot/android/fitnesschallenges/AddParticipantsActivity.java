package com.coveiot.android.fitnesschallenges;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.fitnessbuddies.databinding.ActivityAddBuddiesNewBinding;
import com.coveiot.android.fitnessbuddies.utils.CoveContactsManager;
import com.coveiot.android.fitnessbuddies.utils.PermissionUtils;
import com.coveiot.android.fitnessbuddies.viewmodels.AddBuddiesViewModel;
import com.coveiot.android.fitnesschallenges.adpter.FitnessChallengeShowContactsAdapter;
import com.coveiot.android.fitnesschallenges.utils.ExtensionsKt;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeCleverTapConstants;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.fitnesschallenges.viewModel.CreateChallengeViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleAndMessageWatchFace;
import com.coveiot.android.theme.BottomSheetDialogSuccessImageTitleMessage;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.CommonMessageDialog;
import com.coveiot.android.theme.LoadingDialog;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.coveaccess.fitnesschallenge.model.AddParticipantsReq;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail;
import com.coveiot.coveaccess.fitnesschallenge.model.CreateFitnessChallengeReq;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.utility.AppUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class AddParticipantsActivity extends BaseActivity implements FitnessChallengeShowContactsAdapter.OnBuddiesSelectedListener, SuccessResultListener {
    @Nullable
    public String A;
    public AddBuddiesViewModel B;
    public CreateChallengeViewModel D;
    public int E;
    @Nullable
    public BuddiesChallengeDetail F;
    public boolean G;
    @Nullable
    public BottomSheetDialogSuccessImageTitleMessage H;
    @Nullable
    public BottomSheetDialogImageTitleAndMessageWatchFace I;
    public ActivityAddBuddiesNewBinding p;
    @Nullable
    public LoadingDialog q;
    @Nullable
    public List<CoveContact> s;
    @Nullable
    public List<CoveContact> t;
    @Nullable
    public FitnessChallengeShowContactsAdapter v;
    @Nullable
    public FitnessChallengeShowContactsAdapter w;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int r = 1;
    @Nullable
    public List<CoveContact> u = new ArrayList();
    @NotNull
    public ArrayList<CoveContact> x = new ArrayList<>();
    @NotNull
    public ArrayList<CoveContact> y = new ArrayList<>();
    @NotNull
    public Handler z = new Handler();
    @NotNull
    public CreateFitnessChallengeReq C = new CreateFitnessChallengeReq();

    /* loaded from: classes2.dex */
    public final class RetrieveContactsTask extends AsyncTask<Void, Void, List<CoveContact>> {
        public RetrieveContactsTask() {
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            super.onPreExecute();
            if (AddParticipantsActivity.this.isFinishing()) {
                return;
            }
            LoadingDialog loadingDialog = AddParticipantsActivity.this.q;
            Intrinsics.checkNotNull(loadingDialog);
            if (loadingDialog.isShowing()) {
                return;
            }
            LoadingDialog loadingDialog2 = AddParticipantsActivity.this.q;
            Intrinsics.checkNotNull(loadingDialog2);
            loadingDialog2.show();
        }

        @Override // android.os.AsyncTask
        @NotNull
        public List<CoveContact> doInBackground(@NotNull Void... voids) {
            Intrinsics.checkNotNullParameter(voids, "voids");
            ArrayList<CoveContact> contacts = AppUtils.getContacts(AddParticipantsActivity.this);
            Intrinsics.checkNotNullExpressionValue(contacts, "getContacts(this@AddParticipantsActivity)");
            return contacts;
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(@NotNull List<CoveContact> coveContacts) {
            Intrinsics.checkNotNullParameter(coveContacts, "coveContacts");
            super.onPostExecute((RetrieveContactsTask) coveContacts);
            if (!AddParticipantsActivity.this.isFinishing()) {
                AddParticipantsActivity.this.L(coveContacts);
            }
            CoveContactsManager.INSTANCE.setContacts(coveContacts);
        }
    }

    public static final void C(AddParticipantsActivity this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list != null && list.size() > 0) {
            ArrayList<CoveContact> arrayList = (ArrayList) list;
            this$0.x = arrayList;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                CoveContact coveContact = this$0.x.get(i);
                Intrinsics.checkNotNullExpressionValue(coveContact, "activeContacts[i]");
                if (this$0.H(coveContact)) {
                    this$0.x.get(i).setSelected(true);
                }
            }
            CoveContactsManager.INSTANCE.setActiveContacts(list);
        }
        this$0.R();
    }

    public static final void D(AddParticipantsActivity this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list != null && list.size() > 0) {
            ArrayList<CoveContact> arrayList = (ArrayList) list;
            this$0.y = arrayList;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                CoveContact coveContact = this$0.y.get(i);
                Intrinsics.checkNotNullExpressionValue(coveContact, "inActiveContacts[i]");
                if (this$0.H(coveContact)) {
                    this$0.y.get(i).setSelected(true);
                }
            }
            CoveContactsManager.INSTANCE.setInActiveContacts(list);
        }
        this$0.R();
    }

    public static final void G(AddParticipantsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.E == 2) {
            this$0.K();
        } else {
            this$0.Q();
        }
    }

    public static final void M(AddParticipantsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (ContextCompat.checkSelfPermission(this$0, "android.permission.READ_CONTACTS") != 0) {
            this$0.E();
        } else {
            this$0.refreshClicked();
        }
    }

    public static final void N(AddParticipantsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.O();
    }

    public static final void U(AddParticipantsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace = this$0.I;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace);
        bottomSheetDialogImageTitleAndMessageWatchFace.dismiss();
        this$0.O();
    }

    public static final void V(AddParticipantsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace = this$0.I;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace);
        bottomSheetDialogImageTitleAndMessageWatchFace.dismiss();
        this$0.finish();
    }

    public static final void W(AddParticipantsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace = this$0.I;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace);
        bottomSheetDialogImageTitleAndMessageWatchFace.dismiss();
    }

    public static final void Y(AddParticipantsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissProgress();
        BottomSheetDialogSuccessImageTitleMessage bottomSheetDialogSuccessImageTitleMessage = this$0.H;
        if (bottomSheetDialogSuccessImageTitleMessage != null) {
            bottomSheetDialogSuccessImageTitleMessage.dismiss();
        }
        Intent intent = new Intent();
        intent.putExtra(FitnessChallengeConstants.CHALLENGE_SUCCESS, true);
        this$0.setResult(-1, intent);
        this$0.finish();
    }

    public static final void a0(AddParticipantsActivity this$0, CommonMessageDialog commonMessageDialog) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(commonMessageDialog, "$commonMessageDialog");
        this$0.dismissProgress();
        commonMessageDialog.dismiss();
        this$0.finish();
    }

    public static final boolean f0(AddParticipantsActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FitnessChallengeShowContactsAdapter fitnessChallengeShowContactsAdapter = this$0.v;
        Intrinsics.checkNotNull(fitnessChallengeShowContactsAdapter);
        fitnessChallengeShowContactsAdapter.filter(null);
        FitnessChallengeShowContactsAdapter fitnessChallengeShowContactsAdapter2 = this$0.w;
        Intrinsics.checkNotNull(fitnessChallengeShowContactsAdapter2);
        fitnessChallengeShowContactsAdapter2.filter(null);
        return false;
    }

    public final void B() {
        AddBuddiesViewModel addBuddiesViewModel = this.B;
        AddBuddiesViewModel addBuddiesViewModel2 = null;
        if (addBuddiesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            addBuddiesViewModel = null;
        }
        addBuddiesViewModel.getGetActiveContacts().observe(this, new Observer() { // from class: com.coveiot.android.fitnesschallenges.v
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AddParticipantsActivity.C(AddParticipantsActivity.this, (List) obj);
            }
        });
        AddBuddiesViewModel addBuddiesViewModel3 = this.B;
        if (addBuddiesViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            addBuddiesViewModel2 = addBuddiesViewModel3;
        }
        addBuddiesViewModel2.getGetInActiveContacts().observe(this, new Observer() { // from class: com.coveiot.android.fitnesschallenges.w
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AddParticipantsActivity.D(AddParticipantsActivity.this, (List) obj);
            }
        });
    }

    public final void E() {
        PermissionUtils.INSTANCE.checkPermission(this, "android.permission.READ_CONTACTS", new AddParticipantsActivity$getContactList$1(this));
    }

    public final String F(Context context) {
        String networkCountryIso;
        try {
            Object systemService = context.getSystemService("phone");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
            TelephonyManager telephonyManager = (TelephonyManager) systemService;
            String simCountryIso = telephonyManager.getSimCountryIso();
            if (simCountryIso != null && simCountryIso.length() == 2) {
                String upperCase = simCountryIso.toUpperCase();
                Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
                return upperCase;
            } else if (telephonyManager.getPhoneType() == 2 || (networkCountryIso = telephonyManager.getNetworkCountryIso()) == null || networkCountryIso.length() != 2) {
                return null;
            } else {
                String upperCase2 = networkCountryIso.toUpperCase();
                Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase()");
                return upperCase2;
            }
        } catch (Exception unused) {
            return null;
        }
    }

    public final boolean H(CoveContact coveContact) {
        List<CoveContact> list = this.u;
        if (!(list == null || list.isEmpty())) {
            List<CoveContact> list2 = this.u;
            Intrinsics.checkNotNull(list2);
            for (CoveContact coveContact2 : list2) {
                if (Intrinsics.areEqual(coveContact2.getId(), coveContact.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void I() {
        HashMap<String, Object> hashMap = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);
        CreateChallengeViewModel createChallengeViewModel = this.D;
        if (createChallengeViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeViewModel");
            createChallengeViewModel = null;
        }
        if (createChallengeViewModel.getChallengeId() != null) {
            String value = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_ID.getValue();
            CreateChallengeViewModel createChallengeViewModel2 = this.D;
            if (createChallengeViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeViewModel");
                createChallengeViewModel2 = null;
            }
            Object challengeId = createChallengeViewModel2.getChallengeId();
            Intrinsics.checkNotNull(challengeId);
            hashMap.put(value, challengeId);
        }
        String name = this.C.getName();
        boolean z = true;
        if (!(name == null || name.length() == 0)) {
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_NAME.getValue(), this.C.getName());
        }
        String description = this.C.getDescription();
        if (!(description == null || description.length() == 0)) {
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_DESCRIPTION.getValue(), this.C.getDescription());
        }
        List<CoveContact> list = this.u;
        if (!(list == null || list.isEmpty())) {
            String value2 = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_PARTICIPANT_COUNT.getValue();
            List<CoveContact> list2 = this.u;
            Intrinsics.checkNotNull(list2);
            hashMap.put(value2, Integer.valueOf(list2.size()));
        } else {
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_PARTICIPANT_COUNT.getValue(), 0);
        }
        hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_TYPE.getValue(), FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CUSTOM.getValue());
        hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_CREATOR.getValue(), FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_SELF.getValue());
        if (this.C.getTargetBaseUnits().equals(FitnessChallengeConstants.METERS)) {
            String value3 = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_DISTANCE.getValue();
            Integer target = this.C.getTarget();
            hashMap.put(value3, Integer.valueOf((target != null ? target.intValue() : 0) / 1000));
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_CALORIE.getValue(), CleverTapConstants.CustomEventValues.NA.getValue());
        } else {
            String value4 = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_CALORIE.getValue();
            Integer target2 = this.C.getTarget();
            hashMap.put(value4, Integer.valueOf(target2 == null ? 0 : target2.intValue()));
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_DISTANCE.getValue(), CleverTapConstants.CustomEventValues.NA.getValue());
        }
        String startDate = this.C.getStartDate();
        if (!(startDate == null || startDate.length() == 0)) {
            String startDate2 = this.C.getStartDate();
            Date parse = startDate2 != null ? simpleDateFormat.parse(startDate2) : null;
            Intrinsics.checkNotNull(parse, "null cannot be cast to non-null type java.util.Date");
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_START_DATE.getValue(), ExtensionsKt.formateDateAndTime(parse, true));
        }
        String endDate = this.C.getEndDate();
        if (endDate != null && endDate.length() != 0) {
            z = false;
        }
        if (!z) {
            String endDate2 = this.C.getEndDate();
            Date parse2 = endDate2 != null ? simpleDateFormat.parse(endDate2) : null;
            Intrinsics.checkNotNull(parse2, "null cannot be cast to non-null type java.util.Date");
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_END_DATE.getValue(), ExtensionsKt.formateDateAndTime(parse2, false));
        }
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_CHALLENGE_CREATE_CHALLENGE_SUCCESS.getValue(), hashMap);
    }

    public final void J(CleverTapConstants.CustomEventProperties customEventProperties) {
        HashMap<String, Object> hashMap = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-d", Locale.ENGLISH);
        BuddiesChallengeDetail buddiesChallengeDetail = this.F;
        Intrinsics.checkNotNull(buddiesChallengeDetail);
        String name = buddiesChallengeDetail.getName();
        boolean z = true;
        if (!(name == null || name.length() == 0)) {
            String value = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_NAME.getValue();
            BuddiesChallengeDetail buddiesChallengeDetail2 = this.F;
            Intrinsics.checkNotNull(buddiesChallengeDetail2);
            hashMap.put(value, buddiesChallengeDetail2.getName());
        }
        BuddiesChallengeDetail buddiesChallengeDetail3 = this.F;
        Intrinsics.checkNotNull(buddiesChallengeDetail3);
        String description = buddiesChallengeDetail3.getDescription();
        if (!(description == null || description.length() == 0)) {
            String value2 = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_DESCRIPTION.getValue();
            BuddiesChallengeDetail buddiesChallengeDetail4 = this.F;
            Intrinsics.checkNotNull(buddiesChallengeDetail4);
            hashMap.put(value2, buddiesChallengeDetail4.getDescription());
        }
        if (this.u != null) {
            String value3 = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_PARTICIPANT_COUNT.getValue();
            List<CoveContact> list = this.u;
            Intrinsics.checkNotNull(list);
            hashMap.put(value3, Integer.valueOf(list.size()));
        }
        hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_TYPE.getValue(), FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CUSTOM.getValue());
        hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_CREATOR.getValue(), FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_SELF.getValue());
        BuddiesChallengeDetail buddiesChallengeDetail5 = this.F;
        Intrinsics.checkNotNull(buddiesChallengeDetail5);
        String targetBaseUnits = buddiesChallengeDetail5.getTargetBaseUnits();
        if (!(targetBaseUnits == null || targetBaseUnits.length() == 0)) {
            BuddiesChallengeDetail buddiesChallengeDetail6 = this.F;
            Intrinsics.checkNotNull(buddiesChallengeDetail6);
            if (buddiesChallengeDetail6.getTargetBaseUnits().equals(FitnessChallengeConstants.METERS)) {
                String value4 = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_DISTANCE.getValue();
                BuddiesChallengeDetail buddiesChallengeDetail7 = this.F;
                Intrinsics.checkNotNull(buddiesChallengeDetail7);
                Integer target = buddiesChallengeDetail7.getTarget();
                hashMap.put(value4, Integer.valueOf((target != null ? target.intValue() : 0) / 1000));
                hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_CALORIE.getValue(), CleverTapConstants.CustomEventValues.NA.getValue());
            } else {
                String value5 = FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_CALORIE.getValue();
                BuddiesChallengeDetail buddiesChallengeDetail8 = this.F;
                Intrinsics.checkNotNull(buddiesChallengeDetail8);
                Integer target2 = buddiesChallengeDetail8.getTarget();
                hashMap.put(value5, Integer.valueOf(target2 == null ? 0 : target2.intValue()));
                hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_GOAL_DISTANCE.getValue(), CleverTapConstants.CustomEventValues.NA.getValue());
            }
        }
        BuddiesChallengeDetail buddiesChallengeDetail9 = this.F;
        Intrinsics.checkNotNull(buddiesChallengeDetail9);
        String startDate = buddiesChallengeDetail9.getStartDate();
        if (!(startDate == null || startDate.length() == 0)) {
            BuddiesChallengeDetail buddiesChallengeDetail10 = this.F;
            Intrinsics.checkNotNull(buddiesChallengeDetail10);
            Date parse = simpleDateFormat.parse(buddiesChallengeDetail10.getStartDate());
            Intrinsics.checkNotNull(parse, "null cannot be cast to non-null type java.util.Date");
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_START_DATE.getValue(), ExtensionsKt.formateDateAndTime(parse, true));
        }
        BuddiesChallengeDetail buddiesChallengeDetail11 = this.F;
        Intrinsics.checkNotNull(buddiesChallengeDetail11);
        String endDate = buddiesChallengeDetail11.getEndDate();
        if (endDate != null && endDate.length() != 0) {
            z = false;
        }
        if (!z) {
            BuddiesChallengeDetail buddiesChallengeDetail12 = this.F;
            Intrinsics.checkNotNull(buddiesChallengeDetail12);
            Date parse2 = simpleDateFormat.parse(buddiesChallengeDetail12.getEndDate());
            Intrinsics.checkNotNull(parse2, "null cannot be cast to non-null type java.util.Date");
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_END_DATE.getValue(), ExtensionsKt.formateDateAndTime(parse2, false));
        }
        if (customEventProperties != null) {
            hashMap.put(FitnessChallengeCleverTapConstants.PARTICIPANTS_ADDED_FROM_SOURCE.getValue(), customEventProperties.getValue());
        }
        hashMap.put(FitnessChallengeCleverTapConstants.PARTICIPANT_ADDED_WHEN_SOURCE.getValue(), FitnessChallengeCleverTapConstants.AFTER_CREATION.getValue());
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_CHALLENGE_ADD_PARTICIPANTS.getValue(), hashMap);
    }

    public final void K() {
        List<CoveContact> list = this.u;
        if (!(list == null || list.isEmpty())) {
            String string = getString(R.string.if_you_go_back_now);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.if_you_go_back_now)");
            T("", string, false);
            return;
        }
        finish();
    }

    public final void L(List<CoveContact> list) {
        if (isFinishing()) {
            return;
        }
        if (!isFinishing()) {
            LoadingDialog loadingDialog = this.q;
            Intrinsics.checkNotNull(loadingDialog);
            if (!loadingDialog.isShowing()) {
                LoadingDialog loadingDialog2 = this.q;
                Intrinsics.checkNotNull(loadingDialog2);
                loadingDialog2.show();
            }
        }
        this.t = new ArrayList();
        for (CoveContact coveContact : list) {
            String phoneNumber = coveContact.getPhoneNumber();
            Intrinsics.checkNotNullExpressionValue(phoneNumber, "i.phoneNumber");
            String d0 = d0(this, phoneNumber);
            if (d0 != null) {
                List<CoveContact> list2 = this.t;
                Intrinsics.checkNotNull(list2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.utils.model.CoveContact>");
                ((ArrayList) list2).add(new CoveContact(coveContact.getId(), coveContact.getName(), d0, coveContact.getRunningContactId()));
            }
        }
        List<CoveContact> list3 = this.t;
        if (list3 != null) {
            AddBuddiesViewModel addBuddiesViewModel = this.B;
            if (addBuddiesViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                addBuddiesViewModel = null;
            }
            addBuddiesViewModel.loadCoveBuddies(list3);
        }
    }

    public final void O() {
        if (!AppUtils.isNetConnected(this)) {
            String string = getResources().getString(R.string.please_check_your_internet);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…ease_check_your_internet)");
            c0(string);
            return;
        }
        List<CoveContact> list = this.u;
        if (list == null || list.isEmpty()) {
            return;
        }
        showProgress();
        ArrayList arrayList = new ArrayList();
        List<CoveContact> list2 = this.u;
        Intrinsics.checkNotNull(list2);
        for (CoveContact coveContact : list2) {
            arrayList.add(new CreateFitnessChallengeReq.Participant(coveContact.getName(), coveContact.getPhoneNumber()));
        }
        this.C.setParticipants(arrayList);
        int i = this.E;
        CreateChallengeViewModel createChallengeViewModel = null;
        if (i == 0) {
            CreateChallengeViewModel createChallengeViewModel2 = this.D;
            if (createChallengeViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeViewModel");
            } else {
                createChallengeViewModel = createChallengeViewModel2;
            }
            createChallengeViewModel.createFitnessChallenge(this.C);
        } else if (i == 1) {
            if (this.A != null) {
                CreateChallengeViewModel createChallengeViewModel3 = this.D;
                if (createChallengeViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeViewModel");
                } else {
                    createChallengeViewModel = createChallengeViewModel3;
                }
                String str = this.A;
                Intrinsics.checkNotNull(str);
                createChallengeViewModel.editFitnessChallenge(str, this.C);
            }
        } else if (this.A != null) {
            ArrayList arrayList2 = new ArrayList();
            List<CoveContact> list3 = this.u;
            Intrinsics.checkNotNull(list3);
            for (CoveContact coveContact2 : list3) {
                arrayList2.add(new AddParticipantsReq.Participants(coveContact2.getName(), coveContact2.getPhoneNumber()));
            }
            AddParticipantsReq addParticipantsReq = new AddParticipantsReq(this.A, arrayList2);
            CreateChallengeViewModel createChallengeViewModel4 = this.D;
            if (createChallengeViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fitnessChallengeViewModel");
            } else {
                createChallengeViewModel = createChallengeViewModel4;
            }
            createChallengeViewModel.addParticipantFitnessChallenge(addParticipantsReq);
        }
    }

    public final String P(String str) {
        String replace$default = kotlin.text.m.replace$default(str, "[^\\d+]", "", false, 4, (Object) null);
        int length = replace$default.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) replace$default.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        boolean startsWith$default = kotlin.text.m.startsWith$default(replace$default.subSequence(i, length + 1).toString(), "+", false, 2, null);
        String replace$default2 = kotlin.text.m.replace$default(str, "[^\\d]", "", false, 4, (Object) null);
        if (startsWith$default) {
            return '+' + replace$default2;
        }
        return replace$default2;
    }

    public final void Q() {
        Intent intent = new Intent();
        List<CoveContact> list = this.u;
        if (!(list == null || list.isEmpty())) {
            intent.putExtra(FitnessChallengeConstants.Companion.getSELECTED_CONTACTS(), new ArrayList(this.u));
            setResult(-1, intent);
        }
        super.onBackPressed();
    }

    public final void R() {
        LoadingDialog loadingDialog = this.q;
        if (loadingDialog != null) {
            Intrinsics.checkNotNull(loadingDialog);
            if (loadingDialog.isShowing()) {
                LoadingDialog loadingDialog2 = this.q;
                Intrinsics.checkNotNull(loadingDialog2);
                loadingDialog2.dismiss();
            }
        }
        ArrayList<CoveContact> arrayList = this.x;
        boolean z = true;
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding = null;
        if (arrayList == null || arrayList.isEmpty()) {
            ArrayList<CoveContact> arrayList2 = this.y;
            if (arrayList2 == null || arrayList2.isEmpty()) {
                ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding2 = this.p;
                if (activityAddBuddiesNewBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityAddBuddiesNewBinding = activityAddBuddiesNewBinding2;
                }
                activityAddBuddiesNewBinding.fitnessChallengeViews.setVisibility(8);
                return;
            }
        }
        ArrayList<CoveContact> arrayList3 = this.x;
        if (!(arrayList3 == null || arrayList3.isEmpty())) {
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding3 = this.p;
            if (activityAddBuddiesNewBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding3 = null;
            }
            activityAddBuddiesNewBinding3.tvActiveHeader.setVisibility(0);
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding4 = this.p;
            if (activityAddBuddiesNewBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding4 = null;
            }
            activityAddBuddiesNewBinding4.rvActiveContactsList.setVisibility(0);
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding5 = this.p;
            if (activityAddBuddiesNewBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding5 = null;
            }
            activityAddBuddiesNewBinding5.tvActiveHeader.setText(getString(R.string.boatheads_in_your_contact) + " (" + this.x.size() + HexStringBuilder.COMMENT_END_CHAR);
            this.v = new FitnessChallengeShowContactsAdapter(this, this.x, this, true);
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding6 = this.p;
            if (activityAddBuddiesNewBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding6 = null;
            }
            activityAddBuddiesNewBinding6.rvActiveContactsList.setAdapter(this.v);
            e0();
        } else {
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding7 = this.p;
            if (activityAddBuddiesNewBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding7 = null;
            }
            activityAddBuddiesNewBinding7.tvActiveHeader.setVisibility(8);
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding8 = this.p;
            if (activityAddBuddiesNewBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding8 = null;
            }
            activityAddBuddiesNewBinding8.rvActiveContactsList.setVisibility(8);
        }
        ArrayList<CoveContact> arrayList4 = this.y;
        if (arrayList4 != null && !arrayList4.isEmpty()) {
            z = false;
        }
        if (!z) {
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding9 = this.p;
            if (activityAddBuddiesNewBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding9 = null;
            }
            activityAddBuddiesNewBinding9.tvInactiveHeader.setVisibility(0);
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding10 = this.p;
            if (activityAddBuddiesNewBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding10 = null;
            }
            activityAddBuddiesNewBinding10.rvInActiveContactsList.setVisibility(0);
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding11 = this.p;
            if (activityAddBuddiesNewBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding11 = null;
            }
            activityAddBuddiesNewBinding11.tvInactiveHeader.setText(getString(R.string.other_contacts) + " (" + this.y.size() + HexStringBuilder.COMMENT_END_CHAR);
            this.w = new FitnessChallengeShowContactsAdapter(this, this.y, this, false);
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding12 = this.p;
            if (activityAddBuddiesNewBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding12 = null;
            }
            activityAddBuddiesNewBinding12.rvInActiveContactsList.setAdapter(this.w);
            e0();
        } else {
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding13 = this.p;
            if (activityAddBuddiesNewBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding13 = null;
            }
            activityAddBuddiesNewBinding13.tvInactiveHeader.setVisibility(8);
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding14 = this.p;
            if (activityAddBuddiesNewBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding14 = null;
            }
            activityAddBuddiesNewBinding14.rvInActiveContactsList.setVisibility(8);
        }
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding15 = this.p;
        if (activityAddBuddiesNewBinding15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddBuddiesNewBinding = activityAddBuddiesNewBinding15;
        }
        activityAddBuddiesNewBinding.fitnessChallengeViews.setVisibility(0);
    }

    public final void S() {
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding = this.p;
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding2 = null;
        if (activityAddBuddiesNewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding = null;
        }
        View findViewById = activityAddBuddiesNewBinding.search.findViewById(R.id.search_src_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.EditText");
        EditText editText = (EditText) findViewById;
        editText.setTextColor(getResources().getColor(R.color.main_text_color));
        editText.setHintTextColor(getResources().getColor(R.color.color_666666));
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding3 = this.p;
        if (activityAddBuddiesNewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding3 = null;
        }
        activityAddBuddiesNewBinding3.search.setIconifiedByDefault(false);
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding4 = this.p;
        if (activityAddBuddiesNewBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddBuddiesNewBinding2 = activityAddBuddiesNewBinding4;
        }
        activityAddBuddiesNewBinding2.search.setQueryHint(getResources().getString(R.string.search_buddy_name_here));
    }

    public final void T(String str, String str2, boolean z) {
        BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace = new BottomSheetDialogImageTitleAndMessageWatchFace(this, str, str2);
        this.I = bottomSheetDialogImageTitleAndMessageWatchFace;
        if (!z) {
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace);
            String string = getString(R.string.save);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.save)");
            bottomSheetDialogImageTitleAndMessageWatchFace.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.q
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AddParticipantsActivity.U(AddParticipantsActivity.this, view);
                }
            });
            BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace2 = this.I;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace2);
            String string2 = getString(R.string.cancel);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.cancel)");
            bottomSheetDialogImageTitleAndMessageWatchFace2.setNegativeButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.s
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AddParticipantsActivity.V(AddParticipantsActivity.this, view);
                }
            });
            BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace3 = this.I;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace3);
            bottomSheetDialogImageTitleAndMessageWatchFace3.show();
            BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace4 = this.I;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace4);
            bottomSheetDialogImageTitleAndMessageWatchFace4.setCancelable(false);
            return;
        }
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace);
        String string3 = getString(R.string.done);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.done)");
        bottomSheetDialogImageTitleAndMessageWatchFace.setDoneButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddParticipantsActivity.W(AddParticipantsActivity.this, view);
            }
        });
        BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace5 = this.I;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace5);
        bottomSheetDialogImageTitleAndMessageWatchFace5.show();
        BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace6 = this.I;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleAndMessageWatchFace6);
        bottomSheetDialogImageTitleAndMessageWatchFace6.setCancelable(false);
    }

    public final void X(String str, String str2) {
        BottomSheetDialogSuccessImageTitleMessage bottomSheetDialogSuccessImageTitleMessage;
        if (this.H == null) {
            BottomSheetDialogSuccessImageTitleMessage bottomSheetDialogSuccessImageTitleMessage2 = new BottomSheetDialogSuccessImageTitleMessage(this, str, str2);
            this.H = bottomSheetDialogSuccessImageTitleMessage2;
            String string = getString(R.string.okay);
            Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…challenges.R.string.okay)");
            bottomSheetDialogSuccessImageTitleMessage2.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.n
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AddParticipantsActivity.Y(AddParticipantsActivity.this, view);
                }
            });
        }
        BottomSheetDialogSuccessImageTitleMessage bottomSheetDialogSuccessImageTitleMessage3 = this.H;
        Boolean valueOf = bottomSheetDialogSuccessImageTitleMessage3 != null ? Boolean.valueOf(bottomSheetDialogSuccessImageTitleMessage3.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (!valueOf.booleanValue() && (bottomSheetDialogSuccessImageTitleMessage = this.H) != null) {
            bottomSheetDialogSuccessImageTitleMessage.show();
        }
        BottomSheetDialogSuccessImageTitleMessage bottomSheetDialogSuccessImageTitleMessage4 = this.H;
        if (bottomSheetDialogSuccessImageTitleMessage4 != null) {
            bottomSheetDialogSuccessImageTitleMessage4.setCancelable(false);
        }
    }

    public final void Z(String str) {
        Window window;
        final CommonMessageDialog commonMessageDialog = new CommonMessageDialog(this, str, false, true);
        commonMessageDialog.show(getSupportFragmentManager(), "");
        Dialog dialog = commonMessageDialog.getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setGravity(80);
        }
        this.z.postDelayed(new Runnable() { // from class: com.coveiot.android.fitnesschallenges.m
            @Override // java.lang.Runnable
            public final void run() {
                AddParticipantsActivity.a0(AddParticipantsActivity.this, commonMessageDialog);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
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

    public final void b0() {
        if (this.s == null) {
            List<CoveContact> contacts = CoveContactsManager.INSTANCE.getContacts();
            if (contacts == null) {
                new RetrieveContactsTask().execute(new Void[0]);
                return;
            }
            ArrayList arrayList = new ArrayList();
            this.s = arrayList;
            Intrinsics.checkNotNull(arrayList);
            arrayList.addAll(contacts);
            List<CoveContact> list = this.s;
            Intrinsics.checkNotNull(list);
            L(list);
        }
    }

    public final void c0(String str) {
        Z(str);
    }

    public final String d0(Context context, String str) {
        try {
            Phonenumber.PhoneNumber parse = PhoneNumberUtil.getInstance().parse(P(str), F(context));
            StringBuilder sb = new StringBuilder();
            sb.append('+');
            sb.append(parse.getCountryCode());
            sb.append(parse.getNationalNumber());
            return sb.toString();
        } catch (NumberParseException unused) {
            return null;
        }
    }

    public final void e0() {
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding = this.p;
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding2 = null;
        if (activityAddBuddiesNewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding = null;
        }
        activityAddBuddiesNewBinding.search.setIconifiedByDefault(false);
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding3 = this.p;
        if (activityAddBuddiesNewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding3 = null;
        }
        activityAddBuddiesNewBinding3.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.coveiot.android.fitnesschallenges.AddParticipantsActivity$watchSearchTextBox$1
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(@NotNull String s) {
                FitnessChallengeShowContactsAdapter fitnessChallengeShowContactsAdapter;
                FitnessChallengeShowContactsAdapter fitnessChallengeShowContactsAdapter2;
                FitnessChallengeShowContactsAdapter fitnessChallengeShowContactsAdapter3;
                FitnessChallengeShowContactsAdapter fitnessChallengeShowContactsAdapter4;
                ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding4;
                FitnessChallengeShowContactsAdapter fitnessChallengeShowContactsAdapter5;
                ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding5;
                Intrinsics.checkNotNullParameter(s, "s");
                fitnessChallengeShowContactsAdapter = AddParticipantsActivity.this.v;
                ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding6 = null;
                if (fitnessChallengeShowContactsAdapter != null) {
                    fitnessChallengeShowContactsAdapter5 = AddParticipantsActivity.this.v;
                    Intrinsics.checkNotNull(fitnessChallengeShowContactsAdapter5);
                    Locale locale = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
                    String lowerCase = s.toLowerCase(locale);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                    int filter = fitnessChallengeShowContactsAdapter5.filter(lowerCase);
                    activityAddBuddiesNewBinding5 = AddParticipantsActivity.this.p;
                    if (activityAddBuddiesNewBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddBuddiesNewBinding5 = null;
                    }
                    activityAddBuddiesNewBinding5.tvActiveHeader.setText(AddParticipantsActivity.this.getString(R.string.boatheads_in_your_contact) + " (" + filter + HexStringBuilder.COMMENT_END_CHAR);
                }
                fitnessChallengeShowContactsAdapter2 = AddParticipantsActivity.this.w;
                if (fitnessChallengeShowContactsAdapter2 != null) {
                    fitnessChallengeShowContactsAdapter3 = AddParticipantsActivity.this.w;
                    Intrinsics.checkNotNull(fitnessChallengeShowContactsAdapter3);
                    Locale locale2 = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale2, "getDefault()");
                    String lowerCase2 = s.toLowerCase(locale2);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                    fitnessChallengeShowContactsAdapter3.filter(lowerCase2);
                    fitnessChallengeShowContactsAdapter4 = AddParticipantsActivity.this.w;
                    Intrinsics.checkNotNull(fitnessChallengeShowContactsAdapter4);
                    Locale locale3 = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale3, "getDefault()");
                    String lowerCase3 = s.toLowerCase(locale3);
                    Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase(locale)");
                    int filter2 = fitnessChallengeShowContactsAdapter4.filter(lowerCase3);
                    activityAddBuddiesNewBinding4 = AddParticipantsActivity.this.p;
                    if (activityAddBuddiesNewBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityAddBuddiesNewBinding6 = activityAddBuddiesNewBinding4;
                    }
                    activityAddBuddiesNewBinding6.tvInactiveHeader.setText(AddParticipantsActivity.this.getString(R.string.other_contacts) + " (" + filter2 + HexStringBuilder.COMMENT_END_CHAR);
                    return false;
                }
                return false;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(@NotNull String s) {
                Intrinsics.checkNotNullParameter(s, "s");
                return false;
            }
        });
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding4 = this.p;
        if (activityAddBuddiesNewBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddBuddiesNewBinding2 = activityAddBuddiesNewBinding4;
        }
        activityAddBuddiesNewBinding2.search.setOnCloseListener(new SearchView.OnCloseListener() { // from class: com.coveiot.android.fitnesschallenges.u
            @Override // androidx.appcompat.widget.SearchView.OnCloseListener
            public final boolean onClose() {
                boolean f0;
                f0 = AddParticipantsActivity.f0(AddParticipantsActivity.this);
                return f0;
            }
        });
    }

    @Nullable
    public final BottomSheetDialogImageTitleAndMessageWatchFace getConfirmDialog() {
        return this.I;
    }

    public final void initToolbar() {
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding = this.p;
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding2 = null;
        if (activityAddBuddiesNewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding = null;
        }
        TextView textView = (TextView) activityAddBuddiesNewBinding.toolbar.findViewById(R.id.toolbar_title);
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding3 = this.p;
        if (activityAddBuddiesNewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddBuddiesNewBinding2 = activityAddBuddiesNewBinding3;
        }
        textView.setText(getString(R.string.add_participants));
        ((TextView) activityAddBuddiesNewBinding2.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddParticipantsActivity.G(AddParticipantsActivity.this, view);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.E == 2) {
            K();
        } else {
            Q();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityAddBuddiesNewBinding inflate = ActivityAddBuddiesNewBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        AddBuddiesViewModel addBuddiesViewModel = new AddBuddiesViewModel(this);
        this.B = addBuddiesViewModel;
        addBuddiesViewModel.setMListener(this);
        CreateChallengeViewModel createChallengeViewModel = new CreateChallengeViewModel(this);
        this.D = createChallengeViewModel;
        createChallengeViewModel.setMListener(this);
        if (getIntent().hasExtra("selectedList")) {
            Serializable serializableExtra = getIntent().getSerializableExtra("selectedList");
            ArrayList arrayList = serializableExtra instanceof ArrayList ? (ArrayList) serializableExtra : null;
            Intrinsics.checkNotNull(arrayList);
            this.u = arrayList;
        }
        if (getIntent().hasExtra("buddiesChallengeDetails")) {
            this.F = (BuddiesChallengeDetail) getIntent().getSerializableExtra("buddiesChallengeDetails");
        }
        boolean z = false;
        if (getIntent().hasExtra("isFromViewAllParticipant")) {
            this.G = getIntent().getBooleanExtra("isFromViewAllParticipant", false);
        }
        if (getIntent().getSerializableExtra("createChallengeReq") != null) {
            Serializable serializableExtra2 = getIntent().getSerializableExtra("createChallengeReq");
            Intrinsics.checkNotNull(serializableExtra2, "null cannot be cast to non-null type com.coveiot.coveaccess.fitnesschallenge.model.CreateFitnessChallengeReq");
            this.C = (CreateFitnessChallengeReq) serializableExtra2;
        }
        if (getIntent().getStringExtra("challengeId") != null) {
            this.A = getIntent().getStringExtra("challengeId");
        }
        this.E = getIntent().getIntExtra(FitnessChallengeConstants.CHALLENGE_ADD_PARTICIPANT_TYPE, 0);
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding2 = this.p;
        if (activityAddBuddiesNewBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding2 = null;
        }
        Button button = activityAddBuddiesNewBinding2.btnCreateChallenge;
        List<CoveContact> list = this.u;
        if (list == null || list.isEmpty()) {
            z = true;
        }
        button.setEnabled(!z);
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding3 = this.p;
        if (activityAddBuddiesNewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding3 = null;
        }
        activityAddBuddiesNewBinding3.infoDetails.tvInfoText.setText(String.valueOf(getString(R.string.all_the_selected_buddies_will_get_an_invite)));
        int i = this.E;
        if (i == 0) {
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding4 = this.p;
            if (activityAddBuddiesNewBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding4 = null;
            }
            activityAddBuddiesNewBinding4.btnCreateChallenge.setText(String.valueOf(getString(R.string.create_challenge)));
        } else if (i == 1) {
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding5 = this.p;
            if (activityAddBuddiesNewBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding5 = null;
            }
            activityAddBuddiesNewBinding5.btnCreateChallenge.setText(String.valueOf(getString(R.string.save_challenge)));
        } else {
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding6 = this.p;
            if (activityAddBuddiesNewBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding6 = null;
            }
            activityAddBuddiesNewBinding6.btnCreateChallenge.setText(String.valueOf(getString(R.string.add_participants)));
        }
        B();
        S();
        initToolbar();
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding7 = this.p;
        if (activityAddBuddiesNewBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding7 = null;
        }
        activityAddBuddiesNewBinding7.rvActiveContactsList.setHasFixedSize(true);
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding8 = this.p;
        if (activityAddBuddiesNewBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding8 = null;
        }
        activityAddBuddiesNewBinding8.rvActiveContactsList.setLayoutManager(new LinearLayoutManager(this));
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding9 = this.p;
        if (activityAddBuddiesNewBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding9 = null;
        }
        activityAddBuddiesNewBinding9.rvInActiveContactsList.setHasFixedSize(true);
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding10 = this.p;
        if (activityAddBuddiesNewBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding10 = null;
        }
        activityAddBuddiesNewBinding10.rvInActiveContactsList.setLayoutManager(new LinearLayoutManager(this));
        this.q = new LoadingDialog(this);
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding11 = this.p;
        if (activityAddBuddiesNewBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding11 = null;
        }
        activityAddBuddiesNewBinding11.refresh.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddParticipantsActivity.M(AddParticipantsActivity.this, view);
            }
        });
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding12 = this.p;
        if (activityAddBuddiesNewBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddBuddiesNewBinding = activityAddBuddiesNewBinding12;
        }
        activityAddBuddiesNewBinding.btnCreateChallenge.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.t
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddParticipantsActivity.N(AddParticipantsActivity.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onError(@Nullable String str) {
        if (isFinishing() || str == null) {
            return;
        }
        Z(str);
    }

    @Override // com.coveiot.android.fitnesschallenges.adpter.FitnessChallengeShowContactsAdapter.OnBuddiesSelectedListener
    public void onParticipantSelected(@NotNull CoveContact contact, boolean z) {
        Intrinsics.checkNotNullParameter(contact, "contact");
        if (contact.isSelected()) {
            List<CoveContact> list = this.u;
            if (list != null) {
                list.add(contact);
            }
        } else {
            List<CoveContact> list2 = this.u;
            if (list2 != null) {
                list2.remove(contact);
            }
        }
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding = this.p;
        if (activityAddBuddiesNewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding = null;
        }
        Button button = activityAddBuddiesNewBinding.btnCreateChallenge;
        List<CoveContact> list3 = this.u;
        button.setEnabled(!(list3 == null || list3.isEmpty()));
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == this.r && permissions.length > 0 && Intrinsics.areEqual(permissions[0], "android.permission.READ_CONTACTS") && grantResults.length > 0 && grantResults[0] == 0) {
            b0();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        E();
        super.onResume();
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onSuccess() {
        if (isFinishing()) {
            return;
        }
        int i = this.E;
        if (i == 2) {
            if (this.G) {
                this.G = false;
                J(CleverTapConstants.CustomEventProperties.VIEW_ALL_PARTICIPANT);
            } else {
                J(CleverTapConstants.CustomEventProperties.DETAIL_SREEN);
            }
            String string = getString(R.string.invitation_sent_successfully);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.invitation_sent_successfully)");
            Z(string);
        } else if (i == 1) {
            String string2 = getResources().getString(R.string.challenge_edited_sucessfully);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…lenge_edited_sucessfully)");
            String string3 = getResources().getString(R.string.congratulations_your_challenge_is_set_best_of_luck_on_your_journey);
            Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.st…_of_luck_on_your_journey)");
            X(string2, string3);
        } else {
            String string4 = getResources().getString(R.string.challenge_created_sucessfully);
            Intrinsics.checkNotNullExpressionValue(string4, "resources.getString(R.st…enge_created_sucessfully)");
            String string5 = getResources().getString(R.string.congratulations_your_challenge_is_set_best_of_luck_on_your_journey);
            Intrinsics.checkNotNullExpressionValue(string5, "resources.getString(R.st…_of_luck_on_your_journey)");
            X(string4, string5);
            I();
        }
    }

    public final void refreshClicked() {
        new RetrieveContactsTask().execute(new Void[0]);
    }

    public final void setConfirmDialog(@Nullable BottomSheetDialogImageTitleAndMessageWatchFace bottomSheetDialogImageTitleAndMessageWatchFace) {
        this.I = bottomSheetDialogImageTitleAndMessageWatchFace;
    }
}
