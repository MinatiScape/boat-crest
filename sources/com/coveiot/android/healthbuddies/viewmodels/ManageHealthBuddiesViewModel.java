package com.coveiot.android.healthbuddies.viewmodels;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.healthbuddies.R;
import com.coveiot.android.healthbuddies.utils.HealthBuddiesPreferenceManager;
import com.coveiot.android.tappy.utils.Constants;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.healthbuddies.CoveHealthBuddiesAPIManager;
import com.coveiot.coveaccess.healthbuddies.HealthBuddy;
import com.coveiot.coveaccess.healthbuddies.HealthBuddyLogResponse;
import com.coveiot.coveaccess.healthbuddies.HealthBuddyResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ManageHealthBuddiesViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4595a;
    @Nullable
    public List<HealthBuddy> b;
    @Nullable
    public List<HealthBuddy> c;
    @Nullable
    public List<HealthBuddy> d;
    @NotNull
    public LinkedHashMap<String, List<HealthBuddy>> e;
    @NotNull
    public MutableLiveData<Boolean> f;
    @NotNull
    public MutableLiveData<Boolean> g;

    public ManageHealthBuddiesViewModel(@NotNull Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.f4595a = mContext;
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.e = new LinkedHashMap<>();
        this.f = new MutableLiveData<>();
        this.g = new MutableLiveData<>();
    }

    public final void c(List<HealthBuddy> list) {
        if (list == null || !(!list.isEmpty())) {
            return;
        }
        for (HealthBuddy healthBuddy : list) {
            String str = healthBuddy.lastInvitedDate;
            if (str != null) {
                Intrinsics.checkNotNull(str);
                if (!m.isBlank(str)) {
                    try {
                        healthBuddy.lastInvitedDate = new SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH).format(AppUtils.parseDateUTC(healthBuddy.lastInvitedDate, "yyyy-MM-dd'T'HH:mm:ss.SSS"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                        try {
                            healthBuddy.lastInvitedDate = new SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH).format(AppUtils.parseDateUTC(healthBuddy.lastInvitedDate, Constants.DATE_FORMAT));
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public final void cancelSentHealthBuddyRequest(int i) {
        this.g.postValue(Boolean.TRUE);
        CoveHealthBuddiesAPIManager.cancelHealthBuddyRequest(Integer.valueOf(i), new CoveApiListener<HealthBuddyResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.healthbuddies.viewmodels.ManageHealthBuddiesViewModel$cancelSentHealthBuddyRequest$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                Resources resources;
                ManageHealthBuddiesViewModel.this.getLoading().postValue(Boolean.FALSE);
                if (!AppUtils.isNetConnected(ManageHealthBuddiesViewModel.this.getMContext())) {
                    Toast.makeText(ManageHealthBuddiesViewModel.this.getMContext(), R.string.please_check_your_internet, 0).show();
                    return;
                }
                Context mContext = ManageHealthBuddiesViewModel.this.getMContext();
                Context mContext2 = ManageHealthBuddiesViewModel.this.getMContext();
                Toast.makeText(mContext, (mContext2 == null || (resources = mContext2.getResources()) == null) ? null : resources.getString(R.string.something_went_wrong), 0).show();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable HealthBuddyResponse healthBuddyResponse) {
                ManageHealthBuddiesViewModel.this.getLoading().postValue(Boolean.FALSE);
                ManageHealthBuddiesViewModel.this.manageAllBuddies();
            }
        });
    }

    public final void deleteHealthBuddy(int i) {
        this.g.postValue(Boolean.TRUE);
        CoveHealthBuddiesAPIManager.removeHealthBuddyRequest(Integer.valueOf(i), new CoveApiListener<HealthBuddyResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.healthbuddies.viewmodels.ManageHealthBuddiesViewModel$deleteHealthBuddy$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                Resources resources;
                ManageHealthBuddiesViewModel.this.getLoading().postValue(Boolean.FALSE);
                if (!AppUtils.isNetConnected(ManageHealthBuddiesViewModel.this.getMContext())) {
                    Toast.makeText(ManageHealthBuddiesViewModel.this.getMContext(), R.string.please_check_your_internet, 0).show();
                    return;
                }
                Context mContext = ManageHealthBuddiesViewModel.this.getMContext();
                Context mContext2 = ManageHealthBuddiesViewModel.this.getMContext();
                Toast.makeText(mContext, (mContext2 == null || (resources = mContext2.getResources()) == null) ? null : resources.getString(R.string.something_went_wrong), 0).show();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable HealthBuddyResponse healthBuddyResponse) {
                ManageHealthBuddiesViewModel.this.getLoading().postValue(Boolean.FALSE);
                ManageHealthBuddiesViewModel.this.manageAllBuddies();
            }
        });
    }

    @NotNull
    public final MutableLiveData<Boolean> getLoadViewWithData() {
        return this.f;
    }

    @NotNull
    public final MutableLiveData<Boolean> getLoading() {
        return this.g;
    }

    @NotNull
    public final Context getMContext() {
        return this.f4595a;
    }

    @NotNull
    public final LinkedHashMap<String, List<HealthBuddy>> getMListdata() {
        return this.e;
    }

    public final void h() {
        AppUtils.setLocale(SessionManager.getInstance(this.f4595a).getSelectedLanguage(), this.f4595a);
        CoveHealthBuddiesAPIManager.getHealthBuddies(new CoveApiListener<HealthBuddyResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.healthbuddies.viewmodels.ManageHealthBuddiesViewModel$getHealthBuddies$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                MutableLiveData<Boolean> loadViewWithData = ManageHealthBuddiesViewModel.this.getLoadViewWithData();
                Boolean bool = Boolean.FALSE;
                loadViewWithData.postValue(bool);
                ManageHealthBuddiesViewModel.this.getLoading().postValue(bool);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable HealthBuddyResponse healthBuddyResponse) {
                List list;
                List list2;
                List<HealthBuddy> list3;
                List list4;
                List list5;
                List list6;
                List<HealthBuddy> list7;
                List list8;
                List list9;
                List<? extends HealthBuddy> list10;
                List list11;
                List list12;
                ManageHealthBuddiesViewModel.this.getLoading().postValue(Boolean.FALSE);
                list = ManageHealthBuddiesViewModel.this.c;
                if (list != null) {
                    list11 = ManageHealthBuddiesViewModel.this.c;
                    Intrinsics.checkNotNull(list11);
                    if (!list11.isEmpty()) {
                        list12 = ManageHealthBuddiesViewModel.this.c;
                        Intrinsics.checkNotNull(list12);
                        list12.clear();
                    }
                }
                ArrayList arrayList = new ArrayList();
                Intrinsics.checkNotNull(healthBuddyResponse);
                int size = healthBuddyResponse.getHealthBuddyRequests().size();
                for (int i = 0; i < size; i++) {
                    if (Intrinsics.areEqual(healthBuddyResponse.getHealthBuddyRequests().get(i).relType, ManageHealthBuddiesViewModel.this.getMContext().getResources().getString(R.string.guardian_dependent))) {
                        arrayList.add(healthBuddyResponse.getHealthBuddyRequests().get(i));
                    }
                }
                ManageHealthBuddiesViewModel.this.c = arrayList;
                ManageHealthBuddiesViewModel manageHealthBuddiesViewModel = ManageHealthBuddiesViewModel.this;
                list2 = manageHealthBuddiesViewModel.c;
                manageHealthBuddiesViewModel.c(list2);
                LinkedHashMap<String, List<HealthBuddy>> mListdata = ManageHealthBuddiesViewModel.this.getMListdata();
                Context mContext = ManageHealthBuddiesViewModel.this.getMContext();
                Intrinsics.checkNotNull(mContext);
                String string = mContext.getResources().getString(R.string.my_friends);
                Intrinsics.checkNotNullExpressionValue(string, "mContext!!.resources.get…ring(R.string.my_friends)");
                list3 = ManageHealthBuddiesViewModel.this.c;
                mListdata.put(string, list3);
                HealthBuddiesPreferenceManager.Companion companion = HealthBuddiesPreferenceManager.Companion;
                Context mContext2 = ManageHealthBuddiesViewModel.this.getMContext();
                Intrinsics.checkNotNull(mContext2);
                List<HealthBuddy> healthBuddyRequests = healthBuddyResponse.getHealthBuddyRequests();
                Intrinsics.checkNotNullExpressionValue(healthBuddyRequests, "p0!!.healthBuddyRequests");
                companion.saveHealthBuddies(mContext2, healthBuddyRequests);
                list4 = ManageHealthBuddiesViewModel.this.b;
                if (list4 != null) {
                    LinkedHashMap<String, List<HealthBuddy>> mListdata2 = ManageHealthBuddiesViewModel.this.getMListdata();
                    Context mContext3 = ManageHealthBuddiesViewModel.this.getMContext();
                    Intrinsics.checkNotNull(mContext3);
                    String string2 = mContext3.getResources().getString(R.string.pending_requests);
                    Intrinsics.checkNotNullExpressionValue(string2, "mContext!!.resources.get….string.pending_requests)");
                    list7 = ManageHealthBuddiesViewModel.this.b;
                    mListdata2.put(string2, list7);
                    ManageHealthBuddiesViewModel.this.d = new ArrayList();
                    list8 = ManageHealthBuddiesViewModel.this.d;
                    Intrinsics.checkNotNull(list8);
                    list9 = ManageHealthBuddiesViewModel.this.b;
                    Intrinsics.checkNotNull(list9);
                    list8.addAll(list9);
                    Context mContext4 = ManageHealthBuddiesViewModel.this.getMContext();
                    Intrinsics.checkNotNull(mContext4);
                    list10 = ManageHealthBuddiesViewModel.this.d;
                    Intrinsics.checkNotNull(list10);
                    companion.saveAllHealthBuddyRequests(mContext4, list10);
                }
                list5 = ManageHealthBuddiesViewModel.this.b;
                Intrinsics.checkNotNull(list5);
                if (list5.size() <= 0) {
                    list6 = ManageHealthBuddiesViewModel.this.c;
                    Intrinsics.checkNotNull(list6);
                    if (list6.size() <= 0) {
                        ManageHealthBuddiesViewModel.this.getLoadViewWithData().postValue(Boolean.FALSE);
                        return;
                    }
                }
                ManageHealthBuddiesViewModel.this.getLoadViewWithData().postValue(Boolean.TRUE);
            }
        });
    }

    public final void i() {
        this.g.postValue(Boolean.TRUE);
        AppUtils.setLocale(SessionManager.getInstance(this.f4595a).getSelectedLanguage(), this.f4595a);
        CoveHealthBuddiesAPIManager.getHealthBuddiesRequestLog(new CoveApiListener<HealthBuddyLogResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.healthbuddies.viewmodels.ManageHealthBuddiesViewModel$getPendingHealthBuddies$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                MutableLiveData<Boolean> loadViewWithData = ManageHealthBuddiesViewModel.this.getLoadViewWithData();
                Boolean bool = Boolean.FALSE;
                loadViewWithData.postValue(bool);
                ManageHealthBuddiesViewModel.this.getLoading().postValue(bool);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable HealthBuddyLogResponse healthBuddyLogResponse) {
                List list;
                List list2;
                List list3;
                List list4;
                List list5;
                List<? extends HealthBuddy> list6;
                List list7;
                List list8;
                List list9;
                List list10;
                ManageHealthBuddiesViewModel.this.getLoading().postValue(Boolean.FALSE);
                list = ManageHealthBuddiesViewModel.this.b;
                if (list != null) {
                    list9 = ManageHealthBuddiesViewModel.this.b;
                    Intrinsics.checkNotNull(list9);
                    if (!list9.isEmpty()) {
                        list10 = ManageHealthBuddiesViewModel.this.b;
                        Intrinsics.checkNotNull(list10);
                        list10.clear();
                    }
                }
                if (ManageHealthBuddiesViewModel.this.getMListdata() != null) {
                    LinkedHashMap<String, List<HealthBuddy>> mListdata = ManageHealthBuddiesViewModel.this.getMListdata();
                    Intrinsics.checkNotNull(mListdata);
                    if (!mListdata.isEmpty()) {
                        LinkedHashMap<String, List<HealthBuddy>> mListdata2 = ManageHealthBuddiesViewModel.this.getMListdata();
                        Intrinsics.checkNotNull(mListdata2);
                        mListdata2.clear();
                    }
                }
                list2 = ManageHealthBuddiesViewModel.this.d;
                if (list2 != null) {
                    list7 = ManageHealthBuddiesViewModel.this.d;
                    Intrinsics.checkNotNull(list7);
                    if (!list7.isEmpty()) {
                        list8 = ManageHealthBuddiesViewModel.this.d;
                        Intrinsics.checkNotNull(list8);
                        list8.clear();
                    }
                }
                ArrayList arrayList = new ArrayList();
                Intrinsics.checkNotNull(healthBuddyLogResponse);
                int size = healthBuddyLogResponse.pendingHealthBuddyRequests.size();
                for (int i = 0; i < size; i++) {
                    if (Intrinsics.areEqual(healthBuddyLogResponse.pendingHealthBuddyRequests.get(i).relType, ManageHealthBuddiesViewModel.this.getMContext().getResources().getString(R.string.guardian_dependent))) {
                        arrayList.add(healthBuddyLogResponse.pendingHealthBuddyRequests.get(i));
                    }
                }
                ManageHealthBuddiesViewModel.this.b = arrayList;
                ManageHealthBuddiesViewModel manageHealthBuddiesViewModel = ManageHealthBuddiesViewModel.this;
                list3 = manageHealthBuddiesViewModel.b;
                manageHealthBuddiesViewModel.c(list3);
                list4 = ManageHealthBuddiesViewModel.this.b;
                if (list4 != null) {
                    list5 = ManageHealthBuddiesViewModel.this.b;
                    Intrinsics.checkNotNull(list5);
                    if (!list5.isEmpty()) {
                        HealthBuddiesPreferenceManager.Companion companion = HealthBuddiesPreferenceManager.Companion;
                        Context mContext = ManageHealthBuddiesViewModel.this.getMContext();
                        Intrinsics.checkNotNull(mContext);
                        list6 = ManageHealthBuddiesViewModel.this.b;
                        Intrinsics.checkNotNull(list6);
                        companion.saveSentHealthBuddyRequests(mContext, list6);
                    }
                }
                ManageHealthBuddiesViewModel.this.h();
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x012e, code lost:
        if (r0.size() <= 0) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0141, code lost:
        if (r0.size() > 0) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0143, code lost:
        r6.f.postValue(java.lang.Boolean.TRUE);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:?, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void manageAllBuddies() {
        /*
            Method dump skipped, instructions count: 339
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.healthbuddies.viewmodels.ManageHealthBuddiesViewModel.manageAllBuddies():void");
    }

    public final void reInviteHealthBuddy(int i) {
        this.g.postValue(Boolean.TRUE);
        if (!AppUtils.isNetConnected(this.f4595a)) {
            Toast.makeText(this.f4595a, R.string.please_check_your_internet, 0).show();
            this.g.postValue(Boolean.FALSE);
            return;
        }
        CoveHealthBuddiesAPIManager.reInviteHealthBuddy(Integer.valueOf(i), new CoveApiListener<HealthBuddyResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.healthbuddies.viewmodels.ManageHealthBuddiesViewModel$reInviteHealthBuddy$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                Resources resources;
                ManageHealthBuddiesViewModel.this.getLoading().postValue(Boolean.FALSE);
                Context mContext = ManageHealthBuddiesViewModel.this.getMContext();
                Context mContext2 = ManageHealthBuddiesViewModel.this.getMContext();
                Toast.makeText(mContext, (mContext2 == null || (resources = mContext2.getResources()) == null) ? null : resources.getString(R.string.something_went_wrong), 0).show();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable HealthBuddyResponse healthBuddyResponse) {
                ManageHealthBuddiesViewModel.this.getLoading().postValue(Boolean.FALSE);
                if (healthBuddyResponse == null || healthBuddyResponse.getHealthBuddyRequests() == null) {
                    return;
                }
                List<HealthBuddy> healthBuddyRequests = healthBuddyResponse.getHealthBuddyRequests();
                Intrinsics.checkNotNull(healthBuddyRequests);
                if (healthBuddyRequests.size() <= 0 || healthBuddyResponse.getHealthBuddyRequests().get(0).inviteMessage == null || healthBuddyResponse.getHealthBuddyRequests().get(0).toUserMobileNumber == null) {
                    return;
                }
                AppUtils.openGenericMessageSharingApp(ManageHealthBuddiesViewModel.this.getMContext(), healthBuddyResponse.getHealthBuddyRequests().get(0).inviteMessage);
            }
        });
    }

    public final void setLoadViewWithData(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.f = mutableLiveData;
    }

    public final void setLoading(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.g = mutableLiveData;
    }

    public final void setMListdata(@NotNull LinkedHashMap<String, List<HealthBuddy>> linkedHashMap) {
        Intrinsics.checkNotNullParameter(linkedHashMap, "<set-?>");
        this.e = linkedHashMap;
    }
}
