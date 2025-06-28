package guru.springframework.springrestclientexamples.controllers;

import guru.springframework.springrestclientexamples.services.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ServerWebExchange;

@Controller
@Slf4j
public class UserController {

    private final ApiService apiService;

    public UserController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping({"", "/", "/index"})
    public String index() {
        return "index";
    }

    @PostMapping("/users")
    public String formPost(Model model, ServerWebExchange serverWebExchange) {
//        var users = serverWebExchange.getFormData()
//            .map(map -> {
//                var limit = 0;
//                try {
//                    limit = Integer.parseInt(map.get("limit").get(0));
//                    log.debug("Received limit value {}", limit);
//                    if (limit <= 0) {
//                        log.debug("Setting limit to default of 10");
//                        limit = 10;
//                    }
//                } catch (NumberFormatException e) {
//                    log.error("Invalid limit value, setting to default of 10");
//                    limit = 10;
//                }
//                return apiService.getUsers(limit);
//            });
//
//        model.addAttribute("users", users);

        model.addAttribute("users",
            apiService.getUsers(serverWebExchange.getFormData()
                .map(data -> Integer.parseInt(data.getFirst("limit")))));

        return "userlist";
    }
}
