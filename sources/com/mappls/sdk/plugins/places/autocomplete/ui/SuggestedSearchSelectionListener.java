package com.mappls.sdk.plugins.places.autocomplete.ui;

import androidx.annotation.Keep;
import com.mappls.sdk.services.api.autosuggest.model.SuggestedSearchAtlas;
@Keep
/* loaded from: classes10.dex */
public interface SuggestedSearchSelectionListener {
    void onSuggestedSearchSelected(SuggestedSearchAtlas suggestedSearchAtlas);
}
