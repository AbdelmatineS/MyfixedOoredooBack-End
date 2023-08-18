package tn.ooredoo.prospection.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.ooredoo.prospection.entity.FastBox;
import tn.ooredoo.prospection.repository.FastBoxRepo;
import tn.ooredoo.prospection.service.FastBoxService;

import java.util.*;
import java.util.List;

@RestController
@RequestMapping("FastBox")
@CrossOrigin(origins = {"http://localhost:8100", "http://172.19.3.54:8100"})
public class FastBoxController {
    @Autowired
    private FastBoxService fastBoxService;
    @Autowired
    private   FastBoxRepo fastBoxRepo;
    private FastBox fastBox;
    @Autowired
    public FastBoxController(FastBoxService fastBoxService) {
        this.fastBoxService = fastBoxService;
        this.fastBox = new FastBox(); // Initialisation de l'instance partagée
    }


    @GetMapping("/verify/{numeroTT}")
   public boolean verifyClient(@PathVariable String numeroTT) {
        return fastBoxRepo.existsByNumeroTT(numeroTT);
    }
    @PostMapping("/ajouter")
    public FastBox add (@RequestBody FastBox fastBox){

        return fastBoxService.add(fastBox);
    }
    @PostMapping("/add")
    public ResponseEntity<FastBox> add(@RequestBody Map<String, Object> requestData) {
        try {
            String signatureDataURL = (String) requestData.get("signature");
            String base64Image = signatureDataURL.replace("data:image/png;base64,", "");
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            FastBox fastBox = new FastBox();
            fastBox.setSignatureImage(imageBytes);
            FastBox addedFastBox = fastBoxService.ajouter(fastBox);
            return ResponseEntity.ok().body(addedFastBox);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/signature/{id}")
    public ResponseEntity<byte[]> getSignatureImage(@PathVariable Integer id) {
        try {
            FastBox fastBox = fastBoxService.getById(id);
            byte[] signatureImage = fastBox.getSignatureImage();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentLength(signatureImage.length);

            return new ResponseEntity<>(signatureImage, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }







    @GetMapping("/list")
    public List<FastBox> finAllFastBox(){

        return fastBoxService.getAllList();
    }
    @PutMapping("update/{id}")
    public FastBox update(@PathVariable Integer id, @RequestBody FastBox fastBox) {

        FastBox exitingFastBox = fastBoxService.getList(id);
        exitingFastBox.setDebit(fastBox.getDebit());
        exitingFastBox.setPrix(fastBox.getPrix());
        exitingFastBox.setNumeroserie(fastBox.getNumeroserie());
        exitingFastBox.setMsisdn(fastBox.getMsisdn());
        exitingFastBox.setNumeroTT(fastBox.getNumeroTT());
        exitingFastBox.setCategorie(fastBox.getCategorie());
        exitingFastBox.setAbonnement(fastBox.getAbonnement());
        exitingFastBox.setClientPossedeNumero(fastBox.isClientPossedeNumero());
        exitingFastBox.setSignatureImage(fastBox.getSignatureImage());

        return fastBoxService.add(exitingFastBox);


    }
    @DeleteMapping("delete/{id}")
    public void delete (@PathVariable Integer id ){
        fastBoxService.delete(id);
    }
}
