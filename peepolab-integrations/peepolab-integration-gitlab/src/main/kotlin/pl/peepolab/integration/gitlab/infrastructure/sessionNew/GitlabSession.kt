package pl.peepolab.integration.gitlab.infrastructure.sessionNew

import org.gitlab4j.api.GitLabApi

interface GitlabSession {

    fun getGitlabApi(): GitLabApi

}