package com.mappls.sdk.services.api.autosuggest.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
@Keep
/* loaded from: classes11.dex */
public class AutoSuggestAtlasResponse {
    @SerializedName("explanation")
    @Expose
    private AtlasExplaination explaination;
    @SerializedName("suggestedLocations")
    @Expose
    private ArrayList<ELocation> suggestedLocations = new ArrayList<>();
    @SerializedName("userAddedLocations")
    @Expose
    private ArrayList<ELocation> userAddedLocations = new ArrayList<>();
    @SerializedName("suggestedSearches")
    @Expose
    private ArrayList<SuggestedSearchAtlas> suggestedSearches = new ArrayList<>();

    public AtlasExplaination getExplaination() {
        return this.explaination;
    }

    public ArrayList<ELocation> getSuggestedLocations() {
        return this.suggestedLocations;
    }

    public ArrayList<SuggestedSearchAtlas> getSuggestedSearches() {
        return this.suggestedSearches;
    }

    public ArrayList<ELocation> getUserAddedLocations() {
        return this.userAddedLocations;
    }
}
