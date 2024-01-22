package com.mappls.sdk.maps;

import androidx.annotation.NonNull;
import com.mappls.sdk.maps.http.HttpRequest;
/* loaded from: classes11.dex */
public interface ModuleProvider {
    @NonNull
    HttpRequest createHttpRequest();

    @NonNull
    LibraryLoaderProvider createLibraryLoaderProvider();
}
