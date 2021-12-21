package life.qbic.portal.subscription.view.model

import com.vaadin.icons.VaadinIcons
import com.vaadin.shared.ui.ContentMode
import com.vaadin.ui.Alignment
import com.vaadin.ui.Component
import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme

/**
 * The confirmation screen we want to display to the user upon successful
 * subscription cancellation.
 * @since 1.0.0
 */
class ConfirmationDisplay extends VerticalLayout {

    /**
     * The project identifier we are going to display
     * upon confirmation of the subscription cancellation
     */
    private Label project

    /**
     * The email for which the cancellation has been confirmed
     */
    private Label email

    private LinkedList<Component> components

    ConfirmationDisplay() {
        components = new ArrayList<>()
        buildDisplay()
    }

    private void buildDisplay() {
        Label affirmative = new Label(VaadinIcons.CHECK_CIRCLE_O.getHtml(), ContentMode.HTML)
        affirmative.addStyleName(ValoTheme.LABEL_H1)
        affirmative.addStyleName(ValoTheme.LABEL_COLORED)

        Label topic = new Label("Subscription cancelled!")
        topic.addStyleName(ValoTheme.LABEL_H1)
        topic.addStyleName(ValoTheme.LABEL_COLORED)

        Label info = new Label("You will not receive further updates for")
        info.setStyleName(ValoTheme.LABEL_SMALL)

        project = new Label("PROJECT PLACEHOLDER")
        project.addStyleName(ValoTheme.LABEL_H3)

        Label toLabel = new Label("to")
        toLabel.setStyleName(ValoTheme.LABEL_SMALL)

        email = new Label("EMAIL PLACEHOLDER")
        email.setStyleName(ValoTheme.LABEL_H3)

        components.addAll([affirmative, topic, info, project, toLabel, email])
        components.stream()
                .forEach((Component c) -> {
                    c.setSizeUndefined()
                    this.addComponent(c)
                    this.setComponentAlignment(c, Alignment.MIDDLE_CENTER)
                })
        this.setSizeFull()
    }

    ConfirmationDisplay setProject(String project) {
        this.project.setValue(project)
        return this
    }

    ConfirmationDisplay setEmail(String email) {
        this.email.setValue(email)
        return this
    }
}
