package shop;

public class PanelUI implements UIComponent{
    private UIComponent parent;
    private float x, y, width, height;

    public PanelUI(UIComponent parent, float x, float y, float width, float height) {
        this.parent = parent;
        this.x = parent.getX() + parent.getPadding() + x;
        this.y = parent.getY() + parent.getPadding() + y;
        this.width = width - parent.getPadding()*2;
        this.height = height - parent.getPadding()*2;
    }

    @Override
    public void setParent(UIComponent parent) {
        this.parent = parent;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public float getPadding() {
        return 30;
    }
}
