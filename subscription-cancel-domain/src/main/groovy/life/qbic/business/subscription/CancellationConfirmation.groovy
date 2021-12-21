package life.qbic.business.subscription

import groovy.transform.ToString

@ToString
/**
 * The subscription cancellation request
 * @since 1.0.0
 */
class CancellationConfirmation {

    String project

    String userId
}
