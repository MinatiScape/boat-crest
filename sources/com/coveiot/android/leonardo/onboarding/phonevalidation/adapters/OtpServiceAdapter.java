package com.coveiot.android.leonardo.onboarding.phonevalidation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.constant.PermissionConstants;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ListItemOtpServiceTypeLayoutBinding;
import com.coveiot.android.leonardo.onboarding.phonevalidation.adapters.OtpServiceAdapter;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.coveaccess.model.server.GetOTPServicesRes;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class OtpServiceAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5281a;
    @NotNull
    public final ServiceTypeClickListener b;
    @NotNull
    public List<? extends GetOTPServicesRes.ServiceType> c;
    public int d;
    public int e;
    public int f;
    public boolean g;

    /* loaded from: classes5.dex */
    public interface ServiceTypeClickListener {
        void typeClick(@NotNull GetOTPServicesRes.ServiceType serviceType, int i);
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListItemOtpServiceTypeLayoutBinding f5282a;
        public final /* synthetic */ OtpServiceAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull OtpServiceAdapter otpServiceAdapter, ListItemOtpServiceTypeLayoutBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = otpServiceAdapter;
            this.f5282a = binding;
        }

        public static final void b(OtpServiceAdapter this$0, ViewHolder this$1, GetOTPServicesRes.ServiceType serviceData, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            Intrinsics.checkNotNullParameter(serviceData, "$serviceData");
            this$0.e = this$0.d;
            this$0.d = this$1.getAbsoluteAdapterPosition();
            this$0.notifyItemChanged(this$0.e);
            this$0.notifyItemChanged(this$0.d);
            this$0.getListener().typeClick(serviceData, this$0.d);
        }

        public final void bind(@NotNull final GetOTPServicesRes.ServiceType serviceData) {
            Intrinsics.checkNotNullParameter(serviceData, "serviceData");
            ListItemOtpServiceTypeLayoutBinding listItemOtpServiceTypeLayoutBinding = this.f5282a;
            OtpServiceAdapter otpServiceAdapter = this.b;
            listItemOtpServiceTypeLayoutBinding.tvServiceType.setText(serviceData.getName());
            if (otpServiceAdapter.g) {
                TextView tvServiceType = listItemOtpServiceTypeLayoutBinding.tvServiceType;
                Intrinsics.checkNotNullExpressionValue(tvServiceType, "tvServiceType");
                ViewUtilsKt.visible(tvServiceType);
            } else if (!Intrinsics.areEqual(serviceData.getType(), PermissionConstants.SMS)) {
                TextView tvServiceType2 = listItemOtpServiceTypeLayoutBinding.tvServiceType;
                Intrinsics.checkNotNullExpressionValue(tvServiceType2, "tvServiceType");
                ViewUtilsKt.gone(tvServiceType2);
            }
            if (otpServiceAdapter.d == getAbsoluteAdapterPosition() && otpServiceAdapter.f >= 0) {
                serviceData.setAttemptsRemaining(Integer.valueOf(otpServiceAdapter.f));
            }
            Integer attemptsRemaining = serviceData.getAttemptsRemaining();
            if (attemptsRemaining != null && attemptsRemaining.intValue() == 0) {
                TextView textView = listItemOtpServiceTypeLayoutBinding.tvServiceType;
                textView.setBackgroundResource(R.drawable.disable_button_background_new);
                textView.setTextColor(ContextCompat.getColor(textView.getContext(), R.color.color_666666));
                textView.setTypeface(listItemOtpServiceTypeLayoutBinding.tvServiceType.getTypeface(), 0);
            } else {
                TextView textView2 = listItemOtpServiceTypeLayoutBinding.tvServiceType;
                textView2.setBackgroundResource(R.drawable.rounded_grey_with_border_background_40dp_symptoms);
                textView2.setTextColor(ContextCompat.getColor(textView2.getContext(), R.color.color_999999));
                textView2.setTypeface(listItemOtpServiceTypeLayoutBinding.tvServiceType.getTypeface(), 0);
            }
            View view = this.itemView;
            final OtpServiceAdapter otpServiceAdapter2 = this.b;
            view.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.phonevalidation.adapters.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    OtpServiceAdapter.ViewHolder.b(OtpServiceAdapter.this, this, serviceData, view2);
                }
            });
        }
    }

    public OtpServiceAdapter(@NotNull Context context, @NotNull ServiceTypeClickListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f5281a = context;
        this.b = listener;
        this.c = new ArrayList();
        this.d = -1;
        this.e = -1;
        this.f = -1;
    }

    @NotNull
    public final Context getContext() {
        return this.f5281a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    @NotNull
    public final ServiceTypeClickListener getListener() {
        return this.b;
    }

    public final void setDefaultSelection(boolean z) {
    }

    public final void setServiceList(@NotNull List<? extends GetOTPServicesRes.ServiceType> serviceData) {
        Intrinsics.checkNotNullParameter(serviceData, "serviceData");
        this.c = serviceData;
    }

    public final void setUpdateServiceData(int i) {
        this.f = i;
    }

    public final void showRemainingServices() {
        this.g = true;
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
        ListItemOtpServiceTypeLayoutBinding inflate = ListItemOtpServiceTypeLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
