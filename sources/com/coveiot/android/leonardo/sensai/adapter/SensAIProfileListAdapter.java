package com.coveiot.android.leonardo.sensai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.SensAiProfileItemBinding;
import com.coveiot.android.leonardo.sensai.model.ProfileListModel;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class SensAIProfileListAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5375a;
    @NotNull
    public List<ProfileListModel> b;
    @NotNull
    public final OnItemClickListener c;

    /* loaded from: classes5.dex */
    public interface OnItemClickListener {
        void onItemClicked(int i);
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f5376a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull SensAIProfileListAdapter sensAIProfileListAdapter, SensAiProfileItemBinding itemView) {
            super(itemView.getRoot());
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            ImageView imageView = itemView.ivProfile;
            Intrinsics.checkNotNullExpressionValue(imageView, "itemView.ivProfile");
            this.f5376a = imageView;
            TextView textView = itemView.tvProfile;
            Intrinsics.checkNotNullExpressionValue(textView, "itemView.tvProfile");
            this.b = textView;
            TextView textView2 = itemView.tvAdd;
            Intrinsics.checkNotNullExpressionValue(textView2, "itemView.tvAdd");
            this.c = textView2;
        }

        @NotNull
        public final ImageView getIvProfile() {
            return this.f5376a;
        }

        @NotNull
        public final TextView getTvAdd() {
            return this.c;
        }

        @NotNull
        public final TextView getTvProfile() {
            return this.b;
        }
    }

    public SensAIProfileListAdapter(@NotNull Context mContext, @NotNull List<ProfileListModel> profileListModel, @NotNull OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(profileListModel, "profileListModel");
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        this.f5375a = mContext;
        this.b = profileListModel;
        this.c = onItemClickListener;
    }

    public static final void b(SensAIProfileListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c.onItemClicked(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @NotNull
    public final OnItemClickListener getOnItemClickListener() {
        return this.c;
    }

    public final void setData(@NotNull List<ProfileListModel> profileListModel) {
        Intrinsics.checkNotNullParameter(profileListModel, "profileListModel");
        this.b = profileListModel;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getTvProfile().setText(this.b.get(i).getProfile());
        if (this.b.get(i).isDataPresent()) {
            holder.getIvProfile().setImageDrawable(this.f5375a.getDrawable(R.drawable.sens_ai_success));
            holder.getTvAdd().setVisibility(4);
        } else {
            holder.getIvProfile().setImageDrawable(this.f5375a.getDrawable(R.drawable.sens_ai_delete));
            holder.getTvAdd().setVisibility(0);
        }
        holder.getTvAdd().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.adapter.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SensAIProfileListAdapter.b(SensAIProfileListAdapter.this, i, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        SensAiProfileItemBinding inflate = SensAiProfileItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
