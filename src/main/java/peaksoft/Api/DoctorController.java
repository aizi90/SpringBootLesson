package peaksoft.Api;


        import lombok.RequiredArgsConstructor;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;
        import peaksoft.entities.Department;
        import peaksoft.entities.Doctor;
        import peaksoft.service.DepartmentService;
        import peaksoft.service.DoctorService;
        import peaksoft.service.HospitalService;

@Controller
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    private final HospitalService hospitalService;

    private final DepartmentService departmentService;


    @GetMapping("/{hospitalId}")
    public String getAll(@PathVariable Long hospitalId, Model model){
        model.addAttribute("allHospitals", hospitalService.getAllHospital());
        return "doctor/getAllDoctors";

    }

    @GetMapping("/{hospitalId}/new")
    public String create(@PathVariable("hospitalId") Long hospitalId, Model model){
        model.addAttribute("newDoctor", new Doctor());
        model.addAttribute(hospitalId);
        model.addAttribute("departments", hospitalService.getHospitalById(hospitalId));
        return "doctor/newDoctor";

    }

    @PostMapping("/{hospitalId}/save")
    public String save(@PathVariable long hospitalId, @ModelAttribute("newDoctor") Doctor doctor){
        doctorService.saveDoctor(hospitalId, doctor);
        return "redirect:/doctors/"+hospitalId;

    }

    @DeleteMapping("/{hospitalId}/{doctorId}/delete")
    public String delete(@PathVariable("hospitalId") Long hospitalId, @PathVariable("doctorId") Long doctorId){
        doctorService.deleteDoctorById(doctorId);
        return "redirect:/doctors/"+hospitalId;

    }

    @GetMapping("/{hospitalId}/{doctorId}/edit")
    public String edit(@PathVariable("doctorId") Long id, @PathVariable("hospitalId") Long hospitalId, Model model) {
        model.addAttribute("doctor", doctorService.getDoctorById(id));
        model.addAttribute("hospitalId", hospitalId);
        return "doctor/update";
    }

    @PostMapping("/{hospitalId}/{doctorId}/update")
    public String update(@PathVariable("hospitalId") Long hospitalId, @PathVariable("doctorId") Long doctorId, @ModelAttribute("doctor") Doctor doctor) {
        doctorService.updateDoctor(doctorId, doctor);
        return "redirect:/doctors/" + hospitalId;
    }

    @PostMapping("/{hospitalId}/assign/{doctorId}")
    public String assignDoctor(@PathVariable("hospitalId") Long hospitalId, @PathVariable("doctorId")Long doctorId, @ModelAttribute("department") Department department){
        departmentService.assignDoctor(doctorId, department.getId());
        return "redirect:/doctors/"+hospitalId;
    }

}