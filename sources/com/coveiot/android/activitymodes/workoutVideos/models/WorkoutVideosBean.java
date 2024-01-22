package com.coveiot.android.activitymodes.workoutVideos.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class WorkoutVideosBean {
    @Nullable
    private List<String> categoryIds;
    @Nullable
    private String description;
    @Nullable
    private final Integer duration;
    @Nullable
    private String thumbnailUrl;
    @Nullable
    private final String title;
    @NotNull
    private final String videoId;
    @Nullable
    private final String videoUrl;
    @NotNull
    private final String youTubeVideoId;

    public WorkoutVideosBean(@NotNull String videoId, @NotNull String youTubeVideoId, @Nullable String str, @Nullable String str2, @Nullable Integer num, @Nullable String str3, @Nullable String str4, @Nullable List<String> list) {
        Intrinsics.checkNotNullParameter(videoId, "videoId");
        Intrinsics.checkNotNullParameter(youTubeVideoId, "youTubeVideoId");
        this.videoId = videoId;
        this.youTubeVideoId = youTubeVideoId;
        this.title = str;
        this.description = str2;
        this.duration = num;
        this.videoUrl = str3;
        this.thumbnailUrl = str4;
        this.categoryIds = list;
    }

    @NotNull
    public final String component1() {
        return this.videoId;
    }

    @NotNull
    public final String component2() {
        return this.youTubeVideoId;
    }

    @Nullable
    public final String component3() {
        return this.title;
    }

    @Nullable
    public final String component4() {
        return this.description;
    }

    @Nullable
    public final Integer component5() {
        return this.duration;
    }

    @Nullable
    public final String component6() {
        return this.videoUrl;
    }

    @Nullable
    public final String component7() {
        return this.thumbnailUrl;
    }

    @Nullable
    public final List<String> component8() {
        return this.categoryIds;
    }

    @NotNull
    public final WorkoutVideosBean copy(@NotNull String videoId, @NotNull String youTubeVideoId, @Nullable String str, @Nullable String str2, @Nullable Integer num, @Nullable String str3, @Nullable String str4, @Nullable List<String> list) {
        Intrinsics.checkNotNullParameter(videoId, "videoId");
        Intrinsics.checkNotNullParameter(youTubeVideoId, "youTubeVideoId");
        return new WorkoutVideosBean(videoId, youTubeVideoId, str, str2, num, str3, str4, list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WorkoutVideosBean) {
            WorkoutVideosBean workoutVideosBean = (WorkoutVideosBean) obj;
            return Intrinsics.areEqual(this.videoId, workoutVideosBean.videoId) && Intrinsics.areEqual(this.youTubeVideoId, workoutVideosBean.youTubeVideoId) && Intrinsics.areEqual(this.title, workoutVideosBean.title) && Intrinsics.areEqual(this.description, workoutVideosBean.description) && Intrinsics.areEqual(this.duration, workoutVideosBean.duration) && Intrinsics.areEqual(this.videoUrl, workoutVideosBean.videoUrl) && Intrinsics.areEqual(this.thumbnailUrl, workoutVideosBean.thumbnailUrl) && Intrinsics.areEqual(this.categoryIds, workoutVideosBean.categoryIds);
        }
        return false;
    }

    @Nullable
    public final List<String> getCategoryIds() {
        return this.categoryIds;
    }

    @Nullable
    public final String getDescription() {
        return this.description;
    }

    @Nullable
    public final Integer getDuration() {
        return this.duration;
    }

    @Nullable
    public final String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    @NotNull
    public final String getVideoId() {
        return this.videoId;
    }

    @Nullable
    public final String getVideoUrl() {
        return this.videoUrl;
    }

    @NotNull
    public final String getYouTubeVideoId() {
        return this.youTubeVideoId;
    }

    public int hashCode() {
        int hashCode = ((this.videoId.hashCode() * 31) + this.youTubeVideoId.hashCode()) * 31;
        String str = this.title;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.description;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num = this.duration;
        int hashCode4 = (hashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        String str3 = this.videoUrl;
        int hashCode5 = (hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.thumbnailUrl;
        int hashCode6 = (hashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
        List<String> list = this.categoryIds;
        return hashCode6 + (list != null ? list.hashCode() : 0);
    }

    public final void setCategoryIds(@Nullable List<String> list) {
        this.categoryIds = list;
    }

    public final void setDescription(@Nullable String str) {
        this.description = str;
    }

    public final void setThumbnailUrl(@Nullable String str) {
        this.thumbnailUrl = str;
    }

    @NotNull
    public String toString() {
        return "WorkoutVideosBean(videoId=" + this.videoId + ", youTubeVideoId=" + this.youTubeVideoId + ", title=" + this.title + ", description=" + this.description + ", duration=" + this.duration + ", videoUrl=" + this.videoUrl + ", thumbnailUrl=" + this.thumbnailUrl + ", categoryIds=" + this.categoryIds + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ WorkoutVideosBean(String str, String str2, String str3, String str4, Integer num, String str5, String str6, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : num, (i & 32) != 0 ? null : str5, (i & 64) != 0 ? null : str6, (i & 128) != 0 ? null : list);
    }
}
