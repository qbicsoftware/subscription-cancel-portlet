package life.qbic.portal.subscription.view.model

import groovy.beans.Bindable
import groovy.transform.ToString

@ToString
/**
 * Subscription view model.
 *
 * @since 1.0.0
 */
class SubscriptionModel {

    @Bindable String project

    @Bindable String email

    @Bindable boolean showConfirmation

    @Bindable boolean showFailure

    SubscriptionModel() {
        init()
    }

    void init() {
        project = ""
        email = ""
        showConfirmation = false
        showFailure = true
    }
}
