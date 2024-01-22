package com.coveiot.android.qrtray.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.qrtray.adapter.AddedQRTrayAdapter;
import com.coveiot.android.qrtray.databinding.ListItemAddedQrTrayLayoutBinding;
import com.coveiot.android.qrtray.model.QRCodeDataApp;
import com.coveiot.android.theme.CleverTapConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class AddedQRTrayAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5566a;
    @NotNull
    public final QrMenuClickListener b;
    @NotNull
    public List<QRCodeDataApp> c;
    public int d;

    /* loaded from: classes5.dex */
    public interface QrMenuClickListener {

        /* loaded from: classes5.dex */
        public static final class DefaultImpls {
            public static /* synthetic */ void menuClick$default(QrMenuClickListener qrMenuClickListener, QRCodeDataApp qRCodeDataApp, boolean z, int i, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: menuClick");
                }
                if ((i & 2) != 0) {
                    z = false;
                }
                qrMenuClickListener.menuClick(qRCodeDataApp, z);
            }
        }

        void menuClick(@NotNull QRCodeDataApp qRCodeDataApp, boolean z);
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListItemAddedQrTrayLayoutBinding f5567a;
        public final /* synthetic */ AddedQRTrayAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull AddedQRTrayAdapter addedQRTrayAdapter, ListItemAddedQrTrayLayoutBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = addedQRTrayAdapter;
            this.f5567a = binding;
        }

        public static final void c(AddedQRTrayAdapter this$0, QRCodeDataApp qrData, ViewHolder this$1, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(qrData, "$qrData");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            HashMap<String, Object> hashMap = new HashMap<>();
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            hashMap.putAll(companion.getDeviceId(this$0.getContext()));
            hashMap.putAll(companion.getWatchDetails(this$0.getContext()));
            String value = CleverTapConstants.CustomEventProperties.QR_NAME.getValue();
            String imageTitle = qrData.getImageTitle();
            Intrinsics.checkNotNull(imageTitle);
            hashMap.put(value, imageTitle);
            String value2 = CleverTapConstants.CustomEventProperties.QR_TAG.getValue();
            String imageTag = qrData.getImageTag();
            Intrinsics.checkNotNull(imageTag);
            hashMap.put(value2, imageTag);
            companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_MENU_TAPPED.getValue(), hashMap);
            this$0.d = this$1.getAbsoluteAdapterPosition();
            QrMenuClickListener.DefaultImpls.menuClick$default(this$0.getListener(), qrData, false, 2, null);
        }

        public static final void d(AddedQRTrayAdapter this$0, QRCodeDataApp qrData, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(qrData, "$qrData");
            this$0.getListener().menuClick(qrData, true);
        }

        @SuppressLint({"SetTextI18n"})
        public final void bind(@NotNull final QRCodeDataApp qrData) {
            Intrinsics.checkNotNullParameter(qrData, "qrData");
            ListItemAddedQrTrayLayoutBinding listItemAddedQrTrayLayoutBinding = this.f5567a;
            final AddedQRTrayAdapter addedQRTrayAdapter = this.b;
            listItemAddedQrTrayLayoutBinding.setQrCodeData(qrData);
            listItemAddedQrTrayLayoutBinding.ivMenu.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.qrtray.adapter.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AddedQRTrayAdapter.ViewHolder.c(AddedQRTrayAdapter.this, qrData, this, view);
                }
            });
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.qrtray.adapter.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AddedQRTrayAdapter.ViewHolder.d(AddedQRTrayAdapter.this, qrData, view);
                }
            });
        }
    }

    public AddedQRTrayAdapter(@NotNull Context context, @NotNull QrMenuClickListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f5566a = context;
        this.b = listener;
        this.c = new ArrayList();
        this.d = -1;
    }

    @NotNull
    public final Context getContext() {
        return this.f5566a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    @NotNull
    public final QrMenuClickListener getListener() {
        return this.b;
    }

    public final void setEdited() {
        int i = this.d;
        if (i != -1) {
            this.c.get(i).setAppliedToWatch(true);
            notifyItemChanged(this.d);
            this.d = -1;
        }
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setQrCodeList(@NotNull List<QRCodeDataApp> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.c = TypeIntrinsics.asMutableList(list);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.c.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ListItemAddedQrTrayLayoutBinding inflate = ListItemAddedQrTrayLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
