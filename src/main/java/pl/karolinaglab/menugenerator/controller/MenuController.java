package pl.karolinaglab.menugenerator.controller;

import org.springframework.web.bind.annotation.*;
import pl.karolinaglab.menugenerator.model.Menu;
import pl.karolinaglab.menugenerator.service.MenuService;

import java.util.Map;

@RestController
public class MenuController {

    final private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/menu")
    public Menu addMenu(@RequestBody Map<String,String> body) throws Exception{

        return menuService.createMenu(body);
    }

    @GetMapping("/menu/{id}")
    public Menu getRecipe(@PathVariable int id) throws Exception {
        return menuService.getMenu(id);
    }
}
