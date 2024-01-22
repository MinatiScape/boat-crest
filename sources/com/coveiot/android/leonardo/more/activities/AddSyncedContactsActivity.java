package com.coveiot.android.leonardo.more.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ContactData;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.SyncContactsRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.fitnessbuddies.utils.CoveContactsManager;
import com.coveiot.android.fitnessbuddies.utils.CoveUtils;
import com.coveiot.android.fitnessbuddies.utils.PermissionUtils;
import com.coveiot.android.leonardo.more.adapters.ShowContactsAdapter;
import com.coveiot.android.leonardo.more.viewmodel.BTContactsViewmodel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.android.theme.utils.PhoneNumberUtils;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.utility.AppUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class AddSyncedContactsActivity extends BaseActivity implements ShowContactsAdapter.OnBuddiesSelectedListener, SuccessResultListener {
    public ShowContactsAdapter p;
    @Nullable
    public List<CoveContact> r;
    @Nullable
    public List<? extends CoveContact> s;
    public BTContactsViewmodel u;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int q = 1;
    @Nullable
    public List<CoveContact> t = new ArrayList();

    /* loaded from: classes5.dex */
    public final class RetrieveContactsTask extends AsyncTask<Void, Void, List<CoveContact>> {
        public RetrieveContactsTask() {
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            super.onPreExecute();
            AddSyncedContactsActivity.this.showProgress();
        }

        @Override // android.os.AsyncTask
        @NotNull
        public List<CoveContact> doInBackground(@NotNull Void... voids) {
            Intrinsics.checkNotNullParameter(voids, "voids");
            ArrayList<CoveContact> contacts = AppUtils.getContacts(AddSyncedContactsActivity.this);
            Intrinsics.checkNotNullExpressionValue(contacts, "getContacts(this@AddSyncedContactsActivity)");
            return contacts;
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(@NotNull List<CoveContact> coveContacts) {
            Intrinsics.checkNotNullParameter(coveContacts, "coveContacts");
            super.onPostExecute((RetrieveContactsTask) coveContacts);
            AddSyncedContactsActivity.this.dismissProgress();
            CoveContactsManager.INSTANCE.setContacts(coveContacts);
            AddSyncedContactsActivity.this.E(coveContacts);
        }
    }

    public static final void B(AddSyncedContactsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void C(AddSyncedContactsActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.sendClicked(it);
    }

    public static final void D(AddSyncedContactsActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.sendClicked(it);
    }

    public static final void F(AddSyncedContactsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (ContextCompat.checkSelfPermission(this$0, "android.permission.READ_CONTACTS") != 0) {
            this$0.z();
        } else {
            this$0.refreshClicked();
        }
    }

    public static final void H(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void K(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, AddSyncedContactsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void M(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, AddSyncedContactsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final boolean P(AddSyncedContactsActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ShowContactsAdapter showContactsAdapter = this$0.p;
        if (showContactsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mShowContactsAdapter");
            showContactsAdapter = null;
        }
        showContactsAdapter.filter(null);
        return false;
    }

    public final List<CoveContact> A() {
        return UserDataManager.getInstance(this).getSyncedContacts();
    }

    public final void E(List<CoveContact> list) {
        this.r = new ArrayList();
        for (CoveContact coveContact : list) {
            PhoneNumberUtils phoneNumberUtils = PhoneNumberUtils.INSTANCE;
            String phoneNumber = coveContact.getPhoneNumber();
            Intrinsics.checkNotNullExpressionValue(phoneNumber, "i.phoneNumber");
            String verifyPhoneNumber = phoneNumberUtils.verifyPhoneNumber(this, phoneNumber);
            if (verifyPhoneNumber != null) {
                List<CoveContact> list2 = this.r;
                Intrinsics.checkNotNull(list2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.utils.model.CoveContact>");
                ((ArrayList) list2).add(new CoveContact(coveContact.getId(), coveContact.getName(), verifyPhoneNumber, coveContact.getRunningContactId()));
            }
        }
        List<CoveContact> list3 = this.r;
        Intrinsics.checkNotNull(list3);
        this.p = new ShowContactsAdapter(this, list3, this, true);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.contactsList);
        ShowContactsAdapter showContactsAdapter = this.p;
        ShowContactsAdapter showContactsAdapter2 = null;
        if (showContactsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mShowContactsAdapter");
            showContactsAdapter = null;
        }
        recyclerView.setAdapter(showContactsAdapter);
        List<CoveContact> A = A();
        this.t = A;
        if (A == null) {
            this.t = new ArrayList();
        }
        ShowContactsAdapter showContactsAdapter3 = this.p;
        if (showContactsAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mShowContactsAdapter");
        } else {
            showContactsAdapter2 = showContactsAdapter3;
        }
        List<CoveContact> list4 = this.t;
        Intrinsics.checkNotNull(list4);
        showContactsAdapter2.setSelectedContacts(list4);
        O();
    }

    public final void G() {
        if (!AppUtils.isNetConnected(this)) {
            String string = getResources().getString(R.string.please_check_your_internet);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(com.…ease_check_your_internet)");
            N(string);
        } else if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            showProgress();
            ArrayList<ContactData> arrayList = new ArrayList<>();
            List<CoveContact> list = this.t;
            if (list != null) {
                Intrinsics.checkNotNull(list);
                for (CoveContact coveContact : list) {
                    String name = coveContact.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "contact.name");
                    String phoneNumber = coveContact.getPhoneNumber();
                    Intrinsics.checkNotNullExpressionValue(phoneNumber, "contact.phoneNumber");
                    arrayList.add(new ContactData(name, phoneNumber));
                }
            }
            SyncContactsRequest syncContactsRequest = new SyncContactsRequest.Builder().Builder(arrayList).build();
            BleApi bleApi = BleApiManager.getInstance(this).getBleApi();
            Intrinsics.checkNotNullExpressionValue(syncContactsRequest, "syncContactsRequest");
            bleApi.getData(syncContactsRequest, new DataResultListener() { // from class: com.coveiot.android.leonardo.more.activities.AddSyncedContactsActivity$onSubmitClicked$1

                @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.AddSyncedContactsActivity$onSubmitClicked$1$onDataError$1", f = "AddSyncedContactsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes5.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ AddSyncedContactsActivity this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(AddSyncedContactsActivity addSyncedContactsActivity, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = addSyncedContactsActivity;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.this$0, continuation);
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
                            this.this$0.dismissProgress();
                            this.this$0.J();
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.AddSyncedContactsActivity$onSubmitClicked$1$onDataResponse$1", f = "AddSyncedContactsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes5.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ AddSyncedContactsActivity this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(AddSyncedContactsActivity addSyncedContactsActivity, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.this$0 = addSyncedContactsActivity;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new b(this.this$0, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.this$0.dismissProgress();
                            this.this$0.showSuccessDialog();
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(AddSyncedContactsActivity.this), Dispatchers.getMain(), null, new a(AddSyncedContactsActivity.this, null), 2, null);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    List list2;
                    BTContactsViewmodel bTContactsViewmodel;
                    List<? extends CoveContact> list3;
                    Intrinsics.checkNotNullParameter(response, "response");
                    UserDataManager userDataManager = UserDataManager.getInstance(AddSyncedContactsActivity.this);
                    list2 = AddSyncedContactsActivity.this.t;
                    userDataManager.saveSyncedContacts((ArrayList) list2);
                    bTContactsViewmodel = AddSyncedContactsActivity.this.u;
                    if (bTContactsViewmodel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("btContactsViewModel");
                        bTContactsViewmodel = null;
                    }
                    list3 = AddSyncedContactsActivity.this.t;
                    bTContactsViewmodel.saveBTContactsToServer(list3);
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(AddSyncedContactsActivity.this), Dispatchers.getMain(), null, new b(AddSyncedContactsActivity.this, null), 2, null);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
        } else {
            String string2 = getString(R.string.band_not_connected);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.band_not_connected)");
            String string3 = getString(R.string.please_connect_the_device);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.please_connect_the_device)");
            final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string2, string3);
            String string4 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.hk
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AddSyncedContactsActivity.H(BottomSheetDialogOneButtonTitleMessage.this, view);
                }
            });
            bottomSheetDialogOneButtonTitleMessage.show();
        }
    }

    public final void I() {
        int i = R.id.search;
        View findViewById = ((SearchView) _$_findCachedViewById(i)).findViewById(R.id.search_src_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.EditText");
        EditText editText = (EditText) findViewById;
        editText.setTextColor(getResources().getColor(17170443));
        editText.setHintTextColor(getResources().getColor(17170443));
        ((SearchView) _$_findCachedViewById(i)).setIconifiedByDefault(false);
        ((SearchView) _$_findCachedViewById(i)).setQueryHint(getResources().getString(R.string.search));
    }

    public final void J() {
        if (isFinishing()) {
            return;
        }
        String string = getResources().getString(R.string.contact_sync_failed);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.contact_sync_failed)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getResources().getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …R.string.ok\n            )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.gk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddSyncedContactsActivity.K(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void L() {
        String string = getString(R.string.info);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.info)");
        String string2 = getString(R.string.max_contacts_error_msg);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.max_contacts_error_msg)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
        bottomSheetDialogOneButtonTitleMessage.setCancelableOutside(false);
        bottomSheetDialogOneButtonTitleMessage.setCancelable(false);
        String string3 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.AddSyncedContactsActivity$showMaxContactsError$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogOneButtonTitleMessage.this.dismiss();
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void N(String str) {
        Toast.makeText(this, str, 0).show();
    }

    public final void O() {
        int i = R.id.search;
        ((SearchView) _$_findCachedViewById(i)).setIconifiedByDefault(false);
        ((SearchView) _$_findCachedViewById(i)).setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.coveiot.android.leonardo.more.activities.AddSyncedContactsActivity$watchSearchTextBox$1
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(@NotNull String s) {
                ShowContactsAdapter showContactsAdapter;
                ShowContactsAdapter showContactsAdapter2;
                Intrinsics.checkNotNullParameter(s, "s");
                showContactsAdapter = AddSyncedContactsActivity.this.p;
                if (showContactsAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mShowContactsAdapter");
                }
                showContactsAdapter2 = AddSyncedContactsActivity.this.p;
                if (showContactsAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mShowContactsAdapter");
                    showContactsAdapter2 = null;
                }
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
                String lowerCase = s.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                showContactsAdapter2.filter(lowerCase);
                return false;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(@NotNull String s) {
                Intrinsics.checkNotNullParameter(s, "s");
                return false;
            }
        });
        ((SearchView) _$_findCachedViewById(i)).setOnCloseListener(new SearchView.OnCloseListener() { // from class: com.coveiot.android.leonardo.more.activities.ik
            @Override // androidx.appcompat.widget.SearchView.OnCloseListener
            public final boolean onClose() {
                boolean P;
                P = AddSyncedContactsActivity.P(AddSyncedContactsActivity.this);
                return P;
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

    public final void initToolbar() {
        int i = R.id.toolbar;
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(R.string.add_contacts);
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ck
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddSyncedContactsActivity.B(AddSyncedContactsActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.save)).setText(R.string.sync);
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.save)).setVisibility(8);
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.save)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.bk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddSyncedContactsActivity.C(AddSyncedContactsActivity.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.ek
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddSyncedContactsActivity.D(AddSyncedContactsActivity.this, view);
            }
        });
    }

    @Override // com.coveiot.android.leonardo.more.adapters.ShowContactsAdapter.OnBuddiesSelectedListener
    public void onBuddiesContactSelected(@NotNull CoveContact contact) {
        Intrinsics.checkNotNullParameter(contact, "contact");
        contact.setSelected(true);
        String phoneNumber = contact.getPhoneNumber();
        Intrinsics.checkNotNullExpressionValue(phoneNumber, "contact.phoneNumber");
        String replace$default = kotlin.text.m.replace$default(kotlin.text.m.replace$default(phoneNumber, "[^0-9+]", "", false, 4, (Object) null), HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null);
        ShowContactsAdapter showContactsAdapter = null;
        if (!kotlin.text.m.startsWith$default(replace$default, "+", false, 2, null)) {
            replace$default = "+91 " + replace$default;
        }
        if (!CoveUtils.INSTANCE.isValidPhoneNumber(replace$default)) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String string = getString(R.string.invalid_phone_number);
            Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…ing.invalid_phone_number)");
            String format = String.format(locale, string, Arrays.copyOf(new Object[]{contact.getPhoneNumber()}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            N(format);
            return;
        }
        List<? extends CoveContact> list = this.s;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            Iterator<? extends CoveContact> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().getRunningContactId() == contact.getRunningContactId()) {
                    return;
                }
            }
        }
        List<CoveContact> list2 = this.t;
        if (list2 != null) {
            Intrinsics.checkNotNull(list2);
            if (list2.contains(contact)) {
                List<CoveContact> list3 = this.t;
                Intrinsics.checkNotNull(list3);
                list3.remove(contact);
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                Locale locale2 = Locale.ENGLISH;
                String string2 = getString(R.string.contact_removed);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.an…R.string.contact_removed)");
                String format2 = String.format(locale2, string2, Arrays.copyOf(new Object[]{contact.getName()}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                N(format2);
            } else {
                List<CoveContact> list4 = this.t;
                Intrinsics.checkNotNull(list4);
                if (list4.contains(contact)) {
                    List<CoveContact> list5 = this.t;
                    Intrinsics.checkNotNull(list5);
                    list5.remove(contact);
                }
                if (BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().isContactSyncSupported()) {
                    int maxContactsInOneRequest = BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().getMaxContactsInOneRequest();
                    List<CoveContact> list6 = this.t;
                    Intrinsics.checkNotNull(list6);
                    if (list6.size() >= maxContactsInOneRequest) {
                        L();
                        return;
                    }
                }
                List<CoveContact> list7 = this.t;
                Intrinsics.checkNotNull(list7);
                y(list7, contact, true);
            }
        }
        List<CoveContact> list8 = this.t;
        if (list8 != null) {
            ShowContactsAdapter showContactsAdapter2 = this.p;
            if (showContactsAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mShowContactsAdapter");
            } else {
                showContactsAdapter = showContactsAdapter2;
            }
            Intrinsics.checkNotNull(showContactsAdapter);
            showContactsAdapter.setSelectedContacts(list8);
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_add_synced_contacts);
        I();
        initToolbar();
        BTContactsViewmodel bTContactsViewmodel = new BTContactsViewmodel(this);
        this.u = bTContactsViewmodel;
        bTContactsViewmodel.setMListener(this);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.contactsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.s = UserDataManager.getInstance(this).getSyncedContacts();
        int maxContactsInOneRequest = BleApiManager.getInstance(this).getBleApi().getDeviceSupportedFeatures().getMaxContactsInOneRequest();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(R.string.only_20_contacts_can_be_selected);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.only_…contacts_can_be_selected)");
        String format = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(maxContactsInOneRequest)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        ((TextView) _$_findCachedViewById(R.id.only_20_contacts_can_be)).setText(format);
        int i = R.id.refresh;
        ((ImageButton) _$_findCachedViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.dk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddSyncedContactsActivity.F(AddSyncedContactsActivity.this, view);
            }
        });
        ((ImageButton) _$_findCachedViewById(i)).performClick();
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onError(@Nullable String str) {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_CONTACTS") == 0) {
            refreshClicked();
        }
    }

    @Override // com.coveiot.android.leonardo.more.adapters.ShowContactsAdapter.OnBuddiesSelectedListener
    public void onSOSInfoClicked() {
        ThemesUtils.INSTANCE.showCommonMessageDialog(this, "Chosen as the emergency contact number");
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onSuccess() {
    }

    public final void refreshClicked() {
        new RetrieveContactsTask().execute(new Void[0]);
    }

    public final void sendClicked(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        List<CoveContact> list = this.t;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            if (list.size() > 0) {
                G();
                return;
            }
            String string = getString(R.string.please_select_contact);
            Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…ng.please_select_contact)");
            N(string);
        }
    }

    public final void showSuccessDialog() {
        if (isFinishing()) {
            return;
        }
        String string = getResources().getString(R.string.contact_sync_success);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.contact_sync_success)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getResources().getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …R.string.ok\n            )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.fk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddSyncedContactsActivity.M(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void y(List<CoveContact> list, CoveContact coveContact, boolean z) {
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
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(com.…s.R.string.contact_added)");
                String format = String.format(locale, string, Arrays.copyOf(new Object[]{coveContact.getName()}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                N(format);
            }
        } else {
            list.remove(coveContact2);
            list.add(coveContact);
            if (z) {
                N(coveContact2.getName() + "'s phone number matches " + coveContact.getName() + ". Name updated");
            }
        }
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setEnabled(true);
    }

    public final void z() {
        PermissionUtils.INSTANCE.checkPermission(this, "android.permission.READ_CONTACTS", new AddSyncedContactsActivity$getContactList$1(this));
    }
}
