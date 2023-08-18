package tn.ooredoo.prospection.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.ooredoo.prospection.entity.FastBox;
import tn.ooredoo.prospection.repository.FastBoxRepo;

import java.util.List;
import java.util.Optional;

@Service
public class FastBoxServiceImp implements FastBoxService{
    @Autowired
    private  FastBoxRepo fastBoxRepo;

    @Override
    public FastBox ajouter(FastBox fastBox) {

        return fastBoxRepo.save(fastBox);
    }

    @Override
    public FastBox addFastBox(FastBox fastBox) {
        return fastBoxRepo.save(fastBox);

    }

    @Override
    public FastBox add(FastBox fastBox) {
        return fastBoxRepo.save(fastBox);
    }
    @Override
    public FastBox save(FastBox fastBox) {

        return fastBoxRepo.save(fastBox);
    }

    @Override
    public FastBox getList(Integer id) {
        return fastBoxRepo.findById(id).get();
    }

    @Override
    public List<FastBox> getAllList() {
        return fastBoxRepo.findAll();
    }

    @Override
    public FastBox update(FastBox fastBox, Integer id) {
        // Vérifier si l'entité existe dans la base de données
        Optional<FastBox> fastBoxOptional = fastBoxRepo.findById(id);
        if (fastBoxOptional.isPresent()) {
            FastBox existingFastBox= fastBoxOptional.get();
        // Mettre à jour les propriétés de l'entité existante
            existingFastBox.setAbonnement(fastBox.getAbonnement());
            existingFastBox.setCategorie(fastBox.getCategorie());
            existingFastBox.setDebit(fastBox.getDebit());
            existingFastBox.setMsisdn(fastBox.getMsisdn());
            existingFastBox.setNumeroserie(fastBox.getNumeroserie());
            existingFastBox.setPrix(fastBox.getPrix());
            existingFastBox.setNumeroTT(fastBox.getNumeroTT());
            existingFastBox.setSignatureImage(fastBox.getSignatureImage());

            // Enregistrer les modifications dans la base de données
            return fastBoxRepo.save(existingFastBox);
        } else {
            // Si l'entité n'existe pas, retourner null ou lancer une exception
            return null;
        }
    }

    @Override
    public FastBox getById(Integer id) {
        return fastBoxRepo.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        fastBoxRepo.deleteById(id);

    }
}
