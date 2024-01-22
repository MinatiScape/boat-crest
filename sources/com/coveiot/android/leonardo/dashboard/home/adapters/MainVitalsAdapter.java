package com.coveiot.android.leonardo.dashboard.home.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.home.adapters.MainVitalsAdapter;
import com.coveiot.android.leonardo.dashboard.model.VitalsModel;
import com.coveiot.android.theme.databinding.ListItemVitalsLayoutBinding;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class MainVitalsAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f4779a;
    @NotNull
    public VitalsClickListener b;
    @NotNull
    public List<VitalsModel> c;
    public int d;

    /* loaded from: classes3.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListItemVitalsLayoutBinding f4780a;
        public final /* synthetic */ MainVitalsAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull MainVitalsAdapter mainVitalsAdapter, ListItemVitalsLayoutBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = mainVitalsAdapter;
            this.f4780a = binding;
        }

        public static final void b(MainVitalsAdapter this$0, VitalsModel vital, ViewHolder this$1, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(vital, "$vital");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            this$0.getVitalsClickListener().selectedVital(vital, this$1.getAbsoluteAdapterPosition());
        }

        @SuppressLint({"SetTextI18n"})
        public final void bind(@NotNull final VitalsModel vital) {
            Intrinsics.checkNotNullParameter(vital, "vital");
            ListItemVitalsLayoutBinding listItemVitalsLayoutBinding = this.f4780a;
            final MainVitalsAdapter mainVitalsAdapter = this.b;
            if (mainVitalsAdapter.d == getAbsoluteAdapterPosition()) {
                TextView textView = listItemVitalsLayoutBinding.tvVitalName;
                textView.setTypeface(textView.getTypeface(), 1);
                listItemVitalsLayoutBinding.tvVitalName.setTextColor(ContextCompat.getColor(mainVitalsAdapter.getContext(), R.color.white));
                listItemVitalsLayoutBinding.ivVitalsBg.setBackgroundResource(R.drawable.circular_grey_with_border_background);
            } else {
                TextView textView2 = listItemVitalsLayoutBinding.tvVitalName;
                textView2.setTypeface(textView2.getTypeface(), 0);
                listItemVitalsLayoutBinding.tvVitalName.setTextColor(ContextCompat.getColor(mainVitalsAdapter.getContext(), R.color.info_text_color));
                listItemVitalsLayoutBinding.ivVitalsBg.setBackgroundResource(R.drawable.circular_black_grey_border_background);
            }
            listItemVitalsLayoutBinding.tvVitalName.setText(vital.getVitalName());
            listItemVitalsLayoutBinding.ivVitalImage.setBackgroundResource(vital.getVitalIcon());
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.home.adapters.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MainVitalsAdapter.ViewHolder.b(MainVitalsAdapter.this, vital, this, view);
                }
            });
        }
    }

    /* loaded from: classes3.dex */
    public interface VitalsClickListener {
        void selectedVital(@NotNull VitalsModel vitalsModel, int i);
    }

    public MainVitalsAdapter(@NotNull Context context, @NotNull VitalsClickListener vitalsClickListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(vitalsClickListener, "vitalsClickListener");
        this.f4779a = context;
        this.b = vitalsClickListener;
        this.c = new ArrayList();
        this.d = -1;
    }

    @NotNull
    public final Context getContext() {
        return this.f4779a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    @NotNull
    public final VitalsClickListener getVitalsClickListener() {
        return this.b;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f4779a = context;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setLastSelectedPosition(int i) {
        this.d = i;
        notifyDataSetChanged();
    }

    public final void setVitalsClickListener(@NotNull VitalsClickListener vitalsClickListener) {
        Intrinsics.checkNotNullParameter(vitalsClickListener, "<set-?>");
        this.b = vitalsClickListener;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setVitalsList(@NotNull List<VitalsModel> vitalsData) {
        Intrinsics.checkNotNullParameter(vitalsData, "vitalsData");
        this.c = TypeIntrinsics.asMutableList(vitalsData);
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
        ListItemVitalsLayoutBinding inflate = ListItemVitalsLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n            Lay…, parent, false\n        )");
        return new ViewHolder(this, inflate);
    }
}
