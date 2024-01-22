package com.mappls.sdk.plugin.annotation;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mappls.sdk.geojson.Geometry;
import com.mappls.sdk.gestures.MoveDistancesObject;
import com.mappls.sdk.maps.Projection;
/* loaded from: classes11.dex */
public abstract class Annotation<T extends Geometry> {
    public static final String ID_DATA = "custom_data";
    public static final String ID_KEY = "id";
    public T geometry;
    private boolean isDraggable;
    public JsonObject jsonObject;

    public Annotation(long j, JsonObject jsonObject, T t) {
        this.jsonObject = jsonObject;
        jsonObject.addProperty("id", Long.valueOf(j));
        this.geometry = t;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Annotation annotation = (Annotation) obj;
        if (this.isDraggable == annotation.isDraggable && this.jsonObject.equals(annotation.jsonObject)) {
            return this.geometry.equals(annotation.geometry);
        }
        return false;
    }

    @Nullable
    @Keep
    public JsonElement getData() {
        return this.jsonObject.get(ID_DATA);
    }

    public JsonObject getFeature() {
        return this.jsonObject;
    }

    @Keep
    public T getGeometry() {
        T t = this.geometry;
        if (t != null) {
            return t;
        }
        throw new IllegalStateException();
    }

    public long getId() {
        return this.jsonObject.get("id").getAsLong();
    }

    public abstract String getName();

    @Nullable
    public abstract Geometry getOffsetGeometry(@NonNull Projection projection, @NonNull MoveDistancesObject moveDistancesObject, float f, float f2);

    public int hashCode() {
        return (((this.jsonObject.hashCode() * 31) + this.geometry.hashCode()) * 31) + (this.isDraggable ? 1 : 0);
    }

    @Keep
    public boolean isDraggable() {
        return this.isDraggable;
    }

    @Keep
    public void setData(@Nullable JsonElement jsonElement) {
        this.jsonObject.add(ID_DATA, jsonElement);
    }

    @Keep
    public void setDraggable(boolean z) {
        this.isDraggable = z;
    }

    @Keep
    public void setGeometry(T t) {
        this.geometry = t;
    }

    public abstract void setUsedDataDrivenProperties();

    public String toString() {
        return getName() + "{geometry=" + this.geometry + ", properties=" + this.jsonObject + ", isDraggable=" + this.isDraggable + '}';
    }
}
