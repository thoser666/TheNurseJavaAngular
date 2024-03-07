package biz.brumm.thenursejavaangular.partitioningstrategy;

public record ReceivedMessage(String key, String message, int partition) {

  @Override
  public String toString() {
    return "Key: " + key + " - Message: " + message + " - Partition: " + partition;
  }
}
