package life.qbic.portal.subscription.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The confirmation screen we want to display to the user upon successful subscription cancellation.
 *
 * @since 1.0.0
 */
public class ConfirmationDisplay extends VerticalLayout {

  public ConfirmationDisplay() {
    buildDisplay();
  }

  private void buildDisplay() {
    // A failure icon eye-catcher
    Icon successIcon = new Icon(VaadinIcon.CHECK_CIRCLE_O);
    successIcon.setSize("80px");
    successIcon.setColor("green");

    // The top level topic header
    var topic = new H1("Subscription cancelled!");
    // A confirmation note
    var confirmation = new Paragraph("You will not receive further updates!");

    var components =
        new ArrayList<Component>(
            Arrays.asList(successIcon, topic, confirmation));
    // We align all components in center
    components.forEach(
        c -> {
          this.add(c);
          this.setHorizontalComponentAlignment(Alignment.CENTER, c);
        });
  }
}
