package life.qbic.portal.subscription.view.model

/**
 * <class short description - 1 Line!>
 *
 * <More detailed description - When to use, what it solves, etc.>
 *
 * @since <version tag>
 */
class SubscriptionModel {

    String project

    String email

    boolean showConfirmation

    boolean showFailure

    SubscriptionModel() {
        init()
    }

    void init() {
        project = "QABCE"
        email = "sven.fillinger@mailbox.org"
        showConfirmation = false
        showFailure = true
    }
}
