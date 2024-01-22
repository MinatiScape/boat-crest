package androidx.activity.result;

import androidx.activity.result.contract.ActivityResultContracts;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class PickVisualMediaRequest {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public ActivityResultContracts.PickVisualMedia.VisualMediaType f359a = ActivityResultContracts.PickVisualMedia.ImageAndVideo.INSTANCE;

    /* loaded from: classes.dex */
    public static final class Builder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public ActivityResultContracts.PickVisualMedia.VisualMediaType f360a = ActivityResultContracts.PickVisualMedia.ImageAndVideo.INSTANCE;

        @NotNull
        public final PickVisualMediaRequest build() {
            PickVisualMediaRequest pickVisualMediaRequest = new PickVisualMediaRequest();
            pickVisualMediaRequest.setMediaType$activity_release(this.f360a);
            return pickVisualMediaRequest;
        }

        @NotNull
        public final Builder setMediaType(@NotNull ActivityResultContracts.PickVisualMedia.VisualMediaType mediaType) {
            Intrinsics.checkNotNullParameter(mediaType, "mediaType");
            this.f360a = mediaType;
            return this;
        }
    }

    @NotNull
    public final ActivityResultContracts.PickVisualMedia.VisualMediaType getMediaType() {
        return this.f359a;
    }

    public final void setMediaType$activity_release(@NotNull ActivityResultContracts.PickVisualMedia.VisualMediaType visualMediaType) {
        Intrinsics.checkNotNullParameter(visualMediaType, "<set-?>");
        this.f359a = visualMediaType;
    }
}
