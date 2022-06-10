package model;

public class Deck {


    private boolean success;
    private Object deck_id;
    private Integer remaining;
    private Boolean shuffled;

    public Deck(boolean success, String deck_id, Integer remaning, Boolean shuffled) {
        this.success = success;
        this.deck_id = deck_id;
        this.remaining = remaning;
        this.shuffled = shuffled;
    }

    public Deck() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    public Boolean isShuffled() {
        return shuffled;
    }

    public void setShuffled(Boolean shuffled) {
        this.shuffled = shuffled;
    }
}
