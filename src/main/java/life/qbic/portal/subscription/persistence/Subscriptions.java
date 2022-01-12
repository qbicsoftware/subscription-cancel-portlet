package life.qbic.portal.subscription.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Objects;
import life.qbic.business.subscription.CancellationConfirmation;
import life.qbic.business.subscription.api.SubscriptionService;
import life.qbic.business.subscription.exceptions.SubscriptionServiceException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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

  public static final String CANCEL_ENDPOINT = "/subscription/cancel";
  private final String serviceUrlBase;
  private final String serviceEndPoint;
  Logger logger = LoggerFactory.getLogger(this.getClass());
  final ResponseHandler<String> responseHandler =
      response -> {
        int status = response.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
          HttpEntity entity = response.getEntity();
          return entity != null ? EntityUtils.toString(entity) : null;
        } else {
          logger.error("Subscription cancelling failed.");
          logger.error(String.valueOf(response.getStatusLine()));
          throw new SubscriptionServiceException("Cancellation failed.");
        }
      };

  @Autowired
  public Subscriptions(@Value("${service.url}") String serviceUrlBase) {
    this.serviceUrlBase = Objects.requireNonNull(serviceUrlBase);
    this.serviceEndPoint = serviceUrlBase + CANCEL_ENDPOINT;
  }

  @Override
  public CancellationConfirmation cancelRequest(String requestToken)
      throws SubscriptionServiceException {
    if (requestToken == null || requestToken.isBlank()) {
      throw new SubscriptionServiceException("Request token must not be empty!");
    }
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost(serviceEndPoint + "/" + requestToken);
    httpPost.setHeader("Accept", "application/json");
    try {
      String response = httpClient.execute(httpPost, responseHandler);
      return parseConfirmation(response);
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new SubscriptionServiceException(
          "Subscription service did not cancel the subscription for request " + requestToken);
    }
  }

  private CancellationConfirmation parseConfirmation(String response) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(response, CancellationConfirmation.class);
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new SubscriptionServiceException("Could not confirm cancellation");
    }
  }
}
