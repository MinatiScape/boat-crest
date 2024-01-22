package com.mappls.sdk.navigation.ui.navigation.infobar;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mappls.sdk.navigation.ui.NavigationOptions;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.navigation.ui.databinding.LayoutBottomInfobarBinding;
import com.mappls.sdk.navigation.ui.navigation.infobar.b;
import com.mappls.sdk.services.api.autosuggest.model.ELocation;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;
@Keep
/* loaded from: classes11.dex */
public class InfobarBottomSheetView extends BaseInfobarBottomSheet implements LifecycleObserver {
    private com.mappls.sdk.navigation.ui.navigation.infobar.b adapter;
    private final LayoutBottomInfobarBinding binding;
    private boolean enableTraffic;
    private List<com.mappls.sdk.navigation.ui.navigation.infobar.c> infoBarItems;
    private LifecycleOwner lifecycleOwner;
    private BottomSheetBehavior mBottomSheetBehavior;
    private com.mappls.sdk.navigation.ui.navigation.a navigationViewModel;
    private com.mappls.sdk.navigation.ui.navigation.infobar.e onInfobarCallback;

    /* loaded from: classes11.dex */
    public class a implements b.a {
        public a() {
        }
    }

    /* loaded from: classes11.dex */
    public class b extends BottomSheetBehavior.BottomSheetCallback {
        public b() {
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        public void onSlide(@NonNull View view, float f) {
            InfobarBottomSheetView.this.binding.ivBottomSheetArrow.setRotation(f * 180.0f);
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
        public void onStateChanged(@NonNull View view, int i) {
        }
    }

    /* loaded from: classes11.dex */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (InfobarBottomSheetView.this.mBottomSheetBehavior != null) {
                if (InfobarBottomSheetView.this.mBottomSheetBehavior.getState() == 4) {
                    InfobarBottomSheetView.this.mBottomSheetBehavior.setState(3);
                } else if (InfobarBottomSheetView.this.mBottomSheetBehavior.getState() == 3) {
                    InfobarBottomSheetView.this.mBottomSheetBehavior.setState(4);
                }
            }
        }
    }

    /* loaded from: classes11.dex */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            InfobarBottomSheetView.this.showRouteOverview(false);
            if (InfobarBottomSheetView.this.onInfobarCallback != null) {
                InfobarBottomSheetView.this.onInfobarCallback.d();
            }
            if (InfobarBottomSheetView.this.mBottomSheetBehavior != null) {
                InfobarBottomSheetView.this.mBottomSheetBehavior.setState(4);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (InfobarBottomSheetView.this.onInfobarCallback != null) {
                InfobarBottomSheetView.this.onInfobarCallback.e();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class f implements Observer<ELocation> {
        public f() {
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(ELocation eLocation) {
            ELocation eLocation2 = eLocation;
            if (eLocation2 == null) {
                Timber.e("Please pass Destination using MapplsNavigationViewHelper", new Object[0]);
                InfobarBottomSheetView.this.binding.destinationText.setText("");
                return;
            }
            TextView textView = InfobarBottomSheetView.this.binding.destinationText;
            textView.setText("Destination: " + eLocation2.placeName);
        }
    }

    /* loaded from: classes11.dex */
    public class g implements Observer<com.mappls.sdk.navigation.ui.navigation.infobar.d> {
        public g() {
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(@Nullable com.mappls.sdk.navigation.ui.navigation.infobar.d dVar) {
            com.mappls.sdk.navigation.ui.navigation.infobar.d dVar2 = dVar;
            if (dVar2 != null) {
                InfobarBottomSheetView.this.setData(dVar2);
            }
        }
    }

    public InfobarBottomSheetView(@NotNull Context context) {
        this(context, null);
    }

    public InfobarBottomSheetView(@NotNull Context context, @org.jetbrains.annotations.Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.bottomSheetInfoBarStyle);
    }

    public InfobarBottomSheetView(@NotNull Context context, @org.jetbrains.annotations.Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R.style.BottomSheetInfobarStyle);
    }

    public InfobarBottomSheetView(@NotNull Context context, @org.jetbrains.annotations.Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.binding = LayoutBottomInfobarBinding.inflate(LayoutInflater.from(getContext()), this, true);
        this.adapter = new com.mappls.sdk.navigation.ui.navigation.infobar.b();
        this.infoBarItems = new ArrayList();
        this.enableTraffic = false;
    }

    private SpannableString getColorSpannableString(String str, String str2, int i, int i2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (str.length() > 0) {
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new ForegroundColorSpan(i), 0, str.length(), 33);
            spannableStringBuilder.append((CharSequence) spannableString);
        }
        if (str2.length() > 0) {
            SpannableString spannableString2 = new SpannableString(str2);
            spannableString2.setSpan(new ForegroundColorSpan(i2), 0, str2.length(), 33);
            spannableStringBuilder.append((CharSequence) spannableString2);
        }
        return SpannableString.valueOf(spannableStringBuilder);
    }

    private int getCongestionPercentage(List<String> list, int i) {
        if (list == null || list.isEmpty()) {
            return R.color.navigation_eta_text_color_with_out_traffic;
        }
        List<String> arrayList = list.isEmpty() ? new ArrayList<>() : i < list.size() ? list.subList(i, list.size()) : new ArrayList<>();
        int i2 = 0;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            if (arrayList.get(i3).equals("heavy") || arrayList.get(i3).equals("moderate") || arrayList.get(i3).equals("severe")) {
                i2++;
            }
        }
        int size = !arrayList.isEmpty() ? (i2 * 100) / arrayList.size() : 1;
        return size <= 10 ? R.color.navigation_eta_text_color_with_out_traffic : size <= 25 ? R.color.navigation_eta_text_color_with_low_traffic : R.color.navigation_eta_text_color_with_traffic;
    }

    private void initialiseItem() {
        this.adapter.a(this.infoBarItems);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setData(com.mappls.sdk.navigation.ui.navigation.infobar.d dVar) {
        int i;
        this.binding.textViewTotalDistanceLeft.setText(dVar.e());
        this.binding.textViewTotalTimeLeft.setText(dVar.f());
        this.binding.textViewReachEta.setText(dVar.c());
        this.binding.textViewTotalTimeLeft.setTextColor(ContextCompat.getColor(getContext(), getCongestionPercentage(dVar.b(), dVar.d())));
        try {
            i = (int) (100.0d - ((dVar.a().getLeftDistance() * 100) / dVar.g()));
        } catch (Exception e2) {
            e2.printStackTrace();
            i = 0;
        }
        updateProgress(i);
        TextView textView = this.binding.remainingDistanceTextView;
        textView.setText(dVar.e() + " to go");
        setTextColor();
    }

    private void setTextColor() {
        String charSequence = this.binding.remainingDistanceTextView.getText().toString();
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        this.binding.remainingDistanceTextView.setText(getColorSpannableString(charSequence.split("to go")[0], "to go", com.mappls.sdk.navigation.ui.theme.a.b(getContext(), R.attr.navigationViewDistanceValue), com.mappls.sdk.navigation.ui.theme.a.b(getContext(), R.attr.navigationViewDistanceText)));
    }

    private void updateProgress(int i) {
        this.binding.navProgress.setProgress(i);
    }

    public void isTrafficEnable(boolean z) {
        int i;
        this.enableTraffic = z;
        for (com.mappls.sdk.navigation.ui.navigation.infobar.c cVar : this.infoBarItems) {
            if (cVar.b() == 2) {
                if (z) {
                    cVar.a("Hide Traffic");
                    i = R.attr.navigationViewTrafficOffDrawable;
                } else {
                    cVar.a("Show Traffic");
                    i = R.attr.navigationViewTrafficOnDrawable;
                }
                cVar.a(i);
            }
        }
        this.adapter.a(this.infoBarItems);
    }

    @Override // com.mappls.sdk.navigation.ui.navigation.infobar.BaseInfobarBottomSheet, androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        RecyclerView recyclerView;
        GridLayoutManager gridLayoutManager;
        super.onAttachedToWindow();
        if (getResources().getConfiguration().orientation == 2) {
            recyclerView = this.binding.rvBottomItem;
            gridLayoutManager = new GridLayoutManager(getContext(), 4);
        } else {
            recyclerView = this.binding.rvBottomItem;
            gridLayoutManager = new GridLayoutManager(getContext(), 3);
        }
        recyclerView.setLayoutManager(gridLayoutManager);
        this.binding.rvBottomItem.setLayoutManager(new GridLayoutManager(getContext(), 3));
        this.binding.rvBottomItem.setAdapter(this.adapter);
        initialiseItem();
        this.adapter.a(new a());
        toogleTheme();
        setClickable(false);
        setFocusableInTouchMode(false);
        BottomSheetBehavior from = BottomSheetBehavior.from(this.binding.optionsRecyclerViewContainer);
        this.mBottomSheetBehavior = from;
        from.setHideable(false);
        this.mBottomSheetBehavior.setPeekHeight(getResources().getDimensionPixelSize(R.dimen.navigation_bottom_sheet_top_shadow) + getResources().getDimensionPixelSize(R.dimen.navigation_bottom_sheet_header) + 1);
        this.mBottomSheetBehavior.setState(4);
        this.mBottomSheetBehavior.addBottomSheetCallback(new b());
        this.binding.ivBottomSheetArrow.setOnClickListener(new c());
        this.binding.imageRouteOverview.setOnClickListener(new d());
        this.binding.stopNavigation.setOnClickListener(new e());
    }

    public void setOnInfobarCallback(com.mappls.sdk.navigation.ui.navigation.infobar.e eVar) {
        this.onInfobarCallback = eVar;
    }

    public void showRouteOverview(boolean z) {
        this.binding.imageRouteOverview.setVisibility(z ? 0 : 8);
    }

    public void subscribe(LifecycleOwner lifecycleOwner, com.mappls.sdk.navigation.ui.navigation.a aVar) {
        this.lifecycleOwner = lifecycleOwner;
        lifecycleOwner.getLifecycle().addObserver(this);
        this.navigationViewModel = aVar;
        aVar.c.observe(this.lifecycleOwner, new f());
        aVar.f13009a.observe(this.lifecycleOwner, new g());
    }

    public void toogleTheme() {
        getContext();
        if (com.mappls.sdk.navigation.ui.theme.a.a()) {
            this.binding.topSeparatorViewNightMode.setVisibility(0);
            this.binding.topSeparatorViewDayMode.setVisibility(8);
        } else {
            this.binding.topSeparatorViewNightMode.setVisibility(8);
            this.binding.topSeparatorViewDayMode.setVisibility(0);
        }
        this.binding.verticalSeperatorView.setBackgroundColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), R.attr.navigationViewBottomSheetVerticalSeperator));
        CardView cardView = this.binding.cardView;
        Context context = getContext();
        int i = R.attr.navigationViewPrimary;
        cardView.setCardBackgroundColor(com.mappls.sdk.navigation.ui.theme.a.b(context, i));
        TextView textView = this.binding.textViewTotalDistanceLeft;
        Context context2 = getContext();
        int i2 = R.attr.navigationTextColorSecondary;
        textView.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(context2, i2));
        this.binding.textViewReachEta.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i2));
        this.binding.ivBottomSheetArrow.setImageDrawable(com.mappls.sdk.navigation.ui.theme.a.d(getContext(), R.attr.navigationViewBottomSheetArrow));
        this.binding.imageRouteOverview.setImageDrawable(com.mappls.sdk.navigation.ui.theme.a.d(getContext(), R.attr.navigationViewRouteOverviewDrawable));
        this.binding.stopNavigation.setImageDrawable(com.mappls.sdk.navigation.ui.theme.a.d(getContext(), R.attr.navigationViewStopDrawable));
        this.binding.optionsRecyclerViewContainer.setBackgroundColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i));
        this.binding.destinationText.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), i2));
        this.binding.carSpeedLightImageview.setImageResource(com.mappls.sdk.navigation.ui.theme.a.e(getContext(), R.attr.navigationViewCarSpeedDrawable));
        this.binding.navDestinationImageview.setImageResource(com.mappls.sdk.navigation.ui.theme.a.e(getContext(), R.attr.navigationViewDestinationDrawable));
        this.binding.navProgress.getProgressDrawable().setColorFilter(new PorterDuffColorFilter(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), R.attr.navigationViewDistanceProgress), PorterDuff.Mode.SRC_IN));
        for (com.mappls.sdk.navigation.ui.navigation.infobar.c cVar : this.infoBarItems) {
            if (cVar.b() == 0) {
                getContext();
                cVar.a(com.mappls.sdk.navigation.ui.theme.a.a() ? "Day Theme" : "Night Theme");
            }
        }
        this.adapter.a(this.infoBarItems);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void unsubscribe() {
        com.mappls.sdk.navigation.ui.navigation.a aVar = this.navigationViewModel;
        if (aVar != null) {
            aVar.f13009a.removeObservers(this.lifecycleOwner);
        }
    }

    public void updateOptions(NavigationOptions navigationOptions) {
        if (navigationOptions != null) {
            if (navigationOptions.showDayNightOption().booleanValue()) {
                List<com.mappls.sdk.navigation.ui.navigation.infobar.c> list = this.infoBarItems;
                getContext();
                list.add(new com.mappls.sdk.navigation.ui.navigation.infobar.c(0, com.mappls.sdk.navigation.ui.theme.a.a() ? "Day Theme" : "Night Theme", R.attr.navigationDayNightThemeDrawable));
            }
            this.infoBarItems.add(new com.mappls.sdk.navigation.ui.navigation.infobar.c(1, getResources().getString(R.string.direction_list), R.attr.navigationDirectionListDrawable));
            if (navigationOptions.showTrafficOption().booleanValue()) {
                this.infoBarItems.add(new com.mappls.sdk.navigation.ui.navigation.infobar.c(2, this.enableTraffic ? "Show Traffic" : "Hide Traffic", R.attr.navigationViewTrafficOnDrawable));
            }
            if (navigationOptions.showNavigationSettingsOption().booleanValue()) {
                this.infoBarItems.add(new com.mappls.sdk.navigation.ui.navigation.infobar.c(3, getResources().getString(R.string.settings), R.attr.navigationViewSettingsDrawable));
            }
            if (this.binding.rvBottomItem.getAdapter() != null) {
                this.adapter.a(this.infoBarItems);
            }
        }
    }
}
