package br.com.everis.sovamu.feature.home.model

data class MockData(val title: String, val size: String)

val mockList = mutableListOf(
        MockData(
                title = "90th minute",
                size = "4.39Mb"
        ),
        MockData(
                title = "50th minute",
                size = "4.39Mb"
        ),
        MockData(
                title = "90th minute",
                size = "4.39Mb"
        ),
        MockData(
                title = "90th minute",
                size = "4.39Mb"
        )
)

data class MockNotification(val title: String, val message: String)

val mockListNotification = listOf(
        MockNotification(
                title = "First Notification ",
                message = "Text first notification"
        ),
        MockNotification(
                title = "Second Notification",
                message = "Text second notification"
        ),
        MockNotification(
                title = "Third Notification",
                message = "Text third notification"
        )
)