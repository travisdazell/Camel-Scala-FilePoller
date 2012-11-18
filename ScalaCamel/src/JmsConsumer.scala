import javax.jms.ConnectionFactory
import org.apache.camel.CamelContext
import org.apache.camel.ConsumerTemplate
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.component.jms.JmsComponent
import org.apache.activemq.ActiveMQConnectionFactory

object JmsClient {
	def main(args : Array[String]) {
		val connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false")
  	    val context = new DefaultCamelContext()

		context.addComponent("activemq", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory))

        context.addRoutes(new JmsRoutes())

        val consumerTemplate = context.createConsumerTemplate()

        context.start()
        
      	consumerTemplate.receive("activemq:queue:test.queue")

       	println("received message")
		
        context.stop()
	}
}