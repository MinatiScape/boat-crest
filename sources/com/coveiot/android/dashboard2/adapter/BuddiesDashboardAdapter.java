package com.coveiot.android.dashboard2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.fitnessbuddies.models.BuddyReminderModel;
import com.coveiot.android.fitnessbuddies.utils.PreferenceManager;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveSocial;
import com.coveiot.coveaccess.fitnessbuddies.ReactionType;
import com.coveiot.coveaccess.fitnessbuddies.model.HandleBuddyRequest;
import com.coveiot.coveaccess.fitnessbuddies.model.SendReactionRequest;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.BuddyDetails;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.BuddyGoalsDetails;
import com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyItems;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class BuddiesDashboardAdapter extends RecyclerView.Adapter<BuddiesDashboardViewHolder> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public List<GetBuddyItems> f4213a;
    @Nullable
    public List<BuddyReminderModel> b;

    /* loaded from: classes4.dex */
    public final class BuddiesDashboardViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f4214a;
        public final TextView b;
        public final TextView c;
        public final ProgressBar d;
        public final ConstraintLayout e;
        public final TextView f;
        public final TextView g;
        public final TextView h;
        public final TextView i;
        public final /* synthetic */ BuddiesDashboardAdapter j;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BuddiesDashboardViewHolder(@NotNull BuddiesDashboardAdapter buddiesDashboardAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.j = buddiesDashboardAdapter;
            this.f4214a = (ImageView) itemView.findViewById(R.id.ivBuddyPic);
            this.b = (TextView) itemView.findViewById(R.id.tvBuddyName);
            this.c = (TextView) itemView.findViewById(R.id.tvBuddyGoalCompletionPercentage);
            this.d = (ProgressBar) itemView.findViewById(R.id.buddyGoalCompletionProgress);
            this.e = (ConstraintLayout) itemView.findViewById(R.id.clRemindToCompleteGoal);
            this.f = (TextView) itemView.findViewById(R.id.tvReminderComplete);
            this.g = (TextView) itemView.findViewById(R.id.tvBuddyInfoMessage);
            this.h = (TextView) itemView.findViewById(R.id.tvGreat);
            this.i = (TextView) itemView.findViewById(R.id.tv_badges);
        }

        public static final void e() {
        }

        public static final void f(BuddiesDashboardAdapter this$0, Context context, GetBuddyItems buddyGoal, int i, Ref.ObjectRef reactType, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(buddyGoal, "$buddyGoal");
            Intrinsics.checkNotNullParameter(reactType, "$reactType");
            Intrinsics.checkNotNullExpressionValue(context, "context");
            this$0.b(context, buddyGoal, i, (ReactionType) reactType.element);
        }

        /* JADX WARN: Removed duplicated region for block: B:58:0x0153  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x015a  */
        /* JADX WARN: Removed duplicated region for block: B:61:0x015d  */
        /* JADX WARN: Removed duplicated region for block: B:75:0x01b6  */
        /* JADX WARN: Removed duplicated region for block: B:78:0x01c0  */
        /* JADX WARN: Removed duplicated region for block: B:81:0x01fb  */
        /* JADX WARN: Type inference failed for: r0v3, types: [T, com.coveiot.coveaccess.fitnessbuddies.ReactionType] */
        /* JADX WARN: Type inference failed for: r12v16, types: [T, com.coveiot.coveaccess.fitnessbuddies.ReactionType] */
        /* JADX WARN: Type inference failed for: r12v18, types: [T, com.coveiot.coveaccess.fitnessbuddies.ReactionType] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void bindView(@org.jetbrains.annotations.NotNull final com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyItems r11, boolean r12, final int r13) {
            /*
                Method dump skipped, instructions count: 575
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.dashboard2.adapter.BuddiesDashboardAdapter.BuddiesDashboardViewHolder.bindView(com.coveiot.coveaccess.fitnessbuddies.model.buddydetails.GetBuddyItems, boolean, int):void");
        }

        public final ProgressBar getBuddyCompletionProgress() {
            return this.d;
        }

        public final TextView getBuddyName() {
            return this.b;
        }

        public final ImageView getBuddyPic() {
            return this.f4214a;
        }

        public final TextView getBuddyTargetTextView() {
            return this.c;
        }

        public final ConstraintLayout getRemindToCompleteCL() {
            return this.e;
        }

        public final TextView getTvBuddyInfoMessage() {
            return this.g;
        }

        public final TextView getTvGreat() {
            return this.h;
        }

        public final TextView getTvRemindToComplete() {
            return this.f;
        }

        public final TextView getTv_badges() {
            return this.i;
        }
    }

    public final void a(int i, Context context) {
        GetBuddyItems getBuddyItems;
        BuddyDetails buddyDetails;
        GetBuddyItems getBuddyItems2;
        BuddyDetails buddyDetails2;
        PreferenceManager.Companion companion = PreferenceManager.Companion;
        List<BuddyReminderModel> fitnessBuddiesReminder = companion.getFitnessBuddiesReminder(context);
        List<BuddyReminderModel> fitnessBuddiesReminder2 = companion.getFitnessBuddiesReminder(context);
        Integer num = null;
        if (!AppUtils.isEmpty(fitnessBuddiesReminder2)) {
            Intrinsics.checkNotNull(fitnessBuddiesReminder2);
            for (BuddyReminderModel buddyReminderModel : fitnessBuddiesReminder2) {
                int buddyUserId = buddyReminderModel.getBuddyUserId();
                List<GetBuddyItems> list = this.f4213a;
                Integer num2 = (list == null || (getBuddyItems2 = list.get(i)) == null || (buddyDetails2 = getBuddyItems2.getBuddyDetails()) == null) ? null : buddyDetails2.userId;
                Intrinsics.checkNotNull(num2);
                if (buddyUserId == num2.intValue()) {
                    buddyReminderModel.setHasRemindedBuddy(true);
                    Calendar calendar = Calendar.getInstance();
                    Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
                    buddyReminderModel.setRemindDate(calendar);
                    Intrinsics.checkNotNull(fitnessBuddiesReminder);
                    List<BuddyReminderModel> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) fitnessBuddiesReminder);
                    mutableList.set(mutableList.indexOf(buddyReminderModel), buddyReminderModel);
                    PreferenceManager.Companion.saveBuddiesReminderModel(context, mutableList);
                }
            }
            return;
        }
        ArrayList arrayList = new ArrayList();
        List<GetBuddyItems> list2 = this.f4213a;
        if (list2 != null && (getBuddyItems = list2.get(i)) != null && (buddyDetails = getBuddyItems.getBuddyDetails()) != null) {
            num = buddyDetails.userId;
        }
        Intrinsics.checkNotNull(num);
        int intValue = num.intValue();
        Calendar calendar2 = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
        arrayList.add(new BuddyReminderModel(intValue, true, calendar2));
        companion.saveBuddiesReminderModel(context, arrayList);
    }

    public final void b(final Context context, GetBuddyItems getBuddyItems, final int i, final ReactionType reactionType) {
        int i2;
        StringBuilder sb = new StringBuilder();
        sb.append(context.getString(com.coveiot.android.fitnessbuddies.R.string.hey));
        sb.append(' ');
        sb.append(getBuddyItems.getBuddyDetails().name);
        sb.append(", ");
        sb.append(context.getString(com.coveiot.android.fitnessbuddies.R.string.your_are_little_behind));
        String sb2 = sb.toString();
        if (reactionType == ReactionType.APPLAUD) {
            sb2 = context.getString(com.coveiot.android.fitnessbuddies.R.string.way_to_go) + ' ' + getBuddyItems.getBuddyDetails().name + ' ' + context.getString(com.coveiot.android.fitnessbuddies.R.string.on_reaching_your_goal);
        } else if (reactionType == ReactionType.CHEER) {
            sb2 = context.getString(i2) + ' ' + getBuddyItems.getBuddyDetails().name + ", " + context.getString(com.coveiot.android.fitnessbuddies.R.string.just_a_few_more_steps_and_you_will);
        }
        int i3 = 0;
        Intrinsics.checkNotNull(getBuddyItems);
        Iterator<BuddyGoalsDetails> it = getBuddyItems.getGoalsList().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            BuddyGoalsDetails next = it.next();
            if (m.equals(next.activityType, "WALK", true)) {
                String str = next.goalId;
                Intrinsics.checkNotNullExpressionValue(str, "buddyItemGoals.goalId");
                i3 = Integer.parseInt(str);
                break;
            }
        }
        CoveSocial.sendReaction(Integer.valueOf(i3), new SendReactionRequest(reactionType, sb2), new CoveApiListener<HandleBuddyRequest, CoveApiErrorModel>() { // from class: com.coveiot.android.dashboard2.adapter.BuddiesDashboardAdapter$sendCheerMessage$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                Context context2 = context;
                Toast.makeText(context2, context2.getString(com.coveiot.android.fitnessbuddies.R.string.error_sending_message), 0).show();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable HandleBuddyRequest handleBuddyRequest) {
                BuddiesDashboardAdapter.this.a(i, context);
                if (reactionType != ReactionType.APPLAUD) {
                    Context context2 = context;
                    Toast.makeText(context2, "Great!, " + context.getString(R.string.you_reminded_buddy), 0).show();
                } else {
                    Context context3 = context;
                    Toast.makeText(context3, "Great!, " + context.getString(R.string.you_applauded_buddy), 0).show();
                }
                BuddiesDashboardAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Nullable
    public final List<GetBuddyItems> getBuddiesGoalList() {
        return this.f4213a;
    }

    @Nullable
    public final List<BuddyReminderModel> getBuddiesReminders() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<GetBuddyItems> list = this.f4213a;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            return list.size();
        }
        return 0;
    }

    public final void setBuddiesGoalList(@Nullable List<GetBuddyItems> list) {
        this.f4213a = list;
    }

    public final void setBuddiesReminders(@Nullable List<BuddyReminderModel> list) {
        this.b = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull BuddiesDashboardViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        boolean z = true;
        if (!AppUtils.isEmpty(this.b)) {
            List<BuddyReminderModel> list = this.b;
            Intrinsics.checkNotNull(list);
            for (BuddyReminderModel buddyReminderModel : list) {
                int buddyUserId = buddyReminderModel.getBuddyUserId();
                List<GetBuddyItems> list2 = this.f4213a;
                Intrinsics.checkNotNull(list2);
                Integer num = list2.get(i).getBuddyDetails().userId;
                if (num != null && buddyUserId == num.intValue() && buddyReminderModel.isRemindDataToday()) {
                    z = false;
                }
            }
        }
        List<GetBuddyItems> list3 = this.f4213a;
        Intrinsics.checkNotNull(list3);
        holder.bindView(list3.get(i), z, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public BuddiesDashboardViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_my_buddies_card_layout, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context)\n   …rd_layout, parent, false)");
        return new BuddiesDashboardViewHolder(this, inflate);
    }
}
