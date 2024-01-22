package com.coveiot.android.femalewellness.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.femalewellness.Constants;
import com.coveiot.android.femalewellness.R;
import com.coveiot.android.femalewellness.model.FemaleWellnessSymptoms;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class FemaleWellnessSymptomsAdaptor extends RecyclerView.Adapter<FemaleWellnessSymptomsHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4387a;
    @NotNull
    public final Activity b;
    @NotNull
    public String c;
    @NotNull
    public final List<FemaleWellnessSymptoms> d;
    @NotNull
    public final onItemClickListener e;
    public final boolean f;
    @Nullable
    public onItemClickListener g;
    public int h;

    /* loaded from: classes4.dex */
    public static final class FemaleWellnessSymptomsHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f4388a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final ConstraintLayout d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FemaleWellnessSymptomsHolder(@NotNull View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_flow);
            Intrinsics.checkNotNullExpressionValue(imageView, "view.iv_flow");
            this.f4388a = imageView;
            TextView textView = (TextView) view.findViewById(R.id.tvFlow);
            Intrinsics.checkNotNullExpressionValue(textView, "view.tvFlow");
            this.b = textView;
            TextView textView2 = (TextView) view.findViewById(R.id.tvSymptoms);
            Intrinsics.checkNotNullExpressionValue(textView2, "view.tvSymptoms");
            this.c = textView2;
            ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.clSymptoms_type);
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "view.clSymptoms_type");
            this.d = constraintLayout;
        }

        @NotNull
        public final ConstraintLayout getClSymptoms_type() {
            return this.d;
        }

        @NotNull
        public final ImageView getIv_flow() {
            return this.f4388a;
        }

        @NotNull
        public final TextView getTvFlow() {
            return this.b;
        }

        @NotNull
        public final TextView getTvSymptoms() {
            return this.c;
        }
    }

    /* loaded from: classes4.dex */
    public interface onItemClickListener {
        void onItemClick(int i, @NotNull String str, @NotNull String str2);
    }

    public FemaleWellnessSymptomsAdaptor(@NotNull Context mContext, @NotNull Activity activity, @NotNull String symptomType, @NotNull List<FemaleWellnessSymptoms> symptomsList, @NotNull onItemClickListener itemListener, boolean z) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(symptomType, "symptomType");
        Intrinsics.checkNotNullParameter(symptomsList, "symptomsList");
        Intrinsics.checkNotNullParameter(itemListener, "itemListener");
        this.f4387a = mContext;
        this.b = activity;
        this.c = symptomType;
        this.d = symptomsList;
        this.e = itemListener;
        this.f = z;
        this.h = -1;
        this.g = itemListener;
    }

    public static final void c(FemaleWellnessSymptomsAdaptor this$0, int i, FemaleWellnessSymptomsHolder holder, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(holder, "$holder");
        this$0.d.get(i).setSelected(!this$0.d.get(i).isSelected());
        onItemClickListener onitemclicklistener = this$0.g;
        if (onitemclicklistener != null) {
            onitemclicklistener.onItemClick(i, this$0.c, this$0.d.get(i).getSymptomName());
        }
        this$0.e(holder, i);
    }

    public static final void d(FemaleWellnessSymptomsAdaptor this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        onItemClickListener onitemclicklistener = this$0.g;
        if (onitemclicklistener != null) {
            onitemclicklistener.onItemClick(i, this$0.c, this$0.d.get(i).getSymptomName());
        }
        this$0.h = i;
        this$0.notifyDataSetChanged();
    }

    public final void e(FemaleWellnessSymptomsHolder femaleWellnessSymptomsHolder, int i) {
        femaleWellnessSymptomsHolder.getTvSymptoms().setBackground(this.d.get(i).isSelected() ? this.f4387a.getResources().getDrawable(R.drawable.rounded_red_border_grey_background_symptoms) : this.f4387a.getResources().getDrawable(R.drawable.rounded_grey_with_border_background_40dp_symptoms));
        femaleWellnessSymptomsHolder.getTvSymptoms().setTextColor(this.d.get(i).isSelected() ? this.f4387a.getResources().getColor(R.color.white) : this.f4387a.getResources().getColor(R.color.info_text_color1));
        femaleWellnessSymptomsHolder.getTvSymptoms().setTypeface(femaleWellnessSymptomsHolder.getTvSymptoms().getTypeface(), this.d.get(i).isSelected() ? 1 : 0);
    }

    @NotNull
    public final Activity getActivity() {
        return this.b;
    }

    public final int getCl_row_index() {
        return this.h;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.d.size();
    }

    @NotNull
    public final onItemClickListener getItemListener() {
        return this.e;
    }

    @Nullable
    public final onItemClickListener getListener() {
        return this.g;
    }

    @NotNull
    public final String getSymptomType() {
        return this.c;
    }

    @NotNull
    public final List<FemaleWellnessSymptoms> getSymptomsList() {
        return this.d;
    }

    public final boolean isEditSymptoms() {
        return this.f;
    }

    public final void setCl_row_index(int i) {
        this.h = i;
    }

    public final void setListener(@Nullable onItemClickListener onitemclicklistener) {
        this.g = onitemclicklistener;
    }

    public final void setOnClickListener(@Nullable onItemClickListener onitemclicklistener) {
        this.g = onitemclicklistener;
    }

    public final void setSymptomType(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.c = str;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull final FemaleWellnessSymptomsHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (m.equals(this.c, Constants.SYMPTOMS.getValue(), true)) {
            holder.getClSymptoms_type().setVisibility(8);
            holder.getTvSymptoms().setVisibility(0);
            holder.getTvSymptoms().setText(this.d.get(i).getSymptomName());
            if (this.f) {
                e(holder, i);
            } else {
                holder.getTvSymptoms().setBackground(this.f4387a.getResources().getDrawable(R.drawable.rounded_grey_with_border_background_40dp_symptoms));
            }
        } else {
            holder.getClSymptoms_type().setBackgroundResource(R.drawable.rounded_black_grey_border_background_24dp);
            holder.getTvSymptoms().setVisibility(8);
            holder.getClSymptoms_type().setVisibility(0);
            holder.getTvFlow().setText(this.d.get(i).getSymptomName());
            holder.getIv_flow().setImageDrawable(this.d.get(i).getSymptomImage());
            if (Intrinsics.areEqual(this.d.get(i).getSymptomName(), Constants.PAIN_LOW_CHECK.getValue())) {
                holder.getTvFlow().setText(Constants.PAIN_LIGHT.getValue());
            } else {
                holder.getTvFlow().setText(this.d.get(i).getSymptomName());
            }
        }
        holder.getTvSymptoms().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.adapter.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FemaleWellnessSymptomsAdaptor.c(FemaleWellnessSymptomsAdaptor.this, i, holder, view);
            }
        });
        holder.getClSymptoms_type().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.femalewellness.adapter.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FemaleWellnessSymptomsAdaptor.d(FemaleWellnessSymptomsAdaptor.this, i, view);
            }
        });
        if (this.h == i) {
            holder.getClSymptoms_type().setBackgroundResource(R.drawable.rounded_red_border_grey_background_24dp);
            holder.getTvFlow().setTextColor(this.f4387a.getResources().getColor(R.color.white));
            holder.getIv_flow().setImageDrawable(this.d.get(i).getSelectedSymptomImage());
            return;
        }
        holder.getClSymptoms_type().setBackgroundResource(R.drawable.rounded_black_grey_border_background_24dp);
        holder.getTvFlow().setTextColor(this.f4387a.getResources().getColor(R.color.info_text_color1));
        holder.getIv_flow().setImageDrawable(this.d.get(i).getSymptomImage());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public FemaleWellnessSymptomsHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        this.b.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_female_wellness_symptoms, (ViewGroup) null);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(viewGroup.context)\n…_wellness_symptoms, null)");
        return new FemaleWellnessSymptomsHolder(inflate);
    }
}
