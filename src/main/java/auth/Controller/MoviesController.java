package auth.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class MoviesController {

    @GetMapping("/api/movies")
    public String[] getMovies() {
        String[] moviesList = { "The Shawshank Redemption", "The Godfather", "The Dark Knight" };

        return moviesList;
    }

}
