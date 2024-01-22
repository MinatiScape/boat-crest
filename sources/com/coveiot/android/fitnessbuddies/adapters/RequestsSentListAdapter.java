package com.coveiot.android.fitnessbuddies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.adapters.ManageBuddiesAdapter;
import com.coveiot.android.fitnessbuddies.databinding.BuddiesRequestItemBinding;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.fitnessbuddies.model.common.GlobalRankDetailsRequests;
import com.coveiot.coveaccess.fitnessbuddies.model.common.Requests;
import com.coveiot.coveaccess.fitnessbuddies.model.common.ToUserDetailsRequests;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.ImageLodeListener;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class RequestsSentListAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4432a;
    @NotNull
    public final List<Requests> b;
    @Nullable
    public final ManageBuddiesAdapter.OnOptionSelectedListener c;

    /* loaded from: classes4.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final BuddiesRequestItemBinding f4433a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final TextView d;
        @NotNull
        public final ImageView e;
        @NotNull
        public final TextView f;
        @NotNull
        public final Button g;
        @NotNull
        public final Button h;
        @NotNull
        public final TextView i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull RequestsSentListAdapter requestsSentListAdapter, BuddiesRequestItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f4433a = binding;
            TextView textView = binding.tvRank;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvRank");
            this.b = textView;
            TextView textView2 = binding.tvName;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvName");
            this.c = textView2;
            TextView textView3 = binding.tvBadge;
            Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvBadge");
            this.d = textView3;
            ImageView imageView = binding.ivProfile;
            Intrinsics.checkNotNullExpressionValue(imageView, "binding.ivProfile");
            this.e = imageView;
            TextView textView4 = binding.tvDate;
            Intrinsics.checkNotNullExpressionValue(textView4, "binding.tvDate");
            this.f = textView4;
            Button button = binding.btnDecline;
            Intrinsics.checkNotNullExpressionValue(button, "binding.btnDecline");
            this.g = button;
            Button button2 = binding.btnAccept;
            Intrinsics.checkNotNullExpressionValue(button2, "binding.btnAccept");
            this.h = button2;
            TextView textView5 = binding.tvRequestsContent;
            Intrinsics.checkNotNullExpressionValue(textView5, "binding.tvRequestsContent");
            this.i = textView5;
        }

        @NotNull
        public final Button getBtnAccept() {
            return this.h;
        }

        @NotNull
        public final Button getBtnDecline() {
            return this.g;
        }

        @NotNull
        public final ImageView getIvProfile() {
            return this.e;
        }

        @NotNull
        public final TextView getTvBadge() {
            return this.d;
        }

        @NotNull
        public final TextView getTvDate() {
            return this.f;
        }

        @NotNull
        public final TextView getTvName() {
            return this.c;
        }

        @NotNull
        public final TextView getTvRank() {
            return this.b;
        }

        @NotNull
        public final TextView getTvRequestContent() {
            return this.i;
        }
    }

    public /* synthetic */ RequestsSentListAdapter(Context context, List list, ManageBuddiesAdapter.OnOptionSelectedListener onOptionSelectedListener, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, list, (i & 4) != 0 ? null : onOptionSelectedListener);
    }

    public static final void d() {
    }

    public static final void e(RequestsSentListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ManageBuddiesAdapter.OnOptionSelectedListener onOptionSelectedListener = this$0.c;
        Intrinsics.checkNotNull(onOptionSelectedListener);
        onOptionSelectedListener.onResend(this$0.b.get(i).requestId, this$0.b.get(i).toUserMobileNumber, this$0.b.get(i).toCloverName, this$0.b.get(i));
    }

    public static final void f(RequestsSentListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ManageBuddiesAdapter.OnOptionSelectedListener onOptionSelectedListener = this$0.c;
        Intrinsics.checkNotNull(onOptionSelectedListener);
        onOptionSelectedListener.onDelete(this$0.b.get(i).requestId);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Nullable
    public final ManageBuddiesAdapter.OnOptionSelectedListener getMOnOptionSelectedListener() {
        return this.c;
    }

    @NotNull
    public final List<Requests> getRequestSentList() {
        return this.b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RequestsSentListAdapter(@NotNull Context mContext, @NotNull List<? extends Requests> requestSentList, @Nullable ManageBuddiesAdapter.OnOptionSelectedListener onOptionSelectedListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(requestSentList, "requestSentList");
        this.f4432a = mContext;
        this.b = requestSentList;
        this.c = onOptionSelectedListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, final int i) {
        GlobalRankDetailsRequests globalRankDetailsRequests;
        GlobalRankDetailsRequests globalRankDetailsRequests2;
        ToUserDetailsRequests toUserDetailsRequests;
        GlobalRankDetailsRequests globalRankDetailsRequests3;
        ToUserDetailsRequests toUserDetailsRequests2;
        Intrinsics.checkNotNullParameter(holder, "holder");
        String str = this.b.get(i).toUserName;
        boolean z = false;
        if (!(str == null || str.length() == 0)) {
            holder.getTvName().setText(this.b.get(i).toUserName);
        } else {
            holder.getTvName().setText(this.b.get(i).toCloverName);
        }
        Requests requests = this.b.get(i);
        Integer num = null;
        if (((requests == null || (toUserDetailsRequests2 = requests.toUserDetails) == null) ? null : toUserDetailsRequests2.totalEarnedBadges) != null) {
            ToUserDetailsRequests toUserDetailsRequests3 = this.b.get(i).toUserDetails;
            Integer num2 = toUserDetailsRequests3 != null ? toUserDetailsRequests3.totalEarnedBadges : null;
            if (num2 != null && num2.intValue() == 0) {
                holder.getTvBadge().setText("-- " + this.f4432a.getString(R.string.badges));
            } else if (num2 != null && num2.intValue() == 1) {
                TextView tvBadge = holder.getTvBadge();
                StringBuilder sb = new StringBuilder();
                ToUserDetailsRequests toUserDetailsRequests4 = this.b.get(i).toUserDetails;
                sb.append(toUserDetailsRequests4 != null ? toUserDetailsRequests4.totalEarnedBadges : null);
                sb.append(' ');
                sb.append(this.f4432a.getString(R.string.badge));
                tvBadge.setText(sb.toString());
            } else {
                TextView tvBadge2 = holder.getTvBadge();
                StringBuilder sb2 = new StringBuilder();
                ToUserDetailsRequests toUserDetailsRequests5 = this.b.get(i).toUserDetails;
                sb2.append(toUserDetailsRequests5 != null ? toUserDetailsRequests5.totalEarnedBadges : null);
                sb2.append(' ');
                sb2.append(this.f4432a.getString(R.string.badges));
                tvBadge2.setText(sb2.toString());
            }
        } else {
            holder.getTvBadge().setText("-- " + this.f4432a.getString(R.string.badges));
        }
        Requests requests2 = this.b.get(i);
        if (((requests2 == null || (toUserDetailsRequests = requests2.toUserDetails) == null || (globalRankDetailsRequests3 = toUserDetailsRequests.globalRankData) == null) ? null : globalRankDetailsRequests3.rank) != null) {
            ToUserDetailsRequests toUserDetailsRequests6 = this.b.get(i).toUserDetails;
            Integer num3 = (toUserDetailsRequests6 == null || (globalRankDetailsRequests2 = toUserDetailsRequests6.globalRankData) == null) ? null : globalRankDetailsRequests2.rank;
            if (num3 != null && num3.intValue() == 0) {
                holder.getTvRank().setText("-- " + this.f4432a.getString(R.string.rank));
            } else {
                TextView tvRank = holder.getTvRank();
                StringBuilder sb3 = new StringBuilder();
                ToUserDetailsRequests toUserDetailsRequests7 = this.b.get(i).toUserDetails;
                if (toUserDetailsRequests7 != null && (globalRankDetailsRequests = toUserDetailsRequests7.globalRankData) != null) {
                    num = globalRankDetailsRequests.rank;
                }
                sb3.append(num);
                sb3.append(' ');
                sb3.append(this.f4432a.getString(R.string.rank));
                tvRank.setText(sb3.toString());
            }
        } else {
            holder.getTvRank().setText("-- " + this.f4432a.getString(R.string.rank));
        }
        String str2 = this.b.get(i).toUserDpUrl;
        if (str2 == null || str2.length() == 0) {
            z = true;
        }
        if (!z) {
            GlideUtils.loadCircularImage(this.f4432a, this.b.get(i).toUserDpUrl, holder.getIvProfile(), new ImageLodeListener() { // from class: com.coveiot.android.fitnessbuddies.adapters.f
                @Override // com.coveiot.utils.utility.ImageLodeListener
                public final void onImageLoaded() {
                    RequestsSentListAdapter.d();
                }
            });
        } else {
            holder.getIvProfile().setImageResource(R.drawable.default_avatar);
        }
        holder.getTvDate().setText(ThemesUtils.INSTANCE.formattedDate(this.b.get(i).lastInvitedDate, "dd MMM yyyy"));
        holder.getBtnAccept().setText(this.f4432a.getString(R.string.re_invite));
        holder.getBtnDecline().setText(this.f4432a.getString(R.string.cancel_request));
        holder.getBtnAccept().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.adapters.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RequestsSentListAdapter.e(RequestsSentListAdapter.this, i, view);
            }
        });
        holder.getBtnDecline().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.adapters.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RequestsSentListAdapter.f(RequestsSentListAdapter.this, i, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        BuddiesRequestItemBinding inflate = BuddiesRequestItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
