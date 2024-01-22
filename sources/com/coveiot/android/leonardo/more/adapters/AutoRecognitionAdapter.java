package com.coveiot.android.leonardo.more.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.models.ActivityTypes;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.models.AutoRecognitonModel;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class AutoRecognitionAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f5044a;
    @NotNull
    public ArrayList<AutoRecognitonModel> b;
    @NotNull
    public OnItemClickListener c;

    /* loaded from: classes5.dex */
    public interface OnItemClickListener {
        void onItemClick(@NotNull ActivityTypes activityTypes, boolean z, int i);
    }

    /* loaded from: classes5.dex */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f5045a;
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
            this.f5045a = textView;
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
            return this.f5045a;
        }
    }

    public AutoRecognitionAdapter(@NotNull Context context, @NotNull ArrayList<AutoRecognitonModel> listData, @NotNull OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listData, "listData");
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        this.f5044a = context;
        this.b = listData;
        this.c = onItemClickListener;
    }

    public static final void b(AutoRecognitionAdapter this$0, int i, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OnItemClickListener onItemClickListener = this$0.c;
        ActivityTypes activityTypes = this$0.b.get(i).getActivityTypes();
        Intrinsics.checkNotNull(activityTypes);
        onItemClickListener.onItemClick(activityTypes, z, i);
    }

    @NotNull
    public final Context getContext() {
        return this.f5044a;
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
    public final ArrayList<AutoRecognitonModel> getListData() {
        return this.b;
    }

    @NotNull
    public final OnItemClickListener getOnItemClickListener() {
        return this.c;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f5044a = context;
    }

    public final void setListData(@NotNull ArrayList<AutoRecognitonModel> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.b = arrayList;
    }

    public final void setOnItemClickListener(@NotNull OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(onItemClickListener, "<set-?>");
        this.c = onItemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, final int i) {
        String name;
        Intrinsics.checkNotNullParameter(holder, "holder");
        TextView type_name = holder.getType_name();
        ActivityTypes activityTypes = this.b.get(i).getActivityTypes();
        type_name.setText((activityTypes == null || (name = activityTypes.name()) == null) ? null : kotlin.text.m.capitalize(name));
        holder.getSwitch().setOnCheckedChangeListener(null);
        holder.getSwitch().setChecked(this.b.get(i).isSelected());
        if (this.b.get(i).getImageResource() != null) {
            ImageView iv_aadicon = holder.getIv_aadicon();
            Integer imageResource = this.b.get(i).getImageResource();
            Intrinsics.checkNotNull(imageResource);
            iv_aadicon.setImageResource(imageResource.intValue());
            holder.getIv_aadicon().setVisibility(0);
        } else {
            holder.getIv_aadicon().setVisibility(8);
        }
        holder.getSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.adapters.h
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                AutoRecognitionAdapter.b(AutoRecognitionAdapter.this, i, compoundButton, z);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(this.f5044a).inflate(R.layout.auto_recog_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context)\n          …ecog_item, parent, false)");
        return new ViewHolder(inflate);
    }
}
