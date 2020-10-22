package com.maistruk.galery.model;

public enum Style {
    
    ROMANTICISM("Romanticism"), CUBISM("Cubism"), POST_IMPRESSIONISM("Post-Impressionism"), SELF_PORTRAIT("Self portrait"), RENAISSANE("Renaissance"),
    HIGH_RENAISSANCE("High Renaissance");

    private String style;

    private Style(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
    
    
}
