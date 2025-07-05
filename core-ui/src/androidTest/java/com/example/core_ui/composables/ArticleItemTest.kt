package com.example.core_ui.composables

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import coil3.imageLoader
import com.example.core_ui.theme.AppTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleItemTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    private val title = "Breaking Developments in Heart Health"
    private val description = "Latest advancements in cardiology and heart care..."
    private val category = "Health"
    private val imageUrl = "https://example.com/images/heart-health.jpg"

    @Test
    fun articleItem_displaysAllTextFieldsAndTag() {
        composeRule.setContent {
            AppTheme(
                imageLoader = LocalContext.current.imageLoader
            ) {
                ArticleItem(
                    imageUrl = imageUrl,
                    title = title,
                    description = description,
                    category = category
                )
            }
        }

        // Assert parent container has the test tag
        composeRule.onNodeWithTag("ArticleItem_$title").assertIsDisplayed()

        // Assert category, title, description texts
        composeRule.onNodeWithText(category).assertIsDisplayed()
        composeRule.onNodeWithText(title, substring = true).assertIsDisplayed()
        composeRule.onNodeWithText(description, substring = true).assertIsDisplayed()
    }

    @Test
    fun articleItem_displaysImage() {
        composeRule.setContent {
            AppTheme(
                imageLoader = LocalContext.current.imageLoader
            ) {
                ArticleItem(
                    imageUrl = imageUrl,
                    title = title,
                    description = description,
                    category = category
                )
            }
        }

        composeRule.onNodeWithTag("OnlineImage").assertIsDisplayed()
    }
}
