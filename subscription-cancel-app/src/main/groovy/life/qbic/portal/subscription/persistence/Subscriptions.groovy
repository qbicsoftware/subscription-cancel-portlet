package life.qbic.portal.subscription.persistence


import groovy.json.JsonSlurper
import groovy.util.logging.Log4j2
import life.qbic.business.subscription.CancellationConfirmation
import life.qbic.business.subscription.api.SubscriptionService
import life.qbic.business.subscription.exceptions.SubscriptionServiceException
import org.apache.http.HttpEntity
import org.apache.http.client.ResponseHandler
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils

@Log4j2
/**
 * <b>Class Subscriptions</b>
 *
 * <p>Implementation of the {@link SubscriptionService} interface. Calls the QBiC REST
 * service that handles the subscriptions.</p>
 * @since 1.0.0
 */
class Subscriptions implements SubscriptionService {

    public static final String CANCEL_ENDPOINT = "/subscription/cancel"

    private final String serviceUrlBase

    private final String serviceEndPoint

    static final ResponseHandler<String> responseHandler = response -> {
        int status = response.getStatusLine().getStatusCode()
        if (status >= 200 && status < 300) {
            HttpEntity entity = response.getEntity()
            return entity ? EntityUtils.toString(entity) : null
        } else {
            log.error("Subscription cancelling failed.")
            log.error(response.getStatusLine())
            throw new SubscriptionServiceException("Cancellation failed.")
        }
    }

    Subscriptions(String serviceUrlBase) {
        this.serviceUrlBase = Objects.requireNonNull(serviceUrlBase)
        this.serviceEndPoint = serviceUrlBase + CANCEL_ENDPOINT
        log.info(serviceEndPoint)
    }

    @Override
    CancellationConfirmation cancelRequest(String requestToken) throws SubscriptionServiceException {
        if (!requestToken) {
            throw new SubscriptionServiceException("Request token must not be empty!")
        }
        CloseableHttpClient httpClient = HttpClients.createDefault()
        HttpPost httpPost = new HttpPost(serviceEndPoint + "/" + requestToken)
        httpPost.setHeader("Accept", "application/json")
        String response = httpClient.execute(httpPost, responseHandler)
        return parseConfirmation(response)
    }

    private static CancellationConfirmation parseConfirmation(String response) {
        try {
            Map parsedText = new JsonSlurper().parseText(response) as Map
            return new CancellationConfirmation(project: parsedText['project'], userId: parsedText['userId'])
        } catch (Exception e) {
            log.error(e)
            throw new SubscriptionServiceException("Could confirm cancellation")
        }
    }
}
