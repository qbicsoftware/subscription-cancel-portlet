package life.qbic.portal.subscription.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Display screen we want to show to the user, when the subscription cancellation has failed.
 *
 * @since 1.0.0
 */
public class FailureDisplay extends VerticalLayout {

  public FailureDisplay() {
    buildDisplay();
  }

  private void buildDisplay() {
    // A failure icon eye-catcher
    Icon failureIcon = new Icon(VaadinIcon.CLOSE_CIRCLE_O);
    failureIcon.setSize("80px");
    failureIcon.setColor("red");

    // The top level topic header
    var topic = new H1("Cancellation failed!");
    // A failure note
    var failureNote = new Paragraph("Your subscription was not cancelled.");
    // An action note for the user
    var actionNote =
        new Paragraph(
            new Text("Please contact "),
            new Anchor("mailto:support@qbic.zendesk.com", new Text("support@qbic.zendesk.com")),
            new Text("."));

    var components =
        new ArrayList<Component>(Arrays.asList(failureIcon, topic, failureNote, actionNote));
    // We align all components in center
    components.forEach(
        c -> {
          this.add(c);
          this.setHorizontalComponentAlignment(Alignment.CENTER, c);
        });
  }
}
