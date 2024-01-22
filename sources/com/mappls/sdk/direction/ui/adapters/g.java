package com.mappls.sdk.direction.ui.adapters;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.mappls.sdk.direction.ui.R;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionRouteSummaryItemBinding;
import com.mappls.sdk.services.api.event.route.ReportCriteria;
import com.mappls.sdk.services.api.event.route.model.ReportDetails;
import java.util.ArrayList;
/* loaded from: classes11.dex */
public final class g extends RecyclerView.Adapter<a> {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList<ReportDetails> f12576a;

    /* loaded from: classes11.dex */
    public class a extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public final MapplsDirectionRouteSummaryItemBinding f12577a;

        public a(@NonNull MapplsDirectionRouteSummaryItemBinding mapplsDirectionRouteSummaryItemBinding) {
            super(mapplsDirectionRouteSummaryItemBinding.getRoot());
            this.f12577a = mapplsDirectionRouteSummaryItemBinding;
        }
    }

    public g(ArrayList<ReportDetails> arrayList) {
        this.f12576a = arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        ArrayList<ReportDetails> arrayList = this.f12576a;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(@NonNull a aVar, int i) {
        TextView textView;
        StringBuilder sb;
        String addedByName;
        a aVar2 = aVar;
        ReportDetails reportDetails = this.f12576a.get(i);
        Glide.with(aVar2.f12577a.imageRouteSummaryEventType).m30load(reportDetails.getReportIcon(ReportCriteria.ICON_48_PX)).placeholder(R.drawable.mappls_direction_report_placeholder_48_px).into(aVar2.f12577a.imageRouteSummaryEventType);
        TextView textView2 = aVar2.f12577a.title;
        String childCategory = reportDetails.getChildCategory();
        String str = " at " + reportDetails.getAddress();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        SpannableString spannableString = new SpannableString(childCategory);
        spannableString.setSpan(new StyleSpan(1), 0, childCategory.length(), 33);
        spannableStringBuilder.append((CharSequence) spannableString);
        SpannableString spannableString2 = new SpannableString(str);
        spannableString2.setSpan(new StyleSpan(0), 0, str.length(), 33);
        spannableStringBuilder.append((CharSequence) spannableString2);
        textView2.setText(spannableStringBuilder);
        if (reportDetails.getUsersCount() != null && reportDetails.getUsersCount().longValue() > 1) {
            textView = aVar2.f12577a.subTitle;
            sb = new StringBuilder("Reported By: ");
            sb.append(reportDetails.getUsersCount());
            addedByName = " People";
        } else if (reportDetails.getAddedByName() == null) {
            return;
        } else {
            textView = aVar2.f12577a.subTitle;
            sb = new StringBuilder("Reported By: ");
            addedByName = reportDetails.getAddedByName();
        }
        sb.append(addedByName);
        textView.setText(sb.toString());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public final a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new a((MapplsDirectionRouteSummaryItemBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.mappls_direction_route_summary_item, viewGroup, false));
    }
}
