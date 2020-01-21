package pl.karolinaglab.menugenerator.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.karolinaglab.menugenerator.model.Menu;
import pl.karolinaglab.menugenerator.security.CurrentUser;
import pl.karolinaglab.menugenerator.security.UserPrincipal;
import pl.karolinaglab.menugenerator.service.MenuService;

import java.util.Map;

@RestController
public class MenuController {

    final private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/menu")
    public Menu addMenu(@RequestBody Map<String,String> body, @CurrentUser UserPrincipal currentUser) throws Exception{

        return menuService.createMenu(body, currentUser);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/menu/{id}")
    public Menu getRecipe(@PathVariable int id) throws Exception {
        return menuService.getMenu(id);
    }
}
