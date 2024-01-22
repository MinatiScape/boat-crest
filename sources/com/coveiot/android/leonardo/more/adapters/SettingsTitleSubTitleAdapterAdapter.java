package com.coveiot.android.leonardo.more.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.adapters.SettingsTitleSubTitleAdapterAdapter;
import com.coveiot.android.theme.model.SettingsListItemModel;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SettingsTitleSubTitleAdapterAdapter extends RecyclerView.Adapter<SettingsTitleSubTitleHolder> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public List<SettingsListItemModel> f5083a;

    /* loaded from: classes5.dex */
    public final class SettingsTitleSubTitleHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public final TextView f5084a;
        public final TextView b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SettingsTitleSubTitleHolder(@NotNull SettingsTitleSubTitleAdapterAdapter settingsTitleSubTitleAdapterAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f5084a = (TextView) itemView.findViewById(R.id.title);
            this.b = (TextView) itemView.findViewById(R.id.sub_title);
        }

        public static final void b(SettingsTitleSubTitleHolder this$0, SettingsListItemModel data, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(data, "$data");
            this$0.itemView.getContext().startActivity(new Intent(this$0.itemView.getContext(), data.getNavigationClass()));
        }

        public final void bindView(@NotNull final SettingsListItemModel data) {
            Intrinsics.checkNotNullParameter(data, "data");
            if (data.getAdditionalText() != null) {
                this.b.setVisibility(0);
                this.b.setText(data.getAdditionalText());
            } else {
                this.b.setVisibility(8);
            }
            this.f5084a.setText(data.getName());
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.c0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SettingsTitleSubTitleAdapterAdapter.SettingsTitleSubTitleHolder.b(SettingsTitleSubTitleAdapterAdapter.SettingsTitleSubTitleHolder.this, data, view);
                }
            });
            if (data.getAdditionalTextColor() != null) {
                TextView textView = this.b;
                Integer additionalTextColor = data.getAdditionalTextColor();
                Intrinsics.checkNotNull(additionalTextColor);
                textView.setTextColor(additionalTextColor.intValue());
            }
        }
    }

    public SettingsTitleSubTitleAdapterAdapter(@Nullable List<SettingsListItemModel> list) {
        this.f5083a = list;
    }

    @Nullable
    public final List<SettingsListItemModel> getDataList() {
        return this.f5083a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<SettingsListItemModel> list = this.f5083a;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            return list.size();
        }
        return 0;
    }

    public final void setData(@NotNull List<SettingsListItemModel> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.f5083a = list;
        notifyDataSetChanged();
    }

    public final void setDataList(@Nullable List<SettingsListItemModel> list) {
        this.f5083a = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull SettingsTitleSubTitleHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        List<SettingsListItemModel> list = this.f5083a;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            holder.bindView(list.get(i));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public SettingsTitleSubTitleHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_new_design_title_sub_title, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context)\n   …sub_title, parent, false)");
        return new SettingsTitleSubTitleHolder(this, inflate);
    }
}
