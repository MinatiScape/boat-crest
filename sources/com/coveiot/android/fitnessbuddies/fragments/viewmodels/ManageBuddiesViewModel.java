package com.coveiot.android.fitnessbuddies.fragments.viewmodels;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import androidx.lifecycle.AndroidViewModel;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.constants.FitnessConstants;
import com.coveiot.android.fitnessbuddies.fragments.contarctors.ManageBuddiesContaractor;
import com.coveiot.android.fitnessbuddies.utils.CoveUtils;
import com.coveiot.android.fitnessbuddies.utils.PreferenceManager;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveSocial;
import com.coveiot.coveaccess.fitnessbuddies.FitnessBuddiesAction;
import com.coveiot.coveaccess.fitnessbuddies.model.CancelBuddyRequestResponse;
import com.coveiot.coveaccess.fitnessbuddies.model.GetFitnessBuddyRequestsAndBuddiesResponse;
import com.coveiot.coveaccess.fitnessbuddies.model.HandleBuddyRequest;
import com.coveiot.coveaccess.fitnessbuddies.model.UnfriendBuddyResponse;
import com.coveiot.coveaccess.fitnessbuddies.model.common.Requests;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class ManageBuddiesViewModel extends AndroidViewModel {
    @Nullable
    public List<Requests> d;
    @Nullable
    public List<Requests> e;
    @Nullable
    public List<Requests> f;
    @Nullable
    public List<Requests> g;
    @NotNull
    public ManageBuddiesContaractor h;
    @NotNull
    public final String i;
    @NotNull
    public final String j;
    @NotNull
    public final String k;
    @NotNull
    public LinkedHashMap<String, List<Requests>> l;
    @Nullable
    public Context m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ManageBuddiesViewModel(@NotNull Application application, @NotNull ManageBuddiesContaractor manageBuddiesContaractor) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(manageBuddiesContaractor, "manageBuddiesContaractor");
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.h = manageBuddiesContaractor;
        this.i = "accept";
        this.j = "reject";
        this.k = "reinvite";
        this.l = new LinkedHashMap<>();
        this.m = getApplication();
    }

    public final void deleteBuddy(int i) {
        ManageBuddiesContaractor manageBuddiesContaractor = this.h;
        if (manageBuddiesContaractor != null) {
            Intrinsics.checkNotNull(manageBuddiesContaractor);
            manageBuddiesContaractor.showProgress();
        }
        CoveSocial.cancelBuddyRequestSent(Integer.valueOf(i), new CoveApiListener<CancelBuddyRequestResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnessbuddies.fragments.viewmodels.ManageBuddiesViewModel$deleteBuddy$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                ManageBuddiesContaractor manageBuddiesContaractor2;
                ManageBuddiesContaractor manageBuddiesContaractor3;
                Resources resources;
                ManageBuddiesContaractor manageBuddiesContaractor4;
                Resources resources2;
                ManageBuddiesContaractor manageBuddiesContaractor5;
                manageBuddiesContaractor2 = ManageBuddiesViewModel.this.h;
                if (manageBuddiesContaractor2 != null) {
                    manageBuddiesContaractor5 = ManageBuddiesViewModel.this.h;
                    Intrinsics.checkNotNull(manageBuddiesContaractor5);
                    manageBuddiesContaractor5.dismissPerogress();
                }
                String str = null;
                if (!CoveUtils.INSTANCE.isNetConnected(ManageBuddiesViewModel.this.getApplication())) {
                    manageBuddiesContaractor4 = ManageBuddiesViewModel.this.h;
                    Intrinsics.checkNotNull(manageBuddiesContaractor4);
                    Context context = ManageBuddiesViewModel.this.getContext();
                    if (context != null && (resources2 = context.getResources()) != null) {
                        str = resources2.getString(R.string.please_check_your_internet);
                    }
                    Intrinsics.checkNotNull(str);
                    manageBuddiesContaractor4.showMessage(str);
                    return;
                }
                manageBuddiesContaractor3 = ManageBuddiesViewModel.this.h;
                Intrinsics.checkNotNull(manageBuddiesContaractor3);
                Context context2 = ManageBuddiesViewModel.this.getContext();
                if (context2 != null && (resources = context2.getResources()) != null) {
                    str = resources.getString(R.string.something_went_wrong);
                }
                Intrinsics.checkNotNull(str);
                manageBuddiesContaractor3.showMessage(str);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable CancelBuddyRequestResponse cancelBuddyRequestResponse) {
                ManageBuddiesContaractor manageBuddiesContaractor2;
                ManageBuddiesContaractor manageBuddiesContaractor3;
                manageBuddiesContaractor2 = ManageBuddiesViewModel.this.h;
                if (manageBuddiesContaractor2 != null) {
                    manageBuddiesContaractor3 = ManageBuddiesViewModel.this.h;
                    Intrinsics.checkNotNull(manageBuddiesContaractor3);
                    manageBuddiesContaractor3.dismissPerogress();
                }
                ManageBuddiesViewModel.this.manageAllBuddies();
            }
        });
    }

    @Nullable
    public final Context getContext() {
        return this.m;
    }

    public final void h() {
        ManageBuddiesContaractor manageBuddiesContaractor = this.h;
        if (manageBuddiesContaractor != null) {
            Intrinsics.checkNotNull(manageBuddiesContaractor);
            manageBuddiesContaractor.showContent(this.l);
        }
    }

    public final void handleFitnessRequest(int i, @NotNull String action) {
        Resources resources;
        Resources resources2;
        Intrinsics.checkNotNullParameter(action, "action");
        ManageBuddiesContaractor manageBuddiesContaractor = this.h;
        if (manageBuddiesContaractor != null) {
            Intrinsics.checkNotNull(manageBuddiesContaractor);
            manageBuddiesContaractor.showProgress();
        }
        String str = null;
        if (!CoveUtils.INSTANCE.isNetConnected(getApplication())) {
            ManageBuddiesContaractor manageBuddiesContaractor2 = this.h;
            Context context = this.m;
            if (context != null && (resources2 = context.getResources()) != null) {
                str = resources2.getString(R.string.please_check_your_internet);
            }
            Intrinsics.checkNotNull(str);
            manageBuddiesContaractor2.showMessage(str);
            ManageBuddiesContaractor manageBuddiesContaractor3 = this.h;
            Intrinsics.checkNotNull(manageBuddiesContaractor3);
            manageBuddiesContaractor3.dismissPerogress();
        } else if (Intrinsics.areEqual(action, this.i)) {
            CoveSocial.handleBuddyRequest(Integer.valueOf(i), FitnessBuddiesAction.ACCEPT, new CoveApiListener<HandleBuddyRequest, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnessbuddies.fragments.viewmodels.ManageBuddiesViewModel$handleFitnessRequest$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    ManageBuddiesContaractor manageBuddiesContaractor4;
                    ManageBuddiesContaractor manageBuddiesContaractor5;
                    ManageBuddiesContaractor manageBuddiesContaractor6;
                    Resources resources3;
                    manageBuddiesContaractor4 = ManageBuddiesViewModel.this.h;
                    if (manageBuddiesContaractor4 != null) {
                        manageBuddiesContaractor5 = ManageBuddiesViewModel.this.h;
                        Intrinsics.checkNotNull(manageBuddiesContaractor5);
                        manageBuddiesContaractor5.dismissPerogress();
                        manageBuddiesContaractor6 = ManageBuddiesViewModel.this.h;
                        Context context2 = ManageBuddiesViewModel.this.getContext();
                        String string = (context2 == null || (resources3 = context2.getResources()) == null) ? null : resources3.getString(R.string.something_went_wrong);
                        Intrinsics.checkNotNull(string);
                        manageBuddiesContaractor6.showMessage(string);
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable HandleBuddyRequest handleBuddyRequest) {
                    ManageBuddiesContaractor manageBuddiesContaractor4;
                    ManageBuddiesContaractor manageBuddiesContaractor5;
                    manageBuddiesContaractor4 = ManageBuddiesViewModel.this.h;
                    if (manageBuddiesContaractor4 != null) {
                        manageBuddiesContaractor5 = ManageBuddiesViewModel.this.h;
                        Intrinsics.checkNotNull(manageBuddiesContaractor5);
                        manageBuddiesContaractor5.dismissPerogress();
                    }
                    ManageBuddiesViewModel.this.manageAllBuddies();
                }
            });
        } else if (Intrinsics.areEqual(action, this.j)) {
            CoveSocial.handleBuddyRequest(Integer.valueOf(i), FitnessBuddiesAction.REJECT, new CoveApiListener<HandleBuddyRequest, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnessbuddies.fragments.viewmodels.ManageBuddiesViewModel$handleFitnessRequest$2
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    ManageBuddiesContaractor manageBuddiesContaractor4;
                    ManageBuddiesContaractor manageBuddiesContaractor5;
                    ManageBuddiesContaractor manageBuddiesContaractor6;
                    Resources resources3;
                    manageBuddiesContaractor4 = ManageBuddiesViewModel.this.h;
                    if (manageBuddiesContaractor4 != null) {
                        manageBuddiesContaractor5 = ManageBuddiesViewModel.this.h;
                        Intrinsics.checkNotNull(manageBuddiesContaractor5);
                        manageBuddiesContaractor5.dismissPerogress();
                        manageBuddiesContaractor6 = ManageBuddiesViewModel.this.h;
                        Context context2 = ManageBuddiesViewModel.this.getContext();
                        String string = (context2 == null || (resources3 = context2.getResources()) == null) ? null : resources3.getString(R.string.something_went_wrong);
                        Intrinsics.checkNotNull(string);
                        manageBuddiesContaractor6.showMessage(string);
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable HandleBuddyRequest handleBuddyRequest) {
                    ManageBuddiesContaractor manageBuddiesContaractor4;
                    ManageBuddiesContaractor manageBuddiesContaractor5;
                    manageBuddiesContaractor4 = ManageBuddiesViewModel.this.h;
                    if (manageBuddiesContaractor4 != null) {
                        manageBuddiesContaractor5 = ManageBuddiesViewModel.this.h;
                        Intrinsics.checkNotNull(manageBuddiesContaractor5);
                        manageBuddiesContaractor5.dismissPerogress();
                    }
                    ManageBuddiesViewModel.this.manageAllBuddies();
                }
            });
        } else if (Intrinsics.areEqual(action, this.k)) {
            CoveSocial.handleBuddyRequest(Integer.valueOf(i), FitnessBuddiesAction.REINVITE, new CoveApiListener<HandleBuddyRequest, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnessbuddies.fragments.viewmodels.ManageBuddiesViewModel$handleFitnessRequest$3
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    ManageBuddiesContaractor manageBuddiesContaractor4;
                    ManageBuddiesContaractor manageBuddiesContaractor5;
                    ManageBuddiesContaractor manageBuddiesContaractor6;
                    Resources resources3;
                    manageBuddiesContaractor4 = ManageBuddiesViewModel.this.h;
                    if (manageBuddiesContaractor4 != null) {
                        manageBuddiesContaractor5 = ManageBuddiesViewModel.this.h;
                        Intrinsics.checkNotNull(manageBuddiesContaractor5);
                        manageBuddiesContaractor5.dismissPerogress();
                        manageBuddiesContaractor6 = ManageBuddiesViewModel.this.h;
                        Context context2 = ManageBuddiesViewModel.this.getContext();
                        String string = (context2 == null || (resources3 = context2.getResources()) == null) ? null : resources3.getString(R.string.something_went_wrong);
                        Intrinsics.checkNotNull(string);
                        manageBuddiesContaractor6.showMessage(string);
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable HandleBuddyRequest handleBuddyRequest) {
                    ManageBuddiesContaractor manageBuddiesContaractor4;
                    ManageBuddiesContaractor manageBuddiesContaractor5;
                    ManageBuddiesContaractor manageBuddiesContaractor6;
                    Resources resources3;
                    manageBuddiesContaractor4 = ManageBuddiesViewModel.this.h;
                    if (manageBuddiesContaractor4 != null) {
                        manageBuddiesContaractor5 = ManageBuddiesViewModel.this.h;
                        Intrinsics.checkNotNull(manageBuddiesContaractor5);
                        manageBuddiesContaractor5.dismissPerogress();
                        manageBuddiesContaractor6 = ManageBuddiesViewModel.this.h;
                        Context context2 = ManageBuddiesViewModel.this.getContext();
                        String string = (context2 == null || (resources3 = context2.getResources()) == null) ? null : resources3.getString(R.string.invitation_sent);
                        Intrinsics.checkNotNull(string);
                        manageBuddiesContaractor6.showMessage(string);
                    }
                }
            });
        } else {
            ManageBuddiesContaractor manageBuddiesContaractor4 = this.h;
            if (manageBuddiesContaractor4 != null) {
                Intrinsics.checkNotNull(manageBuddiesContaractor4);
                manageBuddiesContaractor4.dismissPerogress();
                ManageBuddiesContaractor manageBuddiesContaractor5 = this.h;
                Context context2 = this.m;
                if (context2 != null && (resources = context2.getResources()) != null) {
                    str = resources.getString(R.string.something_went_wrong);
                }
                Intrinsics.checkNotNull(str);
                manageBuddiesContaractor5.showMessage(str);
            }
        }
    }

    public final void i() {
        ManageBuddiesContaractor manageBuddiesContaractor = this.h;
        if (manageBuddiesContaractor != null) {
            Intrinsics.checkNotNull(manageBuddiesContaractor);
            manageBuddiesContaractor.showEmptyView();
        }
    }

    public final void manageAllBuddies() {
        AppUtils.setLocale(SessionManager.getInstance(this.m).getSelectedLanguage(), this.m);
        boolean z = true;
        if (CoveUtils.INSTANCE.isNetConnected(getApplication())) {
            CoveSocial.getFitnessBuddiesAndRequestsNew(new CoveApiListener<GetFitnessBuddyRequestsAndBuddiesResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnessbuddies.fragments.viewmodels.ManageBuddiesViewModel$manageAllBuddies$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    ManageBuddiesViewModel.this.i();
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetFitnessBuddyRequestsAndBuddiesResponse getFitnessBuddyRequestsAndBuddiesResponse) {
                    List list;
                    List list2;
                    LinkedHashMap linkedHashMap;
                    List list3;
                    List list4;
                    List list5;
                    LinkedHashMap linkedHashMap2;
                    LinkedHashMap linkedHashMap3;
                    List list6;
                    List list7;
                    List<? extends Requests> list8;
                    List list9;
                    List list10;
                    List list11;
                    List<? extends Requests> list12;
                    List list13;
                    List<? extends Requests> list14;
                    List list15;
                    List list16;
                    LinkedHashMap linkedHashMap4;
                    LinkedHashMap linkedHashMap5;
                    List list17;
                    List list18;
                    List list19;
                    List list20;
                    list = ManageBuddiesViewModel.this.d;
                    if (list != null) {
                        list19 = ManageBuddiesViewModel.this.d;
                        Intrinsics.checkNotNull(list19);
                        if (!list19.isEmpty()) {
                            list20 = ManageBuddiesViewModel.this.d;
                            Intrinsics.checkNotNull(list20);
                            list20.clear();
                        }
                    }
                    list2 = ManageBuddiesViewModel.this.e;
                    if (list2 != null) {
                        list17 = ManageBuddiesViewModel.this.e;
                        Intrinsics.checkNotNull(list17);
                        if (!list17.isEmpty()) {
                            list18 = ManageBuddiesViewModel.this.e;
                            Intrinsics.checkNotNull(list18);
                            list18.clear();
                        }
                    }
                    linkedHashMap = ManageBuddiesViewModel.this.l;
                    if (linkedHashMap != null) {
                        linkedHashMap4 = ManageBuddiesViewModel.this.l;
                        Intrinsics.checkNotNull(linkedHashMap4);
                        if (!linkedHashMap4.isEmpty()) {
                            linkedHashMap5 = ManageBuddiesViewModel.this.l;
                            Intrinsics.checkNotNull(linkedHashMap5);
                            linkedHashMap5.clear();
                        }
                    }
                    list3 = ManageBuddiesViewModel.this.g;
                    if (list3 != null) {
                        list15 = ManageBuddiesViewModel.this.g;
                        Intrinsics.checkNotNull(list15);
                        if (!list15.isEmpty()) {
                            list16 = ManageBuddiesViewModel.this.g;
                            Intrinsics.checkNotNull(list16);
                            list16.clear();
                        }
                    }
                    ManageBuddiesViewModel manageBuddiesViewModel = ManageBuddiesViewModel.this;
                    Intrinsics.checkNotNull(getFitnessBuddyRequestsAndBuddiesResponse);
                    manageBuddiesViewModel.d = getFitnessBuddyRequestsAndBuddiesResponse.sentRequests;
                    ManageBuddiesViewModel.this.e = getFitnessBuddyRequestsAndBuddiesResponse.receivedRequests;
                    list4 = ManageBuddiesViewModel.this.d;
                    if (list4 != null) {
                        list13 = ManageBuddiesViewModel.this.d;
                        Intrinsics.checkNotNull(list13);
                        if (!list13.isEmpty()) {
                            PreferenceManager.Companion companion = PreferenceManager.Companion;
                            Application application = ManageBuddiesViewModel.this.getApplication();
                            Intrinsics.checkNotNull(application);
                            list14 = ManageBuddiesViewModel.this.d;
                            Intrinsics.checkNotNull(list14);
                            companion.saveSentFitnessBuddyRequests(application, list14);
                        }
                    }
                    list5 = ManageBuddiesViewModel.this.e;
                    if (list5 != null) {
                        list11 = ManageBuddiesViewModel.this.e;
                        Intrinsics.checkNotNull(list11);
                        if (!list11.isEmpty()) {
                            PreferenceManager.Companion companion2 = PreferenceManager.Companion;
                            Application application2 = ManageBuddiesViewModel.this.getApplication();
                            Intrinsics.checkNotNull(application2);
                            list12 = ManageBuddiesViewModel.this.e;
                            Intrinsics.checkNotNull(list12);
                            companion2.saveFitnessBuddyRequestsRecieved(application2, list12);
                        }
                    }
                    linkedHashMap2 = ManageBuddiesViewModel.this.l;
                    String INVITE_RECEIVED = FitnessConstants.INVITE_RECEIVED;
                    Intrinsics.checkNotNullExpressionValue(INVITE_RECEIVED, "INVITE_RECEIVED");
                    linkedHashMap2.put(INVITE_RECEIVED, getFitnessBuddyRequestsAndBuddiesResponse.receivedRequests);
                    linkedHashMap3 = ManageBuddiesViewModel.this.l;
                    String INVITE_SENT = FitnessConstants.INVITE_SENT;
                    Intrinsics.checkNotNullExpressionValue(INVITE_SENT, "INVITE_SENT");
                    linkedHashMap3.put(INVITE_SENT, getFitnessBuddyRequestsAndBuddiesResponse.sentRequests);
                    ManageBuddiesViewModel.this.g = new ArrayList();
                    list6 = ManageBuddiesViewModel.this.g;
                    Intrinsics.checkNotNull(list6);
                    List<Requests> list21 = getFitnessBuddyRequestsAndBuddiesResponse.sentRequests;
                    Intrinsics.checkNotNullExpressionValue(list21, "p0!!.sentRequests");
                    list6.addAll(list21);
                    list7 = ManageBuddiesViewModel.this.g;
                    Intrinsics.checkNotNull(list7);
                    List<Requests> list22 = getFitnessBuddyRequestsAndBuddiesResponse.receivedRequests;
                    Intrinsics.checkNotNullExpressionValue(list22, "p0!!.receivedRequests");
                    list7.addAll(list22);
                    PreferenceManager.Companion companion3 = PreferenceManager.Companion;
                    Application application3 = ManageBuddiesViewModel.this.getApplication();
                    Intrinsics.checkNotNull(application3);
                    list8 = ManageBuddiesViewModel.this.g;
                    Intrinsics.checkNotNull(list8);
                    companion3.saveAllFitnessBuddyRequests(application3, list8);
                    list9 = ManageBuddiesViewModel.this.d;
                    Intrinsics.checkNotNull(list9);
                    if (list9.size() <= 0) {
                        list10 = ManageBuddiesViewModel.this.e;
                        Intrinsics.checkNotNull(list10);
                        if (list10.size() <= 0) {
                            ManageBuddiesViewModel.this.i();
                            return;
                        }
                    }
                    ManageBuddiesViewModel.this.h();
                }
            }, 1, "totalEarnedBadges,globalRank");
            return;
        }
        List<Requests> list = this.d;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            if (!list.isEmpty()) {
                List<Requests> list2 = this.d;
                Intrinsics.checkNotNull(list2);
                list2.clear();
            }
        }
        List<Requests> list3 = this.e;
        if (list3 != null) {
            Intrinsics.checkNotNull(list3);
            if (!list3.isEmpty()) {
                List<Requests> list4 = this.e;
                Intrinsics.checkNotNull(list4);
                list4.clear();
            }
        }
        LinkedHashMap<String, List<Requests>> linkedHashMap = this.l;
        if (linkedHashMap != null) {
            Intrinsics.checkNotNull(linkedHashMap);
            if (!linkedHashMap.isEmpty()) {
                LinkedHashMap<String, List<Requests>> linkedHashMap2 = this.l;
                Intrinsics.checkNotNull(linkedHashMap2);
                linkedHashMap2.clear();
            }
        }
        List<Requests> list5 = this.g;
        if (list5 != null) {
            Intrinsics.checkNotNull(list5);
            if (!list5.isEmpty()) {
                List<Requests> list6 = this.g;
                Intrinsics.checkNotNull(list6);
                list6.clear();
            }
        }
        this.g = new ArrayList();
        PreferenceManager.Companion companion = PreferenceManager.Companion;
        Application application = getApplication();
        Intrinsics.checkNotNull(application);
        List<Requests> sentFitnessBuddiesRequests = companion.getSentFitnessBuddiesRequests(application);
        if (!(sentFitnessBuddiesRequests == null || sentFitnessBuddiesRequests.isEmpty())) {
            Application application2 = getApplication();
            Intrinsics.checkNotNull(application2);
            this.d = TypeIntrinsics.asMutableList(companion.getSentFitnessBuddiesRequests(application2));
            LinkedHashMap<String, List<Requests>> linkedHashMap3 = this.l;
            String INVITE_SENT = FitnessConstants.INVITE_SENT;
            Intrinsics.checkNotNullExpressionValue(INVITE_SENT, "INVITE_SENT");
            linkedHashMap3.put(INVITE_SENT, this.d);
            List<Requests> list7 = this.g;
            Intrinsics.checkNotNull(list7);
            List<Requests> list8 = this.d;
            Intrinsics.checkNotNull(list8);
            list7.addAll(list8);
        } else {
            LinkedHashMap<String, List<Requests>> linkedHashMap4 = this.l;
            String INVITE_SENT2 = FitnessConstants.INVITE_SENT;
            Intrinsics.checkNotNullExpressionValue(INVITE_SENT2, "INVITE_SENT");
            linkedHashMap4.put(INVITE_SENT2, this.d);
            List<Requests> list9 = this.g;
            Intrinsics.checkNotNull(list9);
            List<Requests> list10 = this.d;
            Intrinsics.checkNotNull(list10);
            list9.addAll(list10);
        }
        Application application3 = getApplication();
        Intrinsics.checkNotNull(application3);
        List<Requests> fitnessBuddiesRequestsReceived = companion.getFitnessBuddiesRequestsReceived(application3);
        if (!(fitnessBuddiesRequestsReceived == null || fitnessBuddiesRequestsReceived.isEmpty())) {
            Application application4 = getApplication();
            Intrinsics.checkNotNull(application4);
            List<Requests> fitnessBuddiesRequestsReceived2 = companion.getFitnessBuddiesRequestsReceived(application4);
            Intrinsics.checkNotNull(fitnessBuddiesRequestsReceived2, "null cannot be cast to non-null type kotlin.collections.MutableList<com.coveiot.coveaccess.fitnessbuddies.model.common.Requests>");
            this.e = TypeIntrinsics.asMutableList(fitnessBuddiesRequestsReceived2);
            LinkedHashMap<String, List<Requests>> linkedHashMap5 = this.l;
            String INVITE_RECEIVED = FitnessConstants.INVITE_RECEIVED;
            Intrinsics.checkNotNullExpressionValue(INVITE_RECEIVED, "INVITE_RECEIVED");
            linkedHashMap5.put(INVITE_RECEIVED, this.e);
            List<Requests> list11 = this.g;
            Intrinsics.checkNotNull(list11);
            List<Requests> list12 = this.e;
            Intrinsics.checkNotNull(list12);
            list11.addAll(list12);
        } else {
            LinkedHashMap<String, List<Requests>> linkedHashMap6 = this.l;
            String INVITE_RECEIVED2 = FitnessConstants.INVITE_RECEIVED;
            Intrinsics.checkNotNullExpressionValue(INVITE_RECEIVED2, "INVITE_RECEIVED");
            linkedHashMap6.put(INVITE_RECEIVED2, this.e);
            List<Requests> list13 = this.g;
            Intrinsics.checkNotNull(list13);
            List<Requests> list14 = this.e;
            Intrinsics.checkNotNull(list14);
            list13.addAll(list14);
        }
        Application application5 = getApplication();
        Intrinsics.checkNotNull(application5);
        List<Requests> fitnessBuddies = companion.getFitnessBuddies(application5);
        if (fitnessBuddies != null && !fitnessBuddies.isEmpty()) {
            z = false;
        }
        if (!z) {
            Application application6 = getApplication();
            Intrinsics.checkNotNull(application6);
            this.f = TypeIntrinsics.asMutableList(companion.getFitnessBuddies(application6));
            LinkedHashMap<String, List<Requests>> linkedHashMap7 = this.l;
            String FIT_CREW = FitnessConstants.FIT_CREW;
            Intrinsics.checkNotNullExpressionValue(FIT_CREW, "FIT_CREW");
            linkedHashMap7.put(FIT_CREW, this.f);
        }
        if (AppUtils.isEmpty(this.d) && AppUtils.isEmpty(this.e)) {
            i();
        } else {
            h();
        }
    }

    public final void setContext(@Nullable Context context) {
        this.m = context;
    }

    public final void unfreiendBuddy(int i) {
        ManageBuddiesContaractor manageBuddiesContaractor = this.h;
        if (manageBuddiesContaractor != null) {
            Intrinsics.checkNotNull(manageBuddiesContaractor);
            manageBuddiesContaractor.showProgress();
        }
        CoveSocial.unfriendBuddy(Integer.valueOf(i), new CoveApiListener<UnfriendBuddyResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnessbuddies.fragments.viewmodels.ManageBuddiesViewModel$unfreiendBuddy$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                ManageBuddiesContaractor manageBuddiesContaractor2;
                ManageBuddiesContaractor manageBuddiesContaractor3;
                Resources resources;
                ManageBuddiesContaractor manageBuddiesContaractor4;
                Resources resources2;
                ManageBuddiesContaractor manageBuddiesContaractor5;
                manageBuddiesContaractor2 = ManageBuddiesViewModel.this.h;
                if (manageBuddiesContaractor2 != null) {
                    manageBuddiesContaractor5 = ManageBuddiesViewModel.this.h;
                    Intrinsics.checkNotNull(manageBuddiesContaractor5);
                    manageBuddiesContaractor5.dismissPerogress();
                }
                String str = null;
                if (!CoveUtils.INSTANCE.isNetConnected(ManageBuddiesViewModel.this.getApplication())) {
                    manageBuddiesContaractor4 = ManageBuddiesViewModel.this.h;
                    Context context = ManageBuddiesViewModel.this.getContext();
                    if (context != null && (resources2 = context.getResources()) != null) {
                        str = resources2.getString(R.string.please_check_your_internet);
                    }
                    Intrinsics.checkNotNull(str);
                    manageBuddiesContaractor4.showMessage(str);
                    return;
                }
                manageBuddiesContaractor3 = ManageBuddiesViewModel.this.h;
                Context context2 = ManageBuddiesViewModel.this.getContext();
                if (context2 != null && (resources = context2.getResources()) != null) {
                    str = resources.getString(R.string.something_went_wrong);
                }
                Intrinsics.checkNotNull(str);
                manageBuddiesContaractor3.showMessage(str);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable UnfriendBuddyResponse unfriendBuddyResponse) {
                ManageBuddiesContaractor manageBuddiesContaractor2;
                ManageBuddiesContaractor manageBuddiesContaractor3;
                manageBuddiesContaractor2 = ManageBuddiesViewModel.this.h;
                if (manageBuddiesContaractor2 != null) {
                    manageBuddiesContaractor3 = ManageBuddiesViewModel.this.h;
                    Intrinsics.checkNotNull(manageBuddiesContaractor3);
                    manageBuddiesContaractor3.dismissPerogress();
                }
                ManageBuddiesViewModel.this.manageAllBuddies();
            }
        });
    }
}
