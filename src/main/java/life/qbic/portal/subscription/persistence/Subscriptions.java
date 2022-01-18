package life.qbic.portal.subscription.persistence;

import java.util.Objects;
import life.qbic.business.subscription.api.SubscriptionService;
import life.qbic.business.subscription.exceptions.SubscriptionServiceException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <b>Class Subscriptions</b>
 *
 * <p>Implementation of the {@link SubscriptionService} interface. Calls the QBiC REST service that
 * handles the subscriptions.
 *
 * @since 1.0.0
 */
@Component
class Subscriptions implements SubscriptionService {

  public static final String CANCEL_ENDPOINT = "/subscriptions";
  private final String serviceEndPoint;
  Logger logger = LoggerFactory.getLogger(this.getClass());
  final ResponseHandler<Boolean> responseHandler =
      response -> {
        int status = response.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
          return true;
        } else {
          logger.error("Subscription cancelling failed.");
          logger.error(String.valueOf(response.getStatusLine()));
          throw new SubscriptionServiceException("Cancellation failed.");
        }
      };

  @Autowired
  public Subscriptions(@Value("${service.url}") String serviceUrlBase) {
    String serviceUrl = Objects.requireNonNull(serviceUrlBase);
    this.serviceEndPoint = serviceUrl + CANCEL_ENDPOINT;
  }

  @Override
  public void cancelRequest(String requestToken)
      throws SubscriptionServiceException {
    if (requestToken == null || requestToken.isBlank()) {
      throw new SubscriptionServiceException("Request token must not be empty!");
    }
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpDelete httpDelete = new HttpDelete(serviceEndPoint + "/" + requestToken);
    try {
      httpClient.execute(httpDelete, responseHandler);
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new SubscriptionServiceException(
          "Subscription service did not cancel the subscription for request " + requestToken);
    }
  }

}
