package life.qbic.portal.subscription

import com.vaadin.ui.Layout
import life.qbic.business.subscription.CancelSubscription
import life.qbic.business.subscription.CancellationConfirmation
import life.qbic.business.subscription.api.CancelSubscriptionInput
import life.qbic.business.subscription.api.CancelSubscriptionOutput
import life.qbic.portal.subscription.persistence.Subscriptions
import life.qbic.portal.subscription.view.SubscriptionInterface
import life.qbic.portal.subscription.view.SubscriptionPresenter
import life.qbic.portal.subscription.view.model.SubscriptionModel
import life.qbic.portal.utils.ConfigurationManager
import life.qbic.portal.utils.ConfigurationManagerFactory

class DependencyManager {

    private final ConfigurationManager configurationManager
    private final CancelSubscriptionInput cancelSubscriptionInput
    private final SubscriptionModel subscriptionModel
    private final SubscriptionInterface userInterface

    DependencyManager() {

        subscriptionModel = new SubscriptionModel()
        userInterface = new SubscriptionInterface(subscriptionModel)
        CancelSubscriptionOutput cancelSubscriptionOutput = new SubscriptionPresenter(subscriptionModel)
        this.configurationManager = ConfigurationManagerFactory.getInstance()

        this.cancelSubscriptionInput = new CancelSubscription(
                new SubscriptionPresenter(subscriptionModel),
                new Subscriptions(configurationManager.getServicesSubscriptionUrl()))
    }

    CancelSubscriptionInput getCancelSubscriptionInput() {
        return cancelSubscriptionInput
    }

    Layout getLayout() {
        return this.userInterface
    }

}
