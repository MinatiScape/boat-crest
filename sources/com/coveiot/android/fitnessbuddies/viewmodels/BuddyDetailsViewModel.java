package com.coveiot.android.fitnessbuddies.viewmodels;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.constants.FitnessBuddiesLabels;
import com.coveiot.android.fitnessbuddies.fragments.contarctors.LabelBuddiesContractor;
import com.coveiot.android.fitnessbuddies.models.BuddyReminderModel;
import com.coveiot.android.fitnessbuddies.models.FitnessCheerEvent;
import com.coveiot.android.fitnessbuddies.utils.CoveUtils;
import com.coveiot.android.fitnessbuddies.utils.PreferenceManager;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveSocial;
import com.coveiot.coveaccess.fitnessbuddies.ReactionType;
import com.coveiot.coveaccess.fitnessbuddies.model.HandleBuddyRequest;
import com.coveiot.coveaccess.fitnessbuddies.model.SendReactionRequest;
import com.coveiot.coveaccess.fitnessbuddies.model.UnfriendBuddyResponse;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.BuddyDetails;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyDetailsModel;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyItems;
import com.coveiot.coveaccess.leaderboard.CoveLeaderboardApi;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.coveaccess.leaderboard.model.MyRankModel;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class BuddyDetailsViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4477a;
    @NotNull
    public MutableLiveData<GetBuddyItems> b;
    @NotNull
    public MutableLiveData<Integer> c;
    @NotNull
    public MutableLiveData<MyRankModel.DataBean.RankBean> d;
    public LabelBuddiesContractor mLabelBuddiesContractor;

    public BuddyDetailsViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4477a = context;
        this.b = new MutableLiveData<>();
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
    }

    public final Boolean a(List<BuddyReminderModel> list, GetBuddyItems getBuddyItems) {
        boolean z;
        Iterator<BuddyReminderModel> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            } else if (it.next().getBuddyUserId() == getBuddyItems.getBuddyDetails().userId.intValue()) {
                z = true;
                break;
            }
        }
        return Boolean.valueOf(z);
    }

    public final void b(GetBuddyItems getBuddyItems) {
        PreferenceManager.Companion companion = PreferenceManager.Companion;
        List<BuddyReminderModel> fitnessBuddiesReminder = companion.getFitnessBuddiesReminder(this.f4477a);
        List<BuddyReminderModel> fitnessBuddiesReminder2 = companion.getFitnessBuddiesReminder(this.f4477a);
        if (!AppUtils.isEmpty(fitnessBuddiesReminder2)) {
            Intrinsics.checkNotNull(fitnessBuddiesReminder);
            if (Intrinsics.areEqual(a(fitnessBuddiesReminder, getBuddyItems), Boolean.TRUE)) {
                Intrinsics.checkNotNull(fitnessBuddiesReminder2);
                for (BuddyReminderModel buddyReminderModel : fitnessBuddiesReminder2) {
                    int buddyUserId = buddyReminderModel.getBuddyUserId();
                    Integer num = getBuddyItems.getBuddyDetails().userId;
                    if (num != null && buddyUserId == num.intValue()) {
                        buddyReminderModel.setHasRemindedBuddy(true);
                        Calendar calendar = Calendar.getInstance();
                        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
                        buddyReminderModel.setRemindDate(calendar);
                        List<BuddyReminderModel> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) fitnessBuddiesReminder);
                        mutableList.set(mutableList.indexOf(buddyReminderModel), buddyReminderModel);
                        PreferenceManager.Companion.saveBuddiesReminderModel(this.f4477a, mutableList);
                        return;
                    }
                }
                return;
            }
            List<BuddyReminderModel> mutableList2 = CollectionsKt___CollectionsKt.toMutableList((Collection) fitnessBuddiesReminder);
            BuddyDetails buddyDetails = getBuddyItems.getBuddyDetails();
            Integer num2 = buddyDetails != null ? buddyDetails.userId : null;
            Intrinsics.checkNotNull(num2);
            int intValue = num2.intValue();
            Calendar calendar2 = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
            mutableList2.add(new BuddyReminderModel(intValue, true, calendar2));
            companion.saveBuddiesReminderModel(this.f4477a, mutableList2);
            return;
        }
        ArrayList arrayList = new ArrayList();
        Integer num3 = getBuddyItems.getBuddyDetails().userId;
        Intrinsics.checkNotNullExpressionValue(num3, "buddyItems.buddyDetails.userId");
        int intValue2 = num3.intValue();
        Calendar calendar3 = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar3, "getInstance()");
        arrayList.add(new BuddyReminderModel(intValue2, true, calendar3));
        companion.saveBuddiesReminderModel(this.f4477a, arrayList);
    }

    @NotNull
    public final MutableLiveData<GetBuddyItems> getBuddyDetails() {
        return this.b;
    }

    @NotNull
    public final Context getContext() {
        return this.f4477a;
    }

    @NotNull
    public final MutableLiveData<MyRankModel.DataBean.RankBean> getGetBestRankBean() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<Integer> getGetMyBadgesCount() {
        return this.c;
    }

    @NotNull
    public final LabelBuddiesContractor getMLabelBuddiesContractor() {
        LabelBuddiesContractor labelBuddiesContractor = this.mLabelBuddiesContractor;
        if (labelBuddiesContractor != null) {
            return labelBuddiesContractor;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mLabelBuddiesContractor");
        return null;
    }

    public final void getMyBadges() {
        if (AppUtils.isNetConnected(this.f4477a)) {
            CoveLeaderboardApi.getMyBadges(new CoveApiListener<MyBadgesModel, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnessbuddies.viewmodels.BuddyDetailsViewModel$getMyBadges$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    BuddyDetailsViewModel.this.getGetMyBadgesCount().postValue(0);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable MyBadgesModel myBadgesModel) {
                    if (myBadgesModel != null) {
                        MyBadgesModel.DataBean data = myBadgesModel.getData();
                        List<MyBadgesModel.DataBean.BadgesBean> badges = data != null ? data.getBadges() : null;
                        if (!(badges == null || badges.isEmpty())) {
                            BuddyDetailsViewModel.this.getGetMyBadgesCount().postValue(Integer.valueOf(myBadgesModel.getData().getBadges().size()));
                            return;
                        }
                    }
                    BuddyDetailsViewModel.this.getGetMyBadgesCount().postValue(0);
                }
            });
            return;
        }
        this.c.postValue(0);
        Context context = this.f4477a;
        Toast.makeText(context, context.getResources().getString(R.string.please_check_network), 0).show();
    }

    public final void getMyRankDetails() {
        if (AppUtils.isNetConnected(this.f4477a)) {
            CoveLeaderboardApi.getMyRank(GeoCodingCriteria.POD_CITY, new CoveApiListener<MyRankModel, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnessbuddies.viewmodels.BuddyDetailsViewModel$getMyRankDetails$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    BuddyDetailsViewModel.this.getGetBestRankBean().postValue(null);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable MyRankModel myRankModel) {
                    if (BuddyDetailsViewModel.this.getContext() != null && myRankModel != null && myRankModel.getData() != null && myRankModel.getData().getRank() != null) {
                        BuddyDetailsViewModel.this.getGetBestRankBean().postValue(myRankModel.getData().getRank());
                    } else {
                        BuddyDetailsViewModel.this.getGetBestRankBean().postValue(null);
                    }
                }
            });
            return;
        }
        this.d.postValue(null);
        Context context = this.f4477a;
        Toast.makeText(context, context.getResources().getString(R.string.please_check_network), 0).show();
    }

    public final void loadCoveBuddies(@NotNull String buddyId) {
        Intrinsics.checkNotNullParameter(buddyId, "buddyId");
        if (CoveUtils.INSTANCE.isNetConnected(this.f4477a)) {
            CoveSocial.getBuddiesDetails(buddyId, "totalEarnedBadges,globalRank", new CoveApiListener<GetBuddyDetailsModel, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnessbuddies.viewmodels.BuddyDetailsViewModel$loadCoveBuddies$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    LabelBuddiesContractor mLabelBuddiesContractor = BuddyDetailsViewModel.this.getMLabelBuddiesContractor();
                    String msg = coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null;
                    Intrinsics.checkNotNull(msg);
                    mLabelBuddiesContractor.onError(msg);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetBuddyDetailsModel getBuddyDetailsModel) {
                    if (getBuddyDetailsModel == null || getBuddyDetailsModel.getData() == null) {
                        return;
                    }
                    BuddyDetailsViewModel.this.getBuddyDetails().postValue(getBuddyDetailsModel.getData());
                }
            });
            return;
        }
        LabelBuddiesContractor mLabelBuddiesContractor = getMLabelBuddiesContractor();
        String string = this.f4477a.getString(R.string.no_internet_connection);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.no_internet_connection)");
        mLabelBuddiesContractor.onError(string);
    }

    public final void removeBuddyReminderModel(@NotNull GetBuddyItems buddyItems) {
        Intrinsics.checkNotNullParameter(buddyItems, "buddyItems");
        PreferenceManager.Companion companion = PreferenceManager.Companion;
        List<BuddyReminderModel> fitnessBuddiesReminder = companion.getFitnessBuddiesReminder(this.f4477a);
        List<BuddyReminderModel> fitnessBuddiesReminder2 = companion.getFitnessBuddiesReminder(this.f4477a);
        if (AppUtils.isEmpty(fitnessBuddiesReminder2)) {
            return;
        }
        Intrinsics.checkNotNull(fitnessBuddiesReminder2);
        for (BuddyReminderModel buddyReminderModel : fitnessBuddiesReminder2) {
            int buddyUserId = buddyReminderModel.getBuddyUserId();
            BuddyDetails buddyDetails = buddyItems.getBuddyDetails();
            Integer num = buddyDetails != null ? buddyDetails.userId : null;
            Intrinsics.checkNotNull(num);
            if (buddyUserId == num.intValue()) {
                Intrinsics.checkNotNull(fitnessBuddiesReminder);
                List<BuddyReminderModel> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) fitnessBuddiesReminder);
                mutableList.remove(mutableList.indexOf(buddyReminderModel));
                PreferenceManager.Companion.saveBuddiesReminderModel(this.f4477a, mutableList);
                return;
            }
        }
    }

    public final void sendBuddyReaction(int i, @NotNull final FitnessBuddiesLabels fitnessBuddiesLabels, @NotNull String fitnessBuddiesDesc, @NotNull final GetBuddyItems buddyItems) {
        ReactionType reactionType;
        Intrinsics.checkNotNullParameter(fitnessBuddiesLabels, "fitnessBuddiesLabels");
        Intrinsics.checkNotNullParameter(fitnessBuddiesDesc, "fitnessBuddiesDesc");
        Intrinsics.checkNotNullParameter(buddyItems, "buddyItems");
        if (fitnessBuddiesLabels == FitnessBuddiesLabels.APPLAUD) {
            reactionType = ReactionType.APPLAUD;
        } else if (fitnessBuddiesLabels == FitnessBuddiesLabels.CHEER) {
            reactionType = ReactionType.CHEER;
        } else {
            reactionType = ReactionType.NUDGE;
        }
        CoveSocial.sendReaction(Integer.valueOf(i), new SendReactionRequest(reactionType, fitnessBuddiesDesc), new CoveApiListener<HandleBuddyRequest, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnessbuddies.viewmodels.BuddyDetailsViewModel$sendBuddyReaction$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                if (!CoveUtils.INSTANCE.isNetConnected(BuddyDetailsViewModel.this.getContext())) {
                    LabelBuddiesContractor mLabelBuddiesContractor = BuddyDetailsViewModel.this.getMLabelBuddiesContractor();
                    String string = BuddyDetailsViewModel.this.getContext().getString(R.string.please_check_your_internet);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…ease_check_your_internet)");
                    mLabelBuddiesContractor.onError(string);
                    return;
                }
                LabelBuddiesContractor mLabelBuddiesContractor2 = BuddyDetailsViewModel.this.getMLabelBuddiesContractor();
                String string2 = BuddyDetailsViewModel.this.getContext().getString(R.string.error_sending_message);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.error_sending_message)");
                mLabelBuddiesContractor2.onError(string2);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable HandleBuddyRequest handleBuddyRequest) {
                BuddyDetailsViewModel.this.b(buddyItems);
                FitnessBuddiesLabels fitnessBuddiesLabels2 = fitnessBuddiesLabels;
                Intrinsics.checkNotNull(fitnessBuddiesLabels2);
                if (fitnessBuddiesLabels2 == FitnessBuddiesLabels.CHEER) {
                    LabelBuddiesContractor mLabelBuddiesContractor = BuddyDetailsViewModel.this.getMLabelBuddiesContractor();
                    mLabelBuddiesContractor.showLabelSuccessMessage("Great!, " + BuddyDetailsViewModel.this.getContext().getString(R.string.you_reminded_buddy));
                } else {
                    FitnessBuddiesLabels fitnessBuddiesLabels3 = fitnessBuddiesLabels;
                    Intrinsics.checkNotNull(fitnessBuddiesLabels3);
                    if (fitnessBuddiesLabels3 == FitnessBuddiesLabels.APPLAUD) {
                        LabelBuddiesContractor mLabelBuddiesContractor2 = BuddyDetailsViewModel.this.getMLabelBuddiesContractor();
                        mLabelBuddiesContractor2.showLabelSuccessMessage("Great!, " + BuddyDetailsViewModel.this.getContext().getString(R.string.you_applauded_buddy));
                    } else {
                        LabelBuddiesContractor mLabelBuddiesContractor3 = BuddyDetailsViewModel.this.getMLabelBuddiesContractor();
                        mLabelBuddiesContractor3.showLabelSuccessMessage("Great!, " + BuddyDetailsViewModel.this.getContext().getString(R.string.you_reminded_buddy));
                    }
                }
                CoveEventBusManager.getInstance().getEventBus().post(new FitnessCheerEvent());
            }
        });
    }

    public final void setBuddyDetails(@NotNull MutableLiveData<GetBuddyItems> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }

    public final void setGetBestRankBean(@NotNull MutableLiveData<MyRankModel.DataBean.RankBean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }

    public final void setGetMyBadgesCount(@NotNull MutableLiveData<Integer> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void setMLabelBuddiesContractor(@NotNull LabelBuddiesContractor labelBuddiesContractor) {
        Intrinsics.checkNotNullParameter(labelBuddiesContractor, "<set-?>");
        this.mLabelBuddiesContractor = labelBuddiesContractor;
    }

    public final void unFriendBuddy(int i) {
        CoveSocial.unfriendBuddy(Integer.valueOf(i), new CoveApiListener<UnfriendBuddyResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnessbuddies.viewmodels.BuddyDetailsViewModel$unFriendBuddy$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                if (!CoveUtils.INSTANCE.isNetConnected(BuddyDetailsViewModel.this.getContext())) {
                    LabelBuddiesContractor mLabelBuddiesContractor = BuddyDetailsViewModel.this.getMLabelBuddiesContractor();
                    String string = BuddyDetailsViewModel.this.getContext().getString(R.string.please_check_your_internet);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…ease_check_your_internet)");
                    mLabelBuddiesContractor.onError(string);
                    return;
                }
                LabelBuddiesContractor mLabelBuddiesContractor2 = BuddyDetailsViewModel.this.getMLabelBuddiesContractor();
                String string2 = BuddyDetailsViewModel.this.getContext().getString(R.string.something_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.something_went_wrong)");
                mLabelBuddiesContractor2.onError(string2);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable UnfriendBuddyResponse unfriendBuddyResponse) {
                BuddyDetailsViewModel.this.getMLabelBuddiesContractor().removeBuddySuccess();
            }
        });
    }
}
