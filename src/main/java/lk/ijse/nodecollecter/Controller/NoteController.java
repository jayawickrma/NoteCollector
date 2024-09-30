package lk.ijse.nodecollecter.Controller;

import lk.ijse.nodecollecter.DTO.IMPL.NoteDTO;
import lk.ijse.nodecollecter.Entity.EntityIMPL.UserEntity;
import lk.ijse.nodecollecter.Utill.AppUtill;
import lk.ijse.nodecollecter.service.NoteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/notes")
public class NoteController {
    @Autowired
    private NoteServices noteServices;
    //consumes -frontend eken database ekt yawana data
    //produces -database eken frontend ekata yawana data
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDTO> saveNote(@RequestBody NoteDTO noteDTO){
        try{
            noteServices.saveNote(noteDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataAccessException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }
    @GetMapping(value = "/{noteId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public void getSelectedNote(@PathVariable("noteId") String noteID){
        noteServices.getNote(noteID);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteDTO>  getAllNotes(){
        return noteServices.getAllNotes();
    }
    @DeleteMapping(value = "/{noteId}")
    public void deleteNote(@PathVariable("noteId") String noteID){
        noteServices.deleteNote(noteID);
    }
    @PutMapping
    public void updateNote(String noteID,NoteDTO noteDTO){

    }
}
