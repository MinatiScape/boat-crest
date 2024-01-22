package org.apache.commons.cli;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes12.dex */
public class OptionGroup implements Serializable {
    private static final long serialVersionUID = 1;
    private Map optionMap = new HashMap();
    private boolean required;
    private String selected;

    public OptionGroup addOption(Option option) {
        this.optionMap.put(option.getKey(), option);
        return this;
    }

    public Collection getNames() {
        return this.optionMap.keySet();
    }

    public Collection getOptions() {
        return this.optionMap.values();
    }

    public String getSelected() {
        return this.selected;
    }

    public boolean isRequired() {
        return this.required;
    }

    public void setRequired(boolean z) {
        this.required = z;
    }

    public void setSelected(Option option) throws AlreadySelectedException {
        String str = this.selected;
        if (str != null && !str.equals(option.getOpt())) {
            throw new AlreadySelectedException(this, option);
        }
        this.selected = option.getOpt();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = getOptions().iterator();
        stringBuffer.append("[");
        while (it.hasNext()) {
            Option option = (Option) it.next();
            if (option.getOpt() != null) {
                stringBuffer.append("-");
                stringBuffer.append(option.getOpt());
            } else {
                stringBuffer.append("--");
                stringBuffer.append(option.getLongOpt());
            }
            stringBuffer.append(HexStringBuilder.DEFAULT_SEPARATOR);
            stringBuffer.append(option.getDescription());
            if (it.hasNext()) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
