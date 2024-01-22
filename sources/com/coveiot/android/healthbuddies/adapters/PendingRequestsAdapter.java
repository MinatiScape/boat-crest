package com.coveiot.android.healthbuddies.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.healthbuddies.R;
import com.coveiot.coveaccess.healthbuddies.HealthBuddy;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.ImageLodeListener;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class PendingRequestsAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    public final String f4572a;
    @Nullable
    public List<HealthBuddy> b;
    @NotNull
    public OnOptionSelectedListener c;
    @NotNull
    public Context d;

    /* loaded from: classes3.dex */
    public interface OnOptionSelectedListener {
        void onCancel(int i);

        void onDelete(int i);

        void onReInvite(int i, @Nullable String str, @Nullable String str2);
    }

    /* loaded from: classes3.dex */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public View f4573a;
        @NotNull
        public ImageView b;
        @NotNull
        public TextView c;
        @NotNull
        public TextView d;
        @NotNull
        public Button e;
        @NotNull
        public Button f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull View baseLayout) {
            super(baseLayout);
            Intrinsics.checkNotNullParameter(baseLayout, "baseLayout");
            this.f4573a = baseLayout;
            View findViewById = baseLayout.findViewById(R.id.user_pic);
            Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ImageView");
            this.b = (ImageView) findViewById;
            View findViewById2 = this.f4573a.findViewById(R.id.user_name);
            Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
            this.c = (TextView) findViewById2;
            View findViewById3 = this.f4573a.findViewById(R.id.number);
            Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
            this.d = (TextView) findViewById3;
            View findViewById4 = this.f4573a.findViewById(R.id.btnCancel);
            Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.Button");
            this.e = (Button) findViewById4;
            View findViewById5 = this.f4573a.findViewById(R.id.btnReInvite);
            Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.Button");
            this.f = (Button) findViewById5;
        }

        @NotNull
        public final View getBaseLayout() {
            return this.f4573a;
        }

        @NotNull
        public final TextView getContactNameTv() {
            return this.c;
        }

        @NotNull
        public final TextView getContactPhoneNumberTv() {
            return this.d;
        }

        @NotNull
        public final ImageView getContactPhotoIv() {
            return this.b;
        }

        @NotNull
        public final Button getDeleteBtn() {
            return this.e;
        }

        @NotNull
        public final Button getReInvite() {
            return this.f;
        }

        public final void setBaseLayout(@NotNull View view) {
            Intrinsics.checkNotNullParameter(view, "<set-?>");
            this.f4573a = view;
        }

        public final void setContactNameTv(@NotNull TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.c = textView;
        }

        public final void setContactPhoneNumberTv(@NotNull TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.d = textView;
        }

        public final void setContactPhotoIv(@NotNull ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.b = imageView;
        }

        public final void setDeleteBtn(@NotNull Button button) {
            Intrinsics.checkNotNullParameter(button, "<set-?>");
            this.e = button;
        }

        public final void setReInvite(@NotNull Button button) {
            Intrinsics.checkNotNullParameter(button, "<set-?>");
            this.f = button;
        }
    }

    public PendingRequestsAdapter(@NotNull Context context, @Nullable List<HealthBuddy> list, @NotNull OnOptionSelectedListener itemClickInterface) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(itemClickInterface, "itemClickInterface");
        this.f4572a = PendingRequestsAdapter.class.getSimpleName();
        this.b = list;
        this.c = itemClickInterface;
        this.d = context;
    }

    public static final void d() {
    }

    public static final void e(PendingRequestsAdapter this$0, HealthBuddy buddyInfo, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(buddyInfo, "$buddyInfo");
        OnOptionSelectedListener onOptionSelectedListener = this$0.c;
        Integer num = buddyInfo.requestId;
        Intrinsics.checkNotNullExpressionValue(num, "buddyInfo.requestId");
        onOptionSelectedListener.onReInvite(num.intValue(), buddyInfo.toUserMobileNumber, buddyInfo.toContactName);
    }

    public static final void f(PendingRequestsAdapter this$0, HealthBuddy buddyInfo, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(buddyInfo, "$buddyInfo");
        OnOptionSelectedListener onOptionSelectedListener = this$0.c;
        Integer num = buddyInfo.requestId;
        Intrinsics.checkNotNullExpressionValue(num, "buddyInfo.requestId");
        onOptionSelectedListener.onCancel(num.intValue());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<HealthBuddy> list = this.b;
        Intrinsics.checkNotNull(list);
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        String str = this.f4572a;
        StringBuilder sb = new StringBuilder();
        sb.append("requestsentdetail: ");
        List<HealthBuddy> list = this.b;
        Intrinsics.checkNotNull(list);
        sb.append(list.get(i).userId);
        Log.d(str, sb.toString());
        List<HealthBuddy> list2 = this.b;
        Intrinsics.checkNotNull(list2);
        final HealthBuddy healthBuddy = list2.get(i);
        String str2 = healthBuddy.toUserDpUrl;
        if (str2 != null) {
            Intrinsics.checkNotNullExpressionValue(str2, "buddyInfo.toUserDpUrl");
            if (!kotlin.text.m.isBlank(str2)) {
                GlideUtils.loadCircularImage(this.d, healthBuddy.toUserDpUrl, holder.getContactPhotoIv(), new ImageLodeListener() { // from class: com.coveiot.android.healthbuddies.adapters.t
                    @Override // com.coveiot.utils.utility.ImageLodeListener
                    public final void onImageLoaded() {
                        PendingRequestsAdapter.d();
                    }
                });
                holder.getContactNameTv().setText(healthBuddy.toContactName);
                holder.getContactPhoneNumberTv().setText(healthBuddy.lastInvitedDate);
                holder.getReInvite().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.adapters.s
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PendingRequestsAdapter.e(PendingRequestsAdapter.this, healthBuddy, view);
                    }
                });
                holder.getDeleteBtn().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.adapters.r
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PendingRequestsAdapter.f(PendingRequestsAdapter.this, healthBuddy, view);
                    }
                });
            }
        }
        holder.getContactPhotoIv().setImageResource(R.drawable.default_avatar);
        holder.getContactNameTv().setText(healthBuddy.toContactName);
        holder.getContactPhoneNumberTv().setText(healthBuddy.lastInvitedDate);
        holder.getReInvite().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.adapters.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PendingRequestsAdapter.e(PendingRequestsAdapter.this, healthBuddy, view);
            }
        });
        holder.getDeleteBtn().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.adapters.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PendingRequestsAdapter.f(PendingRequestsAdapter.this, healthBuddy, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_health_buddies_list_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(v, "v");
        return new ViewHolder(v);
    }
}
