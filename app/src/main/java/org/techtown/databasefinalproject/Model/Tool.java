package org.techtown.databasefinalproject.Model;

import java.io.Serializable;

public class Tool implements Serializable {
    private String toolName;
    private String description;

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
