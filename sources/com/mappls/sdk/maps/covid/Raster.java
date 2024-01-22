package com.mappls.sdk.maps.covid;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
/* loaded from: classes11.dex */
public class Raster {

    /* renamed from: a  reason: collision with root package name */
    public String f12698a;
    public JsonObject b;
    public String c;
    public boolean d = false;

    public Raster(String str, JsonObject jsonObject) {
        this.f12698a = str;
        this.b = jsonObject;
    }

    public JsonObject a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public Float getBrightnessMax() {
        if (this.b.get("raster-brightness-max") instanceof JsonNull) {
            return null;
        }
        return Float.valueOf(this.b.get("raster-brightness-max").getAsFloat());
    }

    public Float getBrightnessMin() {
        if (this.b.get("raster-brightness-min") instanceof JsonNull) {
            return null;
        }
        return Float.valueOf(this.b.get("raster-brightness-min").getAsFloat());
    }

    public Float getContrast() {
        if (this.b.get("raster-contrast") instanceof JsonNull) {
            return null;
        }
        return Float.valueOf(this.b.get("raster-contrast").getAsFloat());
    }

    public Float getFadeDuration() {
        if (this.b.get("raster-fade-duration") instanceof JsonNull) {
            return null;
        }
        return Float.valueOf(this.b.get("raster-fade-duration").getAsFloat());
    }

    public Float getHueRotate() {
        if (this.b.get("raster-hue-rotate") instanceof JsonNull) {
            return null;
        }
        return Float.valueOf(this.b.get("raster-hue-rotate").getAsFloat());
    }

    public Float getOpacity() {
        if (this.b.get("raster-opacity") instanceof JsonNull) {
            return null;
        }
        return Float.valueOf(this.b.get("raster-opacity").getAsFloat());
    }

    public String getResampling() {
        if (this.b.get("raster-resampling") instanceof JsonNull) {
            return null;
        }
        return this.b.get("raster-resampling").getAsString();
    }

    public Float getSaturation() {
        if (this.b.get("raster-saturation") instanceof JsonNull) {
            return null;
        }
        return Float.valueOf(this.b.get("raster-saturation").getAsFloat());
    }

    public String getType() {
        return this.f12698a;
    }

    public Boolean getVisibility() {
        if (this.b.get("visibility") instanceof JsonNull) {
            return null;
        }
        return Boolean.valueOf(this.b.get("visibility").getAsBoolean());
    }

    public boolean isStyles() {
        return this.d;
    }

    public void setBrightnessMax(Float f) {
        this.b.addProperty("raster-brightness-max", f);
    }

    public void setBrightnessMin(Float f) {
        this.b.addProperty("raster-brightness-min", f);
    }

    public void setContrast(Float f) {
        this.b.addProperty("raster-contrast", f);
    }

    public void setFadeDuration(Float f) {
        this.b.addProperty("raster-fade-duration", f);
    }

    public void setHueRotate(Float f) {
        this.b.addProperty("raster-hue-rotate", f);
    }

    public void setOpacity(Float f) {
        this.b.addProperty("raster-opacity", f);
    }

    public void setResampling(String str) {
        this.b.addProperty("raster-hue-rotate", str);
    }

    public void setSaturation(Float f) {
        this.b.addProperty("raster-saturation", f);
    }

    public void setStyles(boolean z) {
        this.d = z;
    }

    public void setVisibility(boolean z) {
        this.b.addProperty("visibility", Boolean.valueOf(z));
    }
}
