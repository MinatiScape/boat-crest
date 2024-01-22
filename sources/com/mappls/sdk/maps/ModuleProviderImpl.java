package com.mappls.sdk.maps;

import androidx.annotation.NonNull;
import com.mappls.sdk.maps.http.HttpRequest;
import com.mappls.sdk.maps.module.http.HttpRequestImpl;
import com.mappls.sdk.maps.module.loader.LibraryLoaderProviderImpl;
/* loaded from: classes11.dex */
public class ModuleProviderImpl implements ModuleProvider {
    @Override // com.mappls.sdk.maps.ModuleProvider
    @NonNull
    public HttpRequest createHttpRequest() {
        return new HttpRequestImpl();
    }

    @Override // com.mappls.sdk.maps.ModuleProvider
    @NonNull
    public LibraryLoaderProvider createLibraryLoaderProvider() {
        return new LibraryLoaderProviderImpl();
    }
}
