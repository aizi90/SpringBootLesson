package peaksoft.Api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Hospital;
import peaksoft.service.HospitalService;

@Controller
@RequestMapping("/hospitals")//база данныйдагы таблицанын аты
@RequiredArgsConstructor


public class HospitalController {
    private final HospitalService hospitalService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("hospitals", hospitalService.getAllHospital());

        return "getAllHospitals";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("newHospital", new Hospital());
        return "hospital/newHospital";

    }

    @PostMapping("/save")
    public String save(@ModelAttribute("newHospital") Hospital hospital) {
        hospitalService.saveHospital(hospital);
        return "redirect:/hospitals";

    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam("hospitalId") Long id) {
        hospitalService.deleteHospitalById(id);
        return "redirect:/hospitals";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("Hospital1", hospitalService.getHospitalById(id));
        return "hospital/updateHospital";

    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute("hospital") Hospital hospital) {
        hospitalService.updateHospital(id, hospital);
        return "redirect:hospitals";

    }
}




