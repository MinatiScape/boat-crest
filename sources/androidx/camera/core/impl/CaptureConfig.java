package androidx.camera.core.impl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.impl.Config;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes.dex */
public final class CaptureConfig {

    /* renamed from: a  reason: collision with root package name */
    public final List<DeferrableSurface> f697a;
    public final Config b;
    public final int c;
    public final List<CameraCaptureCallback> d;
    public final boolean e;
    @NonNull
    public final TagBundle f;
    public static final Config.Option<Integer> OPTION_ROTATION = Config.Option.create("camerax.core.captureConfig.rotation", Integer.TYPE);
    public static final Config.Option<Integer> OPTION_JPEG_QUALITY = Config.Option.create("camerax.core.captureConfig.jpegQuality", Integer.class);

    /* loaded from: classes.dex */
    public interface OptionUnpacker {
        void unpack(@NonNull UseCaseConfig<?> useCaseConfig, @NonNull Builder builder);
    }

    public CaptureConfig(List<DeferrableSurface> list, Config config, int i, List<CameraCaptureCallback> list2, boolean z, @NonNull TagBundle tagBundle) {
        this.f697a = list;
        this.b = config;
        this.c = i;
        this.d = Collections.unmodifiableList(list2);
        this.e = z;
        this.f = tagBundle;
    }

    @NonNull
    public static CaptureConfig defaultEmptyCaptureConfig() {
        return new Builder().build();
    }

    @NonNull
    public List<CameraCaptureCallback> getCameraCaptureCallbacks() {
        return this.d;
    }

    @NonNull
    public Config getImplementationOptions() {
        return this.b;
    }

    @NonNull
    public List<DeferrableSurface> getSurfaces() {
        return Collections.unmodifiableList(this.f697a);
    }

    @NonNull
    public TagBundle getTagBundle() {
        return this.f;
    }

    public int getTemplateType() {
        return this.c;
    }

    public boolean isUseRepeatingSurface() {
        return this.e;
    }

    /* loaded from: classes.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final Set<DeferrableSurface> f698a;
        public MutableConfig b;
        public int c;
        public List<CameraCaptureCallback> d;
        public boolean e;
        public MutableTagBundle f;

        public Builder() {
            this.f698a = new HashSet();
            this.b = MutableOptionsBundle.create();
            this.c = -1;
            this.d = new ArrayList();
            this.e = false;
            this.f = MutableTagBundle.create();
        }

        @NonNull
        public static Builder createFrom(@NonNull UseCaseConfig<?> useCaseConfig) {
            OptionUnpacker captureOptionUnpacker = useCaseConfig.getCaptureOptionUnpacker(null);
            if (captureOptionUnpacker != null) {
                Builder builder = new Builder();
                captureOptionUnpacker.unpack(useCaseConfig, builder);
                return builder;
            }
            throw new IllegalStateException("Implementation is missing option unpacker for " + useCaseConfig.getTargetName(useCaseConfig.toString()));
        }

        @NonNull
        public static Builder from(@NonNull CaptureConfig captureConfig) {
            return new Builder(captureConfig);
        }

        public void addAllCameraCaptureCallbacks(@NonNull Collection<CameraCaptureCallback> collection) {
            for (CameraCaptureCallback cameraCaptureCallback : collection) {
                addCameraCaptureCallback(cameraCaptureCallback);
            }
        }

        public void addAllTags(@NonNull TagBundle tagBundle) {
            this.f.addTagBundle(tagBundle);
        }

        public void addCameraCaptureCallback(@NonNull CameraCaptureCallback cameraCaptureCallback) {
            if (!this.d.contains(cameraCaptureCallback)) {
                this.d.add(cameraCaptureCallback);
                return;
            }
            throw new IllegalArgumentException("duplicate camera capture callback");
        }

        public <T> void addImplementationOption(@NonNull Config.Option<T> option, @NonNull T t) {
            this.b.insertOption(option, t);
        }

        public void addImplementationOptions(@NonNull Config config) {
            for (Config.Option<?> option : config.listOptions()) {
                Object retrieveOption = this.b.retrieveOption(option, null);
                Object retrieveOption2 = config.retrieveOption(option);
                if (retrieveOption instanceof MultiValueSet) {
                    ((MultiValueSet) retrieveOption).addAll(((MultiValueSet) retrieveOption2).getAllItems());
                } else {
                    if (retrieveOption2 instanceof MultiValueSet) {
                        retrieveOption2 = ((MultiValueSet) retrieveOption2).mo3clone();
                    }
                    this.b.insertOption(option, config.getOptionPriority(option), retrieveOption2);
                }
            }
        }

        public void addSurface(@NonNull DeferrableSurface deferrableSurface) {
            this.f698a.add(deferrableSurface);
        }

        public void addTag(@NonNull String str, @NonNull Integer num) {
            this.f.putTag(str, num);
        }

        @NonNull
        public CaptureConfig build() {
            return new CaptureConfig(new ArrayList(this.f698a), OptionsBundle.from(this.b), this.c, this.d, this.e, TagBundle.from(this.f));
        }

        public void clearSurfaces() {
            this.f698a.clear();
        }

        @NonNull
        public Config getImplementationOptions() {
            return this.b;
        }

        @NonNull
        public Set<DeferrableSurface> getSurfaces() {
            return this.f698a;
        }

        @Nullable
        public Integer getTag(@NonNull String str) {
            return this.f.getTag(str);
        }

        public int getTemplateType() {
            return this.c;
        }

        public void removeSurface(@NonNull DeferrableSurface deferrableSurface) {
            this.f698a.remove(deferrableSurface);
        }

        public void setImplementationOptions(@NonNull Config config) {
            this.b = MutableOptionsBundle.from(config);
        }

        public void setTemplateType(int i) {
            this.c = i;
        }

        public void setUseRepeatingSurface(boolean z) {
            this.e = z;
        }

        public Builder(CaptureConfig captureConfig) {
            HashSet hashSet = new HashSet();
            this.f698a = hashSet;
            this.b = MutableOptionsBundle.create();
            this.c = -1;
            this.d = new ArrayList();
            this.e = false;
            this.f = MutableTagBundle.create();
            hashSet.addAll(captureConfig.f697a);
            this.b = MutableOptionsBundle.from(captureConfig.b);
            this.c = captureConfig.c;
            this.d.addAll(captureConfig.getCameraCaptureCallbacks());
            this.e = captureConfig.isUseRepeatingSurface();
            this.f = MutableTagBundle.from(captureConfig.getTagBundle());
        }
    }
}
