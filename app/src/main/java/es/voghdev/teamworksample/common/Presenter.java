package es.voghdev.teamworksample.common;

public abstract class Presenter<T1, T2> {
    protected T1 view;
    protected T2 navigator;

    public void initialize(InitialData data) { /* Empty */ }

    public void resume() { /* Empty */ }

    public void pause() { /* Empty */ }

    public void destroy() { /* Empty */ }

    public void setView(T1 view) {
        this.view = view;
    }

    public void setNavigator(T2 navigator) {
        this.navigator = navigator;
    }

    public interface InitialData {
        /* Empty */
    }
}
