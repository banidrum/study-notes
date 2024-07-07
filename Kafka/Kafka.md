Kafka has the concept of Publish/Subscribe

Interested services can subscribe to a topic, and will be notified on each event/message that the topic produces

In Spring, KafkaTemplate is used to send the messages

KafkaListener is used to subscribe to the topic, and listen to the messages that are being produced

