package tn.ooredoo.prospection.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.ooredoo.prospection.entity.FixeJdid;
import tn.ooredoo.prospection.service.FixeJdidService;

import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:8100", "http://172.19.3.54:8100"})
@RestController
@RequestMapping("FixeJdid")
public class FixeJdidController {
    @Autowired
    private FixeJdidService fixeJdidService;
    private FixeJdid fixeJdid;
    public FixeJdidController(FixeJdidService fixeJdidService) {
        this.fixeJdidService = fixeJdidService;
        this.fixeJdid = new FixeJdid(); // Créer une instance de SuperBox
    }

    @PostMapping("/ajouter")
    public FixeJdid addFixeJdid(@RequestBody FixeJdid userFixeJdid) {
        fixeJdid.setId(userFixeJdid.getId());
        fixeJdid.setAbonnement(userFixeJdid.getAbonnement());
        fixeJdid.setImei(userFixeJdid.getImei());
        fixeJdid.setDebit(userFixeJdid.getDebit());
        fixeJdid.setContrats(userFixeJdid.getContrats());
        fixeJdid.setFormulaire(userFixeJdid.getFormulaire());
        fixeJdid.setConditions(userFixeJdid.getConditions());
        fixeJdid.setKitcode(userFixeJdid.getKitcode());
        fixeJdid.setMsisdn(userFixeJdid.getMsisdn());
        fixeJdid.setPreuves(userFixeJdid.getPreuves());
        fixeJdid.setType(userFixeJdid.getType());
        fixeJdid.setSignatureImage(userFixeJdid.getSignatureImage());
        return fixeJdidService.addFixeJdid(fixeJdid);
    }
    @PostMapping("/add")
    public ResponseEntity<FixeJdid> add(@RequestBody Map<String, Object> requestData) {
        try {
            String signatureDataURL = (String) requestData.get("signature");
            String base64Image = signatureDataURL.replace("data:image/png;base64,", "");
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            fixeJdid.setSignatureImage(imageBytes);
            // Copiez d'autres propriétés de requestData vers superBox
            FixeJdid addedFixJdid= fixeJdidService.addFixeJdid(fixeJdid);
            return ResponseEntity.ok().body(addedFixJdid);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/signature/{id}")
    public ResponseEntity<byte[]> getSignatureImage(@PathVariable Integer id) {
        try {
            FixeJdid fixeJdid = fixeJdidService.getById(id);
            byte[] signatureImage = fixeJdid.getSignatureImage();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentLength(signatureImage.length);

            return new ResponseEntity<>(signatureImage, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @GetMapping("/images/{id}/{columnName}")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id, @PathVariable String columnName) {
        // Récupérez l'objet FixeJdid correspondant à l'ID spécifié depuis la base de données
        FixeJdid fixe = fixeJdidService.getById(id);

        // Déterminez la colonne à partir de laquelle vous souhaitez récupérer l'image
        byte[] imageBytes;
        if (columnName.equals("formulaire")) {
            imageBytes = fixe.getFormulaire();
        } else if (columnName.equals("conditions")) {
            imageBytes = fixe.getConditions();
        } else if (columnName.equals("preuves")) {
            imageBytes = fixe.getPreuves();
        } else if (columnName.equals("contrats")) {
            imageBytes = fixe.getContrats();
        } else {
            // Gérez le cas où le nom de la colonne spécifié n'est pas valide
            return ResponseEntity.notFound().build();
        }

        // Vérifiez si l'image existe
        if (imageBytes == null) {
            return ResponseEntity.notFound().build();
        }

        // Définissez les en-têtes de la réponse pour indiquer le type de contenu
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Remplacez par le type de contenu approprié si nécessaire

        // Retournez la réponse avec les données de l'image
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<FixeJdid> search(@RequestParam("msisdn") String msisdn) {
        if (msisdn.isEmpty()) {
            return Collections.emptyList();         }

        String prefix = msisdn.substring(0, 1);

        List<FixeJdid> resultList = fixeJdidService.findByMsisdnStartingWith(prefix);

        return resultList;
    }
    @GetMapping("/getAvailableMsisdns")
    public List<String> getAvailableMsisdns() {
        List<String> availableMsisdns = fixeJdidService.getAvailableMsisdns();
        return availableMsisdns;
    }


}
