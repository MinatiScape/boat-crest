package com.mappls.sdk.maps.module.loader;

import com.mappls.sdk.maps.LibraryLoader;
import com.mappls.sdk.maps.LibraryLoaderProvider;
/* loaded from: classes11.dex */
public class LibraryLoaderProviderImpl implements LibraryLoaderProvider {

    /* loaded from: classes11.dex */
    public static class b extends LibraryLoader {
        public b() {
        }

        @Override // com.mappls.sdk.maps.LibraryLoader
        public void load(String str) {
            System.loadLibrary(str);
        }
    }

    @Override // com.mappls.sdk.maps.LibraryLoaderProvider
    public LibraryLoader getDefaultLibraryLoader() {
        return new b();
    }
}
