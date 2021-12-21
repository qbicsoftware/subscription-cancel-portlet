package life.qbic.portal.subscription.view

import com.vaadin.icons.VaadinIcons
import com.vaadin.shared.ui.ContentMode
import com.vaadin.ui.Alignment
import com.vaadin.ui.Label
import com.vaadin.ui.Layout
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme
import life.qbic.portal.subscription.view.model.ConfirmationDisplay
import life.qbic.portal.subscription.view.model.FailureDisplay
import life.qbic.portal.subscription.view.model.SubscriptionModel

/**
 * <b>Class SubscriptionInterface</b>
 *
 * <p>The interface, displaying the subscription cancellation confirmation to the user.</p>
 *
 * @since 1.0.0
 */
class SubscriptionInterface extends VerticalLayout {

    /**
     * The interface data model
     */
    SubscriptionModel model

    /**
     * The screen that is displayed upon successful subscription cancellation
     */
    ConfirmationDisplay confirmationDisplay

    /**
     * The screen that is displayed upon cancellation failure
     */
    FailureDisplay failureDisplay

    /**
     * The constructor to use with its data model passed as argument.
     * @param model the interface data model
     */
    SubscriptionInterface(SubscriptionModel model) {
        this.model = model
        init()
        bindToModel()
        setListeners()
    }

    private void bindToModel() {
        confirmationDisplay.setVisible(model.showConfirmation)
        failureDisplay.setVisible(model.showFailure)
    }

    private void setListeners() {
        model.addPropertyChangeListener("project", {
            confirmationDisplay.setProject(it.newValue as String)
        })
        model.addPropertyChangeListener("email", {
            confirmationDisplay.setEmail(it.newValue as String)
        })
        model.addPropertyChangeListener("showConfirmation", {
            failureDisplay.setVisible(!it.newValue as Boolean)
            confirmationDisplay.setVisible(it.newValue as Boolean)
        })

    }

    private void init() {
        createConfirmationLayout()
        createFailureNotificationLayout()
    }

    private void createConfirmationLayout() {
        this.confirmationDisplay = new ConfirmationDisplay()
        this.addComponent(confirmationDisplay)
    }

    private void createFailureNotificationLayout() {
        this.failureDisplay = new FailureDisplay()
        this.addComponent(failureDisplay)
    }
}
