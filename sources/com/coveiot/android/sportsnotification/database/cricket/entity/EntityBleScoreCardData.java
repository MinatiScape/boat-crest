package com.coveiot.android.sportsnotification.database.cricket.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity
/* loaded from: classes7.dex */
public final class EntityBleScoreCardData {

    /* renamed from: a  reason: collision with root package name */
    public int f5846a;
    @NotNull
    public String b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    @NotNull
    public String j;
    @PrimaryKey(autoGenerate = true)
    public int k;

    public EntityBleScoreCardData(int i, @NotNull String hexColor, int i2, int i3, int i4, int i5, int i6, int i7, int i8, @NotNull String text) {
        Intrinsics.checkNotNullParameter(hexColor, "hexColor");
        Intrinsics.checkNotNullParameter(text, "text");
        this.f5846a = i;
        this.b = hexColor;
        this.c = i2;
        this.d = i3;
        this.e = i4;
        this.f = i5;
        this.g = i6;
        this.h = i7;
        this.i = i8;
        this.j = text;
    }

    public final int component1() {
        return this.f5846a;
    }

    @NotNull
    public final String component10() {
        return this.j;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    public final int component3() {
        return this.c;
    }

    public final int component4() {
        return this.d;
    }

    public final int component5() {
        return this.e;
    }

    public final int component6() {
        return this.f;
    }

    public final int component7() {
        return this.g;
    }

    public final int component8() {
        return this.h;
    }

    public final int component9() {
        return this.i;
    }

    @NotNull
    public final EntityBleScoreCardData copy(int i, @NotNull String hexColor, int i2, int i3, int i4, int i5, int i6, int i7, int i8, @NotNull String text) {
        Intrinsics.checkNotNullParameter(hexColor, "hexColor");
        Intrinsics.checkNotNullParameter(text, "text");
        return new EntityBleScoreCardData(i, hexColor, i2, i3, i4, i5, i6, i7, i8, text);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EntityBleScoreCardData) {
            EntityBleScoreCardData entityBleScoreCardData = (EntityBleScoreCardData) obj;
            return this.f5846a == entityBleScoreCardData.f5846a && Intrinsics.areEqual(this.b, entityBleScoreCardData.b) && this.c == entityBleScoreCardData.c && this.d == entityBleScoreCardData.d && this.e == entityBleScoreCardData.e && this.f == entityBleScoreCardData.f && this.g == entityBleScoreCardData.g && this.h == entityBleScoreCardData.h && this.i == entityBleScoreCardData.i && Intrinsics.areEqual(this.j, entityBleScoreCardData.j);
        }
        return false;
    }

    public final int getFieldId() {
        return this.k;
    }

    public final int getFont() {
        return this.h;
    }

    @NotNull
    public final String getHexColor() {
        return this.b;
    }

    public final int getImageId() {
        return this.g;
    }

    public final int getLength() {
        return this.e;
    }

    public final int getMatchId() {
        return this.f5846a;
    }

    @NotNull
    public final String getText() {
        return this.j;
    }

    public final int getTypeFace() {
        return this.i;
    }

    public final int getWidth() {
        return this.f;
    }

    public final int getXPosition() {
        return this.c;
    }

    public final int getYPosition() {
        return this.d;
    }

    public int hashCode() {
        return (((((((((((((((((Integer.hashCode(this.f5846a) * 31) + this.b.hashCode()) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d)) * 31) + Integer.hashCode(this.e)) * 31) + Integer.hashCode(this.f)) * 31) + Integer.hashCode(this.g)) * 31) + Integer.hashCode(this.h)) * 31) + Integer.hashCode(this.i)) * 31) + this.j.hashCode();
    }

    public final void setFieldId(int i) {
        this.k = i;
    }

    public final void setFont(int i) {
        this.h = i;
    }

    public final void setHexColor(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.b = str;
    }

    public final void setImageId(int i) {
        this.g = i;
    }

    public final void setLength(int i) {
        this.e = i;
    }

    public final void setMatchId(int i) {
        this.f5846a = i;
    }

    public final void setText(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.j = str;
    }

    public final void setTypeFace(int i) {
        this.i = i;
    }

    public final void setWidth(int i) {
        this.f = i;
    }

    public final void setXPosition(int i) {
        this.c = i;
    }

    public final void setYPosition(int i) {
        this.d = i;
    }

    @NotNull
    public String toString() {
        return "EntityBleScoreCardData(matchId=" + this.f5846a + ", hexColor=" + this.b + ", xPosition=" + this.c + ", yPosition=" + this.d + ", length=" + this.e + ", width=" + this.f + ", imageId=" + this.g + ", font=" + this.h + ", typeFace=" + this.i + ", text=" + this.j + HexStringBuilder.COMMENT_END_CHAR;
    }
}
