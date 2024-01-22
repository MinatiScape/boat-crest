package com.coveiot.android.leonardo.more.adapters;

import android.content.res.ColorStateList;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.SupportedDeviceFeatureUtils;
import com.coveiot.android.theme.ItemClickListenerNew;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SettingsSearchAdapterWithListner extends RecyclerView.Adapter<SettingsTitleHolderWithListener> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public List<SupportedDeviceFeatureUtils.Feature> f5079a;
    @NotNull
    public String b = "";
    @Nullable
    public ItemClickListenerNew c;

    /* loaded from: classes5.dex */
    public final class SettingsTitleHolderWithListener extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public final TextView f5080a;
        public final View b;
        public final /* synthetic */ SettingsSearchAdapterWithListner c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SettingsTitleHolderWithListener(@NotNull SettingsSearchAdapterWithListner settingsSearchAdapterWithListner, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.c = settingsSearchAdapterWithListner;
            this.f5080a = (TextView) itemView.findViewById(R.id.settings_name);
            this.b = itemView.findViewById(R.id.list_item_divider);
        }

        public final void bindView(@NotNull SupportedDeviceFeatureUtils.Feature data) {
            Intrinsics.checkNotNullParameter(data, "data");
            if (this.c.getHighlightText().length() > 0) {
                String name = data.getName();
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
                String lowerCase = name.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                String highlightText = this.c.getHighlightText();
                Locale locale2 = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale2, "getDefault()");
                String lowerCase2 = highlightText.toLowerCase(locale2);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                int indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) lowerCase, lowerCase2, 0, false, 6, (Object) null);
                int length = this.c.getHighlightText().length() + indexOf$default;
                if (indexOf$default != -1) {
                    SpannableString spannableString = new SpannableString(data.getName());
                    spannableString.setSpan(new TextAppearanceSpan(null, 1, -1, new ColorStateList(new int[][]{new int[0]}, new int[]{-1}), null), indexOf$default, length, 33);
                    this.f5080a.setText(spannableString);
                    return;
                }
                this.f5080a.setText(data.getName());
                return;
            }
            this.f5080a.setText(data.getName());
        }

        public final View getDivider() {
            return this.b;
        }
    }

    public SettingsSearchAdapterWithListner(@Nullable List<SupportedDeviceFeatureUtils.Feature> list) {
        this.f5079a = list;
    }

    public static final void b(SettingsSearchAdapterWithListner this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ItemClickListenerNew itemClickListenerNew = this$0.c;
        if (itemClickListenerNew == null || itemClickListenerNew == null) {
            return;
        }
        List<SupportedDeviceFeatureUtils.Feature> list = this$0.f5079a;
        Intrinsics.checkNotNull(list);
        itemClickListenerNew.onItemSelected(list.get(i).getName());
    }

    @Nullable
    public final List<SupportedDeviceFeatureUtils.Feature> getDataList() {
        return this.f5079a;
    }

    @NotNull
    public final String getHighlightText() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<SupportedDeviceFeatureUtils.Feature> list = this.f5079a;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            return list.size();
        }
        return 0;
    }

    @Nullable
    public final ItemClickListenerNew getListner() {
        return this.c;
    }

    public final void setData(@Nullable List<SupportedDeviceFeatureUtils.Feature> list, @NotNull String searchedText) {
        Intrinsics.checkNotNullParameter(searchedText, "searchedText");
        this.f5079a = list;
        this.b = searchedText;
        notifyDataSetChanged();
    }

    public final void setDataList(@Nullable List<SupportedDeviceFeatureUtils.Feature> list) {
        this.f5079a = list;
    }

    public final void setHighlightText(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.b = str;
    }

    public final void setListner(@Nullable ItemClickListenerNew itemClickListenerNew) {
        this.c = itemClickListenerNew;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull SettingsTitleHolderWithListener holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (this.f5079a != null) {
            View view = holder.itemView;
            view.setBackgroundColor(view.getContext().getColor(R.color.transparent));
            List<SupportedDeviceFeatureUtils.Feature> list = this.f5079a;
            Intrinsics.checkNotNull(list);
            holder.bindView(list.get(i));
            List<SupportedDeviceFeatureUtils.Feature> list2 = this.f5079a;
            Intrinsics.checkNotNull(list2);
            if (i == list2.size() - 1) {
                holder.getDivider().setVisibility(8);
            } else {
                holder.getDivider().setVisibility(0);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.a0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    SettingsSearchAdapterWithListner.b(SettingsSearchAdapterWithListner.this, i, view2);
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public SettingsTitleHolderWithListener onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_search_list_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context)\n   …list_item, parent, false)");
        return new SettingsTitleHolderWithListener(this, inflate);
    }
}
