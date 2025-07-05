package com.example.core_ui.composables

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import coil3.imageLoader
import com.example.core_ui.theme.AppTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryTabTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    private val tabName = "Health"

    @Test
    fun categoryTab_rendersCorrectly_whenUnselected() {
        composeRule.setContent {
            AppTheme(imageLoader = LocalContext.current.imageLoader) {
                CategoryTab(
                    tabName = tabName,
                    isSelected = false,
                    onClick = {}
                )
            }
        }

        composeRule.onNodeWithTag("CategoryTab_$tabName").assertIsDisplayed()
        composeRule.onNodeWithText(tabName).assertIsDisplayed()
    }

    @Test
    fun categoryTab_rendersCorrectly_whenSelected() {
        composeRule.setContent {
            AppTheme(imageLoader = LocalContext.current.imageLoader) {
                CategoryTab(
                    tabName = tabName,
                    isSelected = true,
                    onClick = {}
                )
            }
        }

        composeRule.onNodeWithTag("CategoryTab_$tabName").assertIsDisplayed()
        composeRule.onNodeWithText(tabName).assertIsDisplayed()
    }

    @Test
    fun categoryTab_invokesCallback_onClick() {
        var clickedValue: String? = null

        composeRule.setContent {
            AppTheme(imageLoader = LocalContext.current.imageLoader) {
                CategoryTab(
                    tabName = tabName,
                    isSelected = false,
                    onClick = { clickedValue = it }
                )
            }
        }

        composeRule.onNodeWithTag("CategoryTab_$tabName").performClick()

        composeRule.runOnIdle {
            assert(clickedValue == tabName) {
                "Expected callback to be called with $tabName, but was $clickedValue"
            }
        }
    }
}