package com.coveiot.android.fitnesschallenges.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.android.fitnesschallenges.adpter.FitnessChallengeAdapter;
import com.coveiot.android.fitnesschallenges.databinding.FitnessChallengeItemBinding;
import com.coveiot.android.fitnesschallenges.utils.ExtensionsKt;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeCleverTapConstants;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FitnessChallengeAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4495a;
    @NotNull
    public final ChallengeClickListener b;
    @NotNull
    public List<BuddiesChallengeRes.Item> c;

    /* loaded from: classes2.dex */
    public interface ChallengeClickListener {
        void challengeClick(@NotNull BuddiesChallengeRes.Item item, boolean z);
    }

    /* loaded from: classes2.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final FitnessChallengeItemBinding f4496a;
        public final /* synthetic */ FitnessChallengeAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull FitnessChallengeAdapter fitnessChallengeAdapter, FitnessChallengeItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = fitnessChallengeAdapter;
            this.f4496a = binding;
        }

        public static final void c(BuddiesChallengeRes.Item challenge, FitnessChallengeAdapter this$0, View view) {
            Intrinsics.checkNotNullParameter(challenge, "$challenge");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_ID.getValue(), challenge.getChallengeId().toString());
            hashMap.put(FitnessChallengeCleverTapConstants.FITNESS_CHALLENGE_CHALLENGE_NAME.getValue(), challenge.getName());
            DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_HP_CHALLENGEBNR_TAPPED.getValue(), hashMap);
            this$0.getListener().challengeClick(challenge, false);
        }

        public static final void d(FitnessChallengeAdapter this$0, BuddiesChallengeRes.Item challenge, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(challenge, "$challenge");
            this$0.getListener().challengeClick(challenge, true);
        }

        @SuppressLint({"SetTextI18n"})
        public final void bind(@NotNull final BuddiesChallengeRes.Item challenge) {
            String valueOf;
            String titlecase;
            Intrinsics.checkNotNullParameter(challenge, "challenge");
            FitnessChallengeItemBinding fitnessChallengeItemBinding = this.f4496a;
            final FitnessChallengeAdapter fitnessChallengeAdapter = this.b;
            fitnessChallengeItemBinding.setChallengeItem(challenge);
            fitnessChallengeItemBinding.setChallengeJoined(challenge.getJoined());
            ImageView ivChallengeBg = fitnessChallengeItemBinding.ivChallengeBg;
            Intrinsics.checkNotNullExpressionValue(ivChallengeBg, "ivChallengeBg");
            fitnessChallengeAdapter.setImage(ivChallengeBg, challenge.getBannerImageUrl(), true);
            String tag = challenge.getTag();
            if (!(tag == null || tag.length() == 0)) {
                fitnessChallengeItemBinding.tvNewChallenge.setVisibility(0);
                TextView textView = fitnessChallengeItemBinding.tvNewChallenge;
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
                textView.setText(lowerCase);
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
                    fitnessChallengeAdapter.setImage(ivTopParticipant1, challenge.getTopParticipants().get(0).getDpUrl(), false);
                    fitnessChallengeItemBinding.setTotalParticipantsCount(fitnessChallengeAdapter.f4495a.getString(R.string.participant));
                    if (topParticipants.size() > 1) {
                        fitnessChallengeItemBinding.ivTopParticipant2.setVisibility(0);
                        fitnessChallengeItemBinding.ivTopParticipant3.setVisibility(8);
                        ImageView ivTopParticipant2 = fitnessChallengeItemBinding.ivTopParticipant2;
                        Intrinsics.checkNotNullExpressionValue(ivTopParticipant2, "ivTopParticipant2");
                        fitnessChallengeAdapter.setImage(ivTopParticipant2, challenge.getTopParticipants().get(1).getDpUrl(), false);
                        Context context = fitnessChallengeAdapter.f4495a;
                        int i = R.string.participants;
                        fitnessChallengeItemBinding.setTotalParticipantsCount(context.getString(i));
                        if (topParticipants.size() > 2) {
                            fitnessChallengeItemBinding.ivTopParticipant3.setVisibility(0);
                            ImageView ivTopParticipant3 = fitnessChallengeItemBinding.ivTopParticipant3;
                            Intrinsics.checkNotNullExpressionValue(ivTopParticipant3, "ivTopParticipant3");
                            fitnessChallengeAdapter.setImage(ivTopParticipant3, challenge.getTopParticipants().get(2).getDpUrl(), false);
                            fitnessChallengeItemBinding.setTotalParticipantsCount(fitnessChallengeAdapter.f4495a.getString(i));
                        }
                        if (topParticipants.size() > 3) {
                            if (challenge.getTotalParticipants() == null) {
                                valueOf = String.valueOf(fitnessChallengeAdapter.f4495a.getString(R.string.lead_the_way_be_the_first_to_join));
                            } else {
                                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                                Locale locale3 = Locale.ENGLISH;
                                String string = fitnessChallengeAdapter.f4495a.getString(R.string.plus_value_participants);
                                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri….plus_value_participants)");
                                valueOf = String.format(locale3, string, Arrays.copyOf(new Object[]{String.valueOf(challenge.getTotalParticipants().intValue() - 3)}, 1));
                                Intrinsics.checkNotNullExpressionValue(valueOf, "format(locale, format, *args)");
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
                fitnessChallengeItemBinding.tvNoParticipants.setText(fitnessChallengeAdapter.f4495a.getString(R.string.lead_the_way_be_the_first_to_join));
            }
            Context context2 = fitnessChallengeAdapter.f4495a;
            String startDate = challenge.getStartDate();
            Intrinsics.checkNotNullExpressionValue(startDate, "challenge.startDate");
            String endDate = challenge.getEndDate();
            Intrinsics.checkNotNullExpressionValue(endDate, "challenge.endDate");
            fitnessChallengeItemBinding.setDaysLeft(ExtensionsKt.getDaysLeftCalculation(context2, startDate, endDate));
            Boolean joined = challenge.getJoined();
            Intrinsics.checkNotNullExpressionValue(joined, "challenge.joined");
            if (joined.booleanValue()) {
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
                int i2 = roundToInt < 100 ? roundToInt : 100;
                TextView textView2 = fitnessChallengeItemBinding.tvChallengeProgress;
                textView2.setText(i2 + fitnessChallengeAdapter.f4495a.getString(R.string.goal_completed));
                fitnessChallengeItemBinding.challengeProgress.setProgress(i2);
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.adpter.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengeAdapter.ViewHolder.c(BuddiesChallengeRes.Item.this, fitnessChallengeAdapter, view);
                }
            });
            fitnessChallengeItemBinding.btnJoin.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnesschallenges.adpter.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessChallengeAdapter.ViewHolder.d(FitnessChallengeAdapter.this, challenge, view);
                }
            });
        }
    }

    public FitnessChallengeAdapter(@NotNull Context context, @NotNull ChallengeClickListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f4495a = context;
        this.b = listener;
        this.c = new ArrayList();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (!this.c.isEmpty()) {
            return this.c.size();
        }
        return 0;
    }

    @NotNull
    public final ChallengeClickListener getListener() {
        return this.b;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setChallengesList(@NotNull List<? extends BuddiesChallengeRes.Item> challengeList) {
        Intrinsics.checkNotNullParameter(challengeList, "challengeList");
        this.c = TypeIntrinsics.asMutableList(challengeList);
        notifyDataSetChanged();
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
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.c.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        FitnessChallengeItemBinding inflate = FitnessChallengeItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
