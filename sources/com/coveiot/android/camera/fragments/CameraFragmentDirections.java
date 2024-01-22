package com.coveiot.android.camera.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.coveiot.android.camera.R;
import java.util.HashMap;
/* loaded from: classes3.dex */
public class CameraFragmentDirections {

    /* loaded from: classes3.dex */
    public static class ActionCameraToGallery implements NavDirections {

        /* renamed from: a  reason: collision with root package name */
        public final HashMap f4093a;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ActionCameraToGallery actionCameraToGallery = (ActionCameraToGallery) obj;
            if (this.f4093a.containsKey("root_directory") != actionCameraToGallery.f4093a.containsKey("root_directory")) {
                return false;
            }
            if (getRootDirectory() == null ? actionCameraToGallery.getRootDirectory() == null : getRootDirectory().equals(actionCameraToGallery.getRootDirectory())) {
                return getActionId() == actionCameraToGallery.getActionId();
            }
            return false;
        }

        @Override // androidx.navigation.NavDirections
        public int getActionId() {
            return R.id.action_camera_to_gallery;
        }

        @Override // androidx.navigation.NavDirections
        @NonNull
        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            if (this.f4093a.containsKey("root_directory")) {
                bundle.putString("root_directory", (String) this.f4093a.get("root_directory"));
            }
            return bundle;
        }

        @NonNull
        public String getRootDirectory() {
            return (String) this.f4093a.get("root_directory");
        }

        public int hashCode() {
            return (((getRootDirectory() != null ? getRootDirectory().hashCode() : 0) + 31) * 31) + getActionId();
        }

        @NonNull
        public ActionCameraToGallery setRootDirectory(@NonNull String str) {
            if (str != null) {
                this.f4093a.put("root_directory", str);
                return this;
            }
            throw new IllegalArgumentException("Argument \"root_directory\" is marked as non-null but was passed a null value.");
        }

        public String toString() {
            return "ActionCameraToGallery(actionId=" + getActionId() + "){rootDirectory=" + getRootDirectory() + "}";
        }

        public ActionCameraToGallery(@NonNull String str) {
            HashMap hashMap = new HashMap();
            this.f4093a = hashMap;
            if (str != null) {
                hashMap.put("root_directory", str);
                return;
            }
            throw new IllegalArgumentException("Argument \"root_directory\" is marked as non-null but was passed a null value.");
        }
    }

    @NonNull
    public static ActionCameraToGallery actionCameraToGallery(@NonNull String str) {
        return new ActionCameraToGallery(str);
    }

    @NonNull
    public static NavDirections actionCameraToPermissions() {
        return new ActionOnlyNavDirections(R.id.action_camera_to_permissions);
    }
}
