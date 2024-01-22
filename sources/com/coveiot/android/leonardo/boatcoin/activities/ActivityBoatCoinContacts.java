package com.coveiot.android.leonardo.boatcoin.activities;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivityBoatCoinContactsBinding;
import com.coveiot.android.fitnessbuddies.utils.CoveContactsManager;
import com.coveiot.android.fitnessbuddies.utils.CoveUtils;
import com.coveiot.android.fitnessbuddies.utils.PermissionUtils;
import com.coveiot.android.leonardo.boatcoin.activities.ActivityBoatCoinContacts;
import com.coveiot.android.leonardo.boatcoin.adapter.ShowContactsBoatCoinAdapter;
import com.coveiot.android.leonardo.boatcoin.model.Recipient;
import com.coveiot.android.leonardo.boatcoin.model.RecipientData;
import com.coveiot.android.leonardo.boatcoin.viewmodel.BoatCoinsContactsViewModel;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.LoadingDialog;
import com.coveiot.android.theme.model.BoatCoinsScreenCaller;
import com.coveiot.coveaccess.boatcoins.model.CoinsDataRequest;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityBoatCoinContacts extends BaseActivity implements ShowContactsBoatCoinAdapter.OnBuddiesSelectedListener {
    public ShowContactsBoatCoinAdapter p;
    @Nullable
    public List<CoveContact> r;
    @Nullable
    public LoadingDialog s;
    public int t;
    public boolean u;
    public CoveContact v;
    public ActivityBoatCoinContactsBinding w;
    public BoatCoinsContactsViewModel x;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int q = 1;

    /* loaded from: classes2.dex */
    public static final class a extends Lambda implements Function1<List<Recipient>, Unit> {
        public a() {
            super(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(ActivityBoatCoinContacts this$0, String url, Dialog window, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(window, "$window");
            if (AppUtils.isNetConnected(this$0)) {
                this$0.finish();
                AppNavigator.Companion companion = AppNavigator.Companion;
                Intrinsics.checkNotNullExpressionValue(url, "url");
                companion.navigateToBoatCoinsWebViewer(this$0, url, BoatCoinsScreenCaller.NULL);
            } else {
                Intrinsics.checkNotNullExpressionValue(url, "url");
                this$0.noInternetDialog(url);
            }
            window.dismiss();
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<Recipient> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable List<Recipient> list) {
            if (list == null) {
                ActivityBoatCoinContacts activityBoatCoinContacts = ActivityBoatCoinContacts.this;
                String string = activityBoatCoinContacts.getString(R.string.something_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.something_went_wrong)");
                activityBoatCoinContacts.K(string);
                ActivityBoatCoinContacts.this.setDisable(false);
                return;
            }
            if (!ActivityBoatCoinContacts.this.isFinishing() && ActivityBoatCoinContacts.this.s != null) {
                LoadingDialog loadingDialog = ActivityBoatCoinContacts.this.s;
                Intrinsics.checkNotNull(loadingDialog);
                if (loadingDialog.isShowing()) {
                    LoadingDialog loadingDialog2 = ActivityBoatCoinContacts.this.s;
                    Intrinsics.checkNotNull(loadingDialog2);
                    loadingDialog2.dismiss();
                }
            }
            for (Recipient recipient : list) {
                if (kotlin.text.m.equals$default(recipient.getStatus(), ActivityBoatCoinContacts.this.getString(R.string.ok_caps), false, 2, null)) {
                    final String coinsHomeUrl = SessionManager.getInstance(ActivityBoatCoinContacts.this).getCoinsHomeUrl();
                    final Dialog dialog = new Dialog(ActivityBoatCoinContacts.this, R.style.DialogTheme);
                    dialog.setContentView(R.layout.gift_pop_up);
                    final ActivityBoatCoinContacts activityBoatCoinContacts2 = ActivityBoatCoinContacts.this;
                    ((Button) dialog.findViewById(R.id.done_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.boatcoin.activities.m
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityBoatCoinContacts.a.invoke$lambda$0(ActivityBoatCoinContacts.this, coinsHomeUrl, dialog, view);
                        }
                    });
                    dialog.setCancelable(false);
                    dialog.setCanceledOnTouchOutside(false);
                    Window window = dialog.getWindow();
                    Intrinsics.checkNotNull(window);
                    window.setBackgroundDrawable(new ColorDrawable(0));
                    dialog.show();
                } else if (kotlin.text.m.equals$default(recipient.getStatus(), ActivityBoatCoinContacts.this.getString(R.string.error_uppercase), false, 2, null)) {
                    ActivityBoatCoinContacts.this.setDisable(false);
                    if (kotlin.text.m.equals$default(recipient.getMessage(), ActivityBoatCoinContacts.this.getString(R.string.user_not_found), false, 2, null)) {
                        RecipientData data = recipient.getData();
                        Intrinsics.checkNotNull(data);
                        data.getInviteText();
                        CoveUtils coveUtils = CoveUtils.INSTANCE;
                        ActivityBoatCoinContacts activityBoatCoinContacts3 = ActivityBoatCoinContacts.this;
                        String string2 = activityBoatCoinContacts3.getString(R.string.whatsapp_uri);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.whatsapp_uri)");
                        if (coveUtils.isAppInstalled(activityBoatCoinContacts3, string2)) {
                            if (data.getMobileNumber() != null && data.getInviteText() != null) {
                                ActivityBoatCoinContacts activityBoatCoinContacts4 = ActivityBoatCoinContacts.this;
                                String mobileNumber = data.getMobileNumber();
                                Intrinsics.checkNotNull(mobileNumber);
                                String inviteText = data.getInviteText();
                                Intrinsics.checkNotNull(inviteText);
                                coveUtils.openWhatsApp(activityBoatCoinContacts4, mobileNumber, inviteText);
                            } else {
                                ActivityBoatCoinContacts activityBoatCoinContacts5 = ActivityBoatCoinContacts.this;
                                String string3 = activityBoatCoinContacts5.getString(R.string.something_went_wrong);
                                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.something_went_wrong)");
                                activityBoatCoinContacts5.K(string3);
                            }
                        } else {
                            ActivityBoatCoinContacts activityBoatCoinContacts6 = ActivityBoatCoinContacts.this;
                            String string4 = activityBoatCoinContacts6.getString(R.string.whatsapp_not_installed);
                            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.whatsapp_not_installed)");
                            activityBoatCoinContacts6.K(string4);
                        }
                    } else if (kotlin.text.m.equals$default(recipient.getMessage(), ActivityBoatCoinContacts.this.getString(R.string.cannot_gift), false, 2, null)) {
                        ActivityBoatCoinContacts activityBoatCoinContacts7 = ActivityBoatCoinContacts.this;
                        String string5 = activityBoatCoinContacts7.getString(R.string.cannot_gift);
                        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.cannot_gift)");
                        activityBoatCoinContacts7.K(string5);
                    } else {
                        ActivityBoatCoinContacts activityBoatCoinContacts8 = ActivityBoatCoinContacts.this;
                        String string6 = activityBoatCoinContacts8.getString(R.string.something_went_wrong);
                        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.something_went_wrong)");
                        activityBoatCoinContacts8.K(string6);
                    }
                } else {
                    ActivityBoatCoinContacts activityBoatCoinContacts9 = ActivityBoatCoinContacts.this;
                    String string7 = activityBoatCoinContacts9.getString(R.string.something_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.something_went_wrong)");
                    activityBoatCoinContacts9.K(string7);
                    ActivityBoatCoinContacts.this.setDisable(false);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static final class b extends Lambda implements Function1<List<CoveContact>, Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<CoveContact> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(List<CoveContact> it) {
            ActivityBoatCoinContacts.this.dismissProgress();
            CoveContactsManager coveContactsManager = CoveContactsManager.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            coveContactsManager.setContacts(it);
            ActivityBoatCoinContacts.this.B(it);
        }
    }

    public static final void A(ActivityBoatCoinContacts this$0, Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        AppNavigator.Companion.navigateToActivityDashboard(this$0);
        this$0.finish();
        ((Dialog) dialog.element).dismiss();
    }

    public static final void C(ActivityBoatCoinContacts this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void D(ActivityBoatCoinContacts this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (ContextCompat.checkSelfPermission(this$0, "android.permission.READ_CONTACTS") != 0) {
            this$0.y();
            return;
        }
        this$0.refreshClicked();
        this$0.u = false;
    }

    public static final void E(ActivityBoatCoinContacts this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!this$0.u) {
            this$0.L();
            return;
        }
        ActivityBoatCoinContactsBinding activityBoatCoinContactsBinding = this$0.w;
        if (activityBoatCoinContactsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinContactsBinding = null;
        }
        activityBoatCoinContactsBinding.sendBoatcoins.setOnClickListener(null);
    }

    public static final void F(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void G(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final boolean O(ActivityBoatCoinContacts this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ShowContactsBoatCoinAdapter showContactsBoatCoinAdapter = this$0.p;
        ShowContactsBoatCoinAdapter showContactsBoatCoinAdapter2 = null;
        if (showContactsBoatCoinAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mShowContactsAdapter");
            showContactsBoatCoinAdapter = null;
        }
        showContactsBoatCoinAdapter.filter(null);
        ShowContactsBoatCoinAdapter showContactsBoatCoinAdapter3 = this$0.p;
        if (showContactsBoatCoinAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mShowContactsAdapter");
        } else {
            showContactsBoatCoinAdapter2 = showContactsBoatCoinAdapter3;
        }
        showContactsBoatCoinAdapter2.notifyDataSetChanged();
        return false;
    }

    public static final void z(ActivityBoatCoinContacts this$0, String url, Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(url, "$url");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (AppUtils.isNetConnected(this$0)) {
            this$0.finish();
            AppNavigator.Companion.navigateToBoatCoinsWebViewer(this$0, url, BoatCoinsScreenCaller.NULL);
            ((Dialog) dialog.element).dismiss();
            return;
        }
        Toast.makeText(this$0, "Please check your internet connection", 0).show();
    }

    public final void B(List<CoveContact> list) {
        this.r = list;
        Intrinsics.checkNotNull(list);
        this.p = new ShowContactsBoatCoinAdapter(this, list, this, true);
        ActivityBoatCoinContactsBinding activityBoatCoinContactsBinding = this.w;
        ShowContactsBoatCoinAdapter showContactsBoatCoinAdapter = null;
        if (activityBoatCoinContactsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinContactsBinding = null;
        }
        RecyclerView recyclerView = activityBoatCoinContactsBinding.recyclerViewBoatCoins;
        ShowContactsBoatCoinAdapter showContactsBoatCoinAdapter2 = this.p;
        if (showContactsBoatCoinAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mShowContactsAdapter");
        } else {
            showContactsBoatCoinAdapter = showContactsBoatCoinAdapter2;
        }
        recyclerView.setAdapter(showContactsBoatCoinAdapter);
        N();
    }

    public final void H() {
        if (!AppUtils.isNetConnected(this)) {
            String string = getResources().getString(R.string.please_check_your_internet);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(com.…ease_check_your_internet)");
            K(string);
            return;
        }
        this.u = true;
        CoinsDataRequest M = M();
        if (M != null) {
            BoatCoinsContactsViewModel boatCoinsContactsViewModel = this.x;
            if (boatCoinsContactsViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("boatCoinsContactsViewModel");
                boatCoinsContactsViewModel = null;
            }
            boatCoinsContactsViewModel.sendCoins(M);
            return;
        }
        String string2 = getString(R.string.something_went_wrong);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.something_went_wrong)");
        K(string2);
    }

    public final void I() {
        ActivityBoatCoinContactsBinding activityBoatCoinContactsBinding = this.w;
        ActivityBoatCoinContactsBinding activityBoatCoinContactsBinding2 = null;
        if (activityBoatCoinContactsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinContactsBinding = null;
        }
        ((EditText) activityBoatCoinContactsBinding.search.findViewById(R.id.search_src_text)).setTextColor(getResources().getColor(R.color.main_text_color));
        ActivityBoatCoinContactsBinding activityBoatCoinContactsBinding3 = this.w;
        if (activityBoatCoinContactsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinContactsBinding3 = null;
        }
        ((EditText) activityBoatCoinContactsBinding3.search.findViewById(R.id.search_src_text)).setHintTextColor(getResources().getColor(R.color.secondary_text_color));
        ActivityBoatCoinContactsBinding activityBoatCoinContactsBinding4 = this.w;
        if (activityBoatCoinContactsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinContactsBinding4 = null;
        }
        activityBoatCoinContactsBinding4.search.setIconifiedByDefault(false);
        ActivityBoatCoinContactsBinding activityBoatCoinContactsBinding5 = this.w;
        if (activityBoatCoinContactsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBoatCoinContactsBinding2 = activityBoatCoinContactsBinding5;
        }
        activityBoatCoinContactsBinding2.search.setQueryHint(getResources().getString(R.string.search));
    }

    public final void J() {
        if (this.r == null) {
            List<CoveContact> contacts = CoveContactsManager.INSTANCE.getContacts();
            if (contacts == null) {
                showProgress();
                BoatCoinsContactsViewModel boatCoinsContactsViewModel = this.x;
                if (boatCoinsContactsViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("boatCoinsContactsViewModel");
                    boatCoinsContactsViewModel = null;
                }
                boatCoinsContactsViewModel.retrieveContacts();
                return;
            }
            ArrayList arrayList = new ArrayList();
            this.r = arrayList;
            Intrinsics.checkNotNull(arrayList);
            arrayList.addAll(contacts);
            List<CoveContact> list = this.r;
            Intrinsics.checkNotNull(list);
            B(list);
        }
    }

    public final void K(String str) {
        Toast.makeText(this, str, 0).show();
    }

    public final void L() {
        List<CoveContact> list = this.r;
        Intrinsics.checkNotNull(list);
        boolean z = false;
        for (CoveContact coveContact : list) {
            if (coveContact.isSelected()) {
                this.v = coveContact;
                z = true;
            }
        }
        if (z) {
            H();
            return;
        }
        String string = getString(R.string.please_select_contact);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_select_contact)");
        K(string);
    }

    public final CoinsDataRequest M() {
        ArrayList arrayList = new ArrayList();
        BoatCoinsContactsViewModel boatCoinsContactsViewModel = this.x;
        CoveContact coveContact = null;
        if (boatCoinsContactsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("boatCoinsContactsViewModel");
            boatCoinsContactsViewModel = null;
        }
        CoveContact coveContact2 = this.v;
        if (coveContact2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedContact");
            coveContact2 = null;
        }
        String phoneNumber = coveContact2.getPhoneNumber();
        Intrinsics.checkNotNullExpressionValue(phoneNumber, "selectedContact.phoneNumber");
        String verifyPhoneNumber = boatCoinsContactsViewModel.verifyPhoneNumber(this, phoneNumber);
        if (verifyPhoneNumber == null) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String string = getString(R.string.invalid_phone_number);
            Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…ing.invalid_phone_number)");
            Object[] objArr = new Object[1];
            CoveContact coveContact3 = this.v;
            if (coveContact3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedContact");
                coveContact3 = null;
            }
            objArr[0] = coveContact3.getPhoneNumber();
            String format = String.format(locale, string, Arrays.copyOf(objArr, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            K(format);
            return null;
        }
        CoveContact coveContact4 = this.v;
        if (coveContact4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedContact");
        } else {
            coveContact = coveContact4;
        }
        arrayList.add(new CoinsDataRequest.Recipients(coveContact.getName(), verifyPhoneNumber, Integer.valueOf(this.t)));
        return new CoinsDataRequest(arrayList);
    }

    public final void N() {
        ActivityBoatCoinContactsBinding activityBoatCoinContactsBinding = this.w;
        ActivityBoatCoinContactsBinding activityBoatCoinContactsBinding2 = null;
        if (activityBoatCoinContactsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinContactsBinding = null;
        }
        activityBoatCoinContactsBinding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.coveiot.android.leonardo.boatcoin.activities.ActivityBoatCoinContacts$watchSearchTextBox$1
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(@NotNull String s) {
                ShowContactsBoatCoinAdapter showContactsBoatCoinAdapter;
                ShowContactsBoatCoinAdapter showContactsBoatCoinAdapter2;
                Intrinsics.checkNotNullParameter(s, "s");
                showContactsBoatCoinAdapter = ActivityBoatCoinContacts.this.p;
                if (showContactsBoatCoinAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mShowContactsAdapter");
                }
                showContactsBoatCoinAdapter2 = ActivityBoatCoinContacts.this.p;
                if (showContactsBoatCoinAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mShowContactsAdapter");
                    showContactsBoatCoinAdapter2 = null;
                }
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
                String lowerCase = s.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                showContactsBoatCoinAdapter2.filter(lowerCase);
                return false;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(@NotNull String s) {
                Intrinsics.checkNotNullParameter(s, "s");
                return false;
            }
        });
        ActivityBoatCoinContactsBinding activityBoatCoinContactsBinding3 = this.w;
        if (activityBoatCoinContactsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBoatCoinContactsBinding2 = activityBoatCoinContactsBinding3;
        }
        activityBoatCoinContactsBinding2.search.setOnCloseListener(new SearchView.OnCloseListener() { // from class: com.coveiot.android.leonardo.boatcoin.activities.f
            @Override // androidx.appcompat.widget.SearchView.OnCloseListener
            public final boolean onClose() {
                boolean O;
                O = ActivityBoatCoinContacts.O(ActivityBoatCoinContacts.this);
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

    public final boolean getDisable() {
        return this.u;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, android.app.Dialog] */
    public final void noInternetDialog(@NotNull final String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? dialog = new Dialog(this, 16973829);
        objectRef.element = dialog;
        ((Dialog) dialog).requestWindowFeature(1);
        ((Dialog) objectRef.element).setContentView(R.layout.no_internet_message);
        Button button = (Button) ((Dialog) objectRef.element).findViewById(R.id.btn_retry);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.boatcoin.activities.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityBoatCoinContacts.z(ActivityBoatCoinContacts.this, url, objectRef, view);
                }
            });
        }
        ((TextView) ((Dialog) objectRef.element).findViewById(R.id.btn_home)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.boatcoin.activities.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBoatCoinContacts.A(ActivityBoatCoinContacts.this, objectRef, view);
            }
        });
        ((Dialog) objectRef.element).show();
    }

    @Override // com.coveiot.android.leonardo.boatcoin.adapter.ShowContactsBoatCoinAdapter.OnBuddiesSelectedListener
    public void onBuddiesContactSelected(@NotNull CoveContact contact) {
        Intrinsics.checkNotNullParameter(contact, "contact");
        if (contact.getPhoneNumber() == null) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String string = getString(R.string.invalid_phone_number);
            Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…ing.invalid_phone_number)");
            String format = String.format(locale, string, Arrays.copyOf(new Object[]{contact.getPhoneNumber()}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            K(format);
            return;
        }
        String phoneNumber = contact.getPhoneNumber();
        Intrinsics.checkNotNullExpressionValue(phoneNumber, "contact.phoneNumber");
        String replace$default = kotlin.text.m.replace$default(kotlin.text.m.replace$default(phoneNumber, "[^0-9+]", "", false, 4, (Object) null), HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null);
        ShowContactsBoatCoinAdapter showContactsBoatCoinAdapter = null;
        if (!kotlin.text.m.startsWith$default(replace$default, "+", false, 2, null)) {
            replace$default = "+91 " + replace$default;
        }
        if (!CoveUtils.INSTANCE.isValidPhoneNumber(replace$default)) {
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            Locale locale2 = Locale.ENGLISH;
            String string2 = getString(R.string.invalid_phone_number);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.an…ing.invalid_phone_number)");
            String format2 = String.format(locale2, string2, Arrays.copyOf(new Object[]{contact.getPhoneNumber()}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            K(format2);
            return;
        }
        List<CoveContact> list = this.r;
        if (list != null) {
            int i = 0;
            for (Object obj : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                CoveContact coveContact = (CoveContact) obj;
                LogHelper.d("ActivityBoatCoinsContact", "the index value of the contact " + i);
                if (coveContact.getRunningContactId() == contact.getRunningContactId()) {
                    coveContact.setSelected(!coveContact.isSelected());
                } else {
                    coveContact.setSelected(false);
                }
                i = i2;
            }
        }
        ShowContactsBoatCoinAdapter showContactsBoatCoinAdapter2 = this.p;
        if (showContactsBoatCoinAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mShowContactsAdapter");
        } else {
            showContactsBoatCoinAdapter = showContactsBoatCoinAdapter2;
        }
        showContactsBoatCoinAdapter.notifyDataSetChanged();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityBoatCoinContactsBinding inflate = ActivityBoatCoinContactsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.w = inflate;
        BoatCoinsContactsViewModel boatCoinsContactsViewModel = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        this.t = getIntent().getIntExtra("coin", 0);
        I();
        this.x = (BoatCoinsContactsViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(BoatCoinsContactsViewModel.class);
        ActivityBoatCoinContactsBinding activityBoatCoinContactsBinding = this.w;
        if (activityBoatCoinContactsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinContactsBinding = null;
        }
        activityBoatCoinContactsBinding.toolbarBackArrow.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.boatcoin.activities.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBoatCoinContacts.C(ActivityBoatCoinContacts.this, view);
            }
        });
        ActivityBoatCoinContactsBinding activityBoatCoinContactsBinding2 = this.w;
        if (activityBoatCoinContactsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinContactsBinding2 = null;
        }
        activityBoatCoinContactsBinding2.recyclerViewBoatCoins.setHasFixedSize(true);
        ActivityBoatCoinContactsBinding activityBoatCoinContactsBinding3 = this.w;
        if (activityBoatCoinContactsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinContactsBinding3 = null;
        }
        activityBoatCoinContactsBinding3.recyclerViewBoatCoins.setLayoutManager(new LinearLayoutManager(this));
        ActivityBoatCoinContactsBinding activityBoatCoinContactsBinding4 = this.w;
        if (activityBoatCoinContactsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinContactsBinding4 = null;
        }
        activityBoatCoinContactsBinding4.refresh.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.boatcoin.activities.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBoatCoinContacts.D(ActivityBoatCoinContacts.this, view);
            }
        });
        ActivityBoatCoinContactsBinding activityBoatCoinContactsBinding5 = this.w;
        if (activityBoatCoinContactsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinContactsBinding5 = null;
        }
        activityBoatCoinContactsBinding5.refresh.performClick();
        ActivityBoatCoinContactsBinding activityBoatCoinContactsBinding6 = this.w;
        if (activityBoatCoinContactsBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinContactsBinding6 = null;
        }
        activityBoatCoinContactsBinding6.sendBoatcoins.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.boatcoin.activities.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBoatCoinContacts.E(ActivityBoatCoinContacts.this, view);
            }
        });
        BoatCoinsContactsViewModel boatCoinsContactsViewModel2 = this.x;
        if (boatCoinsContactsViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("boatCoinsContactsViewModel");
            boatCoinsContactsViewModel2 = null;
        }
        MutableLiveData<List<Recipient>> sendCoinResponseLiveData = boatCoinsContactsViewModel2.getSendCoinResponseLiveData();
        final a aVar = new a();
        sendCoinResponseLiveData.observe(this, new Observer() { // from class: com.coveiot.android.leonardo.boatcoin.activities.h
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityBoatCoinContacts.F(Function1.this, obj);
            }
        });
        BoatCoinsContactsViewModel boatCoinsContactsViewModel3 = this.x;
        if (boatCoinsContactsViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("boatCoinsContactsViewModel");
        } else {
            boatCoinsContactsViewModel = boatCoinsContactsViewModel3;
        }
        MutableLiveData<List<CoveContact>> retrieveContactsLiveData = boatCoinsContactsViewModel.getRetrieveContactsLiveData();
        final b bVar = new b();
        retrieveContactsLiveData.observe(this, new Observer() { // from class: com.coveiot.android.leonardo.boatcoin.activities.g
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityBoatCoinContacts.G(Function1.this, obj);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == this.q && permissions.length > 0 && Intrinsics.areEqual(permissions[0], "android.permission.READ_CONTACTS") && grantResults.length > 0 && grantResults[0] == 0) {
            J();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        y();
    }

    public final void refreshClicked() {
        showProgress();
        BoatCoinsContactsViewModel boatCoinsContactsViewModel = this.x;
        if (boatCoinsContactsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("boatCoinsContactsViewModel");
            boatCoinsContactsViewModel = null;
        }
        boatCoinsContactsViewModel.retrieveContacts();
    }

    public final void setDisable(boolean z) {
        this.u = z;
    }

    public final void y() {
        PermissionUtils.INSTANCE.checkPermission(this, "android.permission.READ_CONTACTS", new ActivityBoatCoinContacts$getContactList$1(this));
    }
}
