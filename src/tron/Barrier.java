package tron;

import java.awt.Color;
import java.awt.Graphics;
import static javafx.scene.paint.Color.color;

/**
 *
 * @author nathaniel ward
 */
public class Barrier {

    Barrier(int i, int i0, Color GREEN, Arena aThis) {

    
    }

    public void draw(Graphics graphics) {
        graphics.setColor(color);
        graphics.fill3DRect(cellData.getSystemCoordX(x, y), cellData.getSystemCoordY(x, y), cellData.getCellWidth(), cellData.getCellHeight(), true);
    }

    public Barrier(int x, int y, Color color, boolean breakable, CellDataProviderIntf cellData) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.breakable = breakable;
        this.cellData = cellData;
    }

//<editor-fold defaultstate="collapsed" desc="Properties">
    private int x, y;
    private Color color;
    private boolean breakable = false;
    private CellDataProviderIntf cellData;

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the Color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param Color the Color to set
     */
    public void setColor(Color Color) {
        this.color = Color;
    }

    /**
     * @return the breakable
     */
    public boolean isBreakable() {
        return breakable;
    }

    /**
     * @param breakable the breakable to set
     */
    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }

    /**
     * @return the cellData
     */
    public CellDataProviderIntf getCellData() {
        return cellData;
    }

    /**
     * @param cellData the cellData to set
     */
    public void setCellData(CellDataProviderIntf cellData) {
        this.cellData = cellData;
    }
//</editor-fold>
}