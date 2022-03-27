package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OwnerController {

    @GetMapping({"/owners", "/owners/index"})
    public String listOwners() {
        return  "owners/index";
    }
}
