package life.qbic.business.subscription;

/**
 * The subscription cancellation request
 *
 * @since 1.0.0
 */
public class CancellationConfirmation {

  private String project;

  private String userId;

  public String getProject() {
    return project;
  }

  public void setProject(String project) {
    this.project = project;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "CancellationConfirmation{"
        + "project='"
        + project
        + '\''
        + ", userId='"
        + userId
        + '\''
        + '}';
  }
}
