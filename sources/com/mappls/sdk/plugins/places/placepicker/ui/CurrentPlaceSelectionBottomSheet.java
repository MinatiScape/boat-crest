package com.mappls.sdk.plugins.places.placepicker.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mappls.sdk.plugins.places.R;
import com.mappls.sdk.plugins.places.placepicker.model.PlacePickerOptions;
import com.mappls.sdk.services.api.Place;
/* loaded from: classes11.dex */
public class CurrentPlaceSelectionBottomSheet extends CoordinatorLayout {
    public BottomSheetBehavior h;
    public CoordinatorLayout i;
    public TextView j;
    public TextView k;
    public ProgressBar l;

    public CurrentPlaceSelectionBottomSheet(Context context) {
        this(context, null);
    }

    public CurrentPlaceSelectionBottomSheet(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public CurrentPlaceSelectionBottomSheet(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b(context);
    }

    public final void a() {
        this.h.setPeekHeight(this.i.findViewById(R.id.root_bottom_sheet).getHeight());
        boolean b = b();
        this.h.setHideable(b);
        this.h.setState(b ? 5 : 4);
    }

    public final void a(PlacePickerOptions placePickerOptions) {
        this.j.setTextColor(placePickerOptions.placeNameTextColor().intValue());
        this.k.setTextColor(placePickerOptions.addressTextColor().intValue());
    }

    public final void b(Context context) {
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) View.inflate(context, R.layout.mappls_search_view_bottom_sheet_container, this);
        this.i = coordinatorLayout;
        BottomSheetBehavior from = BottomSheetBehavior.from(coordinatorLayout.findViewById(R.id.root_bottom_sheet));
        this.h = from;
        from.setHideable(true);
        this.h.setState(5);
        this.j = (TextView) findViewById(R.id.text_view_place_name);
        this.k = (TextView) findViewById(R.id.text_view_place_address);
        this.l = (ProgressBar) findViewById(R.id.progress_bar_place);
    }

    public final boolean b() {
        return this.h.getState() != 5;
    }

    public final void a(@Nullable Place place) {
        if (!b()) {
            this.h.setPeekHeight(this.i.findViewById(R.id.root_bottom_sheet).getHeight());
            boolean b = b();
            this.h.setHideable(b);
            this.h.setState(b ? 5 : 4);
        }
        if (place == null) {
            this.j.setText("");
            this.k.setText("");
            this.l.setVisibility(0);
            return;
        }
        this.l.setVisibility(4);
        this.j.setText((place.getPoi() == null || place.getPoi().equalsIgnoreCase("")) ? "Dropped Pin" : place.getPoi());
        this.k.setText(place.getFormattedAddress());
    }
}
