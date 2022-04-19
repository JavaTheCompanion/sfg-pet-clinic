package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

   /* @GetMapping({"", "/index"})
    public String listOwners(final Model model) {
        model.addAttribute("owners", this.ownerService.findAll());
        return "owners/index";
    }*/

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        Owner owner = this.ownerService.findById(ownerId);
        mav.addObject(owner);
        return mav;
    }

    @GetMapping("/find")
    public String initFindForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "owners/findOwners";
    }

    @GetMapping("")
    public String processFindForm(@RequestParam(defaultValue = "1") int page,
                                  Owner owner,
                                  BindingResult result,
                                  Model model) {

        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        String lastName = owner.getLastName();
        Page<Owner> ownersResults = this.findPaginatedForOwnersLastName(page, lastName);

        if (ownersResults.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (ownersResults.getTotalElements() == 1) {
            // 1 owner found
            owner = ownersResults.iterator().next();
            return "redirect:/owners/" + owner.getId();
        } else {
            // multiple owners found
            return this.addPaginationModel(page, model, ownersResults);
        }
    }

    /**
     * @param page
     * @param lastname
     * @return
     */
    private Page<Owner> findPaginatedForOwnersLastName(int page, String lastname) {
        final int pageSize = 5;
        final Pageable pageable = PageRequest.of(page - 1, pageSize);
        return this.ownerService.findByLastNameLike(lastname, pageable);

    }

    /**
     * @param page
     * @param model
     * @param paginated
     * @return
     */
    private String addPaginationModel(int page, Model model, Page<Owner> paginated) {
        //model.addAttribute("listOwners", paginated);

        List<Owner> listOwners = paginated.getContent();
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", paginated.getTotalPages());
        model.addAttribute("totalItems", paginated.getTotalElements());
        model.addAttribute("listOwners", listOwners);

        return "owners/ownersList";
    }

}
