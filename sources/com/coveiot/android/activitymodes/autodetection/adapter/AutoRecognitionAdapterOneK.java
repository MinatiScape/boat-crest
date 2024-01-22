package com.coveiot.android.activitymodes.autodetection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.models.AutoRecognitonModelOneK;
import com.coveiot.utils.utility.GlideUtils;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class AutoRecognitionAdapterOneK extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f2793a;
    @NotNull
    public ArrayList<AutoRecognitonModelOneK> b;
    @NotNull
    public OnItemClickListener c;

    /* loaded from: classes2.dex */
    public interface OnItemClickListener {
        void onItemClick(@NotNull AutoRecognitonModelOneK autoRecognitonModelOneK, int i);
    }

    /* loaded from: classes2.dex */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f2794a;
        @NotNull
        public final SwitchCompat b;
        @NotNull
        public final ImageView c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            TextView textView = (TextView) itemView.findViewById(R.id.type_name);
            Intrinsics.checkNotNullExpressionValue(textView, "itemView.type_name");
            this.f2794a = textView;
            SwitchCompat switchCompat = (SwitchCompat) itemView.findViewById(R.id.switch_type);
            Intrinsics.checkNotNullExpressionValue(switchCompat, "itemView.switch_type");
            this.b = switchCompat;
            ImageView imageView = (ImageView) itemView.findViewById(R.id.iv_aadicon);
            Intrinsics.checkNotNullExpressionValue(imageView, "itemView.iv_aadicon");
            this.c = imageView;
        }

        @NotNull
        public final ImageView getIv_aadicon() {
            return this.c;
        }

        @NotNull
        public final SwitchCompat getSwitch() {
            return this.b;
        }

        @NotNull
        public final TextView getType_name() {
            return this.f2794a;
        }
    }

    public AutoRecognitionAdapterOneK(@NotNull Context context, @NotNull ArrayList<AutoRecognitonModelOneK> listData, @NotNull OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listData, "listData");
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        this.f2793a = context;
        this.b = listData;
        this.c = onItemClickListener;
    }

    public static final void b(AutoRecognitionAdapterOneK this$0, int i, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.b.get(i).setSelected(z);
        OnItemClickListener onItemClickListener = this$0.c;
        AutoRecognitonModelOneK autoRecognitonModelOneK = this$0.b.get(i);
        Intrinsics.checkNotNullExpressionValue(autoRecognitonModelOneK, "listData[position]");
        onItemClickListener.onItemClick(autoRecognitonModelOneK, i);
    }

    @NotNull
    public final Context getContext() {
        return this.f2793a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    @NotNull
    public final ArrayList<AutoRecognitonModelOneK> getListData() {
        return this.b;
    }

    @NotNull
    public final OnItemClickListener getOnItemClickListener() {
        return this.c;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f2793a = context;
    }

    public final void setListData(@NotNull ArrayList<AutoRecognitonModelOneK> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.b = arrayList;
    }

    public final void setOnItemClickListener(@NotNull OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(onItemClickListener, "<set-?>");
        this.c = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getType_name().setText(this.b.get(i).getActivityName());
        holder.getSwitch().setOnCheckedChangeListener(null);
        holder.getSwitch().setChecked(this.b.get(i).isSelected());
        if (this.b.get(i).getIconUrl() != null) {
            GlideUtils.loadImage(this.f2793a, holder.getIv_aadicon(), this.b.get(i).getIconUrl());
            holder.getIv_aadicon().setVisibility(0);
        } else {
            holder.getIv_aadicon().setVisibility(8);
        }
        holder.getSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.activitymodes.autodetection.adapter.a
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                AutoRecognitionAdapterOneK.b(AutoRecognitionAdapterOneK.this, i, compoundButton, z);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(this.f2793a).inflate(R.layout.auto_recog_item_onek, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context)\n          …item_onek, parent, false)");
        return new ViewHolder(inflate);
    }
}
