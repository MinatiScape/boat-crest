package com.coveiot.android.fitnessbuddies.activities;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.fitnessbuddies.AppReferalHandler;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.adapters.ShowContactsAdapter;
import com.coveiot.android.fitnessbuddies.utils.CoveContactsManager;
import com.coveiot.android.fitnessbuddies.utils.CoveUtils;
import com.coveiot.android.fitnessbuddies.utils.PermissionUtils;
import com.coveiot.android.fitnessbuddies.utils.PreferenceManager;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.LoadingDialog;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveSocial;
import com.coveiot.coveaccess.fitnessbuddies.model.SendFitnessBuddyRequest;
import com.coveiot.coveaccess.fitnessbuddies.model.SendFitnessBuddyRequestResponse;
import com.coveiot.coveaccess.fitnessbuddies.model.common.Requests;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
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
public final class AddBuddiesActivity extends BaseActivity implements ShowContactsAdapter.OnBuddiesSelectedListener {
    @Nullable
    public List<CoveContact> q;
    @Nullable
    public List<? extends CoveContact> s;
    @Nullable
    public List<? extends Requests> t;
    @Nullable
    public ShowContactsAdapter w;
    @Nullable
    public LoadingDialog x;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int p = 1;
    @NotNull
    public final ArrayList<Requests> r = new ArrayList<>();
    @NotNull
    public List<CoveContact> u = new ArrayList();
    @NotNull
    public final ArrayList<CoveContact> v = new ArrayList<>();

    /* loaded from: classes4.dex */
    public final class RetrieveContactsTask extends AsyncTask<Void, Void, List<CoveContact>> {
        public RetrieveContactsTask() {
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            super.onPreExecute();
            if (AddBuddiesActivity.this.isFinishing()) {
                return;
            }
            LoadingDialog loadingDialog = AddBuddiesActivity.this.x;
            Intrinsics.checkNotNull(loadingDialog);
            if (loadingDialog.isShowing()) {
                return;
            }
            LoadingDialog loadingDialog2 = AddBuddiesActivity.this.x;
            Intrinsics.checkNotNull(loadingDialog2);
            loadingDialog2.show();
        }

        @Override // android.os.AsyncTask
        @NotNull
        public List<CoveContact> doInBackground(@NotNull Void... voids) {
            Intrinsics.checkNotNullParameter(voids, "voids");
            ArrayList<CoveContact> contacts = AppUtils.getContacts(AddBuddiesActivity.this);
            Intrinsics.checkNotNullExpressionValue(contacts, "getContacts(this@AddBuddiesActivity)");
            return contacts;
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(@NotNull List<CoveContact> coveContacts) {
            Intrinsics.checkNotNullParameter(coveContacts, "coveContacts");
            super.onPostExecute((RetrieveContactsTask) coveContacts);
            if (!AddBuddiesActivity.this.isFinishing()) {
                if (AddBuddiesActivity.this.x != null) {
                    LoadingDialog loadingDialog = AddBuddiesActivity.this.x;
                    Intrinsics.checkNotNull(loadingDialog);
                    if (loadingDialog.isShowing()) {
                        LoadingDialog loadingDialog2 = AddBuddiesActivity.this.x;
                        Intrinsics.checkNotNull(loadingDialog2);
                        loadingDialog2.dismiss();
                    }
                }
                AddBuddiesActivity.this.z(coveContacts);
            }
            CoveContactsManager.INSTANCE.setContacts(coveContacts);
        }
    }

    public static final void A(AddBuddiesActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.I();
    }

    public static final void B(AddBuddiesActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.ADD_BUDDY_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_BUDDY_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.BACK_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        super.onBackPressed();
    }

    public static final void C(AddBuddiesActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (ContextCompat.checkSelfPermission(this$0, "android.permission.READ_CONTACTS") != 0) {
            this$0.v();
        } else {
            this$0.refreshClicked();
        }
    }

    public static final boolean M(AddBuddiesActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ShowContactsAdapter showContactsAdapter = this$0.w;
        Intrinsics.checkNotNull(showContactsAdapter);
        showContactsAdapter.filter(null);
        return false;
    }

    public final void D() {
        if (!AppUtils.isNetConnected(this)) {
            String string = getResources().getString(R.string.please_check_your_internet);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…ease_check_your_internet)");
            H(string);
            return;
        }
        LoadingDialog loadingDialog = this.x;
        Intrinsics.checkNotNull(loadingDialog);
        if (!loadingDialog.isShowing()) {
            LoadingDialog loadingDialog2 = this.x;
            Intrinsics.checkNotNull(loadingDialog2);
            loadingDialog2.show();
        }
        SendFitnessBuddyRequest J = J();
        new AppReferalHandler(this).handleAppReferalFor(this.v);
        if (J != null) {
            CoveSocial.sendFitnessBuddyRequest(J, new CoveApiListener<SendFitnessBuddyRequestResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnessbuddies.activities.AddBuddiesActivity$onSubmitClicked$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    if (AddBuddiesActivity.this.isFinishing() || AddBuddiesActivity.this.x == null) {
                        return;
                    }
                    LoadingDialog loadingDialog3 = AddBuddiesActivity.this.x;
                    Intrinsics.checkNotNull(loadingDialog3);
                    if (loadingDialog3.isShowing()) {
                        LoadingDialog loadingDialog4 = AddBuddiesActivity.this.x;
                        Intrinsics.checkNotNull(loadingDialog4);
                        loadingDialog4.dismiss();
                    }
                    AddBuddiesActivity addBuddiesActivity = AddBuddiesActivity.this;
                    String string2 = addBuddiesActivity.getResources().getString(R.string.something_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.something_went_wrong)");
                    addBuddiesActivity.H(string2);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SendFitnessBuddyRequestResponse sendFitnessBuddyRequestResponse) {
                    if (!AddBuddiesActivity.this.isFinishing() && AddBuddiesActivity.this.x != null) {
                        LoadingDialog loadingDialog3 = AddBuddiesActivity.this.x;
                        Intrinsics.checkNotNull(loadingDialog3);
                        if (loadingDialog3.isShowing()) {
                            LoadingDialog loadingDialog4 = AddBuddiesActivity.this.x;
                            Intrinsics.checkNotNull(loadingDialog4);
                            loadingDialog4.dismiss();
                        }
                        AddBuddiesActivity addBuddiesActivity = AddBuddiesActivity.this;
                        String string2 = addBuddiesActivity.getString(R.string.request_has_been_sent);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.request_has_been_sent)");
                        addBuddiesActivity.H(string2);
                    }
                    AppUtils.isBuddies = true;
                    AddBuddiesActivity.this.finish();
                }
            });
        }
    }

    public final String E(String str) {
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

    public final void F() {
        int i = R.id.search;
        View findViewById = ((SearchView) _$_findCachedViewById(i)).findViewById(R.id.search_src_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.EditText");
        EditText editText = (EditText) findViewById;
        editText.setTextColor(getResources().getColor(R.color.main_text_color));
        editText.setHintTextColor(getResources().getColor(R.color.secondary_text_color));
        ((SearchView) _$_findCachedViewById(i)).setIconifiedByDefault(false);
        ((SearchView) _$_findCachedViewById(i)).setQueryHint(getResources().getString(R.string.search));
    }

    public final void G() {
        if (this.q == null) {
            List<CoveContact> contacts = CoveContactsManager.INSTANCE.getContacts();
            if (contacts == null) {
                new RetrieveContactsTask().execute(new Void[0]);
                return;
            }
            ArrayList arrayList = new ArrayList();
            this.q = arrayList;
            Intrinsics.checkNotNull(arrayList);
            arrayList.addAll(contacts);
            List<CoveContact> list = this.q;
            Intrinsics.checkNotNull(list);
            z(list);
        }
    }

    public final void H(String str) {
        Toast.makeText(this, str, 0).show();
    }

    public final void I() {
        if (this.v.size() > 0) {
            D();
            return;
        }
        String string = getString(R.string.please_select_fitness_clovers);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_select_fitness_clovers)");
        H(string);
    }

    public final SendFitnessBuddyRequest J() {
        ArrayList arrayList = new ArrayList();
        Iterator<CoveContact> it = this.v.iterator();
        SendFitnessBuddyRequest sendFitnessBuddyRequest = null;
        while (it.hasNext()) {
            CoveContact next = it.next();
            String phoneNumber = next.getPhoneNumber();
            Intrinsics.checkNotNullExpressionValue(phoneNumber, "contact.phoneNumber");
            String K = K(this, phoneNumber);
            if (K == null) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Locale locale = Locale.ENGLISH;
                String string = getString(R.string.invalid_phone_number);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.invalid_phone_number)");
                String format = String.format(locale, string, Arrays.copyOf(new Object[]{next.getPhoneNumber()}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                H(format);
                return sendFitnessBuddyRequest;
            }
            arrayList.add(new SendFitnessBuddyRequest.Buddy(next.getName(), K));
            sendFitnessBuddyRequest = new SendFitnessBuddyRequest(arrayList);
        }
        return sendFitnessBuddyRequest;
    }

    public final String K(Context context, String str) {
        try {
            Phonenumber.PhoneNumber parse = PhoneNumberUtil.getInstance().parse(E(str), x(context));
            StringBuilder sb = new StringBuilder();
            sb.append('+');
            sb.append(parse.getCountryCode());
            sb.append(parse.getNationalNumber());
            return sb.toString();
        } catch (NumberParseException unused) {
            return null;
        }
    }

    public final void L() {
        int i = R.id.search;
        ((SearchView) _$_findCachedViewById(i)).setIconifiedByDefault(false);
        ((SearchView) _$_findCachedViewById(i)).setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.coveiot.android.fitnessbuddies.activities.AddBuddiesActivity$watchSearchTextBox$1
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(@NotNull String s) {
                ShowContactsAdapter showContactsAdapter;
                ShowContactsAdapter showContactsAdapter2;
                Intrinsics.checkNotNullParameter(s, "s");
                showContactsAdapter = AddBuddiesActivity.this.w;
                if (showContactsAdapter != null) {
                    showContactsAdapter2 = AddBuddiesActivity.this.w;
                    Intrinsics.checkNotNull(showContactsAdapter2);
                    Locale locale = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
                    String lowerCase = s.toLowerCase(locale);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                    showContactsAdapter2.filter(lowerCase);
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
        ((SearchView) _$_findCachedViewById(i)).setOnCloseListener(new SearchView.OnCloseListener() { // from class: com.coveiot.android.fitnessbuddies.activities.l
            @Override // androidx.appcompat.widget.SearchView.OnCloseListener
            public final boolean onClose() {
                boolean M;
                M = AddBuddiesActivity.M(AddBuddiesActivity.this);
                return M;
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

    @Override // com.coveiot.android.fitnessbuddies.adapters.ShowContactsAdapter.OnBuddiesSelectedListener
    public void onBuddiesContactSelected(@NotNull CoveContact contact) {
        Intrinsics.checkNotNullParameter(contact, "contact");
        contact.setSelected(true);
        if (contact.getPhoneNumber() == null) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String string = getString(R.string.invalid_phone_number);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.invalid_phone_number)");
            String format = String.format(locale, string, Arrays.copyOf(new Object[]{contact.getPhoneNumber()}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            H(format);
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
            H(format2);
            return;
        }
        List<? extends CoveContact> list = this.s;
        Intrinsics.checkNotNull(list);
        Iterator<? extends CoveContact> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().getRunningContactId() == contact.getRunningContactId()) {
                return;
            }
        }
        if (this.u.contains(contact)) {
            this.u.remove(contact);
            this.v.remove(contact);
            StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
            Locale locale3 = Locale.ENGLISH;
            String string3 = getString(R.string.contact_removed);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.contact_removed)");
            String format3 = String.format(locale3, string3, Arrays.copyOf(new Object[]{contact.getName()}, 1));
            Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
            H(format3);
        } else {
            if (this.u.contains(contact)) {
                this.u.remove(contact);
            }
            u(this.v, contact, false);
            u(this.u, contact, true);
        }
        ShowContactsAdapter showContactsAdapter = this.w;
        Intrinsics.checkNotNull(showContactsAdapter);
        List<CoveContact> list2 = this.u;
        List<? extends CoveContact> list3 = this.s;
        Intrinsics.checkNotNull(list3);
        showContactsAdapter.setSelectedContactsForBuddies(list2, list3, this);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_add_buddies);
        F();
        int i = R.id.toolbar;
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(getString(R.string.fitness_and_buddies));
        TextView textView = (TextView) _$_findCachedViewById(i).findViewById(R.id.save);
        textView.setText(R.string.send);
        textView.setVisibility(0);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.activities.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddBuddiesActivity.A(AddBuddiesActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.activities.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddBuddiesActivity.B(AddBuddiesActivity.this, view);
            }
        });
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.contactsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.x = new LoadingDialog(this);
        ((ImageButton) _$_findCachedViewById(R.id.refresh)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.activities.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddBuddiesActivity.C(AddBuddiesActivity.this, view);
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

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == this.p && permissions.length > 0 && Intrinsics.areEqual(permissions[0], "android.permission.READ_CONTACTS") && grantResults.length > 0 && grantResults[0] == 0) {
            G();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        v();
        super.onResume();
    }

    public final void refreshClicked() {
        new RetrieveContactsTask().execute(new Void[0]);
    }

    public final void sendClicked(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.ADD_BUDDY_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_BUDDY_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.SEND_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        if (this.v.size() > 0) {
            D();
            return;
        }
        String string = getString(R.string.please_select_fitness_clovers);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_select_fitness_clovers)");
        H(string);
    }

    public final void u(List<CoveContact> list, CoveContact coveContact, boolean z) {
        CoveContact coveContact2;
        Iterator<CoveContact> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                coveContact2 = null;
                break;
            }
            coveContact2 = it.next();
            if (coveContact == coveContact2) {
                break;
            }
        }
        if (coveContact2 == null) {
            list.add(coveContact);
            if (z) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Locale locale = Locale.ENGLISH;
                String string = getResources().getString(R.string.contact_added);
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.contact_added)");
                String format = String.format(locale, string, Arrays.copyOf(new Object[]{coveContact.getName()}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                H(format);
                return;
            }
            return;
        }
        list.remove(coveContact2);
        list.add(coveContact);
        if (z) {
            H(coveContact2.getName() + "'s phone number matches " + coveContact.getName() + ". Name updated");
        }
    }

    public final void v() {
        PermissionUtils.INSTANCE.checkPermission(this, "android.permission.READ_CONTACTS", new AddBuddiesActivity$getContactList$1(this));
    }

    public final List<CoveContact> w() {
        String str;
        ArrayList arrayList = new ArrayList();
        List<CoveContact> list = this.q;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            if (list.size() != 0) {
                List<Requests> y = y();
                this.t = y;
                if (y != null) {
                    Intrinsics.checkNotNull(y);
                    if (y.size() > 0) {
                        List<? extends Requests> list2 = this.t;
                        Intrinsics.checkNotNull(list2);
                        for (Requests requests : list2) {
                            List<CoveContact> list3 = this.q;
                            Intrinsics.checkNotNull(list3);
                            for (CoveContact coveContact : list3) {
                                if (requests.id == 0) {
                                    str = requests.toUserMobileNumber;
                                    Intrinsics.checkNotNull(str);
                                } else {
                                    str = requests.mobileNumber;
                                    Intrinsics.checkNotNull(str);
                                }
                                if (PhoneNumberUtils.compare(str, coveContact.getPhoneNumber())) {
                                    u(arrayList, coveContact, false);
                                }
                            }
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public final String x(Context context) {
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

    public final List<Requests> y() {
        this.r.clear();
        PreferenceManager.Companion companion = PreferenceManager.Companion;
        List<Requests> allFitnessBuddyRequests = companion.getAllFitnessBuddyRequests(this);
        List<Requests> fitnessBuddies = companion.getFitnessBuddies(this);
        if (allFitnessBuddyRequests != null && (!allFitnessBuddyRequests.isEmpty())) {
            int size = allFitnessBuddyRequests.size();
            for (int i = 0; i < size; i++) {
                if (allFitnessBuddyRequests.get(i).requestStatus.equals("PENDING")) {
                    this.r.add(allFitnessBuddyRequests.get(i));
                }
            }
        }
        if (!CoveUtils.INSTANCE.isEmpty(fitnessBuddies)) {
            ArrayList<Requests> arrayList = this.r;
            Intrinsics.checkNotNull(fitnessBuddies);
            arrayList.addAll(fitnessBuddies);
        }
        return this.r;
    }

    public final void z(List<CoveContact> list) {
        this.q = list;
        Intrinsics.checkNotNull(list);
        this.w = new ShowContactsAdapter(this, list, this, true);
        ((RecyclerView) _$_findCachedViewById(R.id.contactsList)).setAdapter(this.w);
        this.u = w();
        this.s = w();
        ShowContactsAdapter showContactsAdapter = this.w;
        Intrinsics.checkNotNull(showContactsAdapter);
        List<CoveContact> list2 = this.u;
        List<? extends CoveContact> list3 = this.s;
        Intrinsics.checkNotNull(list3);
        showContactsAdapter.setSelectedContactsForBuddies(list2, list3, this);
        L();
    }
}
