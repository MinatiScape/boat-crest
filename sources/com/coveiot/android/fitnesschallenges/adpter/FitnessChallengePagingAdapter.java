package com.coveiot.android.fitnesschallenges.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.android.fitnesschallenges.adpter.FitnessChallengePagingAdapter;
import com.coveiot.android.fitnesschallenges.databinding.FitnessChallengeItemBinding;
import com.coveiot.android.fitnesschallenges.utils.ExtensionsKt;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FitnessChallengePagingAdapter extends PagingDataAdapter<BuddiesChallengeRes.Item, FitnessViewHolder> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final FitnessChallengePagingAdapter$Companion$COMPARATOR$1 h = new DiffUtil.ItemCallback<BuddiesChallengeRes.Item>() { // from class: com.coveiot.android.fitnesschallenges.adpter.FitnessChallengePagingAdapter$Companion$COMPARATOR$1
        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        @SuppressLint({"DiffUtilEquals"})
        public boolean areContentsTheSame(@NotNull BuddiesChallengeRes.Item oldItem, @NotNull BuddiesChallengeRes.Item newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem, newItem);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(@NotNull BuddiesChallengeRes.Item oldItem, @NotNull BuddiesChallengeRes.Item newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem.getChallengeId(), newItem.getChallengeId());
        }
    };
    @NotNull
    public final Context e;
    public boolean f;
    @NotNull
    public final ChallengeClickListener g;

    /* loaded from: classes2.dex */
    public interface ChallengeClickListener {
        void challengeClick(@NotNull BuddiesChallengeRes.Item item, boolean z);

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
        public final FitnessChallengeItemBinding f4498a;
        public final /* synthetic */ FitnessChallengePagingAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FitnessViewHolder(@NotNull FitnessChallengePagingAdapter fitnessChallengePagingAdapter, FitnessChallengeItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = fitnessChallengePagingAdapter;
            this.f4498a = binding;
        }

        public static final void c(FitnessChallengePagingAdapter this$0, BuddiesChallengeRes.Item challenge, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(challenge, "$challenge");
            this$0.g.challengeClick(challenge, false);
        }

        public static final void d(FitnessChallengePagingAdapter this$0, BuddiesChallengeRes.Item challenge, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(challenge, "$challenge");
            this$0.g.challengeClick(challenge, true);
        }

        public final void bind(@NotNull final BuddiesChallengeRes.Item challenge) {
            String valueOf;
            String titlecase;
            Intrinsics.checkNotNullParameter(challenge, "challenge");
            FitnessChallengeItemBinding fitnessChallengeItemBinding = this.f4498a;
            final FitnessChallengePagingAdapter fitnessChallengePagingAdapter = this.b;
            fitnessChallengeItemBinding.setChallengeItem(challenge);
            fitnessChallengeItemBinding.setChallengeJoined(Boolean.valueOf(fitnessChallengePagingAdapter.isChallengeJoined()));
            if (fitnessChallengePagingAdapter.isChallengeJoined()) {
                Number targetAchieved = challenge.getTargetAchieved();
                if (targetAchieved == null) {
                    targetAchieved = Double.valueOf(0.0d);
                }
                double doubleValue = targetAchieved.doubleValue();
                Number target = challenge.getTarget();
                if (target == null) {
                    target = Double.valueOf(1.0d);
                }
                int roundToInt = kotlin.math.c.roundToInt((doubleValue / target.doubleValue()) * 100);
                int i = roundToInt < 100 ? roundToInt : 100;
                TextView textView = fitnessChallengeItemBinding.tvChallengeProgress;
                textView.setText(i + fitnessChallengePagingAdapter.getContext().getString(R.string.goal_completed));
                fitnessChallengeItemBinding.challengeProgress.setProgress(i);
            }
            ImageView ivChallengeBg = fitnessChallengeItemBinding.ivChallengeBg;
            Intrinsics.checkNotNullExpressionValue(ivChallengeBg, "ivChallengeBg");
            fitnessChallengePagingAdapter.setImage(ivChallengeBg, challenge.getBannerImageUrl(), true);
            String tag = challenge.getTag();
            if (!(tag == null || tag.length() == 0)) {
                fitnessChallengeItemBinding.tvNewChallenge.setVisibility(0);
                TextView textView2 = fitnessChallengeItemBinding.tvNewChallenge;
                String tag2 = challenge.getTag();
                Intrinsics.checkNotNullExpressionValue(tag2, "challenge.tag");
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
                String lowerCase = tag2.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                if (lowerCase.length() > 0) {
                    StringBuilder sb = new StringBuilder();
                    char charAt = lowerCase.charAt(0);
                    if (Character.isLowerCase(charAt) || Character.isUpperCase(charAt)) {
                        Locale locale2 = Locale.getDefault();
                        Intrinsics.checkNotNullExpressionValue(locale2, "getDefault()");
                        titlecase = kotlin.text.a.titlecase(charAt, locale2);
                    } else {
                        titlecase = String.valueOf(charAt);
                    }
                    sb.append((Object) titlecase);
                    String substring = lowerCase.substring(1);
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                    sb.append(substring);
                    lowerCase = sb.toString();
                }
                textView2.setText(lowerCase);
            } else {
                fitnessChallengeItemBinding.tvNewChallenge.setVisibility(8);
            }
            List<BuddiesChallengeRes.Item.TopParticipant> topParticipants = challenge.getTopParticipants();
            if (topParticipants != null) {
                fitnessChallengeItemBinding.clTopParticipant.setVisibility(0);
                if (topParticipants.size() > 0) {
                    fitnessChallengeItemBinding.ivTopParticipant1.setVisibility(0);
                    fitnessChallengeItemBinding.ivTopParticipant2.setVisibility(8);
                    fitnessChallengeItemBinding.ivTopParticipant3.setVisibility(8);
                    fitnessChallengeItemBinding.tvTotalParticipants.setVisibility(0);
                    fitnessChallengeItemBinding.tvNoParticipants.setVisibility(8);
                    ImageView ivTopParticipant1 = fitnessChallengeItemBinding.ivTopParticipant1;
                    Intrinsics.checkNotNullExpressionValue(ivTopParticipant1, "ivTopParticipant1");
                    fitnessChallengePagingAdapter.setImage(ivTopParticipant1, challenge.getTopParticipants().get(0).getDpUrl(), false);
                    fitnessChallengeItemBinding.setTotalParticipantsCount(fitnessChallengePagingAdapter.getContext().getString(R.string.participant));
                    if (topParticipants.size() > 1) {
                        fitnessChallengeItemBinding.ivTopParticipant2.setVisibility(0);
                        fitnessChallengeItemBinding.ivTopParticipant3.setVisibility(8);
                        ImageView ivTopParticipant2 = fitnessChallengeItemBinding.ivTopParticipant2;
                        Intrinsics.checkNotNullExpressionValue(ivTopParticipant2, "ivTopParticipant2");
                        fitnessChallengePagingAdapter.setImage(ivTopParticipant2, challenge.getTopParticipants().get(1).getDpUrl(), false);
                        Context context = fitnessChallengePagingAdapter.getContext();
                        int i2 = R.string.participants;
                        fitnessChallengeItemBinding.setTotalParticipantsCount(context.getString(i2));
                        if (topParticipants.size() > 2) {
                            fitnessChallengeItemBinding.ivTopParticipant3.setVisibility(0);
                            ImageView ivTopParticipant3 = fitnessChallengeItemBinding.ivTopParticipant3;
                            Intrinsics.checkNotNullExpressionValue(ivTopParticipant3, "ivTopParticipant3");
                            fitnessChallengePagingAdapter.setImage(ivTopParticipant3, challenge.getTopParticipants().get(2).getDpUrl(), false);
                            fitnessChallengeItemBinding.setTotalParticipantsCount(fitnessChallengePagingAdapter.getContext().getString(i2));
                        }
                        if (topParticipants.size() > 3) {
                            if (challenge.getTotalParticipants() != null) {
                                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                                Locale locale3 = Locale.ENGLISH;
                                String string = fitnessChallengePagingAdapter.getContext().getString(R.string.plus_value_participants);
                                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri….plus_value_participants)");
                                valueOf = String.format(locale3, string, Arrays.copyOf(new Object[]{String.valueOf(challenge.getTotalParticipants().intValue() - 3)}, 1));
                                Intrinsics.checkNotNullExpressionValue(valueOf, "format(locale, format, *args)");
                            } else {
                                valueOf = String.valueOf(fitnessChallengePagingAdapter.getContext().getString(R.string.lead_the_way_be_the_first_to_join));
                            }
                            fitnessChallengeItemBinding.setTotalParticipantsCount(valueOf);
                        }
                    }
                }
            } else {
                fitnessChallengeItemBinding.clTopParticipant.setVisibility(8);
                fitnessChallengeItemBinding.ivTopParticipant1.setVisibility(8);
                fitnessChallengeItemBinding.ivTopParticipant2.setVisibility(8);
                fitnessChallengeItemBinding.ivTopParticipant3.setVisibility(8);
                fitnessChallengeItemBinding.tvTotalParticipants.setVisibility(8);
                fitnessChallengeItemBinding.tvNoParticipants.setVisibility(0);
                fitnessChallengeItemBinding.tvNoParticipants.setText(fitnessChallengePagingAdapter.getContext().getString(R.string.lead_the_way_be_the_first_to_join));
            }
            if (challenge.getStatus() != null && challenge.getStatus().equals(FitnessChallengeConstants.LEFT) && challenge.getLeftDate() != null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(fitnessChallengePagingAdapter.getContext().getString(R.string.challenge_left_on));
                sb2.append(' ');
                ThemesUtils themesUtils = ThemesUtils.INSTANCE;
                String leftDate = challenge.getLeftDate();
                Intrinsics.checkNotNullExpressionValue(leftDate, "challenge.leftDate");
                sb2.append(themesUtils.formatDateWithSuffix(leftDate));
                fitnessChallengeItemBinding.setDaysLeft(sb2.toString());
            } else {
                Context context2 = fitnessChallengePagingAdapter.getContext();
                String startDate = challenge.getStartDate();
                Intrinsics.checkNotNullExpressionValue(startDate, "challenge.startDate");
                String endDate = challenge.getEndDate();
                Intrinsics.checkNotNullExpressionValue(endDate, "challenge.endDate");
                fitnessChallengeItemBinding.setDaysLeft(ExtensionsKt.getDaysLeftCalculation(context2, startDate, endDate));
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.adpter.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengePagingAdapter.FitnessViewHolder.c(FitnessChallengePagingAdapter.this, challenge, view);
                }
            });
            fitnessChallengeItemBinding.btnJoin.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.adpter.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengePagingAdapter.FitnessViewHolder.d(FitnessChallengePagingAdapter.this, challenge, view);
                }
            });
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FitnessChallengePagingAdapter(@NotNull Context context, boolean z, @NotNull ChallengeClickListener listener) {
        super(h, null, null, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.e = context;
        this.f = z;
        this.g = listener;
    }

    @NotNull
    public final Context getContext() {
        return this.e;
    }

    public final boolean isChallengeJoined() {
        return this.f;
    }

    public final void setChallengeJoined(boolean z) {
        this.f = z;
    }

    public final void setImage(@NotNull ImageView imageView, @Nullable String str, boolean z) {
        Intrinsics.checkNotNullParameter(imageView, "<this>");
        if (z) {
            RequestBuilder<Drawable> m30load = Glide.with(imageView).m30load(str);
            int i = R.drawable.system_fittness_challenge_banner_bg1;
            m30load.error(i).placeholder(i).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
            return;
        }
        int i2 = R.drawable.default_avatar;
        Glide.with(imageView).m30load(str).override(300, 300).error(i2).placeholder(i2).diskCacheStrategy(DiskCacheStrategy.ALL).circleCrop().into(imageView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull FitnessViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        BuddiesChallengeRes.Item item = getItem(i);
        this.g.isDataLoaded(item != null);
        if (item != null) {
            holder.bind(item);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public FitnessViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        FitnessChallengeItemBinding inflate = FitnessChallengeItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new FitnessViewHolder(this, inflate);
    }
}
