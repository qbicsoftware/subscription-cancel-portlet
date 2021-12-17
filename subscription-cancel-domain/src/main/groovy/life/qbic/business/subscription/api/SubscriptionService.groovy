package life.qbic.business.subscription

/**
 * <interface short description - 1 Line!>
 *
 * <More detailed description - When to use, what it solves, etc.>
 *
 * @since <version tag>
 */
interface SubscriptionService {

    cancelRequest(String requestToken) throws SubscriptionServiceException

}