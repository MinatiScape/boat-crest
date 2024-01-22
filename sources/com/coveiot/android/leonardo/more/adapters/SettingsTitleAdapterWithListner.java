package com.coveiot.android.leonardo.more.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.adapters.SettingsTitleAdapterWithListner;
import com.coveiot.android.theme.model.SettingsListItemModelWithListener;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SettingsTitleAdapterWithListner extends RecyclerView.Adapter<SettingsTitleHolderWithListener> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public List<SettingsListItemModelWithListener> f5081a;

    /* loaded from: classes5.dex */
    public final class SettingsTitleHolderWithListener extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public final TextView f5082a;
        public final ImageView b;
        public final View c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SettingsTitleHolderWithListener(@NotNull SettingsTitleAdapterWithListner settingsTitleAdapterWithListner, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f5082a = (TextView) itemView.findViewById(R.id.settings_name);
            this.b = (ImageView) itemView.findViewById(R.id.ivRightArrow);
            this.c = itemView.findViewById(R.id.list_item_divider);
        }

        public static final void b(SettingsListItemModelWithListener data, View view) {
            Intrinsics.checkNotNullParameter(data, "$data");
            data.getListener().onItemSelected(data.getName());
        }

        public final void bindView(@NotNull final SettingsListItemModelWithListener data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.b.setBackgroundResource(R.drawable.ic_right_arrow_white);
            this.f5082a.setText(data.getName());
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.b0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SettingsTitleAdapterWithListner.SettingsTitleHolderWithListener.b(SettingsListItemModelWithListener.this, view);
                }
            });
        }

        public final View getDivider() {
            return this.c;
        }
    }

    public SettingsTitleAdapterWithListner(@Nullable List<SettingsListItemModelWithListener> list) {
        this.f5081a = list;
    }

    @Nullable
    public final List<SettingsListItemModelWithListener> getDataList() {
        return this.f5081a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<SettingsListItemModelWithListener> list = this.f5081a;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            return list.size();
        }
        return 0;
    }

    public final void setData(@NotNull List<SettingsListItemModelWithListener> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.f5081a = list;
        notifyDataSetChanged();
    }

    public final void setDataList(@Nullable List<SettingsListItemModelWithListener> list) {
        this.f5081a = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull SettingsTitleHolderWithListener holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (this.f5081a != null) {
            View view = holder.itemView;
            view.setBackgroundColor(view.getContext().getColor(R.color.transparent));
            List<SettingsListItemModelWithListener> list = this.f5081a;
            Intrinsics.checkNotNull(list);
            holder.bindView(list.get(i));
            List<SettingsListItemModelWithListener> list2 = this.f5081a;
            Intrinsics.checkNotNull(list2);
            if (i == list2.size() - 1) {
                holder.getDivider().setVisibility(8);
            } else {
                holder.getDivider().setVisibility(0);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public SettingsTitleHolderWithListener onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_new_design_divider_withouticon, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context)\n   …thouticon, parent, false)");
        return new SettingsTitleHolderWithListener(this, inflate);
    }
}
