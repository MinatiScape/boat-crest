package com.coveiot.android.watchfaceui.model;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class TouchDialConfig {
    private boolean change_color;
    private int corner;
    @NotNull
    private String default_bg = "";
    private int height;
    private int id;
    @Nullable
    private String name;
    private int preview_corner;
    @Nullable
    private String project;
    private int shape;
    @Nullable
    private List<Integer> style;
    private int width;

    public final boolean getChange_color() {
        return this.change_color;
    }

    public final int getCorner() {
        return this.corner;
    }

    @NotNull
    public final String getDefault_bg() {
        return this.default_bg;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getId() {
        return this.id;
    }

    @Nullable
    public final String getName() {
        return this.name;
    }

    public final int getPreview_corner() {
        return this.preview_corner;
    }

    @Nullable
    public final String getProject() {
        return this.project;
    }

    public final int getShape() {
        return this.shape;
    }

    @Nullable
    public final List<Integer> getStyle() {
        return this.style;
    }

    public final int getWidth() {
        return this.width;
    }

    public final void setChange_color(boolean z) {
        this.change_color = z;
    }

    public final void setCorner(int i) {
        this.corner = i;
    }

    public final void setDefault_bg(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.default_bg = str;
    }

    public final void setHeight(int i) {
        this.height = i;
    }

    public final void setId(int i) {
        this.id = i;
    }

    public final void setName(@Nullable String str) {
        this.name = str;
    }

    public final void setPreview_corner(int i) {
        this.preview_corner = i;
    }

    public final void setProject(@Nullable String str) {
        this.project = str;
    }

    public final void setShape(int i) {
        this.shape = i;
    }

    public final void setStyle(@Nullable List<Integer> list) {
        this.style = list;
    }

    public final void setWidth(int i) {
        this.width = i;
    }
}
