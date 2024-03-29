package biz.brumm.thenursejavaangular.service;

import biz.brumm.thenursejavaangular.mapstructs.Mandant;
import java.util.List;
import java.util.Optional;

public interface IMandantService {
  /**
   * Find all the Mandant objects.
   *
   * @return a list of Mandant objects
   */
  List<Mandant> findAll();

  /**
   * Find a Mandant by its ID.
   *
   * @param id the ID of the Mandant to find
   * @return an Optional containing the found Mandant, or an empty Optional if not found
   */
  Optional<Mandant> find(Long id);

  /**
   * Creates a new Mandant.
   *
   * @param item the Mandant object to be created
   * @return the created Mandant object
   */
  Mandant create(Mandant item);

  /**
   * Update a Mandant with the given id.
   *
   * @param id the id of the Mandant to update
   * @param newItem the new Mandant object
   * @return an Optional containing the updated Mandant, or empty if not found
   */
  Optional<Mandant> update(Long id, Mandant newItem);

  /**
   * Delete an item by its ID.
   *
   * @param id the ID of the item to be deleted
   * @return void
   */
  void delete(Long id);
}
