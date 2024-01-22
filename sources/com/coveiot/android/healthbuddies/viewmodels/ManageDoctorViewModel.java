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
public final class ManageDoctorViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4589a;
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

    public ManageDoctorViewModel(@NotNull Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.f4589a = mContext;
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
        CoveHealthBuddiesAPIManager.cancelHealthBuddyRequest(Integer.valueOf(i), new CoveApiListener<HealthBuddyResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.healthbuddies.viewmodels.ManageDoctorViewModel$cancelSentHealthBuddyRequest$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                Resources resources;
                ManageDoctorViewModel.this.getLoading().postValue(Boolean.FALSE);
                if (!AppUtils.isNetConnected(ManageDoctorViewModel.this.getMContext())) {
                    Toast.makeText(ManageDoctorViewModel.this.getMContext(), R.string.please_check_your_internet, 0).show();
                    return;
                }
                Context mContext = ManageDoctorViewModel.this.getMContext();
                Context mContext2 = ManageDoctorViewModel.this.getMContext();
                Toast.makeText(mContext, (mContext2 == null || (resources = mContext2.getResources()) == null) ? null : resources.getString(R.string.something_went_wrong), 0).show();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable HealthBuddyResponse healthBuddyResponse) {
                ManageDoctorViewModel.this.getLoading().postValue(Boolean.FALSE);
                ManageDoctorViewModel.this.manageAllBuddies();
            }
        });
    }

    public final void deleteHealthBuddy(int i) {
        this.g.postValue(Boolean.TRUE);
        CoveHealthBuddiesAPIManager.removeHealthBuddyRequest(Integer.valueOf(i), new CoveApiListener<HealthBuddyResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.healthbuddies.viewmodels.ManageDoctorViewModel$deleteHealthBuddy$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                Resources resources;
                ManageDoctorViewModel.this.getLoading().postValue(Boolean.FALSE);
                if (!AppUtils.isNetConnected(ManageDoctorViewModel.this.getMContext())) {
                    Toast.makeText(ManageDoctorViewModel.this.getMContext(), R.string.please_check_your_internet, 0).show();
                    return;
                }
                Context mContext = ManageDoctorViewModel.this.getMContext();
                Context mContext2 = ManageDoctorViewModel.this.getMContext();
                Toast.makeText(mContext, (mContext2 == null || (resources = mContext2.getResources()) == null) ? null : resources.getString(R.string.something_went_wrong), 0).show();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable HealthBuddyResponse healthBuddyResponse) {
                ManageDoctorViewModel.this.getLoading().postValue(Boolean.FALSE);
                ManageDoctorViewModel.this.manageAllBuddies();
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
        return this.f4589a;
    }

    @NotNull
    public final LinkedHashMap<String, List<HealthBuddy>> getMListdata() {
        return this.e;
    }

    public final void h() {
        AppUtils.setLocale(SessionManager.getInstance(this.f4589a).getSelectedLanguage(), this.f4589a);
        CoveHealthBuddiesAPIManager.getHealthBuddies(new CoveApiListener<HealthBuddyResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.healthbuddies.viewmodels.ManageDoctorViewModel$getHealthBuddies$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                MutableLiveData<Boolean> loadViewWithData = ManageDoctorViewModel.this.getLoadViewWithData();
                Boolean bool = Boolean.FALSE;
                loadViewWithData.postValue(bool);
                ManageDoctorViewModel.this.getLoading().postValue(bool);
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
                ManageDoctorViewModel.this.getLoading().postValue(Boolean.FALSE);
                list = ManageDoctorViewModel.this.c;
                if (list != null) {
                    list11 = ManageDoctorViewModel.this.c;
                    Intrinsics.checkNotNull(list11);
                    if (!list11.isEmpty()) {
                        list12 = ManageDoctorViewModel.this.c;
                        Intrinsics.checkNotNull(list12);
                        list12.clear();
                    }
                }
                ArrayList arrayList = new ArrayList();
                Intrinsics.checkNotNull(healthBuddyResponse);
                int size = healthBuddyResponse.getHealthBuddyRequests().size();
                for (int i = 0; i < size; i++) {
                    if (Intrinsics.areEqual(healthBuddyResponse.getHealthBuddyRequests().get(i).relType, ManageDoctorViewModel.this.getMContext().getResources().getString(R.string.familydoc_dependent))) {
                        arrayList.add(healthBuddyResponse.getHealthBuddyRequests().get(i));
                    }
                }
                ManageDoctorViewModel.this.c = arrayList;
                ManageDoctorViewModel manageDoctorViewModel = ManageDoctorViewModel.this;
                list2 = manageDoctorViewModel.c;
                manageDoctorViewModel.c(list2);
                LinkedHashMap<String, List<HealthBuddy>> mListdata = ManageDoctorViewModel.this.getMListdata();
                Context mContext = ManageDoctorViewModel.this.getMContext();
                Intrinsics.checkNotNull(mContext);
                String string = mContext.getResources().getString(R.string.my_doctor);
                Intrinsics.checkNotNullExpressionValue(string, "mContext!!.resources.getString(R.string.my_doctor)");
                list3 = ManageDoctorViewModel.this.c;
                mListdata.put(string, list3);
                HealthBuddiesPreferenceManager.Companion companion = HealthBuddiesPreferenceManager.Companion;
                Context mContext2 = ManageDoctorViewModel.this.getMContext();
                Intrinsics.checkNotNull(mContext2);
                List<HealthBuddy> healthBuddyRequests = healthBuddyResponse.getHealthBuddyRequests();
                Intrinsics.checkNotNullExpressionValue(healthBuddyRequests, "p0!!.healthBuddyRequests");
                companion.saveDoctorHealthBuddies(mContext2, healthBuddyRequests);
                list4 = ManageDoctorViewModel.this.b;
                if (list4 != null) {
                    LinkedHashMap<String, List<HealthBuddy>> mListdata2 = ManageDoctorViewModel.this.getMListdata();
                    Context mContext3 = ManageDoctorViewModel.this.getMContext();
                    Intrinsics.checkNotNull(mContext3);
                    String string2 = mContext3.getResources().getString(R.string.pending_requests);
                    Intrinsics.checkNotNullExpressionValue(string2, "mContext!!.resources.get….string.pending_requests)");
                    list7 = ManageDoctorViewModel.this.b;
                    mListdata2.put(string2, list7);
                    ManageDoctorViewModel.this.d = new ArrayList();
                    list8 = ManageDoctorViewModel.this.d;
                    Intrinsics.checkNotNull(list8);
                    list9 = ManageDoctorViewModel.this.b;
                    Intrinsics.checkNotNull(list9);
                    list8.addAll(list9);
                    Context mContext4 = ManageDoctorViewModel.this.getMContext();
                    Intrinsics.checkNotNull(mContext4);
                    list10 = ManageDoctorViewModel.this.d;
                    Intrinsics.checkNotNull(list10);
                    companion.saveAllDoctorHealthBuddyRequests(mContext4, list10);
                }
                list5 = ManageDoctorViewModel.this.b;
                Intrinsics.checkNotNull(list5);
                if (list5.size() <= 0) {
                    list6 = ManageDoctorViewModel.this.c;
                    Intrinsics.checkNotNull(list6);
                    if (list6.size() <= 0) {
                        ManageDoctorViewModel.this.getLoadViewWithData().postValue(Boolean.FALSE);
                        return;
                    }
                }
                ManageDoctorViewModel.this.getLoadViewWithData().postValue(Boolean.TRUE);
            }
        });
    }

    public final void i() {
        this.g.postValue(Boolean.TRUE);
        AppUtils.setLocale(SessionManager.getInstance(this.f4589a).getSelectedLanguage(), this.f4589a);
        CoveHealthBuddiesAPIManager.getHealthBuddiesRequestLog(new CoveApiListener<HealthBuddyLogResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.healthbuddies.viewmodels.ManageDoctorViewModel$getPendingHealthBuddies$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                MutableLiveData<Boolean> loadViewWithData = ManageDoctorViewModel.this.getLoadViewWithData();
                Boolean bool = Boolean.FALSE;
                loadViewWithData.postValue(bool);
                ManageDoctorViewModel.this.getLoading().postValue(bool);
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
                ManageDoctorViewModel.this.getLoading().postValue(Boolean.FALSE);
                list = ManageDoctorViewModel.this.b;
                if (list != null) {
                    list9 = ManageDoctorViewModel.this.b;
                    Intrinsics.checkNotNull(list9);
                    if (!list9.isEmpty()) {
                        list10 = ManageDoctorViewModel.this.b;
                        Intrinsics.checkNotNull(list10);
                        list10.clear();
                    }
                }
                if (ManageDoctorViewModel.this.getMListdata() != null) {
                    LinkedHashMap<String, List<HealthBuddy>> mListdata = ManageDoctorViewModel.this.getMListdata();
                    Intrinsics.checkNotNull(mListdata);
                    if (!mListdata.isEmpty()) {
                        LinkedHashMap<String, List<HealthBuddy>> mListdata2 = ManageDoctorViewModel.this.getMListdata();
                        Intrinsics.checkNotNull(mListdata2);
                        mListdata2.clear();
                    }
                }
                list2 = ManageDoctorViewModel.this.d;
                if (list2 != null) {
                    list7 = ManageDoctorViewModel.this.d;
                    Intrinsics.checkNotNull(list7);
                    if (!list7.isEmpty()) {
                        list8 = ManageDoctorViewModel.this.d;
                        Intrinsics.checkNotNull(list8);
                        list8.clear();
                    }
                }
                ArrayList arrayList = new ArrayList();
                Intrinsics.checkNotNull(healthBuddyLogResponse);
                int size = healthBuddyLogResponse.pendingHealthBuddyRequests.size();
                for (int i = 0; i < size; i++) {
                    if (Intrinsics.areEqual(healthBuddyLogResponse.pendingHealthBuddyRequests.get(i).relType, ManageDoctorViewModel.this.getMContext().getResources().getString(R.string.familydoc_dependent))) {
                        arrayList.add(healthBuddyLogResponse.pendingHealthBuddyRequests.get(i));
                    }
                }
                ManageDoctorViewModel.this.b = arrayList;
                ManageDoctorViewModel manageDoctorViewModel = ManageDoctorViewModel.this;
                list3 = manageDoctorViewModel.b;
                manageDoctorViewModel.c(list3);
                list4 = ManageDoctorViewModel.this.b;
                if (list4 != null) {
                    list5 = ManageDoctorViewModel.this.b;
                    Intrinsics.checkNotNull(list5);
                    if (!list5.isEmpty()) {
                        HealthBuddiesPreferenceManager.Companion companion = HealthBuddiesPreferenceManager.Companion;
                        Context mContext = ManageDoctorViewModel.this.getMContext();
                        Intrinsics.checkNotNull(mContext);
                        list6 = ManageDoctorViewModel.this.b;
                        Intrinsics.checkNotNull(list6);
                        companion.saveSentDoctorHealthBuddyRequests(mContext, list6);
                    }
                }
                ManageDoctorViewModel.this.h();
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
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.healthbuddies.viewmodels.ManageDoctorViewModel.manageAllBuddies():void");
    }

    public final void reInviteHealthBuddy(int i) {
        this.g.postValue(Boolean.TRUE);
        if (!AppUtils.isNetConnected(this.f4589a)) {
            Toast.makeText(this.f4589a, R.string.please_check_your_internet, 0).show();
            this.g.postValue(Boolean.FALSE);
            return;
        }
        CoveHealthBuddiesAPIManager.reInviteHealthBuddy(Integer.valueOf(i), new CoveApiListener<HealthBuddyResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.healthbuddies.viewmodels.ManageDoctorViewModel$reInviteHealthBuddy$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                Resources resources;
                ManageDoctorViewModel.this.getLoading().postValue(Boolean.FALSE);
                Context mContext = ManageDoctorViewModel.this.getMContext();
                Context mContext2 = ManageDoctorViewModel.this.getMContext();
                Toast.makeText(mContext, (mContext2 == null || (resources = mContext2.getResources()) == null) ? null : resources.getString(R.string.something_went_wrong), 0).show();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable HealthBuddyResponse healthBuddyResponse) {
                ManageDoctorViewModel.this.getLoading().postValue(Boolean.FALSE);
                if (healthBuddyResponse == null || healthBuddyResponse.getHealthBuddyRequests() == null) {
                    return;
                }
                List<HealthBuddy> healthBuddyRequests = healthBuddyResponse.getHealthBuddyRequests();
                Intrinsics.checkNotNull(healthBuddyRequests);
                if (healthBuddyRequests.size() <= 0 || healthBuddyResponse.getHealthBuddyRequests().get(0).inviteMessage == null || healthBuddyResponse.getHealthBuddyRequests().get(0).toUserMobileNumber == null) {
                    return;
                }
                AppUtils.openGenericMessageSharingApp(ManageDoctorViewModel.this.getMContext(), healthBuddyResponse.getHealthBuddyRequests().get(0).inviteMessage);
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
