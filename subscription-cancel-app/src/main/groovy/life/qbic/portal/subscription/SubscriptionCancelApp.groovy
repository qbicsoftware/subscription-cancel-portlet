package life.qbic.portal.subscription

import com.vaadin.annotations.Theme
import com.vaadin.server.VaadinPortletSession
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.Layout
import com.vaadin.ui.UI
import groovy.transform.CompileStatic
import groovy.util.logging.Log4j2

import javax.portlet.*

/**
 * Entry point for the application. This class derives from {@link life.qbic.portal.portlet.QBiCPortletUI}.
 *
 * @since: 1.0.0
 * @author: Jennifer Bödker
 * @see <a href=https://github.com/qbicsoftware/portal-utils-lib>portal-utils-lib</a>
 */
@Theme("mytheme")
@SuppressWarnings("serial")
@Log4j2
@CompileStatic
class SubscriptionCancelApp extends QBiCPortletUI implements VaadinPortletSession.PortletListener {

    private DependencyManager dependencyManager

    SubscriptionCancelApp() {
        super()
        // The constructor MUST NOT fail since the user does not get any feedback otherwise.
        try {
            create()
        } catch (Exception e) {
            log.error("Could not initialize {}", SubscriptionCancelApp.getCanonicalName(), e)
        } catch (Error error) {
            log.error("Unexpected runtime error.", error)
        }
    }

    private void create() {
        this.dependencyManager = new DependencyManager()
    }

    @Override
    protected Layout getPortletContent(final VaadinRequest request) {
        request.getParameterMap().forEach((s1, s2) -> {
            if(s1.equals("currentURL")) {
                log.info(s2.getClass().getName())
                log.info(s2.toString())
            }
            log.info(String.format("param: %s, value %s", s1, s2))
        })
        dependencyManager.getCancelSubscriptionInput().cancelSubscription(request.getParameter("token"))
        return dependencyManager.getLayout()
    }

    @Override
    void handleRenderRequest(RenderRequest renderRequest, RenderResponse renderResponse, UI ui) {
        log.info("handle render")
        dependencyManager.getCancelSubscriptionInput()
                .cancelSubscription(renderRequest.getParameter("token"))
    }

    @Override
    void handleActionRequest(ActionRequest actionRequest, ActionResponse actionResponse, UI ui) {
        log.info("handle action")
    }

    @Override
    void handleEventRequest(EventRequest eventRequest, EventResponse eventResponse, UI ui) {
        log.info("handle event")
    }

    @Override
    void handleResourceRequest(ResourceRequest resourceRequest, ResourceResponse resourceResponse, UI ui) {
        log.info("handle resource")
    }
}
