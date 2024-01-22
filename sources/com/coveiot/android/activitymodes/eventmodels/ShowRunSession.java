package com.coveiot.android.activitymodes.eventmodels;

import com.coveiot.android.activitymodes.database.entities.EntityPreparationDay;
import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ShowRunSession implements Serializable {
    @Nullable
    private EntityPreparationDay entityPreparationDay;

    public ShowRunSession(@Nullable EntityPreparationDay entityPreparationDay) {
        this.entityPreparationDay = entityPreparationDay;
    }

    @Nullable
    public final EntityPreparationDay getEntityPreparationDay() {
        return this.entityPreparationDay;
    }

    public final void setEntityPreparationDay(@Nullable EntityPreparationDay entityPreparationDay) {
        this.entityPreparationDay = entityPreparationDay;
    }
}
