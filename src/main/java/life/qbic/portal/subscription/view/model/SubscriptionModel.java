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
  private String project;
  private String email;
  private boolean showConfirmation;
  private boolean showFailure;

  public SubscriptionModel() {
    propertyChangeSupport = new PropertyChangeSupport(this);
    init();
  }

  void init() {
    project = "";
    email = "";
    showConfirmation = false;
    showFailure = true;
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  public String getProject() {
    return project;
  }

  public void setProject(String project) {
    propertyChangeSupport.firePropertyChange("project", this.project, project);
    this.project = project;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    propertyChangeSupport.firePropertyChange("email", this.email, email);
    this.email = email;
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
