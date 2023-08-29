package pl.peepolab.integration.slack.model

import com.google.common.base.Preconditions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import pl.peepolab.module.api.integration.dto.IntegrationType

class SlackIntegrationTypeKtTest {

    @Test
    fun `Should properly recognize type of IntegrationType while using isSlack() function`() {
        // Given
        val otherIntegrationType = object : IntegrationType("OTHER") {}
        val slackIntegrationType = SlackIntegrationType
        // When

        val isSlackOtherIntegrationType = otherIntegrationType.isSlack()
        val isSlackSlackIntegrationType = slackIntegrationType.isSlack()

        //Then
        assertThat(isSlackOtherIntegrationType).isFalse()
        assertThat(isSlackSlackIntegrationType).isTrue()
    }
}