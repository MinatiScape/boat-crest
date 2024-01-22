package com.mappls.sdk.navigation.ui.navigation.directions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.NavigationFormatter;
import com.mappls.sdk.navigation.routing.NavigationStep;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.navigation.ui.databinding.LayoutDirectionAdapterHeaderBinding;
import com.mappls.sdk.navigation.ui.databinding.LayoutDirectionAdapterLightBinding;
import com.mappls.sdk.navigation.ui.navigation.MapplsNavigationViewHelper;
import com.mappls.sdk.services.api.directions.models.LegStep;
import java.util.List;
/* loaded from: classes11.dex */
public class a extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    public List<NavigationStep> f13010a;
    public NavigationApplication b;
    public com.mappls.sdk.navigation.ui.navigation.directions.b c;

    /* renamed from: com.mappls.sdk.navigation.ui.navigation.directions.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class View$OnClickListenerC0650a implements View.OnClickListener {
        public final /* synthetic */ NavigationStep h;

        public View$OnClickListenerC0650a(NavigationStep navigationStep) {
            this.h = navigationStep;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (a.this.c != null) {
                a.this.c.a(this.h);
            }
        }
    }

    /* loaded from: classes11.dex */
    public static class b extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public final LayoutDirectionAdapterHeaderBinding f13011a;

        public b(@NonNull LayoutDirectionAdapterHeaderBinding layoutDirectionAdapterHeaderBinding) {
            super(layoutDirectionAdapterHeaderBinding.getRoot());
            this.f13011a = layoutDirectionAdapterHeaderBinding;
            layoutDirectionAdapterHeaderBinding.directionImage.setImageDrawable(ContextCompat.getDrawable(layoutDirectionAdapterHeaderBinding.getRoot().getContext(), R.drawable.mappls_navigation_stop_baseline));
        }
    }

    /* loaded from: classes11.dex */
    public static class c extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public final LayoutDirectionAdapterHeaderBinding f13012a;

        public c(@NonNull LayoutDirectionAdapterHeaderBinding layoutDirectionAdapterHeaderBinding) {
            super(layoutDirectionAdapterHeaderBinding.getRoot());
            this.f13012a = layoutDirectionAdapterHeaderBinding;
            layoutDirectionAdapterHeaderBinding.directionImage.setImageDrawable(ContextCompat.getDrawable(layoutDirectionAdapterHeaderBinding.getRoot().getContext(), R.drawable.mappls_navigation_direction_play_arrow));
            layoutDirectionAdapterHeaderBinding.navigationListDesc.setText(layoutDirectionAdapterHeaderBinding.getRoot().getContext().getResources().getString(R.string.mappls_navigation_starting_text));
            layoutDirectionAdapterHeaderBinding.navigationDirectionPlaceName.setText(layoutDirectionAdapterHeaderBinding.getRoot().getContext().getResources().getString(R.string.mappls_navigation_your_current_location));
        }
    }

    /* loaded from: classes11.dex */
    public static class d extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public final LayoutDirectionAdapterLightBinding f13013a;

        public d(@NonNull LayoutDirectionAdapterLightBinding layoutDirectionAdapterLightBinding) {
            super(layoutDirectionAdapterLightBinding.getRoot());
            this.f13013a = layoutDirectionAdapterLightBinding;
        }
    }

    public a(NavigationApplication navigationApplication) {
        this.b = navigationApplication;
    }

    public void a(com.mappls.sdk.navigation.ui.navigation.directions.b bVar) {
        this.c = bVar;
    }

    public void a(List<NavigationStep> list) {
        this.f13010a = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<NavigationStep> list = this.f13010a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (i == 0) {
            return 0;
        }
        return i == this.f13010a.size() - 1 ? 2 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        TextView textView;
        String str;
        NavigationStep navigationStep = this.f13010a.get(viewHolder.getAdapterPosition());
        if (!(viewHolder instanceof d)) {
            if (!(viewHolder instanceof b)) {
                c cVar = (c) viewHolder;
                cVar.f13012a.navigationListDesc.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(cVar.f13012a.getRoot().getContext(), R.attr.navigationTextColorTertiary));
                cVar.f13012a.navigationDirectionPlaceName.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(cVar.f13012a.getRoot().getContext(), R.attr.navigationViewDirectionText));
                cVar.f13012a.maneuverViewContainer.setBackground(com.mappls.sdk.navigation.ui.theme.a.d(cVar.f13012a.getRoot().getContext(), R.attr.navigationViewManeuverBackground));
                return;
            }
            b bVar = (b) viewHolder;
            bVar.f13011a.navigationListDesc.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(bVar.f13011a.getRoot().getContext(), R.attr.navigationTextColorTertiary));
            bVar.f13011a.navigationDirectionPlaceName.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(bVar.f13011a.getRoot().getContext(), R.attr.navigationViewDirectionText));
            bVar.f13011a.maneuverViewContainer.setBackground(com.mappls.sdk.navigation.ui.theme.a.d(bVar.f13011a.getRoot().getContext(), R.attr.navigationViewManeuverBackground));
            if (MapplsNavigationViewHelper.getInstance().getDestination() != null) {
                textView = bVar.f13011a.navigationDirectionPlaceName;
                str = MapplsNavigationViewHelper.getInstance().getDestination().placeName;
            } else {
                textView = bVar.f13011a.navigationDirectionPlaceName;
                str = "End Stop";
            }
            textView.setText(str);
            bVar.f13011a.navigationListDesc.setText(navigationStep.getDescriptionRoutePartHTML());
            return;
        }
        d dVar = (d) viewHolder;
        dVar.f13013a.directionText.setText(navigationStep.getDescriptionRoutePartHTML());
        dVar.f13013a.tvDistance.setText(String.format("GO  %s", NavigationFormatter.getFormattedDistance(navigationStep.getDistance(), this.b)));
        dVar.f13013a.tvDistance.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(dVar.f13013a.getRoot().getContext(), R.attr.navigationTextColorTertiary));
        dVar.f13013a.directionText.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(dVar.f13013a.getRoot().getContext(), R.attr.navigationViewDirectionText));
        dVar.f13013a.maneuverViewContainer.setBackground(com.mappls.sdk.navigation.ui.theme.a.d(dVar.f13013a.getRoot().getContext(), R.attr.navigationViewManeuverBackground));
        dVar.f13013a.directionImage.setPrimaryColor(com.mappls.sdk.navigation.ui.theme.a.b(dVar.f13013a.getRoot().getContext(), R.attr.directionListManeuverPrimary));
        dVar.f13013a.directionImage.setSecondaryColor(com.mappls.sdk.navigation.ui.theme.a.b(dVar.f13013a.getRoot().getContext(), R.attr.directionListManeuverSecondary));
        if (navigationStep.getExtraInfo() instanceof LegStep) {
            LegStep legStep = (LegStep) navigationStep.getExtraInfo();
            dVar.f13013a.directionImage.setManeuverTypeAndModifier(legStep.maneuver().type(), legStep.maneuver().modifier());
            if (legStep.maneuver().type() != null && (legStep.maneuver().type().equalsIgnoreCase("roundabout") || legStep.maneuver().type().equalsIgnoreCase("rotary"))) {
                if (legStep.maneuver().degree() != null) {
                    dVar.f13013a.directionImage.setRoundaboutAngle(com.mappls.sdk.navigation.ui.utils.d.b(legStep.maneuver().degree().floatValue()));
                } else {
                    dVar.f13013a.directionImage.setRoundaboutAngle(com.mappls.sdk.navigation.ui.utils.d.b(180.0f));
                }
            }
        }
        dVar.f13013a.getRoot().setOnClickListener(new View$OnClickListenerC0650a(navigationStep));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return i == 0 ? new c((LayoutDirectionAdapterHeaderBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.layout_direction_adapter_header, viewGroup, false)) : i == 2 ? new b((LayoutDirectionAdapterHeaderBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.layout_direction_adapter_header, viewGroup, false)) : new d((LayoutDirectionAdapterLightBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.layout_direction_adapter_light, viewGroup, false));
    }
}
