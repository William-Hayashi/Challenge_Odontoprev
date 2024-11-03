package com.example.C_Odontoprev.controllers;

import com.example.C_Odontoprev.DTOs.EnderecoRecordDto;
import com.example.C_Odontoprev.models.EnderecoModel;
import com.example.C_Odontoprev.repositories.EnderecoRepository;
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
public class EnderecoController {

    @Autowired
    EnderecoRepository enderecoRepository;


    @PostMapping("/endereco")
    public ResponseEntity<EnderecoModel> saveProduct(@RequestBody @Valid EnderecoRecordDto enderecoRecordDto) { //Ele fala que vai ter um retorno pro ClientModel, No RequestBody o metodo recebe como corpo o ClientRecordDto, e o Valid serve para a validação
        var enderecoModel = new EnderecoModel(); //Criacao de um objeto novo do Model que sera inserido no BD
        BeanUtils.copyProperties(enderecoRecordDto, enderecoModel); // Conversao do Dto para o Model, usando o Beans Utils
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoRepository.save(enderecoModel));// O retorno precisa ser coerente então reotrnamos que primeiro foi criado o post, e depois enviamos o que foi salvo no Model
    }

    //GET = READ
    @GetMapping("/endereco")
    public ResponseEntity<List<EnderecoModel>> getAllClient() {
        List<EnderecoModel> EnderecoList = enderecoRepository.findAll();
        if (!EnderecoList.isEmpty()) {
            for (EnderecoModel endereco : EnderecoList) {
                UUID id = endereco.getId_endereco();
                endereco.add(linkTo(methodOn(ClientController.class).getOneClient(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(EnderecoList);
    }

    // GET - READ, BUT NOW BY ID
    @GetMapping("endereco/{id_endereco}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value="id_endereco") UUID id) {
        Optional<EnderecoModel> endereco0 = enderecoRepository.findById(id);
        if(endereco0.isEmpty()) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("endereco not found");
        }
        endereco0.get().add(linkTo(methodOn(ClientController.class).getAllClient()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(endereco0.get());
    }

    @PutMapping("/endereco/{id_endereco}")
    public ResponseEntity<Object> updateClient(@PathVariable (value="id_endereco") UUID id,
                                               @RequestBody @Valid EnderecoRecordDto enderecoRecordDto) {
        Optional<EnderecoModel> endereco0 = enderecoRepository.findById(id);
        if (endereco0.isEmpty()) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("endereco not found");
        }
        var enderecoModel = endereco0.get();
        BeanUtils.copyProperties(enderecoRecordDto, enderecoModel);
        return ResponseEntity.status(HttpStatus.OK).body(enderecoRepository.save(enderecoModel));
    }

    //DELETE
    @DeleteMapping("/endereco/{id_endereco}")
    public ResponseEntity<Object> deleteClient(@PathVariable (value="id_endereco") UUID id) {
        Optional<EnderecoModel> endereco0 = enderecoRepository.findById(id);
        if(endereco0.isEmpty()) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("endereco not found");
        }
        enderecoRepository.delete(endereco0.get());
        return ResponseEntity.status(HttpStatus.OK).body("endereco deleted successfully");
    }
}
