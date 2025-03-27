import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.duetcs.BottomNavigationBar
import com.example.duetcs.about.About
import com.example.duetcs.contributor.Contributor
import com.example.duetcs.panels.Show_Panels_list

@Composable
fun MainScreen() {
    val navController = rememberNavController()


    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        NavHost(navController, startDestination = "about", Modifier.padding(paddingValues)) {

            composable("about") { About() }
            composable("panels") { Show_Panels_list () }
            composable("contributor") { Contributor() }

        }
    }
}


