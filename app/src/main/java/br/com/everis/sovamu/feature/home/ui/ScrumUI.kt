package br.com.everis.sovamu.feature.home.ui

import br.com.everis.sovamu.feature.home.model.MockNotification

interface ScrumUI {
    fun animationCardNotification(animation: Int)
    fun getNotification(notification: MockNotification)
    fun startAnimationNotification(
            notification: MockNotification,
            showCardAnimation: Int,
            hideCardAnimation: Int
    )

    fun endAnimationNotification(hideCardAnimation: Int)
}