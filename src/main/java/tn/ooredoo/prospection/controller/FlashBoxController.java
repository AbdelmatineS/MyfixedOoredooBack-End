package tn.ooredoo.prospection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import tn.ooredoo.prospection.entity.FlashBox;
import tn.ooredoo.prospection.service.FlashBoxService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("FlashBox")
@CrossOrigin(origins = {"http://localhost:8100", "http://172.19.3.54:8100"})
public class FlashBoxController {
    @Autowired
    FlashBoxService flashBoxService;

    @PostMapping("/add")
    public FlashBox add(@RequestBody FlashBox flashBox) {
        // Vérifiez si "Level 4" est sélectionné et mettez à jour la propriété isChecked en conséquence
        if ("Level 4".equals(flashBox.getIsChecked())) {
            flashBox.setIsChecked(null);
        } else {
            flashBox.setIsChecked("Level 4");
        }

        // Enregistrez l'objet FlashBox dans la base de données
        return flashBoxService.add(flashBox);
    }

    @PostMapping("/uploadImages")
    public void uploadImages(@RequestBody FlashBox flashBox,
                             @RequestParam("preuvesImage") MultipartFile preuvesImage,
                             @RequestParam("contratImage") MultipartFile contratImage,
                             @RequestParam("justificatifImage") MultipartFile justificatifImage,
                             @RequestParam("conditionsImage") MultipartFile conditionsImage) {
        try {
            // Check if the image files are not empty
            if (!preuvesImage.isEmpty()) {
                flashBox.setPreuves(preuvesImage.getBytes());
            }
            if (!contratImage.isEmpty()) {
                flashBox.setContrat(contratImage.getBytes());
            }
            if (!justificatifImage.isEmpty()) {
                flashBox.setJustificatif(justificatifImage.getBytes());
            }
            if (!conditionsImage.isEmpty()) {
                flashBox.setConditions(conditionsImage.getBytes());
            }

            // Save the FlashBox entity to the database
            flashBoxService.add(flashBox);

            // Display a success message
            System.out.println("Images saved successfully!");
        } catch (IOException e) {
            // Handle errors during image saving
            System.out.println("An error occurred while saving the images: " + e.getMessage());
        }
    }





    @GetMapping("/search")
    public List<FlashBox> search(@RequestParam("msisdn") String msisdn) {
        if (msisdn.isEmpty()) {

            return Collections.emptyList();         }

        String prefix = msisdn.substring(0, 1);

        List<FlashBox> resultList = flashBoxService.findByMsisdnStartingWith(prefix);

        return resultList;
    }
    @GetMapping("/getAvailableMsisdns")
    public List<String> getAvailableMsisdns() {
        List<String> availableMsisdns = flashBoxService.getAvailableMsisdns();
        return availableMsisdns;
    }

}




