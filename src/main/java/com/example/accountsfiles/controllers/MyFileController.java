package com.example.accountsfiles.controllers;
import com.example.accountsfiles.entities.MyFile;
import com.example.accountsfiles.services.ServiceMyFile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/account")
public class MyFileController {

    private final ServiceMyFile serviceMyFile;

    public MyFileController(ServiceMyFile serviceMyFile) {
        this.serviceMyFile = serviceMyFile;
    }

    @GetMapping()
    public String account(Model model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("name", name);
        return "html/account";
    }

    @GetMapping("/addFile")
    public String addFileForm() {
        return "html/addFile";
    }

    @PostMapping("/addFile")
    public String addFile(@RequestParam("file") MultipartFile file, Model model, Principal principal) throws Exception {
        serviceMyFile.saveFile(file);
        String name = principal.getName();
        model.addAttribute("name", name);
        return "redirect:/account";
    }

    @GetMapping("/deleteFile")
    public String deleteFileForm() {
        return "html/deleteFile";
    }

    @PostMapping("/deleteFile")
    public String deleteFile(@RequestParam Long id, Model model, Principal principal) {
        serviceMyFile.deleteFile(id);
        String name = principal.getName();
        model.addAttribute("name", name);
        return "redirect:/account";
    }

    @GetMapping("/getFile")
    public String getFileForm() {
        return "html/getFile";
    }

    @PostMapping("/getFile")
    public String getFile(@RequestParam Long id, Model model,Principal principal) {
        MyFile file = serviceMyFile.getFile(id, principal.getName());
        model.addAttribute("file", file);
        return "html/getFileDetails";
    }

    @GetMapping("/getFiles")
    public String getFiles(Model model) {
        List<MyFile> files = serviceMyFile.getAllFiles();
        model.addAttribute("files", files);
        return "html/getFiles";
    }
    @GetMapping("/download/{id}")

public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
    MyFile file = serviceMyFile.getFile(id);
    ByteArrayResource resource = new ByteArrayResource(file.getFile_data());
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"file_" + file.getId() + ".txt\"")
            .body(resource);
}
}
