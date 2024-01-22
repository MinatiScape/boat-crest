package com.coveiot.android.healthbuddies.viewmodels;

import android.content.Context;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.healthbuddies.R;
import com.coveiot.android.healthbuddies.utils.HealthBuddiesPreferenceManager;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.healthbuddies.CoveHealthBuddiesAPIManager;
import com.coveiot.coveaccess.healthbuddies.HealthBuddy;
import com.coveiot.coveaccess.healthbuddies.HealthBuddyRequest;
import com.coveiot.coveaccess.healthbuddies.HealthBuddyResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class AddHealthBuddiesViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4586a;
    @NotNull
    public ArrayList<CoveContact> b;
    @NotNull
    public final ArrayList<HealthBuddy> c;
    @NotNull
    public ArrayList<CoveContact> d;
    @NotNull
    public MutableLiveData<Boolean> e;
    @NotNull
    public MutableLiveData<Boolean> f;
    @NotNull
    public MutableLiveData<Boolean> g;
    @NotNull
    public MutableLiveData<Boolean> h;
    @NotNull
    public final ArrayList<CoveContact> i;

    /* loaded from: classes3.dex */
    public final class RetrieveContactsTask extends AsyncTask<Void, Void, ArrayList<CoveContact>> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public String f4587a;
        public final /* synthetic */ AddHealthBuddiesViewModel b;

        @DebugMetadata(c = "com.coveiot.android.healthbuddies.viewmodels.AddHealthBuddiesViewModel$RetrieveContactsTask$onPostExecute$1", f = "AddHealthBuddiesViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes3.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ ArrayList<CoveContact> $coveContacts;
            public int label;
            public final /* synthetic */ AddHealthBuddiesViewModel this$0;
            public final /* synthetic */ RetrieveContactsTask this$1;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(AddHealthBuddiesViewModel addHealthBuddiesViewModel, ArrayList<CoveContact> arrayList, RetrieveContactsTask retrieveContactsTask, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = addHealthBuddiesViewModel;
                this.$coveContacts = arrayList;
                this.this$1 = retrieveContactsTask;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.this$0, this.$coveContacts, this.this$1, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.this$0.getLoadingStatus().postValue(Boxing.boxBoolean(false));
                    ArrayList<CoveContact> arrayList = new ArrayList<>();
                    ArrayList<CoveContact> arrayList2 = this.$coveContacts;
                    if (arrayList2 == null) {
                        this.this$0.setContacts(arrayList);
                    } else {
                        this.this$0.setContacts(arrayList2);
                    }
                    AddHealthBuddiesViewModel addHealthBuddiesViewModel = this.this$0;
                    addHealthBuddiesViewModel.setSelUsers(addHealthBuddiesViewModel.d(this.this$1.getRelationType()));
                    MutableLiveData<Boolean> valueUpdated = this.this$0.getValueUpdated();
                    Boolean value = this.this$0.getValueUpdated().getValue();
                    valueUpdated.postValue(value != null ? Boxing.boxBoolean(!value.booleanValue()) : null);
                    MutableLiveData<Boolean> selectedValueUpdated = this.this$0.getSelectedValueUpdated();
                    Boolean value2 = this.this$0.getSelectedValueUpdated().getValue();
                    selectedValueUpdated.postValue(value2 != null ? Boxing.boxBoolean(!value2.booleanValue()) : null);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public RetrieveContactsTask(@NotNull AddHealthBuddiesViewModel addHealthBuddiesViewModel, String relationType) {
            Intrinsics.checkNotNullParameter(relationType, "relationType");
            this.b = addHealthBuddiesViewModel;
            this.f4587a = relationType;
        }

        @NotNull
        public final String getRelationType() {
            return this.f4587a;
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            super.onPreExecute();
            this.b.getLoadingStatus().postValue(Boolean.TRUE);
        }

        public final void setRelationType(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.f4587a = str;
        }

        @Override // android.os.AsyncTask
        @NotNull
        public ArrayList<CoveContact> doInBackground(@NotNull Void... voids) {
            Intrinsics.checkNotNullParameter(voids, "voids");
            ArrayList<CoveContact> contacts = AppUtils.getContacts(this.b.getMContext());
            Intrinsics.checkNotNullExpressionValue(contacts, "getContacts(mContext)");
            return contacts;
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(@NotNull ArrayList<CoveContact> coveContacts) {
            Intrinsics.checkNotNullParameter(coveContacts, "coveContacts");
            super.onPostExecute((RetrieveContactsTask) coveContacts);
            e.e(ViewModelKt.getViewModelScope(this.b), Dispatchers.getIO(), null, new a(this.b, coveContacts, this, null), 2, null);
        }
    }

    public AddHealthBuddiesViewModel(@NotNull Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.f4586a = mContext;
        this.b = new ArrayList<>();
        this.c = new ArrayList<>();
        this.d = new ArrayList<>();
        this.e = new MutableLiveData<>();
        this.f = new MutableLiveData<>();
        this.g = new MutableLiveData<>();
        this.h = new MutableLiveData<>();
        this.i = new ArrayList<>();
    }

    public final void a(List<CoveContact> list, CoveContact coveContact, boolean z) {
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
            return;
        }
        list.remove(coveContact2);
        list.add(coveContact);
        if (z) {
            f(coveContact2.getName() + "'s phone number matches " + coveContact.getName() + ". Name updated");
        }
    }

    public final List<HealthBuddy> c(String str) {
        List<HealthBuddy> allHealthBuddyRequests;
        List<HealthBuddy> healthBuddies;
        this.c.clear();
        if (Intrinsics.areEqual(str, this.f4586a.getResources().getString(R.string.familydoc_dependent))) {
            HealthBuddiesPreferenceManager.Companion companion = HealthBuddiesPreferenceManager.Companion;
            allHealthBuddyRequests = companion.getAllDoctorHealthBuddyRequests(this.f4586a);
            healthBuddies = companion.getDoctorHealthBuddies(this.f4586a);
        } else {
            HealthBuddiesPreferenceManager.Companion companion2 = HealthBuddiesPreferenceManager.Companion;
            allHealthBuddyRequests = companion2.getAllHealthBuddyRequests(this.f4586a);
            healthBuddies = companion2.getHealthBuddies(this.f4586a);
        }
        if (allHealthBuddyRequests != null && !AppUtils.isEmpty(allHealthBuddyRequests)) {
            this.c.addAll(allHealthBuddyRequests);
        }
        if (healthBuddies != null && !AppUtils.isEmpty(healthBuddies)) {
            this.c.addAll(healthBuddies);
        }
        return this.c;
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0076 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x003f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.ArrayList<com.coveiot.utils.model.CoveContact> d(java.lang.String r8) {
        /*
            r7 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.ArrayList<com.coveiot.utils.model.CoveContact> r1 = r7.b
            if (r1 == 0) goto L7f
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L15
            int r1 = r1.size()
            if (r1 != 0) goto L15
            r1 = r2
            goto L16
        L15:
            r1 = r3
        L16:
            if (r1 == 0) goto L19
            goto L7f
        L19:
            java.util.List r8 = r7.c(r8)
            if (r8 == 0) goto L7f
            boolean r1 = r8.isEmpty()
            r1 = r1 ^ r2
            if (r1 == 0) goto L7f
            java.util.Iterator r8 = r8.iterator()
        L2a:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L7f
            java.lang.Object r1 = r8.next()
            com.coveiot.coveaccess.healthbuddies.HealthBuddy r1 = (com.coveiot.coveaccess.healthbuddies.HealthBuddy) r1
            java.util.ArrayList<com.coveiot.utils.model.CoveContact> r2 = r7.b
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            java.util.Iterator r2 = r2.iterator()
        L3f:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L2a
            java.lang.Object r4 = r2.next()
            com.coveiot.utils.model.CoveContact r4 = (com.coveiot.utils.model.CoveContact) r4
            r5 = 0
            if (r1 == 0) goto L3f
            java.lang.Integer r6 = r1.userId
            if (r6 == 0) goto L64
            if (r6 != 0) goto L55
            goto L5c
        L55:
            int r6 = r6.intValue()
            if (r6 != 0) goto L5c
            goto L64
        L5c:
            java.lang.String r6 = r1.mobileNumber
            if (r6 == 0) goto L6c
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            goto L6b
        L64:
            java.lang.String r6 = r1.toUserMobileNumber
            if (r6 == 0) goto L6c
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
        L6b:
            r5 = r6
        L6c:
            java.lang.String r6 = r4.getPhoneNumber()
            boolean r5 = android.telephony.PhoneNumberUtils.compare(r5, r6)
            if (r5 == 0) goto L3f
            java.lang.String r5 = "contact"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            r7.a(r0, r4, r3)
            goto L3f
        L7f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.healthbuddies.viewmodels.AddHealthBuddiesViewModel.d(java.lang.String):java.util.ArrayList");
    }

    public final String e(String str) {
        String replace$default = m.replace$default(str, "[^\\d+]", "", false, 4, (Object) null);
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
        boolean startsWith$default = m.startsWith$default(replace$default.subSequence(i, length + 1).toString(), "+", false, 2, null);
        String replace$default2 = m.replace$default(str, "[^\\d]", "", false, 4, (Object) null);
        if (startsWith$default) {
            return '+' + replace$default2;
        }
        return replace$default2;
    }

    public final void f(String str) {
        Toast.makeText(this.f4586a, str, 0).show();
    }

    public final HealthBuddyRequest g(Context context, ArrayList<CoveContact> arrayList, String str) {
        ArrayList arrayList2 = new ArrayList();
        Iterator<CoveContact> it = arrayList.iterator();
        HealthBuddyRequest healthBuddyRequest = null;
        while (it.hasNext()) {
            CoveContact next = it.next();
            Context context2 = this.f4586a;
            String phoneNumber = next.getPhoneNumber();
            Intrinsics.checkNotNullExpressionValue(phoneNumber, "contact.phoneNumber");
            String verifyPhoneNumber = verifyPhoneNumber(context2, phoneNumber);
            if (verifyPhoneNumber == null) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Locale locale = Locale.ENGLISH;
                String string = context.getString(R.string.invalid_phone_number);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.invalid_phone_number)");
                String format = String.format(locale, string, Arrays.copyOf(new Object[]{next.getPhoneNumber()}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                f(format);
                return healthBuddyRequest;
            }
            arrayList2.add(new HealthBuddyRequest.HBuddy(next.getName(), verifyPhoneNumber, str));
            healthBuddyRequest = new HealthBuddyRequest(arrayList2);
        }
        return healthBuddyRequest;
    }

    @NotNull
    public final ArrayList<CoveContact> getContacts() {
        return this.b;
    }

    @NotNull
    public final MutableLiveData<Boolean> getFinishActivity() {
        return this.g;
    }

    @NotNull
    public final MutableLiveData<Boolean> getLoadingStatus() {
        return this.e;
    }

    @NotNull
    public final Context getMContext() {
        return this.f4586a;
    }

    @NotNull
    public final ArrayList<CoveContact> getSelUsers() {
        return this.d;
    }

    @NotNull
    public final ArrayList<CoveContact> getSelectedBuddiesContacts() {
        return this.i;
    }

    @NotNull
    public final MutableLiveData<Boolean> getSelectedValueUpdated() {
        return this.h;
    }

    @NotNull
    public final Map<Long, CoveContact> getTransformedUser(@NotNull List<CoveContact> contacts) {
        Intrinsics.checkNotNullParameter(contacts, "contacts");
        HashMap hashMap = new HashMap();
        if (!contacts.isEmpty()) {
            for (CoveContact coveContact : contacts) {
                coveContact.getRunningContactId();
                hashMap.put(Long.valueOf(coveContact.getRunningContactId()), coveContact);
            }
        }
        return hashMap;
    }

    @Nullable
    public final String getUserCountry(@NotNull Context context) {
        String networkCountryIso;
        Intrinsics.checkNotNullParameter(context, "context");
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
            LogHelper.d("ContentValues", "Exception getting country code");
            return null;
        }
    }

    @NotNull
    public final MutableLiveData<Boolean> getValueUpdated() {
        return this.f;
    }

    public final boolean isValidPhoneNumber(@NotNull String phoneNo) {
        Intrinsics.checkNotNullParameter(phoneNo, "phoneNo");
        try {
            if (StringsKt__StringsKt.contains$default((CharSequence) phoneNo, (CharSequence) "+", false, 2, (Object) null)) {
                phoneNo = m.replace$default(phoneNo, "+", "", false, 4, (Object) null);
            }
            if (StringsKt__StringsKt.contains$default((CharSequence) phoneNo, (CharSequence) HexStringBuilder.DEFAULT_SEPARATOR, false, 2, (Object) null)) {
                phoneNo = new Regex("\\s+").replace(phoneNo, "");
            }
            if (StringsKt__StringsKt.contains$default((CharSequence) phoneNo, (CharSequence) "-", false, 2, (Object) null)) {
                phoneNo = new Regex("-").replace(phoneNo, "");
            }
            if (phoneNo.length() < 6) {
                return false;
            }
            Long.parseLong(phoneNo);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final void loadContacts(@NotNull String relationType) {
        Intrinsics.checkNotNullParameter(relationType, "relationType");
        new RetrieveContactsTask(this, relationType).execute(new Void[0]);
    }

    public final void onSubmitClick(@NotNull String relationType) {
        Intrinsics.checkNotNullParameter(relationType, "relationType");
        if (!AppUtils.isNetConnected(this.f4586a)) {
            String string = this.f4586a.getResources().getString(R.string.please_check_your_internet);
            Intrinsics.checkNotNullExpressionValue(string, "mContext.resources.getSt…ease_check_your_internet)");
            f(string);
            return;
        }
        this.e.postValue(Boolean.TRUE);
        HealthBuddyRequest g = g(this.f4586a, this.i, relationType);
        if (g != null) {
            CoveHealthBuddiesAPIManager.sendHealthBuddyRequest(g, new CoveApiListener<HealthBuddyResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.healthbuddies.viewmodels.AddHealthBuddiesViewModel$onSubmitClick$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    AddHealthBuddiesViewModel.this.getLoadingStatus().postValue(Boolean.FALSE);
                    AddHealthBuddiesViewModel addHealthBuddiesViewModel = AddHealthBuddiesViewModel.this;
                    String string2 = addHealthBuddiesViewModel.getMContext().getResources().getString(R.string.something_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string2, "mContext.resources.getSt…ing.something_went_wrong)");
                    addHealthBuddiesViewModel.f(string2);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable HealthBuddyResponse healthBuddyResponse) {
                    AddHealthBuddiesViewModel.this.getLoadingStatus().postValue(Boolean.FALSE);
                    AppUtils.isBuddies = true;
                    AddHealthBuddiesViewModel.this.getFinishActivity().postValue(Boolean.TRUE);
                    Intrinsics.checkNotNull(healthBuddyResponse);
                    if (healthBuddyResponse.getHealthBuddyRequests() == null || healthBuddyResponse.getHealthBuddyRequests().size() <= 0 || healthBuddyResponse.getHealthBuddyRequests().get(0).inviteMessage == null || healthBuddyResponse.getHealthBuddyRequests().get(0).toUserMobileNumber == null) {
                        return;
                    }
                    AppUtils.openGenericMessageSharingApp(AddHealthBuddiesViewModel.this.getMContext(), healthBuddyResponse.getHealthBuddyRequests().get(0).inviteMessage);
                }
            });
        } else {
            this.e.postValue(Boolean.FALSE);
        }
    }

    public final void onUserSelection(@NotNull CoveContact contact) {
        Boolean value;
        Intrinsics.checkNotNullParameter(contact, "contact");
        if (contact.getPhoneNumber() == null) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String string = this.f4586a.getString(R.string.invalid_phone_number);
            Intrinsics.checkNotNullExpressionValue(string, "mContext.getString(R.string.invalid_phone_number)");
            String format = String.format(locale, string, Arrays.copyOf(new Object[]{contact.getPhoneNumber()}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            f(format);
            return;
        }
        contact.setSelected(true);
        String phoneNumber = contact.getPhoneNumber();
        Intrinsics.checkNotNullExpressionValue(phoneNumber, "contact.phoneNumber");
        String replace$default = m.replace$default(m.replace$default(phoneNumber, "[^0-9+]", "", false, 4, (Object) null), HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null);
        if (!m.startsWith$default(replace$default, "+", false, 2, null)) {
            replace$default = "+91 " + replace$default;
        }
        if (!isValidPhoneNumber(replace$default)) {
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            Locale locale2 = Locale.ENGLISH;
            String string2 = this.f4586a.getString(R.string.invalid_phone_number);
            Intrinsics.checkNotNullExpressionValue(string2, "mContext.getString(R.string.invalid_phone_number)");
            String format2 = String.format(locale2, string2, Arrays.copyOf(new Object[]{contact.getPhoneNumber()}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            f(format2);
            return;
        }
        this.i.clear();
        a(this.i, contact, true);
        MutableLiveData<Boolean> mutableLiveData = this.h;
        mutableLiveData.postValue(mutableLiveData.getValue() != null ? Boolean.valueOf(!value.booleanValue()) : null);
    }

    public final void setContacts(@NotNull ArrayList<CoveContact> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.b = arrayList;
    }

    public final void setFinishActivity(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.g = mutableLiveData;
    }

    public final void setLoadingStatus(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.e = mutableLiveData;
    }

    public final void setSelUsers(@NotNull ArrayList<CoveContact> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.d = arrayList;
    }

    public final void setSelectedValueUpdated(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.h = mutableLiveData;
    }

    public final void setValueUpdated(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.f = mutableLiveData;
    }

    public final String verifyPhoneNumber(Context context, String str) {
        try {
            Phonenumber.PhoneNumber parse = PhoneNumberUtil.getInstance().parse(e(str), getUserCountry(context));
            StringBuilder sb = new StringBuilder();
            sb.append('+');
            sb.append(parse.getCountryCode());
            sb.append(parse.getNationalNumber());
            return sb.toString();
        } catch (NumberParseException unused) {
            return null;
        }
    }
}
