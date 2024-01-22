package com.mappls.sdk.plugins.places.autocomplete.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.mappls.sdk.plugins.places.R;
import com.mappls.sdk.plugins.places.autocomplete.model.MapplsFavoritePlace;
import com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions;
import com.mappls.sdk.plugins.places.autocomplete.ui.SearchView;
import com.mappls.sdk.plugins.places.autocomplete.viewmodel.a;
import com.mappls.sdk.plugins.places.common.PlaceConstants;
import com.mappls.sdk.services.api.autosuggest.model.AutoSuggestAtlasResponse;
import com.mappls.sdk.services.api.autosuggest.model.ELocation;
import com.mappls.sdk.services.api.autosuggest.model.SuggestedSearchAtlas;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import timber.log.Timber;
@Keep
/* loaded from: classes10.dex */
public class PlaceAutocompleteFragment extends Fragment implements ResultClickCallback, SearchView.b, SearchView.a, ViewTreeObserver.OnScrollChangedListener, SearchView.c, View.OnClickListener {
    public static final String TAG = "PlaceAutocompleteFragment";
    private CardView currentLocationView;
    private View dropShadowView;
    private Integer favoriteCount;
    private Integer historyCount;
    private int mode;
    private View offlineResultView;
    private PlaceOptions placeOptions;
    private PlaceSelectionListener placeSelectionListener;
    private CardView poorConnectionView;
    private NestedScrollView resultScrollView;
    private View rootView;
    private ResultView searchHistoryView;
    private ResultView searchResultView;
    private SearchView searchView;
    private ResultView starredView;
    private SuggestedSearchSelectionListener suggestedSearchSelectionListener;
    private com.mappls.sdk.plugins.places.autocomplete.viewmodel.a viewModel;
    private boolean isSelectItem = false;
    private String query = "";
    private Handler handler = new Handler(Looper.getMainLooper());
    private List<String> historyPlaceIds = new ArrayList();

    /* loaded from: classes10.dex */
    public class a implements Runnable {
        public final /* synthetic */ CharSequence h;

        public a(CharSequence charSequence) {
            this.h = charSequence;
        }

        @Override // java.lang.Runnable
        public final void run() {
            PlaceAutocompleteFragment.this.query = this.h.toString();
            PlaceAutocompleteFragment.this.viewModel.a(this.h);
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Observer<com.mappls.sdk.plugins.places.common.utils.d<AutoSuggestAtlasResponse>> {
        public b() {
        }

        @Override // androidx.lifecycle.Observer
        public final void onChanged(@NonNull com.mappls.sdk.plugins.places.common.utils.d<AutoSuggestAtlasResponse> dVar) {
            com.mappls.sdk.plugins.places.common.utils.d<AutoSuggestAtlasResponse> dVar2 = dVar;
            int i = dVar2.f13143a;
            if (i == 2) {
                PlaceAutocompleteFragment.this.searchView.showProgress();
            } else if (i == 1) {
                PlaceAutocompleteFragment.this.poorConnectionView.setVisibility(8);
                PlaceAutocompleteFragment.this.searchView.hideProgress();
                PlaceAutocompleteFragment.this.updateSearchResultView(dVar2.b);
            } else {
                PlaceAutocompleteFragment.this.poorConnectionView.setVisibility(8);
                PlaceAutocompleteFragment.this.searchView.hideProgress();
                Timber.v(dVar2.c, new Object[0]);
                PlaceAutocompleteFragment.this.showOfflineView(dVar2.c);
            }
        }
    }

    /* loaded from: classes10.dex */
    public class c implements Observer<List<MapplsFavoritePlace>> {
        public c() {
        }

        @Override // androidx.lifecycle.Observer
        public final void onChanged(List<MapplsFavoritePlace> list) {
            PlaceAutocompleteFragment.this.updateFavoritePlacesView(list);
        }
    }

    /* loaded from: classes10.dex */
    public class d implements Observer<List<com.mappls.sdk.plugins.places.autocomplete.data.entity.a>> {
        public d() {
        }

        @Override // androidx.lifecycle.Observer
        public final void onChanged(@Nullable List<com.mappls.sdk.plugins.places.autocomplete.data.entity.a> list) {
            PlaceAutocompleteFragment.this.updateSearchHistoryView(list);
        }
    }

    private void bindClickListeners() {
        this.searchHistoryView.setOnItemClickListener(this);
        this.searchResultView.setOnItemClickListener(this);
        this.starredView.setOnItemClickListener(this);
        this.searchView.setBackButtonListener(this);
        this.searchView.setQueryListener(this);
        if (this.placeOptions.enableTextSearch().booleanValue()) {
            this.searchView.setOnSearchClick(this);
        }
        this.currentLocationView.setOnClickListener(this);
    }

    private void bindViews() {
        this.searchHistoryView = (ResultView) this.rootView.findViewById(R.id.searchHistoryResultsView);
        this.resultScrollView = (NestedScrollView) this.rootView.findViewById(R.id.scroll_view_results);
        this.offlineResultView = this.rootView.findViewById(R.id.offlineResultView);
        this.searchResultView = (ResultView) this.rootView.findViewById(R.id.searchResultView);
        this.dropShadowView = this.rootView.findViewById(R.id.scroll_drop_shadow);
        this.starredView = (ResultView) this.rootView.findViewById(R.id.favoriteResultView);
        this.searchView = (SearchView) this.rootView.findViewById(R.id.searchView);
        View findViewById = this.rootView.findViewById(R.id.root_layout);
        this.rootView = findViewById;
        this.poorConnectionView = (CardView) findViewById.findViewById(R.id.poor_connection_view);
        this.currentLocationView = (CardView) this.rootView.findViewById(R.id.current_location_layout);
    }

    public static PlaceAutocompleteFragment newInstance() {
        PlaceAutocompleteFragment placeAutocompleteFragment = new PlaceAutocompleteFragment();
        placeAutocompleteFragment.setArguments(new Bundle());
        return placeAutocompleteFragment;
    }

    public static PlaceAutocompleteFragment newInstance(@Nullable PlaceOptions placeOptions) {
        PlaceAutocompleteFragment placeAutocompleteFragment = new PlaceAutocompleteFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(PlaceConstants.PLACE_OPTIONS, placeOptions);
        placeAutocompleteFragment.setArguments(bundle);
        return placeAutocompleteFragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showOfflineView(String str) {
        this.searchResultView.setVisibility(8);
        this.offlineResultView.setVisibility(0);
        ((TextView) this.offlineResultView.findViewById(R.id.tv_error)).setText(str);
        if (com.mappls.sdk.plugins.places.common.utils.a.a(getContext())) {
            this.offlineResultView.findViewById(R.id.error_image).setVisibility(4);
        } else {
            this.offlineResultView.findViewById(R.id.error_image).setVisibility(0);
        }
    }

    private void styleView() {
        View view;
        int dimension;
        int dimension2;
        float f;
        int i;
        if (this.placeOptions == null || (view = this.rootView) == null) {
            return;
        }
        TextView textView = (TextView) view.findViewById(R.id.tv_powered);
        TextView textView2 = (TextView) this.rootView.findViewById(R.id.tv_powered_bottom);
        textView.setVisibility(this.placeOptions.showPoweredByText().booleanValue() ? 0 : 8);
        textView2.setVisibility(this.placeOptions.showPoweredByText().booleanValue() ? 0 : 8);
        LinearLayout linearLayout = (LinearLayout) this.rootView.findViewById(R.id.layout_signature);
        LinearLayout linearLayout2 = (LinearLayout) this.rootView.findViewById(R.id.layout_signature_bottom);
        CardView cardView = (CardView) this.rootView.findViewById(R.id.signature_layout_top);
        CardView cardView2 = (CardView) this.rootView.findViewById(R.id.signature_layout_bottom);
        ImageView imageView = (ImageView) this.rootView.findViewById(R.id.iv_logo);
        ImageView imageView2 = (ImageView) this.rootView.findViewById(R.id.iv_logo_bottom);
        TextView textView3 = (TextView) this.rootView.findViewById(R.id.tv_current_location);
        ImageView imageView3 = (ImageView) this.rootView.findViewById(R.id.iv_current_location);
        if (this.placeOptions.logoSize() == 10) {
            dimension = (int) getResources().getDimension(R.dimen.mappls_search_plugins_logo_height_large);
            dimension2 = (int) getResources().getDimension(R.dimen.mappls_search_plugins_logo_width_large);
            f = 16.0f;
        } else if (this.placeOptions.logoSize() == 8) {
            dimension = (int) getResources().getDimension(R.dimen.mappls_search_plugins_logo_height_small);
            dimension2 = (int) getResources().getDimension(R.dimen.mappls_search_plugins_logo_width_small);
            f = 12.0f;
        } else {
            dimension = (int) getResources().getDimension(R.dimen.mappls_search_plugins_logo_height_medium);
            dimension2 = (int) getResources().getDimension(R.dimen.mappls_search_plugins_logo_width_medium);
            f = 14.0f;
        }
        textView.setTextSize(2, f);
        textView2.setTextSize(2, f);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(dimension2, dimension));
        imageView2.setLayoutParams(new LinearLayout.LayoutParams(dimension2, dimension));
        int i2 = this.placeOptions.attributionVerticalAlignment() == 4 ? 17 : this.placeOptions.attributionVerticalAlignment() == 5 ? GravityCompat.END : GravityCompat.START;
        linearLayout.setGravity(i2);
        linearLayout2.setGravity(i2);
        if (this.placeOptions.isShowCurrentLocation().booleanValue()) {
            i = 0;
            this.currentLocationView.setVisibility(0);
        } else {
            i = 0;
        }
        this.rootView.setBackgroundColor(this.placeOptions.backgroundColor());
        cardView.setVisibility(this.placeOptions.attributionHorizontalAlignment() == 6 ? i : 8);
        cardView2.setVisibility(this.placeOptions.attributionHorizontalAlignment() == 7 ? i : 8);
        View findViewById = this.rootView.findViewById(R.id.toolbar);
        if (findViewById != null) {
            findViewById.setBackgroundColor(this.placeOptions.toolbarColor());
            this.searchView.setTintColor(this.placeOptions.toolbarTintColor());
        }
        if (this.placeOptions.statusBarColor() != null && Build.VERSION.SDK_INT >= 21) {
            ((Activity) this.rootView.getContext()).getWindow().setStatusBarColor(this.placeOptions.statusBarColor().intValue());
        }
        textView3.setTextColor(this.placeOptions.currentLocationTextColor().intValue());
        imageView3.setImageResource(this.placeOptions.currentLocationIcon().intValue());
        SearchView searchView = (SearchView) this.rootView.findViewById(R.id.searchView);
        this.searchView = searchView;
        searchView.setHint(this.placeOptions.hint() == null ? getString(R.string.mappls_search_autocomplete_search_hint) : this.placeOptions.hint());
    }

    private void subscribe() {
        this.viewModel.f13141a.observe(getViewLifecycleOwner(), new b());
        this.viewModel.b.observe(getViewLifecycleOwner(), new c());
        if (this.placeOptions.saveHistory().booleanValue()) {
            com.mappls.sdk.plugins.places.autocomplete.a.a(this.viewModel.d()).a().observe(getViewLifecycleOwner(), new d());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFavoritePlacesView(List<MapplsFavoritePlace> list) {
        this.starredView.getResultsList().clear();
        if (this.placeOptions.favoriteCount() != null) {
            this.favoriteCount = this.placeOptions.favoriteCount();
            for (int i = 0; i < this.favoriteCount.intValue() && i < list.size(); i++) {
                this.starredView.getResultsList().add(new com.mappls.sdk.plugins.places.autocomplete.model.d(list.get(i)));
            }
        } else {
            for (MapplsFavoritePlace mapplsFavoritePlace : list) {
                this.starredView.getResultsList().add(new com.mappls.sdk.plugins.places.autocomplete.model.d(mapplsFavoritePlace));
            }
        }
        ResultView resultView = this.starredView;
        resultView.setVisibility(resultView.getResultsList().isEmpty() ? 8 : 0);
        this.starredView.notifyDataSetChanged();
    }

    public Integer getHistoryCount() {
        return this.historyCount;
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.ui.SearchView.a
    public void onBackButtonPress() {
        if (this.placeSelectionListener != null) {
            com.mappls.sdk.plugins.places.common.utils.c.a(this.searchView);
            this.placeSelectionListener.onCancel();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.current_location_layout) {
            com.mappls.sdk.plugins.places.common.utils.c.a(this.searchView);
            PlaceSelectionListener placeSelectionListener = this.placeSelectionListener;
            if (placeSelectionListener != null) {
                placeSelectionListener.requestForCurrentLocation();
            }
        }
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.ui.ResultClickCallback
    public void onClick(com.mappls.sdk.plugins.places.autocomplete.model.d dVar) {
        this.isSelectItem = true;
        com.mappls.sdk.plugins.places.common.utils.c.a(this.searchView);
        if (dVar.c() == 4) {
            if (this.suggestedSearchSelectionListener != null) {
                this.searchResultView.getResultsList().clear();
                this.searchResultView.notifyDataSetChanged();
                this.searchResultView.setVisibility(8);
                this.suggestedSearchSelectionListener.onSuggestedSearchSelected(dVar.b());
                return;
            }
            return;
        }
        if (dVar.c() == 2) {
            if (dVar.d().placeName != null) {
                SearchView searchView = this.searchView;
                searchView.setText(dVar.d().placeName.trim() + ", " + dVar.d().placeAddress);
            }
            this.viewModel.a(dVar.d());
            this.viewModel.a(dVar.d(), this.query);
            if (this.placeSelectionListener == null) {
                return;
            }
        } else if (dVar.c() != 1) {
            if (dVar.a().getPlaceName() != null) {
                SearchView searchView2 = this.searchView;
                searchView2.setText(dVar.a().getPlaceName().trim() + ", " + dVar.a().getPlaceAddress());
            }
            if (this.placeSelectionListener != null) {
                this.searchResultView.getResultsList().clear();
                this.searchResultView.notifyDataSetChanged();
                this.searchResultView.setVisibility(8);
                this.placeSelectionListener.onFavoritePlaceSelected(dVar.a());
                return;
            }
            return;
        } else {
            if (dVar.d().placeName != null) {
                SearchView searchView3 = this.searchView;
                searchView3.setText(dVar.d().placeName.trim() + ", " + dVar.d().placeAddress);
            }
            this.viewModel.a(dVar.d());
            if (this.placeSelectionListener == null) {
                return;
            }
        }
        this.searchResultView.getResultsList().clear();
        this.searchResultView.notifyDataSetChanged();
        this.searchResultView.setVisibility(8);
        this.placeSelectionListener.onPlaceSelected(dVar.d());
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.placeOptions = (PlaceOptions) arguments.getParcelable(PlaceConstants.PLACE_OPTIONS);
        }
        if (this.placeOptions == null) {
            this.placeOptions = PlaceOptions.builder().build();
        }
        int viewMode = this.placeOptions.viewMode();
        this.mode = viewMode;
        this.rootView = layoutInflater.inflate(viewMode == 2 ? R.layout.mappls_search_fragment_autocomplete_card : R.layout.mappls_search_fragment_autocomplete_full, viewGroup, false);
        bindViews();
        bindClickListeners();
        return this.rootView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        NestedScrollView nestedScrollView = this.resultScrollView;
        if (nestedScrollView != null) {
            nestedScrollView.getViewTreeObserver().removeOnScrollChangedListener(this);
        }
        this.placeSelectionListener = null;
        super.onDestroyView();
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.ui.SearchView.b
    public void onQueryChange(CharSequence charSequence) {
        this.handler.removeCallbacksAndMessages(null);
        if (this.placeOptions.saveHistory().booleanValue()) {
            com.mappls.sdk.plugins.places.autocomplete.a.a(this.viewModel.d()).a(charSequence.toString());
        }
        if (charSequence.length() > 0) {
            this.viewModel.a(charSequence.toString());
        } else {
            this.viewModel.c();
        }
        if (!com.mappls.sdk.plugins.places.common.utils.a.a(getContext())) {
            showOfflineView(getString(R.string.mappls_search_offline_message));
            return;
        }
        this.offlineResultView.setVisibility(8);
        if (this.isSelectItem || charSequence.length() < this.placeOptions.internalMinCharactersForSearch().intValue()) {
            this.searchView.hideProgress();
            this.viewModel.b();
            this.searchResultView.getResultsList().clear();
            ResultView resultView = this.searchResultView;
            resultView.setVisibility(resultView.getResultsList().isEmpty() ? 8 : 0);
            this.searchResultView.notifyDataSetChanged();
        } else {
            this.handler.postDelayed(new a(charSequence), this.placeOptions.internalDebounce().intValue());
            if (!com.mappls.sdk.plugins.places.common.utils.a.b(getContext())) {
                this.poorConnectionView.setVisibility(0);
                this.isSelectItem = false;
            }
        }
        this.poorConnectionView.setVisibility(8);
        this.isSelectItem = false;
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public void onScrollChanged() {
        NestedScrollView nestedScrollView = this.resultScrollView;
        if (nestedScrollView != null) {
            if (nestedScrollView.getScrollY() != 0) {
                com.mappls.sdk.plugins.places.common.utils.c.a(this.resultScrollView);
            }
            if (this.mode == 1) {
                return;
            }
            this.dropShadowView.setVisibility(this.resultScrollView.canScrollVertically(-1) ? 0 : 4);
        }
    }

    @Override // com.mappls.sdk.plugins.places.autocomplete.ui.SearchView.c
    public void onSearchClick(String str) {
        if (!this.placeOptions.enableTextSearch().booleanValue() || str.length() < this.placeOptions.internalMinCharactersForSearch().intValue()) {
            return;
        }
        this.query = str;
        this.viewModel.b(str);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        com.mappls.sdk.plugins.places.autocomplete.viewmodel.a aVar = (com.mappls.sdk.plugins.places.autocomplete.viewmodel.a) new ViewModelProvider(this, new a.b(getActivity().getApplication(), this.placeOptions)).get(com.mappls.sdk.plugins.places.autocomplete.viewmodel.a.class);
        this.viewModel = aVar;
        aVar.e();
        this.viewModel.a();
        this.viewModel.c();
        subscribe();
        this.resultScrollView.getViewTreeObserver().addOnScrollChangedListener(this);
        styleView();
    }

    public void setOnPlaceSelectedListener(PlaceSelectionListener placeSelectionListener) {
        this.placeSelectionListener = placeSelectionListener;
    }

    public void setSuggestedSearchSelectionListener(SuggestedSearchSelectionListener suggestedSearchSelectionListener) {
        this.suggestedSearchSelectionListener = suggestedSearchSelectionListener;
    }

    public void updateSearchHistoryView(@Nullable List<com.mappls.sdk.plugins.places.autocomplete.data.entity.a> list) {
        this.searchHistoryView.getResultsList().clear();
        this.historyPlaceIds.clear();
        if (list != null) {
            if (this.placeOptions.historyCount() != null) {
                this.historyCount = this.placeOptions.historyCount();
                for (int i = 0; i < this.historyCount.intValue() && i < list.size(); i++) {
                    this.historyPlaceIds.add(list.get(i).d());
                    this.searchHistoryView.getResultsList().add(new com.mappls.sdk.plugins.places.autocomplete.model.d(list.get(i).b(), 1));
                }
            } else {
                for (com.mappls.sdk.plugins.places.autocomplete.data.entity.a aVar : list) {
                    this.historyPlaceIds.add(aVar.d());
                    this.searchHistoryView.getResultsList().add(new com.mappls.sdk.plugins.places.autocomplete.model.d(aVar.b(), 1));
                }
            }
        }
        this.searchHistoryView.notifyDataSetChanged();
        ResultView resultView = this.searchHistoryView;
        resultView.setVisibility(resultView.getResultsList().isEmpty() ? 8 : 0);
    }

    public void updateSearchResultView(@Nullable AutoSuggestAtlasResponse autoSuggestAtlasResponse) {
        this.searchResultView.getResultsList().clear();
        if (autoSuggestAtlasResponse != null) {
            if (autoSuggestAtlasResponse.getSuggestedSearches() != null) {
                Iterator<SuggestedSearchAtlas> it = autoSuggestAtlasResponse.getSuggestedSearches().iterator();
                while (it.hasNext()) {
                    this.searchResultView.getResultsList().add(new com.mappls.sdk.plugins.places.autocomplete.model.d(it.next()));
                }
            }
            ArrayList arrayList = new ArrayList();
            if (autoSuggestAtlasResponse.getSuggestedLocations() != null) {
                arrayList.addAll(autoSuggestAtlasResponse.getSuggestedLocations());
            }
            if (this.placeOptions.userAddedLocationEnable().booleanValue() && autoSuggestAtlasResponse.getUserAddedLocations() != null) {
                arrayList.addAll(autoSuggestAtlasResponse.getUserAddedLocations());
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                ELocation eLocation = (ELocation) it2.next();
                if (!this.historyPlaceIds.contains(eLocation.mapplsPin)) {
                    this.searchResultView.getResultsList().add(new com.mappls.sdk.plugins.places.autocomplete.model.d(eLocation, 2));
                }
            }
        }
        ResultView resultView = this.searchResultView;
        resultView.setVisibility(resultView.getResultsList().isEmpty() ? 8 : 0);
        this.searchResultView.notifyDataSetChanged();
        if (this.offlineResultView.getVisibility() == 0) {
            this.offlineResultView.setVisibility(8);
        }
    }
}
