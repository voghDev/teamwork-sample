package es.voghdev.teamworksample.common;

/**
 * Null object pattern for those Screens that don't need to pass InitialData
 * to their Presenters.
 * https://en.wikipedia.org/wiki/Null_object_pattern
 */
public class NullInitialData implements Presenter.InitialData {
    /* Empty */
}
