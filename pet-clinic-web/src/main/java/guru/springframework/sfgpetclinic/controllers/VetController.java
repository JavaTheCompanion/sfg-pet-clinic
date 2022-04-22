package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping({"/vets", "/vets/index", "/vets.html"})
    public String showVetList(@RequestParam(defaultValue = "1") int page, Model model) {
        final Page<Vet> vetResults = this.findPaginated(page);
        return this.addPaginationModel(page, model, vetResults);
    }

    /**
     * @param page
     * @return
     */
    private Page<Vet> findPaginated(int page) {
        final int pageSize = 4;
        final Pageable pageable = PageRequest.of(page - 1, pageSize);
        return this.vetService.findAll(pageable);
    }

    /**
     * @param page
     * @param model
     * @param paginated
     * @return
     */
    private String addPaginationModel(int page, Model model, Page<Vet> paginated) {
        List<Vet> listVets = paginated.getContent();
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", paginated.getTotalPages());
        model.addAttribute("totalItems", paginated.getTotalElements());
        model.addAttribute("listVets", listVets);

        return "vets/vetList";
    }

    @GetMapping({"/api/vets"})
    public @ResponseBody
    List<Vet> showResourcesVetList() {
        return new ArrayList<>(this.vetService.findAll());
    }

}
