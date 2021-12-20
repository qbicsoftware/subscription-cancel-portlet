package life.qbic.portal.subscription

import life.qbic.business.subscription.CancelSubscription
import life.qbic.business.subscription.CancellationConfirmation
import life.qbic.business.subscription.api.CancelSubscriptionInput
import life.qbic.business.subscription.api.CancelSubscriptionOutput
import life.qbic.business.subscription.api.SubscriptionService
import life.qbic.business.subscription.exceptions.SubscriptionServiceException
import life.qbic.portal.subscription.persistence.Subscriptions
import life.qbic.portal.utils.ConfigurationManager
import life.qbic.portal.utils.ConfigurationManagerFactory

class DependencyManager {

    private final ConfigurationManager configurationManager
    private final CancelSubscriptionInput cancelSubscriptionInput

    DependencyManager() {
        this.configurationManager = ConfigurationManagerFactory.getInstance()

        this.cancelSubscriptionInput = new CancelSubscription(new CancelSubscriptionOutput() {
            @Override
            void onSuccess(CancellationConfirmation request) {
                println "Worked: $request"
            }

            @Override
            void onFailure(String reason) {
                println "Failed: $reason"
            }
        }, new Subscriptions(configurationManager.getServicesSubscriptionUrl()))
    }

    CancelSubscriptionInput getCancelSubscriptionInput() {
        return cancelSubscriptionInput
    }

}
