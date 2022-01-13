package life.qbic.portal.views;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import java.util.Optional;
import life.qbic.business.subscription.CancelSubscription;
import life.qbic.business.subscription.api.CancelSubscriptionInput;
import life.qbic.business.subscription.api.CancelSubscriptionOutput;
import life.qbic.business.subscription.api.SubscriptionService;
import life.qbic.portal.subscription.view.SubscriptionInterface;
import life.qbic.portal.subscription.view.model.SubscriptionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@PageTitle("Subscription Cancellation")
@Route(value = "cancel")
@RouteAlias(value = "")
@Component
public class AppMainView extends HorizontalLayout implements HasUrlParameter<String> {

  private final CancelSubscriptionInput cancelSubscriptionInput;
  private static final Logger logger = LoggerFactory.getLogger(AppMainView.class);

  @Autowired
  public AppMainView(
      SubscriptionService subscriptionService,
      SubscriptionModel model,
      CancelSubscriptionOutput presenter) {
    add(new SubscriptionInterface(model));
    cancelSubscriptionInput = new CancelSubscription(presenter, subscriptionService);
  }

  @Override
  public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String s) {
    var token = getToken(beforeEvent.getLocation());
    token.ifPresent(cancelSubscriptionInput::cancelSubscription);
  }

  private Optional<String> getToken(Location location) {
    try {
      return Optional.ofNullable(location.getQueryParameters().getParameters().get("token").get(0));
    } catch (NullPointerException e) {
      return Optional.empty();
    }
  }
}
