package shop;

public interface UIComponent {
    void setParent(UIComponent parent);
    float getX();
    float getY();
    float getWidth();
    float getHeight();
    float getPadding();
}
