package com.example.hostingapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.hostingapi.entity.Client;
import com.example.hostingapi.exception.ResourceNotFoundException;
import com.example.hostingapi.repository.ClientRepository;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client client) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        existingClient.setName(client.getName());
        existingClient.setEmail(client.getEmail());
        existingClient.setPhone(client.getPhone());

        return clientRepository.save(existingClient);
    }

    public void deleteClient(Long id) {

        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente no encontrado");
        }

        clientRepository.deleteById(id);
    }
}
