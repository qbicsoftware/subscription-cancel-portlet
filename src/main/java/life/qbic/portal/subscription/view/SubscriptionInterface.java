package life.qbic.portal.subscription.view;


import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import life.qbic.portal.subscription.view.model.SubscriptionModel;

/**
 * <b>Class SubscriptionInterface</b>
 *
 * <p>The interface, displaying the subscription cancellation confirmation to the user.</p>
 *
 * @since 1.0.0
 */
public class SubscriptionInterface extends VerticalLayout {

    /**
     * The interface data model
     */
    SubscriptionModel model;

    /**
     * The screen that is displayed upon successful subscription cancellation
     */
    ConfirmationDisplay confirmationDisplay;

    /**
     * The screen that is displayed upon cancellation failure
     */
    FailureDisplay failureDisplay;

    /**
     * The constructor to use with its data model passed as argument.
     * @param model the interface data model
     */
    public SubscriptionInterface(SubscriptionModel model) {
        this.model = model;
        init();
        bindToModel();
        setChangeListeners();
    }

    private void setChangeListeners() {
    this.model.addPropertyChangeListener(
        evt -> {
          switch (evt.getPropertyName()) {
            case "project" -> confirmationDisplay.setProject((String) evt.getNewValue());
            case "email" -> confirmationDisplay.setEmail((String) evt.getNewValue());
            case "showConfirmation" -> confirmationDisplay.setVisible(
                (Boolean) evt.getNewValue());
            case "showFailure" -> failureDisplay.setVisible((Boolean) evt.getNewValue());
          }
        });
    }

    private void bindToModel() {
        confirmationDisplay.setVisible(model.isShowConfirmation());
        failureDisplay.setVisible(model.isShowFailure());
    }

    private void init() {
        createConfirmationLayout();
        createFailureNotificationLayout();
    }

    private void createConfirmationLayout() {
        this.confirmationDisplay = new ConfirmationDisplay();
        this.add(confirmationDisplay);
    }

    private void createFailureNotificationLayout() {
        this.failureDisplay = new FailureDisplay();
        this.add(failureDisplay);
    }

}
