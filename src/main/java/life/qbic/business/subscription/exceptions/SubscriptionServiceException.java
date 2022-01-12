package life.qbic.business.subscription.exceptions;

/**
 * Exception to be thrown, when an exception occurs during the subscription service task execution.
 *
 * @since 1.0.0
 */
public class SubscriptionServiceException extends RuntimeException {

  public SubscriptionServiceException() {
    super();
  }

  public SubscriptionServiceException(String message) {
    super(message);
  }
}
