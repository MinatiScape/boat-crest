package com.coveiot.android.femalewellness.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.femalewellness.R;
import com.coveiot.android.femalewellness.model.FemaleWellnessSymptoms;
import com.coveiot.android.femalewellness.utils.ViewUtilsKt;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class FemaleWellnessRecordedSymptomsAdaptor extends RecyclerView.Adapter<FemaleWellnessRecordedSymptomsHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final List<FemaleWellnessSymptoms> f4385a;

    /* loaded from: classes4.dex */
    public static final class FemaleWellnessRecordedSymptomsHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f4386a;
        @NotNull
        public final TextView b;
        @NotNull
        public final View c;
        @NotNull
        public final ConstraintLayout d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FemaleWellnessRecordedSymptomsHolder(@NotNull View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            ImageView imageView = (ImageView) view.findViewById(R.id.ivSymptom);
            Intrinsics.checkNotNullExpressionValue(imageView, "view.ivSymptom");
            this.f4386a = imageView;
            TextView textView = (TextView) view.findViewById(R.id.tvSymptom);
            Intrinsics.checkNotNullExpressionValue(textView, "view.tvSymptom");
            this.b = textView;
            View findViewById = view.findViewById(R.id.dividerView);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.dividerView");
            this.c = findViewById;
            ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.clSymptoms_type);
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "view.clSymptoms_type");
            this.d = constraintLayout;
        }

        @NotNull
        public final ConstraintLayout getClSymptomsType() {
            return this.d;
        }

        @NotNull
        public final View getDividerView() {
            return this.c;
        }

        @NotNull
        public final ImageView getIvSymptom() {
            return this.f4386a;
        }

        @NotNull
        public final TextView getTvSymptom() {
            return this.b;
        }
    }

    public FemaleWellnessRecordedSymptomsAdaptor(@NotNull Context mContext, @NotNull List<FemaleWellnessSymptoms> symptomsList) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(symptomsList, "symptomsList");
        this.f4385a = symptomsList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f4385a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull FemaleWellnessRecordedSymptomsHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getTvSymptom().setText(this.f4385a.get(i).getSymptomName());
        if (this.f4385a.get(i).getSymptomImage() != null) {
            ViewUtilsKt.visible(holder.getClSymptomsType());
            holder.getTvSymptom().setText(this.f4385a.get(i).getSymptomName());
            ViewUtilsKt.visible(holder.getIvSymptom());
            holder.getIvSymptom().setImageDrawable(this.f4385a.get(i).getSelectedSymptomImage());
        } else {
            if (!(this.f4385a.get(i).getSymptomName().length() == 0) && !this.f4385a.get(i).getSymptomName().equals("")) {
                ViewUtilsKt.gone(holder.getClSymptomsType());
                ViewUtilsKt.gone(holder.getTvSymptom());
                ViewUtilsKt.gone(holder.getIvSymptom());
            } else {
                ViewUtilsKt.gone(holder.getClSymptomsType());
            }
        }
        if (this.f4385a.size() > 3) {
            holder.getDividerView().setVisibility(this.f4385a.size() - 2 == i ? 8 : 0);
            return;
        }
        int size = this.f4385a.size();
        if (size == 1) {
            holder.getDividerView().setVisibility(this.f4385a.size() - 1 == i ? 8 : 0);
        } else if (size == 2) {
            holder.getDividerView().setVisibility(this.f4385a.size() - 2 == i ? 8 : 0);
        } else if (size != 3) {
        } else {
            holder.getDividerView().setVisibility(this.f4385a.size() - 3 != i ? 8 : 0);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public FemaleWellnessRecordedSymptomsHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recorded_symptoms, (ViewGroup) null);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(viewGroup.context).…_recorded_symptoms, null)");
        return new FemaleWellnessRecordedSymptomsHolder(inflate);
    }
}
