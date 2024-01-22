package com.coveiot.android.customreminders.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.customreminders.R;
import com.coveiot.android.customreminders.listeners.EditReminderListListener;
import com.coveiot.android.customreminders.model.MainReminderListDataHolder;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class AdapterReminderListMain extends RecyclerView.Adapter<ReminderListHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final EditReminderListListener f4135a;
    @Nullable
    public ArrayList<MainReminderListDataHolder> b;

    /* loaded from: classes3.dex */
    public final class ReminderListHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f4136a;
        @NotNull
        public final ImageView b;
        @NotNull
        public final RecyclerView c;
        public final /* synthetic */ AdapterReminderListMain d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ReminderListHolder(@NotNull AdapterReminderListMain adapterReminderListMain, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.d = adapterReminderListMain;
            View findViewById = itemView.findViewById(R.id.reminderTypeName);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById<Te…w>(R.id.reminderTypeName)");
            this.f4136a = (TextView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.reminderIcon);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById<Im…eView>(R.id.reminderIcon)");
            this.b = (ImageView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.rcv_reminder_list2);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById<Re…(R.id.rcv_reminder_list2)");
            this.c = (RecyclerView) findViewById3;
        }

        public final void bind(@NotNull MainReminderListDataHolder mainReminderListDataHolder) {
            Intrinsics.checkNotNullParameter(mainReminderListDataHolder, "mainReminderListDataHolder");
            this.f4136a.setText(mainReminderListDataHolder.getReminderTypeName());
            this.b.setImageResource(mainReminderListDataHolder.getReminderIcon());
            this.c.setLayoutManager(new LinearLayoutManager(this.itemView.getContext()));
            Context context = this.itemView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "itemView.context");
            AdapterReminderSubListMain adapterReminderSubListMain = new AdapterReminderSubListMain(context, this.d.getListener());
            adapterReminderSubListMain.setReminderList(mainReminderListDataHolder.getCustomReminderDataHolderList());
            this.c.setAdapter(adapterReminderSubListMain);
        }

        @NotNull
        public final RecyclerView getRcvReminderList() {
            return this.c;
        }

        @NotNull
        public final ImageView getReminderIcon() {
            return this.b;
        }

        @NotNull
        public final TextView getReminderTypeName() {
            return this.f4136a;
        }
    }

    public AdapterReminderListMain(@NotNull EditReminderListListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f4135a = listener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<MainReminderListDataHolder> arrayList = this.b;
        if (arrayList != null) {
            Intrinsics.checkNotNull(arrayList);
            return arrayList.size();
        }
        return 0;
    }

    @NotNull
    public final EditReminderListListener getListener() {
        return this.f4135a;
    }

    @Nullable
    public final ArrayList<MainReminderListDataHolder> getMainReminderListDataHolder() {
        return this.b;
    }

    public final void setMainReminderListDataHolder(@Nullable ArrayList<MainReminderListDataHolder> arrayList) {
        this.b = arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ReminderListHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ArrayList<MainReminderListDataHolder> arrayList = this.b;
        MainReminderListDataHolder mainReminderListDataHolder = arrayList != null ? arrayList.get(i) : null;
        Intrinsics.checkNotNull(mainReminderListDataHolder);
        holder.bind(mainReminderListDataHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ReminderListHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_list_item_main, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context)\n   …item_main, parent, false)");
        return new ReminderListHolder(this, inflate);
    }
}
