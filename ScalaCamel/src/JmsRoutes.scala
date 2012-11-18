import org.apache.camel.scala.dsl.builder.RouteBuilder
import org.apache.camel.model.dataformat.BindyType

class JmsRoutes extends RouteBuilder {
	"file://incoming" --> "activemq:queue:test.queue"
}