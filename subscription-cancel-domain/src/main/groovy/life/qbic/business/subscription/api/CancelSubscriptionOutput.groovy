package life.qbic.business.subscription

/**
 * <b>Interface CancelSubscriptionOutput</b>
 *
 * <p>Callback actions for the {@link CancelSubscription} use case.</p>
 *
 * @since 1.0.0
 */
interface CancelSubscriptionOutput {

    /**
     * Is executed after successful use case execution
     * @param request the subscription that has been cancelled
     */
    void onSuccess(CancellationRequest request)

    /**
     * Is executed in the case of failing use case execution
     * @param reason the reason for the failing use case execution
     */
    void onFailure(String reason)

}
