package mate.academy.spring.controller;

import mate.academy.spring.mapper.DtoResponseMapper;
import mate.academy.spring.model.ShoppingCart;
import mate.academy.spring.model.dto.response.ShoppingCartResponseDto;
import mate.academy.spring.service.MovieSessionService;
import mate.academy.spring.service.ShoppingCartService;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;
    private MovieSessionService movieSessionService;
    private UserService userService;
    private DtoResponseMapper<ShoppingCartResponseDto, ShoppingCart> dtoResponse;

    public ShoppingCartController(ShoppingCartService shoppingCartService, MovieSessionService movieSessionService, UserService userService, DtoResponseMapper<ShoppingCartResponseDto, ShoppingCart> dtoResponse) {
        this.shoppingCartService = shoppingCartService;
        this.movieSessionService = movieSessionService;
        this.userService = userService;
        this.dtoResponse = dtoResponse;
    }

    @PutMapping("/movie-session")
    public void createMovieSession(@RequestParam Long userId, @RequestParam Long movieSessionId) {
        shoppingCartService.addSession(movieSessionService.get(movieSessionId), userService.get(userId));
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(@RequestParam Long userId) {
        return  dtoResponse.toDto(shoppingCartService.getByUser(userService.get(userId)));
    }


}
