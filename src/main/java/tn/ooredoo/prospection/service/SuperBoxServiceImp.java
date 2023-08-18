package tn.ooredoo.prospection.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.ooredoo.prospection.entity.SuperBox;
import tn.ooredoo.prospection.repository.SuperBoxRepo;

import java.util.List;
import java.util.Optional;

@Service
public class SuperBoxServiceImp implements SuperBoxService{
    @Autowired
    SuperBoxRepo superBoxRepo;

    @Override
    public SuperBox ajouter(SuperBox superBox) {
        return superBoxRepo.save(superBox);

    }

    @Override
    public SuperBox addSuperBox(SuperBox superBox) {

        return superBoxRepo.save(superBox);
    }
    @Override
    public SuperBox save(SuperBox superBox) {

        return superBoxRepo.save(superBox);
    }

    @Override
    public SuperBox getList(Integer id) {
        return superBoxRepo.findById(id).get();
    }
    @Override
    public SuperBox getById(Integer id) {
        return superBoxRepo.findById(id).orElse(null);
    }

    @Override
    public List<SuperBox> getAllList() {
        return superBoxRepo.findAll();
    }

    @Override
    public SuperBox update(SuperBox superBox, Integer id) {
        // Vérifier si l'entité existe dans la base de données
        Optional<SuperBox> superBoxOptional = superBoxRepo.findById(id);
        if (superBoxOptional.isPresent()) {
            // Mettre à jour les propriétés de l'entité existante
            SuperBox existingSuperBox = superBoxOptional.get();
            existingSuperBox.setChoix(superBox.getChoix());
            existingSuperBox.setMsisdn(superBox.getMsisdn());
            existingSuperBox.setIccid(superBox.getIccid());
            existingSuperBox.setZonerecherche(superBox.getZonerecherche());


            // Enregistrer les modifications dans la base de données
            return superBoxRepo.save(existingSuperBox);
        } else {
            // Si l'entité n'existe pas, retourner null ou lancer une exception
            return null;
        }
    }


    @Override
    public void delete(Integer id) {
        superBoxRepo.deleteById(id);

    }

}
