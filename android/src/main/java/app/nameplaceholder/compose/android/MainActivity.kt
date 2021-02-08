package app.nameplaceholder.compose.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.platform.setContent
import app.nameplaceholder.common.App
import app.nameplaceholder.common.SampleViewModel

@ExperimentalMaterialApi
class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      App(viewModelFactory = { SampleViewModel() })
    }
  }
}
