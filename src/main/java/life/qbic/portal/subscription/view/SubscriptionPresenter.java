package life.qbic.portal.subscription.view;

import life.qbic.business.subscription.api.CancelSubscriptionOutput;
import life.qbic.portal.subscription.view.model.SubscriptionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <b>Class SubscriptionPresenter</b>
 *
 * <p>Consumes the subscription use case output and adjusts the view model properties accordingly.
 *
 * @since 1.0.0
 */
@Component
public class SubscriptionPresenter implements CancelSubscriptionOutput {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  SubscriptionModel model;

  public SubscriptionPresenter(@Autowired SubscriptionModel model) {
    this.model = model;
  }

  @Override
  public void onSuccess() {
    this.model.setShowConfirmation(true);
    this.model.setShowFailure(false);
  }

  @Override
  public void onFailure(String reason) {
    logger.error("Cancellation failed");
    logger.error(reason);
    this.model.setShowConfirmation(false);
    this.model.setShowFailure(true);
  }
}
