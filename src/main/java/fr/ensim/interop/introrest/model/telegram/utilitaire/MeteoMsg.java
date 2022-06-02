package fr.ensim.interop.introrest.model.telegram.utilitaire;

public class MeteoMsg {
    private long chat_id;
    private String text;

    public long getChat_id() {
        return chat_id;
    }

    public void setChat_id(long chat_id) {
        this.chat_id = chat_id;
    }

    @Override
    public String toString() {
        return "MeteoMsg{" +
                "chat_id=" + chat_id +
                ", text='" + text + '\'' +
                '}';
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
