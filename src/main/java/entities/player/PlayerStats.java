package entities.player;

public class PlayerStats {
    private float maxVelocity, maxLife, maxAcceleration, currentLife;

    public PlayerStats(float maxVelocity, float maxLife, float maxAcceleration, float currentLife) {
        this.maxVelocity = maxVelocity;
        this.maxLife = maxLife;
        this.maxAcceleration = maxAcceleration;
        this.currentLife = currentLife;
    }

    public float getMaxVelocity() {
        return maxVelocity;
    }

    public void setMaxVelocity(float maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    public float getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(float maxLife) {
        this.maxLife = maxLife;
    }

    public float getMaxAcceleration() {
        return maxAcceleration;
    }

    public void setMaxAcceleration(float maxAcceleration) {
        this.maxAcceleration = maxAcceleration;
    }

    public float getCurrentLife() {
        return currentLife;
    }

    public void setCurrentLife(float currentLife) {
        this.currentLife = Math.min(currentLife, getMaxLife());
    }
}
