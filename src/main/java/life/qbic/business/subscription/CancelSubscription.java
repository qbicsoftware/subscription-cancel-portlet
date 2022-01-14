package life.qbic.business.subscription;

import life.qbic.business.subscription.api.CancelSubscriptionInput;
import life.qbic.business.subscription.api.CancelSubscriptionOutput;
import life.qbic.business.subscription.api.SubscriptionService;
import life.qbic.business.subscription.exceptions.SubscriptionServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Subscription cancellation use case
 *
 * @since 1.0.0
 */
public class CancelSubscription implements CancelSubscriptionInput {

  private final CancelSubscriptionOutput output;
  private final SubscriptionService subscriptionService;
  Logger logger = LoggerFactory.getLogger(this.getClass());

  public CancelSubscription(
      CancelSubscriptionOutput output, SubscriptionService subscriptionService) {
    this.output = output;
    this.subscriptionService = subscriptionService;
  }

  @Override
  public void cancelSubscription(String requestToken) {
    try {
      subscriptionService.cancelRequest(requestToken);
      output.onSuccess();
    } catch (SubscriptionServiceException e) {
      logger.error(e.getMessage());
      output.onFailure("Could not cancel the subscription.");
    } catch (Exception e) {
      logger.error(e.getMessage());
      output.onFailure("An unexpected exception happened.");
    }
  }
}
