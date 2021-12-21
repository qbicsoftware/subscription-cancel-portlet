package life.qbic.business.subscription.api

/**
 * Executes the cancel subscription use case.
 * @since 1.0.0
 */
interface CancelSubscriptionInput {

    /**
     * Cancel the project update email subscription
     * @param requestToken the cancel request token
     */
    void cancelSubscription(String requestToken)

}
