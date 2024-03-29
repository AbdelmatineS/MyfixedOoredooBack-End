package tn.ooredoo.prospection.service;

import java.util.List;

import tn.ooredoo.prospection.entity.Reservation;


public interface IReservationService {
	
	public Reservation retrieveReservationById(Long id);
	public List<Reservation> retrieveAllReservation();
	//public Reservation addReservation(Reservation r);
	public Reservation addReservation(Long id, Reservation r);
	public Reservation updateReservation(Reservation r, Long id);
	public void deleteReservation(Long id);
	public Reservation getLastReservation();

}