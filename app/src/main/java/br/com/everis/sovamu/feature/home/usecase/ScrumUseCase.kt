package br.com.everis.sovamu.feature.home.usecase

import br.com.everis.sovamu.feature.home.model.MockNotification
import br.com.everis.sovamu.functional.Either

interface ScrumUseCase {
    suspend fun execute(): Either<String, MockNotification>
}