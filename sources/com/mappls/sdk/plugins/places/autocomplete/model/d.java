package com.mappls.sdk.plugins.places.autocomplete.model;

import com.mappls.sdk.services.api.autosuggest.model.ELocation;
import com.mappls.sdk.services.api.autosuggest.model.SuggestedSearchAtlas;
/* loaded from: classes10.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    public ELocation f13136a;
    public SuggestedSearchAtlas b;
    public final int c;
    public MapplsFavoritePlace d;

    public d(MapplsFavoritePlace mapplsFavoritePlace) {
        this.d = mapplsFavoritePlace;
        this.c = 3;
    }

    public d(ELocation eLocation, int i) {
        this.f13136a = eLocation;
        this.c = i;
    }

    public d(SuggestedSearchAtlas suggestedSearchAtlas) {
        this.b = suggestedSearchAtlas;
        this.c = 4;
    }

    public final MapplsFavoritePlace a() {
        return this.d;
    }

    public final SuggestedSearchAtlas b() {
        return this.b;
    }

    public final int c() {
        return this.c;
    }

    public final ELocation d() {
        return this.f13136a;
    }
}
