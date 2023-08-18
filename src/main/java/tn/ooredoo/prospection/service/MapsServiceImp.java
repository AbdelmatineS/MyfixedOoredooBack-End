package tn.ooredoo.prospection.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.ooredoo.prospection.entity.Maps;
import tn.ooredoo.prospection.repository.MapsRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MapsServiceImp implements MapsService{
    @Autowired
    private MapsRepo mapsRepo;
    @Override
    public Maps addMaps(Maps maps) {
        maps.setDate_creation(LocalDateTime.now());
        //maps.setDateDernièreModification(LocalDateTime.now());
        return mapsRepo.save(maps);
    }


    @Override
    public Maps getMaps(Integer id) {

        return mapsRepo.findById(id).get();
    }

    @Override
    public List<Maps> getAllMaps() {
        return mapsRepo.findAll();
    }

    @Override
    public Maps update(Maps maps, Integer id) {
        // Vérifier si l'entité existe dans la base de données
        Optional<Maps> mapsOptional = mapsRepo.findById(id);
        if (mapsOptional.isPresent()) {
            // Mettre à jour les propriétés de l'entité existante
            Maps existingMaps = mapsOptional.get();
            existingMaps.setLongitude(maps.getLongitude());
            existingMaps.setLatitude(maps.getLatitude());
            existingMaps.setEtat(maps.getEtat());
            existingMaps.setDate_creation(maps.getDate_creation());
            existingMaps.setAdresse(maps.getAdresse());

            // Enregistrer les modifications dans la base de données
            return mapsRepo.save(existingMaps);
        } else {
            // Si l'entité n'existe pas, retourner null ou lancer une exception
            return null;
        }
    }
    @Override
    public void delete(Integer id) {
        mapsRepo.deleteById(id);

    }
}
