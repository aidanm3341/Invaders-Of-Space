package messaging;

import entities.Entity;

public class Message {

    private final Entity to, from;
    private final String type, data;

    public Message(Entity to, Entity from, String type, String data)
    {
        this.to = to;
        this.from = from;
        this.type = type;
        this.data = data;
    }

    public Entity getTo() {
        return to;
    }

    public Entity getFrom() {
        return from;
    }

    public String getData() {
        return data;
    }

    public String getType() {
        return type;
    }
}
