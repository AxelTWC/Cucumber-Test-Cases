package seg3102team3.project.infrastructure.web.controllers;

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.http.HttpSession

@Controller
class ApplicationErrorController: ErrorController {
@GetMapping("/error")
    fun showError(model: Model, session: HttpSession): String {
        return "error"
    }
}
