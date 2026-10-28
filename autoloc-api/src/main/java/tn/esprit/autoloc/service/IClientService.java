package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Client;

import java.util.List;

public interface IClientService {

    Client createClient(Client client);

    Client getClientById(Long idClient);

    Client updateClient(Client client);

    void deleteClient(Long idClient);

    List<Client> getAllClients();
}
