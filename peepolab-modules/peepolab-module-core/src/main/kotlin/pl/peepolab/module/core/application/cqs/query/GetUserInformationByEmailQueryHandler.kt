package pl.peepolab.module.core.application.cqs.query

import jakarta.inject.Singleton
import pl.peepolab.module.api.ui.query.GetUserInformationQuery
import pl.peepolab.module.api.ui.query.UserInformationResult
import pl.peepolab.module.core.application.cqs.CoreQueryHandler
import pl.peepolab.module.core.model.CoreUserRepository

@Singleton
class GetUserInformationByEmailQueryHandler(
    private val coreUserRepository: CoreUserRepository,
) : CoreQueryHandler<GetUserInformationQuery.ByEmail, UserInformationResult> {
    override fun handle(query: GetUserInformationQuery.ByEmail): UserInformationResult {
        return UserInformationResult(coreUserRepository.getCoreUser(query.email).id)
    }
}