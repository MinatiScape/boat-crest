package com.mappls.sdk.navigation.ui.navigation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mappls.sdk.navigation.ui.NavigationOptions;
import com.mappls.sdk.navigation.ui.navigation.infobar.d;
import com.mappls.sdk.services.api.autosuggest.model.ELocation;
/* loaded from: classes11.dex */
public class a extends ViewModel {

    /* renamed from: a  reason: collision with root package name */
    public final MutableLiveData<d> f13009a = new MutableLiveData<>();
    public final MutableLiveData<com.mappls.sdk.navigation.ui.navigation.instructioncontainer.a> b = new MutableLiveData<>();
    public final MutableLiveData<ELocation> c = new MutableLiveData<>();
    public NavigationOptions d;

    public NavigationOptions a() {
        return this.d;
    }

    public void a(NavigationOptions navigationOptions) {
        this.d = navigationOptions;
    }
}
