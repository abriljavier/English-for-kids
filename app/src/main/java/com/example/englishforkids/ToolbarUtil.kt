import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.englishforkids.R

// ToolbarUtil.kt

object ToolbarUtil {

    fun setupToolbar(activity: AppCompatActivity, toolbarId: Int, title: String) {
        val toolbar: Toolbar = activity.findViewById(toolbarId)
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> {
                // Acción para "Acerca de la app"
                Toast.makeText(this, "Acerca de la app", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menu_high_scores -> {
                // Acción para "Mejores puntuaciones"
                Toast.makeText(this, "Mejores puntuaciones", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
