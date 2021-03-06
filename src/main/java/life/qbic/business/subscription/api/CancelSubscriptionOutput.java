package life.qbic.business.subscription.api;


/**
 * <b>Interface CancelSubscriptionOutput</b>
 *
 * <p>Callback actions for the {@link life.qbic.business.subscription.CancelSubscription} use case.
 *
 * @since 1.0.0
 */
public interface CancelSubscriptionOutput {

  /**
   * Is executed after successful use case execution
   *
   */
  void onSuccess();

  /**
   * Is executed in the case of failing use case execution
   *
   * @param reason the reason for the failing use case execution
   */
  void onFailure(String reason);
}
