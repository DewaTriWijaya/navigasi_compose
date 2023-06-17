package com.dewandroid.composelearn

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.testing.TestNavHostController
import org.junit.Assert.assertEquals
import org.junit.Before

import org.junit.Rule
import org.junit.Test

@get:Rule
val composeTestRule = createAndroidComposeRule<ComponentActivity>()

private lateinit var navController: TestNavHostController

@Before
fun setupCupcakeNavHost(){
    composeTestRule.setContent {
        navController =
            TestNavHostController(LocalContext.current)
        navController.navigatorProvider.addNavigator(
            ComposeNavigator()
        )
        CupcakeApp(navController = navController)
    }
}

@Test
fun cupcakeNavHost_verifyStartDestination(){
    //assertEquals(CupcakeScreen.Start.name, navController.currentBackStackEntry?.destination?.route)
    navController.assertCurrentRouteName(CupcakeScreen.Start.name)
}

@Test
fun cupcakeNavHost_verifyBackNavigationNotShownOnStartOrderScreen(){
    val backText = composeTestRule.activity.getString(R.string.back_button)
    composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
}

@Test
fun cupcakeNavHost_clickOneCupcake_navigatesToSelectFlavorScreen(){
    composeTestRule.onNodeWithStringId(R.string.one_cupcake)
        .performClick()
    navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
}

private fun navigateToFlavorScreen(){
    composeTestRule.onNodeWithStringId(R.string.one_cupcake)
        .performClick()
    composeTestRule.onNodeWithStringId(R.string.chocolate)
        .performClick()
}
