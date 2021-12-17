package life.qbic.portal.subscription.view

import com.vaadin.icons.VaadinIcons
import com.vaadin.shared.ui.ContentMode
import com.vaadin.ui.Alignment
import com.vaadin.ui.Label
import com.vaadin.ui.Layout
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme
import life.qbic.portal.subscription.view.model.SubscriptionModel

/**
 * <class short description - 1 Line!>
 *
 * <More detailed description - When to use, what it solves, etc.>
 *
 * @since <version tag>
 */
class SubscriptionInterface extends VerticalLayout {

    SubscriptionModel model

    Label project

    Label email

    Layout confirmation

    Layout failureNotification

    SubscriptionInterface(SubscriptionModel model) {
        this.model = model
        init()
        bindToModel()
    }

    void bindToModel() {
        project.setValue(model.project)
        email.setValue(model.email)
        confirmation.setVisible(model.showConfirmation)
        failureNotification.setVisible(model.showFailure)
    }

    void init() {
        createConfirmationLayout()
        createFailureNotificationLayout()
    }

    void createConfirmationLayout() {
        confirmation = new VerticalLayout()
        Label affirmative = new Label(VaadinIcons.CHECK_CIRCLE_O.getHtml(), ContentMode.HTML)
        affirmative.addStyleName(ValoTheme.LABEL_H1)
        affirmative.addStyleName(ValoTheme.LABEL_COLORED)
        affirmative.setSizeUndefined()
        Label mainInfo = new Label("Subscription cancelled.")
        mainInfo.addStyleName(ValoTheme.LABEL_H1)
        mainInfo.addStyleName(ValoTheme.LABEL_COLORED)
        mainInfo.setSizeUndefined()
        Label label = new Label("We stop sending updates for")
        label.setStyleName(ValoTheme.LABEL_SMALL)
        label.setSizeUndefined()
        project = new Label("QTEST")
        project.addStyleName(ValoTheme.LABEL_H3)
        project.setSizeUndefined()
        Label toLabel = new Label("to")
        toLabel.setStyleName(ValoTheme.LABEL_SMALL)
        toLabel.setSizeUndefined()
        email  = new Label("sven.fillinger@web.de")
        email.setStyleName(ValoTheme.LABEL_H3)
        email.setSizeUndefined()
        confirmation.addComponent(affirmative)
        confirmation.addComponent(mainInfo)
        confirmation.addComponent(label)
        confirmation.addComponent(project)
        confirmation.addComponent(toLabel)
        confirmation.addComponent(email)
        confirmation.setComponentAlignment(affirmative, Alignment.MIDDLE_CENTER)
        confirmation.setComponentAlignment(mainInfo, Alignment.MIDDLE_CENTER)
        confirmation.setComponentAlignment(label, Alignment.MIDDLE_CENTER)
        confirmation.setComponentAlignment(project, Alignment.MIDDLE_CENTER)
        confirmation.setComponentAlignment(toLabel, Alignment.MIDDLE_CENTER)
        confirmation.setComponentAlignment(email, Alignment.MIDDLE_CENTER)
        confirmation.setSizeFull()
        this.addComponent(confirmation)
    }

    void createFailureNotificationLayout() {
        failureNotification = new VerticalLayout()
        Label failure = new Label(VaadinIcons.CLOSE.getHtml(), ContentMode.HTML)
        failure.addStyleName(ValoTheme.LABEL_H1)
        failure.addStyleName("failure-red")
        failure.setSizeUndefined()
        Label mainInfo = new Label("Cancellation failed!")
        mainInfo.addStyleName(ValoTheme.LABEL_H1)
        mainInfo.addStyleName("failure-red")
        mainInfo.setSizeUndefined()
        Label label = new Label("You did not cancel you subscription.")
        label.setStyleName(ValoTheme.LABEL_SMALL)
        label.setSizeUndefined()
        Label label2 = new Label("Write an email to <a href='mailto:support@qbic.zendesk.com'>support@qbic.zendesk.com</a> for help.", ContentMode.HTML)
        label.setStyleName(ValoTheme.LABEL_SMALL)
        label.setSizeUndefined()
        failureNotification.addComponent(failure)
        failureNotification.addComponent(mainInfo)
        failureNotification.addComponent(label)
        failureNotification.addComponent(label2)
        failureNotification.setComponentAlignment(failure, Alignment.MIDDLE_CENTER)
        failureNotification.setComponentAlignment(mainInfo, Alignment.MIDDLE_CENTER)
        failureNotification.setComponentAlignment(label, Alignment.MIDDLE_CENTER)
        failureNotification.setComponentAlignment(label2, Alignment.MIDDLE_CENTER)
        this.addComponent(failureNotification)
    }
}
