package life.qbic.business.subscription.api

import life.qbic.business.subscription.CancellationConfirmation
import life.qbic.business.subscription.exceptions.SubscriptionServiceException

/**
 * <interface short description - 1 Line!>
 *
 * <More detailed description - When to use, what it solves, etc.>
 *
 * @since <version tag>
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