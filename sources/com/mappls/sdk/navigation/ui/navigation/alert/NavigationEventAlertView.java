package com.mappls.sdk.navigation.ui.navigation.alert;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.mappls.sdk.navigation.NavigationFormatter;
import com.mappls.sdk.navigation.events.NavEvent;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.navigation.ui.databinding.LayoutAlertViewBinding;
import com.mappls.sdk.navigation.ui.navigation.MapplsNavigationViewHelper;
import com.mappls.sdk.navigation.ui.theme.a;
import com.mappls.sdk.services.api.event.route.ReportCriteria;
@Keep
/* loaded from: classes11.dex */
public class NavigationEventAlertView extends LinearLayout {
    public LayoutAlertViewBinding binding;

    public NavigationEventAlertView(Context context) {
        super(context);
        this.binding = LayoutAlertViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    public NavigationEventAlertView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.binding = LayoutAlertViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    public NavigationEventAlertView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.binding = LayoutAlertViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }

    public boolean isShowingEvent() {
        return getVisibility() == 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        toggleTheme();
        setVisibility(8);
    }

    public void toggleTheme() {
        this.binding.reportLayout.setBackgroundResource(a.e(getContext(), R.attr.navigationViewAlertViewBackground));
        this.binding.textReportDistanceLeft.setBackgroundResource(a.e(getContext(), R.attr.navigationViewAlertViewDistanceBackground));
        TextView textView = this.binding.textReportDistanceLeft;
        Context context = getContext();
        int i = R.attr.navigationTextColorPrimary;
        textView.setTextColor(a.b(context, i));
        this.binding.textReportCategoryName.setTextColor(a.b(getContext(), i));
    }

    public void updateRouteEvent(NavEvent navEvent) {
        if (navEvent == null || navEvent.getName() == null) {
            setVisibility(8);
            return;
        }
        setVisibility(navEvent.getDistanceLeft() > 0.0d ? 0 : 8);
        this.binding.textReportDistanceLeft.setText(NavigationFormatter.getFormattedDistance((float) navEvent.getDistanceLeft(), MapplsNavigationViewHelper.getInstance().getApplication()));
        this.binding.textReportCategoryName.setText(navEvent.getName());
        if (navEvent.getReportDetails() != null) {
            Glide.with(this).m30load(navEvent.getReportDetails().getReportIcon(ReportCriteria.ICON_54_PX)).placeholder(R.drawable.ic_report_placeholder_24_px).into(this.binding.reportIcon);
        } else {
            this.binding.reportIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_report_placeholder_24_px));
        }
    }
}
