package com.coveiot.android.fitnessbuddies.fragments.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.coveiot.android.fitnessbuddies.fragments.contarctors.NotificationsContarctor;
import com.coveiot.android.fitnessbuddies.utils.CoveUtils;
import com.coveiot.android.fitnessbuddies.utils.PreferenceManager;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveSocial;
import com.coveiot.coveaccess.fitnessbuddies.model.GetFitnessBuddiesGoalsResponse;
import com.coveiot.coveaccess.fitnessbuddies.model.GetFitnessBuddiesResponse;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyDataBean;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyItems;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyListModel;
import com.coveiot.coveaccess.fitnessbuddies.model.common.BuddiesGoal;
import com.coveiot.coveaccess.fitnessbuddies.model.common.Requests;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class NotificationsViewModel extends AndroidViewModel {
    @NotNull
    public final NotificationsContarctor d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationsViewModel(@NotNull Application application, @NotNull NotificationsContarctor notificationsContarctor) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(notificationsContarctor, "notificationsContarctor");
        this.d = notificationsContarctor;
    }

    public final void getFitnessBuddies() {
        AppUtils.setLocale(SessionManager.getInstance(getApplication()).getSelectedLanguage(), getApplication());
        CoveSocial.getFitnessBuddies(new CoveApiListener<GetFitnessBuddiesResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnessbuddies.fragments.viewmodels.NotificationsViewModel$getFitnessBuddies$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetFitnessBuddiesResponse getFitnessBuddiesResponse) {
                PreferenceManager.Companion companion = PreferenceManager.Companion;
                Application application = NotificationsViewModel.this.getApplication();
                Intrinsics.checkNotNull(application);
                Intrinsics.checkNotNull(getFitnessBuddiesResponse);
                List<Requests> list = getFitnessBuddiesResponse.fitnessBuddies;
                Intrinsics.checkNotNullExpressionValue(list, "p0!!.fitnessBuddies");
                companion.saveFitnessBuddies(application, list);
            }
        });
    }

    @NotNull
    public final NotificationsContarctor getNotificationsContarctor() {
        return this.d;
    }

    public final void loadBuddiesDetailsInformation() {
        CoveUtils coveUtils = CoveUtils.INSTANCE;
        Application application = getApplication();
        Intrinsics.checkNotNull(application);
        boolean isNetConnected = coveUtils.isNetConnected(application);
        boolean z = true;
        if (isNetConnected) {
            CoveSocial.getBuddiesStats(1, "totalEarnedBadges,globalRank", new CoveApiListener<GetBuddyListModel, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnessbuddies.fragments.viewmodels.NotificationsViewModel$loadBuddiesDetailsInformation$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    if (NotificationsViewModel.this.getNotificationsContarctor() != null) {
                        NotificationsContarctor notificationsContarctor = NotificationsViewModel.this.getNotificationsContarctor();
                        Intrinsics.checkNotNull(notificationsContarctor);
                        notificationsContarctor.dismissProgress();
                    }
                    if (NotificationsViewModel.this.getNotificationsContarctor() != null) {
                        NotificationsContarctor notificationsContarctor2 = NotificationsViewModel.this.getNotificationsContarctor();
                        Intrinsics.checkNotNull(notificationsContarctor2);
                        notificationsContarctor2.showEmptyLayout();
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetBuddyListModel getBuddyListModel) {
                    GetBuddyDataBean data;
                    List<GetBuddyItems> itemsList;
                    if (NotificationsViewModel.this.getNotificationsContarctor() != null) {
                        NotificationsContarctor notificationsContarctor = NotificationsViewModel.this.getNotificationsContarctor();
                        Intrinsics.checkNotNull(notificationsContarctor);
                        notificationsContarctor.dismissProgress();
                    }
                    Integer valueOf = (getBuddyListModel == null || (data = getBuddyListModel.getData()) == null || (itemsList = data.getItemsList()) == null) ? null : Integer.valueOf(itemsList.size());
                    Intrinsics.checkNotNull(valueOf);
                    if (valueOf.intValue() > 0) {
                        PreferenceManager.Companion companion = PreferenceManager.Companion;
                        Application application2 = NotificationsViewModel.this.getApplication();
                        Intrinsics.checkNotNull(application2);
                        List<GetBuddyItems> itemsList2 = getBuddyListModel.getData().getItemsList();
                        Intrinsics.checkNotNullExpressionValue(itemsList2, "p0.data.itemsList");
                        companion.saveBuddiesListDetails(application2, itemsList2);
                        if (NotificationsViewModel.this.getNotificationsContarctor() != null) {
                            NotificationsContarctor notificationsContarctor2 = NotificationsViewModel.this.getNotificationsContarctor();
                            Intrinsics.checkNotNull(notificationsContarctor2);
                            List<GetBuddyItems> itemsList3 = getBuddyListModel.getData().getItemsList();
                            Intrinsics.checkNotNullExpressionValue(itemsList3, "p0.data.itemsList");
                            notificationsContarctor2.showBuddyContents(itemsList3);
                        }
                    } else if (NotificationsViewModel.this.getNotificationsContarctor() != null) {
                        NotificationsContarctor notificationsContarctor3 = NotificationsViewModel.this.getNotificationsContarctor();
                        Intrinsics.checkNotNull(notificationsContarctor3);
                        notificationsContarctor3.showEmptyLayout();
                    }
                }
            });
            return;
        }
        NotificationsContarctor notificationsContarctor = this.d;
        if (notificationsContarctor != null) {
            Intrinsics.checkNotNull(notificationsContarctor);
            notificationsContarctor.dismissProgress();
        }
        PreferenceManager.Companion companion = PreferenceManager.Companion;
        Application application2 = getApplication();
        Intrinsics.checkNotNull(application2);
        List<GetBuddyItems> fitnessBuddiesDetailsGoals = companion.getFitnessBuddiesDetailsGoals(application2);
        if (fitnessBuddiesDetailsGoals != null && !fitnessBuddiesDetailsGoals.isEmpty()) {
            z = false;
        }
        if (!z) {
            NotificationsContarctor notificationsContarctor2 = this.d;
            if (notificationsContarctor2 != null) {
                Intrinsics.checkNotNull(notificationsContarctor2);
                Application application3 = getApplication();
                Intrinsics.checkNotNull(application3);
                List<GetBuddyItems> fitnessBuddiesDetailsGoals2 = companion.getFitnessBuddiesDetailsGoals(application3);
                Intrinsics.checkNotNull(fitnessBuddiesDetailsGoals2);
                notificationsContarctor2.showBuddyContents(fitnessBuddiesDetailsGoals2);
                return;
            }
            return;
        }
        NotificationsContarctor notificationsContarctor3 = this.d;
        if (notificationsContarctor3 != null) {
            Intrinsics.checkNotNull(notificationsContarctor3);
            notificationsContarctor3.showEmptyLayout();
        }
    }

    public final void loadBuddiesGoalInformation() {
        CoveUtils coveUtils = CoveUtils.INSTANCE;
        Application application = getApplication();
        Intrinsics.checkNotNull(application);
        if (coveUtils.isNetConnected(application)) {
            CoveSocial.getFitnessBuddiesGoals(new CoveApiListener<GetFitnessBuddiesGoalsResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnessbuddies.fragments.viewmodels.NotificationsViewModel$loadBuddiesGoalInformation$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    if (NotificationsViewModel.this.getNotificationsContarctor() != null) {
                        NotificationsContarctor notificationsContarctor = NotificationsViewModel.this.getNotificationsContarctor();
                        Intrinsics.checkNotNull(notificationsContarctor);
                        notificationsContarctor.dismissProgress();
                    }
                    Intrinsics.checkNotNull(coveApiErrorModel);
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                    StringsKt__StringsKt.contains$default((CharSequence) msg, (CharSequence) "Unable to resolve host", false, 2, (Object) null);
                    if (NotificationsViewModel.this.getNotificationsContarctor() != null) {
                        NotificationsContarctor notificationsContarctor2 = NotificationsViewModel.this.getNotificationsContarctor();
                        Intrinsics.checkNotNull(notificationsContarctor2);
                        notificationsContarctor2.showEmptyLayout();
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetFitnessBuddiesGoalsResponse getFitnessBuddiesGoalsResponse) {
                    if (NotificationsViewModel.this.getNotificationsContarctor() != null) {
                        NotificationsContarctor notificationsContarctor = NotificationsViewModel.this.getNotificationsContarctor();
                        Intrinsics.checkNotNull(notificationsContarctor);
                        notificationsContarctor.dismissProgress();
                    }
                    Intrinsics.checkNotNull(getFitnessBuddiesGoalsResponse);
                    if (getFitnessBuddiesGoalsResponse.buddiesGoals.size() > 0) {
                        PreferenceManager.Companion companion = PreferenceManager.Companion;
                        Application application2 = NotificationsViewModel.this.getApplication();
                        Intrinsics.checkNotNull(application2);
                        List<BuddiesGoal> list = getFitnessBuddiesGoalsResponse.buddiesGoals;
                        Intrinsics.checkNotNullExpressionValue(list, "p0!!.buddiesGoals");
                        companion.saveBuddiesGoals(application2, list);
                        if (NotificationsViewModel.this.getNotificationsContarctor() != null) {
                            NotificationsContarctor notificationsContarctor2 = NotificationsViewModel.this.getNotificationsContarctor();
                            Intrinsics.checkNotNull(notificationsContarctor2);
                            List<BuddiesGoal> list2 = getFitnessBuddiesGoalsResponse.buddiesGoals;
                            Intrinsics.checkNotNullExpressionValue(list2, "p0!!.buddiesGoals");
                            notificationsContarctor2.showContents(list2);
                        }
                    } else if (NotificationsViewModel.this.getNotificationsContarctor() != null) {
                        NotificationsContarctor notificationsContarctor3 = NotificationsViewModel.this.getNotificationsContarctor();
                        Intrinsics.checkNotNull(notificationsContarctor3);
                        notificationsContarctor3.showEmptyLayout();
                    }
                }
            });
            return;
        }
        NotificationsContarctor notificationsContarctor = this.d;
        if (notificationsContarctor != null) {
            Intrinsics.checkNotNull(notificationsContarctor);
            notificationsContarctor.dismissProgress();
        }
        PreferenceManager.Companion companion = PreferenceManager.Companion;
        Application application2 = getApplication();
        Intrinsics.checkNotNull(application2);
        if (companion.getFitnessBuddiesGoals(application2) != null) {
            Application application3 = getApplication();
            Intrinsics.checkNotNull(application3);
            List<BuddiesGoal> fitnessBuddiesGoals = companion.getFitnessBuddiesGoals(application3);
            Intrinsics.checkNotNull(fitnessBuddiesGoals);
            if (!fitnessBuddiesGoals.isEmpty()) {
                NotificationsContarctor notificationsContarctor2 = this.d;
                if (notificationsContarctor2 != null) {
                    Intrinsics.checkNotNull(notificationsContarctor2);
                    Application application4 = getApplication();
                    Intrinsics.checkNotNull(application4);
                    List<BuddiesGoal> fitnessBuddiesGoals2 = companion.getFitnessBuddiesGoals(application4);
                    Intrinsics.checkNotNull(fitnessBuddiesGoals2);
                    notificationsContarctor2.showContents(fitnessBuddiesGoals2);
                    return;
                }
                return;
            }
        }
        NotificationsContarctor notificationsContarctor3 = this.d;
        if (notificationsContarctor3 != null) {
            Intrinsics.checkNotNull(notificationsContarctor3);
            notificationsContarctor3.showEmptyLayout();
        }
    }
}
