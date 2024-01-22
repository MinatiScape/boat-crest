package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import com.clevertap.android.sdk.Constants;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleMusicControl extends BleWritable {
    @NotNull
    private final String mContent;
    @NotNull
    private final MusicAttr mMusicAttr;
    @NotNull
    private final MusicEntity mMusicEntity;

    public BleMusicControl(@NotNull MusicEntity mMusicEntity, @NotNull MusicAttr mMusicAttr, @NotNull String mContent) {
        Intrinsics.checkNotNullParameter(mMusicEntity, "mMusicEntity");
        Intrinsics.checkNotNullParameter(mMusicAttr, "mMusicAttr");
        Intrinsics.checkNotNullParameter(mContent, "mContent");
        this.mMusicEntity = mMusicEntity;
        this.mMusicAttr = mMusicAttr;
        this.mContent = mContent;
    }

    public static /* synthetic */ BleMusicControl copy$default(BleMusicControl bleMusicControl, MusicEntity musicEntity, MusicAttr musicAttr, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            musicEntity = bleMusicControl.mMusicEntity;
        }
        if ((i & 2) != 0) {
            musicAttr = bleMusicControl.mMusicAttr;
        }
        if ((i & 4) != 0) {
            str = bleMusicControl.mContent;
        }
        return bleMusicControl.copy(musicEntity, musicAttr, str);
    }

    @NotNull
    public final MusicEntity component1() {
        return this.mMusicEntity;
    }

    @NotNull
    public final MusicAttr component2() {
        return this.mMusicAttr;
    }

    @NotNull
    public final String component3() {
        return this.mContent;
    }

    @NotNull
    public final BleMusicControl copy(@NotNull MusicEntity mMusicEntity, @NotNull MusicAttr mMusicAttr, @NotNull String mContent) {
        Intrinsics.checkNotNullParameter(mMusicEntity, "mMusicEntity");
        Intrinsics.checkNotNullParameter(mMusicAttr, "mMusicAttr");
        Intrinsics.checkNotNullParameter(mContent, "mContent");
        return new BleMusicControl(mMusicEntity, mMusicAttr, mContent);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(this.mMusicEntity.getMEntity());
        writeInt8(this.mMusicAttr.getMAttr());
        BleWritable.writeString$default(this, this.mContent, null, 2, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleMusicControl) {
            BleMusicControl bleMusicControl = (BleMusicControl) obj;
            return this.mMusicEntity == bleMusicControl.mMusicEntity && this.mMusicAttr == bleMusicControl.mMusicAttr && Intrinsics.areEqual(this.mContent, bleMusicControl.mContent);
        }
        return false;
    }

    @NotNull
    public final String getMContent() {
        return this.mContent;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        byte[] bytes = this.mContent.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        return bytes.length + 2;
    }

    @NotNull
    public final MusicAttr getMMusicAttr() {
        return this.mMusicAttr;
    }

    @NotNull
    public final MusicEntity getMMusicEntity() {
        return this.mMusicEntity;
    }

    public int hashCode() {
        return (((this.mMusicEntity.hashCode() * 31) + this.mMusicAttr.hashCode()) * 31) + this.mContent.hashCode();
    }

    @NotNull
    public String toString() {
        return "BleMusicControl(mMusicEntity=" + this.mMusicEntity + ", mMusicAttr=" + this.mMusicAttr + ", mContent='" + this.mContent + "')";
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BleMusicControl(@NotNull MusicEntity musicEntity, @NotNull MusicAttr musicAttr, @NotNull List<String> contents) {
        this(musicEntity, musicAttr, CollectionsKt___CollectionsKt.joinToString$default(contents, Constants.SEPARATOR_COMMA, null, null, 0, null, null, 62, null));
        Intrinsics.checkNotNullParameter(musicEntity, "musicEntity");
        Intrinsics.checkNotNullParameter(musicAttr, "musicAttr");
        Intrinsics.checkNotNullParameter(contents, "contents");
    }
}
