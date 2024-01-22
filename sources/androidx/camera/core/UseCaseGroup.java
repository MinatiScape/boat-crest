package androidx.camera.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.List;
@ExperimentalUseCaseGroup
/* loaded from: classes.dex */
public final class UseCaseGroup {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final ViewPort f658a;
    @NonNull
    public final List<UseCase> b;

    @ExperimentalUseCaseGroup
    /* loaded from: classes.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public ViewPort f659a;
        public final List<UseCase> b = new ArrayList();

        @NonNull
        public Builder addUseCase(@NonNull UseCase useCase) {
            this.b.add(useCase);
            return this;
        }

        @NonNull
        public UseCaseGroup build() {
            Preconditions.checkArgument(!this.b.isEmpty(), "UseCase must not be empty.");
            return new UseCaseGroup(this.f659a, this.b);
        }

        @NonNull
        public Builder setViewPort(@NonNull ViewPort viewPort) {
            this.f659a = viewPort;
            return this;
        }
    }

    public UseCaseGroup(@Nullable ViewPort viewPort, @NonNull List<UseCase> list) {
        this.f658a = viewPort;
        this.b = list;
    }

    @NonNull
    public List<UseCase> getUseCases() {
        return this.b;
    }

    @Nullable
    public ViewPort getViewPort() {
        return this.f658a;
    }
}
