package life.qbic.business.subscription

import groovy.util.logging.Log4j2
import life.qbic.business.subscription.api.CancelSubscriptionInput
import life.qbic.business.subscription.api.CancelSubscriptionOutput
import life.qbic.business.subscription.api.SubscriptionService

/**
 * Subscription cancellation use case
 * @since 1.0.0
 */
@Log4j2
class CancelSubscription implements CancelSubscriptionInput {

    private final CancelSubscriptionOutput output

    private final SubscriptionService subscriptionService

    CancelSubscription(CancelSubscriptionOutput output, SubscriptionService subscriptionService) {
        this.output = output
        this.subscriptionService = subscriptionService
    }

    @Override
    void cancelSubscription(String requestToken) {
        try {
            CancellationConfirmation confirmation = subscriptionService.cancelRequest(requestToken)
            output.onSuccess(confirmation)
        } catch (SecurityException e) {
            log.error(e.getMessage())
            output.onFailure("Could not cancel the subscription.")
        } catch (Exception e) {
            log.error(e.stackTrace.join("\n"))
            output.onFailure("An unexpected exception happened.")
        }
    }
}
