package com.example.C_Odontoprev.controllers;

import com.example.C_Odontoprev.DTOs.ClientRecordDto;
import com.example.C_Odontoprev.models.ClientModel;
import com.example.C_Odontoprev.repositories.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ClientController {

    @Autowired
    ClientRepository clientRepository;


    @PostMapping("/cadastro")
    public ResponseEntity<ClientModel> saveProduct(@RequestBody @Valid ClientRecordDto clientRecordDto) { //Ele fala que vai ter um retorno pro ClientModel, No RequestBody o metodo recebe como corpo o ClientRecordDto, e o Valid serve para a validação
        var ClientModel = new ClientModel(); //Criacao de um objeto novo do Model que sera inserido no BD
        BeanUtils.copyProperties(clientRecordDto, ClientModel); // Conversao do Dto para o Model, usando o Beans Utils
        return ResponseEntity.status(HttpStatus.CREATED).body(clientRepository.save(ClientModel));// O retorno precisa ser coerente então reotrnamos que primeiro foi criado o post, e depois enviamos o que foi salvo no Model
    }

    //GET = READ
    @GetMapping("/cadastro")
    public ResponseEntity<List<ClientModel>> getAllClient() {
        List<ClientModel> ClientList = clientRepository.findAll();
        if (!ClientList.isEmpty()) {
            for (ClientModel client : ClientList) {
                UUID id = client.getId();
                client.add(linkTo(methodOn(ClientController.class).getOneClient(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(ClientList);
    }

    // GET - READ, BUT NOW BY ID
    @GetMapping("cadastro/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value="id") UUID id) {
        Optional<ClientModel> client0 = clientRepository.findById(id);
        if(client0.isEmpty()) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("cadastro not found");
        }
        client0.get().add(linkTo(methodOn(ClientController.class).getAllClient()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(client0.get());
    }

    @PutMapping("/cadastro/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable (value="id") UUID id,
                                                @RequestBody @Valid ClientRecordDto clientRecordDto) {
        Optional<ClientModel> client0 = clientRepository.findById(id);
        if (client0.isEmpty()) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("cadastro not found");
        }
        var clientModel = client0.get();
        BeanUtils.copyProperties(clientRecordDto, clientModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.save(clientModel));

    }

    //DELETE
    @DeleteMapping("/cadastro/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable (value="id") UUID id) {
        Optional<ClientModel> client0 = clientRepository.findById(id);
        if(client0.isEmpty()) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("cadastro not found");
        }
        clientRepository.delete(client0.get());
        return ResponseEntity.status(HttpStatus.OK).body("cadastro deleted successfully");
    }
}
