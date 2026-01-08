/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;


import Entities.AppointmentNoteEntity;
import Repositories.AppointmentNoteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class AppointmentNoteRepositoryImpl
        implements AppointmentNoteRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public AppointmentNoteEntity save(AppointmentNoteEntity note) {
        em.persist(note);
        return note;
    }

    @Override
    public AppointmentNoteEntity update(AppointmentNoteEntity note) {
        return em.merge(note);
    }

    @Override
    public Optional<AppointmentNoteEntity> findById(UUID id) {
        return Optional.ofNullable(
                em.find(AppointmentNoteEntity.class, id)
        );
    }

    @Override
    public List<AppointmentNoteEntity> findByAppointmentId(UUID appointmentId) {
        TypedQuery<AppointmentNoteEntity> query =
                em.createQuery("""
                    SELECT n
                    FROM AppointmentNoteEntity n
                    WHERE n.appointment.id = :appointmentId
                    ORDER BY n.createdAt DESC
                """, AppointmentNoteEntity.class);

        return query
                .setParameter("appointmentId", appointmentId)
                .getResultList();
    }

    @Override
    public List<AppointmentNoteEntity> findByDoctorId(UUID doctorId) {
        TypedQuery<AppointmentNoteEntity> query =
                em.createQuery("""
                    SELECT n
                    FROM AppointmentNoteEntity n
                    WHERE n.createdBy.id = :doctorId
                    ORDER BY n.createdAt DESC
                """, AppointmentNoteEntity.class);

        return query
                .setParameter("doctorId", doctorId)
                .getResultList();
    }

    @Override
    public void delete(AppointmentNoteEntity note) {
        AppointmentNoteEntity managed =
                em.contains(note) ? note : em.merge(note);
        em.remove(managed);
    }
}

