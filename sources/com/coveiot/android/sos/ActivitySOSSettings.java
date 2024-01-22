package com.coveiot.android.sos;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.clevertap.android.sdk.Constants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.fitnessbuddies.utils.CoveContactsManager;
import com.coveiot.android.sos.ActivitySOSSettings;
import com.coveiot.android.sos.adapter.ShowContactsAdapterNew;
import com.coveiot.android.sos.databinding.ActivitySosSettingsBinding;
import com.coveiot.android.sos.utils.SOSCleverTapConstants;
import com.coveiot.android.sos.viewmodel.SOSSettingsViewmodel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.CommonMessageDialog;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.LoadingDialog;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.android.theme.utils.PhoneNumberUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.sos.SOSData;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class ActivitySOSSettings extends BaseActivity implements ShowContactsAdapterNew.OnContactSelectedListener, SuccessResultListener {
    public ActivitySosSettingsBinding p;
    @Nullable
    public LoadingDialog r;
    @Nullable
    public List<CoveContact> s;
    @Nullable
    public ShowContactsAdapterNew t;
    public SOSSettingsViewmodel w;
    @Nullable
    public CoveContact x;
    @Nullable
    public List<CoveContact> z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int q = 1;
    @Nullable
    public List<CoveContact> u = new ArrayList();
    public boolean v = true;
    public final int y = 99;

    /* loaded from: classes7.dex */
    public final class RetrieveContactsTask extends AsyncTask<Void, Void, List<CoveContact>> {
        public RetrieveContactsTask() {
        }

        public static final void b(ActivitySOSSettings this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (this$0.r != null) {
                LoadingDialog loadingDialog = this$0.r;
                Intrinsics.checkNotNull(loadingDialog);
                if (loadingDialog.isShowing()) {
                    LoadingDialog loadingDialog2 = this$0.r;
                    Intrinsics.checkNotNull(loadingDialog2);
                    loadingDialog2.dismiss();
                }
            }
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            super.onPreExecute();
            if (ActivitySOSSettings.this.isFinishing()) {
                return;
            }
            LoadingDialog loadingDialog = ActivitySOSSettings.this.r;
            Intrinsics.checkNotNull(loadingDialog);
            if (loadingDialog.isShowing()) {
                return;
            }
            LoadingDialog loadingDialog2 = ActivitySOSSettings.this.r;
            Intrinsics.checkNotNull(loadingDialog2);
            loadingDialog2.show();
        }

        @Override // android.os.AsyncTask
        @NotNull
        public List<CoveContact> doInBackground(@NotNull Void... voids) {
            Intrinsics.checkNotNullParameter(voids, "voids");
            ArrayList<CoveContact> contacts = AppUtils.getContacts(ActivitySOSSettings.this);
            Intrinsics.checkNotNullExpressionValue(contacts, "getContacts(this@ActivitySOSSettings)");
            return contacts;
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(@NotNull List<CoveContact> coveContacts) {
            Intrinsics.checkNotNullParameter(coveContacts, "coveContacts");
            super.onPostExecute((RetrieveContactsTask) coveContacts);
            if (!ActivitySOSSettings.this.isFinishing()) {
                Handler handler = new Handler();
                final ActivitySOSSettings activitySOSSettings = ActivitySOSSettings.this;
                handler.postDelayed(new Runnable() { // from class: com.coveiot.android.sos.e0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivitySOSSettings.RetrieveContactsTask.b(ActivitySOSSettings.this);
                    }
                }, 1500L);
                ActivitySOSSettings.this.E(coveContacts);
            }
            CoveContactsManager.INSTANCE.setContacts(coveContacts);
        }
    }

    public static final void A(ActivitySOSSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void F(ActivitySOSSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.C(SOSCleverTapConstants.BC_SOS_ADD_CONTACT_TAPPED.getValue());
        this$0.v = false;
        this$0.x();
    }

    public static final void G(ActivitySOSSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0)) {
            this$0.C(SOSCleverTapConstants.BC_SOS_TNC_VIEWED.getValue());
            SessionManager sessionManager = SessionManager.getInstance(this$0);
            Intent intent = new Intent();
            intent.setClassName(this$0, "com.coveiot.android.leonardo.websupport.ActivityInAppWebViewer");
            intent.putExtra(WorkoutConstants.INTENT_EXTRA_URL, sessionManager.getSOSDisclaimerURL() != null ? sessionManager.getSOSDisclaimerURL() : "https://docs.coveiot.com/kaha/eula.html");
            intent.putExtra(WorkoutConstants.INTENT_EXTRA_TITLE, this$0.getString(R.string.terms_conditions_title));
            this$0.startActivity(intent);
            return;
        }
        this$0.showNoInternetMessage();
    }

    public static final void H(ActivitySOSSettings this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            if (AppUtils.isNetConnected(this$0)) {
                if (this$0.x != null) {
                    this$0.showProgress();
                    this$0.C(SOSCleverTapConstants.BC_SOS_CONTACT_SELECTED.getValue());
                    SOSData sOSConfig = SessionManager.getInstance(this$0).getSOSConfig();
                    boolean z = true;
                    if (sOSConfig != null) {
                        String contactName = sOSConfig.getContactName();
                        if (!(contactName == null || contactName.length() == 0)) {
                            String contactNumber = sOSConfig.getContactNumber();
                            if (!(contactNumber == null || contactNumber.length() == 0)) {
                                Boolean isSOSEnabled = sOSConfig.isSOSEnabled();
                                Intrinsics.checkNotNull(isSOSEnabled);
                                z = isSOSEnabled.booleanValue();
                            }
                        }
                    }
                    SOSSettingsViewmodel sOSSettingsViewmodel = this$0.w;
                    if (sOSSettingsViewmodel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        sOSSettingsViewmodel = null;
                    }
                    CoveContact coveContact = this$0.x;
                    Intrinsics.checkNotNull(coveContact);
                    String name = coveContact.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "selectedContact!!.name");
                    CoveContact coveContact2 = this$0.x;
                    Intrinsics.checkNotNull(coveContact2);
                    String phoneNumber = coveContact2.getPhoneNumber();
                    Intrinsics.checkNotNullExpressionValue(phoneNumber, "selectedContact!!.phoneNumber");
                    sOSSettingsViewmodel.setSOSConfigSettings(z, name, phoneNumber, false);
                    return;
                }
                return;
            }
            this$0.showNoInternetMessage();
            return;
        }
        this$0.showWatchDisconnectedDialog(this$0);
    }

    public static final void L(CommonMessageDialog commonMessageDialog, boolean z, ActivitySOSSettings this$0) {
        Intrinsics.checkNotNullParameter(commonMessageDialog, "$commonMessageDialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseConstants.PREVIOUS_SCREEN_NAME.setValue(FirebaseEventParams.ScreenName.SOS_CONTACT_SELECT.getValue());
        commonMessageDialog.dismiss();
        if (z) {
            return;
        }
        this$0.startActivity(new Intent(this$0, ActivitySOS.class));
        this$0.finish();
    }

    public static final boolean O(ActivitySOSSettings this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ShowContactsAdapterNew showContactsAdapterNew = this$0.t;
        Intrinsics.checkNotNull(showContactsAdapterNew);
        showContactsAdapterNew.filter(null);
        return false;
    }

    public final void B(String str, String str2, boolean z) {
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(str);
        analyticsLog.setPreviousScreenName(FirebaseConstants.PREVIOUS_SCREEN_NAME.getValue());
        analyticsLog.setScreenName(str2);
        HashMap<String, String> hashMap = new HashMap<>();
        if (z) {
            String value = FirebaseEventParams.Description.CV_CONTACT_SELECTED_NAME.getValue();
            CoveContact coveContact = this.x;
            Intrinsics.checkNotNull(coveContact);
            hashMap.put(value, coveContact.getName());
            String value2 = FirebaseEventParams.Description.CV_CONTACT_SELECTED_NUMBER.getValue();
            CoveContact coveContact2 = this.x;
            Intrinsics.checkNotNull(coveContact2);
            hashMap.put(value2, coveContact2.getPhoneNumber());
        }
        analyticsLog.setMapData(hashMap);
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
    }

    public final void C(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        hashMap.putAll(companion.getWatchDetails(this));
        hashMap.putAll(companion.getDeviceId(this));
        companion.logAnalyticsEvent(str, hashMap);
    }

    public final void D() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(CleverTapConstants.CustomEventProperties.SOURCE.getValue(), SOSCleverTapConstants.SOS_APP_PUSH.getValue());
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        hashMap.putAll(companion.getWatchDetails(this));
        hashMap.putAll(companion.getDeviceId(this));
        companion.logAnalyticsEvent(SOSCleverTapConstants.BC_SOS_LANDINGPAGE_VIEWED.getValue(), hashMap);
    }

    public final void E(List<CoveContact> list) {
        C(SOSCleverTapConstants.BC_SOS_SELECTCONTACT_PAGELOADED.getValue());
        this.z = new ArrayList();
        for (CoveContact coveContact : list) {
            PhoneNumberUtils phoneNumberUtils = PhoneNumberUtils.INSTANCE;
            String phoneNumber = coveContact.getPhoneNumber();
            Intrinsics.checkNotNullExpressionValue(phoneNumber, "i.phoneNumber");
            String verifyPhoneNumber = phoneNumberUtils.verifyPhoneNumber(this, phoneNumber);
            if (verifyPhoneNumber != null) {
                List<CoveContact> list2 = this.z;
                Intrinsics.checkNotNull(list2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.utils.model.CoveContact>");
                ((ArrayList) list2).add(new CoveContact(coveContact.getId(), coveContact.getName(), verifyPhoneNumber, coveContact.getRunningContactId()));
            }
        }
        boolean z = this.v;
        List<CoveContact> list3 = this.z;
        Intrinsics.checkNotNull(list3, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.utils.model.CoveContact>");
        I(z, (ArrayList) list3);
    }

    public final void I(boolean z, List<CoveContact> list) {
        ActivitySosSettingsBinding activitySosSettingsBinding = null;
        if (!z) {
            SOSData sOSConfig = SessionManager.getInstance(this).getSOSConfig();
            if (!(list == null || list.isEmpty())) {
                ActivitySosSettingsBinding activitySosSettingsBinding2 = this.p;
                if (activitySosSettingsBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySosSettingsBinding2 = null;
                }
                activitySosSettingsBinding2.clEmptyLayout.setVisibility(8);
                ActivitySosSettingsBinding activitySosSettingsBinding3 = this.p;
                if (activitySosSettingsBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activitySosSettingsBinding3 = null;
                }
                activitySosSettingsBinding3.clContacts.setVisibility(0);
                this.u = z();
                String string = getString(R.string.select_emergency_contact);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.select_emergency_contact)");
                initToolbar(string);
                Intrinsics.checkNotNull(list);
                this.t = new ShowContactsAdapterNew(this, list, this.u, this, sOSConfig);
                ActivitySosSettingsBinding activitySosSettingsBinding4 = this.p;
                if (activitySosSettingsBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activitySosSettingsBinding = activitySosSettingsBinding4;
                }
                activitySosSettingsBinding.rvActiveContactsList.setAdapter(this.t);
                ShowContactsAdapterNew showContactsAdapterNew = this.t;
                Intrinsics.checkNotNull(showContactsAdapterNew);
                showContactsAdapterNew.notifyDataSetChanged();
                N();
                return;
            }
            ActivitySosSettingsBinding activitySosSettingsBinding5 = this.p;
            if (activitySosSettingsBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySosSettingsBinding5 = null;
            }
            activitySosSettingsBinding5.clNoContacts.setVisibility(0);
            ActivitySosSettingsBinding activitySosSettingsBinding6 = this.p;
            if (activitySosSettingsBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activitySosSettingsBinding = activitySosSettingsBinding6;
            }
            activitySosSettingsBinding.clContacts.setVisibility(8);
            return;
        }
        ActivitySosSettingsBinding activitySosSettingsBinding7 = this.p;
        if (activitySosSettingsBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosSettingsBinding7 = null;
        }
        activitySosSettingsBinding7.clEmptyLayout.setVisibility(0);
        ActivitySosSettingsBinding activitySosSettingsBinding8 = this.p;
        if (activitySosSettingsBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySosSettingsBinding = activitySosSettingsBinding8;
        }
        activitySosSettingsBinding.clContacts.setVisibility(8);
    }

    public final void J() {
        ActivitySosSettingsBinding activitySosSettingsBinding = this.p;
        ActivitySosSettingsBinding activitySosSettingsBinding2 = null;
        if (activitySosSettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosSettingsBinding = null;
        }
        View findViewById = activitySosSettingsBinding.search.findViewById(R.id.search_src_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.EditText");
        EditText editText = (EditText) findViewById;
        editText.setTextColor(getResources().getColor(R.color.main_text_color));
        editText.setHintTextColor(getResources().getColor(R.color.color_666666));
        ActivitySosSettingsBinding activitySosSettingsBinding3 = this.p;
        if (activitySosSettingsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosSettingsBinding3 = null;
        }
        activitySosSettingsBinding3.search.setIconifiedByDefault(false);
        ActivitySosSettingsBinding activitySosSettingsBinding4 = this.p;
        if (activitySosSettingsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySosSettingsBinding2 = activitySosSettingsBinding4;
        }
        activitySosSettingsBinding2.search.setQueryHint(getResources().getString(R.string.search));
    }

    public final void K(final boolean z, String str) {
        Window window;
        final CommonMessageDialog commonMessageDialog = new CommonMessageDialog(this, str, false, true);
        commonMessageDialog.show(getSupportFragmentManager(), "");
        Dialog dialog = commonMessageDialog.getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setGravity(80);
        }
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.sos.d0
            @Override // java.lang.Runnable
            public final void run() {
                ActivitySOSSettings.L(CommonMessageDialog.this, z, this);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    public final void M() {
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

    public final void N() {
        ActivitySosSettingsBinding activitySosSettingsBinding = this.p;
        ActivitySosSettingsBinding activitySosSettingsBinding2 = null;
        if (activitySosSettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosSettingsBinding = null;
        }
        activitySosSettingsBinding.search.setIconifiedByDefault(false);
        ActivitySosSettingsBinding activitySosSettingsBinding3 = this.p;
        if (activitySosSettingsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosSettingsBinding3 = null;
        }
        activitySosSettingsBinding3.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.coveiot.android.sos.ActivitySOSSettings$watchSearchTextBox$1
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(@NotNull String s) {
                ShowContactsAdapterNew showContactsAdapterNew;
                ShowContactsAdapterNew showContactsAdapterNew2;
                Intrinsics.checkNotNullParameter(s, "s");
                showContactsAdapterNew = ActivitySOSSettings.this.t;
                if (showContactsAdapterNew != null) {
                    showContactsAdapterNew2 = ActivitySOSSettings.this.t;
                    Intrinsics.checkNotNull(showContactsAdapterNew2);
                    Locale locale = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
                    String lowerCase = s.toLowerCase(locale);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                    showContactsAdapterNew2.filter(lowerCase);
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
        ActivitySosSettingsBinding activitySosSettingsBinding4 = this.p;
        if (activitySosSettingsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySosSettingsBinding2 = activitySosSettingsBinding4;
        }
        activitySosSettingsBinding2.search.setOnCloseListener(new SearchView.OnCloseListener() { // from class: com.coveiot.android.sos.c0
            @Override // androidx.appcompat.widget.SearchView.OnCloseListener
            public final boolean onClose() {
                boolean O;
                O = ActivitySOSSettings.O(ActivitySOSSettings.this);
                return O;
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

    public final void initToolbar(String str) {
        ((TextView) findViewById(R.id.toolbar_title)).setText(str);
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.a0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOSSettings.A(ActivitySOSSettings.this, view);
            }
        });
    }

    @Override // com.coveiot.android.sos.adapter.ShowContactsAdapterNew.OnContactSelectedListener
    public void onContactSelected(@NotNull CoveContact contact) {
        Intrinsics.checkNotNullParameter(contact, "contact");
        this.x = contact;
        w(true);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivitySosSettingsBinding inflate = ActivitySosSettingsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        Intent intent = getIntent();
        ActivitySosSettingsBinding activitySosSettingsBinding = null;
        if ((intent != null ? intent.getData() : null) != null) {
            D();
            FirebaseConstants.PREVIOUS_SCREEN_NAME.setValue(FirebaseEventParams.ScreenName.SYSTEM_NOTIFICATION.getValue());
            if (SessionManager.getInstance(this).getSOSConfig() != null) {
                startActivity(new Intent(this, ActivitySOS.class));
                finish();
            }
        }
        SOSSettingsViewmodel sOSSettingsViewmodel = new SOSSettingsViewmodel(this);
        this.w = sOSSettingsViewmodel;
        sOSSettingsViewmodel.setMListener(this);
        ActivitySosSettingsBinding activitySosSettingsBinding2 = this.p;
        if (activitySosSettingsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosSettingsBinding2 = null;
        }
        setContentView(activitySosSettingsBinding2.getRoot());
        J();
        String string = getString(R.string.emergency_sos);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.emergency_sos)");
        initToolbar(string);
        this.r = new LoadingDialog(this);
        y();
        ActivitySosSettingsBinding activitySosSettingsBinding3 = this.p;
        if (activitySosSettingsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosSettingsBinding3 = null;
        }
        activitySosSettingsBinding3.rvActiveContactsList.setLayoutManager(new LinearLayoutManager(this));
        if (SessionManager.getInstance(this).getSOSConfig() != null) {
            this.v = false;
            x();
        } else {
            FirebaseConstants.PREVIOUS_SCREEN_NAME.setValue(FirebaseEventParams.ScreenName.SOS_FTU.getValue());
            ActivitySosSettingsBinding activitySosSettingsBinding4 = this.p;
            if (activitySosSettingsBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySosSettingsBinding4 = null;
            }
            activitySosSettingsBinding4.clEmptyLayout.setVisibility(0);
            ActivitySosSettingsBinding activitySosSettingsBinding5 = this.p;
            if (activitySosSettingsBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySosSettingsBinding5 = null;
            }
            activitySosSettingsBinding5.clContacts.setVisibility(8);
        }
        ActivitySosSettingsBinding activitySosSettingsBinding6 = this.p;
        if (activitySosSettingsBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosSettingsBinding6 = null;
        }
        activitySosSettingsBinding6.btnAddEmergencyContact.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.b0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOSSettings.F(ActivitySOSSettings.this, view);
            }
        });
        SpannableString spannableString = new SpannableString(getString(R.string.terms_and_conditions));
        spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
        ActivitySosSettingsBinding activitySosSettingsBinding7 = this.p;
        if (activitySosSettingsBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosSettingsBinding7 = null;
        }
        activitySosSettingsBinding7.tvTNC.setText(spannableString);
        ActivitySosSettingsBinding activitySosSettingsBinding8 = this.p;
        if (activitySosSettingsBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosSettingsBinding8 = null;
        }
        activitySosSettingsBinding8.tvTNC.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOSSettings.G(ActivitySOSSettings.this, view);
            }
        });
        ActivitySosSettingsBinding activitySosSettingsBinding9 = this.p;
        if (activitySosSettingsBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySosSettingsBinding = activitySosSettingsBinding9;
        }
        activitySosSettingsBinding.btnSaveContact.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sos.z
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySOSSettings.H(ActivitySOSSettings.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onError(@Nullable String str) {
        dismissProgress();
        if (str != null) {
            K(true, str);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == this.q && permissions.length > 0 && Intrinsics.areEqual(permissions[0], "android.permission.READ_CONTACTS") && grantResults.length > 0 && grantResults[0] == 0) {
            M();
        }
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onSuccess() {
        dismissProgress();
        C(SOSCleverTapConstants.BC_SOS_EMERGENCY_CONTACT_ADDED.getValue());
        B(FirebaseEventParams.EventName.CV_EMERGENCY_CONTACT_SELECT.getValue(), FirebaseEventParams.ScreenName.SOS_CONTACT_SELECT.getValue(), true);
        SessionManager.getInstance(this).saveSOSContact(this.x);
        SessionManager.getInstance(this).saveShowSOSTooltip(true);
        String string = getString(R.string.sos_contact_saved_successfully);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.sos_contact_saved_successfully)");
        K(false, string);
    }

    @Override // com.coveiot.android.sos.adapter.ShowContactsAdapterNew.OnContactSelectedListener
    public void onSyncInfoClicked() {
        C(SOSCleverTapConstants.BC_SOS_SYNC_TAPPED.getValue());
        String string = getString(R.string.sos_sync_desc);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.sos_sync_desc)");
        K(true, string);
    }

    public final void refreshClicked() {
        new RetrieveContactsTask().execute(new Void[0]);
    }

    public final void w(boolean z) {
        ActivitySosSettingsBinding activitySosSettingsBinding = this.p;
        if (activitySosSettingsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySosSettingsBinding = null;
        }
        activitySosSettingsBinding.btnSaveContact.setEnabled(z);
    }

    public final void x() {
        PermissionUtils.checkPermission(this, "android.permission.READ_CONTACTS", new ActivitySOSSettings$getContactList$1(this));
    }

    public final void y() {
        PermissionUtils.checkPermission(this, "android.permission.ACCESS_FINE_LOCATION", new ActivitySOSSettings$getLocationPermission$1(this));
    }

    public final List<CoveContact> z() {
        return UserDataManager.getInstance(this).getSyncedContacts();
    }
}
