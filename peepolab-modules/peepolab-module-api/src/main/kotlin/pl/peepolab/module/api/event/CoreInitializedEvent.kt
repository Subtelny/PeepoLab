package pl.peepolab.module.api.event

import io.micronaut.context.event.ApplicationEvent
import pl.peepolab.module.api.CoreContext

class CoreInitializedEvent(coreContext: CoreContext) : ApplicationEvent(coreContext) {
    override fun getSource(): CoreContext {
        return super.getSource() as CoreContext
    }
}
