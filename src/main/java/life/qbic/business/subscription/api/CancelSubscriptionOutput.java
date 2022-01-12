package life.qbic.business.subscription.api;

import life.qbic.business.subscription.CancellationConfirmation;

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
   * @param request the subscription that has been cancelled
   */
  void onSuccess(CancellationConfirmation request);

  /**
   * Is executed in the case of failing use case execution
   *
   * @param reason the reason for the failing use case execution
   */
  void onFailure(String reason);
}
