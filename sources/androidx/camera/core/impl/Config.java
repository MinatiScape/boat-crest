package androidx.camera.core.impl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import java.util.Set;
/* loaded from: classes.dex */
public interface Config {

    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class Option<T> {
        @NonNull
        public static <T> Option<T> create(@NonNull String str, @NonNull Class<?> cls) {
            return create(str, cls, null);
        }

        @NonNull
        public abstract String getId();

        @Nullable
        public abstract Object getToken();

        @NonNull
        public abstract Class<T> getValueClass();

        @NonNull
        public static <T> Option<T> create(@NonNull String str, @NonNull Class<?> cls, @Nullable Object obj) {
            return new b(str, cls, obj);
        }
    }

    /* loaded from: classes.dex */
    public interface OptionMatcher {
        boolean onOptionMatched(@NonNull Option<?> option);
    }

    /* loaded from: classes.dex */
    public enum OptionPriority {
        ALWAYS_OVERRIDE,
        REQUIRED,
        OPTIONAL
    }

    static boolean hasConflict(@NonNull OptionPriority optionPriority, @NonNull OptionPriority optionPriority2) {
        OptionPriority optionPriority3 = OptionPriority.ALWAYS_OVERRIDE;
        if (optionPriority == optionPriority3 && optionPriority2 == optionPriority3) {
            return true;
        }
        OptionPriority optionPriority4 = OptionPriority.REQUIRED;
        return optionPriority == optionPriority4 && optionPriority2 == optionPriority4;
    }

    @NonNull
    static Config mergeConfigs(@Nullable Config config, @Nullable Config config2) {
        MutableOptionsBundle create;
        if (config == null && config2 == null) {
            return OptionsBundle.emptyBundle();
        }
        if (config2 != null) {
            create = MutableOptionsBundle.from(config2);
        } else {
            create = MutableOptionsBundle.create();
        }
        if (config != null) {
            for (Option<?> option : config.listOptions()) {
                create.insertOption(option, config.getOptionPriority(option), config.retrieveOption(option));
            }
        }
        return OptionsBundle.from(create);
    }

    boolean containsOption(@NonNull Option<?> option);

    void findOptions(@NonNull String str, @NonNull OptionMatcher optionMatcher);

    @NonNull
    OptionPriority getOptionPriority(@NonNull Option<?> option);

    @NonNull
    Set<OptionPriority> getPriorities(@NonNull Option<?> option);

    @NonNull
    Set<Option<?>> listOptions();

    @Nullable
    <ValueT> ValueT retrieveOption(@NonNull Option<ValueT> option);

    @Nullable
    <ValueT> ValueT retrieveOption(@NonNull Option<ValueT> option, @Nullable ValueT valuet);

    @Nullable
    <ValueT> ValueT retrieveOptionWithPriority(@NonNull Option<ValueT> option, @NonNull OptionPriority optionPriority);
}
