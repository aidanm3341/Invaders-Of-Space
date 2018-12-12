package messaging;

import java.util.LinkedList;

public class MessageQueue {

    private LinkedList<Message> messages;

    private MessageQueue()
    {
        messages = new LinkedList<Message>();
    }

    public void add(Message msg)
    {
        messages.add(msg);
    }

    public void dispatch()
    {
        for(Message msg : messages)
        {
            msg.getTo().onMessage(msg);
            messages.remove(msg);
        }
    }

    private static class SingletonHolder {
        private static final MessageQueue INSTANCE = new MessageQueue();
    }

    public static MessageQueue getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
