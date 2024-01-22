package com.coveiot.android.leonardo.quickreply.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.quickreply.model.QuickReplyModel;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ManageQuickReplyListAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5344a;
    @Nullable
    public List<QuickReplyModel> b;
    @NotNull
    public OnItemClickListener c;

    /* loaded from: classes5.dex */
    public interface OnItemClickListener {
        void onEditItemClickListener(@NotNull String str, int i);
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f5345a;
        @NotNull
        public final ImageView b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull ManageQuickReplyListAdapter manageQuickReplyListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            TextView textView = (TextView) itemView.findViewById(R.id.tv_msg);
            Intrinsics.checkNotNullExpressionValue(textView, "itemView.tv_msg");
            this.f5345a = textView;
            ImageView imageView = (ImageView) itemView.findViewById(R.id.iv_msg_edit);
            Intrinsics.checkNotNullExpressionValue(imageView, "itemView.iv_msg_edit");
            this.b = imageView;
        }

        @NotNull
        public final ImageView getIvMsgEdit() {
            return this.b;
        }

        @NotNull
        public final TextView getTvMsg() {
            return this.f5345a;
        }
    }

    public ManageQuickReplyListAdapter(@NotNull Context mContext, @Nullable List<QuickReplyModel> list, @NotNull OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        this.f5344a = mContext;
        this.b = list;
        this.c = onItemClickListener;
    }

    public static final void b(ManageQuickReplyListAdapter this$0, ViewHolder holder, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(holder, "$holder");
        this$0.c.onEditItemClickListener(holder.getTvMsg().getText().toString(), holder.getBindingAdapterPosition());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<QuickReplyModel> list = this.b;
        Intrinsics.checkNotNull(list);
        return list.size();
    }

    @NotNull
    public final OnItemClickListener getOnItemClickListener() {
        return this.c;
    }

    @Nullable
    public final List<QuickReplyModel> getQuickReplyList() {
        return this.b;
    }

    public final void setOnItemClickListener(@NotNull OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(onItemClickListener, "<set-?>");
        this.c = onItemClickListener;
    }

    public final void setQuickReplyList(@Nullable List<QuickReplyModel> list) {
        this.b = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull final ViewHolder holder, int i) {
        QuickReplyModel quickReplyModel;
        Intrinsics.checkNotNullParameter(holder, "holder");
        TextView tvMsg = holder.getTvMsg();
        List<QuickReplyModel> list = this.b;
        tvMsg.setText((list == null || (quickReplyModel = list.get(i)) == null) ? null : quickReplyModel.getQuickReplyMessage());
        holder.getIvMsgEdit().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.quickreply.adapter.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ManageQuickReplyListAdapter.b(ManageQuickReplyListAdapter.this, holder, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(this.f5344a).inflate(R.layout.item_manage_quick_reply, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(mContext).inflate(R…ick_reply, parent, false)");
        return new ViewHolder(this, inflate);
    }
}
