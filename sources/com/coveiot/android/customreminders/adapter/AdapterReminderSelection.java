package com.coveiot.android.customreminders.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.customreminders.R;
import com.coveiot.android.customreminders.ReminderType;
import com.coveiot.android.customreminders.model.ReminderTypeModel;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class AdapterReminderSelection extends RecyclerView.Adapter<ReminderSelectionViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ReminderTypeSelectionListener f4137a;
    @NotNull
    public ArrayList<ReminderTypeModel> b;

    /* loaded from: classes3.dex */
    public final class ReminderSelectionViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f4138a;
        public final TextView b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ReminderSelectionViewHolder(@NotNull AdapterReminderSelection adapterReminderSelection, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f4138a = (ImageView) itemView.findViewById(R.id.settings_icon);
            this.b = (TextView) itemView.findViewById(R.id.settings_name);
        }

        public final TextView getSettingName() {
            return this.b;
        }

        public final ImageView getSettingsIcon() {
            return this.f4138a;
        }
    }

    /* loaded from: classes3.dex */
    public interface ReminderTypeSelectionListener {
        void onReminderTypeSelected(@NotNull ReminderType reminderType);
    }

    public AdapterReminderSelection(@NotNull ReminderTypeSelectionListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f4137a = listener;
        this.b = new ArrayList<>();
    }

    public static final void b(AdapterReminderSelection this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.f4137a.onReminderTypeSelected(this$0.b.get(i).getReminderType());
    }

    public final void addReminderType(@NotNull ReminderTypeModel reminderTypeModel) {
        Intrinsics.checkNotNullParameter(reminderTypeModel, "reminderTypeModel");
        this.b.add(reminderTypeModel);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @NotNull
    public final ReminderTypeSelectionListener getListener() {
        return this.f4137a;
    }

    @NotNull
    public final ArrayList<ReminderTypeModel> getReminderTypeList() {
        return this.b;
    }

    public final void setReminderTypeList(@NotNull ArrayList<ReminderTypeModel> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.b = arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ReminderSelectionViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getSettingName().setText(this.b.get(i).getName());
        holder.getSettingsIcon().setImageResource(this.b.get(i).getImageResource());
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.customreminders.adapter.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdapterReminderSelection.b(AdapterReminderSelection.this, i, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ReminderSelectionViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_new_design_without_icon_padding, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context)\n   …n_padding, parent, false)");
        return new ReminderSelectionViewHolder(this, inflate);
    }
}
