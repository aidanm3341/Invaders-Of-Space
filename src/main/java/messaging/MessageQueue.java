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
        for(int i=0; i<messages.size(); i++)
        {
            messages.get(i).getTo().onMessage(messages.get(i));
            messages.remove(messages.get(i));
        }
    }

    private static class SingletonHolder {
        private static final MessageQueue INSTANCE = new MessageQueue();
    }

    public static MessageQueue getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
