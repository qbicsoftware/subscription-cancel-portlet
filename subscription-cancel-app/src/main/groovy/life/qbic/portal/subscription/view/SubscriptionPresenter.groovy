package life.qbic.portal.subscription.view

import groovy.util.logging.Log4j2
import life.qbic.business.subscription.CancellationConfirmation
import life.qbic.business.subscription.api.CancelSubscriptionOutput
import life.qbic.portal.subscription.view.model.SubscriptionModel

/**
 * <b>Class SubscriptionPresenter</b>
 *
 * <p>Consumes the subscription use case output and adjusts the view model properties
 * accordingly.</p>
 * @since 1.0.0
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
