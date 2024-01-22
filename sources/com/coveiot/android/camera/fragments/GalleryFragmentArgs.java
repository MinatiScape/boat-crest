package com.coveiot.android.camera.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import java.util.HashMap;
/* loaded from: classes3.dex */
public class GalleryFragmentArgs implements NavArgs {

    /* renamed from: a  reason: collision with root package name */
    public final HashMap f4094a;

    @NonNull
    public static GalleryFragmentArgs fromBundle(@NonNull Bundle bundle) {
        GalleryFragmentArgs galleryFragmentArgs = new GalleryFragmentArgs();
        bundle.setClassLoader(GalleryFragmentArgs.class.getClassLoader());
        if (bundle.containsKey("root_directory")) {
            String string = bundle.getString("root_directory");
            if (string != null) {
                galleryFragmentArgs.f4094a.put("root_directory", string);
                return galleryFragmentArgs;
            }
            throw new IllegalArgumentException("Argument \"root_directory\" is marked as non-null but was passed a null value.");
        }
        throw new IllegalArgumentException("Required argument \"root_directory\" is missing and does not have an android:defaultValue");
    }

    @NonNull
    public static GalleryFragmentArgs fromSavedStateHandle(@NonNull SavedStateHandle savedStateHandle) {
        GalleryFragmentArgs galleryFragmentArgs = new GalleryFragmentArgs();
        if (savedStateHandle.contains("root_directory")) {
            String str = (String) savedStateHandle.get("root_directory");
            if (str != null) {
                galleryFragmentArgs.f4094a.put("root_directory", str);
                return galleryFragmentArgs;
            }
            throw new IllegalArgumentException("Argument \"root_directory\" is marked as non-null but was passed a null value.");
        }
        throw new IllegalArgumentException("Required argument \"root_directory\" is missing and does not have an android:defaultValue");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GalleryFragmentArgs galleryFragmentArgs = (GalleryFragmentArgs) obj;
        if (this.f4094a.containsKey("root_directory") != galleryFragmentArgs.f4094a.containsKey("root_directory")) {
            return false;
        }
        return getRootDirectory() == null ? galleryFragmentArgs.getRootDirectory() == null : getRootDirectory().equals(galleryFragmentArgs.getRootDirectory());
    }

    @NonNull
    public String getRootDirectory() {
        return (String) this.f4094a.get("root_directory");
    }

    public int hashCode() {
        return 31 + (getRootDirectory() != null ? getRootDirectory().hashCode() : 0);
    }

    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        if (this.f4094a.containsKey("root_directory")) {
            bundle.putString("root_directory", (String) this.f4094a.get("root_directory"));
        }
        return bundle;
    }

    @NonNull
    public SavedStateHandle toSavedStateHandle() {
        SavedStateHandle savedStateHandle = new SavedStateHandle();
        if (this.f4094a.containsKey("root_directory")) {
            savedStateHandle.set("root_directory", (String) this.f4094a.get("root_directory"));
        }
        return savedStateHandle;
    }

    public String toString() {
        return "GalleryFragmentArgs{rootDirectory=" + getRootDirectory() + "}";
    }

    public GalleryFragmentArgs() {
        this.f4094a = new HashMap();
    }

    /* loaded from: classes3.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final HashMap f4095a;

        public Builder(@NonNull GalleryFragmentArgs galleryFragmentArgs) {
            HashMap hashMap = new HashMap();
            this.f4095a = hashMap;
            hashMap.putAll(galleryFragmentArgs.f4094a);
        }

        @NonNull
        public GalleryFragmentArgs build() {
            return new GalleryFragmentArgs(this.f4095a);
        }

        @NonNull
        public String getRootDirectory() {
            return (String) this.f4095a.get("root_directory");
        }

        @NonNull
        public Builder setRootDirectory(@NonNull String str) {
            if (str != null) {
                this.f4095a.put("root_directory", str);
                return this;
            }
            throw new IllegalArgumentException("Argument \"root_directory\" is marked as non-null but was passed a null value.");
        }

        public Builder(@NonNull String str) {
            HashMap hashMap = new HashMap();
            this.f4095a = hashMap;
            if (str != null) {
                hashMap.put("root_directory", str);
                return;
            }
            throw new IllegalArgumentException("Argument \"root_directory\" is marked as non-null but was passed a null value.");
        }
    }

    public GalleryFragmentArgs(HashMap hashMap) {
        HashMap hashMap2 = new HashMap();
        this.f4094a = hashMap2;
        hashMap2.putAll(hashMap);
    }
}
