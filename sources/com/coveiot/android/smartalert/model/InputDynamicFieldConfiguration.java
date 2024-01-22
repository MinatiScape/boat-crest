package com.coveiot.android.smartalert.model;

import com.clevertap.android.sdk.Constants;
import com.coveiot.android.smartalert.SmartAlertHandler;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.maps.style.layers.Property;
import java.io.Serializable;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class InputDynamicFieldConfiguration implements Serializable {
    @SerializedName("buttonAction")
    @Expose
    @Nullable
    private Integer buttonAction;
    @SerializedName("buttonRgb565Color")
    @Expose
    @Nullable
    private Integer buttonRgb565Color;
    @SerializedName("color")
    @Expose
    @Nullable
    private String color;
    @SerializedName("endImage")
    @Expose
    @Nullable
    private Integer endImageId;
    @SerializedName("frameTime")
    @Expose
    @Nullable
    private Integer frameTime;
    @SerializedName(Property.ICON_TEXT_FIT_HEIGHT)
    @Expose
    @Nullable
    private Integer height;
    @SerializedName("imageId")
    @Expose
    @Nullable
    private Integer imageId;
    @SerializedName("imgIdList")
    @Expose
    @Nullable
    private ArrayList<Integer> imgIdList;
    @SerializedName("progressBgImg")
    @Expose
    @Nullable
    private Integer progressBgImgId;
    @SerializedName("sideBySide")
    @Expose
    @Nullable
    private Integer sideBySide;
    @SerializedName("startImage")
    @Expose
    @Nullable
    private Integer sliderImageId;
    @SerializedName("templateId")
    @Expose
    @Nullable
    private Integer templateId;
    @SerializedName("type")
    @Expose
    @Nullable
    private String type;
    @SerializedName(Property.ICON_TEXT_FIT_WIDTH)
    @Expose
    @Nullable
    private Integer width;
    @SerializedName("x_position")
    @Expose
    @Nullable
    private Integer xPosition;
    @SerializedName("y_position")
    @Expose
    @Nullable
    private Integer yPosition;
    @SerializedName("textSize")
    @Expose
    @Nullable
    private Integer textSize = 24;
    @SerializedName("format")
    @Expose
    @Nullable
    private String format = SmartAlertHandler.Companion.getTextFormat(TextFormatType.CENTER_HORIZONTAL.getType()).getType();
    @SerializedName("typeface")
    @Expose
    @Nullable
    private Integer typeface = 0;
    @SerializedName("buttonTextLength")
    @Expose
    @Nullable
    private Integer buttonTextLength = 0;
    @SerializedName("progress")
    @Expose
    @Nullable
    private Integer progress = 0;
    @SerializedName("progressColor")
    @Expose
    @Nullable
    private String progressColor = Constants.WHITE;

    @Nullable
    public final Integer getButtonAction() {
        return this.buttonAction;
    }

    @Nullable
    public final Integer getButtonRgb565Color() {
        return this.buttonRgb565Color;
    }

    @Nullable
    public final Integer getButtonTextLength() {
        return this.buttonTextLength;
    }

    @Nullable
    public final String getColor() {
        return this.color;
    }

    @Nullable
    public final Integer getEndImageId() {
        return this.endImageId;
    }

    @Nullable
    public final String getFormat() {
        return this.format;
    }

    @Nullable
    public final Integer getFrameTime() {
        return this.frameTime;
    }

    @Nullable
    public final Integer getHeight() {
        return this.height;
    }

    @Nullable
    public final Integer getImageId() {
        return this.imageId;
    }

    @Nullable
    public final ArrayList<Integer> getImgIdList() {
        return this.imgIdList;
    }

    @Nullable
    public final Integer getProgress() {
        return this.progress;
    }

    @Nullable
    public final Integer getProgressBgImgId() {
        return this.progressBgImgId;
    }

    @Nullable
    public final String getProgressColor() {
        return this.progressColor;
    }

    @Nullable
    public final Integer getSideBySide() {
        return this.sideBySide;
    }

    @Nullable
    public final Integer getSliderImageId() {
        return this.sliderImageId;
    }

    @Nullable
    public final Integer getTemplateId() {
        return this.templateId;
    }

    @Nullable
    public final Integer getTextSize() {
        return this.textSize;
    }

    @Nullable
    public final String getType() {
        return this.type;
    }

    @Nullable
    public final Integer getTypeface() {
        return this.typeface;
    }

    @Nullable
    public final Integer getWidth() {
        return this.width;
    }

    @Nullable
    public final Integer getXPosition() {
        return this.xPosition;
    }

    @Nullable
    public final Integer getYPosition() {
        return this.yPosition;
    }

    public final void setButtonAction(@Nullable Integer num) {
        this.buttonAction = num;
    }

    public final void setButtonRgb565Color(@Nullable Integer num) {
        this.buttonRgb565Color = num;
    }

    public final void setButtonTextLength(@Nullable Integer num) {
        this.buttonTextLength = num;
    }

    public final void setColor(@Nullable String str) {
        this.color = str;
    }

    public final void setEndImageId(@Nullable Integer num) {
        this.endImageId = num;
    }

    public final void setFormat(@Nullable String str) {
        this.format = str;
    }

    public final void setFrameTime(@Nullable Integer num) {
        this.frameTime = num;
    }

    public final void setHeight(@Nullable Integer num) {
        this.height = num;
    }

    public final void setImageId(@Nullable Integer num) {
        this.imageId = num;
    }

    public final void setImgIdList(@Nullable ArrayList<Integer> arrayList) {
        this.imgIdList = arrayList;
    }

    public final void setProgress(@Nullable Integer num) {
        this.progress = num;
    }

    public final void setProgressBgImgId(@Nullable Integer num) {
        this.progressBgImgId = num;
    }

    public final void setProgressColor(@Nullable String str) {
        this.progressColor = str;
    }

    public final void setSideBySide(@Nullable Integer num) {
        this.sideBySide = num;
    }

    public final void setSliderImageId(@Nullable Integer num) {
        this.sliderImageId = num;
    }

    public final void setTemplateId(@Nullable Integer num) {
        this.templateId = num;
    }

    public final void setTextSize(@Nullable Integer num) {
        this.textSize = num;
    }

    public final void setType(@Nullable String str) {
        this.type = str;
    }

    public final void setTypeface(@Nullable Integer num) {
        this.typeface = num;
    }

    public final void setWidth(@Nullable Integer num) {
        this.width = num;
    }

    public final void setXPosition(@Nullable Integer num) {
        this.xPosition = num;
    }

    public final void setYPosition(@Nullable Integer num) {
        this.yPosition = num;
    }
}
