package com.mappls.sdk.direction.ui.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import com.mappls.sdk.direction.ui.R;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionPreviewPagerBinding;
import com.mappls.sdk.plugin.directions.DirectionsUtils;
import com.mappls.sdk.plugin.directions.view.ManeuverView;
import com.mappls.sdk.services.api.directions.models.LegStep;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class a extends PagerAdapter {
    public List<LegStep> c;
    public MapplsDirectionPreviewPagerBinding d;
    @StyleRes
    public int e;
    public Context f;

    public a(List<LegStep> list, @StyleRes int i, Context context) {
        this.c = list == null ? new ArrayList<>() : list;
        this.e = i;
        this.f = context;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public final void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public final int getCount() {
        List<LegStep> list = this.c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @NonNull
    public final Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
        ManeuverView maneuverView;
        float f;
        MapplsDirectionPreviewPagerBinding inflate = MapplsDirectionPreviewPagerBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        this.d = inflate;
        viewGroup.addView(inflate.getRoot());
        this.f.setTheme(this.e);
        Resources.Theme theme = this.f.getTheme();
        int[] iArr = R.styleable.mappls_direction;
        TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(iArr);
        Objects.requireNonNull(obtainStyledAttributes);
        this.d.directionPreviewText.setTextColor(obtainStyledAttributes.getColor(R.styleable.mappls_direction_directions_list_nextstep_distance_tv_color, ContextCompat.getColor(this.f, R.color.mappls_directions_steps_text)));
        TypedArray obtainStyledAttributes2 = this.f.getTheme().obtainStyledAttributes(iArr);
        Objects.requireNonNull(obtainStyledAttributes2);
        this.d.directionPreviewDist.setTextColor(obtainStyledAttributes2.getColor(R.styleable.mappls_direction_directions_list_nextstep_tv_color, ContextCompat.getColor(this.f, R.color.mappls_directions_distance_text_Color)));
        TypedArray obtainStyledAttributes3 = this.f.getTheme().obtainStyledAttributes(iArr);
        Objects.requireNonNull(obtainStyledAttributes3);
        ManeuverView maneuverView2 = this.d.navigateIcon;
        int i2 = R.styleable.mappls_direction_directions_mv_primary_color;
        Context context = this.f;
        int i3 = R.color.mappls_direction_colorBlack;
        maneuverView2.setPrimaryColor(obtainStyledAttributes3.getColor(i2, ContextCompat.getColor(context, i3)));
        TypedArray obtainStyledAttributes4 = this.f.getTheme().obtainStyledAttributes(iArr);
        Objects.requireNonNull(obtainStyledAttributes4);
        this.d.navigateIcon.setSecondaryColor(obtainStyledAttributes4.getColor(R.styleable.mappls_direction_direction_mv_secondary_color, ContextCompat.getColor(this.f, i3)));
        TypedArray obtainStyledAttributes5 = this.f.getTheme().obtainStyledAttributes(iArr);
        Objects.requireNonNull(obtainStyledAttributes5);
        this.d.navigateIcon.setSecondaryColor(obtainStyledAttributes5.getColor(R.styleable.mappls_direction_directions_mv_bg_color, ContextCompat.getColor(this.f, i3)));
        TypedArray obtainStyledAttributes6 = this.f.getTheme().obtainStyledAttributes(iArr);
        Objects.requireNonNull(obtainStyledAttributes6);
        this.d.maneuverViewContainer.setBackgroundResource(obtainStyledAttributes6.getResourceId(R.styleable.mappls_direction_directions_mv_bg_drawable, R.drawable.mappls_direction_circle_black));
        this.d.directionPreviewText.setText(DirectionsUtils.getTextInstructions(this.c.get(i)));
        ManeuverView maneuverView3 = this.d.navigateIcon;
        String type = this.c.get(i).maneuver().type();
        Objects.requireNonNull(type);
        maneuverView3.setManeuverTypeAndModifier(type, this.c.get(i).maneuver().modifier());
        String type2 = this.c.get(i).maneuver().type();
        if (type2 != null && (type2.equalsIgnoreCase("roundabout") || type2.equalsIgnoreCase("rotary"))) {
            if (this.c.get(i).maneuver().degree() != null) {
                maneuverView = this.d.navigateIcon;
                f = this.c.get(i).maneuver().degree().floatValue();
            } else {
                maneuverView = this.d.navigateIcon;
                f = 180.0f;
            }
            maneuverView.setRoundaboutAngle(f);
        }
        this.d.directionPreviewDist.setText(String.format("GO  %s", com.mappls.sdk.direction.ui.common.d.b(this.c.get(i).distance())));
        return this.d.getRoot();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public final boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view == obj;
    }
}
