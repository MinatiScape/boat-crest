package com.coveiot.android.fitnesschallenges.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.android.fitnesschallenges.databinding.ListItemFitnessChallengeLeaderboardLayoutBinding;
import com.coveiot.android.fitnesschallenges.utils.ExtensionsKt;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.fitnesschallenge.model.GetAllParticipantsFitnessChallengeRes;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FitnessChallengeLeaderboardRankingPagingAdapter extends PagingDataAdapter<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails, FitnessViewHolder> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final FitnessChallengeLeaderboardRankingPagingAdapter$Companion$COMPARATOR$1 i = new DiffUtil.ItemCallback<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails>() { // from class: com.coveiot.android.fitnesschallenges.adpter.FitnessChallengeLeaderboardRankingPagingAdapter$Companion$COMPARATOR$1
        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        @SuppressLint({"DiffUtilEquals"})
        public boolean areContentsTheSame(@NotNull GetAllParticipantsFitnessChallengeRes.ParticipantsDetails oldItem, @NotNull GetAllParticipantsFitnessChallengeRes.ParticipantsDetails newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem, newItem);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(@NotNull GetAllParticipantsFitnessChallengeRes.ParticipantsDetails oldItem, @NotNull GetAllParticipantsFitnessChallengeRes.ParticipantsDetails newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem.getName(), newItem.getName());
        }
    };
    @NotNull
    public final Context e;
    @NotNull
    public final ChallengeClickListener f;
    @NotNull
    public final String g;
    @NotNull
    public final ArrayList<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails> h;

    /* loaded from: classes2.dex */
    public interface ChallengeClickListener {
        void getTopParticipants(@NotNull ArrayList<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails> arrayList);

        void isDataLoaded(boolean z);
    }

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes2.dex */
    public final class FitnessViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListItemFitnessChallengeLeaderboardLayoutBinding f4497a;
        public final /* synthetic */ FitnessChallengeLeaderboardRankingPagingAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FitnessViewHolder(@NotNull FitnessChallengeLeaderboardRankingPagingAdapter fitnessChallengeLeaderboardRankingPagingAdapter, ListItemFitnessChallengeLeaderboardLayoutBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = fitnessChallengeLeaderboardRankingPagingAdapter;
            this.f4497a = binding;
        }

        public final void bind(@NotNull GetAllParticipantsFitnessChallengeRes.ParticipantsDetails participant) {
            int intValue;
            Intrinsics.checkNotNullParameter(participant, "participant");
            ListItemFitnessChallengeLeaderboardLayoutBinding listItemFitnessChallengeLeaderboardLayoutBinding = this.f4497a;
            FitnessChallengeLeaderboardRankingPagingAdapter fitnessChallengeLeaderboardRankingPagingAdapter = this.b;
            listItemFitnessChallengeLeaderboardLayoutBinding.setParticipantData(participant);
            if (participant.getRank() != null) {
                listItemFitnessChallengeLeaderboardLayoutBinding.tvUserRank.setText(String.valueOf(participant.getRank()));
            }
            String targetAchievedDate = participant.getTargetAchievedDate();
            if (!(targetAchievedDate == null || targetAchievedDate.length() == 0)) {
                TextView textView = listItemFitnessChallengeLeaderboardLayoutBinding.tvAchievedDate;
                StringBuilder sb = new StringBuilder();
                sb.append(fitnessChallengeLeaderboardRankingPagingAdapter.getContext().getString(R.string.goal_achieved_on));
                sb.append(' ');
                ThemesUtils themesUtils = ThemesUtils.INSTANCE;
                String targetAchievedDate2 = participant.getTargetAchievedDate();
                Intrinsics.checkNotNullExpressionValue(targetAchievedDate2, "participant.targetAchievedDate");
                sb.append(themesUtils.formatDateWithSuffix(targetAchievedDate2));
                textView.setText(sb.toString());
            } else {
                listItemFitnessChallengeLeaderboardLayoutBinding.tvAchievedDate.setText("");
            }
            if (Intrinsics.areEqual(fitnessChallengeLeaderboardRankingPagingAdapter.getUnit(), FitnessChallengeConstants.METERS)) {
                TextView textView2 = listItemFitnessChallengeLeaderboardLayoutBinding.tvUserCalories;
                Number targetAchieved = participant.getTargetAchieved();
                if (targetAchieved == null) {
                    targetAchieved = Double.valueOf(0.0d);
                }
                textView2.setText(String.valueOf(ExtensionsKt.formatToTwoDecimalPlace((float) (targetAchieved.doubleValue() / 1000.0d))));
            } else {
                TextView textView3 = listItemFitnessChallengeLeaderboardLayoutBinding.tvUserCalories;
                Integer targetAchieved2 = participant.getTargetAchieved();
                if (targetAchieved2 == null) {
                    intValue = 0;
                } else {
                    Intrinsics.checkNotNullExpressionValue(targetAchieved2, "participant.targetAchieved?:0");
                    intValue = targetAchieved2.intValue();
                }
                textView3.setText(String.valueOf(intValue));
            }
            ImageView tvUserProfilePic = listItemFitnessChallengeLeaderboardLayoutBinding.tvUserProfilePic;
            Intrinsics.checkNotNullExpressionValue(tvUserProfilePic, "tvUserProfilePic");
            fitnessChallengeLeaderboardRankingPagingAdapter.setImage(tvUserProfilePic, participant.getDpUrl());
            if (getAbsoluteAdapterPosition() == 0 || getAbsoluteAdapterPosition() == 1 || getAbsoluteAdapterPosition() == 2) {
                if (!fitnessChallengeLeaderboardRankingPagingAdapter.getTopParticipantsList().contains(participant)) {
                    fitnessChallengeLeaderboardRankingPagingAdapter.getTopParticipantsList().add(participant);
                }
                fitnessChallengeLeaderboardRankingPagingAdapter.f.getTopParticipants(fitnessChallengeLeaderboardRankingPagingAdapter.getTopParticipantsList());
            }
            if (fitnessChallengeLeaderboardRankingPagingAdapter.getTopParticipantsList().contains(participant)) {
                listItemFitnessChallengeLeaderboardLayoutBinding.getRoot().setVisibility(8);
                listItemFitnessChallengeLeaderboardLayoutBinding.getRoot().setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                return;
            }
            listItemFitnessChallengeLeaderboardLayoutBinding.getRoot().setVisibility(0);
            listItemFitnessChallengeLeaderboardLayoutBinding.getRoot().setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FitnessChallengeLeaderboardRankingPagingAdapter(@NotNull Context context, @NotNull ChallengeClickListener listener, @NotNull String unit) {
        super(i, null, null, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.e = context;
        this.f = listener;
        this.g = unit;
        this.h = new ArrayList<>();
    }

    @NotNull
    public final Context getContext() {
        return this.e;
    }

    @NotNull
    public final ArrayList<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails> getTopParticipantsList() {
        return this.h;
    }

    @NotNull
    public final String getUnit() {
        return this.g;
    }

    public final void setImage(@NotNull ImageView imageView, @Nullable String str) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        int i2 = R.drawable.default_avatar;
        Glide.with(imageView).m30load(str).override(300, 300).error(i2).placeholder(i2).diskCacheStrategy(DiskCacheStrategy.ALL).circleCrop().into(imageView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull FitnessViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        GetAllParticipantsFitnessChallengeRes.ParticipantsDetails item = getItem(i2);
        this.f.isDataLoaded(item != null);
        if (item != null) {
            holder.bind(item);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public FitnessViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ListItemFitnessChallengeLeaderboardLayoutBinding inflate = ListItemFitnessChallengeLeaderboardLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new FitnessViewHolder(this, inflate);
    }
}
