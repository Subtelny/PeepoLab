package pl.peepolab.module.api

import pl.peepolab.module.api.integration.CoreContextExternalIntegrations

interface CoreContext {

    fun getCoreIntegrations(): CoreContextExternalIntegrations

}