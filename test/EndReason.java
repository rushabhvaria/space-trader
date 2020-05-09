public enum EndReason {
    SHIP_HEALTH_ZERO(false), BOUGHT_UNIVERSE(true);

    private boolean win;

    EndReason(boolean win) {
        this.win = win;
    }
}
