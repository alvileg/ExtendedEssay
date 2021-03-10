package dev.alvileg.extendedessay.enums;

public enum WorldSize {
    SMALL("platform",8,4,8 ),MEDIUM("skyblock", 0, 67, 0),BIG("lobby", 1,53,0);

    private String fileName;
    private int x;
    private int y;
    private int z;
    WorldSize(String fileName, int x, int y, int z){
        this.fileName = fileName;
        this.x = x;
        this.y = y;
        this.z = z;

    }

    public String getFileName() {
        return fileName;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getZ() {
        return z;
    }

}
