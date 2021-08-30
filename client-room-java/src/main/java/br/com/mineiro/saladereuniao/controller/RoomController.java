package br.com.mineiro.saladereuniao.controller;

import br.com.mineiro.saladereuniao.exception.ResourceNotFoundException;
import br.com.mineiro.saladereuniao.model.Room;
import br.com.mineiro.saladereuniao.repository.RoomRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @PostMapping("/rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public Room createRoom(@Valid @RequestBody Room room) {
        return roomRepository.save(room);
    }

    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/rooms/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Room> getRoomById(
            @PathVariable(value = "id") Long roomId)
            throws ResourceNotFoundException {

        Room room = verifyIfExists(roomId);
        return ResponseEntity.ok().body(room);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(
            @PathVariable(value = "id") Long roomId,
            @Valid @RequestBody Room roomDetails)
            throws ResourceNotFoundException {

        Room room = verifyIfExists(roomId);
        room.setNome(roomDetails.getNome());
        room.setData(roomDetails.getData());
        room.setHoraInicio(roomDetails.getHoraInicio());
        room.setHoraFim(roomDetails.getHoraFim());

        final Room updateRoom = roomRepository.save(room);
        return ResponseEntity.ok(updateRoom);
    }

    @DeleteMapping("/rooms/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") Long roomId)
            throws ResourceNotFoundException {

        Room room = verifyIfExists(roomId);
        roomRepository.delete(room);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deletado", Boolean.TRUE);
        return response;
    }

    private Room verifyIfExists(Long id) throws ResourceNotFoundException {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room não encontrado por este ID:: " + id));
    }
}
