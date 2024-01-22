package com.coveiot.android.dashboard2.adapter;

import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.dashboard2.customui.DragListener;
import com.coveiot.android.dashboard2.customui.Listener;
import com.coveiot.android.dashboard2.model.FitnessVitalsDataModel;
import com.coveiot.android.dashboard2.util.FitnessVitalsHelper;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class EditHomeDashboardVitalListAdapter extends RecyclerView.Adapter<ViewHolder> implements View.OnTouchListener {
    @NotNull
    public final Context h;
    @NotNull
    public List<FitnessVitalsDataModel> i;
    @NotNull
    public Listener j;

    /* loaded from: classes4.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f4222a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final ImageView d;
        @NotNull
        public final ConstraintLayout e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull EditHomeDashboardVitalListAdapter editHomeDashboardVitalListAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.ivVitalImage);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.ivVitalImage)");
            this.f4222a = (ImageView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.tvVitalName);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.tvVitalName)");
            this.b = (TextView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.tvInfo);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.tvInfo)");
            this.c = (TextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.ivHamburger);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.ivHamburger)");
            this.d = (ImageView) findViewById4;
            View findViewById5 = itemView.findViewById(R.id.clItem);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "itemView.findViewById(R.id.clItem)");
            this.e = (ConstraintLayout) findViewById5;
        }

        @NotNull
        public final ConstraintLayout getClItem() {
            return this.e;
        }

        @NotNull
        public final ImageView getIvHamburger() {
            return this.d;
        }

        @NotNull
        public final ImageView getIvVitalImage() {
            return this.f4222a;
        }

        @NotNull
        public final TextView getTvInfo() {
            return this.c;
        }

        @NotNull
        public final TextView getTvVitalName() {
            return this.b;
        }
    }

    public EditHomeDashboardVitalListAdapter(@NotNull Context mContext, @NotNull List<FitnessVitalsDataModel> fitnessVitalsList, @NotNull Listener listener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(fitnessVitalsList, "fitnessVitalsList");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.h = mContext;
        this.i = fitnessVitalsList;
        this.j = listener;
    }

    @Nullable
    public final DragListener getDragInstance() {
        Listener listener = this.j;
        if (listener != null) {
            return new DragListener(listener);
        }
        Log.e("ListAdapter", "Listener wasn't initialized!");
        return null;
    }

    @NotNull
    public final List<FitnessVitalsDataModel> getFitnessVitalsList() {
        return this.i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<FitnessVitalsDataModel> list = this.i;
        Intrinsics.checkNotNull(list);
        return list.size();
    }

    @NotNull
    public final List<FitnessVitalsDataModel> getList() {
        return this.i;
    }

    @NotNull
    public final Listener getListener() {
        return this.j;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(@NotNull View v, @NotNull MotionEvent event) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getAction() == 0) {
            ClipData newPlainText = ClipData.newPlainText("", "");
            View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
            if (Build.VERSION.SDK_INT >= 24) {
                v.startDragAndDrop(newPlainText, dragShadowBuilder, v, 0);
                return true;
            }
            v.startDrag(newPlainText, dragShadowBuilder, v, 0);
            return true;
        }
        return false;
    }

    public final void setFitnessVitalsList(@NotNull List<FitnessVitalsDataModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.i = list;
    }

    public final void setList(@NotNull List<FitnessVitalsDataModel> fitnessVitalsList) {
        Intrinsics.checkNotNullParameter(fitnessVitalsList, "fitnessVitalsList");
        this.i = fitnessVitalsList;
        notifyDataSetChanged();
    }

    public final void setListener(@NotNull Listener listener) {
        Intrinsics.checkNotNullParameter(listener, "<set-?>");
        this.j = listener;
    }

    public final void updateList(@NotNull List<FitnessVitalsDataModel> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.i = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        List<FitnessVitalsDataModel> list = this.i;
        FitnessVitalsDataModel fitnessVitalsDataModel = list != null ? list.get(i) : null;
        if (fitnessVitalsDataModel != null) {
            ImageView ivVitalImage = holder.getIvVitalImage();
            FitnessVitalsHelper fitnessVitalsHelper = FitnessVitalsHelper.INSTANCE;
            ivVitalImage.setImageDrawable(fitnessVitalsHelper.getFitnessVitalDrawable(this.h, fitnessVitalsDataModel.getFitnessVitalType()));
            holder.getTvVitalName().setText(fitnessVitalsHelper.getFitnessVitalTitleForEditScreen(this.h, fitnessVitalsDataModel.getFitnessVitalType()));
            holder.getTvInfo().setText(fitnessVitalsHelper.getFitnessVitalInfoTextForEditScreen(this.h, fitnessVitalsDataModel.getFitnessVitalType()));
            holder.getClItem().setTag(Integer.valueOf(i));
            holder.getClItem().setOnTouchListener(this);
            holder.getClItem().setOnDragListener(new DragListener(this.j));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(this.h).inflate(R.layout.item_edit_home_dashboard_vitals, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(mContext)\n         …rd_vitals, parent, false)");
        return new ViewHolder(this, inflate);
    }
}
