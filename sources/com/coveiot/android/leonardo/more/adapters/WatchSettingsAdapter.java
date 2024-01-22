package com.coveiot.android.leonardo.more.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.adapters.WatchSettingsAdapter;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.WatchDisconnectedStatusListener;
import com.coveiot.android.theme.model.SettingsListItemModel;
import com.coveiot.android.theme.utils.ThemesUtils;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class WatchSettingsAdapter extends RecyclerView.Adapter<WatchSettingsHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f5097a;
    @Nullable
    public List<SettingsListItemModel> b;
    @NotNull
    public final GuestUserListener c;
    @Nullable
    public WatchDisconnectedStatusListener d;
    @Nullable
    public BottomSheetDialogTwoButtons e;

    /* loaded from: classes5.dex */
    public interface GuestUserListener {
        void isGuestUser();
    }

    /* loaded from: classes5.dex */
    public final class WatchSettingsHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public final TextView f5098a;
        public final ImageView b;
        public final View c;
        public final /* synthetic */ WatchSettingsAdapter d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WatchSettingsHolder(@NotNull WatchSettingsAdapter watchSettingsAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.d = watchSettingsAdapter;
            this.f5098a = (TextView) itemView.findViewById(R.id.settings_name);
            this.b = (ImageView) itemView.findViewById(R.id.settings_icon);
            this.c = itemView.findViewById(R.id.list_item_divider);
        }

        public static final void b(SettingsListItemModel data, WatchSettingsAdapter this$0, WatchSettingsHolder this$1, View view) {
            WatchDisconnectedStatusListener watchDisconnectedStatusListner;
            Intrinsics.checkNotNullParameter(data, "$data");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            if (data.getShouldCheckForConnectionStatus()) {
                ThemesUtils themesUtils = ThemesUtils.INSTANCE;
                if (!themesUtils.isGuestUser(this$0.getContext()) && !themesUtils.isPairDeviceLater(this$0.getContext())) {
                    if (BleApiManager.getInstance(this$1.itemView.getContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                        this$1.itemView.getContext().startActivity(new Intent(this$1.itemView.getContext(), data.getNavigationClass()));
                        return;
                    } else if (this$0.getWatchDisconnectedStatusListner() == null || (watchDisconnectedStatusListner = this$0.getWatchDisconnectedStatusListner()) == null) {
                        return;
                    } else {
                        watchDisconnectedStatusListner.onWatchDisconnected();
                        return;
                    }
                }
                this$1.itemView.getContext().startActivity(new Intent(this$1.itemView.getContext(), data.getNavigationClass()));
                return;
            }
            ThemesUtils themesUtils2 = ThemesUtils.INSTANCE;
            if (!themesUtils2.isGuestUser(this$0.getContext()) && !themesUtils2.isPairDeviceLater(this$0.getContext())) {
                this$1.itemView.getContext().startActivity(new Intent(this$1.itemView.getContext(), data.getNavigationClass()));
            } else {
                this$0.getListener().isGuestUser();
            }
        }

        public final void bindView(@NotNull final SettingsListItemModel data, boolean z) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.b.setImageResource(data.getIconResource());
            this.f5098a.setText(data.getName());
            View view = this.itemView;
            final WatchSettingsAdapter watchSettingsAdapter = this.d;
            view.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.n0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    WatchSettingsAdapter.WatchSettingsHolder.b(SettingsListItemModel.this, watchSettingsAdapter, this, view2);
                }
            });
            if (z) {
                this.c.setVisibility(8);
            } else {
                this.c.setVisibility(0);
            }
        }
    }

    public WatchSettingsAdapter(@NotNull Context context, @Nullable List<SettingsListItemModel> list, @NotNull GuestUserListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f5097a = context;
        this.b = list;
        this.c = listener;
    }

    @NotNull
    public final Context getContext() {
        return this.f5097a;
    }

    @Nullable
    public final List<SettingsListItemModel> getDataList() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<SettingsListItemModel> list = this.b;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            return list.size();
        }
        return 0;
    }

    @NotNull
    public final GuestUserListener getListener() {
        return this.c;
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getWatchDisconnectedDialog() {
        return this.e;
    }

    @Nullable
    public final WatchDisconnectedStatusListener getWatchDisconnectedStatusListner() {
        return this.d;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f5097a = context;
    }

    public final void setData(@NotNull List<SettingsListItemModel> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.b = list;
        notifyDataSetChanged();
    }

    public final void setDataList(@Nullable List<SettingsListItemModel> list) {
        this.b = list;
    }

    public final void setWatchDisconnectedDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.e = bottomSheetDialogTwoButtons;
    }

    public final void setWatchDisconnectedStatusListner(@Nullable WatchDisconnectedStatusListener watchDisconnectedStatusListener) {
        this.d = watchDisconnectedStatusListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull WatchSettingsHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        List<SettingsListItemModel> list = this.b;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            SettingsListItemModel settingsListItemModel = list.get(i);
            List<SettingsListItemModel> list2 = this.b;
            Intrinsics.checkNotNull(list2);
            holder.bindView(settingsListItemModel, CollectionsKt__CollectionsKt.getLastIndex(list2) == i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public WatchSettingsHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_new_design_divider, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context)\n   …n_divider, parent, false)");
        return new WatchSettingsHolder(this, inflate);
    }
}
