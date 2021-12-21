package life.qbic.portal.subscription.view.model

import com.vaadin.icons.VaadinIcons
import com.vaadin.shared.ui.ContentMode
import com.vaadin.ui.Alignment
import com.vaadin.ui.Component
import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme

/**
 * Display screen we want to show to the user, when the subscription cancellation has failed.
 *
 * @since 1.0.0
 */
class FailureDisplay extends VerticalLayout {

    FailureDisplay() {
        buildDisplay()
    }

    private void buildDisplay() {
        Label failureIcon = new Label(VaadinIcons.CLOSE_CIRCLE_O.getHtml(), ContentMode.HTML)
        failureIcon.addStyleName(ValoTheme.LABEL_H1)
        failureIcon.addStyleName("failure-red")

        Label topic = new Label("Cancellation failed!")
        topic.addStyleName(ValoTheme.LABEL_H1)
        topic.addStyleName("failure-red")

        Label failureNote = new Label("You did not cancel you subscription.")
        failureNote.setStyleName(ValoTheme.LABEL_SMALL)

        Label actionNote = new Label("Please write an email to <a href='mailto:support@qbic.zendesk.com'>support@qbic.zendesk.com</a> for help.", ContentMode.HTML)
        actionNote.setStyleName(ValoTheme.LABEL_SMALL)

        List<Component> components = [failureIcon, topic, failureNote, actionNote]
        components.stream().forEach(c -> {
            c.setSizeUndefined()
            this.addComponent(c)
            this.setComponentAlignment(c, Alignment.MIDDLE_CENTER)
        })

        this.setSizeFull()
    }
}
