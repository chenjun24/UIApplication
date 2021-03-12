package com.cj.uiapplication.model.bean;

public class DrawItemInfo {
    public String text;
    public DrawType drawType;
    public enum DrawType {
        COLOR("drawColor"),
        CIRCLE("drawCircle"),
        BITMAP("drawBitmap");
        private final String drawType;

        DrawType(String drawType) {
            this.drawType = drawType;
        }
    }

}
