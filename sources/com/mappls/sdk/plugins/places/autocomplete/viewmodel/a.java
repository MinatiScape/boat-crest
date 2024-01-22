package com.mappls.sdk.plugins.places.autocomplete.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.google.gson.Gson;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.plugins.places.autocomplete.data.SearchHistoryDatabase;
import com.mappls.sdk.plugins.places.autocomplete.model.MapplsFavoritePlace;
import com.mappls.sdk.plugins.places.autocomplete.model.PlaceOptions;
import com.mappls.sdk.plugins.places.common.utils.d;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest;
import com.mappls.sdk.services.api.autosuggest.MapplsAutosuggestManager;
import com.mappls.sdk.services.api.autosuggest.model.AutoSuggestAtlasResponse;
import com.mappls.sdk.services.api.autosuggest.model.ELocation;
import com.mappls.sdk.services.api.feedback.MapplsFeedback;
import com.mappls.sdk.services.api.feedback.MapplsFeedbackManager;
import com.mappls.sdk.services.api.textsearch.MapplsTextSearch;
import com.mappls.sdk.services.api.textsearch.MapplsTextSearchManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import timber.log.Timber;
/* loaded from: classes10.dex */
public final class a extends AndroidViewModel implements OnResponseCallback<AutoSuggestAtlasResponse> {

    /* renamed from: a  reason: collision with root package name */
    public final MutableLiveData<d<AutoSuggestAtlasResponse>> f13141a;
    public final MutableLiveData<List<MapplsFavoritePlace>> b;
    public MapplsTextSearch.Builder d;
    public MapplsAutoSuggest.Builder e;
    public PlaceOptions f;
    public MapplsAutosuggestManager g;
    public ArrayList h;

    /* renamed from: com.mappls.sdk.plugins.places.autocomplete.viewmodel.a$a  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public class C0662a implements OnResponseCallback<Void> {
        @Override // com.mappls.sdk.services.api.OnResponseCallback
        public final void onError(int i, String str) {
        }

        @Override // com.mappls.sdk.services.api.OnResponseCallback
        public final /* bridge */ /* synthetic */ void onSuccess(Void r1) {
        }
    }

    /* loaded from: classes10.dex */
    public static class b implements ViewModelProvider.Factory {

        /* renamed from: a  reason: collision with root package name */
        public final Application f13142a;
        public final PlaceOptions b;

        public b(@NonNull Application application, @NonNull PlaceOptions placeOptions) {
            this.f13142a = application;
            this.b = placeOptions;
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        @NonNull
        public final <T extends ViewModel> T create(@Nullable Class<T> cls) {
            return new a(this.f13142a, this.b);
        }
    }

    public a(@NonNull Application application, @NonNull PlaceOptions placeOptions) {
        super(application);
        this.f = placeOptions;
        this.f13141a = new MutableLiveData<>();
        this.b = new MutableLiveData<>();
    }

    public final void a() {
        this.e = MapplsAutoSuggest.builder();
        this.d = MapplsTextSearch.builder();
        Point location = this.f.location();
        if (location != null) {
            this.e.setLocation(Double.valueOf(location.latitude()), Double.valueOf(location.longitude()));
            this.d.setLocation(Double.valueOf(location.latitude()), Double.valueOf(location.longitude()));
        }
        String filter = this.f.filter();
        if (filter != null) {
            this.e.filter(filter);
        }
        String pod = this.f.pod();
        if (pod != null) {
            this.e.pod(pod);
        }
        Boolean bool = this.f.tokenizeAddress();
        if (bool != null) {
            this.e.tokenizeAddress(bool);
        }
        Boolean hyperLocal = this.f.hyperLocal();
        if (hyperLocal != null) {
            this.e.hyperLocal(hyperLocal);
        }
        Boolean bridge = this.f.bridge();
        if (bridge != null) {
            this.e.bridge(bridge);
            this.d.bridge(bridge);
        }
        if (this.f.responseLang() != null) {
            this.e.responseLang(this.f.responseLang());
        }
        Double zoom = this.f.zoom();
        if (zoom != null) {
            this.e.zoom(zoom);
        }
    }

    public final void a(ELocation eLocation) {
        if (this.f.saveHistory().booleanValue()) {
            com.mappls.sdk.plugins.places.autocomplete.a.a(d()).a(new com.mappls.sdk.plugins.places.autocomplete.data.entity.a(eLocation.getMapplsPin(), eLocation, eLocation.placeName, eLocation.placeAddress, eLocation.alternateName, System.currentTimeMillis()));
        }
    }

    public final void a(ELocation eLocation, String str) {
        MapplsFeedback.Builder locationName = MapplsFeedback.builder().mapplsPin(eLocation.mapplsPin).index(Integer.valueOf((int) eLocation.orderIndex)).userName("AUTOCOMPLETE_USER").appVersion("2.1.0").typedKeyword(str).locationName(eLocation.placeName);
        if (this.f.location() != null) {
            locationName.latitude(Double.valueOf(this.f.location().latitude()));
            locationName.longitude(Double.valueOf(this.f.location().longitude()));
        }
        MapplsFeedbackManager.newInstance(locationName.build()).call(new C0662a());
    }

    public final void a(CharSequence charSequence) {
        String charSequence2 = charSequence.toString();
        if (charSequence2.isEmpty()) {
            return;
        }
        this.f13141a.setValue(d.a());
        if (charSequence2.length() >= 45) {
            b(charSequence2);
            return;
        }
        MapplsAutoSuggest.Builder builder = this.e;
        if (builder == null) {
            builder = MapplsAutoSuggest.builder();
            this.e = builder;
        }
        MapplsAutosuggestManager newInstance = MapplsAutosuggestManager.newInstance(builder.query(charSequence2).build());
        this.g = newInstance;
        newInstance.call(this);
    }

    public final void a(String str) {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.h.iterator();
        while (it.hasNext()) {
            MapplsFavoritePlace mapplsFavoritePlace = (MapplsFavoritePlace) it.next();
            if (mapplsFavoritePlace.getPlaceName().contains(str) || ((mapplsFavoritePlace.getPlaceAddress() != null && mapplsFavoritePlace.getPlaceAddress().toLowerCase().contains(str.toLowerCase())) || (mapplsFavoritePlace.getMapplsPin() != null && mapplsFavoritePlace.getMapplsPin().toLowerCase().contains(str.toLowerCase())))) {
                arrayList.add(mapplsFavoritePlace);
            }
        }
        this.b.postValue(arrayList);
    }

    public final void b() {
        MapplsAutosuggestManager mapplsAutosuggestManager = this.g;
        if (mapplsAutosuggestManager == null || !mapplsAutosuggestManager.isExecuted()) {
            return;
        }
        this.g.cancel();
    }

    public final void b(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        this.f13141a.setValue(d.a());
        MapplsTextSearch.Builder builder = this.d;
        if (builder == null) {
            builder = MapplsTextSearch.builder();
            this.d = builder;
        }
        builder.query(str);
        MapplsTextSearchManager.newInstance(this.d.build()).call(this);
    }

    public final void c() {
        this.b.postValue(this.h);
    }

    public final SearchHistoryDatabase d() {
        return SearchHistoryDatabase.a(getApplication().getApplicationContext());
    }

    public final void e() {
        List<String> injectedPlaces = this.f.injectedPlaces();
        this.h = new ArrayList();
        if (injectedPlaces == null || injectedPlaces.isEmpty()) {
            return;
        }
        for (String str : injectedPlaces) {
            this.h.add((MapplsFavoritePlace) new Gson().fromJson(str, (Class<Object>) MapplsFavoritePlace.class));
        }
    }

    @Override // com.mappls.sdk.services.api.OnResponseCallback
    public final void onError(int i, String str) {
        MutableLiveData<d<AutoSuggestAtlasResponse>> mutableLiveData;
        if (i == 0) {
            return;
        }
        if (i == 204) {
            mutableLiveData = this.f13141a;
        } else {
            Timber.e(str, new Object[0]);
            mutableLiveData = this.f13141a;
            str = "Something Went wrong";
        }
        mutableLiveData.setValue(d.a(str));
    }

    @Override // com.mappls.sdk.services.api.OnResponseCallback
    public final void onSuccess(AutoSuggestAtlasResponse autoSuggestAtlasResponse) {
        this.f13141a.setValue(d.a(autoSuggestAtlasResponse));
    }
}
