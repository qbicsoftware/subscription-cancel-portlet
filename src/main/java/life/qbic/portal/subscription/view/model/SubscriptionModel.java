package life.qbic.portal.subscription.view.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Subscription view model.
 *
 * @since 1.0.0
 */
@Component
@Scope("singleton")
public class SubscriptionModel {

  private final PropertyChangeSupport propertyChangeSupport;
  Logger logger = LoggerFactory.getLogger(this.getClass());
  private boolean showConfirmation;
  private boolean showFailure;

  public SubscriptionModel() {
    propertyChangeSupport = new PropertyChangeSupport(this);
    init();
  }

  void init() {
    showConfirmation = false;
    showFailure = true;
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }


  public boolean isShowConfirmation() {
    return showConfirmation;
  }

  public void setShowConfirmation(boolean showConfirmation) {
    propertyChangeSupport.firePropertyChange(
        "showConfirmation", this.showConfirmation, showConfirmation);
    this.showConfirmation = showConfirmation;
  }

  public boolean isShowFailure() {
    return showFailure;
  }

  public void setShowFailure(boolean showFailure) {
    propertyChangeSupport.firePropertyChange("showFailure", this.showFailure, showFailure);
    this.showFailure = showFailure;
  }

  public void reset() {
    init();
  }
}
