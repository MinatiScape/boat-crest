package com.coveiot.android.fitnessbuddies.activities;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.clevertap.android.sdk.Constants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.fitnessbuddies.AppReferalHandler;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.activities.AddBuddiesActivityNew;
import com.coveiot.android.fitnessbuddies.adapters.ShowContactsAdapterNew;
import com.coveiot.android.fitnessbuddies.constants.FitnessConstants;
import com.coveiot.android.fitnessbuddies.databinding.ActivityAddBuddiesNewBinding;
import com.coveiot.android.fitnessbuddies.dialogs.InviteBuddyDialog;
import com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor;
import com.coveiot.android.fitnessbuddies.fragments.viewmodels.ManageBuddiesViewModel;
import com.coveiot.android.fitnessbuddies.utils.CoveContactsManager;
import com.coveiot.android.fitnessbuddies.utils.CoveUtils;
import com.coveiot.android.fitnessbuddies.utils.PermissionUtils;
import com.coveiot.android.fitnessbuddies.utils.PreferenceManager;
import com.coveiot.android.fitnessbuddies.viewmodels.AddBuddiesViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.CommonMessageDialog;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.LoadingDialog;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.coveaccess.CoveSocial;
import com.coveiot.coveaccess.fitnessbuddies.model.SendFitnessBuddyRequest;
import com.coveiot.coveaccess.fitnessbuddies.model.common.Requests;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.utility.AppUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class AddBuddiesActivityNew extends BaseActivity implements ShowContactsAdapterNew.OnBuddiesSelectedListener, SuccessResultListener, ManageBuddiesContaractor {
    public ActivityAddBuddiesNewBinding p;
    @Nullable
    public LoadingDialog q;
    @Nullable
    public List<CoveContact> s;
    @Nullable
    public List<CoveContact> t;
    @Nullable
    public ShowContactsAdapterNew u;
    @Nullable
    public ShowContactsAdapterNew v;
    public AddBuddiesViewModel x;
    public ManageBuddiesViewModel y;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int r = 1;
    @NotNull
    public Handler w = new Handler();
    @NotNull
    public ArrayList<CoveContact> z = new ArrayList<>();
    @NotNull
    public ArrayList<CoveContact> A = new ArrayList<>();

    /* loaded from: classes4.dex */
    public final class RetrieveContactsTask extends AsyncTask<Void, Void, List<CoveContact>> {
        public RetrieveContactsTask() {
        }

        public static final void b(AddBuddiesActivityNew this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (this$0.q != null) {
                LoadingDialog loadingDialog = this$0.q;
                Intrinsics.checkNotNull(loadingDialog);
                if (loadingDialog.isShowing()) {
                    LoadingDialog loadingDialog2 = this$0.q;
                    Intrinsics.checkNotNull(loadingDialog2);
                    loadingDialog2.dismiss();
                }
            }
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            super.onPreExecute();
            if (AddBuddiesActivityNew.this.isFinishing()) {
                return;
            }
            LoadingDialog loadingDialog = AddBuddiesActivityNew.this.q;
            Intrinsics.checkNotNull(loadingDialog);
            if (loadingDialog.isShowing()) {
                return;
            }
            LoadingDialog loadingDialog2 = AddBuddiesActivityNew.this.q;
            Intrinsics.checkNotNull(loadingDialog2);
            loadingDialog2.show();
        }

        @Override // android.os.AsyncTask
        @NotNull
        public List<CoveContact> doInBackground(@NotNull Void... voids) {
            Intrinsics.checkNotNullParameter(voids, "voids");
            ArrayList<CoveContact> contacts = AppUtils.getContacts(AddBuddiesActivityNew.this);
            Intrinsics.checkNotNullExpressionValue(contacts, "getContacts(this@AddBuddiesActivityNew)");
            return contacts;
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(@NotNull List<CoveContact> coveContacts) {
            Intrinsics.checkNotNullParameter(coveContacts, "coveContacts");
            super.onPostExecute((RetrieveContactsTask) coveContacts);
            if (!AddBuddiesActivityNew.this.isFinishing()) {
                Handler handler = new Handler();
                final AddBuddiesActivityNew addBuddiesActivityNew = AddBuddiesActivityNew.this;
                handler.postDelayed(new Runnable() { // from class: com.coveiot.android.fitnessbuddies.activities.x
                    @Override // java.lang.Runnable
                    public final void run() {
                        AddBuddiesActivityNew.RetrieveContactsTask.b(AddBuddiesActivityNew.this);
                    }
                }, 1500L);
                AddBuddiesActivityNew.this.E(coveContacts);
            }
            CoveContactsManager.INSTANCE.setContacts(coveContacts);
        }
    }

    public static final void D(AddBuddiesActivityNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.ADD_BUDDY_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_BUDDY_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.BACK_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        super.onBackPressed();
    }

    public static final void F(AddBuddiesActivityNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (ContextCompat.checkSelfPermission(this$0, "android.permission.READ_CONTACTS") != 0) {
            this$0.A();
        } else {
            this$0.refreshClicked();
        }
    }

    public static final void L(AddBuddiesActivityNew this$0, CoveContact contact, InviteBuddyDialog inviteBuddyDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(contact, "$contact");
        Intrinsics.checkNotNullParameter(inviteBuddyDialog, "$inviteBuddyDialog");
        this$0.G(contact);
        inviteBuddyDialog.dismiss();
    }

    public static final void N(CommonMessageDialog commonMessageDialog) {
        Intrinsics.checkNotNullParameter(commonMessageDialog, "$commonMessageDialog");
        commonMessageDialog.dismiss();
    }

    public static final boolean T(AddBuddiesActivityNew this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ShowContactsAdapterNew showContactsAdapterNew = this$0.u;
        Intrinsics.checkNotNull(showContactsAdapterNew);
        showContactsAdapterNew.filter(null);
        ShowContactsAdapterNew showContactsAdapterNew2 = this$0.v;
        Intrinsics.checkNotNull(showContactsAdapterNew2);
        showContactsAdapterNew2.filter(null);
        return false;
    }

    public static final void y(AddBuddiesActivityNew this$0, List list) {
        LoadingDialog loadingDialog;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list != null && list.size() > 0) {
            this$0.z = (ArrayList) list;
            CoveContactsManager.INSTANCE.setActiveContacts(list);
        }
        this$0.I();
        LoadingDialog loadingDialog2 = this$0.q;
        boolean z = true;
        if (loadingDialog2 == null || !loadingDialog2.isShowing()) {
            z = false;
        }
        if (!z || (loadingDialog = this$0.q) == null) {
            return;
        }
        loadingDialog.dismiss();
    }

    public static final void z(AddBuddiesActivityNew this$0, List list) {
        LoadingDialog loadingDialog;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list != null && list.size() > 0) {
            this$0.A = (ArrayList) list;
            CoveContactsManager.INSTANCE.setInActiveContacts(list);
        }
        this$0.I();
        LoadingDialog loadingDialog2 = this$0.q;
        boolean z = true;
        if (loadingDialog2 == null || !loadingDialog2.isShowing()) {
            z = false;
        }
        if (!z || (loadingDialog = this$0.q) == null) {
            return;
        }
        loadingDialog.dismiss();
    }

    public final void A() {
        PermissionUtils.INSTANCE.checkPermission(this, "android.permission.READ_CONTACTS", new AddBuddiesActivityNew$getContactList$1(this));
    }

    public final void B() {
        List<Requests> allFitnessBuddyRequests = PreferenceManager.Companion.getAllFitnessBuddyRequests(this);
        ManageBuddiesViewModel manageBuddiesViewModel = null;
        AddBuddiesViewModel addBuddiesViewModel = null;
        if (allFitnessBuddyRequests != null && (!allFitnessBuddyRequests.isEmpty())) {
            for (Requests requests : allFitnessBuddyRequests) {
                List<CoveContact> list = this.t;
                Intrinsics.checkNotNull(list);
                for (CoveContact coveContact : list) {
                    if (kotlin.text.m.equals(requests.requestStatus, "PENDING", true) && kotlin.text.m.equals(coveContact.getPhoneNumber(), requests.toUserMobileNumber, true) && !coveContact.isRequestSent()) {
                        coveContact.setRequestSent(true);
                    }
                    if (requests.fromUserMobileNumber != null && kotlin.text.m.equals(coveContact.getPhoneNumber(), requests.fromUserMobileNumber, true) && !coveContact.isRequestReceived()) {
                        coveContact.setRequestReceived(true);
                    }
                }
            }
            List<CoveContact> list2 = this.t;
            if (list2 != null) {
                AddBuddiesViewModel addBuddiesViewModel2 = this.x;
                if (addBuddiesViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    addBuddiesViewModel = addBuddiesViewModel2;
                }
                addBuddiesViewModel.loadCoveBuddies(list2);
                return;
            }
            return;
        }
        ManageBuddiesViewModel manageBuddiesViewModel2 = this.y;
        if (manageBuddiesViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageBuddiesViewModel");
        } else {
            manageBuddiesViewModel = manageBuddiesViewModel2;
        }
        manageBuddiesViewModel.manageAllBuddies();
    }

    public final String C(Context context) {
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

    public final void E(List<CoveContact> list) {
        if (isFinishing()) {
            return;
        }
        this.t = new ArrayList();
        for (CoveContact coveContact : list) {
            String phoneNumber = coveContact.getPhoneNumber();
            Intrinsics.checkNotNullExpressionValue(phoneNumber, "i.phoneNumber");
            String R = R(this, phoneNumber);
            if (R != null) {
                List<CoveContact> list2 = this.t;
                Intrinsics.checkNotNull(list2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.utils.model.CoveContact>");
                ((ArrayList) list2).add(new CoveContact(coveContact.getId(), coveContact.getName(), R, coveContact.getRunningContactId()));
            }
        }
        B();
    }

    public final void G(CoveContact coveContact) {
        if (!AppUtils.isNetConnected(this)) {
            String string = getResources().getString(R.string.please_check_your_internet);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…ease_check_your_internet)");
            P(string);
            return;
        }
        LoadingDialog loadingDialog = this.q;
        Intrinsics.checkNotNull(loadingDialog);
        if (!loadingDialog.isShowing()) {
            LoadingDialog loadingDialog2 = this.q;
            Intrinsics.checkNotNull(loadingDialog2);
            loadingDialog2.show();
        }
        SendFitnessBuddyRequest Q = Q(coveContact);
        new AppReferalHandler(this);
        if (Q != null) {
            CoveSocial.sendFitnessBuddyRequest(Q, new AddBuddiesActivityNew$onSubmitClicked$1(this, coveContact));
        }
    }

    public final String H(String str) {
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

    public final void I() {
        ArrayList<CoveContact> arrayList = this.z;
        boolean z = true;
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding = null;
        if (!(arrayList == null || arrayList.isEmpty())) {
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding2 = this.p;
            if (activityAddBuddiesNewBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding2 = null;
            }
            activityAddBuddiesNewBinding2.tvActiveHeader.setVisibility(0);
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding3 = this.p;
            if (activityAddBuddiesNewBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding3 = null;
            }
            activityAddBuddiesNewBinding3.rvActiveContactsList.setVisibility(0);
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding4 = this.p;
            if (activityAddBuddiesNewBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding4 = null;
            }
            activityAddBuddiesNewBinding4.tvActiveHeader.setText(getString(R.string.boatheads_in_your_contact) + " (" + this.z.size() + HexStringBuilder.COMMENT_END_CHAR);
            this.u = new ShowContactsAdapterNew(this, this.z, this, true);
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding5 = this.p;
            if (activityAddBuddiesNewBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding5 = null;
            }
            activityAddBuddiesNewBinding5.rvActiveContactsList.setAdapter(this.u);
            S();
        } else {
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding6 = this.p;
            if (activityAddBuddiesNewBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding6 = null;
            }
            activityAddBuddiesNewBinding6.tvActiveHeader.setVisibility(8);
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding7 = this.p;
            if (activityAddBuddiesNewBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding7 = null;
            }
            activityAddBuddiesNewBinding7.rvActiveContactsList.setVisibility(8);
        }
        ArrayList<CoveContact> arrayList2 = this.A;
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            z = false;
        }
        if (!z) {
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding8 = this.p;
            if (activityAddBuddiesNewBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding8 = null;
            }
            activityAddBuddiesNewBinding8.tvInactiveHeader.setVisibility(0);
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding9 = this.p;
            if (activityAddBuddiesNewBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding9 = null;
            }
            activityAddBuddiesNewBinding9.rvInActiveContactsList.setVisibility(0);
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding10 = this.p;
            if (activityAddBuddiesNewBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddBuddiesNewBinding10 = null;
            }
            activityAddBuddiesNewBinding10.tvInactiveHeader.setText(String.valueOf(getString(R.string.other_contacts)));
            this.v = new ShowContactsAdapterNew(this, this.A, this, false);
            ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding11 = this.p;
            if (activityAddBuddiesNewBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityAddBuddiesNewBinding = activityAddBuddiesNewBinding11;
            }
            activityAddBuddiesNewBinding.rvInActiveContactsList.setAdapter(this.v);
            S();
            return;
        }
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding12 = this.p;
        if (activityAddBuddiesNewBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding12 = null;
        }
        activityAddBuddiesNewBinding12.tvInactiveHeader.setVisibility(8);
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding13 = this.p;
        if (activityAddBuddiesNewBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddBuddiesNewBinding = activityAddBuddiesNewBinding13;
        }
        activityAddBuddiesNewBinding.rvInActiveContactsList.setVisibility(8);
    }

    public final void J() {
        int i = R.id.search;
        View findViewById = ((SearchView) _$_findCachedViewById(i)).findViewById(R.id.search_src_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.EditText");
        EditText editText = (EditText) findViewById;
        editText.setTextColor(getResources().getColor(R.color.main_text_color));
        editText.setHintTextColor(getResources().getColor(R.color.color_666666));
        ((SearchView) _$_findCachedViewById(i)).setIconifiedByDefault(false);
        ((SearchView) _$_findCachedViewById(i)).setQueryHint(getResources().getString(R.string.search_buddy_name_here));
    }

    public final void K(final CoveContact coveContact, boolean z) {
        if (z) {
            G(coveContact);
            return;
        }
        final InviteBuddyDialog inviteBuddyDialog = new InviteBuddyDialog(this, coveContact);
        inviteBuddyDialog.setPositiveButton(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.activities.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddBuddiesActivityNew.L(AddBuddiesActivityNew.this, coveContact, inviteBuddyDialog, view);
            }
        });
        inviteBuddyDialog.show();
    }

    public final void M(String str) {
        Window window;
        final CommonMessageDialog commonMessageDialog = new CommonMessageDialog(this, str, false, false, 12, null);
        commonMessageDialog.show(getSupportFragmentManager(), "");
        Dialog dialog = commonMessageDialog.getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setGravity(80);
        }
        this.w.postDelayed(new Runnable() { // from class: com.coveiot.android.fitnessbuddies.activities.w
            @Override // java.lang.Runnable
            public final void run() {
                AddBuddiesActivityNew.N(CommonMessageDialog.this);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    public final void O() {
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
            E(list);
        }
    }

    public final void P(String str) {
        M(str);
    }

    public final SendFitnessBuddyRequest Q(CoveContact coveContact) {
        ArrayList arrayList = new ArrayList();
        String phoneNumber = coveContact.getPhoneNumber();
        Intrinsics.checkNotNullExpressionValue(phoneNumber, "contact.phoneNumber");
        String R = R(this, phoneNumber);
        if (R == null) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String string = getString(R.string.invalid_phone_number);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.invalid_phone_number)");
            String format = String.format(locale, string, Arrays.copyOf(new Object[]{coveContact.getPhoneNumber()}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            P(format);
            return null;
        }
        arrayList.add(new SendFitnessBuddyRequest.Buddy(coveContact.getName(), R));
        return new SendFitnessBuddyRequest(arrayList);
    }

    public final String R(Context context, String str) {
        try {
            Phonenumber.PhoneNumber parse = PhoneNumberUtil.getInstance().parse(H(str), C(context));
            StringBuilder sb = new StringBuilder();
            sb.append('+');
            sb.append(parse.getCountryCode());
            sb.append(parse.getNationalNumber());
            return sb.toString();
        } catch (NumberParseException unused) {
            return null;
        }
    }

    public final void S() {
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
        } else {
            activityAddBuddiesNewBinding2 = activityAddBuddiesNewBinding3;
        }
        activityAddBuddiesNewBinding2.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.coveiot.android.fitnessbuddies.activities.AddBuddiesActivityNew$watchSearchTextBox$1
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(@NotNull String s) {
                ShowContactsAdapterNew showContactsAdapterNew;
                ShowContactsAdapterNew showContactsAdapterNew2;
                ShowContactsAdapterNew showContactsAdapterNew3;
                ShowContactsAdapterNew showContactsAdapterNew4;
                Intrinsics.checkNotNullParameter(s, "s");
                showContactsAdapterNew = AddBuddiesActivityNew.this.u;
                if (showContactsAdapterNew != null) {
                    showContactsAdapterNew4 = AddBuddiesActivityNew.this.u;
                    Intrinsics.checkNotNull(showContactsAdapterNew4);
                    Locale locale = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
                    String lowerCase = s.toLowerCase(locale);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                    showContactsAdapterNew4.filter(lowerCase);
                }
                showContactsAdapterNew2 = AddBuddiesActivityNew.this.v;
                if (showContactsAdapterNew2 != null) {
                    showContactsAdapterNew3 = AddBuddiesActivityNew.this.v;
                    Intrinsics.checkNotNull(showContactsAdapterNew3);
                    Locale locale2 = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale2, "getDefault()");
                    String lowerCase2 = s.toLowerCase(locale2);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                    showContactsAdapterNew3.filter(lowerCase2);
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
        ((SearchView) _$_findCachedViewById(R.id.search)).setOnCloseListener(new SearchView.OnCloseListener() { // from class: com.coveiot.android.fitnessbuddies.activities.t
            @Override // androidx.appcompat.widget.SearchView.OnCloseListener
            public final boolean onClose() {
                boolean T;
                T = AddBuddiesActivityNew.T(AddBuddiesActivityNew.this);
                return T;
            }
        });
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

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor
    public void dismissPerogress() {
        dismissProgress();
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
        textView.setText(getString(R.string.add_buddies));
        ((TextView) activityAddBuddiesNewBinding2.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.activities.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddBuddiesActivityNew.D(AddBuddiesActivityNew.this, view);
            }
        });
    }

    @Override // com.coveiot.android.fitnessbuddies.adapters.ShowContactsAdapterNew.OnBuddiesSelectedListener
    public void onBuddiesContactSelected(@NotNull CoveContact contact, boolean z) {
        Intrinsics.checkNotNullParameter(contact, "contact");
        if (contact.getPhoneNumber() == null) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String string = getString(R.string.invalid_phone_number);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.invalid_phone_number)");
            String format = String.format(locale, string, Arrays.copyOf(new Object[]{contact.getPhoneNumber()}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            P(format);
            return;
        }
        String phoneNumber = contact.getPhoneNumber();
        Intrinsics.checkNotNullExpressionValue(phoneNumber, "contact.phoneNumber");
        String replace$default = kotlin.text.m.replace$default(kotlin.text.m.replace$default(phoneNumber, "[^0-9+]", "", false, 4, (Object) null), HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null);
        if (!kotlin.text.m.startsWith$default(replace$default, "+", false, 2, null)) {
            replace$default = "+91 " + replace$default;
        }
        if (!CoveUtils.INSTANCE.isValidPhoneNumber(replace$default)) {
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            Locale locale2 = Locale.ENGLISH;
            String string2 = getString(R.string.invalid_phone_number);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.invalid_phone_number)");
            String format2 = String.format(locale2, string2, Arrays.copyOf(new Object[]{contact.getPhoneNumber()}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            P(format2);
            return;
        }
        K(contact, z);
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
        this.x = new AddBuddiesViewModel(this);
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "this.application");
        this.y = new ManageBuddiesViewModel(application, this);
        AddBuddiesViewModel addBuddiesViewModel = this.x;
        if (addBuddiesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            addBuddiesViewModel = null;
        }
        addBuddiesViewModel.setMListener(this);
        x();
        J();
        initToolbar();
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding2 = this.p;
        if (activityAddBuddiesNewBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding2 = null;
        }
        activityAddBuddiesNewBinding2.rvActiveContactsList.setHasFixedSize(true);
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding3 = this.p;
        if (activityAddBuddiesNewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding3 = null;
        }
        activityAddBuddiesNewBinding3.rvActiveContactsList.setLayoutManager(new LinearLayoutManager(this));
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding4 = this.p;
        if (activityAddBuddiesNewBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding4 = null;
        }
        activityAddBuddiesNewBinding4.rvInActiveContactsList.setHasFixedSize(true);
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding5 = this.p;
        if (activityAddBuddiesNewBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddBuddiesNewBinding5 = null;
        }
        activityAddBuddiesNewBinding5.rvInActiveContactsList.setLayoutManager(new LinearLayoutManager(this));
        this.q = new LoadingDialog(this);
        ActivityAddBuddiesNewBinding activityAddBuddiesNewBinding6 = this.p;
        if (activityAddBuddiesNewBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddBuddiesNewBinding = activityAddBuddiesNewBinding6;
        }
        activityAddBuddiesNewBinding.refresh.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.activities.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddBuddiesActivityNew.F(AddBuddiesActivityNew.this, view);
            }
        });
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.ADD_BUDDY_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onError(@Nullable String str) {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == this.r && permissions.length > 0 && Intrinsics.areEqual(permissions[0], "android.permission.READ_CONTACTS") && grantResults.length > 0 && grantResults[0] == 0) {
            O();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        A();
        super.onResume();
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onSuccess() {
    }

    public final void refreshClicked() {
        new RetrieveContactsTask().execute(new Void[0]);
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor
    public void showContent(@NotNull LinkedHashMap<String, List<Requests>> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        List<Requests> list = data.get(FitnessConstants.INVITE_RECEIVED);
        if (list != null && (!list.isEmpty())) {
            arrayList.addAll(list);
        }
        List<Requests> list2 = data.get(FitnessConstants.INVITE_SENT);
        if (list2 != null && (!list2.isEmpty())) {
            arrayList.addAll(list2);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Requests requests = (Requests) it.next();
            List<CoveContact> list3 = this.t;
            Intrinsics.checkNotNull(list3);
            for (CoveContact coveContact : list3) {
                if (kotlin.text.m.equals(requests.requestStatus, "PENDING", true) && kotlin.text.m.equals(coveContact.getPhoneNumber(), requests.toUserMobileNumber, true) && !coveContact.isRequestSent()) {
                    coveContact.setRequestSent(true);
                }
                if (requests.fromUserMobileNumber != null && kotlin.text.m.equals(coveContact.getPhoneNumber(), requests.fromUserMobileNumber, true) && !coveContact.isRequestReceived()) {
                    coveContact.setRequestReceived(true);
                }
            }
        }
        List<CoveContact> list4 = this.t;
        if (list4 != null) {
            AddBuddiesViewModel addBuddiesViewModel = this.x;
            if (addBuddiesViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                addBuddiesViewModel = null;
            }
            addBuddiesViewModel.loadCoveBuddies(list4);
        }
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor
    public void showEmptyView() {
        List<CoveContact> list = this.t;
        if (list != null) {
            AddBuddiesViewModel addBuddiesViewModel = this.x;
            if (addBuddiesViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                addBuddiesViewModel = null;
            }
            addBuddiesViewModel.loadCoveBuddies(list);
        }
    }

    @Override // com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor
    public void showMessage(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
    }

    public final void x() {
        AddBuddiesViewModel addBuddiesViewModel = this.x;
        AddBuddiesViewModel addBuddiesViewModel2 = null;
        if (addBuddiesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            addBuddiesViewModel = null;
        }
        addBuddiesViewModel.getGetActiveContacts().observe(this, new Observer() { // from class: com.coveiot.android.fitnessbuddies.activities.v
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AddBuddiesActivityNew.y(AddBuddiesActivityNew.this, (List) obj);
            }
        });
        AddBuddiesViewModel addBuddiesViewModel3 = this.x;
        if (addBuddiesViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            addBuddiesViewModel2 = addBuddiesViewModel3;
        }
        addBuddiesViewModel2.getGetInActiveContacts().observe(this, new Observer() { // from class: com.coveiot.android.fitnessbuddies.activities.u
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AddBuddiesActivityNew.z(AddBuddiesActivityNew.this, (List) obj);
            }
        });
    }
}
