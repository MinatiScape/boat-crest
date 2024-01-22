package com.coveiot.android.sportsnotification.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.sportsnotification.R;
import com.coveiot.android.sportsnotification.adapter.SportIntervalAdapter;
import com.coveiot.android.sportsnotification.model.SettingsModel;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SportIntervalAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final List<SettingsModel> f5834a;
    @NotNull
    public final IntervalSelectListener b;
    public int c;
    public boolean d;

    /* loaded from: classes7.dex */
    public interface IntervalSelectListener {
        void onIntervalSelect(int i);
    }

    /* loaded from: classes7.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public RadioButton f5835a;
        public final /* synthetic */ SportIntervalAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull final SportIntervalAdapter sportIntervalAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.b = sportIntervalAdapter;
            View findViewById = view.findViewById(R.id.radio_button);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.radio_button)");
            RadioButton radioButton = (RadioButton) findViewById;
            this.f5835a = radioButton;
            radioButton.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.adapter.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    SportIntervalAdapter.ViewHolder.b(SportIntervalAdapter.this, this, view2);
                }
            });
        }

        public static final void b(SportIntervalAdapter this$0, ViewHolder this$1, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            this$0.c = this$1.getAdapterPosition();
            this$0.notifyDataSetChanged();
            IntervalSelectListener intervalSelectListener = this$0.b;
            Intrinsics.checkNotNull(intervalSelectListener);
            intervalSelectListener.onIntervalSelect(this$0.c);
        }

        @NotNull
        public final RadioButton getSelectionState() {
            return this.f5835a;
        }

        public final void setSelectionState(@NotNull RadioButton radioButton) {
            Intrinsics.checkNotNullParameter(radioButton, "<set-?>");
            this.f5835a = radioButton;
        }
    }

    public SportIntervalAdapter(@NotNull List<SettingsModel> settingList, @NotNull Context context, @NotNull IntervalSelectListener selectListener) {
        Intrinsics.checkNotNullParameter(settingList, "settingList");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(selectListener, "selectListener");
        this.f5834a = settingList;
        this.b = selectListener;
        this.c = -1;
        this.d = true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5834a.size();
    }

    public final void setEnabled(boolean z) {
        this.d = z;
        notifyDataSetChanged();
    }

    public final void updateSetting(@Nullable Integer num) {
        Iterator<SettingsModel> it = this.f5834a.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            SettingsModel next = it.next();
            int value = next.getValue();
            if (num != null && value == num.intValue()) {
                this.c = this.f5834a.indexOf(next);
                break;
            }
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getSelectionState().setText(this.f5834a.get(i).getName());
        holder.getSelectionState().setEnabled(this.d);
        holder.getSelectionState().setChecked(this.c == i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_settings, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ViewHolder(this, view);
    }
}
