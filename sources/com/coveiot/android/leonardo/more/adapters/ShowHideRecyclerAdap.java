package com.coveiot.android.leonardo.more.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.model.ShowHideTypeModel;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class ShowHideRecyclerAdap extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f5087a;
    @NotNull
    public ArrayList<ShowHideTypeModel> b;
    @NotNull
    public OnItemClickListenerHideShow c;

    /* loaded from: classes5.dex */
    public interface OnItemClickListenerHideShow {
        void onHideShow(@NotNull ShowHideTypeModel showHideTypeModel, int i);
    }

    /* loaded from: classes5.dex */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f5088a;
        @NotNull
        public final ImageView b;
        @NotNull
        public final ConstraintLayout c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            TextView textView = (TextView) itemView.findViewById(R.id.type_name);
            Intrinsics.checkNotNullExpressionValue(textView, "itemView.type_name");
            this.f5088a = textView;
            ImageView imageView = (ImageView) itemView.findViewById(R.id.hide_show_icon);
            Intrinsics.checkNotNullExpressionValue(imageView, "itemView.hide_show_icon");
            this.b = imageView;
            ConstraintLayout constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.layout_constraint);
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "itemView.layout_constraint");
            this.c = constraintLayout;
        }

        @NotNull
        public final ConstraintLayout getCard() {
            return this.c;
        }

        @NotNull
        public final ImageView getHide_show_icon() {
            return this.b;
        }

        @NotNull
        public final TextView getType_name() {
            return this.f5088a;
        }
    }

    public ShowHideRecyclerAdap(@NotNull Context context, @NotNull ArrayList<ShowHideTypeModel> listData, @NotNull OnItemClickListenerHideShow onItemClickListenerHideShow) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listData, "listData");
        Intrinsics.checkNotNullParameter(onItemClickListenerHideShow, "onItemClickListenerHideShow");
        this.f5087a = context;
        this.b = listData;
        this.c = onItemClickListenerHideShow;
    }

    public static final void b(ShowHideRecyclerAdap this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (kotlin.text.m.equals$default(this$0.b.get(i).getShowOrHideText(), this$0.f5087a.getResources().getString(R.string.show), false, 2, null)) {
            this$0.c.onHideShow(new ShowHideTypeModel(this$0.b.get(i).getTypeName(), Integer.valueOf((int) R.drawable.hide_icon), this$0.f5087a.getResources().getString(R.string.hide)), i);
        } else {
            this$0.c.onHideShow(new ShowHideTypeModel(this$0.b.get(i).getTypeName(), Integer.valueOf((int) R.drawable.show_icon), this$0.f5087a.getResources().getString(R.string.show)), i);
        }
    }

    @NotNull
    public final Context getContext() {
        return this.f5087a;
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
    public final ArrayList<ShowHideTypeModel> getListData() {
        return this.b;
    }

    @NotNull
    public final OnItemClickListenerHideShow getOnItemClickListenerHideShow() {
        return this.c;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f5087a = context;
    }

    public final void setListData(@NotNull ArrayList<ShowHideTypeModel> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.b = arrayList;
    }

    public final void setOnItemClickListenerHideShow(@NotNull OnItemClickListenerHideShow onItemClickListenerHideShow) {
        Intrinsics.checkNotNullParameter(onItemClickListenerHideShow, "<set-?>");
        this.c = onItemClickListenerHideShow;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getType_name().setText(this.b.get(i).getTypeName());
        ImageView hide_show_icon = holder.getHide_show_icon();
        Integer hideShowIcon = this.b.get(i).getHideShowIcon();
        Intrinsics.checkNotNull(hideShowIcon);
        hide_show_icon.setImageResource(hideShowIcon.intValue());
        holder.getCard().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.h0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShowHideRecyclerAdap.b(ShowHideRecyclerAdap.this, i, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(this.f5087a).inflate(R.layout.item_sports_type, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(context)\n          …sports_type,parent,false)");
        return new ViewHolder(inflate);
    }
}
