package com.cj.uiapplication.model.bean;

public class DrawItemInfo {
    public int position =-1;
    public String text;
    public DrawType drawType;
    public enum DrawType {
        COLOR("drawColor"),
        CIRCLE("drawCircle"),
        TEXT("drawText"),
        PATH("drawPath"),
        BITMAP("drawBitmap");
        private final String drawType;

        DrawType(String drawType) {
            this.drawType = drawType;
        }
    }

}
