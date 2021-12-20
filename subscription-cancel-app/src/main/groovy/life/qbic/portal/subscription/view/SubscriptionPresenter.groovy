package life.qbic.portal.subscription.view

import groovy.util.logging.Log4j2
import life.qbic.business.subscription.CancellationConfirmation
import life.qbic.business.subscription.api.CancelSubscriptionOutput
import life.qbic.portal.subscription.view.model.SubscriptionModel


/**
 * <class short description - 1 Line!>
 *
 * <More detailed description - When to use, what it solves, etc.>
 *
 * @since <version tag>
 */
@Log4j2
class SubscriptionPresenter implements CancelSubscriptionOutput {

    SubscriptionModel model

    SubscriptionPresenter(SubscriptionModel model) {
        this.model = model
    }

    @Override
    void onSuccess(CancellationConfirmation request) {
        this.model.showConfirmation = true
        this.model.showFailure = false
        this.model.project = request.project
        this.model.email = request.userId
        log.info("Successful cancellation request for: " + request)
    }

    @Override
    void onFailure(String reason) {
        log.error("Cancellation failed")
        log.error(reason)
        this.model.showConfirmation = false
        this.model.showFailure = true
    }
}
