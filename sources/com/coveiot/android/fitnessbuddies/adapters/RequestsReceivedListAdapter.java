package com.coveiot.android.fitnessbuddies.adapters;

import android.content.Context;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.adapters.ManageBuddiesAdapter;
import com.coveiot.android.fitnessbuddies.databinding.BuddiesRequestItemBinding;
import com.coveiot.android.theme.compundview.MySpannable;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.fitnessbuddies.model.common.GlobalRankDetailsRequests;
import com.coveiot.coveaccess.fitnessbuddies.model.common.Requests;
import com.coveiot.coveaccess.fitnessbuddies.model.common.ToUserDetailsRequests;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.ImageLodeListener;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class RequestsReceivedListAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4430a;
    @NotNull
    public final List<Requests> b;
    @Nullable
    public final ManageBuddiesAdapter.OnOptionSelectedListener c;

    /* loaded from: classes4.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final BuddiesRequestItemBinding f4431a;
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
        public ViewHolder(@NotNull RequestsReceivedListAdapter requestsReceivedListAdapter, BuddiesRequestItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f4431a = binding;
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

    public /* synthetic */ RequestsReceivedListAdapter(Context context, List list, ManageBuddiesAdapter.OnOptionSelectedListener onOptionSelectedListener, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, list, (i & 4) != 0 ? null : onOptionSelectedListener);
    }

    public static final void f() {
    }

    public static final void g(RequestsReceivedListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ManageBuddiesAdapter.OnOptionSelectedListener onOptionSelectedListener = this$0.c;
        Intrinsics.checkNotNull(onOptionSelectedListener);
        onOptionSelectedListener.onAccept(this$0.b.get(i).requestId);
    }

    public static final void h(RequestsReceivedListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ManageBuddiesAdapter.OnOptionSelectedListener onOptionSelectedListener = this$0.c;
        Intrinsics.checkNotNull(onOptionSelectedListener);
        onOptionSelectedListener.onReject(this$0.b.get(i).requestId);
    }

    public final SpannableStringBuilder e(Spanned spanned, final TextView textView, int i, String str, final boolean z) {
        String obj = spanned.toString();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spanned);
        if (StringsKt__StringsKt.contains$default((CharSequence) obj, (CharSequence) str, false, 2, (Object) null)) {
            spannableStringBuilder.setSpan(new MySpannable() { // from class: com.coveiot.android.fitnessbuddies.adapters.RequestsReceivedListAdapter$addClickablePartTextViewResizable$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(false);
                }

                @Override // com.coveiot.android.theme.compundview.MySpannable, android.text.style.ClickableSpan
                public void onClick(@NotNull View widget) {
                    Intrinsics.checkNotNullParameter(widget, "widget");
                    TextView textView2 = textView;
                    textView2.setLayoutParams(textView2.getLayoutParams());
                    TextView textView3 = textView;
                    textView3.setText(textView3.getTag().toString(), TextView.BufferType.SPANNABLE);
                    textView.invalidate();
                    if (z) {
                        this.makeTextViewResizable(textView, -1, "Read Less", false);
                    } else {
                        this.makeTextViewResizable(textView, 1, "... Read More", true);
                    }
                }
            }, StringsKt__StringsKt.indexOf$default((CharSequence) obj, str, 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) obj, str, 0, false, 6, (Object) null) + str.length(), 0);
        }
        return spannableStringBuilder;
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
    public final List<Requests> getRequestReceivedList() {
        return this.b;
    }

    public final void makeTextViewResizable(@NotNull final TextView tv, final int i, @NotNull final String expandText, final boolean z) {
        Intrinsics.checkNotNullParameter(tv, "tv");
        Intrinsics.checkNotNullParameter(expandText, "expandText");
        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        tv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.coveiot.android.fitnessbuddies.adapters.RequestsReceivedListAdapter$makeTextViewResizable$1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                int lineEnd;
                String str;
                SpannableStringBuilder e;
                tv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int i2 = i;
                if (i2 == 0) {
                    lineEnd = tv.getLayout().getLineEnd(0);
                    str = ((Object) tv.getText().subSequence(0, (lineEnd - expandText.length()) + 1)) + ' ' + expandText;
                } else if (i2 > 0 && tv.getLineCount() >= i) {
                    lineEnd = tv.getLayout().getLineEnd(i - 1);
                    str = ((Object) tv.getText().subSequence(0, (lineEnd - expandText.length()) + 1)) + ' ' + expandText;
                } else {
                    lineEnd = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    str = ((Object) tv.getText().subSequence(0, lineEnd)) + ' ' + expandText;
                }
                int i3 = lineEnd;
                tv.setText(str);
                tv.setMovementMethod(LinkMovementMethod.getInstance());
                TextView textView = tv;
                e = this.e(new SpannableString(tv.getText().toString()), tv, i3, expandText, z);
                textView.setText(e, TextView.BufferType.SPANNABLE);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public RequestsReceivedListAdapter(@NotNull Context mContext, @NotNull List<? extends Requests> requestReceivedList, @Nullable ManageBuddiesAdapter.OnOptionSelectedListener onOptionSelectedListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(requestReceivedList, "requestReceivedList");
        this.f4430a = mContext;
        this.b = requestReceivedList;
        this.c = onOptionSelectedListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, final int i) {
        ToUserDetailsRequests toUserDetailsRequests;
        GlobalRankDetailsRequests globalRankDetailsRequests;
        ToUserDetailsRequests toUserDetailsRequests2;
        GlobalRankDetailsRequests globalRankDetailsRequests2;
        ToUserDetailsRequests toUserDetailsRequests3;
        GlobalRankDetailsRequests globalRankDetailsRequests3;
        ToUserDetailsRequests toUserDetailsRequests4;
        ToUserDetailsRequests toUserDetailsRequests5;
        ToUserDetailsRequests toUserDetailsRequests6;
        ToUserDetailsRequests toUserDetailsRequests7;
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getTvName().setText(this.b.get(i).fromUserName);
        Requests requests = this.b.get(i);
        Integer num = null;
        if (((requests == null || (toUserDetailsRequests7 = requests.fromUserDetails) == null) ? null : toUserDetailsRequests7.totalEarnedBadges) != null) {
            Requests requests2 = this.b.get(i);
            Integer num2 = (requests2 == null || (toUserDetailsRequests6 = requests2.fromUserDetails) == null) ? null : toUserDetailsRequests6.totalEarnedBadges;
            if (num2 != null && num2.intValue() == 0) {
                TextView tvBadge = holder.getTvBadge();
                tvBadge.setText("-- " + this.f4430a.getString(R.string.badges));
            } else if (num2 != null && num2.intValue() == 1) {
                TextView tvBadge2 = holder.getTvBadge();
                StringBuilder sb = new StringBuilder();
                Requests requests3 = this.b.get(i);
                sb.append((requests3 == null || (toUserDetailsRequests5 = requests3.fromUserDetails) == null) ? null : toUserDetailsRequests5.totalEarnedBadges);
                sb.append(' ');
                sb.append(this.f4430a.getString(R.string.badge));
                tvBadge2.setText(sb.toString());
            } else {
                TextView tvBadge3 = holder.getTvBadge();
                StringBuilder sb2 = new StringBuilder();
                Requests requests4 = this.b.get(i);
                sb2.append((requests4 == null || (toUserDetailsRequests4 = requests4.fromUserDetails) == null) ? null : toUserDetailsRequests4.totalEarnedBadges);
                sb2.append(' ');
                sb2.append(this.f4430a.getString(R.string.badges));
                tvBadge3.setText(sb2.toString());
            }
        } else {
            TextView tvBadge4 = holder.getTvBadge();
            tvBadge4.setText("-- " + this.f4430a.getString(R.string.badges));
        }
        Requests requests5 = this.b.get(i);
        if (((requests5 == null || (toUserDetailsRequests3 = requests5.fromUserDetails) == null || (globalRankDetailsRequests3 = toUserDetailsRequests3.globalRankData) == null) ? null : globalRankDetailsRequests3.rank) != null) {
            Requests requests6 = this.b.get(i);
            Integer num3 = (requests6 == null || (toUserDetailsRequests2 = requests6.fromUserDetails) == null || (globalRankDetailsRequests2 = toUserDetailsRequests2.globalRankData) == null) ? null : globalRankDetailsRequests2.rank;
            if (num3 != null && num3.intValue() == 0) {
                TextView tvRank = holder.getTvRank();
                tvRank.setText("-- " + this.f4430a.getString(R.string.rank));
            } else {
                TextView tvRank2 = holder.getTvRank();
                StringBuilder sb3 = new StringBuilder();
                Requests requests7 = this.b.get(i);
                if (requests7 != null && (toUserDetailsRequests = requests7.fromUserDetails) != null && (globalRankDetailsRequests = toUserDetailsRequests.globalRankData) != null) {
                    num = globalRankDetailsRequests.rank;
                }
                sb3.append(num);
                sb3.append(' ');
                sb3.append(this.f4430a.getString(R.string.rank));
                tvRank2.setText(sb3.toString());
            }
        } else {
            TextView tvRank3 = holder.getTvRank();
            tvRank3.setText("-- " + this.f4430a.getString(R.string.rank));
        }
        String str = this.b.get(i).fromUserDpUrl;
        if (!(str == null || str.length() == 0)) {
            GlideUtils.loadCircularImage(this.f4430a, this.b.get(i).fromUserDpUrl, holder.getIvProfile(), new ImageLodeListener() { // from class: com.coveiot.android.fitnessbuddies.adapters.c
                @Override // com.coveiot.utils.utility.ImageLodeListener
                public final void onImageLoaded() {
                    RequestsReceivedListAdapter.f();
                }
            });
        } else {
            holder.getIvProfile().setImageResource(R.drawable.default_avatar);
        }
        holder.getTvDate().setText(ThemesUtils.INSTANCE.formattedDate(this.b.get(i).lastInvitedDate, "dd MMM yyyy"));
        holder.getBtnAccept().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.adapters.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RequestsReceivedListAdapter.g(RequestsReceivedListAdapter.this, i, view);
            }
        });
        holder.getBtnDecline().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.fitnessbuddies.adapters.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RequestsReceivedListAdapter.h(RequestsReceivedListAdapter.this, i, view);
            }
        });
        holder.getTvRequestContent().setVisibility(0);
        TextView tvRequestContent = holder.getTvRequestContent();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String string = this.f4430a.getString(R.string.buddy_request_msg);
        Intrinsics.checkNotNullExpressionValue(string, "mContext.getString(R.string.buddy_request_msg)");
        String format = String.format(locale, string, Arrays.copyOf(new Object[]{SessionManager.getInstance(this.f4430a).getUserDetails().getName()}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        tvRequestContent.setText(Html.fromHtml(format), TextView.BufferType.SPANNABLE);
        makeTextViewResizable(holder.getTvRequestContent(), 1, "... Read More", true);
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
