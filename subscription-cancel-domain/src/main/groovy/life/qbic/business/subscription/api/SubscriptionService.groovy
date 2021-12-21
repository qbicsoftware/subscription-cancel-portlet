package life.qbic.business.subscription.api

import life.qbic.business.subscription.CancellationConfirmation
import life.qbic.business.subscription.exceptions.SubscriptionServiceException

/**
 * <b>Interface SubscriptionService</b>
 *
 * <p>Provides access to an subscription service implementation. A subscription service
 * needs to accept a valid request token and confirm the cancellation by
 * providing a {@link CancellationConfirmation}.</p>
 *
 * @since 1.0.0
 */
interface SubscriptionService {

    /**
     * Calls the subscription cancellation service with the provided request token
     * @param requestToken the cancellation request token
     * @return
     * @throws SubscriptionServiceException
     */
    CancellationConfirmation cancelRequest(String requestToken) throws SubscriptionServiceException

}
